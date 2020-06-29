<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>数据字典新增</title>
    <link rel="stylesheet" href="${ctx}/static/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <script src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
<div class="container">
    <tags:nav/>
    <div class="page-header">
        <h3>数据字典管理${action}</h3>
    </div>
    <form method="post" action="${ctx}/dict/${action}">
        <input type="hidden" name="id" value="${dict.id}">
        <p>字典名称：<input type="text" class="inpu-medium" name="type" value="${dict.type}"></p>
        <p>属性编码：<input type="text" class="input-medium" name="code" value="${dict.code}"></p>
        <p>属性名称：<input type="text" class="input-medium" name="name" value="${dict.name}"></p>
        <p><button type="submit" class="btn btn-primary">保存</button></p>
    </form>
</div>

<script src="${ctx}/static/bootstrap-3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
