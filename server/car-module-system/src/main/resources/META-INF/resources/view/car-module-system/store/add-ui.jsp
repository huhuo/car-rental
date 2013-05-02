<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	$(document).ready(function(){
		$('#addStoreForm').submit(function(){
			var fir = $('#addStoreForm');
			$.post("${path}/cmsystem/store/add.do",fir.serialize(),function(data,status,xhrq){
				$('#storeEditDivId').hide();
				$("#storeMgrDivId").show();
				// load element to cartypeEditDivId
				$("#storeEditDivId").load('${path}/cmsystem/store/index.do');
				
			});
			 
			return false ;
		});
	});
	
	
	
	
</script>
<form id="addStoreForm" class="form-horizontal well" action="${path}/cmsystem/store/add.do" method="post">
	<div class="row-fluid">
		<div class="span12">
			<div class="control-group">
				<label class="control-label" for="inputname">分店名称</label>
				<div class="controls">
					<input type="text" class="required" id="inputname" name="name" placeholder="分店名称...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputaddress">分店地址</label>
				<div class="controls">
					<input type="text" class="required" id="inputaddress" name="address" placeholder="分店地址...">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputManagerId">分店店长</label>
				<div class="controls">
					<select id="inputManagerId" name="managerId">
						 <c:forEach items="${mgrs}" var="mgr">
							<option value="${mgr.id}">${mgr.username} ${mgr.genderDict.dictDisplayName}</option>
						 </c:forEach>	
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputtelephone">联系电话</label>
				<div class="controls">
					<input type="text" class="required" id="inputtelephone" name="telephone" placeholder="联系电话...">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn">添加</button>
				</div>
			</div>
		</div>
	</div>
</form>
