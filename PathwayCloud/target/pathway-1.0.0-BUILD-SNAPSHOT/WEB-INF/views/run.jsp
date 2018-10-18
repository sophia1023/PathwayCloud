<%@page import="snu.bioinfo.pathway.domain.ToolVO"%>
<%@page import="snu.bioinfo.pathway.controller.*"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Run</title>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<meta name="viewport" content="width=device-width, initail-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script   src="https://code.jquery.com/jquery-3.2.1.min.js"   integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="  crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		$(document).ready(function(){
			var pCheckRes = $("#pCheckRes").val();
			if(pCheckRes == "fail"){
				alert("�ش� ������Ʈ ���� �����մϴ�. �ٸ� �̸��� �Է��� �ּ���!");
			} 
			else if(pCheckRes == "done"){
				alert("������Ʈ�� ���������� �����Ǿ����ϴ�!");
			}
		});
	
	</script>
</head>
<body style="margin:10px">
<%@ include file="header.jsp" %>
<%@ include file="nav.jsp" %>
<h1 style="margin:20px 20px 30px 20px; font-weight:bold;">�� Run Tools</h1>
<div style="margin:40px 60px 40px 60px;">
	<div style="font-size:18pt; font-weight:bold;" class="text-primary">Set up Project</div>
	<form  class="border" style="font-size:13pt; margin:10px 10px 0px 10px" name="makeProject" id="makeProject" action="run" method="post">
		<input style="width:300px" type="text" name="projectName" placeholder="������� ����� �Է����ּ���."/>
		<input type="hidden" name="pCheckResult" id="pCheckRes" value="${pCheckRes}"/>
		<button class="btn btn-success" type="submit">SET UP</button>
	</form>
	<hr/>
	<div style="font-size:18pt; font-weight:bold;" class="text-primary">Select Run Option</div>
	<form  class="border" style="font-size:13pt; margin:10px" name="toolOption" id="toolOption" action="run/toolOption" method="post" enctype="multipart/form-data">
		<label style="font-size:15pt">&gt; input Project Name</label><br/>
		<input style="width:300px; margin-left:20px;" type="text" name="projectName" placeholder="������ ������Ʈ���� �Է��ϼ���."/><br/><br/>
		
		<label style="font-size:15pt">&gt; input User Email</label><br/>
		<input style="width:300px; margin-left:20px;" type="text" name="userEmail" placeholder="�̸����� �Է����ּ���."/><br/><br/>
		
		<label style="font-size:15pt">&gt; input Gene expression File (.CSV)</label><br/>
		<input style="margin-left:20px;" type="file" name="inputFiles" /><br/>
		
		<label style="font-size:15pt">&gt; input Sample information File (.CSV)</label><br/>
		<input style="margin-left:20px;" type="file" name="inputFiles" /><br/>
		
		<label style="font-size:15pt">&gt; select Tool</label><br/>
		<input style="margin-left:20px;" type="checkbox" name="toolSet" value="GSVA"/>&nbsp;GSVA &nbsp;&nbsp;
		<input type="checkbox" name="toolSet" value="DART"/>&nbsp;DART &nbsp;&nbsp;
		<input type="checkbox" name="toolSet" value="ESEA"/>&nbsp;ESEA &nbsp;&nbsp;
		<input type="checkbox" name="toolSet" value="LLR"/>&nbsp;LLR &nbsp;&nbsp;
		<input type="checkbox" name="toolSet" value="PADOG"/>&nbsp;PADOG &nbsp;&nbsp;
		<input type="checkbox" name="toolSet" value="PADOG"/>&nbsp;PADOG_prepare &nbsp;&nbsp;
		<input type="checkbox" name="toolSet" value="PathAct"/>&nbsp;PathAct &nbsp;&nbsp;
		<input type="checkbox" name="toolSet" value="Pathifier"/>&nbsp;Pathifier &nbsp;&nbsp;
		<input type="checkbox" name="toolSet" value="SAS"/>&nbsp;SAS &nbsp;&nbsp;
		<input type="checkbox" name="toolSet" value="SAS"/>&nbsp;Test	       
		<br/><br/>
		
		<button class="btn btn-success" type="submit">START</button>
	</form>
</div>
</body>
</html>