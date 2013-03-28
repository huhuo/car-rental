<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" scope="application"></c:set>

<script type="text/javascript">

	$("#addStoreForm").hide();
	
	$("#addStore").click(function() {
		$("#storeManagerDiv").hide();
		$("#aotu_read").hide();
		$("#addStoreForm").show();
		$("#btn_group").show();
		$("#search_div").show();
		
	});
	
	$("#editStore").click(function() {
		$("#storeManagerDiv").hide();
		$("#addStoreForm").show();
	});
	
	$("#submitAdd").click(function(){
		$("#storeManagerDiv").show();
		$("#addStoreForm").hide();
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

<div id="addStoreForm" style="display: true">
	<form class="form-search" action="">
		<div class="span2 " style="min-height: 500px">
			<button id="aotu_read" style="width: 200px" class="btn">自动识别</button>
		</div>

		<div class="span10 " style="min-height: 500px">
			<div class="row-fluid ">
				<div class="span5 well " style="min-height: 500px">
					<div>
						<label>分店名称:</label> 
						<input type="text" class="span6" placeholder="请输入分店名称">
					</div>
					<div>
						<label>分店地址:</label> 
						<input type="text" class="span6" placeholder="请输入分店名称">
					</div>
					<div>
						<label>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编:</label> 
						<input type="text" class="span6" placeholder="请输入邮编">
					</div>
					
					<div>
						<label>联系电话:</label> 
						<input type="text" class="span6" placeholder="请输入联系电话">
					</div>
					
					<div>
						<label>分店经理:</label> 
						<input type="text" class="span6" placeholder="请输入分店经理（类似google suggest）">
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

<div id="storeManagerDiv" class="well" style="padding: 0px;">
<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<div id = "btn_group" class="btn-group">
					<button id="addStore" class="btn">添加</button>
					<button class="btn">编辑</button>
					<button class="btn">删除</button>
				</div>
				<!-- search box -->
				<div id="search_div"class="pull-right">
					<ul class="nav">
						<li class="dropdown"><a href="javascript:void(0)"
							class="dropdown-toggle" data-toggle="dropdown">搜索条件<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a class="search-term" id="name"
									href="javascript:void(0)">店名</a></li>
								<li><a class="search-term" id="category"
									href="javascript:void(0)">地址</a></li>
							</ul></li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;"
						action="${path }/cmcar/cartype/condition/get.do">
						<input type="text" class="search-query span6" name="mobileNumber"
							placeholder="店名">
						<button type="submit" class="btn">搜索</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div>
	<table class="table table-hover table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>店logo</th>
				<th>分店名称</th>
				<th>联系电话</th>
				<th>联分店经理</th>
				<th>待租车辆</th>
				<th>已租车辆</th>
				<th>查看统计</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="radio" name="optionsRadios"  id="optionsRadios1"></td>
				<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
				<td>福州店</td>
				<td>15999999999</td>
				<td>郭德纲</td>
				<td>20</td>
				<td>30</td>
				<td><button style="width: 100px" class="btn">查看统计</button></td>
			</tr>
			<tr>
				<td><input type="radio" name="optionsRadios"  id="optionsRadios1"></td>
				<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
				<td>北京店</td>
				<td>15999999999</td>
				<td>刘德华</td>
				<td>30</td>
				<td>20</td>
				<td><button style="width: 100px" class="btn">查看统计</button></td>
			</tr>
			<tr>
				<td><input type="radio" name="optionsRadios"  id="optionsRadios1"></td>
				<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
				<td>厦门店</td>
				<td>15999999999</td>
				<td>陈润发</td>
				<td>10</td>
				<td>10</td>
				<td><button style="width: 100px" class="btn">查看统计</button></td>
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
