$(document).on("click", "#btnToday", function() {
    let today = getToday();
    $.ajax({
        type: "POST",
        url: "/history/reserve/today",
        data: {userId: $("#usernum").val(), date: today},
        dataType: "JSON",
        beforeSend: function () {
            $("#paymentTable").empty();
        },
        success: function (reserve) {
            console.log(reserve['isPayment']);
            let temp = `<tr class ="text-center">
                            <th>예매번호</th>
                            <th>제목</th>
                            <th>예매일자</th>
                            <th>상태</th>
                            <th>확인/신청</th>
                        </tr>`
            $("#paymentTable").append(temp);
            let state;
            if (reserve['isPayment'] == 'N') {
                state = "예약취소";
                let str = `
                    <tr class = "p-5 text-center">
                        <td id = ${reserve['orderId']}>${reserve['orderId']}</td>
                        <td>${reserve['exhibitName']}</td>
                        <td>${reserve['reserveDate']}</td>
                        <td>${state}</td>
                    </tr>`;
                $("#paymentTable").append(str);
            } else {
                state = "예약완료"
                let str = `
                    <tr class = "p-5 text-center">
                        <td id = ${reserve['orderId']}>${reserve['orderId']}</td>
                        <td>${reserve['exhibitName']}</td>
                        <td>${reserve['reserveDate']}</td>
                        <td>${state}</td>
                        <td><button type="button" class="btn btn-outline-dark" id="btnDetail">상세보기</button></td>
                    </tr>`;
                $("#paymentTable").append(str);
            }
        }
    })
})


function getPaymentHistory() {
    $.ajax({
        type : 'POST',
        url : '/history/reserve/'+ $("#usernum").val(),
        data : '',
        dataType : "JSON",
        beforeSend : function() {
            $("#paymentTable").empty();
        },
        success : function (data) {
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
                        <td id = ${reserve['orderId']}>${reserve['orderId']}</td>
                        <td>${reserve['exhibitionName']}</td>
                        <td>${reserve['reserveDate']}</td>
                        <td>${state}</td>
                    </tr>`;
                    $("#paymentTable").append(str);
                } else {
                    state = "예약완료"
                    let str = `
                    <tr class = "p-5 text-center">
                        <td id = ${reserve['orderId']}>${reserve['orderId']}</td>
                        <td>${reserve['exhibitionName']}</td>
                        <td>${reserve['reserveDate']}</td>
                        <td>${state}</td>
                        <td><button type="button" class="btn btn-outline-dark" id="btnDetail">상세보기</button></td>
                    </tr>`;
                    $("#paymentTable").append(str);
                }
            }
        }
    })
}

function getToday(){
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
    let theDate  = currentDay.getDate();
    let theDayOfWeek = currentDay.getDay();

    let thisWeek = [];

    for(let i=0; i<7; i++) {
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
    firstDay = firstDay.toISOString().substring(0,10);
    lastDay = lastDay.toISOString().substring(0,10);
    let thisMonth = [];
    thisMonth[0] = firstDay;
    thisMonth[1] = lastDay;
    return thisMonth;
}

$(document)
    .on('click','#logo',function(){
        document.location.href='/';
    })
$(document)
    .ready(function() {
        getPaymentHistory();
        $('#history').dialog({
            autoOpen:false,
            width:600,
            height:800,
            open: function() {

            },
            close: function() {
            }
        })
    })
    .on('click','#btnDetail',function() {
        let id = $(this).parent().parent().find('td:eq(0)').attr('id');
        console.log("오픈할 id = " + id);
        $.ajax({
            type : 'POST',
            url : '/history/reserve/detail/' + id,
            data : {userId : $("#usernum").val()},
            dataType : 'JSON',
            beforeSend : function () {
                $("#tb1").empty();
            },
            success : function (data) {
                console.log("성공 " + data['orderId']);
                let str = `
	 <tr>
		<th>예매번호</th>
		<td id = orderId>${data['orderId']}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${data['exhibitionName']}</td>
	</tr>
	<tr>
		<th>날짜</th>
		<td>${data['reserveDate']}</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>${data['representName']}</td>
	</tr>
	<tr>
		<th>인원</th>
		<td>${data['person']}</td>
	</tr>
	<tr>
		<th>상태</th>
		<td>예매완료</td>
	</tr>
	<tr style="display: none;">
	</tr>
	<tr>
		<td colspan="2">
			<p style="margin-top: 100px;"></p>
			<button type="button" class="btn btn-outline-dark" id="btnC">취소하기</button>
			<button type="button" class="btn btn-outline-dark" id="btnCancel">닫기</button>
		</td>
	</tr>`
                $("#tb1").append(str);
            }
        })
        $('#history').dialog('open');
    })
    .on('click','#btnCancel',function() {
        $('#history').dialog('close');
        getPaymentHistory();
    })
    .on('click','#btnC',function() {
        if(!confirm('예매를 취소하시겠습니까?'))  {
            return false;
        }
        else {
            $.ajax({
                type : 'POST',
                url : '/history/reserve/delete/'+$("#orderId").text(),
                data : '',
                dataType : 'JSON',
                success : function() {
                    $('#history').dialog('close');
                    getPaymentHistory();
                }
            })
        }
    })
    .on("click", "#btnWeek", function() {
        let thisWeek = getThisWeek();
        $.ajax({
            type : "POST",
            url : "/history/reserve/thisWeek",
            data : {userId :$("#usernum").val(), startDate : thisWeek[0], endDate : thisWeek[6]},
            dataType : "JSON",
            beforeSend : function() {
              $("#paymentTable").empty();
            },
            success : function(data) {
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
                        <td id = ${reserve['orderId']}>${reserve['orderId']}</td>
                        <td>${reserve['exhibitName']}</td>
                        <td>${reserve['reserveDate']}</td>
                        <td>${state}</td>
                    </tr>`;
                        $("#paymentTable").append(str);
                    } else {
                        state = "예약완료"
                        let str = `
                    <tr class = "p-5 text-center">
                        <td id = ${reserve['orderId']}>${reserve['orderId']}</td>
                        <td>${reserve['exhibitName']}</td>
                        <td>${reserve['reserveDate']}</td>
                        <td>${state}</td>
                        <td><button type="button" class="btn btn-outline-dark" id="btnDetail">상세보기</button></td>
                    </tr>`;
                        $("#paymentTable").append(str);
                    }
                }
            }
        })
    })
    .on("click", "#btnMonth", function() {
        let date = getThisMonth();
        $.ajax({
            type : "POST",
            url : "/history/reserve/thisMonth",
            data : {userId :$("#usernum").val(), startDate : date[0], endDate : date[1]},
            dataType : "JSON",
            beforeSend : function() {
                $("#paymentTable").empty()
            },
            success : function(data) {
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
                        <td id = ${reserve['orderId']}>${reserve['orderId']}</td>
                        <td>${reserve['exhibitName']}</td>
                        <td>${reserve['reserveDate']}</td>
                        <td>${state}</td>
                    </tr>`;
                        $("#paymentTable").append(str);
                    } else {
                        state = "예약완료"
                        let str = `
                    <tr class = "p-5 text-center">
                        <td id = ${reserve['orderId']}>${reserve['orderId']}</td>
                        <td>${reserve['exhibitName']}</td>
                        <td>${reserve['reserveDate']}</td>
                        <td>${state}</td>
                        <td><button type="button" class="btn btn-outline-dark" id="btnDetail">상세보기</button></td>
                    </tr>`;
                        $("#paymentTable").append(str);
                    }
                }
            }
        })
    })
