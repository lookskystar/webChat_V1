package com.webChat.util;

import com.webChat.properties.Constants;

/**
* @ClassName: getUploadTypeUitl
* @Description: TODO(得到上传文件的类型)
* @author andy
* @version V1.0  
* @date Sep 8, 2016 6:41:06 AM
 */
public class GetUploadTypeUitl implements Constants{

	/*
	* @Title: getTypeByFileExtName 
	* @Description: TODO(通过文件扩展名得到文件类型)
	* @param fileName
	* @return 
	* @throws 
	*/ 
	public static String getTypeByFileName(String fileName){
		String fileType="";
		String fileExtName=fileName.substring(fileName.lastIndexOf(".")+1);
		if("jpg".equals(fileExtName.toLowerCase())||
		   "gif".equals(fileExtName.toLowerCase())||
		   "png".equals(fileExtName.toLowerCase())
		   ){
			//支持图片的类型
			fileType=IMAGE;
		}else if("mp3".equals(fileExtName.toLowerCase())||
				 "wma".equals(fileExtName.toLowerCase())||
				 "wav".equals(fileExtName.toLowerCase())||
				 "amr".equals(fileExtName.toLowerCase())||
				 "aac".equals(fileExtName.toLowerCase())||
				 "ac3".equals(fileExtName.toLowerCase())||
				 "m4a".equals(fileExtName.toLowerCase())||
				 "ogg".equals(fileExtName.toLowerCase())
				 ){
			//支持音频格式的类型
			fileType=VOICE;
		}else if("mp4".equals(fileExtName.toLowerCase())||
				 "flv".equals(fileExtName.toLowerCase())||
				 "f4v".equals(fileExtName.toLowerCase())||
				 "webm".equals(fileExtName.toLowerCase())||
				 "m4v".equals(fileExtName.toLowerCase())||
				 "mov".equals(fileExtName.toLowerCase())||
				 "3gp".equals(fileExtName.toLowerCase())||
				 "3g2".equals(fileExtName.toLowerCase())||
				 "rm".equals(fileExtName.toLowerCase())||
				 "rmvb".equals(fileExtName.toLowerCase())||
				 "wmv".equals(fileExtName.toLowerCase())||
				 "avi".equals(fileExtName.toLowerCase())||
				 "asf".equals(fileExtName.toLowerCase())||
				 "mpg".equals(fileExtName.toLowerCase())||
				 "mpeg".equals(fileExtName.toLowerCase())||
				 "mpe".equals(fileExtName.toLowerCase())||
				 "ts".equals(fileExtName.toLowerCase())||
				 "div".equals(fileExtName.toLowerCase())||
				 "dv".equals(fileExtName.toLowerCase())||
				 "divx".equals(fileExtName.toLowerCase())||
				 "vob".equals(fileExtName.toLowerCase())||
				 "dat".equals(fileExtName.toLowerCase())||
				 "mkv".equals(fileExtName.toLowerCase())||
				 "swf".equals(fileExtName.toLowerCase())||
				 "lavf".equals(fileExtName.toLowerCase())||
				 "cpk".equals(fileExtName.toLowerCase())||
				 "dirac".equals(fileExtName.toLowerCase())||
				 "ram".equals(fileExtName.toLowerCase())||
				 "qt".equals(fileExtName.toLowerCase())||
				 "fli".equals(fileExtName.toLowerCase())||
				 "flc".equals(fileExtName.toLowerCase())||
				 "mod".equals(fileExtName.toLowerCase())
				 ){
			//支持视频格式
			fileType=VIDEO;
		}
		return fileType;
	}
	
	/*
	* @Title: getExtNameByFileName 
	* @Description: TODO(通过文件名得到文件扩展名)
	* @param fileName 文件名
	* @return 扩展名
	* @throws 
	*/ 	
	public static String getExtNameByFileName(String fileName){
		String fileExtName=null;
		if(!"".equals(fileName)||fileName==""){
			fileExtName=fileName.substring(fileName.lastIndexOf(".")+1);
		}
		return fileExtName;
	}
}
