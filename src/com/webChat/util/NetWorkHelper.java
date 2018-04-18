package com.webChat.util;
import javax.net.ssl.*;

import com.alibaba.fastjson.JSONObject;
import com.webChat.properties.Constants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
* @ClassName: NetWorkHelper
* @Description: TODO(3、访问网络用到的工具类,发https请求)
* @author andy
* @version V1.0  
* @date Aug 30, 2016 9:42:17 AM
 */
public class NetWorkHelper implements Constants{

    /**
     * 发起Https请求
     * @param reqUrl 请求的URL地址
     * @param requestMethod
     * @return 响应后的字符串
     */
    public  String getHttpsResponse(String reqUrl, String requestMethod) {
        URL url;
        InputStream is;
        String resultData = "";
        try {
            url = new URL(reqUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = {xtm};

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            
            con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            con.setDoInput(true); //允许输入流，即允许下载
            //在android中必须将此项设置为false
            con.setDoOutput(false); //允许输出流，即允许上传
            con.setUseCaches(false); //不使用缓冲
            if (null != requestMethod && !requestMethod.equals("")) {  //""|null 默认为get请求
                con.setRequestMethod(requestMethod); //使用指定的方式
            } else {
                con.setRequestMethod("GET"); //使用get请求
            }
            is = con.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is,UTF_8);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData += inputLine + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }
    
    
    /*
    * @Title: getHttpsResponse 
    * @Description: TODO(发起https请求，并传入数据到服务器，使用get请求，requestMethod为"")
    * @param reqUrl
    * @param requestMethod
    * @param dataType
    * @param data
    * @return 
    * @throws 
    */ 	
    public String getHttpsResponse(String reqUrl, String requestMethod, String data) {
    	URL url;
        InputStream is;
        OutputStream os;
        
        String resultData = "";
        try {
            url = new URL(reqUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = {xtm};

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            
            con.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
            con.setDoInput(true); //允许输入流，即允许下载
            //在android中必须将此项设置为false
            con.setDoOutput(true); //允许输出流，即允许上传
            con.setUseCaches(false); //不使用缓冲
            if (null != requestMethod && !requestMethod.equals("")) {  //""|null 默认为get请求
                con.setRequestMethod(requestMethod); //使用指定的方式
            } else {
                con.setRequestMethod("GET"); //使用get请求
            }
            
            //连接传入数据
            os=con.getOutputStream();//此时才真正建立链接
            os.write(data.getBytes(UTF_8));
            os.flush();
            os.close();
            
            
            //获取发回的数据
            is = con.getInputStream();   //获取输入流
            InputStreamReader isr = new InputStreamReader(is,UTF_8);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData += inputLine + "\n";
            }
            
            is.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }
   

    X509TrustManager xtm = new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {

        }

        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {

        }
    };
}