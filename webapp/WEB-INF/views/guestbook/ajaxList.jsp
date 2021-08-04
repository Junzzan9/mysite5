<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css"
>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet"
	type="text/css"
>
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet"
	type="text/css"
>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"
></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"
></script>



</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name" value=""></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass" type="password" name="pass" value=""></td>
								</tr>
								<tr>
									<td colspan="4"><textarea id="input-content" name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center">
										<button id="btnSubmit" type="submit">등록</button>
									</td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">

					</form>

					<div id="listArea">
						<!--                   jquery 리스트 그리는 영역 -->

					</div>



				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
	<!-- //wrap -->




	<!--                                Delete Modal                                        -->
	<div id="delModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록 삭제</h4>
				</div>
				<div class="modal-body">
					<label for="modalPassword">비밀번호</label> <input id="modalPassword" type="password"
						name="password" value=""
					> <input type="hidden" name="no" value="">
				</div>
				<div class="modal-footer">
					<button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!--                                Delete Modal                                        -->





</body>

<script type="text/javascript">
	//화면 로딩되기 직전!
	$(document).ready(function() {
		console.log("화면 로딩 직전");

		//ajax 요청하기
		$.ajax({

			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: ”홍길동"},

			//dataType : "json",
			success : function(guestList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestList);

				//화면에 그리기
				for (var i = 0; i < guestList.length; i++) {

					render(guestList[i], "down"); //list draw

				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});

	$("#btnSubmit").on("click", function() {
		console.log("click")
		event.preventDefault();

		/*
		//getName
		var userName = $("#input-uname").val();
		//getPasswaod 
		var userPassword = $("#input-pass").val();
		//getContent
		var userContent = $("[name='content']").val();
		 */

		var guestbookVo = {
			name : $("#input-uname").val(),
			password : $("#input-pass").val(),
			content : $("[name='content']").val()
		};

		fetchList();

	});

	/* Delete Guestbook */

	$("#listArea").on("click", ".btnDel", function() {
		console.log("btnDel clicked")

		//hidden no input
		var no = $(this).data("no"); // data-no 읽어오기

		$("[name='no']").val(no);

		//reset password value
		$("#modalPassword").val("");

		//modal
		$("#delModal").modal();
	});
	/* Delete Guestbook */

	$("#modalBtnDel").on("click", function() {
		console.log("modalBtnDel clicked")

		var no = $("[name='no']").val();

		var guestbookVo = {
			no : $("[name='no']").val(),
			password : $("[name='password']").val()

		};
		console.log(guestbookVo);

		//request delete to server (param no,password)
		$.ajax({
			
			//request
			url : "${pageContext.request.contextPath }/api/guestbook/remove",
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,
			
			
			//response
			dataType : "json",
			success : function(count) {

				if (count === 1) {
					//close modal
					$("#delModal").modal("hide");

					//guestbook remove on html
					$("#t-" + no).remove();
				} else {

					//close modal
					$("#delModal").modal("hide");
				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
	
	
	function fetchList(){
		$.ajax({

			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "post",
			//contentType : "application/json",
			//data : {name: ”홍길동"},

			//dataType : "json",
			success : function(guestList) {
				/*성공시 처리해야될 코드 작성*/
				console.log(guestList);

				//화면에 그리기
				for (var i = 0; i < guestList.length; i++) {

					render(guestList[i], "down"); //list draw

				}

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	};

	function render(guestVo, type) {
		var str = "";
		str += '<table id="t-'+ guestVo.no +'" class="guestRead">';
		str += '   <colgroup>';
		str += '      <col style="width: 10%;">';
		str += '      <col style="width: 40%;">';
		str += '      <col style="width: 40%;">';
		str += '      <col style="width: 10%;">';
		str += '   </colgroup>';
		str += '   <tr>';
		str += '      <td>' + guestVo.no + '</td> ';
		str += '      <td>' + guestVo.name + '</td>';
		str += '      <td>' + guestVo.regDate + '</td>';
		str += '      <td><button class="btnDel" data-no="' + guestVo.no + '">[삭제]</button></td>';
		str += '   </tr> ';
		str += '   <tr> ';
		str += '      <td colspan=4 class="text-left">' + guestVo.content
				+ '</td> ';
		str += '   </tr>';
		str += '</table> ';
		if (type === "down") {
			$("#listArea").append(str);
		} else if (type === "up") {
			$("#listArea").prepend(str);
		} else {
			console.log("방향")
		}
	};
</script>


</html>