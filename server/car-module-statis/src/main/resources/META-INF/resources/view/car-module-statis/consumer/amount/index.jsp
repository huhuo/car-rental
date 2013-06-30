<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
div.huhuo-label {
	padding-bottom: 6px;
	padding-top: 6px;
	padding-right: 10px;
	text-align: right;
}
.huhuoyes {
	background-image: url("res/images/status/btn_check_buttonless_on.png");
	background-repeat: no-repeat;
	background-position: 80% center;

}

.form-horizontal .control-label {
	width: 100px;
}
.form-horizontal .controls {
	margin-left: 120px;
}
input, textarea, .uneditable-input {
	width: 266px;
}

select {
	width: 280px;
}

.nav-tabs > li > a{
	border: 0px solid;
}
</style>
<script type="text/javascript">
/* $(function() {
	var list = JSON.parse('${list}');
	console.log(list);
	var categories = [];
	var data = [];
	$.each(list,function(index,record){
		console.log(record.day);
		categories[index]=record.day;
		data[index] =record.totalNum;
	});
	//	var list = ${list};
	$('#pagediv').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: '客户量统计'
        },
        subtitle: {
        	text: '来源: www.zuchechina.com'
        },
        xAxis: {
            categories:categories
        },
        yAxis: {
            title: {
                text: '人数'
            },
            labels: {
                formatter: function() {
                    return this.value +'（人）';
                }
            }
        },
        tooltip: {
            crosshairs: false,
            shared: true
        },
        plotOptions: {
            spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }
        },
        series: [{
            name: '本周租车人次统计',
            marker: {
                symbol: 'square'
            },
            data: data

        }]
    });
}); */
$(function () {
	$("#pagediv").load('${path}/cmstatis/consumer/amount/this-week.do',function(){
	});
});

function ststisWeek() {
	$("#pagediv").load('${path}/cmstatis/consumer/amount/this-week.do',function(){
	});
}
function ststisMonth() {
	$("#pagediv").load('${path}/cmstatis/consumer/amount/last-month.do',function(){
	});
}
function ststisQuarter() {
	$("#pagediv").load('${path}/cmstatis/consumer/amount/last-quarter.do',function(){
	});
}
function ststisYear() {
	$("#pagediv").load('${path}/cmstatis/consumer/amount/last-year.do',function(){
	});
}
	// load element to cartypeEditDivId
	
	
/* btnGroup.children('button[name="week"]').click(function(event) {
	// load element to cartypeEditDivId
	$("#pagediv").load('${path}/cmstatis/consumer/amount/last-quarter.do',function(){
	});
	
});
btnGroup.children('button[name="month"]').click(function(event) {
	// load element to cartypeEditDivId
	$("#pagediv").load('${path}/cmstatis/consumer/amount/last-year.do',function(){
	});
	
}); */
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
