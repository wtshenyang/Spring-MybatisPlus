package com.iflytek.springboot.base.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IDCardValidate {
    public static boolean validate(String no) {
        // 对身份证号进行长度等简单判断
        if (ValidateUtil.isEmpty(no) || no.length() != 18 || !no.matches("\\d{17}[0-9X]")) {
                return false;
        }
        // 1-17位相乘因子数组
        int[] factor = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        // 18位随机码数组
        char[] random = "10X98765432".toCharArray();
        // 计算1-17位与相应因子乘积之和
        int total = 0;
        for (int i = 0; i < 17; i++) {
            total += Character.getNumericValue(no.charAt(i)) * factor[i];
        }
        // 判断随机码是否相等
        return random[total % 11] == no.charAt(17);
    }

    //提取性别和出生日期
    public static Map<String, String> getCarInfo(String CardCode) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            String year = CardCode.substring(6).substring(0, 4);// 得到年份
            String yue = CardCode.substring(10).substring(0, 2);// 得到月份
            // String day=CardCode.substring(12).substring(0,2);//得到日
            String csrq = CardCode.substring(6, 14);
            String sex;
            String sexdm;
            if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
                sex = "女";
                sexdm = "2";
            } else {
                sex = "男";
                sexdm = "1";
            }
            Date date = new Date();// 得到当前的系统时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fyear = format.format(date).substring(0, 4);// 当前年份
            String fyue = format.format(date).substring(5, 7);// 月份
            // String fday=format.format(date).substring(8,10);
            int age = 0;
            if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
                age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
            } else {// 当前用户还没过生
                age = Integer.parseInt(fyear) - Integer.parseInt(year);
            }
            map.put("sex", sex);
            map.put("sexdm", sexdm);
            map.put("age", age+"");
            map.put("csrq", getFormatDate(csrq));
            return map;
        } catch (NumberFormatException e) {
            return new HashMap<String, String>();
        }
    }

    /**
     * 15位身份证的验证
     *
     * @param
     * @throws Exception
     */
    public static Map<String, String> getCarInfo15W(String card) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            String uyear = "19" + card.substring(6, 8);// 年份
            String uyue = card.substring(8, 10);// 月份
            // String uday=card.substring(10, 12);//日
            String csrq = "19" + card.substring(6, 12);
            String usex = card.substring(14, 15);// 用户的性别
            String sex;
            String sexdm;
            if (Integer.parseInt(usex) % 2 == 0) {
                sex = "女";
                sexdm = "2";
            } else {
                sex = "男";
                sexdm = "1";

            }
            Date date = new Date();// 得到当前的系统时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fyear = format.format(date).substring(0, 4);// 当前年份
            String fyue = format.format(date).substring(5, 7);// 月份
            // String fday=format.format(date).substring(8,10);
            int age = 0;
            if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
                age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
            } else {// 当前用户还没过生
                age = Integer.parseInt(fyear) - Integer.parseInt(uyear);
            }
            map.put("sex", sex);
            map.put("sexdm", sexdm);
            map.put("age", age+"");
            map.put("csrq", getFormatDate(csrq));
            return map;
        } catch (NumberFormatException e) {
            return new HashMap<>();
        }
    }
    public static String getFormatDate(String type) {
        StringBuffer sb = new StringBuffer();
        if (ValidateUtil.isEmpty(type)) {
            return "";
        } else if (type.length() == 8) {
            return type.substring(0, 4) + "-" + type.substring(4, 6) + "-" + type.substring(6, 8);
        } else if (type.length() == 14) {
            return type.substring(0, 4) + "-" + type.substring(4, 6) + "-" + type.substring(6, 8) + " " +
                    "" + type.substring(8, 10) + ":" + type.substring(10, 12) + ":" + type.substring(12, 14);

        } else {
            return "";
        }

    }
    
    	

    public static void main(String[] args) {
        // 正确
        System.out.println(validate("432831196411150810"));
        // 错误
        System.out.println(validate("230715199505300225"));
    }
}