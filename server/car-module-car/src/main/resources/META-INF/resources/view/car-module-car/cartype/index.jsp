<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
div.huhuo-label {
	padding-bottom: 6px;
	padding-top: 6px;
	padding-right: 10px;
	text-align: right;
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
								<li><a class="search-term huhuo-item-selected" keyword="name" href="javascript:void(0)">车型名称</a></li>
								<li><a class="search-term autofill" keyword="category" paramKey="disp" url="${path }/cmsystem/dict/groups/simple/CUST_CAR_TYPE_CATEGORY.do" href="javascript:void(0)">类别</a></li>
								<li><a class="search-term" keyword="seating" href="javascript:void(0)">座位数</a></li>
								<li><a class="search-term" keyword="tankCapacity" href="javascript:void(0)">油箱容量（L）</a></li>
								
							</ul>
						</li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;" action="${path }/cmcar/cartype/condition/get.do">
						<input type="hidden" name="status" value="1">
						<input type="hidden" name="page.pageNo" value="1">
						<input type="hidden" name="page.limit" value="10">
						<input type="text" class="span6 search-query" placeholder="车型名称">
						<input type="hidden" id="keyword" name="name">
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
			var keyWordHidden = $('#keyword');
			keyWordHidden.attr('name', selectedItem.attr('keyword'));
			if(selectedItem.attr('keyword') == 'category') {
				searchInput.autoFill(selectedItem.attr('url'), selectedItem.attr('paramKey'), 
						$('#huhuoForm'), null, null, null, function(data) {
					keyWordHidden.val(data.key);
					$('#huhuoForm').trigger('submit');
				});
			} else {
				searchInput.change(function(e) {
					keyWordHidden.val($(this).val());
				});
			}
			// reset keyword value
			keyWordHidden.val('');
			// when the search input value is empty, then clear up the keyword param's value
			searchInput.focusout(function(event) {
				if(searchInput.val() == '') {
					keyWordHidden.val('');
				}
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
		$('.search-term.huhuo-item-selected').trigger('click');
		$('#huhuoForm').trigger('submit');
		// add and delete button group event
		var btnGroup = $('#mgrDivId div.navbar div.btn-group');
		btnGroup.children('button[name="add"]').click(function(event) {
			$("#mgrDivId").hide();
			// load element to editDivId
			$("#editDivId").load('${path}/cmcar/cartype/add-ui.do', {
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
						console.log($(input).parent().next().text());
						ids.push($(input).parent().next().text());
					}
				});
				// set request to server to delete the record selected
				$.post('${path}/cmcar/cartype/delete.do', {
					ids: ids
				} , function(data, status, xhReq) {
					$('#huhuoForm').trigger('submit');
				});
			}
		});
		
	});
</script>