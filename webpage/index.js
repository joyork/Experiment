/**
 * @author weixin
 */
//<![CDATA[
NTES.ready(function( $ ) {
	function showDiv(e){
                var o = document.getElementById("hidDiv"); 
                o.style.left = e.clientX-45+"px"; 
                o.style.top = e.clientY+10+"px"; 
                o.style.display="block";}
    function hideDiv(e){
        		var o = document.getElementById("hidDiv");
        		o.style.display="none";}
	var isLoginFlag = false;
	if(GameCookie.isLogined()){
// ��Ҫ��̨��Ϸ����������û���Ϣ��Ŀǰ��HTMLģ����ʾ
//		jQuery.ajax({
//			cache:false,
//			type:"GET",
//			url:"/userInfo.do",
//			dataType:"html",
//			success:function(html){
//				if(html!='false'){
//					jQuery('#userinfo').addClass('welcome');
//					jQuery('#userinfo').removeClass('loginform');
//					jQuery('#userinfo').html(html);
//					jQuery('#userinfo').show();
//					isLoginFlag = true;
//				}else{
//					showLogin();
//					
//				}
//			}
//		});
		var nowblock = NTES("#userloginB").NTES(0);
		nowblock.innerHTML = "";
		nowblock.removeCss("user-login");
		nowblock.addCss("welcome");
		nowblock.innerHTML=NTES("#welcomeBlock").NTES(0).innerHTML;		
	}else{
		showLogin();
	}
	function showLogin(){
		var login = '';
		login += 	'<form target="_self" method="post" action="https://reg.163.com/logins.jsp"  id="loginForm">';
		login += 	'<input type="hidden" value="http://test.reg.163.com/wan.html" id="loginToUrl" name="url" />';
		login += 	'<p><label>�ʺţ�</label><input type="text" onblur="if(this.value==\'\'){this.value=\'�� name@example.com\';this.style.color=\'#bbbbbb\';}" onfocus="if(this.value==\'�� name@example.com\'){this.value=\'\';this.style.color=\'#000000\';}" id="username" value="�� name@example.com" name="username" class="loginform-txtin" /> </p>';		
		login += 	'<p><label>���룺</label><input type="password" id="password" name="password" class="loginform-txtin" /></p>';		
		login += 	'<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><input type="checkbox" value="1"  name="savelogin" class="loginform-check" onmouseout="hideDiv(event);" onmouseover="showDiv(event);"/>��ס��¼�ʺ�</label>&nbsp;&nbsp;<a href="http://reg.163.com/RecoverPassword.shtml" class="forgetpw">��������</a></p>';		
		login += 	'<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" class="btn-login" value="��½" />&nbsp;<a href="http://reg.163.com/reg/reg.jsp?product=wan&amp;url=http://wan.163.com/&amp;loginurl=http://wan.163.com" class="btn-reg">ע��</a></p>';
		login += 	'<p class="cRed">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* ��¼����ͨ��֤���Ϳ��档</p>';
		login += '</form>'
		var nowblock = NTES("#userloginB").NTES(0);
		nowblock.innerHTML = "";
		nowblock.removeCss("welcome");
		nowblock.addCss("user-login");
		nowblock.innerHTML=login;
		new Passport(NTES("#username"));	
//		jQuery('#password').keypress(function(e){
//			if(e.keyCode==13){
//				jQuery('#loginForm').submit();
//			}
//		});
	}		
}); 
//]]>	
