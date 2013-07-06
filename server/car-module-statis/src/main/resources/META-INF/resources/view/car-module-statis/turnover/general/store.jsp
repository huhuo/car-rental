<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
	// data handling
	var list = JSON.parse('${list}');
	var categories = [];
	var series = [];
	series.push({name: '各分店本月营业额', data: []});
	var total = 0;
	$.each(list, function(idx, item) {
		// construct categories
		categories[idx] = item.store.name;
		// construct series
		var data = parseFloat(item.totalFee.toFixed(0));
		total += data;
		series[0].data.push(data);
	});
	
	// chart drawing
	$('#analy_general_store').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '本月各分店营业额（总额：<b>' + $.formatNumber(total, {format: '#,###'}) + '</b>元）'
        },
        subtitle: {
            text: 'Source: http://www.zuchechina.com/'
        },
        xAxis: {
            categories: categories,
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '营业额（万元）',
                align: 'middle'
            },
            labels: {
                overflow: 'justify',
                formatter: function() {
                	return this.value/10000;
                }
            }
        },
        tooltip: {
        	formatter: function() {
        		return this.x + ': ' + $.formatNumber(this.y, {format: '#,###'}) + '元';
        	}
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true,
                    formatter: function() {
                		return $.formatNumber(this.y/total*100) + '%';
                	}
                }
            }
        },
        legend: {
        	enabled: false,
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -100,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: '#FFFFFF',
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: series
    });
});
</script>