<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
    <!-- 左侧可伸缩菜单栏结束 -->
		<div class="col-md-9" style="margin-top:18px">
		<ul class="nav nav-tabs">
            <li><a href="ReadNotificationServlet?code=display">已查看</a></li>
            <li class="active"><a href="NotificationServlet?code=display">未查看</a></li>
        </ul>
			<div class="page-header">
				<h3><strong>未查看通知</strong></h3>
			</div>
	        <div class="tab-content">
		        <table class="table table-striped table-bordered">
		            <caption>
		                <ul class="pagination pagination-sm" style="float:left;">
		                    <li title="第一页"><a href="NotificationServlet?code=display">&laquo;</a></li>
		                    <li title="上一页"><a href="NotificationServlet?code=${currentPageNum*(-1)}">&lsaquo;</a></li>
		                    <li title="当前页" class="active"><a href="#"><c:out value="${requestScope.currentPageNum}"/></a></li>
		                    <li title="下一页"><a href="NotificationServlet?code=${currentPageNum}">&rsaquo;</a></li>
		                    <li title="最末页"><a href="NotificationServlet?code=lastPage">&raquo;</a></li>
		                    <li>
		                        <a href="">共<strong>${requestScope.notificationNum}</strong>条记录，
		                        <strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
		                    </li>
		                </ul>
		            </caption>
		            <thead>
		                <tr>
		                    <th>编号</th>
		                    <th>通知内容</th>
		                    <th>来源</th>
		                    <th>时间</th>
		                </tr>
		            </thead>
		            <tbody>
		            <c:forEach var="notification" items="${requestScope.notificationList}"
		            					begin="${currentPageNum%5 - 1 + 4*(currentPageNum-1)}" 
            							end="${currentPageNum%5 + 3 + 4*(currentPageNum-1)}">
		                <tr>
		                    <td>${notification.getNotificationId()}</td>
		                    <td>${notification.getNotificationDetail()}</td>
		                     <td>${notification.getNotificationSource()}</td>
		                      <td>${notification.getNotificationDate()}</td>
		                </tr>
		            </c:forEach>
		            </tbody>
		        </table>
            </div>
		</div>                
				
    </div>
    <div class="container text-center">
        <hr />
        &bull;<span class="team_name">实训开发第三组</span>
        <br />
        <br />
    </div>
</body>
<script src="./lib/scripts/jquery-1.11.0.min.js"></script>
<script src="./lib/bootstrap/js/bootstrap.min.js"></script>
<script src="./lib/scripts/bootbox.min.js"></script>
</html>