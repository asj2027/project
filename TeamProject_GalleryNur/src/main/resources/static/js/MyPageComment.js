$(document)
    .on("click", "#myComment", function () {
        $("#acc").empty();
        getComment();
    })
    .on("click", "#myPost", function () {
        $("#acc").empty();
        getPost();
    })

function getComment(page) {
    if (page == null || page == 0) {
        page = 1;
        $("#currentCommentPage").val(1);
    }
    else if (page == $("#pageMax").val()) {
        page = page - 1;
        $("#currentCommentPage").val(page);
    }
    else {
        $("#currentCommentPage").val(page);
    }
    $.ajax({
        type: "POST",
        url: "/findAllMyComment",
        data: {userId: $("#usernum").val(), pageNumber : page},
        dataType: "JSON",
        beforeSend: function () {
            $("#acc").empty();
            $("#comment").empty();
        },
        success: function (data) {
            console.log(data);
            let temp = `
                    <main class="container text-center">
                    <br><br><h2 style="text-align: center">내가 작성한 댓글</h2><hr><br>
                    <table id = "comment" class = "table">
                        <tr><th>
                            번호
                        </th>
                        <th>
                            작성 글 번호
                        </th>
                        <th>
                            내용
                        </th>
                       <th>
                            날짜
                        </th></tr>
                    </table>
                    </main>`;
            $("#acc").append(temp);
            let paging = `<nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-end" id = "commentPage">
                                </ul>
                            </nav>`
            $("#acc").append(paging);

            for (let i = 0; i < data.length; i++) {
                let userComment = data[i];
                let content = userComment['content'];
                content = content.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
                console.log(userComment);
                $.ajax({
                    type: "POST",
                    url: "/getCategory",
                    data: {postId: userComment['postId']},
                    dataType: "text",
                    success: function (data) {
                        let str =
                            `<tr><td>${userComment['id']}</td>
                        <td>${userComment['postId']}</td>
                        <td><a href = ${data}?id=${userComment['postId']}>${content}</td></a>
                        <td>${userComment['postDate']}</td></tr>`;
                        $("#comment").append(str);
                    }
                })
            }
            commentPaging(page);
        }
    })
}
function commentPaging(page) {
    let RowsPerPage = 15;
    let pageList;
    if (page == null || page == 0) {
        page = 1;
    }
    console.log("페이징 호출여부");
    $.ajax({
        type : "POST",
        url : "/getMyCommentNum",
        dataType : "text",
        data : {userId : $("#usernum").val()},
        beforeSend : function() {
            $("#commentPage").empty();
        },
        success : function (data) {
            let commentCount = data;
            console.log("댓글 -> ", commentCount);
            pageList = Math.ceil(commentCount / RowsPerPage) + 1;
            $("#pageMax").val(pageList);
            console.log("페이지 리스트 -> ", pageList);
            let temp = `<li class="page-item" id = "previous">
                    <span class="page-link" onclick="getComment(${$("#currentCommentPage").val()-1})" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </span>
                </li>`;
            $("#commentPage").append(temp);
            if (pageList == 1) {
                let str = `<li class="page-item">
                    <span class="page-link" onclick="getComment(1)">1</span>
                 </li>`;
                $("#commentPage").append(str);
            } else {
                for (let i = 1; i < pageList; i++) {
                    if (i == page) {
                        let str = `<li class="page-item active">
                    <span class="page-link" onclick="getComment(${i})">${i}</span>
                 </li>`;
                        $("#commentPage").append(str);
                    } else {
                        let str = `<li class="page-item">
                    <span class="page-link" onclick="getComment(${i})">${i}</span>
                 </li>`;
                        $("#commentPage").append(str);
                    }
                }
            }
            let temp2 = `<li class="page-item" id = "next">
                    <span class="page-link" class = "paging" onclick="getComment(${parseInt($("#currentCommentPage").val()) + 1})" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </span>
                </li>`;
            $("#commentPage").append(temp2);
        }
    })
}

function getPost(page) {
    if (page == null || page == 0) {
        page = 1;
        $("#currentPostPage").val(1);
    }
    else if (page == $("#postPageMax").val()) {
        page = page - 1;
        $("#currentPostPage").val(page);
    }
    else {
        $("#currentPostPage").val(page);
    }
    $.ajax({
        type: "POST",
        url: "/findPost",
        data: {writer: $("#usernum").val(), pageNumber: page},
        dataType: "JSON",
        beforeSend: function () {
            $("#acc").empty();
            $("#post").empty();
        },
        success: function (data) {
            let temp =
                `<main class="container text-center">
                        <br><br><h2 style="text-align: center">내가 작성한 글</h2><hr><br><table id = "post" class = "table">
                        <tr><th>
                            번호
                        </th>
                        <th>
                            제목
                        </th>
                       <th>
                            날짜
                        </th></tr>
                    </table></main>`;
            $("#acc").append(temp);
            let paging = `<nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-end" id = "commentPage">
                                </ul>
                            </nav>`
            $("#acc").append(paging);

            for (let i = 0; i < data.length; i++) {
                let userPost = data[i];
                $.ajax({
                    type: "POST",
                    url: "/getCategory",
                    data: {postId: userPost['id']},
                    dataType: "text",
                    success: function (data) {
                        let str =
                            `<tr><td>${userPost['id']}</td>
                        <td><a href = ${data}?id=${userPost['id']}>${userPost['title']}</td></a>
                        <td>${userPost['postDate']}</td></tr>`;
                        $("#post").append(str);
                    }
                })
            }
            postPaging(page);
        }
    })
}

function postPaging(page) {
    let RowsPerPage = 10;
    let pageList;
    if (page == null || page == 0) {
        page = 1;
    }
    console.log("페이징 호출여부");
    $.ajax({
        type : "POST",
        url : "/getMyPostNum",
        dataType : "text",
        data : {userId : $("#usernum").val()},
        beforeSend : function() {
            $("#postPage").empty();
        },
        success : function (data) {
            let commentCount = data;
            console.log("댓글 -> ", commentCount);
            pageList = Math.ceil(commentCount / RowsPerPage) + 1;
            $("#pageMax").val(pageList);
            console.log("페이지 리스트 -> ", pageList);
            let temp = `<li class="page-item" id = "previous">
                    <span class="page-link" onclick="getPost(${$("#currentPostPage").val()-1})" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </span>
                </li>`;
            $("#commentPage").append(temp);
            if (pageList == 1) {
                let str = `<li class="page-item">
                    <span class="page-link" onclick="getPost(1)">1</span>
                 </li>`;
                $("#commentPage").append(str);
            } else {
                for (let i = 1; i < pageList; i++) {
                    if (i == page) {
                        let str = `<li class="page-item active">
                    <span class="page-link" onclick="getPost(${i})">${i}</span>
                 </li>`;
                        $("#commentPage").append(str);
                    } else {
                        let str = `<li class="page-item">
                    <span class="page-link" onclick="getPost(${i})">${i}</span>
                 </li>`;
                        $("#commentPage").append(str);
                    }
                }
            }
            let temp2 = `<li class="page-item" id = "next">
                    <span class="page-link" class = "paging" onclick="getPost(${parseInt($("#currentPostPage").val()) + 1})" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </span>
                </li>`;
            $("#commentPage").append(temp2);
        }
    })
}