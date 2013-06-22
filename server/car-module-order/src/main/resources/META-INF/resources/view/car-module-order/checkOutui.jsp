<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function() {
	
		$("#addOrderform").find("[name='order.rentalType']").first().find("[value='${order.rentalType }']")[0].selected=true;
		
		$("#returnSearch").click(function() {
			var addOrderDiv = $("#addOrderDiv");
			addOrderDiv.hide(200, function() {
				$("#orderSearch").show(200);
			});
		});
		
		$('#addOrderform').huhuoFormPost(function(data, status){
			if($.isJsonObj(data)) {
				$.huhuoGrowlUI( data.msg);
			} 
			if (status == 'success') {
				var addOrderDiv = $("#addOrderDiv");
				addOrderDiv.hide(200, function() {
					$("#orderSearch").show(200);
				});
				$("#orderTableloadDiv").divBlickLoad("${path }/cmorder/order/get.do");
			}
			
		});
		
		
		
		function getTotalPrice(){
			var days=$("#addOrderform").find("[name='order.orderdays']").first().val();
			days=Number(days);
			if(days==NaN){
				days=0;
			}
			var rent=$("#addOrderform").find("[name='chargeStandard.rent']").first().val();
			rent=Number(rent);
			if(rent==NaN){
				rent=0;
			}
			
			
			
			var diffShopReturnFare=0;  
			if($("#addOrderform").find("[name='order.isDiffShopReturn']").first()[0].checked){
				diffShopReturnFare=$("#addOrderform").find("[name='chargeStandard.diffShopReturnFare']").first().val();
				diffShopReturnFare=Number(diffShopReturnFare);
				if(diffShopReturnFare==NaN){
					diffShopReturnFare=0;
				}
			}
			
			var carSendFare=0;  
			if($("#addOrderform").find("[name='order.isCarSend']").first()[0].checked){  
				carSendFare=$("#addOrderform").find("[name='chargeStandard.carSendFare']").first().val();
				carSendFare=Number(carSendFare);
				if(carSendFare==NaN){
					carSendFare=0;
				}
			}
			  
			var totalPrice=	days *rent+diffShopReturnFare+carSendFare;
			  
			
			
			$("#addOrderform").find("[name='order.totalPrice']").first().val(totalPrice);  
		};

	});
</script>

