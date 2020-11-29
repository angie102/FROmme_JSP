<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<form action="${pageContext.request.contextPath}/board/BoardList.bo" class="minisrch_form" name="srchform" method="get">
		<fieldset style="margin-top:20px;">
			<input type="hidden" name="cat" value="${category_no}" />
			<input type="hidden" name="sort" value="${sort}" />
			<input type="hidden" name="listtype" value="${listtype}" />
			<select name='field' size='0' class="listbox">
				<option value='title' ${field eq "title" ? "selected" : "" }>제목</option>
				<option value='users_id' ${field eq "users_id" ? "selected" : "" }>아이디</option>
			</select>
			<legend>검색</legend>
			<input type="text" class="tbox" name="search" title="검색어를 입력하세요" placeholder="검색어를 입력하세요" value="${param.search}">
			<a href="javascript:searchBoard()" class="btn_srch">검색</a>
			<div class="btn_wrap">
				<c:if test="${users_authority eq 3}">
					<c:if test="${category_no eq 1}">
						<a href="javascript:checkBoard('del',${category_no},${nowPage})" class="btn_bbs write_btn">삭제</a>			
					</c:if>
					<c:if test="${listtype ne 'image'}">
						<a href="javascript:checkBoard('pub',${category_no},${nowPage})" class="btn_bbs write_btn">공개</a>			
					</c:if>
				</c:if>
				<c:if test="${category_no ne 1}">
					<a href="javascript:checkSessionIdBeforeSubmitBoardWrite()" class="btn_bbs write_btn">글쓰기</a>
				</c:if>
			</div>
		</fieldset>
		<div class="current_page" style="margin-top:20px;">
			<c:if test="${nowPage >= 1}">
				${nowPage} / ${totalPage} page			
			</c:if>
		</div>
	</form>
	<form action="${pageContext.request.contextPath}/board/BoardWrite.bo" method="GET" name="boardwriteform">
		<input type="hidden" name="cat" value="${category_no}" />
		<input type="hidden" name="catname" value="${category_name}" />
		<input type="hidden" name="page" value="${nowPage}" />
	</form>
</div>
<div class="pagination">
	<c:set var="url" value="${pageContext.request.contextPath}/board/BoardList.bo?&cat=${category_no}&sort=${sort}&field=${field}&search=${search}&listtype=${listtype}&catname=${catname}" />
	<c:set var="image_path" value="${pageContext.request.contextPath}/app/assets/img/board_images" />
	<c:choose>
		<c:when test="${nowPage > 1}">
			<a href="${url}&page=${startPage}" class="firstpage pbtn"><img src="${image_path}/btn_firstpage.png" alt="첫 페이지로 이동"></a>
			<a href="${url}&page=${nowPage - 1}" class="prevpage pbtn"><img src="${image_path}/btn_prevpage.png" alt="이전 페이지로 이동"></a>          	
		</c:when>          
	</c:choose>
	<c:if test="${nowPage >= 1}">
		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<c:choose>
				<c:when test="${i eq nowPage}">
					<a href="#"><span class="pagenum currentpage">${i}</span></a>
				</c:when>
				<c:otherwise>
					<a href="${url}&page=${i}"><span class="pagenum">${i}</span></a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</c:if>
	<c:choose>
		<c:when test="${nowPage != totalPage}">
			<a href="${url}&page=${nowPage + 1}" class="nextpage pbtn"><img src="${image_path}/btn_nextpage.png" alt="다음 페이지로 이동"></a>
			<a href="${url}&page=${endPage}" class="lastpage pbtn"><img src="${image_path}/btn_lastpage.png" alt="마지막 페이지로 이동"></a>                              	
		</c:when>
	</c:choose>
</div>