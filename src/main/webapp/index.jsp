<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()
            +":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <!-- 引入Vue -->
    <script src="//cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/at-ui-style/css/at.min.css">
    <!-- 引入组件库 -->
    <script src="//cdn.jsdelivr.net/npm/at-ui/dist/at.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>
</body>
</html>
