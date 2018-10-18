<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<title>RunStartResult</title>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<meta name="viewport" content="width=device-width, initail-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"   integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="  crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="margin:10px">
<%@ include file="header.jsp" %>
<%@ include file="nav.jsp" %>
<div Style="position:relative; text-align:center; margin-top:10%; font-size:13pt;" class="border border-primary">
	<div>Tool이 성공적으로 실행되었습니다.</div>
	<div>일정 시간이 지난 후 결과를 확인 하실 수 있습니다.</div>
	<div>완료 시 입력하신 메일로 알려드리겠습니다.</div><br/>
	<a class="btn btn-info" href="home">홈으로 돌아가기</a>
</div>
</body>
</html>