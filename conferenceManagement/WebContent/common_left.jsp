<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="./lib/scripts/jquery-1.11.0.min.js"></script>

<!-- 顶部导航栏结束 -->
<div class="container wrap_content">
    <div class="col-md-3 left_menu">
        <div class="panel-group" id="accordion">
            <div class="panel-body">
                <div class="list-group" style="margin:0">
                    <a class="list-group-item" href="ViewAllUserServlet?code=meeting">
                        会议申请
                    </a>
                    <a class="list-group-item" href="ConferenceServlet?code=choose">
                        会议室选择
                    </a>
                    <a class="list-group-item" href="NotificationServlet?code=display">
                        通知<span class="badge">${unreadNotificationNum}</span>
                    </a>
                    <a class="list-group-item" href="ConferenceInfoServlet?code=display">
                        会议信息
                    </a>

                </div>
            </div>

			<c:if test = "${privilege < 2}">
            <div class="panel-body">
                <div class="list-group" style="margin:0">
                    <a class="list-group-item" href="ViewAllUserServlet?code=approve">
                        用户注册审查<span class="badge"><c:out value="${unapprovedUserNumCommon}"/></span>
                    </a>
                    <a class="list-group-item" href="ViewAllUserServlet?code=manage">
                        用户信息管理
                    </a>
                    <a class="list-group-item" href="ConferenceServlet?code=check">
                        会议审查<span class="badge"><c:out value= "${unapprovedConferenceNum}"></c:out></span>
                    </a>
                    <a class="list-group-item" href="ViewAllDepartmentsServlet?code=viewalldepartments">
                        部门信息管理
                    </a>
                    <a class="list-group-item" href="ConferenceRoomServlet?code=display">
                        会议室信息管理
                    </a>
                    
                </div>
            </div>
			</c:if>
			
			<c:if test = "${privilege == 0}">
            <div class="panel-body">
                <div class="list-group" style="margin:0">
                    <a class="list-group-item" href="./right_change.jsp">
                        权限管理
                    </a>
                </div>
            </div>
            </c:if>

        </div>
    </div>