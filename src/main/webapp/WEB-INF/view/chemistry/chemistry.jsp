<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>

<br>

<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item"><a class="nav-link ${inorganic}"
		id="inorganic-tab" data-toggle="tab" href="#inorganic" role="tab"
		aria-controls="inorganic" aria-selected="false"><h6>Общая и
				неорганическая химия</h6></a></li>
	<li class="nav-item"><a class="nav-link ${organic}"
		id="organic-tab" data-toggle="tab" href="#organic" role="tab"
		aria-controls="organic" aria-selected="false"><h6>Органическая
				химия</h6></a></li>
	<li class="nav-item"><a class="nav-link ${analitic}"
		id="analitic-tab" data-toggle="tab" href="#analitic" role="tab"
		aria-controls="analitic" aria-selected="false"><h6>Аналитическая
				химия</h6></a></li>
	<li class="nav-item"><a class="nav-link ${examChemistry}"
		id="examChemistry-tab" data-toggle="tab" href="#examChemistry"
		role="tab" aria-controls="examChemistry" aria-selected="false"><h6>Экзамен</h6></a></li>
</ul>
<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show ${inorganic}" id="inorganic"
		role="tabpanel" aria-labelledby="inorganic-tab">
		<br>

		<div align="right" style="margin-bottom: -32px;">
			<form action="do?command=account&tab=order" method=POST>

				<input name="taskForOrder" value="Контрольная работа по общей химии"
					hidden>

				<textarea name="noteForOrder" hidden>Методичка: «Программа и контрольные задания по химии. Методические указания». Минск 2006. Под редакцией В.Н. Яглова.

Номера заданий: 


Контактный номер: 

</textarea>

				<textarea name="instructionForOrder" hidden>⬅  Напишите номер вашего варианта, либо перечень номеров нужных вам заданий.
⬅  Укажите ваш контактный номер (если не заполнены "личные данные")
⬆️  Нажмите кнопку "Заказать"</textarea>

				<button id="Update" value="toOrder" name="toOrder" align="center"
					class="btn btn-success">Перейти к заказу</button>
			</form>
		</div>

		<div align="center">
			<h4>
				<p>
					<strong> Общая и неорганическая химия.</strong>
				</p>
			</h4>
		</div>

		<p>Методичка: «Программа и контрольные задания по химии.
			Методические указания». Минск 2006. Под редакцией В.Н. Яглова.</p>
		<p style="text-align: center;">
			<a
				href="do?command=chemistry&tab=inorganic&textbook=true"><img
				alt="" src="pict/chemistry/inorganic/met1_bntu_inorg_mini.png"></a>
		</p>
		<p style="text-align: left;">
			Контрольная работа состоит <strong> из 12 заданий </strong>. Номер
			варианта и входящие в него задания определяет преподаватель.
		</p>

		<p align="left">
			Цена данной контрольной работы: <span style="color: #0000ff;"><strong>12
					бел.руб.</strong></span>
		</p>
		<p align="left">
			Цена одного задания (при заказе поштучно): <span
				style="color: #0000ff;"><strong>1,5 бел.руб.</strong></span>
		</p>

		<p style="text-align: left;">
			Пример решения:<a
				href="download?fileName=material/chemistry/inorg_task_5-22.pdf"
				target="_blank" style="text-decoration: none;"> Задание 5.22</a>
		</p>

	</div>

	<div class="tab-pane fade show ${organic}" id="organic" role="tabpanel"
		aria-labelledby="organic-tab">

		<br>
		<div align="right" style="margin-bottom: -32px;">
			<form action="do?command=account&tab=order" method=POST>

				<input name="taskForOrder" value="Контрольная работа по органической химии"
					hidden>

				<textarea name="noteForOrder" hidden>Методичка: «Органическая химия. Учебно-методическое пособие». Минск 2005. Л.М. Слепнева.

Номера заданий: 


Контактный номер: 

</textarea>

				<textarea name="instructionForOrder" hidden>⬅  Напишите номер вашего варианта, либо перечень номеров нужных вам заданий.
⬅  Укажите ваш контактный номер (если не заполнены "личные данные")
⬆️  Нажмите кнопку "Заказать"</textarea>

				<button id="Update" value="toOrder" name="toOrder" align="center"
					class="btn btn-success">Перейти к заказу</button>
			</form>
		</div>

		<div align="center">
			<h4>
				<p>
					<strong> Органическая химия.</strong>
				</p>
			</h4>
		</div>
		
		<p>Методичка: «Органическая химия. Учебно-методическое пособие».
			Минск 2005. Л.М. Слепнева.</p>
		<p style="text-align: center;">
			<a href="do?command=chemistry&tab=organic&textbook=true"><img
				alt="" src="pict/chemistry/organic/met1_bntu_org_mini.png"></a>
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
			Пример решения:<a
				href="download?fileName=material/chemistry/org_task_9-3.pdf"
				target="_blank" style="text-decoration: none;"> Задание 9.3</a>
		</p>

	</div>

	<div class="tab-pane fade show ${analitic}" id="analitic"
		role="tabpanel" aria-labelledby="analitic-tab">

		<br>
		
		<div align="right" style="margin-bottom: -32px;">
			<form action="do?command=account&tab=order" method=POST>

				<input name="taskForOrder" value="Контрольная работа по аналитической химии"
					hidden>

				<textarea name="noteForOrder" hidden>Методичка: «АНАЛИТИЧЕСКАЯ ХИМИЯ». Учебно-методическое пособие. Минск 2010. С.В. Дорожко, Н.Ф. Макаревич.

Номера заданий: 


Контактный номер: 

</textarea>

				<textarea name="instructionForOrder" hidden>⬅  Напишите номер вашего варианта, либо перечень номеров нужных вам заданий.
⬅  Укажите ваш контактный номер (если не заполнены "личные данные")
⬆️  Нажмите кнопку "Заказать"</textarea>

				<button id="Update" value="toOrder" name="toOrder" align="center"
					class="btn btn-success">Перейти к заказу</button>
			</form>
		</div>

		<div align="center">
			<h4>
				<p>
					<strong> Аналитическая химия.</strong>
				</p>
			</h4>
		</div>
		
		<p>Методичка: «АНАЛИТИЧЕСКАЯ ХИМИЯ». Учебно-методическое пособие. Минск 2010. С.В. Дорожко, Н.Ф. Макаревич.</p>
		<p style="text-align: center;">
			<a href="do?command=chemistry&tab=analitic&textbook=true"><img
				alt="" src="pict/chemistry/analitic/met1_bntu_analit_mini.png"></a>
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
			Пример решения:<a
				href="download?fileName=material/chemistry/analit_task_4_60.pdf"
				target="_blank" style="text-decoration: none;"> Задание 4.60</a>
		</p>

	</div>

	<div class="tab-pane fade show ${examChemistry}" id="examChemistry"
		role="tabpanel" aria-labelledby="examChemistry-tab">

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