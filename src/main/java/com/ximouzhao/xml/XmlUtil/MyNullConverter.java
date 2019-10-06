package com.ximouzhao.xml.XmlUtil;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

public class MyNullConverter implements Converter {
    public boolean canConvert(Class type) {
        System.out.println(type.getSimpleName());
        return true;
    }

    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        System.out.println(source);
        ExtendedHierarchicalStreamWriterHelper.startNode(writer, "null", Mapper.Null.class);
        writer.endNode();
    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return null;
    }
}
