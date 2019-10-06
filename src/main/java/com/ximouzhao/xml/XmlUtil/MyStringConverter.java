package com.ximouzhao.xml.XmlUtil;

import com.thoughtworks.xstream.converters.basic.StringConverter;

import java.util.Map;

public class MyStringConverter extends StringConverter {
    public MyStringConverter(Map map, int lengthLimit) {
        super(map, lengthLimit);
    }

    public MyStringConverter(Map map) {
        super(map);
    }

    public MyStringConverter(int lengthLimit) {
        super(lengthLimit);
    }

    public MyStringConverter() {
        super();
    }

    @Override
    public boolean canConvert(Class type) {
        return super.canConvert(type);
    }

    @Override
    public Object fromString(String str) {
        return super.fromString(str);
    }
}
