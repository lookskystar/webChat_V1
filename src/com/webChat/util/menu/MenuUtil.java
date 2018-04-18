package com.webChat.util.menu;

import com.webChat.entry.AccessTokenInfo;
import com.webChat.properties.Constants;
import com.webChat.util.NetWorkHelper;

/**
* @ClassName: MenuUtil
* @Description: TODO(创建菜单)
* @author andy
* @version V1.0  
* @date Oct 10, 2016 5:15:17 AM
 */
public class MenuUtil implements Constants{
	static NetWorkHelper netWorkHelper=new NetWorkHelper();
	/*
	* @Title: bulidClikAndViewMenu 
	* @Description: TODO(创建点击菜单)
	* @param access_token
	* @return 
	* @throws 
	*/ 
	public static String bulidClikAndViewMenu(String access_token){
		//响应消息
		String responseMessage=null;
		
		String data=MenuBuild.bulidClikAndViewMenu();
		
		String url=String.format(MENU_CREATE_URL, access_token);
		
		responseMessage=netWorkHelper.getHttpsResponse(url, "POST",data);
		
		return responseMessage;
	}
	
	
	/*
	* @Title: bulidScancodeAndSendPic 
	* @Description: TODO(创建，扫码，发图片菜单)
	* @param access_token
	* @return 
	* @throws 
	*/ 
	public static String bulidScancodeAndSendPic(String access_token){
		//响应消息
		String responseMessage=null;
		
		String data=MenuBuild.bulidScancodeAndSendPic();
		
		String url=String.format(MENU_CREATE_URL, access_token);
		
		responseMessage=netWorkHelper.getHttpsResponse(url, "POST",data);
		
		return responseMessage;
	}
	
	
	/*
	* @Title: getMenu 
	* @Description: TODO(得到自定义菜单)
	* @param access_token
	* @return 
	* @throws 
	*/ 
	public static String getMenu(String access_token){
		//响应消息
		String responseMessage=null;
		
		String url=String.format(GET_MENU_URL, access_token);
		
		responseMessage=netWorkHelper.getHttpsResponse(url, "");
		
		return responseMessage;
	}
	
	public static String delMenu(String access_token){
		//响应消息
		String responseMessage=null;
		
		String url=String.format(DEL_MENU_URL, access_token);
		
		responseMessage=netWorkHelper.getHttpsResponse(url, "");
		
		return responseMessage;
	}
}
