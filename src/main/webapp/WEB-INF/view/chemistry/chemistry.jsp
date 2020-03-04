<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>

<br>

<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item"><a class="nav-link ${Inorganic}" id="home-tab"
		data-toggle="tab" href="#home" role="tab" aria-controls="home"
		aria-selected="true"><h6>Общая и неорганическая химия</h6></a></li>
	<li class="nav-item"><a class="nav-link ${Organic}" id="profile-tab"
		data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
		aria-selected="false"><h6>Органическая химия</h6></a></li>
	<li class="nav-item"><a class="nav-link ${Analitic}" id="contact-tab"
		data-toggle="tab" href="#contact" role="tab" aria-controls="contact"
		aria-selected="false"><h6>Аналитическая химия</h6></a></li>
	<li class="nav-item"><a class="nav-link ${ExamChemistry}" id="contact-tab"
		data-toggle="tab" href="#exam" role="tab" aria-controls="exam"
		aria-selected="false"><h6>Экзамен</h6></a></li>
</ul>
<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show active" id="home" role="tabpanel"
		aria-labelledby="home-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Общая и неорганическая химия.</strong>
			</p>
		</h4>
		<p>Методичка: «Программа и контрольные задания по химии.
			Методические указания». Минск 2006. Под редакцией В.Н. Яглова.</p>
		<p style="text-align: center;">
			<a href="do?command=inorganic"><img alt=""
				src="pict/chemistry/inorganic/met1_bntu_inorg_mini.png"></a>
		</p>
		<p style="text-align: left;">
			Контрольная работа состоит <strong> из 12 заданий </strong>. Номер
			варианта и входящие в него задания определяет преподаватель.
		</p>
		<p style="text-align: left;">
			Цена данной контрольной работы: <span style="color: #0000ff;"><strong>12
					бел.руб.</strong></span>
		</p>
		<p style="text-align: left;">
			Цена одного задания (при заказе поштучно): <span
				style="color: #0000ff;"><strong>1,5 бел.руб.</strong></span>
		</p>
		<p style="text-align: left;">
			Пример решения:<a href="download?fileName=met1_bntu_inorg_task_5-22.pdf"
				target="_blank" style="text-decoration: none;"> Задание 5.22</a>
		</p>

	</div>

	<div class="tab-pane fade" id="profile" role="tabpanel"
		aria-labelledby="profile-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Органическая химия.</strong>
			</p>
		</h4>
		<p>Методичка: «Органическая химия. Учебно-методическое пособие».
			Минск 2005. Л.М. Слепнева.</p>
		<p style="text-align: center;">
			<a href="organ/him_organ.html"><img alt=""
				src="organ/met1_bntu_org_mini.png"></a>
		</p>
		<p style="text-align: left;">
			Контрольная работа состоит <strong> из 19 заданий </strong>. Номер
			варианта и входящие в него задания определяет преподаватель.
		</p>
		<p style="text-align: left;">
			Цена данной контрольной работы: <span style="color: #0000ff;"><strong>30
					бел.руб.</strong></span>
		</p>
		<p style="text-align: left;">
			Цена одного задания (при заказе поштучно): <span
				style="color: #0000ff;"><strong>2 бел.руб.</strong></span>
		</p>
		<p style="text-align: left;">
			Пример решения:<a href="organ/met1_bntu_org_Zad_9-3.pdf"
				target="_blank" style="text-decoration: none;"> Задание 9.3</a>
		</p>

	</div>
	<div class="tab-pane fade" id="contact" role="tabpanel"
		aria-labelledby="contact-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Аналитическая химия.</strong>
			</p>
		</h4>
		<p>Методичка: "АНАЛИТИЧЕСКАЯ ХИМИЯ". Учебно-методическое пособие.
			Минск 2010. С.В.</p>
		<p style="text-align: center;">
			<a href="analit/him_analit.html"><img alt=""
				src="analit/met_bntu_analit_mini.png"></a>
		</p>
		<p style="text-align: left;">
			Контрольная работа состоит <strong> из 18 заданий </strong>. Номер
			варианта и входящие в него задания определяет преподаватель.
		</p>
		<p style="text-align: left;">
			Цена данной контрольной работы: <span style="color: #0000ff;"><strong>30
					бел.руб.</strong></span>
		</p>
		<p style="text-align: left;">
			Цена одного задания (при заказе поштучно): <span
				style="color: #0000ff;"><strong>2 бел.руб.</strong></span>
		</p>
		<p style="text-align: left;">
			Пример решения:<a href="analit/met_bntu_analit_Zad_4_60.pdf"
				target="_blank" style="text-decoration: none;"> Задание 4.60</a>
		</p>

	</div>

	<div class="tab-pane fade" id="exam" role="tabpanel"
		aria-labelledby="contact-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Экзамен.</strong>
			</p>
		</h4>
		<br>
		<p>Даём консультации и оказываем online-помощь на экзаменах и
			зачётах. Подробности уточняйте по телефону или viber.</p>
		<p style="text-align: left;">
			Цена консультации, в случае успешной сдачи экзамена (зачёта) <span
				style="color: #0000ff;"><strong>25 бел.руб.</strong></span>
		</p>


	</div>
</div>


<%@ include file="../include/end-html.jsp"%>