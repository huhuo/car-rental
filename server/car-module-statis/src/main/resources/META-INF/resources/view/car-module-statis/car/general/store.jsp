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
	$('#analy_general_store').highcharts({
		chart: {
            type: 'bar'
        },
        title: {
            text: '前6个月各分店车辆数量统计'
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
            }
        },
    	tooltip: {
        	headerFormat: '<span style="font-size:12px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 辆</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
            	pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: series
    });
});
</script>