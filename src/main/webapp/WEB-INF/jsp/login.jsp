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
<div class="uk-flex uk-flex-center">
    <div id="loginBox" class="uk-card uk-card-default uk-card-body uk-width-1-2@m">
        <form class="uk-form-stacked" action="/login" method="post" id="login_form">
            <div class="uk-margin">
                <label class="uk-form-label" for="num">编号</label>
                <div class="uk-form-controls">
                    <input class="uk-input" :class="{'uk-form-danger' : isExistInvalidChar}" id="num" name="num" type="text" placeholder="员工编号" v-model="num" >
                </div>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="pass">密码</label>
                <div class="uk-form-controls">
                    <input class="uk-input" id="pass" name="pass" type="password" placeholder="密码" v-model="pass">
                </div>
            </div>
        </form>
        <div class="uk-button-group" style="width: 100%;">
            <button class="uk-button uk-button-text uk-width-1-1 uk-margin-small-bottom">现在注册</button>
            <button class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom" :disabled="isExistInvalidChar" @click="login">登录</button>
        </div>
    </div>
</div>
</body>
<script>
    var login  = new Vue({
        el:"#loginBox",
        data:{
            num:'',
            pass:'',
            isExistInvalidChar:false
        },
        watch:{
            num:function (val) {
                /*如果不全是数字，isExistInvalidChar返回true*/
                for(var i = 0; i < val.length; i++){
                    if (isNaN(parseInt(val.charAt(i)))) {
                        this.isExistInvalidChar = true
                        return
                    }
                }
                this.isExistInvalidChar = false
            }
        },
        methods:{
            login:function () {
                /*设置提交的表单的值num和pass*/
                login_form.num.value = this.num
                login_form.pass.value = this.pass

                /*提交表单*/
                login_form.submit()
            }
        }
    })
</script>
</html>
