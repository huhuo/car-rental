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

<script type="text/javascript">
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
		$('#huhuoForm').huhuoFormPost(function(data, status) {
			if($.isJsonObj(data)) {
				$.huhuoGrowlUI('error occur in server --> ' + data.msg);
			} else {
				$('#pagediv').empty();
				$('#pagediv').append(data);
			}
		});
		$('#huhuoForm').trigger('submit');
		// add and delete button group event
		var btnGroup = $('#cartypeMgrDivId div.navbar div.btn-group');
		btnGroup.children('button[name="add"]').click(function(event) {
			$("#cartypeMgrDivId").hide();
			// load element to cartypeEditDivId
			$("#cartypeEditDivId").load('${path}/cmcar/cartype/add-ui.do', {
				aa: 323
			}, function(resp, status, xhReq) {
				$("#cartypeEditDivId").show(500);
			});
			
		});
		btnGroup.children('button[name="delete"]').click(function(event) {
			var confirm = window.confirm('确定删除？');
			if(confirm) {
				// get item selected
				var ids = new Array();
				$('#cartypePageGridId tbody input').each(function(index, input) {
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

<div id="cartypeMgrDivId" class="well" style="padding: 0px;">
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
								<li><a class="search-term huhuo-item-selected" id="name" href="javascript:void(0)">车型名称</a></li>
								<li><a class="search-term" id="category" href="javascript:void(0)">类别</a></li>
								<li><a class="search-term" id="seating" href="javascript:void(0)">座位数</a></li>
								<li><a class="search-term" id="tankCapacity" href="javascript:void(0)">油箱容量（L）</a></li>
							</ul>
						</li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;" action="${path }/cmcar/cartype/condition/get.do">
						<input type="hidden" name="status" value="1">
						<input type="hidden" name="page.pageNo" value="1">
						<input type="hidden" name="page.limit" value="10">
						<input type="text" class="span6 search-query" name="name" placeholder="车型名称">
						<button type="submit" class="btn">search</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="pagediv">
	</div>
</div>

<div id="cartypeEditDivId">
</div>
