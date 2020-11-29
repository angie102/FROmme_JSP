<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div style="width:25%; float:left; margin-right:3%; height:1000px;">
				<div style="width:80%; background-color:white; height:250px; margin:0 auto; margin-bottom:20px; text-align:center;">
					<br><label>프로필</label>
					<br><label>${session_id}님 환영합니다.</label>
				</div>
				<div style="width:80%; background-color:white; height:350px; margin:0 auto; text-align:center;">
					<div style="width:100%; background-color:gray; height:50px; margin:0 auto; text-align:center;">
						<br><p style="color:white;">퀵메뉴</p>
					</div>
					<div style="width:90%; background-color:white; height:300px; margin:0 auto; text-align:left;">
						<br><ul>
							<li>
								<a>MY 회원정보</a>
								<ul style="display:block;">
								<%
									if(session.getAttribute("authority") == null){
										session.setAttribute("authority", 0);
									}
									if(((int)session.getAttribute("authority") == 1)){
								%>
									<li><a href="${pageContext.request.contextPath}/user/UserCheckIdPw.use?no=100">개인정보 / 수정</a></li>
									<li><a href="${pageContext.request.contextPath}/user/UserCheckIdPw.use?no=200">공방정보 / 수정</a></li>
									<li><a href="#">회　원　탈　퇴</a></li>
								<%
									} else {
								%>		
									<li><a href="${pageContext.request.contextPath}/user/UserCheckIdPw.use?no=100">개인정보 / 수정</a></li>
									<li><a href="#">회　원　탈　퇴</a></li>
								<%
									}
								%>
								</ul>
							</li>
						</ul><br>
						<ul>
							<li>
								<a>MY 활동 내역</a>
								<ul style="display:block;">
									<li><a href="#">나의　예약 정보</a></li>
									<li><a href="#">문　의　내　역</a></li>
									<li><a href="#">주　문　내　역</a></li>
									<li><a href="#">내　가　쓴　글</a></li>
									<li><a href="${pageContext.request.contextPath}/message/MessageList.msg">쪽　　지　　함</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>