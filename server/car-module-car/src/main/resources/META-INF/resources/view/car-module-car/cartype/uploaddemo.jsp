<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {
		console.log($('#fileUploadForm'));
		$('#fileUploadForm').ajaxForm({
			beforeSend : function() {
				console.log('beforeSend');
			},
			uploadProgress : function(event, position, total, percentComplete) {
				console.log('position --> ' + position);
				console.log('total --> ' + total);
				console.log('percentComplete --> ' + percentComplete);
			},
			success : function() {
				console.log('success');
			},
			complete : function() {
				console.log('complete');
			}
		});
	});
</script>

<form id="fileUploadForm" class="form-horizontal"
	action="${path}/cmsystem/file/fileupload/cached.do" method="post"
	enctype="multipart/form-data">
	<ul class="thumbnails">
		<li class="span12"><a href="javascript:void(0)" class="thumbnail">
				<img class="img-rounded" src="${path }/res/images/status/bazzi.jpg"
				alt="">
		</a></li>
	</ul>
	<div class="control-group">
		<label class="control-label" for="inputIcon">图片上传</label>
		<div class="controls">
			<input type="file" id="inputIcon" name="uploadFile"> <input
				type="submit" value="Upload File to Server">
		</div>
	</div>
	<div class="progress">
		<div class="bar"></div>
		<div class="percent">0%</div>
	</div>
</form>

