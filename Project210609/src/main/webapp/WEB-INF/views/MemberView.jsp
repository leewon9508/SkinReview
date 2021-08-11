<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<script>
	// 회원 정보 수정 요청
	function update() {
		console.log('회원 정보 수정');
		location.href = "memberupdate";
	}

	// 회원 탈퇴
	function mdelete() {
		console.log('탈퇴');
		var pwd = prompt('작성한 글, 댓글, 별점 모두 삭제 됩니다. 탈퇴하시겠습니까? 희망하시면 비밀번호를 입력 하세요.');
		var mpassword = '${View.mpassword}';
		if (pwd == mpassword) {
			location.href = 'memberdelete?mid=' + '${View.mid}';
			alert('탈퇴 했습니다 초기화면으로 돌아갑니다.');
		} else {
			alert('비밀번호 불일치');
		}
	}
</script>
</head>
<body>
	<div id="header">
		<h1>MemberView</h1>
		<h2>${sessionScope.loginMember} 님 로그인 중</h2>	
	</div>
	
	<div id="nav">
		<c:if test="${sessionScope.loginMember ne 'admin'}">
			<h3>회원 전용</h3>
			<button onclick="update()">회원 정보 수정</button>
			<button onclick="mdelete()">회원 탈퇴</button>
			<br>
		</c:if>
		<br>
		
		<c:if test="${sessionScope.loginMember eq 'admin'}">
			<a href="memberlist">회원 목록 조회</a>
		</c:if>
		
		<c:if test="${sessionScope.loginMember ne 'admin'}">
			<a href="mypage?mid=${sessionScope.loginMember}">마이 페이지</a>
		</c:if>
	</div>
	
	<div id="content">
		<br> 아이디 : ${View.mid}
		<br> 비밀번호 : ${View.mpassword}
		<br> 이름 : ${View.mname}
		<br> 전화번호 : ${View.mphon}
		<br> 이메일 : ${View.memail}
	</div>

</body>
</html>