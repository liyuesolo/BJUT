<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--用户登录页面-->
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title></title>
        <link href="./lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
        <link href="./lib/system/css/left_menu.css" rel="stylesheet" />
        <link href="css/login.css" rel="stylesheet" />
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
                        <li class="active"><a href="./index.jsp">登录</a></li>
                        <li><a href="./signup.jsp">注册</a></li>
                    </ul>

                </div>
            </div>
        </nav>
        <div id="Layer1" style="position:absolute; left:0px; top:0px; width:100%; height:100%">  
		<img src="img/cao.jpg" class="blur" style="width:100%; height:100%"/> 
			</div> 
        <div class="container wrap_content">
            <div id="login_box">
                <p id="login_title">登录<p>
                <div id="form_login">
                    <form name="login_form" method="post" id="login_form" action = "LoginServlet">
                        <table>
                            <tr>
                            	<label id="label_name" for="name"></label>
                                <input class="input_text" id="name" name="user_name"  placeholder="输入你的用户名" type="text" />
                            </tr>
                            <tr>
                                <label id="label_password" for="password"></label>
                                <input class="input_text" id="password" name="password" placeholder="输入密码" type="password" />
                            </tr>
                            <tr>
                                <div id="login_safe">
                                </div>
                            </tr>
                            <tr>
                            	<td><font color="red">${requestScope.msg}</font></td>
                                <input type="submit" id="button_submit" value="登陆"/>
                            </tr>
                            <tr align="right">
                                <p id="register_redirect">没有帐号？新成员？
                                    <span id="register_link" 
                                    onclick="window.location.href='ViewAllDepartmentsServlet?code=regist'">点我注册</span>
                                </p>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <div class="container text-center">
            <hr />
            &bull;<span class="team_name">实习开发第三组</span>
            <br />
            <br />
        </div>
    </body>
    <script src="./lib/scripts/jquery-1.11.0.min.js"></script>
    <script src="./lib/bootstrap/js/bootstrap.min.js"></script>
    <script src="./lib/scripts/bootbox.min.js"></script>
</html>