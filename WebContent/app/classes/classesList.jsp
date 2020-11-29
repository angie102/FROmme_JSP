<%@  page language="java" contentType="text/html; charset=UTF-8"
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
	href="${pageContext.request.contextPath}/app/classes/css/jquery.bxslider.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app/classes/css/class-style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/app/assets/css/board-style.css">


<%-- JSTL --%>
<c:set var="genreNameList" value="${sessionScope.genreNameList}" />
<c:set var="classesList" value="${requestScope.classesList}" />
<c:set var="dateList" value="${requestScope.dateList}" />
<c:set var="LikedClassesNoList" value="${requestScope.LikedClassesNoList}" />

<c:set var="nowPage" value="${requestScope.nowPage}" />
<c:set var="startPage" value="${requestScope.startPage}" />
<c:set var="endPage" value="${requestScope.endPage}" />
<c:set var="keyword" value="${requestScope.keyword}" />

<c:set var="totalCount" value="${requestScope.totalCount}" />
<c:set var="totalPage" value="${requestScope.totalPage}" />
<c:set var="sort_type" value="${requestScope.sort_type}" />

<c:set var="url"
	value="${pageContext.request.contextPath}/classes/ClassesSortList.cls?" />

<c:if test="${keyword != null }">
	<c:set var="url"
		value="${pageContext.request.contextPath}/classes/ClassesSortList.cls?keyword=${keyword }" />
</c:if>

