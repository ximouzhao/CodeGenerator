package com.ximouzhao.YApiGenerator;

import java.util.HashMap;

public class DataTypeCodeYApiMap {
    private static HashMap<Integer,String> hashmap=new HashMap();
    static {
        hashmap.put(7,"number");
        hashmap.put(7,"number");
        hashmap.put(-4,"string");
        hashmap.put(1,"object");
        hashmap.put(-2,"string");
        hashmap.put(3,"number");
        hashmap.put(3,"number");
        hashmap.put(1,"string");
        hashmap.put(-1,"string");
        hashmap.put(-1,"string");
        hashmap.put(4,"integer");
        hashmap.put(91,"string");
        hashmap.put(93,"string");
        hashmap.put(8,"number");
        hashmap.put(8,"number");
        hashmap.put(12,"integer");
        hashmap.put(-6,"integer");
        hashmap.put(-4,"string");
        hashmap.put(4,"integer");
        hashmap.put(-2,"string");
        hashmap.put(1,"object");
        hashmap.put(3,"number");
        hashmap.put(3,"number");
        hashmap.put(-1,"string");
        hashmap.put(-5,"integer");
        hashmap.put(92,"string");
        hashmap.put(4,"integer");
        hashmap.put(-7,"boolean");
        hashmap.put(91,"string");
        hashmap.put(93,"string");
        hashmap.put(-2,"string");
        hashmap.put(-4,"string");
        hashmap.put(5,"integer");
        hashmap.put(4,"integer");
        hashmap.put(8,"string");
        hashmap.put(12,"string");
        hashmap.put(-3,"string");
    }
    public static String get(int key){
        return hashmap.get(key);
    }
}
