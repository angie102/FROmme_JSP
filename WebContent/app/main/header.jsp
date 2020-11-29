<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- Top Navigation -->
		<nav
			class="background-transparent background-transparent-hightlight full-width sticky" style="background:white;">
			<div class="s-12 l-2 header-logo">
				<a href="${pageContext.request.contextPath}/app/main/index.jsp" class="logo"> <!-- Logo White Version --> <img
					class="logo-white"
					src="${pageContext.request.contextPath}/app/assets/img/logo-dark.png" alt="">
					<!-- Logo Dark Version --> <img class="logo-dark"
					src="${pageContext.request.contextPath}/app/assets/img/logo-dark.png" alt="">
				</a>
			</div>
			<div class="top-nav s-12 l-10">
			
				<ul class="right chevron text-dark">
					<li><a href="${pageContext.request.contextPath}/app/main/index.jsp">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/classes/classesList.cls">Class</a></li>
					<li>
						<a href="${pageContext.request.contextPath}/board/BoardList.bo?cat=1">Board</a>
						<ul>
							<li><a href="${pageContext.request.contextPath}/board/BoardList.bo?cat=1">공지사항</a></li>
							<li><a href="${pageContext.request.contextPath}/board/BoardList.bo?cat=2">문의사항</a></li>
						</ul>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/board/BoardList.bo?cat=3">Community</a>
						<ul>
							<li><a href="${pageContext.request.contextPath}/board/BoardList.bo?cat=3">내작품자랑</a></li>
							<li><a href="${pageContext.request.contextPath}/board/BoardList.bo?cat=4">중고거래</a></li>
						</ul>
					</li>
					<li><a href="../user/map.html">Map</a></li>
					<%
						String users_id = "";
						if(session.getAttribute("session_id") != null){
							users_id = (String)session.getAttribute("session_id");
						}
						if (users_id.equals("")){
					%>
						<li><a href="${pageContext.request.contextPath}/app/user/login.jsp"><b>Login</b></a></li>
					<%		
						} else {
					%>
						<li>
							<%
								if(session.getAttribute("authority") == null){
									session.setAttribute("authority", 0);
								}
								if(((int)session.getAttribute("authority") == 1)){
							%>
								<a href="${pageContext.request.contextPath}/app/user/myPage_studio.jsp?cat=5"><b><%=users_id %></b></a>
								<ul>
									<li><a href="${pageContext.request.contextPath}/app/user/myPage_studio.jsp?cat=5">MyPage</a></li>
									<li><a href="${pageContext.request.contextPath}/app/user/myStudio.jsp?cat=6">MyStudio</a></li>
									<li><a href="${pageContext.request.contextPath}/user/UserLogout.use?cat=7">Logout</a></li>
								</ul>
								<%		
									}else{
								%>
								<a href="${pageContext.request.contextPath}/app/user/myPage.jsp?cat=5"><b><%=users_id %></b></a>
								<ul>
									<li><a href="${pageContext.request.contextPath}/app/user/myPage.jsp?cat=5">MyPage</a></li>
									<li><a href="${pageContext.request.contextPath}/user/UserLogout.use?cat=6">Logout</a></li>
								</ul>
								<%
									}
								%>
						</li>
					<%
						}
					%>
				</ul>
			</div>
		</nav>