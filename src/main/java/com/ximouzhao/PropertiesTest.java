package com.ximouzhao;

import org.springframework.core.io.ClassPathResource;
 
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Properties;
 
public class PropertiesTest {
 
    //读取配置文件
    public static Properties prop = null;
 
    public static void main(String args[]){
 
        if(prop == null){
            prop = new Properties();
            try {
                //这里的填写的参数是配置文件的相对路径

                ClassPathResource classPathResource = new ClassPathResource("config/config.properties");
                //文件流的编码方式
                prop.load(new InputStreamReader(classPathResource.getInputStream(),"utf-8"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
 
        String[] addressInfo = {"小明","东方小区","5.35"};
        String[] gradesInfo = {"88","92.5","97"};
        String[] hobbyInfo = {"跳远","跑步","散步"};
 
        System.out.println("地址信息：" + MessageFormat.format(prop.getProperty("address"),addressInfo));
        System.out.println("成绩信息：" + MessageFormat.format(prop.getProperty("grades"),gradesInfo));
        System.out.println("爱好信息：" + MessageFormat.format(prop.getProperty("hobby"),hobbyInfo));
    }
}