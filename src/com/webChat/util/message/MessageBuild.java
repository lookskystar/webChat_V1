package com.webChat.util.message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.webChat.entry.MusicMessage;
import com.webChat.entry.NewsItem;
import com.webChat.entry.VideoMessage;
import com.webChat.properties.MessageTypeEnum;

/**
* @ClassName: MessageBuild
* @Description: TODO(构造各类型消息)
* @author andy
* @version V1.0  
* @date Sep 2, 2016 9:04:29 AM
 */
public class MessageBuild {
	
	/*
	* @Title: buildWelcomeTextMessage 
	* @Description: TODO(构建文本欢迎提示消息)
	* @param map 封装了解析结果的Map
	* @return responseMessageXml 返回构造消息xml字符串
	* @throws 
	*/ 
	public static String buildWelcomeTextMessage(Map<String,String> map){
		String responseMessageXml=""; //构造消息xml字符串
		String fromUserName=map.get("FromUserName"); //发送者微信号
		String toUserName=map.get("ToUserName"); //开发者微信号
		
		responseMessageXml=String.format(
				"<xml>"+ 
					"<ToUserName><![CDATA[%s]]></ToUserName>"+
					"<FromUserName><![CDATA[%s]]></FromUserName>"+
					"<CreateTime>%s</CreateTime>"+
					"<MsgType><![CDATA[text]]></MsgType>"+
					"<Content><![CDATA[%s]]></Content>"+
				"</xml>", 
				fromUserName,toUserName,getMessageCreateTime(),
				"感谢您关注-风吹来的砂-个人公众号，\n" +
				"请回复如下关键词来使用服务：" +
				"\n文本\n图片\n语音\n视频\n音乐\n图文"
		);
		return responseMessageXml;
	}	
	
	/*
	* @Title: buildTextMessage 
	* @Description: TODO(构造文本消息)
	* @param map 封装了解析结果的map
	* @param content 文本消息内容
	* @return responseMessageXml 构造消息xml字符串
	* @throws 
	*/ 
	public static String buildTextMessage(Map<String,String> map,String content){
		String responseMessageXml=""; //构造消息xml字符
		String fromUserName=map.get("FromUserName"); //发送者微信号
		String toUserName=map.get("ToUserName"); //开发者微信号
		
		//微信文本消息XML数据格式，参阅微信接口API
		responseMessageXml=String.format(
				"<xml>"+
					"<ToUserName><![CDATA[%s]]></ToUserName>"+
					"<FromUserName><![CDATA[%s]]></FromUserName>"+
					"<CreateTime>%s</CreateTime>"+
					"<MsgType><![CDATA[text]]></MsgType>"+
					"<Content><![CDATA[%s]]></Content>" + 
				"</xml>", 
				fromUserName,toUserName, getMessageCreateTime(), content);
		
		return responseMessageXml;
	}
	
	/*
	* @Title: buildImageMessage 
	* @Description: TODO(构建图片消息，bmp,jpeg,jpg,gif，2M内)
	* @param map 封装
	* @param mediaId 通过素材管理接口上传多媒体文件得到的id
	* @return 图片消息XML字符串
	* @throws 
	*/ 
	public static String buildImageMessage(Map<String,String> map,String mediaId){
		String responseMessageXml=""; //构造消息xml字符
		String fromUserName=map.get("FromUserName"); //发送者微信号
		String toUserName=map.get("ToUserName"); //开发者微信号
		
		//图片消息XML数据格式，参阅微信接口API
		responseMessageXml=String.format(
				"<xml>"+
					"<ToUserName><![CDATA[%s]]></ToUserName>"+
					"<FromUserName><![CDATA[%s]]></FromUserName>"+
					"<CreateTime>%s</CreateTime>"+
					"<MsgType><![CDATA[image]]></MsgType>" + 
						"<Image>"+
							"<MediaId><![CDATA[%s]]></MediaId>" + 
						"</Image>"+
				"</xml>",
				fromUserName,toUserName,getMessageCreateTime(),mediaId);
		return responseMessageXml;
	}
	
