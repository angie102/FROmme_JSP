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

<head>
<title>FROmme - 내 손으로 만드는 특별한 순간</title>
</head>

<c:set var="orderList" value="${sessionScope.orderList }" />

<body>

	<!-- HEADER -->
	<header role="banner" class="position-absolute">
		<%@ include file="../main/header.jsp"%>
	</header>
	<div class="margin-bottom-70 "></div>
	<hr>
	<div></div>

	<section>
		<div class="orderlist-wrapper">
			<div class="margin-left-right-80 margin-m-left-right-10">
				<div class="page-title">
					<div class="text-center">
						<div style="margin: 140px 70px;">
							<h1>주문 완료</h1>
							<div class="margin-bottom-30 h6">
								<p>신청이 완료되었습니다. 7일 이내 미입금시 자동취소됩니다.</p>

							</div>
						</div>
					</div>
				</div>
				<div class="padding"></div>

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

</script>


</html>