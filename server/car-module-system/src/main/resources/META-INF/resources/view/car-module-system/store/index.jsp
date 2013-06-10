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
			searchInput.autoFill(selectedItem.attr('url'), selectedItem.attr('paramKey'), 
					$('#huhuoForm'), null, null, null, function(data) {
				$('#keyword').attr('name', selectedItem.attr('keyword'));
				if(selectedItem.attr('keyword') == 'status') {
					$('#keyword').val(data.dictKey);
				} else {
					$('#keyword').val(data.id);
				}
				$('#huhuoForm').trigger('submit');
			});
			$('#keyword').val('');	// reset keyword value
			// when the search input value is empty, then clear up the keyword param's value
			searchInput.focusout(function(event) {
				if(searchInput.val() == '') {
					$('#keyword').val('');
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
		function check() {
			var ret = false;
			$("#storePageGridId tbody input").each(function(index,element){
					if($("#storePageGridId tbody input:checked").size()==0) {
						window.confirm('请选择要一个要操作的对象？');
						$("#storeMgrDivId").show();
						$('#storeEditDivId').hide();
						ret = false;
						return false;
					}else if ($("#storePageGridId tbody input:checked").size()>0) {
						ret = true;
						return true;
					}
				}
			);
			return ret;
		}
		$('#huhuoForm').trigger('submit');
		// add, edit and delete button group event
		var btnGroup = $('#storeMgrDivId div.navbar div.btn-group');
		btnGroup.children('button[name="add"]').click(function(event) {
			$("#storeMgrDivId").hide();
			$("#storeEditDivId").show();
			// load element to cartypeEditDivId
			$("#storeEditDivId").load('${path}/cmsystem/store/add-ui.do');
			
		});
		btnGroup.children('button[name="delete"]').click(function(event) {
			var ret = check();
			if(ret){
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
								<li><a class="search-term huhuo-item-selected" keyword="id" paramKey="name" url="${path }/cmsystem/store/typeahead/store.do" href="javascript:void(0)">分店名</a></li>
								<li><a class="search-term" keyword="managerId" paramKey="username" url="${path }/cmsystem/store/typeahead/user.do" href="javascript:void(0)">店长姓名</a></li>
								<!-- <li><a class="search-term" id="address" href="javascript:void(0)">地址</a></li> -->
							</ul>
						</li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;" action="${path }/cmsystem/store/condition/get.do">
						<input type="hidden" name="status" value="1">
						<input type="hidden" name="page.pageNo" value="1">
						<input type="hidden" name="page.limit" value="10">
						<input type="text" class="span6 search-query" name="name" placeholder="分店名">
						<input type="hidden" id="keyword">
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
