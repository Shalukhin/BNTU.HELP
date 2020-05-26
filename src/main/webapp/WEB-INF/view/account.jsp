<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include/begin-html.jsp"%>

<script>
function confirmSubmit(i) {
  if (confirm("${languageManager.getString('account01')}")) {
    document.getElementById("formDelOrderId"+i).submit();
  }
  return false;
}
</script>

<c:set var="delIter" value="0" />
<br>
<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item"><a class="nav-link ${welcome}" id="acc1-tab"
		data-toggle="tab" href="#acc1" role="tab" aria-controls="acc1"
		aria-selected="true"><h6>${languageManager.getString("account02")}</h6></a></li>
	<li class="nav-item"><a class="nav-link ${order}" id="acc2-tab"
		data-toggle="tab" href="#acc2" role="tab" aria-controls="acc2"
		aria-selected="false"><h6>${languageManager.getString("account03")}</h6></a></li>
	<li class="nav-item"><a class="nav-link ${list}" id="acc3-tab"
		data-toggle="tab" href="#acc3" role="tab" aria-controls="acc3"
		aria-selected="false"><h6>${languageManager.getString("account04")}</h6></a></li>
	
	<c:if test="${((user.getRole().getNameRole() == \"realizer\") || (user.getRole().getNameRole() == \"admin\")) && (user.getStatus().getNameStatus() != \"blocked\")}" >
		<li class="nav-item"><a class="nav-link ${listExecute}" id="acc5-tab"
			data-toggle="tab" href="#acc5" role="tab" aria-controls="acc5"
			aria-selected="false"><h6>${languageManager.getString("account05")}</h6></a></li>
		<li class="nav-item"><a class="nav-link ${listComplete}" id="acc6-tab"
			data-toggle="tab" href="#acc6" role="tab" aria-controls="acc6"
			aria-selected="false"><h6>${languageManager.getString("account06")}</h6></a></li>		
	</c:if>
		
	<li class="nav-item"><a class="nav-link ${personal}" id="acc4-tab"
		data-toggle="tab" href="#acc4" role="tab" aria-controls="acc4"
		aria-selected="false"><h6>${languageManager.getString("account07")}</h6></a></li>
</ul>
<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show ${welcome}" id="acc1" role="tabpanel"
		aria-labelledby="acc1-tab">

		<br>
		<c:set var="userName" value="${user.getLogin()}" />
		<c:if test="${user.getPersonalData().getName() != null}" >
			<c:set var="userName" value="${user.getPersonalData().getName()}" />
		</c:if>
		
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("account08")} ${userName}</strong>
			</p>
		</h4>

		<br>
		<font size="5" face="Constantia">&emsp;&emsp;${languageManager.getString("account09")}</font><br>
		<font size="5" face="Segoe Print">
		&emsp;&#9658;&nbsp;<a href="do?command=chemistry&tab=inorganic">${languageManager.getString("begin03")}</a><br>
		&emsp;&#9658;&nbsp;<a href="do?command=math">${languageManager.getString("begin04")}</a><br>
		&emsp;&#9658;&nbsp;<a href="do?command=engin">${languageManager.getString("begin05")}</a><br>
		&emsp;&#9658;&nbsp;<a href="do?command=informatic">${languageManager.getString("begin06")}</a>
		</font>
	<!-- 		 -->

	</div>


	<div class="tab-pane fade show ${order}" id="acc2" role="tabpanel"
		aria-labelledby="acc2-tab">

		<br>

		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("account10")}</strong>
			</p>
		</h4>

		<br>

		<c:set var="selected" scope="request" value="" />
		<c:set var="instruction" scope="request"
			value="↖   ${languageManager.getString('account10')}
