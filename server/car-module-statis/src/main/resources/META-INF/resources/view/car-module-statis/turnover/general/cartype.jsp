<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
	// data handling
	var list = JSON.parse('${list}');
	var series = [{type: 'pie', name: 'Browser share', data:[]}];
	var total = 0;
	$.each(list, function(idx, item) {
		var data = {};
		data.name = item.name;
		if(idx == (list.length-1)) {
			data.sliced = true;
			data.selected = true;
		}
		var num = parseFloat(item.totalFee.toFixed(0));
		data.y = num;
		series[0].data.push(data);
		total += num;
	});
	
	// chart drawing
//	$('#analy_general div.tab-content').highcharts({
	$('#analy_general_cartype').highcharts({
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false
	    },
	    title: {
	        text: '本月各车型营业额（总额：<b>' + $.formatNumber(total, {format: '#,###'}) + '</b>元）'
	    },
	    subtitle: {
            text: '来源: http://www.zuchechina.com/'
        },
	    tooltip: {
	    	formatter: function() {
	    		return this.key + ': ' + $.formatNumber(this.y, {format: '#,###'}) + '元';
	    	}
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                color: '#000000',
	                connectorColor: '#000000',
	                formatter: function() {
	                    var perc =$.formatNumber(this.percentage);
	                	return '<b>'+ this.point.name +'</b>: '+ perc +' %';
	                }
	            }
	        }
	    },
	    series: series
	});
});
</script>
