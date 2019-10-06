package com.ximouzhao.YApiGenerator;

import java.util.HashMap;

/**
 * 此类仅作为对照，方便查看
 */
public class DataTypeCodeValueMap {
    public static HashMap<Integer,String> hashmap=new HashMap();
    static {
        hashmap.put(7,"FLOAT");
        hashmap.put(7,"FLOAT unsigned");
        hashmap.put(-4,"BLOB");
        hashmap.put(1,"SET");
        hashmap.put(-2,"BINARY");
        hashmap.put(3,"DECIMAL");
        hashmap.put(3,"DECIMAL unsigned");
        hashmap.put(1,"CHAR");
        hashmap.put(-1,"TEXT");
        hashmap.put(-1,"MEDIUMTEXT");
        hashmap.put(4,"INT");
        hashmap.put(91,"YEAR");
        hashmap.put(93,"TIMESTAMP");
        hashmap.put(8,"DOUBLE");
        hashmap.put(8,"DOUBLE unsigned");
        hashmap.put(12,"TINYTEXT");
        hashmap.put(-6,"TINYINT");
        hashmap.put(-4,"LONGBLOB");
        hashmap.put(4,"INTEGER");
        hashmap.put(-2,"GEOMETRY");
        hashmap.put(1,"ENUM");
        hashmap.put(3,"NUMERIC");
        hashmap.put(3,"NUMERIC unsigned");
        hashmap.put(-1,"LONGTEXT");
        hashmap.put(-5,"BIGINT");
        hashmap.put(92,"TIME");
        hashmap.put(4,"MEDIUMINT");
        hashmap.put(-7,"BIT");
        hashmap.put(91,"DATE");
        hashmap.put(93,"DATETIME");
        hashmap.put(-2,"TINYBLOB");
        hashmap.put(-4,"MEDIUMBLOB");
        hashmap.put(5,"SMALLINT");
        hashmap.put(4,"INT24");
        hashmap.put(8,"REAL");
        hashmap.put(12,"VARCHAR");
        hashmap.put(-3,"VARBINARY");
    }
    public static String get(int key){
        return hashmap.get(key);
    }
}
