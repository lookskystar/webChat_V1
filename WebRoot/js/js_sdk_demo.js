<!--<pre name="code" class="javascript">-->
wx.ready(function(){  
	alert("��ʼ���.....");
     // config��Ϣ��֤���ִ��ready���������нӿڵ��ö�������config�ӿڻ�ý��֮��config��һ���ͻ��˵��첽������  
     //���������Ҫ��ҳ�����ʱ�͵�����ؽӿڣ��������ؽӿڷ���ready�����е�����ȷ����ȷִ�С������û�����ʱ��  
     //���õĽӿڣ������ֱ�ӵ��ã�����Ҫ����ready�����С�  
     wx.checkJsApi({  
        jsApiList: ['onMenuShareAppMessage'], // ��Ҫ����JS�ӿ��б�����JS�ӿ��б����¼2,  
        success: function(res) {  
          alert("֧��onMenuShareAppMessage");  
          // �Լ�ֵ�Ե���ʽ���أ����õ�apiֵtrue��������Ϊfalse  
          // �磺{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}  
        }  
     });  
     
     document.querySelector('#btn1').onclick = function (){
     	wx.onMenuShareAppMessage({  
     		title: '����',
     		desc: '����',
     		link: 'http://movie.douban.com/subject/25785114/',  
     		imgUrl: 'http://demo.open.weixin.qq.com/jssdk/images/p2166127561.jpg', 
     		trigger: function (res) {  
     			// ��Ҫ������trigger��ʹ��ajax�첽�����޸ı��η�������ݣ���Ϊ�ͻ��˷��������һ��ͬ����������ʱ��ʹ��ajax�Ļذ��ỹû�з���  
     			alert('�û�������͸�����');  
     		}, 
     		success: function (res) {  
     			alert('�ѷ���');  
     		},
     		cancel: function (res) {
     			alert('��ȡ��');    
     		},
     		fail: function (res) {
     			alert(JSON.stringify(res)); 
     		}  
     	});
     	 alert('��ע���ȡ�����͸����ѡ�״̬�¼�');  
     };  
     
     document.querySelector('#btn2').onclick = function () {  
     	 alert("hideOptionMenu");    
     	 wx.hideOptionMenu();  
     };
     
     wx.error(function(res){ 
     	// config��Ϣ��֤ʧ�ܻ�ִ��error��������ǩ�����ڵ�����֤ʧ�ܣ����������Ϣ���Դ�config��debugģʽ�鿴��  
        //Ҳ�����ڷ��ص�res�����в鿴������SPA�������������ǩ����  
        alert(res);
     });
});  