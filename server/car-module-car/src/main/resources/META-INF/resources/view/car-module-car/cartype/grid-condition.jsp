<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${records }" var="item" varStatus="status">
	<tr>
		<td>${item.id}</td>
		<td>${item.icon}</td>
		<td>${item.name}</td>
		<td>${item.category}</td>
		<td>${item.seating}</td>
		<td>${item.tankCapacity}</td>
		<td>${item.drivingRange}</td>
	</tr>
</c:forEach>
${msg}