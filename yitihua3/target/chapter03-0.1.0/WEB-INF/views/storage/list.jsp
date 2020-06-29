<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>资源存储路径</title>
    <link rel="stylesheet" href="${ctx}/static/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <script src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/static/commons.js"></script>
    <script>
        $(document).ready(function(){
            $("#btn_Create").bind("click",function () {
                window.location.href="${ctx}/storage/create";
            })
        });
    </script>
</head>
<body>
<div class="container">
    <tags:nav/>
    <div class="page-header">
        <h3>资源存储管理</h3>
    </div>
    <div class="row">
        <div class="col-md-12">
            <button type="button" class="btn btn-default" id="btn_Create">创建</button>
            <button type="button" class="btn btn-danger" id="btn_Delete">批量删除</button>
        </div>
    </div>
    <table  id="contentTable" class="table table-striped table-hover">
        <thead><tr>
            <th><input type="checkbox" id="chkAll"></th>
            <th>序号</th>
            <th>文件路径</th>
            <th>描述</th>
            <th>类型</th>
            <th>操作</th>
        </tr></thead>
        <tbody>
            <c:forEach items="${list.content}" var="storage" varStatus="idxStatus">
            <tr>
                <td><input type="checkbox" name="chkIds" value="${storage.id}"></td>
                <td>${idxStatus.index+1}</td>
                <td>${storage.path}</td>
                <td>${storage.description}</td>
                <td>${storage.type}</td>
                <td>
                    <input type="button" name="edit" value="编辑">
                    <input type="button" name="delete" value="删除">
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <tags:pagination page="${list}" paginationSize="${PAGE_SIZE}"/>
</div>

<script src="${ctx}/static/bootstrap-3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
