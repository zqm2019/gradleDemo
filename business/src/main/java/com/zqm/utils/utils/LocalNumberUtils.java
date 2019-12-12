package com.zqm.utils.utils;

import java.util.IntSummaryStatistics;
import java.util.List;

/**
 * 数字工具类
 * Date: 2019-12-06
 *
 * @author chencheng10
 */
public class LocalNumberUtils {

    /**
     * 取List<Integer>的平均数
     *
     * @param numbers
     * @return
     */
    public static Integer getAverage(List<Integer> numbers) {
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        return (int) stats.getAverage();
    }
}
