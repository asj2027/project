<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
table {border-collapse:collapse;}
th, td {border:1px solid darkgreen;}
#btnAdd, #btnReset, #btnDelete {
	border:1px solid purple;
	background-color:white;
	font-weight: bold;
	width: 55px;
	height: 25px;
}
</style>
<body>
<table id=tblEdit>
	<tr><td colspan=2 align=center><a style='font-weight:bold;'>메뉴관리</a></td></tr>
	<tr><td>메뉴명</td><td><input type=text id=name></td></tr>
	<tr><td>가격</td><td><input type=number id=price></td></tr>
	<tr><td colspan=2 align=center><input type=button id=btnAdd value='추가'>
						<input type=button id=btnReset value='비우기'></td></tr>
</table>
<input type=hidden id=seqno>
<br><br>
<table id=tblData>
	<tr><th colspan=4 align=center><a style='font-weight:bold;'>메뉴리스트</a>
	<tr><th>번호</th><th>메뉴명</th><th>가격</th><th>삭제</th></tr>
</table>
</body>
<script src='https://code.jquery.com/jquery-3.4.1.js'></script>
<script>
$(document)
.ready(function() {
	list();
})
.on('click','#btnAdd',function() {
	if($('#name').val()=='' || $('#price').val()=='') {
		alert('메뉴명과 가격은 모두 입력되어야 합니다.');
		return false;
	}
	if($(this).val()=='추가') {
		$.get(
			"addnew",
			{name:$('#name').val(),price:$('#price').val()},
			function(data){
				list();
				$('#btnReset').trigger('click');
			},"json");
	} else {
		$.get(
			"change",
			{name:$('#name').val(), price:$('#price').val(),seqno:$('#seqno').val()},
			function(data){
				list();
				$('#btnReset').trigger('click');
			},"json");
	}
})
.on('click','#btnReset',function() {
	$('#name,#price').val('');
	$('#btnAdd').val('추가');
	return false;
})
.on('click','#btnDelete',function(){
	if(!confirm('삭제하시겠습니까?')) return false;
	let seqno=$(this).attr('seqno');
	$.get(
		"delete",
		{seqno:seqno},
		function(data){
			list();
			$('#btnReset').trigger('click');
		},"json");
})
.on('click','#tblData tr:gt(1)',function(){
	let seqno=$(this).find('td:eq(0)').text();
	$('#seqno').val(seqno);
	let name=$(this).find('td:eq(1)').text();
	$('#name').val(name);
	let price=$(this).find('td:eq(2)').text();
	$('#price').val(price);
	$('#btnAdd').val('수정');
	return false;
})
function list(){
	$.ajax({
		url : 'menulist',
		data : '',
		dataType : 'json',
		type : 'get',
		beforeSend:function(){
			$('#tblData tr:gt(1)').remove();
		},
		success : function(data) {
			for (let i = 0; i < data.length; i++) {
				menuitem = data[i];
				let str = '<tr><td>' + menuitem['seqno']
						+ '</td><td>' + menuitem['name']
						+ '</td><td>' + menuitem['price']
						+ '</td><td><button id=btnDelete seqno='+menuitem['seqno']+'>삭제</button></td></tr>';
				$('#tblData').append(str);
			}
		}
	});
}
</script>
</html>