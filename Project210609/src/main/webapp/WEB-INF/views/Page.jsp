<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page</title>
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
<!-- 로그아웃 -->
	function logout() {
		var check = confirm('로그아웃 하시겠습니까?');
		if (check){
		location.href = "logout";
		alert('로그아웃 성공, 초기 화면으로 돌아갑니다.');			
		} else {
			alert('로그아웃 취소');
		}
	}
	
<!-- 검색 기능 -->
	function search(){
		var text = document.getElementById('text').value;
		if (text.length == 0) {
			alert('검색어를 입력 해주세요,');	
		}else {			
			searchform.submit();
		}
	}
</script>

</head>
<body>
	<div id="header">
		<h1>Page</h1>
		<h2>${sessionScope.loginMember} 님 로그인 중</h2>	
	</div>
	
	<div id="nav">
		<!-- 로그아웃 -->
		<button onclick="logout()">로그아웃</button>
		<br>
	
		<!-- 관리자 -->
		<c:if test="${sessionScope.loginMember eq 'admin'}">
			<h3>관리자 전용</h3>
			<a href="memberlist">회원 목록 조회</a>
			<br>
		</c:if>
	
		<!-- 회원 -->
		<c:if test="${sessionScope.loginMember ne 'admin'}">
			<h3>회원 전용</h3>
			<a href="mypage?mid=${sessionScope.loginMember}">마이 페이지</a>
			<br>
			<a href="writepage">글 쓰기</a>
		</c:if>
		<br>
	
		<!-- 메뉴 -->
		<a href="paging">전체보기</a>
		<a href="skinpage" >스킨</a>
		<a href="lotionpage">로션</a>
		<a href="lippage">립</a>
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
				<a href="paging?page=${paging.page-1}">[이전]</a>&nbsp;
			</c:otherwise>
		</c:choose>
	
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i"
			step="1">
			<c:choose>
				<c:when test="${i eq paging.page}">
					${i} <!-- 현재 클릭한 페이지는 클릭 못하게 -->
				</c:when>
				<c:otherwise>
					<a href="paging?page=${i}">${i}</a>
					<!-- 현재 클릭한 페이지를 제외하고 클릭 할수 있게 -->
				</c:otherwise>
			</c:choose>
		</c:forEach>
	
		<c:choose>
			<c:when test="${paging.page>=paging.maxPage}">
				[다음]
			</c:when>
			<c:otherwise>
				<a href="paging?page=${paging.page+1}">[다음]</a>
			</c:otherwise>
		</c:choose>
	
		<!-- 검색기능 -->
		<form action="search" method="get" name="searchform">
			<select name="searchtype">
				<option value="bproduct">제품명</option>
				<option value="bbrand">브랜드명</option>
				<option value="bwriter">작성자</option>
			</select> 
			<input type="text" name="keyword" placeholder="검색어 입력" id="text">
			<input type="button" value="검색" onclick="search()">
		</form>
	</div>

</body>
</html>