<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/class-form.css">
  <link href='https://fonts.googleapis.com/css?family=Playfair+Display&subset=latin,latin-ext' rel='stylesheet'
    type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700,800&subset=latin,latin-ext' rel='stylesheet'
    type='text/css'>
  <script type="text/javascript" src="${pageContext.request.contextPath}/app/assets/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/app/js/jquery-ui.min.js"></script>
  <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
  
  <!-- summer note -->
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> 
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>  
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>  
  
  <script src="${pageContext.request.contextPath}/app/assets/js/summernote/lang/summernote-ko-KR.js"></script>
  <link href="${pageContext.request.contextPath}/app/assets/js/summernote/summernote.css" rel="stylesheet"> 
  <script src="${pageContext.request.contextPath}/app/assets/js/summernote/summernote.js"></script> 
<%@ include file="../main/src_forInclude.html" %>

<!-- jstl -->
<c:set var="s_vo" value="${requestScope.s_vo}"/>
<c:set var="genre_name" value="${requestScope.genre_name}"/>

<c:set var="class_data" value="${sessionScope.c_list }"/>
<c:set var="c_vo" value="${requestScope.c_vo}"/>
<c:set var="class_limit" value="${requestScope.class_limit}"/>

</head>


<body class="size-1140">
	<c:set var="s_vo" value="${requestScope.s_vo}"/>
	<c:set var="studioList" value="${requestScope.studioList}"/>
	<c:set var="classesList" value="${requestScope.classesList}"/>
  <!-- HEADER -->
<header role="banner" class="position-absolute">
	<%@ include file="../main/header.jsp" %>
