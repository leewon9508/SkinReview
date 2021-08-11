<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberList`</title>
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

table, tr, td, th {
	border: 1px black solid;
	border-collapse: collapse;
	text-align: center;
}

th {
	background-color: yellow;
}
</style>

<script>
	// 회원 강제 탈퇴
	function mdelete(id) {
		console.log('탈퇴 할 아이디' + id)
		var check = confirm('작성한 글, 댓글, 별점 모두 삭제 됩니다. 탈퇴하시겠습니까?');
		if (check) {
			location.href = "mmemberdelete?mid=" + id;
			alert('탈퇴 했습니다.');
		} else {
			alert('삭제 실패');
		}
	}
</script>
</head>
<body>
	<div id="header">
		<h1>Member List</h1>
		<h2>${sessionScope.loginMember} 님 로그인 중</h2>
		<h2>관리자 전용</h2>
	</div>
	
	<div id="nav">
		<a href="paging">페이징 이동</a>
	</div>
	
	<div id="content">
		<table>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>상세 조회</th>
				<th>작성 글 조회</th>
				<th>회원 강제 탈퇴</th>
			</tr>
			<c:forEach var="member" items="${MemberList}">
				<tr>
					<td>${member.mid}</td>
					<td>${member.mname}</td>
					<td><a href="memberview?mid=${member.mid}">조회</a></td>
					<td><a href="mywritelist?mid=${member.mid}">조회</a></td>
					<td><button onclick="mdelete('${member.mid}')">탈퇴</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>