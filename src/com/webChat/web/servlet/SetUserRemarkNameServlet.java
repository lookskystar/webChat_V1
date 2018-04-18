package com.webChat.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webChat.entry.AccessTokenInfo;
import com.webChat.util.users.UsersUtil;

/**
* @ClassName: SetUserRemarkNameServlet
* @Description: TODO(设置用户备注名)
* @author andy
* @version V1.0  
* @date Sep 28, 2016 9:21:43 AM
 */
public class SetUserRemarkNameServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersUtil usersUtil=new UsersUtil();
		AccessTokenInfo accessTokenInfo=new AccessTokenInfo();
		
		String responseJson="";
		responseJson=usersUtil.setUserRemarkName(accessTokenInfo.accessToken.getAccessToken());
		
		System.out.println("设置用户备注名-responseJson："+responseJson);
		
	}

}
