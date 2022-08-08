<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tf" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>detailProduct</title>
<style>
.Area {	margin: 10px 0;}
select {width: 100px;}
label {	display: inline-block;width: 70px;padding: 5px;}
label[for='gdsDes'] {display: block;}
input {	width: 150px;}
textarea#proDes {width: 400px;height: 180px;}
.oriImg {width: 300px;height: auto;}
.thumbImg {	width: 300px;	height: auto;}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">

	var proNum = $("#proNum").val();
	var memId = $("#login_memId").val()

	$(".btn-order").click(function() {
	
	location.assign("/order/insert");
	});

	$(".btn-cart").click(function() {
		$.ajax({
			type : "post",
			url : "/order/cart/" + productId,
			data : {
				productId : productId
			},
			dataType : "text",
			success : function(result) {

				if (result.trim() == 'add_success') {
					var check = confirm("카트에 등록되었습니다.");
					if (check) {
						location.assign("/order/mycart/" + userid);
					}
				} else if (result.trim() == 'already_existed') {
					alert("이미 카트에 등록된 상품입니다.");
				}
			}
		});
	});

	$(".btn-wishlist").click(function() {
		alert("상품을 위시리스트에 추가하였습니다.");
	});
</script>
</head>
<body>
	<hr>
	<h1>상품 상세</h1>
	<hr>
	<p>상품명: ${data.proName }</p>
	<p>
		상품가격:
		<fmt:formatNumber type="number" maxFractionDigits="3"
			value="${data.proPrice }" />
		원
	</p>
	<p>
		입고 날짜 :
		<fmt:parseDate value="${data.proDate}" pattern="yyyy-MM-dd HH:mm"
			var="myDate" />
		<fmt:formatDate value="${myDate}" pattern="yyyy-MM-dd" />
	<hr>
	<div class="Area">
		<label for="proDes">상품 소개 </label> <br> <span>
			"${data.proDes}" </span>
	</div>
	<div class="inputArea">
		<label for="proImg"> 이미지</label>
		<p>상품 이미지</p>
		<img src="<%='.' %>${data.proThumbImg}" class="thumbImg" />
	</div>
	<div class="inputArea">
		<p>
			<%-- 	<form method="post" action="addProCartVoPost">
			<input type="hidden" name="proNum" value="${data.proNum}">
			<input type="submit" value="장바구니">
		</form> --%>
			<%-- 	<form method="post" action="addProCartVoPost">
			<input type="hidden" name="proNum" value="${data.proNum}">
			<input type="submit" value="장바구니"> --%>

			<button class="btn btn-default btn-order">주문하기</button>
			<button class="btn btn-default btn-cart">장바구니</button>
			<button class="btn btn-default btn-wishlist">위시리스트</button>
	</div>
</body>
</html>

<!-- 
<form action="/examples/media/action_target.php" method="get">
    이름 : <input type="text" name="st_name"><br>
    학번 : <input type="text" name="st_id"><br>
    학과 : <input type="text" name="department"><br>
    <input type="submit">
</form>
-->
