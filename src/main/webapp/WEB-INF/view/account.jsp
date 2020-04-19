<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include/begin-html.jsp"%>



<br>

<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item"><a class="nav-link ${welcome}" id="acc1-tab"
		data-toggle="tab" href="#acc1" role="tab" aria-controls="acc1"
		aria-selected="true"><h6>Приветствуем вас</h6></a></li>
	<li class="nav-item"><a class="nav-link ${order}" id="acc2-tab"
		data-toggle="tab" href="#acc2" role="tab" aria-controls="acc2"
		aria-selected="false"><h6>Сделать заказ</h6></a></li>
	<li class="nav-item"><a class="nav-link ${list}" id="acc3-tab"
		data-toggle="tab" href="#acc3" role="tab" aria-controls="acc3"
		aria-selected="false"><h6>Заказанные услуги</h6></a></li>
	<li class="nav-item"><a class="nav-link ${personal}" id="acc4-tab"
		data-toggle="tab" href="#acc4" role="tab" aria-controls="acc4"
		aria-selected="false"><h6>Личные данные</h6></a></li>
</ul>
<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show ${welcome}" id="acc1" role="tabpanel"
		aria-labelledby="acc1-tab">

		<br>

		<h4>
			<p style="text-align: center;">
				<strong>Приветствуем вас, уважаемый ${user.getLogin()}</strong>
			</p>
		</h4>

		<br> Это ваш личный кабинет. Здесь вы можете добавить или
		изменить личные данные, заказать и оплатить нужные вам контрольные
		работы, а так же просмотреть список всех заказанных вами работ!
		Ознакомиться с перечнем предлагаемых услуг, посмотреть примеры
		выполнения контрольных работ, а также узнать актуальные цены и сделать
		заказ вы сможете в соответствующих разделах сайта: Химия Математика
		Инженерная графика Информатика

	</div>


	<div class="tab-pane fade show ${order}" id="acc2" role="tabpanel"
		aria-labelledby="acc2-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Методические пособия</strong>
			</p>
		</h4>




	</div>

	<div class="tab-pane fade show ${list}" id="acc3" role="tabpanel"
		aria-labelledby="acc3-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Цены</strong>
			</p>
		</h4>
		<br>
		<p style="text-align: left;">
			Цена одной контрольной работы <span style="color: #0000ff;"><strong>15
					бел.руб.</strong></span>
		</p>


	</div>



	<div class="tab-pane fade show ${personal}" id="acc4" role="tabpanel"
		aria-labelledby="acc4-tab">
		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Личные данные</strong>
			</p>
		</h4>

		<br>
		<div class="col-md-5">
		<form action="do?command=personal&kindOfAccount=personal" method="post">
			
			<div class="form-group">
				<label for="name">Ваше имя:</label> 
				<input class="form-control" id="name" placeholder="Введите ваше имя" name="name" value="${personalData.getName()}"> 
			<!--<small class="form-text text-muted">
					We'll never	share your email with anyone else.
				</small>  -->
			</div>
			
			<div class="form-group">
				<label for="name">Ваш номер телефона:</label> 
				<input class="form-control" id="phone" placeholder="+375XXXXXXXXX" name="phone" value="${personalData.getPhone()}"> 
			<!--<small class="form-text text-muted">
					We'll never	share your email with anyone else.
				</small>  -->
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">Адрес электронной почты:</label> 
				<input
					type="email" class="form-control" id="exampleInputEmail1"
					aria-describedby="emailHelp" placeholder="Enter email" name="email" value="${personalData.getEmail()}"> 
				<!--	<small id="emailHelp" class="form-text text-muted">We'll never
					share your email with anyone else.</small>  -->
			</div>
			<!--<div class="form-group">
				<label for="exampleInputPassword1">Password</label> 
				<input
					type="password" class="form-control" id="exampleInputPassword1"
					placeholder="Password" name="pass">
			</div>
			  <div class="form-check">
				<input type="checkbox" class="form-check-input" id="exampleCheck1">
				<label class="form-check-label" for="exampleCheck1">Check me
					out</label>
			</div>  -->
			<button type="submit" class="btn btn-primary" name="submit" value="save">Сохранить</button>
		</form>
		<p style="text-align: center;">${messagePersonal}</p>
		</div>
		
		

	</div>
</div>

<%@ include file="include/end-html.jsp"%>