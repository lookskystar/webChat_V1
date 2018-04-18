package com.webChat.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webChat.entry.VideoMessage;
import com.webChat.properties.Constants;
import com.webChat.properties.MessageTypeEnum;
import com.webChat.util.PropertiesUtil;

/**
* @ClassName: UpadtePropertiesServlet
* @Description: TODO(更新资源文件)
* @author andy
* @version V1.0  
* @date Sep 13, 2016 10:22:08 AM
 */
public class UpadtePropertiesServlet extends HttpServlet implements Constants{


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文件类型
		String fileType=request.getParameter("fileType");
		//图片
		String img_mediaId=request.getParameter("img_mediaId");
		//语音
		String voice_mediaId=request.getParameter("voice_mediaId");
		
		//音乐
		String music_title=request.getParameter("music_title");
		String music_description=request.getParameter("music_description");
		String music_musicUrl=request.getParameter("music_musicUrl");
		String music_hqMusicUrl=request.getParameter("music_hqMusicUrl");
		
		//视频
		String video_title=request.getParameter("video_title");
		String video_description=request.getParameter("video_description");
		String video_mediaId=request.getParameter("video_mediaId");
		
		//文本
		String text_title=request.getParameter("text_title");
		String text_sub_title=request.getParameter("text_sub_title");
		String text_contents=request.getParameter("text_contents");
		
		//响应消息
		String responseMessage = "";
		
		if("".equals(img_mediaId)||"".equals(voice_mediaId)){
			responseMessage="图片或语音，值为空";
		}else if("".equals(video_title)||"".equals(video_description)||"".equals(video_mediaId)){
			responseMessage="视频，值为空";
		}else if("".equals(text_title)||"".equals(text_sub_title)||"".equals(text_contents)){
			responseMessage="文本值，为空";
		}else{
			MessageTypeEnum messageTypeEnum=Enum.valueOf(MessageTypeEnum.class, fileType.toUpperCase());
			
			switch (messageTypeEnum) {
			case TEXT:
				//处理文本消息
				System.out.println("更新文本消息");
				
				PropertiesUtil.writeProperties(TEXT_MESSAGE_MAIN_TITLE, text_title);
				PropertiesUtil.writeProperties(TEXT_MESSAGE_SUB_TITLE, text_sub_title);
				responseMessage=PropertiesUtil.writeProperties(TEXT_MESSAGE_CONTENTS, text_contents);
				break;
			case IMAGE:
				//处理图片消息
				System.out.println("更新图片消息");
				responseMessage=PropertiesUtil.writeProperties(IMAGE_MESSAGE_MEDIAID, img_mediaId);
				break;
			case VOICE:
				//处理语音消息
				System.out.println("更新语音消息");
				responseMessage=PropertiesUtil.updateProperties(VOICE_MESSAGE_MEDIAID, voice_mediaId);
				break;
			case MUSIC:
				//处理音乐
				System.out.println("更新音乐");
				PropertiesUtil.writeProperties(MUSIC_TITLE, music_title);
				PropertiesUtil.writeProperties(MUSIC_DESCRIPTION, music_description);
				PropertiesUtil.writeProperties(MUSIC_MUSIC_URL, music_musicUrl);
				responseMessage=PropertiesUtil.writeProperties(MUSIC_HQ_MUSIC_URL, music_hqMusicUrl);
				break;
			case VIDEO:
				//处理视频消息
				System.out.println("更新视频消息"); 
				
				//写入视频消息
				PropertiesUtil.updateProperties(VIDEO_MESSAGE_TITLE, video_title);
				PropertiesUtil.updateProperties(VIDEO_MESSAGE_DESCRIPTION, video_description);
				responseMessage=PropertiesUtil.updateProperties(VIDEO_MESSAGE_MEDIAID, video_mediaId);
				break;
			case SHORTVIDEO:
				//处理小视频消息
				System.out.println("更新小视频消息");
				responseMessage="";
				break;
			case LOCATION:
				//处理位置消息
				System.out.println("更新位置消息");
				responseMessage="";
				break;
			case LINK:
				//处理链接消息
				System.out.println("更新链接消息");
				responseMessage="";
				break;
			case EVENT:
				//处理事件消息，用户在关注与取消关注公众号时，微信会向我们的公众号服务器发送事件消息，开发者接收到事件消息后就可以给用户发欢迎消息
				System.out.println("更新事件消息");
				responseMessage="";
				break;
			case NEWS:
				System.out.println("更新图文消息");
				responseMessage="";
			default:
				break;
			}
		}

		//响应消息到页面
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		out.write(responseMessage);
		out.close();
	}

}
