package com.ximouzhao.test;

import java.io.*;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Map;
/** 
 * @author  编码小王子
 * @date    2018年5月25日 下午6:17:31 
 * @version 1.0   
 */
public class HttpUtils {
    //发送POST请求
    public static String sendPostJSON(String url, String jsonStr) {
        String result = "";// 返回的结果
        OutputStream os=null;
        BufferedReader br=null;
        try {
            // 创建URL对象
            java.net.URL connURL = new java.net.URL(url);
            // 打开URL连接
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
            // 设置通用属性
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("Content-Type", "application/json;utf-8");
            httpConn.setRequestProperty("Accept", "application/json");

            // 设置POST方式
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流

            os= httpConn.getOutputStream();
            byte[] input = jsonStr.getBytes("utf-8");
            os.write(input, 0, input.length);
            br = new BufferedReader(
                    new InputStreamReader(httpConn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}