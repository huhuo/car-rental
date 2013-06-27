<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
.form-horizontal .control-label {
	width: 25%;
}

.form-horizontal .controls {
	margin-left: 27%;
}

input.consumerinput {
	width: 80%;
}

div.orderinput input {
	width: auto;
}

div.titlewell {
	background-color: #5D89F8;
	color: #FFFFFF;
	padding: 5px 0px 3px;
}
</style>
<script type="text/javascript">
	
	// form commit
	$('#addConsumerForm').huhuoFormPost(function(data, status) {
		if(data.status == 'SUCCESS') {
			$('#consumerEditDivId').hide();
			$('#consumerManagerDiv').show(500);
			$('#huhuoForm').trigger('submit');
		} else {
			$.huhuoGrowlUI('error occur in server --> ' + data.msg);
		}
	}, '${path}/cmconsumer/consumer/add.do'); 
	
	
	$(document).ready(function() {
		console.log($('.datetimepicker'));
		
		var datetimePicker = $('.datetimepicker').datetimepicker({
			language: 'zh'
		});
		
		datetimePicker.on('changeDate', function(ev){
			var myBirthday = $("#inputBirthday").val();  
			var dd = [];
			dd = myBirthday.split("-");
			var length = dd.length;
			var len = parseInt(length);
			if (len > 1) {
				var year = dd[0];
				var date = new Date();
				var fYear = date.getFullYear();
				console.log("fYear:" + fYear);
				var curYear = parseInt(fYear);
				console.log("curYear:" + curYear);
				var birYear = parseInt(year);
				var age = curYear - birYear;
				console.log("age:" + age);
				$("#inputAge").val(age);
			} else {
				// TODO
			}
		});
		
		$("#returnSearch").click(function() {
			$('#consumerEditDivId').hide();
			$('#consumerManagerDiv').show(500);
			
			
		});
	});
</script>
<div style="position: relative">
	<button id="returnSearch" class="btn" style="margin-bottom: 5px;">返回</button>
	<form id='addConsumerForm' class="form-horizontal" action="${path }/cmconsumer/consumer/add.do">
		<div class="row-fluid">
			<div class="span12">
				<div class="row-fluid">
					<div class="span4 well">

						<ul class="thumbnails">
							<li class="span12"><a href="javascript:void(0)"
								class="thumbnail"> <img class="img-rounded"
									src="${path }/res/images/status/bazzi.jpg" alt="">
							</a></li>
						</ul>
						<!-- 
						<label style='text-align: center; font-weight: bold;'>扫描身份证</label>
						 -->
						<button style='text-align: center; font-weight: bold;' class="btn " id="scanIDCard"
									name="scanIDCard">扫描身份证</button>
					</div>
					
					<div class="span4 well " >
						<div class='well titlewell'>
							<label style='text-align: center; font-weight: bold;'>客户信息</label>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputSeating">会员姓名</label>
							<div class="controls">
								<input type="text" class="consumerinput required"
									id="inputCustomerName" name="username" placeholder="输入会员姓名">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputCellPhone">移动电话</label>
							<div class="controls">
								<input type="number" class="consumerinput required digits" id="inputCellPhone"
									name="mobileNumber" placeholder="输入移动电话">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputTelephone">固定电话</label>
							<div class="controls">
								<input type="number" class="consumerinput required digits" id="inputTelephone"
									name="telephone" placeholder="输入固定电话">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputIdentityCardId">身份证号</label>
							<div class="controls">
								<input type="text" class="consumerinput required digits" id="inputIdentityCardId"
									name="identityCardId" placeholder="输入身份证号">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputBirthday">出生年月</label>
							<div class="controls">
								<!-- 
								<input type="text" class="consumerinput required" id="inputBirthday" name=birthday placeholder="输入出生年月">
								 -->
								<div class="datetimepicker">
									<input type="text" data-format="yyyy-MM-dd"  
										class="consumerinput required" id="inputBirthday" name=xbirthday placeholder="输入出生年月">
									<span class="add-on"><i class="icon-th"></i></span>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputAge">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄</label>
							<div class="controls">
								<input type="number" disabled="disabled" class="consumerinput required digits" id="inputAge"
									name="age" placeholder="年龄">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputGender">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
							<div class="controls">
								<label class="radio inline"> <input type="radio"
									id="inputGender" name="gender" id="optionsRadios1" value="1"
									checked> 男
								</label> <label class="radio inline"> <input type="radio"
									id="inputGender" name="gender" id="optionsRadios2" value="2">女
								</label>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPermanentAddress">户籍地址</label>
							<div class="controls">
								<input type="text" class="consumerinput required"
									id="inputPermanentAddress" name="permanentAddress" placeholder="输入户籍地址">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputnVIP">会员类型</label>
							<div class="controls">
								<select id="inputVIP" name="vip" style="width: 87% ">
									<option value="1">普通会员</option>
									<option value="2">银卡会员</option>
									<option value="3">金卡会员</option>
									<option value="4">白金卡会员</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputCurrentAddress">现在住址</label>
							<div class="controls">
								<input type="text" class="consumerinput required"
									id="inputCurrentAddress" name="address"
									placeholder="输入现在住址">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputEmergencyContact">紧急联系人</label>
							<div class="controls">
								<input type="text" class="consumerinput required"
									id="inputemergencyContact" name="emergencyContact"
									placeholder="输入紧急联系人">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="inputEmergencyTel">紧急联系人电话</label>
							<div class="controls">
								<input type="text" class="consumerinput required digits"
									id="inputEmergencyTel" name="emergencyTel"
									placeholder="输入紧急联系人电话">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="inputBondsman">担保人姓名</label>
							<div class="controls">
								<input type="text" class="consumerinput"
									id="inputBondsman" name="bondsman"
									placeholder="输入担保人姓名">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="inputBondsmanIdentityCard">担保人身份证号</label>
							<div class="controls">
								<input type="text" class="consumerinput digits"
									id="inputBondsmanIdentityCard" name="bondsmanIdentityCard"
									placeholder="输入担保人身份证号">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputBondsmanTel">担保人电话</label>
							<div class="controls">
								<input type="text" class="consumerinput digits"
									id="inputBondsmanTel"" name="bondsmanTel"
									placeholder="输入担保人电话">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputConsumerStatus">客户状态</label>
							<div class="controls">
								<label class="radio inline"> 
									<input type="radio" id="inputConsumerStatus" name="status" id="optionsRadios1" value="1" checked> 正常
								</label> 
								<label class="radio inline"> 
								<input type="radio" id="inputConsumerStatus" name="status" id="optionsRadios2" value="2">黑名单
								</label>
							</div>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-primary" id="inputSubmit"
									name="submit">添加</button>
							</div>
						</div>
					</div>
					
					<div class="span4 well">
						<div class='well titlewell'>
							<label style='text-align: center; font-weight: bold;'>驾照信息</label>
						</div>

						<div class="control-group">
							<label class="control-label" for="inputDrivingLicenseNo">驾驶证号</label>
							<div class="controls">
								<input type="text" class="consumerinput"
									id="inputDrivingLicenseNo" name="licenseNum"
									placeholder="输入驾驶证号">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="inputReceivingArea">领取地区</label>
							<div class="controls">
								<input type="text" class="consumerinput"
									id="inputReceivingArea" name="receivingarea"
									placeholder="请输入领取地区">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="inputnDriverType">驾照类型</label>
							<div class="controls">
								<select id="inputDriverType" name="drivertype" style="width: 87% ">
									<option>请选择</option>
									<option>c1</option>
									<option>b1</option>
									<option>a1</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
