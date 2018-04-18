package com.webChat.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webChat.entry.AccessTokenInfo;
import com.webChat.properties.Constants;
import com.webChat.util.users.UsersUtil;

/**
* @ClassName: AddUsersGroupServlet
* @Description: TODO(添加一个用户分组，和客服一样，不能用，报65400错误)
* @author andy
* @version V1.0  
* @date Sep 28, 2016 4:34:27 AM
 */
public class AddUsersGroupServlet extends HttpServlet implements Constants{


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersUtil usersUtil=new UsersUtil();
		AccessTokenInfo accessTokenInfo=new AccessTokenInfo();
		
		String responseJson="";
		responseJson=usersUtil.addUsersGroup(accessTokenInfo.accessToken.getAccessToken());
		
		System.out.println("添加用户组-responseJson："+responseJson);
		
	}

}
