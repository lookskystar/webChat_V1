package com.webChat.util.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.webChat.entry.MusicMessage;
import com.webChat.entry.NewsItem;
import com.webChat.entry.VideoMessage;
import com.webChat.properties.Constants;
import com.webChat.properties.MessageTypeEnum;
import com.webChat.util.PropertiesUtil;
import com.webChat.util.menu.MenuBuild;

/**
* @ClassName: MessageHandle
* @Description: TODO(接收消息处理类)
* @author andy
* @version V1.0  
* @date Sep 2, 2016 6:32:57 AM
 */
public class MessageHandle implements Constants{
	
	/*
	* @Title: buildResponseMessage 
	* @Description: TODO(根据解析微信发来的请求类型代码，调用构造消息方法，返回消息)
	* @param map 封装了解析结果的Map
	* @return  responseMessage（响应消息XML字符串）
	*/ 
	public static String handleResponseMessage(Map map){
		//响应消息
		String responseMessage = "";
		//得到消息类型,MsgType为微信服务器返回xml中的节点名，在requestParseXml中，被封装到map中
		String msgType=map.get("MsgType").toString();
		System.out.println("MsgType:-->"+msgType);
		
		MessageTypeEnum messageTypeEnum=Enum.valueOf(MessageTypeEnum.class, msgType.toUpperCase());
		
		switch (messageTypeEnum) {
		case TEXT:
			//处理文本消息
			System.out.println("处理文本消息,如果不是文本消息就分流");
			responseMessage=handleResponseTextMessage(map);
			break;
		case IMAGE:
			//处理图片消息
			System.out.println("处理图片消息");
			responseMessage=handleImageMessage(map);
			break;
		case VOICE:
			//处理语音消息
			System.out.println("处理语音消息");
			responseMessage=handleVoiceMessage(map);
			break;
		case VIDEO:
			//处理视频消息
			System.out.println("处理视频消息");
			responseMessage=handleVideoMessage(map);
			break;
		case SHORTVIDEO:
			//处理小视频消息
			System.out.println("处理小视频消息");
			responseMessage=handleSmallVideoMessage(map);
			break;
		case LOCATION:
			//处理位置消息
			System.out.println("处理位置消息");
			responseMessage= handleLocationMessage(map);
			break;
		case LINK:
			//处理链接消息
			System.out.println("处理链接消息");
			responseMessage=handleLinkMessage(map);
			break;
		case EVENT:
			//处理事件消息，用户在关注与取消关注公众号时，微信会向我们的公众号服务器发送事件消息，开发者接收到事件消息后就可以给用户发欢迎消息
			System.out.println("处理事件消息");
			responseMessage=handleEventMessage(map);
			break;
		case NEWS:
			System.out.println("处理图文消息");
			responseMessage=handleNewsMessage(map);
		default:
			break;
		}
		return responseMessage;
	}
	
	
	/*
	* @Title: handleTextMessage 
	* @Description: TODO(接收到消息后处理。文本消息直接在该方法处理，非文本消息则在该方法中再次分流进行构建处理，在该方法中，可以从数据库取数据)
	* @param map 封装了解析结果的Map
	* @return 处理后的文本消息字符串
	* @throws 
	*/ 
	public static String handleResponseTextMessage(Map<String,String> map){
		//响应消息
		String responseMessage=null;
		//消息内容
		String content=map.get("Content");
		
		//进行消息处理和分流
		if("文本".equals(content)){
			
			String textMessage_mainTitle=PropertiesUtil.getValueByKey(TEXT_MESSAGE_MAIN_TITLE);
			String textMessage_subTitle=PropertiesUtil.getValueByKey(TEXT_MESSAGE_SUB_TITLE);
			String textMessage_contents=PropertiesUtil.getValueByKey(TEXT_MESSAGE_CONTENTS);
			
			String msgText=textMessage_mainTitle+"\n" +
						   textMessage_subTitle+"\n"+
						   textMessage_contents;
			
			responseMessage=MessageBuild.buildTextMessage(map,msgText);
		}else if("图片".equals(content)){
			//通过素材管理接口上传图片时得到的media_id
			//String imgMediaId = "FCc8xiuMNAyN0f5kYHYM9qicy9apduEoMpjQBQXNo5W7PURy5OddG844iXlKiEGx";
			String imgMediaId = PropertiesUtil.getValueByKey(IMAGE_MESSAGE_MEDIAID);
			responseMessage = MessageBuild.buildImageMessage(map, imgMediaId);
		}else if ("语音".equals(content)) {
			// 通过素材管理接口上传语音文件时得到的media_id
			//String voiceMediaId = "iHu1oHQWt1VgeW_mzWop6wrqgpedvNLEGP6VkIP3Xh0F8vqgoabhxREzuxrjPk-j";
			String voiceMediaId=PropertiesUtil.getValueByKey(VOICE_MESSAGE_MEDIAID);
			responseMessage = MessageBuild.buildVoiceMessage(map,voiceMediaId);
		} else if ("图文".equals(content)) {
			
			//测试----------------------------------------------------从数据库中得到数据进行对象化并添加到list中
			//图文消息对象1
			NewsItem newsItem1=new NewsItem();
			//图文消息对象2
			NewsItem newsItem2=new NewsItem();
			NewsItem newsItem3=new NewsItem();

			newsItem1.setTitle("图文消息1标题测试--");
			newsItem1.setDescription("图文消息1描述测试--");
			//图片地址
			newsItem1.setPicUrl("http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%A0%E7%89%A9&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=4225406410,1864486066&os=2938138617,2473968941&simid=&pn=0&rn=1&di=0&ln=1986&fr=&fmq=1463574658346_R&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=&istype=2&ist=&jit=&bdtype=-1&adpicid=0&pi=0&gsm=0&objurl=http%3A%2F%2Fwww.dabaoku.com%2Fsucaidatu%2Fdongwu%2Fchongwujingling%2F902438.JPG&rpstart=0&rpnum=0&adpicid=0&ctd=1473170848620^3_1350X639%1");
			//连接地址
			newsItem1.setUrl("http://image.baidu.com/");
			
			newsItem2.setTitle("图文消息2标题测试00");
			newsItem2.setDescription("图文消息2描述测试00");
			newsItem2.setPicUrl("");
			newsItem2.setUrl("http://www.163.com");
			
			newsItem3.setTitle("图文消息3标题测试33");
			newsItem3.setDescription("图文消息3描述测试33");
			newsItem3.setPicUrl("");
			newsItem3.setUrl("http://www.163.com");
			
			List<NewsItem> list=new ArrayList<NewsItem>();

			list.add(newsItem1);
			list.add(newsItem2);
			list.add(newsItem3);
			//测试----------------------------------------------------
			
			//responseMessage = MessageBuild.buildNewsMessage(map);
			responseMessage = MessageBuild.buildNewsMessage(map,list);
			
		} else if ("音乐".equals(content)) {
			MusicMessage musicMessage = new MusicMessage();
			
			String music_title=PropertiesUtil.getValueByKey(MUSIC_TITLE);
			String music_description=PropertiesUtil.getValueByKey(MUSIC_DESCRIPTION);
			String music_music_url=PropertiesUtil.getValueByKey(MUSIC_MUSIC_URL);
			String music_hq_music_url=PropertiesUtil.getValueByKey(MUSIC_HQ_MUSIC_URL);
			
			System.out.println("music_music_url--->"+music_music_url);
			
			musicMessage.setTitle(music_title);
			musicMessage.setDescription(music_description);
			musicMessage.setMusicUrl(music_music_url);
			musicMessage.setHqMusicUrl(music_hq_music_url);
			
			responseMessage = MessageBuild.buildMusicMessage(map, musicMessage);
		} else if ("视频".equals(content)) {
			VideoMessage videoMessage = new VideoMessage();
			videoMessage.setMediaId(VIDEO_MESSAGE_MEDIAID);
			videoMessage.setTitle(VIDEO_MESSAGE_TITLE);
			videoMessage.setDescription(VIDEO_MESSAGE_DESCRIPTION);
			responseMessage = MessageBuild.buildVideoMessage(map, videoMessage);
		}else{//如果什么都不是，则返回文本类型欢迎提示消息
			responseMessage=MessageBuild.buildWelcomeTextMessage(map);
		}
		
		//返回响应消息
		return responseMessage;
	}
	
	
	
