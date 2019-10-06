package com.ximouzhao.xml.XmlUtil;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @ClassName: MoreDataResultNullConverter
 * @description: 处理含有null值对象的xstream转换器
 * @date: 2014-5-19 下午6:05:12
 *
 * @author renxingchen
 * @since JDK 1.6
 */

public class MoreDataResultNullConverter implements Converter {

	private Class currentType;
	private final String clazzNames[] = { "Person", "PhoneNumber","SalesInvoiceInvokeRequestModel","SalesInvoiceInvokeModel", "SalesInvoiceInvokeBodyModel"};// 定义所要转换的对象及所包含的对象名称
	private List<String> clazzNamesList;

	public boolean canConvert(Class type) {
		currentType = type;
		clazzNamesList = Arrays.asList(clazzNames);
		if (clazzNamesList.contains(currentType.getSimpleName())) {
			System.out.println(true);
			return true;
		} else {
			System.out.println(false);
			return false;
		}
	}
 

	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		try {
			marshalSuper(source, writer, context, currentType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 
		}
	}

	private Object getObj(Class clazz, String fieldName, Object source)
			throws Exception {
		Method method = clazz.getMethod("get"
				+ Character
						.toUpperCase(fieldName.substring(0, 1).toCharArray()[0])
				+ fieldName.substring(1));
		Object obj = null;
		if(source==null){
			return "";
		}
		obj = method.invoke(clazz.cast(source), new Object[0]);
		return obj;
	}

	private void objConverter(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context, Class clazz, String fieldName,
			Class fieldClazz,String nodeName) throws Exception {
		Object obj = getObj(clazz, fieldName, source);
		writer.startNode(nodeName);
		marshalSuper(obj, writer, context, fieldClazz);
		writer.endNode();
	}


	private void collectionConverter(Object source,
			HierarchicalStreamWriter writer, MarshallingContext context,
			Class clazz, String fieldName, Field field,String nodeName) throws Exception {
		Type types[] = ((ParameterizedType) field.getGenericType())
				.getActualTypeArguments();
		Object obj = getObj(clazz, fieldName, source);
		Collection collection = null;
		if (field.getType().equals(List.class)) {
			collection = (List) obj;
		} else if (field.getType().equals(Set.class)) {
			collection = (Set) obj;
		}
		Boolean isShowCollectionNode=(field.getAnnotation(XStreamImplicit.class)==null);
		if(isShowCollectionNode){
			writer.startNode(nodeName);
			if(collection==null){
				writer.setValue("");
			}else{
				for (Object object : collection) {
					String classNodeName=null;
					if(object.getClass().getAnnotation(XStreamAlias.class)!=null){
						classNodeName=object.getClass().getAnnotation(XStreamAlias.class).value();
					}else{
						classNodeName= ((Class) types[0]).getSimpleName();
					}
					writer.startNode(Character.toLowerCase(classNodeName.substring(0, 1)
							.toCharArray()[0]) + classNodeName.substring(1));
					marshalSuper(object, writer, context, (Class) types[0]);
					writer.endNode();
				}
			}
			writer.endNode();
		}else {
			for (Object object : collection) {
				String classNodeName=null;
				if(object.getClass().getAnnotation(XStreamAlias.class)!=null){
					classNodeName=object.getClass().getAnnotation(XStreamAlias.class).value();
				}else{
					classNodeName= ((Class) types[0]).getSimpleName();
				}
				writer.startNode(classNodeName);
				marshalSuper(object, writer, context, (Class) types[0]);
				writer.endNode();
			}
		}

	}

	private void basicTypeConverter(Object source,
			HierarchicalStreamWriter writer, MarshallingContext context,
			Class clazz,String fieldName, String nodeName) throws Exception {
		Object obj = getObj(clazz, fieldName, source);
		writer.startNode(nodeName);
		writer.setValue(obj == null ? "" : obj.toString());
		writer.endNode();
	}

	private void marshalSuper(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context, Class clazz) throws Exception {
		Field fields[] = clazz.getDeclaredFields();

		for (Field field : fields) {

			String fieldName = field.getName();
			String nodeName=fieldName;
			Class fieldClazz = field.getType();
			if(field.getAnnotation(XStreamOmitField.class)!=null){
			    continue;
            }else if(field.getAnnotation(XStreamAlias.class)!=null){
				nodeName= field.getAnnotation(XStreamAlias.class).value();
			}
			if (clazzNamesList.contains(fieldClazz.getSimpleName())) {
				objConverter(source, writer, context, clazz, fieldName,
						fieldClazz,nodeName);
			} else if (Arrays.asList(fieldClazz.getInterfaces()).contains(
					Collection.class)) {
				collectionConverter(source, writer, context, clazz, fieldName,
						field,nodeName);
			} else {
				basicTypeConverter(source, writer, context, clazz,fieldName, nodeName);
			}
		}
	}
 

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		// TODO Auto-generated method stub
		return null;
	}
 
}