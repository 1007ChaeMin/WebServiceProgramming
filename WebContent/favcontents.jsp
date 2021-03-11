<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tld/HeaderTag.tld" prefix="headertag" %>
<%@ taglib uri="/WEB-INF/tld/NavTag.tld" prefix="navtag" %>
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
    	<navtag:nav userSession="${empty userSession?'null':userSession}" menu="2"  bbsConfigFK="-1"/>
        <div id="main">
        	<headertag:header userSession="${empty userSession?'null':userSession}"/>
			<div class="main-content container-fluid">
				<div class="page-title">
					<h3>즐겨찾기</h3>
				</div>
				<section class="section">
					<div class="row mb-3">
						<div class="col-md-12">
							<div class="card">
								<div class="card-body px-0 pb-0">
									<div class="table-responsive">
										<table class="table mb-0" id="table1">
											<thead>
												<tr align="center">
													<th></th>
													<th>제목</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${favlist}" varStatus="status">
													<tr>
														<td align="center">
															<form name="fav${status.index}" action="FavcontentsServlet?cmd=favdel" method="post">
																<input type="hidden" name="userSession" value="${item.id}">
																<input type="hidden" name="menu" value="${item.menu}">
																<input type="hidden" name="pkid" value="${item.pkid}">
																<input type="hidden" name="bbsConfigFK" value="${item.bbsConfigFK}">
																<input type="hidden" name="title" value="${item.title}">
																<input type="image" src="resources/css/icons/staractive.svg" name="submit" value="submit">
															</form>
														</td>
														<td><a href="content.jsp?menu=${item.menu}&pkid=${item.pkid}&bbsConfigFK=${item.bbsConfigFK}">${item.title}</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
        </div>
    </div>
    <script src="resources/js/feather.min.js"></script>
    <script src="resources/js/perfect-scrollbar.min.js"></script>
    <script src="resources/js/app.js"></script>
    <script src="resources/js/main.js"></script>
</body>
</html>