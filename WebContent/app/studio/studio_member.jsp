<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	

  
</head>

<%-- jstl --%>
	<c:set var="studioName" value="바바스튜디오"/>
	<c:set var="StudioPresident" value="한동석"/>
	<c:set var="studioPhoneNum" value="01012341234"/>
	<c:set var="studioAddress" value="서울시 강남구"/>
	<c:set var="studioAddress_detail" value="역삼역 3번출구"/>
	<c:set var="studiogenre1" value="장르1"/>
	<c:set var="studiogenre2" value="장르2"/>
	<c:set var="studiogenre3" value="장르3"/>
	
	<c:set var="className" value="유아찰흙놀이"/>
	<c:set var="classType" value="원데이클래스"/>
	<c:set var="classLikeCount" value="123"/>
	<c:set var="classPrice" value="12000"/>
	<c:set var="classDate" value="11.16"/>
	<c:set var="classDay" value="토"/>


<body class="size-1140">
  <!-- HEADER -->
  <header role="banner" class="position-absolute">
    <!-- Top Navigation -->
    <nav class="background-transparent background-transparent-hightlight full-width sticky">
      <div class="s-12 l-2">
        <a href="index.html" class="logo">
          <!-- Logo White Version -->
          <img class="logo-white" src="${pageContext.request.contextPath}/app/assets/img/logo-dark.png" alt="">
          <!-- Logo Dark Version -->
          <img class="logo-dark" src="${pageContext.request.contextPath}/app/assets/img/logo-dark.png" alt="">
        </a>
      </div>
      <div class="top-nav s-12 l-10">

        <ul class="right chevron text-dark">
          <li><a href="${pageContext.request.contextPath}/app/main/index.jsp">Home</a></li>
          <li><a href="${pageContext.request.contextPath}/app/classes/classesList.jsp">Class</a></li>
          <li><a href="${pageContext.request.contextPath}/app/board/boardList.jsp">Board</a></li>
          <li><a href="${pageContext.request.contextPath}/app/map/map.jsp">Map</a></li>
          <li><a href="${pageContext.request.contextPath}/app/studio/studio.jsp"><b>Studio</b></a></li>
        </ul>
      </div>
    </nav>
  </header>



  <!-- slide banner -->
  
  <!-- MAIN -->
  <main role="main">
    <!-- Main Header -->
    <header>
      <div class="margin-top-70">
        <div class="carousel-default owl-carousel carousel-main carousel-nav-white background-dark text-center">

          <div class="item">
            <div class="s-12">
              <img src="${pageContext.request.contextPath}/app/assets/img/banner/event_mini_banner1.png" alt="background-dark">
            </div>
          </div>

        </div>
      </div>
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
              		</div>
              			<div class="margin">
              			<div class="s-12 m-6 1-6">
              				<div class="margin-right-005">
              					<br>
                     			<h2>${studioName}<span><p>${studiogenre1} · ${studiogenre2} · ${studiogenre3} </p></span></h2>
                     			
                     			<p>전화번호 : ${studioPhoneNum }</p>
                     			<p>주소 : ${studioAddress }</p>
                     			<p>대표 : ${StudioPresident }</p>

                     			<!-- center=다음에 place주소 넣으면 위치보여짐 -->
    							<div style="width:55%">
                     			 	<img src="https://maps.googleapis.com/maps/api/staticmap?center=페이브먼트스토어+_+Pavement+Store+-+vivastudio&zoom=13&size=600x300&maptype=roadmap
										&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318
										&markers=color:red%7Clabel:C%7C40.718217,-73.998284
										&key=AIzaSyCFMfyw8OFY3So4WuE-jpm7tX0mdSmPu1I" alt="" class="small_map">
								</div>
								<br>		
                      				<div>
                      					<div class="s-12">
                  						<button class="s-12 submit-form button background-primary text-white"style="width:35%" onclick="location.href='http://www.vivastudio.co.kr/'">
                    						홈페이지 바로가기
                  						</button>

               						 </div>
                        	</div>
                      	</div>
                   	 </div>
              	 </div>	
              	
                </div>
                <br>
                <div class="l-12 m-6 l-3" >
                <!-- 상단 지도 클릭시 확대되서 나타남-->
                 <img src="https://maps.googleapis.com/maps/api/staticmap?center=페이브먼트스토어+_+Pavement+Store+-+vivastudio&zoom=13&size=600x300&maptype=roadmap
					&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318
					&markers=color:red%7Clabel:C%7C40.718217,-73.998284
					&key=AIzaSyCFMfyw8OFY3So4WuE-jpm7tX0mdSmPu1I" alt="" class="expand_map" style="margin:auto; width:100%; display:none">
				</div>

                
                
                
                <br>
               <h3>${studioName}의 클래스</h3>
               <%-- 클래스가 없을때 클래스 새로 생성하기 링크나타내기 <c:if 만들어야함--%>
     
               
               <%-- 보유한 클래스가 있을때만 --%>
						<!-- 클래스반복 -->
						<c:forEach var="studioClassList" begin="1" end="5" step="1" varStatus="status">
							<div class="s-12 m-6 l-3">
								<a class="classFragment__HoverStyledLink" href="${pageContext.request.contextPath}/classes/ClassesView.cls">
									<div class="image-with-hover-overlay image-hover-zoom margin-bottom">
										<div class="image-hover-overlay background-primary">
											<div class=" image-hover-overlay-content text-center padding-2x">
												<!-- hover action text -->
                       							<p>신청하기</p>
											</div>
										</div>
										<img src="${pageContext.request.contextPath}/app/studio/img/studio/vava_class_01.jpg" alt="" title="Portfolio Image 2" />
									</div>
									<div class="category-tag tag"> ${className}
										<span class="category-tag-From-Creator">・</span>${classType}
									</div>
									<div class="margin-right-005">
										<div class="tag">
                      						<div class="margin-right-01"><i class="icon-heart"></i> ${classLikeCount}</div>
                    					</div>
									</div>
									<hr class="break margin-top-bottom-10">
                  					<!-- price -->
                   					<div class="s-6 tag margin-right-02 text-dark">
                    					<p>${classPrice}원</p>
                  					</div>
                    				<div class="right tag-small" style=" line-height: 1.77rem;">
                     					 ${classDate} (${classDay})
                    				</div>
								</a>
							</div>
						</c:forEach>
      </article>
    </main>


    <div class="line"></div>




    <!-- FOOTER -->
    <footer>
      <!-- Main Footer -->
      <section class="background-white full-width">
        <hr class="break margin-top-bottom-0" style="border-color: rgba(199, 199, 199, 0.8);">
        <!-- Logo img -->
        <div class="margin-top-20 margin-left-right-20" style="width: 20%;"><img src="img/logo-dark.png" alt=""></div>

        <div class="margin-top-bottom-0">
          <div class="s-6 l-6">
            <div class="margin-top-10 text-size-16 padding">
              <div class="" style="font-family: GmarketSansMedium;">크리에이터</div>
              <a href="#" style="font-family: GmarketSansMedium;" class="text-size-12">공방 등록 문의</a>
            </div>
          </div>

          <div class="s-12 l-6">
            <div class="margin-top-10 text-size-16 padding">
              <div class="" style="font-family: GmarketSansMedium;">고객센터 010-000-0000</div>
              <a href="#" style="font-family: GmarketSansMedium;" class="text-size-12">오전 10시 ~ 오후 6시 (주말, 공휴일 제외)</a>
            </div>
          </div>
        </div>
        </div>

      </section>
      <hr class="break margin-top-bottom-0" style="border-color: rgba(199, 199, 199, 0.8);">

      <!-- Bottom Footer -->
      <section class="padding background-white full-width ">
        <div class="Footer__AgreementInner text-size-12" style="font-family: GmarketSansMedium;">
          <div class="center margin-bottom-10">

            <div class="s-12 l-12">
              <a href="#" target="_blank" rel="noreferrer" class="Footer__AgreementLink-1">이용약관 |</a>
              <a href="#" target="_blank" rel="noreferrer" class="Footer__AgreementLink-2"><b>개인정보 처리방침 |</b></a>
              <a href="#" target="_blank" rel="noreferrer" class="Footer__AgreementLink-3">사업자 정보 확인 |</a>
              <a href="#" target="_blank" rel="noreferrer" class="Footer__AgreementLink-4">제휴/협력 문의</a>
            </div>

          </div>

          <div>
            <p class="text-size-12">
              (주) FROmme | 대표 홍길동 | 서울특별시 역삼동 통일로 10 세브란스빌딩 18층
              | 사업자등록번호 : 000-00-00000 |
              통신판매업신고 : 2020-서울강남-0000 |
              주식회사 FROmme는 전자상거래 등에서의 소비자보호에 관한 법률에 따른 통신판매업과 통신판매중개업을 영위하고 있습니다.
              주식회사 FROmme는 통신판매중개자로서 중개하는 통신판매에 관하여서는 통신판매의 당사자가 아니므로 어떠한 책임도 부담하지 아니합니다.
              created by Team Method
            </p>
          </div>
          <div class="s-12 l-12">

          </div>
      </section>
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
</body>


</html>