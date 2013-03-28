<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path"
	scope="application"></c:set>

<script type="text/javascript">
	$("#addForm").hide();

	$("#addCustomer").click(function() {
		alert(111);
		$("#customerManagerDiv").hide();
		$("#addForm").show();
	});

	$("#editCustomer").click(function() {
		$("#customerManagerDiv").hide();
		$("#addForm").show();
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

	$("#submitAdd").click(
			function() {

				var username = $("#addUserName").val();
				alert("username:" + username);

				$.get("${path }/cmconsumer/consumer/register.do?username="
						+ username + "&t=" + Math.random(), function(data,
						status) {
					console.info(data);

				});

				$("#addForm").hide();
				$("#customerManagerDiv").show();
			});

	function loadData() {

		$.get("${path }/cmconsumer/consumer/get.do?t=" + Math.random(),
				function(data, status) {
					console.info(data);

				});
	}
</script>

<div id="addForm" style="display: true">
	<form class="form-search" action="">
		<div class="span2  well" style="min-height: 500px">
			<img src="http://www.baidu.com/img/baidu_jgylogo3.gif"
				class="img-polaroid"> <br>
			<button style="width: 100px" class="btn">自动识别</button>
		</div>

		<div class="span10  well" style="min-height: 500px">
			<div class="row-fluid ">
				<div class="span5 well " style="min-height: 500px">
					<div>
						<label class="btn btn-large btn-block">会员信息</label>
					</div>
					<br>
					<div>
						<label>会员姓名:</label> <input type="text" class="span6"
							placeholder="自动识别">
					</div>
					<div>
						<label>固定电话:</label> <input type="text" class="span6"
							placeholder="请输入固定电话...">
					</div>
					<div>
						<label>移动电话:</label> <input type="text" class="span6"
							placeholder="请输入移动电话...">
					</div>
					<div>
						<label>身份证号:</label> <input type="text" class="span6"
							placeholder="自动识别">
					</div>

					<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label> <label
						class="radio"> <input type="radio" name="optionsRadios"
						id="optionsRadios1" value="option1" checked>男
					</label> <label class="radio"> <input type="radio"
						name="optionsRadios" id="optionsRadios2" value="option2">女
					</label>

					<div>
						<label>民&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;族:</label> <input
							type="text" class="span6" placeholder="自动识别">
					</div>

					<div>
						<label>出生年月:</label> <input type="text" class="span6"
							placeholder="自动识别">
					</div>
					<div>
						<label>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄:</label> <input
							type="text" class="span6" placeholder="自动识别">
					</div>
					<div>
						<label>户籍地址:</label> <input type="text" class="span6"
							placeholder="自动识别">
					</div>
					<div>
						<label>发证机关:</label> <input type="text" class="span6"
							placeholder="自动识别">
					</div>
					<div>
						<label>会员类型:</label> <input type="text" class="span6"
							placeholder="此处应该是下拉列表">
					</div>

				</div>
				<div class="span5 well" style="min-height: 500px">
					<div>
						<label class="btn btn-large btn-block">驾照信息</label>
					</div>
					<br>
					<div>
						<label>驾驶证号:</label> <input type="text" class="span6"
							placeholder="请输入驾驶证号">
					</div>
					<div>
						<label>领取地区:</label> <input type="text" class="span6"
							placeholder="请输入领取地区">
					</div>
					<div>
						<label>驾照类型:</label> <input type="text" class="span6"
							placeholder="驾照类型(下拉列表，A1,B1,C1,C2等)">
					</div>

				</div>
			</div>
			<div class="row-fluid ">
				<div class="span10 well">
					<div>
						<label>地址:</label> <input type="text" class="span6"
							placeholder="请输入地址">
					</div>
					<br>
					<button id="submitAdd" style="width: 200px" class="btn btn-primary">提交</button>

				</div>
			</div>
		</div>
	</form>
</div>

<div class="well" style="padding: 0px;">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<div class="btn-group">
					<button id="addCustomer" class="btn">添加</button>
					<button class="btn">编辑</button>
					<button class="btn">删除</button>
				</div>
				<!-- search box -->
				<div class="pull-right">
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
							placeholder="车型名称">
						<button type="submit" class="btn">search</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="customerManagerDiv">
		<table class="table table-hover table-condensed">
			<thead>
				<tr>
					<th></th>
					<th>&nbsp;&nbsp;头像</th>
					<th>会员编号</th>
					<th>会员姓名</th>
					<th>联系电话</th>
					<th>会员状态</th>
					<th>会员积分</th>
					<th>短信</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="radio" name="optionsRadios"
						id="optionsRadios1"></td>
					<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
					<td>10010</td>
					<td>虎货轩</td>
					<td>13838383838</td>
					<td>黑名单</td>
					<td>250+38+2</td>
					<td><button style="width: 100px" class="btn">短信</button></td>
				</tr>
				<tr>
					<td><input type="radio" name="optionsRadios"
						id="optionsRadios2"></td>
					<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
					<td>10086</td>
					<td>三皮兄</td>
					<td>15252525252</td>
					<td>正常</td>
					<td>2080</td>
					<td><button style="width: 100px" class="btn">短信</button></td>
				</tr>
				<tr>
					<td><input type="radio" name="optionsRadios"
						id="optionsRadios3"></td>
					<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
					<td>10000</td>
					<td>陈屌丝</td>
					<td>15222222222</td>
					<td>正常</td>
					<td>1080</td>
					<td><button style="width: 100px" class="btn">短信</button></td>
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