	/*
	* @Title: buildMusicMessage 
	* @Description: TODO(构造音乐消息)
	* @param map 封装了解析结果的Map
	* @param musicMessage 封装好的音乐消息内容
	* @return 音乐消息XML字符串
	* @throws 
	*/ 	
	public static String buildMusicMessage(Map<String,String> map,MusicMessage musicMessage){
		String responseMessageXml=""; //构造消息xml字符
		String fromUserName=map.get("FromUserName"); //发送者微信号
		String toUserName=map.get("ToUserName"); //开发者微信号
		
		//音乐消息XML数据格式，参阅微信接口API
		responseMessageXml=String.format(
				"<xml>"+
				 	"<ToUserName><![CDATA[%s]]></ToUserName>"+
				 	"<FromUserName><![CDATA[%s]]></FromUserName>"+
				 	"<CreateTime>%s</CreateTime>"+
				 	"<MsgType><![CDATA[music]]></MsgType>" + 
				 	"<Music>"+
				 		"<Title><![CDATA[%s]]></Title>"+
				 		"<Description><![CDATA[%s]]></Description>"+
				 		"<MusicUrl><![CDATA[%s]]></MusicUrl>"+
				 		"<HQMusicUrl><![CDATA[%s]]></HQMusicUrl>" + 
				 	"</Music>"+
				"</xml>",
				fromUserName,toUserName,getMessageCreateTime(),
				musicMessage.getTitle(),musicMessage.getDescription(),musicMessage.getMusicUrl(),musicMessage.getHqMusicUrl()
			);
		
		return responseMessageXml;
	}
	
	/*
	* @Title: buildVideoMessage 
	* @Description: TODO(构造视频消息,20M以下，rm,rmvb,wmv,avi,mpg,mpeg,mp4)
	* @param msp 封装了解析结果的Map
	* @param videoMessage 封装了视频消息的实体
	* @return 视频消息XML字符串
	* @throws 
	*/ 		
	public static String buildVideoMessage(Map<String,String> map,VideoMessage videoMessage){
		String responseMessageXml=""; //构造消息xml字符
		String fromUserName=map.get("FromUserName"); //发送者微信号
		String toUserName=map.get("ToUserName"); //开发者微信号
		
		//音乐视频XML数据格式，参阅微信接口API
		responseMessageXml=String.format(
				"<xml>"+ 
					"<ToUserName><![CDATA[%s]]></ToUserName>"+
					"<FromUserName><![CDATA[%s]]></FromUserName>"+
					"<CreateTime>%s</CreateTime>"+
					"<MsgType><![CDATA[video]]></MsgType>" + 
					"<Video>"+
						"<MediaId><![CDATA[%s]]></MediaId>"+
						"<Title><![CDATA[%s]]></Title>"+
						"<Description><![CDATA[%s]]></Description>" + 
				    "</Video>"+
				"</xml>",
				fromUserName,toUserName,getMessageCreateTime(),
				videoMessage.getMediaId(),videoMessage.getTitle(),videoMessage.getDescription()
				);
		
		return responseMessageXml;
	}
	

	/*
	* @Title: buildVoiceMessage 
	* @Description: TODO(构造语音消息,语音消息必须是60s内，不能超过5M)
	* @param map 封装了解析结果的Map
	* @return responseMessageXml 语音消息XML字符串
	* @throws 
	*/ 
	public static String buildVoiceMessage(Map<String,String> map,String mediaId){
		String responseMessageXml=""; //构造消息xml字符
		String fromUserName=map.get("FromUserName"); //发送者微信号
		String toUserName=map.get("ToUserName"); //开发者微信号
		
		responseMessageXml=String.format(
				"<xml>"+
					"<ToUserName><![CDATA[%s]]></ToUserName>" +
					"<FromUserName><![CDATA[%s]]></FromUserName>"+
					"<CreateTime>%s</CreateTime>"+
					"<MsgType><![CDATA[voice]]></MsgType>" + 
					"<Voice>"+
						"<MediaId><![CDATA[%s]]></MediaId>" + 
					"</Voice>" +
				"</xml>", 
				fromUserName, toUserName, getMessageCreateTime(),mediaId
				);
		
		return responseMessageXml;
	}
	
