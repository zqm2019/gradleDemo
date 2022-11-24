package com.zqm.algorithm.search;

/**
 * @describe: 二分查找
 *              数组有序,将n个元素分成大致相等的两部分，取a[n/2]与x做比较;
 *              如果x=a[n/2],则找到x,算法中止;
 *              如果x<a[n/2],则只要在数组a的左半部分继续搜索x;
 *              如果x>a[n/2],则只要在数组a的右半部搜索x;
 *              即不是中间就是在两边,复杂度是O(log2n)
 *
 * @author:zqm
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {6, 12, 33, 87, 90, 97, 108, 561};
        // 位数从0开始
        System.out.println("循环查找第:" + binarySearch(arr, 87) + "位");
        System.out.println("递归查找第:" + binarySearch(arr, 87, 0, arr.length - 1) + "位");

        System.out.println("循环查找第:" + differenceSearch(arr, 87) + "位");
        System.out.println("递归查找第:" + differenceSearch(arr, 88, 0, arr.length - 1) + "位");

    }


    /**
     * 循环实现二分查找算法arr 已排好序的数组x 需要查找的数-1 无法查到数据
     */
    public static int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (x == arr[mid]) {
                return mid;
            } else if (x < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 递归二分查找
     */
    public static int binarySearch(int[] array, int queryData, int startIndex, int endIndex) {
        int midIndex = (startIndex + endIndex) / 2;
        if (queryData < array[startIndex] || queryData > array[endIndex] || startIndex > endIndex) {
            return -1;
        }
        if (queryData < array[midIndex]) {
            return binarySearch(array, queryData, startIndex, midIndex - 1);
        } else if (queryData > array[midIndex]) {
            return binarySearch(array, queryData, midIndex + 1, endIndex);
        } else {
            return midIndex;
        }
    }

    /**
     * 循环实现差值查找算法arr 已排好序的数组x 需要查找的数-1 无法查到数据
     */
    public static int differenceSearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low+(x-arr[low])/(arr[high]-arr[low])*(high-low);
            if (x == arr[mid]) {
                return mid;
            } else if (x < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 递归实现差值查找
     */
    public static int differenceSearch(int[] array, int queryData, int startIndex, int endIndex) {
        int midIndex = startIndex+(queryData-array[startIndex])/(array[endIndex]-array[startIndex])*(endIndex-startIndex);
        if (queryData < array[startIndex] || queryData > array[endIndex] || startIndex > endIndex) {
            return -1;
        }
        if (queryData < array[midIndex]) {
            return binarySearch(array, queryData, startIndex, midIndex - 1);
        } else if (queryData > array[midIndex]) {
            return binarySearch(array, queryData, midIndex + 1, endIndex);
        } else {
            return midIndex;
        }
    }

}
