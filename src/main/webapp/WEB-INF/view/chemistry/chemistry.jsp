<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>

<br>

<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item"><a class="nav-link ${inorganic}"
		id="inorganic-tab" data-toggle="tab" href="#inorganic" role="tab"
		aria-controls="inorganic" aria-selected="false"><h6>${languageManager.getString("chemistry01")}</h6></a></li>
	<li class="nav-item"><a class="nav-link ${organic}"
		id="organic-tab" data-toggle="tab" href="#organic" role="tab"
		aria-controls="organic" aria-selected="false"><h6>${languageManager.getString("chemistry02")}</h6></a></li>
	<li class="nav-item"><a class="nav-link ${analitic}"
		id="analitic-tab" data-toggle="tab" href="#analitic" role="tab"
		aria-controls="analitic" aria-selected="false"><h6>${languageManager.getString("chemistry03")}</h6></a></li>
	<li class="nav-item"><a class="nav-link ${examChemistry}"
		id="examChemistry-tab" data-toggle="tab" href="#examChemistry"
		role="tab" aria-controls="examChemistry" aria-selected="false"><h6>${languageManager.getString("chemistry04")}</h6></a></li>
</ul>
<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show ${inorganic}" id="inorganic"
		role="tabpanel" aria-labelledby="inorganic-tab">
		<br>

		<div align="right" style="margin-bottom: -32px;">
			<form action="do?command=account&tab=order" method=POST>

				<input name="taskForOrder" value="Контрольная работа по общей химии" hidden>

				<textarea name="noteForOrder" hidden>${languageManager.getString("chemistry05")}: «Программа и контрольные задания по химии. Методические указания». Минск 2006. Под редакцией В.Н. Яглова.

${languageManager.getString("chemistry06")}: 


${languageManager.getString("chemistry07")}: 

</textarea>

				<textarea name="instructionForOrder" hidden>⬅  ${languageManager.getString("chemistry08")}
⬅  ${languageManager.getString("chemistry09")}
⬆️  ${languageManager.getString("chemistry10")}</textarea>

				<button id="Update" value="toOrder" name="toOrder" align="center"
					class="btn btn-success">${languageManager.getString("chemistry11")}</button>
			</form>
		</div>

		<div align="center">
			<h4>
				<p>
					<strong>${languageManager.getString("chemistry12")}</strong>
				</p>
			</h4>
		</div>

		<p>${languageManager.getString("chemistry13")}: «Программа и контрольные задания по химии.
			Методические указания». Минск 2006. Под редакцией В.Н. Яглова.</p>
		<p style="text-align: center;">
			<a
				href="do?command=chemistry&tab=inorganic&textbook=true"><img
				alt="" src="pict/chemistry/inorganic/met1_bntu_inorg_mini.png"></a>
		</p>
		<p style="text-align: left;">
			${languageManager.getString("chemistry14")}
		</p>

		<p align="left">
			${languageManager.getString("chemistry15")}
		</p>
		<p align="left">
			${languageManager.getString("chemistry16")}
		</p>

		<p style="text-align: left;">
			${languageManager.getString("chemistry17")}<a href="download?fileName=material/chemistry/inorg_task_5-22.pdf"
				target="_blank" style="text-decoration: none;"> ${languageManager.getString("chemistry18")} 5.22</a>
		</p>

	</div>

	<div class="tab-pane fade show ${organic}" id="organic" role="tabpanel"
		aria-labelledby="organic-tab">

		<br>
		<div align="right" style="margin-bottom: -32px;">
			<form action="do?command=account&tab=order" method=POST>

				<input name="taskForOrder" value="Контрольная работа по органической химии"
					hidden>

				<textarea name="noteForOrder" hidden>${languageManager.getString("chemistry05")}: «Органическая химия. Учебно-методическое пособие». Минск 2005. Л.М. Слепнева.

${languageManager.getString("chemistry06")}: 


${languageManager.getString("chemistry07")}: 

</textarea>

				<textarea name="instructionForOrder" hidden>⬅  ${languageManager.getString("chemistry08")}.
