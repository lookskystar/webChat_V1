<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort() + path + "/";
	StringBuffer uploadUrl = new StringBuffer("http://");
	uploadUrl.append(request.getHeader("Host"));
	uploadUrl.append(request.getContextPath());
	uploadUrl.append("/FileUploadServlet");
	System.out.println("uploadUrl====>"+uploadUrl);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>SWFUpload Demos</title>
		<link href="css/default.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="css/button.css" type="text/css" />
		<script type="text/javascript" src="js/swfupload/swfupload.js"></script>
		<script type="text/javascript" src="js/swfupload/swfupload.queue.js"></script>
		<script type="text/javascript" src="js/swfupload/handlers.js"></script>
		
		<script type="text/javascript" src="myJs/jquery-1.4.js"></script>
		
		<script type="text/javascript">
			var swfu;
			window.onload = function () {
				swfu = new SWFUpload({
					upload_url: "<%=uploadUrl.toString()%>",
					post_params: {"name" : "andyTest"},
					use_query_string:true,
					// File Upload Settings
					file_size_limit : "10 MB",	// 文件大小控制
					file_types : "*.*",
					file_types_description : "All Files",
					file_upload_limit : "0",
					file_queue_limit : "1",
									
					file_queue_error_handler : fileQueueError,
					file_dialog_complete_handler : fileDialogComplete,//选择好文件后提交
					file_queued_handler : fileQueued,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError,
					upload_success_handler : uploadSuccess,
					upload_complete_handler : uploadComplete,
					button_placeholder_id : "spanButtonPlaceholder",
					button_width: 180,
					button_height: 18,
					button_text : '<span class="button">请选择文件 </span>',
					button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 10pt; }',
					button_text_top_padding: 0,
					button_text_left_padding: 18,
					button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor: SWFUpload.CURSOR.HAND,
					button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE,
					
					// Flash Settings
					flash_url : "js/swfupload/swfupload.swf",
	
					custom_settings : {
						upload_target : "divFileProgressContainer"
					},
					// Debug Settings
					debug: false  //是否显示调试窗口
				});
			};
			function startUploadFile(){
				swfu.startUpload();
			}
			
			function uploadSuccess(file,serverData){
				//alert(serverData);
				var divFileObj=document.getElementById("divFileProgressContainer");
				
				divFileObj.innerHTML=serverData;	
			}
			
			
			//资源素材更新 ajax
			$(document).ready(function(){
				//更新图片id
				$("#img_mediaId_btn").click(function(){
					var img_mediaId=$("#img_mediaId_txt").val();
					if(img_mediaId==""){
						alert('图片id没有值');
					}else{	
						$.ajax({
							type:'POST',
							url:'/webChat_V1/UpadtePropertiesServlet',
							data:{
								fileType:'IMAGE',
								img_mediaId:img_mediaId
							},
							cache:'false',
							dataType:'json',
							success:function(data){
								if(data==true){
									alert('图片id成功');
								}else{
									alert('图片id失败');
								}
							},
							error:function(){
								alert('异常');
							}
						});
					}
				});
				
				//更新语音id
				$("#voice_mediaId_btn").click(function(){
					var voice_mediaId=$("#voice_mediaId_txt").val();
					if(voice_mediaId==""){
						alert("语音id没有值")
					}else{
						$.ajax({
							type:'POST',
							url:'/webChat_V1/UpadtePropertiesServlet',
							data:{
								fileType:'VOICE',
								voice_mediaId:voice_mediaId
							},
							cache:'false',
							dataType:'json',
							success:function(data){
								if(data==true){
									alert('语音id成功');
								}else{
									alert('语音id失败');
								}
							},
							error:function(){
								alert('异常');
							}
						});
					}
				});
				
				//更新视频消息
				$("#video_btn").click(function(){
					var video_title=$("#video_title_txt").val();
					var video_description=$("#video_description_txt").val();
					var video_mediaId=$("#video_mediaId_txt").val();
					
					if(video_title==""||video_description==""||video_mediaId==""){
						alert("视频输入框不能为空！");
					}else{
						$.ajax({
							type:'POST',
							url:'/webChat_V1/UpadtePropertiesServlet',
							data:{
								fileType:'VIDEO',
								video_title:video_title,
								video_description:video_description,
								video_mediaId:video_mediaId
							},
							cache:'false',
							dataType:'json',
							success:function(data){
								if(data==true){
									alert('视频成功');
								}else{
									alert('视频失败');
								}
							},
							error:function(){
								alert('异常');
							}
						});
					}
				});
				
				//更新音乐消息
				$("#music_btn").click(function(){
					var music_title=$("#music_title_txt").val();
					var music_description=$("#music_description_txt").val();
					var music_musicUrl=$("#music_musicUrl_txt").val();
					var music_hqMusicUrl=$("#music_hqMusicUrl_txt").val();
					
					if(music_title==""||music_description==""||music_musicUrl==""||music_hqMusicUrl==""){
						alert("音乐输入框不能为空");
					}else{
						$.ajax({
							type:'POST',
							url:'/webChat_V1/UpadtePropertiesServlet',
							data:{
								fileType:'MUSIC',
								music_title:music_title,
								music_description:music_description,
								music_musicUrl:music_musicUrl,
								music_hqMusicUrl:music_hqMusicUrl
							},
							cache:'false',
							dataType:'json',
							success:function(data){
								if(data==true){
									alert('音乐成功');
								}else{
									alert('音乐失败');
								}
							},
							error:function(){
								alert("异常");
							}
						});
					}
				});
				
				
				//更新文本消息
				$("#text_btn").click(function(){
					var text_title=$("#text_title_txt").val();
					var text_sub_title=$("#text_sub_title_txt").val();
					var text_contents=$("#text_contents_txt").val();
					
					if(text_title==""||text_sub_title==""||text_contents==""){
						alert("文本输入不能为空");
					}else{
						$.ajax({
							type:'POST',
							url:'/webChat_V1/UpadtePropertiesServlet',
							data:{
								fileType:'TEXT',
								text_title:text_title,
								text_sub_title:text_sub_title,
								text_contents:text_contents
							},
							cache:'false',
							dataType:'json',
							success:function(data){
								if(data==true){
									alert("文本成功");
								}else{
									alert("文本失败");
								}
							},
							error:function(){
								alert("异常");
							}
						});
					}
				});
			
				//---
			});
			
			
					
		</script>
	</head>
	<body style="background-color: #C0D1E3; padding: 2px;">
		<div id="content">
			<form>
				<div
					style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;">
					<span id="spanButtonPlaceholder"></span>
					<input id="btnUpload" type="button" value="上  传"
						onclick="startUploadFile();" class="btn3_mouseout" 
						/>
					<input id="btnCancel" type="button" value="取消所有上传" onclick="cancelUpload();" disabled="disabled" class="btn3_mouseout" 
						/>
				</div>
			</form>
			<div id="divFileProgressContainer">
				
			</div>
			<div id="thumbnails">
				<table id="infoTable" border="0" width="530" style="display: inline; border: solid 1px #7FAAFF; background-color: #C5D9FF; padding: 2px;margin-top:8px;">
				</table>
			</div>
			
		</div>	
		lookings_8793的接口测试号
		
		<br />
		更新资源素材<br />
		
		文本<br />
		文本主标题:<input type="text" id="text_title_txt" value="" width="500px"/><br />
		文本副标题:<input type="text" id="text_sub_title_txt" value="" width="500px"/><br />
		文本内容:<input type="text" id="text_contents_txt" value="" width="800px"/><br />
		<input type="button" id="text_btn" value="修改" /><br />
		<br />
		
		 图片<br />
		 图片id：<input type="text" id="img_mediaId_txt" value="" width="300px"/>
		 <input type="button" id="img_mediaId_btn" value="修改" />
		 <br />
		 语音<br />
		 语音id:<input type="text" id="voice_mediaId_txt" value="" width="300px"/>
		 <input type="button" id="voice_mediaId_btn" value="修改" />
		 <br />
		 
		 图文1-2<br />
		 图文1-标题：<input type="text" id="news_item_1_title_txt" value="" width="300px"/><br />
		 图文1-描述：<input type="text" id="news_item_1_description_txt" value="" width="300px"/><br />
		 图文1-图片url：<input type="text" id="news_item_1_picUrl_txt" value="" width="300px"/><br />
		 图文1-url：<input type="text" id="news_item_1_url_txt" value="" width="300px"/><br />
		 
		 图文2-标题：<input type="text" id="news_item_2_title_txt" value="" width="300px"/><br />
		 图文2-描述：<input type="text" id="news_item_2_description_txt" value="" width="300px"/><br />
		 图文2-图片url：<input type="text" id="news_item_2_picUrl_txt" value="" width="300px"/><br />
		 图文2-url：<input type="text" id="news_item_2_url_txt" value="" width="300px"/><br />
		 <input type="button" id="news_item_btn" value="修改" />
		 <br />
		 
		 音乐<br />
		 音乐标题：<input type="text" id="music_title_txt" value="" width="300px"/><br />
		 音乐描述：<input type="text" id="music_description_txt" value="" width="300px"/><br />
		 音乐url：<input type="text" id="music_musicUrl_txt" value="" width="300px"/><br />
		 高清url：<input type="text" id="music_hqMusicUrl_txt" value="" width="300px"/><br />
		 <input type="button" id="music_btn" value="修改" />
		 <br />
		 
		 视频<br />
		 视频标题：<input type="text" id="video_title_txt" value="" width="300px"/><br />
		 视频描述：<input type="text" id="video_description_txt" value="" width="300px"/><br />
		 视频id：<input type="text" id="video_mediaId_txt" value="" width="300px"/><br />
		 <input type="button" id="video_btn" value="修改" />
		 <br />
		
	</body>
</html>