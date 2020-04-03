<%--
  Created by IntelliJ IDEA.
  User: fpf
  Date: 2020/3/25
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加课程</title>
    <jsp:include page="../template/bootstrap_common.jsp"></jsp:include>
</head>
<body>
<div class="container">

    <div class="row">
        <table class="table table-striped table-hover table-bordered">
            <tbody id="body1">
            <tr>
                <td align="center">课程名称</td>
                <td><input type="text" name="coursename" size="10" maxlength="10" class="form-control"></td>
            </tr>
            <tr>
                <td align="center">授课教师</td>
                <td><input type="text" name="teacher" size="10" maxlength="10" class="form-control"></td>
            </tr>
            <tr>
                <td align="center">选课人数</td>
                <td><input type="text" name="number" size="10" maxlength="10" class="form-control" value="0"
                           disabled="disabled"></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="row">
        <button type="button" class="container btn btn-primary" id="btnSubmit">提交<span class="glyphicon glyphicon-log-in"></span></button>
    </div>

</div>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    var provinces = null;
    $(function () {
        //给提交按钮绑定事件处理函数
        $("#btnSubmit").click(checkAndSubmitData);
    });

    function checkAndSubmitData() {
        var valid = true;
        var coursename = $("input[name='coursename']").val();
        var teacher = $("input[name='teacher']").val();
        var number = $("input[name='number']");
        number.each(function (index, element) {
            if (isNaN(Number(element.value))) {
                valid = false;
            }
        });
        if (valid) {
            var obj = {};
            obj.coursename = coursename;
            obj.teacher = teacher;
            obj.number = 0;

            //提交请求
            $.ajax({
                url: "${pageContext.request.contextPath}/course/ajax/input",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(obj),
                dataType: 'json',
                success: function (resp) {
                    if (resp.code < 0) {
                        alert(resp.msg);
                    } else {
                        alert(resp.msg);
                    }
                }
            });

        } else {
            alert("请检查你的输入，确保输入有效的数值!");
        }
    }
</script>
</body>
</html>
