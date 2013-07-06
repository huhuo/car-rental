<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		//生成车型下拉框
		$.post("${path }/cmorder/order/carType.do",null,function(data,status){
			if (status == 'success') {
				var records=data.records;
				ordercartypemap={};
				var carTypeDom=$("#addOrderCarType");
			  	$.each(records, function (i, carType) {
			  		ordercartypemap[carType.id]=carType;
			  		carTypeDom.append("<option value='"+carType.id+"'>"+carType.name+"</option>");
			    });
				
				
			} else {
				$.huhuoGrowlUI("查询车型数据错误");
			}
		});
		$("#addOrderCarType").change(function(){
			createCarOpt();
		});
		
		createCarOpt();
		//生成车辆下拉框
		function createCarOpt(){
			$.post("${path }/cmorder/order/car.do",{'carTypeId':$("#addOrderCarType").val()},function(data,status){
				if (status == 'success') {
					var records=data.records;
					ordercarmap = {};
					var carDom=$("#addOrderCar");
					carDom.empty();
					carDom.append("<option value=''></option>");
				  	$.each(records, function (i, car) {
				  		ordercarmap[car.id]=car;
				  		carDom.append("<option value='"+car.id+"'>"+ordercartypemap[''+car.carTypeId].name+"  "+car.licencePlate+"</option>");
				    });
					
					
				} else {
					$.huhuoGrowlUI("查询车型数据错误");
				}
			});
		};
		
		
		$("#addOrderCar").change(function(){
			var val=$(this).val();
			var car=ordercarmap[''+val];
			if(val!=null&&val!=''){
				 $.setFormValue(car,$("#addOrderform"),"car");
				$("#addOrderform").find("[name='carType.id']").first().find('[value='+car.carTypeId+']')[0].selected=true;
				$("#addOrderform").find("[name='car.colorDict.disp']").first().attr('value',car.colorDict.disp);
				var carType=ordercartypemap[''+car.carTypeId];
				//查询车辆附属付费信息
				$.post("${path }/cmorder/order/chargeStandard.do",{chargeStandardId:carType.chargeStandardId},function(data,status){
					if (status == 'success') {
						var records=data.records;
						if(records!=null&&records.length>0){
							var record =records[0];
							 $.setFormValue(record,$("#addOrderform"),"chargeStandard");
						}
					} else {
						$.huhuoGrowlUI("查询车辆附属付费信息失败");
					}
				});
				

				//查询车辆所属门店
				$.post("${path }/cmorder/order/store.do",{storeId:car.storeId},function(data,status){
					if (status == 'success') {
						var records=data.records;
						if(records!=null&&records.length>0){
							var record =records[0];
							$("#addOrderform").find("[name='car.storeName']").first().attr('value',record.name);
						}
					} else {
						$.huhuoGrowlUI("查询车辆所属门店失败");
					}
				});
			
			}
		});
		
		changeTime();
		
		$("#addOrderDays").keyup(function(){
			changeTime();
		});
		
		function changeTime(){
			  var days=$("#addOrderDays").val();
			  days=Number(days);
			  if(days!=NaN){
				  
				  var myDate=new Date();
				  
				  var times=myDate.getTime();
				  
				  times=times+days*1000*60*60*24;
				  
				  var tarDate=new Date(times);
				  
				  var timeStr=tarDate.format('yyyy-MM-dd hh:mm:ss');
				  
				  $("#addOrderRetTime").val(timeStr);
				  
			  }
			  $("#addOrderRetTime").css("background-color","#FFFFCC");
		}
		
		//根据对应的手机号，查询对应的用户信息并且填充
		$("#phonePromptOrderAdd").autoFill("${path }/cmorder/order/conumer.do","mobileNumber",$("#addOrderform"),null,"consumer");
		
		
		
		
		
		/*
		var carparams=function(params){
			params['carTypeId']=$("#addOrderCarType").val();
			return params;
		};
		
		//添加输入车牌号自动填充对应内容
		$("#licencePlatePromptOrderAdd").autoFill("${path }/cmorder/order/car.do","licencePlate",$("#addOrderform"),carparams,"car",null,function(item){
			//查询车型
			$.post("${path }/cmorder/order/carType.do",{carTypeId:item.carTypeId},function(data,status){
				if (status == 'success') {
					var records=data.records;
					console.info(records);
					if(records!=null&&records.length>0){
						var record =records[0];
						$("#addOrderform").find("[name='carType.id']").first().find('[value='+record.id+']')[0].selected=true;
						
						
						//查询车辆附属付费信息
						$.post("${path }/cmorder/order/chargeStandard.do",{chargeStandardId:record.chargeStandardId},function(data,status){
							if (status == 'success') {
								var records=data.records;
								console.info(records);
								if(records!=null&&records.length>0){
									var record =records[0];
									 $.setFormValue(record,$("#addOrderform"),"chargeStandard");
								}
							} else {
								$.huhuoGrowlUI("查询车辆附属付费信息失败");
							}
						});
						
					}
				} else {
					$.huhuoGrowlUI("查询车型数据错误");
				}
			});
			
			
			//查询车辆所属门店
			$.post("${path }/cmorder/order/store.do",{storeId:item.storeId},function(data,status){
				if (status == 'success') {
					var records=data.records;
					console.info(records);
					if(records!=null&&records.length>0){
						var record =records[0];
						$("#addOrderform").find("[name='car.storeName']").first().attr('value',record.name);
					}
				} else {
					$.huhuoGrowlUI("查询车辆所属门店失败");
				}
			});
			
			
		});
		*/
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
				$("#orderTableloadDiv").divBlockLoad("${path }/cmorder/order/get.do");
			}
			
		});

		$("#countButton").click(function(){
			
			getTotalPrice();
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
			  
			
			
			$("#addOrderform").find("[name='order.totalFee']").first().val(totalPrice);  
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
								name="consumer.statusStr" placeholder="客户状态...">
							<input type="hidden"
								name="consumer.status">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">担保人</label>
						<div class="controls">
							<input type="text" class="orderinput" readonly="readonly" 
								name="consumer.bondsmanIdentityCard" placeholder="担保人...">
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
								name="consumer.mobileNumber" placeholder="移动电话...">
								<input type="hidden"
								name="consumer.id">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">客户姓名</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly" 
								name="consumer.username" placeholder="客户姓名...">
						</div>
					</div>

					<div class="control-group">
						<label class="control-label" for="inputName">固定电话</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly"
								name="consumer.telephone" placeholder="固定电话...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">身份证号</label>
						<div class="controls">
							<input type="text" class="orderinput" readonly="readonly"
								name="consumer.identityCardId" placeholder="身份证号...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">年龄</label>
						<div class="controls">
							<input type="text" class="orderinput" readonly="readonly"
								name="consumer.age" placeholder="年龄...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">性别</label>
						<div class="controls">
							<div class="row-fluid">
								<div class="span4">
									<label class="radio" style="padding-top: 5px;"> <input
										type="radio" name="consumer.gender" readonly="readonly" value="1" checked="true">男
									</label>
								</div>
								<div class="span3">
									<label class="radio" style="padding-top: 5px;"> <input
										type="radio" name="consumer.gender" readonly="readonly" value="2">女
									</label>
								</div>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">驾驶证号</label>
						<div class="controls">
							<input type="text" class="orderinput" readonly="readonly"
								name="consumer.licenseNum" placeholder="驾驶证号...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">住址</label>
						<div class="controls">
							<input type="text" class="orderinput" readonly="readonly"
								name="consumer.address" placeholder="住址...">
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
							<select  id='addOrderCar'  style='width: 87%;'>
								
							</select>
								<input type="hidden"
								name="car.id">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">车型</label>
						<div class="controls">
							<select  id='addOrderCarType' name='carType.id' style='width: 87%;'>
								<option value="">全部</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">门店</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly" 
								name="car.storeName" placeholder="门店...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">颜色</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly" 
								name="car.colorDict.disp" placeholder="颜色...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">发动机号</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly" 
								name="car.engineNo" placeholder="发动机号...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">当前油量</label>
						<div class="controls">
							<input type="text" class="orderinput required"  readonly="readonly" 
								name="car.oilMass" placeholder="当前油量...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">当前里程</label>
						<div class="controls">
							<input type="text" class="orderinput required" readonly="readonly" 
								name="car.drivedKilometer" placeholder="当前里程...">
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
								name="order.carPlanRetTime" placeholder="还车时间...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">租金标准</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="required"  name="chargeStandard.rent"
									placeholder="租金标准..." value='0'> <span class="add-on">元/天</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">异店还车</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required" 
									name="chargeStandard.diffShopReturnFare" placeholder="异店还车..."> <span
									class="add-on">元/次</span>
							</div>
							<input type="checkbox" name="order.isDiffShopReturn" value="true">
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
								name="chargeStandard.deposit" placeholder="押金金额...">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">超时标准</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.overTimeFare" placeholder="超时标准..."> <span
									class="add-on">元/小时</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">共计金额</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required" readonly="readonly"
									name="order.totalFee" placeholder="共计金额..."><span
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
									name="chargeStandard.mileageLimits" placeholder="里程限制..."> <span
									class="add-on">公里/日</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">超里程费</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.overMileageFare" placeholder="超里程费..."> <span
									class="add-on">元/公里</span>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputName">上门送车</label>
						<div class="controls">
							<div class="input-append  orderinput">
								<input type="text" class="orderinput required"
									name="chargeStandard.carSendFare" placeholder="上门送车..."> <span
									class="add-on">元/次</span>
							</div>
							<input type="checkbox" name="order.isCarSend" value="true">
						</div>
					</div>
				</div>

			</div>
			<div class="control-group">
				<label class="control-label" for="inputName" style="width: 8%;">备注</label>
				<div class="controls" style="margin-left: 8.7%;">
					<textarea class="orderinput required" name="order.remark"
						style="height: 51px; width: 95%;" placeholder="备注..."></textarea>
				</div>
			</div>
			<button class='span4 offset4' type="submit" class="btn">提交</button>
		</div>
	</div>

</form>