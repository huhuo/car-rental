<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function() {
		// validation
		$('#cartypeEditDivId form').validate();
		// cartype add page
		$('#cartypeEditDivId form button').click(function(event) {
			$('#cartypeEditDivId').hide();
			$('#cartypeMgrDivId').show(500);
			return false;
		});
		
	});
</script>
<form class="form-horizontal well" action="${path}/cmcar/cartype/add.do">
	<div class="row-fluid">
		<div class="span6">
			<input type="hidden" value="${carType.id }">
			<div class="control-group">
				<label class="control-label" for="inputName">车型名称</label>
				<div class="controls">
					<input type="text" class="required" id="inputName" name="name" value="${carType.name }" readonly="readonly">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="selectCategory">车辆类别</label>
				<div class="controls">
					<select id="selectCategory" name="category" disabled="disabled">
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
					<input type="number" id="inputSeating" readonly="readonly" value="${carType.seating }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputTankCapacity">油箱容量</label>
				<div class="controls">
					<input type="number" id="inputTankCapacity" readonly="readonly" value="${carType.tankCapacity }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDrivingRange">可行驶里程数</label>
				<div class="controls">
					<input type="number" id="inputDrivingRange" readonly="readonly" value="${carType.drivingRange }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDeposit">押金</label>
				<div class="controls">
					<input type="number" id="inputDeposit" readonly="readonly" value="${carType.chargeStandard.deposit }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPremium">保险费</label>
				<div class="controls">
					<input type="number" id="inputPremium" readonly="readonly" value="${carType.chargeStandard.premium }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRent">租金</label>
				<div class="controls">
					<input type="number" id="inputRent" readonly="readonly" value="${carType.chargeStandard.rent }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputMileageLimits">里程限制</label>
				<div class="controls">
					<input type="number" id="inputMileageLimits" readonly="readonly" value="${carType.chargeStandard.mileageLimits }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOverMileageFare">超里程费用</label>
				<div class="controls">
					<input type="number" id="inputOverMileageFare" readonly="readonly" value="${carType.chargeStandard.overMileageFare }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOverTimeFare">超时费用</label>
				<div class="controls">
					<input type="number" id="inputOverTimeFare" readonly="readonly" value="${carType.chargeStandard.overTimeFare }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputCarSendFare">上门送车费用</label>
				<div class="controls">
					<input type="number" id="inputCarSendFare" readonly="readonly" value="${carType.chargeStandard.carSendFare }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDiffShopReturnFare">异店结算附加费</label>
				<div class="controls">
					<input type="number" id="inputDiffShopReturnFare" readonly="readonly" value="${carType.chargeStandard.diffShopReturnFare }">
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
						<img class="img-rounded" src="${path }/${carType.icon.path}/${carType.icon.md5}" alt="">
					</a>
				</li>
			</ul>
		</div>
	</div>
</form>
