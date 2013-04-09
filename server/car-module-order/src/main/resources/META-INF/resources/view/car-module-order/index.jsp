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

		$("#addOrder").click(function() {
			var orderSearch = $("#orderSearch");
			console.info(orderSearch.css("width"));
			orderSearch.hide(200, function() {
				$("#addOrderDiv").show(200);
			});

		});
		
		$("#phonePromptOrderAdd").typeahead({
			source: function (query, process) {
				consumers = [];
				consumermap = {};
			    var records;
			    $.post('${path }/cmorder/order/conumer.do',{'phone':query},function(data,status){
			    	
			    	records=data.records;
			    	
				    $.each(records, function (i, consumer) {
				    	consumermap[consumer.mobileNumber] = consumer;
				        consumers.push(consumer.mobileNumber);
				    });
				 
				    process(consumers);
			    });
			    
			 
			},
			updater: function (item) {
			    var consumer = consumermap[item];
			    console.info(consumer.mobileNumber);
			    var addOrderform=$('#addOrderform');
			    $.each(consumer, function (key, value) {
			    	
			    	var input=addOrderform.find('[name='+key+']').first();
			    	console.info(input+'name='+key);
			    	//單選框
			    	if(input.attr('type')=='radio'){
			    		
			    		addOrderform.find('[name='+key+'][value='+value+']').first().attr('checked',true);
			    	//下拉	
			    	}else if (input.is('select')){
			    		console.info(input+'name='+key+'--------');
			    		input.find('[value='+value+']').attr('selected','selected');
			    	}
			    	//普通input
			    	else{
			    		console.info(key);
			    		input.attr("value",value);
			    	}
			    	
			    });
			    
			    return item;
			}
		
		
		});
		
		$("#returnSearch").click(function() {
			var addOrderDiv = $("#addOrderDiv");
			addOrderDiv.hide(200, function() {
				$("#orderSearch").show(200);
			});
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
						<div class="span4 well">

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
										name="name" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">担保人</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
						</div>
						<div class="span4 well">
							<div class='well titlewell'>
								<label style='text-align: center; font-weight: bold;'>客户信息</label>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">移动电话</label>
								<div class="controls">
									<input type="text" autocomplete='off' class="orderinput required" id='phonePromptOrderAdd'
										name="mobileNumber" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">客户姓名</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="username" placeholder="客户姓名...">
								</div>
							</div>

							<div class="control-group">
								<label class="control-label" for="inputName">固定电话</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="telephone" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">身份证号</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="identityCardId" placeholder="身份证号...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">年龄</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="age" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">性别</label>
								<div class="controls">
									<div class="row-fluid">
										<div class="span4">
											<label class="radio" style="padding-top: 5px;"> <input
												type="radio" name="gender" value="1" checked="true">男
											</label>
										</div>
										<div class="span3">
											<label class="radio" style="padding-top: 5px;"> <input
												type="radio" name="gender" value="0">女
											</label>
										</div>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">驾驶证号</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="licenseNum" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">住址</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="address" placeholder="车型名称...">
								</div>
							</div>

						</div>
						<div class="span4 well">
							<div class='well titlewell'>
								<label style='text-align: center; font-weight: bold;'>车辆信息</label>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">车型</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">车辆</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">门店</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">牌号</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">颜色</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">发动机号</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">当前油量</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">当前里程</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
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
										<input type="text" class="required"  name="name"
											placeholder="车型名称..."> <span class="add-on">天</span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">还车时间</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">租金标准</label>
								<div class="controls">
									<div class="input-append  orderinput">
										<input type="text" class="required"  name="name"
											placeholder="车型名称..."> <span class="add-on">元/天</span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">异店还车</label>
								<div class="controls">
									<div class="input-append  orderinput">
										<input type="text" class="orderinput required" 
											name="name" placeholder="车型名称..."> <span
											class="add-on">元/次</span>
									</div>
								</div>
							</div>
						</div>
						<div class="span4">
							<div class="control-group">
								<label class="control-label" for="inputName">押金方式</label>
								<div class="controls">
									<select name='email'>
										<option value="1">现金</option>
										<option value="2">刷卡</option>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">押金金额</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">超时标准</label>
								<div class="controls">
									<div class="input-append  orderinput">
										<input type="text" class="orderinput required" 
											name="name" placeholder="车型名称..."> <span
											class="add-on">元/小时</span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">共计金额</label>
								<div class="controls">
									<input type="text" class="orderinput required" 
										name="name" placeholder="车型名称...">
								</div>
							</div>
						</div>
						<div class="span4">
							<div class="control-group">
								<label class="control-label" for="inputName">里程限制</label>
								<div class="controls">
									<div class="input-append  orderinput">
										<input type="text" class="orderinput required" 
											name="name" placeholder="车型名称..."> <span
											class="add-on">公里/日</span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">超里程费</label>
								<div class="controls">
									<div class="input-append  orderinput">
										<input type="text" class="orderinput required" 
											name="name" placeholder="车型名称..."> <span
											class="add-on">元/公里</span>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="inputName">上门送车</label>
								<div class="controls">
									<div class="input-append  orderinput">
										<input type="text" class="orderinput required" 
											name="name" placeholder="车型名称..."> <span
											class="add-on">元/次</span>
									</div>
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
