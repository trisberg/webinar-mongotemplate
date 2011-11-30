<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Mongo Bookshelf :: Spring Data MongoDB Demo</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/javascript/dijit/themes/tundra/tundra.css" />" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/screen.css" />" type="text/css" media="screen, projection" />
	<link rel="stylesheet" href="<c:url value="/resources/styles/blueprint/print.css" />" type="text/css" media="print" />
	<!--[if lt IE 8]>
	        <link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection" />
	<![endif]-->
	<link rel="stylesheet" href="<c:url value="/resources/styles/travel.css" />" type="text/css" media="screen" />
	<script type="text/javascript" src="<c:url value="/resources/javascript/dojo/dojo.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/spring/Spring.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/javascript/spring/Spring-Dojo.js" />"></script>
</head>
<body class="tundra">
<div id="page" class="container">
	<div id="header">
		<div id="topbar">
			<p>
			</p>
		</div>
		<div id="logo">
			<p>
				<a href="<c:url value="/" />">
					<img src="<c:url value="/resources/images/header.jpg"/>" alt="Spring Travel" />
				</a>
			</p>
		</div>
	</div>
	<div id="content">
		<div id="main" class="span-18 last">

			<!-- START PAGE CONTENT-->
			<h1>
				Search for Books  
			</h1>

<div id="searchForm">
	<div class="span-18">
		<spring:hasBindErrors name="search">
			<div class="error">
				<spring:bind path="search.*">
					<c:forEach items="${status.errorMessages}" var="error">
						<c:out value="${error}"/><br/>
					</c:forEach>
				</spring:bind>
			</div>
		</spring:hasBindErrors>
		<form:form modelAttribute="search">
			<fieldset>
				<legend>Search Criteria</legend>
				<div>
					<div class="span-3">
						<label for="categories">Categories to include:</label>
					</div>
					<div class="span-4 last">
						<p><form:select path="categories" multiple="true" items="${categories}"/></p>
					</div>
					<div class="span-3">
						<label for="startYear">Earliest year published:</label>
					</div>
					<div class="span-4 last">
						<p><form:select path="startYear" multiple="false" items="${years}"/></p>
					</div>
					<p>
					<button type="submit" id="proceed" name="_proceed">Search</button>
					</p>
				</div>
			</fieldset>
		</form:form>	
	</div>
</div>
			<br/>
			<br/>
			<table class="summary">
				<thead>
					<tr>
						<th>Title</th>
						<th>Author</th>
						<th>ISBN</th>
						<th>Price</th>
						<th>Published</th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${bookList}">
						<tr>
							<td>${book.title}</td>
							<td>${book.author.name}</td>
							<td>${book.isbn}</td>
							<td>${book.price}</td>
							<td><fmt:formatDate value="${book.published}" type="both" pattern="MM/dd/yyyy" /></td>
							<td><a href="edit/${book.isbn}">Edit Book</a></td>
						</tr>
					</c:forEach>
					<c:if test="${empty bookList}">
						<tr>
							<td colspan="6">No books found</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<br/>
			<br/>
			<a href="<c:url value="/" />">Home</a>
			<br/>
			<br/>
			<br/>

			<!-- END PAGE CONTENT-->

		</div>
	</div>
	<hr />
	<div id="footer">
	</div>
</div>
</body>
</html>