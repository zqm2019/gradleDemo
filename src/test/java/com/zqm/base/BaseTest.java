
package com.zqm.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/*
 **调用MockMvc.perform(RequestBuilder requestBuilder)后将得到ResultActions，通过ResultActions完成如下三件事：
 **ResultActions andExpect(ResultMatcher matcher) ：添加验证断言来判断执行请求后的结果是否是预期的；
 **ResultActions andDo(ResultHandler handler) ：添加结果处理器，用于对验证成功后执行的动作，如输出下请求/结果信息用于调试；
 **MvcResult andReturn() ：返回验证成功后的MvcResult；用于自定义验证/下一步的异步处理；
 **
 **1.ResultMatcher用来匹配执行完请求后的结果验证，其就一个match(MvcResult result)断言方法，如果匹配失败将抛出相应的异常；spring mvc测试框架提供了很多***ResultMatchers来满足测试需求。注意这些***ResultMatchers并不是ResultMatcher的子类，而是返回ResultMatcher实例的。Spring mvc测试框架为了测试方便提供了MockMvcResultMatchers静态工厂方法方便操作；
 **
 **2.具体的API如下：
 **HandlerResultMatchers handler()：请求的Handler验证器，比如验证处理器类型/方法名；此处的Handler其实就是处理请求的控制器；
 **RequestResultMatchers request()：得到RequestResultMatchers验证器；
 **ModelResultMatchers model()：得到模型验证器；
 **ViewResultMatchers view()：得到视图验证器；
 **FlashAttributeResultMatchers flash()：得到Flash属性验证；
 **StatusResultMatchers status()：得到响应状态验证器；
 **HeaderResultMatchers header()：得到响应Header验证器；
 **CookieResultMatchers cookie()：得到响应Cookie验证器；
 **ContentResultMatchers content()：得到响应内容验证器；
 **JsonPathResultMatchers jsonPath(String expression, Object ... args)/ResultMatcher jsonPath(String expression, Matcher matcher)：得到Json表达式验证器；
 **XpathResultMatchers xpath(String expression, Object... args)/XpathResultMatchers xpath(String expression, Map<string, string=""> namespaces, Object... args)：得到Xpath表达式验证器；
 **ResultMatcher forwardedUrl(final String expectedUrl)：验证处理完请求后转发的url（绝对匹配）；
 **ResultMatcher forwardedUrlPattern(final String urlPattern)：验证处理完请求后转发的url（Ant风格模式匹配，@since spring4）；
 **ResultMatcher redirectedUrl(final String expectedUrl)：验证处理完请求后重定向的url（绝对匹配）；
 **ResultMatcher redirectedUrlPattern(final String expectedUrl)：验证处理完请求后重定向的url（Ant风格模式匹配，@since spring4）；
 **
 */

//   单元测试的测试类一定要和启动类在同一个根目录下
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebAppConfiguration
@SpringBootConfiguration
public abstract class BaseTest {
    @Autowired
    private WebApplicationContext context;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
