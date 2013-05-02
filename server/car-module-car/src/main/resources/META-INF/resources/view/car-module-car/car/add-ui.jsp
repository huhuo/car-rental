<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
	<div class="span6">
		<form id="addForm" class="form-horizontal well" action="${path}/cmcar/car/add.do">
			<div class="control-group">
				<label class="control-label" for="inputCarTypeId">所属车型</label>
				<div class="controls">
					<select id="inputCarTypeId" name="carTypeId">
						<c:forEach items="${carTypeList }" var="carType">
						<option value="${carType.id }" >${carType.name }</option>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputLicencePlate">车牌号</label>
				<div class="controls">
					<input type="text" class="required" id="inputLicencePlate" name="licencePlate" placeholder="车牌号...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="selectStoreId">所属门店</label>
				<div class="controls">
					<select id="selectStoreId" name="storeId">
						<c:forEach items="${storeList }" var="store">
						<option value="${store.id }" >${store.name }</option>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputEngineNo">引擎编号</label>
				<div class="controls">
					<input type="text" class="required" id="inputEngineNo" name="engineNo" placeholder="引擎编号...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputGpsNo">GPS设备编号</label>
				<div class="controls">
					<input type="text" class="required" id="inputGpsNo" name="gpsNo" placeholder="GPS设备编号...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="selectColor">车身颜色</label>
				<div class="controls">
					<select id="selectColor" name="color">
					<c:forEach items="${colorList }" var="color">
						<option value="${color.dictKey }" >${color.dictDisplayName }</option>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDrivedKilometer">已行驶里程数</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputDrivedKilometer" name="drivedKilometer" placeholder="已行驶里程数...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOilMass">油量</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputOilMass" name="oilMass" placeholder="油量（单位，升）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRent">租金</label>
				<div class="controls">
					<input type="number" class="required number" id="inputRent" name="chargeStandard.rent" placeholder="租金（xxx元/天）...">
				</div>
			</div>
			<input type="hidden" name="picture.name" value="">
			<input type="hidden" name="picture.md5" value="">
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn">添加</button>
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
				<label class="control-label" for="inputIcon">图片上传</label>
				<div class="controls">
					<input type="file" id="inputIcon" name="cachedFile">
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
	// validation
	$('#addForm').validate();
	// image upload event
	$('#fileUploadForm').fileUpload(function(data, status, xhr) {
		// add upload file url to submit form
		if(data.data) {
			$('#addForm input[name="picture.name"]').val(data.data.name);
			$('#addForm input[name="picture.md5"]').val(data.data.md5);
		}
	});
	// form commit
	$('#addForm').huhuoFormPost(function(data, status) {
		if(data.status == 'SUCCESS') {
			$('#editDivId').hide();
			$('#mgrDivId').show(500);
			$('#huhuoForm').trigger('submit');
		} else {
			$.huhuoGrowlUI('error occur in server --> ' + data.msg);
		}
	}, '${path}/cmcar/car/add.do');
	
});
</script>

