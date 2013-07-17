<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path"
	scope="application"></c:set>

<style type="text/css">
table.table-hover tbody tr.huhuo-item-selected {
	background-color: #5D89F8;
	color: #FFF;
}

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

<div id="consumerManagerDiv" class="well" style="padding: 0px;">
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
						<li class="dropdown"><a href="javascript:void(0)"
							class="dropdown-toggle" data-toggle="dropdown">搜索条件<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a class="search-term" id="mobileNumber"
									href="javascript:void(0)">手机号</a></li>
								<li><a class="search-term" id="username"
									href="javascript:void(0)">姓名</a></li>
								<li class="divider"></li>
								<li><a class="search-term" id="identityCardId"
									href="javascript:void(0)">身份证号</a></li>
								<li><a class="search-term" id="address"
									href="javascript:void(0)">住址</a></li>
							</ul></li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;" action="${path }/cmconsumer/consumer/condition/get.do">
						<input type="text" class="search-query span6" name="mobileNumber" placeholder="手机号" x-webkit-speech lang="zh-CN">
						<input type="hidden" name="status" value="1">
						<input type="hidden" name="page.pageNo" value="1">
						<input type="hidden" name="page.limit" value="10">
						<button type="submit" class="btn">搜索</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="pagediv">
	</div>
</div>
<div id="consumerEditDivId">
</div>

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
	// add, edit and delete button group event
	var btnGroup = $('#consumerManagerDiv div.navbar div.btn-group');
	btnGroup.children('button[name="add"]').click(function(event) {
		$("#consumerManagerDiv").hide();
		// load element to consumerEditDivId
		$("#consumerEditDivId").load('${path}/cmconsumer/consumer/add-ui.do', {
			aa: 323
		}, function(resp, status, xhReq) {
			$("#consumerEditDivId").show(500);
		});
		
	});
	btnGroup.children('button[name="edit"]').click(function(event) {
		$("#consumerManagerDiv").hide();
		// load element to consumerEditDivId
		
		$("#consumerEditDivId").load('${path}/cmconsumer/consumer/edit-ui.do', {
			aa: 323
		}, function(resp, status, xhReq) {
			$("#consumerEditDivId").show(500);
		});
	});
	btnGroup.children('button[name="delete"]').click(function(event) {
		var confirm = window.confirm('确定删除？');
		if(confirm) {
			// get item selected
			var ids = new Array();
			$('#pagediv tbody input').each(function(index, input) {
				if(input.checked) {
					console.log($(input).parent().next().text());
					ids.push($(input).parent().next().text());
				}
			});
			// set request to server to delete the record selected
			$.post('${path}/cmconsumer/consumer/delete.do', {
				ids: ids
			} , function(data, status, xhReq) {
				$('#huhuoForm').trigger('submit');
			});
		}
	});
	
});
</script>
