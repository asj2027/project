<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="hotel.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <title>호텔관리</title>
</head>
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
<script src="hotel.js" charset="utf-8"></script>
</html>