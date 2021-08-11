<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board View</title>

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

table, tr, td, th {
	border: 1px black solid;
	border-collapse: collapse;
	text-align: center;
}

th {
	background-color: yellow;
</style>

<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<script>

//글 삭제
	function boardDelete() {
		var pwd = prompt('글 비밀번호를 입력하세요');
		var bpassword = '${board.bpassword}';
		if (pwd == bpassword) {
			location.href = 'boarddelete?bnumber=' + ${board.bnumber};
		} else {
			alert('비밀번호 불일치');
		}
	}

	//댓글 내용
	$(document).ready(function(){
		$("#cwrite-btn").click(function(){
			var cwriter = document.getElementById('cwriter').value;
			var ccontents = document.getElementById('ccontents').value;
			var cbnumber = '${board.bnumber}';
			console.log(cwriter);
			console.log(ccontents);
			console.log(cbnumber);
			$.ajax({
				type: 'post',
				url: 'comment/commentwrite',
				data:{
									
					'cwriter': cwriter,
					'ccontents': ccontents,
					'cbnumber': cbnumber},
				dataType: 'json',
				success: function(list){
					console.log(list);
					var output = "<table border='1'>";
					output += "<tr><th>작성자</th>";
					output += "<th>내용</th>";
					output += "<th>날짜</th>";
					output += "<th>삭제</th></tr>";
					for(var i in list){
						output += "<tr>";
						output += "<td>"+list[i].cwriter+"</td>";
						output += "<td>"+list[i].ccontents+"</td>";
						output += "<td>"+list[i].cdate+"</td>";
						output += "<td><button onclick=commentDelete("+"'"+list[i].cnumber+"'"+")>삭제</button></td>";
						output += "</tr>";
					}
					output += "</table>";
					document.getElementById('comment-list').innerHTML = output;
					document.getElementById('cwriter').value='${sessionScope.loginMember}';
					document.getElementById('ccontents').value='';
				},
				error: function(){
					console.log('문제있음.');
				}
			});
		});
	});

	// 댓글 삭제
		function commentDelete(cnumber) {
			//var cnumber = document.getElementById('cnumber').value;
			var cbnumber = '${board.bnumber}';
			//var cwriter = document.getElementById('cwriter').value;
			//var ccontents = document.getElementById('ccontents').value;
			//var cdate = document.getElementById('cdate').value;
			console.log(cnumber);
			console.log(cbnumber);
			//console.log(cwriter);
			//console.log(ccontents);
			//console.log(cdate);
			if(confirm('댓글을 삭제 하시겠습니까?')){
			$.ajax({
				type: 'post',
				url: 'comment/commentDelete',
				data:{
					'cnumber': cnumber,					
					'cbnumber': cbnumber
					/* 'cwriter': cwriter,
					'ccontents': ccontents,
					'cdate': cdate */
					},
				dataType: 'json',
				success: function(list){
					console.log(list);
					var output = "<table border='1'>";
					output += "<tr><th>작성자</th>";
					output += "<th>내용</th>";
					output += "<th>날짜</th>";
					output += "<th>삭제</th></tr>";
					for(var i in list){
						output += "<tr>";
						output += "<td>"+list[i].cwriter+"</td>";
						output += "<td>"+list[i].ccontents+"</td>";
						output += "<td>"+list[i].cdate+"</td>";
						output += "<td><button onclick=commentDelete("+"'"+list[i].cnumber+"'"+")>삭제</button></td>";
						output += "</tr>";
					//document.getElementById('cnumber').value='+list[i].cnumber+';
					//document.getElementById('cbnumber').value='+list[i].cbnumber+';
					//document.getElementById('cwriter').value='+list[i].cwriter+';
					//document.getElementById('ccontents').value='+list[i].ccontents+';
					//document.getElementById('cdate').value='+list[i].cdate+';
					}
					output += "</table>";
					document.getElementById('comment-list').innerHTML = output;
					document.getElementById('cwriter').value='${sessionScope.loginMember}';
					document.getElementById('ccontents').value='';
				},
				error: function(){
					console.log('문제있음.');
					console.log('삭제 실패.');
					alert('삭제하지 못했습니다.');
				}
			});
		   }
		}

	// 별점 주기 중복 제거
	function swriterCheck(){
		var swriter = document.getElementById('bwriter').value;
		var sbnumber = document.getElementById('bnumber').value;
		console.log(swriter, sbnumber);
		$.ajax({
			type: 'post',
			url: 'sWriterCheck',
			data: {'swriter':swriter,
					'sbnumber':sbnumber},
			dataType: 'text',
			success: function (result) {
				console.log('리턴 값 : ' + result);
				if (result =="ok") {
					scoreform.submit();
				} else {
					alert('이미 별점을 주셨습니다.')
				}
			},
			error: function (){
				console.log('작동 실패')
			}
		});
	}
	
	<!--별점 삭제-->
	function scoreDelete(snumber,bnumber) {
		var snumber = document.getElementById('snumber').value;
		var bnumber = document.getElementById('bnumber').value;
		console.log('삭제');
		console.log('snumber : ', snumber);
		console.log('bnumber : ', bnumber);
		var check = confirm('별점을 삭제 하시겠습니까?');
		if (check) {
			location.href = "scoreDelete?snumber=" + snumber + "&bnumber=" + bnumber;
			alert('별점을 삭제 했습니다.');
		}else  {
			alert('삭제를 취소 하셨습니다.');
		}
	}
	
	
</script>

</head>
<body>
	<div id="header">
		<h1>Board View</h1>
		<h2>${sessionScope.loginMember} 님 로그인 중</h2>	
	</div>
	
	<div id="nav">
		<a href="paging">페이징 이동</a>
	</div>
	
	<div id="content">
		글 번호 : ${board.bnumber}
		<br> 제품명 : ${board.bproduct}
		<br> 브랜드명 :${board.bbrand}
		<br> 작성자 : ${board.bwriter}
		<br> 장점 : ${board.bmerit}
		<br> 단점 : ${board.bflaw}
		<br> 카테고리 : ${board.bcatrgort}
		<br> 사진 :
		<img src="resources/upload/${board.bfilename}" height="100" width="100">
		<br> 개인 별점 : ${board.bscore}
		<br> 작성 날자 : ${board.bdate}
		<br> 조회수 : ${board.bhits}
		<br>
		<c:if test="${sessionScope.loginMember == board.bwriter}">
			<h3>작성자 전용</h3>
			<!-- 수정 -->
			<a href="boardupdate?bnumber=${board.bnumber}">수정</a> <br>
			<button onclick="boardDelete()">삭제</button>
			<br>
		</c:if>
	
		<!-- 댓글 -->
		<h3>댓글</h3>
		<div id="comment-write">
			작성자 : <input type="text" id="cwriter" value="${sessionScope.loginMember}" readonly> <br> 
			내용 : <input type="text" id="ccontents"> <br>
			<button id="cwrite-btn">댓글 등록</button>
		</div>
		<br>
		
		<!-- 댓글 목록출력 -->
		<div id="comment-list">
			<table>
				<tr>
					<th>작성자</th>
					<th>내용</th>
					<th>날짜</th>
					<th>삭제</th>
				</tr>
			<c:forEach var="comment" items="${commentList}">
					<tr>
						<td>${comment.cwriter}</td>
						<td>${comment.ccontents}</td>
						<td>${comment.cdate}</td>
						<td>
						
							<%-- <input type="hidden" value="${comment.cnumber}" id="cnumber">
							<input type="hidden" value="${comment.cwriter}" id="cwriter">
							<input type="hidden" value="${comment.ccontents}" id="ccontents">
							<input type="hidden" value="${comment.cdate}" id="cdate"> --%>
							<button onclick="commentDelete('${comment.cnumber}')">삭제</button>
						</td>
						
					</tr>
			</c:forEach>
			</table>
		</div>
		<!-- 별점 -->
		<h3>별점</h3>
		<h3>별점 평균</h3>
			<h5>${score}</h5>
	
		<form action="scoregive" method="post" name="scoreform">
			<div class="star-rating space-x-4 mx-auto">
				<input type="hidden" name="bnumber" value="${board.bnumber}" id ="bnumber">
				<input type="hidden" name="sbnumber" value="${board.bnumber}" >
				<input type="hidden" name="swriter" value="${board.bwriter}" id="bwriter">
				<input type="radio" id="5-stars" name="sscore" value="5" v-model="ratings"/>
					<label for="5-stars" class="star pr-4">★</label>
				<input type="radio" id="4-stars" name="sscore" value="4" v-model="ratings"/>
					<label for="4-stars" class="star">★</label>
				<input type="radio" id="3-stars" name="sscore" value="3" v-model="ratings"/>
					<label for="3-stars" class="star">★</label>
				<input type="radio" id="2-stars" name="sscore" value="2" v-model="ratings"/>
					<label for="2-stars" class="star">★</label>
				<input type="radio" id="1-star" name="sscore" value="1" v-model="ratings" />
					<label for="1-star" class="star">★</label>
			</div>
				<input type="button" value="별점 주기" onclick="swriterCheck()">
		</form>
	
		<!-- 별점 출력 -->
		<h3>별점 목록</h3>
		<table>
			<tr>
				<th>별점</th>
				<th>
					<c:if test="${sessionScope.loginMember ne 'admin'}">
						삭제
					</c:if>
				</th>
			</tr>
				<c:forEach var="score" items="${scoreList}">
				<tr>
					<c:if test="${board.bnumber eq score.sbnumber}">
							<td>
							${score.sscore}
							</td>
							<td>
							<c:if test="${sessionScope.loginMember ne 'admin'}">
								<input type="hidden" value="${score.snumber}" id="snumber">
								<input type="hidden" value="${board.bnumber}" id="bnumber">
									<button onclick="scoreDelete(${score.snumber},${board.bnumber})">별점 취소</button>
							</c:if>
							</td>
					</c:if>
				</tr>
				</c:forEach>	
		</table>
	</div>
	
</body>
</html>