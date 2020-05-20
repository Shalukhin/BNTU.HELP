<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../include/begin-html.jsp"%>

<br>

<ul class="nav nav-tabs" id="myTab" role="tablist">

	<li class="nav-item"><a class="nav-link active" id="engin1-tab"
		data-toggle="tab" href="#engin1" role="tab" aria-controls="engin1"
		aria-selected="true"><h6>${languageManager.getString("engin01")}</h6></a></li>

	<li class="nav-item"><a class="nav-link " id="engin2-tab"
		data-toggle="tab" href="#engin2" role="tab" aria-controls="engin2"
		aria-selected="false"><h6>${languageManager.getString("engin02")}</h6></a></li>

	<li class="nav-item"><a class="nav-link" id="exam-tab"
		data-toggle="tab" href="#exam" role="tab" aria-controls="exam"
		aria-selected="false"><h6>${languageManager.getString("engin03")}</h6></a></li>

</ul>

<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show active" id="engin1" role="tabpanel"
		aria-labelledby="engin1-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("engin04")}</strong>
			</p>
		</h4>
		<br>
		<table border="0">
			<tr>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=engin&example=1" target="_blank"><img alt=""
							src="pict/engin/engin_example1_mini.jpg"></a><br> <a
							href="do?command=engin&example=1" target="_blank">(${languageManager.getString("engin05")})</a>
					</p>
				</td>
				<td width="55"></td>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=engin&example=2" target="_blank"><img alt=""
							src="pict/engin/engin_example2_mini.jpg"></a><br> <a
							href="do?command=engin&example=2" target="_blank">(${languageManager.getString("engin05")})</a>
					</p>
				</td>
				<td width="55"></td>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=engin&example=3" target="_blank"><img alt=""
							src="pict/engin/engin_example3_mini.jpg"></a><br> <a
							href="do?command=engin&example=3" target="_blank">(${languageManager.getString("engin05")})</a>
					</p>
				</td>
			</tr>
			<tr>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=engin&example=4" target="_blank"><img alt=""
							src="pict/engin/engin_example4_mini.jpg"></a><br> <a
							href="do?command=engin&example=4" target="_blank">(${languageManager.getString("engin05")})</a>
					</p>
				</td>
				<td width="55"></td>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=engin&example=5" target="_blank"><img alt=""
							src="pict/engin/engin_example5_mini.jpg"></a><br> <a
							href="do?command=engin&example=5" target="_blank">(${languageManager.getString("engin05")})</a>
					</p>
				</td>
				<td width="55"></td>
				<td width="330">
					<p style="text-align: center;">
						<a href="do?command=engin&example=6" target="_blank"><img alt=""
							src="pict/engin/engin_example6_mini.jpg"></a><br> <a
							href="do?command=engin&example=6" target="_blank">(${languageManager.getString("engin05")})</a>
					</p>
				</td>
			</tr>

		</table>

	</div>

	<div class="tab-pane fade" id="engin2" role="tabpanel"
		aria-labelledby="engin2-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("engin06")}</strong>
			</p>
		</h4>
		<br>

		<p style="text-align: left;">
			${languageManager.getString("engin07")}<br>
		</p>
		<ul type="disc">
			<li><b>A4</b> - <span style="color: #0000ff;"><strong>${languageManager.getString("engin08")}</strong></span></li>
			<li><b>A3</b> - <span style="color: #0000ff;"><strong>${languageManager.getString("engin09")}</strong></span></li>
			<li><b>A2</b> - <span style="color: #0000ff;"><strong>${languageManager.getString("engin10")}</strong></span></li>
			<li><b>A1</b> - <span style="color: #0000ff;"><strong>${languageManager.getString("engin11")}</strong></span></li>
		</ul>
	</div>


	<div class="tab-pane fade" id="exam" role="tabpanel"
		aria-labelledby="exam-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("engin12")}</strong>
			</p>
		</h4>
		<br>
		<p>${languageManager.getString("engin13")}</p>
		<p style="text-align: left;">
			${languageManager.getString("engin14")}
		</p>


	</div>
</div>


<%@ include file="../include/end-html.jsp"%>