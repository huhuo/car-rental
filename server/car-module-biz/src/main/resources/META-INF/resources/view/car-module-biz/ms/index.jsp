<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="row-fluid">
	<div class="span12">
		<form id="addForm" class="form-horizontal well" method="post" action="${path}/cmsystem/ms/sms/send.do">
			<div class="control-group">
				<label class="control-label" >收信人</label>
				<div class="controls" >
					<label class="radio">
						<input type="radio" name="allContact" value="false" checked>
						<input type="text" name="recieverArrs" readonly="readonly" class="input-xlarge">
						<input type="hidden" name="recieverIds">
						<input id="selectRecieverBtnId" type="button" value="选择收信人">
					</label>
					<label class="radio">
						<input type="radio" name="allContact" value="true">
						全部联系人
					</label>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" >短信内容</label>
				<div class="controls">
					<textarea name = "content" cols="220" rows="8" style="width: 380px;" ></textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" >定时发送</label>
				<div class="controls">
					<div class="datetimepicker">
						<input type="text" data-format="yyyy-MM-dd hh:mm:ss" name="startTime" placeholder="定时发送" style="width:380px ">
							<span class="add-on"><i class="icon-th"></i></span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="submit" class="btn btn-large btn-primary">发送</button>
				</div>
			</div>
		</form>
	</div>
</div>

<div id="askDialog" class="modal hide fade" style="left: 30%; width: 1000px;">
	<div class="modal-header">
		<a href="script:void(0)" class="close" data-dismiss="modal">&times;</a>
		<h3 id="askDialogPrompt">请选择短信接收人</h3>
	</div>
	<div class="modal-body">
		<div class="divDialogElements">
			<div id="mgrDivId" class="well" style="padding: 0px;">
				<div class="navbar">
					<div class="navbar-inner">
						<div class="container">
							<!-- search box -->
							<div class="pull-right">
								<ul class="nav">
									<li class="dropdown">
										<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">搜索条件<b class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a class="search-term huhuo-item-selected" name="mobileNumber" href="javascript:void(0)">手机号</a></li>
											<li><a class="search-term" name="username" href="javascript:void(0)">用户名</a></li>
										</ul>
									</li>
								</ul>
								<form id="huhuoForm" class="navbar-form" style="width: 400px;" 
									action="${path }/cmbiz/ms/condition/get.do">
									<input type="hidden" name="page.pageNo" value="1">
									<input type="hidden" name="page.limit" value="10">
									<input type="text" class="span6 search-query" name="mobileNumber" placeholder="手机号">
									<button type="submit" class="btn">搜索</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div id="pagediv">
				</div>
			</div>
		</div>
	</div>
	<div class="modal-footer">
		<a href="javascript:void(0)" class="btn cancel">取消</a> <a href="javascript:void(0)" class="btn btn-primary">确定</a>
	</div>
</div>
<script type="text/javascript">
$(function() {
	// date time picker plugin initialization
	var datetimePicker = $('.datetimepicker').datetimepicker({
		language: 'zh'
	});
	console.log(datetimePicker);
	// ask dialog initialization
	var dialog = $("#askDialog");
	$('#selectRecieverBtnId').click(function(event) {
		dialog.modal("show");
		// bind click event to search button ==> query record by search term
		$('#huhuoForm').huhuoFormPost(function(data, status) {
			if($.isJsonObj(data)) {
				$.huhuoGrowlUI('error occur in server --> ' + data.msg);
			} else {
				$('#pagediv').empty();
				$('#pagediv').append(data);
			}
		});
		$('.search-term.huhuo-item-selected').trigger('click');
		$('#huhuoForm').trigger('submit');
	});
	
	// select modal event
	$('.search-term').click(function(event) {
		$('.search-term').each(function(idx, item) {
			$(item).removeClass("huhuo-item-selected");
		});
		var selectedItem = $(this);
		selectedItem.addClass("huhuo-item-selected");
		
		var searchInput = $('#huhuoForm').children('.search-query');
		searchInput.attr('placeholder', selectedItem.html());
		searchInput.attr('name', selectedItem.attr('name'));
		searchInput.val('');	// reset keyword value
	});
	
	// ask dialog button event
	$('.modal-footer .btn.cancel').click(function() {
		dialog.modal("hide");
	});
	$('.modal-footer .btn.btn-primary').click(function() {
		var recieverArrs = null;
		var recieverIds = null;
		var first = true;
		$('#pagediv table tbody tr.huhuo-item-selected').each(function(index, element) {
			if(first) {
				recieverArrs = $(element).find('td')[2].innerHTML;
				recieverIds = [];
				first = false;
			} else {
				recieverArrs += ', ' + $(element).find('td')[2].innerHTML;
			}
			recieverIds.push($(element).find('td')[1].innerHTML);
		});
		console.log(recieverArrs);
		console.log(recieverIds);
		$('#addForm input[name="recieverArrs"]').val(recieverArrs);
		$('#addForm input[name="recieverIds"]').val(recieverIds);
		dialog.modal("hide");
	});
	
	$('#addForm').huhuoFormPost(function(data, status) {
		console.log(data);
		console.log(status);
		$.huhuoGrowlUI(data.msg);
	});
	
});
</script>