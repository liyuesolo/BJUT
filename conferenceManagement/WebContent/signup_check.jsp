<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
    <!-- 左侧可伸缩菜单栏结束 -->
        <div class="col-md-9" style="margin-top: -25px;">
        <div class="page-header">
            <h3><strong>用户注册审查</strong></h3>
        </div>
        <table class="table table-striped table-bordered text-center">
            <caption>
                <h4><strong>所有用户申请</strong></h4>
                <ul class="pagination pagination-sm" style="float:left;">
                    <li title="第一页"><a href="ViewUnapprovedUserServlet?code=firstPage">&laquo;</a></li>
                    <li title="上一页"><a href="ViewUnapprovedUserServlet?code=${currentPageNum*(-1)}">&lsaquo;</a></li>
                    <li title="当前页" class="active">
                   	 <a href="#"><c:out value="${requestScope.currentPageNum }"/></a>
                    </li>
                    <li title="下一页"><a href="ViewUnapprovedUserServlet?code=${currentPageNum}">&rsaquo;</a></li>
                    <li title="最末页"><a href="ViewUnapprovedUserServlet?code=lastPage">&raquo;</a></li>
                    <li>
                        <a href="">共<strong><c:out value="${requestScope.unapprovedUserNum}"/></strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
                    </li>
                </ul>
            </caption>
            <thead>
                <tr>
                    <th class="text-center">用户名</th>
                    <th class="text-center">真实姓名</th>
                    <th class="text-center">性别</th>
                    <th class="text-center">年龄</th>
                    <th class="text-center">邮件</th>
                    <th class="text-center">电话</th>
                    <th class="text-center">所属部门</th>
                    <th class="text-center">操作</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="user" items="${requestScope.unapprovedUserList}"
            							begin="${currentPageNum%5 - 1 + 4*(currentPageNum-1)}" 
            							end="${currentPageNum%5 + 3 + 4*(currentPageNum-1)}">
					<tr>
						<td>${user.getUsername()}</td>
						<td>${user.getUserid()}</td>
						<td>${user.getGender() }</td>
						<td>${user.getAge() }</td>
						<td>${user.getMail() }</td>
						<td>${user.getPhone() }</td>
						<td>${user.getDepartmentname() }</td>
						<td><input type="button" class="btn btn-primary btn-sm" value="通过"
							onclick="window.location.href='ApproveServlet?user_name=${user.getUsername() }&oper=yes'" />
							<input type="button" class="btn btn-danger btn-sm col-md-offset-1" value="不通过"
							onclick="window.location.href='ApproveServlet?user_name=${user.getUsername() }&oper=no'" /></td>
					</tr>
				</c:forEach>
            </tbody>
        </table>
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
<script src="js/signup_check.js"></script>
</html>