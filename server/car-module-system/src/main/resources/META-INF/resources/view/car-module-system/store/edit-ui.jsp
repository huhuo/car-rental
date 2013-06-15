<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="span6">
		<form id="editstoreForm" class="form-horizontal" action="${path}/cmsystem/store/update.do">
			<input type="hidden" name="id" value="${store.id }">
			<div class="control-group">
				<label class="control-label" for="inputName">分店名称</label>
				<div class="controls">
					<input type="text" class="required" id="inputName" name="name" placeholder="分店名称..." value="${store.name }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="storeManager">分店店长</label>
				<div class="controls">
					<select id="storeManager" name="managerId">
					<c:forEach items="${users}" var="user">
						<c:choose>
						<c:when test="${manager.id == user.id }">
							<option value="${user.id }" selected="selected">${user.username }</option>
						</c:when>
						<c:otherwise>
							<option value="${user.id }">${user.username } </option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
					<input type="hidden" name="id" value="${user.id }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputAddress">分店地址</label>
				<div class="controls">
					<input type="text" class="required" id="inputAddress" name="address" placeholder="分店地址..." value="${store.address }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputTelephone">联系方式</label>
				<div class="controls">
					<input type="text" class="required digits" id="inputTelephone" name="telephone" placeholder="联系方式..." value="${store.telephone }">
				</div>
			</div>
			<input type="hidden"name="id" value="${store.id }">	
			<input type="hidden" name="picture.id" value="${store.picture.id }">
	        <input type="hidden" name="picture.name" value="${store.picture.name }">
			<input type="hidden" name="picture.md5" value="${store.picture.md5 }">
			<!-- charge standard -->
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn">确定</button>
				</div>
			</div>
		</form>
	</div>
	<div class="span6">
		<form id="fileUploadForm" class="form-horizontal">
			<ul class="thumbnails">
				<li class="span12">
					<a href="javascript:void(0)" class="thumbnail">
						<img class="img-rounded" src="${store.picture.url}" alt="">
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
		$('#editstoreForm').validate();
		
		$('#fileUploadForm').fileUpload(function(data, status, xhr) {
			// add upload file url to submit form
			if(data.data) {
				$('#editstoreForm input[name="picture.name"]').val(data.data.name);
				$('#editstoreForm input[name="picture.md5"]').val(data.data.md5);
			}
		});
		
		// store add page
		$('#editstoreForm').huhuoFormPost(function(data, status) {
			if(data.status == 'SUCCESS') {
				$('#storeEditDivId').hide();
				$('#storeMgrDivId').show(500);
				$('#huhuoForm').trigger('submit');
			} else {
				$.huhuoGrowlUI('error occur in server --> ' + data.msg);
			}
		});
	});
</script>