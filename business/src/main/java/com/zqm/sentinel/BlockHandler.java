package com.zqm.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: gaodonghui
 * @Date: 2020/8/31 19:02
 * @Description:
 */
public class BlockHandler {
    private static final Logger logger = LoggerFactory.getLogger(BlockHandler.class);

    public void doSomething(BlockException blockException) {
        logger.warn("Blocked by Sentinel getCommonKzSkuCodes");
        throw new RuntimeException("Blocked by Sentinel getCommonKzSkuCodes");
    }

}
