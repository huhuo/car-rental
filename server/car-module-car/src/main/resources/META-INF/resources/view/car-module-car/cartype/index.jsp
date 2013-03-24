<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<script type="text/javascript">
	$(document).ready(function(){
		$('#huhuoForm').huhuoFormPost(function(data, status) {
			console.log(data);
			console.log(status);
		});
	});
</script>

<div class="well" style="padding: 0px;">
	<div class="bs-navbar-top-example">
		<div class="navbar">
			<div class="navbar-inner" style="border-radius:4px 4px 0 0 ;">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar">sa</span>
						<span class="icon-bar">sda</span>
						<span class="icon-bar">ddd</span>
					</a>
					<a class="brand" href="#">Project name</a>
					<div class="brand" class="nav-collapse collapse">
					</div>
					<div class="nav-collapse collapse navbar-responsive-collapse">
						<ul class="nav">
							<li class="active"><a href="#">被选中</a></li>
							<li><a href="#">导航标签未被选中</a></li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><a href="#">add</a></li>
									<li><a href="#">edit</a></li>
									<li class="divider"></li>
									<li class="nav-header">Nav header</li>
									<li><a href="#">Separated link</a></li>
									<li><a href="#">One more separated link</a></li>
								</ul>
							</li>
						</ul>
						<!-- search box -->
						<form id="huhuoForm" class="navbar-form pull-left" action="${path }/cmcar/cartype/huhuo/form.do">
							<input type="text" class="span4" name="first" placeholder="input your number">
							<input type="text" class="span4" name="second" placeholder="input your name">
							<button type="submit" class="btn">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="prettyprint linenums">
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
</div>