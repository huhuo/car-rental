<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
.nav-tabs > li > a {
border: 0px solid;
}
</style>
<div id="analy_general" class="well">
	<div><h4>车辆统计</h4></div>
	<div id="analy_general_store" class="tab-content">
	</div>
	<div id="analy_general_cartype" class="tab-content">
	</div>
</div>
<script type="text/javascript">
$(function() {
	// general analy
	$('#analy_general_store').load('${path }/cmstatis/car/general/store.do');
	$('#analy_general_cartype').load('${path }/cmstatis/car/general/cartype.do');
});
</script>