<button id="returnSearch" class="btn" style="margin-bottom: 5px;">返回</button>
<form id='addOrderform' class="form-horizontal"
	action="${path}/cmorder/order/addorder.do" method="post">
	<div class="row-fluid">
		<div class="span12">
			<div class="row-fluid">
				<div class="span4 well"  style="min-height:495px;">

					<ul class="thumbnails">
						<li class="span12"><a href="javascript:void(0)"
							class="thumbnail"> <img class="img-rounded"
								src="${path }/res/images/status/bazzi.jpg" alt="">
						</a></li>
					</ul>
					<label style='text-align: center; font-weight: bold;'>身份证扫描</label>
					<div class="control-group">
						<label class="control-label" for="inputName">客户状态</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly"
								name="consumer.statusStr" placeholder="客户状态..." value="${consumer.statusStr }">
							<input type="hidden"
								name="consumer.status" value="${consumer.status }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">担保人</label>
						<div class="controls">
							<input type="text" class="orderinput" readonly="readonly" 
								name="consumer.bondsmanIdentityCard" placeholder="担保人..." value="${consumer.bondsmanIdentityCard }">
						</div>
					</div>
				</div>
				<div class="span4 well"   style="min-height:495px;">
					<div class='well titlewell'>
						<label style='text-align: center; font-weight: bold;'>客户信息</label>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">移动电话</label>
						<div class="controls">
							<input type="text" autocomplete='off' class="orderinput required" id='phonePromptOrderAdd'
								name="consumer.mobileNumber" placeholder="移动电话..."  value="${consumer.mobileNumber }">
								<input type="hidden"
								name="consumer.id" value="${consumer.id }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">客户姓名</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly" 
								name="consumer.username" placeholder="客户姓名..." value="${consumer.username }">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputName">固定电话</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly"
								name="consumer.telephone" placeholder="固定电话..." value="${consumer.telephone }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">身份证号</label>
						<div class="controls">
							<input type="text" class="orderinput" readonly="readonly"
								name="consumer.identityCardId" placeholder="身份证号..."  value="${consumer.identityCardId }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">年龄</label>
						<div class="controls">
							<input type="text" class="orderinput" readonly="readonly"
								name="consumer.age" placeholder="年龄..." value="${consumer.age }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">性别</label>
						<div class="controls">
							<div class="row-fluid">
								<div class="span4">
									<label class="radio" style="padding-top: 5px;"> <input
										type="radio" name="consumer.gender" readonly="readonly" value="1" ${consumer.gender==1?"checked='true'":"" } >男
									</label>
								</div>
								<div class="span3">
									<label class="radio" style="padding-top: 5px;"> <input
										type="radio" name="consumer.gender" readonly="readonly" value="2" ${consumer.gender==2?"checked='true'":"" }>女
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">驾驶证号</label>
						<div class="controls">
							<input type="text" class="orderinput" readonly="readonly"
								name="consumer.licenseNum" placeholder="驾驶证号..."  value="${consumer.licenseNum }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">住址</label>
						<div class="controls">
							<input type="text" class="orderinput" readonly="readonly"
								name="consumer.address" placeholder="住址..." value="${consumer.address }">
						</div>
					</div>

				</div>
				<div class="span4 well"  style="min-height:495px;">
					<div class='well titlewell'>
						<label style='text-align: center; font-weight: bold;'>车辆信息</label>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">车辆</label>
						<div class="controls">
							<select  id='addOrderCar'  style='width: 87%;' disabled="disabled">
								<option value='${car.id }'>${carType.name }  ${car.licencePlate }</option>
							</select>
								<input type="hidden"
								name="car.id" value="${car.id }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">车型</label>
						<div class="controls">
							<select  id='addOrderCarType' name='carType.id' style='width: 87%;' disabled="disabled">
								<option value="${carType.id }">${carType.name }</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">门店</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly" 
								name="car.storeName" placeholder="门店..."  value="${car.store.name }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">颜色</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly" 
								name="car.color" placeholder="颜色..."  value="${car.colorDict.disp }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">发动机号</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly" 
								name="car.engineNo" placeholder="发动机号..."  value="${car.engineNo }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">当前油量</label>
						<div class="controls">
							<input type="text" class="orderinput required"  readonly="readonly" 
								name="car.oilMass" placeholder="当前油量..."  value="${car.oilMass }">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">当前里程</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly" 
								name="car.drivedKilometer" placeholder="当前里程..."  value="${car.drivedKilometer }">
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class='well titlewell'>
				<label style='text-align: center; font-weight: bold;'>租赁信息</label>
			</div>
			<div class="row-fluid">
				<div class="span4">
					<div class="control-group">
						<label class="control-label" for="inputName">出租天数</label>
						<div class="controls">
							<div class="input-append orderinput">
								<input type="text" class="required"  name="order.orderdays" id='addOrderDays'
									placeholder="出租天数..." value='1' > <span class="add-on">天</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">还车时间</label>
						<div class="controls">
							<input type="text" class="orderinput required" id='addOrderRetTime' readonly="readonly" 
								name="order.carPlanRetTime" placeholder="还车时间..."  value='${order.carPlanRetTime }' >
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">租金标准</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="required"  name="chargeStandard.rent"
									placeholder="租金标准..." value='0'  value='${order.rent }'> <span class="add-on">元/天</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">异店还车</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required" 
									name="chargeStandard.diffShopReturnFare" placeholder="异店还车..." value="${order.diffShopReturnFare }"> <span
									class="add-on">元/次</span>
							</div>
							<input type="checkbox" name="order.isDiffShopReturn" value="true" ${order.isDiffShopReturn?"checked":"" }>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="control-group">
						<label class="control-label" for="inputName">押金方式</label>
						<div class="controls">
							<select name='order.rentalType' >
								<option value="1">现金</option>
								<option value="2">刷卡</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">押金金额</label>
						<div class="controls">
							<input type="text" class="orderinput required" 
								name="chargeStandard.deposit" placeholder="押金金额..." value='${order.deposit }'>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">超时标准</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.overTimeFare" placeholder="超时标准..." value='${order.overTimeFare }'> <span
									class="add-on">元/小时</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">共计金额</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required" readonly="readonly"
									name="order.totalFee" placeholder="共计金额..." value='${order.totalFee }'><span
									class="add-on">元</span>
									<button id='countButton' class="btn btn-success" type="button">计算</button>
							</div>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="control-group">
						<label class="control-label" for="inputName">里程限制</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.mileageLimits" placeholder="里程限制..."  value='${order.mileageLimits }'> <span
									class="add-on">公里/日</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">超里程费</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.overMileageFare" placeholder="超里程费..."   value='${order.overMileageFare }'> <span
									class="add-on">元/公里</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">上门送车</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.carSendFare" placeholder="上门送车..."  value='${order.carSendFare }'> <span
									class="add-on">元/次</span>
							</div>
							<input type="checkbox" name="order.isCarSend" value="true" ${order.isCarSend?"checked":"" }>
						</div>
					</div>
				</div>

			</div>
			<div class="control-group">
				<label class="control-label" for="inputName" style="width: 8%;">备注</label>
				<div class="controls" style="margin-left: 8.7%;">
					<textarea class="orderinput required" name="order.remark"
						style="height: 51px; width: 95%;" placeholder="备注..."   value='${order.remark }'></textarea>
				</div>
			</div>
			
			
			<div class='well titlewell'>
				<label style='text-align: center; font-weight: bold;'>结算信息</label>
			</div>
			<div class="row-fluid">
				<div class="span4">
					<div class="control-group">
						<label class="control-label" for="inputName">开始时间</label>
						<div class="controls">
							<div class="input-append orderinput">
								<input type="text" class="required"  name="order.orderdays" id='addOrderDays'
									placeholder="出租天数..." value='1' > <span class="add-on">天</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">订单时间</label>
						<div class="controls">
							<input type="text" class="orderinput required" id='addOrderRetTime' readonly="readonly" 
								name="order.carPlanRetTime" placeholder="还车时间..."  value='${order.carPlanRetTime }' >
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">实际时间</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="required"  name="chargeStandard.rent"
									placeholder="租金标准..." value='0'  value='${order.rent }'> <span class="add-on">元/天</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">超出天数</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required" 
									name="chargeStandard.diffShopReturnFare" placeholder="异店还车..." value="${order.diffShopReturnFare }"> <span
									class="add-on">元/次</span>
							</div>
							<input type="checkbox" name="order.isDiffShopReturn" value="true" ${order.isDiffShopReturn?"checked":"" }>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="control-group">
						<label class="control-label" for="inputName">初始里程</label>
						<div class="controls">
							<select name='order.rentalType' >
								<option value="1">现金</option>
								<option value="2">刷卡</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">结束里程</label>
						<div class="controls">
							<input type="text" class="orderinput required" 
								name="chargeStandard.deposit" placeholder="押金金额..." value='${order.deposit }'>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">最大里程</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.overTimeFare" placeholder="超时标准..." value='${order.overTimeFare }'> <span
									class="add-on">元/小时</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">超出里程</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required" readonly="readonly"
									name="order.totalFee" placeholder="共计金额..." value='${order.totalFee }'><span
									class="add-on">元</span>
									<button id='countButton' class="btn btn-success" type="button">计算</button>
							</div>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="control-group">
						<label class="control-label" for="inputName">上门送车</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.mileageLimits" placeholder="里程限制..."  value='${order.mileageLimits }'> <span
									class="add-on">公里/日</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">异店换车</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.overMileageFare" placeholder="超里程费..."   value='${order.overMileageFare }'> <span
									class="add-on">元/公里</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">上门送车</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.carSendFare" placeholder="上门送车..."  value='${order.carSendFare }'> <span
									class="add-on">元/次</span>
							</div>
							<input type="checkbox" name="order.isCarSend" value="true" ${order.isCarSend?"checked":"" }>
						</div>
					</div>
				</div>

			</div>
			<button class='span4 offset4' type="submit" class="btn">结账</button>
		</div>
	</div>

</form>