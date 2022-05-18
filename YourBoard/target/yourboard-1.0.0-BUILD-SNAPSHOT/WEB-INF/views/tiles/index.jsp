
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix = "tiles" uri = "http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title" /></title>
<link rel="stylesheet" href="/resources/css/tiles.css" />
​
</head>
<body>
<tiles:insertAttribute name="header" />
<div class="container">
<tiles:insertAttribute name="aside" />
<tiles:insertAttribute name="body" /> 
</div>
<tiles:insertAttribute name="footer" />
​
</body>
</html>
