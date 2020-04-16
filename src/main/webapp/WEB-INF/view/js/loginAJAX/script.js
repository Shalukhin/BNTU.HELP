$(document).ready(function(){
    $("#loginAJAX").keyup(function(){
        var data = {};
        data = {"login":$("#loginAJAX").val()};
        //
        $.ajax
        ({
            type: "POST",//Метод передачи
            data: data,//Передаваемые данные в JSON - формате
            url: 'AJAXServlet',//Название сервлета
            success:function(serverData)//Если запрос удачен
            {
               // $("#auth-info").css({"background-color":serverData.backgroundColor, "height": "50px", "color":"white"});
                $("#loginAJAX").css({"background-color":serverData.backgroundColor});
                $("#auth-info").html(serverData.serverInfo);
                
            },
            error: function(e)//Если запрос не удачен
            {
               
            }            
        });        
    });    
});

