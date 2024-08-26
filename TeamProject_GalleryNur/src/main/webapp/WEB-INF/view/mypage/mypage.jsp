<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>
        내 정보 - Gallery nur
    </title>
</head>
<style>
    #history {
        background-color: gainsboro;

    }

    table {
        border-collapse: separate;
        border-spacing: 0 10px;
    }

    .nav-item {
        list-style-type: none;
        font-size: 55px;
    }

    .nav-link {
        font-weight: 600;
        color: #000000;
        padding: 0 0.3125rem;
        font-size: 20px;
        font-size: 1.25rem;
    }

    .nav-link:hover {
        color: darkgrey;
    }

    #detail {
        font-size: 0.7rem;
    }

    .page-title {
        border-top: 10px solid black;
        border-bottom: 10px solid black;
        margin-bottom: 2rem;
    }

    .page-title h1 {
        padding: 2rem;
        text-align: center;
    }

    body {
	font-family: 'HallymGothic-Regular';    }

@font-face {
	font-family: 'HallymGothic-Regular';
	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2204@1.0/HallymGothic-Regular.woff2') format('woff2');
	font-weight: 400;
	font-style: normal;
}

    a {
        text-decoration: none;
        color: black;
    }

    a {
        text-decoration-line: none;
    }

    #comment, #post {
        text-align: center;
    }

    .FieldError {
        color: red;
    }

    .page-link {
        cursor: pointer;
    }
