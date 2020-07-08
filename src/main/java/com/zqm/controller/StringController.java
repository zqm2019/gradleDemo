
package com.zqm.controller;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串测试类
 * Date: 2019-09-04
 *
 * @author zhaqianming
 */
public class StringController {

    //转换符
    /**
     * %s  字符串类型
     * %c 字符类型
     * %b  布尔类型
     * %d 整数类型（十进制）
     * %x 整数类型（十六进制）
     * %o  整数类型（八进制）
     * %e  指数类型
     * %g 通用浮点类型（f和e类型中较短的） 
     * %h 散列码
     * %%  百分比类型
     * %n  换行符 
     * %tx  日期与时间类型（x代表不同的日期与时间转换符
     */

    //搭配转换符的标志

    /**
     * + 为证书或者负数添加符号                ("%+d",15)
     * - 左对齐                              ("%-5d",15)
     * 0 前面数字补0                          ("%4d", 99)
     * 空格 在整数之前添加指定数量的空格         ("%4d", 99)
     * $ 被格式化的参数索引                     ("%1$d,%2$s", 99,"abc")
     */
//    public static void main(String[] args) {
//        String a = "一本书的价值%s";
//        a = String.format(a, "100元");
//        System.out.println(a);
//
//        String str = null;
//        //$使用
//        str = String.format("格式参数$的使用：%1$d,%2$s", 99, "abc");
//        System.out.println(str);
//        //+使用
//        System.out.printf("显示正负数的符号：%+d与%d%n", 99, -99);
//        //补O使用
//        System.out.printf("最牛的编号是：%03d%n", 7);
//        //空格使用
//        System.out.printf("Tab键的效果是：% 8d%n", 7);
//        //.使用
//        System.out.printf("整数分组的效果是：%,d%n", 9989997);
//        //空格和小数点后面个数
//        System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);
//
//    }

//    //砍价红包随机生成
//    private BigDecimal newNextAmount(BargainOrder bargainOrder) {
//        //设置的砍价总次数
//        int setNum = bargainOrder.getBargainPersonNumLimit();
//        //剩余未砍的次数
//        int leftNum = setNum - bargainOrder.getBargainPersonNum();
//
//        //剩余的砍价
//        BigDecimal leftTotal = bargainOrder.getBargainAmountLimit().subtract(bargainOrder.getBargainAmount()).multiply(BigDecimal.TEN);
//
//        if (leftNum <= 0) {
//            return BigDecimal.ZERO;
//        } else if (leftNum == 1) {
//            return leftTotal.divide(BigDecimal.TEN, 1, BigDecimal.ROUND_HALF_UP);
//        } else {
//            BigDecimal end;
//            if (leftTotal.subtract(leftTotal.multiply(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(leftNum), 1, BigDecimal.ROUND_DOWN)).compareTo(BigDecimal.ONE) <= 0) {
//                end = leftTotal.divide(BigDecimal.valueOf(leftNum), 2, BigDecimal.ROUND_DOWN);
//            } else {
//                end = leftTotal.multiply(BigDecimal.valueOf(2)).divide(BigDecimal.valueOf(leftNum), 1, BigDecimal.ROUND_DOWN);
//            }
//            return BigDecimal.valueOf(RandomUtils.nextInt(1, end.intValue())).divide(BigDecimal.TEN, 1, BigDecimal.ROUND_HALF_UP);
//        }
//    }
    public static void distributeRedPacket(BigDecimal totalAmount, int num) {
        int amount = totalAmount.multiply(new BigDecimal("100")).intValue();
        Set<Integer> points = new TreeSet<>();
        Random random = new Random();
        while (points.size() < num - 1) {
            points.add(random.nextInt(amount - 1) + 1);
        }
        points.add(amount);
        int before = 0;
        for (int point : points) {
            System.out.println((double) (point - before) / 100);
            before = point;
        }
    }

//    public static void main(String[] args) {
//        distributeRedPacket(new BigDecimal(12), 5);
//    }


