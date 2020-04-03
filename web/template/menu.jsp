<%--  Created by sibingmao  Date: 2020/2/26 15:53 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input id="hiddenName" type="hidden" value="${loginedUser.role}" />
<div class="panel-group" id="menu">
    <div class="panel panel-primary" id="show1">
        <div class="panel-heading">
            <h4 class="panel-title">
               <a data-toggle="collapse" data-parent="#menu"
               href="#div1">用户管理</a>
            </h4>
        </div>
        <div id="div1" class="panel-collapse in">
            <div class="list-group">
                <a href="#"
                   class="list-group-item">查看用户</a>
                <a href="#" class="list-group-item">添加用户</a>
            </div>
        </div>
    </div>
    <div class="panel panel-primary" id="show2">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a class="collapsed" data-toggle="collapse"
                       data-parent="#menu" href="#div2">课程管理</a>
            </h4>
        </div>
        <div id="div2" class="panel-collapse collapse">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/admin/queryCourse.jsp" target="context1" class="list-group-item">查看课程</a>
                <a href="${pageContext.request.contextPath}/admin/addCourse.jsp" target="context1"  class="list-group-item">添加课程</a>
            </div>
        </div>
     </div>
    <div class="panel panel-primary" id="show3">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a class="collapsed" data-toggle="collapse"
                       data-parent="#menu" href="#div3">选课管理</a>
            </h4>
        </div>
        <div id="div3" class="panel-collapse collapse">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/admin/unselectedCourses.jsp" target="context1"  class="list-group-item">未选课程</a>
                <a href="${pageContext.request.contextPath}/admin/selectedCourses.jsp" target="context1"  class="list-group-item">已选课程</a>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
    $(function () {
        var user= '<%= session.getAttribute("loginedUser.role")%>';
        var role = $("#hiddenName").val();
        if(role==1){
            $("#show1").hide();
            $("#show2").hide();
        }
    });
</script>
