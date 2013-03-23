<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath }" var="path"
	scope="application"></c:set>
	
	<script type="text/javascript">
	function loadData() {
		
		var xmlhttp;
		if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
			  xmlhttp=new XMLHttpRequest();
			  alert("支持XMLHttpRequest " + xmlhttp);
		  }
		else{// code for IE6, IE5
		  	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  	xmlhttp=new XMLHttpRequest();
			alert("不支持XMLHttpRequest " + xmlhttp);
		  }
		xmlhttp.onreadystatechange=function(){
		  if (xmlhttp.readyState==4 && xmlhttp.status==200){
		    	//document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
		    	alert("server response content : " + xmlhttp.responseText);
		    } else {
		    	//alert("response error");
		    }
		  		
		  }
		xmlhttp.open("GET","${path }/cmconsumer/consumer/get.do?t="+ Math.random(),true);
		xmlhttp.send();
	}
</script>

<div class="row-fluid">

	<form class="well  form-search" action="${path }/cmconsumer/consumer/get.do">
		<div class="row-fluid">
			<div class="span3">
				<label>客户姓名:</label> <input type="text" class="span6"
					placeholder="请输入文字...">
			</div>
			<div class="span3">
				<label>手机号码:</label> <input type="text" class="span6"
					placeholder="请输入文字...">
			</div>
			<div class="span3">
				<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label> <input
					type="text" class="span6" placeholder="请输入文字...">
			</div>
			<div class="span3">
				<button style="width: 100px" class="btn">搜索</button>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span3">
				<label>查询编号:</label> <input type="text" class="span6"
					placeholder="请输入文字...">
				<button type="submit" class="btn">搜索</button>
			</div>
		</div>
	</form>
</div>

<button onclick="loadData()" style="width: 100px" class="btn">添加</button>
<button style="width: 100px" class="btn">编辑</button>
<button style="width: 100px" class="btn">删除</button>

<div>

</div>
<div class="row-fluid">
	<div class="span4"></div>
	<div class="span4"></div>
	<div class="span4"></div>
</div>

<table class="table table-hover table-condensed">
	<thead>
		<tr>
			<th></th>
			<th>头像</th>
			<th>会员编号</th>
			<th>会员姓名</th>
			<th>联系电话</th>
			<th>会员状态</th>
			<th>会员积分</th>
		</tr>
	</thead>



	<tbody>
		<tr>
			<td><input id="allchecked" type="checkbox" onclick="check();">
			</td>
			<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
			<td>10010</td>
			<td>虎货轩</td>
			<td>13838383838</td>
			<td>黑名单</td>
			<td>250+38+2</td>
		</tr>
		<tr>
			<td><input id="allchecked" type="checkbox" onclick="check();"></td>
			<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
			<td>10086</td>
			<td>三皮兄</td>
			<td>15252525252</td>
			<td>正常</td>
			<td>2080</td>
		</tr>
		<tr>
			<td><input id="allchecked" type="checkbox" onclick="check();"></td>
			<td><img src="http://www.baidu.com/img/baidu_jgylogo3.gif"></td>
			<td>10000</td>
			<td>陈屌丝</td>
			<td>15222222222</td>
			<td>正常</td>
			<td>1080</td>
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