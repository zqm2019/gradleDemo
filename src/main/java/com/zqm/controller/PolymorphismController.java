
package com.zqm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zqm.polymorphism.PolymorphismService;
import com.zqm.vo.CallBackRequest;

/**
 * 多态:一个实物的多种表现形态(两个或两个以上的对象在调父类中的方法时，实现了各自的内容)
 * <p>
 * 多态代码：
 * 父类 对象 = new 子类();    //父类引用指向子类对象
 * 子类 对象 = new 父类();    //不可以
 * <p>
 * 多于多态来说，编译状态(看左边的类型) ，运行状态(看右边的类型)
 * <p>
 * 实现：
 * 向上转型 : 父类 对象 = new 子类();    理解:自动类型转换
 * 向下转型 : 父类类型对象 instanceof 子类类型  => 子类类型 对象=(子类类型)父类类型的对象 ;
 * Date: 2019-10-25
 *
 * @author zhaqianming
 */
@RestController
@RequestMapping("polymorphism")
public class PolymorphismController {

    @Autowired
    private PolymorphismService polymorphismService;

    @RequestMapping(value = "callBack", method = RequestMethod.POST)
    public boolean createCallBack(@RequestBody CallBackRequest callBackRequest) throws Exception {
        if (callBackRequest == null || StringUtils.isEmpty(callBackRequest.getStrContext())) {
            throw new Exception("参数不正确");
        }
        return polymorphismService.createCallBack(callBackRequest.getnType(), callBackRequest.getStrContext());
    }
}
