package com.zqm.aop.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
}
