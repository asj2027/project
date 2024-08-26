<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<title>회원가입</title>
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
#id, #pw, #pw1 {width:240px;}
</style>
<body align=center>
<br><h1>회원가입</h1>
<form id=frmSignin method=POST action='addMember'>
<div class="container-fluid row justify-content-center align-items-center">
&nbsp;&nbsp;&nbsp;아이디: &nbsp;<input type=text class="form-control" id=id name=id><br><br></div>
<div class="container-fluid row justify-content-center align-items-center">
비밀번호:&nbsp;&nbsp;<input type=password class="form-control" name=pw id=pw><br><br></div>
<div class="container-fluid row justify-content-center align-items-center">
비밀번호 확인:&nbsp;<input type=password class="form-control" name=pw1 id=pw1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br></div>
<br><input type=submit class="btn btn-outline-success" value='회원가입'>
</form>
</body>
<script src='https://code.jquery.com/jquery-3.4.1.js'></script>
<script>
$(document)
.on('submit','#frmSignin',function() {
	if($('#pw').val()!=$('#pw1').val()) {
		alert('비밀번호가 일치하지 않습니다.');
		return false;
	}
	return true;
})
</script>
</html>