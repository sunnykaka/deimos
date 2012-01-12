<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>

<body>
	<div style="padding-top:40px;" class="slider-wrapper theme-default">
		<div id="slider" class="nivoSlider">
            <img src="images/slide1.png" title="this is a caption!" alt="slider image 1"/>
			<img src="images/slide2.png"  alt="slider image 2"/>
		</div>
    </div>

	<div class="box">
		<h1>Looking for an Awesome Title? Hmmmm Awesome Title...</h1>
    </div>

	<div class="one_fourth"><!--start top half content-->
		<h3>Secondary awesome title required also!</h3>
		<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vestibulum massa eu nisi lacinia quis. <a href="#">link</a></p>
	</div>
	<div class="one_fourth">
		<div class="fancy"><a id="fancybox" href="images/210-150-1.png"><img alt="fancybox" src="images/210-150-1.png" /></a></div>
	</div>
	<div class="one_fourth">
		<div class="fancy"><a id="fancybox" href="images/210-150-2.png"><img alt="fancybox" src="images/210-150-2.png" /></a></div>
	</div>
	<div class="one_fourth column-last">
		<div class="fancy"><a id="fancybox" href="images/210-150-3.png"><img alt="fancybox" src="images/210-150-3.png" /></a></div>
	</div><!--end top half content-->

	<div class="clear"></div>

	<div class="one_fourth">
		<h3>One</h3>
		<p>Donec metus leo, elementum at ultrices ac, dapibus at justo. Donec tristique hendrerit dui vitae lacinia. Suspendisse ante ligula, adipiscing porta aliquam et, rutrum nec lectus. Nulla erat risus, molestie non dapibus ac, fermentum vitae felis.</p>
	</div>
	<div class="one_fourth">
		<h3>Two</h3>
		<p>Donec metus leo, elementum at ultrices ac, dapibus at justo. Donec tristique hendrerit dui vitae lacinia. Suspendisse ante ligula, adipiscing porta aliquam et, rutrum nec lectus. Nulla erat risus, molestie non dapibus ac, fermentum vitae felis.</p>
	</div>
	<div class="one_fourth">
		<h3>Three</h3>
		<p>Donec metus leo, elementum at ultrices ac, dapibus at justo. Donec tristique hendrerit dui vitae lacinia. Suspendisse ante ligula, adipiscing porta aliquam et, rutrum nec lectus. Nulla erat risus, molestie non dapibus ac, fermentum vitae felis.</p>
	</div>
	<div class="one_fourth column-last">
		<h3>Four</h3>
		<p>Donec metus leo, elementum at ultrices ac, dapibus at justo. Donec tristique hendrerit dui vitae lacinia. Suspendisse ante ligula, adipiscing porta aliquam et, rutrum nec lectus. Nulla erat risus, molestie non dapibus ac, fermentum vitae felis.</p>
	</div>
    

    <!--Nivo Slider-->
    <script type="text/javascript" src="${ctxPath}/js/slide/jquery.nivo.slider.pack.js"></script>
    <script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider();
    });
    </script>
	<!--Nivo Slider-->
	
</body>
</html>