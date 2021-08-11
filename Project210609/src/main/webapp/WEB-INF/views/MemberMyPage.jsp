<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>my page</title>

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

</style>
</head>
<body>
	<div id="header">
		<h1>My Page</h1>
		<h2>${sessionScope.loginMember} 님 로그인 중</h2>	
	</div>
	
	<div id="nav">
		<a href="memberview?mid=${member.mid}">상세 조회</a> <br>
		<a href="memberupdate?mid=${member.mid}">회원 수정</a> <br>
		<a href="mywritelist?mid=${member.mid}">내가 작성한 글 보기</a> <br>
		<a href="paging">페이징 이동</a>
	</div>
</body>
</html>