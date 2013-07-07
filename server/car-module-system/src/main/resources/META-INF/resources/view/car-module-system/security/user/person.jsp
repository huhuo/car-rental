<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="span6">
		<form id="editPersonForm" class="form-horizontal"
			action="${path}/cmsystem/security/user/editPerson.do" method="post">
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label>尊敬的<b style="color: #007AB2">${user.loginName }</b>用户，您可以在此修改个人信息</label>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="inputOldPassword">旧密码</label>
						<div class="controls">
							<input type="password" class="required" id="inputOldPassword"
								name="oldPassword" placeholder="旧密码...">
							<input id="DBpassword" value="${user.password}"  name="OldPassword" type="hidden"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">新密码</label>
						<div class="controls">
							<input type="password" class="required" id="inputPassword"
								name="password" placeholder="新密码...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputConfirm_password">确认密码</label>
						<div class="controls">
							<input type="password" class="required" id="inputConfirm_password"
								name="confirm_password" placeholder="确认密码...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputMobileNumber">手机号码</label>
						<div class="controls">
							<input type="text" class="required" id="inputMobileNumber"
								name="mobileNumber" placeholder="联系电话..." value="${user.mobileNumber}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputAddress">现居住地址</label>
						<div class="controls">
							<input type="text" class="required" id="inputAddress"
								name="address" placeholder="现居住地址..." value="${user.address}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputEmail">邮箱</label>
						<div class="controls">
							<input type="email" id="inputEmail" name="email"
								placeholder="..." value="${user.email}"> <input type="hidden"
								name="status" value="1">
							<input type="hidden"
								name="id" value="${user.id }">	
						</div>
					</div>
					<input type="hidden" name="picture.id" value="${user.picture.id }">
			        <input type="hidden" name="picture.name" value="${user.picture.name }">
					<input type="hidden" name="picture.md5" value="${user.picture.md5 }">
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="addbtn">确定</button>
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
						<img class="img-rounded" src="${user.picture.url}" alt="">
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
<script type="text/javascript">
$(document).ready(function() {
	$('#editPersonForm').submit(function(){
		var fir = $(this);
		if(fir.valid()){
			$.post("${path}/cmsystem/security/user/editPerson.do",fir.serialize(),function(data,status,xhrq){
				if(data.status == 'SUCCESS') {
					$.huhuoGrowlUI("您的个人信息修改成功,请牢记您的新密码");
				}else{
					$.huhuoGrowlUI("系统内部发生错误：  "+data.msg);
				}
			});
		}
		return false;
	});
	$('#fileUploadForm').fileUpload(function(data, status, xhr) {
		// add upload file url to submit form
		if(data.data) {
			$('#editUserForm input[name="picture.name"]').val(data.data.name);
			$('#editUserForm input[name="picture.md5"]').val(data.data.md5);
		}
	});
});

$("#editPersonForm").validate({
	rules :{
		password: {
			required: true,
			minlength: 6,
			maxlength: 18
		},
		confirm_password: {
			equalTo: "#inputPassword"
		},
		
		oldPassword: {
			equalTo: "#DBpassword"
		}
	},
	messages: {
		confirm_password:"两次输入的密码不一致",
		
		oldPassword:"与原密码不一致"
	}
});
</script>