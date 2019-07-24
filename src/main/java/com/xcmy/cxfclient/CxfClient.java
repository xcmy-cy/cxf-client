package com.xcmy.cxfclient;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.namespace.QName;

public class CxfClient {

    public static void main(String[] args) {
        main1();
    }

    /**
     * 功能描述: 动态调用
     * PS:调用webservice可以通过
     * 生成客户端文件,这种方式需要将代理文件放入项目中,调用超级简单但是很少用
     * 用HttpClient发送soap报文,需要区分soap版本,需要组装请求报文,复用性和控制性(超时时间等)好
     * cxf静态调用,类似生成客户端文件,但只需要服务端通过server类(没用过,简单查了一下),通过JaxWsProxyFactoryBean类
     * cxf动态调用JaxWsDynamicClientFactory.newInstance(),只要提供接口名和路径就可以调用.
     *
     * @Author: cy
     * @Date: 2019-07-23 11:52
     * @Version 1.0
     **/
    public static void main1() {

        // 接口地址
        String address = "http://192.168.0.116:8080/services/user?wsdl";
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(address);
        //指定请求的命名空间和方法名(当服务端接口和实现不在同一个包下必须指定接口的命名空间)
        QName name = new QName("http://server.cxfserver.xcmy.com/", "getUser");
//        当接口和实现在同一个包下 可以直接用方法名
//        String name = "getUser";

        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke(name, "2");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