</style>
<body>
<div class="container">
    <header class="blog-header py-3" style="height: 230px">
        <div class="row flex-nowrap justify-content-between align-items-center">
            <div class="text-center">
                <!-- 상단에 메인로고 출력 -->
                <a href="/"><img src="/logo.png" id="logo" style="height: 80px"/></a>
            </div>
        </div>
        <br/><br/><br/>
        <div class="nav-scroller mb-7" id="list">
            <ul class="nav justify-content-center">
                <li class="nav-item mx-5">
                    <!-- 클릭 시 가장 첫 메뉴인 인사말로 이동 -->
                    <a class="nav-link active p-7" aria-current="page" href="/letter" id="nav1">
                        <spring:message code="About"></spring:message>
                    </a>
                    <div>
                        <ul class="nav justify-content-end" style="display: none" id="none1">
                            <li class="nav-item">
                                <!-- 클릭 시 인사말로 이동 -->
                                <a class="nav-link" aria-current="page" href="/letter" id="detail">
                                    <spring:message code="letter"></spring:message>
                                </a>
                            </li>
                            <li class="nav-item">
                                <!-- 클릭 시 오시는 길로 이동 -->
                                <a class="nav-link" aria-current="page" href="/visit" id="detail">
                                    <spring:message code="Visit"></spring:message>
                                </a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="nav-item mx-5">
                    <!-- 클릭 시 전시회 목록으로 이동 -->
                    <a class="nav-link" href="/exhibit" id="nav2">
                        <spring:message code="Exhibition"></spring:message>
                    </a>
                </li>
                <li class="nav-item mx-5">
                    <!-- 클릭 시 아티스트 목록으로 이동 -->
                    <a class="nav-link" href="/artist" id="nav3">
                        <spring:message code="artist"></spring:message>
                    </a>
                </li>
                <li class="nav-item mx-5">
                    <!-- 클릭 시 가장 첫 메뉴인 리뷰게시판으로 이동 -->
                    <a class="nav-link" href="/review" id="nav4">
                        <spring:message code="Post"></spring:message>
                    </a>
                    <ul class="nav justify-content-end" style="display: none" id="none2">
                        <li class="nav-item">
                            <!-- 클릭 시 리뷰게시판으로 이동 -->
                            <a class="nav-link" aria-current="page" href="/review" id="detail">
                                <spring:message code="Review"></spring:message>
                            </a>
                        </li>
                        <li class="nav-item">
                            <!-- 클릭 시 질문게시판으로 이동 -->
                            <a class="nav-link" href="/qna" id="detail">
                                <spring:message code="QNA"></spring:message>
                            </a>
                        </li>
                        <li class="nav-item">
                            <!-- 클릭 시 FAQ게시판으로 이동 -->
                            <a class="nav-link" href="/FAQ" id="detail">
                                <spring:message code="FAQ"></spring:message>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="nav-item mx-5">
                    <!-- 로그인이 되어 있지 않을 시 로그인으로 이동 -->
                    <c:if test="${user.id == null}">
                        <a class="nav-link" aria-current="page" href="/login" id="nav5">
                            <spring:message code="Member"></spring:message>
                        </a>
                    </c:if>
                    <!-- 로그인한 유저의 역할이 방문자일 시 마이페이지로 이동 -->
                    <c:if test="${user.id != null && user.role == '방문자'}">
                        <a class="nav-link" aria-current="page" href="/mypage" id="nav5">
                            <spring:message code="Mypage"></spring:message>
                        </a>
                    </c:if>
                    <!-- 로그인한 유저의 역할이 관리자일 시 유저관리 메뉴로 이동 -->
                    <c:if test="${user.id != null && user.role == '관리자'}">
                        <a class="nav-link" aria-current="page" href="/listuser" id="nav5">
                            <spring:message code="Admin"></spring:message>
                        </a>
                    </c:if>
                    <ul class="nav justify-content-end" style="display: none" id="none3">
                        <!-- 로그인이 되어 있지 않을 시 로그인 버튼, 회원가입 버튼만 노출 -->
                        <c:if test="${user.id == null}">
                            <li class="nav-item">
                                <!-- 클릭 시 로그인으로 이동 -->
                                <a class="nav-link" aria-current="page" href="/login" id="detail">
                                    <spring:message code="Login"></spring:message>
                                </a>
                            </li>
                            <li class="nav-item">
                                <!-- 클릭 시 회원가입으로 이동 -->
                                <a class="nav-link" href="/signin" id="detail">
                                    <spring:message code="Signin"></spring:message>
                                </a>
                            </li>
                        </c:if>
                        <!-- 로그인이 되어 있을 시 로그아웃 버튼만 노출 -->
                        <c:if test="${user.id != null }">
                            <li class="nav-item">
                                <!-- 클릭 시 로그아웃 -->
                                <a class="nav-link" aria-current="page" href="/logout" id="detail">
                                    <spring:message code="Logout"></spring:message>
                                </a>
                            </li>
                        </c:if>
                        <!-- 로그인한 유저의 역할이 방문자일 시 마이페이지 메뉴만 노출 -->
                        <c:if test="${user.role == '방문자'}">
                            <li class="nav-item">
                                <a class="nav-link" href="/mypage" id="detail">
                                    <spring:message code="Mypage"></spring:message>
                                </a>
                            </li>
                        </c:if>
                        <!-- 로그인한 유저의 역할이 관리자일 시 회원관리, 예약관리 메뉴 노출 -->
                        <c:if test="${user.role == '관리자'}">
                            <li class="nav-item">
                                <!-- 클릭 시 회원관리 메뉴로 이동 -->
                                <a class="nav-link" href="/listuser" id="detail">회원관리</a>
                            </li>
                            <li class="nav-item">
                                <!-- 클릭 시 예약관리 메뉴로 이동 -->
                                <a class="nav-link" href="/listReserve" id="detail">예약관리</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/management" id="detail">갤러리 관리</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/reason" id="detail">탈퇴 사유</a>
                            </li>
                        </c:if>
                    </ul>
                </li>
                <li class="nav-item mx-5">
                    <!-- 클릭 시 공지로 이동 -->
                    <a class="nav-link" aria-current="page" href="/notice" id="nav6">
                        <spring:message code="Notice"></spring:message>
                    </a>
                </li>
            </ul>
        </div>
    </header>
</div>
<input type="hidden" id="role" value="${user.role}">
<input type="hidden" id="usernum" value="${user.userNum}">
<input type="hidden" id="userid" value="${user.id}">
<input type="hidden" id="currentCommentPage" value="">
<input type="hidden" id="pageMax" value="">
<input type="hidden" id="currentPostPage" value="">
<input type="hidden" id="postPageMax" value="">
<!-- main 안에다가 주 내용 작성할것 -->
<main class="container p-5">
    <div class="container">
        <div class="page-title">
            <h1>${user.id}(${user.role})님의 MY PAGE</h1>
        </div>
    </div>
    <ul class="nav justify-content-center" id="gry">
        <li class="nav-item col-2">
            <span class="nav-link" aria-current="page" id="cgv" style="cursor:hand;">예매 내역</span>
        </li>
        <li class="nav-item col-2">
            <span class="nav-link" aria-current="page" id="mmt" style="cursor:hand;">개인 정보 관리</span>
        </li>
        <li class="nav-item col-2">
            <span class="nav-link" aria-current="page" id="myPost" style="cursor:hand;">내가 작성한 글</span>
        </li>
        <li class="nav-item col-2">
            <span class="nav-link" aria-current="page" id="myComment" style="cursor:hand;">내가 작성한 댓글</span>
        </li>
        <li class="nav-item col-2">
            <span class="nav-link" aria-current="page" id="sec" style="cursor:hand;">회원 탈퇴</span>
        </li>
    </ul>
    <div id="acc">
    </div>
