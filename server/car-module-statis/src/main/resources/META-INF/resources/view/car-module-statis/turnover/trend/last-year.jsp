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
		categories[idx] = item.sumTime;
		// construct series
		var data = parseFloat(item.totalFee.toFixed(0));
		if(map[item.storeId] == null) {
			var serie = {};
			serie.name = item.store.name;
			serie.data = [data];
			map[item.storeId] = serie;
		} else {
			map[item.storeId].data.push(data);
		}
	});
	for(propName in map) {
		series.push(map[propName]);
	}
	
	// chart drawing
	$('#analy_trend div.tab-content').highcharts({
		chart: {
            type: 'column'
        },
        title: {
            text: '前12个月营业额统计'
        },
        subtitle: {
            text: '来源: http://www.zuchechina.com/'
        },
        xAxis: {
            categories: categories
        },
        yAxis: {
            min: 0,
            title: {
                text: '营业额（万元）'
            },
            labels: {
	            formatter: function() {
	            	return $.formatNumber(this.value/10000, {format: '#,###'});
	            }
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
            	var serie = '<font style="color: #color;">#serieName: #y (#percentage%)</font><br>';
            	serie = serie.replace('#color', this.series.color).replace('#serieName', this.series.name);
            	serie = serie.replace('#y', $.formatNumber(this.y, {format: '#,###'})).replace('#percentage', $.formatNumber(this.percentage));
                return '<b>'+ this.x + '</b><br/>' +
                	serie +
                    '合计: '+ $.formatNumber(this.point.stackTotal, {format: '#,###'});
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