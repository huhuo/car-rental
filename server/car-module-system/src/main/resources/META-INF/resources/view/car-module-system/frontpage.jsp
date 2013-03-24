<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path"
	scope="application"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bootstrap 101 Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${path }/res/js/bootstrap/css/bootstrap.css"
	rel="stylesheet" media="screen">
<link href="${path }/res/js/huhuo/huhuo.css" rel="stylesheet" type="text/css">
<script src="${path }/res/js/jquery/jquery.js"></script>
<script src="${path }/res/js/bootstrap/js/bootstrap.js"></script>
<script src="${path }/res/js/jquery/jquery.blockUI.js"></script>
<script src="${path }/res/js/huhuo/huhuo.js"></script>
<style type="text/css">



</style>

<script type="text/javascript">
	$(document).ready(function() {

	});
</script>

</head>
<body>
	<div class="container-fluid">

		<div class="row-fluid show-grid">
			<div class="top" style="padding-top:10px">
				<h3 align="center" style="margin-top:5px;">虎货汽车租赁系统 3.0 beta</h3>
				
				<!--  
				<ul class="breadcrumb" style="opacity:30">
					<li><a href="#">订单管理</a> <span class="divider">/</span></li>
					<li><a href="#">添加客户</a> <span class="divider">/</span></li>
					<li class="active">数据</li>
				</ul>
				-->
				<div style="color: white;">
					<a href="#">订单管理</a> / <a href="#">添加客户</a> / 数据
					<div align="right">吴雨轩，您好</div>
				</div>
			</div>
			
			<div class="row-fluid" style="margin-top: 20px;">
				<div class="span12">
					<div class="span2 " style="min-height: 500px">
						<ul class="nav nav-tabs nav-stacked">
							<li data-toggle="collapse" data-target="#rentalInfoMgr"><a
								class="menu" href="javascript:void(0)">租赁信息管理</a></li>
							<li id="rentalInfoMgr" class="collapse leftdivchange"><a
								class="huhuoItem first" href="${path }/cmorder/order/index.do">订单管理</a>
								<a class="huhuoItem end" href="${path }/cmorder/order/history.do">历史订单</a>
							</li>

							<li data-toggle="collapse" data-target="#carInfoMgr"><a
								class="menu" href="javascript:void(0)">车辆管理</a></li>
							<li id="carInfoMgr" class="collapse leftdivchange"><a
								class="huhuoItem  first" href="${path }/cmcar/cartype/index.do">车型管理</a>
								<a class="huhuoItem" href="${path }/cmcar/car/index.do">车辆管理</a>
								<a class="huhuoItem end" href="${path }/cmcar/car/trace/index.do">车辆跟踪</a>
							</li>

							<li data-toggle="collapse" data-target="#consumerInfoMgr"><a
								class="menu" href="javascript:void(0)">客户管理</a></li>
							<li id="consumerInfoMgr" class="collapse leftdivchange"><a
								class="huhuoItem first" href="${path }/cmconsumer/consumer/index.do">会员管理</a>
								<a class="huhuoItem end"
								href="${path }/cmconsumer/consumer/points.do">会员积分管理</a></li>

							<li data-toggle="collapse" data-target="#businessMgr"><a
								class="menu" href="javascript:void(0)">业务运营（该模块未添加）</a></li>
							<li id="businessMgr" class="collapse leftdivchange"><a
								class="huhuoItem first" href="${path }/cmorder/order/index.do">车辆运营服务</a>
								<a class="huhuoItem" href="${path }/cmorder/order/index.do">短信发布服务</a>
								<a class="huhuoItem end" href="${path }/cmorder/order/index.do">移动终端服务</a>
							</li>

							<li data-toggle="collapse" data-target="#analyMgr"><a
								class="menu" href="javascript:void(0)">运营分析统计（该模块未添加）</a></li>
							<li id="analyMgr" class="collapse leftdivchange"><a
								class="huhuoItem first" href="${path }/cmorder/order/index.do">营业额统计</a>
								<a class="huhuoItem end" href="${path }/cmorder/order/index.do">客户量统计</a>
							</li>

							<li data-toggle="collapse" data-target="#systemMgr"><a
								class="menu" href="javascript:void(0)">系统管理</a></li>
							<li id="systemMgr" class="collapse leftdivchange"><a
								class="huhuoItem frist" href="${path }/cmsystem/user/index.do">系统用户管理</a>
								<a class="huhuoItem" href="${path }/cmsystem/store/index.do">门店管理</a>
								<a class="huhuoItem end" href="${path }/cmsystem/user/person.do">个人信息管理</a>
							</li>

						</ul>


					</div>

					<div class="span10 well loaddiv" style="min-height: 500px">
						<div class="row-fluid">

							<form class="well  form-search">
								<div class="row-fluid">
									<div class="span4">
										<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label> <input
											type="text" class="span6" placeholder="请输入文字...">
									</div>
									<div class="span4">
										<label>手机号码:</label> <input type="text" class="span6"
											placeholder="请输入文字...">
									</div>
									<div class="span4">
										<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label> <input
											type="text" class="span6" placeholder="请输入文字...">
									</div>
								</div>
								<div class="row-fluid">
									<div class="span4">
										<div class="row-fluid">
											<div class="span3">
												<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
											</div>
											<div class="span2">

												<label class="radio"> <input type="radio"
													name="optionsRadios" id="optionsRadios1" value="option1"
													checked>男
												</label>
											</div>
											<div class="span4">
												<label class="radio"> <input type="radio"
													name="optionsRadios" id="optionsRadios2" value="option2">
													女
												</label>
											</div>
										</div>
									</div>
									<div class="span4">
										<div class="span3">
											<label>个人爱好:</label>
										</div>
										<label class="checkbox"> <input type="checkbox"
											value="">篮球
										</label> <label class="checkbox"> <input type="checkbox"
											value="">足球
										</label> <label class="checkbox"> <input type="checkbox"
											value="">羽毛球
										</label>
									</div>
								</div>
								<div class="row-fluid">
									<div class="span4">
										<label>查询编号:</label> <input type="text" class="span6"
											placeholder="请输入文字...">
										<button type="submit" class="btn">Search</button>
									</div>
								</div>
							</form>

						</div>
						<table class="table table-hover table-condensed">
							<thead>
								<tr>
									<th>#</th>
									<th>电视剧</th>
									<th>类型</th>
									<th>年代</th>
									<th>电视剧</th>
									<th>类型</th>
									<th>年代</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>爱情公寓</td>
									<td>都市爱情喜剧</td>
									<td>2010</td>
									<td>爱情公寓</td>
									<td>都市爱情喜剧</td>
									<td>2010</td>
								</tr>
								<tr>
									<td>2</td>
									<td>邪恶力量</td>
									<td>悬疑魔幻</td>
									<td>2005</td>
									<td>爱情公寓</td>
									<td>都市爱情喜剧</td>
									<td>2010</td>
								</tr>
								<tr>
									<td>3</td>
									<td>神探伽俐略</td>
									<td>推理探案</td>
									<td>2008</td>
									<td>爱情公寓</td>
									<td>都市爱情喜剧</td>
									<td>2010</td>
								</tr>

							</tbody>

						</table>
						<!-- style="position:fixed;top:150px;left:1100px" -->
						<div class="pagination pagination-centered">
							<ul>
								<li><a href="#">?</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">?</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>