</main>

<!-- 하단 -->
<div class="container-fluid">
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
        <p class="col-md-4 mb-0 text-muted">&copy; 2022 Company, Inc</p>

        <a href="/"
           class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
            <svg class="bi me-2" width="40" height="32">
                <use xlink:href="#bootstrap"/>
            </svg>
        </a>

        <ul class="nav col-md-4 justify-content-end">
            <li class="nav-item">
                <!-- 클릭 시 메인 홈으로 이동 -->
                <a href="/" class="nav-link px-2 text-muted">Home</a>
            </li>
            <li class="nav-item">
                <!-- 클릭 시 갤러리 소개 페이지로 이동 -->
                <a href="/visit" class="nav-link px-2 text-muted">About</a>
            </li>
            <li class="nav-item">
                <!-- 클릭 시 공지 페이지로 이동 -->
                <a href="/notice" class="nav-link px-2 text-muted">Notice</a>
            </li>
            <li class="nav-item">
                <!-- 클릭 시 FAQ 페이지로 이동 -->
                <a href="/FAQ" class="nav-link px-2 text-muted">FAQ</a>
            </li>
        </ul>
    </footer>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script>
    $(document)
        .ready(function () {
            console.log("시작 화면");
            $("#nav1").hover(function () {
                $("#none1").css("display", "block");
                $("#none2").css("display", "none");
                $("#none3").css("display", "none");
            }, function () {
            })
            $("#nav2").hover(function () {
                $("#none1").css("display", "none");
                $("#none2").css("display", "none");
                $("#none3").css("display", "none");
            })
            $("#nav3").hover(function () {
                $("#none1").css("display", "none");
                $("#none2").css("display", "none");
                $("#none3").css("display", "none");
            })
            $("#nav4").hover(function () {
                $("#none1").css("display", "none");
                $("#none2").css("display", "block");
                $("#none3").css("display", "none");
            })
            $("#nav5").hover(function () {
                $("#none1").css("display", "none")
                $("#none2").css("display", "none");
                $("#none3").css("display", "block");
            })
            $("#nav6").hover(function () {
                $("#none1").css("display", "none")
                $("#none2").css("display", "none");
                $("#none3").css("display", "none");
            })
            $("#paymentTable").empty();
            $("#acc").empty();
            let str = `<br><br><h2 style="text-align: center">예매 내역</h2><hr>
                    <main class = "container p-5">
                      <div class = "container">
                      </div>
                        <div class="col-12 col-sm-12 text-center">
                            조회기간&nbsp;&nbsp;
                            <input type="button" class="btn btn-outline-secondary" id="btnToday" value = "오늘"></input>&nbsp;
                            <input type="button" class="btn btn-outline-secondary" id="btnWeek" value = "이번주"></input>&nbsp;
                            <input type="button" class="btn btn-outline-secondary" id="btnMonth" value = "이번달"></input>&nbsp;
                            <input type="date" id="date1">~
                            <input type="date" id="date2">&nbsp;
                            <input type="button" class="btn btn-outline-secondary" id="selectPayment" style="font-weight: bold;" value = "조회"></input>
                        </div>
                        <br><br>
                      <div class = "container" id = "boardList">
                        <div class = "row">
                          <table id = "paymentTable">

                          </table>
                          <p></p>
                          <p></p>
                          <p></p>
                        </div>
                      </div>
                        <div id="history" style="display: none;" align="center">
                        <p style="margin-top: 40px;"></p>
                        <h2>예매내역</h2><br>
                        <table id="tb1" class="table table-striped text-center">
                        </table>
                        </div>
                    </main>
                    `;
            $("#acc").append(str);
            getPaymentHistory();
            $('#history').dialog({
                resizable: false,
                autoOpen: false,
                width: 600,
                height: 400,
                open: function () {
                    $(".ui-dialog-titlebar-close", $(this).parent()).hide();
                },
                close: function () {
                    $("#paymentTable").empty();
                    getPaymentHistory();
                }
            });
        })
</script>
<script
        src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js">
