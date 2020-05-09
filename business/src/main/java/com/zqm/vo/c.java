package com.zqm.vo;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @describe:
 * @author:zqm
 */
@Getter
@Setter
public class c {

    private static final long serialVersionUID = -6920934492324729614L;

    /**
     * 属性名id
     */
    private Integer   attributeId;

    /**
     * 属性名
     */
    private String attributeName;

    /**
     * 新康众属性值id
     */
    private Integer   attributeValueId;

    /**
     * 属性值
     */
    private String attributeValue;

    @Override
    public String toString() {
        return attributeName + ':' + attributeValue + ' ';
    }

    public static void main(String[] args) {
        List<c> attributeList = new ArrayList<>();
        c tdCspuDetailSo = new c();
        tdCspuDetailSo.setAttributeId(20000);
        tdCspuDetailSo.setAttributeValueId(218);
        c tdCspuDetailSo1 = new c();
        tdCspuDetailSo1.setAttributeId(20001);
        tdCspuDetailSo1.setAttributeValueId(218);
        attributeList.add(tdCspuDetailSo1);
        attributeList.add(tdCspuDetailSo);
        System.out.println(JSON.toJSONString(attributeList));
        attributeList.stream().sorted(Comparator.comparing(c::getAttributeId)).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(attributeList));


    }
}