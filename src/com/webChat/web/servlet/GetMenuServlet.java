package com.webChat.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webChat.entry.AccessTokenInfo;
import com.webChat.properties.Constants;
import com.webChat.util.menu.MenuUtil;

/**
* @ClassName: GetMenuServlet
* @Description: TODO(得到自定义菜单)
* @author andy
* @version V1.0  
* @date Oct 10, 2016 9:37:07 AM
 */
public class GetMenuServlet extends HttpServlet implements Constants{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String access_token=AccessTokenInfo.accessToken.getAccessToken();
		String responseStr=MenuUtil.getMenu(access_token);
		System.out.println("得到自定菜单-----》"+responseStr);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
