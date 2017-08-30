<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="./common_header.jsp"></c:import>
<c:import url="./common_left.jsp"></c:import>
    <!-- 左侧可伸缩菜单栏结束 -->
        <div id="PersonalInformationDiv" class="col-md-9">
           <div class="page-header">
            <h3><strong>个人信息详情</strong></h3>
        </div>
        <form class="col-md-9">
            <fieldset class="form-horizontal">
                <legend>填写个人信息</legend>
                <div class="form-group">
                    <label for="name">真实姓名：</label>
                    <input type="text" id="name" value="${requestScope.currentUser.getUserid()}" class="form-control" disabled="disabled"/>
                </div>
                <div class="form-group">
                    <label for="user_name">用户名：</label>
                    <input type="text" id="user_name" value="${requestScope.currentUser.getUsername()}" class="form-control" disabled="disabled"/>
                </div>
				<div class="form-group">
                    <label for="age">年龄：</label>
                    <input type="text" id="age" value="${requestScope.currentUser.getAge()}" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="gender">性别：</label>
                    <div>
                        <label class="radio-inline">
                           <input id="male" name="gender" type="radio" value="1" checked="checked" disabled="disabled"/>
                            
                            <c:choose>
                            	<c:when test="${requestScope.currentUser.getGender()==0}">
                            		<c:out value="男"/>
                            	</c:when>
                            	<c:otherwise>
                            		<c:out value="女"/>
                            	</c:otherwise>
                            </c:choose>
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="department">部门：</label>
                    <select id="department" class="form-control" disabled="disabled">
                        <option selected="selected"><c:out value="${requestScope.currentUser.getDepartmentname()}"/></option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="password">密码：</label>
                    <input type="password" id="password" value="${requestScope.currentUser.getPassword()}" class="form-control" />
                </div>
				<div class="form-group">
                    <label for="password">确认修改密码：</label>
                    <input type="password" id="confirmpassword" value="${requestScope.currentUser.getPassword()}" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="email">邮箱：</label>
                    <input type="text" id="email" value="${requestScope.currentUser.getMail()}" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="tel">电话：</label>
                    <input type="text" id="tel" value="${requestScope.currentUser.getPhone()}" class="form-control" />
                </div>
            </fieldset>
            <div class="text-center form-group">
                <button type="button" class="btn btn-primary" onclick="sure()">确定</button>
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
</body>
<script src="lib/scripts/jquery-1.11.0.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script src="lib/scripts/bootbox.min.js"></script>
<script src="js/profile.js"></script>
<script>
function sure(){
	var password = document.getElementById("password").value;
	var mail = document.getElementById("email").value;
	var phone = document.getElementById("tel").value;
	var age = document.getElementById("age").value;
	var user_name = document.getElementById("user_name").value;
	var data = 
	{		password:password,
			mail:mail,
			phone:phone,
			user_name:user_name,
			age:age};
	$.post("UpdateProfileServlet?code=update",data,function(result)
			{
		alert("修改成功！");
	});
}
</script>
</html>