	/*
	* @Title: handleImageMessage 
	* @Description: TODO(处理接收到的图片消息)
	* @param map 封装好解析结果的map
	* @return 返回图片消息处理结果，以文本消息返回给微信
	* @throws 
	*/ 	
	public static String handleImageMessage(Map<String,String> map){
		String picUrl=map.get("PicUrl");
		String mediaId=map.get("MediaId");
		String result="";//消息关键内容以及提示信息
		
		result=String.format("已收到您发来的图片，图片Url为：%s\n图片素材Id为：%s",picUrl, mediaId);
		
		//返回图片消息处理结果，以文本消息返回给微信
		return MessageBuild.buildTextMessage(map,result);
	}
	
	
	/*
	* @Title: handleVoiceMessage 
	* @Description: TODO(处理接收到语音消息)
	* @param map 封装好解析结果的map
	* @return 返回图片消息处理结果，以文本消息返回给微信
	* @throws 
	*/ 
	public static String handleVoiceMessage(Map<String,String> map){
		String format=map.get("Format");
		String mediaId=map.get("MediaId");
		String result="";//消息关键内容以及提示信息
		
		result=String.format("已收到您发来的语音，语音格式为：%s\n语音素材Id为：%s", format, mediaId);
		
		//返回图片消息处理结果，以文本消息返回给微信
		return MessageBuild.buildTextMessage(map,result);
	}
	
	
	/*
	* @Title: handleVideoMessage 
	* @Description: TODO(处理接收到的视频消息)
	* @param map 封装好解析结果的map
	* @return 返回图片消息处理结果，以文本消息返回给微信
	* @throws 
	*/ 	
	public static String handleVideoMessage(Map<String,String> map){
		String thumbMediaId=map.get("ThumbMediaId");
		String mediaId=map.get("MediaId");
		String result="";//消息关键内容以及提示信息
		
		result=String.format("已收到您发来的视频，视频中的素材ID为:%s\n视频Id为:%s",thumbMediaId,mediaId);
		
		//返回图片消息处理结果，以文本消息返回给微信
		return MessageBuild.buildTextMessage(map,result);
	}
	
