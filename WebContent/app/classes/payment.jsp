<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="ko-KR">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<head>
<title>FROmme - 내 손으로 만드는 특별한 순간</title>
</head>

<%-- templete include --%>
<%@ include file="../main/src_forInclude.html" %>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app/classes/css/class-style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app/classes/css/payment-style.css">


<%-- JSTL --%>
<c:set var="isDirect" value="${requestScope.isDirect}" />
<c:set var="cartList" value="${requestScope.cartList}" />
<c:set var="userBean" value="${requestScope.user_bean}" />
<c:set var="totalPrice" value="${requestScope.total_price}" />


<body class="size-1140">

	<!-- HEADER -->
	<header role="banner" class="position-absolute">
		<%@ include file="../main/header.jsp"%>
	</header>

	<section>
		<div class="margin-bottom-70 "></div>
		<hr>

		<div class="line">
			<div class="margin">
				<div class="page-title">
					<div class="text-center">
						<div style="margin: 140px 70px;">
							<h1>결제 정보</h1>
						</div>
					</div>
				</div>
				<!-- delivery info Form -->
				<form name="orderForm" class="customform" method="post"
					action="${pageContext.request.contextPath }/classes/PaymentOk.cls?isDirect=${isDirect}">
					<input type="hidden" name="total_price" value="${total_price}">
					<input type="hidden" name="cartList" value="${cartList}">
					<div class="s-12 m-12 l-6 padding ">
						<div class="parchase-box">

							<h2 class="margin-bottom-30">주문 정보</h2>
							<div class="line margin-bottom-20">
								<div class="margin">
									<div class="l-12 margin-bottom-10">
										<p class="h6">주문자</p>
									</div>

									<div class="s-12 m-12 l-6">
										<input name="orderer-name" class="required name"
											value="${userBean.getUsers_name() }" placeholder="이름"
											title="이름" type="text">
									</div>
									<div class="s-12 m-12 l-6">
										<input name="orderer-phone" class="required phone"
											placeholder="연락처" value="${userBean.getUsers_phone() }"
											title="연락처" type="text">
									</div>
									<div class="l-12">
										<input name="orderer-email" class="required email"
											placeholder="email" value="${userBean.getUsers_email() }"
											title="이메일" type="email">
									</div>


								</div>
							</div>

						</div>

						<div class="margin-top-30"></div>
						<div class="parchase-box">
							<div class="line margin-bottom-20">
								<h2 class="margin-bottom-20">결제 수단</h2>
								<input type="hidden" value="" id="pg_type" name="pg_type">
								<div class="l-12">
									<input type="radio" name="pay_type" value="cash" onchange="checkPayType($(this).val())" data-name="무통장입금" checked="checked" class="_pay_type"> 
										<span>무통장입금</span>
										<input type="radio" name="pay_type" value="phone" onchange="checkPayType($(this).val())" data-name="핸드폰결제" class="_pay_type">
									<span>핸드폰</span> 
										<input type="radio" name="pay_type" value="toss" onchange="checkPayType($(this).val())" data-name="계좌이체" class="_pay_type">
									<span>토스페이</span> 
								</div>
							</div>
							<div class="line">
								
								<div class="form-select-wrap cash_wrapper on"
									style="display: ''">
									<select class="form-control  text-padding-small "
										style="background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%;; color: #000; z-index: 10"
										name="cash_idx" id="cash_idx" title="입금계좌 안내">
										<option value="계좌선택">계좌 선택</option>
										<option value="신한 110-000-0000 (김세현)">신한 110-000-0000
											(김세현)</option>
										<option value="신한 110-000-0000 (이형준)">신한 110-000-0000
											(이형준)</option>
										<option value="신한 110-000-0000 (윤순호)">신한 110-000-0000
											(윤순호)</option>
										<option value="신한 110-000-0000 (양희재)">신한 110-000-0000
											(양희재)</option>
										<option value="신한 110-000-0000 (양희재)">신한 110-000-0000
											(신운교)</option>
									</select> <input type="text"
										class="form-control text-padding-small margin-bottom-0"
										id="depositor_name" name="depositor_name"
										placeholder="입금자명 (미입력시 주문자명)"
										style="z-index: 10; position: relative;"
										title="입금자명 (미입력시 주문자명)">
								</div>
								
								<div class="form-select-wrap phone_wrapper on" style="display: none;">
									핸드폰 결제
								</div>
								
										<div class="form-select-wrap toss_wrapper on" style="display: none;">
									<a id="tossPayment_btn"> <img src="" alt="토스페이"> <script
											src="https://js.tosspayments.com/v1"></script>
									</a>
								</div>
							</div>

						</div>

					</div>



					<div class="s-12 m-12 l-6 padding">
						<div class="parchase-box">
							<div class="orderList-wrapper">
								<div class="line">
									<h2 class="margin-bottom-30">주문 상품 정보</h2>
									<c:forEach var="cartBean" items="${cartList }"
										varStatus="status">
										<div class="s-12 margin-bottom-10">
											<div class="l-3">
												<img
													src="${pageContext.request.contextPath}/app/assets/img/class_thumbnail/classes_thumbnail_${ cartBean.getClasses_no()}.jpg"
													alt="${pageContext.request.contextPath}/app/assets/img/class_thumbnail/no_image.jpg" style="height: 75px;" />

											</div>
											<div class="l-9">
												<p class="text-strong">${cartBean.getClasses_name()}</p>
												<p>[ 수강일 : ${fn:substring(cartBean.getChoice_date(),0, 11) }]</p>
												<p>
													<span>총 ${cartBean.getCart_quantity()}개 </span>${cartBean.getCart_sum_price()}<span>원</span>
												</p>
											</div>

										</div>
									</c:forEach>


									<hr>
									<div class="s-12">
										<div class="margin-bottom-20">
											<p class="text-size-12 margin-bottom-10">결제예정금액</p>
											<p class="h2">${totalPrice }
												<span>원</span>
											</p>
										</div>

										<input id="purchase_btn" type="button"
											class="s-12 button background-primary text-white text-size-16 margin-bottom-0"
											value="결제하기" style="font-family: GmarketSansMedium;">
									</div>
								</div>


							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>

	<div class="margin-top-70"></div>

	<!-- FOOTER -->
	<footer>
		<%@ include file="../main/footer.jsp"%>
	</footer>

