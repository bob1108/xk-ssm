<%--  Created by sibingmao  Date: 2020/2/26 15:52 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row" style="background-color: #006AC2;margin-bottom: 3px;">
    <div class="col-md-6 logo">高校选课系统</div>
    <div class="col-md-2 col-md-offset-4">
        <p>欢迎你：<span class="label label-info">${loginedUser.realname}</span></p>
        <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-danger">退出登录</a>
    </div>
</div>

