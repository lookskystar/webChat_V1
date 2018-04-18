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
* @ClassName: GetUsersListServlet
* @Description: TODO(得到所有用户列表json，可用)
* @author andy
* @version V1.0  
* @date Sep 28, 2016 6:19:07 AM
* 
* http://2089ab83.ngrok.natapp.cn/webChat_V1/GetUsersListServlet
 */
public class GetUsersListServlet extends HttpServlet implements Constants{


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AccessTokenInfo accessTokenInfo=new AccessTokenInfo();
		NetWorkHelper netWorkHelper=new NetWorkHelper();
		
		String responseJson="";
		String url=String.format(GET_USERS_LIST_URL, accessTokenInfo.accessToken.getAccessToken(),"");
		responseJson=netWorkHelper.getHttpsResponse(url, "GET");
		
		System.out.println("得到所有用户列表-responseJson:"+responseJson);
		
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
