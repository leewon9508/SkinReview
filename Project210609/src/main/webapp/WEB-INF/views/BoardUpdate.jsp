<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Update</title>

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

.star-rating {
  display: flex;
  flex-direction: row-reverse;
  font-size: 1.5rem;
  line-height: 2.5rem;
  justify-content: space-around;
  padding: 0 0.2em;
  text-align: center;
  width: 5em;
}
 
.star-rating input {
  display: none;
}
 
.star-rating label {
  -webkit-text-fill-color: transparent; /* Will override color (regardless of order) */
  -webkit-text-stroke-width: 1.3px;
  -webkit-text-stroke-color: #2b2a29;
  cursor: pointer;
}
 
.star-rating :checked ~ label {
  -webkit-text-fill-color: gold;
}
 
.star-rating label:hover,
.star-rating label:hover ~ label {
  -webkit-text-fill-color: #fff58c;
}
</style>

<script> src="https://code.jquery.com/jquery-3.6.0.js" </script>
<script>
// 글 수정
function update() {
	console.log('수정');
	console.log(${board.bnumber});
	var bproduct = document.getElementById('bproduct').value;
	var bbrand = document.getElementById('bbrand').value;
	var bpassword = document.getElementById('bpassword').value;
	var bmerit = document.getElementById('bmerit').value;
	var bflaw = document.getElementById('bflaw').value;
	var bcatrgort = document.getElementById('bcatrgort').value;
	if (bproduct.length == 0) {
		alert('제품명을 입력 하세여요.');		
	} else if (bbrand.length == 0) {
		alert('브랜드명을 입력 하세요.');		
	} else if (bpassword.length == 0) {
		alert('글 비밀번호를 입력 하세요.');		
	} else if (bmerit.length == 0) {
		alert('장점을 입력 하세요.');		
	} else if (bflaw.length == 0) {
		alert('단점을 입력 하세요.');		
	} else if (bcatrgort.length == 0) {
		alert('카테고리를 선택 해주세요.');		
	} else {
		var pwd = prompt('수정를 위해 글 비밀번호를 입력하세요');
		var bpassword = '${board.bpassword}';
		if (pwd == bpassword) {
			updateform.submit();
			alert('수정에 성공하였습니다.!!!');	
		} else{
			alert('비밀번호가 틀립니다!!!');	
		}
	}
	
}

</script>
</head>
<body>
	<div id="header">
		<h1>Board Update</h1>
		<h2>${sessionScope.loginMember} 님 로그인 중</h2>	
	</div>
	
	<div id="nav">
		<a href="boardview?bnumber=${board.bnumber}&page=${paging.page}">글 상세 조회</a>
	</div>
	
	<div id="content">
	<form action="bupdateprocess" method="post" enctype="multipart/form-data" id="bupdateprocess" name="updateform">
		<input type="hidden" name="bnumber" value="${board.bnumber}"><br>
		제품명 : <input type="text" name="bproduct" maxlength="50" id="bproduct"> <br>
		브랜드명 : <input type="text" name="bbrand" maxlength="50" id="bbrand"> <br>
		작성자 : <input name="bwriter" value="${sessionScope.loginMember}" readonly> <br>  
		글 비밀 번호 : <input type="text" name="bpassword" maxlength="5" placeholder="5자리" id="bpassword"> <br>
		장점 : <textarea name="bmerit" cols="50" rows="10" maxlength="100" id="bmerit"></textarea> <br> 
		단점 : <textarea name="bflaw" cols="50" rows="10" maxlength="100" id="bflaw"></textarea> <br> 
		카테고리 : <select name="bcatrgort" id="bcatrgort">
					<option value="">선택 하세요.</option>
					<option value="스킨">스킨</option>
					<option value="로션">로션</option>
					<option value="립밤">립밤</option>
				</select> <br> 
		사진 : <input type="file" name="bfile"> <br> 
		개인 별점을 새롭게 주세요 : <div class="star-rating space-x-4 mx-auto"> 
								<input type="radio" id="5-stars" name="bscore" value="5" v-model="ratings"/>
									<label for="5-stars" class="star pr-4">★</label>
								<input type="radio" id="4-stars" name="bscore" value="4" v-model="ratings"/>
									<label for="4-stars" class="star">★</label>
								<input type="radio" id="3-stars" name="bscore" value="3" v-model="ratings"/>
									<label for="3-stars" class="star">★</label>
								<input type="radio" id="2-stars" name="bscore" value="2" v-model="ratings"/>
									<label for="2-stars" class="star">★</label>
								<input type="radio" id="1-star" name="bscore" value="1" v-model="ratings" />
									<label for="1-star" class="star">★</label>
							</div>
			 <br> 
		<input type="button" value="글 수정" onclick="update()">
	</form>
	</div>
	
	
	
</body>
</html>