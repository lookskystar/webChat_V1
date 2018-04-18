package com.webChat.util.kfAccountMsg;

import com.webChat.entry.AccessTokenInfo;
import com.webChat.properties.Constants;
import com.webChat.util.NetWorkHelper;

/**
* @ClassName: KfAccountUtil
* @Description: TODO(客服接口开发类，测试号不能添加客服，没法测试)
* @author andy
* @version V1.0  
* @date Sep 26, 2016 10:51:07 AM
 */
public class KfAccountUtil implements Constants{
	
	//添加客服
	public static String addAccount(String access_token){
		NetWorkHelper netWorkHelper=new NetWorkHelper();
		String url=String.format(ADD_KF_ACCOUNT_URL, access_token);
		
		//响应消息
		String responseMessage=null;
		responseMessage=netWorkHelper.getHttpsResponse(url, "POST");
		
		return responseMessage;
	}
	
	//修改客服
	
	//删除客服
	
	//设置客服账号的头像
	
	//获取所有客服账号
	
	//客服接口-发送消息
		//发送文本消息
		//发送图片消息
		//发送语音消息
		//发送视频消息
		//发送音乐消息
		//发送图文消息
		//发送卡卷
	
}
