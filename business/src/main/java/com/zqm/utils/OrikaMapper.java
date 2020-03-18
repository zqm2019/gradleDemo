package com.zqm.utils;

import com.google.common.collect.Lists;
import com.zqm.service.OrikaRegist;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * @describe:
 * @author:zqm
 */
@Component
public class OrikaMapper {


    private MapperFacade mapperFacade = null;

    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    @Autowired(required = false)
    private List<OrikaRegist> orikaRegistyList = Lists.newArrayList();
    /**
     * 初始化Orika注册
     */
    @PostConstruct
    private void init() {

        if (!orikaRegistyList.isEmpty()) {
            for (OrikaRegist orikaRegist : orikaRegistyList) {
                orikaRegist.register(mapperFactory);
            }
        }

        mapperFacade = mapperFactory.getMapperFacade();
    }



    public  <V, P> P convert(V base, Class<P> target) {

        return mapperFacade.map(base, target);
    }

    public <V, P> List<P> convertList(List<V> baseList, Class<P> target) {
        List<P> list = CollectionUtils.isEmpty(baseList) ? Collections.<P>emptyList() : mapperFacade.mapAsList(baseList, target);

        return list;
    }

}
