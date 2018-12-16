<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <!-- UIkit CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.23/css/uikit.min.css"/>

    <!-- UIkit JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.23/js/uikit.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/uikit/3.0.0-rc.23/js/uikit-icons.min.js"></script>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="${basePath}/css/index.css">
    <script rel="script" src="${basePath}/js/vue.js"></script>
    <title>个人</title>
</head>
<body>
<div class="uk-flex uk-flex-right" style="margin-right: 40px">
    <a href="/signout" class="uk-icon-button" uk-icon="icon: sign-out; ratio: 1.5"></a>
</div> `
<div class="uk-flex uk-flex-center" style="margin-top: 120px">
    <div class="uk-card uk-card-default uk-card-body uk-width-1-2@m" id="app">
        <table class="uk-table">
            <thead>
            <tr>
                <th>编号</th>
                <th>名字</th>
                <th>密码</th>
                <th>出生日期</th>
                <th>入职时间</th>
                <th>职位</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <td>${person.id}</td>
                <td>${person.name}</td>
                <td>${person.age}</td>
                <td>${person.pass}</td>
                <td>${person.bornTimeStr}</td>
                <td>${person.enterTimeStr}</td>
                <td>${person.placeName}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div id="mainModal" uk-modal>
    <%--更新modal--%>
    <div id="personMo" uk-modal>
        <person-modal :id="id"
                      :name="name"
                      :pass="pass"
                      :born="born"
                      :age="age"
                      :deptname="deptName"
                      :datatype="dataType"
                      :person_places="places"
                      :selectvalue="placeSelectValue"
                      @on-select-value-change="onSelectValueChange"
                      @on-name-change="onNameChange"
                      @on-pass-change="onPassChange"
                      @on-born-change="onBornChange"
                      @on-age-change="onAgeChange"
        ></person-modal>
    </div>
</body>
<script>
    var vm = new Vue({
        el:'#app',

    })
</script>
</html>
