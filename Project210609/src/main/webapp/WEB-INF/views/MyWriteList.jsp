<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Write List</title>
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

<script>

	//글 강제 삭제
	function mdelete(bnumber, bwriter) {
		console.log('삭제 글' + bnumber + bwriter);
		var check = confirm('삭제하시겠습니까?');
		if (check) {
			location.href = "mboarddelete?bnumber=" + bnumber + "&bwriter=" + bwriter;
			alert('삭제 했습니다.');
		} else {
			alert('삭제 실패 했습니다.');
		}
	}
</script>
</head>
<body>
	<div id="header">
		<h1>My Write List</h1>
		<h2>${sessionScope.loginMember} 님 로그인 중</h2>	
	</div>
	
	<div id="nav">
		<c:if test="${sessionScope.loginMember eq 'admin'}">
			<a href="memberlist">회원 목록 조회</a>
		</c:if>
		
		<c:if test="${sessionScope.loginMember ne 'admin'}">
			<a href="mypage?mid=${sessionScope.loginMember}">마이 페이지 돌아가기</a>
		</c:if>
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
				<th>
					<!-- 관리자 --> 
					<c:if test="${sessionScope.loginMember eq 'admin'}">
						관리자 전용
					</c:if>
				</th>
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
					<td>
						<!-- 관리자 --> <c:if test="${sessionScope.loginMember eq 'admin'}">
							<button onclick="mdelete('${board.bnumber}','${board.bwriter}')">글 강제 삭제</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
</body>
</html>