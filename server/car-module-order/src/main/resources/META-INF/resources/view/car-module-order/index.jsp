<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
.form-horizontal .control-label {
	width: 25%;
}

.form-horizontal .controls {
	margin-left: 27%;
}

input.orderinput {
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
		$("#addOrderDiv").hide();
		//生成下拉框
		$.post("${path }/cmorder/order/carType.do",null,function(data,status){
			if (status == 'success') {
				var records=data.records;
				var carTypeDom=$("#addOrderCarType");
			  	$.each(records, function (i, carType) {
			  		carTypeDom.append("<option value='"+carType.id+"'>"+carType.name+"</option>");
			    });
				
				
			} else {
				$.huhuoGrowlUI("查询车型数据错误");
			}
		});
		
		
		$("#addOrder").click(function() {
			var orderSearch = $("#orderSearch");
			console.info(orderSearch.css("width"));
			orderSearch.hide(200, function() {
				$("#addOrderDiv").show(200);
			});

		});
		
		
		
		$("#addOrderDays").change(function(){
			  var days=$(this).val();
			  
			  days=Number(days);
			  
			  if(days!=NaN){
				  
				  var myDate=new Date();
				  
				  var times=myDate.getTime();
				  
				  times=times+days*1000*60*60*24;
				  
				  var tarDate=new Date(times);
				  
				  var timeStr=tarDate.format('yyyy-MM-dd hh:mm:ss');
				  console.info(timeStr);
				  
				  $("#addOrderRetTime").val(timeStr);
				  
			  }
			  
			  
			
			  $("#addOrderRetTime").css("background-color","#FFFFCC");
		});
		
		//根据对应的手机号，查询对应的用户信息并且填充
		$("#phonePromptOrderAdd").autoFill("${path }/cmorder/order/conumer.do","mobileNumber",$("#addOrderform"),null,"consumer");
		
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
		
		$("#returnSearch").click(function() {
			var addOrderDiv = $("#addOrderDiv");
			addOrderDiv.hide(200, function() {
				$("#orderSearch").show(200);
			});
		});
		
		$('#huhuoFormPost').huhuoFormPost(function(){
			
			
			
		});

		//指定搜索表单刷新的div元素
		$('#huhuoForm').huhuoFormRefushDiv($("#orderTableloadDiv"));

		// bind click event to drop down component ==> reset search term
		$('.search-term').click(function(event) {
			$('.search-term').each(function(idx, item) {
				$(item).removeClass("huhuo-item-selected");
			});
			$(this).addClass("huhuo-item-selected");
			var searchInput = $('#huhuoForm').children('.search-query');
			searchInput.attr('placeholder', $(this).html());
			searchInput.attr('name', $(this).attr('valuename'));
		});
		//指定div去刷新对应的table页面
		$("#orderTableloadDiv").divBlickLoad("${path }/cmorder/order/get.do");
		
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
				diffShopReturnFare=Number(rent);
				if(diffShopReturnFare==NaN){
					diffShopReturnFare=0;
				}
			}
			
			var carSendFare=0;  
			if($("#addOrderform").find("[name='order.isCarSend']").first()[0].checked){  
				carSendFare=$("#addOrderform").find("[name='chargeStandard.carSendFare']").first().val();
				carSendFare=Number(rent);
				if(carSendFare==NaN){
					carSendFare=0;
				}
			}
			  
			var totalPrice=	days *rent+diffShopReturnFare+carSendFare;
			  
			
			
			$("#addOrderform").find("[name='order.totalPrice']").first().val(totalPrice);  
		};

	});
</script>

