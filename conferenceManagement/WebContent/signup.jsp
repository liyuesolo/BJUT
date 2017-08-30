<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript">
   
	//使用ajax方法访问，验证账户名是否存在
	function validate() {
		$.ajax({
			type : "POST",
			url : "ValidateUsernameServlet",
			data : { user_name : $("#user_name").val()},
			success : function(message) {
				var validateMessage = $("#validateMessage");
				var data = JSON.parse(message);
				validateMessage.html(data.msg);
				if (data.flag) {
					validateMessage.css({
						color : "green"
					});
				} else {
					validateMessage.css({
						color : "red"
					});
				}

			}
		});
	}
   
	//验证两次密码是否相同
	function check() {
		if (register_form.password.value != register_form.ensure_password.value) {
			confirminfo.innerHTML = "<font color=red>两次输入的密码不相符</font>";
		} else {
			confirminfo.innerHTML = "<font color=green>两次输入的密码相符</font>";
		}
	}
</script>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="lib/system/css/left_menu.css" rel="stylesheet" />
    <link href="css/signup.css" rel="stylesheet" />
</head>
<body>
<!-- 顶部导航栏开始 -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav">
                <span class="sr-only">Toggle</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand system_title" href="#">会议室管理系统</a>
        </div>
        <div class="collapse navbar-collapse navbar-right" id="nav">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">登录</a></li>
                <li class="active"><a href="signup.jsp">注册</a></li>
            </ul>

        </div>
    </div>
</nav>
<div id="Layer1" style="position:absolute; z-index:-1;left:0px; top:0px; width:100%; height:100%">  
	<img src="img/cao.jpg" class="blur" style="width:100%; height:100%"/> 
</div> 
<!-- 顶部导航栏结束 -->
<div class="container wrap_content">
    <div id="register_box">
        <p id="register_title">注册</p>
        <div id="form_register">
            <form name="register_form" method="post" id="register_form" action = "RegisterServlet">
                <table>
                    <tbody>
					<tr>
                        <td><label id="label_name" for="name">用户名</label></td>
                        <td><input class="input_text" id="user_name" name="user_name"  placeholder="输入你的用户名" type="text" 
                        	onblur="return checkUsernameEmpty();" onchange="validate()"/>
                        <td id="usernamets" style="width: 150px;text-align: left;font-size: 12px;"></td>
                        <div id="validateMessage"></div></td>
                    </tr>
                    <tr>
                        <td><label id="label_name" for="name">姓名</label></td>
                        <td><input class="input_text" id="name" name="user_id"  placeholder="输入你的真实姓名" type="text" 
                        onblur ="return checkUserRealNameEmpty();"/></td>
                        <td id="userrealnamets" style="width: 150px;text-align: left;font-size: 12px;"></td>
                    </tr>
                    <tr>
                        <td><label id="label_password" for="password">密码</label></td>
                        <td><input class="input_text" id="password" name="password" placeholder="输入密码" type="password" 
                        onblur="return checkPasswordEmpty();"/>
                        </td>
                        <td id="passwordts" style="width: 150px;text-align: left;font-size: 12px;"></td>
                    </tr>
                    <tr>
                        <td><label id="label_ensure_password" for="ensure_password">确认密码</label></td>
                        <td><input class="input_text" id="ensure_password" name="ensure_password" placeholder="再次输入密码" type="password"
                        onblur="return checkEnsurePasswordEmpty();"
                        onchange="check()" />
                        <td id="ensurepasswordts" style="width: 150px;text-align: left;font-size: 12px;"></td>
                        <div id="confirminfo"></div></td>
                    </tr>
					 <tr>
                        <td><label id="label_name" for="age">年龄</label></td>
                        <td><input class="input_text" id="age" name="age"  placeholder="输入你的年龄" type="text" 
                        onblur="return checkAgeEmpty();"/></td>
                        <td id="agets" style="width: 150px;text-align: left;font-size: 12px;"></td>
                    </tr>

                    <tr>
                        <td><label id="label_gender" for="gender">性别</label></td>
                        <td>
                            <select id="gender" name="gender">
                                <option value = "0">男</option>
                                <option value = "1">女</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label id="label_department" for="department">所在部门</label></td>
                        <td>
                            <select id="department" name="department_name">
                                <c:forEach var="department"
									items="${requestScope.departmentsList}">
									<c:if test="${department.getDepartmentname() == param.department_name}">
										<option value="${department.getDepartmentname()}" selected>${department.getDepartmentname()}</option>
									</c:if>
									<c:if test="${department.getDepartmentname()!= param.department_name}">
										<option value="${department.getDepartmentname()}">${department.getDepartmentname()}</option>
									</c:if>
								</c:forEach>	
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label id="label_email" for="email">邮箱</label></td>
                        <td><input class="input_text" id="email" 
                        name="mail" placeholder="输入邮箱地址" type="text" 
                        onblur="return checkemail();"/></td>
                        <td id="emailts" style="width: 150px;text-align: left;font-size: 12px;"></td>
                    </tr>
                    <tr>
                        <td><label id="label_tel" for="tel">手机</label></td>
                        <td><input class="input_text" id="tel" name="phone" placeholder="输入手机号" type="text" 
                        	onblur="return checkPhoneNumber();"/></td>
                       	<td id="phonets" style="width: 150px;text-align: left;font-size: 12px;"></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                        <input type="submit" id="register_button" value="注册" onclick="document.location.href='index.jsp'"/>
						</td>
                    </tr>
                    <tr align="right">
                        <td colspan="2">
                            <p id="login_redirect">已有账号？
                                <span id="login_link" onclick="document.location.href='index.jsp'">点我登录</span>
                            </p>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
