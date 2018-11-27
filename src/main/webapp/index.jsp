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
    <title></title>
</head>
<body>
<nav class="uk-navbar-container" uk-navbar>
    <div class="uk-navbar-left">
        <div id="navbar">
            <ul class="uk-navbar-nav">
                <li @click="navId = 0" :class="{'uk-active':navId === 0}">
                    <a href="#">人员</a></li>
                <li @click="navId = 1" :class="{'uk-active':navId === 1}">
                    <a href="#">部门</a></li>
                <li @click="navId = 2" :class="{'uk-active':navId === 2}">
                    <a href="#">职位</a></li>
                <li @click="navId = 3" :class="{'uk-active':navId === 3}">
                    <a href="#">奖金</a></li>
            </ul>
        </div>
    </div>
</nav>


<!-- This is the modal -->
<div id="mainModal" uk-modal>
    <div id="personMo" uk-modal>
        <person-modal :id="id"
                      :name="name"
                      :salary="salary"
                      :datatype="dataType"
                      :selectvalue="placeSelectValue"
                      @on-select-value-change="onSelectValueChange"
                      @on-name-change="onNameChange"
        ></person-modal>
    </div>

    <div id="deptMo" uk-modal>
        <dept-modal :id="id"
                    :name="name"
                    :fatherid="fatherId"
                    @on-name-change="onNameChange"
                    @on-fatherid-change="onFatherIdChange"
        ></dept-modal>
    </div>

    <div id="placeMo" uk-modal>
        <place-modal :id="id"
                     :name="name"
                     :salary="salary"
                     :deptid="deptId"
                     @on-name-change="onNameChange"
                     @on-deptid-change="onDeptIdChange"
        >
        </place-modal>
    </div>

    <div id="bonusMo" uk-modal>
        <bonus-modal :id="id"
                     :name="name"
                     :rate="rate"
                     @on-name-change="onNameChange"
                     @on-rate-change="onRateChange"
        >
        </bonus-modal>
    </div>

    <%--删除提示modal--%>
    <div id="deleteModal" uk-modal>
        <div class="uk-modal-dialog uk-modal-body">
            <span class=".uk-text-danger">{{deleteInfo}}</span>
            <button class="uk-button uk-button-danger uk-align-right uk-modal-close"
                    @click="deleteData">确认</button>
        </div>
    </div>
</div>

<div id="main">
    <div id="my-table">
        <table class="uk-table uk-table-hover uk-table-divider">
            <thead is="person-head" v-if="navIdInTb === 0"></thead>
            <thead is="dept-head" v-if="navIdInTb === 1"></thead>
            <thead is="place-head" v-if="navIdInTb === 2"></thead>
            <thead is="bonus-head" v-if="navIdInTb === 3"></thead>
            <tbody>
            <tr v-for="data in datas" v-if="navIdInTb === 0" @click="showmodal">
                <td>{{data.id}}</td>
                <td>{{data.name}}</td>
                <td>{{data.salary}}</td>
                <td>{{data.placeName}}</td>
            </tr>
            <tr v-for="data in datas" v-if="navIdInTb === 1" @click="showmodal">
                <td>{{data.id}}</td>
                <td>{{data.name}}</td>
                <td>{{data.fatherId}}</td>
            </tr>
            <tr v-for="data in datas" v-if="navIdInTb === 2" @click="showmodal">
                <td>{{data.id}}</td>
                <td>{{data.name}}</td>
                <td>{{data.salary}}</td>
                <td>{{data.deptId}}</td>
            </tr>
            <tr v-for="data in datas" v-if="navIdInTb === 3" @click="showmodal">
                <td>{{data.id}}</td>
                <td>{{data.personId}}</td>
                <td>{{data.rate}}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div id="pagenation">
        <ul class="uk-pagination uk-flex-center" uk-margin>
            <li @click="goPrev" :class="{'uk-disabled': currentPage - 1 === 0}"><a href="#"><span
                    uk-pagination-previous></span></a></li>
            <li v-for="(page, index) in pages"
                :class="{'uk-active': currentPage === index + 1}"
                @click="focusCurrent">
                <a href="#">{{index + 1}}</a>
            </li>
            <li @click="toNext" :class="{'uk-disabled': currentPage === pages}"><a href="#"><span
                    uk-pagination-next></span></a></li>
        </ul>
    </div>
</div>
</body>
<script src="${basePath}/js/component.js"></script>
<script src="${basePath}/js/index.js"></script>
</html>