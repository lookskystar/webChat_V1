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
import com.webChat.util.kfAccountMsg.MsgBuild;


/**
* @ClassName: AddKfAccountServlet
* @Description: TODO(添加客服，没有测试，测试号不能添加客服)
* @author andy
* @version V1.0  
* @date Sep 27, 2016 6:35:05 AM
* 
* http://2089ab83.ngrok.natapp.cn/webChat_V1/AddKfAccountServlet
 */
public class AddKfAccountServlet extends HttpServlet implements Constants{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AccessTokenInfo accessTokenInfo=new AccessTokenInfo();	
		NetWorkHelper netWorkHelper=new NetWorkHelper();
		MsgBuild msgBuild=new MsgBuild();
		
		String data=msgBuild.buildAddAccountMsg(null);
		
		String add_kf_account_url=String.format(ADD_KF_ACCOUNT_URL, accessTokenInfo.accessToken.getAccessToken());
		
		String resultData=netWorkHelper.getHttpsResponse(add_kf_account_url,"POST",data);
		
		System.out.println("resultData-->"+resultData);
	
	}

}
