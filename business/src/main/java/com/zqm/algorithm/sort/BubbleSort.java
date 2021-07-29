package com.zqm.algorithm.sort;

import com.alibaba.fastjson.JSON;

/**
 * @describe: 冒泡排序
 *              两两比较大的下沉,小的上浮,一次循环只能找到一个最大值或者最小值。
 *              重复此步骤n-1次,即直到倒数第二个结束,所有值有序
 *              时间复杂度O(n2)
 * @author:zqm
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {79, 3, 43, 23, 56, 45};
//        bubbleSort(array);
        bubbleSort2(array);

        System.out.println(JSON.toJSONString(array));
    }

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 算法改进
     * 设置变量isSwap,如果在某一次循环比较时,不需要互换值,说明该值得位置已确定。
     *
     * @param array
     */
    public static void bubbleSort2(int[] array) {
        boolean isSwap;

        //n-1次比较
        for (int i = 0; i < array.length - 1; i++) {
            isSwap = false;
            //两两相比,array.length - 1 - i 含义是因为比较i次后就少了i个值需要排序
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSwap = true;
                }
                if (!isSwap) {
                    break;
                }

            }
        }
    }
}
