<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>列表</title>
    <!--******** css ********************************************--->
    <link rel="stylesheet" href="${ctx}/static/bootstrap-3.3.5/css/bootstrap.min.css"/>
    <script src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/static/commons.js"></script>
    <script>
        $(document).ready(function () {
            $("#btn_Create").bind("click",function () {
                window.location.href="${ctx}/<module>/create"
            })

            $("#btn_BatchDelete").bind("click",function () {
                //
            })
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
    <!--******** 功能按钮区域（功能按钮、查询条件）*********************************--->
    <div class="row">
        <!--======== 功能按钮区域 =====================================-->
        <div class="col-md-12">
            <button type="button" class="btn btn-default" id="btn_Create">创建</button>
            <button type="button" class="btn btn-danger" id="btn_BatchDelete">批量删除</button>
        </div>

        <!--======== 查询条件区域 =====================================-->
        <div class="col-md-12">
            <form class="form-search" method="post" action="#">
                <label>名称：</label> <input type="text" name="s_LIKE_name" class="input-medium" value="${param.s_LIKE_name}">
                <button type="submit" class="btn btn-danger"><i class="icon-search"></i> Search</button>
            </form>
        </div>
    </div>
    <!--*******Table列表页面*********************************************--->
    <table>
        <thead>
            <tr>
                <th>表头的列写法使用th</th>
            </tr>
        </thead> <!--表头，全选-->
        <c:forEach items="${page.content}" var="entity" varStatus="idxStatus"> <!--分页列表数据循环-->
            <tbody>
                <tr><td>一行数据</td></tr><!--一行数据-->
                <tr>
                    <td>数据的列写法使用td</td>

                    <td> <!--操作：编辑、删除等功能按钮-->
                        <a href="#" class="btn_edit" id="${entity.pkId}">编辑</a><!--方法一-->
                        <a href="${ctx}/storage/edit/${entity.pkId}">编辑</a><!--方法二-->
                        <button type="button" class="btn_Delete" id="${entity.pkId}">删除</button><!--方法三-->
                        <input type="button" class="btn_Delete" id="${entity.pkId}" value="删除">
                    </td>
                </tr>
            </tbody> <!--数据显示区域-->
        </c:forEach>
    </table>
    <!--**************分页标签***************************************************--->
    <tags:pagination page="${page}" paginationSize="${PAGE_SIZE}"/>
</div>

<!--******** js ********************************************--->
<script src="${ctx}/static/bootstrap-3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
