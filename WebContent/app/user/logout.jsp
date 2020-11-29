<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko-KR">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// 담긴 모든 세션은 제거한다.
	session.invalidate();
	// user_id만 삭제된다.
	// session.removeAttribute("uesr_id");
%>
	<script>
		alert("로그아웃 되었습니다.");
		location.href = "${pageContext.request.contextPath}/app/main/index.jsp";
	</script>
</body>
</html>