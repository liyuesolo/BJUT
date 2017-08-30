<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
    <!-- 左侧可伸缩菜单栏结束 -->
		  <div class="col-md-9" style="margin-top:-25px">
        <div class="page-header">
            <h3><strong>会议信息列表</strong></h3>
        </div>
        <table class="table table-striped table-bordered">
            <caption>
            	<c:if test="${requestScope.flag==0 }">
                <ul class="pagination pagination-sm" style="float:left;">
                    <li title="第一页"><a href="ConferenceInfoServlet?code=display&conference_status=${requestScope.conference_status }">&laquo;</a></li>
                    <li title="上一页"><a href="ConferenceInfoServlet?code=${currentPageNum*(-1)}&conference_status=${requestScope.conference_status }">&lsaquo;</a></li>
                    <li title="当前页" class="active"><a href="#"><c:out value="${requestScope.currentPageNum}"/></a></li>
                    <li title="下一页"><a href="ConferenceInfoServlet?code=${currentPageNum}&conference_status=${requestScope.conference_status }">&rsaquo;</a></li>
                    <li title="最末页"><a href="ConferenceInfoServlet?code=lastPage&conference_status=${requestScope.conference_status }">&raquo;</a></li>
                    <li>
                       	<a href="">共<strong>${requestScope.conferenceNum}</strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
                    </li>
                </ul>
                </c:if>
                <c:if test="${requestScope.flag==1}">
                <ul class="pagination pagination-sm" style="float:left;">
                    <li title="第一页"><a href="SearchConferenceServlet?code=display&conference_status=${requestScope.conference_status }">&laquo;</a></li>
                    <li title="上一页"><a href="SearchConferenceServlet?code=${currentPageNum*(-1)}&conference_status=${requestScope.conference_status }">&lsaquo;</a></li>
                    <li title="当前页" class="active"><a href="#"><c:out value="${requestScope.currentPageNum}"/></a></li>
                    <li title="下一页"><a href="SearchConferenceServlet?code=${currentPageNum}&conference_status=${requestScope.conference_status }">&rsaquo;</a></li>
                    <li title="最末页"><a href="SearchConferenceServlet?code=lastPage&conference_status=${requestScope.conference_status }">&raquo;</a></li>
                    <li>
                       	<a href="">共<strong>${requestScope.conferenceNum}</strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
                    </li>
                </ul>
                </c:if>
                <form action="SearchConferenceServlet">
                <div style="float:right;padding:5px">
					<select id="state" class="form-control" name = "conference_status">
					<c:choose>
					<c:when test="${requestScope.flag==1 }">
						<c:choose>
						<c:when test="${requestScope.conference_status == 0}">
							<option selected="selected" value="0">全部会议</option>
						</c:when>
						<c:otherwise>
							<option value="0">全部会议</option>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${requestScope.conference_status == 1}">
							<option selected="selected" value="1">未审核</option>
						</c:when>
						<c:otherwise>
							<option  value="1">未审核</option>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${requestScope.conference_status == 2}">
							<option selected="selected" value="2">审核未通过</option>
						</c:when>
						<c:otherwise>
							<option  value="2">审核未通过</option>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${requestScope.conference_status == 3}">
							<option selected="selected" value="3">未申请会议室</option>
						</c:when>
						<c:otherwise>
							<option  value="3">未申请会议室</option>
						</c:otherwise>
						</c:choose>
						<c:choose>
						<c:when test="${requestScope.conference_status == 4}">
							<option selected="selected" value="4">已申请会议室</option>
						</c:when>
						<c:otherwise>
							<option  value="4">已申请会议室</option>
						</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
					    <option selected="selected" value="0">全部会议</option>
						<option value="1">未审核</option>
						<option  value="2">审核未通过</option>
						<option  value="3">未申请会议室</option>
						<option  value="4">已申请会议室</option>
					</c:otherwise>
					</c:choose>
					</select>
                </div>
                <input type ="hidden" value="display" name="code"/>
                 <div class="form-group" style="margin-left: 22px;float:right;margin-top:6px;">
					<button type="submit" class="btn btn-info">查询</button>
				</div>
                </form>
               
            </caption>
            <thead>
                <tr>
                    <th>编号</th>
                    <th>会议名称</th>
                    <th>会议发起人</th>
                    <th>会议起止时间</th>
                    <th>会议状态</th>
					<th>是否本人拥有</th>
					<c:if test="${requestScope.privilege < 2}">
						<th>操作</th>
					</c:if>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="conference" items="${requestScope.conferenceList}"
            							begin="${currentPageNum%5 - 1 + 4*(currentPageNum-1)}" 
            							end="${currentPageNum%5 + 3 + 4*(currentPageNum-1)}">
            	<tr>
            		<td><c:out value="${conference.getConferenceid()}"></c:out></td>
            		<td><c:out value="${conference.getConferencename()}"></c:out></td>
            		<td><c:out value="${conference.getConferenceRaisername()}"></c:out></td>
            		<td>${conference.getConferenceDate()} ${conference.getConferenceStarttime()} ~ ${conference.getConferenceEndtime()}</td>
            		<td>
            		<c:if test="${conference.getConferencestatus() == 0}">
            			<c:out value="未审查"/>
            		</c:if>
            		<c:if test="${conference.getConferencestatus() == 2}">
            			<c:out value="未通过"/>
            		</c:if>
            		<c:if test="${conference.getConferencestatus() == 1}">
            			<c:choose>
            			<c:when test="${conference.getConferenceroomid() == 0}">
            				<c:out value="未申请会议室"/>
	            		</c:when>
	            		<c:otherwise>
	            			<c:out value="已申请会议室"/>
	            		</c:otherwise>
	            		</c:choose>
            		</c:if>
            		</td>
            		<td>
            		<c:forEach var="uc" items="${requestScope.userConferenceList}">
            			<c:if test="${conference.getConferenceid() == uc}">
            				<img src="img/check.png" id="check_size"/>
            			</c:if>
            		</c:forEach>
            		</td>
            		<c:if test="${requestScope.privilege < 2}">
            		<td>
						<a class="btn btn btn-danger btn-sm col-md-offset-1" href="ConferenceServlet?code=cancel&conference_id=${conference.getConferenceid()}">取消</a>
					</td>
					</c:if>
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
</html>