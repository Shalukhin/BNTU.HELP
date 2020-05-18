<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include/begin-html.jsp"%>
<br>

<c:set var="statusOrder" value="${languageManager.getString('order01')}" />

<c:if test="${curretnOrder.isProcessed()}">
	<c:set var="statusOrder" value="${languageManager.getString('order02')}" />
</c:if>
<c:if test="${curretnOrder.isConfirmed()}">
	<c:set var="statusOrder" value="${languageManager.getString('order03')}" />
</c:if>
<c:if test="${curretnOrder.isPaid()}">
	<c:set var="statusOrder" value="${languageManager.getString('order04')}" />
</c:if>
<c:if test="${curretnOrder.isCompleted()}">
	<c:set var="statusOrder" value="${languageManager.getString('order05')}" />
</c:if>

<h4>
	<p style="text-align: center;">
		<strong>${languageManager.getString("order06")} №${curretnOrder.getId()} &ndash; ${statusOrder}</strong>
	</p>
</h4>

<p style="text-align: left;">
	<strong>${languageManager.getString("order07")}</strong> &emsp;<font color="#0000ff"><strong>${curretnOrder.getTask().getNameTask()}</strong></font>
</p>

<c:set var="currentpriceOrder" value="______" />
<c:if test="${curretnOrder.isProcessed()}">
	<c:set var="currentpriceOrder" value="${curretnOrder.getPriceOrder()}" />
</c:if>

<p style="text-align: left;"> 
	<strong>${languageManager.getString("order08")}</strong> &emsp; <font color="#0000ff"><strong>${currentpriceOrder} ${languageManager.getString("order09")}</strong></font>
</p>


<c:set var="currentNameUser"
	value="${curretnOrder.getUser().getLogin()}" />
<c:if
	test="${curretnOrder.getUser().getPersonalData().getName() != null}">
	<c:set var="currentNameUser"
		value="${curretnOrder.getUser().getPersonalData().getName()}" />
</c:if>

<c:set var="currentNameRealizer"
	value="${curretnOrder.getRealizer().getLogin()}" />
<c:if
	test="${curretnOrder.getRealizer().getPersonalData().getName() != null}">
	<c:set var="currentNameRealizer"
		value="${curretnOrder.getRealizer().getPersonalData().getName()}" />
</c:if>

<p style="text-align: left;">
	<strong>${languageManager.getString("order10")}</strong><br>

	
<table border="1px solid black" width="100%">
<tr>
		<td colspan="2" width="50%" align="center"><strong>${languageManager.getString("order11")}</strong></td>
		<td colspan="2" width="50%" align="center"><strong>${languageManager.getString("order12")}</strong></td>
	</tr>
	<tr align="center">
		<td width="10%">${languageManager.getString("order13")}</td>
		<td>${currentNameUser}</td>
		<td width="10%">${languageManager.getString("order13")}</td>
		<td>${currentNameRealizer}</td>
	</tr>
	<tr align="center">
		<td width="10%">${languageManager.getString("order14")}</td>
		<td>${curretnOrder.getUser().getPersonalData().getPhone()}</td>
		<td width="10%">${languageManager.getString("order14")}</td>
		<td>${curretnOrder.getRealizer().getPersonalData().getPhone()}</td>
	</tr>
	<tr align="center">
		<td width="10%">e-mail</td>
		<td>${curretnOrder.getUser().getPersonalData().getEmail()}</td>
		<td width="10%">e-mail</td>
		<td>${curretnOrder.getRealizer().getPersonalData().getEmail()}</td>
	</tr></table>
</p>

<!--  <div class="row">
<div class="col-md-6">sss</div>
<div class="col-md-6">dddd</div>
</div> -->

<p style="text-align: left;">
	<strong>${languageManager.getString("order15")}</strong><br><table width="50%" border="1px">
	<tr align="center">
		<td width="10%"><font face="cursive">${languageManager.getString("order16")}</font></td>
		<td width="10%"><font face="cursive">${languageManager.getString("order17")}</font></td>
		<td width="10%"><font face="cursive">${languageManager.getString("order18")}</font></td>
		<td width="10%"><font face="cursive">${languageManager.getString("order19")}</font></td>
		<td width="10%"><font face="cursive">${languageManager.getString("order20")}</font></td>
	</tr>
	<tr align="center">
		<td>${curretnOrder.getDateCreate().format(dateFormat)}</td>
		<td>${curretnOrder.getDateProcess().format(dateFormat)}</td>
		<td>${curretnOrder.getDateConfirm().format(dateFormat)}</td>
		<td>${curretnOrder.getDatePay().format(dateFormat)}</td>
		<td>${curretnOrder.getDateComplete().format(dateFormat)}</td>
	</tr>
</table>
</p>
<br>

<c:if test="${tab == null}" >
	<c:set var="tab" value="list" />
</c:if>
<p style="text-align: center;">
	<span style="color: #0000ff;"><strong><a
			href="do?command=account&tab=${tab}">(${languageManager.getString("order21")})</a></strong></span>
</p>

<%@ include file="include/end-html.jsp"%>