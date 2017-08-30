<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
        <!-- 左侧可伸缩菜单栏结束 -->
        <div id="content_panel" class="col-md-9">
			<div class="page-header">
				<h3><strong>会议申请</strong></h3>
			</div>
			<form class="col-md-8" action = "MeetingApplyServlet" method = "post" name="meeting_apply">
            <fieldset id="content_fieldset">
                <legend>填写申请信息</legend>
                <div class="form-group">
                    <label for="conferenceName">会议名称</label>
                    <input type="text" id="conferenceName" placeholder="会议名称" class="form-control" name="conference_name" />
                </div>
                <div class="form-group">
                    <label for="raiserName">发起人姓名</label>
                    <input type="text" id="raiserName" placeholder="发起人用户名" class="form-control" name="conference_raiser_name" />
                </div>
                <div class="form-group">
                    <label for="date">预期会议日期</label>
                    <input type="date" id="date" placeholder="yyyy-mm-dd" class="form-control" name="date"/>
                </div>
                <div class="form-group">
                    <label for="expected_num">参加人数</label>
                    <input type = "text" id="expected_num" class="form-control" name = "expected_num"/>
                </div>
                <div class="form-group">
                    <label for="start_time">会议开始时间</label>
                    <input type="text" id="start_time" placeholder="hh:mm:ss" class="form-control" name="start_time"/>
                </div>
                <div class="form-group">
                    <label for="end_time">会议结束时间</label>
                    <input type="text" id="end_time" placeholder="hh:mm:ss" class="form-control" name="end_time"
                    onchange="timeCheck()" onblur="checkValid()"/>
                </div>
                <div class="form-group">
                    <label>会议简介</label>
                    <input type="text" id="brief" placeholder="必填，不少于50字" class="form-control" name="conference_info"/>
                </div>
                <div class="form-group">
                    <label for="gender">参会人员</label>
                    <div class="form-group" >
                        <select class="form-control" id="department" onchange="selectFromDepartment(this.options[this.options.selectedIndex].value)" >
                        <option value="" selected>请选择部门</option>
                        <c:forEach var="department"
							items="${requestScope.departmentsList}">
							<c:if test="${department.getDepartmentname() == param.department_name}">
								<option value="${department.getDepartmentname()}" selected>${department.getDepartmentname()}</option>
							</c:if>
							<c:if test="${department.getDepartmentname()!= param.department_name}">
								<option value="${department.getDepartmentname()}">${department.getDepartmentname()}</option>
							</c:if>
						</c:forEach>
                        </select>
                    </div>
                    <%-- <div  id="invited">
                  		<c:forEach var = "user" items="${requestScope.userList}">
                  			<label class="radio-inline">
                  				<input name="invited" type="checkbox" value="${user.getUsername()}"/>
                  				<c:out value="${user.getUsername()}"/>
                  			</label>
                  		</c:forEach>
                    </div> --%>
                    <div id="divfrom">
                        <select id="users" multiple="multiple">
                        </select>
                    </div>
                    <div id="divoperator">
                        <input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                        <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                    </div>
                    <div id="divto">
                        <select id="selectedUsers" name="invited" multiple="multiple">
                        </select>
                    </div>
                </div>
            </fieldset>
            <div id="button_panel" class="text-center form-group">
                 <button type="button" class="btn btn-primary" onclick="checkinfor()">申请</button>
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
		<style type="text/css">
            #divfrom{
                float:left;
                width:150px;
            }
            #divto{
                float:left;
                width:150px;
            }
            #divoperator{
                float:left;
                width:50px;
                padding:60px 5px;
            }
            #divoperator input[type="button"]{
                margin:10px 0;
            }
            #selDepartments{
                display:block;
                width:100%;
            }
            #users{
                display:block;
                width:100%;
                height:200px;
            }
            #selectedUsers{
                display:block;
                width:100%;
                height:200px;
            }
        </style>
		<script src="./lib/scripts/jquery-1.11.0.min.js"></script>
		<script src="./lib/bootstrap/js/bootstrap.min.js"></script>
		<script src="./lib/scripts/bootbox.min.js"></script>
		<script>
		var userNames;
		var users;
		var selectedUsers;
		var length;
		users = document.getElementById("users");
		selectedUsers = document.getElementById("selectedUsers");
		function selectFromDepartment(department_name)
		{
			userNames = new Array();
			
			$.ajax({
				type : "POST",
				async:false,
				url : "ReturnUserInDepartmentServlet",
				data:{department_name : department_name},
				success : function(data) 
				{
					for (var i = 0; i < data.length; i++) 
					{
						length = userNames.push(data[i]);
					}
				},
				dataType:"json"
			});
			clearList(users);
			for(i=0;i<length;i++){
                var user = document.createElement("option");
                user.value = userNames[i];
                user.text = userNames[i];
                users.appendChild(user);
            }
		}
		function clearList(list)
		{
            while(list.childElementCount > 0){
                list.removeChild(list.lastChild);
            }
        }
		function selectEmployees()
		{
            for(var i=0;i<users.options.length;i++)
            {
                if (users.options[i].selected)
                {
                    addEmployee(users.options[i]);
                    users.options[i].selected = false;
                }
            }
        }
		function addEmployee(optEmployee)
		{
            var options = selectedUsers.options;
            var i = 0;
            var insertIndex = -1;
            while(i < options.length)
            {
                
            	if (optEmployee.value == options[i].value)
                {
                    return;
                } else if (optEmployee.value < options[i].value) 
                {
                    insertIndex = i;
                    break;
                }
                i++;
            }
            var opt = document.createElement("option");
            opt.value = optEmployee.value;
            opt.text = optEmployee.text;
            
            if (insertIndex == -1)
            {
            	selectedUsers.appendChild(opt);
            } else {
            	selectedUsers.insertBefore(opt, options[insertIndex]);
            }
        }
		function deSelectEmployees(){
            var elementsToRemoved = new Array();
            var options = selectedUsers.options;
            for(var i=0;i<options.length;i++){
                if (options[i].selected)
                {
                    elementsToRemoved.push(options[i]);
                }
            }
            for(i=0;i<elementsToRemoved.length;i++)
            {
            	selectedUsers.removeChild(elementsToRemoved[i]);
            }
        }
		
		function checkinfor()
		{
			var conference_name = document.getElementById("conferenceName").value;
			var conference_raiser_name = document.getElementById("raiserName").value;
			var date = document.getElementById("date").value;
			var expected_num = document.getElementById("expected_num").value;
			var start_time = document.getElementById("start_time").value;
			var end_time = document.getElementById("end_time").value;
			var conference_info = document.getElementById("brief").value;
			var a=selectedUsers.length;

			for(var i=0;i<a;i++)
			{
				selectedUsers.options[i].selected = true;
			}
			if(!(conference_name&&conference_raiser_name&&date&&start_time&&end_time&&expected_num&&conference_info))
			{
				alert("请输入完整信息!");
			}
			else if(a==0)
			{
				alert("请选择参会人员！");
			}
			else if(confirm("确认申请？"))
			{
				document.meeting_apply.submit();
			}
		}
		
		</script>
		<script>
		function checkValid()
		{
			var date = document.getElementById("date").value;
			var start_time = document.getElementById("start_time").value;
			var end_time = document.getElementById("end_time").value;
			start_time = (Date.parse(date + "T" +start_time+"Z"));
			end_time = (Date.parse(date + "T" +end_time+"Z"));
			if(start_time > end_time)
			{
				alert("结束时间应在开始时间之后！");
			}
			
		}
		</script>
	</body>
</html>