<body class="size-1140">
	<!-- HEADER -->
	<header role="banner" class="position-absolute">
		<%@ include file="../main/header.jsp"%>
	</header>

	<div class="margin-bottom-70 "></div>

	<!-- slide banner -->
	<div style="padding-top: 0;">
		<img
			src="${pageContext.request.contextPath}/app/assets/img/banner/event_mini_banner1.png"
			alt="이벤트 배너 이미지">
	</div>


	<section class="section-small-padding section-top-small-padding">
		<div class="classList-wrapper">

			<div class="line margin-bottom-30">
				<div class="classList-search-wrapper">
					<div class="line">
						<div class="s-12 m-12 l-6">
							<div class="h2 margin-left-10 text-dark">
								<a
									href="${url}sort_type=all&sort_by=new&classes_sortName=${classes_sortName }">ALL</a>
								&nbsp; <a
									href="${url}sort_type=all&sort_by=best&classes_sortName=${classes_sortName }">BEST</a>&nbsp;
								<a
									href="${url}sort_type=all&sort_by=recommend&classes_sortName=${classes_sortName }">RECOMMEND</a>&nbsp;
							</div>
						</div>
						<div class="s-12 m-12 l-6">
							<div class="margin-m-left-right-0 margin-l-left-right-20 "
								style="padding: 0 1%;">
								<div class="l-12 full-width">
									<fieldset class="field-container">
										<form name="searchForm" class="searchform" method="get"
											action="${url}&sort_type=all&sort_by=search">
											<input name="keyword" type="text" placeholder="원하는 클래스가 있나요?"
												class="field">
											<div class="icons-container">
												<div class="icon-search"></div>
												<div class="icon-close">
													<div class="x-up"></div>
													<div class="x-down"></div>
												</div>
											</div>
										</form>
									</fieldset>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="line margin-bottom-0">
				<div class="margin-l-left-right-20 margin-m-left-right-0"
					style="padding: 0 1%;">
					<div id="bannerWrap">

						<a id="prevBtn"><img alt="이전"
							src="${pageContext.request.contextPath}/app/assets/img/board_images/btn_prevpage.png"></a>
						<ul id="slideBanner" class="margin-left-right-20">
							<%-- 장르 DB에서 받아온 뒤 for문 돌리기 --%>
							<c:forEach var="genre" items="${genreNameList }">
								<li class=""><a
									class="text-white-hover s-12 button button-primary-stroke text-primary"
									href="${url}sort_type=genre&sort_by=${genre }&classes_sortName=${classes_sortName }">${genre }</a>
								</li>
							</c:forEach>
						</ul>
						<a id="nextBtn"><img alt="다음"
							src="${pageContext.request.contextPath}/app/assets/img/board_images/btn_nextpage.png"></a>
					</div>
				</div>
			</div>
		</div>
	</section>


	<%-- 전체 클래스 --%>
	<section class="section-small-padding section-top-small-padding">
		<div class="classList-wrapper">
			<div class="line">
				<div class="margin-l-left-right-20 margin-m-left-right-0"
					style="padding: 0 1%;">

					<div class="function-sort-wrapper">
						<ul id="type" class="right xans-product classes-orderby">
							<li class="sort "><a
								href="${url}sort_type=subSort&sort_by=new&subSort=new&classes_sortName=${classes_sortName }">신상품</a></li>
							<li class="sort"><a
								href="${url}sort_type=subSort&sort_by=name&classes_sortName=${classes_sortName }">상품명</a></li>
							<li class="sort"><a
								href="${url}sort_type=subSort&sort_by=lowPrice&classes_sortName=${classes_sortName }">낮은가격</a></li>
							<li class="sort"><a
								href="${url}sort_type=subSort&sort_by=highPrice&classes_sortName=${classes_sortName }">높은가격</a></li>
							<li class="sort"><a
								href="${url}sort_type=subSort&sort_by=like&classes_sortName=${classes_sortName }">인기순</a></li>
						</ul>
					</div>

					<%-- 전체 클래스 --%>
					<div class="classList-title-wrapper">

						<!-- 클래스 출력 -->
						<div class="classList-title">

							<h2 class="margin-bottom-0">${classes_sortName }</h2>
						</div>
					</div>
				</div>
			</div>

			<%-- 클래스 리스트 --%>
			<ul class="classList-detail-wrapper line">
				<c:choose>

					<c:when
						test="${classesList != null and fn:length(classesList) > 0}">
						<!-- 반복문 -->
						<c:forEach var="cl_bean" items="${classesList}" varStatus="status">
							<li class="classesRow"><span class="classes-no"
								hidden="hidden">${ cl_bean.getClasses_no()}</span>

								<div class="s-12 m-12 l-6 xl-3" style="padding: 0 1%;">
									<div class="margin-m-left-right-0  margin-top-10">
										<%-- 클래스 상세정보 링크 --%>
										
										<a class="classDetail_link"
											href="${pageContext.request.contextPath }/classes/ClassesView.cls?seq=${cl_bean.getClasses_no()}&gen=${cl_bean.getGenre_name()}&stu=${cl_bean.getStudio_name()}">
											<div
												class="image-with-hover-overlay image-hover-zoom margin-bottom ">
												
												<%-- 
												<div class=" image-hover-overlay-content text-center padding-2x">
														<i class="icon-sli-shopping"></i>
												</div>  
												--%>
												<img
													src="${pageContext.request.contextPath}/app/assets/img/class_thumbnail/classes_thumbnail_${ cl_bean.getClasses_no()}.jpg"
													/>
											</div>
										</a>

									</div>
									<div class="margin-bottom-30">
										<div class="line margin-top-10">
											<!-- category / 공방 이름-->
											<div class="category-tag tag ">
												${cl_bean.getGenre_name()} <span
													class="category-tag-From-Creator">・</span>
												${cl_bean.getStudio_name()}
											</div>
										</div>
										<!-- simple description -->
										<div class="line">
											<p id="classes-description">${cl_bean.getClasses_name() }</p>
										</div>
										<div class="line">
											<div class="tag">
												<c:if test="${cl_bean.getClasses_state().trim() eq 1}">
													<p class="classes_state text-primary">현재 신청 가능</p>
												</c:if>
												<c:if test="${cl_bean.getClasses_state().trim() eq 3}">
													<p class="classes_state text-gray">모집 완료(예약가능)</p>
												</c:if>
											</div>
											<div class="text-dark-hover">
												<p class="classes-like-wrapper float-left tag text-size-16">
													<a name="heart"
														class="icon-heart 
														
														<c:forEach var="liked" items="${LikedClassesNoList }" varStatus="stat">
														<c:set var="liked_color" value="${liked == cl_bean.getClasses_no() }"/>
															<c:if test="${liked == cl_bean.getClasses_no() }">
																text-dark
															</c:if>		
														</c:forEach>
															"
														onclick="increaseLike(this);"> <span
														class="classes-like">${cl_bean.getClasses_like() }</span>
													</a>
												</p>
											</div>

										</div>


										<div class="line">
											<hr class="break margin-top-bottom-10">
										</div>
										<!-- price -->
										<div class="line">
											<div class="s-12 m-6">
												<div class=" text-dark">
													<p class="tag">${cl_bean.getClasses_price() }<span>원</span>
													</p>
												</div>
											</div>
											<div class="s-12 m-6 ">
												<p class="tag-small right">${dateList[status.index]}시작</p>
											</div>
										</div>
									</div>
								</div></li>
						</c:forEach>

					</c:when>
				</c:choose>
			</ul>
		</div>

		<div class="pagination">
			<c:choose>
				<c:when test="${nowPage > 1}">
					<a
						href="${url}sort_type=${sort_type }&sort_by=${sort_by }&classes_sortName=${classes_sortName }&page=${startPage}"
						class="firstpage pbtn"><img
						src="${pageContext.request.contextPath}/app/assets/img/board_images/btn_firstpage.png"
						alt="첫 페이지로 이동"></a>
					<a
						href="${url}sort_type=${sort_type }&sort_by=${sort_by }&classes_sortName=${classes_sortName }&page=${nowPage - 1}"
						class="prevpage pbtn"><img
						src="${pageContext.request.contextPath}/app/assets/img/board_images/btn_prevpage.png"
						alt="이전 페이지로 이동"></a>
				</c:when>
			</c:choose>
			<c:if test="${nowPage >= 1}">
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<c:choose>
						<c:when test="${i eq nowPage}">
							<a href="#"><span class="pagenum currentpage">${i}</span></a>
						</c:when>
						<c:otherwise>
							<a
								href="${url}sort_type=${sort_type }&sort_by=${sort_by }&classes_sortName=${classes_sortName }&page=${i}"><span
								class="pagenum">${i}</span></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
			<c:choose>
				<c:when test="${nowPage != totalPage}">
					<a
						href="${url}sort_type=${sort_type }&sort_by=${sort_by }&classes_sortName=${classes_sortName }&page=${nowPage + 1}"
						class="nextpage pbtn"><img
						src="${pageContext.request.contextPath}/app/assets/img/board_images/btn_nextpage.png"
						alt="다음 페이지로 이동"></a>
					<a
						href="${url}sort_type=${sort_type }&sort_by=${sort_by }&classes_sortName=${classes_sortName }&page=${endPage}"
						class="lastpage pbtn"><img
						src="${pageContext.request.contextPath}/app/assets/img/board_images/btn_lastpage.png"
						alt="마지막 페이지로 이동"></a>
				</c:when>
			</c:choose>
		</div>
	</section>

	<!-- FOOTER -->
	<footer>
		<%@ include file="../main/footer.jsp"%>
	</footer>
		<%@ include file="../board/board_template/loginModal.jsp" %>
