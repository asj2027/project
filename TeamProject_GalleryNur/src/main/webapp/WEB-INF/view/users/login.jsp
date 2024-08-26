<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
      href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Water+Brush&display=swap"
      rel="stylesheet"
/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous"
    />
<title>로그인 - Gallery nur</title>
</head>
<style>
html,
body {
  height: 100%;
}
h1, label, p, form {
	  font-family: "Noto Sans KR", sans-serif;
}
.FieldError {
	color:red;
}
body {
  display: flex;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-signin .checkbox {
  font-weight: 400;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
#findUserId:hover, #findUserPwd:hover {
    cursor:pointer;
}
.page-title {
    border-top: 10px solid black;
    border-bottom : 10px solid black;
    margin-bottom : 2rem;
}
.page-title h1{
    padding : 2rem;
    text-align : center;
}
</style>
<body class="text-center">
<div class="container">
    <header class="blog-header">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="text-center">
                <a href="/"><img src="/logo.png" id="logo" style="height: 80px"/></a>
            </div>
        </div>
        <br /><br /><br />
    </header>
<main class= "form-signin">
<form action="./kakaologin.do" method="post" name="lfrm" hidden>
    <input type="text" name="kakaoemail" id="kakaoemail" value="" />
</form>

<form:form id = "frmAdd" action = "/login" method = "POST" modelAttribute = "user">
	<h1 class="h3 mb-3 fw-normal">로그인</h1>
    <div class="form-floating">
      <form:input path = "id" class="form-control" id="floatingInput" placeholder="name@example.com" />
      <form:errors path = "id" class = "FieldError"/>
      <label for="floatingInput">아이디를 입력하세요</label>
    </div>
    <div class="form-floating">
      <form:password path = "password" class="form-control" id="floatingPassword" placeholder="Password" />
      <form:errors path = "password" class = "FieldError"/>
      <label for="floatingPassword">비밀번호를 입력하세요</label>
    </div>
    <div>
        <span id = "findUserId" class = "col-6 px-2">아이디 찾기</span>
        <span id = "findUserPwd" class = "col-6 px-2">비밀번호 찾기</span>
    </div>
    <label class = "FieldError">${loginFail}</label>
    <button class="w-100 btn btn-lg btn-primary" type="submit" >로그인</button>
    <div class="form-floating">
        <div><b>or</b></div>
    </div>
    <div class="form-floating" id="kakaologin">
        <div class="kakaobtn">
            <a href="https://kauth.kakao.com/oauth/authorize?client_id=11afdf6f295b3f272c88971d1ea73cdd&redirect_uri=http://nurgallery.shop/auth/kakao/callback&response_type=code">
                <img src="/loginButton.png" width="300px" height="47px"/>
            </a>
        </div>
        <div class="form-floating" id="googleLogin">
            <div class="googlebtn">
                <a href="/google/login">
                    <img src="/googleLogin.png" width="300px" height="47px" style="margin-top: 8px; border-radius: 5px;"/>
                </a>
            </div>
        </div>
    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
</div>
</form:form>
</main>
</div>
<c:if test = "${error ne null}">
    <script>
        alert("${error}");
    </script>
</c:if>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script>
    $(document)
    .on("click", "#findUserId", function() {
        window.open("/findMyId", "asdsad", width="44rem", height="20rem");
    })
    .on("click" , "#findUserPwd", function() {
        window.open("/findMyPwd", "asdas", width = "30rem", height = "15rem");
    })
</script>
</html>