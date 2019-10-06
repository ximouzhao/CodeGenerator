package com.ximouzhao.xml.qwenxml;

import javax.xml.bind.Marshaller;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class MarshallerListener extends Marshaller.Listener {
 
    public static final String BLANK_CHAR = "";
 
    @Override
    public void beforeMarshal(Object source) {
        super.beforeMarshal(source);
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            //获取字段上注解<pre name="code" class="java">          
             try {
                    if(f.getAnnotation(XMLNotNeed.class)==null){
                        if (f.get(source) == null) {
                            if(f.getType()==String.class){
                                f.set(source, BLANK_CHAR);
                            }else if(f.getType()==Integer.class){
                                f.set(source,0);
                                f.set(source,null);
                            }else if(f.getType()== BigDecimal.class){
                                f.set(source,new BigDecimal(0));
                                f.set(source,null);
                            }
                            else{
                                f.set(source, null);
                            }
                        }
                    }
                    //f.set(source, BLANK_CHAR);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void afterMarshal(Object source) {
        super.afterMarshal(source);
    }
}