	/*
	* @Title: buildNewsMessage 
	* @Description: TODO(构建图文消息)
	* @param map 封装了解析结果的Map
	* @return responseMessageXml 图文消息XML字符串
	* @throws 
	*/ 
	public static String buildNewsMessage(Map<String,String> map){
		String responseMessageXml=""; //构造消息xml字符
		String fromUserName=map.get("FromUserName"); //发送者微信号
		String toUserName=map.get("ToUserName"); //开发者微信号
		
		//两条图文消息XML字符串
		String newsItemContent1="";
		String newsItemContent2="";
		
		//图文消息对象1
		NewsItem newsItem1=new NewsItem();
		//图文消息对象2
		NewsItem newsItem2=new NewsItem();

		newsItem1.setTitle("图文消息1标题测试--");
		newsItem1.setDescription("图文消息1描述测试--");
		//图片地址
		newsItem1.setPicUrl("http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%AE%A0%E7%89%A9&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=4225406410,1864486066&os=2938138617,2473968941&simid=&pn=0&rn=1&di=0&ln=1986&fr=&fmq=1463574658346_R&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=&istype=2&ist=&jit=&bdtype=-1&adpicid=0&pi=0&gsm=0&objurl=http%3A%2F%2Fwww.dabaoku.com%2Fsucaidatu%2Fdongwu%2Fchongwujingling%2F902438.JPG&rpstart=0&rpnum=0&adpicid=0&ctd=1473170848620^3_1350X639%1");
		//连接地址
		newsItem1.setUrl("http://image.baidu.com/");
		//把图文消息对象构建成图文消息XML字符串
		newsItemContent1=buildSingleItem(newsItem1); 
		
		newsItem2.setTitle("图文消息2标题测试00");
		newsItem2.setDescription("图文消息2描述测试00");
		newsItem2.setPicUrl("");
		newsItem2.setUrl("http://www.163.com");
		newsItemContent2=buildSingleItem(newsItem2);
		
		responseMessageXml=String.format(
				"<xml>\n"+
					"<ToUserName><![CDATA[%s]]></ToUserName>\n"+
					"<FromUserName><![CDATA[%s]]></FromUserName>\n"+
					"<CreateTime>%s</CreateTime>\n"+
					"<MsgType><![CDATA[news]]></MsgType>\n"+
					"<ArticleCount>%s</ArticleCount>\n" + "<Articles>\n" + 
					"%s"+
					"</Articles>\n" + 
				"</xml> ",
				fromUserName,toUserName,getMessageCreateTime(),2,newsItemContent1 + newsItemContent2
		);
		
		System.out.println("responseMessageXml--->"+responseMessageXml);
		
		return responseMessageXml;
	}
	
	
	
	/*
	* @Title: buildNewsMessage 
	* @Description: TODO(重构buildNewsMessage方法，实现按图文消息队列对象数量构建图文消息)
	* @param map 封装了解析结果的Map
	* @param list 图文消息队列对象集合
	* @return responseMessageXml 图文消息XML字符串
	* @throws 
	*/ 
	public static String buildNewsMessage(Map<String,String> map,List<NewsItem> list ){
		String responseMessageXml=""; //构造消息xml字符
		String fromUserName=map.get("FromUserName"); //发送者微信号
		String toUserName=map.get("ToUserName"); //开发者微信号
		
		//图文消息队列内容XML
		String newsItemContents="";
		
		int listSize=list.size();//list大小
		
		//根据list集合大小，创建图文消息队列内容字符串
		for (int i = 0; i < listSize; i++) {
			//图文消息队列临时存储字符串，要为每一个图文对象创建一个字符串对象
			String newsItemContentTmp=buildSingleItem(list.get(i));
			//组成图文消息队列字符串，每个图文消息用“+”，连接
			newsItemContents+=newsItemContentTmp+"+";
		}
		//去掉图文队列字符串中最后的一个”+“
		newsItemContents=newsItemContents.substring(0,newsItemContents.lastIndexOf("+"));
		
		responseMessageXml=String.format(
				"<xml>\n"+
					"<ToUserName><![CDATA[%s]]></ToUserName>\n"+
					"<FromUserName><![CDATA[%s]]></FromUserName>\n"+
					"<CreateTime>%s</CreateTime>\n"+
					"<MsgType><![CDATA[news]]></MsgType>\n"+
					"<ArticleCount>%s</ArticleCount>\n" + "<Articles>\n" + 
					"%s"+
					"</Articles>\n" + 
				"</xml> ",
				fromUserName,toUserName,getMessageCreateTime(),listSize,newsItemContents
		);
		
		return responseMessageXml;
	}
	
	/*
	* @Title: getMessageCreateTime 
	* @Description: TODO(生成消息创建时间)
	* @return 消息创建时间字符
	* @throws 
	*/ 
	public static String getMessageCreateTime(){
		Date dt = new Date();// 如果不需要格式,可直接用dt,dt就是当前系统时间
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");// 设置显示格式
		String nowTime = df.format(dt);
		long dd = 0;
		try {
			dd = df.parse(nowTime).getTime();
		} catch (Exception e) {

		}
		return String.valueOf(dd);
	}
	
	/*
	* @Title: buildSingleItem 
	* @Description: TODO(生成图文消息一条记录)
	* @param newsItem 图文消息对象
	* @return itemContent 图文消息XML字符串
	* @throws 
	*/ 
	public static String buildSingleItem(NewsItem newsItem){
		String itemContent="";
		
		itemContent=String.format(
				"<item>\n"+
					"<Title><![CDATA[%s]]></Title> \n"+
					"<Description><![CDATA[%s]]></Description>\n"+
					"<PicUrl><![CDATA[%s]]></PicUrl>\n"+
					"<Url><![CDATA[%s]]></Url>\n" + 
				"</item>",
				newsItem.getTitle(),newsItem.getDescription(),newsItem.getPicUrl(),newsItem.getUrl()
		);
		
		return itemContent;
	}
	
}
