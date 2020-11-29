<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="board_title">${category_name}</div>
<div class="sort_wrapper">
	<h4>검색 결과 : ${totalCount} 건</h4>
	<ul>
		<c:if test="${(category_no == 3) or (category_no == 4)}">
			<c:set var="url" value="${pageContext.request.contextPath}/board/BoardList.bo?cat=${category_no}&sort=${date}&field=${field}&search=${search}" />
			<li>
				<i><a href="${url}&listtype=text" class="material-icons sort_icon ${(listtype == null) or (listtype == 'text') ? 'selected' : ''}">article</a></i>
			</li>
			<li>
	      		<i><a href="${url}&listtype=image" class="material-icons sort_icon ${(listtype == 'image') ?  'selected' : ''}">perm_media</a></i>
	      	</li>
		</c:if>
		<c:set var="url" value="${pageContext.request.contextPath}/board/BoardList.bo?cat=${category_no}&field=${field}&search=${search}&listtype=${listtype}" />
      	<li class="sort">
      		<a href="${url}&sort=date" class="${(sort == 'date') or (sort == null) ? 'selected' : ''}">최신순</a>
      	</li>
      	<li class="sort">
      		<a href="${url}&sort=views" class="${sort == 'views' ? 'selected' : ''}">조회수</a>
		</li>
	</ul>
</div>