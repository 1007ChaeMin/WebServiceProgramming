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
		<navtag:nav userSession="${empty userSession?'null':userSession}" menu="-1"  bbsConfigFK="-1"/>
		<div id="main">
			<headertag:header userSession="${empty userSession?'null':userSession}"/>
			<section class="my-5">
				<div class="row justify-content-center">
					<div class="col-8">
						<div class="card">
							<div class="card-header">
								<h1 class="text-center mt-5">${message}</h1>
							</div>
							<div class="card-body">
								<div class="d-flex justify-content-center mb-2">
									<a href="index.jsp"><button type="button" class="btn btn-primary btn-lg round mb-5">메인으로</button></a>
								</div>
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