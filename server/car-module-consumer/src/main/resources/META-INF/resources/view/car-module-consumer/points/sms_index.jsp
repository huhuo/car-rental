<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
	var datetimePicker = $('.datetimepicker').datetimepicker({
		language: 'zh'
	});
</script>
<body>

	<div class="row-fluid">
	<div class="span12">
		<form id="addForm" class="form-horizontal well" method="post" action="${path}/cmsystem/ms/sms/sendSMS.do">
		
			<div class="control-group">
				<label class="control-label" >收信人</label>
					<div class="controls" >
						<input type="text" name="phoneNum" placeholder="支持手机号搜索客户"  style="width:300px ">
						<input type="checkbox" name="allContacts"> <label >全部联系人</label>
						<!-- 
							<span class="add-on"><i class="icon-plus-sign"></i></span>
						 -->
					</div>
			</div>
			<div class="control-group">
				<label class="control-label" >短信内容</label>
					<div class="controls">
						<textarea name = "content" cols="200" rows="8" style="width:300px "></textarea>
					</div>
			</div>
			<div class="control-group">
				<label class="control-label" >定时发送</label>
					<div class="controls">
						<div class="datetimepicker">
							<input type="text" data-format="yyyy-MM-dd HH:mm:ss" name="timing" placeholder="定时发送" style="width:300px ">
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
</body>
</html>