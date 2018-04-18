package com.webChat.util;

import com.webChat.entry.AccessToken;
import com.webChat.properties.Constants;

/**
* @ClassName: GetWebChatServerUtil
* @Description: TODO(获得微信服务器其他一些相关功能)
* @author andy
* @version V1.0  
* @date Sep 26, 2016 6:49:33 AM
 */
public class GetWebChatServerUtil implements Constants {
	
	/*
	* @Title: getWebChatServerIp 
	* @Description: TODO(得到微信服务器的IP地址)
	* @param access_token 公众号的access_token,系统启动时获得，过时时间7200秒
	* @return json格式的字符串ip地址列表
	* @throws 
	*/ 
	public static String getWebChatServerIp(String access_token){
		NetWorkHelper netWorkHelper=new NetWorkHelper();
		String url=String.format(WEB_CHAT_SERVER_IP_URL, access_token);
		
		//响应消息
		String responseMessage=null;
		responseMessage=netWorkHelper.getHttpsResponse(url, "GET");
		
		return responseMessage;
	}
	
	
	

}
