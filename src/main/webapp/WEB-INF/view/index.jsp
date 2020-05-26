<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<c:set var="mainPict" value="main_ru.gif" />
<c:if test="${languageManager.getCurrentLanguage() == \"en\"}" >
	<c:set var="mainPict" value="main_en.gif" />
</c:if>
<br>

<div>
	<img id="pict" usemap="#map" src="pict/main/${mainPict}" class="img-fluid" alt="Responsive image">
	<map name="map">	
		<area id="link1" shape="rect" coords="" href="do?command=chemistry&tab=inorganic" alt="Химия">
		<area id="link2" shape="rect" coords="" href="do?command=math" alt="Математика">
		<area id="link3" shape="rect" coords="" href="do?command=informatic" alt="Информатика">
		<area id="link4" shape="rect" coords="" href="do?command=engin" alt="Инженерная графика">
  	</map>
</div>
<c:set var="chim" value="" />
<script type="text/javascript">
var koefX = document.getElementById("pict").width / 1920;
var koefY = document.getElementById("pict").height / 1030;
document.getElementById("link1").coords = 110 * koefX + "," + 420 * koefY + "," + 325 * koefX + "," + 490 * koefY;
document.getElementById("link2").coords = 110 * koefX + "," + 510 * koefY + "," + 475 * koefX + "," + 565 * koefY;
document.getElementById("link3").coords = 110 * koefX + "," + 590 * koefY + "," + 535 * koefX + "," + 660 * koefY;
document.getElementById("link4").coords = 110 * koefX + "," + 670 * koefY + "," + 745 * koefX + "," + 740 * koefY;
</script>

<%@ include file="include/end-html.jsp" %>