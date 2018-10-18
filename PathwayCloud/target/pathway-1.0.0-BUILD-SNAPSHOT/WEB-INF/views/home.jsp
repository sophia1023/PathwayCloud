<%@page import="snu.bioinfo.pathway.controller.HomeController"%>
<%@ page session="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"  %>  


<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<meta name="viewport" content="width=device-width, initail-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script   src="https://code.jquery.com/jquery-3.2.1.min.js"   integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="  crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="margin:10px">
<%@ include file="header.jsp" %>
<%@ include file="nav.jsp" %>
<h1 style="margin:40px 60px 40px 60px; font-weight:bold;" class="text-primary">Hello!:)</h1>
<div style="margin:40px 60px 40px 60px;">
	<div style="font-size:13pt">This web-based tool is to infer pathway activity values out of input gene expression data. <br/>
	 It provides 13 previously published tools to be run with conditions applied.<br/>
	 Users can also be provided which of the tools are adequate for their scope of analysis and also download the result to local storage.
	 </div><br/>
	 <div style="text-align:center">
	 	<img src="resources/images/mainFig.png"/>
	 </div>
</div>
</body>
</html>
