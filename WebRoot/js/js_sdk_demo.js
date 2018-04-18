<!--<pre name="code" class="javascript">-->
wx.ready(function(){  
	alert("开始检测.....");
     // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，  
     //所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才  
     //调用的接口，则可以直接调用，不需要放在ready函数中。  
     wx.checkJsApi({  
        jsApiList: ['onMenuShareAppMessage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,  
        success: function(res) {  
          alert("支持onMenuShareAppMessage");  
          // 以键值对的形式返回，可用的api值true，不可用为false  
          // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}  
        }  
     });  
     
     document.querySelector('#btn1').onclick = function (){
     	wx.onMenuShareAppMessage({  
     		title: '标题',
     		desc: '描述',
     		link: 'http://movie.douban.com/subject/25785114/',  
     		imgUrl: 'http://demo.open.weixin.qq.com/jssdk/images/p2166127561.jpg', 
     		trigger: function (res) {  
     			// 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回  
     			alert('用户点击发送给朋友');  
     		}, 
     		success: function (res) {  
     			alert('已分享');  
     		},
     		cancel: function (res) {
     			alert('已取消');    
     		},
     		fail: function (res) {
     			alert(JSON.stringify(res)); 
     		}  
     	});
     	 alert('已注册获取“发送给朋友”状态事件');  
     };  
     
     document.querySelector('#btn2').onclick = function () {  
     	 alert("hideOptionMenu");    
     	 wx.hideOptionMenu();  
     };
     
     wx.error(function(res){ 
     	// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，  
        //也可以在返回的res参数中查看，对于SPA可以在这里更新签名。  
        alert(res);
     });
});  