<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
table.table-hover tbody tr.huhuo-item-selected {
	background-color: #5D89F8;
	color: #FFF;
}
</style>
<table id="pageGridId" class="table table-hover table-condensed">
	<thead>
		<tr>
			<th><input type="checkbox" class="checkbox"></th>
			<th>序号</th>
			<th>用户名</th>
			<th>手机号码</th>
			<th>住址</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${records }" var="record">
		<tr>
			<td><input type="checkbox" class="checkbox"></td>
			<td>${record.id}</td>
			<td>${record.username}</td>
			<td>${record.mobileNumber}</td>
			<td>${record.address}</td>
		</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="9">
				<div class="pagination pagination-centered" style="margin-bottom: 0px;"></div>
			</td>
		</tr>
	</tfoot>
</table>

<script type="text/javascript">
$(function() {
	// 绑定标签元素。设置当前页，页面数据条数，总数，要访问的url，对应的参数，点击标签时刷新的div，标签数
	var page = JSON.parse('${page}');
	var t = JSON.parse('${t}');
	$(".pagination").myPage(page, '${path }/cmbiz/ms/condition/get.do', t, $("#pagediv"), 5);
	// add select css
	$('#pageGridId tbody tr').click(function(event) {
		$(this).toggleClass('huhuo-item-selected');
		if ($(event.target).attr("type") != "checkbox") {
			$(this).find(':checkbox')[0].checked = !$(this).find(':checkbox')[0].checked;
		}
	});
	// add select event
	$('#pageGridId thead tr :checkbox').click(function(event) {
		$('#pageGridId tbody :checkbox').each(function(index, element) {
			element.checked = $('#pageGridId thead tr :checkbox')[0].checked;
			if(element.checked) {
				$('#pageGridId tbody tr').addClass('huhuo-item-selected');
			} else {
				$('#pageGridId tbody tr').removeClass('huhuo-item-selected');
			}
		});
	});
});
</script>
