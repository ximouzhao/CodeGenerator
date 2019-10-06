package com.ximouzhao.test;

public class DemoMain {
    public static void main(String[] args) {
        String json="{\n" +
                "  \"ddbh\": \"11111\",\n" +
                "  \"retcode\": \"001\",\n" +
                "  \"retmsg\": \"msg\",\n" +
                "  \"eserialno\": \"1111111111\",\n" +
                "  \"einvoiceOutUrl\": \"http://www.baidu.com\",\n" +
                "  \"nsrmc\": \"购货方名称\",\n" +
                "  \"fee\": 3333333333333.3335,\n" +
                "  \"invoiceCode\": \"12312312321\",\n" +
                "  \"invoiceValue\": \"222222\",\n" +
                "  \"createDate\": \"2019-08-04\",\n" +
                "  \"invoiceType\": 1,\n" +
                "  \"standby1\": null,\n" +
                "  \"standby2\": null,\n" +
                "  \"standby3\": null,\n" +
                "  \"standby4\": null,\n" +
                "  \"standby5\": null,\n" +
                "  \"standby6\": null,\n" +
                "  \"standby7\": null,\n" +
                "  \"standby8\": null\n" +
                "}";
        String returnJson=HttpUtils.sendPostJSON("http://z422325773.xicp.net/salesInvoiceCallback",json);
        System.out.println(returnJson);
    }
}
