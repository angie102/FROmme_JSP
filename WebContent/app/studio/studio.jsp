<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>FROmme - 내 손으로 만드는 특별한 순간</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/components.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/icons.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/responsee.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/app/owl-carousel/owl.carousel.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/app/owl-carousel/owl.theme.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/template-style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/app/asstes/css/class-form.css">
  <link href='https://fonts.googleapis.com/css?family=Playfair+Display&subset=latin,latin-ext' rel='stylesheet'
    type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800&subset=latin,latin-ext' rel='stylesheet'
    type='text/css'>
  <script type="text/javascript" src="${pageContext.request.contextPath}/app/assets/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/app/assets/js/jquery-ui.min.js"></script>
   <%@ include file="../main/src_forInclude.html" %>
</head>
<%-- jstl --%>
  <c:set var="s_vo" value="${requestScope.s_vo}"/>
  <c:set var="c_list" value="${requestScope.c_list}"/>
  <c:set var="classCnt" value="${requestScope.classCnt}"/>
  <c:set var="genre_name" value="${requestScope.genre_name}"/>
<body class="size-1140">
<!-- HEADER -->
<header role="banner" class="position-absolute">
  <%@ include file="../main/header.jsp" %>
</header>
<!-- MAIN -->
<main role="main">
  <!-- Main Header -->
	<header>
		<%@ include file="../main/banner.jsp" %>
	</header>

    <!-- MAIN -->
    <main role="main">
      <!-- Content -->
  		<article>
            <div class="line">
				<!-- 선택한 공방표시 -->
                <div class="line margin-top-bottom-20">
              		<div class="margin">
              			<div class="s-12 m-6 1-6">
              				<img src="${pageContext.request.contextPath}/app/studio/img/studio/gongbang_logo_01.jpg">
              		</div><br>
              			<div class="margin">
              			<div class="s-12 m-6 1-6">
              				<div class="margin-right-005" style="font-family: GmarketSansMedium;">
                     			<h2>${s_vo.getStudio_name()}<span style="font-size:12px; color:gray;"> &nbsp; ${genre_name}</span></h2>
                     			<p style="font-size:15px">전화번호 : ${s_vo.getStudio_phone()}</p>
                     			<p style="font-size:15px">주소 : ${s_vo.getStudio_address()}&nbsp;${s_vo.getStudio_address2()}</p>
                     			<p style="font-size:15px">대표 : ${s_vo.getStudio_president()}</p>

    							<div id="map" style="width:55%;height:150px;" class="small_map"></div><br>	
                  				<button class="s-12 submit-form button background-primary text-white"style="width:35%" onclick="location.href='http://www.vivastudio.co.kr/'">홈페이지 바로가기</button>
                      	</div>
                   	 </div>
              	 </div>	
              	
                </div>
                <br>

                                  
                <br>
               <h3>${s_vo.getStudio_name()}님의 클래스</h3>
               <%-- 클래스가 없을때 클래스 새로 생성하기 링크나타내기 <c:if 만들어야함--%>
     
               
               <%-- 보유한 클래스가 있을때만 --%>
						<!-- 클래스반복 -->
						<c:forEach var="class_content" items="${c_list}" begin="0" end="${classCnt}" step="1" varStatus="status">
							<div class="s-12 m-6 l-3" style="font-family: GmarketSansMedium">
								<a class="classFragment__HoverStyledLink" href="${pageContext.request.contextPath}/app/studio/StudioEditAction.std?seq=${class_content.classes_no}">
									<div class="image-with-hover-overlay image-hover-zoom margin-bottom">
										<div class="image-hover-overlay background-primary">
											<div class=" image-hover-overlay-content text-center padding-2x">
												<!-- hover action text -->
                       							<p>수정하기</p>
											</div>
										</div>
										<img src="${pageContext.request.contextPath}/app/upload/images/${class_content.image_path}" alt="이미지를 등록해주세요."  title="디폴트 이미지" style="height:280px"/>

									</div>
									<div class="category-tag tag" style="font-size:15px">${class_content.classes_name}
										<span class="category-tag-From-Creator">・</span>
										<c:if test="${class_content.genre_no eq 1}">목공예</c:if>
										<c:if test="${class_content.genre_no eq 41}">공예</c:if>
										<c:if test="${class_content.genre_no eq 42}">사진/영상</c:if>
										<c:if test="${class_content.genre_no eq 43}">요리/음료</c:if>
										<c:if test="${class_content.genre_no eq 44}">디자인</c:if>
										<c:if test="${class_content.genre_no eq 1}">목공예</c:if>
										<c:if test="${class_content.genre_no eq 21}">순수미술</c:if>
									</div>
									<div class="margin-right-005" name="teacher" style="font-size:13px">
										강사 : ${class_content.classes_teacher}
									</div>
									
									<div class="margin-right-005">
										<div class="tag">
                      						<div class="right tag-small"><i class="icon-heart"></i> ${class_content.classes_like}&nbsp;</div>
                    					</div>
									</div>
									<br>
									<div class="left tag-small" style=" line-height: 1.77rem; font-size: 10px;">
                     					 모집시작일 : 
                     					 <fmt:parseDate value="${class_content.classes_start}" var="cs" pattern="yyyy-MM-dd"/>
                     					 <fmt:formatDate value="${cs}" pattern="yyyy년 MM월 dd일 (E)"/>
                    				</div>
                    				<div class="left tag-small" style=" line-height: 1.77rem; font-size: 10px;">
                     					 모집마감일 : 
										 <fmt:parseDate value="${class_content.classes_end}" var="ce" pattern="yyyy-MM-dd"/>
                     					 <fmt:formatDate value="${ce}" pattern="yyyy년 MM월 dd일 (E)"/>
                    				</div>
                    				<div class="left tag-small" style=" line-height: 1.77rem; font-size: 10px;">
                     					 수업일 : 
                     					 <fmt:parseDate value="${class_content.classes_registerdate}" var="cr" pattern="yyyy-MM-dd"/>
                     					 <fmt:formatDate value="${cr}" pattern="yyyy년 MM월 dd일 (E)"/>
                    				</div>
                    				<div class="right tag-small">
										( ${class_content.classes_apply_num}  / ${class_content.classes_limit}  )&nbsp;
									</div>
									<hr class="break margin-top-bottom-10">
                  					<!-- price -->
                   						<div class=" classDetail-text-price">
											<div class="right">
												<p class="" id="classes_price" style="font-size: 1.5rem;">${class_content.classes_price}<span>원</span>
											</p>
										</div>
									</div>
                  					
                    				<div class="line">
										<div class="classDetail-text-available">
											<c:choose>
												<c:when test="${class_content.classes_state eq 1}">
													<p class="text-primary">현재 신청 가능</p>
												</c:when>
												<c:when test="${class_content.classes_state eq 3}">
													<p class="text-gray">정원 모집 마감</p>
												</c:when>
											</c:choose>
										</div>
									</div>
								</a>
							</div>
						</c:forEach>
						<div class="s-12 m-6 l-3">
							<div class="image-with-hover-overlay image-hover-zoom margin-bottom">	
								<a class="classFragment__HoverStyledLink" href="${pageContext.request.contextPath}/app/studio/studioAddAction.std">
									<img src="${pageContext.request.contextPath}/app/studio/img/studio/class_plus_btn_01.jpg">
								</a>
							</div>
						</div>
      </article>
    </main>


    <div class="line"></div>



	<!-- FOOTER -->
	<footer>
		<%@ include file="../main/footer.jsp"%>
	</footer>
	
    <script type="text/javascript" src="js/responsee.js"></script>
    <script type="text/javascript" src="owl-carousel/owl.carousel.js"></script>
    <script type="text/javascript" src="js/template-scripts.js"></script>
    <script>
		$(function(){
    		$('.small_map').on('click',function(){
        		$('.expand_map').show();
  	 		});
		});	
	</script>
	   <script>
		$(function(){
    		$('.expand_map').on('click',function(){
        		$('.expand_map').hide();
  	 		});
		});	
	</script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1a354b9eb9cb42ab99114924570a7b8d&libraries=services"></script>
	<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 8 // 지도의 확대 레벨
    };  

	// 공방 주소와 공방이름 정보
	var addr = '<c:out value="${s_vo.getStudio_address()}"/>';
	var name = '<c:out value="${s_vo.getStudio_name()}"/>';
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(addr, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:100px;text-align:center;font-size:5px;">'+name+'</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});  
	</script>
</body>


</html>