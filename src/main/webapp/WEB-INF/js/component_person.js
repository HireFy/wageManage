Vue.component('person-modal', {
    props: ['id', 'name', 'salary', 'selectvalue', 'person_places', 'etime', 'pass', 'born', 'age', 'deptname'],
    data: function () {
        return {
            childSelectVal: this.selectvalue,
            childName: this.name,
            childPlaces: this.person_places,
            childPass: this.pass,
            childBorn: this.born,
            childAge: this.age,
            childDeptName: this.deptname,
            childEnterTime:this.etime
            }
        },
    computed: {
        isEverythingOK: function () {
            return this.childName != ''
                && this.childPass != ''
                && this.childBorn != ''
        }
    },
    watch: {
        selectvalue: function (val) {
            this.childSelectVal = val
        },
        name: function (val) {
            this.childName = val
        },
        childName: function (val) {
            this.$emit('on-name-change', val)
        },
        childSelectVal: function (val) {
            console.log("子组件内的变化: " + val)
            this.$emit('on-select-value-change', val)
        },
        pass: function (val) {
            this.childPass = val
        },
        childPass: function (val) {
            this.$emit('on-pass-change', val)
        },
        person_places: function (val) {
            this.childPlaces = val
        },
        born: function (val) {
            this.childBorn = val
        },
        childBorn: function (val) {
            this.childAge = 2018 - val.substring(0, 4)
            this.$emit('on-born-change', val)
        },
        age: function (val) {
            this.childAge = val
        },
        childAge: function (val) {
            this.$emit('on-age-change', val)
        },
        deptname: function (val) {
            this.childDeptName = val
        },
        etime:function (val) {
            this.childEnterTime = val
        }
    },
    methods: {
        update: function () {
            data = JSON.stringify({
                id: this.id,
                name: this.name,
                placeId: this.childSelectVal,
                pass: this.pass,
                born: this.childBorn,
                age: this.childAge
            })

            update('person', data)
        },
        checkDateComp: function (date) {
            console.log("checkDateComp: " + checkDate(date))
            return checkDate(date)
        },
    },
    template: '<div class="uk-modal-dialog uk-modal-body">\n' +
        '        <h2 class="uk-modal-title">{{id}}</h2>\n' +
        '        <form class="uk-form-stacked">\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-stacked-text">姓名</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="childName" class="uk-input" id="form-stacked-text" type="text" :placeholder="name">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-pass-text">密码</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="childPass" class="uk-input" id="form-pass-text" type="text" :placeholder="pass">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-age-text">年龄</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="childAge" class="uk-input" id="form-age-text" type="text" disabled="disabled">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-born-text">出生日期</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="childBorn" class="uk-input" id="form-born-text" type="text" disabled="disabled">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-born-text">入职时间</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="childEnterTime" class="uk-input" id="form-born-text" type="text" disabled="disabled">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-stacked-select">职位</label>\n' +
        '                <div class="uk-form-controls">\n' + '<select class="uk-select" id="form-stacked-select" v-model="childSelectVal">\n' +
        '    <option v-for="place in childPlaces" :value="place.id">{{place.name}}</option>\n' +
        '  \n' +
        '</select>' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="deptname-text">所属部门</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="childDeptName" class="uk-input" id="deptname-text" type="text" disabled="disabled">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </form>\n' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="update" :disabled="!isEverythingOK">修改</button>' +
        '    </div>'
})