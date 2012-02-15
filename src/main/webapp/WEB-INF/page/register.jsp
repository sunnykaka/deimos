<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/inc/taglibs.jsp"%>

<script type="text/javascript">
var emailReg = /^[0-9a-zA-Z_\-\.]+@[0-9a-zA-Z_\-]+(\.[0-9a-zA-Z_\-]+)*$/;
$(function(){
	$("#register").submit(function() {
		var email = $("#r_email").val();
		if(email && email.length > 32) {
			$("#r_error_msg").html("邮箱长度不能超过32位").show();
			return false;
		}
		
		var password = $("#r_password").val();
		alert('1');
		if(password && (password.length < 6 || password.length > 18)) {
			alert('2');
			$("#r_error_msg").html("密码长度应该大于等于6位小于等于18位").show();
			return false;			
		}
		
		var passwordConfirm = $("#r_password_confirm").val();
		if(password && passwordConfirm && passwordConfirm != password) {
			$("#r_error_msg").html("两次输入的密码必须相同").show();
			return false;
		}
	});
});
</script>
	
<form id="register">
	<h1>注册</h1>
	
	<fieldset class="error_msg" id="r_error_msg" <c:if test="${empty errorMsg }">style="display:none;"</c:if>>
		${errorMsg }
	</fieldset>
	
	<fieldset id="r_inputs">
		邮箱：<input id="r_email" type="email" required><br/>
		密码：<input id="r_password" type="password" required><br/>
		确认：<input id="r_password_confirm" type="password" required>
	</fieldset>

	<fieldset id="r_actions">
		<input type="submit" id="r_submit" value="注册">
	</fieldset>

</form>
