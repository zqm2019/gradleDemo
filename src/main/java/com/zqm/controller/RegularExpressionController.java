
package com.zqm.controller;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试入口
 * Date: 2019-08-06
 *
 * @author zhaqianming
 */
public class RegularExpressionController {


    public static int getMaxKey(Map<Integer, Integer> map) {
        if (map == null) {
            return 0;
        }
        Set<Integer> set = map.keySet();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return (int) obj[obj.length - 1];
    }

    public static int getMinKey(Map<Integer, Integer> map) {
        if (map == null) {
            return 0;
        }
        Set<Integer> set = map.keySet();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return (int) obj[0];
    }


//    public static void main(String[] args) {
//        String str = "122\uD83E\uDDE1\uD83E\uDDE1";
//        System.out.println(buildNickNamePerfect(str));
//
//    }

    /**
     * 完美解决  使用codePointCount 代码点表示一个字符的概念。
     *
     * @param str
     * @return
     */
    public static String buildNickNamePerfect(String str) {
        if (StringUtils.isEmpty(str)) {
            return "牛牛用户";
        }

        String result;
        int length = str.length();
        int index;
        if (str.codePointCount(0, length) == 1) {
            return str + "**";
        }
        if (str.codePointCount(0, length) >= 3) {
            index = 3;
        } else {
            index = 1;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 1; i <= str.length(); i++) {

            if (str.codePointCount(0, i) == index) {
                count++;
                map.put(count, i);
            }
        }
        int lcont = 0;
        Map<Integer, Integer> lmap = new HashMap<>();
        for (int i = str.length() - 1; i >= 0; i--) {

            if (str.codePointCount(i, length) == 1) {
                lcont++;
                lmap.put(lcont, i);
            }
        }
        result = str.substring(0, map.get(getMaxKey(map))) + "**" + str.substring(lmap.get(getMaxKey(lmap)), length);
        return result;
    }


//    public static void main(String[] args) {
//        //String a = "\uD83C\uDF44途牛旅游息莎莎\uD83C\uDF44\uD83C\uDF40";
//        String aa = "\uD83C\uDF44\uD83C\uDF44\uD83C\uDF44eee";
//        String bb =  "\uD83C\uDF44\uD83C\uDF44\uD83C\uDF44";
//        String bb1 =  "\uD83C\uDF44\uD83C\uDF44eeee";
//        String bb2 =  "1\uD83C\uDF44\uD83C\uDF44eeee";
//        String bb3 =  "12\uD83C\uDF44\uD83C\uDF44eeee";
//        String bb4 =  "123\uD83C\uDF44\uD83C\uDF44eeee";
//        String bb5 =  "1234\uD83C\uDF44\uD83C\uDF44eeee";
//        String bb6 =  "12345\uD83C\uDF44\uD83C\uDF44eeee";
//        String bb7 =  "\uD83C\uDF44ewf\uD83C\uDF44eeee";
//        String bb8 =  "\uD83C\uDF44e\uD83C\uDF44eeee";
//        String cc =  "1\uD83C\uDF44\uD83C\uDF44\uD83C\uDF44";
//        String cc1 =  "1\uD83C\uDF44\uD83C\uDF44";
//        String cc2 =  "\uD83C\uDF441\uD83C\uDF44";
//        String cc3 =  "\uD83C\uDF44\uD83C\uDF44err";
//        String cc4 =  "\uD83C\uDF44e";
//        String cc5 =  "w\uD83C\uDF44";
//        String cc7 =  "\uD83C\uDF44\uD83C\uDF44e";
//        String cc6 =  "w\uD83C\uDF44ww";
//        buildNickName(cc2);
//
//        List<String> stringArray = new ArrayList(Arrays.asList(aa,bb,bb1,bb2,bb3,bb4,bb5,bb6,bb7,bb8,
//                cc,cc1,cc2,cc3,cc4,cc5,cc6,cc7));
//        for (String a : stringArray) {
//            buildNickName(a);
//        }
//    }

