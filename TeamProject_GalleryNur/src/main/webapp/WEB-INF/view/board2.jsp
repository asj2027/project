<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2022-07-19
  Time: 오후 2:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="w3-bar">
    <a href="#" class="w3-button">&laquo;</a>
    <a href="#" class="w3-button">1</a>
    <a href="#" class="w3-button">2</a>
    <a href="#" class="w3-button">3</a>
    <a href="#" class="w3-button">4</a>
    <a href="#" class="w3-button">&raquo;&raquo;</a>
</div>
<div class="boardContainer">
    <div class="boardBox">
        <div class="pull-right amountDiv">
            <select name="" id="amount">
                <option value="">--</option>
                <option value="10">10개씩</option>
                <option value="20">20개씩</option>
                <option value="30">30개씩</option>
            </select>
        </div>
        <table class="table table-striped boardTable">
            <thead>
            <th class="th100">글번호</th>
            <th class="th100">카테고리</th>
            <th>제목</th>
            <th>작성자</th>
            <th class="th100">작성일</th>
            <th class="th100">조회수</th>
            </thead>
            <tbody>
            <c:forEach var="post" items="${list}">
                <tr>
                    <td>${post.postNo}</td>
                    <td>${post.postCategory}</td>
                    <td><a class="postGo" href="/bo/post?postNo=${post.postNo}">${post.postTitle}</td>
                    <td>${post.postWriter}</td>
                    <td>${post.postDate}</td>
                    <td>${post.postHit}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="searchDiv">
            <label class="searchLabel">검색</label>
            <input type="text" id="keyword">
            <button id="searchBtn" name="searchBtn" type="submit" class="btn"></button>
            <select id="type">
                <option value="TC" ${pageMaker.cri.type eq 'TC' ? 'selected' : ''}>제목+내용</option>
                <option value="T" ${pageMaker.cri.type eq 'T' ? "selected" : ''}>제목</option>
                <option value="C" ${pageMaker.cri.type eq 'C' ? "selected" : ''}>내용</option>
                <option value="W" ${pageMaker.cri.type eq 'W' ? "selected" : ''}>작성자</option>
                <option value="TWC" ${pageMaker.cri.type eq 'TWC' ? "selected" : ''}>전체</option>
            </select>
        </div>
        <div class="pull-right writeBtnDiv">
            <c:if test="${loginInfo != null}">
                <a href="/bo/write"><button>글쓰기</button></a>
            </c:if>
        </div>
        <div class="paginationDiv">
            <ul class="pagination">
                <c:if test="${pageMaker.prev }">
                    <li class="paginate_button previous">
                        <a href="${pageMaker.startPage-1 }">이전</a>
                    </li>
                </c:if>
                <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
                    <li class="paginate_button ${pageMaker.cri.pageNum == num? "active":"" }">
                        <a href="${num }">${num}</a>
                    </li>
                </c:forEach>
                <c:if test="${pageMaker.next }">
                    <li class="paginate_button next">
                        <a href="${pageMaker.endPage+1 }">다음</a>
                    </li>
                </c:if>
                <c:if test="${pageMaker.next }">
                    <li class="paginate_button next">
                        <a href="${pageMaker.lastPage }">맨끝</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
