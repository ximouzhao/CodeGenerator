//package com.ximouzhao.test;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import net.sf.json.JSONObject;
//import sun.net.www.http.HttpClient;
//
//public class testOne {
//	public static void main(String[] args) {
//	}
//
//	public static String post(String json,String path) {
//		String result="";
//        try {
//        	HttpClient client=new DefaultHttpClient();
//			HttpPost post=new HttpPost(url);
//			post.setHeader("Content-Type", "appliction/json");
//			post.addHeader("Authorization", "Basic YWRtaW46");
//			StringEntity s=new StringEntity(json.toString(), "utf-8");
//			s.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "appliction/json"));
//			post.setEntity(s);
//			HttpResponse httpResponse=client.execute(post);
//			InputStream in=httpResponse.getEntity().getContent();
//			BufferedReader br=new BufferedReader(new InputStreamReader(in, "utf-8"));
//			StringBuilder strber=new StringBuilder();
//			String line=null;
//			while ((line=br.readLine())!=null) {
//				strber.append(line+"\n");
//
//			}
//			in.close();
//			result=strber.toString();
//			if(httpResponse.getStatusLine().getStatusCode()!=HttpStatus.SC_OK){
//				result="服务器异常";
//			}
//        } catch (Exception e) {
//            System.out.println("请求异常");
//            throw new RuntimeException(e);
//        }
//        System.out.println("result=="+result);
//        return result;
//    }
//}