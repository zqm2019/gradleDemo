package com.zqm.controller;

import com.google.common.base.Preconditions;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @describe:
 * @author:zqm
 */
@RestController
@RequestMapping("test")
public class TestValidController {


    @PostMapping(value = "test1")
    public void test(@Valid @RequestBody Param Param) {
        Preconditions.checkArgument(StringUtils.isNotBlank(Param.getId()), "id不能为空");
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(Param.getList()), "List不能为空");
        //do something
    }


    @Data
    public static class Param {
        @NotNull(message = "不能为空")
        private String id;

        @NotEmpty(message = "不能为空")
        private List<String> list;

    }



}