<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" scope="application"></c:set>

<form class="form-horizontal">
  <div class="control-group">
  <label class="control-label" for="inputEmail">账号名称</label>
    <div class="controls">
      <a href="#" class="btn btn-large disabled">驰通</a>
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputEmail">当前密码</label>
    <div class="controls">
      <input type="text" id="inputEmail" placeholder="输入当前密码">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputPassword">新的密码</label>
    <div class="controls">
      <input type="password" id="inputPassword" name="password" placeholder="输入新密码">
    </div>
  </div>
  <div class="control-group">
    <label class="control-label" for="inputRepeatPassword">确认密码</label>
    <div class="controls">
      <input type="password" id="inputRepeatPassword" placeholder="再次输入新密码">
    </div>
  </div>
  <div class="control-group">
    <div class="controls">
			<button id="submitAdd" type="submit"  class="btn btn-primary" class="btn">提交</button>
    </div>
  </div>
</form>