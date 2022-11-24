package com.zqm.algorithm.sort;

/**
 * @describe: 插入排序
 *              从第二个值开始与前面的值比较，比其小，就交换位置。
 * @author:zqm
 */
public class InsertSort {

    public static void main(String[] args) {
        int [] array = {34,90,87,33,22 ,11,5};
        insertSort(array);
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0) {
                if (arr[j] < arr[j - 1]) {
                    int temp;
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    j--;
                } else {
                    break;
                }
            }
        }
    }
}
