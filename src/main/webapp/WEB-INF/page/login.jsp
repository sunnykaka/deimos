<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>欢迎来到Deimos</title>

<link rel="stylesheet" href="${ctxPath }/css/jquery.fancybox.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctxPath }/css/css.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctxPath }/css/login.css" type="text/css" media="screen" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<script type="text/javascript" src="${ctxPath }/js/jquery.fancybox.pack.js"></script>


</head>



<body>
	<form id="login">
		<h1>登录</h1>
		<fieldset id="inputs">
			<input id="email" type="email" placeholder="邮箱" autofocus required> 
			<input id="password" type="password" placeholder="密码" required>
		</fieldset>
		<input type="checkbox" id="remeberme" />记住我 

		<fieldset id="actions">
			<input type="submit" id="submit" value="登录"> <a href="">忘记密码?</a><a href="${ctxPath}/register.action" class="fancybox">注册</a>
		</fieldset>

	</form>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$(".fancybox").fancybox({
			type:"ajax",

			helpers : {
				overlay : {
					css : {
						'background-color' : '#eee'	
					}
				}
			}
		});
	});
</script>



</html>
