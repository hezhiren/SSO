$("#register").click(
   function() {
       var data = {
           email: $("#email").val(),
           userName: $("#userName").val(),
           password:$("#password").val()
       }
       $.ajax({
           type:"POST",
           dataType:"json",
           contentType:"application/json",
           url:"register.do",
           data:JSON.stringify(data),  //necessary
           success:function (result) {
               console.log(result);
               if(result.code = 0) {
                   alert("注册成功");
               }else {
                   alert(result.msg);
               }
           },
           error:function () {
               alert("注册异常，请重试！");
           }
       });
   }
);

$("#login").click(
    function() {
        var data = {
            email: $("#email").val(),
            userName: $("#userName").val(),
            password:$("#password").val()
        }
        $.ajax({
            type:"POST",
            dataType:"json",
            contentType:"application/json",
            url:"login.do",
            data:JSON.stringify(data),  //necessary
            success:function (result) {
                console.log(result);
                if(result.code = 0) {
                    alert("登录成功");
                    window.location.href="toIndex.do";
                }else {
                    alert(result.msg);
                }
            },
            error:function () {
                alert("登录异常，请重试！");
            }
        });
    }
);
