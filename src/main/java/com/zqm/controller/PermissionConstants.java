package com.zqm.controller;

/**
 * @author dinggh
 */
public class PermissionConstants {
    private PermissionConstants() {
    }

    /**
     * 菜单权限
     */
    public static final String ADAPT_MENU = "menu:adapt";
    public static final String ADAPT_RELATE_CREATE = "adapt:relate:create";
    public static final String ADAPT_PRODUCT_VIEW = "adapt:product:view";
    public static final String ADAPT_CARMODEL_VIEW = "adapt:carmodel:view";
    public static final String ADAPT_VIN_VIEW = "adapt:vin:view";
    public static final String ADAPT_GROUP_VIEW = "adapt:group:view";
    public static final String ADAPT_GROUP_IMPORT = "adapt:group:import";
    public static final String ADAPT_OIL_RELATE = "adapt:oil:relate";
    public static final String DELETE_TASK_VIEW = "adapt:task:delete";

    /**
     * 资料权限
     */
    public static final String ADAPT_DATUM_ADD = "adapt:datum:create";
    public static final String ADAPT_DATUM_EDIT = "adapt:datum:edit";
    public static final String ADAPT_DATUM_DELETE = "adapt:datum:delete";

    /**
     * 资料匹配权限
     */
    public static final String ADAPT_DATUMMATCH_ADD = "adapt:datumMatch:add";
    public static final String ADAPT_DATUMMATCH_DELETE = "adapt:datumMatch:delete";

    /**
     * 通用组
     */
    public static final String ADAPT_PRODUCT_CREATE = "adapt:product:create";
}
