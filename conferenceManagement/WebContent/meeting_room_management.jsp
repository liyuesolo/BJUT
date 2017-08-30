<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
	<!-- 左侧可伸缩菜单栏结束 -->
        <div class="col-md-9" style="margin-top: -25px;">
				<div class="page-header">
					<div>
						<h3><strong>会议室管理</strong></h3>
					</div>
				</div>

				<!-- 搜索表单开始 -->
				<div>
					<form action ="SearchRoomServlet" method = "post">
						<div class="form-inline">
							<div class="form-group col-md-offset-1">
								<input type="text" placeholder="会议室门牌号" class="form-control" 
								name = "room_id"/>
							</div>
							<div class="form-group">
							<select class="form-control" name = "room_status">
							<c:choose>
								<c:when test = "${requestScope.flag==1 }">
									<c:set var ="status" value="${requestScope.currentRoomStatus}"/>
	                        		<c:if test ="${status==1}">
	                        			<option value="2">全部</option>
										<option value="0">可使用</option>
										<option value="1" selected = "selected">维修中</option>
	                        		</c:if>
	                        		<c:if test ="${status==0}">
	                        			<option value="2">全部</option>
										<option value="0" selected = "selected">可使用</option>
										<option value="1">维修中</option>
	                        		</c:if>
	                        		<c:if test ="${status==2}">
	                        			<option value="2" selected = "selected">全部</option>
										<option value="0">可使用</option>
										<option value="1">维修中</option>
	                        		</c:if>
								</c:when>
								<c:otherwise>
									<option value="2">全部</option>
									<option value="0">可使用</option>
									<option value="1">维修中</option>	
								</c:otherwise>
							</c:choose>
						</select>
							</div>
							<div class="form-group col-md-offset-1">
								<button type="submit" class="btn btn-info">查询</button>
							</div>
						</div>
						<input type="hidden" name="code" value="firstPage"/>
					</form>
				</div>
				<!-- 搜索表单结束 -->

				<div>
					<table class="table table-striped table-bordered text-center">
						<caption>
							<h4><strong>查询结果</strong></h4>
							<c:if test="${requestScope.flag==0 }">
							<ul class="pagination pagination-sm col-md-12">
								<li title="第一页"><a href="ConferenceRoomServlet?code=display">&laquo;</a>
								</li>
								<li title="上一页"><a href="ConferenceRoomServlet?code=${requestScope.currentPageNum*(-1)}">&lsaquo;</a>
								</li>
								<li title="当前页" class="active"><a href="#"><c:out value="${requestScope.currentPageNum}"/></a>
								</li>
								<li title="下一页"><a href="ConferenceRoomServlet?code=${requestScope.currentPageNum}">&rsaquo;</a>
								</li>
								<li title="最末页"><a href="ConferenceRoomServlet?code=lastPage">&raquo;</a>
								</li>
								<li>
									<a href="">共<strong><c:out value="${requestScope.roomNum}"/></strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
								</li>
								<li class="pull-right">

									<div>
										<a href="./meeting_room_management_add.jsp" class="btn btn-success btn-sm">添加会议室</a>
									</div>
								</li>
							</ul>
							</c:if>
							<c:if test="${requestScope.flag==1 }">
							<ul class="pagination pagination-sm col-md-12">
								<li title="第一页"><a href="SearchRoomServlet?code=firstPage&room_id=${requestScope.room_id }&room_status=${requestScope.room_status}">&laquo;</a>
								</li>
								<li title="上一页"><a href="SearchRoomServlet?code=${requestScope.currentPageNum*(-1)}&room_id=${requestScope.room_id }&room_status=${requestScope.room_status}">&lsaquo;</a>
								</li>
								<li title="当前页" class="active"><a href="#"><c:out value="${requestScope.currentPageNum}"/></a>
								</li>
								<li title="下一页"><a href="SearchRoomServlet?code=${requestScope.currentPageNum}&room_id=${requestScope.room_id }&room_status=${requestScope.room_status}">&rsaquo;</a>
								</li>
								<li title="最末页"><a href="SearchRoomServlet?code=lastPage&room_id=${requestScope.room_id }&room_status=${requestScope.room_status}">&raquo;</a>
								</li>
								<li>
									<a href="">共<strong><c:out value="${requestScope.roomNum}"/></strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
								</li>
								<li class="pull-right">

									<div>
										<a href="./meeting_room_management_add.jsp" class="btn btn-success btn-sm">添加会议室</a>
									</div>
								</li>
							</ul>
							</c:if>
						</caption>

						<thead>
							<tr>
								<th class="text-center">门牌号</th>
								<th class="text-center">容纳人数</th>
								<th class="text-center">具体信息</th>
								<th class="text-center">操作</th>
							</tr>
						</thead>

						<tbody>
						<c:forEach var="room" items = "${requestScope.roomList}" 
											begin="${currentPageNum%5 - 1 + 4*(currentPageNum-1)}" 
											end="${currentPageNum%5 + 3 + 4*(currentPageNum-1)}">
							<tr>
								<td class="room_id">${room.getRoomid()}</td>
								<td class="capacity">${room.getRoomcapacity()}</td>
								<td>
									<a href="ConferenceRoomServlet?code=detail&room_id=${room.getRoomid()}" class="btn btn-link">点击产看具体信息</a>
								</td>
								<td >
									<a  class="btn btn-primary btn-sm" href="meeting_room_management_edit.jsp">编辑</a>
									<a  class="btn btn btn-danger btn-sm col-md-offset-1" onclick="deletemeeting(this)">删除</a>
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
function deletemeeting(t)
{
    alert("确定删除？");
    var conference_room_id = $(t).parent().siblings(".room_id").first().text();
	window.location.href="ConferenceRoomServlet?code=delete&conference_room_id="+conference_room_id;
}
</script>
</html>