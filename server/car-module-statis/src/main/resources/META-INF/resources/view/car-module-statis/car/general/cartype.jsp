<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
	// data handling
	var list = JSON.parse('${list}');
	var categories = [];
	var series = [];
	var map = {};
	$.each(list, function(idx, item) {
		// construct categories
		categories[idx] = item.countTime;
		// construct series
		var data = parseFloat(item.num.toFixed(0));
		if(map[item.carTypeId] == null) {
			var serie = {};
			serie.name = item.cartype.name;
			serie.data = [data];
			map[item.carTypeId] = serie;
		} else {
			map[item.carTypeId].data.push(data);
		}
	});
	for(propName in map) {
		series.push(map[propName]);
	}
	
	// chart drawing
	$('#analy_general_cartype').highcharts({
		chart: {
            type: 'column'
        },
        title: {
            text: '前6个月各车型车辆数量分布统计'
        },
        subtitle: {
            text: 'Source: http://www.zuchechina.com/'
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            min: 0,
            title: {
                text: '车辆数量（辆）'
            },
            stackLabels: {
                enabled: false,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }
        },
        tooltip: {
            formatter: function() {
            	var serie = '<font style="color: #color;">#serieName: #y 辆 (#percentage%)</font><br>';
            	serie = serie.replace('#color', this.series.color).replace('#serieName', this.series.name);
            	serie = serie.replace('#y', this.y).replace('#percentage', this.percentage.toFixed(2));
                return '<b>'+ this.x + '</b><br/>' +
                	serie +
                    '合计: '+ this.point.stackTotal + '辆';
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                dataLabels: {
                    enabled: true,
                    formatter: function() {
                    	return this.percentage.toFixed(0) + "%";
                    },
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
                }
            }
        },
        series: series
    });
});
</script>