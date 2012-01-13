<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>欢迎来到Deimos</title>

<style>

html,body {
	height: 100%;
}

body {
	font:12px/20px "Helvetica Neue", Helvetica, Arial, sans-serif;
	background:#FFF;
	color:#000;
	background: url("${ctxPath}/images/bg.png") repeat scroll 0 0 transparent;
}

/*--------------------*/
#login {
	background-color: #fff;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#eee) );
	background-image: -webkit-linear-gradient(top, #fff, #eee);
	background-image: -moz-linear-gradient(top, #fff, #eee);
	background-image: -ms-linear-gradient(top, #fff, #eee);
	background-image: -o-linear-gradient(top, #fff, #eee);
	background-image: linear-gradient(top, #fff, #eee);
	height: 240px;
	width: 400px;
	margin: -150px 0 0 -230px;
	padding: 30px;
	position: absolute;
	top: 50%;
	left: 50%;
	z-index: 0;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	-webkit-box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px
		rgba(0, 0, 0, .2), 0 3px 0 #fff, 0 4px 0 rgba(0, 0, 0, .2), 0 6px 0
		#fff, 0 7px 0 rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 1px 1px 0 rgba(0, 0, 0, .1),
		3px 3px 0 rgba(255, 255, 255, 1), 4px 4px 0 rgba(0, 0, 0, .1), 6px 6px
		0 rgba(255, 255, 255, 1), 7px 7px 0 rgba(0, 0, 0, .1);
	box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px rgba(0, 0, 0, .2), 0
		3px 0 #fff, 0 4px 0 rgba(0, 0, 0, .2), 0 6px 0 #fff, 0 7px 0
		rgba(0, 0, 0, .2);
}

#login:before {
	content: '';
	position: absolute;
	z-index: -1;
	border: 1px dashed #ccc;
	top: 5px;
	bottom: 5px;
	left: 5px;
	right: 5px;
	-moz-box-shadow: 0 0 0 1px #fff;
	-webkit-box-shadow: 0 0 0 1px #fff;
	box-shadow: 0 0 0 1px #fff;
}

/*--------------------*/
h1 {
	text-shadow: 0 1px 0 rgba(255, 255, 255, .1), 0px 2px 0
		rgba(0, 0, 0, .1);
	text-align: center;
	color: #666;
	margin: 0 0 30px 0;
	letter-spacing: 4px;
	font: normal 26px/1 Verdana, Helvetica;
	position: relative;
}

h1:after,h1:before {
	background-color: #777;
	content: "";
	height: 1px;
	position: absolute;
	top: 15px;
	width: 120px;
}

h1:after {
	background-image: -webkit-gradient(linear, left top, right top, from(#777),
		to(#fff) );
	background-image: -webkit-linear-gradient(left, #777, #fff);
	background-image: -moz-linear-gradient(left, #777, #fff);
	background-image: -ms-linear-gradient(left, #777, #fff);
	background-image: -o-linear-gradient(left, #777, #fff);
	background-image: linear-gradient(left, #777, #fff);
	right: 0;
}

h1:before {
	background-image: -webkit-gradient(linear, right top, left top, from(#777),
		to(#fff) );
	background-image: -webkit-linear-gradient(right, #777, #fff);
	background-image: -moz-linear-gradient(right, #777, #fff);
	background-image: -ms-linear-gradient(right, #777, #fff);
	background-image: -o-linear-gradient(right, #777, #fff);
	background-image: linear-gradient(right, #777, #fff);
	left: 0;
}

/*--------------------*/
fieldset {
	border: 0;
	padding: 0;
	margin: 0;
}

/*--------------------*/
#inputs input {
	background: #f1f1f1
		url("${ctxPath}/images/login-sprite.png")
		no-repeat;
	padding: 15px 15px 15px 30px;
	margin: 0 0 10px 0;
	width: 353px; /* 353 + 2 + 45 = 400 */
	border: 1px solid #ccc;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	-moz-box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
	-webkit-box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
	box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
}

#username {
	background-position: 5px -2px !important;
}

#password {
	background-position: 5px -52px !important;
}

#remeberme {
	margin: 5px 5px 0 0;
}

#inputs input:focus {
	background-color: #fff;
	border-color: #e8c291;
	outline: none;
	-moz-box-shadow: 0 0 0 1px #e8c291 inset;
	-webkit-box-shadow: 0 0 0 1px #e8c291 inset;
	box-shadow: 0 0 0 1px #e8c291 inset;
}

/*--------------------*/
#actions {
	margin: 25px 0 0 0;
}

#submit {
	background-color: #ffb94b;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#fddb6f),
		to(#ffb94b) );
	background-image: -webkit-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -moz-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -ms-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -o-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: linear-gradient(top, #fddb6f, #ffb94b);
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0
		rgba(255, 255, 255, 0.3) inset;
	-webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0
		rgba(255, 255, 255, 0.3) inset;
	box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3)
		inset;
	border-width: 1px;
	border-style: solid;
	border-color: #d69e31 #e3a037 #d5982d #e3a037;
	float: left;
	height: 35px;
	padding: 0;
	width: 120px;
	cursor: pointer;
	font: bold 15px Arial, Helvetica;
	color: #8f5a0a;
}

#submit:hover,#submit:focus {
	background-color: #fddb6f;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ffb94b),
		to(#fddb6f) );
	background-image: -webkit-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -moz-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -ms-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -o-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: linear-gradient(top, #ffb94b, #fddb6f);
}

#submit:active {
	outline: none;
	-moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	-webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
}

#submit::-moz-focus-inner {
	border: none;
}

#actions a {
	color: #3151A2;
	float: right;
	line-height: 35px;
	margin-left: 10px;
}

/* register start */
#register {
	background-color: #fff;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#eee) );
	background-image: -webkit-linear-gradient(top, #fff, #eee);
	background-image: -moz-linear-gradient(top, #fff, #eee);
	background-image: -ms-linear-gradient(top, #fff, #eee);
	background-image: -o-linear-gradient(top, #fff, #eee);
	background-image: linear-gradient(top, #fff, #eee);
	height: 360px;
	width: 480px;
	padding: 30px;
	z-index: 0;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	-webkit-box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px
		rgba(0, 0, 0, .2), 0 3px 0 #fff, 0 4px 0 rgba(0, 0, 0, .2), 0 6px 0
		#fff, 0 7px 0 rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 1px 1px 0 rgba(0, 0, 0, .1),
		3px 3px 0 rgba(255, 255, 255, 1), 4px 4px 0 rgba(0, 0, 0, .1), 6px 6px
		0 rgba(255, 255, 255, 1), 7px 7px 0 rgba(0, 0, 0, .1);
	box-shadow: 0 0 2px rgba(0, 0, 0, 0.2), 0 1px 1px rgba(0, 0, 0, .2), 0
		3px 0 #fff, 0 4px 0 rgba(0, 0, 0, .2), 0 6px 0 #fff, 0 7px 0
		rgba(0, 0, 0, .2);
}

#register:before {
	content: '';
	position: absolute;
	z-index: -1;
	border: 1px dashed #ccc;
	top: 5px;
	bottom: 5px;
	left: 5px;
	right: 5px;
	-moz-box-shadow: 0 0 0 1px #fff;
	-webkit-box-shadow: 0 0 0 1px #fff;
	box-shadow: 0 0 0 1px #fff;
}

/*--------------------*/
#r_inputs input {
	background: #f1f1f1;
	padding: 15px 15px 15px 10px;
	margin: 0 0 10px 0;
	width: 353px; /* 353 + 2 + 45 = 400 */
	border: 1px solid #ccc;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	-moz-box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
	-webkit-box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
	box-shadow: 0 1px 1px #ccc inset, 0 1px 0 #fff;
}

#r_username {
	background-position: 5px -2px !important;
}

#r_password {
	background-position: 5px -52px !important;
}

/*--------------------*/
#r_actions {
	margin: 25px 0 0 0;
	text-align:center;
}

#r_submit {
	background-color: #ffb94b;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#fddb6f),
		to(#ffb94b) );
	background-image: -webkit-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -moz-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -ms-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: -o-linear-gradient(top, #fddb6f, #ffb94b);
	background-image: linear-gradient(top, #fddb6f, #ffb94b);
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.5);
	-moz-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0
		rgba(255, 255, 255, 0.3) inset;
	-webkit-box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0
		rgba(255, 255, 255, 0.3) inset;
	box-shadow: 0 0 1px rgba(0, 0, 0, 0.3), 0 1px 0 rgba(255, 255, 255, 0.3)
		inset;
	border-width: 1px;
	border-style: solid;
	border-color: #d69e31 #e3a037 #d5982d #e3a037;
	float: none;
	height: 35px;
	padding: 0;
	width: 120px;
	cursor: pointer;
	font: bold 15px Arial, Helvetica;
	color: #8f5a0a;
}

#r_submit:hover,#r_submit:focus {
	background-color: #fddb6f;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ffb94b),
		to(#fddb6f) );
	background-image: -webkit-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -moz-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -ms-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: -o-linear-gradient(top, #ffb94b, #fddb6f);
	background-image: linear-gradient(top, #ffb94b, #fddb6f);
}

#r_submit:active {
	outline: none;
	-moz-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	-webkit-box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.5) inset;
}

#r_submit::-moz-focus-inner {
	border: none;
}

#r_actions a {
	color: #3151A2;
	float: right;
	line-height: 35px;
	margin-left: 10px;
}

</style>
<link rel="stylesheet" href="${ctxPath }/css/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js"></script>
<script type="text/javascript" src="${ctxPath }/js/jquery.fancybox.pack.js"></script>


</head>



<body>
	<form id="login">
		<h1>登录</h1>
		<fieldset id="inputs">
			<input id="email" type="text" placeholder="邮箱" autofocus required> 
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