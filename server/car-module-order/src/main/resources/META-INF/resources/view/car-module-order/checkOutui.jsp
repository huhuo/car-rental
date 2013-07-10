<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		
		var oilPrice=${order.oilPrice };
		
		var overMileageFare=${order.overMileageFare };
		
		var oilmassBegin=${order.oilmassBegin };
		var limitMile=${limitMile };
		var normalPrice=${normalPrice };
		var overTimePrice=${overTimePrice };
		
		
		$("#returnSearch").click(function() {
			var addOrderDiv = $("#addOrderDiv");
			addOrderDiv.hide(200, function() {
				$("#orderSearch").show(200);
			});
		});
		
		function count(){
			var oil=Number($("#ordercurrentoil").val());
			if(oil==NaN){
				oil=0;
			}
			var mile=Number($("#ordercurrentmile").val());
			if(mile==NaN){
				mile=0;
			}
			$("#orderCurrenterOil").val(oil);
			$("#orderCurrenterMile").val(mile);
			
			var oilSpend=0;
			if(oil<oilmassBegin){
				oilSpend=(oilmassBegin-oil)*oilPrice;
			}
			var mileEnd=0;
			if(mile>limitMile){
				mileEnd=(mile-limitMile)*overMileageFare;
			}
			
			$("#overOilPrice").val(oilSpend);
			$("#overMilePrice").val(mileEnd);
			
			var totalPrice=normalPrice+overTimePrice+oilSpend+mileEnd;
			$("#orderTotalPrice").val(totalPrice);
		}
		  
		
		 $("#orderInputDiv").show();
		 $("#orderViewInfoDiv").hide();
		 
		
		$("#orderFirstBtn").click(function() {
			var orderInputDiv = $("#orderInputDiv");
			orderInputDiv.hide(200, function() {
				$("#orderViewInfoDiv").show(200);
			});
			count();
			return false;
		});
		
		
		$("#orderSecondBtn").click(function() {
			var orderViewInfoDiv = $("#orderViewInfoDiv");
			orderViewInfoDiv.hide(200, function() {
				$("#orderInputDiv").show(200);
			});
			return false;
		});
		
		$('#addOrderform').huhuoFormPost(function(data, status){
			if($.isJsonObj(data)) {
				$.huhuoGrowlUI( data.msg);
			} 
			if (status == 'success') {
				var addOrderDiv = $("#addOrderDiv");
				addOrderDiv.hide(200, function() {
					$("#orderSearch").show(200);
				});
				$("#orderTableloadDiv").divBlockLoad("${path }/cmorder/order/get.do");
			}
			
		});
	
	});
</script>

<button id="returnSearch" class="btn" style="margin-bottom: 5px;">返回</button>

<div class="row-fluid"  id="orderInputDiv">
	<form  class="form-horizontal">
		<div class="span12">
			<div class="control-group" style="margin-top: 60px;">
				<label class="control-label" for="inputName" >当前油量</label>
				<div class="controls">
					<input type="text" class="orderinput"  id="ordercurrentoil"
						name="currentoil" placeholder="当前油量...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputName">当前里程</label>
				<div class="controls">
					<input type="text" class="orderinput"  id="ordercurrentmile"
						name="currentmile" placeholder="当前油量...">
				</div>
		
			</div>
			<button id="orderFirstBtn" class='span4 offset4 btn'>下一步</button>
		</div>
	</form>
</div>

<div id="orderViewInfoDiv">
	<form id='addOrderform' class="form-horizontal"
		action="${path}/cmorder/order/checkout.do" method="post">
		<div class="row-fluid">
				
			<div class="span12">
				<div class="control-group" style="margin-top: 60px;">
					<label class="control-label" for="inputName" >租车开始</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="order.carRentTime" placeholder="租车开始..." value="${order.carRentTime }">
						<!-- 订单Id -->
						<input type="hidden" class="orderinput" readonly="readonly" 
							name="orderId"  value="${order.id }">
					</div>
				</div>
				<div class="control-group" style="margin-top: 60px;">
					<label class="control-label" for="inputName" >预计还车</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="order.carPlanRetTime" placeholder="租车开始..." value="${order.carPlanRetTime }">
					</div>
				</div>
				<div class="control-group" style="margin-top: 60px;">
					<label class="control-label" for="inputName" >实际还车</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="order.retTime" placeholder="租车开始..." value="${retTime }">
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="inputName">租金</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="order.rent" placeholder="共计天数..." value=${order.rent }>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">超时费用</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="order.overTimeFare" placeholder="共计天数..." id="orderDays" value=${order.overTimeFare }>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">超时</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="order.hours" placeholder="租车结束..."  value=${hours } >
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">标准费用</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="order.normalPrice" placeholder="共计天数..." value=${normalPrice }>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="inputName">超时费用</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="order.overTimePrice" placeholder="租车结束..." value=${overTimePrice } >
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">初始油量</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="consumer.oilmassBegin" placeholder="共计天数..." value=${order.oilmassBegin }>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">现在油量</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" id="orderCurrenterOil" 
							name="oil" placeholder="共计天数...">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">油耗费用</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" id="overOilPrice" 
							name="order.overOilPrice" placeholder="共计天数...">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">初始里程</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="consumer.mileageBegin" placeholder="共计天数..."  value=${order.mileageBegin }>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">实际里程</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" id="orderCurrenterMile" 
							name="mile" placeholder="共计天数...">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">里程上限</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" 
							name="order.limitMile" placeholder="共计天数..."  value=${limitMile }>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">超里程费</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" id="overMilePrice"
							name="overMilePrice" placeholder="共计天数...">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputName">共计</label>
					<div class="controls">
						<input type="text" class="orderinput" readonly="readonly" id="orderTotalPrice"
							name="order.totalPrice" placeholder="共计天数...">
					</div>
				</div>
				<div class="row-fluid">
				<button id="orderSecondBtn" class='span3 offset3 btn'>上一步</button>
				<button type="submit" class='span3 offset1 btn'>结账</button>
				</div>
			</div>
		</div>
	
	</form>
</div>