<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="org.jsoup.Jsoup, org.jsoup.nodes.Document, org.jsoup.nodes.Element, org.jsoup.select.Elements" %>
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
    	<navtag:nav userSession="${empty userSession?'null':userSession}" menu="${empty param.menu?'0':param.menu}"  bbsConfigFK="${empty param.bbsConfigFK?'1':param.bbsConfigFK}"/>
        <div id="main">
        	<headertag:header userSession="${empty userSession?'null':userSession}"/>
			<%
				String menu = (String)request.getParameter("menu");
				String bbsConfigFK = (String)request.getParameter("bbsConfigFK");
				String pkid = (String)request.getParameter("pkid");
				if(menu == null) {
					menu = "0";
				}
				if(bbsConfigFK == null) {
					bbsConfigFK = "1";
				}
				String url = "";
				String title = "";
				StringBuffer content = new StringBuffer();
				String content_str = "";
				StringBuffer file = new StringBuffer();
				String file_str = "";
				if(menu.equals("0")) {
					url = "http://www.kpu.ac.kr/front/boardview.do?pkid="+pkid+"&bbsConfigFK="+bbsConfigFK;
				}else if (menu.equals("1")) {
					url = "http://subweb.kpu.ac.kr/front/boardview.do?pkid="+pkid+"&bbsConfigFK="+bbsConfigFK;
				}
				try {
					Document doc = Jsoup.connect(url).get();
					title = doc.select("tbody .subject").text();
					content.append(doc.select(".article").toString());
					content_str = content.toString();
					if(menu.equals("0")) {
						content_str = content_str.replace("/dataview", "http://www.kpu.ac.kr/dataview");
					}else if (menu.equals("1")) {
						content_str = content_str.replace("/dataview", "http://subweb.kpu.ac.kr/dataview");
					}
					Elements file_elements = doc.select(".file");
					if(file_elements != null) {
						file.append("<div class=\"card\">");
						file.append("<ul class=\"list-group list-group-flush\">");
						for(Element file_element : file_elements) {
							file.append("<li class=\"list-group-item\">");
							file.append(file_element);
							file.append("</li>");
						}
						file.append("</ul>");
						file.append("</div>");
						file_str = file.toString();
						if(menu.equals("0")) {
							file_str = file_str.replace("/Download", "http://www.kpu.ac.kr/Download");
						}else if (menu.equals("1")) {
							file_str = file_str.replace("/Download", "http://subweb.kpu.ac.kr/Download");
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			%>
			<div class="main-content container-fluid">
				<section class="section">
					<div class="row mb-3">
						<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<h4><%=title %></h4>
								</div>
								<div class="p-1 mb-2 bg-primary"></div>
								<div class="card-body px-4 pb-0">
									<%=file_str %>
									<div class="card">
										<div class="card-body px-3 pb-0">
											<p class="card-text">
												<%=content_str %>
											</p>
										</div>
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