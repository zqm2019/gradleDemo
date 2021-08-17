package com.zqm.aop.interceptor;

import com.alibaba.fastjson.JSON;
import com.zqm.enums.ResultCode;
import com.zqm.vo.Result;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * TODO: description
 * Date: 2019-10-31
 *
 * @author zhaqianming
 */
@Configuration
@Log4j
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public MyInterceptor1 myInterceptor1() {
        return new MyInterceptor1();
    }

    @Bean
    public MyInterceptor2 myInterceptor2() {
        return new MyInterceptor2();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(myInterceptor2()).addPathPatterns("/**");
//        registry.addInterceptor(myInterceptor1()).addPathPatterns("/**");
        registry.addInterceptor(myInterceptor1());
        super.addInterceptors(registry);
    }


    /**
     * {@inheritDoc}
     * <p>This implementation is empty.
     *
     * @param exceptionResolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add((request, response, handler, e) -> {
            Result result = new Result();
            if (e instanceof IllegalArgumentException) {
                result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
            } else if (e instanceof MethodArgumentNotValidException) {
                result.setCode(ResultCode.FAIL).setMessage(handlerNotValidException((MethodArgumentNotValidException) e));
            } else if (e instanceof NoHandlerFoundException) {
//                要捕获到404异常，然后进行统一返回值的处理其实也很简单。
//                第一步，关闭springboot的异常自动资源映射，让其抛出异常：
//#出现错误时, 直接抛出异常
//                spring.mvc.throw-exception-if-no-handler-found=true
//#关闭工程中的资源文件建立映射
//                spring.resources.add-mappings=false

                result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
            } else if (e instanceof HttpRequestMethodNotSupportedException){
                result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口" + request.getRequestURI() + ((HttpRequestMethodNotSupportedException) e).getMethod() + e.getMessage());
            }
           // todo 此处还还可增加自定义其他异常,如登录异常，权限异常等等

            else {
                result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
                String message;
                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                            request.getRequestURI(),
                            handlerMethod.getBean().getClass().getName(),
                            handlerMethod.getMethod().getName(),
                            e.getMessage());
                } else {
                    message = e.getMessage();
                }
                log.error(message, e);
            }
            responseResult(response, result);
            return new ModelAndView();
        });
    }

    /**
     * 统一异常处理
     *
     * @param exceptionResolvers
     */
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        exceptionResolvers.add((request, response, handler, e)->{
////            Result result = new Result();
////            if (e instanceof ServiceException || e instanceof IllegalArgumentException) {
////                result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
////            } else if (e instanceof NoHandlerFoundException) {
////                result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
////            } else if (e instanceof ServletException) {
////                result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
////            } else if (e instanceof AuthorizationException) {
////                result.setCode(ResultCode.FORBIDDEN).setMessage(e.getMessage());
////            } else if (e instanceof AuthenticationException || e instanceof IllegalAccessException) {
////                result.setCode(ResultCode.UNAUTHORIZED).setMessage(e.getMessage());
////            } else {
////                result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
////                String message;
////                if (handler instanceof HandlerMethod) {
////                    HandlerMethod handlerMethod = (HandlerMethod) handler;
////                    message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
////                            request.getRequestURI(),
////                            handlerMethod.getBean().getClass().getName(),
////                            handlerMethod.getMethod().getName(),
////                            e.getMessage());
////                } else {
////                    message = e.getMessage();
////                }
////                logger.error(message, e);
////            }
////            responseResult(response, result);
////            return new ModelAndView();
////        });
//    }
    private void responseResult(HttpServletResponse response, Object result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    private String handlerNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuffer errorBuffer = new StringBuffer("参数异常:");
        fieldErrors.forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorBuffer.append(fieldName);
            errorBuffer.append(errorMessage);
            errorBuffer.append(";");
        });
        return errorBuffer.toString();
    }
}
