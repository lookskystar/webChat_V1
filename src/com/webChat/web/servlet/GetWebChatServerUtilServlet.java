package com.webChat.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webChat.entry.AccessToken;
import com.webChat.entry.AccessTokenInfo;
import com.webChat.properties.ApiTypeEnum;
import com.webChat.properties.MessageTypeEnum;
import com.webChat.util.GetWebChatServerUtil;

/**
* @ClassName: GetWebChatServerUtilServlet
* @Description: TODO(得到微信服务器其他接口功能)
* @author andy
* @version V1.0  
* @date Sep 26, 2016 10:00:42 AM
 */
public class GetWebChatServerUtilServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String apiType=request.getParameter("apiType");
			
			AccessTokenInfo accessTokenInfo=new AccessTokenInfo();
			
			//响应消息
			String responseMessage = "";
			
			if("".equals(apiType)){
				responseMessage="api类型为空";
			}else{
				ApiTypeEnum apiTypeEnum=Enum.valueOf(ApiTypeEnum.class, apiType.toUpperCase());
				
				switch (apiTypeEnum) {
				case GET_IP:
					
					System.out.println("得到微信服务器的ip地址列表-"+accessTokenInfo.accessToken.getAccessToken());
					responseMessage=GetWebChatServerUtil.getWebChatServerIp(accessTokenInfo.accessToken.getAccessToken());
					
					break;

				default:
					break;
				}
				
				System.out.println("responseMessage-->"+responseMessage);
				//响应消息到页面
				response.setCharacterEncoding("UTF-8");
				PrintWriter out=response.getWriter();
				out.write(responseMessage);
				out.close();
			}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request,response);
		
	}

}
