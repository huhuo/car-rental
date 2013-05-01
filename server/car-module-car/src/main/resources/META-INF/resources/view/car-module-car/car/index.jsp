<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="mgrDivId" class="well" style="padding: 0px;">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<div class="btn-group">
					<button name="add" class="btn">添加</button>
					<button name="delete" class="btn">删除</button>
				</div>
				<!-- search box -->
				<div class="pull-right">
					<ul class="nav">
						<li class="dropdown">
							<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">搜索条件<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a class="search-term huhuo-item-selected" keyword="id" paramKey="licencePlate" url="${path }/cmcar/car/typeahead/car.do" href="javascript:void(0)">车牌号</a></li>
								<li><a class="search-term" keyword="carTypeId" paramKey="name" url="${path }/cmcar/car/typeahead/cartype.do" href="javascript:void(0)">车型名称</a></li>
								<li><a class="search-term" keyword="storeId" paramKey="name" url="${path }/cmcar/car/typeahead/store.do" href="javascript:void(0)">所属门店名称</a></li>
								<li><a class="search-term" keyword="warehouseId" paramKey="name" url="${path }/cmcar/car/typeahead/store.do" href="javascript:void(0)">入库门店名称</a></li>
								<li><a class="search-term" keyword="status" paramKey="dictDisplayName" url="${path }/cmcar/car/typeahead/dict.do" href="javascript:void(0)">车辆状态</a></li>
							</ul>
						</li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;" 
						action="${path }/cmcar/car/condition/get.do">
						<input type="hidden" name="page.pageNo" value="1">
						<input type="hidden" name="page.limit" value="10">
						<input type="text" class="span6 search-query" placeholder="车牌号">
						<input type="hidden" id="keyword" name="licencePlate">
						<button type="submit" class="btn">search</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="pagediv">
	</div>
</div>

<div id="editDivId">
</div>

<script type="text/javascript">
$(document).ready(function(){
	// bind click event to drop down component ==> reset search term
	$('.search-term').click(function(event) {
		$('.search-term').each(function(idx, item) {
			$(item).removeClass("huhuo-item-selected");
		});
		var selectedItem = $(this);
		selectedItem.addClass("huhuo-item-selected");
		// remove input
		$('#huhuoForm ul').remove();
		$('#huhuoForm .search-query').remove();
		// add new input
		$('#huhuoForm input[name="page.limit"]').after('<input type="text" class="span6 search-query">');
		var searchInput = $('#huhuoForm').children('.search-query');
		searchInput.val('');
		searchInput.attr('placeholder', selectedItem.html());
		// autofil event listener
		searchInput.autoFill($(this).attr('url'), $(this).attr('paramKey'), 
				$('#huhuoForm'), null, null, null, function(data) {
			$('#keyword').attr('name', selectedItem.attr('keyword'));
			$('#keyword').val(data.id);
			$('#huhuoForm').trigger('submit');
		});
	});
	// bind click event to search button ==> query record by search term
	$('#huhuoForm').huhuoFormPost(function(data, status) {
		if($.isJsonObj(data)) {
			$.huhuoGrowlUI('error occur in server --> ' + data.msg);
		} else {
			$('#pagediv').empty();
			$('#pagediv').append(data);
		}
	});
	console.log($('.search-term.huhuo-item-selected'));
	console.log('---');
	$('#huhuoForm').trigger('submit');
	// add and delete button group event
	var btnGroup = $('#mgrDivId div.navbar div.btn-group');
	btnGroup.children('button[name="add"]').click(function(event) {
		$("#mgrDivId").hide();
		// load element to editDivId
		$("#editDivId").load('${path}/cmcar/car/add-ui.do', {
			aa: 323
		}, function(resp, status, xhReq) {
			$("#editDivId").show(500);
		});
		
	});
	btnGroup.children('button[name="delete"]').click(function(event) {
		var confirm = window.confirm('确定删除？');
		if(confirm) {
			// get item selected
			var ids = new Array();
			$('#pageGridId tbody input').each(function(index, input) {
				if(input.checked) {
					ids.push($(input).parent().next().text());
				}
			});
			// set request to server to delete the record selected
			$.post('${path}/cmcar/car/delete.do', {
				ids: ids
			} , function(data, status, xhReq) {
				$('#huhuoForm').trigger('submit');
			});
		}
	});
	
});
</script>