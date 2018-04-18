package com.webChat.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webChat.properties.Constants;
import com.webChat.util.menu.MenuUtil;
import com.webChat.util.message.MessageHandle;
import com.webChat.util.message.MessageUtil;

/**
* @ClassName: WebChatServlet
* @Description: TODO(1、控制器，校验签名请求doGet，处理微信服务器发来的消息)
* @author andy
* @version V1.0  
* @date Aug 30, 2016 6:14:04 AM
 */
public class WebChatServlet extends HttpServlet implements Constants{
	
	/**
	 * Constructor of the object.
	 */
	public WebChatServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 1.1、确认请求来自微信服务器，校验签名
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("开始校验签名.....");
		//接收微信服务器发送请求时传递过来的4个参数
		String signature = request.getParameter("signature");//微信加密签名signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	    String timestamp = request.getParameter("timestamp");//时间戳
	    String nonce = request.getParameter("nonce");//随机数
	    String echostr = request.getParameter("echostr");//随机字符串（返回）
	    
	    //排序，按字典顺序排序
	    String sortStr=sort(TOKEN, timestamp, nonce);
	    //加密
	    String mySignature=sha1(sortStr);
	    
	    //校验签名,通过检验signature对请求进行校验，若校验成功则原样返回echostr,表示接入成功，否则接入失败
	    if(mySignature!=null && mySignature!="" && mySignature.equals(signature)){
	    	System.out.println("签名校验通过......");
	    	//如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
	    	 //response.getWriter().println(echostr);
            response.getWriter().write(echostr);
	    }else{
	    	 System.out.println("签名校验失败......");
	    }
	}
	
	/**
	 * The doPost method of the servlet. <br>
	 * 处理微信服务发来的消息。接收，处理，响应微信服务器转发的用户发送个公众号的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//将请求和响应编码均设置为UTF-8(防止中文乱码)
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("进入请求...");
		
		String responseMessage="";
		try {
			//解析微信发来的请求，将解析后的结果封装成Map返回
			Map<String,String> map = MessageUtil.requestParseXml(request);
			System.out.println("开始构造响应消息...");
			responseMessage=MessageHandle.handleResponseMessage(map);
			System.out.println("responseMessage:->"+responseMessage);
			if("".equals(responseMessage)){
				responseMessage="未正确响应...";
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("发生异常：->"+e.getMessage());
			responseMessage="未正确响应";
		}
		
		response.getWriter().println(responseMessage);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		
	}
	
	
	
	/*
	* @Title: sort 
	* @Description: TODO(1.2、排序方法)
	* @param token
	* @param timestamp
	* @param nonce
	* @return 
	* @throws 
	*/ 
	public String sort(String token,String timestamp,String nonce){
		String[] strArray={token,timestamp,nonce};
		Arrays.sort(strArray);
		StringBuilder sb=new StringBuilder();
		for(String str:strArray){
			sb.append(str);
		}
		return sb.toString();
	}
	
	
	/*
	* @Title: shal 
	* @Description: TODO(1.3、将字符串进行sha1加密)
	* @param str 需要加密的字符串
	* @return  加密后的字符串
	* @throws 
	*/ 
	public static String sha1(String str){
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
	}

}
