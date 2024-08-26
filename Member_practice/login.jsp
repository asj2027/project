<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<title>로그인</title>
</head>
${alert}
<style>
@font-face {
     font-family: 'S-CoreDream-4Regular';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}
h1 {font-weight:bold;}
body {background-color:lavender;
	  font-family: 'S-CoreDream-4Regular';}
#id, #pw {width:270px;}
</style>
<body align=center>
<br><h1>로그인</h1>
<form method=POST action='check'>
<div class="container-fluid row justify-content-center align-items-center">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=text class="form-control" name=id id=id><br></div>
<div class="container-fluid row justify-content-center align-items-center">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=password class="form-control" name=pw id=pw><br><br><br></div>
<input type=submit class="btn btn-outline-primary" value='로그인'>
</form>
</body>
</html>