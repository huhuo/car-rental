<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function() {
	// validation  这里不能选择"cartypeEditDivId"下的所有form validate（），不然新加的form也会被校验，你个大虎货
 	 $('#addCarTypeForm').validate(); 
	// image upload event
	var percent = $('#fileUploadForm .progress .percent');
	var bar = $('#fileUploadForm .progress .bar');
	$('#fileUploadForm').ajaxForm({
		beforeSubmit: function(file, form, option) {
			if(file[0].value == '') {
				alert('请先选择要上传的图片');
				return false;
			}
		},
		beforeSend: function(s, xhr) {
			var percentVal = '0%';
			bar.width(percentVal);
			percent.html(percentVal);
		},
		uploadProgress: function(event, position, total, percentComplete) {
			var percentVal = percentComplete + '%';
			bar.width(percentVal);
			percent.html(percentVal);
		},
		success: function(data, status, xhr) {
			var percentVal = '100%';
			bar.width(percentVal);
			percent.html(percentVal);
			// add upload file url to submit form
			if(data.data) {
				$('#addCarTypeForm input[name="icon.name"]').val(data.data.name);
				$('#addCarTypeForm input[name="icon.md5"]').val(data.data.md5);
			}
		},
		complete: function(xhr) {
			msg = JSON.parse(xhr.responseText);
			$.huhuoGrowlUI(msg.msg);
			if(msg.data) {
				$('#fileUploadForm img').attr('src', '${path}/' + msg.data.path + '/' + msg.data.md5);
			}
		}
	});
	
	// form commit
	$('#addCarTypeForm').huhuoFormPost(function(data, status) {
		if(data.status == 'SUCCESS') {
			$('#cartypeEditDivId').hide();
			$('#cartypeMgrDivId').show(500);
			$('#huhuoForm').trigger('submit');
		} else {
			$.huhuoGrowlUI('error occur in server --> ' + data.msg);
		}
	}, '${path}/cmcar/cartype/add.do');
	
});
</script>

<div class="row-fluid">
	<div class="span6">
		<form id="addCarTypeForm" class="form-horizontal well" action="${path}/cmcar/cartype/add.do">
			<div class="control-group">
				<label class="control-label" for="inputName">车型名称</label>
				<div class="controls">
					<input type="text" class="required" id="inputName" name="name" placeholder="车型名称...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="selectCategory">车辆类别</label>
				<div class="controls">
					<select id="selectCategory" name="category">
						<option value="1" >轿车</option>
						<option value="2" >越野汽车</option>
						<option value="3" >客车</option>
						<option value="4" >货车</option>
						<option value="5" >自卸汽车</option>
						<option value="6" >牵引汽车</option>
						<option value="7" >专用汽车</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputSeating">座位数</label>
				<div class="controls">
					<input type="number" class="required digits" id="inputSeating" name="seating" placeholder="座位数...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputTankCapacity">油箱容量</label>
				<div class="controls">
					<input type="number" class="required digits" id="inputTankCapacity" name="tankCapacity" placeholder="油箱容量（单位：升）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDrivingRange">可行驶里程数</label>
				<div class="controls">
					<input type="number" class="required digits" id="inputDrivingRange" name="drivingRange" placeholder="可行驶里程数（单位：公里）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDeposit">押金</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputDeposit" name="chargeStandard.deposit" placeholder="押金（xxx元）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPremium">保险费</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputPremium" name="chargeStandard.premium" placeholder="保险费，xxx元/次...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRent">租金</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputRent" name="chargeStandard.rent" placeholder="租金（xxx元/天）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputMileageLimits">里程限制</label>
				<div class="controls">
					<input type="number" class="required digits" id="inputMileageLimits" name="chargeStandard.mileageLimits" placeholder="里程限制（xxx公里/日）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOverMileageFare">超里程费用</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputOverMileageFare" name="chargeStandard.overMileageFare" placeholder="超里程费用（xxx元/公里）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOverTimeFare">超时费用</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputOverTimeFare" name="chargeStandard.overTimeFare" placeholder="超时费用（xxx元/小时）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputCarSendFare">上门送车费用</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputCarSendFare" name="chargeStandard.carSendFare" placeholder="上门送车费用（xxx元）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDiffShopReturnFare">异店结算附加费</label>
				<div class="controls">
					<input type="number" class="required digit" id="inputDiffShopReturnFare" name="chargeStandard.diffShopReturnFare" placeholder="异店结算（还车）附加费（xxx元）...">
				</div>
			</div>
			<input type="hidden" name="icon.name" value="">
			<input type="hidden" name="icon.md5" value="">
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn">添加</button>
				</div>
			</div>
		</form>
	</div>
	<div class="span6">
		<form id="fileUploadForm" class="form-horizontal" action="${path}/cmsystem/file/fileupload/cached.do" method="post" enctype="multipart/form-data">
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

