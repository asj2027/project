<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="cafe.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <title>Cafe</title>
</head>
<body>
<h1 style="text-align: center;">ī���ֹ�����</h1>
<table style="width: 100%;">
<tr>
    <td style="width: 33%; border: 1px solid black;">
        <table style="width:100%; background-color: #EBF7FF;">
            <tr><td style="text-align:center;"><label>�޴����</label></td></tr>
            <tr><td><select id="sel" size="6"></select></td></tr>
            <tr><td>�޴�&nbsp;<input type="text" id="menu" readonly>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
            <tr><td>����&nbsp;<input type="number" id="su" min="1" max="100">&nbsp;��</td></tr>
            <tr><td>����&nbsp;<input type="number" id="price" readonly>&nbsp;��</td></tr>
            <tr><td colspan="2" align="center">
                    <input type="submit" value="�߰�" id="sub">
                    <input type="reset" value="���" id="reset">
                </td></tr>
        </table>
    </td>
    <td style="width: 33%; border: 1px solid black;">
        <table style="width:100%; background-color: #EBF7FF;">
            <tr><td style="text-align:center;"><label>�ֹ����</label></td></tr>
            <tr><td style="vertical-align: top;"><select size="6" id="sel2"></select></td></tr>
            <tr><td>����Ϲ�ȣ&nbsp;<input type="text" id="mobile" maxlength="8">&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
            <tr><td>&nbsp;&nbsp;&nbsp;����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="number" id="t_price" readonly>&nbsp;��</td></tr>
            <tr><td colspan="2" align="center">
                    <input type="submit" value="�ֹ��Ϸ�" id="sub2">
                    <input type="reset" value="���" id="reset2">
                </td></tr>
        </table>
    </td>
    <td style="width: 33%; border: 1px solid black;">
        <table style="width:100%; background-color:#EBF7FF;">
            <tr><td style="text-align:center;"><label>����</label></td></tr>
            <tr><td style="vertical-align: top;"><select size="6" id="sel3"></select></td></tr>
            <tr><td>�����հ�&nbsp;<input type="text" id="mm" readonly>&nbsp;��</td></tr>
        </table>
    </td>
</tr>
</table><br>
<a href="#" id="btnMenu">�޴�����</a>
<div id="dvMenu" style="display: none;">
<table style="background-color: #EBF7FF;">
<tr>
    <td><select id="selMenu1" size="12" style="width: 250px; height: 350px;"></select></td>
    <td style="vertical-align:top;">
        <input type="text" id="optype" value="Add" readonly>
        <table style="width: 100%;">
        <tr><td><input type="hidden" id="seqnum" value="" readonly></td></tr>
        <tr><td>�޴���&nbsp;<input type="text" id="_name" size="12"></td></tr>
        <tr><td>����&nbsp;<input type="number" id="_price" min="1" max="99999">&nbsp;��</td></tr>
        <tr><td>&nbsp;</td></tr>
        <tr><td><button id="btnAdd">���</button>&nbsp;&nbsp;<button id="btnDelete">����</button></td></tr>
        <tr><td><button id="btnRemove">�����</button>&nbsp;&nbsp;<button id="btnCancel">����</button></td></tr>
        </table>
    </td>
