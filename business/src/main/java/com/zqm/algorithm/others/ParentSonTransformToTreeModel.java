package com.zqm.algorithm.others;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @describe:
 * @author:zqm
 */
public class ParentSonTransformToTreeModel {


    public static void main(String[] args) {
        List<ParentSonTreeVo> parentSonVoList = Lists.newArrayList();
        parentSonVoList.add(new ParentSonTreeVo(1001, "第一个顶级节点", 0));
        parentSonVoList.add(new ParentSonTreeVo(1002, "第二个顶级节点", 0));
        parentSonVoList.add(new ParentSonTreeVo(1003, "第三个顶级节点", 0));
        parentSonVoList.add(new ParentSonTreeVo(2001, "第一个顶级节点儿子1", 1001));
        parentSonVoList.add(new ParentSonTreeVo(2002, "第一个顶级节点儿子2", 1001));
        parentSonVoList.add(new ParentSonTreeVo(2003, "第一个顶级节点儿子3", 1001));
        parentSonVoList.add(new ParentSonTreeVo(2004, "第二个顶级节点的儿子1", 1002));
        parentSonVoList.add(new ParentSonTreeVo(2005, "第二个顶级节点的儿子2", 1002));
        parentSonVoList.add(new ParentSonTreeVo(2006, "第三个顶级节点儿子", 1003));
        parentSonVoList.add(new ParentSonTreeVo(3001, "第一个顶级节点儿子1的儿子1", 2001));
        parentSonVoList.add(new ParentSonTreeVo(3002, "第一个顶级节点儿子1的儿子2", 2001));
        parentSonVoList.add(new ParentSonTreeVo(3003, "第一个顶级节点儿子2的儿子", 2002));

        System.out.println(JSON.toJSONString(parentSonVoList));

        List<ParentSonTreeVo> result = getCarTreeList(parentSonVoList);
        System.out.println(JSON.toJSONString(result));





    }

    private static List<ParentSonTreeVo> getCarTreeList(List<ParentSonTreeVo> parentSonVos) {
        if (CollectionUtils.isNotEmpty(parentSonVos)) {
            // 递归获取菜单树形结构
            // 获取父节点，说明：父节点的parentId都是0）
            return parentSonVos.stream()
                    .filter((ParentSonTreeVo m) -> m.getParentId() == 0)
                    .peek((ParentSonTreeVo m) -> m.setNextParentSonVo(getChildrenCarAttr(m, parentSonVos))
                    ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private static List<ParentSonTreeVo> getChildrenCarAttr(ParentSonTreeVo brandSeriesVo, List<ParentSonTreeVo> allCarBrandSeriesVos) {
        // 子节点parentId = 父节点ID
        return allCarBrandSeriesVos.stream()
                .filter((ParentSonTreeVo m1) -> brandSeriesVo.getId() == (m1.getParentId()))
                .peek((ParentSonTreeVo m1) -> m1.setNextParentSonVo(getChildrenCarAttr(m1, allCarBrandSeriesVos)))
                .collect(Collectors.toList());
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ParentSonVo {
        private int id;
        private String value;
        private int parentId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ParentSonTreeVo {
        private int id;
        private String value;
        private int parentId;
        private List<ParentSonTreeVo> nextParentSonVo;

        public ParentSonTreeVo(int id, String value, int parentId) {
            this.id = id;
            this.value = value;
            this.parentId = parentId;
        }
    }

}
