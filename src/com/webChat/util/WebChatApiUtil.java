package com.webChat.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.webChat.properties.Constants;

/**
* @ClassName: WebChatApiUtil
* @Description: TODO(微信多媒体上传下载接口调用)
* @author andy
* @version V1.0  
* @date Sep 7, 2016 5:59:28 AM
 */
public class WebChatApiUtil {

	/*
	 * @Title: main 
	 * @Description: TODO(主函数，测试用)
	 * @param args 
	 * @throws 
	 */

	public static void main(String[] args) {
		//媒体文件路径
        String filePath = "D:\\fileUpload\\ip.JPG";
        //String filePath = "/Applications/apache-tomcat-7.0.70/webapps/webChat/media/voice/voice.mp3";
        //String filePath = "/Applications/apache-tomcat-7.0.70/webapps/webChat/media/video/xpg.mp4";
        //媒体文件类型
        String type = "image";
        //String type = "voice";
        //String type = "video";
        JSONObject uploadResult = uploadMedia(filePath, type);
        //{"media_id":"dSQCiEHYB-pgi7ib5KpeoFlqpg09J31H28rex6xKgwWrln3HY0BTsoxnRV-xC_SQ","created_at":1455520569,"type":"image"}
        System.out.println(uploadResult.toString());

        //下载刚刚上传的图片以id命名
        String media_id = uploadResult.getString("media_id");
        System.out.println("media_id:"+media_id);
        File file = downloadMedia("D:\\fileUpload\\" + media_id + ".jpg", media_id);
        //File file = downloadMedia("/Applications/apache-tomcat-7.0.70/webapps/webChat/media/video/" + media_id + ".mp3", media_id);
        System.out.println(file.getName());

	}
	
	/*
	* @Title: getTokenUrl 
	* @Description: TODO(得到TokenUrl地址)
	* @return 地址
	* @throws 
	*/ 
	public static String getTokenUrl(){
		return String.format(Constants.ACCESS_TOKEN,Constants.APP_ID,Constants.APP_SECRET);
	}
	
	/*
	* @Title: getDownloadUrl 
	* @Description: TODO(得到多媒体文件下载地址)
	* @param mediaId 多媒体文件id
	* @return 地址
	* @throws 
	*/ 	
	public static String getDownloadUrl(String mediaId){
		return String.format(Constants.DOWNLOAD_MEDIA,Constants.ACCESS_TOKEN,mediaId);
	}
	
	
	/*
	* @Title: getToken 
	* @Description: TODO(通过接口获取Token凭证)
	* @return 
	* @throws 
	*/ 
	public static String getToken(){
		String token=null;
		String tokenUrl=getTokenUrl();
		
		String response=httpsRequestToString(tokenUrl,"GET",null);
		
//		System.out.println(response);
		
		JSONObject jsonObject=JSON.parseObject(response);
		if(null!=jsonObject){
			try {
				token=jsonObject.getString("access_token");
				System.out.println("token:"+token);
			} catch (Exception e) {
				token=null;//获取token失败
			}
		}
		return token;
	}
	
