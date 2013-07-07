<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
.nav-tabs > li > a{
	border: 0px solid;
}
</style>
<script type="text/javascript">
$(function () {
	$("#pagediv").divBlockLoad('${path}/cmstatis/consumer/amount/this-week.do',null,
			function(data, status, event, loadDiv){
			if('success'!=status){
				loadDiv.append('无数据');
			}
	});
});

function ststisWeek() {
	$("#pagediv").divBlockLoad('${path}/cmstatis/consumer/amount/this-week.do',null,
			function(data, status, event, loadDiv){
		if('success'!=status){
			loadDiv.append('无数据');
		}
	});
}
function ststisMonth() {
	$("#pagediv").divBlockLoad('${path}/cmstatis/consumer/amount/last-month.do',null,
			function(data, status, event, loadDiv){
		if('success'!=status){
			loadDiv.append('无数据');
		}
	});
}
function ststisQuarter() {
	$("#pagediv").divBlockLoad('${path}/cmstatis/consumer/amount/last-quarter.do',null,
			function(data, status, event, loadDiv){
		if('success'!=status){
			loadDiv.append('无数据');
		}
	});
}
function ststisYear() {
	$("#pagediv").divBlockLoad('${path}/cmstatis/consumer/amount/last-year.do',null,
			function(data, status, event, loadDiv){
		if('success'!=status){
			loadDiv.append('无数据');
		}
	});
}
$('#storeMgrDivId ul a').click(function() {
	console.log(this);
});

</script>

<div id="storeMgrDivId" class="" style="padding: 0px;">
		<ul class="nav nav-tabs">
			<li class="active" ><a href="#" data-toggle="tab" onclick="ststisWeek()">本周统计</a></li>
			<li ><a href="#" data-toggle="tab" onclick="ststisMonth()">上一月统计</a></li>
			<li ><a href="#" data-toggle="tab" onclick="ststisQuarter()">前12周统计</a></li>
			<li ><a href="#" data-toggle="tab" onclick="ststisYear()">前12个月统计</a></li>
		</ul>
	<div id="pagediv">
	</div>
</div>

<div id="storeEditDivId">

</div>
