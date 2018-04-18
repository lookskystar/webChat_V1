package com.webChat.util.kfAccountMsg;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
* @ClassName: msgBuild
* @Description: TODO(构建客服消息类，测试号不能添加客服)
* @author andy
* @version V1.0  
* @date Sep 26, 2016 12:03:10 PM
 */
public class MsgBuild {
	
	
	/*
	* @Title: buildAddAccountMsg 
	* @Description: TODO(构建添加客服消息)
	* @param map 封装了解析结果的Map
	* @return 构建消息的json字符串
	* @throws 
	*/ 
	public static String buildAddAccountMsg(Map<String,String> map){
		String responseMsgJson=""; //构建消息的字符串
		
//		String kf_account=map.get("kf_account");
//		String nickname=map.get("nickname");
//		String password=map.get("password");
		
		String kf_account="lookskystar@lookings_8793的接口测试号";
		String nickname="风吹来的砂";
		String password="4ba10b303570e82b23528005b28f1f7d";
		
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("kf_account", kf_account);
		jsonObj.put("nickname", nickname);
		jsonObj.put("password", password);
		
		System.out.println(jsonObj.toJSONString());
		
		return responseMsgJson;
	}
	
	public static void main(String[] args) {
		buildAddAccountMsg(null);
	}
}
