<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path" scope="application"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>汽车租赁管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="${path }/res/js/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${path }/res/js/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link href="${path }/res/js/huhuo/huhuo.css" rel="stylesheet" type="text/css">
<script src="${path }/res/js/jquery/jquery.js"></script>
<script src="${path }/res/js/bootstrap/js/bootstrap.js"></script>
<script src="${path }/res/js/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
<script src="${path }/res/js/bootstrap/js/bootstrap-datetimepicker.pt-zh.js"></script>
<script src="${path }/res/js/jquery/jquery.blockUI.js"></script>
<script src="${path }/res/js/jquery/jquery.validate.js"></script>
<script src="${path }/res/js/jquery/jquery.validate.message.js"></script>
<script src="${path }/res/js/jquery/jquery.form.js"></script>
<script src="${path }/res/js/jquery/jshashtable-2.1.js"></script>
<script src="${path }/res/js/jquery/jquery.numberformatter-1.2.3.js"></script>
<script src="${path }/res/js/jquery/jquery.pin.js"></script>
<script type="text/javascript" src="${path }/res/js/high-charts/highcharts.js"></script>
<script type="text/javascript" src="${path }/res/js/high-charts/exporting.js"></script>
<%-- <script src="${path }/res/js/kissy/kissy.js"></script> --%>
<script src="${path }/res/js/huhuo/huhuo.js"></script>
<style type="text/css">

.nav-tabs.nav-stacked > li > a.huhuo-item-selected {
    background-color: #5D89F8;
    color: #FFFFFF;
}

</style>

<script type="text/javascript">
// define a global variables
var ctxPath = '${path}';
$(document).ready(function() {
	$('.huhuoItem').click(function(event) {
		$('.huhuoItem').each(function(idx, item) {
			$(item).removeClass("huhuo-item-selected");
		});
		$(this).addClass("huhuo-item-selected");
	});
	$('#rentalInfoMgr a.first').trigger('click');
});
</script>

</head>
<body>
	<div class="container-fluid" style='min-width:1200px'>

		<div class="row-fluid show-grid">
			<div class="top" style="padding-top:10px">
				<h3 align="center" style="margin-top:5px; color: white;">弛通汽车租赁管理系统 3.0 beta</h3>
				
				<ul class="breadcrumb" style="border-radius: 4px 4px 0px 0px;">
					<li><a href="#">首页</a><span class="divider">/</span></li>
					<li><a href="#">车辆管理</a><span class="divider">/</span></li>
					<li><a href="#">车型管理</a><span class="divider">/</span></li>
					<li class="active">Data</li>
					<li style="float: right;">${user.username }，您好    <a href="${path }/cmsystem/security/validation/logout.do">logout</a></li>
				</ul>
			</div>
			
			<div class="row-fluid" style="margin-top: 20px;">
				<div class="span12">
					<div class="span2 " style="min-height: 500px">
						<ul class="nav nav-tabs nav-stacked">
							<li data-toggle="collapse" data-target="#rentalInfoMgr"><a
								class="menu" href="javascript:void(0)"><i class="icon-shopping-cart"></i>&nbsp;租赁信息管理</a></li>
							<li id="rentalInfoMgr" class="collapse leftdivchange"><a
								class="huhuoItem first" href="${path }/cmorder/order/index.do">订单管理</a>
								<a class="huhuoItem end" href="${path }/cmorder/order/history.do">历史订单</a>
							</li>

							<li data-toggle="collapse" data-target="#carInfoMgr">
								<a class="menu" href="javascript:void(0)"><i class="icon-bullhorn"></i>&nbsp;车辆管理</a>
							</li>
							<li id="carInfoMgr" class="collapse leftdivchange">
								<a class="huhuoItem  first" href="${path }/cmcar/cartype/index.do">车型管理</a>
								<a class="huhuoItem" href="${path }/cmcar/car/index.do">车辆管理</a>
								<!-- ${path }/cmcar/car/trace/index.do -->
								<a class="huhuoItem end" href="${path }/cmcar/trace/trace.do">车辆跟踪</a>
							</li>
							<li data-toggle="collapse" data-target="#consumerInfoMgr">
								<a class="menu" href="javascript:void(0)"><i class="icon-user"></i>&nbsp;客户管理</a>
							</li>
							<li id="consumerInfoMgr" class="collapse leftdivchange"><a
								class="huhuoItem first" href="${path }/cmconsumer/consumer/index.do">客户管理</a>
								<!-- 
								<a class="huhuoItem end" href="${path }/cmconsumer/consumer/points.do">客户积分管理</a></li>
								 -->

							<li data-toggle="collapse" data-target="#businessMgr"><a
								class="menu" href="javascript:void(0)"><i class="icon-fire"></i>&nbsp;业务运营</a></li>
							<li id="businessMgr" class="collapse leftdivchange">
							<!-- 
							<a class="huhuoItem first" href="${path }/cmorder/order/index.do">车辆运营服务</a>
							 -->
								<a class="huhuoItem" href="${path }/cmbiz/ms/index.do">短信发布服务</a>
								<a class="huhuoItem end" href="${path }/cmbiz/ms/mobile_index.do">移动终端服务</a>
							</li>

							<li data-toggle="collapse" data-target="#analyMgr"><a
								class="menu" href="javascript:void(0)"><i class="icon-align-right"></i>&nbsp;运营分析统计</a></li>
							<li id="analyMgr" class="collapse leftdivchange"><a
								class="huhuoItem first" href="${path }/cmstatis/turnover/index.do">营业额统计</a>
								<a class="huhuoItem end" href="${path }/cmstatis/consumer/amount/index.do">客户量统计</a>
								<a class="huhuoItem end" href="${path }/cmstatis/car/index.do">汽车统计</a>
							</li>

							<li data-toggle="collapse" data-target="#systemMgr"><a
								class="menu" href="javascript:void(0)"><i class="icon-wrench"></i>&nbsp;系统管理</a></li>
							<li id="systemMgr" class="collapse leftdivchange"><a
								class="huhuoItem frist" href="${path }/cmsystem/security/user/index.do">系统用户管理</a>
								<a class="huhuoItem" href="${path }/cmsystem/store/index.do">门店管理</a>
								<a class="huhuoItem end" href="${path }/cmsystem/security/user/person.do">个人信息管理</a>
							</li>

						</ul>
					</div>
					<div id="cmsystem_main_panel" class="span10 well loaddiv" style="min-height: 500px"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
