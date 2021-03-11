<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/BoardTag.tld" prefix="boardtag" %>
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
    	<navtag:nav userSession="${empty userSession?'null':userSession}" menu="${empty param.menu?'0':param.menu}" bbsConfigFK="${empty param.bbsConfigFK?'1':param.bbsConfigFK}"/>
        <div id="main">
        	<headertag:header userSession="${empty userSession?'null':userSession}"/>
			<boardtag:board userSession="${empty userSession?'null':userSession}" menu="${empty param.menu?'0':param.menu}" bbsConfigFK="${empty param.bbsConfigFK?'1':param.bbsConfigFK}" currentPage="${empty param.currentPage?'1':param.currentPage}"/>
        </div>
    </div>
    <script src="resources/js/feather.min.js"></script>
    <script src="resources/js/perfect-scrollbar.min.js"></script>
    <script src="resources/js/app.js"></script>
    <script src="resources/js/main.js"></script>
</body>
</html>