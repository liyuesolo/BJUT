<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
    <!-- 左侧可伸缩菜单栏结束 -->


    <div class="col-md-9 main_content">
        <div class="page-header">
            <h3><strong>会议室选择</strong></h3>
        </div>

        <form id="meeting_list" class="col-md-12">
            <fieldset class="form-horizontal">
                <legend>您可以为以下会议选择会议室</legend>
                <table class="table table-striped table-bordered text-center">
                    <thead>
                    <tr>
                       <th class="text-center">会议编号</th>
	                    <th class="text-center">主题</th>
	                    <th class="text-center">发起者</th>
	                    <th class="text-center">参与人数</th>
	                    <th class="text-center">申请会议日期</th>
	                    <th class="text-center">会议开始时间</th>
	                    <th class="text-center">会议结束时间</th>
                        <th class="text-center"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var = "conference" items="${requestScope.conferenceList}">
                    <tr id="meeting_one">
                        <td id = "conf_id">${conference.getConferenceid()}</td>
	            		<td id ="conf_name">${conference.getConferencename()}</td>
	            		<td>${conference.getConferenceRaisername()}</td>
	            		<td id="expected_num"><c:set var="expected_num" value="${conference.getExpectednum()}"></c:set>${conference.getExpectednum()}</td>
	            		<td id="conference_date">${conference.getConferenceDate()}</td>
	            		<td id="conference_starttime">${conference.getConferenceStarttime()}</td>
	            		<td id="conference_endtime">${conference.getConferenceEndtime()}</td>
                        <td><a class="btn btn-primary btn-sm" id="meeting_room_choose_btn">选择会议室</a></td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </fieldset>
        </form>

        <form id="meeting_room_choose_one" class="col-md-12">
            <fieldset class="form-horizontal">
                <legend>以下会议室满足条件</legend>
                    <table class="table table-striped table-bordered text-center">
                        <thead>
                        <tr>
                            <th class="text-center">门牌号</th>
                            <th class="text-center">容纳人数</th>
                            <th class="text-center">选择</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var = "room" items = "${requestScope.roomList}">
             			<c:if test="${expected_num<=room.getRoomcapacity()}">
	            			<c:if test="${room.getRoomstatus() == 0}">
			           			<tr>
			                          <td id="room_id">${room.getRoomid()}</td>
			                          <td>${room.getRoomcapacity()}</td>
			                          <td>
			                          	<a class="btn btn-primary btn-sm" id="meeting_room_sure_btn">选择</a>
			                          </td>
			                     </tr>
			                </c:if>
             			</c:if>
                        
                        </c:forEach>
                        </tbody>
                    </table>
            </fieldset>

        </form>

        <form id="meeting_time_success" class="col-md-12" action = "ConferenceRoomServlet">
            <fieldset class="form-horizontal">
                <legend>会议室确认</legend>
                <div class="form-group">
                    <label for="meeting_num" class="col-md-3 control-label">会议编号：</label>
                    <div class="col-md-8">
                        <p class="text-primary" id="conf_id_confirm"></p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="meeting_name" class="col-md-3 control-label">会议名称：</label>
                    <div class="col-md-8">
                        <p class="text-primary" id = "conf_name_confirm" ></p>
                        <input type="hidden" name = "conf_name" id="conf_name_confirm_hidden"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-md-3 control-label">起止时间：</label>
                    <div class="col-md-8">
                        <p class="text-primary" id="date_time"></p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="start_time" class="col-md-3 control-label">会议室：</label>
                    <div class="col-md-8">
                        <p class="text-primary" id="room_id_confirm"></p>
                        <input type="hidden" name = "room_id" id="room_id_confirm_hidden"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="start_time" class="col-md-3 control-label">参会人数：</label>
                    <div class="col-md-8">
                        <p class="text-primary" id="expected_num_confirm"></p>
                    </div>
                </div>
            </fieldset>
            <input type="hidden" name="code" value="update"/>
            <div class="text-center form-group">
                <button type="submit" class="btn btn-primary col-md-offset-1 back_to_list_btn">确认</button>
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
<script src="lib/scripts/jquery-1.11.0.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<!-- <script src="lib/scripts/bootbox.min.js"></script> -->
<script type="text/javascript">


$(function () 
{
	var conf_id;
	var conf_name;
	var room_id;
	var expected_num;
	var conference_date;
	var conference_starttime;
	var conference_endtime
    $("#meeting_room_choose_btn").click(function () {
        $("#meeting_list").hide();
        $("#meeting_room_choose_one").show();
        conf_id = $("#conf_id").html();
        conf_name = $("#conf_name").html();
        expected_num = $("#expected_num").html();
        conference_date = $("#conference_date").html();
    	conference_starttime = $("#conference_starttime").html();
    	conference_endtime = $("#conference_endtime").html();
    });

    $(".back_to_list_btn").click(function () {
        $("#meeting_list").show();
        $("#meeting_room_choose_one").hide();
        $("#meeting_time_choose").hide();
        $("#meeting_time_success").hide();
        
    });
    $("#meeting_room_sure_btn").click(function () 
    		{
    	room_id = $("#room_id").html();
    	$("#conf_id_confirm").html(conf_id);
    	$("#conf_name_confirm").html(conf_name);
    	$("#conf_name_confirm_hidden").val(conf_name);
    	$("#room_id_confirm_hidden").val(room_id);
    	$("#date_time").html(conference_date + ' '+conference_starttime+' ~ '+conference_date+' '+conference_endtime);
        $("#room_id_confirm").html(room_id);
        $("#expected_num_confirm").html(expected_num);
    	$("#meeting_list").hide();
        $("#meeting_room_choose_one").hide();
        $("#meeting_time_success").show();
        
    })


});


</script>
</html>