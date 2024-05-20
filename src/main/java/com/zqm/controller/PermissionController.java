package com.zqm.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe:
 * @author:zqm
 */
@RestController
public class PermissionController {

    /**
     * 获取车型属性全部清单
     *
     * @return
     */
    @RequestMapping("getParams")
    @RequiresPermissions(PermissionConstants.ADAPT_MENU)
    public String getVehicleParamList() {
        return "OK";
    }
}
