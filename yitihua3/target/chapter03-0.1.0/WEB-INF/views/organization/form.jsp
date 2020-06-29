<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>表单页面</title>
    <link rel="stylesheet" href="${ctx}/static/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <script src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
<div class="container">
    <tags:nav/> <%--菜单--%>
    <div class="page-header">
        <h3>新建机构</h3>
    </div>
    <form id="createFrm" action="${ctx}/organization/create" method="post">
        <p>上级机构为：${porgan.name} <input type="text" name="pId" value="${porgan.id}"> </p>
        <p>机构名称：<input type="text" class="inpu-medium" name="name" value="${organization.name}"></p>
        <p><button type="submit" class="btn btn-primary">保存</button></p>
    </form>
</div>

<script src="${ctx}/static/bootstrap-3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
