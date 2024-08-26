<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-07-28
  Time: 오후 2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>비밀번호 찾기 - Gallery nur</title>
</head>
<style>
    @import url(https://fonts.googleapis.com/css?family=Roboto:300);

    .login-page {
        width: 360px;
        padding: 8% 0 0;
        margin: auto;
    }
    .form {
        position: relative;
        z-index: 1;
        background: #ffffff;
        max-width: 360px;
        margin: 0 auto 100px;
        padding: 45px;
        text-align: center;
        box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
    }
    .form input {
        font-family: "Roboto", sans-serif;
        outline: 0;
        background: #f2f2f2;
        width: 100%;
        border: 0;
        margin: 0 0 15px;
        padding: 15px;
        box-sizing: border-box;
        font-size: 14px;
    }
    #submitMail {
        font-family: "Roboto", sans-serif;
        text-transform: uppercase;
        outline: 0;
        background: #4caf50;
        width: 100%;
        border: 0;
        padding: 15px;
        color: #ffffff;
        font-size: 14px;
        cursor: pointer;
    }
    .form button:hover,
    .form button:active,
    .form button:focus {
        background: #43a047;
    }
    .container {
        position: relative;
        z-index: 1;
        max-width: 300px;
        margin: 0 auto;
    }
    .container:before,
    .container:after {
        content: "";
        display: block;
        clear: both;
    }
    .container .info {
        margin: 50px auto;
        text-align: center;
    }
    .container .info h1 {
        margin: 0 0 15px;
        padding: 0;
        font-size: 36px;
        font-weight: 300;
        color: #1a1a1a;
    }
    .container .info span {
        color: #4d4d4d;
        font-size: 12px;
    }
    .container .info span a {
        color: #000000;
        text-decoration: none;
    }
    .container .info span .fa {
        color: #ef3b3a;
    }
    body {
        background: #76b852; /* fallback for old browsers */
        background: rgb(141, 194, 111);
        background: linear-gradient(
                90deg,
                rgba(141, 194, 111, 1) 0%,
                rgba(118, 184, 82, 1) 50%
        );
        font-family: "Roboto", sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }

</style>
<body>
<div class="login-page">
    <div class="form" id = "inside">
            <input type="email" id = "userEmail" placeholder="이메일을 입력하세요"/>
        <input type="text" id = "userId" placeholder="아이디를 입력하세요"/>
        <input type="text" id = "verifyNum" placeholder="인증번호 입력"/>
            <input type = "button" id = "submitMail" value = "인증번호 받기"/>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
    $(document)
    .on("click", "#submitMail", function() {
        if ($("#userEmail").val() == '' || $("#userId").val() == '') {
            alert("입력란을 채워주세요.");
        }
        else if ($("#submitMail").val() == '인증번호 받기') {
            $.ajax({
                type : "POST",
                url : "/findMyPwd",
                data : {email : $("#userEmail").val(), userId : $("#userId").val()},
                dataType : "text",
                success : function(data) {
                    console.log(data);
                    if (data == 'false') {
                        alert("등록된 이메일 또는 아이디가 아닙니다.");
                    }
                    else {
                        $("#submitMail").val("인증번호 입력");
                        $.ajax({
                            type : "POST",
                            url : "/findMyId/Email",
                            data : {email : $("#userEmail").val()},
                            dataType : "text",
                            success : function(data) {
                                console.log(data);
                            }
                        })
                        alert("해당 메일로 인증 번호를 발송하였습니다.");
                    }
                }
            })
        } else if ($("#submitMail").val() == "인증번호 입력") {
            if ($("#verifyNum").val() == '')
            {
                alert("인증번호를 입력해주세요");
            }
            else {
                $.ajax({
                    type: "POST",
                    url: "/findMyId/Email/code",
                    data: {code: $("#verifyNum").val()},
                    success: function (data) {
                        console.log("입력된 값 =" + data);
                        let tempPassword = Math.random().toString(36).slice(2);
                        console.log(tempPassword);
                        if (data == "true") {
                            $.ajax({
                                type : "POST",
                                url : "/findMyPwd/temp",
                                data : {userId : $("#userId").val(), password : tempPassword},
                                dataType : "text",
                                success : function(data) {
                                    console.log(data);
                                    $("#inside").empty();
                                    $("#inside").append(`<label>임시 비밀번호는 \${tempPassword}입니다.</label>`);
                                }
                            })
                        } else {
                            alert("인증번호가 틀렸습니다. 다시 입력해주세요 (-) 제외하고 입력");
                        }
                    }
                })
            }
        }
        })
</script>
</html>
