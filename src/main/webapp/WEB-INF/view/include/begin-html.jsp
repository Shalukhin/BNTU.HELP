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
<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> -->

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">


<link href='http://fonts.googleapis.com/css?family=Lobster'
	rel='stylesheet' type='text/css'>





<title>BNTU.HELP</title>
</head>

<body background="pict/background.gif">

	<br>

	<div class="container">
		<div class="row">
			<div class="col-md-1">
				<a href="view/index.html"> <img
					style="display: block; margin-left: auto; margin-right: auto;"
					src="pict/logo.gif" alt="">
				</a>
			</div>
			<div class="col-md-11" style="text-align: center;">
				<h1>Контрольные работы и помощь для студентов БНТУ</h1>
			</div>
		</div>
		<br>


		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
			<div
				class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item "><a
						class="nav-link ${chemistryMenuPosition}"
						href="do?command=chemistry&kindOfChemistry=inorganic">Химия</a></li>

					<li class="nav-item"><a class="nav-link"
						href="view/math/math.jsp">Математика</a></li>

					<li class="nav-item"><a class="nav-link"
						href="view/inj/inj.jsp">Инженерная графика</a></li>

					<li class="nav-item"><a class="nav-link"
						href="view/inform/inform.jsp">Информатика</a></li>

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
						href="do?command=sign&kindOfSign=login">Вход</a></li>
				</c:if>
				
				<c:if test="${user != null}">
					<li class="nav-item"><a class="nav-link ${accountMenuPosition}"
						href="do?command=account">${user.getLogin()}</a></li>
					<li class="nav-item"><a class="nav-link"
						href="do?command=logout">Выход</a></li>
				</c:if>
					
					<li class="nav-item"><a class="nav-link"
						href="view/oplata.jsp">Оплата</a></li>
					<li class="nav-item"><a
						class="nav-link ${contactMenuPosition}" href="do?command=contact">Контакты</a></li>
					<li class="nav-item"><a class="nav-link ${indexMenuPosition}"
						href="do?command=index">О нас</a></li>
				</ul>
			</div>
		</nav>