<%--
  Created by IntelliJ IDEA.
  User: Tomcat
  Date: 2017/4/22
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>增加学生信息</title>
</head>
<body>
<form:form action="add" method="POST" modelAttribute="student">
    <!-- path 属性对应html表单标签的name属性 -->
    Name: <form:input path="name"></form:input><br>
    call:<form:input path="call"></form:input><br>
    <%
        Map<String, String> genders = new HashMap<String, String>();
        genders.put("0", "male");
        genders.put("1", "female");
        request.setAttribute("genders", genders);
    %>
    Gender:<form:radiobuttons path="sex" items="${genders}"/><br>
    <input type="submit" value="Submit"/>
</form:form>

</body>
</html>