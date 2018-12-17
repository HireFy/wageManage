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
</div>
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
<div class="uk-flex uk-flex-center" style="margin-top: 120px" id="app">
    <div class="uk-card uk-card-default uk-card-body uk-width-1-2@m">
        <table class="uk-table">
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
    </div>
</div>

</body>
<script src="${basePath}/js/component_person.js"></script>
<script>
    var Person

    var Places

    /**
     * 从数据库获取Place信息，生成id和name对应的Place对象
     */
    function getDataInfos(dataType) {
        $.ajax({
            type: "post",
            url: "/" + dataType + "/all",
            dataType: "json",
            async: false,
            success: function (data) {
                if (dataType === 'place')
                    Places = data
                if (dataType === 'dept')
                    Depts = data
            }
        })
    }

    getDataInfos('place')

    function getPersonById(personId) {
        $.ajax({
            type: 'post',
            url: '/person/get/' + personId,
            dataType: 'json',
            async: false,
            success: function (data) {
                Person = data
                console.log("ajax中:" + Person.name);
            }
        })
    }

    function getDeptNameByPlaceId(placeId) {
        $.ajax({
            type: 'post',
            url: '/dept/name/place/' + placeId,
            dataType: 'json',
            async: false,
            success: function (data) {
                vm.deptName = data.deptName
            }
        })
    }

    /**
     * 执行更新，自动刷新当前页 提示修改成功或失败
     * @param dataType
     * @param data
     */
    function update(dataType, data) {
        $.ajax({
            type: "post",
            url: "/" + dataType + "/update",
            dataType: "json",
            contentType: "application/json",
            data: data,
            error: function (error) {
                UIkit.modal.dialog('<div class="uk-alert-danger" uk-alert>\n' +
                    '    <a class="uk-alert-close" uk-close></a>\n' +
                    '    <p>更新操作失败</p>\n' +
                    '</div>');
            },
            success: function (data) {
                window.location.reload()
                UIkit.modal.dialog('<div class="uk-alert-success" uk-alert>\n' +
                    '    <a class="uk-alert-close" uk-close></a>\n' +
                    '    <p>更新操作已生效</p>\n' +
                    '</div>');
            }
        })
    }

    /**
     * 检验时间格式
     * @param date
     * @returns {boolean}
     */
    function checkDate(date) {
        var pattern = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/
        return pattern.test(date)
    }

    var table = new Vue({
        el: '#app',
        methods: {
            showmodal: function (event) {
                UIkit.modal('#mainModal').show()
                var nodes = event.currentTarget.childNodes
                var dataArr = new Array()
                for (let i = 0; i < nodes.length; i++) {
                    data = nodes[i].innerText
                    if (data != null) {
                        dataArr.push(data)
                    }
                }
                vm.id = dataArr[0]
                vm.name = dataArr[1]
                vm.age = dataArr[2]
                vm.pass = dataArr[3]
                vm.bornTime = dataArr[4]
                vm.enterTime = dataArr[5]

                getPersonById(vm.id)
                console.log("person: " + Person)
                vm.placeSelectValue = Person.placeId

                getDeptNameByPlaceId(Person.placeId)
            },
        }
    })

    var vm = new Vue({
        el: '#mainModal',
        data: {
            id: 0,
            name: '',
            pass: '',
            born: '',
            age: '',
            enterTime: '',
            bornTime:'',
            placeSelectValue: '',
            deptName: '',
            places: Places
        },
        methods: {
            onSelectValueChange: function (val) {
                getDeptNameByPlaceId(val)
                console.log("父组件接收到调用函数")
                this.placeSelectValue = val
                console.log("新的selectValue: " + val)
            },
            onNameChange: function (val) {
                this.name = val
            },
            onPassChange: function (val) {
                this.pass = val
            },
            onBornChange: function (val) {
                this.born = val
            },
            onAgeChange: function (val) {
                this.age = val
            }
        }
    })
</script>
</html>
