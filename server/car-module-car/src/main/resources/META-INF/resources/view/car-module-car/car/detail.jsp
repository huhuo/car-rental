<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="span6">
		<form id="editForm" class="form-horizontal">
			<input type="hidden" name="id" value="${car.id }">
			<div class="control-group">
				<label class="control-label" for="inputCarTypeId">所属车型</label>
				<div class="controls">
					<select id="inputCarTypeId" name="carTypeId" disabled="disabled">
					<c:forEach items="${carTypeList }" var="carType">
						<c:choose>
						<c:when test="${car.carTypeId == carType.id }">
							<option value="${carType.id }" selected="selected">${carType.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${carType.id }">${carType.name }</option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputLicencePlate">车牌号</label>
				<div class="controls">
					<input type="text" class="required" id="inputLicencePlate" name="licencePlate" value="${car.licencePlate }"  disabled="disabled">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="selectStoreId">所属门店</label>
				<div class="controls">
					<select id="selectStoreId" name="storeId" disabled="disabled">
					<c:forEach items="${storeList }" var="store">
						<c:choose>
						<c:when test="${car.storeId == store.id }">
							<option value="${store.id }" selected="selected">${store.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${store.id }" >${store.name }</option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputEngineNo">引擎编号</label>
				<div class="controls">
					<input type="text" class="required" id="inputEngineNo" name="engineNo" value="${car.engineNo }" disabled="disabled">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputGpsNo">GPS设备编号</label>
				<div class="controls">
					<input type="text" class="required" id="inputGpsNo" name="gpsNo" value="${car.gpsNo }" disabled="disabled">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="selectColor">车身颜色</label>
				<div class="controls">
					<select id="selectColor" name="color" disabled="disabled">
					<c:forEach items="${colorList }" var="color">
						<c:choose>
						<c:when test="${car.color == color.dictKey }">
							<option value="${color.dictKey }" selected="selected">${color.dictDisplayName }</option>
						</c:when>
						<c:otherwise>
							<option value="${color.dictKey }" >${color.dictDisplayName }</option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDrivedKilometer">已行驶里程数</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputDrivedKilometer" name="drivedKilometer" value="${car.drivedKilometer }" disabled="disabled">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOilMass">油量</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputOilMass" name="oilMass" value="${car.oilMass }" disabled="disabled">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn">返回</button>
				</div>
			</div>
		</form>
	</div>
	<div class="span6">
		<form id="fileUploadForm" class="form-horizontal" method="post"
			action="${path}/cmsystem/file/fileupload/cached.do" enctype="multipart/form-data">
			<ul class="thumbnails">
				<li class="span12">
					<a href="javascript:void(0)" class="thumbnail">
						<img class="img-rounded" src="${car.picture.url}" alt="">
					</a>
				</li>
			</ul>
		</form>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		// number output format
		$('.digits').formatNumber({format:'#', local:'cn'});
		$('.number').formatNumber({format:'#.00', local:'cn'});
		// cartype add page
		$('#editDivId form button').click(function(event) {
			$('#editDivId').hide();
			$('#mgrDivId').show(500);
			return false;
		});
	});
</script>