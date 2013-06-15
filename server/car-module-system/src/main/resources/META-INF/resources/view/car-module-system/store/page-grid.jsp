<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">

table.table-hover tbody tr.huhuo-item-selected {
    background-color: #5D89F8;
	color: #FFF;
}


</style>
<table id="storePageGridId" class="table table-hover table-condensed">
	<thead>
		<tr>
			<th><input type="checkbox" class="checkbox"></th>
				<th>id</th>
				<th>图          片</th>
				<th>分店名称</th>
				<th>分店地址</th>
				<th>联系电话</th>
				<th>分店经理</th>
				<th>待租车辆</th>
				<th>已租车辆</th>
				<th>相关操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${records}" var="record">
		<tr>
			<td><input type="checkbox" class="checkbox"></td>
			<td>${record.id}</td>
			<td>
				<a href="javascript:void(0)" class="thumbnail"> 
					<img class="img-rounded" style="height: 100px; width: 150px;" src="${record.picture.url}">
				</a>
			</td>
			<td>${record.name}</td>
			<td>${record.address}</td>
			<td>${record.telephone}</td>
			<td>${record.user.username}</td>
			<td>${record.freeNum}</td>
			<td>${record.rentNum}</td>
			<td>
				<div class="btn-group">
					<button  name="detail" class="btn">查看详情</button>
					<button  name="edit" class="btn">修改</button>
				</div>
			</td>
		</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<!-- style="position:fixed;top:150px;left:1100px" -->
		<tr>
			<td colspan="9">
				<div class="pagination pagination-centered" style="margin-bottom: 0px;"></div>
			</td>
		</tr>
	</tfoot>
</table>

<script type="text/javascript">
$(document).ready(function() {
	// 绑定标签元素。设置当前页，页面数据条数，总数，要访问的url，对应的参数，点击标签时刷新的div，标签数
	var page = JSON.parse('${page}');
	var t = JSON.parse('${t}');
	console.info(page);
	$(".pagination").myPage(page, '${path }/cmsystem/store/condition/get.do', t, $("#pagediv"), 5);
	// add select css
	$('#storePageGridId tbody tr').click(function(event) {
		$(this).toggleClass('huhuo-item-selected');
		if ($(event.target).attr("type") != "checkbox") {
			$(this).find(':checkbox')[0].checked = !$(this).find(':checkbox')[0].checked;
		}
		console.log("++++++++++>>>>");
	});
	// add select event
	$('#storePageGridId thead tr :checkbox').click(function(event) {
		$('#storePageGridId tbody :checkbox').each(function(index, element) {
			element.checked = $('#storePageGridId thead tr :checkbox')[0].checked;
			if(element.checked) {
				$('#storePageGridId tbody tr').addClass('huhuo-item-selected');
			} else {
				$('#storePageGridId tbody tr').removeClass('huhuo-item-selected');
			}
		});
	});
	// add event to edit button
	$('#storePageGridId tbody button[name="detail"]').click(function(event) {
		var selectedId = $(this).parent().parent().parent().children().slice(1, 2).text();
		$("#storeEditDivId").load('${path}/cmsystem/store/detail.do', {
			id: selectedId
			
		}, function(resp, status, xhReq) {
			$("#storeMgrDivId").hide();
			$("#storeEditDivId").show(500);
		});
	});
	$('#storePageGridId tbody button[name="edit"]').click(function(event) {
		console.log($(this));
		var selectedId = $(this).parent().parent().parent().children().slice(1, 2).text();
		
		$("#storeEditDivId").load('${path}/cmsystem/store/edit-ui.do', {
			id: selectedId
		}, function(resp, status, xhReq) {
			$("#storeMgrDivId").hide();
			$("#storeEditDivId").show(500);
		});
	});
});
</script>
