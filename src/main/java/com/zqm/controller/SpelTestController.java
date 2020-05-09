package com.zqm.controller;

import com.zqm.vo.Person;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @describe:  Spring的SpEL可以单独使用，可以使用SpEL对表达式计算、求值。SpEL主要提供了如下三个接口(位于org.springframework.expression包)：
 * <p>
 * ExpressionParser：该接口的实例负责解析一个SpEL表达式，返回一个Expression对象
 * Expression：该接口的实例代表一个表达式
 * EvaluationContext：代表计算表达式值的上下文，当SpEL表达式中含有变量时，程序将需要使用该API来计算表达式的值
 *   Expression实例代表一个表达式，它包含了如下方法用于计算，得到表达式的值：
 * <p>
 * Object getValue()：计算表达式的值
 * <T> T getValue(Class<T> desiredResultType)：计算表达式的值，而且尝试将该表达式的值当成desiredResultType类型处理
 * Object getValue(EvaluationContext context)：使用指定的EvaluationContext来计算表达式的值（其中EvaluationContext相当于容器包含着Expression表达式中所需要用到的值，如果是根对象，那么连对象名称都可以省略直接使用）
 * <T> T getValue(EvaluationContext context,Class<T> desiredResultType)：使用指定的EvaluationContext来计算表达式的值，而且尝试将该表达式的值当成desiredResultType类型处理
 * Object getValue(Object rootObject)：以rootObject作为表达式的root对象来计算表达式的值
 * <T> T getValue(Object rootObject,Class<T> desiredResultType)：以rootObject作为表达式的root对象来计算表达式的值，而且尝试将该表达式的值当成desiredResultType类型处理
 * @author:zqm
 */
public class SpelTestController {


    public static void main(String[] args) {
        // 创建一个ExpressionParser对象，用于解析表达式
        ExpressionParser parser = new SpelExpressionParser();
        // 最简单的字符串表达式
        Expression exp = parser.parseExpression("'HelloWorld'");
        System.out.println("'HelloWorld'的结果： " + exp.getValue());
        // 调用方法的表达式
        exp = parser.parseExpression("'HelloWorld'.concat('!')");
        System.out.println("'HelloWorld'.concat('!')的结果： " + exp.getValue());
        // 调用对象的getter方法
        exp = parser.parseExpression("'HelloWorld'.bytes");
        System.out.println("'HelloWorld'.bytes的结果： " + exp.getValue());
        // 访问对象的属性(d相当于HelloWorld.getBytes().length)
        exp = parser.parseExpression("'HelloWorld'.bytes.length");
        System.out.println("'HelloWorld'.bytes.length的结果：" + exp.getValue());
        // 使用构造器来创建对象
        exp = parser.parseExpression("new String('helloworld')" + ".toUpperCase()");
        System.out.println("new String('helloworld')" + ".toUpperCase()的结果是： " + exp.getValue(String.class));
        Person person = new Person(1, "孙悟空", new Date());
        exp = parser.parseExpression("name");
        // 以指定对象作为root来计算表达式的值
        // 相当于调用person.name表达式的值
        System.out.println("以persn为root，name表达式的值是： " + exp.getValue(person, String.class));
        exp = parser.parseExpression("name=='孙悟空'");

        ;
// 创建一个List
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Spring");
// 创建一个EvaluationContext对象，作为SpEL解析变量的上下文
        EvaluationContext ctx = new StandardEvaluationContext();
// 设置一个变量
        ctx.setVariable("myList", list);
// 对集合的第一个元素进行赋值
        parser.parseExpression("#myList[0]='我爱你中国'").getValue(ctx);
// 下面测试输出
        System.out.println("List更改后的第一个元素的值为：" + list.get(0));
// 使用三目运算符
        System.out.println(parser.parseExpression("#myList.size()>3 ? 'myList长度大于3':'myList长度不大于于3'").getValue(ctx));

    }
}
