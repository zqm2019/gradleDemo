package com.zqm.algorithm.search;

/**
 * @describe: 顺序查找
 *              找到完全靠缘分,重头到位遍历一遍，找到即返回
 *              如果在最后一位或者是不在范围内就得全遍历一遍了
 *              复杂度未O(n)
 * @author:zqm
 */
public class SortSearch {
    public static void main(String[] args) {
        int [] array = {1,2,3,4,5,6};
        System.out.println(sortSearch(array,6));
        System.out.println(sortSearch(array,20));

    }


    public static int sortSearch(int[] array, int queryData) {
        for (int i = 0; i < array.length; i++) {
            if (queryData == array[i]) {
                return i;
            }
        }
        return -1;
    }

}
