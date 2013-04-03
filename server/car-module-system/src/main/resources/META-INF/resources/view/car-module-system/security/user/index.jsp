<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" scope="application"></c:set>

<script type="text/javascript">
	$("#addUserForm").hide();
	
	$("#addUser").click(function(){
			
		$("#userManagerDiv").hide();
		$("#aotu_read").hide();
		$("#addUserForm").show();
		
	});
	
	$("#submitAdd").click(function(){
		$("#userManagerDiv").show();
		$("#addUserForm").hide();
	});
	
	$(document).ready(function(){
		// bind click event to drop down component ==> reset search term
		$('.search-term').click(function(event) {
			var searchInput = $('#huhuoForm').children('.search-query');
			searchInput.attr('placeholder', $(this).html());
			searchInput.attr('name', $(this).attr('id'));
		});
		// bind click event to search button ==> query record by search term
		$('#huhuoForm').huhuoFormPost(function(data, status) {
			console.log(data);
		});
	});
</script>






<div id="addUserForm"  class="form-horizontal">
	<form class="form-search" action="">
		<div class="span2 " style="min-height: 500px">
			<button id="aotu_read" style="width: 200px" class="btn">自动识别</button>
		</div>

		<div class="span10 " style="min-height: 500px">
			<div class="row-fluid ">
				<div class="span5" style="min-height: 500px">
				
					<div class="control-group">
					    <label class="control-label" for="inputUsername">用户姓名</label>
					    <div class="controls">
					      	<input type="text" id="inputUsername"  name="username" placeholder="输入用户名">
					    </div>
				  	</div>
					<div class="control-group">
					    <label class="control-label" for="inputPassword">用户密码</label>
					    <div class="controls">
					      	<input type="password" id="inputPassword"  name="password" placeholder="输入用户密码">
					    </div>
				  	</div>
					<div class="control-group">
					    <label class="control-label" for="inputRepeatPassword">重复密码</label>
					    <div class="controls">
					      	<input type="password" id="inputRepeatPassword"  name="password" placeholder="再次输入用户密码">
					    </div>
				  	</div>
				  	
					<div class="control-group">
					    <label class="control-label" for="inputMobileNumber">移动电话</label>
					    <div class="controls">
					      	<input type="text" id="inputMobileNumber"  name="mobilenumber" placeholder="输入移动电话">
					    </div>
				  	</div>
					
					<div class="control-group">
					    <label class="control-label" for="inputIdentityCardId">身份证号</label>
					    <div class="controls">
					      	<input type="text" id="inputIdentityCardId"  name="identitycardid" placeholder="输入身份证号">
					    </div>
				  	</div>
				  	
					
					<div class="control-group">
					    <label class="control-label" for="inputBirthday">出生日期</label>
					    <div class="controls">
					      	<input type="text" id="inputBirthday"  name="birthday" placeholder="输入出生日期">
					    </div>
				  	</div>
					
					<div class="control-group">
						<label class="control-label" for="inputGender">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
						<div class="controls">
								<label class="radio inline">
									<input type="radio" id="inputGender" name="gender" id="optionsRadios1" value="1" checked> 男
								</label>
								<label class="radio inline">
									<input type="radio" id="inputGender" name="gender" id="optionsRadios2" value="2">女
								</label>
						</div>
					</div>
					
					<div class="control-group">
					    <div class="controls">
								<button id="submitAdd" type="submit"  class="btn btn-large btn-primary" class="btn">提交</button>
					    </div>
				  	</div>

				</div>
			</div>
		</div>
	</form>
</div>

<div id="userManagerDiv" class="well" style="padding: 0px;">
<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<div id = "btn_group" class="btn-group">
					<button id="addUser" class="btn">添加</button>
					<button id="editUser" class="btn">编辑</button>
					<button id="deleteUser" class="btn">删除</button>
				</div>
				<!-- search box -->
				<div id="search_div"class="pull-right">
					<ul class="nav">
						<li class="dropdown"><a href="javascript:void(0)"
							class="dropdown-toggle" data-toggle="dropdown">搜索条件<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a class="search-term" id="name"
									href="javascript:void(0)">手机号</a></li>
								<li><a class="search-term" id="category"
									href="javascript:void(0)">姓名</a></li>
								<li class="divider"></li>
								<li><a class="search-term" id="seating"
									href="javascript:void(0)">身份证号</a></li>
								<li><a class="search-term" id="tankCapacity"
									href="javascript:void(0)">住址</a></li>
							</ul></li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;"
						action="${path }/cmcar/cartype/condition/get.do">
						<input type="text" class="search-query span6" name="mobileNumber"
							placeholder="手机号">
						<button type="submit" class="btn">搜索</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div >

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
	


</div>
