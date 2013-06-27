<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form class="form-horizontal well">
	<div class="row-fluid">
		<div class="span6">
			<input type="hidden" value="${carType.id }">
			<div class="control-group">
				<label class="control-label" for="inputName">车型名称</label>
				<div class="controls">
					<label  class="btn btn-info">${carType.name }</label >
					<!-- 
					<input type="text" class="required" id="inputName" name="name" value="${carType.name }" readonly="readonly">
					 -->
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="selectCategory">车辆类别</label>
				<div class="controls">
					<select id="selectCategory" name="category" disabled="disabled">
					<c:forEach items="${carTypeCategoryList }" var="category">
					<c:choose>
					<c:when test="${category.key == carType.category }">
						<option value="${category.key }" selected="selected">${category.disp }</option>
					</c:when>
					<c:otherwise>
						<option value="${category.key }">${category.disp }</option>
					</c:otherwise>
					</c:choose>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputSeating">座位数</label>
				<div class="controls">
					<!-- 
					<input type="number" id="inputSeating" readonly="readonly" value="${carType.seating }">
					 -->
					<label class="btn btn-info">${carType.seating } 个</label >
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputTankCapacity">油箱容量</label>
				<div class="controls">
					<!--
					<input type="number" id="inputTankCapacity" readonly="readonly" value="${carType.tankCapacity }">
					  -->
					<label class="btn btn-info">${carType.tankCapacity } 升</label >
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDrivingRange">可行驶里程数</label>
				<div class="controls">
					<!-- 
					<input type="number" id="inputDrivingRange" readonly="readonly" value="${carType.drivingRange }">
					 -->
					<label class="btn btn-info">${carType.drivingRange } 公里</label >
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDeposit">押金</label>
				<div class="controls">
					<!-- 
					<input type="number" id="inputDeposit" readonly="readonly" value="${carType.chargeStandard.deposit }">
					 -->
					<label  class="btn btn-info">${carType.chargeStandard.deposit  } 元</label >
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPremium">保险费</label>
				<div class="controls">
					<!-- 
					<input type="number" id="inputPremium" readonly="readonly" value="${carType.chargeStandard.premium }">
					 -->
					<label  class="btn btn-info">${carType.chargeStandard.premium} 元</label >
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRent">租金</label>
				<div class="controls">
					<!-- 
					<input type="number" id="inputRent" readonly="readonly" value="${carType.chargeStandard.rent }">
					 -->
					<label  class="btn btn-info">${carType.chargeStandard.rent } 元</label >
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputMileageLimits">里程限制</label>
				<div class="controls">
					<!-- 
					<input type="number" id="inputMileageLimits" readonly="readonly" value="${carType.chargeStandard.mileageLimits }">
					 -->
					<label  class="btn btn-info">${carType.chargeStandard.mileageLimits} 公里</label >					
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOverMileageFare">超里程费用</label>
				<div class="controls">
					<!-- 
					<input type="number" id="inputOverMileageFare" readonly="readonly" value="${carType.chargeStandard.overMileageFare }">
					 -->
					<label  class="btn btn-info">${carType.chargeStandard.overMileageFare} 元</label >		
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOverTimeFare">超时费用</label>
				<div class="controls">
					<!-- 
					<input type="number" id="inputOverTimeFare" readonly="readonly" value="${carType.chargeStandard.overTimeFare }">
					 -->
					 <label  class="btn btn-info">${carType.chargeStandard.overTimeFare} 元</label >		
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputCarSendFare">上门送车费用</label>
				<div class="controls">
					<!-- 
					<input type="number" id="inputCarSendFare" readonly="readonly" value="${carType.chargeStandard.carSendFare }">
					 -->
					 <label  class="btn btn-info">${carType.chargeStandard.carSendFare} 元</label >		
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDiffShopReturnFare">异店结算附加费</label>
				<div class="controls">
					<!-- 
					<input type="number" id="inputDiffShopReturnFare" readonly="readonly" value="${carType.chargeStandard.diffShopReturnFare }">
					 -->
					 <label  class="btn btn-info">${carType.chargeStandard.diffShopReturnFare } 元</label >
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
						<img class="img-rounded" src="${carType.icon.url}" alt="">
					</a>
				</li>
			</ul>
		</div>
	</div>
</form>
<script type="text/javascript">
	$(document).ready(function() {
		// cartype add page
		$('#editDivId form button').click(function(event) {
			$('#editDivId').hide();
			$('#mgrDivId').show(500);
			return false;
		});
	});
</script>