<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> 

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
	
	

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
	
	-->
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
 

<link href='http://fonts.googleapis.com/css?family=Lobster'
	rel='stylesheet' type='text/css'>
	
<script>
function ChangerLanguageGUI() {
	document.getElementById("lang").submit();	
}
</script>

<style type="text/css">
h1 {
	font-size: 20px;
}

@media ( min-width : 576px) {
	h1 {
		font-size: 40px;
	}
}

/* скрываем чекбоксы и блоки с содержанием */
.hide, .hide+label ~ div {
	display: none;
}
/* вид текста label */
.hide+label, .hide:checked+label {
	padding: 0;
	color: green;
	cursor: pointer;
	border-bottom: 1px dotted green;
}
/* вид текста label при активном переключателе */
.hide:checked+label {
	color: red;
	border-bottom: 0;
}
/* когда чекбокс активен показываем блоки с содержанием  */
.hide:checked+label+div {
	display: block;
	background: #efefef;
	-moz-box-shadow: inset 3px 3px 10px #7d8e8f;
	-webkit-box-shadow: inset 3px 3px 10px #7d8e8f;
	box-shadow: inset 3px 3px 10px #7d8e8f;
	padding: 10px;
}
</style>



<title>BNTU.HELP</title>
</head>

<body background="pict/background.gif">

	<br>

	<div class="container">
		<div class="row">
			<div class="col-md-1">
				<a href="do?command=index"> <img
					style="display: block; margin-left: auto; margin-right: auto;"
					src="pict/logo.gif" alt="">
				</a>
			</div>
			<div class="col-md-10" style="text-align: center;">
			<!--  	<h1>Контрольные работы и помощь для студентов БНТУ</h1> -->
			<h1>${languageManager.getString("begin01")}<font color="red" face="cursive">.HELP</font> &#8212; ${languageManager.getString("begin02")}</h1>
			</div>
	<!--  	<div>
				<a href="do?command=chemistry&tab=inorganic"> <img
					style="display: block; margin-left: auto; margin-right: auto;"
					src="pict/logo2.png" alt="">
				</a>
			</div>  -->
		</div>
		<br>


		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
			<div
				class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
				<ul class="navbar-nav mr-auto">
				
					<li class="nav-item "><a
						class="nav-link ${chemistryMenuPosition}"
						href="do?command=chemistry&tab=inorganic">${languageManager.getString("begin03")}</a></li>

					<li class="nav-item"><a 
						class="nav-link ${mathMenuPosition}"
						href="do?command=math">${languageManager.getString("begin04")}</a></li>

					<li class="nav-item"><a 
					class="nav-link ${enginMenuPosition}"
						href="do?command=engin">${languageManager.getString("begin05")}</a></li>

					<li class="nav-item"><a 
					class="nav-link ${informaticMenuPosition}"
						href="do?command=informatic">${languageManager.getString("begin06")}</a></li>

					<!--<li class="nav-item">
                	<a class="nav-link" href="#">Link</a>
            		</li> -->
				</ul>
			</div>
			<div class="mx-auto order-0">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target=".dual-collapse2">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
				<ul class="navbar-nav ml-auto">

					<c:if test="${user == null}">
						<li class="nav-item"><a class="nav-link ${signMenuPosition}"
							href="do?command=sign&tab=login">${languageManager.getString("begin07")}</a></li>
					</c:if>

					<c:if test="${user != null}">
						<li class="nav-item"><a	class="nav-link ${accountMenuPosition}" 
							href="do?command=account&tab=welcome">${user.getLogin()}</a></li>
						
						<c:if test="${user.getRole().getNameRole() == \"admin\"}" >
							<li class="nav-item"><a class="nav-link ${adminMenuPosition}"
							href="do?command=administrator&tab=allUser">${languageManager.getString("begin08")}</a></li>						
						</c:if>	
							
						<li class="nav-item"><a class="nav-link"
							href="do?command=logout">${languageManager.getString("begin09")}</a></li>
					</c:if>

					<li class="nav-item"><a class="nav-link ${payMenuPosition}"
						href="do?command=pay">${languageManager.getString("begin10")}</a></li>
					<li class="nav-item"><a
						class="nav-link ${contactMenuPosition}" href="do?command=contact">${languageManager.getString("begin11")}</a></li>
					<li class="nav-item"><a class="nav-link ${aboutMenuPosition}"
						href="do?command=about">${languageManager.getString("begin12")}</a></li>
						
					<form id="lang" method="post" action="do?command=changelanguage"></form>

					<c:if test="${languageManager.getCurrentLanguage() == \"ru\" }" >
						<li class="nav-item"> <a onclick="ChangerLanguageGUI()" class="nav-link" href="#">En</a></li>
					</c:if>
					
					<c:if test="${languageManager.getCurrentLanguage() == \"en\" }" >
						<li class="nav-item"> <a onclick="ChangerLanguageGUI()" class="nav-link" href="#">Ru</a></li>
					</c:if> 					
					
				</ul>
			</div>
		</nav>