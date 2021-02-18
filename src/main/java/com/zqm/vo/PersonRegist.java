package com.zqm.vo;

import com.zqm.service.OrikaRegist;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

/**
 * @describe: 对象的属性映射，跟对象的顺序位置无关。
 * @author:zqm
 */
@Component
public class PersonRegist implements OrikaRegist {

    @Override
    public void register(MapperFactory factory) {
        factory.classMap(com.zqm.vo.User.class,Person.class )
                .field("nickName", "name")
                .byDefault()
                .register();
    }
}
