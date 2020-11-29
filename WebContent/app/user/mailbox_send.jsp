<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko-KR">
  <head>
  	<style type="text/css">
  		#box{border:1px solid black;}
  		#postcodify_search_button{border:1px solid black;}
  		#body_back{background-image:url("${pageContext.request.contextPath}/app/assets/img/banner/background_body.png");}
  	</style>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>회원정보 / FROmme - 내 손으로 만드는 특별한 순간</title>
	<%@ include file="../main/src_forInclude.html" %>
  </head>

  <body class="size-1140">
  <c:set var="reply_id" value="${param.reply_id}"/>
    <!-- HEADER -->
    <%@ include file="../main/session_check.jsp" %>
    <header role="banner" class="position-absolute">
		<%@ include file="../main/header.jsp" %>
	</header>
	<!-- MAIN -->
	  <main role="main">
	    <!-- Main Header -->
	    <header>
	      <%@ include file="../main/banner.jsp" %>
	    </header>
	    <div class="line"> 	
	    </div>
    <!-- MAIN -->
    <main role="main">
      <!-- Content -->
      <article>
		<div class="section background-white" id="body_back">
		<div style="width:70%; margin:auto; font-family: GmarketSansMedium;">
			<%@ include file="banner_myPage.jsp" %>
		<div style="width:72%; float:left; height:1000px;">
			<div style="background-color:white; height:1000px; margin:0 auto; margin-bottom:20px;">
				<br><h4><label style="margin-left: 5%;">쪽지함</label></h4>
				<form name="messageForm" class="customform" method="post" action="${pageContext.request.contextPath}/message/MessageWriteOk.msg">
				  	<table style="border-top:2px solid black; width:90%; margin:auto;">
				  		<colgroup>
				  			<col width="30%;">
				  			<col width="">
				  			<col width="15%;">
				  		</colgroup>
				  		<tbody>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  					<label for="writer">받는 이</label>
				  				</th>
				  				<td>
				  					<label>제목</label>
				  				</td>
				  				<td></td>
				  			</tr>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  				<c:choose>
				  				<c:when test="${reply_id != null}">
				  					<input for="writer" name="receive_id" type="text" value="${reply_id}" style="background-color:transparent;">
				  				</c:when>
				  				<c:otherwise>
				  					<input for="writer" name="receive_id" type="text" value="">
				  				</c:otherwise>
				  				</c:choose>
					  				<input name="send_id" type="text" value="${session_id}" style="display:none;">
				  				</th>
				  				<td>
				  					<input type="text" name="message_title" value="">
				  				</td>
				  				<td></td>
				  			</tr>
				  		</tbody>
				  	</table>
				  	<table style="width:90%; margin:auto;">
				  		<tbody>
				  			<tr style="border-bottom:1px solid gray;">
				  				<td>
				  					<textarea name="message_content" style="width:100%; height:185px; resize:none;"></textarea>
				  				</td>
				  			</tr>
				  		</tbody>
				  	</table>
					<br>
		 		  </form>	
				<div class="s-12 m-12 l-6 center" style="text-align:center;">
					<a type="button button-black-stroke s-12" href="javascript:sendMessage();" style="text-align:center;">보내기</a>
				</div>
			</div>
		</div>
		</div>
          <div class="line">
			</div>
		</div>
      </article>
    </main>

  <!-- FOOTER -->
  <footer>
    <%@ include file="../main/footer.jsp" %>
  </footer>
  <script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
  <script>
  	autosize($("textarea.replys"));
  
  	function sendMessage() {
		messageForm.submit();
	}
  </script>
</body>
</html>