</script>
<script>
    $(document)
        .on('click', '#cgv', function () {
            $("#paymentTable").empty();
            $("#acc").empty();
            let str = `<br><br><h2 style="text-align: center">예매 내역</h2><hr>
                    <main class = "container p-5">
                      <div class = "container">
                      </div>
                        <div class="col-12 col-sm-12 text-center">
                            조회기간&nbsp;&nbsp;
                            <input type="button" class="btn btn-outline-secondary" id="btnToday" value = "오늘"></input>&nbsp;
                            <input type="button" class="btn btn-outline-secondary" id="btnWeek" value = "이번주"></input>&nbsp;
                            <input type="button" class="btn btn-outline-secondary" id="btnMonth" value = "이번달"></input>&nbsp;
                            <input type="date" id="date1">~
                            <input type="date" id="date2">&nbsp;
                            <input type="button" class="btn btn-outline-secondary" id="selectPayment" style="font-weight: bold;" value = "조회"></input>
                        </div>
                        <br><br>
                      <div class = "container" id = "boardList">
                        <div class = "row">
                          <table id = "paymentTable">

                          </table>
                          <p></p>
                          <p></p>
                          <p></p>
                        </div>
                      </div>
                        <div id="history" style="display: none;" align="center">
                        <p style="margin-top: 40px;"></p>
                        <h2>예매내역</h2><br>
                        <table id="tb1" class="table table-striped text-center">
                        </table>
                        </div>
                    </main>
                    `;
            $("#acc").append(str);
            getPaymentHistory();
            $('#history').dialog({
                resizable: false,
                autoOpen: false,
                width: 600,
                height: 400,
                open: function () {
                    $(".ui-dialog-titlebar-close", $(this).parent()).hide();
                },
                close: function () {
                    $("#paymentTable").empty();
                    getPaymentHistory();
                }
            })
        })
    $(document)
        .on("click", "#btnToday", function () {
            let today = getToday();
            $.ajax({
                type: "POST",
                url: "/history/reserve/today",
                data: {userId: $("#usernum").val(), date: today},
                dataType: "JSON",
                beforeSend: function () {
                    $("#paymentTable").empty();
                },
                success: function (data) {
                    let temp = `<tr class ="text-center">
                            <th>예매번호</th>
                            <th>제목</th>
                            <th>예매일자</th>
                            <th>상태</th>
                            <th>확인/신청</th>
                        </tr>`
                    $("#paymentTable").append(temp);
                    let state;
                    for (let i = 0; i < data.length; i++) {
                        reserve = data[i];
                        if (reserve['is_Payment'] == 'N') {
                            state = "예약취소";
                            let str = `
                    <tr class = "p-5 text-center">
                        <td id = \${reserve['orderId']}>\${reserve['orderId']}</td>
                        <td>\${reserve['exhibitName']}</td>
                        <td>\${reserve['reserveDate']}</td>
                        <td>\${state}</td>
                    </tr>`;
                            $("#paymentTable").append(str);
                        } else {
                            state = "예약완료"
                            let str = `
                    <tr class = "p-5 text-center">
                        <td id = \${reserve['orderId']}>\${reserve['orderId']}</td>
                        <td>\${reserve['exhibitName']}</td>
                        <td>\${reserve['reserveDate']}</td>
                        <td>\${state}</td>
                        <td><button type="button" class="btn btn-outline-dark" id="btnDetail">상세보기</button></td>
                    </tr>`;
                            $("#paymentTable").append(str);
                        }
                    }
                }
            })
        })


    function getPaymentHistory() {
        $.ajax({
            type: 'POST',
            url: '/history/reserve/' + $("#usernum").val(),
            data: '',
            dataType: "JSON",
            beforeSend: function () {
                $("#paymentTable").empty();
            },
            success: function (data) {
                let temp = `<tr class ="text-center">
                            <th>예매번호</th>
                            <th>제목</th>
                            <th>예매일자</th>
                            <th>상태</th>
                            <th>확인/신청</th>
                        </tr>`
                $("#paymentTable").append(temp);
                for (let i = 0; i < data.length; i++) {
                    let reserve = data[i];
                    let state;
                    if (reserve['state'] == 'N') {
                        state = "예약취소"
                        let str = `
                    <tr class = "p-5 text-center">
                        <td id = \${reserve['orderId']}>\${reserve['orderId']}</td>
                        <td>\${reserve['exhibitionName']}</td>
                        <td>\${reserve['reserveDate']}</td>
                        <td>\${state}</td>
                    </tr>`;
                        $("#paymentTable").append(str);
                    } else {
                        state = "예약완료"
                        let str = `
                    <tr class = "p-5 text-center">
                        <td id = \${reserve['orderId']}>\${reserve['orderId']}</td>
                        <td>\${reserve['exhibitionName']}</td>
                        <td>\${reserve['reserveDate']}</td>
                        <td>\${state}</td>
                        <td><button type="button" class="btn btn-outline-dark" id="btnDetail">상세보기</button></td>
                    </tr>`;
                        $("#paymentTable").append(str);
                    }
                }
            }
        })
    }

    function getToday() {
        let date = new Date();
        let year = date.getFullYear();
        let month = ("0" + (1 + date.getMonth())).slice(-2);
        let day = ("0" + date.getDate()).slice(-2);

        return year + "-" + month + "-" + day;
    }

    function getThisWeek() {

        let currentDay = new Date();
        let theYear = currentDay.getFullYear();
        let theMonth = currentDay.getMonth();
        let theDate = currentDay.getDate();
        let theDayOfWeek = currentDay.getDay();

        let thisWeek = [];

        for (let i = 0; i < 7; i++) {
            let resultDay = new Date(theYear, theMonth, theDate + (i - theDayOfWeek));
            let yyyy = resultDay.getFullYear();
            let mm = Number(resultDay.getMonth()) + 1;
            let dd = resultDay.getDate();

            mm = String(mm).length === 1 ? '0' + mm : mm;
            dd = String(dd).length === 1 ? '0' + dd : dd;

            thisWeek[i] = yyyy + '-' + mm + '-' + dd;
        }

        return thisWeek;
    }

    function getThisMonth() {
        let date = new Date();
        let firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
        let lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
        console.log(firstDay);
        console.log(lastDay);
        firstDay = firstDay.toISOString().substring(0, 10);
        lastDay = lastDay.toISOString().substring(0, 10);
        let thisMonth = [];
        thisMonth[0] = firstDay;
        thisMonth[1] = lastDay;
        return thisMonth;
    }

    $(document)
        .on('click', '#btnDetail', function () {
            let id = $(this).parent().parent().find('td:eq(0)').attr('id');
            console.log("오픈할 id = " + id);
            $.ajax({
                type: 'POST',
                url: '/history/reserve/detail/' + id,
                data: {userId: $("#usernum").val()},
                dataType: 'JSON',
                beforeSend: function () {
                    $("#tb1").empty();
                },
                success: function (data) {
                    console.log("성공 " + data['orderId']);
                    let today1 = getToday();
                    if (data['reserveDate'] <= today1) {
                        let str = `
	 <tr>
		<th>예매번호</th>
		<td id = orderId>\${data['orderId']}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>\${data['exhibitionName']}</td>
	</tr>
	<tr>
		<th>날짜</th>
		<td>\${data['reserveDate']}</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>\${data['representName']}</td>
	</tr>
	<tr>
		<th>인원</th>
		<td>\${data['person']}</td>
	</tr>
	<tr>
		<th>상태</th>
		<td>예매완료</td>
	</tr>
	<tr style="display: none;">
	</tr>
	<tr>
		<td colspan="2" style="background: gainsboro">
			<p style="margin-top: 20px;"></p>
			<button type="button" class="btn btn-outline-dark" id="btnCancel">닫기</button>
		</td>
	</tr>`
                        $("#tb1").append(str);
                    } else {
                        let str = `
	 <tr>
		<th>예매번호</th>
		<td id = orderId>\${data['orderId']}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>\${data['exhibitionName']}</td>
	</tr>
	<tr>
		<th>날짜</th>
		<td>\${data['reserveDate']}</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>\${data['representName']}</td>
	</tr>
	<tr>
		<th>인원</th>
		<td>\${data['person']}</td>
	</tr>
	<tr>
		<th>상태</th>
		<td>예매완료</td>
	</tr>
	<tr style="display: none;">
	</tr>
	<tr>
		<td colspan="2" style="background: gainsboro">
			<p style="margin-top: 20px;"></p>
			<button type="button" class="btn btn-outline-dark" id="btnC">취소하기</button>
			<button type="button" class="btn btn-outline-dark" id="btnCancel">닫기</button>
		</td>
	</tr>`
                        $("#tb1").append(str);
                    }
                }
            })
            $('#history').dialog('open');
        })
        .on('click', '#btnCancel', function () {
            $('#history').dialog('close');
        })
        .on('click', '#btnC', function () {
            if (!confirm('예매를 취소하시겠습니까?')) {
                return false;
            } else {
                $.ajax({
                    type: 'POST',
                    url: '/history/reserve/delete/' + $("#orderId").text(),
                    data: '',
                    dataType: 'JSON',
                    success: function () {
                        $('#history').dialog('close');
                    }
                })
            }
        })
        .on("click", "#btnWeek", function () {
            let thisWeek = getThisWeek();
            $.ajax({
                type: "POST",
                url: "/history/reserve/thisWeek",
                data: {userId: $("#usernum").val(), startDate: thisWeek[0], endDate: thisWeek[6]},
                dataType: "JSON",
                beforeSend: function () {
                    $("#paymentTable").empty();
                },
                success: function (data) {
                    let temp = `<tr class ="text-center">
                            <th>예매번호</th>
                            <th>제목</th>
                            <th>예매일자</th>
                            <th>상태</th>
                            <th>확인/신청</th>
                        </tr>`
                    $("#paymentTable").append(temp);
                    for (let i = 0; i < data.length; i++) {
                        let reserve = data[i];
                        let state;
                        if (reserve['isPayment'] == 'N') {
                            state = "예약취소"
                            let str = `
                    <tr class = "p-5 text-center">
                        <td id = \${reserve['orderId']}>\${reserve['orderId']}</td>
                        <td>\${reserve['exhibitName']}</td>
                        <td>\${reserve['reserveDate']}</td>
                        <td>\${state}</td>
                    </tr>`;
                            $("#paymentTable").append(str);
                        } else {
                            state = "예약완료"
                            let str = `
                    <tr class = "p-5 text-center">
                        <td id = \${reserve['orderId']}>\${reserve['orderId']}</td>
                        <td>\${reserve['exhibitName']}</td>
                        <td>\${reserve['reserveDate']}</td>
                        <td>\${state}</td>
                        <td><button type="button" class="btn btn-outline-dark" id="btnDetail">상세보기</button></td>
                    </tr>`;
                            $("#paymentTable").append(str);
                        }
                    }
                }
            })
        })
        .on("click", "#btnMonth", function () {
            let date = getThisMonth();
            $.ajax({
                type: "POST",
                url: "/history/reserve/thisMonth",
                data: {userId: $("#usernum").val(), startDate: date[0], endDate: date[1]},
                dataType: "JSON",
                beforeSend: function () {
                    $("#paymentTable").empty()
                },
                success: function (data) {
                    let temp = `<tr class ="text-center">
                            <th>예매번호</th>
                            <th>제목</th>
                            <th>예매일자</th>
                            <th>상태</th>
                            <th>확인/신청</th>
                        </tr>`
                    $("#paymentTable").append(temp);
                    for (let i = 0; i < data.length; i++) {
                        let reserve = data[i];
                        let state;
                        if (reserve['isPayment'] == 'N') {
                            state = "예약취소"
                            let str = `
                    <tr class = "p-5 text-center">
                        <td id = \${reserve['orderId']}>\${reserve['orderId']}</td>
                        <td>\${reserve['exhibitName']}</td>
                        <td>\${reserve['reserveDate']}</td>
                        <td>\${state}</td>
                    </tr>`;
                            $("#paymentTable").append(str);
                        } else {
                            state = "예약완료"
                            let str = `
                    <tr class = "p-5 text-center">
                        <td id = \${reserve['orderId']}>\${reserve['orderId']}</td>
                        <td>\${reserve['exhibitName']}</td>
                        <td>\${reserve['reserveDate']}</td>
                        <td>\${state}</td>
                        <td><button type="button" class="btn btn-outline-dark" id="btnDetail">상세보기</button></td>
                    </tr>`;
                            $("#paymentTable").append(str);
                        }
                    }
                }
            })
        })
        .on("click", "#selectPayment", function () {
            if ($("#date1").val() == '' || $("#date2").val() == '') {
                alert("날짜를 제대로 선택해주세요");
            } else {
                $.ajax({
                    type: "POST",
                    url: "/history/reserve/date",
                    data: {userId: $("#usernum").val(), startDate: $("#date1").val(), endDate: $("#date2").val()},
                    dataType: "JSON",
                    beforeSend: function () {
                        $("#paymentTable").empty()
                    },
                    success: function (data) {
                        let temp = `<tr class ="text-center">
                            <th>예매번호</th>
                            <th>제목</th>
                            <th>예매일자</th>
                            <th>상태</th>
                            <th>확인/신청</th>
                        </tr>`
                        $("#paymentTable").append(temp);
                        for (let i = 0; i < data.length; i++) {
                            let reserve = data[i];
                            let state;
                            if (reserve['isPayment'] == 'N') {
                                state = "예약취소"
                                let str = `
                    <tr class = "p-5 text-center">
                        <td id = \${reserve['orderId']}>\${reserve['orderId']}</td>
                        <td>\${reserve['exhibitName']}</td>
                        <td>\${reserve['reserveDate']}</td>
                        <td>\${state}</td>
                    </tr>`;
                                $("#paymentTable").append(str);
                            } else {
                                state = "예약완료"
                                let str = `
                    <tr class = "p-5 text-center">
                        <td id = \${reserve['orderId']}>\${reserve['orderId']}</td>
                        <td>\${reserve['exhibitName']}</td>
                        <td>\${reserve['reserveDate']}</td>
                        <td>\${state}</td>
                        <td><button type="button" class="btn btn-outline-dark" id="btnDetail">상세보기</button></td>
                    </tr>`;
                                $("#paymentTable").append(str);
                            }
                        }
                    }
                })
            }
        })
