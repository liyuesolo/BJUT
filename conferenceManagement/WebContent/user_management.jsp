<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>

    <!-- 左侧可伸缩菜单栏结束 -->
        <div id="content_panel" class="col-md-9">
        <div class="page-header">
            <h3><strong>用户信息管理</strong></h3>
        </div>
        <!-- 搜索表单开始 -->
		<div>
			<form action = "SearchUserServlet">
				<div class="form-inline  ">
					<div class="form-group" style="margin-left: 3px;">
						<input type="text" placeholder="用户名" class="form-control" name="user_name"/>
					</div>
					<div class="form-group" style="margin-left: 4px;">
						<select class="form-control" name = "user_status">
							<c:choose>
								<c:when test = "${requestScope.flag==1 }">
									<c:set var ="status" value="${requestScope.currentUserStatus}"/>

	                        		<c:if test ="${status== 0}">
	                        			<option value="2">全部</option>
										<option value="1">已审批</option>
										<option value="0" selected = "selected">未审批</option>
	                        		</c:if>
	                        		<c:if test ="${status== 1}">
	                        			<option value="2">全部</option>
										<option value="1" selected = "selected">已审批</option>
										<option value="0">未审批</option>
	                        		</c:if>
	                        		<c:if test ="${status== 2}">
		                        		<option value="2" selected = "selected">全部</option>
										<option value="1">已审批</option>
										<option value="0">未审批</option>
	                        		</c:if>
								</c:when>
								<c:otherwise>
									<option value="2">全部</option>
									<option value="1">已审批</option>
									<option value="0">未审批</option>
								</c:otherwise>
							</c:choose>
							
						</select>
						<select class="form-control" name = "del_flag">
							<c:choose>
								<c:when test = "${requestScope.flag==1 }">
									<c:set var ="status" value="${requestScope.currentDelflag}"/>

	                        		<c:if test ="${status== 0}">
	                        			<option value="2">全部</option>
										<option value="1">已停用</option>
										<option value="0" selected = "selected">未停用</option>
	                        		</c:if>
	                        		<c:if test ="${status== 1}">
	                        			<option value="2">全部</option>
										<option value="1" selected = "selected">已停用</option>
										<option value="0">未停用</option>
	                        		</c:if>
	                        		<c:if test ="${status== 2}">
		                        		<option value="2" selected = "selected">全部</option>
										<option value="1">已停用</option>
										<option value="0">未停用</option>
	                        		</c:if>
								</c:when>
								<c:otherwise>
									<option value="2">全部</option>
									<option value="1">已停用</option>
									<option value="0">未停用</option>	
								</c:otherwise>
							</c:choose>
							
						</select>
						<select class="form-control" name = "department_name">
							<c:choose>
							<c:when test="${requestScope.flag == 0 }">
							<option>
                    			<c:out value="全部部门"></c:out>
                    		</option>
							<c:forEach var="department" items="${requestScope.departmentsList}">
							<option>
                    			<c:out value="${department.getDepartmentname()}"></c:out>
                    		</option>
                    		</c:forEach>
							</c:when>
							<c:otherwise>
							<c:set var="dp" scope="session" value="${requestScope.currentDepartmentname}"></c:set>
		                    	<option><c:out value="全部部门"></c:out></option>
		                    	<c:forEach var="dpn" items="${requestScope.departmentsList}">
		                    		<c:choose>
		                    			<c:when test="${dp== dpn.getDepartmentname()}">
		                    				<option selected="selected">
		                    					<c:out value = "${dp}"/>
		                    				</option>
		                    			</c:when>
		                    			<c:otherwise>
		                    				<option>
		                    					<c:out value="${dpn.getDepartmentname()}"/>
		                    				</option>
		                    			</c:otherwise>
		                    		</c:choose>
		                    	</c:forEach>
							</c:otherwise>
							</c:choose>
							
						</select>
					</div>
					
					<div class="form-group" style="margin-left: 22px;">
						<button type="submit" class="btn btn-info">查询</button>
					</div>
				</div>
				<div><input name="code" value = "search" hidden="hidden"></div>
			</form>
		</div>
				<!-- 搜索表单结束 -->
    	<div class="col-md-12">
        	<table class="table table-striped table-bordered text-center">
            	<caption>
            	    <h4><strong>所有用户信息</strong></h4>
            	    <c:if test="${requestScope.flag==0 }">
            	    <ul class="pagination pagination-sm" style="float:left;">
                  		  <li title="第一页"><a href="ViewAllUserServlet?code=manage">&laquo;</a></li>
                  		  <li title="上一页"><a href="ViewAllUserServlet?code=${currentPageNum*(-1)}">&lsaquo;</a></li>
                  		  <li title="当前页" class="active">
                  		  	<a href="ViewAllUserServlet?code=manage">
								<c:out value="${requestScope.currentPageNum }"/>
							</a>
						  </li>
                  		  <li title="下一页"><a href="ViewAllUserServlet?code=${currentPageNum}">&rsaquo;</a></li>
                  		  <li title="最末页"><a href="ViewAllUserServlet?code=manageLastPage">&raquo;</a></li>
                   		  <li>
                        	<a href="">共<strong>
                        		<c:out value="${requestScope.userNum }"/>
                        		</strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
                    	  </li>
                	</ul>
            	    </c:if>
            	    <c:if test="${requestScope.flag==1 }">
            	    <ul class="pagination pagination-sm" style="float:left;">
                  		  <li title="第一页"><a href="SearchUserServlet?code=search&user_name=${requestScope.currentUsername}&user_status=${requestScope.currentUserStatus}&del_flag=${currentDelflag}&department_name=${currentDepartmentname}">&laquo;</a></li>
                  		  <li title="上一页"><a href="SearchUserServlet?code=${currentPageNum*(-1)}&user_name=${requestScope.currentUsername}&user_status=${requestScope.currentUserStatus}&del_flag=${currentDelflag}&department_name=${currentDepartmentname}">&lsaquo;</a></li>
                  		  <li title="当前页" class="active">
                  		  	<a href="SearchUserServlet?code=search&user_name=${requestScope.currentUsername}&user_status=${requestScope.currentUserStatus}&del_flag=${currentDelflag}&department_name=${currentDepartmentname}">
								<c:out value="${requestScope.currentPageNum }"/>
							</a>
						  </li>
                  		  <li title="下一页"><a href="SearchUserServlet?code=${currentPageNum}&user_name=${requestScope.currentUsername}&user_status=${requestScope.currentUserStatus}&del_flag=${currentDelflag}&department_name=${currentDepartmentname}">&rsaquo;</a></li>
                  		  <li title="最末页"><a href="SearchUserServlet?code=searchLastPage&user_name=${requestScope.currentUsername}&user_status=${requestScope.currentUserStatus}&del_flag=${currentDelflag}&department_name=${currentDepartmentname}">&raquo;</a></li>
                   		  <li>
                        	<a href="">共<strong>
                        		<c:out value="${requestScope.userNum }"/>
                        		</strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
                    	  </li>
                	</ul>
            	    </c:if>
               		
            	</caption>
           	 <thead>
                <tr>
                    <th class="text-center">用户名</th>
                    <th class="text-center">真实姓名</th>
                    <th class="text-center">性别</th>
                    <th class="text-center">年龄</th>
                    <th class="text-center">邮箱</th>
                    <th class="text-center">手机号</th>
                    <th class="text-center">部门</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="user" items="${requestScope.userList}" 
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
						<td class="dep">
	                    	<select id="a00" class="form-control" disabled="disabled">
		                    	<c:set var="dp" scope="session" value="${user.getDepartmentname()}"></c:set>
		                    	<c:forEach var="dpn" items="${requestScope.departmentsList}">
		                    		<c:choose>
		                    			<c:when test="${dp== dpn.getDepartmentname()}">
		                    				<option selected="selected">
		                    					<c:out value = "${dp}"/>
		                    				</option>
		                    			</c:when>
		                    			<c:otherwise>
		                    				<option>
		                    					<c:out value="${dpn.getDepartmentname()}"/>
		                    				</option>
		                    			</c:otherwise>
		                    		</c:choose>
		                    	</c:forEach>
                 			</select>
                    	</td>
						<td class = "w">
							<select id="a01" class="form-control" disabled="disabled">
	                        	<c:set var="df" scope="session" value="${user.getDelflag()}"></c:set>
	                        	<c:choose>
	                        		<c:when test ="${df== 0}">
	                        			<option selected="selected">在职</option>
	                        			<option >离职</option>
	                        		</c:when>
	                        		<c:otherwise>
		                        		<option>在职</option>
		                        		<option selected="selected">离职</option>
	                        		</c:otherwise>
	                        	</c:choose>
                    		</select>
						</td>
						<td><a id="modify_0" class="btn btn-danger btn-sm" onclick="update(this)">修改</a>
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

function update(button){
	if($(button).text()=="保存")
	{
		var user_name = $(button).parent().siblings(".user_name").first().text();
		var department_name=$(button).parent().siblings(".dep").first().children("#a00").first().val();
		var del_flag=$(button).parent().siblings(".w").first().children("#a01").first().val();
		$(button).parent().siblings(".dep").first().children("#a00").first().attr("disabled","disabled");
		$(button).parent().siblings(".w").first().children("#a01").first().attr("disabled","disabled");
		$(button).text("修改");
		var data={user_name:user_name,department_name:department_name,del_flag:del_flag};
		$.post("UpdateUserInfoServlet",data,function(result)
		{
			alert("修改成功！");
			window.location.href = "ViewAllUserServlet?code=manage";
		});
	}else if($(button).text()=="修改"){
		$(button).parent().siblings(".dep").first().children("#a00").first().removeAttr("disabled");
		$(button).parent().siblings(".w").first().children("#a01").first().removeAttr("disabled");
		$(button).text("保存");
	}
}
</script>
</html>