    /**
     * 第一次尝试 正则
     *
     * @param a
     */
    private static void buildNickNamep(String a) {

        String codeSing = "**";
        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        System.out.println();
        System.out.println(a + "长度是:" + a.length());

        String lastString = "";
        String beforeResult = "";
        String preResult = "";
        //匹配(0,2),(1,3)(3,5)(2,4)(4,6)
        String firstString = "";
        String secondString = "";
        String thirdString = "";
        String fourthString = "";
        String fivethString = "";
        String sixString = "";

        //最终结果
        String result = "";
        if (a.length() == 1) {
            System.out.println(a);
        }
        if (a.length() >= 4) {
            sixString = a.substring(0, 3);
            lastString = a.substring(a.length() - 2);
            Matcher lastMatcher = pattern.matcher(lastString);
            firstString = a.substring(0, 2);
            secondString = a.substring(1, 3);
            thirdString = a.substring(2, 4);

            if (a.length() >= 5) {
                fourthString = a.substring(3, 5);
            }
            if (a.length() >= 6) {
                fivethString = a.substring(4, 6);
            }
            String senvenString = a.substring(0, 4);


            Matcher firstMatcher = pattern.matcher(firstString);
            Matcher secondMatcher = pattern.matcher(secondString);
            Matcher thirdMatcher = pattern.matcher(thirdString);
            Matcher fourthMatcher = pattern.matcher(fourthString);
            Matcher fivethMatcher = pattern.matcher(fivethString);
            Matcher sixMatcher = pattern.matcher(sixString);
            Matcher senvenMatcher = pattern.matcher(senvenString);
            boolean first = firstMatcher.find();//前两位
            boolean second = secondMatcher.find();//第二第三
            boolean third = thirdMatcher.find();//第三第四
            boolean fourth = fourthMatcher.find();//第四第五
            boolean five = fivethMatcher.find();//第五第6
            boolean six = sixMatcher.find();//前三个
            boolean seven = senvenMatcher.find();//前四个


            if (!seven) {
                preResult = a.substring(0, 3);
            } else if (!six && third) {
                preResult = a.substring(0, 4);
            } else if (first && third && five) {
                preResult = a.substring(0, 6);
            } else if (first && (third || fourth)) {
                preResult = a.substring(0, 5);
            } else if (first && !third) {
                preResult = a.substring(0, 4);
            } else if (!first && second && !fourth) {
                preResult = a.substring(0, 4);
            } else if (!first && second && fourth) {
                preResult = a.substring(0, 5);
            } else if (first || second || third) {
                preResult = a.substring(0, 5);
            } else {
                preResult = a.substring(0, 3);
            }

            if (lastMatcher.find()) {
                beforeResult = lastMatcher.group(0);

            } else {
                beforeResult = a.substring(a.length() - 1);

            }
            System.out.println(preResult + codeSing + beforeResult);
        } else {

            firstString = a.substring(0, 2);
            Matcher firstMatcher = pattern.matcher(firstString);

            if (firstMatcher.find()) {
                result = firstMatcher.group(0) + codeSing;
                System.out.println(result);
            } else {
                result = a.substring(0, 1) + codeSing;
                System.out.println(result);
            }

        }

        Objects.equals(a,result);
    }


    private static void test(){
        String pattern = "\\d{4}-\\d{1,2}-\\d{1,2}T\\d{2}:\\d{2}:\\d{2}|((?<=\\+08:00) Sys.*)";

        String str = "<109>1 2023-10-09T13:47:46.940+08:00 System-MantaRay23/Node-omc1vm67/LogType-RHEL-Auditd SLC SLNBI SUCCESS [origin software=\"SLC\" swVersion=\"23\"][SLC@18060 eventType=\"SERVICE_STOP\" dn=\"System-MantaRay23/Node-omc1vm67/LogType-RHEL-Auditd\" gid=\"UNKNOWN\" logType=\"RHEL-Auditd\" seType=\"NA_NODE\" userName=\"root\" seVersion=\"23\" occurrence=\"1\"][RhelAuditdParser@18061 ses=\"unset\" comm=\"systemd\" auid=\"unset\" u";


        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
        while (m.find()){
            System.out.println(m.group().trim().replace("T"," "));
        }


    }

    public static void main(String[] args) {
        String input = "My phone number is (123) 456-7890";

        // 提取电话号码的区号和号码部分
        String patternString = "\\((\\d{3})\\) (\\d{3}-\\d{4})";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);

        // 提取并输出区号和号码部分
        if (matcher.find()) {
            String areaCode = matcher.group(1);
            String phoneNumber = matcher.group(2);
            System.out.println("Area code: " + areaCode);
            System.out.println("Phone number: " + phoneNumber);
        }

        test();
    }
}
