<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学生信息查询</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
            font-size: 14px;
            color: #000;
        }

        #biaoge {
            width: 907px;
            margin: 30px auto;
        }

        #biaoge li, #biaoge li.biaotou {
            list-style-type: none;
            width: 150px;
            height: 30px;
            line-height: 30px;
            text-align: center;
            float: left;
            margin-left: 1px;
            margin-bottom: 1px;
            background: #ccc;
        }

        #biaoge li.biaotou {
            background: #999;
        }
    </style>
</head>
<body>
<c:if test="${empty requestScope.students}">
    <span style="font-size: 15px"><a href="emp">没有学生信息</a></span>
</c:if>
<c:if test="${!empty requestScope.students }">
    <div style="float:left;text-align:center">
        <a href="emp"><span style="font-size: 20px">增加学生信息</span></a>
    </div>
    <div >
        <ul id="biaoge">
            <li class="biaotou">ID</li>
            <li class="biaotou">姓名</li>
            <li class="biaotou">电话号码</li>
            <li class="biaotou">性别</li>
            <li class="biaotou">编辑</li>
            <li class="biaotou">删除</li>
            <c:forEach items="${requestScope.students}" var="stu">
                <li>${stu.id }</li>
                <li>${stu.name }</li>
                <li>${stu.call }</li>
                <li>${stu.sex==0?'男':'女' }</li>
                <li><a href="students/${stu.id}">Edit</a></li>
                <li><a href="delete?id=${stu.id}">Delete</a></li>
            </c:forEach>
        </ul>
    </div>

    </c:if>



</body>
</html>