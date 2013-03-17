<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" scope="application"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bootstrap 101 Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${path }/res/js/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<script src="${path }/res/js/jquery/jquery.js"></script>
<script src="${path }/res/js/bootstrap/js/bootstrap.js"></script>
<script src="${path }/res/js/jquery/jquery.blockUI.js"></script>

<style type="text/css">
.table-hover tbody tr:hover>td,.table-hover tbody tr:hover>th {
	background-color: #5D89F8;
	color: #FFF;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {

		$('a.huhuoItem').click(function() {

			//屏蔽要加载页面的div
			$('div.loaddiv').block({
				message : "<img src='images/busy.gif' style='margin:20%' />",
				css : {
					top : '38%',
					border : 'none',
					backgroundColor : '#fff',
					height : '100%',
					width : '100%',
					opacity : .5,
				}

			});
			console.info($(this).attr("targetUrl"));
			//加载对应页面
			$('div.loaddiv').load($(this).attr("targetUrl"), function() {
				$('this').unblock();
			})
		});

	});
</script>
</head>
<body>
	<div class="container-fluid">

		<div class="row-fluid show-grid">
			<div class="span12">
				<h1>简介</h1>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="span2 well" style="min-height: 500px">
						<ul class="nav nav-tabs nav-stacked">
							<li data-toggle="collapse" data-target="#demo"><a href="#">首页</a></li>
							<li id="demo" class="collapse leftdivchange">
								<a class="huhuoItem" href="#" targetUrl="4.html">美容</a>
								<a class="huhuoItem" href="#" targetUrl="3.html">美食</a>
							
							</li>
							<li><a href="#">介绍</a></li>
							<li><a href="#">消息</a></li>
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
								<li><a href="#">«</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">»</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>