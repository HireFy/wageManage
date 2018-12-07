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
<nav class="uk-navbar-container" uk-navbar id="navbar" uk-sticky>
    <div class="uk-navbar-left">
        <ul class="uk-navbar-nav">
            <li @click="navId = 0" :class="{'uk-active':navId === 0}">
                <a><span :class="{'uk-text-large':navId === 0}">人员</span></a></li>
            <li @click="navId = 1" :class="{'uk-active':navId === 1}">
                <a><span :class="{'uk-text-large':navId === 1}">部门</span></a></li>
            <li @click="navId = 2" :class="{'uk-active':navId === 2}">
                <a><span :class="{'uk-text-large':navId === 2}">职位</span></a></li>
            <li @click="navId = 3" :class="{'uk-active':navId === 3}">
                <a href="#"><span :class="{'uk-text-large':navId === 3}">工资</span></a></li>
        </ul>
    </div>

    <div class="uk-navbar-right" uk-grid>
        <div>
            <button class="uk-button uk-button-default" style="margin-right: 20px;"
                    @click="showAddModal"
            >添加
            </button>
        </div>
        <div class="uk-grid-collapse" uk-grid @keyup.enter="find">
            <div class="uk-inline">
                <input class="uk-input" id="form-stacked-text" type="text" autofocus v-model="value"
                       :placeholder="placeHolder">
            </div>
            <div>
                <button class="uk-button uk-button-default" @click="find" :disabled="value === ''"
                        autofocus="autofocus">搜索
                </button>
            </div>
        </div>
    </div>
</nav>

<div class="uk-flex uk-flex-right" style="margin-right: 40px">
    <a href="/signout" class="uk-icon-button" uk-icon="icon: sign-out; ratio: 1.5"></a>
</div>

<div id="mainModal" uk-modal>
    <%--更新modal--%>
    <div id="personMo" uk-modal>
        <person-modal :id="id"
                      :name="name"
                      :pass="pass"
                      :born="born"
                      :age="age"
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
                     :deptlist="depts"
                     :deptselect="deptSelectValue"
                     @on-name-change="onNameChange"
                     @on-salary-change="onSalaryChange"
                     @on-dept-select-change="onDeptSelectChange"
        >
        </place-modal>
    </div>

    <div id="salaryMo" uk-modal>
        <salary-modal :id="id"
                      :name="name"
                      :salary="salary"
        >
        </salary-modal>
    </div>
    <%--更新modal END--%>


    <%--增加modal--%>
    <div id="personAddMo" uk-modal>
        <person-add-modal
                :currentdate="currentDate"
                :person_places="places">
        </person-add-modal>
    </div>

    <div id="deptAddMo" uk-modal>
        <dept-add-modal>
        </dept-add-modal>
    </div>

    <div id="placeAddMo" uk-modal>
        <place-add-modal
                :deptlist="depts"
        >
        </place-add-modal>
    </div>


    <%--删除提示modal--%>
    <div id="deleteModal" uk-modal>
        <div class="uk-modal-dialog uk-modal-body">
            <span class=".uk-text-danger">{{deleteInfo}}</span>
            <button class="uk-button uk-button-danger uk-align-right uk-modal-close"
                    @click="deleteData">确认
            </button>
        </div>
    </div>
</div>

<div id="main">
    <div class="uk-flex uk-flex-center">
        <div id="my-table"
             v-show="showSalary"
             :class="{'uk-width-1-2@m': navIdInTb != 0}">
            <div class="uk-card uk-card-default uk-card-body" v-show="showSalaryHead">
                <div class="uk-text-left">人员编号:<span class="uk-text-large">{{personNum}}</span></div>
                <div class="uk-text-center"><span class="uk-text-large">{{personName}}</span></div>
            </div>
            <div class="uk-card uk-card-default uk-card-body">
                <table class="uk-table uk-table-hover uk-table-divider">
                    <thead is="person-head" v-if="navIdInTb === 0"></thead>
                    <thead is="dept-head" v-if="navIdInTb === 1"></thead>
                    <thead is="place-head" v-if="navIdInTb === 2"></thead>
                    <thead is="salary-head" v-if="navIdInTb === 3"></thead>
                    <tbody>
                    <tr v-for="data in datas" v-if="navIdInTb === 0" @click="showmodal">
                        <td>{{data.id}}</td>
                        <td>{{data.name}}</td>
                        <td>{{data.sex}}</td>
                        <td>{{data.age}}</td>
                        <td>{{data.pass}}</td>
                        <td>{{data.bornTimeStr}}</td>
                        <td>{{data.enterTimeStr}}</td>
                        <td>{{data.placeName}}</td>
                    </tr>
                    <tr v-for="data in datas" v-if="navIdInTb === 1" @click="showmodal">
                        <td>{{data.id}}</td>
                        <td>{{data.name}}</td>
                    </tr>
                    <tr v-for="data in datas" v-if="navIdInTb === 2" @click="showmodal">
                        <td>{{data.id}}</td>
                        <td>{{data.name}}</td>
                        <td>{{data.salary}}</td>
                        <td>{{data.deptName}}</td>
                    </tr>
                    <tr v-for="data in datas" v-if="navIdInTb === 3" @click="showmodal">
                        <td>{{data.id}}</td>
                        <%--<td>{{data.personName}}</td>--%>
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
</div>
</body>
<script src="${basePath}/js/component.js"></script>
<script src="${basePath}/js/index.js"></script>
</html>