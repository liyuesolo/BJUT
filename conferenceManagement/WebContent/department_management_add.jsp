<%@ page language="java" import="java.util.*,conferenceManagement.Entity.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url = "common_header.jsp"></c:import>
<c:import url = "common_left.jsp"></c:import>
    <!-- 左侧可伸缩菜单栏结束 -->
        <div class="col-md-9" style="margin-top: -25px;">
        <div class="page-header">
            <h3><strong>添加部门</strong></h3>
        </div>
        <form class="col-md-8" action = "DepartmentServlet">
            <fieldset class="form-horizontal">
                <legend>填写部门名称</legend>
                <div class="form-group">
                    <label for="name" class="col-md-3 control-label">部门名称：</label>
                    <div class="col-md-8">
                        <input type="text" id="name" placeholder="名称" class="form-control" 
                        name = "department_name"/>
                        <input type="hidden" name="code" value="add">
                    </div>
                </div>
                
            </fieldset>
            <div class="text-center form-group" style="margin-left: 90px;">
                <button type="submit" class="btn btn-success">确定</button>
                <button type="button" class="btn btn-default" onclick="window.location.href='./department_management.jsp'">取消</button>
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