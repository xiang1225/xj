<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>表单页面</title>
    <link rel="stylesheet" href="${ctx}/static/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <script src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
    <script>
        $(document).ready(function () {

        })
    </script>
</head>
<body>
<div class="container">
    <tags:nav/><!--导航菜单-->
    <!--**********************************************************************--->
    <div class="page-header">
        <h3>数据字典管理</h3>
    </div>
    <!--********Form表单区域 **************************************************--->
    <form id="createFrm" action="${ctx}/storage/create" method="post">
        <p>文件路径：<input type="text" class="inpu-medium" name="path" value="${storage.path}"></p>
        <p>描述：<textarea class="inpu-medium" name="description">${storage.description}</textarea></p>
        <p>存储类型： <input type="text" class="inpu-medium" name="type" value="${storage.type}"></p>

        <p><button type="submit" class="btn btn-primary">保存</button></p>
    </form>
</div>

<!--******** js ********************************************--->
<script src="${ctx}/static/bootstrap-3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
