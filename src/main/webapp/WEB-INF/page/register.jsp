<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/inc/taglibs.jsp"%>

<script type="text/javascript">
$(function(){
	/*
	$("#r_submit").submit(function() {
		
	});
	*/
});
</script>
	
<form id="register">
	<h1>注册</h1>
	<fieldset id="r_inputs">
		邮箱：<input id="r_email" type="text" required><br/>
		密码：<input id="r_password" type="password" required><br/>
		确认：<input id="r_password_confirm" type="password" required>
	</fieldset>

	<fieldset id="r_actions">
		<input type="submit" id="r_submit" value="注册">
	</fieldset>

</form>