</tr>
</table>
</div>
</body>
<script>
sum=0;
ssum=0;
$(document)
.ready(function() {
//    arMenu=['Latte','Mocca','Esppresso','Cappuccino','Americano','Greentea'];
//    arPrice=[3000,3500,2500,3800,2700,2500];
//    for(i=0;i<arMenu.length;i++){
//        let str='<option value=' +arMenu[i] +'>' +arMenu[i] +': ' +arPrice[i] +'</option>';
//        $('#sel').append(str);
	order();
	loadData();
    $('#dvMenu').dialog({
        autoOpen:false,
        width:550,
        height:600,
        open: function() {
        	loadMenu();
        },
        close: function() {
        	loadData();
        }
    })
})
.on('click','#sel', function() {
    m_text=$('#sel option:selected').text();
    ar=m_text.split(':');
    $('#menu').val(ar[0]);
    $('#su').val(1);
    m_price=parseInt(ar[1]);
    $('#price').val(m_price);
    return false;
})
.on('change','#su', function() {
    a=$('#su').val();
    b=m_price;
    $('#price').val(a*b);
    return false;
})
.on('click','#sub',function() {
    ss=$('#su').val();
    ms=ss*ar[1];
    op='<option value='+$('#sel option:selected').val()+'>' +ar[0] +' - ' +ss +' - ' +ms +'</option>';
    $('#sel2').append(op);
    sum+=ms;
    $('#t_price').val(sum);
    $('#reset').trigger('click');
    return false;
})
.on('click','#reset', function() {
    $('#menu,#su,#price').val('');
    return false;
})
.on('click','#sub2',function() {
    if($('#mobile').val()=='') {
        $('#mobile').val('--------');
    }
    for(let i=0;i<$('#sel2 option').length;i++) {
		let temp=$('#sel2 option:eq('+i+')').text().split("-");
    	$.ajax({
			type:'get',
			url:'addIncome',
			data:{mobile:$('#mobile').val(), 
			  	seqno:$('#sel2 option:eq('+i+')').val(),
			  	qty:parseInt(temp[1]),
			  	price:parseInt(temp[2])},
			dataType:'text',
			success:function() {
				order();
//				Sum();
				$('#sel2').empty();
				$('#mobile').val('');
				$('#t_price').val('');
				sum=0;
			},
			error:function() {},
			complete:function() {}
		})
    }
    $('#t_price').val(0);
})
.on('click','#reset2', function() {
    $('#mobile').val('');
    $('#t_price').val(0);
    $('#sel2').empty();
    sum=0;
    return false;
})
.on('click','#btnMenu',function() {
    $('#dvMenu').dialog('open');
})
.on('click','#btnCancel',function() {
    $('#dvMenu').dialog('close');
})
.on('click','#selMenu1 option', function() {
    let str=$(this).text();
    let bb=str.split(':');
    $("#_name").val(bb[0]);
    $('#_price').val(parseInt(bb[1]));
    $('#optype').val('Update');
    return false;
})
.on('click','#btnDelete',function() {
	let answer=confirm("������ ����ðڽ��ϱ�?");
	if(!answer) return false;
	$.ajax({
		type:'get',
		url:'deletemenu',
		data:{seqno:$('#selMenu1 option:selected').val()},
		dataType:'text',
		success:function(){
			$('#_name,#_price').val('');
			loadMenu();
		},
		error:function(){
			alert('���� ����');
		},
		complete:function(){}
	});
    $('#optype').val('Add');
    $('#btnRemove').trigger('click');
    return false;
})
.on('click','#btnRemove',function() {
    $('#_name,#_price').val('');
    $('#optype').val('Add');
    return false;
})
.on('click','#btnAdd',function() {
    if($('#_name').val()=='') return false;
    if($('#_price').val()=='') return false;
    if($('#optype').val()=='Update') {
    	$.ajax({
			type:'get',
			url:'modify',
			dataType:'text',
			data: {seqno:$('#selMenu1 option:selected').val(), name:$('#_name').val(), price:$('#_price').val()},
			success:function() {
				$('#_name,#_price').val('');
				loadMenu();
				$('#optype').val('Add');
			},
			error:function() {
				alert('������ ��� ����');
			},
			complete:function(){}
		})
    } else if ($('#optype').val()=='Add') {
    	$.ajax({
    		type:'get',
    		url:'addmenu',
    		data:{name:$('#_name').val(), price:$('#_price').val()},
    		dataType:'text',
    		success:function() {
    			loadMenu();
    			$('#_name,#_price').val('');
    		},
    		error:function() {},
    		complete:function() {}
    	})
    }
    $('#optype').val('Add');
    $('#btnRemove').trigger('click');
})
function getToday() {
    let dt=new Date();
    let today=dt.getFullYear()+'-';
    today+=dt.getMonth()<9?'0'+(dt.getMonth()+1):dt.getMonth()+1;
    today+='-'+(dt.getDate()<10?'0'+dt.getDate():dt.getDate());
    today+='&nbsp;'+(dt.getHours()<10?'0'+dt.getHours():dt.getHours());
    today+=':'+(dt.getMinutes()<10?'0'+dt.getMinutes():dt.getMinutes());
    return today;
}
function loadData() {
	$.ajax({
    	type:'get',
    	url:'menulist',
    	dataType:'json',
    	success:function(ja) {
//    		$('#sel').empty().append(data);
//			console.log(ja);
			$('#sel').empty();
			for(let i=0;i<ja.length;i++) {
    		let jo=ja[i];
			let str='<option value='+jo['seqno']+'>'+jo['name']+': '+jo['price']+'</option>';
			$('#sel').append(str);
			}
    	},
    	error:function() {},
    	complete:function() {}
	})
}
function loadMenu() {
	$.ajax({
		type:'get',
		url:'menulist',
		dataType:'json',
		success:function(ja) {
//			$('#selMenu1').empty().append(data);
			$('#selMenu1').empty();
			for(let i=0;i<ja.length;i++) {
			let jo=ja[i];
			let str='<option value='+jo['seqno']+'>'+jo['name']+': '+jo['price']+'</option>';
			$('#selMenu1').append(str);
			}
		},
		error:function() {},
		complete:function() {}
	})
}
function order() {
	$.ajax({
		type:'get',
		url:'Incomelist',
		dataType:'json',
		success:function(ja) {
//			$('#sel3').empty().append(data);
			$('#sel3').empty();
			for(let i=0;i<ja.length;i++) {
				let jo=ja[i];
				let str='<option>'+jo['mobile']+' / '+jo['menuname']+' / '+jo['qty']+' / '+jo['price']+' / '+jo['income_date']+'</option>';
				$('#sel3').append(str);
			}
			Sum();
		},
		error:function() {},
		complete:function() {}
	})
}
function Sum() {
	$.ajax({
		type:'get',
		url:'sum',
		dataType:'text',
		success:function(data) {
			$('#mm').val(data);
		},
		error:function() {},
		complete:function() {}
	})
}
</script>
</html>