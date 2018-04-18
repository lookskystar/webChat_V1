package com.webChat.util.users;

import com.webChat.properties.Constants;
import com.webChat.util.NetWorkHelper;

/**
* @ClassName: UsersUtil
* @Description: TODO(用户管理工具类)
* @author andy
* @version V1.0  
* @date Sep 27, 2016 10:23:43 AM
 */
public class UsersUtil implements Constants{
	
	//创建用户分组
	public static String addUsersGroup(String access_token){
		
		NetWorkHelper netWorkHelper=new NetWorkHelper();
		UsersMsgBuild usersMsgBuild=new UsersMsgBuild();
		
		//响应消息
		String responseMessage=null;
		String url=String.format(ADD_KF_ACCOUNT_URL, access_token);
		String data=usersMsgBuild.addUsersGroup(null);
		
		responseMessage=netWorkHelper.getHttpsResponse(url, "POST",data);
		
		return responseMessage;
	}
	

	/*
	* @Title: setUserRemarkName 
	* @Description: TODO(设置用户备注名)
	* @param access_token 
	* @return 成功json字符串
	* @throws 
	*/ 
	public static String setUserRemarkName(String access_token){
		
		NetWorkHelper netWorkHelper=new NetWorkHelper();
		UsersMsgBuild usersMsgBuild=new UsersMsgBuild();
		
		//响应消息
		String responseMessage=null;
		
		String url=String.format(SET_USERS_REMARK_NAME_URL, access_token);
		String data=usersMsgBuild.setUsersRemarkName(null);
		
		responseMessage=netWorkHelper.getHttpsResponse(url, "POST", data);
		
		return responseMessage;
	}
	

}
