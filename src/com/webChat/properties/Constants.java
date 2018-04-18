package com.webChat.properties;

import com.webChat.util.PropertiesUtil;

/**
* @ClassName: Constants
* @Description: TODO(常量接口)
* @author andy
* @version V1.0  
* @date Aug 30, 2016 7:31:11 AM
 */
public interface Constants {
	//Token可有开发者任意填写，用作生产签名。（该Token会和接口URL中包含的Token进行比对，从而验证安全性）
	public final String TOKEN="andyTest";
	
	//第三方用户唯一凭证，微信公众平台产生给
	public final String APP_ID="wxbdfcabc4ddc2c82d";
	
	//第三用户唯一凭证秘钥，微信公众平台产生给
	public final String APP_SECRET="1398fee9763abf7d98751b47c2ea2b93";
	
	//微信服务器接口地址
	//https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
	public final String ACCESS_TOKEN="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	//微信服务器素材上传接口地址
	// 素材上传(POST)https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
	public final String UPLOAD_MEDIA="https://api.weixin.qq.com/cgi-bin/media/upload";
	
	//微信服务器素材下载接口地址,不支持视频下载（GET）
	public final String DOWNLOAD_MEDIA="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
	
	//微信服务器ip地址接口
	public final String WEB_CHAT_SERVER_IP_URL="https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=%s";
	
	//添加客服接口（测试号不能用）
	public final String ADD_KF_ACCOUNT_URL="https://api.weixin.qq.com/customservice/kfaccount/add?access_token=%s";
	
	//创建用户分组
	public final String ADD_USERS_GROUP_URL="https://api.weixin.qq.com/cgi-bin/groups/create?access_token=%s";
	//得到用户列表
	public final String GET_USERS_LIST_URL="https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s&next_openid=%s";
	//设置用户备注名
	public final String SET_USERS_REMARK_NAME_URL="https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=%s";
	//获得用户的基本信息
	public final String GET_USERS_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
	
	
	//微信js-sdk 测试号不能用，无反应
	//api-ticket
	public final String API_TICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=%s";
	public final String JS_API="jsapi";
	
	//生成菜单
	public final String MENU_CREATE_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	//自定义菜单查询接口
	public final String GET_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";
	//删除自定义菜单
	public final String DEL_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";
	
	
	//编码
	public final String ISO_8859_1="iso-8859-1";
	public final String UTF_8="utf-8";
	
	
	public final String TEXT="text";//文本消息
	public final String IMAGE="image";//图片消息
	public final String VOICE="voice";//语音消息
	public final String MUSIC="music";//音乐消息
	public final String VIDEO="video";//视频消息
	public final String SHORTVIDEO="shortvideo";//小视频消息
	public final String LOCATION="location";//地理位置消息
	public final String LINK="link";//链接消息
	public final String NEWS="news";//图文消息
	public final String EVENT="event";//事件消息
	
	public final String PROP_MESSAGE_NAME="webChat.properties";//资源文件名
	public final String PROP_PATH=(PropertiesUtil.class.getClassLoader().getResource("com/webChat/properties/").toString().substring(6));//资源文件路劲
	
	public final String TEXT_MESSAGE_MAIN_TITLE="textMessage.mainTitle";//文本消息主标题
	public final String TEXT_MESSAGE_SUB_TITLE="textMessage.subTitle";//文本消息副标题
	public final String TEXT_MESSAGE_CONTENTS="textMessage.contents";//文本消息内容
	
	public final String IMAGE_MESSAGE_MEDIAID="imageMessage.mediaId";//图片素材id
	public final String VOICE_MESSAGE_MEDIAID="voiceMessage.mediaId";//语音素材id
	
	public final String MUSIC_TITLE="music.title";//音乐标题
	public final String MUSIC_DESCRIPTION="music.description";//音乐描述
	public final String MUSIC_MUSIC_URL="music.musicUrl";//音乐url
	public final String MUSIC_HQ_MUSIC_URL="music.hqMusicUrl";//高清音乐url
	
	
	public final String VIDEO_MESSAGE_TITLE="videoMessage.title";//视频素材标题
	public final String VIDEO_MESSAGE_DESCRIPTION="videoMessage.description";//视频素材描述
	public final String VIDEO_MESSAGE_MEDIAID="videoMessage.mediaId";//视频素材id
	
}
