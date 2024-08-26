<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<title>카카오 회원 가입 - Gallery nur</title>
</head>
<style>
	.FieldError {
		color:red;
	}
	.nav-item{
		list-style-type:none;
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
		border-bottom : 10px solid black;
		margin-bottom : 2rem;
	}
	.page-title h1{
		padding : 2rem;
		text-align : center;
	}
	body {
		font-family: 'IBMPlexSansKR-Regular';
	}
	@font-face {
		font-family: 'IBMPlexSansKR-Regular';
		src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
		font-weight: normal;
		font-style: normal;
	}
	a {
		text-decoration: none;
		color:black;
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
		<br /><br /><br />
		<div class="nav-scroller mb-7" id="list">
			<ul class="nav justify-content-center">
				<li class="nav-item mx-5">
					<!-- 클릭 시 가장 첫 메뉴인 인사말로 이동 -->
					<a class="nav-link active p-7" aria-current="page" href="/letter" id="nav1">
						<spring:message code = "About"></spring:message>
					</a>
					<div>
						<ul class="nav justify-content-end" style="display: none" id="none1">
							<li class="nav-item">
								<!-- 클릭 시 인사말로 이동 -->
								<a class="nav-link" aria-current="page" href="/letter" id="detail">
									<spring:message code = "letter"></spring:message>
								</a>
							</li>
							<li class="nav-item">
								<!-- 클릭 시 오시는 길로 이동 -->
								<a class="nav-link" aria-current="page" href="/visit" id="detail">
									<spring:message code = "Visit"></spring:message>
								</a>
							</li>
						</ul>
					</div>
				</li>
				<li class="nav-item mx-5">
					<!-- 클릭 시 전시회 목록으로 이동 -->
					<a class="nav-link" href="/exhibit" id="nav2">
						<spring:message code = "Exhibition"></spring:message>
					</a>
				</li>
				<li class="nav-item mx-5">
					<!-- 클릭 시 아티스트 목록으로 이동 -->
					<a class="nav-link" href="/artist" id="nav3">
						<spring:message code = "artist"></spring:message>
					</a>
				</li>
				<li class="nav-item mx-5">
					<!-- 클릭 시 가장 첫 메뉴인 리뷰게시판으로 이동 -->
					<a class="nav-link" href="/review" id="nav4">
						<spring:message code = "Post"></spring:message>
					</a>
					<ul class="nav justify-content-end" style="display: none" id="none2">
						<li class="nav-item">
							<!-- 클릭 시 리뷰게시판으로 이동 -->
							<a class="nav-link" aria-current="page" href="/review" id="detail">
								<spring:message code = "Review"></spring:message>
							</a>
						</li>
						<li class="nav-item">
							<!-- 클릭 시 질문게시판으로 이동 -->
							<a class="nav-link" href="/qna" id="detail">
								<spring:message code = "QNA"></spring:message>
							</a>
						</li>
						<li class="nav-item">
							<!-- 클릭 시 FAQ게시판으로 이동 -->
							<a class="nav-link" href="/FAQ" id="detail">
								<spring:message code = "FAQ"></spring:message>
							</a>
						</li>
					</ul>
				</li>
				<li class="nav-item mx-5">
					<!-- 로그인이 되어 있지 않을 시 로그인으로 이동 -->
					<c:if test="${user.id == null}">
						<a class="nav-link" aria-current="page" href="/login" id="nav5">
							<spring:message code = "Member"></spring:message>
						</a>
					</c:if>
					<!-- 로그인한 유저의 역할이 방문자일 시 마이페이지로 이동 -->
					<c:if test="${user.id != null && user.role == '방문자'}">
						<a class="nav-link" aria-current="page" href="/mypage" id="nav5">
							<spring:message code = "Mypage"></spring:message>
						</a>
					</c:if>
					<!-- 로그인한 유저의 역할이 관리자일 시 유저관리 메뉴로 이동 -->
					<c:if test="${user.id != null && user.role == '관리자'}">
						<a class="nav-link" aria-current="page" href="/listuser" id="nav5">
							<spring:message code = "Member"></spring:message>
						</a>
					</c:if>
					<ul class="nav justify-content-end" style="display: none" id="none3">
						<!-- 로그인이 되어 있지 않을 시 로그인 버튼, 회원가입 버튼만 노출 -->
						<c:if test="${user.id == null}">
							<li class="nav-item">
								<!-- 클릭 시 로그인으로 이동 -->
								<a class="nav-link" aria-current="page" href="/login" id="detail">
									<spring:message code = "Login"></spring:message>
								</a>
							</li>
							<li class="nav-item">
								<!-- 클릭 시 회원가입으로 이동 -->
								<a class="nav-link" href="/signin" id="detail">
									<spring:message code = "Signin"></spring:message>
								</a>
							</li>
						</c:if>
						<!-- 로그인이 되어 있을 시 로그아웃 버튼만 노출 -->
						<c:if test="${user.id != null }">
							<li class="nav-item">
								<!-- 클릭 시 로그아웃 -->
								<a class="nav-link" aria-current="page" href="/logout" id="detail">
									<spring:message code = "Logout"></spring:message>
								</a>
							</li>
						</c:if>
						<!-- 로그인한 유저의 역할이 방문자일 시 마이페이지 메뉴만 노출 -->
						<c:if test="${user.role == '방문자'}">
							<li class="nav-item">
								<a class="nav-link" href="/mypage" id="detail">
									<spring:message code = "Mypage"></spring:message>
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
						</c:if>
					</ul>
				</li>
				<li class="nav-item mx-5">
					<!-- 클릭 시 공지로 이동 -->
					<a class="nav-link" aria-current="page" href="/notice" id="nav6">
						<spring:message code = "Notice"></spring:message>
					</a>
				</li>
			</ul>
		</div>
	</header>
</div>
	<!-- main 안에다가 주 내용 작성할것 -->
	<main class="p-5">
		<div class="container">
			<div class="page-title">
				<h1>회원가입</h1>
			</div>
		</div>
		<main class="container">
			<form:form id = "frmAdd" action = "/kakao/signin" method = "POST" modelAttribute = "userSign">
				<input type = "hidden" name = "id" value = ${userSign.id}>
				<div>
					<p>
						<label class = "col-4 text-end">이름 : </label>
						<form:input path ="name" class = "col-8" style = "width:40%;" placeholder="이름을 입력해주세요." value = "${userSign.name}"/>
						<form:errors path = "name" class = "FieldError" />
					</p>
					<p>
						<label class = "col-4 text-end">우편번호 : </label>
						<form:input path = "postcode" class="col-8" style = "width:40%;" id="sample6_postcode" readonly ="true" placeholder="우편번호"/>
						<form:errors path = "postcode" class = "FieldError"/>
						<input type="button" class = "btn btn-dark" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
					</p>
					<p>
						<label class = "col-4 text-end">주소 : </label>
						<form:input path = "address" id="sample6_address" class="col-8" style = "width:40%;"
							placeholder="주소" readonly ="true"/>
					</p>
					<p class = "col-12 text-center">
						<form:errors path = "address" class = "FieldError"/>
					</p>
					<p>
						<label class = "col-4 text-end">상세주소 : </label>
						<form:input path = "dtaddress" id="sample6_detailAddress"
							class = "col-4" placeholder="상세주소" style="width:20%;"/>
						<form:input path = "refAddress"	class = "col-4" style = "width:20%;" id="sample6_extraAddress" placeholder="참고항목" readonly = "true"/>
					</p>
					<p class = "col-12 text-center">
						<form:errors path = "dtaddress" class = "FieldError" style = "width:100%;"/>
					</p>
					<p class = "col-12 text-center">
						<form:errors path = "refAddress" class ="FieldError" />
					</p>
					<p>
						<label class = "col-4 text-end">전화번호 : </label>
						<form:input path = "mobile" class = "col-4" style = "width:40%;" placeholder="'-' 를 제외한 전화번호를 입력해주세요."/> <br>
					</p>
					<p class = "col-12 text-center">
						<form:errors path = "mobile" class = "col-12 text-center FieldError"/>
					</p>
					<p class="col-9 text-end">
						<button type="submit" id="updateBtn" class = "btn btn-dark">회원 가입</button>
					</p>
				</div>
			</form:form>
		</main>
	</main>

	<!-- 하단 -->
	<div class="container-fluid">
		<footer
			class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
			<p class="col-md-4 mb-0 text-muted">&copy; 2022 Company, Inc</p>

			<a href="/"
				class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
				<svg class="bi me-2" width="40" height="32">
					<use xlink:href="#bootstrap" /></svg>
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
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src=""></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(document)
			.on('click','#logo',function(){
				document.location.href='/';
			})
	$(document).ready(function() {
		console.log("시작 화면");
		$("#nav1").hover(function() {
			$("#none1").css("display", "block");
			$("#none2").css("display", "none");
			$("#none3").css("display", "none");
		}, function() {
		})
		$("#nav2").hover(function() {
			$("#none1").css("display", "none");
			$("#none2").css("display", "none");
			$("#none3").css("display", "none");
		})
		$("#nav3").hover(function() {
			$("#none1").css("display", "none");
			$("#none2").css("display", "none");
			$("#none3").css("display", "none");
		})
		$("#nav4").hover(function() {
			$("#none1").css("display", "none");
			$("#none2").css("display", "block");
			$("#none3").css("display", "none");
		})
		$("#nav5").hover(function() {
			$("#none1").css("display", "none")
			$("#none2").css("display", "none");
			$("#none3").css("display", "block");
		})
		$("#nav6").hover(function() {
			$("#none1").css("display", "none")
			$("#none2").css("display", "none");
			$("#none3").css("display", "none");
		})
	})
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
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
							// 조합된 참고항목을 해당 필드에 넣는다.
							document.getElementById("sample6_extraAddress").value = extraAddr;

						} else {
							document.getElementById("sample6_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode;
						document.getElementById("sample6_address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_detailAddress")
								.focus();
					}
				}).open();
	}
</script>
</html>