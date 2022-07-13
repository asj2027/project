<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>호텔관리</title>
</head>
<style>
@font-face {
    font-family: 'S-CoreDream-3Light';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
table {
    height: 500px;
    font-family: 'S-CoreDream-3Light';
    font-size: 14pt;
    text-align: center;
    background-color: cornsilk;
}
select,a {
    font-family: 'S-CoreDream-3Light';
    font-size: 13pt;
}
#sel {
    width: 45%;
}
#sel2 {
    width: 100%;
    height: 300px;
    font-size: 14pt;
    padding: 10pt;
    background-color: antiquewhite;
}
#sel3 {
    width: 100%;
    height: 400px;
    font-size: 12pt;
    padding: 10pt;
    background-color: antiquewhite;
    white-space: nowrap;
}
option {
    margin-bottom: 5px;
    white-space: nowrap;
}
#ser, #sub, #re, #blank {
    font-family: 'S-CoreDream-3Light';
    font-size: 14px;
    font-weight: bold;
}
#snum, #yenum, #gname, #gtype, #gnum, #yename, #mobile, #gprice {
    font-family: 'S-CoreDream-3Light';
    font-size: 12pt;
}
#da1, #da2, #da3, #da4 {
    font-family: 'S-CoreDream-3Light';
    font-size: 10pt;
}
#sel4 {
    width: 100%;
    height: 430px;
    font-size: 14pt;
    padding: 10px;
    background-color: antiquewhite;
}
option {
    margin-bottom: 10px;
}
#sub2, #del2, #remove {
    width: 80px;
    height: 35px;
    font-family: 'S-CoreDream-3Light';
    font-size: 14px;
    font-weight: bold;
}
#ggname, #ggnum, #ggtype, #ggprice {
	font-family: 'S-CoreDream-3Light';
    font-size: 12pt;
    width: 150px;
}
#label {
	font-size: 9pt;
	font-weight: bold;
}
</style>
<body>
<a>예약관리</a>&nbsp;&nbsp;<a href="#" id="btnMenu">객실관리</a>
<table style="width: 100%;">
<tr>
    <td style="width: 33%; border: 1px solid black;">
    <table style="width:100%;">
        <tr><td>숙박기간&nbsp;<input type="date" id="da1">&nbsp;~&nbsp;<input type="date" id="da2"></td></tr>
        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;숙박인원&nbsp;<input type="number" id="snum" min="1">&nbsp;명</td></tr>
        <tr><td>객실종류&nbsp;<select id="sel" size="1">                                                                                                                                                  
        </select></td></tr>
        <tr><td align="center"><input type="button" id="ser" value="검색" class="btn btn-warning"></td></tr>
        <tr><td align="left"><label>예약가능객실</label></td></tr>
        <tr><td><select id="sel2" size="10"></select></td></tr>
    </table>
    </td>
    <td style="width: 33%; border: 1px solid black;">
    <table style="width:100%;">
    	<tr><td><input type="hidden" id="hi" value="" readonly></td></tr>
        <tr><td>예약번호&nbsp;<input type="text" id="yenum" readonly>&nbsp;&nbsp;&nbsp;</td></tr>
        <tr><td><input type="hidden" id="se" readonly></td></tr>
        <tr><td><input type="hidden" id="ty" readonly></td></tr>
        <tr><td>객실명&nbsp;<input type="text" id="gname" readonly></td></tr>
        <tr><td>객실종류&nbsp;<input type="text" id="gtype" readonly>&nbsp;&nbsp;&nbsp;</td></tr>
        <tr><td>&nbsp;숙박인원&nbsp;<input type="number" id="gnum">&nbsp;명</td></tr>
        <tr><td>숙박기간&nbsp;<input type="date" id="da3" readonly>&nbsp;~&nbsp;<input type="date" id="da4" readonly></td></tr>
        <tr><td>예약자&nbsp;<input type="text" id="yename"></td></tr>
        <tr><td>모바일&nbsp;<input type="text" id="mobile"></td></tr>
        <tr><td>&nbsp;숙박총액&nbsp;<input type="text" id="gprice" readonly>&nbsp;원</td></tr>
        <tr><td colspan="2" align="center">
            <input type="submit" id="sub" value="예약등록" class="btn btn-warning">
            <input type="reset" id="re" value="예약취소" class="btn btn-warning">
        </td></tr>
        <tr><td align="center"><input type="reset" id="blank" value="비우기" class="btn btn-warning"></td></tr>
    </table>
    </td>
    <td style="width: 33%; border: 1px solid black;">
    <table style="width:100%;">
        <tr><td align="left"><label>예약내역</label></td></tr>
        <tr><td><select id="sel3" size="10"></select></td></tr>
    </table>
    </td>
