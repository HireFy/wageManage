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
    <title>login</title>
</head>
<body>
<div class="uk-flex uk-flex-center" style="margin-top: 120px">
    <div id="loginBox" class="uk-card uk-card-default uk-card-body uk-width-1-2@m">
        <form class="uk-form-stacked" id="login_form">
            <div class="uk-margin">
                <label class="uk-form-label" for="num">编号</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="num" name="num" type="text" placeholder="员工编号"
                           v-model="num"
                           :class="{'uk-form-danger' : isExistInvalidChar || !isNumOk}"
                           @blur="numBlur">
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="pass">密码</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="pass" name="pass" type="password" placeholder="密码"
                           v-model="pass"
                           :class="{'uk-form-danger' : !isPassOk}"
                           @blur="passBlur">
                </div>
            </div>
        </form>
        <div class="uk-button-group" style="width: 100%;">
            <button class="uk-button uk-button-text uk-width-1-1 uk-margin-small-bottom">现在注册</button>
            <button class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom"
                    :disabled="isDisabled"
                    @click="login"
                    @keyup.enter="login">登录
            </button>
        </div>
    </div>
</div>
</body>

<script>
    function check(num, pass) {
        $.ajax({
            type: "post",
            url: "/person/check",
            data: {
                "num": num,
                "pass": pass
            },
            dataType: "json",
            success: function (data) {
                if (data) {
                    window.location.href = "/person/"+num
                } else {
                    UIkit.modal.dialog('<div class="uk-alert-danger" uk-alert>\n' +
                        '    <a class="uk-alert-close" uk-close></a>\n' +
                        '    <p>编号或密码错误</p>\n' +
                        '</div>');
                }
            }
        })
    }
</script>

<script>
    var login = new Vue({
        el: "#loginBox",
        data: {
            num: '',
            pass: '',
            isExistInvalidChar: false,
            isPassOk: true,
            isNumOk: true,
            isDisabled: false,
        },
        watch: {
            num: function (val) {
                /*如果不全是数字，isExistInvalidChar返回true*/
                for (var i = 0; i < val.length; i++) {
                    if (isNaN(parseInt(val.charAt(i)))) {
                        this.isExistInvalidChar = true
                        return
                    }
                }
                this.isExistInvalidChar = false

                /*如果输入值时不为空,设定isNumOk不为空*/
                if (val != "") {
                    this.isNumOk = true
                }
            },
            pass: function (val) {
                /*如果输入值时不为空,设定isPassOk不为空*/
                if (val != "") {
                    this.isPassOk = true
                }
            }
        },
        methods: {
            login: function () {
                check(this.num, this.pass)
            },
            numBlur: function () {
                if (this.num == "") {
                    this.isNumOk = false
                    this.isDisabled = true
                } else {
                    this.isNumOk = true
                    this.isDisabled = false
                }
            },
            passBlur: function () {
                if (this.pass == "") {
                    this.isPassOk = false
                    this.isDisabled = true
                } else {
                    this.isPassOk = true
                    this.isDisabled = false
                }
            },
        }
    })
</script>
</html>
