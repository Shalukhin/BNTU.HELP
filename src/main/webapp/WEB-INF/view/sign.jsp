<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include/begin-html.jsp"%>

<script type="text/javascript">	
<%@include file="js/loginAJAX/jquery-1.11.1.min.js"%>	
</script>

<script type="text/javascript">	
<%@include file="js/loginAJAX/script.js"%>	
</script>

<style>
.form-3 {
	font-family: 'Ubuntu', 'Lato', sans-serif;
	font-weight: 400;
	/* Size and position */
	width: 300px;
	position: relative;
	margin: 30px auto 30px;
	padding: 10px;
	overflow: hidden;
	/* Styles */
	background: #111;
	border-radius: 0.4em;
	border: 1px solid #191919;
	box-shadow: inset 0 0 2px 1px rgba(255, 255, 255, 0.08), 0 16px 10px
		-8px rgba(0, 0, 0, 0.6);
}

.form-3 label {
	/* Size and position */
	width: 50%;
	float: left;
	padding-top: 9px;
	/* Styles */
	color: #ddd;
	font-size: 12px;
	text-transform: uppercase;
	letter-spacing: 1px;
	text-shadow: 0 1px 0 #000;
	text-indent: 10px;
	font-weight: 700;
	cursor: pointer;
}

.form-3 input[type=text], .form-3 input[type=password] {
	/* Size and position */
	width: 50%;
	float: left;
	padding: 8px 5px;
	margin-bottom: 10px;
	font-size: 12px;
	/* Styles */
	background: linear-gradient(#1f2124, #27292c);
	border: 1px solid #000;
	box-shadow: 0 1px 0 rgba(255, 255, 255, 0.1);
	border-radius: 3px;
	/* Font styles */
	font-family: 'Ubuntu', 'Lato', sans-serif;
	color: #fff;
}

.form-3 input[type=text]:hover, .form-3 input[type=password]:hover,
	.form-3 label:hover ~ input[type=text], .form-3 label:hover ~ input[type=password]
	{
	background: #27292c;
}

.form-3 input[type=text]:focus, .form-3 input[type=password]:focus {
	box-shadow: inset 0 0 2px #000;
	background: #494d54;
	border-color: #51cbee;
	outline: none; /* Remove Chrome outline */
}

.form-3 p:nth-child(3), .form-3 p:nth-child(4) {
	float: left;
	width: 50%;
}

.form-3 label[for=remember] {
	width: auto;
	float: none;
	display: inline-block;
	text-transform: capitalize;
	font-size: 11px;
	font-weight: 400;
	letter-spacing: 0px;
	text-indent: 2px;
}

.form-3 input[type=checkbox] {
	margin-left: 10px;
	vertical-align: middle;
}

.form-3 input[type=submit] {
	/* Width and position */
	width: 100%;
	padding: 8px 5px;
	/* Styles */
	border: 1px solid #0273dd; /* Fallback */
	border: 1px solid rgba(0, 0, 0, 0.4);
	box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.3), inset 0 10px 10px
		rgba(255, 255, 255, 0.1);
	border-radius: 3px;
	background: #38a6f0;
	cursor: pointer;
	/* Font styles */
	font-family: 'Ubuntu', 'Lato', sans-serif;
	color: white;
	font-weight: 700;
	font-size: 15px;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.8);
}

.form-3 input[type=submit]:hover {
	box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.6);
}

.form-3 input[type=submit]:active {
	background: #287db5;
	box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.6);
	border-color: #000; /* Fallback */
	border-color: rgba(0, 0, 0, 0.9);
}

.no-boxshadow .form-3 input[type=submit]:hover {
	background: #2a92d8;
}

/* Gradient line */
.form-3:after {
	/* Size and position */
	content: "";
	height: 1px;
	width: 33%;
	position: absolute;
	left: 20%;
	top: 0;
	/* Styles */
	background: linear-gradient(left, transparent, #444, #b6b6b8, #444, transparent);
}

/* Small flash */
.form-3:before {
	/* Size and position */
	content: "";
	width: 8px;
	height: 5px;
	position: absolute;
	left: 34%;
	top: -7px;
	/* Styles */
	border-radius: 50%;
	box-shadow: 0 0 6px 4px #fff;
}

.form-3 p:nth-child(1):before {
	/* Size and position */
	content: "";
	width: 250px;
	height: 100px;
	position: absolute;
	top: 0;
	left: 45px;
	/* Styles */
	transform: rotate(75deg);
	background: linear-gradient(50deg, rgba(255, 255, 255, 0.15),
		rgba(0, 0, 0, 0));
	pointer-events: none;
}

.no-pointerevents .form-3 p:nth-child(1):before {
	display: none;
}

.login-text {
	text-align: center;
	margin: 20px;
	color: #515151;
	font-family: 'Lobster', cursive;
	font-size: x-large;
}
</style>


<br>
<!-- <ul class="nav nav-tabs justify-content-center" id="myTab" role="tablist"> -->
<ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">


	<li class="nav-item"><a class="nav-link ${login}" id="login-tab"
		data-toggle="tab" href="#login" role="tab" aria-controls="login"
		aria-selected="true">
			<h6>Войти</h6>
	</a></li>


	<li class="nav-item"><a class="nav-link ${registration}"
		id="profile-tab" data-toggle="tab" href="#profile" role="tab"
		aria-controls="profile" aria-selected="false">
			<h6>Регистрация</h6>
	</a></li>

</ul>

<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show ${login}" id="login" role="tabpanel"
		aria-labelledby="login-tab">

		<p class="login-text">Авторизация на сайте</p>

		<form class="form-3" action="do?command=login&kindOfAccount=welcome" method="post">
			<p class="clearfix">
				<label for="login">Логин</label> 
				<input type="text" name="login"	id="login" placeholder="Логин">
			</p>
			<p class="clearfix">
				<label for="password">Пароль</label> 
				<input type="password" name="password" id="password" placeholder="Пароль">
			</p>
			<p class="clearfix">
				<input type="checkbox" name="remember" id="remember"> <label
					for="remember">Запомнить меня</label>
			</p>
			<p class="clearfix">
				<input type="submit" name="submit" value="Войти">
			</p>
		</form>

		<p style="text-align: center;">${messageLogin}</p>

	</div>

	<div class="tab-pane fade show ${registration}" id="profile"
		role="tabpanel" aria-labelledby="profile-tab">
		
		<p class="login-text">Регистрация на сайте</p>
		
		<form class="form-3" action="do?command=registration&kindOfAccount=welcome" method="post">
			<p class="clearfix">
				<label for="login">Логин</label> 
				<input type="text" name="login"	id="loginAJAX" placeholder="Логин">
			</p>
			<p class="clearfix">
				<label for="password">Пароль</label> 
				<input type="password" name="password" id="password" placeholder="Пароль">
			</p>
			<p></p>

			<p class="clearfix">
				<input type="submit" name="submit" value="Регистрация">
			</p>
		</form>
		<div id="auth-info" align="center">${messageRegistration}</div>
		
		
	</div>

</div>


<b>${error}</b>

</div>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous">
	</script>
	
<script type="text/javascript">	
 <%@include file="js/bootstrap.min.js"%> 
</script>

</body>
</html>