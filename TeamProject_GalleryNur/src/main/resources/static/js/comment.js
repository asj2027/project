$(document).ready(function () {
    console.log("시작 화면");
    getComment();
    getCommentNum();
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
    })
})
    .on("click", "#addComment", function() {
        editor = CKEDITOR.instances.commentForm.getData();
        console.log("입력한 값 = ", editor);
        if (editor == null || editor == '') {
            alert("댓글 내용을 입력해주세요");
        }
        else {
            $.ajax({
                type: "POST",
                url: "/commentWrite",
                dataType: "text",
                data: {postId: $("#postId").val(), writer: $("#usernum").val(), content: editor},
                beforeSend: function () {
                    // $("#addComment").after().remove();
                },
                success: function (data) {
                    getComment(parseInt($("#pageMax").val()) - 1);
                }
            })
        }
    })
    .on("click", "#addReply", function() {
        console.log("호출 여부");
        let replyContent = CKEDITOR.instances.replyForm.getData();
        if (replyContent == '' || replyContent == null) {
            alert("댓글 내용을 입력하세요");
        } else {
            $.ajax({
                type: "POST",
                url: "/commentWrite",
                dataType: "text",
                data: {
                    postId: $("#postId").val(), writer: $("#usernum").val(), content: replyContent,
                    reparent: $("#reparent").val()
                },
                beforeSend: function () {
                    // $("#writeComment").empty();
                },
                success: function () {
                    console.log("데이터 보낸거같은데");
                    getComment($("#currentCommentPage").val());
                }
            })
        }
    })
    .on("click", "#updateReply", function() {
        let temp = $(this).parent().attr('id');
        console.log(temp.split("userComment"));
        temp = temp.split("userComment");
        let commentId = temp[1];
        console.log(commentId);

        let content = CKEDITOR.instances.updateForm.getData();

        console.log("수정 전 값 = ", content);
        if (content == null || content == '') {
            alert("댓글은 공백을 입력할 수 없습니다.");
        } else {
            content += " [수정된 댓글입니다]";
            doUpdate(commentId, content);
        }
    })
