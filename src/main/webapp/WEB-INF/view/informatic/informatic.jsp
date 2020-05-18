<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>

<br>

<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item"><a class="nav-link active" id="inf1-tab"
		data-toggle="tab" href="#inf1" role="tab" aria-controls="inf1"
		aria-selected="true"><h6>${languageManager.getString("inform01")}</h6></a></li>
	<li class="nav-item"><a class="nav-link " id="inf2-tab"
		data-toggle="tab" href="#inf2" role="tab" aria-controls="inf2"
		aria-selected="false"><h6>${languageManager.getString("inform02")}</h6></a></li>
	<li class="nav-item"><a class="nav-link" id="inf3-tab"
		data-toggle="tab" href="#inf3" role="tab" aria-controls="inf3"
		aria-selected="false"><h6>${languageManager.getString("inform03")}</h6></a></li>
	<li class="nav-item"><a class="nav-link" id="exam-tab"
		data-toggle="tab" href="#exam" role="tab" aria-controls="exam"
		aria-selected="false"><h6>${languageManager.getString("inform04")}</h6></a></li>
</ul>
<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show active" id="inf1" role="tabpanel"
		aria-labelledby="inf1-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("inform05")}</strong>
			</p>
		</h4>

		<br>
		<table border="0">
			<tr>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=informatic&example=1"><img alt=""
							src="pict/informatic/informatic_example1_mini.png"></a><br>
						<a href="do?command=informatic&example=1">(${languageManager.getString("inform06")})</a>
					</p>
				</td>
				<td width="55"></td>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=informatic&example=2"><img alt=""
							src="pict/informatic/informatic_example2_mini.png"></a><br>
						<a href="do?command=informatic&example=2">(${languageManager.getString("inform06")})</a>
					</p>
				</td>
				<td width="55"></td>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=informatic&example=3"><img alt=""
							src="pict/informatic/informatic_example3_mini.png"></a><br>
						<a href="do?command=informatic&example=3">(${languageManager.getString("inform06")})</a>
					</p>
				</td>
			</tr>

		</table>

	</div>


	<div class="tab-pane fade" id="inf2" role="tabpanel"
		aria-labelledby="inf2-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("inform07")}</strong>
			</p>
		</h4>

		<p>«Методические указания и индивидуальные задания для выполнения
			контрольной работы по дисциплине «Информатика» для студентов заочной
			формы обучения специальности "Промышленное и гражданское
			строительство" Часть 1. Язык программирования Delphi. Электронный
			учебный материал. Автор А.В. Стрелюхин. Минск 2019</p>
		<p style="text-align: center;">
			<a href="do?command=informatic&textbook=true" target="_blank"><img alt=""
				src="pict/informatic/informatic_textbook_mini.png"></a>
		</p>

		<hr align="left" width="980" size="4" color="#0000dd">

		<p style="text-align: left;">
			<b>${languageManager.getString("inform08")}</b>
		</p>

	</div>

	<div class="tab-pane fade" id="inf3" role="tabpanel"
		aria-labelledby="inf3-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("inform09")}</strong>
			</p>
		</h4>
		<br>
		<p style="text-align: left;">
			${languageManager.getString("inform10")}
		</p>

	</div>



	<div class="tab-pane fade" id="exam" role="tabpanel"
		aria-labelledby="exam-tab">
		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("inform11")}</strong>
			</p>
		</h4>

		<br>
		<p>${languageManager.getString("inform12")}</p>
		<p style="text-align: left;">
			${languageManager.getString("inform13")}
		</p>

	</div>
</div>

<%@ include file="../include/end-html.jsp"%>