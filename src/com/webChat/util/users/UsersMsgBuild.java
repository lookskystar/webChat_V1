package com.webChat.util.users;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.webChat.util.message.MessageBuild;

/**
* @ClassName: UsersMsgBuild
* @Description: TODO(构建用户管理消息json)
* @author andy
* @version V1.0  
* @date Sep 28, 2016 4:13:05 AM
 */
public class UsersMsgBuild {
	
	
	/*
	* @Title: addUsersGroup 
	* @Description: TODO(创建用户分组消息)
	* @param map 封装了解析结果的Map
	* @return 创建分组json字符串
	* @throws 
	*/ 
	public static String addUsersGroup(Map<String,String> map){
		String responseMsgJson=""; //构建消息的字符串
				
		String name="test"; //分组名
		
		//{"group":{"name":"test"}}
		
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("name", name);
		
		responseMsgJson="{\"group\":"+jsonObj.toJSONString()+"}";
		
		System.out.println(responseMsgJson);
		
		return responseMsgJson;
	}
	
	
	/*
	* @Title: setUsersRemarkName 
	* @Description: TODO(构建设置用户备注名消息)
	* @param map 封装了解析结果的Map
	* @return 用户备注名json字符串
	* @throws 
	*/ 
	public static String setUsersRemarkName(Map<String,String> map){
		String responseMsgJson=""; //构建消息的字符串
		
		String openid="oRdXYwBSEkWx25PMj54wS72au9H4";
		String remarkName="test";
	
		
		JSONObject jsonObj=new JSONObject();
		
		jsonObj.put("openid", openid);
		jsonObj.put("remark", remarkName);
		
		responseMsgJson=jsonObj.toJSONString();
		
		return responseMsgJson;
	}
	
	/*
	* @Title: getUserLocation 
	* @Description: TODO(构建获取用户地理位置xml字符串)
	* @param map 封装了解析结果的Map
	* @return 用户地理位置xml字符串
	* @throws 
	*/ 
	public static String getUserLocation(Map<String,String> map){
		String responseMsgXml="";
		
		String toUserName=map.get("ToUserName"); //开发者微信号
		String fromUserName=map.get("FromUserName"); //发送者微信号
		
		String latitude=map.get("Latitude");
		String longitude=map.get("Longitude");
		String precision=map.get("Precision");
		
		responseMsgXml=String.format(
				"<xml>"+
					"<ToUserName><![CDATA[%s]]></ToUserName>"+
					"<FromUserName><![CDATA[%s]]></FromUserName>"+
					"<CreateTime>%s</CreateTime>"+
					"<MsgType><![CDATA[event]]></MsgType>"+
					"<Event><![CDATA[LOCATION]]></Event>"+
					"<Latitude>%s</Latitude>"+
					"<Longitude>%s</Longitude>"+
					"<Precision>%s</Precision>"+
				"</xml>"
				,
				toUserName,fromUserName,MessageBuild.getMessageCreateTime(),latitude,longitude,precision
		);
		
		return responseMsgXml;
	}
	
	
	
	public static void main(String[] args) {
		addUsersGroup(null);
	}
}