    /**
     Java Character类
     Java Character.isLetter() 方法,判断字符是否为字母
     Java Character.isDigit() 方法,判断字符是否为数字
     Java Character.isUpperCase() 方法,判断字符是否为大写字母
     Java Character.isLowerCase() 方法,判断字符是否为小写字母
     Java Character.toUpperCase() 方法,将小写字符转换为大写。
     Java Character.toLowerCase() 方法,将大写字符转换为小写。
     Java Character.toString() 方法,char字符转换成string
     Java Character.isTitleCase() 是否标题首字符
     Java Character.getNumericValue() Unicode字符int值
     */

    /**
     * StringUtils.isEmpty()  字符串是否为空 不能区分" "
     * StringUtils.hasText()  是否有字符串 可以
     * <p>
     * lang3.StringUtils.isBlank 是否是空白 可以
     */

//    public static void main(String[] args) throws Exception {
//
//        System.out.println(segment("A4"));
//        int[] s = new int[1024];
//        System.out.println("size(s):" + RamUsageEstimator.humanSizeOf(s));
//        //将list转字符串
//        List<String> list1 = Arrays.asList(
//                "Google", "Guava", "Java", "Scala", "", null);
//        System.out.println("size(s):" + RamUsageEstimator.humanSizeOf(list1));
//        System.out.println("size(s):" + RamUsageEstimator.humanSizeOf("java"));
//        System.out.println("size(s):" + RamUsageEstimator.humanSizeOf("Guava"));
//
//        System.out.println("size(s):" + RamUsageEstimator.humanSizeOf("Google"));
//
//        System.out.println("size(s):" + RamUsageEstimator.humanSizeOf("Scala"));
//        System.out.println("size(s):" + RamUsageEstimator.humanSizeOf(null));
//
//
//        System.out.println(lineToHump("band_name"));
//        //跳过null的凭借，注意，最后一个字符不能为null，否则空指针
//        System.out.println(Joiner.on(";").skipNulls().join("dfd", "dfs", null, ""));
//        //字符串拼接，把null转换成制定的00
//        System.out.println(Joiner.on(";").useForNull("00").join("dfd", "dfs", "", null));
//
//
//        System.out.println(Joiner.on(":").skipNulls().join(list1));
//        System.out.println(list1.stream().filter(o -> o != null).collect(Collectors.joining(";")));
//
//    }


    public static Set segment(String text) throws Exception {

        Set<String> set = new HashSet<>();

        StringReader re = new StringReader(text.trim());

        IKSegmenter ik = new IKSegmenter(re, true);

        Lexeme lex;

        while ((lex = ik.next()) != null) {

            set.add(lex.getLexemeText().toUpperCase());

        }

        return set;

    }

    /**
     * 将驼峰转换成下划线形式
     *
     * @param str
     * @return
     */
    private static String humpToLine(String str) {
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        //大小转小写
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    //        //驼峰转下划线
//        List<String> nameList = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(so.getVehicleAttributeList())) {
//            for (VehicleAttribute vehicleAttribute : so.getVehicleAttributeList()) {
//                if (vehicleAttribute == null) {
//                    continue;
//                }
//                nameList.add(vehicleAttribute.getAttributeName());
//                vehicleAttribute.setAttributeName(humpToLine(vehicleAttribute.getAttributeName()));
//            }
//        }
//

    /**
     * Joiner 分隔list转string
     */
//        if (!CollectionUtils.isEmpty(nameList)){
//            //表头英文逗号相隔开
//            formCommonsVo.setFormHead(Joiner.on(",").join(nameList));
//        }
    public static void testSplit(String str) {
        //去除空格trim()是去掉首尾空格
        //2.str.replace(" ", ""); 去掉所有空格，包括首尾、中间
        //str = .replaceAll("\\s*", "")
        //或者replaceAll(" +",""); 去掉所有空格
        str = str.trim();
//        String s = ;
        String s1 = str.replaceAll("\\s*","");
        //中英文逗号分开
        List<String> oeList = Arrays.asList(str.toUpperCase().replace(" ","").split(",|，"));
        System.out.println(oeList);
//        System.out.println( Arrays.asList(s.split(",|，")));
        System.out.println( Arrays.asList(s1.split(",|，")));


    }

    public static void main(String[] args) {
        testSplit("wo    是，zho ng,国人");
    }



}