	/*
	* @Title: handleSmallVideoMessage 
	* @Description: TODO(处理接收到的短视频消息)
	* @param map 封装好解析结果的map
	* @return 返回图片消息处理结果，以文本消息返回给微信
	* @throws 
	*/ 
	public static String handleSmallVideoMessage(Map<String,String> map){
		String thumbMediaId=map.get("ThumbMediaId");
		String mediaId=map.get("MediaId");
		String result="";//消息关键内容以及提示信息
		
		result=String.format("已收到您发来的小视频，小视频中素材ID为：%s,\n小视频Id为：%s",thumbMediaId,mediaId);
		
		//返回图片消息处理结果，以文本消息返回给微信
		return MessageBuild.buildTextMessage(map,result);
	}
	
	
	
	/*
	* @Title: handleLocationMessage 
	* @Description: TODO(处理接收到位置返回经纬度信息)
	* @param map
	* @return 
	* @throws 
	*/ 
	public static String handleLocationMessage(Map<String,String> map){
		String location_X=map.get("Location_X");//纬度
		String location_y=map.get("Location_y");//经度
		String label=map.get("Label");//地理位置精度
		String result=String.format("纬度：%s\n经度：%s\n地理位置：%s", location_X,location_y,label);
		return MessageBuild.buildTextMessage(map,result);
	}
	
	/*
	* @Title: handleLinkMessage 
	* @Description: TODO(处理接收到的链接信息)
	* @param map 封装好解析结果的map
	* @return 返回链接消息处理结果，以文本消息返回给微信
	* @throws 
	*/ 
	public static String handleLinkMessage(Map<String,String> map){
		String title=map.get("Title");
		String description=map.get("Description");
		String url=map.get("Url");
		String result="";//消息关键内容以及提示信息
		
		result=String.format("已收到您发来的链接，链接标题为:\n描述为:%s\n,链接地址为:%s",title,description,url);
		
		//返回图片消息处理结果，以文本消息返回给微信
		return MessageBuild.buildTextMessage(map,result);
	}
	
	/*
	* @Title: handleEventMessage 
	* @Description: TODO(处理接收到的事件消息)
	* @param map
	* @return 
	* @throws 
	*/ 	
	public static String handleEventMessage(Map<String,String> map){
		
		System.out.println("Event=----------------------"+map.get("Event"));
		System.out.println("EventKey=----------------------"+map.get("EventKey"));
		System.out.println("MsgType=----------------------"+map.get("MsgType"));
		
		String responseMessage="";
		String eventKey=map.get("EventKey");
		
		//如果是单击V1001_TODAY_MUSIC菜单
		if("V1001_TODAY_MUSIC".equals(eventKey)){
			System.out.println("单击V1001_TODAY_MUSIC菜单");
			responseMessage=MenuBuild.buildClickEvent1(map);
		}
		else{
			responseMessage=MessageBuild.buildWelcomeTextMessage(map);
		}
		return responseMessage;
	}
	
	/*
	* @Title: handleNewsMessage 
	* @Description: TODO(处理图文消息)
	* @param map
	* @return 
	* @throws 
	*/ 
		
	public static String handleNewsMessage(Map<String,String> map){
		String responseMessage=MessageBuild.buildNewsMessage(map);
		return responseMessage;
	}

}
