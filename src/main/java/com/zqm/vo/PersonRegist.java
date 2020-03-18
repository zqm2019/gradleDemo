package com.zqm.vo;

import com.zqm.service.OrikaRegist;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

/**
 * @describe:
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
