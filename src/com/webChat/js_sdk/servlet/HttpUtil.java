package com.webChat.js_sdk.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webChat.entry.AccessTokenInfo;
import com.webChat.entry.Ticket;
import com.webChat.entry.TicketInfo;
import com.webChat.properties.Constants;
import com.webChat.web.servlet.WebChatServlet;

public class HttpUtil extends HttpServlet implements Constants{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 	long time = System.currentTimeMillis()/1000;    
	        String randomStr = UUID.randomUUID().toString();  
	        //特别注意的是调用微信js，url必须是当前页面(转发的不行)    
	        String str = "jsapi_ticket="+TicketInfo.ticket.getTicket()+"&noncestr="+randomStr+"&timestamp="+time+"&url=http://7a630a9f.ngrok.natapp.cn/webChat_V1/HttpUtil";    
	        System.out.println("str-:"+str);
	        String signature = WebChatServlet.sha1(str);    
	        RequestDispatcher rd =request.getRequestDispatcher("js_sdk_demo.jsp");  
	        String accerssToken =AccessTokenInfo.accessToken.getAccessToken();
	        String jsApiTicket =String.format(API_TICKET_URL, accerssToken,JS_API); 
	        request.setAttribute("time", time);  
	        request.setAttribute("randomStr", randomStr);  
	        request.setAttribute("signature", signature);  
	        request.setAttribute("accessToken", accerssToken);  
	        request.setAttribute("jsapi_ticket", jsApiTicket);  
	        rd.forward(request, response);  
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
