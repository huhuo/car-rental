<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function(){
		$('#addStoreForm').submit(function(){
			var fir = $('#addStoreForm');
			$.post("${path}/cmsystem/store/add.do",fir.serialize(),function(data,status,xhrq){
				$('#storeEditDivId').hide();
				$("#storeMgrDivId").show();
				// load element to cartypeEditDivId
				$("#storeEditDivId").load('${path}/cmsystem/store/index.do');
				
			});
			 
			return false ;
		});
		
		$('#fileUploadForm').fileUpload(function(data, status, xhr) {
			// add upload file url to submit form
			if(data.data) {
				$('#addStoreForm input[name="picture.name"]').val(data.data.name);
				$('#addStoreForm input[name="picture.md5"]').val(data.data.md5);
			}
		});
	});
	
	
	
	
</script>
<div class="row-fluid">
	<div class="span6">
		<form id="addStoreForm" class="form-horizontal well"
			action="${path}/cmsystem/store/add.do" method="post">
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label" for="inputname">分店名称</label>
						<div class="controls">
							<input type="text" class="required" id="inputname" name="name"
								placeholder="分店名称...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputaddress">分店地址</label>
						<div class="controls">
							<input type="text" class="required" id="inputaddress"
								name="address" placeholder="分店地址...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputManagerId">分店店长</label>
						<div class="controls">
							<select id="inputManagerId" name="managerId">
								<c:forEach items="${mgrs}" var="mgr">
									<option value="${mgr.id}">${mgr.username}
										${mgr.genderDict.dictDisplayName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputtelephone">联系电话</label>
						<div class="controls">
							<input type="text" class="required" id="inputtelephone"
								name="telephone" placeholder="联系电话...">
						</div>
					</div>
					<input type="hidden" name="status" value="1">
					<input type="hidden" name="picture.name" value="">
			        <input type="hidden" name="picture.md5" value="">
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn">添加</button>
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