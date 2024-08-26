<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous"
    />
    <title>${exhibit.name} - Gallery nur</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<style>
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
        text-decoration-line: none;
    }

    html {
        position: relative;
        min-height: 100%;
        margin: 0;
    }

    body {
        min-height: 100%;
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
<br>
<input type="hidden" id="role" value="${user.role}"/>
<input type="hidden" id="usernum" value="${user.userNum}"/>
<input type="hidden" id="exhibitId" value="${exhibit.id}"/>
<!-- main 안에다가 주 내용 작성할것 -->
<main class="container p-5">
    <div class="page-title">
        <h1>전시회 정보</h1>
    </div>
    <div class="row mb-2">
        <div class="col-6">
            <div
                    id="carouselExampleSlidesOnly"
                    class="carousel slide"
                    data-bs-ride="carousel"
            >
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <br>
                        <img src="${exhibit.images}" class="d-block w-100" alt="..." style="margin-top: 12px"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-6 h-auto">
            <div class="container h-auto">
                <dl class="row h-auto py-5">
                    <dt class="col-sm-4">전시회 이름</dt>
                    <dd class="col-sm-8">${exhibit.name}</dd>
                    <dt class="col-sm-4">아티스트 정보</dt>
                    <dd class="col-sm-8">
                        <p>${exhibit.artist}</p>
                    </dd>
                    <dt class="col-sm-4">전시회 날짜</dt>
                    <dd class="col-sm-8">
                        <p>${exhibit.startDate} ~ ${exhibit.endDate}</p>
                    </dd>
                    <dt class="col-sm-4">전시회 정보</dt>
                    <dd class="col-sm-8">${exhibit.info}</dd>

                    <dt class="col-sm-4">가격</dt>
                    <dd class="col-sm-8">${exhibit.price}</dd>
                    <dt class="col-sm-4">입장 가능 인원</dt>
                    <dd class="col-sm-8">${exhibit.total}</dd>
                </dl>
                <c:set var="now" value="<%=new java.util.Date()%>"/>
                <c:set var="nowDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:set>
                <c:if test="${exhibit.endDate > nowDate}">
                    <button type="button" class="btn btn-primary" id="reserveExhibit">예매하기</button>
                </c:if>
                <c:if test="${exhibit.endDate < nowDate}">
                    <button type="button" class="btn btn-danger" disabled>마감</button>
                </c:if>
                <c:if test="${user.role == '관리자'}">
                    <button type="button" class="btn btn-dark" onclick="location.href='/exhibit/modify/${exhibit.id}'">
                        전시회 수정
                    </button>
                    <button type="button" class="btn btn-danger" id="deleteExhibit">전시회 삭제</button>
                </c:if>
                <button type="button" class="btn btn-dark" onclick="location.href='/exhibit'">목록</button>
            </div>
        </div>
    </div>
</main>

<!-- 하단 -->
<div class="container-fluid" id="footer">
    <footer
            class="d-flex justify-content-between align-items-center py-3 my-4 h-auto"
    >
        <p class="col-md-4 mb-0 text-muted">&copy; 2022 Company, Inc</p>

        <a
                href="/"
                class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none"
        >
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
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"
></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
    $(document)
        .ready(function () {
            $("#nav1").hover(
                function () {
                    $("#none1").css("display", "block");
                    $("#none2").css("display", "none");
                    $("#none3").css("display", "none");
                },
                function () {
                }
            );
            $("#nav2").hover(function () {
                $("#none1").css("display", "none");
                $("#none2").css("display", "none");
                $("#none3").css("display", "none");
            });
            $("#nav3").hover(function () {
                $("#none1").css("display", "none");
                $("#none2").css("display", "none");
                $("#none3").css("display", "none");
            });
            $("#nav4").hover(function () {
                $("#none1").css("display", "none");
                $("#none2").css("display", "block");
                $("#none3").css("display", "none");
            });
            $("#nav5").hover(function () {
                $("#none1").css("display", "none");
                $("#none2").css("display", "none");
                $("#none3").css("display", "block");
            });
            $("#nav6").hover(function () {
                $("#none1").css("display", "none");
                $("#none2").css("display", "none");
                $("#none3").css("display", "none");
            });
        })
        // 전시회 삭제
        .on("click", "#deleteExhibit", function () {
            if (confirm("정말 삭제하시겠습니까?")) {
                $.ajax({
                    type: "POST",
                    url: "/exhibit/delete/${exhibit.id}",
                    data: {},
                    dataType: "text",
                    success: function (data) {
                        if (data == "true") {
                            alert("삭제가 완료되었습니다.");
                            document.location.href = "/exhibit";
                        } else if (data == "false") {
                            alert("삭제할 수 없는 아티스트입니다.");
                        }
                    },
                });
            }
        })
        // 전시회 예약
        .on("click", "#reserveExhibit", function () {
            if (confirm("예약하시겠습니까?")) {
                document.location.href = "/reserve/exhibit/" + $("#exhibitId").val();
            }
        });
</script>
</html>
