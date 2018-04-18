package com.webChat.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import com.webChat.properties.Constants;

/**
* @ClassName: PropertiesUtil
* @Description: TODO(实现对java配置文件Properties读取，写入与更新操作)
* @author andy
* @version V1.0  
* @date Sep 13, 2016 6:36:57 AM
 */
public class PropertiesUtil implements Constants{
	//资源文件路径
	static String proFilePath=PROP_PATH+PROP_MESSAGE_NAME;
	//采用静态方法
	private static Properties props=new Properties();
	static{
		try {
			props.load(new FileInputStream(proFilePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	* @Title: getValueByKey 
	* @Description: TODO(读取资源文件中相应键值)
	* @param key 键
	* @return 值
	* @throws 
	*/ 
	public static String getValueByKey(String key){
		return props.getProperty(key);
	}
	
	/*
	* @Title: readValue 
	* @Description: TODO(根据主键key读取主键的值value)
	* @param filePath 属性文件路径
	* @param key 键名
	* @return 
	* @throws 
	*/ 
	public static String readValue(String filePath,String key){
		try {
			InputStream in=new BufferedInputStream(new FileInputStream(filePath));
			
			props.load(in);
			
			String value=utf8ToIso(props.getProperty(key));
			
			in.close();
			
			System.out.println(key+"键的值是："+value);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	* @Title: writeProperties 
	* @Description: TODO(更新（或插入）一对properties信息（主键及其键值），如果该主键已经存在，更新该主键值，如果该主键不存在，则插件一对键值)
	* @param keyName 键名
	* @param keyValue 键值
	* @throws 
	*/ 
	public static String writeProperties(String keyName,String keyValue){
		String msg=null;
		keyValue=isoToUtf8(keyValue);
		try {
			FileOutputStream fos=new FileOutputStream(proFilePath);
			
			props.setProperty(keyName, keyValue);
			props.store(fos, "Update'"+keyName+"'value");

			fos.close();
			
			msg="true";
		} catch (Exception e) {
			System.out.println("属性文件更新错误！");
			msg="false";
			e.printStackTrace();
		}
		return msg;
	}
	
	/*
	* @Title: updateProperties 
	* @Description: TODO(更新properties文件键值对，如果该主键已经存在，更新该主键的值；如果该主键不存在，则插件一对键值)
	* @param keyName 键名
	* @param keyValue 键值
	* @throws 
	*/ 	
	public static String updateProperties(String keyName,String keyValue){
		String msg=null;
		keyValue=isoToUtf8(keyValue);
		try {
			props.load(new FileInputStream(proFilePath));
			//调用Hashtable的方法put，使用getProperty方法提供并行性。
			//强制要求为属性的键和值使用字符串。返回值是Hashtable，调用put结果。
			OutputStream fos=new FileOutputStream(proFilePath);
			
			props.setProperty(keyName,keyValue);
			//以适合使用load方法加载到Properties表中的格式，将此Properties表中的属性列表（键和元素对）写入输出流
			props.store(fos, "Update'"+keyName+"'value");
			
			fos.close();
			
			msg="true";
		} catch (Exception e) {
			System.out.println("属性未见更新错误");
			msg="false";
			e.printStackTrace();
		}
		return msg;
	}
	
	/*
	* @Title: isoToUtf8 
	* @Description: TODO(将data字符串的编码转为iso-8859-1，方便写入prop)
	* @param data 要转编码的字符串
	* @return 转好编码的字符串
	* @throws 
	*/ 
	public static String isoToUtf8(String data){
		String result="";
		try {
			result=new String(data.getBytes(ISO_8859_1),UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	* @Title: utf8ToIso 
	* @Description: TODO(将data字符串的编码转为utf-8，方便从prop中读取)
	* @param data 要转编码的字符串
	* @return 转好编码的字符串
	* @throws 
	*/ 
	public static String utf8ToIso(String data){
		String result="";
		try {
			result=new String(data.getBytes(UTF_8),ISO_8859_1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
