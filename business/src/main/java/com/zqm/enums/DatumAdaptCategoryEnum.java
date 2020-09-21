package com.zqm.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @describe: 资料和配件类目的关联
 * @author:zqm
 */
@Getter
public enum DatumAdaptCategoryEnum {
    OIL_CATEGORY(10000016,6301)
    ;

    private Integer datumCategoryId;

    private Integer adaptCategoryId;

    public static final Map<Integer,Integer> datumAdaptMap = new HashMap<>();
    static {
        Arrays.stream(DatumAdaptCategoryEnum.values()).forEach((DatumAdaptCategoryEnum t) -> datumAdaptMap.put(t.getDatumCategoryId(), t.getDatumCategoryId()));
    }
    DatumAdaptCategoryEnum(Integer datumCategoryId, Integer adaptCategoryId) {
        this.datumCategoryId = datumCategoryId;
        this.adaptCategoryId = adaptCategoryId;
    }

    static {
        for (DatumAdaptCategoryEnum roleTypeEnum : DatumAdaptCategoryEnum.values()) {
            datumAdaptMap.put(roleTypeEnum.getDatumCategoryId(), roleTypeEnum.getAdaptCategoryId());
        }
    }

    private static final Map<Integer,Integer> map;
    static {
        map = Stream.of(DatumAdaptCategoryEnum.values()).collect(Collectors.toMap((DatumAdaptCategoryEnum p) -> p.getDatumCategoryId(), p -> p.getAdaptCategoryId()));
    }

    public static Map<Integer,Integer> getMap(){
        return map;
    }
}
