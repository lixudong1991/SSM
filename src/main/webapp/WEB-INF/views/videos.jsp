<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script language="JavaScript">
        function checkAll()
        {
            var object = document.getElementsByName("delete");
            var length = object.length;
            var checked = document.getElementById("all").checked;
            for (var i = 0; i < length; i++)
            {
                object[i].checked=checked;
            }
        }
        function checkItem()
        {
            var e = window.event.srcElement;
            var all=document.getElementById("all");
            all.checked=true;
            if(e.checked){
                var object = document.getElementsByName("delete");
                var length = object.length;
                for (var i = 0; i < length; i++)
                {
                    if(!object[i].checked){
                        all.checked=false;
                    }

                }
            }else{
                all.checked=false;
            }
        }
    </script>
    <title>文件列表</title>
</head>
<body>
<div>

    <c:if test="${!empty requestScope.items}">
        <div>
            <a href="index.jsp"><span>返回主目录</span></a>
            <a href="play?path=${requestScope.ppath}"><span>上一级目录</span></a>
            <form:form action="deletefile?path=${pat}" method="POST">
            <ul>
                <c:forEach items="${requestScope.items}" var="emp">
                    <li>
                        <input name="delete" type="checkbox" value="${emp}" onclick="checkItem()"/>
                        <a href="look?path=${requestScope.pat}&name=${emp}">
                             <span class="text">
                                 <span>${emp}</span>
                             </span>
                        </a>
                    </li>
                </c:forEach>
            </ul>
            <span>全选<input id="all" type="checkbox" value="" onclick="checkAll()"/></span><br/>
                <input type="submit" value="删除"/>
            </form:form>
        </div>
    </c:if>
    <div>
        <form action="FileUpload?path=${requestScope.pat}" method="POST" enctype="multipart/form-data">
            File: <input type="file" name="file"/>
            <input type="submit" value="upload"/>
        </form>
    </div>
</div>
</body>
</html>