<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/HeaderTag.tld" prefix="headertag"%>
<%@ taglib uri="/WEB-INF/tld/NavTag.tld" prefix="navtag"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>KPU Board</title>
	<link rel="stylesheet" href="resources/css/bootstrap.css">
	<link rel="stylesheet" href="resources/css/app.css">
	<link rel="stylesheet" href="resources/css/perfect-scrollbar.css">
</head>
<body>
	<div id="app">
		<navtag:nav userSession="${empty userSession?'null':userSession}" menu="-1" bbsConfigFK="-1" />
		<div id="main" class="h-100">
			<headertag:header userSession="${empty userSession?'null':userSession}" />
			<section>
				<div class="d-flex justify-content-center">
					<div class="card col-8">
						<div class="card-header">
							<h4 class="card-title">회원가입</h4>
						</div>
						<div class="card-content">
							<div class="card-body">
								<form class="form form-vertical" action="MemberServlet?cmd=signup" method="post">
									<div class="form-body">
										<div class="row">
											<div class="col-12">
												<div class="form-group">
													<label for="first-name-vertical">ID</label>
													<input type="text" id="id" class="form-control" name="id" placeholder="ID">
												</div>
											</div>
											<div class="col-12">
												<div class="form-group">
													<label for="password-vertical">PASSWORD</label>
													<input type="password" id="passwd" class="form-control" name="passwd" placeholder="Password">
												</div>
											</div>
											<div class="col-12">
												<div class="form-group">
													<label for="first-name-vertical">USERNAME</label>
													<input type="text" id="username" class="form-control" name="username" placeholder="Username">
												</div>
											</div>
											<div class="col-12">
												<div class="form-group">
													<label for="first-name-vertical">STUDENT NUMBER</label>
													<input type="text" id="snum" class="form-control" name="snum" placeholder="Student Number">
												</div>
											</div>
											<div class="col-12">
												<div class="form-group">
													<label for="email-id-vertical">EMAIL</label>
													<input type="email" id="email" class="form-control" name="email" placeholder="Email">
												</div>
											</div>
											<div class="col-12">
												<div class="form-group">
													<label for="first-name-vertical">DEPARTMENT</label>
													<select class="form-select" id="depart" name="depart">
														<option>게임공학부</option>
														<option>경영학부</option>
														<option>기계공학과</option>
														<option>기계설계공학과</option>
														<option>나노반도체공학과</option>
														<option>디자인학부</option>
														<option>메카트로닉스공학과</option>
														<option>생명화학공학과</option>
														<option>신소재공학과</option>
														<option>에너지,전기공학과</option>
														<option>전자공학부</option>
														<option selected>컴퓨터공학부</option>
													</select>
												</div>
											</div>
											<div class="col-12 d-flex justify-content-end">
												<button type="submit" class="btn btn-primary mt-2 mr-1 mb-4">Submit</button>
												<button type="reset" class="btn btn-light-secondary mt-2 mr-1 mb-4">Reset</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
	<script src="resources/js/feather.min.js"></script>
	<script src="resources/js/perfect-scrollbar.min.js"></script>
	<script src="resources/js/app.js"></script>
	<script src="resources/js/main.js"></script>
</body>
</html>