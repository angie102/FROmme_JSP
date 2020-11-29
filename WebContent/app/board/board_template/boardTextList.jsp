<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table class="bbsListTbl" summary="번호, 제목, 조회수, 작성일 등을 제공하는 표">
	<caption class="hdd">공지사항 목록</caption>
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">아이디</th>
			<th scope="col">작성일</th>
			<th scope="col">조회수</th>
			<c:if test="${users_authority == 3}">
				<th scope="col">비공개</th>
				<c:if test="${category_no == 1}">
					<th scope="col">삭제</th>
				</c:if>
			</c:if>
		</tr>
	</thead>
	<tbody>
		<form action="#" method="POST" name="listform">
			<c:choose>
				<c:when test="${list != null and fn:length(list) > 0}">
					<c:forEach var="b_bean" items="${list}" varStatus="stat">
						<c:set var="today_year" value="${today.year + 1900}"></c:set>
						<c:set var="today_day" value="${today.date}"></c:set>
						<fmt:parseDate var="parsedDate" value="${b_bean.getPost_date()}" pattern="yyyy-MM-dd HH:mm:ss"/>
						<fmt:parseNumber var="post_year" value="${parsedDate.year + 1900}"/>
						<fmt:parseNumber var="post_date" value="${parsedDate.date}"/>
						<c:set var="dayDiff" value="${today_day - post_date}"/>
						<c:set var="isToday" value="${(today_year eq post_year) and (dayDiff eq 0)}" />
						<tr>
							<td>${totalCount - stat.index - ((nowPage - 1) * 10)}</td>
							<td class="tit_notice">
								<a href="javascript:checkSessionIdBeforeSubmitBoardView(${b_bean.getPost_no()},${b_bean.getCategory_no()})">
									${b_bean.getPost_title()}
								</a>
								<c:if test="${b_bean.getReply_count() > 0}">
									<span style="color:red; font-weight:bold;">[${b_bean.getReply_count()}]</span>								
								</c:if>
								<c:if test="${isToday}">
									<span class="material-icons fiber_new">fiber_new</span>								
								</c:if>
							</td>
							<td class="id_notice notice${stat.index}" style="position:relative;">
								<%-- 공방주일 때 --%>
								<c:if test="${b_bean.getUsers_authority() == 2}">
									<i class="icon-sli-star" style="color:#000; font-size: 12px; vertical-align: middle; font-weight: bold;"></i>
								</c:if>
								<%-- 관리자일 때 --%>
								<c:if test="${b_bean.getUsers_authority() == 3}">
									<i class="icon-sli-key" style="color:#000; font-size: 12px; vertical-align: middle; font-weight: bold;"></i>
								</c:if>
								<c:if test="${b_bean.getUsers_id() eq session_id }">
									<span class="id">${b_bean.getUsers_id()}</span>								
								</c:if>
								<c:if test="${b_bean.getUsers_id() != session_id }">
									<span class="id" onclick="showmenu(${stat.index})">${b_bean.getUsers_id()}</span>
									<ul style="display:none;">
										<li><a href="${pageContext.request.contextPath}/board/BoardListByUser.bo?cat=${b_bean.getCategory_no()}&page=1&users_id=${b_bean.getUsers_id()}&filter=board">게시글보기</a></li>
										<li><a href="javascript:checkSessionIdBeforeSendMessage('${b_bean.getUsers_id()}')">쪽지보내기</a></li>
										<li><a href="javascript:checkSessionIdBeforeSendMessage('admin')">신고하기</a></li>
									</ul>
								</c:if>
							</td>
							<td>
								<c:choose>
									<c:when test="${isToday}">
										<fmt:formatDate var="boardPostDate" value="${parsedDate}" pattern="HH:mm"/>
									</c:when>
									<c:otherwise>
										<fmt:formatDate var="boardPostDate" value="${parsedDate}" pattern="yyyy.MM.dd"/>
									</c:otherwise>
								</c:choose>
								${boardPostDate}
							</td>
							<td>${b_bean.getPost_views()}</td>
							<c:if test="${users_authority == 3}">
								<td><input type="checkbox" style="width:17px; height:17px;" name="pub_check" value="${b_bean.getPost_no()}" ${b_bean.getPost_pub() eq 2 ? 'checked' : ''}/></td>
								<c:if test="${category_no == 1}">
									<td><input type="checkbox" style="width:17px; height:17px;" name="del_check" value="${b_bean.getPost_no()}"/></td>
								</c:if>
								<input type="hidden" name="nowPages_post_no" value="${b_bean.getPost_no()}" />
							</c:if> 
						</tr>          		
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7" align="center">등록된 게시물이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</form>
		<form action="${pageContext.request.contextPath}/message/MessageWrite.msg" method="GET" name="sendmessageform">
			<input type="hidden" name="reply_id" value="" />
		</form>
		<form action="${pageContext.request.contextPath}/board/BoardView.bo" method="GET" name="boardviewform">
			<input type="hidden" name="seq" value="" />
			<input type="hidden" name="cat" value="" />
			<input type="hidden" name="page" value="${nowPage}" />
			<input type="hidden" name="sort" value="${sort}" />
			<input type="hidden" name="field" value="${param.field}" />
			<input type="hidden" name="search" value="${param.search}" />
			<input type="hidden" name="listtype" value="${listtype}" />
			<input type="hidden" name="catname" value="${category_name}" />
		</form>
	</tbody>
</table>