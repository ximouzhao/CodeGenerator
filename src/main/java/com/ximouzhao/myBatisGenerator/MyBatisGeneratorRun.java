package com.ximouzhao.myBatisGenerator;

import com.ximouzhao.GeneratorConfig;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行此方法生成mybatis代码
 * 生成代码自动放入对应目录
 * 配置文件targetProject应从项目名称开始到要生成到的classpath
 * Created by huhaichao on 2017/5/15.
 */
public class MyBatisGeneratorRun  {

    public static void run() throws Exception{
        MyBatisGeneratorRun app = new MyBatisGeneratorRun();

        System.out.println(app.getClass().getResource("/").getPath());
        app.generator();
        System.out.println(System.getProperty("user.dir"));
    }

    public static void main(String[] args) throws Exception {
        run();
        //run(GeneratorConfigMyAliyun.class);
    }

    public void generator() throws Exception{

        List<String> warnings = new ArrayList<String>();
        //这里替换成自己的config方便配置
        Configuration configuration = GeneratorConfig.getConfiguration();
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
        myBatisGenerator.generate(null);

        for(String warning:warnings){
            System.out.println(warning);
        }
    }
}