$('#delete').click(function () {
    if(!confirm('게시글을 삭제하시겠습니까?')) {
        return false;
    }
})
let click = 0;
function reply_click(commentNumber) {
    click++;
    console.log(commentNumber);
    console.log("클릭 ! " ,click);
    if (click % 2 == 1) {
        let str =
            `
         <div id="writePostComment" class="mb-3">
            <input type = "hidden" id = "reparent" value = ${commentNumber}>
            <div id="comment">
          <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-90deg-up" viewBox="0 0 16 16">
          <path fill-rule="evenodd" d="M4.854 1.146a.5.5 0 0 0-.708 0l-4 4a.5.5 0 1 0 .708.708L4 2.707V12.5A2.5 2.5 0 0 0 6.5 15h8a.5.5 0 0 0 0-1h-8A1.5 1.5 0 0 1 5 12.5V2.707l3.146 3.147a.5.5 0 1 0 .708-.708l-4-4z"/>
        </svg> <strong>댓글 쓰기</strong>
            </div>
            <textarea id="replyForm" rows="3" cols="10" style="width:90%; display: inline"></textarea>
            <button type="button" id="addReply" class="btn btn-outline-Dark" style="height:auto;">등록</button>
         </div>`;
        $(`#userComment${commentNumber}`).append(str);
        CKEDITOR.replace("replyForm");
    } else {
        $("#writePostComment").remove();
    }
}

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
        url : "/getComment",
        type : "GET",
        dataType : "JSON",
        data : {postId : $("#postId").val(), pageNumber : page},
        beforeSend : function() {
            $("#writeComment").empty();
        },
        success : function(data) {
            if ($("#usernum").val() == '')
            {
                let text = `<div id="comment">
                                    댓글 쓰기
                                </div>
                            <textarea id="noneLoginForm" rows="3" cols="10"  placeholder = "로그인 후 입력이 가능합니다." style="width:90%; display: inline" readonly = "true" onclick="sendLoginForm()"></textarea>
                            <input type="button" id="" class="btn btn-outline-Dark" style="height:auto; margin-bottom: 33px" value = "댓글 등록"/>`
                $("#writeComment").append(text);
            } else {
                let text = `<div id="comment">
                                    댓글 쓰기
                                </div>
                                <textarea id="commentForm" rows="3" cols="10" style="width:90%; display: inline"></textarea>
                                <input type="button" id="addComment" class="btn btn-outline-Dark" style="height:auto; margin-bottom: 33px" value = "댓글 등록"/>`
                $("#writeComment").append(text);
            }
            for (let i = 0; i < data.length; i++) {
                let comment = data[i];
                let str;
                if (comment['isDelete'] == 'Y') {
                    str =
                        `<div class = reply${i} style="border-bottom:1px solid black;">
                                <div >
                                    ${comment['writeName']}<span style="float: right">${comment['postDate']} </span>
                                </div>
                               <div class="py-4" id = userComment${comment['id']}>
                                    ${comment['content']}
                                </div>
                            </div>`;
                }
                else if ($("#usernum").val() == '') {
                    str =
                        `<div class = reply${i} style="border-bottom:1px solid black;">
                                <div >
                                    ${comment['writeName']}<span style="float: right">${comment['postDate']} </span>
                                </div>
                               <div class="py-4" id = userComment${comment['id']}>
                                    ${comment['content']}
                                </div>
                            </div>`;
                }
                else if (comment['writer'] == $("#usernum").val()) {
                    str =
                        `<div class = reply${i} style="border-bottom:1px solid black;">
                                <div >
                                    ${comment['writeName']}<span class = "commentJob" style="float: right"> <span class = "px-2" onclick = "updateComment(${comment['id']})"> 수정 </span>  <span class = "px-2" onclick = "doDelete(${comment['id']})"> 삭제 </span> <span class = "px-2" onclick = "reply_click(${comment['id']})">댓글 </span> ${comment['postDate']} </span>
                                </div>
                               <div class="py-4" id = userComment${comment['id']}>
                                    ${comment['content']}
                                </div>
                            </div>`;
                }
                else {
                    str =
                        `<div class = reply${i} style="border-bottom:1px solid black;">
                                <div>
                                   ${comment['writeName']} <span class = "commentJob" style="float: right"> <span class = "px-2" onclick = "reply_click(${comment['id']})"> 댓글 </span> ${comment['postDate']} </span>
                                </div>
                               <div class="py-4" id = userComment${comment['id']}>
                                    ${comment['content']}
                                </div>
                            </div>`;
                }
                $("#writeComment").append(str);
                let temp = comment['redepth'];
                $(".reply"+i).css("margin-left", temp*20);
            }
            getCommentNum();
            commentPaging(page);
            CKEDITOR.replace("commentForm");
            CKEDITOR.replace("replyForm");
        }
    })
}
function getCommentNum() {
    $.ajax({
        type : "GET",
        url : "/getCommentNum",
        dataType : "text",
        data : {postId: $("#postId").val()},
        success : function (data) {
            console.log("댓글 개수 " + data);
            $("#commentNum").text("댓글 : " + data);
        }
    })
}
let updateClick = 1;
function updateComment(commentNumber) {
    updateClick++;
    let commentPost;
    console.log("나는 업데이트 할거임 = {}", commentNumber);
    $.ajax({
        type : "POST",
        url : "/getCommentById",
        data : {commentId : commentNumber},
        dataType : "text",
        success : function(data) {
            console.log(data);
            commentPost = data;
            commentPost = commentPost.split("[수정된 댓글입니다]");
            console.log("쪼갰을 때 -> ", commentPost);
            if (updateClick % 2 == 0) {
                $("#userComment"+commentNumber).empty();
                let str = `<textarea id="updateForm" rows="3" cols="10" style="width:90%; display: inline; text-align:left;">${commentPost[0]}</textarea>
                <button type="button" id="updateReply" class="btn btn-outline-Dark" style="height:auto;">등록</button>`;
                $("#userComment"+commentNumber).append(str);
                CKEDITOR.replace("updateForm");
            } else {
                $("#userComment"+commentNumber).children().remove();
                $("#userComment"+commentNumber).text(commentPost[0]);
            }
        }
    })
}
function doUpdate(commentNumber, content) {
    $.ajax({
        url : "/commentWrite",
        type : "POST",
        dataType : "",
        data : {id : commentNumber, content : content},
        success : function () {
            getComment($("#currentCommentPage").val());
        }
    })
}
function doDelete(commentNumber) {
    console.log("삭제할 댓글의 번호 = ", commentNumber);
    if (confirm("정말 삭제하시겠습니까 ? "))
    {
        $.ajax({
            url: "/deleteComment",
            type : "POST",
            dataType : "",
            data : {id : commentNumber},
            success : function() {
                alert("삭제 완료..");
                getComment($("#currentCommentPage").val());
            }
        })
    }
}

function sendLoginForm() {
    if (confirm("로그인 후 이용이 가능합니다. 로그인 하시겠습니까?")) {
        document.location.href = "/login";
    }
}

function commentPaging(page) {
    let RowsPerPage = 15;
    let pageList;
    let commentCount;
    console.log("페이징 호출여부");
    $.ajax({
        type : "GET",
        url : "/getCommentNum",
        dataType : "text",
        data : {postId: $("#postId").val()},
        beforeSend : function() {
            $("#commentPage").empty();
        },
        success : function (data) {
            commentCount = data;
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
                    <span class="page-link" onclick="getComment(1)">
                    1
                    </span>
                 </li>`;
                $("#commentPage").append(str);
            } else {
                for (let i = 1; i < pageList; i++) {
                    if (i == page) {
                        let str = `<li class="page-item active">
                    <span class="page-link" onclick="getComment(${i})">
                    ${i}
                    </span>
                 </li>`;
                        $("#commentPage").append(str);
                    } else {
                        let str = `<li class="page-item">
                    <span class="page-link" onclick="getComment(${i})">
                    ${i}
                    </span>
                 </li>`;
                        $("#commentPage").append(str);
                    }
                }
            }
            let temp2 = `<li class="page-item" id = "next">
                    <span class="page-link" onclick="getComment(${parseInt($("#currentCommentPage").val()) + 1})" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </span>
                </li>`;
            $("#commentPage").append(temp2);
        }
    })
}