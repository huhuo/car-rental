<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-hover table-condensed">
	<thead>
		<tr>
			<th>序号</th>
			<th>图片</th>
			<th>车型名称</th>
			<th>类别</th>
			<th>座位数</th>
			<th>油箱容量（单位：升）</th>
			<th>可行驶里程数</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${records }" var="record">
		<tr>
			<td>${record.id}</td>
			<td>${record.icon}</td>
			<td>${record.name}</td>
			<td>${record.category}</td>
			<td>${record.seating}</td>
			<td>${record.tankCapacity}</td>
			<td>${record.drivingRange}</td>
			<td>
				<div class="btn-group">
					<button class="btn">查看详情</button>
					<button class="btn">修改</button>
				</div>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<!-- style="position:fixed;top:150px;left:1100px" -->
<div class="pagination pagination-centered"></div>

<script type="text/javascript">
	//绑定标签元素。设置当前页，页面数据条数，总数，要访问的url，对应的参数，点击标签时刷新的div，标签数
	var page = JSON.parse('${page}');
	var t = JSON.parse('${t}');
	$(".pagination").myPage(page, '${path }/cmcar/cartype/condition/get.do', t, $("#pagediv"), 5);
</script>