⬅  ${languageManager.getString("chemistry09")}
⬆️  ${languageManager.getString("chemistry10")}</textarea>

				<button id="Update" value="toOrder" name="toOrder" align="center"
					class="btn btn-success">${languageManager.getString("chemistry11")}</button>
			</form>
		</div>

		<div align="center">
			<h4>
				<p>
					<strong>${languageManager.getString("chemistry19")}</strong>
				</p>
			</h4>
		</div>
		
		<p>${languageManager.getString("chemistry20")}: «Органическая химия. Учебно-методическое пособие».
			Минск 2005. Л.М. Слепнева.</p>
		<p style="text-align: center;">
			<a href="do?command=chemistry&tab=organic&textbook=true"><img
				alt="" src="pict/chemistry/organic/met1_bntu_org_mini.png"></a>
		</p>
		<p style="text-align: left;">
			${languageManager.getString("chemistry21")}
		</p>
		<p style="text-align: left;">
			${languageManager.getString("chemistry22")}
		</p>
		<p style="text-align: left;">
			${languageManager.getString("chemistry23")}
		</p>
		<p style="text-align: left;">
			${languageManager.getString("chemistry24")}:<a
				href="download?fileName=material/chemistry/org_task_9-3.pdf"
				target="_blank" style="text-decoration: none;"> ${languageManager.getString("chemistry25")} 9.3</a>
		</p>

	</div>

	<div class="tab-pane fade show ${analitic}" id="analitic"
		role="tabpanel" aria-labelledby="analitic-tab">

		<br>
		
		<div align="right" style="margin-bottom: -32px;">
			<form action="do?command=account&tab=order" method=POST>

				<input name="taskForOrder" value="Контрольная работа по аналитической химии"
					hidden>

				<textarea name="noteForOrder" hidden>${languageManager.getString("chemistry05")}: «АНАЛИТИЧЕСКАЯ ХИМИЯ». Учебно-методическое пособие. Минск 2010. С.В. Дорожко, Н.Ф. Макаревич.

${languageManager.getString("chemistry06")}: 


${languageManager.getString("chemistry07")}: 

</textarea>

				<textarea name="instructionForOrder" hidden>⬅ ${languageManager.getString("chemistry08")}
⬅  ${languageManager.getString("chemistry09")}
⬆️ ${languageManager.getString("chemistry10")}</textarea>

				<button id="Update" value="toOrder" name="toOrder" align="center"
					class="btn btn-success">${languageManager.getString("chemistry11")}</button>
			</form>
		</div>

		<div align="center">
			<h4>
				<p>
					<strong>${languageManager.getString("chemistry26")}</strong>
				</p>
			</h4>
		</div>
		
		<p>${languageManager.getString("chemistry27")}: «АНАЛИТИЧЕСКАЯ ХИМИЯ». Учебно-методическое пособие. Минск 2010. С.В. Дорожко, Н.Ф. Макаревич.</p>
		<p style="text-align: center;">
			<a href="do?command=chemistry&tab=analitic&textbook=true"><img
				alt="" src="pict/chemistry/analitic/met1_bntu_analit_mini.png"></a>
		</p>
		<p style="text-align: left;">
			${languageManager.getString("chemistry28")}
		</p>
		<p style="text-align: left;">
			${languageManager.getString("chemistry29")}
		</p>
		<p style="text-align: left;">
			${languageManager.getString("chemistry30")}
		</p>
		<p style="text-align: left;">
			${languageManager.getString("chemistry31")}:<a
				href="download?fileName=material/chemistry/analit_task_4_60.pdf"
				target="_blank" style="text-decoration: none;">${languageManager.getString("chemistry32")} 4.60</a>
		</p>

	</div>

	<div class="tab-pane fade show ${examChemistry}" id="examChemistry"
		role="tabpanel" aria-labelledby="examChemistry-tab">

		<br>
		
		<div align="right" style="margin-bottom: -32px;">
			<form action="do?command=account&tab=order" method=POST>

				<input name="taskForOrder" value="Экзамен по химии"	hidden>

				<textarea name="noteForOrder" hidden>${languageManager.getString("chemistry33")}
${languageManager.getString("chemistry34")} 

${languageManager.getString("chemistry07")}: 

</textarea>

				<textarea name="instructionForOrder" hidden>⬅ ${languageManager.getString("chemistry35")}
⬅  ${languageManager.getString("chemistry09")}
⬆️ ${languageManager.getString("chemistry10")}</textarea>

				<button id="Update" value="toOrder" name="toOrder" align="center"
					class="btn btn-success">${languageManager.getString("chemistry11")}</button>
			</form>
		</div>
		
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("chemistry36")}</strong>
			</p>
		</h4>
		
		<p>${languageManager.getString("chemistry37")}</p>
			
		<h5><p style="text-align: center;">${languageManager.getString("chemistry38")}</p></h5>
		<p style="text-align: center;">
		<span><strong>2 ${languageManager.getString("chemistry39")} - </strong></span> <span style="color: #0000ff;"><strong>15 ${languageManager.getString("chemistry40")}</strong></span><br>
		<span><strong>3 ${languageManager.getString("chemistry39")} - </strong></span> <span style="color: #0000ff;"><strong>20 ${languageManager.getString("chemistry40")}</strong></span><br>
		<span><strong>4 ${languageManager.getString("chemistry39")} - </strong></span> <span style="color: #0000ff;"><strong>25 ${languageManager.getString("chemistry40")}</strong></span><br>
		<span><strong>(${languageManager.getString("chemistry41")} 4) - </strong></span> <span style="color: #0000ff;"><strong>25 ${languageManager.getString("chemistry40")}</strong></span><br>
		</p>
	</div>

</div>

<%@ include file="../include/end-html.jsp"%>