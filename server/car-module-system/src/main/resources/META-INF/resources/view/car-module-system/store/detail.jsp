<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form class="form-horizontal well">
	<div class="row-fluid">
		<div class ="span6">
			<input type="hidden" value="${store.id }">
			<div class="control-group">
				<label class="control-label" for="inputName">分店名称</label>
				<div class="controls">
					<input type="text" class="required" id="inputName" name="name" value="${store.name }" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputAdress">分店地址</label>
				<div class="controls">
					<input type="text" class="required" id="inputAdress" name="adress" value="${store.address }" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputManage">分店店长</label>
				<div class="controls">
					<input type="text" class="required" id="inputManage" name="manage" value="${manage.username}" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputTelephone">联系电话</label>
				<div class="controls">
					<input type="text" id="inputTelephone" readonly="readonly" value="${store.telephone }">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn">返回</button>
				</div>
			</div>
		</div>
		<div class="span6">
			<ul class="thumbnails">
				<li class="span12">
					<a href="javascript:void(0)" class="thumbnail">
						<img class="img-rounded" src="${path }/${detail.icon.path}/${detail.icon.md5}" alt="">
					</a>
				</li>
			</ul>
		</div>
	</div>
</form>
<script type="text/javascript">
	$(document).ready(function() {
		// detail add page
		$('#storeEditDivId form button').click(function(event) {
			$('#storeEditDivId').hide();
			$('#storeMgrDivId').show(500);
			return false;
		});
		
	});
</script>