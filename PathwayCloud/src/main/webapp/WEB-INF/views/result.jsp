<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Result</title>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<meta name="viewport" content="width=device-width, initail-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script   src="https://code.jquery.com/jquery-3.2.1.min.js"   integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="  crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="margin:10px">
<%@ include file="header.jsp" %>
<%@ include file="nav.jsp" %>
<h1 style="margin:40px 60px 40px 60px; font-weight:bold;">▶ Show Result</h1>
<div style="margin:40px 60px 40px 60px;">
	<div id="resultOptionBox">
		<form>
			<label style="font-size:18pt" class="text-primary">Input Project Name</label><br/>
			<input style="width:300px;" type="text" name="projectName" placeholder="생성한 프로젝트명을 입력하세요."/><br/><br/>
			
			<label style="font-size:18pt" class="text-primary">Select Tool</label><br/>
			<input type="radio" name="toolSet" value="GSVA"/>&nbsp;GSVA &nbsp;&nbsp;
			<input type="radio" name="toolSet" value="DART"/>&nbsp;DART &nbsp;&nbsp;
			<input type="radio" name="toolSet" value="ESEA"/>&nbsp;ESEA &nbsp;&nbsp;
			<input type="radio" name="toolSet" value="LLR"/>&nbsp;LLR &nbsp;&nbsp;
			<input type="radio" name="toolSet" value="PADOG"/>&nbsp;PADOG &nbsp;&nbsp;
			<input type="radio" name="toolSet" value="PADOG"/>&nbsp;PADOG_prepare &nbsp;&nbsp;
			<input type="radio" name="toolSet" value="PathAct"/>&nbsp;PathAct &nbsp;&nbsp;
			<input type="radio" name="toolSet" value="Pathifier"/>&nbsp;Pathifier &nbsp;&nbsp;
			<input type="radio" name="toolSet" value="SAS"/>&nbsp;SAS	       
			<br/><br/>
			
			<button class="btn btn-success" type="submit">SHOW</button>
		</form>
	</div>
	<hr/>
	<div id="resultBox">
		<div id="result1Box">
			<div id="result1Info">
				<p style="font-size:15pt; font-weight:bold" class="text-info">&gt; Figure1</p>
				<p style="font-size:13pt; margin-left:20px;">comparison between CONTROL vs CASE TCGA COAD samples of the pathway activity values from'GSVAmax' tool.<Br/>X-axis indicates -log10 transformed FDR value of two-sample t-test.</p>				
			</div><br/>
			<div style="text-align:center" id="result1">
				<img style="width:30%;" src="resources/images/fig1.png"/>
			</div>
		</div><br/><br/>
		<div id="result2Box">
			<div id="result2Info">
				<p style="font-size:15pt; font-weight:bold" class="text-info">&gt; Figure2</p>
				<p style="font-size:13pt; margin-left:20px;">Boxplot of activity values for the pathway 'hsa03013' among CONTROL and CASE samples using the tool 'GSVAmax'.</p>
			</div><br/>
			<div style="text-align:center" id="result2">
				<img style="width:30%;" src="resources/images/fig2.png"/>
			</div>
		</div><br/><br/>
		<div id="result3Box">
			<div id="result3Info">
				<p style="font-size:15pt; font-weight:bold" class="text-info">&gt; Figure3</p>
				<p style="font-size:13pt; margin-left:20px;">Comparison of the level of similarity between pathway activity values from 13 tools run using the same COAD samples and the input data.<Br/>Greater value indicates that the structure of corresponding pathway activity values is less distant to that of the original input matrix.</p>
			</div><br/>
			<div style="text-align:center" id="result3">
				<img style="width:50%;" src="resources/images/fig3.png"/>
			</div>
		</div><br/><br/>
		<div id="result4Box">
			<div id="result4Info">
				<p style="font-size:15pt; font-weight:bold" class="text-info">&gt; CSV File Download</p>
			</div>
			<div id="result4">
				<p style="font-size:13pt; margin-left:20px;">☞<a href="none"> Link_for_GSVAmax.csv.gz</a></p>
			</div>
		</div><br/><br/>
	</div>
</div>

</body>
</html>