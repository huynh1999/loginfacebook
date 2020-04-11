<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title><dec:title /></title>
<dec:head />
<link href="<c:url value='/template/web/css/bootstrap.min.css' />"
	rel="stylesheet" type="text/css">
<link href="<c:url value='/template/web/css/font-awesome.min.css' />"
	rel="stylesheet" type="text/css">
<link href="<c:url value='/template/web/css/prettyPhoto.css' />"
	rel="stylesheet" type="text/css">
<link href="<c:url value='/template/web/css/price-range.css' />"
	rel="stylesheet" type="text/css">
<link href="<c:url value='/template/web/css/animate.css' />"
	rel="stylesheet" type="text/css">
<link href="<c:url value='/template/web/css/main.css' />"
	rel="stylesheet" type="text/css">
<link href="<c:url value='/template/web/css/responsive.css' />"
	rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon"
	href="<c:url value='/template/web/images/ico/favicon.ico' />">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value='/template/web/images/ico/apple-touch-icon-144-precomposed.png' />">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value='/template/web/images/ico/apple-touch-icon-114-precomposed.png' />">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value='/template/web/images/ico/apple-touch-icon-72-precomposed.png' />">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value='/template/web/images/ico/apple-touch-icon-57-precomposed.png' />">
<script src="<c:url value='/template/web/js/jquery.js' />"></script>
<script src="<c:url value='/template/web/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/template/web/js/jquery.scrollUp.min.js' />"></script>
<script src="<c:url value='/template/web/js/price-range.js' />"></script>
<script src="<c:url value='/template/web/js/jquery.prettyPhoto.js'/>"></script>
<script src="<c:url value='/template/web/js/main.js' />"></script>

</head>
<body>
	<!-- header -->
	<%@include file="/common/web/header.jsp"%>


	<dec:body />

	<!-- Footer -->
	<%@include file="/common/web/footer.jsp"%>


</body>
</html>