</header>



  <!-- slide banner -->
  <!-- MAIN -->
  <main role="main">
  	<!-- Main Header -->
	<header>
		<%@ include file="../main/banner.jsp" %>
	</header>






    <div class="line"></div>
    <!-- MAIN -->
    <main role="main"  style="font-family: GmarketSansMedium; margin:auto;" >
      <!-- Content -->
  		<article>
  			<div class="line margin-top-bottom-20">
  				<div class="s-12 m-6 1-6">
              		<img src="${pageContext.request.contextPath }/app/studio/img/studio/gongbang_logo_01.jpg">
              	</div>
              	<div class="margin">
              			<div class="s-12 m-6 1-6">
              				<div class="margin-right-005">
                     			<h2>${s_vo.getStudio_name()}<span style="font-size:12px; color:gray;"> &nbsp; ${genre_name} · ${genre_name}</span></h2>
                     			<p style="font-size:15px">전화번호 : ${s_vo.getStudio_phone()}</p>
                     			<p style="font-size:15px">주소 : ${s_vo.getStudio_address()}&nbsp;${s_vo.getStudio_address2()}</p>
                     			<p style="font-size:15px">대표 : ${s_vo.getStudio_president()}</p>
            		</div>
            	</div>
            </div>
  		<br>
  		<div class="margin-left-right-80">
        	<div class="text-l-size-30 line"> ${c_vo.getClasses_name()} 수정하기<a href="${pageContext.request.contextPath}/studio/StudioDelete.std?class_no=${c_vo.getClasses_no()}" style="font-size: 15px;">(삭제하기)</a></div><br>
              <form name="editForm" method="post" style="font-family: GmarketSansMedium" action="${pageContext.request.contextPath}/studio/StudioEditOkAction.std?class_no=${c_vo.getClasses_no()}" enctype="multipart/form-data">
                     	<div class="line">장르
                      		<select name="genre" style="width:20%; height:20%; ">
                      			<option value="0">장르선택</option>
                      			<option value="1">목공예</option>
                      			<option value="41">공예</option>
                      			<option value="42">사진/영상</option>
                      			<option value="43">요리/음료</option>
                      			<option value="44">디자인</option>
                      			<option value="21">순수미술</option>
                      		</select>
                    </div> 
              	<div class="line">
					<div class="s-12 m-12 l-6">클래스이름
                      	<input name="class_name" placeholder="클래스이름" title="class_name" type="text" value="${c_vo.getClasses_name()}"/>
                    </div>
                <div>
                <div class="s-12 m-12 l-6">강사명
                	<input name="class_teacher" class="edit class_teacher" placeholder="강사명" title="class_teacher" type="text" value="${c_vo.getClasses_teacher()}"/>
                </div>
                <div class="line">
                 	<div class="margin">
                 		<div class="s-12 m-12 l-6">클래스정원
                 			<input name="class_limit" class="edit class_limit" placeholder="기존데이터"style="width:30%" value="${class_limit}"> 명
                 		</div>
                	 </div>
               	 </div>
                  <div class="line">
                 	 <div class="margin">
                 		<div class="s-12 m-12 l-6">클래스가격
                 			<input name="class_price" class="edit class_price" placeholder="기존데이터"style="width:30%" value="${c_vo.getClasses_price() }"> 원
                 		</div>
                	 </div>
               	 </div>
               	 <div class="line">
                 	 <div class="margin">
                 		<div class="s-12 m-12 l-6">기존모집시작일 : 
                 			<fmt:parseDate value="${c_vo.getClasses_start() }" var="cs" pattern="yyyy-MM-dd"/>
                 			<fmt:formatDate value="${cs}" pattern="yyyy년MM월dd일(E)"/>
                 		<br>
                 			수정할 날짜 : <input name="class_start_date" class="edit class_start_date" placeholder="모집시작일" type="date">
                 		</div>
                	 </div>
                </div>
                <div class="margin">
                 	<div class="s-12 m-12 l-6">기존모집마감일 : 
						<fmt:parseDate value="${c_vo.getClasses_end() }" var="ce" pattern="yyyy-MM-dd"/>
                 		<fmt:formatDate value="${ce}" pattern="yyyy년MM월dd일(E)"/>
					<br>
                 		수정할 날짜 : <input name="class_finish_date" class="edit class_finish_date" placeholder="모집마감일" type="date">
                 	</div>
                </div>
                <div class="margin">
                 	<div class="s-12 m-12 l-6">기존수업날짜 : 
						<fmt:parseDate value="${c_vo.getClasses_registerdate() }" var="cd" pattern="yyyy-MM-dd"/>
                 		<fmt:formatDate value="${cd}" pattern="yyyy년MM월dd일(E)"/>
					<br>
                 		수정할 날짜 : <input name="class_date" class="edit class_date" placeholder="수업날짜" type="date">
                 	</div>
              	</div><br>

               	 <div>클래스내용</div>
               	 
				<textarea name="class_content" id="summernote" style="width:100%; height:300px; resize:none;" placeholder="상세내용입력">${c_vo.getClasses_content()}</textarea>
              	<div>대표 이미지 설정(준비중)-이미지 파일만 업로드 됩니다.</div>
               	<input type="file" name="represent_image" id="represent_image" accept=".jpg,.jpeg,.png,.git"/>
              	</div>
               	<br>
				<div class="line">
					<input type="button" class="s-12 submit-form button background-primary text-white" style="width:50%" onclick="checkNull()" value="수정"></input>
					<button class="s-12 submit-form button background-primary text-white" style="width:50%" name="cancle" type="reset">취소</button>
				</div>
			</div>
		</form>
	    </article>
    </main>
    

	</main>
    <div class="line"></div>

	<!-- FOOTER -->
	<footer>
		<%@ include file="../main/footer.jsp"%>
	</footer>
	
    <script type="text/javascript" src="js/responsee.js"></script>
    <script type="text/javascript" src="owl-carousel/owl.carousel.js"></script>
    <script type="text/javascript" src="js/template-scripts.js"></script>
    <script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
    <script>
			$(document).ready(function() {
				$('#summernote').summernote({
					toolbar: [
						// [groupName, [list of button]]
						['fontname', ['fontname']],
						['fontsize', ['fontsize']],
						['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
						['color', ['forecolor','color']],
						['table', ['table']],
						['para', ['ul', 'ol', 'paragraph']],
						['height', ['height']],
						['insert',['picture','link','video']],
						['view', ['fullscreen', 'help']]
					],
					fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
					fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
					height: 300,                 // 에디터 높이
					minHeight: null,             // 최소 높이
					maxHeight: null,             // 최대 높이
					focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
					lang: "ko-KR",					// 한글 설정
					placeholder: '내용을 입력해 주세요.',
					callback : {
						onImageUpload: function(files, editor, welEditable) {
							for (var i = files.length - 1; i >= 0; i--) {
								sendFile(files[i], this);
							}
						},
						onPaste: function (e) {
							var clipboardData = e.originalEvent.clipboardData;
							if (clipboardData && clipboardData.items && clipboardData.items.length) {
								var item = clipboardData.items[0];
								if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
									e.preventDefault();
								}
							}
						}
					}
				});
			});
	</script>
	<script type="text/javascript">
		function checkNull(){
			if(document.editForm.class_name.value.trim() == "" || document.editForm.class_teacher.value.trim() == "" || document.editForm.class_price.value.trim() =="" || document.editForm.class_start_date.value.trim() =="" || document.editForm.class_finish_date.value.trim() =="" || document.editForm.class_date.value.trim() =="" || document.editForm.class_content.value.trim() =="" || document.editForm.class_limit.value.trim() =="" || document.editForm.represent_image.value ==""){
				alert("빈칸없이입력해주세요.");
				return;
			}else{
				alert("수정완료.");
				editForm.submit();
			}
		}
	</script>
</body>


</html>