<script src="./lib/scripts/jquery-1.11.0.min.js"></script>
<script src="./lib/bootstrap/js/bootstrap.min.js"></script>
<script src="./lib/scripts/bootbox.min.js"></script>
<script>
function checkemail()
{
    var userEmail = $("#email").val();
    var ets = document.getElementById("emailts");
    if(!isEmail(userEmail)){
        ets.innerHTML ="邮箱格式不正确!";
        ets.style.color="red";
        return false;
    } 
    ets.innerHTML ="邮箱可以使用!";
    ets.style.color="green";
    return true;
}
function isEmail(str)
{
    var reg = /[a-z0-9-]{1,30}@[a-z0-9-]{1,65}.[a-z]{3}/;
    return reg.test(str);
}
function checkPhoneNumber()
{

   var phoneNum = $("#tel").val();
   var ets = document.getElementById("phonets");
	
   if(isPhone(phoneNum)){
       ets.innerHTML ="不能输入非数字字符!";
       ets.style.color="red";
       return false;
   } 
   if(phone.length==0)
   {
	   	ets.innerHTML ="手机号不能为空!";
     	ets.style.color="red";
      	return false;
   }
   ets.innerHTML ="手机号格式正确!";
   ets.style.color="green";
   return true;

}
function isPhone(str)
{
	var reg =  /\D/;
	return reg.test(str);
}
function checkUsernameEmpty()
{
	var id = $("#user_name").val();
	var ets = document.getElementById("usernamets");
	if(id.length==0)
	{
		ets.innerHTML ="用户名不能为空!";
      	ets.style.color="red";
       	return false;
	}
	ets.innerHTML ="";
   	return true;	
}
function checkAgeEmpty()
{
	var id = $("#age").val();
	var ets = document.getElementById("agets");
	if(id.length==0)
	{
		ets.innerHTML ="年龄不能为空!";
      	ets.style.color="red";
       	return false;
	}
	ets.innerHTML ="";
   	return true;	
}
function checkUserRealNameEmpty()
{
	var id = $("#name").val();
	var ets = document.getElementById("userrealnamets");
	if(id.length==0)
	{
		ets.innerHTML ="姓名不能为空!";
      	ets.style.color="red";
       	return false;
	}
	ets.innerHTML ="";
   	return true;	
}
function checkPasswordEmpty()
{
	var id = $("#password").val();
	var ets = document.getElementById("passwordts");
	if(id.length==0)
	{
		ets.innerHTML ="密码不能为空!";
      	ets.style.color="red";
       	return false;
	}
	ets.innerHTML ="";
   	return true;	
}
function checkEnsurePasswordEmpty()
{
	var id = $("#ensure_password").val();
	var ets = document.getElementById("ensurepasswordts");
	if(id.length==0)
	{
		ets.innerHTML ="密码不能为空!";
      	ets.style.color="red";
       	return false;
	}
	ets.innerHTML ="";
   	return true;	
}
</script>
</html>