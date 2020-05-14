<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include/begin-html.jsp"%>

<br>

<ul class="nav nav-tabs" id="myTab" role="tablist">
	<li class="nav-item"><a class="nav-link ${allUser}" id="adm1-tab"
		data-toggle="tab" href="#adm1" role="tab" aria-controls="adm1"
		aria-selected="true"><h6>Список пользователей</h6></a></li>
	<li class="nav-item"><a class="nav-link ${allTask}" id="adm2-tab"
		data-toggle="tab" href="#adm2" role="tab" aria-controls="adm2"
		aria-selected="false"><h6>Список услуг</h6></a></li>
	<li class="nav-item"><a class="nav-link ${allOrder}" id="adm3-tab"
		data-toggle="tab" href="#adm3" role="tab" aria-controls="adm3"
		aria-selected="false"><h6>Список заказов</h6></a></li>
	<li class="nav-item"><a class="nav-link ${reset}" id="adm4-tab"
		data-toggle="tab" href="#adm4" role="tab" aria-controls="adm4"
		aria-selected="false"><h6>Отчистка базы</h6></a></li>
</ul>
<div class="tab-content" id="myTabContent">

	<div class="tab-pane fade show ${allUser}" id="adm1" role="tabpanel"
		aria-labelledby="adm1-tab">

		<br>

		<h4>
			<p style="text-align: center;">
				<strong>Список пользователей</strong>
			</p>
		</h4>
		
		<p style="text-align: center;">
			<font color="#CD5C5C"><strong>${messageAdminUserList}</strong></font>
		</p>
		
		<table class="table table-bordered" >
			<thead>
				<tr bgcolor="#A9A9A9">
					<th scope="col" width="3%">#</th>
					<th scope="col" width="6%">id</th>
					<th scope="col" width="20%">Логин</th>
					<th scope="col" width="20%">Пароль</th>
					<th scope="col" width="20%">Роль</th>
					<th scope="col" width="20%">Статус</th>
					<th scope="col" width="7%">Изменить</th>
					<th scope="col" width="4%">Удалить</th>
				</tr>
			</thead>
			<tbody bgcolor="#FFFAFA">
				<c:set var="i" value="1" />
				<c:forEach items="${listAllUser}" var="itemUser">
					
					<tr>
						<form id="formChangeUserID${itemUser.getId()}" action="do?command=changeuser&tab=allUser" method="post">
									
						
							<td>${i}</td>
							<td><input readonly size="4" name="changeIdUser" value="${itemUser.getId()}"></td>
							<td><input name="changeLoginUser" value="${itemUser.getLogin()}"></td>
							<td><input name="changePasswordUser" value="${itemUser.getPassword()}"></td>
							<td>
							
								<select id="selectRole" name="changeRoleUser" class="form-control">
									
									<c:forEach items="${listAllRole}" var="role">

										<c:if test="${itemUser.getRole().getId() == role.getId()}">
										
											<c:if test="${itemUser.getRole().getSubject() == null}" >
												<c:set var="selected" scope="request" value="selected" />
											</c:if>
											
											<c:if test="${itemUser.getRole().getSubject() != null}" >											
												<c:if test="${itemUser.getRole().getSubject().getId() == role.getSubject().getId()}" >
													<c:set var="selected" scope="request" value="selected" />
												</c:if>												
											</c:if>											
											
										</c:if>

										<option ${selected}>${role.getNameRole()} ${role.getSubject().getNameSubject()}</option>

										<c:set var="selected" scope="request" value="" />
									</c:forEach>
									
								</select>	
							
							</td>
							
							<td>
							
								<select id="selectStatus" name="changeStatusUser" class="form-control">
									
									<c:forEach items="${listAllStatus}" var="status">

										<c:if test="${itemUser.getStatus().getNameStatus() == status.getNameStatus()}">
											<c:set var="selected" scope="request" value="selected" />
										</c:if>

										<option ${selected}>${status.getNameStatus()}</option>

										<c:set var="selected" scope="request" value="" />
									</c:forEach>
									
								</select>
							
							</td>
							
						</form>
						
						<td>
							<button title="Изменить" class="btn btn-outline-primary" style="margin-top: 0px;" type="submit" 
									form="formChangeUserID${itemUser.getId()}" name="action" value="edit">Изменить</button>
						</td>
										
						<td>
							<button title="Удалить" class="btn btn-outline-danger" style="margin-top: 0px;" type="submit" 
									form="formChangeUserID${itemUser.getId()}" name="action" value="del">del</button>
						</td>
					</tr>
					
					<c:set var="i" value="${i + 1}" />
					
				</c:forEach>			
				
			</tbody>
		</table>
		
	</div>


	<div class="tab-pane fade show ${allTask}" id="adm2" role="tabpanel"
		aria-labelledby="adm2-tab">

		<br>

		<h4>
			<p style="text-align: center;">
				<strong>Список услуг</strong>
			</p>
		</h4>

		<p style="text-align: center;">
			<font color="#CD5C5C"><strong>${messageAdminTaskList}</strong></font>
		</p>
		
		<table class="table table-bordered" >
			<thead>
			
				<tr bgcolor="#90EE90">					
						<form id="formCreateTask" action="do?command=changetask&tab=allTask" method="post">
						
							<td colspan="2"><font size="2"><strong>Новая услуга</strong></font></td>							
							<td><input size="48" name="changeNameTask" placeholder="Введите название услуги" value="${inputNameTask}"></td>
							<td><input size="4" name="changePriceTask" placeholder="0.00" value="${inputPriceTask}"></td>
							
							<td>
							
								<select id="selectCourse" name="changeCourseTask" class="form-control">
								
									<option>#</option>
									
									<c:forEach items="${listAllCourse}" var="course">										

										<c:if test="${inputCourseTask == course.getNumberCourseStr()}">
											<c:set var="selected" scope="request" value="selected" />
										</c:if>

										<option ${selected}>${course.getNumberCourse()}</option>

										<c:set var="selected" scope="request" value="" />
										
									</c:forEach>
									
								</select>	
							
							</td>
							
							<td>
							
								<select id="selectSubject" name="changeSubjectTask" class="form-control">
									
									<option selected>Предмет</option>
									
									<c:forEach items="${listAllSubject}" var="subject">										

										<c:if test="${inputSubjectTask == subject.getNameSubject()}">
											<c:set var="selected" scope="request" value="selected" />
										</c:if>

										<option ${selected}>${subject.getNameSubject()}</option>

										<c:set var="selected" scope="request" value="" />
										
									</c:forEach>
									
								</select>
							
							</td>						
						
						</form>
						
						<td colspan="2">
							<button title="Добавить услугу" class="btn btn-success" style="margin-top: 0px;" type="submit" 
									form="formCreateTask" name="action" value="create">Добавить услугу в БД</button>

						</td>						
					
					</tr>
			
				<tr bgcolor="#A9A9A9">
					<th scope="col" width="3%">#</th>
					<th scope="col" width="5%">id</th>
					<th scope="col" width="40%">Названи услуги</th>
					<th scope="col" width="5%">Цена</th>
					<th scope="col" width="10%">Курс</th>
					<th scope="col" width="30%">Предмет</th>
					<th scope="col" width="7%">Изменить</th>
					<th scope="col" width="4%">Удалить</th>
				</tr>
			</thead>
			<tbody bgcolor="#FFFAFA">
			
				
			
				<c:set var="i" value="1" />
				<c:forEach items="${listAllTask}" var="itemTask">					
					
					<tr>
						<form id="formChangeTaskID${itemTask.getId()}" action="do?command=changetask&tab=allTask" method="post">									
						
							<td>${i}</td>
							<td><input readonly size="3" name="changeIdTask" value="${itemTask.getId()}"></td>
							<td><input size="48"name="changeNameTask" value="${itemTask.getNameTask()}"></td>
							<td><input size="4" name="changePriceTask" value="${itemTask.getPriceTask()}"></td>
							<td>
							
								<select id="selectCourse" name="changeCourseTask" class="form-control">
									
									<c:forEach items="${listAllCourse}" var="course">

										<c:if test="${itemTask.getCourse().getNumberCourse() == course.getNumberCourse()}">
											<c:set var="selected" scope="request" value="selected" />
										</c:if>

										<option ${selected}>${course.getNumberCourse()}</option>

										<c:set var="selected" scope="request" value="" />
									</c:forEach>
									
								</select>	
							
							</td>
							
							<td>
							
								<select id="selectSubject" name="changeSubjectTask" class="form-control">
									
									<c:forEach items="${listAllSubject}" var="subject">

										<c:if test="${itemTask.getSubject().getNameSubject() == subject.getNameSubject()}">
											<c:set var="selected" scope="request" value="selected" />
										</c:if>

										<option ${selected}>${subject.getNameSubject()}</option>

										<c:set var="selected" scope="request" value="" />
									</c:forEach>
									
								</select>
							
							</td>
							
						</form>
						
						<td>
							<button title="Изменить" class="btn btn-outline-primary" style="margin-top: 0px;" type="submit" 
									form="formChangeTaskID${itemTask.getId()}" name="action" value="edit">Изменить</button>
						</td>
										
						<td>
							<button title="Удалить" class="btn btn-outline-danger" style="margin-top: 0px;" type="submit" 
									form="formChangeTaskID${itemTask.getId()}" name="action" value="del">del</button>
						</td>
					</tr>
					
					<c:set var="i" value="${i + 1}" />
					
				</c:forEach>			
				
			</tbody>
		</table>



	</div>

	<div class="tab-pane fade show ${allOrder}" id="adm3" role="tabpanel"
		aria-labelledby="adm3-tab">

		<br>
		<h4>
			<p style="text-align: center;">
				<strong>Список заказов</strong>
			</p>
		</h4>
		<br>
		
		<form action="do?command=test" method="POST" enctype="multipart/form-data">
    		<input type="file" name="myfile">
   			 <br/>
   	 		<input type="submit" name="Submit">
		</form>
		
		<br>
		<br>
		
		<form>
  			<div class="form-group">
    			<label for="exampleFormControlFile1">Example file input</label>
    			<input type="file" class="form-control-file" id="exampleFormControlFile1">
 			 </div>
		</form>





	</div>



	<div class="tab-pane fade show ${reset}" id="adm4" role="tabpanel"
		aria-labelledby="adm4-tab">
		<br>
		<h4>
			<p style="text-align: center;">
				<strong>Отчистка базы</strong>
			</p>
		</h4>

		<br>



	</div>
</div>



<%@ include file="include/end-html.jsp"%>