</body>
<script>
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script>
	//주문자 정보와 동일 체크박스 선택시
	$(document).ready(
			function() {
				$("#deliv_info_check").click(
						function() {
							if ($("#deliv_info_check").prop("checked")) {
								$("input[name=deliv-name]").attr("value",
										$("input[name=orderer-name]").val());
								$("input[name=deliv-phone]").attr("value",
										$("input[name=orderer-phone]").val());
							} else {
								//주문자 정보와 동일 체크박스 선택 해제시
								$("input[name=deliv-name]").attr("value", "");
								$("input[name=deliv-phone]").attr("value", "");
							}
						})
			})

	// required 미입력시 처리를 위한 변수
	var required_css_save;
	// 결제 수단 체크를 위한 변수
	var pay_type_checked = "account";//기본값

	$("input[name=orderer-name]").click(
			function(evt) {
				if (required_css_save != null) {
					$("input[name=orderer-name]").css("border-color",
							required_css_save);
				}
			});
	$("input[name=orderer-phone]").click(
			function(evt) {
				if (required_css_save != null) {
					$("input[name=orderer-phone]").css("border-color",
							required_css_save);
				}
			});
	$("input[name=orderer-email]").click(
			function(evt) {
				if (required_css_save != null) {
					$("input[name=orderer-email]").css("border-color",
							required_css_save);
				}
			});

	// submit 하기 전 충족시켜야하는 조건
	$("#purchase_btn").click(
			function(evt) {

				//필수 입력 조건 미 충족시 css 변경을 위해 기존 css 저장
				if (required_css_save == null) {
					required_css_save = $("input[name=orderer-name]").css(
							"border-color");
				}
				if ($("input[name=orderer-name]").val() == "") {
					$("input[name=orderer-name]").attr("placeholder",
							"이름을 입력하세요.");
					$("input[name=orderer-name]").css("border-color", "red");
				}
				if ($("input[name=orderer-phone]").val() == "") {
					$("input[name=orderer-phone]").attr("placeholder",
							"핸드폰 번호를 입력하세요.");
					$("input[name=orderer-phone]").css("border-color", "red");
				}
				if ($("input[name=orderer-email]").val() == "") {
					$("input[name=orderer-email]").attr("placeholder",
							"메일주소를 입력하세요.");
					$("input[name=orderer-email]").css("border-color", "red");
				}
				//필수 입력 조건 체크
				if ($(".required").val() == "") {
					return false;
				}

				//계좌선택 체크
				if (pay_type_checked == "cash") {
					if ($("#cash_idx").val() == "계좌선택") {
						alert("입금할 계좌번호를 선택해주세요.");
						return false;
					}
					//주문자명 비어있을 경우 복사
					if ($("input[name=depositor_name]").val() == "") {
						orderer_name = $("input[name=orderer-name]").val();
						$("input[name=depositor_name]").val(orderer_name);
					}
				}
				orderForm.submit();
			});

	
	//결제 수단 선택에 따라 보여줄 옵션
	var pay_type;
	function checkPayType(pay_type) {
		pay_type_checked = pay_type;
		$(".form-select-wrap").css("display", "none");
		
		if (pay_type == "account")
			$(".account_wrapper").css("display", "");
		else if (pay_type == "phone") 
			$(".phone_wrapper").css("display", "");
		else if (pay_type == "toss") 
			$(".toss_wrapper").css("display", "");
		else
			$(".cash_wrapper").css("display", "");
	
	}

	var clientKey = 'test_ck_qLlDJaYngroAG7ZJwgn3ezGdRpXx';
	var tossPayments = TossPayments(clientKey);
	$("#tossPayment_btn").click(function() {
		tossPayments.requestPayment('휴대폰', {
			amount : ${totalPrice},
			orderId : '7xXA5I8i1IbM-9dghDkyW',
			orderName : '토스 티셔츠 외 2건',
			customerName : '박토스',
			successUrl : window.location.origin + '/success',
			failUrl : window.location.origin + '/fail',
		})
	});
</script>

<script>
	//토스페이
</script>

</html>