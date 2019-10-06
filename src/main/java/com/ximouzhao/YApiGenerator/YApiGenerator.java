package com.ximouzhao.YApiGenerator;
import com.ximouzhao.GeneratorConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YApiGenerator {
    public static Connection getConnection(){
        Connection conn=null;

        try{
            Class.forName(GeneratorConfig.driverClass);  //注册数据库驱动
            String url= GeneratorConfig.connectionURL;
            //定义连接数据库的url
            conn= DriverManager.getConnection(url, GeneratorConfig.userId, GeneratorConfig.password);   //获取数据库连接
            System.out.println("数据库连接成功！"+conn);
            return (Connection)conn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        yApiGenerator();
    }
    public static void yApiGenerator() throws SQLException {
        Connection conn=getConnection();
        DatabaseMetaData meta=conn.getMetaData();
        ResultSet rs=meta.getColumns(null,null, GeneratorConfig.tablename,"%");
        List<Map<String,String>> list= new ArrayList<Map<String, String>>();
        while(rs.next()){
            HashMap<String,String> info=new HashMap<String, String>();
            System.out.print(DataTypeCodeYApiMap.get(rs.getInt("DATA_TYPE"))+"\t");
            System.out.print(rs.getInt("COLUMN_SIZE")+"\t");
            System.out.print(rs.getString("COLUMN_NAME")+"\t");
            System.out.print((rs.getInt("NULLABLE") == 1)+"\t");
            System.out.print(rs.getInt("DECIMAL_DIGITS")+"\t");
            System.out.print(rs.getString("REMARKS")+"\t");
            System.out.print(rs.getString("COLUMN_DEF")+"\t");
            System.out.print(rs.getString("IS_AUTOINCREMENT")+"\t");
            System.out.print(rs.getString("IS_GENERATEDCOLUMN")+"\t");
            System.out.print(rs.getString("TABLE_CAT")+"\t");
            System.out.print(rs.getString("TABLE_SCHEM")+"\t");
            System.out.println(rs.getString("IS_NULLABLE")+"\t");
            info.put("DATA_TYPE",DataTypeCodeYApiMap.get(rs.getInt("DATA_TYPE")));
            info.put("COLUMN_SIZE",rs.getInt("COLUMN_SIZE")+"");
            info.put("COLUMN_NAME",rs.getString("COLUMN_NAME"));
            info.put("NULLABLE",(rs.getInt("NULLABLE") == 1)+"");
            info.put("DECIMAL_DIGITS",rs.getInt("DECIMAL_DIGITS")+"");
            info.put("REMARKS",rs.getString("REMARKS"));
            info.put("COLUMN_DEF",rs.getString("COLUMN_DEF"));
            info.put("IS_AUTOINCREMENT",rs.getString("IS_AUTOINCREMENT"));
            info.put("IS_GENERATEDCOLUMN",rs.getString("IS_GENERATEDCOLUMN"));
            info.put("TABLE_CAT",rs.getString("TABLE_CAT"));
            info.put("TABLE_SCHEM",rs.getString("TABLE_SCHEM"));
            info.put("IS_NULLABLE",rs.getString("IS_NULLABLE"));
            list.add(info);
        }
        genejsonscheme(list);
    }
    public static void genejsonscheme(List<Map<String,String>> list){
        String scheme="{\n" +
                "\t\"$schema\": \"http://json-schema.org/draft-04/schema#\",\n" +
                "\t\"type\": \"object\",\n" +
                "\t\"properties\": {\n" +
                "\t\t\"code\": {\n" +
                "\t\t\t\"type\": \"integer\",\n" +
                "\t\t\t\"mock\": {\n" +
                "\t\t\t\t\"mock\": \"@natural\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"msg\": {\n" +
                "\t\t\t\"type\": \"string\"\n" +
                "\t\t},\n" +
                "\t\t\"data\": {\n" +
                "\t\t\t\"type\": \"object\",\n" +
                "\t\t\t\"properties\": {\n" +
                "\t\t\t\t\"pageList\": {\n" +
                "\t\t\t\t\t\"type\": \"object\",\n" +
                "\t\t\t\t\t\"properties\": {\n" +
                "\t\t\t\t\t\t\"total\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"number\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"list\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"array\",\n" +
                "\t\t\t\t\t\t\t\"items\": {\n" +
                "\t\t\t\t\t\t\t\t\"type\": \"object\",";
        StringBuffer propertiesBuffer=new StringBuffer("\n\t\t\t\t\t\t\t\t\"properties\": {\n");
        StringBuffer requiredBuffer=new StringBuffer("\t\t\t\t\t\t\t\t\"required\": [\n");
        for(Map<String,String> map:list){
            String columnName=StringUtils.underline2Camel(map.get("COLUMN_NAME"),true);
            propertiesBuffer.append("\t\t\t\t\t\t\t\t\t\""+columnName+"\":{\n");
            propertiesBuffer.append("\t\t\t\t\t\t\t\t\t\t\"type\":"+"\""+map.get("DATA_TYPE")+"\",\n");
            propertiesBuffer.append("\t\t\t\t\t\t\t\t\t\t\"description\":"+"\""+map.get("REMARKS")+"\"\n");
            propertiesBuffer.append("\t\t\t\t\t\t\t\t\t},\n");
            if("NO".equals(map.get("IS_NULLABLE"))){
                requiredBuffer.append("\t\t\t\t\t\t\t\t\t\""+columnName+"\",\n");
            }
        }
        String propertiesStr=propertiesBuffer.substring(0,propertiesBuffer.length()-2)+"\n"+"\t\t\t\t\t\t\t\t},\n";
        //判断是否不存在“必须项”
        String requireStr="";
        if(requiredBuffer.length()>2){
            requireStr=requiredBuffer.substring(0,requiredBuffer.length()-2)+"\n"+"\t\t\t\t\t\t\t\t]";
        }
        //requiredBuffer.append("\t\t\t\t\t\t\t]");
        String schemeEnd="\n\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"pageNum\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"pageSize\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"size\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"startRow\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"endRow\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"pages\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"prePage\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"nextPage\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"isFirstPage\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"boolean\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"isLastPage\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"boolean\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"hasPreviousPage\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"boolean\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"hasNextPage\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"boolean\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"navigatePages\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"navigatepageNums\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"array\",\n" +
                "\t\t\t\t\t\t\t\"items\": {\n" +
                "\t\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"navigateFirstPage\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"navigateLastPage\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"firstPage\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t\"lastPage\": {\n" +
                "\t\t\t\t\t\t\t\"type\": \"integer\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"required\": [\n" +
                "\t\t\t\t\t\t\"total\",\n" +
                "\t\t\t\t\t\t\"list\",\n" +
                "\t\t\t\t\t\t\"pageNum\",\n" +
                "\t\t\t\t\t\t\"pageSize\",\n" +
                "\t\t\t\t\t\t\"size\",\n" +
                "\t\t\t\t\t\t\"startRow\",\n" +
                "\t\t\t\t\t\t\"endRow\",\n" +
                "\t\t\t\t\t\t\"prePage\",\n" +
                "\t\t\t\t\t\t\"nextPage\",\n" +
                "\t\t\t\t\t\t\"isFirstPage\",\n" +
                "\t\t\t\t\t\t\"isLastPage\",\n" +
                "\t\t\t\t\t\t\"hasPreviousPage\",\n" +
                "\t\t\t\t\t\t\"hasNextPage\",\n" +
                "\t\t\t\t\t\t\"navigatePages\",\n" +
                "\t\t\t\t\t\t\"navigatepageNums\",\n" +
                "\t\t\t\t\t\t\"navigateFirstPage\",\n" +
                "\t\t\t\t\t\t\"navigateLastPage\",\n" +
                "\t\t\t\t\t\t\"firstPage\",\n" +
                "\t\t\t\t\t\t\"lastPage\",\n" +
                "\t\t\t\t\t\t\"pages\"\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t}\n" +
                "\t\t\t},\n" +
                "\t\t\t\"required\": [\n" +
                "\t\t\t\t\"pageList\"\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"required\": [\n" +
                "\t\t\"code\",\n" +
                "\t\t\"msg\",\n" +
                "\t\t\"data\"\n" +
                "\t]\n" +
                "}";
//        System.out.println(propertiesBuffer.toString());
//        System.out.println(requiredBuffer.toString());
        System.out.println(scheme+propertiesStr+requireStr+schemeEnd);
        //buffer.
    }
}
