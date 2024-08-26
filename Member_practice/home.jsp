<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<title>Home</title>
</head>
${alert}
<style>
@font-face {
     font-family: 'S-CoreDream-4Regular';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}
body {background-color:lavender;
	  font-family: 'S-CoreDream-4Regular';}
table {border-collapse:collapse;
	   text-align:center;
	   border:1px solid black;
	   background-color:white;
}
h1 {font-weight:bold;}
.hidden-col {display:none;}
</style>
<body align=center>
<c:if test="${userinfo==''}">
<p><a href='login' style='font-size:15pt;'>로그인</a>&nbsp;&nbsp;<a href='signin' style='font-size:15pt;'>회원가입</a></p>
</c:if>
<c:if test="${userinfo!=''}">
<p align=center style='font-size:15pt;'>${userinfo} 회원님, 환영합니다.&nbsp;<a href='logout'>로그아웃</a>&nbsp;&nbsp;</p>
</c:if>
<h1>게시판</h1>
<br>
<table align=center class="table table-hover" style='width:70%'>
<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성날짜</th><c:if test="${userinfo!=''}"><th>수정/삭제</th></c:if></tr>
<c:forEach var="boardDTO" items="${list}">
<tr><td><a href='view?seqbbs=${boardDTO.seqbbs}'>${boardDTO.seqbbs}</a></td><td>${boardDTO.title}</td>
	<td class='hidden-col'>${boardDTO.content}</td>
	<td>${boardDTO.writer}</td><td>${boardDTO.created}</td>
	<c:if test="${userinfo!=''}">
	<td><a id='a_view' href='up?seqbbs=${boardDTO.seqbbs}'>수정</a>&nbsp;
		<a id='a_del' href='delete/${boardDTO.seqbbs}'>삭제</a></td></c:if></tr>
</c:forEach>
</table>
<c:if test="${userinfo!=''}">
<br>
<a href='newpost'>새글쓰기</a>
</c:if>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
$(document)
.on('click','a',function() {
	if(this.id=='a_del') {
		if(!confirm('정말 삭제하시겠습니까?')) return false;
	}
	return true;
})
</script>
</html>
