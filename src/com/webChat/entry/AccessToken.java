package com.webChat.entry;

/**
* @ClassName: AccessToken
* @Description: TODO(2、AceessToken实体类)
* @author andy
* @version V1.0  
* @date Aug 30, 2016 9:27:56 AM
 */
public class AccessToken {
	//获取到的凭证(access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token，开发者需要妥善保存access_token的存储至少要保留512个字符空间。有两个小时的有效性)
	private String accessToken;
	//凭证有效时间，单位：秒
	private int expriesinTime;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpriesinTime() {
		return expriesinTime;
	}
	public void setExpriesinTime(int expriesinTime) {
		this.expriesinTime = expriesinTime;
	}
	
}
