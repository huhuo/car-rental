<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" scope="application"></c:set>
<<script type="text/javascript">
	
	//$("#myModal").hide();
	
	$("#showAnimation").click(function(){
		//$('#myModal').modal('show')
	});
	

	
</script>
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


	<a id="showAnimation"href="#myModal" role="button" class="btn" data-toggle="modal">Launch demo modal</a>
	 
	<!-- Modal -->
	<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel">个人信息管理</h3>
	  </div>
	  <div class="modal-body">
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
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	    <button class="btn btn-primary">Save changes</button>
	  </div>
	</div>

