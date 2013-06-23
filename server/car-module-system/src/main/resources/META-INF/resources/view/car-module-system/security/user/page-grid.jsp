<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">

table.table-hover tbody tr.huhuo-item-selected {
    background-color: #5D89F8;
	color: #FFF;
}
.greed {
   background-color: #51A351;
   border:0;
}
.red {
  background-color: #BD362F;
  border:0;
}
</style>
</head>
<body onload="lockActive()">
<table id="userPageGridId" class="table table-hover table-condensed">
	<thead>
		<tr>
			<th><input type="checkbox" class="checkbox"></th>
				<th>id</th>
				<th>相          片</th>
				<th>用户姓名</th>
				<th>性          别</th>
				<th>身份证号</th>
				<th>联系电话</th>
				<th>所属分店</th>
				<th>状          态</th>
				<th>相关操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="record">
		<tr>
			<td><input type="checkbox" class="checkbox"></td>
			<td>${record.id}</td>
			<td>
				<a href="javascript:void(0)" class="thumbnail">
					<img class="img-rounded" style="height: 100px; width: 150px;" src="${record.picture.url}">
				</a>
			</td>
			<td>${record.username}</td>
			<td>${record.genderDict.dictDisplayName}</td>
			<td>${record.identityCardId}</td>
			<td>${record.mobileNumber}</td>
			<td>${record.store.name}</td>
			<td class="disp">${record.statusDict.disp}</td>
			<td>
				<div class="btn-group">
<<<<<<< HEAD
							<c:choose>
								<c:when test="${record.status eq 1}">
									<button name="activate" class="btn" disabled="disabled">激活</button>
									<button name="lock" class="btn">锁定</button>
								</c:when>
								<c:otherwise>
									<button name="activate" class="btn">激活</button>
									<button name="lock" class="btn" disabled="disabled">锁定</button>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
=======
					<c:choose>
						<c:when test="${list.status eq 1 }">
							<button name="activate" class="btn" disabled="disabled">激活</button>
							<button name="lock" class="btn">锁定</button>
						</c:when>
						<c:otherwise>
							<button name="activate" class="btn">激活</button>
							<button name="lock" class="btn" disabled="disabled">锁定</button>
						</c:otherwise>
					</c:choose>
				</div>
			</td>
>>>>>>> branch 'master' of https://github.com/huhuo/car-rental.git
		</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<!-- style="position:fixed;top:150px;left:1100px" -->
		<tr>
			<td colspan="9">
				<div class="pagination pagination-centered" style="margin-bottom: 0px;"></div>
			</td>
		</tr>
	</tfoot>
</table>
</body>
<script type="text/javascript">
$(document).ready(function() {
	// 绑定标签元素。设置当前页，页面数据条数，总数，要访问的url，对应的参数，点击标签时刷新的div，标签数
	var page = JSON.parse('${page}');
	var t = JSON.parse('${t}');
	$(".pagination").myPage(page, '${path }/cmsystem/security/user/condition/get.do', t, $("#pagediv"), 5);
	// add select css
	$('#userPageGridId tbody tr').click(function(event) {
		$(this).toggleClass('huhuo-item-selected');
		if ($(event.target).attr("type") != "checkbox") {
			$(this).find(':checkbox')[0].checked = !$(this).find(':checkbox')[0].checked;
		}
	});
	// add select event
	$('#userPageGridId thead tr :checkbox').click(function(event) {
		$('#userPageGridId tbody :checkbox').each(function(index, element) {
			element.checked = $('#userPageGridId thead tr :checkbox')[0].checked;
			if(element.checked) {
				$('#userPageGridId tbody tr').addClass('huhuo-item-selected');
			} else {
				$('#userPageGridId tbody tr').removeClass('huhuo-item-selected');
			}
		});
	});
	// add event to edit button
	$('#userPageGridId tbody button[name="activate"]').click(function(event) {
		var flag = window.confirm("确定要激活吗？");
		if (flag){
			var selectedId = $(this).parent().parent().parent().children().slice(1, 2).text();
			$.get('${path}/cmsystem/security/user/activate.do', {
				id: selectedId
			}, function(resp, status, xhReq) {
				console.log(resp);
				if(status=="success") {
					alert("已经为您激活！");
					$.huhuoGrowlUI(JSON.parse(resp).msg);
					$('#userEditDivId').hide();
					$("#userMgrDivId").show();
					// load element to cartypeEditDivId
					$('#huhuoForm').trigger('submit');
				}
			});
	   }
	});
	
	$('#userPageGridId tbody button[name="lock"]').click(function(event) {
		var flag = window.confirm("确定要锁定吗？");
		if(flag){
			var selectedId = $(this).parent().parent().parent().children().slice(1, 2).text();


			$.get('${path}/cmsystem/security/user/lock.do', {
				id: selectedId
			}, function(data, status, xhReq) {
				console.log(data);
				if(status=="success"){
					$.huhuoGrowlUI(data.msg);
					$('#huhuoForm').trigger('submit');
				}
			});
		}
	});
});
</script>
<<<<<<< HEAD
</body>
<!-- <script type="text/javascript">
$(document).ready(function(){
	$('#userPageGridId tbody tr .disp').each(function(index, td) {
		console.log($(td).text());
		console.info(index);
		//console.log($(td).parent().parent().parent().children().children().slice(9,10).children().children().first());
			if($(td).text()=="已激活"){
				console.info("-------------1");
				console.info($(td).parent().children().last().children().children().first().text());
				$(td).parent().children().last().children().children().first().attr('disabled',"disabled");
			}else{
				console.info("-------------2");
				console.info($(td).parent().children().last().children().children().last().text());
				$(td).parent().children().last().children().children().last().attr('disabled',"disabled");
			}
	});
});

</script> -->
</html>
