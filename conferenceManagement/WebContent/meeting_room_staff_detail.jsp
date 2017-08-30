<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
	<!-- 左侧可伸缩菜单栏结束 -->
        <div class="col-md-9" style="margin-top: -25px;">
			<div class="page-header">
				<div>
					<h3><strong>与会员工</strong></h3>
				</div>
			</div>
		
		<div>
			<table class="table table-striped table-bordered text-center">
				<thead>
	                <tr>
	                    <th class="text-center">员工姓名</th>
	                </tr>
	            </thead>
	            <tbody>
	            <c:forEach var="staff" items="${requestScope.userList}">
					<tr>
						<td ><c:out value = "${staff}"/></td>
					</tr>
				</c:forEach>
	            </tbody>
			</table>
		</div>	
		<div>
			<a href="ConferenceRoomServlet?code=display" class="btn btn-primary">返回</a>
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