<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">

 $(document).ready(function(){
	$("#addOrderDiv").hide();
	
	$("#addOrder").click(function(){
		var orderSearch= $("#orderSearch");
		console.info(orderSearch.css("width"));
		orderSearch.animate({
			marginLeft:orderSearch.css("width")+""
		},2000,function(){
			orderSearch.hide();
			$("#addOrderDiv").show();
		});
	});
	$("#returnSearch").click(function(){
		var addOrderDiv= $("#addOrderDiv");
		addOrderDiv.animate({
			marginLeft:addOrderDiv.css("width")+""
		},2000,function(){
			addOrderDiv.hide();
			$("#orderSearch").show();
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

<div style="overflow:hidden;position:relative ;">
	
<div id="addOrderDiv" style="position:relative">
<button id="returnSearch" class="btn">返回</button>
</div>
	
	
<div id="orderSearch" class="well" style="padding: 0px;position:relative">
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
								<li><a class="search-term huhuo-item-selected" valuename="condition.opt.phone" 
									href="javascript:void(0)">手机号</a></li>
								<li><a class="search-term" valuename="condition.opt.orderNo"
									href="javascript:void(0)" >订单编号</a></li>
								<li class="divider"></li>
								<li><a class="search-term" valuename="condition.opt.name"
									href="javascript:void(0)" >姓名</a></li>
								<li class="divider"></li>
								<li><a class="search-term" id="seating" valuename="condition.opt.cardId"
									href="javascript:void(0)">身份证号</a></li>
								<li><a class="search-term" id="tankCapacity" valuename="condition.opt.address"
									href="javascript:void(0)">住址</a></li>
							</ul></li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;"
						action="${path }/cmorder/order/get.do">
						<input type="text" class="search-query span6" name="condition.opt.phone"
							placeholder="手机号">
						<button type="submit" class="btn">search</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="orderTableloadDiv">
	</div>
</div>
</div>