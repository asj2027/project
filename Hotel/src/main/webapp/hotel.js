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
	let answer=confirm("정말로 지우시겠습니까?");
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