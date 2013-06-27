<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" scope="application"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${path }/res/js/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${path }/res/js/huhuo/huhuo.css" rel="stylesheet" media="screen">
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
<title>Insert title here</title>
</head>

<body>
	<h2 style="text-align: center;">弛通汽车租赁系统</h2>
	<form class="form-horizontal" action="${path}/cmsystem/security/validation/login.do" method="post">
		
		<!-- <div class="control-group">
			<label class="control-label" for="inputUsername">用户名</label>
			<div class="controls">
				<input type="text" class="required" id="inputUsername" name="username" placeholder="请输入用户名...">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputPassword">密码</label>
			<div class="controls">
				<input type="password" class="required" id="inputPassword" name="password" placeholder="请输入密码...">
			</div>
		</div> -->
		
		<table align="center">
			<tr>
				<td colspan="3">
					<c:if test="${not empty seLoginErrMsg}">
						${seLoginErrMsg}
					</c:if>
				</td>
			</tr>
			<tr> 
				<td><label>用户名：</label></td>
				<td><input type="text" name="username" /></td>
				<td></td>
			</tr>
			<tr>
				<td><label>密码：</label></td>
				<td><input type="password" name="password"/></td>
				<td></td>
			</tr>
			<tr>
				<td>
					<label>验证码：</label>
				</td>
				<td>
					<input name="captcha" type="text" id="captcha" />
				</td>
				<td>
					<img src="${path }/cmsystem/security/validation/captcha.do" 
						id="captchaImage" title="刷新" style= "CURSOR:pointer"/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="登录" /></td>
				<td></td>
			</tr>
		</table>
		

	</form>


</body>
</html>