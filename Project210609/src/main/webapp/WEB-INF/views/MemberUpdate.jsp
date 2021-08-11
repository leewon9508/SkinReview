
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Update</title>
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
	// 회원 정보 수정
		function update() {
	 		console.log('수정');
			var pwd = prompt('수정를 위해 비밀번호를 입력하세요');
			var mpassword = '${member.mpassword}';
			if(pwd == mpassword){
				updateform.submit();
				alert('수정 했습니다. 마이페이지 돌아갑니다.');
			} else {
				alert('비밀번호 불일치');
			}
	 	}
	
	// 비밀번호 정규식
		function pwd_ob() {
			var pwd = document.getElementById('pwd').value;
			var pwd_result = document.getElementById('pwd_result');
			var exp = /^(?=.*[a-z])(?=.*\d)[a-z\d]{6,10}$/;
			if (pwd.length == 0) {
				pwd_result.style.color = 'red';
				pwd_result.innerHTML = '비밀번호는 필수 입력 입니다.';
			} else if (pwd.match(exp)) {
				pwd_result.style.color = 'green';
				pwd_result.innerHTML = '사용 가능 한 비밀번호 입니다.';
			} else {
				pwd_result.style.color = 'red';
				pwd_result.innerHTML = '입력 조건이 맞지 않습니다.';
			}
		}
</script>
</head>
<body>
	<div id="header">
		<h1>Member Update</h1>
		<h2>${sessionScope.loginMember} 님 로그인 중</h2>	
	</div>
	
	<div id="nav">
		<a href="mypage?mid=${sessionScope.loginMember}">마이 페이지로 돌아가기</a>
	</div>

	<div id="content">
		<form action="mupdateprocess" method="post" name = "updateform">
			아이디 : <input type="text" name = "mid" value="${member.mid}" readonly > <br>
			비밀번호 : <input type="text" name="mpassword" id = "pwd" onkeyup="pwd_ok()" onblur="pwd_ob()"placeholder="변경 할 비밀번호 영문 소문자, 숫자 6~10글자"> <br>
					<span id="pwd_result"></span> <br>
			이름 : <input type="text" name="mname" value="${member.mname}" readonly> <br>
			전화번호 : <input type="text" name="mphon" id="mphon" placeholder="변경 할 전화번호" maxlength="15"> <br>
			이메일 : <input type="text" name="memail" id = "memail" placeholder="변경 할 이메일" maxlength="50"> <br>
			<input type="button" value="수정" onclick="update()"> <br>
		</form>
	</div>
</body>
</html>