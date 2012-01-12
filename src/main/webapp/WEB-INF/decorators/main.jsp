<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" buffer="64kb"%>
<%@ include file="/WEB-INF/page/inc/taglibs.jsp"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
		<title><decorator:title default="欢迎来到Deimos" /></title>
		<link rel="stylesheet" href="${ctxPath}/css/style.css" type="text/css" media="screen"/>
	
	<!--Nivo Slider-->
	   <link rel="stylesheet" href="${ctxPath}/js/slide/default.css" type="text/css" media="screen" />
	   <link rel="stylesheet" href="${ctxPath}/js/slide/nivo-slider.css" type="text/css" media="screen" />
	<!--Nivo Slider-->
	
	<!--FANCY BOX-->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
		<script>
			!window.jQuery && document.write('<script src="${ctxPath}/js/jquery-1.4.3.min.js"><\/script>');
		</script>
		<script type="text/javascript" src="${ctxPath}/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctxPath}/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
		<script type="text/javascript">
			$(document).ready(function() {
				$("a#fancybox").fancybox();	 
			});
		</script>
		<style>
		.fancy a img {
		border: 1px solid #BBB;
		padding: 2px;
		margin: 10px 20px 10px 0;
		vertical-align: top;
		}
		.fancy a img.last {
		margin-right: 0;	
		}
		</style>
		
		<decorator:head />
	<!--FANCY BOX-->
	</head>
	<body>
		<%@ include file="/WEB-INF/page/inc/header.jsp"%>
		
		<div id="body-wrapper" class="clearfix"><!--start body-wrapper-->
			<decorator:body />
			
			<div class="clear"></div>
			
			<%@ include file="/WEB-INF/page/inc/footer.jsp"%>
		</div>
	</body>
</html>