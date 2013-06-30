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
</style>
<script type="text/javascript">
 $(function(){
	 console.log("--------->>");
		var list = JSON.parse('${list}');
		console.log("1--------->>");
		var categories = [];
		var data = [];
		$.each(list,function(index,record){
			console.log(record.month);
			categories[index]=record.month;
			data[index] =record.totalNum;
		});
		//	var list = ${list};
		$('#last-year').highcharts({
			chart: {
	        	 type: 'column',
	             margin: [ 50, 50, 100, 90]
	        },
	        title: {
	            text: '客户量统计'
	        },
	        subtitle: {
	        	text: '来源: www.zuchechina.com'
	        },
	        xAxis: {
	            categories:categories,
	            labels: {
                    align: 'right',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
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
	            name: '前12个月租车人次统计',
	            data: data,
	            dataLabels: {
                    enabled: true,
                    rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    x: 4,
                    y: 10,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }

	        }]
	    });
 });
</script>
<div class="well" style="padding: 0px;">
<div id="last-year"></div>
</div>
