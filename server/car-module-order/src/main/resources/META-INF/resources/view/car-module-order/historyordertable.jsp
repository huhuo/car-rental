<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
a.pic {
	border: 1px solid #E9E9E9;
	margin-right: 10px;
	display: inline;
	float: left;
	height: 50px;
	width: 50px;
	color: #3366CC;
	text-decoration: none;
}

img.pic {
	height: 50px;
	width: 50px;
	max-height: 50px;
	max-width: 50px;
	border: medium none;
}

.desc {
	overflow: hidden;
	width: 180px;
	word-wrap: break-word;
	display: inline;
	float: left;
	margin: 0;
	padding: 0;
	color: #3366CC;
	text-decoration: none;
}

a.car-detail {
	display: block;
	line-height: 18px;
	margin-top: -2px;
	max-height: 38px;
	overflow: hidden;
}
</style>
<table id='orderPageGrid' class="table table-hover table-condensed">
	<thead>
		<tr>
			<th>订单号</th>
			<th>车辆</th>
			<th>客户</th>
			<th>创建时间</th>
			<th>还车时间</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${orderPage.records != null }">
			<c:forEach items="${orderPage.records }" var="record">
			<tr>
				<td>${record.id}</td>
				<td><a class="pic"
					href="http://www.baidu.com"
					title="${record.name}" target="_blank" data-spm-anchor-id="a1z09.2.9.70">
						<img class='pic'
						src="${path }/${record.path}/${record.md5} "
						alt="图片暂无">
				</a>
					<div class="desc">
						<a
							href="http://trade.taobao.com/trade/detail/tradeSnap.htm?spm=a1z09.2.9.71.tHHXch&amp;tradeID=287862446464244"
							target="_blank" class="car-detail"
							data-spm-anchor-id="a1z09.2.9.71"> 车型：${record.name} <br>
							牌号：${record.licencePlate} </a>
					</div></td>
				<td>${record.username}(${record.mobileNumber})</td>
				<td>${record.carRentTime}</td>
				<td>${record.carPlanRetTime}</td>
			</tr>
			</c:forEach>
		</c:if>

	</tbody>

</table>
<!-- style="position:fixed;top:150px;left:1100px" -->
<div id="bb" class="pagination pagination-centered"></div>

<script type="text/javascript">
	//绑定标签元素。设置当前页，页面数据条数，总数，要访问的url，对应的参数，点击标签时刷新的div，标签数
	
	
	var condition = JSON.parse('${condition}');
	var orderPage = JSON.parse('${orderPage}');
	$("#bb").myPage(orderPage, "${path }/cmorder/order/historyordertable.do", condition.opt, $("#orderTableloadDiv"), 5);
</script>
