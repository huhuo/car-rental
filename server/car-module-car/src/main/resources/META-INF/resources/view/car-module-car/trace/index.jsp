<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(function() {
	
	// load div's configuration
	$('div.loaddiv').css('height', '700px');
	
	//注意：吴总注意，以下代码应当在第一次进入页面和当屏幕宽高发生变化时调用。
	var setWdithHeight = function(){
		var width = $('div.loaddiv').css('width');
		var height = $('div.loaddiv').css('height');
		width = Number(width.replace('px', '')) * .97;
		height = Number(height.replace('px', '')) * .9;
		$('iframe').css('width', width + 'px');
		$('iframe').css('height', height + 'px'); 
	};
	
	window.onload = setWdithHeight();

	window.onresize = function(){
		setWdithHeight();
	};
	
  	$.ajax({
  		url: 'http://222.76.126.217:88/loginServlet',
		data: {
			username: '3390',
			password: '123456'
		},
		dataType: 'jsonp',
		jsonpCallback: 'showResponse'
	});
});
</script>
</head>
<iframe src="http://222.76.126.217:88/camion2.html" ></iframe>
</html>

