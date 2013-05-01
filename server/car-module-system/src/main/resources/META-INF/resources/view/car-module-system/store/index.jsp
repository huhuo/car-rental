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
		$('#huhuoForm').huhuoFormPost(function(data, status) {
			if($.isJsonObj(data)) {
				$.huhuoGrowlUI('error occur in server --> ' + data.msg);
			} else {
				$('#pagediv').empty();
				$('#pagediv').append(data);
			}
		});
		$('#huhuoForm').trigger('submit');
		// add, edit and delete button group event
		var btnGroup = $('#storeMgrDivId div.navbar div.btn-group');
		btnGroup.children('button[name="add"]').click(function(event) {
			$("#storeMgrDivId").hide();
			$("#storeEditDivId").show();
			// load element to cartypeEditDivId
			$("#storeEditDivId").load('${path}/cmsystem/store/add-ui.do');
			
		});
		btnGroup.children('button[name="edit"]').click(function(event) {
			$("#storeMgrDivId").hide();
			// load element to cartypeEditDivId
			
			$("#storeEditDivId").load('${path}/cmsystem/store/edit-ui.do', {
				aa: 323
			}, function(resp, status, xhReq) {
				$("#storeEditDivId").show(500);
			});
		});
		btnGroup.children('button[name="delete"]').click(function(event) {
			var flag = window.confirm('确定要删除吗？');
			if(flag) {
				var ids = new Array();
				$('#storePageGridId tbody input').each(function(index, input) {
					if(input.checked) {
						console.log($(input).parent().next().text());
						ids.push($(input).parent().next().text());
					}
					
				});
				$.post('${path}/cmsystem/store/delete.do',{ids : ids},
						function(data, status, xhReq){
							$('#huhuoForm').trigger('submit');
				});
			}
			console.log('=====delete======');
		});
		
	});
</script>

<script type="text/javascript">
	/****************************
	event in edit panel
	****************************/
	// initialization
	$('#storeEditDivId').hide();
	$('#storeEditDivId form').validate();
	// cartype add page
	$('#storeEditDivId form').huhuoFormPost(function(data, status) {
		$('#storeEditDivId').hide();
		$('#storeMgrDivId').show(500);
		$('#storeMgrDivId form button').trigger("click");
		console.log($('#storeEditDivId form').serialize());
	});
	
</script>

<div id="storeMgrDivId" class="well" style="padding: 0px;">
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
								<li><a class="search-term" id="name" href="javascript:void(0)">名称</a></li>
								<li><a class="search-term" id="address" href="javascript:void(0)">地址</a></li>
							</ul>
						</li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;" action="${path }/cmsystem/store/condition/get.do">
						<input type="hidden" name="status" value="1">
						<input type="hidden" name="page.pageNo" value="1">
						<input type="hidden" name="page.limit" value="10">
						<input type="text" class="span6 search-query" name="name" placeholder="名称">
						<button type="submit" class="btn">search</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="pagediv">
	</div>
</div>

<div id="storeEditDivId">

</div>
