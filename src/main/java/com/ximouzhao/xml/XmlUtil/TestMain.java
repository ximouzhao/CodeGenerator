//package com.ximouzhao.xml.XmlUtil;
//
//import com.jbs.hermes.hangxin.utils.IdGeneratorUUID;
//import com.jbs.hermes.salesinvoice.model.SalesInvoiceInvokeBodyModel;
//import com.jbs.hermes.salesinvoice.model.SalesInvoiceInvokeModel;
//import com.jbs.hermes.salesinvoice.model.SalesInvoiceInvokeRequestModel;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.xml.DomDriver;
//import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//public class TestMain {
//    public static void main(String[] args) {
//        //XStream xstream = new XStream();
//        //XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
//
////        Person joe = new Person("Joe", "Walnes",
////                new PhoneNumber(123, "1234-456"),
////                new PhoneNumber(123, "9999-999"));
//        //joe.setLastname(null);
//        //String xml = xstream.toXML(joe);
//        //System.out.println(xml);
//
//        PhoneNumber number1=new PhoneNumber(222,"15454154",new BigDecimal("222.22"),new Timestamp(new Date().getTime()));
//        PhoneNumber number2=new PhoneNumber(222,"15454154",new BigDecimal("222.22"),new Timestamp(new Date().getTime()));
//
//        XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
//        Person kiven=new Person();
//        kiven.setLastname("hhh");
//
//        List<PhoneNumber> list=new ArrayList<>();
//        list.add(number1);
//        list.add(number2);
//
//        kiven.setPhoneNumberList(list);
//        //xstream.registerConverter(new MyNullConverter());
//        xstream.autodetectAnnotations(true);
//        xstream.registerConverter(new MoreDataResultNullConverter());
//        //String xmlKiven=xstream.toXML(kiven);
//
//
//        //System.out.println(xmlKiven);
//
//        String model=xstream.toXML(getInvoiceRequest());
//        System.out.println(model);
//
//    }
//    private static SalesInvoiceInvokeRequestModel getInvoiceRequest() {
//        //SalesInvoiceInvokeBodyModel invoiceDetailModel=(SalesInvoiceInvokeBodyModel)new ModelXMLProxy(new SalesInvoiceInvokeBodyModel()).newProxyInstance();
//        //SalesInvoiceInvokeBodyModel modelproxy=(SalesInvoiceInvokeBodyModel)proxy.newProxyInstance();
//
//        //详情
//        SalesInvoiceInvokeBodyModel invoiceDetailModel = new SalesInvoiceInvokeBodyModel();
//
//        invoiceDetailModel.setXMMC("项目名称1").setXMDW("项目单位1").setSPBM("商品编码1");
//
//        //SalesInvoiceInvokeBodyModel invoiceDetailModel2=(SalesInvoiceInvokeBodyModel)new ModelXMLProxy(new SalesInvoiceInvokeBodyModel()).newProxyInstance();
//        SalesInvoiceInvokeBodyModel invoiceDetailModel2 = new SalesInvoiceInvokeBodyModel();
//        invoiceDetailModel2.setXMMC("项目名称2").setXMDW("项目单位2").setSPBM("商品编码2").setXMJE(new BigDecimal("222.222"));
//        ArrayList<SalesInvoiceInvokeBodyModel> invoiceDetailModels = new ArrayList<>();
//        invoiceDetailModels.add(invoiceDetailModel);
//        invoiceDetailModels.add(invoiceDetailModel2);
//
//        //发票
//        //SalesInvoiceInvokeModel invoiceModel=(SalesInvoiceInvokeModel)new ModelXMLProxy(new SalesInvoiceInvokeModel()).newProxyInstance();
//
//        SalesInvoiceInvokeModel invoiceModel = new SalesInvoiceInvokeModel();
//        invoiceModel.setDDBH("订单编号").setIS_APPLY(1).setKPXM("开票项目").setKPY("启文").setGHF_GDDH("GDDH");
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
//        //SalesInvoiceInvokeBodyModel invoiceDetailModel=(SalesInvoiceInvokeBodyModel)new ModelXMLProxy(new SalesInvoiceInvokeBodyModel()).newProxyInstance();
//        //SalesInvoiceInvokeBodyModel modelproxy=(SalesInvoiceInvokeBodyModel)proxy.newProxyInstance();
//
//        //详情
//        SalesInvoiceInvokeBodyModel invoiceDetailModel = new SalesInvoiceInvokeBodyModel();
//
//        invoiceDetailModel.setXMMC("项目名称1").setXMDW("项目单位1").setSPBM("商品编码1");
//
//        //SalesInvoiceInvokeBodyModel invoiceDetailModel2=(SalesInvoiceInvokeBodyModel)new ModelXMLProxy(new SalesInvoiceInvokeBodyModel()).newProxyInstance();
//        SalesInvoiceInvokeBodyModel invoiceDetailModel2 = new SalesInvoiceInvokeBodyModel();
//        invoiceDetailModel2.setXMMC("项目名称2").setXMDW("项目单位2").setSPBM("商品编码2").setXMJE(new BigDecimal("222.222"));
//        ArrayList<SalesInvoiceInvokeBodyModel> invoiceDetailModels = new ArrayList<>();
//        invoiceDetailModels.add(invoiceDetailModel);
//        invoiceDetailModels.add(invoiceDetailModel2);
//
//        //发票
//        //SalesInvoiceInvokeModel invoiceModel=(SalesInvoiceInvokeModel)new ModelXMLProxy(new SalesInvoiceInvokeModel()).newProxyInstance();
//
//        SalesInvoiceInvokeModel invoiceModel = new SalesInvoiceInvokeModel();
//        invoiceModel.setDDBH("订单编号").setIS_APPLY(1).setKPXM("开票项目").setKPY("启文").setGHF_GDDH("GDDH");
//        invoiceModel.setSalesInvoiceInvokeBodyModelList(invoiceDetailModels);
//        return  invoiceModel ;
//    }
//}
