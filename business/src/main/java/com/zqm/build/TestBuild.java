
package com.zqm.build;

/**
 *  FunctionalInterface 此注解是非必须的，只要包含一个接口的方法就符合函数式接口，虚拟机自动判断
 *  最好在接口上使用注解@FunctionalInterface进行声明，以免团队的其他人员错误地往接口中添加新的方法
 * Date: 2019-07-29
 *
 * @author zhaqianming
 */
@FunctionalInterface
public interface TestBuild<A, B> {

    void build(A a, B b);

}
