//package com.ximouzhao.xml.qwenxml;
//
//import com.jbs.hermes.hangxin.utils.IdGeneratorUUID;
//import com.jbs.hermes.salesinvoice.model.SalesInvoiceInvokeBodyModel;
//import com.jbs.hermes.salesinvoice.model.SalesInvoiceInvokeModel;
//import com.jbs.hermes.salesinvoice.model.SalesInvoiceInvokeRequestModel;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import java.io.ByteArrayOutputStream;
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Set;
//
///**
// * @author Qwen
// * @date 2019/9/4-12:05
// */
//public class TestXml {
//    public static void main(String[] args) throws JAXBException {
//        Serializable e=(Serializable)new ModelXMLProxy(new SalesInvoiceInvokeBodyModel()).newProxyInstance();
//        //System.out.println(obj.);
//        JAXBContext context = JAXBContext.newInstance(SalesInvoiceInvokeRequestModel.class, SalesInvoiceInvokeModel.class, SalesInvoiceInvokeBodyModel.class);
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setListener(new MarshallerListener());
//        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // 设置编码字符集
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进
//        //marshaller.marshal(getInvoiceRequest(),System.out);   // 打印到控制台
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        marshaller.marshal(getInvoiceRequest(), baos);
//        String xmlObj = new String(baos.toByteArray());         // 生成XML字符串
//        System.out.println(xmlObj);
//    }
//
//    public static SalesInvoiceInvokeRequestModel getInvoiceRequest() {
//        SalesInvoiceInvokeBodyModel invoiceDetailModel=(SalesInvoiceInvokeBodyModel)new ModelXMLProxy(new SalesInvoiceInvokeBodyModel()).newProxyInstance();
//        //SalesInvoiceInvokeBodyModel modelproxy=(SalesInvoiceInvokeBodyModel)proxy.newProxyInstance();
//
//        //详情
//        //SalesInvoiceInvokeBodyModel invoiceDetailModel = new SalesInvoiceInvokeBodyModel();
//
//        invoiceDetailModel.setXMMC("项目名称1").setXMDW("项目单位1").setSPBM("商品编码1");
//
//        SalesInvoiceInvokeBodyModel invoiceDetailModel2=(SalesInvoiceInvokeBodyModel)new ModelXMLProxy(new SalesInvoiceInvokeBodyModel()).newProxyInstance();
//        //SalesInvoiceInvokeBodyModel invoiceDetailModel2 = new SalesInvoiceInvokeBodyModel();
//        invoiceDetailModel2.setXMMC("项目名称2").setXMDW("项目单位2").setSPBM("商品编码2").setXMJE(new BigDecimal("222.222"));
//        ArrayList<SalesInvoiceInvokeBodyModel> invoiceDetailModels = new ArrayList<>();
//        invoiceDetailModels.add(invoiceDetailModel);
//        invoiceDetailModels.add(invoiceDetailModel2);
//
//        //发票
//        SalesInvoiceInvokeModel invoiceModel=(SalesInvoiceInvokeModel)new ModelXMLProxy(new SalesInvoiceInvokeModel()).newProxyInstance();
//
//        //SalesInvoiceInvokeModel invoiceModel = new SalesInvoiceInvokeModel();
//        invoiceModel.setDDBH("订单编号").setIS_APPLY(1).setKPXM("开票项目").setKPY("启文");
//        invoiceModel.setSalesInvoiceInvokeBodyModelList(invoiceDetailModels);
//
//        SalesInvoiceInvokeRequestModel invoiceRequestModel = new SalesInvoiceInvokeRequestModel();
//        invoiceRequestModel.setSalesInvoiceInvokeModel(invoiceModel);
//
//        String uuid= IdGeneratorUUID.getFixLenthString();
//        invoiceModel.setDDBH(uuid);
//
//        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
//        Validator validator = vf.getValidator();
//        Set<ConstraintViolation<SalesInvoiceInvokeModel>> set = validator.validate(invoiceModel);
//        for (ConstraintViolation<SalesInvoiceInvokeModel> constraintViolation : set) {
//            System.out.println(constraintViolation.getMessage());
//        }
//
//        return  invoiceRequestModel ;
//    }
//    public static SalesInvoiceInvokeModel getInvoiceModel() {
//        SalesInvoiceInvokeBodyModel invoiceDetailModel=new SalesInvoiceInvokeBodyModel();
//        //SalesInvoiceInvokeBodyModel modelproxy=(SalesInvoiceInvokeBodyModel)proxy.newProxyInstance();
//
//        //详情
//        //SalesInvoiceInvokeBodyModel invoiceDetailModel = new SalesInvoiceInvokeBodyModel();
//
//        invoiceDetailModel.setXMMC("项目名称1").setXMDW("项目单位1").setSPBM("商品编码1");
//
//        SalesInvoiceInvokeBodyModel invoiceDetailModel2=(SalesInvoiceInvokeBodyModel)new ModelXMLProxy(new SalesInvoiceInvokeBodyModel()).newProxyInstance();
//        //SalesInvoiceInvokeBodyModel invoiceDetailModel2 = new SalesInvoiceInvokeBodyModel();
//        invoiceDetailModel2.setXMMC("项目名称2").setXMDW("项目单位2").setSPBM("商品编码2").setXMJE(new BigDecimal("222.222"));
//        ArrayList<SalesInvoiceInvokeBodyModel> invoiceDetailModels = new ArrayList<>();
//        invoiceDetailModels.add(invoiceDetailModel);
//        invoiceDetailModels.add(invoiceDetailModel2);
//
//        //发票
//        SalesInvoiceInvokeModel invoiceModel=(SalesInvoiceInvokeModel)new ModelXMLProxy(new SalesInvoiceInvokeModel()).newProxyInstance();
//
//        //SalesInvoiceInvokeModel invoiceModel = new SalesInvoiceInvokeModel();
//        invoiceModel.setDDBH("订单编号").setIS_APPLY(1).setKPXM("开票项目").setKPY("启文");
//        invoiceModel.setSalesInvoiceInvokeBodyModelList(invoiceDetailModels);
//
//
//        return  invoiceModel ;
//    }
//
//}
