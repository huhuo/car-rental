<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">

table.table-hover tbody tr.huhuo-item-selected {
    background-color: #5D89F8;
	color: #FFF;
}


</style>
<table id="consumerPageGridId" class="table table-hover table-condensed">
	<thead>
		<tr>
			<th><input type="checkbox" class="checkbox"></th>
			<th>&nbsp;&nbsp;头像</th>
			<th>会员编号</th>
			<th>会员姓名</th>
			<th>手机号码</th>
			<th>会员状态</th>
			<th>会员积分</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${records }" var="record">
		<tr>
			<td><input type="checkbox" class="checkbox"></td>
			<td>${record.avatar}</td>
			<td>${record.id}</td>
			<td>${record.username}</td>
			<td>${record.mobileNumber}</td>
			<td>${record.blackList}</td>
			<td>${record.integral}</td>
			<td>
				<div class="btn-group">
					<button name="detail" class="btn">查看详情</button>
					<button name="edit" class="btn">修改</button>
					<button class="btn">短信</button>
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
	$(".pagination").myPage(page, '${path }/cmconsumer/consumer/condition/get.do', t, $("#pagediv"), 5);
	// add select css
	$('#consumerPageGridId tbody tr').click(function(event) {
		$(this).toggleClass('huhuo-item-selected');
		$(this).find(':checkbox')[0].checked = !$(this).find(':checkbox')[0].checked;
	});
	$('#consumerPageGridId thead tr :checkbox').click(function(event) {
		$('#consumerPageGridId tbody :checkbox').each(function(index, element) {
			element.checked = $('#consumerPageGridId thead tr :checkbox')[0].checked;
			if(element.checked) {
				$('#consumerPageGridId tbody tr').addClass('huhuo-item-selected');
			} else {
				$('#consumerPageGridId tbody tr').removeClass('huhuo-item-selected');
			}
		});
	});
	
	// add event to edit button
	$('#consumerPageGridId tbody button[name="detail"]').click(function(event) {
		var selectedId = $(this).parent().parent().parent().children().slice(1, 2).text();
		$("#consumerEditDivId").load('${path}/cmconsumer/consumer/detail.do', {
			id: selectedId
		}, function(resp, status, xhReq) {
			$("#consumerManagerDiv").hide();
			$("#consumerEditDivId").show(500);
		});
	});
	$('#consumerPageGridId tbody button[name="edit"]').click(function(event) {
		var selectedId = $(this).parent().parent().parent().children().slice(1, 2).text();
		$("#consumerEditDivId").load('${path}/cmconsumer/consumer/edit-ui.do', {
			id: selectedId
		}, function(resp, status, xhReq) {
			$("#consumerManagerDiv").hide();
			$("#consumerEditDivId").show(500);
		});
	});
});
</script>
