<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.nav-tabs > li > a {
border: 0px solid;
}
</style>
<div id="analy_general" class="well">
	<div><h4>统计概述</h4></div>
	<div id="analy_general_store" class="tab-content">
	</div>
	<div id="analy_general_cartype" class="tab-content">
	</div>
</div>
<div id="analy_trend" class="well tabbable">
	<div><h4>趋势分析</h4></div>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${path }/cmstatis/turnover/trend/last-month.do">前15天</a>
		</li>
		<li>
			<a href="${path }/cmstatis/turnover/trend/last-quarter.do">前12周</a>
		</li>
		<li>
			<a href="${path }/cmstatis/turnover/trend/last-year.do">前12个月</a>
		</li>
	</ul>
	<div class="tab-content">
	</div>
</div>
<script type="text/javascript">
$(function() {
	// general analy
	$('#analy_general_store').divBlockLoad('${path }/cmstatis/turnover/general/store.do');
	$('#analy_general_cartype').divBlockLoad('${path }/cmstatis/turnover/general/cartype.do');
	
	// trend analy
	$('#analy_trend ul[class="nav nav-tabs"] a').click(function(e) {
		var a = $(this);
		// activate tab with css
		$('#analy_trend ul[class="nav nav-tabs"] li').removeClass('active');
		a.parent().addClass('active');
		// load tab's page
		var url = a.attr('href');
		$('#analy_trend .tab-content').divBlockLoad(url, null,
				function(data, status, event, loadDiv) {
			if(status != 'success') {
				loadDiv.append('无数据');
			}
		});
		return false;
	});
	// trigger active tab click event
	$('#analy_trend ul[class="nav nav-tabs"] li.active a').trigger('click');
});
</script>