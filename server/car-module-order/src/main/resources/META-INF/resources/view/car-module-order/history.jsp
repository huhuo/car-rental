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
		$("#orderTableloadDiv").divBlockLoad("${path }/cmorder/order/historyordertable.do");
		
		
		
		

	});
</script>

<div style="overflow: hidden">

	<div id="orderSearch" class="well"
		style="padding: 0px">
		<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<!-- search box -->
					<div class="pull-right">
						<ul class="nav">
							<li class="dropdown"><a href="javascript:void(0)"
								class="dropdown-toggle" data-toggle="dropdown">搜索条件<b
									class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a class="search-term huhuo-item-selected"
										valuename="opt.phone" href="javascript:void(0)">手机号</a></li>
									<li><a class="search-term"
										valuename="opt.orderNo" href="javascript:void(0)">订单编号</a></li>
									<li class="divider"></li>
									<li><a class="search-term" valuename="opt.name"
										href="javascript:void(0)">姓名</a></li>
									<li class="divider"></li>
								</ul></li>
						</ul>
						<form id="huhuoForm" class="navbar-form" style="width: 400px;"
							action="${path }/cmorder/order/historyordertable.do">
							<input type="text" class="search-query span6"
								name="opt.phone" placeholder="手机号">
							<button type="submit" class="btn">search</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="orderTableloadDiv"></div>
	</div>
</div>
