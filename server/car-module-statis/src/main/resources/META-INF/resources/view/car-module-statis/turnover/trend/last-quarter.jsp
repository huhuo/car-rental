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
            text: '前12周营业额统计'
        },
        subtitle: {
            text: 'Source: http://www.zuchechina.com/'
        },
        xAxis: {
            categories: categories,
            tickmarkPlacement: 'on',
            title: {
                enabled: false
            }
        },
        yAxis: {
            title: {
                text: '营业额（万元）'
            },
            labels: {
                formatter: function() {
                    return this.value / 10000;
                }
            }
        },
        tooltip: {
            shared: true,
            valueSuffix: ' 元'
        },
        plotOptions: {
            area: {
                stacking: 'normal',
                lineColor: '#666666',
                lineWidth: 1,
                marker: {
                    lineWidth: 1,
                    lineColor: '#666666'
                }
            }
        },
        series: series
    });
});
</script>