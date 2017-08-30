<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
	<!-- 左侧可伸缩菜单栏结束 -->
        <div class="col-md-9" style="margin-top: -25px;">
			<div class="page-header">
				<div>
					<h3><strong>详细信息</strong></h3>
				</div>
			</div>
		
		<div>
		<c:choose>
			
				<c:when test = "${requestScope.hasConference == 1}">
					<table class="table table-striped table-bordered text-center">
						<thead>
			                <tr>
			                    <th class="text-center">会议名称</th>
			                    <th class="text-center">发起人</th>
			                    <th class="text-center">会议日期</th>
			                    <th class="text-center">会议开始时间</th>
			                    <th class="text-center">会议结束时间</th>
			                    <th class="text-center">参会人员</th>
			                </tr>
			            </thead>
			            <tbody>
							<tr>
								<td ><c:out value = "${requestScope.conference_name}"/></td>
								<td ><c:out value = "${requestScope.conference_raiser_name}"/></td>
								<td ><c:out value = "${requestScope.conference_date}"/></td>
								<td ><c:out value = "${requestScope.start_time}"/></td>
								<td ><c:out value = "${requestScope.end_time}"/></td>
								<td>
									<a href="ConferenceServlet?code=staff&conference_id=${requestScope.conference_id}" class="btn btn-link">点击产看具体信息</a>
								</td>
							</tr>
			            </tbody>
					</table>
				</c:when>
				<c:otherwise>
					<c:out value="无会议内容"></c:out>
				</c:otherwise>
		
		</c:choose>	
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