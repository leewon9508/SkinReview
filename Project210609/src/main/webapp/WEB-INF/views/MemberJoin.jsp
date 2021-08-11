<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>MemberJoin</title>
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
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	// 아이디 중복 확인
	function id_ob(){
		var mid = document.getElementById('mid').value;
		var mid_result = document.getElementById('mid_result');
		var exp = /^(?=.*[a-z])(?=.*\d)[a-z\d]{6,10}$/;
		if (mid.length == 0) {
			mid_result.style.color = 'red';
			mid_result.innerHTML = '아이디는 필수 입력 입니다.';
		} else if (mid.match(exp)) {
			mid_result.style.color = 'green';
			mid_result.innerHTML = '사용 가능한 아이디 입니다.';
				$.ajax({
					type: 'post', 
					url: 'idcheck',
					data: {'mid':mid}, 
					dataType: 'text',
					success: function (result) { 
						console.log('제대로 돌고 있음');
						console.log('리턴 값' + result);
						if(result =="ok"){
							mid_result.style.color = 'green';
							mid_result.innerHTML = '사용가능한 아이디 입니다.';
						}else {
							mid_result.style.color = 'red';
							mid_result.innerHTML = '이미 사용중인 아이디 입니다.';
						}
					},
					error: function () {
						console.log('제대로 안돌고 있음');
					}
				});
		} else if (mid == 'admin'){
			mid_result.style.color = 'green';
			mid_result.innerHTML = '관리자 모드 아이디 입니다.';
		}else {
			mid_result.style.color = 'red';
			mid_result.innerHTML = '입력 조건이 맞지 않습니다.';
		}
	}
	
	// 비밀번호 정규식
	function pwd_ob() {
		var pwd = document.getElementById('mpassword').value;
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
	
	// 입력하지 않은 내용 체크
	function check() {
		var mid = document.getElementById('mid').value;
		var mpassword = document.getElementById('mpassword').value;
		var mname = document.getElementById('mname').value;
		var mphon = document.getElementById('mphon').value;
		var memail = document.getElementById('memail').value;
		if (mid.length == 0) {
			alert('아이디를 입력 하세요.');		
		} else if (mpassword.length == 0) {
			alert('비밀번호를 입력 하세요.');		
		} else if (mname.length == 0) {
			alert('이름을 입력 하세요.');		
		} else if (mphon.length == 0) {
			alert('전화번호를 입력 하세요.');		
		} else if (memail.length == 0) {
			alert('이메일을 입력 하세요.');		
		} else {
			memberform.submit();
		}
	}
	
</script>
</head>
<body>
	<div id="header">
		<h1>MemberJoin</h1>
	</div>
	
	<div id="nav">
		<a href="./">홈으로</a>
	</div>
	
	<div id="content">
		<form action="memberjoin" method="post" name="memberform">
				아이디 : <input type="text" name="mid" id="mid" onkeyup="id_ob()" onblur="id_ob()" placeholder="영문 소문자, 숫자 6~10글자" maxlength="20"><br> 
					<span id="mid_result"></span> <br>
				비밀번호 : <input type="text" name="mpassword" id="mpassword" onkeyup="pwd_ok()" onblur="pwd_ob()"placeholder="영문 소문자, 숫자 6~10글자" maxlength="20"> <br> 
					<span id="pwd_result"></span> <br>
				이름 : <input type="text" name="mname" maxlength="10" id="mname"> <br> 
				전화번호 : <input type="text" name="mphon" maxlength="15" id="mphon"> <br> 
				이메일 : <input type="text"name="memail" maxlength="50" id="memail"> <br> 
				<input type="button" value="회원 가입 하기" onclick="check()">
		</form>
	</div>
</body>
</html>
