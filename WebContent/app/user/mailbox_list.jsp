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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/app/assets/css/board-style.css">
  </head>
  <body class="size-1140">
  	<c:set var="list" value="${requestScope.messageList}"/>
  	<c:set var="nowPage" value="${requestScope.nowPage}"/>
  	<c:set var="startPage" value="${requestScope.startPage}"/>
  	<c:set var="endPage" value="${requestScope.endPage}"/>
  	<c:set var="totalPage" value="${requestScope.totalPage}"/>
  	<c:set var="totalCnt" value="${requestScope.totalCnt}"/>
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
				<br><h4 style="margin-left: 5%;">쪽지함</h4>
				<form name="messageForm" class="customform" method="#">
				  	<table style="border-top:2px solid black; width:90%; margin:auto;">
				  		<colgroup>
				  			<col width="10%;">
				  			<col width="20%;">
				  			<col width="">
				  			<col width="15%;">
				  		</colgroup>
				  			<tr style="border-bottom:1px solid gray;">
				  				<th scope="row">
				  				</th>
				  				<td><label for="writer">보낸 이</label></td>
				  				<td><label>제목</label></td>
				  				<td><label>날짜</label></td>
				  			</tr>
				  			<c:choose>
				  			<c:when test="${list != null and fn:length(list) > 0}">
				  				<c:forEach var="m_bean" items="${list}">
				  				<tr>
				  					<th class="message">
				  						<input type="checkbox" name="check_num" value="${m_bean.getMessage_no()}">
				  					</th>
				  					<td>
				  						${m_bean.getSend_id()}
				  					</td>
				  					<td>
				  						<a href="${pageContext.request.contextPath}/message/MessageView.msg?seq=${m_bean.getMessage_no()}">${m_bean.getMessage_title()}</a>
				  					</td>
				  					<td>
				  						${m_bean.getReceive_date()}
				  					</td>
				  				</tr>
				  				</c:forEach>
				  			</c:when>
				  			<c:otherwise>
							<tr height="50px">
								<td colspan="5" align="center">쪽지함이 비어있습니다.</td>
							</tr>
							</c:otherwise>
				  		</c:choose>
				  	</table>
				  	<br>
				  	<table border="0" cellpadding="0" cellspacing="0" width="900px">
						<tr align="center" valign="middle">
							<td>
							<c:choose>
								<c:when test="${nowPage > 1}">
									<a href="${pageContext.request.contextPath}/message/MessageList.msg?page=${nowPage - 1}">[이전]</a>&nbsp;
								</c:when>
							</c:choose>
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:choose>
									<c:when test="${i eq nowPage}">
										[${i}]&nbsp;
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/message/MessageList.msg?page=${i}">${i}&nbsp;</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:choose>
								<c:when test="${nowPage != totalPage}">
									<a href="${pageContext.request.contextPath}/message/MessageList.msg?page=${nowPage + 1}">[다음]</a>&nbsp;
								</c:when>
							</c:choose>
							</td>
						</tr>
					</table>
					<br><br>
		 		  </form>
		 		  <div class="s-12 m-12 l-6 center" id="mailval" style="text-align:center;">
					<a type="button button-black-stroke s-12" href="${pageContext.request.contextPath}/message/MessageWrite.msg" style="text-align:center;">쪽지보내기&nbsp;</a>
					<a type="button button-black-stroke s-12" href="${pageContext.request.contextPath}/message/MessageDeleteOk.msg?seq='+'javascript:click()" style="text-align:center;">삭제</a>
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
  function click() {
	  var val;
  	$(".message").click(function(){
  		var btn = $(this);
  		var data = btn.children($("<img>").attr("name","check_num"));
  		var value = data.attr("value");
  		val = value;
  	});
  	return val;
	}
  </script>
</body>
</html>