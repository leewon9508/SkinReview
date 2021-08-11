<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PageSearch</title>
<style>

#header {
		padding: 1px;
		background-color: black;
}

#nav {
		padding: 10px;
		background-color: gainsboro;
}

#content {
		padding: 20px;
}

h1 {
		padding-left: 10px;
		color: white;
}

h2{
		padding-left: 10px;
		color: yellow;
}

table, tr, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
}

th {
	background-color: yellow;
}
</style>
</head>
<body>
	<div id="header">
		<h1>PageSearch</h1>
		<h2>${sessionScope.loginMember} 님 로그인 중</h2>	
	</div>
	
	<div id="nav">
		<a href="paging">페이징 이동</a>
	</div>
	
	<div id="content">
		<!-- 페이징 -->
		<table>
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>제품명</th>
				<th>브랜드명</th>
				<th>카테고리</th>
				<th>작성자 개인 별점</th>
				<th>작성 날자</th>
				<th>조회수</th>
				<th>상세보기</th>
			</tr>
			<c:forEach var="board" items="${boardList}">
				<tr>
					<td>${board.bnumber}</td>
					<td>${board.bwriter}</td>
					<td>${board.bproduct}</td>
					<td>${board.bbrand}</td>
					<td>${board.bcatrgort}</td>
					<td>${board.bscore}</td>
					<td>${board.bdate}</td>
					<td>${board.bhits}</td>
					<td><a href="boardview?bnumber=${board.bnumber}&page=${paging.page}">상세보기</a></td>
				</tr>
			</c:forEach>
		</table>
	
		<c:choose>
			<c:when test="${paging.page<=1}">
				[이전]&nbsp;
			</c:when>
			<c:otherwise>
				<a href="search?page=${paging.page-1}&searchtype=${searchType}&keyword=${keyword}">[이전]</a>&nbsp;
			</c:otherwise>
		</c:choose>
	
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
			<c:choose>
				<c:when test="${i eq paging.page}">
					${i}
				</c:when>
				<c:otherwise>
					<a href="search?page=${i}&searchtype=${searchType}&keyword=${keyword}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	
		<c:choose>
			<c:when test="${paging.page>=paging.maxPage}">
				[다음]
			</c:when>
			<c:otherwise>
				<a href="search?page=${paging.page+1}&searchtype=${searchType}&keyword=${keyword}">[다음]</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>