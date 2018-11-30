<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<html xmlns:th="http://www.thymeleaf.org">
<head>
	<title>All Products</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h1> PRODUCTOS </h1>
		</div>
	</div>
	<div class="row">
		<div class="col-md-8">
		<table class="table">
				<thead>
					<tr>
						<th> Nombre </th>
						<th> Descripción </th>
					</tr>
				</thead>
				<tbody>
				
				<tr th:each="prod: ${products}">
      <td th:text="${prod.nombre}"></td>
    </tr>
				
				
					<%-- <tr <c:if test="${not empty products}">
						<td colspan="2"> No Books Available </td>
					</tr> --%>
				
					
			
				<c:if test="${not empty products}">
			<c:forEach var="listValue" items="${products}">
				<tr> 
						<td>${listValue.nombre}</td>
						<td>${listValue.descripcion}</td>
						
						</c:forEach>
					</tr>
					
					</c:if>
				</tbody>
			</table>
			
		

		

	
			
			
		</div>
	<%-- 	<div class="col-md-6">
			<a class="btn btn-success" href="#" th:href="@{/books/create}"> Add Books </a>
			<a class="btn btn-primary" href="#" th:href="@{/books/edit}" th:classappend="${books.empty} ? 'disabled' : ''"> Edit Books </a>
		</div> --%>
	</div>
</div>
</body>
</html>