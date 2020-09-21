package com.zqm.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zqm.sentinel.BlockHandler;
import com.zqm.service.SentinelService;
import org.springframework.stereotype.Service;

/**
 * @describe:
 * @author:zqm
 */
@Service
public class SentinelServiceImpl implements SentinelService {

    @Override
    @SentinelResource(value = "doSomething", blockHandlerClass = BlockHandler.class, blockHandler = "doSomething" )
    public void doSometing() {
        System.out.println("dfadsf");
    }
}
