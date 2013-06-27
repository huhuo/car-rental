<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	$(document).ready(function() {
		// validation
		$('#consumerEditDivId form').validate();
		// consumer add page
		$('#consumerEditDivId form button').click(function(event) {
			$('#consumerEditDivId').hide();
			$('#consumerManagerDiv').show(500);
			return false;
		});
	});
	
	$(document).ready(function() {
		$("#returnSearch").click(function() {
			$('#consumerEditDivId').hide();
			$('#consumerManagerDiv').show(500);
		});
	});
</script>
<div style="position: relative">
	<button id="returnSearch" class="btn" style="margin-bottom: 5px;">返回</button>
	<form class="form-horizontal"
		action="${path }/cmconsumer/consumer/add.do">
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
					
					<div class="span4 well">
						<div class='well titlewell'>
							<label style='text-align: center; font-weight: bold;'>客户信息</label>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputSeating">会员姓名</label>
							<div class="controls">
								<input type="text" disabled="disabled" class="consumerinput digits" value="${consumer.username }" 
									id="inputCustomerName" name="username" placeholder="输入会员姓名">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputCellPhone">移动电话</label>
							<div class="controls">
								<input type="number" disabled="disabled" class="consumerinput required digits" id="inputCellPhone"  
									value="${consumer.mobileNumber }" name="mobileNumber" placeholder="输入移动电话">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputTelephone">固定电话</label>
							<div class="controls">
								<input type="number" class="consumerinput required digits" id="inputTelephone" disabled="disabled"  
									value="${consumer.telephone }" name="telephone" placeholder="输入固定电话" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputIdentityCardId">身份证号</label>
							<div class="controls">
								<input type="number" class="consumerinput required digits" id="inputIdentityCardId" 
										disabled="disabled" value="${consumer.identityCardId }"
									name="identityCardId" placeholder="输入身份证号">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputBirthday">出生年月</label>
							<div class="controls">
								<input type="text" class="consumerinput" id="inputBirthday"  disabled="disabled"  value="${xbirthday }"
									name="brithday" placeholder="输入出生年月">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputAge">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄</label>
							<div class="controls">
								<input type="number" class="consumerinput required digits" id="inputAge"  disabled="disabled" value="${consumer.age }" 
									name="age" placeholder="输入年龄">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputGender">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
							<div class="controls">
								<label class="radio inline"> 
									<input type="radio" disabled="disabled" id="inputGender" name="gender" id="optionsRadios1" value="1"  readonly="readonly" checked> 男
								</label> 
								<label class="radio inline"> 
									<input type="radio" disabled="disabled" id="inputGender" name="gender" id="optionsRadios2" value="2">女 
								</label>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputCurrentAddress">现在住址</label>
							<div class="controls">
								<input type="text" class="consumerinput required" value="${consumer.address }"
									id="inputCurrentAddress"   disabled="disabled" name="currentaddress"
									placeholder="输入现在住址">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputPermanentAddress">户籍地址</label>
							<div class="controls">
								<input type="number" class="consumerinput required"  disabled="disabled"  value="${consumer.permanentAddress }"
									id="inputPermanentAddress" name="address" placeholder="输入户籍地址">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputnVIP">会员类型</label>
							<div class="controls">
								<select id="inputVIP" name="vip" style="width: 87% " disabled="disabled">
									<option value="1">普通会员</option>
									<option value="2">银卡会员</option>
									<option value="3">金卡会员</option>
									<option value="4">白金卡会员</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputEmergencyContact">紧急联系人</label>
							<div class="controls">
								<input type="text" class="consumerinput required digits"   disabled="disabled" value="${consumer.emergencyContact }"
									id="inputemergencyContact" name="emergencyContact"
									placeholder="输入紧急联系人">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputEmergencyTel">紧急联系人电话</label>
							<div class="controls">
								<input type="text" class="consumerinput required digits"
									id="inputEmergencyTel" name="emergencyTel" disabled="disabled" value="${consumer.emergencyTel }"
									placeholder="输入紧急联系人电话">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="inputBondsman">担保人姓名</label>
							<div class="controls">
								<input type="text" class="consumerinput" value="${consumer.bondsman }"
									id="inputBondsman" name="bondsman" disabled="disabled" placeholder="输入担保人姓名">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label" for="inputBondsmanIdentityCard">担保人身份证号</label>
							<div class="controls">
								<input type="text" class="consumerinput" value="${consumer.bondsmanIdentityCard }"
									id="inputBondsmanIdentityCard" name="bondsmanIdentityCard"  disabled="disabled" 
									placeholder="输入担保人身份证号">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputBondsmanTel">担保人电话</label>
							<div class="controls">
								<input type="text" class="consumerinput" value="${consumer.bondsmanTel }"
									id="inputBondsmanTel"" name="bondsmanTel"  disabled="disabled" 
									placeholder="输入担保人电话">
							</div>
						</div>
						
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-primary" id="inputSubmit" style="display: none;"
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
								<input type="text" class="consumerinput required digits" 
									id="inputDrivingLicenseNo"   disabled="disabled" name="licenseNum" value="${consumer.licenseNum }"
									placeholder="输入驾驶证号">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="inputReceivingArea">领取地区</label>
							<div class="controls">
								<input type="text" class="consumerinput required digits" 
									id="inputReceivingArea"   disabled="disabled" name="receivingarea" value="${consumer.receiveArea }"
									placeholder="请输入领取地区">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="inputnDriverType">驾照类型</label>
							<div class="controls">
								<select id="inputDriverType" name="drivertype" style="width: 87% " disabled="disabled">
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