⬅   ${languageManager.getString('account12')}
⬅   ${languageManager.getString('account13')}
⬅   ${languageManager.getString('account14')}
⬅   ${languageManager.getString('account15')}
⬆️   ${languageManager.getString('account16')}" />

		<c:if test="${externalInstruction != null}">
			<c:set var="instruction" scope="request" value="${externalInstruction}" />
			<c:set var="externalInstruction" scope="session" value="${null}" />
		</c:if>

		<form action="do?command=order&tab=order" method=POST>
			<div class="row">
				<div class=col-md-5>
					<select id="selectTask" name="orderedTask" class="form-control">
						<option>~ ${languageManager.getString("account17")} ~</option>

						<c:forEach items="${listAllTask}" var="task">

							<c:if test="${externalFirstOption == task.getNameTask()}">
								<c:set var="selected" scope="request" value="selected" />
							</c:if>

							<option ${selected}>${task.getNameTask()}</option>

							<c:set var="selected" scope="request" value="" />
						</c:forEach>
					</select>
					<c:set var="externalFirstOption" scope="session" value="${null}" />
				</div>


				<div class=col-md-1>
					<c:set var="block" value="" />
					<c:if test="${user.getStatus().getNameStatus() == \"blocked\" }" >
						<c:set var="block" value="disabled" />
						<c:set var="messageOrder" value="<font color=&quot;#e76767&quot;><strong>${languageManager.getString('account18')}</strong></font>" />
						
					</c:if>
					<button ${block} id="Update" value="Update" name="toOrder"
						class="btn btn-success">${languageManager.getString("account19")}</button>
				</div>

			</div>
			<br>
			<div class="row">
				<div class=col-md-5>
					<div class="form-group">
						<!--  <label for="exampleFormControlTextarea1">Example textarea</label> -->
						<textarea class="form-control" id="exampleFormControlTextarea1"
							rows="10" name="note"
							placeholder="${languageManager.getString('account20')}">${externalNote}</textarea>
						<c:set var="externalNote" scope="session" value="${null}" />
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
		<c:set var="messageOrder" scope="session" value="${null}" />
		<c:set var="jobType" scope="session" value="${null}" />
		
	</div>

	<div class="tab-pane fade show ${list}" id="acc3" role="tabpanel"
		aria-labelledby="acc3-tab">
		<form id="formRefreshList" action="do?command=account&tab=list" method="post"></form>
		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("account21")}</strong>&nbsp;&nbsp;
				<button type="submit" title="${languageManager.getString('account22')}" class="btn btn-outline-dark btn-sm" form="formRefreshList">&#x21bb;</button>
			</p>
		</h4>
		
		<p style="text-align: center;">
			<font color="#CD5C5C"><strong>${messageOrderList}</strong></font>
			<c:set var="messageOrderList" scope="session" value="${null}" />
		</p>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col" width="3%"></th>					
					<th scope="col" width="37%">${languageManager.getString("account23")}</th>
					<th scope="col" width="15%">${languageManager.getString("account24")}</th>
					<th scope="col" width="10%">${languageManager.getString("account25")}</th>
					<th scope="col" width="14%">${languageManager.getString("account26")}</th>
					<th scope="col" width="16%">${languageManager.getString("account27")}</th>
					<th scope="col" width="5%"> </th>

				</tr>
			</thead>
			<tbody>
				<c:set var="i" value="1" />

				<c:forEach items="${listUserOrder}" var="order">

					<tr>
						<th scope="row">${i}</th>
						
						<td>
							<a href="do?command=vieworder&viewOrderId=${order.getId()}&tab=list">${order.getTask().getNameTask()}</a>&nbsp&nbsp
							<input type="checkbox" id="hd-${i}" class="hide" /> 
							<label for="hd-${i}">(${languageManager.getString("account28")})</label>
							<div>
								${languageManager.getString("account29")}  &ndash; №${order.getId()} <br>
								${order.getNote()}</div>
						</td>
						
						<td><ctg:date-time value="${order.getDateCreate()}" /></td>

						<td>
							<c:set var="priceOrder" value="________" />
							<c:if test="${order.isProcessed()}" >
								<c:set var="priceOrder" value="<font color=&quot;#747478&quot;><strong>${order.getPriceOrder()} ${languageManager.getString('account50')}</strong></font>" />
							</c:if>
							<c:if test="${order.isConfirmed()}" >
								<c:set var="priceOrder" value="<font color=&quot;#e76767&quot;><strong>${order.getPriceOrder()} ${languageManager.getString('account50')}</strong></font>" />
							</c:if>
							<c:if test="${order.isPaid()}" >
								<c:set var="priceOrder" value="<font color=&quot;#40853d&quot;><strong>${order.getPriceOrder()} ${languageManager.getString('account50')}</strong></font>" />
							</c:if>
							
							${priceOrder}
						</td>

						<td>
							<c:set var="statusOrder" value="${languageManager.getString('account30')}" /> 
							
							<c:if test="${order.isProcessed()}">
								<c:set var="statusOrder" value="${languageManager.getString('account31')}" />
							</c:if> 
							<c:if test="${order.isConfirmed()}">
								<c:set var="statusOrder" value="${languageManager.getString('account32')}" />
							</c:if> 
							<c:if test="${order.isPaid()}">
								<c:set var="statusOrder" value="${languageManager.getString('account33')}" />
							</c:if> 
							<c:if test="${order.isCompleted()}">
								<c:set var="statusOrder" value="${languageManager.getString('account34')}" />
							</c:if> 
							
							${statusOrder}							
						</td>
						
						<c:if test="${order.isPaid()}" >
							<c:set var="colspan" value="colspan=&quot;2&quot;" />
						</c:if>
						
						<td ${colspan}>
						<c:set var="colspan" value="" />
						<c:set var="actionUser" value="<font size=&quot;2&quot;>${languageManager.getString('account35')}</font>" /> 
							
							<c:if test="${order.isProcessed()}">
								<form id="formConfirmOrderId${order.getId()}" action="do?command=orderconfirm&tab=list" method="post">
									<input name="confirmOrderId" value="${order.getId()}" hidden>									
								</form>
								<c:set var="actionUser" value="<button title=&quot;${languageManager.getString('account36')}&quot; 
										class=&quot;btn btn-outline-info btn-sm&quot; style=&quot;margin-top: 10px; margin-left: 0px&quot; 
										type=&quot;submit&quot; form=&quot;formConfirmOrderId${order.getId()}&quot;>${languageManager.getString('account36')}</button>" />
							</c:if> 
							
							<c:if test="${order.isConfirmed()}">
								<form id="formGoToPayOrderId${order.getId()}" action="do?command=gotopay" method="post">
									<input name="goToPayOrderId" value="${order.getId()}" hidden>									
								</form>
								<c:set var="actionUser" value="<button title=&quot;${languageManager.getString('account37')}&quot; 
										class=&quot;btn btn-outline-success btn-sm&quot; style=&quot;margin-top: 10px; margin-left: 0px&quot; 
										type=&quot;submit&quot; form=&quot;formGoToPayOrderId${order.getId()}&quot;>${languageManager.getString('account37')}</button>" />
							</c:if>
							
							<c:if test="${order.isPaid()}">
								<c:set var="actionUser" value="<font size=&quot;2&quot;>${languageManager.getString('account38')}</font>" />
							</c:if> 
							
							<c:if test="${order.isCompleted()}">
								<form id="formDownloadFinishFileId${order.getId()}" action="download?source=db" method="post">
									<input name="downloadFinishFileId" value="${order.getFinishFile().getId()}" hidden>									
								</form>
								<c:set var="actionUser" value="<button title=&quot;${languageManager.getString('account39')}&quot; 
										class=&quot;btn btn-success btn-sm&quot; style=&quot;margin-top: 10px; margin-left: 0px&quot; 
										type=&quot;submit&quot; form=&quot;formDownloadFinishFileId${order.getId()}&quot;>${languageManager.getString('account39')}</button>" />
							</c:if> 
							
							${actionUser}							
						</td>
						
						<c:if test="${!order.isPaid()}" >
						
							<td valign="middle">
								<c:if test="${!order.isPaid()}" >
									<form id="formDelOrderId${delIter}" action="do?command=orderdelete&tab=list" method="post">
										<input name="delOrderId" value="${order.getId()}" hidden>
									</form>
									<button type="button" title="${languageManager.getString('account40')}" class="btn btn-outline-danger btn-sm" style="margin-top: 10px;" 
											onclick="confirmSubmit(${delIter})">&nbsp;X&nbsp;</button>						
								</c:if>
							</td>
						
						</c:if>
						
					</tr>

					<c:set var="i" value="${i + 1}" />
					<c:set var="delIter" value="${delIter + 1}" />

				</c:forEach>

			</tbody>
		</table>




	</div>
	
	<c:if test="${((user.getRole().getNameRole() == \"realizer\") || (user.getRole().getNameRole() == \"admin\")) && (user.getStatus().getNameStatus() != \"blocked\")}" >
	
	
	<div class="tab-pane fade show ${listExecute}" id="acc5" role="tabpanel"
		aria-labelledby="acc5-tab">
		<form id="formRefreshListExecute" action="do?command=account&tab=listExecute" method="post"></form>										
		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("account41")}</strong>&nbsp;&nbsp;
				<button type="submit" title="${languageManager.getString('account42')}" class="btn btn-outline-dark btn-sm" form="formRefreshListExecute">&#x21bb;</button>
			</p>
		</h4>
		
		<p style="text-align: center;">
			<font color="#CD5C5C"><strong>${messageOrderExecute}</strong></font>
			<c:set var="messageOrderExecute" scope="session" value="${null}" />
		</p>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col" width="3%"></th>
					<th scope="col" width="35%">${languageManager.getString("account43")}</th>
					<th scope="col" width="10%">${languageManager.getString("account44")}</th>
					<th scope="col" width="11%">${languageManager.getString("account45")}</th>
					<th scope="col" width="9%">${languageManager.getString("account46")}</th>
					<th scope="col" width="12%">${languageManager.getString("account47")}</th>
					<th scope="col" width="16%">${languageManager.getString("account48")}</th>
					<th scope="col" width="4%"> </th>

				</tr>
			</thead>
			<tbody>
				<c:set var="j" value="1" />

				<c:forEach items="${listExecuteOrder}" var="order">

					<tr>
						<th scope="row">${j}</th>
						
						<td>
							<a href="do?command=vieworder&viewOrderId=${order.getId()}&tab=listExecute">${order.getTask().getNameTask()}</a>&nbsp&nbsp							
							<input type="checkbox" id="hdExecute-${j}" class="hide" /> 
							<label for="hdExecute-${j}">(${languageManager.getString("account49")})</label>							
							<div>
								${languageManager.getString("account29")}  &ndash; №${order.getId()} <br>
								${order.getNote()}
							</div>
						</td>
						
						<td>
							${order.getUser().getLogin()}							
						</td>
						
						<td><ctg:date-time value="${order.getDateCreate()}" /></td>

						<td>
							
							<c:set var="priceOrder" value="						
								<form id=&quot;formProcessOrderId${order.getId()}&quot; action=&quot;do?command=orderprocess&tab=listExecute&quot; method=&quot;post&quot;>
									<input name=&quot;processOrderId&quot; value=&quot;${order.getId()}&quot; hidden>
									<input size=&quot;3&quot; name=&quot;processAdjustedPriceTask&quot; value=&quot;${order.getAdjustedPriceTask()}&quot;>
								</form>							
							" />
							
							<c:if test="${order.isProcessed()}" >
								<c:set var="priceOrder" value="<font color=&quot;#747478&quot;><strong>${order.getPriceOrder()} ${languageManager.getString('account50')}</strong></font>" />
							</c:if>
							<c:if test="${order.isConfirmed()}" >
								<c:set var="priceOrder" value="<font color=&quot;#e76767&quot;><strong>${order.getPriceOrder()} ${languageManager.getString('account50')}</strong></font>" />
							</c:if>
							<c:if test="${order.isPaid()}" >
								<c:set var="priceOrder" value="<font color=&quot;#40853d&quot;><strong>${order.getPriceOrder()} ${languageManager.getString('account50')}</strong></font>" />
							</c:if>
							
							${priceOrder}							
							
						</td>

						<td>
							<c:set var="statusOrder" value="${languageManager.getString('account51')}" /> 
							
							<c:if test="${order.isProcessed()}">
								<c:set var="statusOrder" value="${languageManager.getString('account52')}" />
							</c:if> 
							<c:if test="${order.isConfirmed()}">
								<c:set var="statusOrder" value="${languageManager.getString('account53')}" />
							</c:if> 
							<c:if test="${order.isPaid()}">
								<c:set var="statusOrder" value="${languageManager.getString('account54')}" />
							</c:if> 							
							
							${statusOrder}							
						</td>
						
						
						<c:if test="${order.isPaid()}" >
							<c:set var="colspan" value="colspan=&quot;2&quot;" />
						</c:if>
						
						<td ${colspan}>
						<c:set var="colspan" value="" />
						<c:set var="actionUser" value="<button title=&quot;${languageManager.getString('account55')}&quot; 
										class=&quot;btn btn-outline-info btn-sm&quot; style=&quot;margin-top: 10px; margin-left: 0px&quot; 
										type=&quot;submit&quot; form=&quot;formProcessOrderId${order.getId()}&quot;>${languageManager.getString('account55')}</button>" /> 
							
							<c:if test="${order.isProcessed()}">
								<c:set var="actionUser" value="<font size=&quot;2&quot;>${languageManager.getString('account56')}</font>" /> 
							</c:if> 
							
							<c:if test="${order.isConfirmed()}">
								<form id="formPayOrderId${order.getId()}" action="do?command=orderpay&tab=listExecute" method="post">
									<input name="payOrderId" value="${order.getId()}" hidden>									
								</form>
								<c:set var="actionUser" value="<button title=&quot;${languageManager.getString('account57')}&quot; 
										class=&quot;btn btn-outline-success btn-sm&quot; style=&quot;margin-top: 10px; margin-left: 0px&quot; 
										type=&quot;submit&quot; form=&quot;formPayOrderId${order.getId()}&quot;>${languageManager.getString('account57')}</button>" />
							</c:if>
							
							<c:if test="${order.isPaid()}">							
							
								<c:set var="actionUser" value="
									<div class=&quot;custom-file mb-3&quot;>
    									<form id=&quot;formDownOrderId${order.getId()}&quot; action=&quot;do?command=ordercomplete&tab=listComplete&completeOrderId=${order.getId()}&quot; method=&quot;POST&quot; enctype=&quot;multipart/form-data&quot;>
    										
      										<input type=&quot;file&quot; class=&quot;custom-file-input&quot; id=&quot;customFile&quot; name=&quot;filename&quot;>
      										<label class=&quot;custom-file-label&quot; for=&quot;customFile&quot;>${languageManager.getString('account58')}</label>
      									<!--  	<input type=&quot;submit&quot; name=&quot;Submit&quot; class=&quot;btn btn btn-outline-primary btn-sm&quot;>  -->
     									</form>
     									<button class=&quot;btn btn btn-outline-primary btn-sm&quot; type=&quot;submit&quot; form=&quot;formDownOrderId${order.getId()}&quot;>${languageManager.getString('account59')}</button>
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
									<form id="formDelOrderId${delIter}" action="do?command=orderdelete&tab=listExecute" method="post">
										<input name="delOrderId" value="${order.getId()}" hidden>
									</form>
									<button type="button" title="Удалить заказ" class="btn btn-outline-danger btn-sm" style="margin-top: 10px;" 
											onclick="confirmSubmit(${delIter})">&nbsp;X&nbsp;</button>						
								</c:if>
							</td>
						
						</c:if>
						
					</tr>

					<c:set var="j" value="${j+1}" />
					
					<c:set var="delIter" value="${delIter + 1}" />

				</c:forEach>

			</tbody>
			
		</table>
		
	</div>
	
	<div class="tab-pane fade show ${listComplete}" id="acc6" role="tabpanel"
		aria-labelledby="acc6-tab">
		<form id="formRefreshListComplete" action="do?command=account&tab=listComplete" method="post"></form>
		<br>
		<h4>
			<p style="text-align: center;">
				<strong>${languageManager.getString("account60")}</strong>&nbsp;&nbsp;
				<button type="submit" title="${languageManager.getString('account61')}" class="btn btn-outline-dark btn-sm" form="formRefreshListComplete">&#x21bb;</button>
			</p>
		</h4>
		
		<p style="text-align: center;">
			<font color="#CD5C5C"><strong>${messageOrderComplete}</strong></font>
			<c:set var="messageOrderComplete" scope="session" value="${null}" />
		</p>

		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col" width="3%"></th>
					<th scope="col" width="40%">${languageManager.getString("account62")}</th>
					<th scope="col" width="10%">${languageManager.getString("account63")}</th>
					<th scope="col" width="10%">${languageManager.getString("account64")}</th>
					<th scope="col" width="10%">${languageManager.getString("account65")}</th>
					<th scope="col" width="10%">${languageManager.getString("account66")}</th>
					<th scope="col" width="9%">${languageManager.getString("account67")}</th>					
					<th scope="col" width="8%">${languageManager.getString("account68")}</th>
					

				</tr>
			</thead>
			<tbody>
				<c:set var="k" value="1" />

				<c:forEach items="${listCompleteOrder}" var="order">

					<tr>
						<th scope="row">${k}</th>
						
						<td>
							<a href="do?command=vieworder&viewOrderId=${order.getId()}&tab=listComplete">${order.getTask().getNameTask()}</a>&nbsp&nbsp
							<input type="checkbox" id="hdComplete-${k}" class="hide" /> 
							<label for="hdComplete-${k}">(${languageManager.getString("account69")})</label>
							<div>
								${languageManager.getString("account29")} &ndash; №${order.getId()} <br>
								${order.getNote()}
							</div>
						</td>
						
						<td>
							${order.getUser().getLogin()}							
						</td>
						
						<td>
							${order.getRealizer().getLogin()}							
						</td>
						
						<td><ctg:date-time value="${order.getDateCreate()}" /></td>
						
						<td><ctg:date-time value="${order.getDateComplete()}" /></td>

						<td><font color=&quot;#40853d&quot;><strong>${order.getPriceOrder()} ${languageManager.getString("account50")}</strong></font></td>					
						
						<td>
							<form id="formDownloadFinishFileId${order.getId()}" action="download?source=db" method="post">
								<input name="downloadFinishFileId" value="${order.getFinishFile().getId()}" hidden>									
							</form>
								
							<button title="${languageManager.getString('account70')}" class="btn btn-success btn-sm" style="margin-top: 10px; margin-left: 0px" 
										type="submit" form="formDownloadFinishFileId${order.getId()}">${languageManager.getString("account71")}</button>	
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
				<strong>${languageManager.getString("account72")}</strong>
			</p>
		</h4>

		<br>

		<div class="col-md-5">
			<form action="do?command=personal&tab=personal"
				method="post">

				<div class="form-group">
					<label for="name">${languageManager.getString("account73")}</label> 
					<input class="form-control" id="name" placeholder="${languageManager.getString('account74')}" name="name"
						value="${user.getPersonalData().getName()}">
					<!--<small class="form-text text-muted">
					We'll never	share your email with anyone else.
				</small>  -->
				</div>

				<div class="form-group">
					<label for="name">${languageManager.getString("account75")}</label> <input
						class="form-control" id="phone" placeholder="+375XXXXXXXXX"
						name="phone" value="${user.getPersonalData().getPhone()}">
					<!--<small class="form-text text-muted">
					We'll never	share your email with anyone else.
				</small>  -->
				</div>

				<div class="form-group">
					<label for="exampleInputEmail1">${languageManager.getString("account76")}</label> <input
						type="email" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="${languageManager.getString('account77')}"
						name="email" value="${user.getPersonalData().getEmail()}">
					<!--	<small id="emailHelp" class="form-text text-muted">We'll never
					share your email with anyone else.</small>  -->
				</div>

				<div class="form-group">
					<label for="name">${languageManager.getString("account78")}</label> <input
						class="form-control" readonly="readonly" id="money" name="money"
						placeholder="0.00"
						value="${user.getPersonalData().getBonusMoney().toString()}">
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
					value="save">${languageManager.getString("account79")}</button>
			</form>
			<p style="text-align: center;">${messagePersonal}</p>
			<c:set var="messagePersonal" scope="session" value="${null}" />
		</div>
		
	</div>
</div>

<%@ include file="include/end-html.jsp"%>