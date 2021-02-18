package com.zqm.vo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AllCarBrandSeriesVo implements Serializable {
    private static final long serialVersionUID = 8523609967737455400L;

    private Integer carAttributeId;
    private String carAttributeName;

    private Integer parentId;


    private List<AllCarBrandSeriesVo> subCarAttr;


    public static void main(String[] args) {
        List<AllCarBrandSeriesVo> allCarBrandSeriesVos = new ArrayList<>();
        AllCarBrandSeriesVo allCarBrandSeriesVo = new AllCarBrandSeriesVo();
        allCarBrandSeriesVo.setCarAttributeId(1);
        allCarBrandSeriesVo.setCarAttributeName("品牌");
        allCarBrandSeriesVo.setParentId(0);

        AllCarBrandSeriesVo fas = new AllCarBrandSeriesVo();
        fas.setCarAttributeId(21);
        fas.setCarAttributeName("厂家1");
        fas.setParentId(1);

        AllCarBrandSeriesVo dfdf = new AllCarBrandSeriesVo();
        dfdf.setCarAttributeId(22);
        dfdf.setCarAttributeName("厂家2");
        dfdf.setParentId(1);

        AllCarBrandSeriesVo fdfsfa = new AllCarBrandSeriesVo();
        fdfsfa.setCarAttributeId(33);
        fdfsfa.setCarAttributeName("车型1");
        fdfsfa.setParentId(21);


        AllCarBrandSeriesVo fdfsfa1 = new AllCarBrandSeriesVo();
        fdfsfa1.setCarAttributeId(34);
        fdfsfa1.setCarAttributeName("车型2");
        fdfsfa1.setParentId(22);

        allCarBrandSeriesVos.add(allCarBrandSeriesVo);
        allCarBrandSeriesVos.add(fas);
        allCarBrandSeriesVos.add(fdfsfa);
        allCarBrandSeriesVos.add(fdfsfa1);
        allCarBrandSeriesVos.add(dfdf);

        List<AllCarBrandSeriesVo> resultMenus = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(allCarBrandSeriesVos)) {
            // 递归获取菜单树形结构
            // 获取父节点，说明：父节点的parentId都是1（根节点是0）
            resultMenus = allCarBrandSeriesVos.stream()
                    .filter(m -> m.getParentId() == 0)
                    .peek((m) -> m.setSubCarAttr(getChildren(m, allCarBrandSeriesVos))
            ).collect(Collectors.toList());
        }


        System.out.println(resultMenus);
        System.out.println(JSON.toJSONString(resultMenus));

    }


    private static List<AllCarBrandSeriesVo> getChildren(AllCarBrandSeriesVo m, List<AllCarBrandSeriesVo> menus) {
        // 子节点parentId = 父节点ID
        return menus.stream().filter(m1 -> m.getCarAttributeId().equals(m1.getParentId()))
                .peek(m1 -> m1.setSubCarAttr(getChildren(m1, menus))).collect(Collectors.toList());
    }
}