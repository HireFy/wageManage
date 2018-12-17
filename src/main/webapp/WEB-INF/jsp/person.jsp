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
    <title>你好, ${person.name}</title>
</head>
<body>
<nav class="uk-navbar-container" uk-navbar id="navbar" uk-sticky>
    <div class="uk-navbar-left">
        <ul class="uk-navbar-nav">
            <li @click="navId = 0" :class="{'uk-active':navId === 0}">
                <a><span :class="{'uk-text-large':navId === 0}">个人</span></a></li>
            <li @click="navId = 1" :class="{'uk-active':navId === 1}">
                <a><span :class="{'uk-text-large':navId === 1}">工资</span></a></li>
        </ul>
    </div>
</nav>
<a href="/signout" class="uk-icon-button" uk-icon="icon: sign-out; ratio: 1.5"></a>

<div id="mainModal" uk-modal>
    <%--更新modal--%>
    <%--<div id="mainModal" uk-modal>--%>
    <person-modal :id="id"
                  :name="name"
                  :pass="pass"
                  :born="bornTime"
                  :age="age"
                  :deptname="deptName"
                  :person_places="places"
                  :etime="enterTime"
                  :selectvalue="placeSelectValue"
                  @on-select-value-change="onSelectValueChange"
                  @on-name-change="onNameChange"
                  @on-pass-change="onPassChange"
                  @on-born-change="onBornChange"
                  @on-age-change="onAgeChange"
    ></person-modal>
    <%--</div>--%>
</div>
<div class="uk-flex uk-flex-center" id="app">
    <div class="uk-card uk-card-default uk-card-body uk-width-1-2@m">
        <table class="uk-table uk-table-hover uk-table-divider" v-show="showPerson">
            <thead>
            <tr>
                <th>编号</th>
                <th>名字</th>
                <th>年龄</th>
                <th>密码</th>
                <th>出生日期</th>
                <th>入职时间</th>
                <th>职位</th>
            </tr>
            </thead>

            <tbody>
            <tr @click="showmodal">
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

        <div v-show="!showPerson">
            <div class="uk-margin" uk-grid>
                <div>
                    <label class="uk-form-label" for="year-select">年份</label>
                    <select class="uk-select" id="year-select" v-model="selectYear">
                        <option></option>
                        <option v-for="year in years" :value="year">{{year}}</option>
                    </select>
                </div>
                <div>
                    <label class="uk-form-label" for="month-select">月份</label>
                    <select class="uk-select" id="month-select" v-model="selectMonth">
                        <option></option>
                        <option v-for="month in months" :value="month">{{month}}</option>
                    </select>
                </div>


            </div>
            <table class="uk-table uk-table-hover uk-table-divider">
                <thead>
                <tr>
                    <th>工资编号</th>
                    <th>应发薪水</th>
                    <th>加班补贴 <span class="uk-label uk-label-success">{{ov_days}}</span></th>
                    <th>缺勤扣除 <span class="uk-label uk-label-danger">{{ab_days}}</span></th>
                    <th>实发薪水</th>
                    <th>记录日期</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="data in datas" @mouseover="getReward">
                    <td>{{data.id}}</td>
                    <td>{{data.baseSalary}}</td>
                    <td>{{data.overTimeSalary}}</td>
                    <td>{{data.cutSalary}}</td>
                    <td>{{data.finalSalary}}</td>
                    <td>{{data.recordDateStr}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div id="pagenation" v-show="showPagenation">
    <ul class="uk-pagination uk-flex-center" uk-margin style="margin: 20px">
        <li @click="goPrev" :class="{'uk-disabled': currentPage - 1 === 0}"><a href="#"><span
                uk-pagination-previous></span></a></li>
        <li v-for="(page, index) in pages"
            :class="{'uk-active uk-text-large': currentPage === index + 1}"
        >
            <a href="#" @click="focusCurrent">{{index + 1}}</a>
        </li>
        <li @click="toNext" :class="{'uk-disabled': currentPage === pages}"><a href="#"><span
                uk-pagination-next></span></a></li>
    </ul>
</div>

</body>
<script src="${basePath}/js/component_person.js"></script>
<script src="${basePath}/js/person.js"></script>
</html>
