<%--  Created by sibingmao  Date: 2020/2/25 11:39 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录界面</title>
    <%--  <link href="favicon.ico" rel="shortcut icon" />--%>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <style>
        #myModalLabel{
            color: #006AC2;
            font-family: 华文彩云;
            font-size: 36px;
            text-shadow: 1px 1px 1px white;
        }
    </style>
</head>
<body style=" background: #006AC2 no-repeat center center fixed; background-size: 100%;">


<div class="modal-dialog" style="margin-top: 10%;">
    <form action="${pageContext.request.contextPath}/user/login" method="post" class="form-horizontal">
        <div class="modal-content" style="padding-top: 20px;padding-bottom: 20px;">
            <div class="modal-header">

                <h2 class="modal-title text-center" id="myModalLabel">高校选课系统</h2>
            </div>
            <div class="modal-body" id = "model-body">
                <div class="form-group" style="margin-left: 20px;margin-right: 20px;">

                    <input type="text" class="form-control"placeholder="用户名" autocomplete="off" name="username">
                </div>
                <div class="form-group" style="margin-left: 20px;margin-right: 20px;">

                    <input type="password" class="form-control" placeholder="密码" autocomplete="off" name="password">
                </div>
            </div>
            <div class="modal-footer">
                <div class="form-group" style="margin-left: 20px;margin-right: 20px;">
                    <button type="submit" class="btn btn-primary form-control">登录</button>
                </div>
                <c:if test="${not empty msg}">
                    <div class="row">
                        <div class="col-md-4 col-md-offset-4">
                            <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
                                    ${msg}
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </form><!-- /.modal-content -->

</div>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</body>
</html>