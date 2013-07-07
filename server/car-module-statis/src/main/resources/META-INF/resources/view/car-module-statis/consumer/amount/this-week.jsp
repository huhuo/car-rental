<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
 $(function(){
	 console.log("--------->>");
		var list = JSON.parse('${list}');
		console.log("1--------->>");
		var categories = [];
		var data = [];
		$.each(list,function(index,record){
			console.log(record.day);
			categories[index]=record.day;
			data[index] =record.totalNum;
		});
		//	var list = ${list};
		$('#last-month').highcharts({
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
	        	min: 0,
	            title: {
	                text: '人数（人）'
	            },
	            labels: {
	                formatter: function() {
	                    return this.value;
	                }
	            }
	        },
	        tooltip: {
	        	formatter: function() {
                    return '<b>'+ this.x +'共租车:</b>'+
                        Highcharts.numberFormat(this.y, 0) +
                        '人次';
                }
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
	            name: '本周租车人次统计(星期天为星期的最后一天)',
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
<div id="last-month"></div>
</div>
