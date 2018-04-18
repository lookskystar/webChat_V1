package com.webChat.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.webChat.entry.AccessToken;
import com.webChat.entry.AccessTokenInfo;
import com.webChat.entry.Ticket;
import com.webChat.entry.TicketInfo;
import com.webChat.properties.Constants;
import com.webChat.util.NetWorkHelper;
import com.webChat.util.menu.MenuUtil;

/**
* @ClassName: AccessTokenServlet
* @Description: TODO(4、工程启动时就运行，线程定时得到Access_token和ticketStr)
* @author andy
* @version V1.0  
* @date Aug 30, 2016 9:46:26 AM
 */
public class AccessTokenServlet extends HttpServlet implements Constants {
	
	//发https请求，网络连接对象
	NetWorkHelper netHepler=new NetWorkHelper();
	
	/**
	 * 4.2、Initialization of the servlet. <br>
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		System.out.println("启动AccessTokenServlet，线程定时得到access_token");
		
		super.init();
		
		//开启一个新的线程，用于定时获得access_token
		new Thread(new Runnable(){
			public void run() {
				while(true){
					try {
						Ticket ticket=new Ticket();
						
						//获取access_token
						AccessTokenInfo.accessToken=getAccessToken(Constants.APP_ID,Constants.APP_SECRET);
						TicketInfo.ticket=getJsApiTicket();
						
						System.out.println("AccessTokenInfo.accessToken-->"+AccessTokenInfo.accessToken.getAccessToken());
						System.out.println("TicketInfo.ticket-->"+TicketInfo.ticket);
						
						
						
						//获取成功，休眠7000秒
						if(AccessTokenInfo.accessToken!=null&&TicketInfo.ticket!=null){
							Thread.sleep(7000*1000);
						}else{
							//获取失败,休眠3秒
							Thread.sleep(3*1000);
						}
						
						
					} catch (Exception e) {
						System.out.println("发生异常情况："+e.getMessage());
						e.printStackTrace();
						try {
							//发生异常，休眠10秒
							Thread.sleep(1000*10);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
			}	
		}).start();
	}

	/*
	* @Title: getAccessToken 
	* @Description: TODO(4.1、获取access_token)
	* @param appId
	* @param appSecret
	* @return AccessToken
	*/ 
	private AccessToken getAccessToken(String appId,String appSecret){
		
		/**微信服务器，接口地址：
		 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET，其中grant_type固定写为client_credential即可。
		 */
		String url=String.format(Constants.ACCESS_TOKEN, Constants.APP_ID,Constants.APP_SECRET);
		//此请求为https的get请求，返回的数据格式为{"access_token":"ACCESS_TOKEN","expires_in":7200}
		String result=netHepler.getHttpsResponse(url, "");
		System.out.println("获取到access_token="+result);
		//使用FacstJson字符串解析成Json对象
		JSONObject json=JSON.parseObject(result);
		AccessToken token = new AccessToken();
	    token.setAccessToken(json.getString("access_token"));
	    token.setExpriesinTime(json.getInteger("expires_in"));
	    return token;
	}
	
	
	/*
	* @Title: getJsApiTicket 
	* @Description: TODO(调用微信js接口的临时票据,微信js开发)
	* @param access_token 接口访问凭证
	* @return 微信js接口的临时票据字符串
	* @throws 
	*/ 
	private Ticket getJsApiTicket(){
		
		Ticket ticket=new Ticket();
		
		String url=String.format(API_TICKET_URL,AccessTokenInfo.accessToken.getAccessToken() ,JS_API);

		//发起GET请求获取凭证
		String responseStr=netHepler.getHttpsResponse(url,"GET");
		JSONObject jsonObject=JSONObject.parseObject(responseStr);
		if(null!=jsonObject){
			try {
				String str=jsonObject.getString("ticket");
				ticket.setTicket(str);
				System.out.println("获取到ticket："+ticket.getTicket());
				
			} catch (JSONException e) {
				//获取失败
				System.out.println("获取ticket失败："+jsonObject.getString("errcode")+","+jsonObject.getString("errmsg"));
				e.printStackTrace();
			}
		}
		
		return ticket;
	}
}