</tr>
</table><br>
<div id="dvMenu" style="display: none;">
<table>
<tr>
    <td><select id="sel4" size="12" style="width: 250px; height: 350px;"></select></td>
    <td style="vertical-align:top;">
        <input type="hidden" id="optype" value="Add" readonly>
        <table style="width: 100%;">
        <tr><td><input type="hidden" id="seq" value="" readonly></td></tr>
        <tr><td>&nbsp;&nbsp;객실명&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" id="ggname"></td></tr>
        <tr><td>객실종류&nbsp;&nbsp;&nbsp;&nbsp;<select id="sel5" size="1"></select></td></tr>
        <tr><td>숙박가능인원&nbsp; <input type="number" id="ggnum">&nbsp;명</td></tr>
        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;1박요금&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="ggprice">&nbsp;원</td></tr>
        <tr><td><button id="sub2" class="btn btn-warning">등록</button>&nbsp;&nbsp;<button id="del2" class="btn btn-warning">삭제</button></td></tr>
        <tr><td><button id="remove" class="btn btn-warning">비우기</button></td></tr>
        </table>
    </td>
</tr>
</table>
</div>
</body>
<script>
$(document)
.ready(function() {
	$('#snum').val(1);
	type();
	$('#hi').val('Add');
    $('#dvMenu').dialog({
        autoOpen:false,
        width:600,
        height:600,
        open: function(data) {
            hlist();
            type();
       		$('#optype').val('Add');
        },
        close: function() {
			$('#remove').trigger('click');
        }
    })
})
.on('click','#btnMenu',function() {
	$('#dvMenu').dialog('open');
})
.on('click','#sel4 option',function() {
	text=$('#sel4 option:selected').text();
    ar=text.split('/');
    $('#ggname').val(ar[0]);
    $('#sel5').val(ar[1]);
    $('#ggnum').val(parseInt(ar[2]));
    $('#ggprice').val(parseInt(ar[3]));
    $('#optype').val('Update');
    return false;
})
.on('click','#remove',function() {
	$('#ggname, #sel5, #ggnum, #ggprice').val('');
    $('#optype').val('Add');
    return false;
})
.on('click','#del2',function() {
	let answer=confirm("정말 지우시겠습니까?");
	if(!answer) return false;
	$.ajax({
		type:'get',
		url:'hdelete',
		data:{seq:$('#sel4 option:selected').val()},
		dataType:'text',
		success:function(){
			$('#ggname, #sel5, #ggnum, #ggprice').val('');
			hlist();
		},
		error:function(){
			alert('삭제 실패');
		},
		complete:function(){}
	});
    $('#optype').val('Add');
    $('#remove').trigger('click');
    return false;
})
.on('click','#sub2',function() {
    if($('#ggname').val()=='') return false;
    if($('#ggnum').val()=='') return false;
    if($('#ggprice').val()=='') return false;
    if($('#optype').val()=='Update') {
    	$.ajax({
			type:'get',
			url:'hupdate',
			dataType:'text',
			data: {seq:$('#sel4 option:selected').val(), name:$('#ggname').val(), type:$('#sel5').val(), num:$('#ggnum').val(), price:$('#ggprice').val()},
			success:function() {
				$('#ggname, #sel5, #ggnum, #ggprice').val('');
				hlist();
				$('#optype').val('Add');
			},
			error:function() {
				alert('데이터 등록 실패');
			},
			complete:function(){}
		})
    } else if ($('#optype').val()=='Add') {
    	$.ajax({
    		type:'get',
    		url:'hadd',
    		data:{name:$('#ggname').val(), type:$('#sel5').val(), num:$('#ggnum').val(), price:$('#ggprice').val()},
    		dataType:'text',
    		success:function() {
    			hlist();
    			$('#ggname, #sel5, #ggnum, #ggprice').val('');
    		},
    		error:function() {},
    		complete:function() {}
    	})
    }
    $('#optype').val('Add');
    $('#remove').trigger('click');
})
.on('click','#sel2 option',function() {
	$('#yenum,#yename,#mobile,#gprice').val('');
	a=$('#sel2 option:selected').text();
    ar=a.split(' ');
    $('#gname').val(ar[0]);
    $('#gtype').val($('#sel option:selected').text());
    $('#gnum').val($('#snum').val());
    $('#da3').val($('#da1').val());
    $('#da4').val($('#da2').val());
    ar1=$('#da3').val().split('-');
    ar2=$('#da4').val().split('-');
    bb=parseInt(ar2[2]-ar1[2]);
    $('#gprice').val(parseInt(ar[2])*bb);
    $('#sub').val("예약등록");
    $('#hi').val('Add');
})
.on('click','#blank',function() {
	$('#yenum, #gname, #gtype, #gnum, #da3, #da4, #yename, #mobile, #gprice').val('');
	$('#hi').val('Add');
    return false;
})
.on('click','#re',function() {
	let answer=confirm("지우시겠습니까?");
	if(!answer) return false;
	$.ajax({
		type:'get',
		url:'condelete',
		data:{order_no:$('#sel3 option:selected').val()},
		dataType:'text',
		success:function(){
			data();
    		not();
    		$('#yenum, #gname, #gtype, #gnum, #da3, #da4, #yename, #mobile, #gprice').val('');
    		$('#hi').val('Add');
		},
		error:function(){
			alert('삭제 실패');
		},
		complete:function(){}
	});
})
.on('click','#sel3 option:selected',function() {
	$('#gnum').attr('readonly',false);
	$('#sub').val("예약수정");
	ydata();
    $('#hi').val('Update');
})
.on('click','#sub',function() {
    if($('#gnum').val()=='') return false;
    if($('#yename').val()=='') return false;
	if($('#mobile').val()=='') return false;
	if($('#gnum').val()>$('#sel option:selected').val() || $('#gnum').val()>=10) {
		alert("객실 정원 초과");
		return false;
	}
    if($('#hi').val()=='Update') {
			$.ajax({
			type:'get',
			url:'conupdate',
			dataType:'text',
			data: {order_no:$('#sel3 option:selected').val(), tnum:$('#gnum').val(), tname:$('#yename').val(), mobile:$('#mobile').val()},
			success:function() {
				data();
    			not();
    			$('#yenum, #gname, #gtype, #gnum, #da3, #da4, #yename, #mobile, #gprice').val('');
    			$('#sub').val("예약등록");
			},
			error:function() {
				alert('데이터 등록 실패');
			},
			complete:function(){}
		})
		$('#hi').val('Add');	
    } else if($('#hi').val()=='Add') {
		$('#gnum').attr('readonly',true);
    	$.ajax({
    		type:'get',
    		url:'conadd',
    		data:{seq:$('#sel2 option:selected').val(),
    				tnum:$('#gnum').val(), checkin:$('#da3').val(), checkout:$('#da4').val(), type:$('#sel option:selected').attr("value"),
    				tname:$('#yename').val(), mobile:$('#mobile').val(), tprice:$('#gprice').val()},
    		dataType:'text',
    		success:function() {
				data();
    			not();
    			$('#yenum, #gname, #gtype, #gnum, #da3, #da4, #yename, #mobile, #gprice').val('');
    		},
    		error:function() {
				alert('데이터 등록 실패');
			},
    		complete:function() {}
    	})
    }
/*    $('#blank').trigger('click');*/
})
.on('click','#ser',function() {
	data();
	not();
	$('#gnum').attr('readonly',true);
})
function hlist() {
	$.ajax({
		type:'get',
		url:'hlist',
		dataType:'json',
		success:function(ja) {
			$('#sel4').empty();
			for(let i=0;i<ja.length;i++) {
			let jo=ja[i];
			let str='<option value='+jo['seq']+'>'+jo['name']+'/'+jo['type']+'/'+jo['num']+'/'+jo['price']+'</option>';
			$('#sel4').append(str);
			}
		},
		error:function() {},
		complete:function() {}
	})
}
function data() {
	$.ajax({
		type:'get',
		url:'ye',
		data:{type:$('#sel option:selected').val(),num:$('#snum').val(),
			  checkin:$('#da1').val(),checkout:$('#da2').val()},
		dataType:'json',
		success:function(ja) {
			$('#sel2').empty();
			for(let i=0;i<ja.length;i++) {
			let jo=ja[i];
			console.log(ja[i]);
			let str='<option value='+jo['seq']+'>'+jo['name']+' '+jo['num']+' '+jo['price']+'</option>';
			$('#sel2').append(str);
			}
		},
		error:function() {},
		complete:function() {}
	})
}
function not() {
	$.ajax({
		type:'get',
		url:'not',
		data:{type:$('#sel option:selected').val(), tnum:$('#snum').val(),
			  checkin:$('#da1').val(),checkout:$('#da2').val()},
		dataType:'json',
		success:function(ja) {
			$('#sel3').empty();
			for(let i=0;i<ja.length;i++) {
			let jo=ja[i];
			console.log(ja[i]);
			let str='<option value='+jo['order_no']+'>'+jo['name']+' '+jo['tnum']+' '
						+jo['tname']+' '+jo['checkin']+' ~ '+jo['checkout']+'</option>';
			$('#sel3').append(str);
			}
		},
		error:function() {},
		complete:function() {}
	})
}
function type() {
	$.ajax({
		type:'get',
		url:'type',
		dataType:'json',
		success:function(ja) {
			$('#sel5').empty();
			$('#sel').empty();
			$('#sel5').append('<option value=-1>-</option>')
			for(let i=0;i<ja.length;i++) {
			let jo=ja[i];
			let str='<option value='+jo['type']+'>'+jo['typename']+'</option>';
			$('#sel5').append(str);
			$('#sel').append(str);
			}
		},
		error:function() {},
		complete:function() {}
	})
}
function ydata() {
	$.ajax({
		type:'get',
		url:'conlist',
		data:{order_no:$('#sel3 option:selected').val()},
		dataType:'json',
		success:function(data) {
			for(let i=0;i<data.length;i++) {
				let jo=data[i];
				$('#yenum').val(jo['order_no']),
				$('#gname').val(jo['name']);
				$('#gtype').val(jo['typename']);
				$('#gnum').val(jo['tnum']);
				$('#da3').val(jo['checkin']);
				$('#da4').val(jo['checkout']);
				$('#yename').val(jo['tname']);
				$('#mobile').val(jo['mobile']);
				$('#gprice').val(jo['tprice']);
			}
		},
		error:function() {},
		complete:function() {}
	})
}
</script>
</html>