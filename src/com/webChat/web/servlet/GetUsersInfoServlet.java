package com.webChat.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webChat.entry.AccessTokenInfo;
import com.webChat.properties.Constants;
import com.webChat.util.NetWorkHelper;

/**
* @ClassName: GetUsersInfoServlet
* @Description: TODO(得到用户基本信息,可用)
* @author andy
* @version V1.0  
* @date Sep 28, 2016 10:09:31 AM
* 
* http://2089ab83.ngrok.natapp.cn/webChat_V1/GetUsersInfoServlet
 */
public class GetUsersInfoServlet extends HttpServlet implements Constants{


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccessTokenInfo accessTokenInfo=new AccessTokenInfo();
		NetWorkHelper netWorkHelper=new NetWorkHelper();
		
		String responseJson="";
		String url=String.format(GET_USERS_INFO_URL, accessTokenInfo.accessToken.getAccessToken(),"oRdXYwBSEkWx25PMj54wS72au9H4");
		responseJson=netWorkHelper.getHttpsResponse(url, "GET");
		
		System.out.println("得到用户基本信息-responseJson:"+responseJson);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
