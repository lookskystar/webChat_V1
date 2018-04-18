package com.webChat.util.message;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.webChat.properties.MessageTypeEnum;

/**
* @ClassName: MessageUtil
* @Description: TODO(2、消息解析工具类)
* @author andy
* @version V1.0  
* @date Aug 30, 2016 8:47:35 AM
 */
public class MessageUtil {
	
	/*
	* @Title: requestParseXml 
	* @Description: TODO(解析微信发来的请求（XML）)
	* @param request 封装了请求信息的HttpServletRequest对象
	* @return map 解析结果
	* @throws Exception
	*/	
	public static Map<String,String> requestParseXml(HttpServletRequest request) throws Exception {
		//将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		//从request中取得输入流
		InputStream inputStream = request.getInputStream();
		
		//读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		//得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();
		
		// 遍历所有子节点，并放入map中
		for (Element e : elementList) {
			System.out.println(e.getName() + "|" + e.getText());
			map.put(e.getName(), e.getText());
		}
		
		// 释放资源
		inputStream.close();
		inputStream = null;
		
		return map;	
	}

}
