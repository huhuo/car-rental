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
					<input type="text" class="required" id="inputManage" name="manageId" value="${store.user.username}" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputTelephone">联系电话</label>
				<div class="controls">
					<input type="text" id="inputTelephone" readonly="readonly" value="${store.telephone }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRentNum">已租车辆数</label>
				<div class="controls">
					<input type="text" id="inputRentNum" readonly="readonly" value="${store.rentNum } （辆）">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputFreeNum">待租车辆数</label>
				<div class="controls">
					<input type="text" id="inputFreeNum" readonly="readonly" value="${store.freeNum } （辆）">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRepairNum">维修车辆数</label>
				<div class="controls">
					<input type="text" id="inputRepairNum" readonly="readonly" value="${store.repairNum } （辆）">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDisableNum">报废车辆数</label>
				<div class="controls">
					<input type="text" id="inputDisableNum" readonly="readonly" value="${store.disableNum } （辆）">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputCreateTime">入网时间</label>
				<div class="controls">
					<input type="text" id="inputCreateTime" readonly="readonly" value="${store.createTime }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputUpdateTime">最近更新时间</label>
				<div class="controls">
					<input type="text" id="inputUpdateTime" readonly="readonly" value="${store.updateTime }">
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
					<a href="javascript:void(0)"class="thumbnail">
						<img class="img-rounded" src="${store.picture.url}" alt="">
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