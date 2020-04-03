<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>未选课程</title>
    <style type="text/css">
        #body1 {
            background-color: #fff;
        }
    </style>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/bootstrap/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/respond.js"></script>
    <![endif]-->
</head>
<body id="body1">
<div class="container1">
    <div class="row" style="height: auto; overflow: auto;">
        <div class="col-md-12" style="background-color:#fff;">
            <table class="table table-hover table-bordered table-striped">
                <thead>
                <tr>
                    <th>课程编号</th>
                    <th>课程名称</th>
                    <th>授课教师</th>
                    <th>选课人数</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="tbody1">

                </tbody>
            </table>
            <!--分页栏-->
            <!--显示分页信息  -->
            <div class="row">
                <!--分页文字信息  -->
                <div class="col-md-4" id="page_info_area" style="padding-top: 25px;">
                    <!-- 当前页，总页，总条记录 -->
                </div>
                <!--分页条信息  -->
                <div class="col-md-6" id="page_nav_area">

                </div>
            </div>
            <input type="hidden" id="userId" value="${loginedUser.userId}">
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    //定义全局变量，总记录数
    var totalRecord, currentPage;
    $(function () {
        to_page(1);
    });
    //定义个用来给表格中装载数据的函数
    var fillToTable = function (courseInfos) {
        var tbody1 = $("#tbody1");
        tbody1.empty();
        $.each(courseInfos.list, function (index, courseInfo) {
            var tr = $("<tr>");
            var td = $("<td>");
            td.text(courseInfo.courseId);
            tr.append(td);

            td = $("<td>");
            td.text(courseInfo.coursename);
            tr.append(td);

            td = $("<td>");
            td.text(courseInfo.teacher);
            tr.append(td);


            td = $("<td>");
            td.text(courseInfo.number);
            tr.append(td);

            td = $("<td>");
            td.html("<button type='button' class='btn btn-default btn-sm'>" +
                "<span class='glyphicon glyphicon-edit'></span> 编辑" +
                "</button>");
            tr.append(td);

            tbody1.append(tr);

        });
    };

    //解析显示分页信息
    function build_page_info(result) {
        //清空分页信息
        $("#page_info_area").empty();
        $("#page_info_area").append("当前第" + result.pageNum + "页，共" +
            result.pages + "页，总" +
            result.total + "条记录");
        totalRecord = result.total;
        currentPage = result.pageNum;
    }

    //解析显示分页条
    function build_page_nav(result) {
        //清空分页导航
        $("#page_nav_area").empty();
        //page_nav_area
        //创建ul
        var ul = $("<ul></ul>").addClass("pagination");
        //首页
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        //前一页
        var prePageLi = $("<li></li>").append($("<a></a>").append($("<span></span>").append("&laquo;")));
        //如果没有了前一页，首页和前一页按钮不可用
        if (result.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");

        }
        //为元素添加点击翻页事件
        //首页单击事件
        firstPageLi.click(function () {
            to_page(1);
        });
        //上一页单击事件:当前页-1
        prePageLi.click(function () {
            to_page(result.pageNum - 1);
        });

        //后一页
        var nextPageLi = $("<li></li>").append($("<a></a>").append($("<span></span>").append("&raquo;")));
        //末页
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
        //如果没有了下一页，末页和下一页按钮不可用
        if (result.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");

        }
        //下一页的单击事件：当前页+1
        nextPageLi.click(function () {
            to_page(result.pageNum + 1);
        });
        lastPageLi.click(function () {
            to_page(result.pages);
        });


        //添加首页和前一页的提示
        ul.append(firstPageLi).append(prePageLi);
        //循环遍历给ul中添加页码提示，取出页码号 1,2,3,4,5，
        $.each(result.navigatepageNums, function (index, item) {
            var numLi = $("<li></li>").append($("<a></a>").append(item));
            //如果当前页码等于正在循环遍历的页码，则页码变色
            if (result.pageNum == item) {
                numLi.addClass("active");
            }
            //给页码添加单击事件
            numLi.click(function () {
                to_page(item);
            });
            ul.append(numLi);
        });
        //添加下一页和末页的提示
        ul.append(nextPageLi).append(lastPageLi);
        //创建nav,将ul标签添加到nav标签中
        var nav = $("<nav></nav>").append(ul);
        //将分页条添加到上面id中
        nav.appendTo("#page_nav_area");
    }

    //点击页面时，实现页码跳转
    function to_page(pageNum) {
        //获取session保存的userId
        var userId = $("#userId").val();
        //获取已选课程信息
        $.ajax({
            url: "${pageContext.request.contextPath}/course/ajax/selectedCourses",
            data: "pageNum=" + pageNum+"&userId="+userId,//pageNum是用户指定跳转的页码数
            type: "get",
            //请求成功的回调函数，result是服务器响应给浏览器的数据
            success: function (result) {
                //console.log(result);//需要在浏览器的控制台中查看
                //1.解析并显示课程信息
                if (result.code < 0) {
                    alert(result.msg);
                } else {
                    fillToTable(result.data);
                }
                //2.解析并显示分页信息
                build_page_info(result.data);
                //3.解析显示分页条
                build_page_nav(result.data);

            }
        });
    }
</script>
</body>
</html>
