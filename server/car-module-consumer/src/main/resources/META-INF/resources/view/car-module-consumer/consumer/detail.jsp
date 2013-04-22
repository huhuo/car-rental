<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</script>
<form id="addConsumerForm" class="form-horizontal " action="${path }/cmconsumer/consumer/add.do">
	<div class="span2  well" style="min-height: 500px">
		<img src="http://www.baidu.com/img/baidu_jgylogo3.gif" class="img-polaroid"> <br>
		<button style="width: 100px" class="btn">自动识别</button>
	</div>

	<div class="span10  well" style="min-height: 500px">
		<div class="row-fluid ">
			<div class="span5  " style="min-height: 500px">
				<div>
					<label class="btn btn-large btn-block">会员信息</label>
				</div>
				<input type="hidden" name="id" value="${consumer.id }">
				<div class="control-group">
					<label class="control-label" for="inputSeating">会员姓名</label>
					<div class="controls">
						<input type="number" class="required digits" readonly="readonly" 
							id="inputCustomerName" name="username" placeholder="输入会员姓名" value="${consumer.username }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputCellPhone">移动电话</label>
					<div class="controls">
						<input type="number" class="required digits" id="inputCellPhone" readonly="readonly" 
							name="mobileNumber" placeholder="输入移动电话" value="${consumer.mobileNumber }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputTelephone">固定电话</label>
					<div class="controls">
						<input type="number" class="required digits" id="inputTelephone" readonly="readonly" 
							name="telephone" placeholder="输入固定电话" value="${consumer.telephone }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputIDCardNo">身份证号</label>
					<div class="controls">
						<input type="number" class="required digits" id="inputIDCardNo" readonly="readonly" 
							name="identityCardId" placeholder="输入身份证号" >
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="inputBirthday">出生年月</label>
					<div class="controls">
						<input type="number" class="required digits" id="inputBirthday" readonly="readonly" 
							name="brithday" placeholder="输入出生年月" value="${consumer.birthday }">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="inputAge">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄</label>
					<div class="controls">
						<input type="number" class="required digits" id="inputAge" readonly="readonly" 
							name="age" placeholder="输入年龄" value="${consumer.age }">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="inputGender">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
					<div class="controls">
						<label class="radio inline"> <input type="radio"  disabled="disabled"
							id="inputGender" name="gender" id="optionsRadios1" value="1" 
							checked> 男
						</label> <label class="radio inline"> <input type="radio" disabled="disabled"
							id="inputGender" name="gender" id="optionsRadios2" value="2">女
						</label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputPermanentAddress">户籍地址</label>
					<div class="controls">
						<input type="number" class="required digits"  readonly="readonly" 
							id="inputPermanentAddress" name="address" placeholder="输入户籍地址" >
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputIssuingAuthority ">发证机关</label>
					<div class="controls">
						<input type="text" class="required digits"  readonly="readonly" 
							id="inputIssuingAuthority" name="issuingauthority"
							placeholder="输入发证机关">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputnVIP">会员类型</label>
					<div class="controls">
						<select id="inputVIP" name="vip" disabled="disabled" >
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
						<input type="text" class="required digits"  readonly="readonly" 
							id="inputCurrentAddress" name="currentaddress"
							placeholder="输入现在住址" value="${consumer.address }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputEmergencyContact">紧急联系人</label>
					<div class="controls">
						<input type="text" class="required digits"  readonly="readonly" 
							id="inputemergencyContact" name="emergencyContact"
							placeholder="输入紧急联系人" value="${consumer.emergencyContact }">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputEmergencyTel">紧急联系人电话</label>
					<div class="controls">
						<input type="text" class="required digits" id="inputEmergencyTel" readonly="readonly" 
							name="emergencyTel" placeholder="输入紧急联系人电话" value="${consumer.emergencyTel }">
					</div>
				</div>
	
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-primary" id="inputSubmit"
							name="submit">添加</button>
					</div>
				</div>

			</div>
			<div class="span5 " style="min-height: 500px">
				<div>
					<label class="btn btn-large btn-block">驾照信息</label>
				</div>
				<br>
				<div class="control-group">
					<label class="control-label" for="inputDrivingLicenseNo">驾驶证号</label>
					<div class="controls">
						<input type="text" class="required digits" readonly="readonly" 
							id="inputDrivingLicenseNo" name="licenseNum" placeholder="输入驾驶证号" value="${consumer.licenseNum }">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="inputReceivingArea">领取地区</label>
					<div class="controls">
						<input type="text" class="required digits" id="inputReceivingArea" readonly="readonly" 
							name="receivingarea" placeholder="请输入领取地区" >
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="inputnDriverType">驾照类型</label>
					<div class="controls">
						<select id="inputDriverType" name="drivertype"  disabled="disabled">
							<option>c1</option>
							<option>b1</option>
							<option>a1</option>
						</select>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
