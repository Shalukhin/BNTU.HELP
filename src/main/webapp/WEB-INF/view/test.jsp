<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="include/begin-html.jsp"%>

<script type="text/javascript">	        	
 			<%@include file="js/loginAJAX/jquery-1.11.1.min.js"%> 
		</script>  
		      
        <script type="text/javascript">	
        	
 			<%@include file="js/loginAJAX/script.js"%> 
		</script>
		
		
<br>
<br>
<br>
<br>
<p style="text-align: center;">
	<strong> Test OK</strong>
</p>

<div>
            <form class="auth-forms">
                <div id="auth-info"></div>
                <input type="text" placeholder="Логин" id="login"/>
                <br/>
                <input type="password" placeholder="Пароль" id="password"/>
                <br/>
            </form>
            <br/>
            <button id="button">Вход</button>
        </div>


<%@ include file="include/end-html.jsp"%>