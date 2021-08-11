<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberLogin</title>

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
	// 입력하지 않은 내용 체크
	function check() {
		var mid = document.getElementById('mid').value;
		var mpassword = document.getElementById('mpassword').value;
		console.log(mid);
		console.log(mpassword);
		if (mid.length == 0) {
			alert('아이디를 입력 하세요.');		
		} else if (mpassword.length == 0) {
			alert('비밀번호를 입력 하세요.');		
		} else {
			$.ajax({
				type: 'post', 
				url: 'idpwdcheck',
				data: {'mid':mid,
					   'mpassword':mpassword}, 
				dataType: 'text',
				success: function (result) { 
					if(result =="ok"){
						updateform.submit();
					}else {
						alert('정보가 일치하지 않습니다.');		
					}
				},
				error: function () {
					console.log('제대로 안돌고 있음');
				}
			});
		}
	}
</script>
</head>

<body>
	<div id="header">
		<h1>MemberLogin</h1>
	</div>
	<div id="nav">
		<a href="./">홈으로</a>	
	</div>
	
	<div id="content">
		<form action="login" method="post" name="updateform">
			아이디 : <input type="text" name="mid" id="mid"> <br>
			비밀번호 : <input type="text" name="mpassword" id="mpassword"> <br>
			<input type="button" value="로그인" onclick="check()">
		</form>
	</div>
	
	
</body>
</html>