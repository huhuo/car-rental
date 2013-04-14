<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path"
	scope="application"></c:set>

<style type="text/css">
table.table-hover tbody tr.huhuo-item-selected {
	background-color: #5D89F8;
	color: #FFF;
}
</style>

<script type="text/javascript">
	$("#addForm").hide();
	// add, edit and delete button group event
	var btnGroup = $('#customerManagerDiv div.navbar div.btn-group');
	btnGroup.children('button[name="add"]').click(function(event) {
		$("#customerManagerDiv").hide();
		$("#addForm").show(500);
	});
	btnGroup.children('button[name="edit"]').click(function(event) {
		console.log('=====edit======');
	});
	btnGroup.children('button[name="delete"]').click(function(event) {
		console.log('=====delete======');
	});

	$("#editCustomer").click(function() {
		$("#customerManagerDiv").hide();
		$("#addForm").show();
	});

	$(document).ready(function() {
		// bind click event to drop down component ==> reset search term
		$('.search-term').click(function(event) {
			$('.search-term').each(function(idx, item) {
				$(item).removeClass("huhuo-item-selected");
			});
			$(this).addClass("huhuo-item-selected");
			var searchInput = $('#huhuoForm').children('.search-query');
			searchInput.attr('placeholder', $(this).html());
			searchInput.attr('name', $(this).attr('id'));
		});
		// bind click event to search button ==> query record by search term
		$('#huhuoForm').huhuoFormPost(function(data, status) {
			if($.isJsonObj(data)) {
				$.huhuoGrowlUI('error occur in server --> ' + data.msg);
			} else {
				$('#consumerPageGridId').empty();
				$('#consumerPageGridId').append(data);
			}
		});
		$('#huhuoForm').trigger('submit');

		$('#addForm form').huhuoFormPost(function(data, status) {
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

</script>

<div id="addForm">
	<form class="form-horizontal "
		action="${path }/cmconsumer/consumer/add.do">
		<div class="span2  well" style="min-height: 500px">
			<img src="http://www.baidu.com/img/baidu_jgylogo3.gif"
				class="img-polaroid"> <br>
			<button style="width: 100px" class="btn">自动识别</button>
		</div>

		<div class="span10  well" style="min-height: 500px">
			<div class="row-fluid ">
				<div class="span5  " style="min-height: 500px">
					<div>
						<label class="btn btn-large btn-block">会员信息</label>
					</div>
					<br>
					<div class="control-group">
						<label class="control-label" for="inputSeating">会员姓名</label>
						<div class="controls">
							<input type="number" class="required digits"
								id="inputCustomerName" name="username" placeholder="输入会员姓名">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputCellPhone">移动电话</label>
						<div class="controls">
							<input type="number" class="required digits" id="inputCellPhone"
								name="mobileNumber" placeholder="输入移动电话">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputTelephone">固定电话</label>
						<div class="controls">
							<input type="number" class="required digits" id="inputTelephone"
								name="telephone" placeholder="输入固定电话">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputIDCardNo">身份证号</label>
						<div class="controls">
							<input type="number" class="required digits" id="inputIDCardNo"
								name="identityCardId" placeholder="输入身份证号">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputBirthday">出生年月</label>
						<div class="controls">
							<input type="number" class="required digits" id="inputBirthday"
								name="brithday" placeholder="输入出生年月">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputAge">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄</label>
						<div class="controls">
							<input type="number" class="required digits" id="inputAge"
								name="age" placeholder="输入年龄">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputGender">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
						<div class="controls">
							<label class="radio inline"> <input type="radio"
								id="inputGender" name="gender" id="optionsRadios1" value="1"
								checked> 男
							</label> <label class="radio inline"> <input type="radio"
								id="inputGender" name="gender" id="optionsRadios2" value="2">女
							</label>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPermanentAddress">户籍地址</label>
						<div class="controls">
							<input type="number" class="required digits"
								id="inputPermanentAddress" name="address" placeholder="输入户籍地址">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputIssuingAuthority ">发证机关</label>
						<div class="controls">
							<input type="text" class="required digits"
								id="inputIssuingAuthority" name="issuingauthority"
								placeholder="输入发证机关">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputnVIP">会员类型</label>
						<div class="controls">
							<select id="inputVIP" name="vip">
								<option value="1">普通会员</option>
								<option value="2">银卡会员</option>
								<option value="3">金卡会员</option>
								<option value="4">白金卡会员</option>
							</select>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputCurrentAddress">现在住址</label>
						<div class="controls">
							<input type="text" class="required digits"
								id="inputCurrentAddress" name="currentaddress"
								placeholder="输入现在住址">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputEmergencyContact">紧急联系人</label>
						<div class="controls">
							<input type="text" class="required digits"
								id="inputemergencyContact" name="emergencyContact"
								placeholder="输入紧急联系人">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputEmergencyTel">紧急联系人电话</label>
						<div class="controls">
							<input type="text" class="required digits" id="inputEmergencyTel"
								name="emergencyTel" placeholder="输入紧急联系人电话">
						</div>
					</div>

					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary" id="inputSubmit"
								name="submit">添加</button>
						</div>
					</div>

				</div>
				<div class="span5 " style="min-height: 500px">
					<div>
						<label class="btn btn-large btn-block">驾照信息</label>
					</div>
					<br>
					<div class="control-group">
						<label class="control-label" for="inputDrivingLicenseNo">驾驶证号</label>
						<div class="controls">
							<input type="text" class="required digits"
								id="inputDrivingLicenseNo" name="licenseNum"
								placeholder="输入驾驶证号">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputReceivingArea">领取地区</label>
						<div class="controls">
							<input type="text" class="required digits"
								id="inputReceivingArea" name="receivingarea"
								placeholder="请输入领取地区">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputnDriverType">驾照类型</label>
						<div class="controls">
							<select id="inputDriverType" name="drivertype">
								<option>c1</option>
								<option>b1</option>
								<option>a1</option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<div id="customerManagerDiv" class="well" style="padding: 0px;">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<div class="btn-group">
					<button name="add" class="btn">添加</button>
					<button name="edit" class="btn">编辑</button>
					<button name="delete" class="btn">删除</button>
				</div>
				<!-- search box -->
				<div class="pull-right">
					<ul class="nav">
						<li class="dropdown"><a href="javascript:void(0)"
							class="dropdown-toggle" data-toggle="dropdown">搜索条件<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a class="search-term" id="mobileNumber"
									href="javascript:void(0)">手机号</a></li>
								<li><a class="search-term" id="username"
									href="javascript:void(0)">姓名</a></li>
								<li class="divider"></li>
								<li><a class="search-term" id="identityCardId"
									href="javascript:void(0)">身份证号</a></li>
								<li><a class="search-term" id="address"
									href="javascript:void(0)">住址</a></li>
							</ul></li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;"
						action="${path }/cmconsumer/consumer/condition/get.do">
						<input type="text" class="search-query span6" name="mobileNumber"
							placeholder="手机号">
						<button type="submit" class="btn">search</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="consumerPageGridId">
	</div>
</div>
