<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
    <!-- 左侧可伸缩菜单栏结束 -->
         <div class="col-md-9" style="margin-top: -25px;">
        <div class="page-header">
            <h3><strong>会议取消确认</strong></h3>
        </div>
        <form class="col-md-8" action = "ConferenceServlet">
            <fieldset class="form-horizontal">
                <legend>填写部</legend>
                <div class="form-group">
                	 <div class="form-group">
                    	<label for="start_time" class="col-md-3 control-label">会议名称：</label>
	                    <div class="col-md-8">
	                        <input class="text-primary"  name="conference_name" value="${requestScope.conference_name }"/>
	                    </div>
                	</div>
                    <br/>
                    <label for="name" class="col-md-3 control-label">会议取消原因：</label>
                    <div class="col-md-8">
                        <input type="text" id="name" placeholder="会议取消原因" class="form-control" 
                        name = "cancel_reason"/>
                    </div>
                </div>
                <input type="hidden" name = "code" value="cancel_confirm"/>
                <input type="hidden" name = "conference_id" value="${requestScope.conference_id}"/>
            </fieldset>
            <div class="text-center form-group" style="margin-left: 90px;">
                <button type="submit" class="btn btn-success">确定取消</button>
                <a href="ConferenceInfoServlet?code=display" class="btn btn-primary">取消</a>
            </div>
        </form>
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