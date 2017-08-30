<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
	<!-- 左侧可伸缩菜单栏结束 -->
        <div class="col-md-9" style="margin-top: -25px;">
			<div class="page-header">
				<div>
					<h3><strong>部门管理</strong></h3>
				</div>
			</div>
			<div>
				<table class="table table-striped table-bordered text-center">
					<caption>
						<h4><strong><c:out value="${requestScope.department_name}"/>成员列表</strong></h4>
						<ul class="pagination pagination-sm" style="float:left;">
                  		  <li title="第一页">
                  		  	<a href="ViewUserInDepartment?code=firstPage&department_name=${requestScope.department_name}">&laquo;</a>
                  		  </li>
                  		  <li title="上一页">
                  		  <a href="ViewUserInDepartment?code=${requestScope.currentPageNum*(-1)}&department_name=${requestScope.department_name}">
                  		  &lsaquo;</a></li>
                  		  <li title="当前页" class="active">
                  		  	<a href="#">
								<c:out value="${requestScope.currentPageNum }"/>
							</a>
						  </li>
                  		  <li title="下一页">
                  		  <a href="ViewUserInDepartment?code=${requestScope.currentPageNum}&department_name=${requestScope.department_name}">
                  		  &lsaquo;</a></li>
                  		  <li title="最末页">
                  		  <a href="ViewUserInDepartment?code=lastPage&department_name=${requestScope.department_name}">
                  		  &lsaquo;</a></li>
                   		  <li>
                        	<a href="">共<strong>
                        		<c:out value="${requestScope.userNum }"/>
                        		</strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
                    	  </li>
                	</ul>
					</caption>
					<thead>
                <tr>
                    <th class="text-center">用户名</th>
                    <th class="text-center">真实姓名</th>
                    <th class="text-center">性别</th>
                    <th class="text-center">年龄</th>
                    <th class="text-center">邮箱</th>
                    <th class="text-center">手机号</th>
                    <th class="text-center">状态</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="user" items="${requestScope.userInDepartmentList}" 
            							begin="${currentPageNum%5 - 1 + 4*(currentPageNum-1)}" 
            							end="${currentPageNum%5 + 3 + 4*(currentPageNum-1)}">
					<tr>
						<td class="user_name"><c:out value = "${user.getUsername()}"/></td>
						<td><c:out value = "${user.getUserid()}"/></td>
						<td>
						<c:choose>
							<c:when test="${user.getGender() == 0}">
								<c:out value="男"/>
							</c:when>
							<c:otherwise>
								<c:out value="女"/>
							</c:otherwise>
						</c:choose>
						</td>
						<td><c:out value = "${user.getAge()}"/></td>
						<td><c:out value = "${user.getMail()}"/></td>
						<td><c:out value = "${user.getPhone()}"/></td>
						<td class = "w">
							<c:out value="${user.getDelflag()}"/>
						</td>
						
					</tr>
				</c:forEach>
            </tbody>
				</table>
				<a href="ViewAllDepartmentsServlet?code=viewalldepartments" class="btn btn-primary">返回</a>
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