</script>
<script>
    $(document)
        .on('click', '#mmt', function () {
            $("#acc").empty();
            let str = `<main class="container text-center">
            <br><br><h2 style="text-align: center">개인 정보 수정</h2><hr><br>
            <table style="width:100%" style="text-align: center">
                <tr><td><input type="password" id="pwd2" class="container text-center"  placeholder="비밀번호를 입력하세요."/></td></tr>
                <tr><td colSpan="2" style="text-align: center"><button class = "btn btn-dark" id="btnopen">입력</button></td><td></td></tr>
            </table>
                </main>`;
            $("#acc").append(str);
        })
        .on("click", "#btnopen", function () {
            $.ajax({
                type: "POST",
                url: "/pwdcheck",
                data: {userId: $("#userid").val(), password: $("#pwd2").val()},
                dataType: "JSON",
                success: function (data) {
                    console.log(data);
                    if (data) {
                        $("#acc").empty();
                        let str = `<main class="container text-center">
                            <br><br><h2 style="text-align: center">개인 정보 수정</h2><hr><br>
                            <form:form id="frmupdate" name="frm" action="/mypage" method="POST" modelAttribute = "mypage">
                            <table style="width:100%" style="text-align: center">
                            <tr><td >아이디</td><td style="text-align: center"><form:input path="id" name="id" class="container text-center" value="${user.id}" readonly="true"/>
                            <form:errors path = "id" class = "FieldError" /></td></tr>
                            <tr><td >비밀번호</td><td style="text-align: center"><form:password path="password" name="pwd" id="pwd" class="container text-center" placeholder="PASS WORD"/>
                            <form:errors path = "password" class = "FieldError"/></td></tr>
                            <tr><td >비밀 번호 확인</td><td style="text-align: center"><form:password path="passwordCheck" name="pwd2" class="container text-center"  id="pwd2" placeholder="PASS WORD CHECK"/>
                            <form:errors path = "passwordCheck" class = "FieldError"/></td></tr>
                            <tr><td >이름</td><td style="text-align: center"><form:input path="name" name="name" class="container text-center" placeholder="NAME" value="${list.username}"/>
                            <form:errors path = "name" class = "FieldError" /></td></tr>
                            <tr><td >E-mail</td><td style="text-align: center"><form:input path="email" name="email" class="container text-center"  id="mail" placeholder="example@example.com" value="${list.email}"/>
                            <form:errors path = "email" class = "FieldError"/></td></tr>
                            <tr><td >우편 번호</td><td style="text-align: center"><form:input path="postcode" name="postcode" class="container text-center"  id="sample6_postcode" placeholder="POST CODE" value="${list.postcode}"/>
                            <form:errors path = "postcode" class = "FieldError"/></td></tr>
                            <tr><td></td><td style="text-align: center"><input type="button" class = "btn btn-dark" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"></td></tr>
                            <tr><td >주소</td><td style="text-align: center"><form:input path="address" name="address" class="container text-center"  id="sample6_address" placeholder="ADRESS" value="${list.address}"/>
                            <form:errors path = "address" class = "FieldError"/></td></tr>
                            <tr><td >상세 주소</td><td style="text-align: center"><form:input path="dtaddress" name="dtaddress" class="container text-center"  id="sample6_extraAddress" placeholder="DETAIL" value="${list.dtaddress}"/>
                            <form:errors path = "dtaddress" class = "FieldError"/> </td></tr>
                            <tr><td >전화 번호</td><td style="text-align: center"><form:input path="mobile" name="mobile" class="container text-center"  id="phonenum" placeholder="PHONE NUMDER" value="${list.mobile}"/>
                            <form:errors path = "mobile" class = "FieldError"/></td></tr>
                             </form:form>
                            <tr><td></td><td style="text-align: center"><button type="submit" class = "btn btn-dark" id="btnadj" name="btnadj">수정 완료</button></td></tr>
                            </table>

                            </main>`;
                        $("#acc").append(str);
                    } else {
                        alert("비밀번호가 일치하지 않습니다.");
                    }
                }
            })
        })
    $(document)
        .on('click', '#btnadj', function () {
            pwdRegExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
            if ((frm.id.value == "") || (frm.pwd2.value == "") || (frm.name.value == "") || (frm.email.value == "") || (frm.postcode.value == "") || (frm.address.value == "") || (frm.dtaddress.value == "") || (frm.mobile.value == "")) {
                alert("입력되지 않은 항목이 있습니다.");
                return false;
            } else if (!pwdRegExp.test(pwd.value)) {
                alert("비밀 번호를 제대로 입력해주세요.");
                return false;
            } else if (confirm("수정을 완료하시겠습니까?")) {
                alert("수정이 완료 되었습니다.");
            } else {
                return false;
            }
        })
