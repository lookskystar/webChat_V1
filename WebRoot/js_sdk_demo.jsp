<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.webChat.properties.Constants;"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>微信js-sdk例子</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>  
	
	<!--<script type="text/javascript" src="js/js_sdk_demo.js"></script>-->  
	
	<script type="text/javascript">
		 wx.config({  
            debug:true,//开启调试模式，调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。  
            appId:<%=Constants.APP_ID%>,//必填，公众号唯一标识  
            timestamp:'<%=request.getAttribute("time") %>', //必填，生成签名的时间戳  
            nonceStr:'<%=request.getAttribute("randomStr") %>', //必填，生产签名的随机串  
            signature:'<%=request.getAttribute("signature") %>', //必填，签名，  
            jsApiList:['onMenuShareAppMessage','hideOptionMenu'] //必填，需要使用的JS接口列表  
        });  
        
        wx.checkJsApi({  
        	jsApiList: ['onMenuShareAppMessage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,  
        	success: function(res) {  
          		alert("支持onMenuShareAppMessage");  
          		// 以键值对的形式返回，可用的api值true，不可用为false  
          		// 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}  
        	}  
     	});  
	</script>

  </head>
  
  <body>
    这是微信js-sdk案例<br>
     AccessToken：<%=request.getAttribute("accessToken") %><br/>  
     JSApi_Ticket：<%=request.getAttribute("jsapi_ticket") %><br/>  
     timestamp：<%=request.getAttribute("time") %><br/>  
     nonceStr：<%=request.getAttribute("randomStr") %><br/>  
     signature：<%=request.getAttribute("signature") %><br/>   
     <a href="javascript:void(0);" id="btn1" >onMenuShareAppMessage</a><br/><br/>  
     <a href="javascript:void(0);" id="btn2">hideOptionMenu</a>  <br /><br /> 
     
     
       <input type="button" id="btn1" value="onMenuShareAppMessage" /><br /><br /> 
     <input type="button" id="btn2" value="hideOptionMenu" /> 
  </body>
</html>
