<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
    <!-- 左侧可伸缩菜单栏结束 -->
        <div class="col-md-9" style="margin-top: -25px;">
        <div class="page-header">
            <h3><strong>会议审查</strong></h3>
        </div>
        <table class="table table-striped table-bordered text-center">
            <caption>
                <h4><strong>所有会议申请</strong></h4>
                <ul class="pagination pagination-sm" style="float:left;">
                    <li title="第一页"><a href="ConferenceServlet?code=check">&laquo;</a></li>
                    <li title="上一页"><a href="ConferenceServlet?code=${currentPageNum*(-1)}">&lsaquo;</a></li>
                    <li title="当前页" class="active">
                    <a href="#">
                    	<c:out value="${requestScope.currentPageNum }"/>
                    </a></li>
                    <li title="下一页"><a href="ConferenceServlet?code=${currentPageNum}">&rsaquo;</a></li>
                    <li title="最末页"><a href="ConferenceServlet?code=lastPage">&raquo;</a></li>
                    <li>
                        <a href="">共<strong><c:out value="${requestScope.conferenceNum}"/></strong>条记录，<strong><c:out value="${requestScope.pageNum}"/></strong>页</a>
                    </li>
                </ul>
            </caption>
            <thead>
                <tr>
                    <th class="text-center">会议编号</th>
                    <th class="text-center">主题</th>
                    <th class="text-center">发起者</th>
                    <th class="text-center">参与人数</th>
                    <th class="text-center">申请会议日期</th>
                    <th class="text-center">会议开始时间</th>
                    <th class="text-center">会议结束时间</th>
                    <th class="text-center">操作</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var = "conference" items = "${requestScope.conferenceList}"
            							begin="${currentPageNum%5 - 1 + 4*(currentPageNum-1)}" 
            							end="${currentPageNum%5 + 3 + 4*(currentPageNum-1)}">
            	<tr>
            		<td>${conference.getConferenceid()}</td>
            		<td>${conference.getConferencename()}</td>
            		<td>${conference.getConferenceRaisername()}</td>
            		<td>${conference.getExpectednum()}</td>
            		<td>${conference.getConferenceDate()}</td>
            		<td>${conference.getConferenceStarttime()}</td>
            		<td>${conference.getConferenceEndtime()}</td>
            		<td >
                        <a href="ConferenceServlet?code=approve&conference_id=${conference.getConferenceid()}" class="btn btn-primary btn-sm" onclick="return Agree()">同意</a>
                        <a href="ConferenceServlet?code=disApprove&conference_id=${conference.getConferenceid()}" class="btn btn-danger btn-sm col-md-offset-1" onclick="return Disagree();">不同意</a>
                    </td>
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
<script type="text/javascript">
function Agree(){
	if(confirm("确认同意？")){
		return true;
	}
	else
	{
		return false;
	}
}
function Disagree(){
	if(confirm("确认不同意？")){
		return true;
	}
	else
	{
		return false;
	}
}
</script>
</html>