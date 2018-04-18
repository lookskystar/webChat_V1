package com.webChat.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webChat.entry.AccessTokenInfo;
import com.webChat.util.menu.MenuUtil;

/**
* @ClassName: AddMenuServlet
* @Description: TODO(创建自定义菜单)
* @author andy
* @version V1.0  
* @date Oct 10, 2016 9:40:18 AM
 */
public class AddMenuServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//创建菜单
		bulidMenu(AccessTokenInfo.accessToken.getAccessToken());
	}
	
	
	/*
	* @Title: createMenu 
	* @Description: TODO(创建菜单) 
	* @throws 
	*/ 
	private void bulidMenu(String accessToken){
		//创建菜单
		//创建菜单-点击和显示
		//String req=MenuUtil.bulidClikAndViewMenu(accessToken);
		//二维码等
		String req=MenuUtil.bulidClikAndViewMenu(accessToken);
		System.out.println("菜单创建返回值："+req);
	}

}
