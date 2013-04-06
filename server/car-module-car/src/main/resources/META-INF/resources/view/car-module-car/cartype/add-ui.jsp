<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function() {
	$('form').huhuoFormPost(function(data, status) {
		console.log(data);
		if(data.status == 'SUCCESS') {
			$('#cartypeEditDivId').hide();
			$('#cartypeMgrDivId').show(500);
	//		$('#huhuoForm').trigger('submit');
		} else {
			$.huhuoGrowlUI('error occur in server --> ' + data.msg);
		}
	}, '${path}/cmcar/cartype/add.do');
});
</script>
<form class="form-horizontal well" action="${path}/cmcar/cartype/add.do">
	<div class="row-fluid">
		<div class="span6">
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
