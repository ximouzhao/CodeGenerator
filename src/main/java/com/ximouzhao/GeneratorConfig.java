package com.ximouzhao;

import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GeneratorConfig {

    //读取配置文件
    public static Properties prop = null;

    public static String active;

    public static String driverClass;
    public static String connectionURL;
    public static String userId;
    public static String password;
    public static String tablename;
    public static String targetPackage;
    public static String targetProject;


    static{
        if(prop == null){
            prop = new Properties();
            try {
                //这里的填写的参数是配置文件的相对路径

                ClassPathResource classPathResource = new ClassPathResource("config/config.properties");
                //文件流的编码方式
                prop.load(new InputStreamReader(classPathResource.getInputStream(),"utf-8"));
                active=prop.getProperty("active");

                ClassPathResource classPathResourceActive = new ClassPathResource("config/"+active+".properties");
                prop.load(new InputStreamReader(classPathResourceActive.getInputStream(),"utf-8"));

                driverClass=prop.getProperty("driverClass");
                connectionURL=prop.getProperty("connectionURL");
                userId=prop.getProperty("userId");
                password=prop.getProperty("password");
                tablename=prop.getProperty("tablename");
                targetPackage=prop.getProperty("targetPackage");
                targetProject=prop.getProperty("targetProject");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static Configuration getConfiguration () throws IOException, XMLParserException {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        InputStream resourceAsStream = GeneratorMain.class.getClassLoader().getResourceAsStream("myGeneratorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(resourceAsStream);
        JDBCConnectionConfiguration jdbcConfig=config.getContexts().get(0).getJdbcConnectionConfiguration();
        jdbcConfig.setDriverClass(driverClass);
        jdbcConfig.setConnectionURL(connectionURL);
        jdbcConfig.setUserId(userId);
        jdbcConfig.setPassword(password);
        //jdbcConfig.addProperty("useInformationSchema","true");

        JavaClientGeneratorConfiguration clientConfig=config.getContexts().get(0).getJavaClientGeneratorConfiguration();
        clientConfig.setTargetPackage(targetPackage+".dao");
        clientConfig.setTargetProject(targetProject);

        JavaModelGeneratorConfiguration javamodelConfig=config.getContexts().get(0).getJavaModelGeneratorConfiguration();
        javamodelConfig.setTargetPackage(targetPackage+".model");
        javamodelConfig.setTargetProject(targetProject);

        SqlMapGeneratorConfiguration javaTypeRolv=config.getContexts().get(0).getSqlMapGeneratorConfiguration();
        javaTypeRolv.setTargetPackage(targetPackage+".mapping");
        javaTypeRolv.setTargetProject(targetProject);

        config.getContexts().get(0).getTableConfigurations().get(0).setTableName(tablename);
        return config;
    }
}