</script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode(
            {
                oncomplete: function (data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var addr = ''; // 주소 변수
                    var extraAddr = ''; // 참고항목 변수

                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                    }

                    // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                    if (data.userSelectedType === 'R') {
                        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                        if (data.bname !== ''
                            && /[동|로|가]$/g.test(data.bname)) {
                            extraAddr += data.bname;
                        }
                        // 건물명이 있고, 공동주택일 경우 추가한다.
                        if (data.buildingName !== ''
                            && data.apartment === 'Y') {
                            extraAddr += (extraAddr !== '' ? ', '
                                + data.buildingName : data.buildingName);
                        }
                        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                        if (extraAddr !== '') {
                            extraAddr = ' (' + extraAddr + ')';
                        }

                    } else {
                        document.getElementById("sample6_address").value = '';
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('sample6_postcode').value = data.zonecode;
                    document.getElementById("sample6_address").value = addr + " " + extraAddr;
                    // 커서를 상세주소 필드로 이동한다.
                    document.getElementById("sample6_address")
                        .focus();
                }
            }).open();
    }
</script>
<script src=/js/MyPageComment.js></script>
<script>
    $(document)
        .on('click', '#sec', function () {
            $("#acc").empty();
            let str = `<main class="container text-center">
            <br><br><h2 style="text-align: center">회원 탈퇴</h2><hr><br>
            <table style="width:100%" style="text-align: center">
                <tr><td><input type="password" id="pwd3" class="container text-center"  placeholder="비밀번호를 입력하세요."/></td></tr>
                <tr><td colSpan="2" style="text-align: center"><button class = "btn btn-dark" id="btnopen2">입력</button></td><td></td></tr>
            </table>
                </main>`;
            $('#acc').append(str);
        })
        .on("click", "#btnopen2", function () {
            $.ajax({
                type: "POST",
                url: "/pwdcheck2",
                data: {userId: $("#userid").val(), password: $("#pwd3").val()},
                dataType: "JSON",
                success: function (data) {
                    console.log(data);
                    if (data) {
                        $("#acc").empty();
                        let str = `<main class="container text-center">
                            <br><br><h2 style="text-align: center">탈퇴 사유</h2><hr><br>&nbsp&nbsp
                            <input type="radio" name="list" id="ra1" value="갤러리 서비스 불만">갤러리 서비스 불만&nbsp&nbsp
                            <input type="radio" name="list" id="ra2" value="높은 이용 금액">높은 이용 금액<br><br>&nbsp&nbsp
                            <input type="radio" name="list" id="ra3" value="사이트 장애">사이트 장애&nbsp&nbsp
                            <input type="radio" name="list" id="ra4" value="단순 변심">단순 변심&nbsp&nbsp
                            <input type="radio" name="list" id="ra5" value="기타">기타<br><br>&nbsp&nbsp
                            <button id="btnbb" class = "btn btn-dark" style="text-align: center">탈퇴하기</button>
                            </main>`;
                        $("#acc").append(str);
                    } else {
                        alert("비밀번호가 일치하지 않습니다.");
                    }
                }
            })
        })

    $(document)
        .on('click', '#btnbb', function () {
            listVal = $('input[name="list"]:checked').val();
            if ($('input:radio[name=list]').is(":checked") == "") {
                alert("탈퇴 사유를 선택하세요.");
            } else if (confirm("정말 탈퇴하시겠습니까?")) {
                $.ajax({
                    type: "POST",
                    url: "/secession",
                    data: {userId: $('#usernum').val(), listVal: listVal},
                    dataType: "JSON",
                    success: function (data) {
                        console.log(data);
                        alert("탈퇴가 완료되었습니다.");
                        document.location.href = '/';
                    }
                })
            } else {
            }
        })
</script>
</html>