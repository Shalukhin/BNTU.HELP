<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<c:set var="mainPict" value="main_ru.gif" />
<c:if test="${languageManager.getCurrentLanguage() == \"en\"}" >
	<c:set var="mainPict" value="main_en.gif" />
</c:if>

<div>
	<img src="pict/main/${mainPict}" class="img-fluid" alt="Responsive image">
</div>

<%@ include file="include/end-html.jsp" %>