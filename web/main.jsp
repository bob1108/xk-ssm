<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>高校选课系统</title>
    <jsp:include page="template/bootstrap_common.jsp"></jsp:include>
    <style>
        .container1{
            margin-left: 20px;
            margin-right: 20px;
        }
    </style>

</head>
<body>

<div class="container1">
    <jsp:include page="template/top.jsp"></jsp:include>
    <div class="row">
        <div class="col-md-2">
            <jsp:include page="template/menu.jsp"></jsp:include>
        </div>
        <div class="col-md-10">
            <ul class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/main.jsp">主页</a></li>
                <li>后台主页</li>
            </ul>
            <iframe src="wellcome.jsp" name="context1" width="100%" height="800px" frameborder="0" scrolling="no" marginheight="0" marginwidth="0">

            </iframe>

        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
</body>
</html>
