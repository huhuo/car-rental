<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form class="form-horizontal well">
	<div class="row-fluid">
		<div class="span6">
			<div class="control-group">
				<label class="control-label" for="inputname">用户姓名</label>
				<div class="controls">
					<input type="text" class="required" id="inputname" name="username"
						placeholder="用户姓名..." value="${user.username }"
						readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputBirthday">出生日期</label> <input
					type="hidden" name="birthday1">
				<div class="controls">
					<div class="datetimepicker">
						<input data-format="yyyy-MM-dd" class="required" type="text"
							id="inputBirthday" placeholder="请选择出生日期..."
							value="${user.birthday }" readonly="readonly"></input>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="inputIdentityCardId">身份证号码</label>
				<div class="controls">
					<input type="text" class="required" id="inputIdentityCardId"
						name="identityCardId" placeholder="请输入身份证号码..."
						value="${user.identityCardId}" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputGender">性别</label>
				<div class="controls">
					<input type="radio" id="gender_male" value="1" disabled="disabled"
						<c:if test="${user.gender=='1'}">checked="checked"</c:if>
						name="gender">男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="radio" id="gender_female" value="2" disabled="disabled"
						<c:if test="${user.gender=='2'}">checked="checked"</c:if>
						name="gender">女
					<div id="inputGenderSpan" style="float: center"></div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputStoreId">所属门店</label>
				<div class="controls">
					<select id="inputStoreId" name="storeId" class="required"
						disabled="disabled">
						<option value="${user.storeId}">
							${store.name} </ option>
							<c:forEach items="${list }" var="store">
								<option value="${store.id}">${store.name }</option>
							</c:forEach>
					</select>
					<div id="inputGenderSpan" style="float: center"></div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputtelephone">固定电话</label>
				<div class="controls">
					<input type="text" class="required" id="inputtelephone"
						name="telephone" placeholder="联系电话..." value="${user.telephone}"
						readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputMobileNumber">手机号码</label>
				<div class="controls">
					<input type="text" class="required" id="inputMobileNumber"
						name="mobileNumber" placeholder="联系电话..."
						value="${user.mobileNumber}" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputAddress">现居住地址</label>
				<div class="controls">
					<input type="text" class="required" id="inputAddress"
						name="address" placeholder="现居住地址..." value="${user.address}"
						readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputEmail">邮箱</label>
				<div class="controls">
					<input type="email" id="inputEmail" name="email"
						placeholder="没有更新邮箱..." value="${user.email}" readonly="readonly">
					<input type="hidden" name="status" value="1"> <input
						type="hidden" name="id" value="${user.id }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputCreateTime">创建时间</label>
				<div class="controls">
					<input type="email" id="inputCreateTime" name="createTime"
						value="${user.createTime}" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputUpdateTime">最近修改时间</label>
				<div class="controls">
					<input type="email" id="inputUpdateTime" name="updateTime"
						value="${user.updateTime}" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="addbtn">返回</button>
				</div>
			</div>
		</div>
		<div class="span6">
			<ul class="thumbnails">
				<li class="span12">
					<a href="javascript:void(0)"class="thumbnail">
						<img class="img-rounded" src="${user.picture.url}" alt="">
					</a>
				</li>
			</ul>
		</div>
	</div>
</form>
<script type="text/javascript">
	$(document).ready(function() {
		// $('#editstoreForm').validate();
		// store add page
		$('#editUserForm .datetimepicker').datetimepicker({
			language: 'zh'
		}).on('changeDate', function(ev) {
			console.log(ev);
			var birthday = $('#inputBirthday').val();
			console.log(birthday);
			$('input[name="birthday1"]').val(birthday);
		});
		
		$('#userEditDivId form button').click(function(event) {
			$('#userEditDivId').hide();
			$('#userMgrDivId').show(500);
			return false;
		});
	});
</script>