<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				Edit a Book  
			</h1>

<div id="bookForm">
	<div class="span-12">
		<spring:hasBindErrors name="book">
			<div class="error">
				<spring:bind path="book.*">
					<c:forEach items="${status.errorMessages}" var="error">
						<c:out value="${error}"/><br/>
					</c:forEach>
				</spring:bind>
			</div>
		</spring:hasBindErrors>
		<form:form modelAttribute="book">
			<fieldset>
				<legend>Book</legend>
		        <div>
					<div class="span-3">
						<label for="title">Title:</label>
					</div>
					<div class="span-7 last">
						<p><form:input path="title"/></p>
					</div>
				</div>
				<div>
					<div class="span-3">
						<label for="author.name">Author:</label>
					</div>
					<div class="span-7 last">
						<p><form:input path="author.name"/></p>
					</div>
				</div>
				<div>
					<div class="span-3">
						<label for="isbn">ISBN:</label>
					</div>
					<div class="span-7 last">
						<p><form:input readonly="true" path="isbn"/></p>
					</div>
				</div>
				<div>
					<div class="span-3">
						<label for="price">Price:</label>
					</div>
					<div class="span-7 last">
						<p><form:input path="price"/></p>
					</div>
				</div>
				<div>
					<div class="span-3">
						<label for="published">Published:</label>
					</div>
					<div class="span-7 last">
						<p><form:input path="published"/></p>
						<script type="text/javascript">
							Spring.addDecoration(new Spring.ElementDecoration({
								elementId : "published",
								widgetType : "dijit.form.DateTextBox",
								widgetAttrs : { datePattern : "MM-dd-yyyy", required : true }}));  
						</script>
					</div>
				</div>
				<div>
					<div class="span-3">
						<label for="categories">Categories:</label>
					</div>
					<div class="span-7 last">
						<p><form:select path="categories" multiple="true" items="${categories}"/></p>
					</div>
				</div>
				<div>
					<p>
					<button type="submit" id="proceed" name="_proceed">Save</button>
					<button type="submit" name="_delete" >Delete</button>
					<button type="submit" name="_cancel" >Cancel</button>
					</p>
				</div>
			</fieldset>
		</form:form>	
	</div>
</div>
			<br/>
			<br/>
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