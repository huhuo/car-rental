<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function(){
	//图片上传
	$('#fileUploadForm').fileUpload(function(data, status, xhr) {
		// add upload file url to submit form
		if(data.data) {
			$('#addUserForm input[name="picture.name"]').val(data.data.name);
			$('#addUserForm input[name="picture.md5"]').val(data.data.md5);
		}
	});
	
	$('#addUserForm').submit(function(){
		var fir = $(this);
		if(true){
			$.post("${path}/cmsystem/security/user/add.do",fir.serialize(),function(data,status,xhrq){
				$('#userEditDivId').hide();
				$("#userMgrDivId").show();
				// load element to cartypeEditDivId
				$('#huhuoForm').trigger('submit');
			});
		}
		return false;
	});
	$('#addUserForm .datetimepicker').datetimepicker({
		language: 'zh'
	}).on('changeDate', function(ev) {
		console.log(ev);
		var birthday = $('#inputBirthday').val();
		console.log(birthday);
		$('input[name="birthday1"]').val(birthday);
	});
});
</script>
<style>
label.error {
	color: #ea5200;
	margin-left: 4px;
	padding: 0px 20px;
	margin-bottom: 3px;
	background: url(res/images/status/unchecked.gif) no-repeat 2px 0
}

label.right {
	margin-left: 4px;
	padding-left: 20px;
	background: url(res/images/status/checked.gif) no-repeat 2px 0;
	vertical-align: middle;
}
</style>
<div class="row-fluid">
	<div class="span6">
		<form id="addUserForm" class="form-horizontal well"
			action="${path}/cmsystem/security/user/add.do" method="post">
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label" for="inputname">用户姓名</label>
						<div class="controls">
							<input type="text" class="required" id="inputname"
								name="username" placeholder="用户姓名...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputLoginName">登录名</label>
						<div class="controls">
							<input type="text" class="required" id="inputLoginName"
								name="loginName" placeholder="请输入系统登录名...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputBirthday">出生日期</label> <input
							type="hidden" name="birthday1">
						<div class="controls">
							<div class="datetimepicker">
								<input data-format="yyyy-MM-dd" class="required" type="text"
									id="inputBirthday" placeholder="请选择出生日期..."></input> <span
									class="add-on"> <i data-time-icon="icon-time"
									data-date-icon="icon-calendar"> </i>
								</span>
							</div>
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputIdentityCardId">身份证号码</label>
						<div class="controls">
							<input type="text" class="required" id="inputIdentityCardId"
								name="identityCardId" placeholder="请输入身份证号码...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword1">设置密码</label>
						<div class="controls">
							<input type="password" class="required" id="inputPassword1"
								name="password" placeholder="请设置密码...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword2">确认密码</label>
						<div class="controls">
							<input type="password" class="required" id="inputPassword2"
								name="confirm_password" placeholder="请确认密码...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputGender">性别</label>
						<div class="controls">
							<input type="radio" id="gender_male" value="1" name="gender">男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" id="gender_female" value="2" name="gender">女
							<div id="inputGenderSpan" style="float: center"></div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputStoreId">所属门店</label>
						<div class="controls">
							<select id="inputStoreId" name="storeId" class="required">
								<option value="">
									请选择...</ option>
									<c:forEach items="${mgrs }" var="mgr">
										<option value="${mgr.id}">${mgr.name }</option>
									</c:forEach>
							</select>
							<div id="inputGenderSpan" style="float: center"></div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputtelephone">固定电话</label>
						<div class="controls">
							<input type="text" class="required" id="inputtelephone"
								name="telephone" placeholder="联系电话...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputMobileNumber">手机号码</label>
						<div class="controls">
							<input type="text" class="required" id="inputMobileNumber"
								name="mobileNumber" placeholder="联系电话...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputAddress">现居住地址</label>
						<div class="controls">
							<input type="text" class="required" id="inputAddress"
								name="address" placeholder="现居住地址...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputEmail">邮箱</label>
						<div class="controls">
							<input type="email" id="inputEmail" name="email"
								placeholder="请输入邮箱地址..."> <input type="hidden"
								name="status" value="1">
						</div>
					</div>
					<input type="hidden" name="picture.name" value="">
			        <input type="hidden" name="picture.md5" value="">
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="addbtn">添加</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="span6">
		<form id="fileUploadForm" class="form-horizontal">
			<ul class="thumbnails">
				<li class="span12">
					<a href="javascript:void(0)" class="thumbnail">
						<img class="img-rounded" src="${path }/res/images/default.png" alt="">
					</a>
				</li>
			</ul>
			<div  class="control-group">
				<label class="control-label" for="inputPicture">图片上传</label>
				<div class="controls">
					<input type="file" id="inputPicture" name="cachedFile">
					<input type="submit" value="Upload File to Server">
				</div>
			</div>
			<div class="progress progress-striped active">
				<div class="bar"></div >
				<div class="percent">0%</div >
			</div>
		</form>
	</div>
</div>