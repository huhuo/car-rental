<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" scope="application"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${path }/res/js/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${path }/res/js/huhuo/huhuo.css" rel="stylesheet" media="screen">
<link href="${path }/res/js/huhuo/stylebt.css" rel="stylesheet" type="text/css">
<script src="${path }/res/js/jquery/jquery.js"></script>
<script src="${path }/res/js/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#captchaImage').click(function(event) {
		$(this).hide().attr('src', '${path }/cmsystem/security/validation/captcha.do?' 
			+ Math.floor(Math.random()*100) ).fadeIn();
	});
});
</script>
<title>弛通汽车租赁系统</title>
</head>

<body style="margin-top: 100px">
	<div class="container">
  
  <div class="well huhuo-signin">
    <h2 class="form-signin-heading" style="text-align:center;padding:10px 0 20px">北京弛通科技有限公司</h2>
  <form class="huhuo-form" action="${path}/cmsystem/security/validation/login.do" method="post">
    <div class="control-group">
    <div class="input-prepend">
      <span class="add-on" style="width:60px;text-align:right">用户名：</span>
      <input class="span3" name="username" id="username" type="text" placeholder="姓名" x-webkit-speech lang="en-US">
    </div>
    </div>
    <div class="control-group">
    <div class="input-prepend">
      <span class="add-on" style="width:60px;text-align:right">密码：</span>
      <input class="span3" name="password" id="password" type="password" placeholder="密码">
    </div>
    </div>
    <p style="text-align:center"><button class="btn btn-large btn-primary" type="submit">登录</button></p>
  </form>
  </div>
  
  <hr>
  <div class="huhuo-footer">
    <p color="gray">Copyright©2012-2013 Beijing Chitong Technology Co., Ltd. All Rights Reserved 弛通科技 版权所有</p>
    <p color="gray">京ICP备13024190号-1</p>
    <!-- 
    <a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备13024190号-1</a>
     -->
  </div>
</div>


</body>
</html>