</body>

<script>
	$(function() {
		var mySlider = $("#slideBanner").bxSlider({
			mode : "horizontal", // 가로 수평으로 슬라이드된다.
			speed : 500, // 이동 속도를 설정한다.
			pager : false, // 현재 위치 페이지 표시 여부를 설정한다.
			moveSlides : 1, // 슬라이드 이동 시 개수를 설정한다.
			slideWidth : 140, // 슬라이드 너비를 설정한다.
			minSlides : 1, // 최소 노출 개수를 설정한다.
			maxSlides : 6, // 최대 노출 개수를 설정한다.
			slideMargin : 60, // 슬라이드 간의 간격을 설정한다.
			auto : true, // 자동으로 흐를지 여부를 설정한다.
			autoHover : true, // 마우스오버 시 정지할지를 설정한다.
			controls : false, // 이전 버튼, 다음 버튼 노출 여부를 설정한다.
			touchEnabled : (navigator.maxTouchPoints > 0)
		// a링크 오류 해결

		});

		// 이전 버튼을 클릭하면 이전 슬라이드로 전환된다.
		$("#prevBtn").on("click", function() {
			// 이전 슬라이드 배너로 이동된다.
			mySlider.goToPrevSlide();
			// <a>의 링크를 차단한다.
			return false;
		});

		// 다음 버튼을 클릭하면 다음 슬라이드로 전환한다.
		$("#nextBtn").on("click", function() {
			// 다음 슬라이드 배너로 이동된다.
			mySlider.goToNextSlide();
			// <a>의 링크를 차단한다.
			return false;
		});
	});
</script>

<script>
	function increaseLike(likeNum) {
		var classesRow = $(likeNum).parent().parent().parent().parent().parent().parent();
		var seq = classesRow.find('.classes-no').text();
		var like = parseInt($(likeNum).text());

		$.ajax({
					type : "GET",
					url : "${pageContext.request.contextPath}/classes/ClassesLikeIncreaseOk.cls?seq="
							+ seq + "&like=" + like,
					dataType : "text",
					error : function() {
						console.log("ajax 통신 오류");
					},
					success : function(data) {
						console.log(data);
						if (data.trim() == "increase") {
							like = like + 1;
							$(likeNum).addClass("text-dark");
						} else if (data.trim() == "decrease") {
							like = like - 1;
							$(likeNum).removeClass("text-dark");
						} else if (data.trim() == "access denied") {
							//비회원
							alert("해당 기능은 로그인 후 이용해 주세요.");
						}

						$(likeNum).text(" " + like);

					}
				});
	}
</script>



</html>