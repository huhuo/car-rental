<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row-fluid">
	<form class="well form-search">
		<div class="row-fluid">
			<div class="span4">
				<label>车辆型号：</label>
				<select class="span6">
					<option value ="0" label="请选择..." />
					<option value ="0" label="宝马 x6" />
					<option value ="0" label="奔驰 S600" />
					<option value ="0" label="奥迪A4L" />
					<option value ="0" label="君威" />
					<option value ="0" label="北京现代" />
				</select>
			</div>
			<div class="span4">
				<label>座位数：</label>
				<select class="span6">
					<option value ="0" label="请选择..." />
					<option value ="1" label="4座" />
					<option value ="2" label="5座" />
					<option value ="3" label="6座" />
					<option value ="4" label="7座" />
					<option value ="5" label="7座以上" />
				</select>
			</div>
			<div class="span4">
				<label>租金：</label>
				<select class="span6">
					<option value ="0">车辆型号</option>
					<option value ="1">宝马 x6</option>
					<option value ="2">奔驰 S600</option>
					<option value ="3">奥迪A4L</option>
					<option value ="4">君威</option>
					<option value ="5">北京现代</option>
				</select>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span4">
				<div class="row-fluid">
					<div class="span3">
						<label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</label>
					</div>
					<div class="span2">

						<label class="radio"> <input type="radio"
							name="optionsRadios" id="optionsRadios1" value="option1" checked>男
						</label>
					</div>
					<div class="span4">
						<label class="radio"> <input type="radio"
							name="optionsRadios" id="optionsRadios2" value="option2">
							女
						</label>
					</div>
				</div>
			</div>
			<div class="span4">
				<div class="span3">
					<label>个人爱好:</label>
				</div>
				<label class="checkbox"> <input type="checkbox" value="">篮球
				</label> <label class="checkbox"> <input type="checkbox" value="">足球
				</label> <label class="checkbox"> <input type="checkbox" value="">羽毛球
				</label>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span4">
				<label>查询编号:</label> <input type="text" class="span6"
					placeholder="请输入文字...">
				<button type="submit" class="btn">Search</button>
			</div>
		</div>
	</form>

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