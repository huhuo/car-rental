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
	$('#loginForm').submit(function(Event){
		$.post('${path}/cmsystem/security/validation/login.do',{
			username : $('#username').val(),
			password : $('#password').val(),
		}, function(data, stutas, xreq){
			console.log('====');
			console.log(data);
			if(data.status=="ERROR"){
				$('#userLoginModal .modal-body').empty();
				$('#userLoginModal .modal-body').append("<P>用户名或密码不正确</p>");
				$('#userLoginModal').modal('show');
			}else if (data.status=="FAILURE") {
				$('#userLoginModal .modal-body').empty();
				$('#userLoginModal .modal-body').append("<P>该用户已被锁定或已被删除,请与系统管理员联系</p>");
				$('#userLoginModal').modal('show');
			} else{
				window.location.href = '${path}';
			}
		});
		return false;
	});
});
</script>
<title>驰通汽车租赁系统</title>
</head>
 <div id="userLoginModal" class="modal hide fade" tabindex="-1" role="dialog" 
aria-labelledby="myModalLabel" aria-hidden="true" style="width: 480px; text-align:center; margin-left:-242px">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">提示</h3>
  </div>
  <div class="modal-body">
  </div>
  <div class="modal-footer">
  </div>
  </div>
<body style="margin-top: 100px">
	<div class="container">
  
  <div class="well huhuo-signin">
    <h2 class="form-signin-heading" style="text-align:center;padding:10px 0 20px">北京驰通科技有限公司</h2>
  <form class="huhuo-form" id="loginForm" action="${path}/cmsystem/security/validation/login.do" method="post">
    <div class="control-group">
    <div class="input-prepend">
      <span class="add-on" style="width:60px;text-align:right">用户名：</span>
      <input class="span3" name="username" id="username" type="text" placeholder="姓名" x-webkit-speech lang="zh-CN">
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
    <p color="gray">Copyright©2012-2013 Beijing Chitong Technology Co., Ltd. All Rights Reserved 驰通科技 版权所有</p>
    <p color="gray">京ICP备13024190号-1</p><script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
		document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F6cb456aa69030df11b0802cad3042e7d' type='text/javascript'%3E%3C/script%3E"));
	</script>
    <!-- 
    <a href="http://www.miibeian.gov.cn/" target="_blank">京ICP备13024190号-1</a>
     -->
  </div>
</div>

</body>
</html>
