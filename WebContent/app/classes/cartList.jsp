<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html>
<html lang="ko-KR">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<%-- templete include --%>
<%@ include file="../main/src_forInclude.html" %>

<!-- import for cart.jsp -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app/classes/css/class-style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app/classes/css/cart-style.css">

<%-- JSTL --%>
<c:set var="cartList" value="${requestScope.cartList}" />
<c:set var="totalPrice" value="${requestScope.totalPrice}" />
<c:set var="genreList" value="${requestScope.genreList}" />

<head>
<title>FROmme - 내 손으로 만드는 특별한 순간</title>
</head>

<body>

	<!-- HEADER -->
	<header role="banner" class="position-absolute">
		<%@ include file="../main/header.jsp"%>
	</header>
	<div class="margin-bottom-70 "></div>
	<hr>
	<div></div>

	<section>
		<div class="cart-wrapper">
			<div class="margin-left-right-80 margin-m-left-right-10">
				<div class="page-title">
					<div class="text-center">
						<div style="margin: 140px 70px;">
							<h1>장바구니</h1>
						</div>
					</div>
				</div>
				<div class="padding">
					<div class="margin-bottom-30 h6">
					<c:set var="cartInfo" value="창을 닫으면 장바구니 목록이 사라집니다."/>
					<c:if test="${session_id != null }">
						<c:set var="cartInfo" value="장바구니에 담긴 상품은 30일간 보관됩니다."/>
					</c:if>
						<p>${cartInfo }</p>
					</div>
				
 				<form
					action="${pageContext.request.contextPath}/classes/PaymentView.cls"
					method="post" name="cartForm" >
				
					
				
					<c:choose>
						<c:when test="${cartList != null and fn:length(cartList) > 0 }">
							<div class="shopping-cart">
			
								<div class="column-labels margin-left-right-15">

									<label class="product-image">Image</label> <label
										class="product-details">Product</label> <label
										class="product-price">가격</label> <label
										class="product-quantity">수량</label> <label
										class="product-removal">Remove</label> <label
										class="product-line-price">합계</label>
								</div>
								<c:forEach var="cartBean" items="${cartList}" varStatus="status">
									<div class="product"> 
									<span class="cart_no" hidden="hidden">${cartBean.getCart_no()}</span>
									<input class="product_cart_no" hidden="hidden" value="${cartBean.getCart_no()}">
										<div class="product-image">
											<img
													src="${pageContext.request.contextPath}/app/assets/img/class_thumbnail/classes_thumbnail_${ cartBean.getClasses_no()}.jpg"
													alt="${pageContext.request.contextPath}/app/assets/img/class_thumbnail/no_image.jpg" title="Portfolio Image" />
										</div>
										<div class="product-details">
										<input hidden="hidden" value="${cartBean.getCart_no() }">
											<div class="product-title">${cartBean.getClasses_name() }</div>
											<p class="product-description">[ 수강일 : ${fn:substring(cartBean.getChoice_date(),0, 11) }]</p>
										</div>
										<div class="product-price">${cartBean.getClasses_price() }</div>
										<div class="product-quantity"> 
											<input type="number" value="${cartBean.getCart_quantity() }" min="1">
										</div>
										<div class="product-removal">
											<input type="button" class="remove-product" value="삭제">
										</div>
										<div class="product-line-price">${cartBean.getCart_sum_price() }</div>
									</div>

								</c:forEach>
								<div class="margin-top-50">
									<div class="totals">
										<%--
										<div class="totals-item">
											<label>Subtotal</label>
											<div class="totals-value" id="cart-subtotal">0</div>
										</div>
										<div class="totals-item">
											<label>Tax (5%)</label>
											<div class="totals-value" id="cart-tax">3.60</div>
										</div>
										<div class="totals-item">
											<label>Shipping</label>
											<div class="totals-value" id="cart-shipping">15.00</div>
										</div>
										--%>
										<div class="totals-item totals-item-total margin-bottom-30">
											<label>결제 예정금액</label>
											<div class="totals-value h3" id="cart-total">${totalPrice}</div>
										</div>
										<div class="margin right">
											<input id="paymentProcess_button" type="button"
												class="button background-dark text-size-16" value="클래스 신청하기"
												onclick="goSubmit();"
												>
										</div>
									</div>
								</div>
							</div>

						</c:when>

						<c:otherwise>
						<div class="text-center">
							<p>장바구니가 비었습니다.</p>
						</div>
						</c:otherwise>
					</c:choose>
		</form>
				</div>

			</div>

		</div>
	</section>

	<div class="margin-top-60"></div>

	<!-- FOOTER -->
	<footer>
		<%@ include file="../main/footer.jsp"%>
	</footer>

