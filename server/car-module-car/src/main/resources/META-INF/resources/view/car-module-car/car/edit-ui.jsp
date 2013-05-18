<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="span6">
		<form id="editForm" class="form-horizontal" action="${path}/cmcar/car/update.do">
			<input type="hidden" name="id" value="${car.id }">
			<div class="control-group">
				<label class="control-label" for="inputCarTypeId">所属车型</label>
				<div class="controls">
					<select id="inputCarTypeId" name="carTypeId">
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
					<input type="text" class="required" id="inputLicencePlate" name="licencePlate" value="${car.licencePlate }" placeholder="车牌号...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="selectStoreId">所属门店</label>
				<div class="controls">
					<select id="selectStoreId" name="storeId">
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
					<input type="text" class="required" id="inputEngineNo" name="engineNo" value="${car.engineNo }" placeholder="引擎编号...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputGpsNo">GPS设备编号</label>
				<div class="controls">
					<input type="text" class="required" id="inputGpsNo" name="gpsNo" value="${car.gpsNo }" placeholder="GPS设备编号...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="selectColor">车身颜色</label>
				<div class="controls">
					<select id="selectColor" name="color">
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
					<input type="number" class="required digit" id="inputDrivedKilometer" name="drivedKilometer" value="${car.drivedKilometer }" placeholder="已行驶里程数...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOilMass">油量</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputOilMass" name="oilMass" value="${car.oilMass }" placeholder="油量（单位，升）...">
				</div>
			</div>
			<input type="hidden" name="picture.id" value="${car.picture.id }">
			<input type="hidden" name="picture.name" value="${car.picture.name }">
			<input type="hidden" name="picture.md5" value="${car.picture.md5 }">
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn">修改</button>
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
		// number output format
		$('.digits').formatNumber({format:'#', local:'cn'});
		$('.number').formatNumber({format:'#.00', local:'cn'});
		// validation
		$('#editForm').validate();
		// image upload event
		$('#fileUploadForm').fileUpload(function(data, status, xhr) {
			// add upload file url to submit form
			if(data.data) {
				$('#editForm input[name="picture.name"]').val(data.data.name);
				$('#editForm input[name="picture.md5"]').val(data.data.md5);
			}
		});
		// cartype add page
		$('#editForm').huhuoFormPost(function(data, status) {
			if(data.status == 'SUCCESS') {
				$('#editDivId').hide();
				$('#mgrDivId').show(500);
				$('#huhuoForm').trigger('submit');
			} else {
				$.huhuoGrowlUI('error occur in server --> ' + data.msg);
			}
		});
	});
</script>