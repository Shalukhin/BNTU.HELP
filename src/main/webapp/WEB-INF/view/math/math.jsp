<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>

<br>

<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item"><a class="nav-link active" id="math1-tab"
		data-toggle="tab" href="#math1" role="tab" aria-controls="math1"
		aria-selected="true"><h6>${languageManager.getString("math01")}</h6></a></li>
	<li class="nav-item"><a class="nav-link " id="math2-tab"
		data-toggle="tab" href="#math2" role="tab" aria-controls="math2"
		aria-selected="false"><h6>${languageManager.getString("math02")}</h6></a></li>
	<li class="nav-item"><a class="nav-link" id="math3-tab"
		data-toggle="tab" href="#math3" role="tab" aria-controls="math3"
		aria-selected="false"><h6>${languageManager.getString("math03")}</h6></a></li>
	<li class="nav-item"><a class="nav-link" id="exam-tab"
		data-toggle="tab" href="#exam" role="tab" aria-controls="exam"
		aria-selected="false"><h6>${languageManager.getString("math04")}</h6></a></li>
</ul>

<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show active" id="math1" role="tabpanel"
		aria-labelledby="math1-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("math05")}</strong>
			</p>
		</h4>

		<br>
		<table border="0">
			<tr>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=math&example=1"><img alt=""
							src="pict/math/math_example1_mini.jpg"></a><br> <a
							href="do?command=math&example=1">(${languageManager.getString("math06")})</a>
					</p>
				</td>
				<td width="55"></td>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=math&example=2"><img alt=""
							src="pict/math/math_example2_mini.jpg"></a><br> <a
							href="do?command=math&example=2">(${languageManager.getString("math06")})</a>
					</p>
				</td>
				<td width="55"></td>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=math&example=3"><img alt=""
							src="pict/math/math_example3_mini.jpg"></a><br> <a
							href="do?command=math&example=3">(${languageManager.getString("math06")})</a>
					</p>
				</td>
			</tr>


		</table>

	</div>


	<div class="tab-pane fade" id="math2" role="tabpanel"
		aria-labelledby="math2-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("math07")}</strong>
			</p>
		</h4>

		<div class="list">
			<input type="checkbox" id="hd-1" class="hide" /> <label for="hd-1">2000</label>
			<div>
				<p>
					ВЫСШАЯ МАТЕМАТИКА. Программа, методические указания и контрольные
					задания для студентов-заочников инженерных и
					инженерно-экономических специальностей приборостроительного
					факультета. В 2-х частях. Часть I. Минск 2000 <a
						href="download?fileName=material/math/vm4_kr_1_tit.pdf">${languageManager.getString("math08")}</a>
				</p>
			</div>

			<br /> <input type="checkbox" id="hd-2" class="hide" /> <label
				for="hd-2">2001</label>
			<div>
				<p>
					ВЫСШАЯ МАТЕМАТИКА. Программа, методические указания и контрольные
					задания для студентов-заочников инженерных и
					инженерно-экономических специальностей приборостроительного
					факультета. В 2-х частях. Часть II. Минск 2001 <a
						href="download?fileName=material/math/vm4_kr_2_tit.pdf">${languageManager.getString("math08")}</a>
				</p>
			</div>

			<br /> <input type="checkbox" id="hd-3" class="hide" /> <label
				for="hd-3">2002</label>
			<div>
				<p>
					ВЫСШАЯ МАТЕМАТИКА. Методические указания и контрольные задания для
					студентов-заочников энергетических и инженерно-педагогических
					специальностей. 2-е издание. Под общей редакцией Н.П.Кеда. Минск
					2002 <a href="download?fileName=material/math/vm2_kr1_1_tit.pdf">${languageManager.getString("math08")}</a>
				</p>
			</div>
			<br /> <input type="checkbox" id="hd-4" class="hide" /> <label
				for="hd-4">2007</label>
			<div>
				<p>
					Методические указания и задания к контрольной работе № 2 по высшей
					математике для студентов-заочников I курса ФТУГ. Минск 2007 <a
						href="download?fileName=material/math/vm2_kr2_1_tit.pdf">${languageManager.getString("math08")}</a>
				</p>
			</div>

			<br /> <input type="checkbox" id="hd-5" class="hide" /> <label
				for="hd-5">2010</label>
			<div>
				<p>
					Методические указания и задания к контрольной работе № 1 по высшей
					математике для студентов заочного отделения ФТУГ экономических
					специальностей. Минск 2010 <a
						href="download?fileName=material/math/vm2_kr1_2_tit.pdf">${languageManager.getString("math08")}</a>
				</p>
				<hr>
				<p>
					Методические указания и задания к контрольной работе № 2 по высшей
					математике для студентов заочного отделения ФТУГ экономических
					специальностей. Минск БНТУ 2010 <a
						href="download?fileName=material/math/vm2_kr2_2_tit.pdf">${languageManager.getString("math08")}</a>
				</p>
			</div>

			<br /> <input type="checkbox" id="hd-6" class="hide" /> <label
				for="hd-6">2011</label>
			<div>
				<p>
					Контрольная работа № 1 для студентов инженерно-технических
					специальностей заочной формы обучения. Методические указания и
					индивидуальные задания. Минск 2011 <a
						href="download?fileName=material/math/vm1_kr1_tit.pdf">${languageManager.getString("math08")}</a>
				</p>
				<hr>
				<p>
					Методические указания по выполнению контрольной работы № 2 по
					математике для студентов инженерно-технических специальностей
					заочной формы обучения. Минск 2011 <a
						href="download?fileName=material/math/vm1_kr2_tit.pdf">${languageManager.getString("math08")}</a>
				</p>
			</div>

			<br /> <input type="checkbox" id="hd-7" class="hide" /> <label
				for="hd-7">2012</label>
			<div>
				<p>
					Контрольная работа № 4 для студентов машиностроительных
					специальностей заочной формы обучения. Методические указания и
					индивидуальные задания. Учебное электронное издание. Минск 2012 <a
						href="download?fileName=material/math/vm1_kr4_tit.pdf">${languageManager.getString("math08")}</a>
				</p>
				<hr>
				<p>
					МЕТОДИЧЕСКИЕ УКАЗАНИЯ И ЗАДАНИЯ К КОНТРОЛЬНОЙ РАБОТЕ № 2 ПО ВЫСШЕЙ
					МАТЕМАТИКЕ для студентов заочного отделения ФТУГ экономических
					специальностей. Минск БНТУ 2012 <a
						href="download?fileName=material/math/vm2_kr2_3_tit.pdf">${languageManager.getString("math08")}</a>
				</p>

			</div>

			<br /> <input type="checkbox" id="hd-8" class="hide" /> <label
				for="hd-8">2013</label>
			<div>
				<p>
					Методические указания и контрольные задания по высшей математике
					для студентов заочного отделения ФТУГ (I курс) специальностей 1-26
					02 02 «Менеджмент» и 1-25 01 07 «Экономика и управление на
					предприятии». Минск БНТУ 2013 <a
						href="download?fileName=material/math/vm2_kr1_3_tit.pdf">${languageManager.getString("math08")}</a>
				</p>

			</div>

			<br /> <input type="checkbox" id="hd-9" class="hide" /> <label
				for="hd-9">${languageManager.getString("math09")}</label>
			<div>
				<p>
					1) Контрольная работа. <a
						href="download?fileName=material/math/vm1_kr3_tit.pdf">${languageManager.getString("math08")}</a>
				</p>
				<hr>
				<p>
					2) Контрольная работа. <a
						href="download?fileName=material/math/vm3_kr_tit.pdf">${languageManager.getString("math08")}</a>
				</p>

			</div>
		</div>

		<hr align="left" width="980" size="4" color="#0000dd">

		<p style="text-align: left;">
			<b>${languageManager.getString("math10")}</b>
		</p>

	</div>

	<div class="tab-pane fade" id="math3" role="tabpanel"
		aria-labelledby="math3-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("math11")}</strong>
			</p>
		</h4>
		<br>
		<p style="text-align: left;">
			${languageManager.getString("math12")}
		</p>
		<p style="text-align: left;">
			${languageManager.getString("math13")}
		</p>

	</div>
	

	<div class="tab-pane fade" id="exam" role="tabpanel"
		aria-labelledby="exam-tab">
		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("math14")}</strong>
			</p>
		</h4>

		<br>
		<p>${languageManager.getString("math15")}</p>
			
		<h5><p style="text-align: center;">${languageManager.getString("math16")}</p></h5>
		<p style="text-align: center;">
		<span><strong>2 ${languageManager.getString("math17")} - </strong></span> <span style="color: #0000ff;"><strong>15 ${languageManager.getString("math19")}</strong></span><br>
		<span><strong>3 ${languageManager.getString("math17")} - </strong></span> <span style="color: #0000ff;"><strong>20 ${languageManager.getString("math19")}</strong></span><br>
		<span><strong>4 ${languageManager.getString("math17")} - </strong></span> <span style="color: #0000ff;"><strong>25 ${languageManager.getString("math19")}</strong></span><br>
		<span><strong>5 ${languageManager.getString("math18")} - </strong></span> <span style="color: #0000ff;"><strong>30 ${languageManager.getString("math19")}</strong></span><br>
		</p>

	</div>
</div>

<%@ include file="../include/end-html.jsp"%>