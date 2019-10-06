package com.ximouzhao;

import com.ximouzhao.YApiGenerator.YApiGenerator;
import com.ximouzhao.myBatisGenerator.MyBatisGeneratorRun;

public class GeneratorMain {
    public static void main(String[] args) throws Exception {
        MyBatisGeneratorRun.run();
        YApiGenerator.yApiGenerator();
    }
}
