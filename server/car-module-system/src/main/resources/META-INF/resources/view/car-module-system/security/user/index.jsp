<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" scope="application"></c:set>

<script type="text/javascript">
	$("#addUserForm").hide();
	
	$("#addUser").click(function(){
			
		$("#userManagerDiv").hide();
		$("#addUserForm").show();
		
	});
	
	$("#submitAdd").click(function(){
		$("#userManagerDiv").show();
		$("#addUserForm").hide();
	});
	

</script>



<div id="addUserForm" style="display: true">
	<form class="form-search" action="">
		<div class="span2 " style="min-height: 500px">
			<button id="aotu_read" style="width: 200px" class="btn">自动识别</button>
		</div>

		<div class="span10 " style="min-height: 500px">
			<div class="row-fluid ">
				<div class="span5 well " style="min-height: 500px">
					<div>
						<label>用户姓名:</label> 
						<input type="text" class="span6" placeholder="自动识别">
					</div>
					<div>
						<label>移动电话:</label> 
						<input type="text" class="span6" placeholder="请输入移动电话...">
					</div>
					<div>
						<label>身份证号:</label> 
						<input type="text" class="span6" placeholder="自动识别">
					</div>
					
						<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
					
								<label class="radio"> 
									<input type="radio" name="optionsRadios"  id="optionsRadios1"  value="option1" checked>男
								</label>
								<label class="radio"> 
									<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">女
								</label>
					
					<div>
						<label>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族:</label> 
						<input type="text" class="span6" placeholder="自动识别">
					</div>
					
					<div>
						<label>出生年月:</label> 
						<input type="text" class="span6" placeholder="自动识别">
					</div>
					<div>
						<label>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄:</label> 
						<input type="text" class="span6" placeholder="自动识别">
					</div>

				</div>
				<div class="span5 " style="min-height: 500px">
				</div>
			</div>
			<div class="row-fluid ">
				<div class="span10">
					<button id="submitAdd" style="width: 500px" class="btn">提交</button>
				</div>
			</div>
		</div>
	</form>
</div>

<div id="userManagerDiv">
	<div class="span2">
		<button id="addUser" style="width: 100px" class="btn">添加用户</button>
	</div>
	<div class="span2">
		<button id="editUser" style="width: 100px" class="btn">编辑用户</button>
	</div>
	<div class="span2">
		<button id="deleteUser" style="width: 100px" class="btn">删除用户</button>
	</div>
<br><br>
	<div class="row-fluid">
		<form class="well  form-search"
			action="${path }/cmconsumer/consumer/get.do">
			<div class="row-fluid">
				<div class="span3">
					<label>用户姓名:</label> 
					<input type="text" class="span6" placeholder="请输入用户姓名...">
				</div>
				<div class="span3">
					<label>手机号码:</label> <input type="text" class="span6"
						placeholder="请输入手机号...">
				</div>
				<div class="span3">
					<button style="width: 100px"class="btn">搜索</button>
				</div>
			</div>
		</form>
	</div>

	<table class="table table-hover table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>用户姓名</th>
				<th>联系电话</th>
				<th>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</th>
				<th>身份证号</th>
				<th>分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;店</th>
				<th>是否激活</th>
				<th>是否锁定</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="radio" name="optionsRadios"  id="optionsRadios1">
				</td>
				<td>虎货轩</td>
				<td>13838383838</td>
				<td>女</td>
				<td>111111111111111111</td>
				<td>福州店</td>
				<td><button  class="btn">激活</button></td>
				<td><button  class="btn">锁定</button></td>
			</tr>
			<tr>
				<td><input type="radio" name="optionsRadios"  id="optionsRadios1">
				</td>
				<td>虎货三皮</td>
				<td>13838383838</td>
				<td>男</td>
				<td>111111111111111222</td>
				<td>北京店</td>
				<td><button  class="btn">激活</button></td>
				<td><button  class="btn">锁定</button></td>
			</tr>
			<tr>
				<td><input type="radio" name="optionsRadios"  id="optionsRadios1">
				</td>
				<td>虎货陈</td>
				<td>13838383838</td>
				<td>男</td>
				<td>111111111111111333</td>
				<td>厦门店</td>
				<td><button  class="btn">激活</button></td>
				<td><button  class="btn">锁定</button></td>
			</tr>
		</tbody>
	</table>

	<!-- style="position:fixed;top:150px;left:1100px" -->
	<div class="pagination pagination-centered">
		<ul>
			<li><a href="#">«</a></li>
			<li><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">»</a></li>
		</ul>
	</div>
</div>
