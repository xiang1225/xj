<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <link rel="stylesheet" href="${ctx}/static/bootstrap-3.3.5/css/bootstrap.min.css"/>
        <script src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
    </head>
    <body>
    <div class="container">
        <tags:nav/>
        <div class="jumbotron">
            <h2>内容管理系统<small>（SSH框架整合实战项目）</small></h2>
            <p>一体化软件工程实践三之整合框架开发，采用Bootstrap + Spring MVC + Spring + JPA技术，实现内容管理系统</p>
            <p></p>
            <%--        <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>--%>
        </div>

        <ul>
<%--            <li><a href="${ctx}/login/110312">登录页面</a></li>--%>
            <li>MVC示例页面</li>
            <li><a href="${ctx}/file/index"> 文件上传下载示例</a></li>
        </ul>

    </div>
    <script src="${ctx}/static/bootstrap-3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>
