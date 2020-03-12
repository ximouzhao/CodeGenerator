package com.ximouzhao.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeTest {
    public static void main(String[] args) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.println(f.format(new Date())); // 将当前时间袼式化为指定的格式        System.out.println(dateFormat.format(new Date()));
    }
}
