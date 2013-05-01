<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {
		// cartype add page
		$('#storeEditDivId form').huhuoFormPost(function(data, status) {
			$('#storeEditDivId').hide();
			$('#storeMgrDivId').show(500);
			$('#storeMgrDivId form button').trigger("click");
			console.log($('#storeEditDivId form').serialize());
		}, '${path}/');
	});
</script>
<form class="form-horizontal well" action="${path}/cmcar/cartype/add.do">
	<div class="row-fluid">
		<div class="span6">
			<input type="hidden" value="${carType.id }">
			<div class="control-group">
				<label class="control-label" for="inputName">车型名称${carType }</label>
				<div class="controls">
					<input type="text" class="required" id="inputName" name="name" placeholder="车型名称..." value="${carType.name }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="selectCategory">车辆类别</label>
				<div class="controls">
					<select id="selectCategory" name="category">
					<c:forEach items="${carTypeCategoryList }" var="category">
					<c:choose>
					<c:when test="${category.dictKey == carType.category }">
						<option value="${category.dictKey }" selected="selected">${category.dictDisplayName }</option>
					</c:when>
					<c:otherwise>
						<option value="${category.dictKey }">${category.dictDisplayName }</option>
					</c:otherwise>
					</c:choose>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputSeating">座位数</label>
				<div class="controls">
					<input type="number" class="required digits" id="inputSeating" name="seating" placeholder="座位数..." value="${carType.seating }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputTankCapacity">油箱容量</label>
				<div class="controls">
					<input type="number" class="required digits" id="inputTankCapacity" name="tankCapacity" placeholder="油箱容量（单位：升）..." value="${carType.tankCapacity }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDrivingRange">可行驶里程数</label>
				<div class="controls">
					<input type="number" class="required digits" id="inputDrivingRange" name="drivingRange" placeholder="可行驶里程数（单位：公里）..." value="${carType.drivingRange }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDeposit">押金</label>
				<div class="controls">
					<input type="number" class="required" id="inputDeposit" name="chargeStandard.deposit" placeholder="押金（xxx元）..." value="${carType.chargeStandard.deposit }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPremium">保险费</label>
				<div class="controls">
					<input type="number" class="required" id="inputPremium" name="chargeStandard.premium" placeholder="保险费，xxx元/次..." value="${carType.chargeStandard.premium }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRent">租金</label>
				<div class="controls">
					<input type="number" class="required" id="inputRent" name="chargeStandard.rent" placeholder="租金（xxx元/天）..." value="${carType.chargeStandard.rent }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputMileageLimits">里程限制</label>
				<div class="controls">
					<input type="number" class="required digits" id="inputMileageLimits" name="chargeStandard.mileageLimits" placeholder="里程限制（xxx公里/日）..." value="${carType.chargeStandard.mileageLimits }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOverMileageFare">超里程费用</label>
				<div class="controls">
					<input type="number" class="required" id="inputOverMileageFare" name="chargeStandard.overMileageFare" placeholder="超里程费用（xxx元/公里）..." value="${carType.chargeStandard.overMileageFare }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOverTimeFare">超时费用</label>
				<div class="controls">
					<input type="number" class="required" id="inputOverTimeFare" name="chargeStandard.overTimeFare" placeholder="超时费用（xxx元/小时）..." value="${carType.chargeStandard.overTimeFare }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputCarSendFare">上门送车费用</label>
				<div class="controls">
					<input type="number" class="required" id="inputCarSendFare" name="chargeStandard.carSendFare" placeholder="上门送车费用（xxx元）..." value="${carType.chargeStandard.carSendFare }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDiffShopReturnFare">异店结算附加费</label>
				<div class="controls">
					<input type="number" class="required" id="inputDiffShopReturnFare" name="chargeStandard.diffShopReturnFare" placeholder="异店结算（还车）附加费（xxx元）..." value="${carType.chargeStandard.diffShopReturnFare }">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn">添加</button>
				</div>
			</div>
		</div>
		<div class="span6">
			<ul class="thumbnails">
				<li class="span12">
					<a href="javascript:void(0)" class="thumbnail">
						<img class="img-rounded" src="${path }/res/images/status/bazzi.jpg" alt="">
					</a>
				</li>
			</ul>
			<div class="control-group">
				<label class="control-label" for="inputIcon">图片上传</label>
				<div class="controls">
					<input type="file" id="inputIcon" name="icon" placeholder="图片上传...">
				</div>
			</div>
		</div>
	</div>
</form>
