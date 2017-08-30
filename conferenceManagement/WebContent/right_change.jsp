<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url = "common_header.jsp"></c:import>
<c:import url = "common_left.jsp"></c:import>
<script type="text/javascript">
function display() {
	$.ajax({
		type : "POST",
		url : "RightManagementServlet",
		data : { user_name : $("#user_name").val(), privilege: $("#privilege").val()},
		success : function(message) {
			var success = $("#success");
			var data = JSON.parse(message);
			success.html(data.msg);
			success.css({
				color : "green"
			});

		}
	});
}
</script>
    <!-- 左侧可伸缩菜单栏结束 -->
        <div id="content_panel" class="col-md-9" >
            		<div class="page-header">
						<h3><strong>选择被更改的用户及权限</strong></h3>
						<div id="success" ></div>
					</div>
					
                    <div>
                        <label class="radio-inline">
                            <input id="user_name" name="user_name" class="form-control" type="text" placeholder="请输入用户名" />
                        </label>
                        <select id="privilege" name="privilege">
       						<option value="0">管理员</option>
       						<option value="1">部门经理</option>
    						<option value="2">普通用户</option>
        				</select>
        				<button onclick= "display()" class="btn btn-success">更改</button>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                         
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