	/*
	* @Title: uploadMedia 
	* @Description: TODO(微信服务器素材上传)
	* @param file 表单名称media
	* @param token 凭证
	* @param type type只支持四种类型素材（video/image/voice/thumb）
	* @return 
	* @throws 
	*/ 
	public static JSONObject uploadMedia(File file,String token,String type){
		if(file==null||token==null||type==null){
			return null;
		}
		
		if(!file.exists()){
			System.out.println("上传文件不存在，请检查！");
			return null;
		}
		
		String url=Constants.UPLOAD_MEDIA;
		JSONObject jsonObject=null;
		PostMethod post=new PostMethod(url);
		post.setRequestHeader("Connection","Keep-Alive");
		post.setRequestHeader("Cache-Control","no-cache");
		
		FilePart media;
		HttpClient httpClient=new HttpClient();
		//信任任何类型的证书
		Protocol myhttps=new Protocol("https",new SSLProtocolSocketFactory(),443);
		Protocol.registerProtocol("https", myhttps);
		
		try {
			media=new FilePart("media",file);
			Part[] parts=new Part[]{new StringPart("access_token",token),new StringPart("type",type),media};
			MultipartRequestEntity entity=new MultipartRequestEntity(parts,post.getParams());
			post.setRequestEntity(entity);
			int status=httpClient.executeMethod(post);
			if(status==HttpStatus.SC_OK){
				String text=post.getResponseBodyAsString();
				jsonObject=JSONObject.parseObject(text);
			}else{
				System.out.println("上传media错误，status是："+status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	/*
	* @Title: downloadMedia 
	* @Description: TODO(多媒体下载接口)
	* @param fileName 文件名
	* @param token 认证token
	* @param mediaId 素材id（对应上传后获取到的ID）
	* @return 素材文件
	* @throws 
	* @comment 不支持视频文件下载
	*/ 
	public static File downloadMedia(String fileName,String token,String mediaId){
		String url=getDownloadUrl(mediaId);
		return httpRequestToFile(fileName,url,"GET",null);
	}
	
	/*
	* @Title: downloadMedia 
	* @Description: TODO(多媒体下载接口)
	* @param fileName 文件名
	* @param mediaId 素材ID（对应上传后获取到的ID）
	* @return 素材文件
	* @throws 
	* @comment 不支持视频文件的下载
	*/ 
	public static File downloadMedia(String fileName,String mediaId){
		String token=getToken();
		return downloadMedia(fileName,token,mediaId);
	}
	
	
	/*
	* @Title: upliadMedia 
	* @Description: TODO(上传素材)
	* @param filePath 媒体文件路径（绝对路径）
	* @param type 媒体文件类型，图片（image）,语音（voice）,视频（video）,和缩列图（thumb）
	* @return 
	* @throws 
	*/ 
	public static JSONObject uploadMedia(String filePath,String type){
		File file=new File(filePath); //获取本地文件
		String token=getToken();
		
		
		JSONObject jsonObject=uploadMedia(file,token,type);
		return jsonObject;
	}
	
	
	/*
	* @Title: httpsRequestToString 
	* @Description: TODO(发送请求以https方式发送请求并将请求响应内容以String方式返回) 
	* @param path 请求路径
	* @param method 请求方法
	* @param body 请求数据体
	* @return 请求响应内容转换成字符串信息
	* @throws 
	*/ 	
	public static String httpsRequestToString(String path,String method,String body){
		if(path==null||method==null){
			return null;
		}
		
		String response=null;
		InputStream inputStream=null;
		InputStreamReader inputStreamReader=null;
		BufferedReader bufferedReader=null;
		HttpsURLConnection conn=null;
		
		try {
			TrustManager[] tm={new JEEWeiXinX509TrustManager()};
			SSLContext sslContext=SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf=sslContext.getSocketFactory();
			
			System.out.println("path->"+path);
			URL url=new URL(path);
			conn=(HttpsURLConnection)url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(method);
			
			if(null!=body){
				OutputStream outputStream=conn.getOutputStream();
				outputStream.write(body.getBytes("UTF-8"));
				outputStream.close();
			}
			
			inputStream=conn.getInputStream();
		
			inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
			
			bufferedReader=new BufferedReader(inputStreamReader);
			String str=null;
			StringBuffer buffer=new StringBuffer();
			
		
			
			
			while((str=bufferedReader.readLine())!=null){
				buffer.append(str);
			}
			
			response=buffer.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(conn!=null){
				conn.disconnect();
			}
			try {
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return response;
	}
	
	
	/*
	* @Title: httpRequestToFile 
	* @Description: TODO(以http方式发送请求，并将请求响应内容输出到文件)
	* @param fileName 文件名
	* @param path 请求路径
	* @param method 请求方法
	* @param body 请求数据
	* @return 返回响应的存储到文件
	* @throws 
	*/ 
	public static File httpRequestToFile(String fileName,String path,String method,String body){
		if(fileName==null||path==null||method==null){
			return null;
		}
		
		File file=null;
		HttpURLConnection conn=null;
		InputStream inputStream=null;
		FileOutputStream fileOut=null;
		
		  try {
	            URL url = new URL(path);
	            conn = (HttpURLConnection) url.openConnection();
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            conn.setUseCaches(false);
	            conn.setRequestMethod(method);
	            if (null != body) {
	                OutputStream outputStream = conn.getOutputStream();
	                outputStream.write(body.getBytes("UTF-8"));
	                outputStream.close();
	            }

	            inputStream = conn.getInputStream();
	            if (inputStream != null) {
	                file = new File(fileName);
	            } else {
	                return file;
	            }

	            //写入到文件
	            fileOut = new FileOutputStream(file);
	            if (fileOut != null) {
	                int c = inputStream.read();
	                while (c != -1) {
	                    fileOut.write(c);
	                    c = inputStream.read();
	                }
	            }
	        } catch (Exception e) {
	        } finally {
	            if (conn != null) {
	                conn.disconnect();
	            }

	            /*
	             * 必须关闭文件流
	             * 否则JDK运行时，文件被占用其他进程无法访问
	             */
	            try {
	                inputStream.close();
	                fileOut.close();
	            } catch (IOException execption) {
	            }
	        }
	        return file;
	}

}
