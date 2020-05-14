<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include/begin-html.jsp"%>

<script>
function confirmSubmit(i) {
  if (confirm("Вы уверены, что хотите удалить этот заказ?")) {
    document.getElementById("formDelOrderId"+i).submit();
  }
  return false;
}
</script>




<br>

<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item"><a class="nav-link ${welcome}" id="acc1-tab"
		data-toggle="tab" href="#acc1" role="tab" aria-controls="acc1"
		aria-selected="true"><h6>Приветствуем вас</h6></a></li>
	<li class="nav-item"><a class="nav-link ${order}" id="acc2-tab"
		data-toggle="tab" href="#acc2" role="tab" aria-controls="acc2"
		aria-selected="false"><h6>Сделать заказ</h6></a></li>
	<li class="nav-item"><a class="nav-link ${list}" id="acc3-tab"
		data-toggle="tab" href="#acc3" role="tab" aria-controls="acc3"
		aria-selected="false"><h6>Заказанные вами услуги</h6></a></li>
	
	<c:if test="${((user.getRole().getNameRole() == \"realizer\") || (user.getRole().getNameRole() == \"admin\")) && (user.getStatus().getNameStatus() != \"blocked\")}" >
		<li class="nav-item"><a class="nav-link ${listExecute}" id="acc5-tab"
			data-toggle="tab" href="#acc5" role="tab" aria-controls="acc5"
			aria-selected="false"><h6>Услуги для выполнения</h6></a></li>
		<li class="nav-item"><a class="nav-link ${listComplete}" id="acc6-tab"
			data-toggle="tab" href="#acc6" role="tab" aria-controls="acc6"
			aria-selected="false"><h6>Выполненные услуги</h6></a></li>		
	</c:if>
		
	<li class="nav-item"><a class="nav-link ${personal}" id="acc4-tab"
		data-toggle="tab" href="#acc4" role="tab" aria-controls="acc4"
		aria-selected="false"><h6>Личные данные</h6></a></li>
</ul>
<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show ${welcome}" id="acc1" role="tabpanel"
		aria-labelledby="acc1-tab">

		<br>

		<h4>
			<p style="text-align: center;">
				<strong>Приветствуем вас, уважаемый ${user.getLogin()}</strong>
			</p>
		</h4>

		<br> Это ваш личный кабинет. Здесь вы можете добавить или
		изменить личные данные, заказать и оплатить нужные вам контрольные
		работы, а так же просмотреть список всех заказанных вами работ!
		Ознакомиться с перечнем предлагаемых услуг, посмотреть примеры
		выполнения контрольных работ, а также узнать актуальные цены и сделать
		заказ вы сможете в соответствующих разделах сайта: Химия Математика
		Инженерная графика Информатика

	</div>


	<div class="tab-pane fade show ${order}" id="acc2" role="tabpanel"
		aria-labelledby="acc2-tab">

		<br>

		<h4>
			<p style="text-align: center;">
				<strong> Сделайте ваш заказ</strong>
			</p>
		</h4>

		<br>

		<c:set var="selected" scope="request" value="" />
		<c:set var="instruction" scope="request"
			value="↖  Выберите дисциплину.