</body>


<script>var contextPath = "${pageContext.request.contextPath}";</script>

<script>
/**
 * 장바구니 이벤트 script
 */
/* Set rates + misc */
var taxRate = 10;
var shippingRate = 0; 
var fadeTime = 300;


/* Assign actions */
$('.product-quantity input').change( function() {
  updateQuantity(this);
});

$('.product-removal input').click( function() {
  removeItem(this);
});


/* Recalculate cart */
function recalculateCart()
{
  var subtotal = 0;
  
  /* Sum up row totals */
  $('.product').each(function () {
    subtotal += parseInt($(this).children('.product-line-price').text());
  });
  
  /* Calculate totals */
  // var tax = subtotal * taxRate;
  // var shipping = (subtotal > 0 ? shippingRate : 0);
  // var total = subtotal + tax + shipping;
  var total = subtotal;
  
  /* Update totals display */
  $('.totals-value').fadeOut(fadeTime, function() {
    // $('#cart-subtotal').html(subtotal.toFixed(0));
    // $('#cart-tax').html(tax.toFixed(0));
    // $('#cart-shipping').html(shipping.toFixed(0));
    $('#cart-total').html(total.toFixed(0));
    if(total == 0){
      $('.checkout').fadeOut(fadeTime);
    }else{
      $('.checkout').fadeIn(fadeTime);
    }
    $('.totals-value').fadeIn(fadeTime);
  });
}


/* Update quantity */
function updateQuantity(quantityInput){
  /* Calculate line price */
  var productRow = $(quantityInput).parent().parent();
  var price = parseInt(productRow.children('.product-price').text());
  var quantity = $(quantityInput).val();
  var linePrice = price * quantity;
  
  var cart_no = productRow.find("span").text();
  
	$.ajax({
	    type : "GET",
	    url : "${pageContext.request.contextPath}/classes/CartModifyOk.cls?cart_no="+cart_no+"&quantity="+quantity,
	    dataType : "text",
	    error : function() {
	    	console.log("ajax 통신 오류");
	    },
	    success : function(data) {
	    	console.log("수정 확인");
	    }
	});
  
  
  /* Update line price display and recalc cart totals */
  productRow.children('.product-line-price').each(function () {
    $(this).fadeOut(fadeTime, function() {
      $(this).text(linePrice.toFixed(0));
      recalculateCart();
      $(this).fadeIn(fadeTime);
    });
  });  
}


/* Remove item from cart */

function removeItem(removeButton)
{
  /* Remove row from DOM and recalc cart total */
  var productRow = $(removeButton).parent().parent();
  var cart_no = productRow.find("span").text();

  productRow.slideUp(fadeTime, function() {
    productRow.remove();

	$.ajax({
	    type : "GET",
	    url : "${pageContext.request.contextPath}/classes/CartDeleteOk.cls?cart_no="+cart_no,
	    dataType : "text",
	    error : function() {
	    	console.log("ajax 통신 오류");
	    },
	    success : function(data) {
	    	console.log("삭제 확인");
	    }
	});
	
    recalculateCart();
  });
}
</script>

<script >
$('#paymentProcess_button').click( function() {
	//cartForm.submit();
	window.location.href = "${pageContext.request.contextPath}/classes/PaymentView.cls?isDirect=false";
});

function goSubmit() {
	// 공백체크
	cartForm.submit();
}

	
</script>

</html>