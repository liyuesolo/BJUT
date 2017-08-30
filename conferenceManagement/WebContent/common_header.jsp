<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<% String username = (String)session.getAttribute("user_name");%>
<% int privilege = (int)session.getAttribute("privilege");%>
<% int unapprovedConferenceNum = (int)session.getAttribute("unapprovedConferenceNum");%>
<% int unapprovedUserNumCommon = (int)session.getAttribute("unapprovedUserNumCommon");%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="./lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="./lib/system/css/left_menu.css" rel="stylesheet" />
    <link href="/lib/timeline/jquery.timeline.css" rel="stylesheet" />
    <link href="css/pick.css" rel="stylesheet"/>
    <link href="css/right_change.css" rel="stylesheet"/>
    <link href="css/meeting_apply.css" rel="stylesheet"/>
    <link href="css/meeting_info_detail.css" rel="stylesheet"/>
    <link href="css/meeting_info_list.css" rel="stylesheet"/>
    <link href="css/meeting_apply.css" rel="stylesheet" />
    
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav">
                <span class="sr-only">Toggle</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand system_title" href="#">会议室管理系统</a>
        </div>
        <div class="collapse navbar-collapse navbar-right" id="nav">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <%=username %>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="UpdateProfileServlet?code=show&user_name=<%=username%>">修改个人信息</a></li>
                        <li><a href="./index.jsp">注销</a></li>
                    </ul>
                </li>
            </ul>

        </div>
    </div>
</nav>