⬅  Укажите методическое пособие.
⬅  Напишите номер вашего варианта, либо перечень номеров нужных вам заданий.
⬅  Укажите дату и время экзамена (при заказе услуги    помощи на экзамене).
⬅  Укажите ваш контактный номер (если не заполнены \"личные данные\").
⬆️  Нажмите кнопку \"Заказать\"" />

		<c:if test="${externalInstruction != null}">
			<c:set var="instruction" scope="request"
				value="${externalInstruction}" />
		</c:if>

		<form action="do?command=order&tab=order" method=POST>
			<div class="row">
				<div class=col-md-5>
					<select id="selectTask" name="orderedTask" class="form-control">
						<option>~ Выберите услугу ~</option>

						<c:forEach items="${listAllTask}" var="task">

							<c:if test="${externalFirstOption == task.getNameTask()}">
								<c:set var="selected" scope="request" value="selected" />
							</c:if>

							<option ${selected}>${task.getNameTask()}</option>

							<c:set var="selected" scope="request" value="" />
						</c:forEach>
					</select>
				</div>


				<div class=col-md-1>
					<c:set var="block" value="" />
					<c:if test="${user.getStatus().getNameStatus() == \"blocked\" }" >
						<c:set var="block" value="disabled" />
						<c:set var="messageOrder" value="<font color=&quot;#e76767&quot;><strong>Ваш аккаунт заблокирован! Вы не можете заказывать услуги!</strong></font>" />
						
					</c:if>
					<button ${block} id="Update" value="Update" name="toOrder"
						class="btn btn-success">Заказать</button>
				</div>

			</div>
			<br>
			<div class="row">
				<div class=col-md-5>
					<div class="form-group">
						<!--  <label for="exampleFormControlTextarea1">Example textarea</label> -->
						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="10" name="note"
							placeholder="Введите информацию по вашему заказу...">${externalNote}</textarea>
					</div>
				</div>
				<div class=col-md-5>
					<div class="form-group">
						<!--  <label for="exampleFormControlTextarea1">Example textarea</label> -->
						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="10" readonly="readonly">${instruction}
						</textarea>
					</div>
				</div>
			</div>
		</form>
		<p style="text-align: center;">${messageOrder}
			<span style="color: #0000ff;"><strong> ${jobType}</strong></span>
		</p>




	</div>

	<div class="tab-pane fade show ${list}" id="acc3" role="tabpanel"
		aria-labelledby="acc3-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Список заказанных вами услуг</strong>
			</p>
		</h4>
		<br>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col" width="3%"></th>
					<th scope="col" width="37%">Заказанная услуга</th>
					<th scope="col" width="15%">Дата заказа</th>
					<th scope="col" width="10%">Цена</th>
					<th scope="col" width="14%">Статус заказа</th>
					<th scope="col" width="16%">Ваши действия</th>
					<th scope="col" width="5%"> </th>

				</tr>
			</thead>
			<tbody>
				<c:set var="i" value="1" />

				<c:forEach items="${listUserOrder}" var="order">

					<tr>
						<th scope="row">${i}</th>
						
						<td>
							${order.getTask().getNameTask()}&nbsp&nbsp
							<input type="checkbox" id="hd-${i}" class="hide" /> 
							<label for="hd-${i}">(инф)</label>
							<div>${order.getNote()}</div>
						</td>
						
						<td>${order.getDateCreate().format(dateFormat)}</td>

						<td>
							<c:set var="priceOrder" value="________" />
							<c:if test="${order.isProcessed()}" >
								<c:set var="priceOrder" value="<font color=&quot;#747478&quot;><strong>${order.getPriceOrder()} р.</strong></font>" />
							</c:if>
							<c:if test="${order.isConfirmed()}" >
								<c:set var="priceOrder" value="<font color=&quot;#e76767&quot;><strong>${order.getPriceOrder()} р.</strong></font>" />
							</c:if>
							<c:if test="${order.isPaid()}" >
								<c:set var="priceOrder" value="<font color=&quot;#40853d&quot;><strong>${order.getPriceOrder()} р.</strong></font>" />
							</c:if>
							${priceOrder}
							
							
						</td>

						<td>
							<c:set var="statusOrder" value="В процессе обработки" /> 
							
							<c:if test="${order.isProcessed()}">
								<c:set var="statusOrder" value="В ожидании подтверждения" />
							</c:if> 
							<c:if test="${order.isConfirmed()}">
								<c:set var="statusOrder" value="В ожидании оплаты" />
							</c:if> 
							<c:if test="${order.isPaid()}">
								<c:set var="statusOrder" value="В процессе выполнения" />
							</c:if> 
							<c:if test="${order.isCompleted()}">
								<c:set var="statusOrder" value="Выполнен" />
							</c:if> 
							
							${statusOrder}							
						</td>
						
						<c:if test="${order.isPaid()}" >
							<c:set var="colspan" value="colspan=&quot;2&quot;" />
						</c:if>
						
						<td ${colspan}>
						<c:set var="colspan" value="" />
						<c:set var="actionUser" value="<font size=&quot;2&quot;>Ожидайте, исполнитель уточняет цену заказа</font>" /> 
							
							<c:if test="${order.isProcessed()}">
								<form id="formConfirmOrderId${order.getId()}" action="do?command=orderconfirm&tab=list" method="post">
									<input name="confirmOrderId" value="${order.getId()}" hidden>									
								</form>
								<c:set var="actionUser" value="<button title=&quot;Подтвердить заказ&quot; 
										class=&quot;btn btn-outline-info btn-sm&quot; style=&quot;margin-top: 10px; margin-left: 0px&quot; 
										type=&quot;submit&quot; form=&quot;formConfirmOrderId${order.getId()}&quot;>Подтвердить заказ</button>" />
							</c:if> 
							
							<c:if test="${order.isConfirmed()}">
								<form id="formPayOrderId${order.getId()}" action="do?command=test" method="post">
									<input name="payOrderId" value="${order.getId()}" hidden>									
								</form>
								<c:set var="actionUser" value="<button title=&quot;Оплатить заказ&quot; 
										class=&quot;btn btn-outline-success btn-sm&quot; style=&quot;margin-top: 10px; margin-left: 0px&quot; 
										type=&quot;submit&quot; form=&quot;formPayOrderId${order.getId()}&quot;>Оплатить заказ</button>" />
							</c:if>
							
							<c:if test="${order.isPaid()}">
								<c:set var="actionUser" value="<font size=&quot;2&quot;>Ожидайте, исполнитель работает над вашим заказом</font>" />
							</c:if> 
							
							<c:if test="${order.isCompleted()}">
								<form id="formDownloadOrderId${order.getId()}" action="do?command=test" method="post">
									<input name="downloadOrderId" value="${order.getId()}" hidden>									
								</form>
								<c:set var="actionUser" value="<button title=&quot;Скачать выполненный заказ&quot; 
										class=&quot;btn btn-success btn-sm&quot; style=&quot;margin-top: 10px; margin-left: 0px&quot; 
										type=&quot;submit&quot; form=&quot;formDownloadOrderId${order.getId()}&quot;>Скачать выполненный заказ</button>" />
							</c:if> 
							
							${actionUser}							
						</td>
						
						<c:if test="${!order.isPaid()}" >
						
							<td valign="middle">
								<c:if test="${!order.isPaid()}" >
									<form id="formDelOrderId${order.getId()}" action="do?command=test&tab=list" method="post">
										<input name="delOrderId" value="${order.getId()}" hidden>
									</form>
									<button type="button" title="Удалить заказ" class="btn btn-outline-danger btn-sm" style="margin-top: 10px;" 
											onclick="confirmSubmit(${order.getId()})">del</button>						
								</c:if>
							</td>
						
						</c:if>
						
					</tr>

					<c:set var="i" value="${i+1}" />

				</c:forEach>

			</tbody>
		</table>




	</div>
	
	<c:if test="${((user.getRole().getNameRole() == \"realizer\") || (user.getRole().getNameRole() == \"admin\")) && (user.getStatus().getNameStatus() != \"blocked\")}" >
	
	
	<div class="tab-pane fade show ${listExecute}" id="acc5" role="tabpanel"
		aria-labelledby="acc5-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Список услуг для выполнения</strong>
			</p>
		</h4>
		<br>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col" width="3%"></th>
					<th scope="col" width="36%">Заказанная услуга</th>
					<th scope="col" width="10%">Заказчик</th>
					<th scope="col" width="11%">Дата заказа</th>
					<th scope="col" width="8%">Цена</th>
					<th scope="col" width="12%">Статус заказа</th>
					<th scope="col" width="16%">Ваши действия</th>
					<th scope="col" width="4%"> </th>

				</tr>
			</thead>
			<tbody>
				<c:set var="j" value="1" />

				<c:forEach items="${listExecuteOrder}" var="order">

					<tr>
						<th scope="row">${j}</th>
						
						<td>
							${order.getTask().getNameTask()}&nbsp&nbsp
							<input type="checkbox" id="hdExecute-${j}" class="hide" /> 
							<label for="hdExecute-${j}">(инф)</label>
							<div>${order.getNote()}</div>
						</td>
						
						<td>
							${order.getUser().getLogin()}							
						</td>
						
						<td>${order.getDateCreate().format(dateFormat)}</td>

						<td>
							
							<c:set var="priceOrder" value="<input size=&quot;3&quot; name=&quot;processAdjustedPriceTask&quot; value=&quot;${order.getAdjustedPriceTask()}&quot;>" />
							
							<c:if test="${order.isProcessed()}" >
								<c:set var="priceOrder" value="<font color=&quot;#747478&quot;><strong>${order.getPriceOrder()} р.</strong></font>" />
							</c:if>
							<c:if test="${order.isConfirmed()}" >
								<c:set var="priceOrder" value="<font color=&quot;#e76767&quot;><strong>${order.getPriceOrder()} р.</strong></font>" />
							</c:if>
							<c:if test="${order.isPaid()}" >
								<c:set var="priceOrder" value="<font color=&quot;#40853d&quot;><strong>${order.getPriceOrder()} р.</strong></font>" />
							</c:if>
							
							<form id="formProcessOrderId${order.getId()}" action="do?command=test&tab=listExecute" method="post">
								${priceOrder}
							</form>
							
						</td>

						<td>
							<c:set var="statusOrder" value="В процессе обработки" /> 
							
							<c:if test="${order.isProcessed()}">
								<c:set var="statusOrder" value="В ожидании подтверждения" />
							</c:if> 
							<c:if test="${order.isConfirmed()}">
								<c:set var="statusOrder" value="В ожидании оплаты" />
							</c:if> 
							<c:if test="${order.isPaid()}">
								<c:set var="statusOrder" value="В процессе выполнения" />
							</c:if> 							
							
							${statusOrder}							
						</td>
						
						
						<c:if test="${order.isPaid()}" >
							<c:set var="colspan" value="colspan=&quot;2&quot;" />
						</c:if>
						
						<td ${colspan}>
						<c:set var="colspan" value="" />
						<c:set var="actionUser" value="<button title=&quot;Подтвердить цену&quot; 
										class=&quot;btn btn-outline-info btn-sm&quot; style=&quot;margin-top: 10px; margin-left: 0px&quot; 
										type=&quot;submit&quot; form=&quot;formProcessOrderId${order.getId()}&quot;>Подтвердить цену</button>" /> 
							
							<c:if test="${order.isProcessed()}">
								<c:set var="actionUser" value="<font size=&quot;2&quot;>Ожидайте подтверждения заказа </font>" /> 
							</c:if> 
							
							<c:if test="${order.isConfirmed()}">
								<form id="formPaidOrderId${order.getId()}" action="do?command=test" method="post">
									<input name="paidOrderId" value="${order.getId()}" hidden>									
								</form>
								<c:set var="actionUser" value="<button title=&quot;Подтвердить оплату&quot; 
										class=&quot;btn btn-outline-success btn-sm&quot; style=&quot;margin-top: 10px; margin-left: 0px&quot; 
										type=&quot;submit&quot; form=&quot;formPaidOrderId${order.getId()}&quot;>Подтвердить оплату</button>" />
							</c:if>
							
							<c:if test="${order.isPaid()}">							
							
								<c:set var="actionUser" value="
									<div class=&quot;custom-file mb-3&quot;>
    									<form action=&quot;do?command=test&quot; method=&quot;POST&quot; enctype=&quot;multipart/form-data&quot;>
      										<input type=&quot;file&quot; class=&quot;custom-file-input&quot; id=&quot;customFile&quot; name=&quot;filename&quot;>
      										<label class=&quot;custom-file-label&quot; for=&quot;customFile&quot;>Choose file</label>
      										<input type=&quot;submit&quot; name=&quot;Submit&quot; class=&quot;btn btn btn-outline-primary btn-sm&quot;>
     									</form>
    								</div>				
								" />
								
							</c:if> 
							
							${actionUser}
							
							<script>
							// Add the following code if you want the name of the file appear on select
							$(".custom-file-input").on("change", function() {
							  var fileName = $(this).val().split("\\").pop();
							  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
							});
							</script>							
						</td>
						
						<c:if test="${!order.isPaid()}" >
						
							<td valign="middle">
								<c:if test="${!order.isPaid()}" >
									<form id="formDelOrderId${order.getId()}" action="do?command=test&tab=listExecute" method="post">
										<input name="delOrderId" value="${order.getId()}" hidden>
									</form>
									<button type="button" title="Удалить заказ" class="btn btn-outline-danger btn-sm" style="margin-top: 10px;" 
											onclick="confirmSubmit(${order.getId()})">del</button>						
								</c:if>
							</td>
						
						</c:if>
						
					</tr>

					<c:set var="j" value="${j+1}" />

				</c:forEach>

			</tbody>
			
		</table>
		
	</div>
	
	<div class="tab-pane fade show ${listComplete}" id="acc6" role="tabpanel"
		aria-labelledby="acc6-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Список выполнененых заказов</strong>
			</p>
		</h4>
		<br>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col" width="3%"></th>
					<th scope="col" width="40%">Заказанная услуга</th>
					<th scope="col" width="10%">Заказчик</th>
					<th scope="col" width="10%">Исполнитель</th>
					<th scope="col" width="10%">Заказан</th>
					<th scope="col" width="10%">Выполнен</th>
					<th scope="col" width="8%">Цена</th>					
					<th scope="col" width="9%">Скачать</th>
					

				</tr>
			</thead>
			<tbody>
				<c:set var="k" value="1" />

				<c:forEach items="${listCompleteOrder}" var="order">

					<tr>
						<th scope="row">${k}</th>
						
						<td>
							${order.getTask().getNameTask()}&nbsp&nbsp
							<input type="checkbox" id="hdComplete-${k}" class="hide" /> 
							<label for="hdComplete-${j}">(инф)</label>
							<div>${order.getNote()}</div>
						</td>
						
						<td>
							${order.getUser().getLogin()}							
						</td>
						
						<td>
							${order.getRealizer().getLogin()}							
						</td>
						
						<td>${order.getDateCreate().format(dateFormat)}</td>
						
						<td>${order.getDateComplete().format(dateFormat)}</td>

						<td><font color=&quot;#40853d&quot;><strong>${order.getPriceOrder()} р.</strong></font></td>					
						
						<td>
							<form id="formDownloadOrderId${order.getId()}" action="do?command=test" method="post">
								<input name="downloadOrderId" value="${order.getId()}" hidden>									
							</form>
								
							<button title="Скачать выполненный заказ" class="btn btn-success btn-sm" style="margin-top: 10px; margin-left: 0px" 
										type="submit" form="formDownloadOrderId${order.getId()}">Скачать</button>	
						</td>		
						
					</tr>

					<c:set var="k" value="${k+1}" />

				</c:forEach>

			</tbody>
			
		</table>
		
	</div>
	
	
	</c:if>



	<div class="tab-pane fade show ${personal}" id="acc4" role="tabpanel"
		aria-labelledby="acc4-tab">
		<br>
		<h4>
			<p style="text-align: center;">
				<strong> Личные данные</strong>
			</p>
		</h4>

		<br>

		<div class="col-md-5">
			<form action="do?command=personal&tab=personal"
				method="post">

				<div class="form-group">
					<label for="name">Ваше имя:</label> <input class="form-control"
						id="name" placeholder="Введите ваше имя" name="name"
						value="${personalData.getName()}">
					<!--<small class="form-text text-muted">
					We'll never	share your email with anyone else.
				</small>  -->
				</div>

				<div class="form-group">
					<label for="name">Ваш номер телефона:</label> <input
						class="form-control" id="phone" placeholder="+375XXXXXXXXX"
						name="phone" value="${personalData.getPhone()}">
					<!--<small class="form-text text-muted">
					We'll never	share your email with anyone else.
				</small>  -->
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1">Адрес электронной почты:</label> <input
						type="email" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Enter email"
						name="email" value="${personalData.getEmail()}">
					<!--	<small id="emailHelp" class="form-text text-muted">We'll never
					share your email with anyone else.</small>  -->
				</div>

				<div class="form-group">
					<label for="name">Бонусные деньги:</label> <input
						class="form-control" readonly="readonly" id="money" name="money"
						placeholder="0.00"
						value="${personalData.getBonusMoney().toString()}">
					<!--<small class="form-text text-muted">
					We'll never	share your email with anyone else.
				</small>  -->
				</div>
				<!--<div class="form-group">
				<label for="exampleInputPassword1">Password</label> 
				<input
					type="password" class="form-control" id="exampleInputPassword1"
					placeholder="Password" name="pass">
			</div>
			  <div class="form-check">
				<input type="checkbox" class="form-check-input" id="exampleCheck1">
				<label class="form-check-label" for="exampleCheck1">Check me
					out</label>
			</div>  -->
				<button type="submit" class="btn btn-primary" name="submit"
					value="save">Сохранить</button>
			</form>
			<p style="text-align: center;">${messagePersonal}</p>
		</div>



	</div>
</div>

<%@ include file="include/end-html.jsp"%>