package com.webChat.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webChat.entry.AccessToken;
import com.webChat.entry.AccessTokenInfo;
import com.webChat.util.menu.MenuUtil;

/**
* @ClassName: DelMenuServlet
* @Description: TODO(删除自定义菜单)
* @author andy
* @version V1.0  
* @date Oct 10, 2016 9:54:00 AM
 */
public class DelMenuServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String access_token=AccessTokenInfo.accessToken.getAccessToken();
		String responseStr=MenuUtil.delMenu(access_token);
		
		System.out.println("删除自定义菜单-->"+responseStr);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	
	}

}
