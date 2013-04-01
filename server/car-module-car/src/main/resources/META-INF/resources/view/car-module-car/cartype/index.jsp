<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
div.huhuo-label {
	padding-bottom: 6px;
	padding-top: 6px;
	padding-right: 10px;
	text-align: right;
}
.huhuoyes {
	background-image: url("res/images/status/btn_check_buttonless_on.png");
	background-repeat: no-repeat;
	background-position: 80% center;

}

.form-horizontal .control-label {
	width: 100px;
}
.form-horizontal .controls {
	margin-left: 120px;
}
input, textarea, .uneditable-input {
	width: 266px;
}

select {
	width: 280px;
}

</style>

<script type="text/javascript">
	/****************************
	event in cartype management panel
	****************************/
	$(document).ready(function(){
		// bind click event to drop down component ==> reset search term
		$('.search-term').click(function(event) {
			$('.search-term').each(function(idx, item) {
				$(item).removeClass("huhuo-item-selected");
			});
			$(this).addClass("huhuo-item-selected");
			var searchInput = $('#huhuoForm').children('.search-query');
			searchInput.attr('placeholder', $(this).html());
			searchInput.attr('name', $(this).attr('id'));
		});
		// bind click event to search button ==> query record by search term
//		$("#pagediv tbody").divBlickLoad("${path }/cmorder/order/get.do");
		
		$('#huhuoForm').huhuoFormPost(function(data, status) {
			if($.isJsonObj(data)) {
				$.huhuoGrowlUI('error occur in server --> ' + JSON.parse(data).msg);
			} else {
				$('#pagediv tbody').empty();
				$('#pagediv tbody').append(data);
			}
		});
		// add, edit and delete button group event
		var btnGroup = $('#cartypeMgrDivId div.navbar div.btn-group');
		btnGroup.children('button[name="add"]').click(function(event) {
			$("#cartypeEditDivId :input").val('');
			$("#cartypeMgrDivId").hide();
			$("#cartypeEditDivId").show(500);
		});
		btnGroup.children('button[name="edit"]').click(function(event) {
			console.log('=====edit======');
		});
		btnGroup.children('button[name="delete"]').click(function(event) {
			console.log('=====delete======');
		});
		
	});
</script>

<script type="text/javascript">
	/****************************
	event in edit panel
	****************************/
	// initialization
	$('#cartypeEditDivId').hide();
	$('#cartypeEditDivId').validate();
	// cartype add page
	$('#cartypeEditDivId').huhuoFormPost(function(data, status) {
		$('#cartypeEditDivId').hide();
		$('#cartypeMgrDivId').show(500);
		console.log($('#cartypeEditDivId').serialize());
	}); 
	
	/* $('#submitBtnId').click(function(event) {
		console.log($('#cartypeEditDivId').valid());
	}); */
	
</script>

<div id="cartypeMgrDivId" class="well" style="padding: 0px;">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<div class="btn-group">
					<button name="add" class="btn">添加</button>
					<button name="edit" class="btn">编辑</button>
					<button name="delete" class="btn">删除</button>
				</div>
				<!-- search box -->
				<div class="pull-right">
					<ul class="nav">
						<li class="dropdown">
							<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">搜索条件<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a class="search-term huhuo-item-selected" id="name" href="javascript:void(0)">车型名称</a></li>
								<li><a class="search-term" id="category" href="javascript:void(0)">类别</a></li>
								<li><a class="search-term" id="seating" href="javascript:void(0)">座位数</a></li>
								<li><a class="search-term" id="tankCapacity" href="javascript:void(0)">油箱容量（L）</a></li>
							</ul>
						</li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;" action="${path }/cmcar/cartype/condition/get.do">
						<input type="text" class="span6 search-query" name="name" placeholder="车型名称">
						<button type="submit" class="btn">search</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="pagediv">
		<table class="table table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>序号</th>
					<th>图片</th>
					<th>车型名称</th>
					<th>类别</th>
					<th>座位数</th>
					<th>油箱容量（单位：升）</th>
					<th>可行驶里程数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td>${item.id}</td>
					<td>${item.icon}</td>
					<td>${item.name}</td>
					<td>${item.category}</td>
					<td>${item.seating}</td>
					<td>${item.tankCapacity}</td>
					<td>${item.drivingRange}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- style="position:fixed;top:150px;left:1100px" -->
		<div class="pagination pagination-centered">
			<ul>
				<li><a href="#">?</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">?</a></li>
			</ul>
		</div>
	</div>
</div>

<form id="cartypeEditDivId" class="form-horizontal well" action="${path}/cmcar/cartype/add.do">
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
					<input type="number" class="required" id="inputDeposit" name="deposit" placeholder="押金（xxx元）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPremium">保险费</label>
				<div class="controls">
					<input type="number" class="required" id="inputPremium" name="premium" placeholder="保险费，xxx元/次...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputRent">租金</label>
				<div class="controls">
					<input type="number" class="required" id="inputRent" name="rent" placeholder="租金（xxx元/天）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputMileageLimits">里程限制</label>
				<div class="controls">
					<input type="number" class="required digits" id="inputMileageLimits" name="mileageLimits" placeholder="里程限制（xxx公里/日）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOverMileageFare">超里程费用</label>
				<div class="controls">
					<input type="number" class="required" id="inputOverMileageFare" name="overMileageFare" placeholder="超里程费用（xxx元/公里）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputOverTimeFare">超时费用</label>
				<div class="controls">
					<input type="number" class="required" id="inputOverTimeFare" name="overTimeFare" placeholder="超时费用（xxx元/小时）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputCarSendFare">上门送车费用</label>
				<div class="controls">
					<input type="number" class="required" id="inputCarSendFare" name="carSendFare" placeholder="上门送车费用（xxx元）...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputDiffShopReturnFare">异店结算附加费</label>
				<div class="controls">
					<input type="number" class="required" id="inputDiffShopReturnFare" name="diffShopReturnFare" placeholder="异店结算（还车）附加费（xxx元）...">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button id="submitBtnId" type="submit" class="btn">添加</button>
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