<div style="overflow: hidden; position: relative;">

	<div id="addOrderDiv" style="position: relative">
		<button id="returnSearch" class="btn" style="margin-bottom: 5px;">返回</button>
		<form id='addOrderform' class="form-horizontal"
			action="${path}/cmorder/order/addorder.do">
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
									<input type="text" class="orderinput required" 
										name="consumer.consumerStatus" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">担保人</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="consumer.bondsmanIdentityCard" placeholder="车型名称...">
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
									<input type="text" class="orderinput required" 
										name="consumer.username" placeholder="客户姓名...">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="inputName">固定电话</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="consumer.telephone" placeholder="固定电话...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">身份证号</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="consumer.identityCardId" placeholder="身份证号...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">年龄</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="consumer.age" placeholder="年龄...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">性别</label>
								<div class="controls">
									<div class="row-fluid">
										<div class="span4">
											<label class="radio" style="padding-top: 5px;"> <input
												type="radio" name="consumer.gender" value="1" checked="true">男
											</label>
										</div>
										<div class="span3">
											<label class="radio" style="padding-top: 5px;"> <input
												type="radio" name="consumer.gender" value="2">女
											</label>
										</div>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">驾驶证号</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="consumer.licenseNum" placeholder="驾驶证号...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">住址</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="consumer.address" placeholder="车型名称...">
								</div>
							</div>

						</div>
						<div class="span4 well"  style="min-height:495px;">
							<div class='well titlewell'>
								<label style='text-align: center; font-weight: bold;'>车辆信息</label>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">牌号</label>
								<div class="controls">
									<input type="text" class="orderinput required" id="licencePlatePromptOrderAdd"
										name="car.licencePlate" placeholder="牌号...">
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
									<input type="text" class="orderinput required" 
										name="car.storeName" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">颜色</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="car.color" placeholder="颜色...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">发动机号</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="car.engineNo" placeholder="发动机号...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">当前油量</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="car.oilMass" placeholder="当前油量...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">当前里程</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
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
									<input type="text" class="orderinput required" id='addOrderRetTime' 
										name="order.carPlanRetTime" placeholder="还车时间...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">租金标准</label>
								<div class="controls">
									<div class="input-append  orderinput">
										<input type="text" class="required"  name="chargeStandard.rent"
											placeholder="租金标准..."> <span class="add-on">元/天</span>
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
									<input type="checkbox" name="order.isDiffShopReturn" value="0">
								</div>
							</div>
						</div>
						<div class="span4">
							<div class="control-group">
								<label class="control-label" for="inputName">押金方式</label>
								<div class="controls">
									<select name='order.rentalType'>
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
										<input type="text" class="orderinput required" 
											name="order.totalPrice" placeholder="共计金额..."><span
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
									<input type="checkbox" name="order.isCarSend" value="0">
								</div>
							</div>
						</div>

					</div>
					<div class="control-group">
						<label class="control-label" for="inputName" style="width: 8%;">备注</label>
						<div class="controls" style="margin-left: 8.7%;">
							<textarea class="orderinput required" name="name"
								style="height: 51px; width: 95%;" placeholder="车型名称..."></textarea>
						</div>
					</div>
					<button class='span4 offset4' type="submit" class="btn">提交</button>
				</div>
			</div>

		</form>
	</div>


	<div id="orderSearch" class="well"
		style="padding: 0px; position: relative">
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<div class="btn-group">
						<button id="addOrder" class="btn">添加订单</button>
						<button class="btn">删除订单</button>
					</div>
					<!-- search box -->
					<div class="pull-right">
						<ul class="nav">
							<li class="dropdown"><a href="javascript:void(0)"
								class="dropdown-toggle" data-toggle="dropdown">搜索条件<b
									class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a class="search-term huhuo-item-selected"
										valuename="condition.opt.phone" href="javascript:void(0)">手机号</a></li>
									<li><a class="search-term"
										valuename="condition.opt.orderNo" href="javascript:void(0)">订单编号</a></li>
									<li class="divider"></li>
									<li><a class="search-term" valuename="condition.opt.name"
										href="javascript:void(0)">姓名</a></li>
									<li class="divider"></li>
									<li><a class="search-term" id="seating"
										valuename="condition.opt.cardId" href="javascript:void(0)">身份证号</a></li>
									<li><a class="search-term" id="tankCapacity"
										valuename="condition.opt.address" href="javascript:void(0)">住址</a></li>
								</ul></li>
						</ul>
						<form id="huhuoForm" class="navbar-form" style="width: 400px;"
							action="${path }/cmorder/order/get.do">
							<input type="text" class="search-query span6"
								name="condition.opt.phone" placeholder="手机号">
							<button type="submit" class="btn">search</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="orderTableloadDiv"></div>
	</div>
</div>
