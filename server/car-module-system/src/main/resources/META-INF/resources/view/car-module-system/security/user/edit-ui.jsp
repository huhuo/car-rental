<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="span6">
		<form id="editUserForm" class="form-horizontal"
			action="${path}/cmsystem/security/user/update.do" method="post">
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label" for="inputname">用户姓名</label>
						<div class="controls">
							<input type="text" class="required" id="inputname" 
							name="username" placeholder="用户姓名..." value="${user.username }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputBirthday">出生日期</label> <input
							type="hidden" name="birthday1">
						<div class="controls">
							<div class="datetimepicker">
								<input data-format="yyyy-MM-dd" class="required" type="text"
									id="inputBirthday" placeholder="请选择出生日期..." value="${user.birthday }"></input> <span
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
								name="identityCardId" placeholder="请输入身份证号码..." value="${user.identityCardId}">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputGender">性别</label>
						<div class="controls">
							<input type="radio" id="gender_male" value="1" <c:if test="${user.gender=='1'}">checked="checked"</c:if> name="gender" >男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" id="gender_female" value="2" <c:if test="${user.gender=='2'}">checked="checked"</c:if> name="gender">女
							<div id="inputGenderSpan" style="float: center"></div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputStoreId">所属门店</label>
						<div class="controls">
							<select id="inputStoreId" name="storeId" class="required">
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
								name="telephone" placeholder="联系电话..." value="${user.telephone}">
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
		// $('#editstoreForm').validate();
		// store add page
		$('#editUserForm').huhuoFormPost(function(data, status) {
			if(data.status == 'SUCCESS') {
				$('#userEditDivId').hide();
				$('#userMgrDivId').show(500);
				$('#huhuoForm').trigger('submit');
			} else {
				$.huhuoGrowlUI('error occur in server --> ' + data.msg);
			}
		});
		
		$('#fileUploadForm').fileUpload(function(data, status, xhr) {
			// add upload file url to submit form
			if(data.data) {
				$('#editUserForm input[name="picture.name"]').val(data.data.name);
				$('#editUserForm input[name="picture.md5"]').val(data.data.md5);
			}
		});
		$("#inputBirthday").val('${user.birthday }'.substring(0, 10));
		$('#editUserForm .datetimepicker').datetimepicker({
			language: 'zh'
		}).on('changeDate', function(ev) {
			console.log(ev);
			var birthday = $('#inputBirthday').val();
			console.log(birthday);
			$('input[name="birthday1"]').val(birthday);
		});
	});
</script>