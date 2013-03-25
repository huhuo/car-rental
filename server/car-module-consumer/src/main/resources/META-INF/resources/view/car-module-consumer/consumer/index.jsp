<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" scope="application"></c:set>

<script type="text/javascript">
	$("#addForm").hide();

	$("#addCustomer").click(function() {
		$("#customerManagerDiv").hide();
		$("#addForm").show();
	});
	
	$("#editCustomer").click(function() {
		$("#customerManagerDiv").hide();
		$("#addForm").show();
	});
	

	$("#submitAdd").click(
			function() {

				var username = $("#addUserName").val();
				alert("username:" + username);

				$.get("${path }/cmconsumer/consumer/register.do?username=" + username + "&t=" + Math.random(), 
						function(data,status) {
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
			<img src="http://www.baidu.com/img/baidu_jgylogo3.gif" class="img-polaroid">
			<br>
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
						<label>会员姓名:</label> 
						<input type="text" class="span6" placeholder="自动识别">
					</div>
					<div>
						<label>固定电话:</label> 
						<input type="text" class="span6" placeholder="请输入固定电话...">
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
					<div>
						<label>户籍地址:</label> 
						<input type="text" class="span6" placeholder="自动识别">
					</div>
					<div>
						<label>发证机关:</label> 
						<input type="text" class="span6" placeholder="自动识别">
					</div>
					<div>
						<label>会员类型:</label> 
						<input type="text" class="span6" placeholder="此处应该是下拉列表">
					</div>

				</div>
				<div class="span5 well" style="min-height: 500px">
					<div>
						<label class="btn btn-large btn-block">驾照信息</label> 
					</div>
					<br>
					<div>
						<label>驾驶证号:</label> 
						<input type="text" class="span6" placeholder="请输入驾驶证号">
					</div>
					<div>
						<label>领取地区:</label> 
						<input type="text" class="span6" placeholder="请输入领取地区">
					</div>
					<div>
						<label>驾照类型:</label> 
						<input type="text" class="span6" placeholder="驾照类型(下拉列表，A1,B1,C1,C2等)">
					</div>
				
				</div>
			</div>
			<div class="row-fluid ">
				<div class="span10 well">
					<div>
						<label>地址:</label> 
						<input type="text" class="span6" placeholder="请输入地址">
					</div>
					<br>
					<button id="submitAdd" style="width: 200px" class="btn btn-primary">提交</button>

				</div>
			</div>
		</div>
	</form>
</div>

<div id="customerManagerDiv">
	<div class="span2">
		<button id="addCustomer" style="width: 100px" class="btn">添加会员</button>
	</div>
	<div class="span2">
		<button id="editCustomer" style="width: 100px" class="btn">编辑会员</button>
	</div>
	<div class="span2">
		<button id="delteCustomer" style="width: 100px" class="btn">删除会员</button>
	</div>
	<br><br>
	<div class="row-fluid">
		<form class="well form-search"
			action="${path }/cmconsumer/consumer/get.do">
			<div class="row-fluid">
				<div class="span3">
					<label>客户姓名:</label> 
					<input type="text" class="span6" placeholder="请输入姓名">
				</div>
				<div class="span3">
					<label>手机号码:</label> 
					<input type="text" class="span6" placeholder="请输入手机号">
				</div>
				<div class="span3">
					<button style="width: 100px" class="btn">搜索</button>
				</div>
			</div>
		</form>
	</div>

	<table class="table table-hover table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>头像</th>
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
				<td><input type="radio" name="optionsRadios"  id="optionsRadios1">
				</td>
				<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
				<td>10010</td>
				<td>虎货轩</td>
				<td>13838383838</td>
				<td>黑名单</td>
				<td>250+38+2</td>
				<td><button style="width: 100px" class="btn">短信</button></td>
			</tr>
			<tr>
				<td><input type="radio" name="optionsRadios"  id="optionsRadios2"></td>
				<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
				<td>10086</td>
				<td>三皮兄</td>
				<td>15252525252</td>
				<td>正常</td>
				<td>2080</td>
				<td><button style="width: 100px" class="btn">短信</button></td>
			</tr>
			<tr>
				<td><input type="radio" name="optionsRadios"  id="optionsRadios3"></td>
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