<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<script type="text/javascript">
	$(document).ready(function(){
		// bind click event to drop down component ==> reset search term
		$('.search-term').click(function(event) {
			var searchInput = $('#huhuoForm').children('.search-query');
			searchInput.attr('placeholder', $(this).html());
			searchInput.attr('name', $(this).attr('id'));
		});
		// bind click event to search button ==> query record by search term
		$('#huhuoForm').huhuoFormPost(function(data, status) {
			console.log(data);
		});
	});
</script>

<div class="well" style="padding: 0px;">
	<div class="navbar">
		<div class="navbar-inner">
			<div class="container">
				<ul class="nav">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">数据操作<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">添加</a></li>
							<li><a href="#">编辑</a></li>
							<li><a href="#">删除</a></li>
						</ul>
					</li>
				</ul>
				<!-- search box -->
				<div class="pull-right">
					<ul class="nav">
						<li class="dropdown">
							<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">搜索条件<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a class="search-term" id="name" href="javascript:void(0)">车型名称</a></li>
								<li><a class="search-term" id="category" href="javascript:void(0)">分类</a></li>
								<li class="divider"></li>
								<li><a class="search-term" id="seating" href="javascript:void(0)">座位数</a></li>
								<li><a class="search-term" id="tankCapacity" href="javascript:void(0)">油箱容量（L）</a></li>
							</ul>
						</li>
					</ul>
					<form id="huhuoForm" class="navbar-form" style="width: 400px;" action="${path }/cmcar/cartype/condition/get.do">
						<input type="text" class="search-query span6" name="mobileNumber" placeholder="车型名称">
						<button type="submit" class="btn">search</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="pagediv">
		<table class="table table-hover table-condensed">
			<thead>
				<tr>
					<th>#</th>
					<th>电视剧</th>
					<th>类型</th>
					<th>年代</th>
					<th>电视剧</th>
					<th>类型</th>
					<th>年代</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>爱情公寓</td>
					<td>都市爱情喜剧</td>
					<td>2010</td>
					<td>爱情公寓</td>
					<td>都市爱情喜剧</td>
					<td>2010</td>
				</tr>
				<tr>
					<td>2</td>
					<td>邪恶力量</td>
					<td>悬疑魔幻</td>
					<td>2005</td>
					<td>爱情公寓</td>
					<td>都市爱情喜剧</td>
					<td>2010</td>
				</tr>
				<tr>
					<td>3</td>
					<td>神探伽俐略</td>
					<td>推理探案</td>
					<td>2008</td>
					<td>爱情公寓</td>
					<td>都市爱情喜剧</td>
					<td>2010</td>
				</tr>
	
			</tbody>
		</table>
		<!-- style="position:fixed;top:150px;left:1100px" -->
		<div class="pagination pagination-centered">
			<ul>
				<li><a href="#">«</a></li>
				<li><a href="#">1</a></li>
				<li><a href="#">2</a></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><a href="#">»</a></li>
			</ul>
		</div>
	</div>
</div>