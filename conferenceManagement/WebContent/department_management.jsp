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

				<!-- 搜索表单开始 -->
				<div>
					<form action="SearchDepartmentServlet">
						<div class="form-inline">
							<div class="form-group" style="margin-left: 3px;">
								<input type="text" placeholder="部门名称" class="form-control" name = "department_name"/>
							</div>
							<input type="hidden" name="code" value="search"/>
							<div class="form-group" style="margin-left: 22px;">
								<button type="submit" class="btn btn-info">查询</button>
							</div>
						</div>
					</form>
				</div>
				<!-- 搜索表单结束 -->

				<div>
					<table class="table table-striped table-bordered text-center">
						<caption>
							<h4><strong>查询结果</strong></h4>
							<c:if test="${requestScope.flag==0 }">
							<ul class="pagination pagination-sm col-md-12">
								<li title="第一页"><a href="ViewAllDepartmentsServlet?code=viewalldepartments">&laquo;</a>
								</li>
								<li title="上一页"><a href="ViewAllDepartmentsServlet?code=${currentPageNum*(-1)}">&lsaquo;</a>
								</li>
								<li title="当前页" class="active"><a href="#"><c:out value="${requestScope.currentPageNum}"/></a>
								</li>
								<li title="下一页"><a href="ViewAllDepartmentsServlet?code=${currentPageNum}">&rsaquo;</a>
								</li>
								<li title="最末页"><a href="ViewAllDepartmentsServlet?code=lastPage">&raquo;</a>
								</li>
								<li>
									<a href="">共<strong>${requestScope.department_num}</strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
								</li>
								<li class="pull-right">
									<div>
										<a href="./department_management_add.jsp" class="btn btn-success btn-sm">添加部门</a>
									</div>
								</li>
							</ul>
 							</c:if>
 							<c:if test="${requestScope.flag==1 }">
							<ul class="pagination pagination-sm col-md-12">
								<li title="第一页"><a href="SearchDepartmentServlet?code=search&department_name=${requestScope.department_name}">&laquo;</a>
								</li>
								<li title="上一页"><a href="SearchDepartmentServlet?code=${currentPageNum*(-1)}&department_name=${requestScope.department_name}">&lsaquo;</a>
								</li>
								<li title="当前页" class="active"><a href="#"><c:out value="${requestScope.currentPageNum}"/></a>
								</li>
								<li title="下一页"><a href="SearchDepartmentServlet?code=${currentPageNum}&department_name=${requestScope.department_name}">&rsaquo;</a>
								</li>
								<li title="最末页"><a href="SearchDepartmentServlet?code=lastPage&department_name=${requestScope.department_name}">&raquo;</a>
								</li>
								<li>
									<a href="">共<strong>${requestScope.departmentNum}</strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
								</li>
								<li class="pull-right">
									<div>
										<a href="./department_management_add.jsp" class="btn btn-success btn-sm">添加部门</a>
									</div>
								</li>
							</ul>
 							</c:if>
						</caption>
						<thead>
							<tr>
								<th class="text-center">部门</th>
								<th class="text-center">成员人数</th>
								<th class="text-center">成员列表</th>
								<th class="text-center">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.departmentsList}" var="department"
										begin="${currentPageNum%5 - 1 + 4*(currentPageNum-1)}" 
            							end="${currentPageNum%5 + 3 + 4*(currentPageNum-1)}">  
					            <tr>  
					                <td class="department_name">${department.getDepartmentname()}</td>  
					                <td>${department.getStaffNumber()}</td>
					                <td>
										<a href="ViewUserInDepartment?department_name=${department.getDepartmentname()}&code=firstPage">点击查看详情</a>
									</td>
					                <td>
					                	<a  class="btn btn-primary btn-sm" href="ConferenceRoomServlet?code=modify&department_name=${department.getDepartmentname()}">编辑</a>
					                	<a class="btn btn btn-danger btn-sm col-md-offset-1" onclick="deleteConfirm(this)">删除</a>
					                </td>  
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
<script>
	function deleteConfirm(t){
		var department_name = $(t).parent().siblings(".department_name").text();
		if(confirm("确定删除么?"))
		{
			var data={department_name:department_name};
			$.post("ValidateDeleteServlet",data,function(result)
			{
				if(result==1)
				{
					alert("当前部门中还有员工，不能被删除!")
				}
				else
				{
					window.location.href="DepartmentServlet?code=delete&"+
							"department_name="+department_name
				}
			})
			
		}
	}
</script>
</html>