<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include/begin-html.jsp"%>

<br>

<c:if test="${orderForPay == null}">
	<h5>
		<p>${languageManager.getString("pay01")}</p>
		<p>1) ${languageManager.getString("pay02")}</p>
		<p>2) ${languageManager.getString("pay03")}</p>
	</h5>
	<p>${languageManager.getString("pay04")}</p>					
</c:if>

<c:if test="${orderForPay != null}">
	<c:if test="${orderForPay.getRealizer().getLogin() == 'Vasil'}">
		<h4><p style ="text-align: center;"><strong>${orderForPay.getTask().getNameTask()} (заказ №${orderForPay.getIdIndicate()})</strong></p></h4>

		<p style="text-align: left;"><strong>Что-бы получить выполненную работу в электронном виде, произведите 
		оплату (<span style="color: #0000ff;"><strong>${orderForPay.getPriceOrder()} бел.руб.</strong></span>) одним из следующих способов:</strong><br>
		<strong>а) по системе ЕРИП:</strong><br>		
		Платёж ЕРИП (код платежа: 4485471):<br>
		"Банковские, финансовые услуги" / "Банки, НКФО" / "Альфа-Банк" / "Пополнение счета" / 3014301316014<br>
		Система должна распознать счёт и отобразить моё имя-отчество "ВАСИЛИЙ СЕРГЕЕВИЧ", далее вводите сумму (${orderForPay.getPriceOrder()} бел.руб.) и оплачиваете. <br>
		<strong>б) переводом на карту:</strong><br>		
		Карта Альфа-Банка: 4585204226773276 
		</p>
		
		<hr>

		<p style="text-align: left;"><strong>Что-бы получить выполненную работу в распечатанном виде</strong>, 
		вы можете приехать в согласованное время по адресу:<br>
		г.Минск, ул.Лидская, д.2<br> 
		В этом случае оплата производится <strong>наличными</strong>.
		</p>
		
		<hr>
		
		<p style="text-align: left;">Cвяжитесь со мной <strong>мтс +375295662016 (viber/sms)</strong> для получения ссылки 
		на файл выполненной работы (после вашей оплаты), либо для согласования времени нашей встречи.
		</p>
		
		<p style="text-align: center;">
			<span style="color: #0000ff;"><strong><a
			href="do?command=account&tab=list">(назад к списку)</a></strong></span>
		</p>
	</c:if>
</c:if>


	<%@ include file="include/end-html.jsp"%>