<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>得到微信服务器其他接口功能</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


	<script type="text/javascript" src="myJs/jquery-1.4.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//接口类型
			var apiType='GET_IP';
			//得到ip列表
			$("#get_web_chat_ip_btn").click(function(){
				$.ajax({
					type:'GET',
					url:'/webChat_V1/GetWebChatServerUtilServlet',
					data:{
						apiType:apiType
					},
					cache:'false',
					dataType:'json',
					success:function(data){
						//把后台返回的json，微信服务器ip地址列表在div中显示出来
						var msgDivObj=document.getElementById("msgDiv");
						msgDivObj.innerHTML=data.ip_list;	
					},
					error:function(){
						alert('异常');
					}
				});
			});
		});
	</script>

  </head>
  
  <body>
  
  <div id="msgDiv">
  
  </div>
  
  
    得到微信服务器ip地址列表 <br>
    <input type="button" id="get_web_chat_ip_btn" value="得到ip列表" />
  </body>
</html>
