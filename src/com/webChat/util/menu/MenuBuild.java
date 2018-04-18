package com.webChat.util.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webChat.entry.ButtonEntry;
import com.webChat.entry.ButtonOkEntry;
import com.webChat.entry.ClickEntry;
import com.webChat.entry.Sub_ButtonEntry;
import com.webChat.entry.ViewEntry;
import com.webChat.util.message.MessageBuild;

/**
* @ClassName: MenuBuild
* @Description: TODO(菜单构建)
* @author andy
* @version V1.0  
* @date Oct 9, 2016 6:49:16 AM
 */
public class MenuBuild {
	
	/*
	* @Title: bulidClikAndViewMenuTest 
	* @Description: TODO(测试创建菜单)
	* @return 
	* @throws 
	*/ 
	public static String bulidClikAndViewMenuTest(){
		String responseMessageJson=""; //构造消息json字符
		
//		Map<String,Object> map = new HashMap<String,Object>();
//		List<Object> list=new ArrayList<Object>();
//		
//		ButtonEntry buttonEntry=new ButtonEntry();
//		buttonEntry.setType("click");
//		buttonEntry.setName("今日歌曲");
//		buttonEntry.setKey("V1001_TODAY_MUSIC");
//		
//		Sub_ButtonEntry sub_ButtonEntry=new Sub_ButtonEntry();
//		sub_ButtonEntry.setName("菜单");
//		
//		List<Object> list1=new ArrayList<Object>();
//		
//		ViewEntry viewEntry=new ViewEntry();
//		viewEntry.setType("view");
//		viewEntry.setName("搜索");
//		viewEntry.setUrl("http://www.soso.com/");
//		
//		ViewEntry viewEntry1=new ViewEntry();
//		viewEntry1.setType("view");
//		viewEntry1.setName("视频");
//		viewEntry1.setUrl("http://v.qq.com/");
//		
//		ClickEntry clickEntry=new ClickEntry();
//		clickEntry.setType("click");
//		clickEntry.setName("赞一下我们");
//		clickEntry.setKey("V1001_GOOD");
//		
//		sub_ButtonEntry.setSub_button(list1);
//		
//		list.add(sub_ButtonEntry);
//		
//		list1.add(clickEntry);
//		list1.add(viewEntry1);
//		list1.add(viewEntry);
//		
//		list.add(buttonEntry);
//		
//		map.put("button", list);
		
		
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		List<Object> list1=new ArrayList<Object>();
		List<Object> list2=new ArrayList<Object>();
		
		Sub_ButtonEntry sub_ButtonEntry=new Sub_ButtonEntry();
		ClickEntry clickEntry1=new ClickEntry();
		ViewEntry viewEntry1=new ViewEntry();
		ViewEntry viewEntry2=new ViewEntry();
		ClickEntry clickEntry2=new ClickEntry();

		
		clickEntry1.setType("click");
		clickEntry1.setName("今日歌曲");
		clickEntry1.setKey("V1001_TODAY_MUSIC");
		
		viewEntry1.setType("view");
		viewEntry1.setName("搜索");
		viewEntry1.setUrl("http://www.soso.com/");
		
		viewEntry2.setType("view");
		viewEntry2.setName("视频");
		viewEntry2.setUrl("http://v.qq.com/");
		
		clickEntry2.setType("click");
		clickEntry2.setName("赞一下我们");
		clickEntry2.setKey("V1001_GOOD");
		
		sub_ButtonEntry.setName("菜单");
		
		
		list1.add(clickEntry1);
		list1.add(sub_ButtonEntry);
		
		list2.add(viewEntry1);
		list2.add(viewEntry2);
		list2.add(clickEntry1);
		
		sub_ButtonEntry.setSub_button(list2);
		
		map.put("button", list1);
		
		responseMessageJson=JSONObject.toJSON(map).toString();
		
		System.out.println(responseMessageJson);
	
		return responseMessageJson;
	}
	
	
	/*
	* @Title: bulidClikAndViewMenu 
	* @Description: TODO(创建菜单1——点击和显示)
	* @return json字符串
	* @throws 
	*/ 
	public static String bulidClikAndViewMenu(){
		String responseMessageJson=""; //构造消息json字符
		responseMessageJson="{" +
			     "\"button\":[" +
			     "{"+	
			          "\"type\":\"click\"," +
			          "\"name\":\"测试推送事件\"," +
			          "\"key\":\"V1001_TODAY_MUSIC\""+
			      "}," +
			      "{"+
			           "\"name\":\"菜单\","+
			           "\"sub_button\":["+
			           "{"+	
			               "\"type\":\"view\","+
			               "\"name\":\"搜索\","+
			               "\"url\":\"http://www.soso.com/\""+
			            "},"+
			            "{"+
			               "\"type\":\"view\","+
			               "\"name\":\"视频\","+
			               "\"url\":\"http://v.qq.com/\""+
			            "},"+
			            "{"+
			               "\"type\":\"click\","+
			               "\"name\":\"赞一下我们\","+
			               "\"key\":\"V1001_GOOD\""+
			            "}]"+
			       "}]"+
			 "}";
		
		System.out.println(responseMessageJson);
		return responseMessageJson;
	}
	
	
	/*
	* @Title: bulidScancodeAndSendPic 
	* @Description: TODO(构建扫码、发图、发送位置，图文消息等菜单)
	* @return json字符串
	* @throws 
	*/ 
	public static String bulidScancodeAndSendPic(){
		String responseMessageJson=""; //构造消息json字符
		
		responseMessageJson="{"+
			    				"\"button\": ["+
			    								"{"+
			    									"\"name\": \"扫码\","+ 
			    									"\"sub_button\": ["+
			    														"{"+
			    															"\"type\": \"scancode_waitmsg\","+ 
			    															"\"name\": \"扫码带提示\"," +
			    															"\"key\": \"rselfmenu_0_0\","+ 
			    															"\"sub_button\": [ ]"+
			    														 "},"+ 
			    														 "{"+
			    														 	"\"type\": \"scancode_push\","+ 
			    														 	"\"name\": \"扫码推事件\","+ 
			    														 	"\"key\": \"rselfmenu_0_1\","+ 
			    														 	"\"sub_button\": [ ]"+
			    														 "}"+
			    													 "]"+
			               						 "},"+ 
			               						 "{"+
			               						 	"\"name\": \"发图\","+ 
			               						 	"\"sub_button\": ["+
			               						 "{"+
			               						 	"\"type\": \"pic_sysphoto\","+ 
			               						 	"\"name\": \"系统拍照发图\","+ 
			               						 	"\"key\": \"rselfmenu_1_0\","+ 
			               						 	"\"sub_button\": [ ]"+
			               						 "}," +
			               						 "{"+
			               						 	"\"type\": \"pic_photo_or_album\","+ 
			               						 	"\"name\": \"拍照或者相册发图\","+ 
			               						 	"\"key\": \"rselfmenu_1_1\","+ 
			               						 	"\"sub_button\": [ ]"+
			                       				 "},"+ 
			                       				 "{"+
			                       				 	"\"type\": \"pic_weixin\","+ 
			                       				 	"\"name\": \"微信相册发图\","+ 
			                       				 	"\"key\": \"rselfmenu_1_2\","+ 
			                       				 	"\"sub_button\": [ ]"+
			                       				 "}"+
			                       			 "]"+
			                  "},"+ 
			                  "{"+
			                  	"\"name\": \"发送位置\","+ 
			                  	"\"type\": \"location_select\","+ 
			                  	"\"key\": \"rselfmenu_2_0\","+
			                  "},"+
			                  "{"+
			                  	"\"type\": \"media_id\","+ 
			                  	"\"name\": \"图片\"," +
			                  	"\"media_id\": \"MEDIA_ID1\""+
			               	  "},"+ 
			               	  "{"+
			               	  	"\"type\": \"view_limited\","+ 
			               	  	"\"name\": \"图文消息\","+ 
			               	  	"\"media_id\": \"MEDIA_ID2\""+
			                  "}"+
			       			"}";
		
		System.out.println(responseMessageJson);
		return responseMessageJson;
	}
	
	
	
	
	/*
	* @Title: buildClickEvent1 
	* @Description: TODO(点击菜单拉取消息时的时间推送1)
	* @param map
	* @return 
	* @throws 
	*/ 
	public static String buildClickEvent1(Map<String,String> map){
		String responseMessageXml=""; //构造消息xml字符串
		String toUserName=map.get("ToUserName"); //开发者微信号
		String fromUserName=map.get("FromUserName"); //发送者微信号
		
		String eventKey=map.get("EventKey");//事件KEY值，与自定义菜单接口中KEY值对应
		
		responseMessageXml=String.format(
				"<xml>"+ 
					"<ToUserName><![CDATA[%s]]></ToUserName>"+
					"<FromUserName><![CDATA[%s]]></FromUserName>"+
					"<CreateTime>%s</CreateTime>"+
					"<MsgType><![CDATA[text]]></MsgType>"+
					"<Content><![CDATA[%s]]></Content>"+
				"</xml>", 
				fromUserName,toUserName,MessageBuild.getMessageCreateTime(),
				"点击菜单V1001_TODAY_MUSIC拉取消息时的时间推送1"
		);
		return responseMessageXml;
	}
	

	public static void main(String[] args) {
		bulidScancodeAndSendPic();
	}
}
