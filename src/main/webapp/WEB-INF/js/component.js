Vue.component('person-head', {
    template: '<thead>\n' +
        '        <tr>\n' +
        '            <th>id</th>\n' +
        '            <th>姓名</th>\n' +
        '            <th>性别</th>\n' +
        '            <th>年龄</th>\n' +
        '            <th>密码</th>\n' +
        '            <th>出生日期</th>\n' +
        '            <th>入职时间</th>\n' +
        '            <th>职位</th>\n' +
        '        </tr>\n' +
        '    </thead>\n'
})

Vue.component('dept-head', {
    template: ' <thead>\n' +
        '        <tr>\n' +
        '            <th>id</th>\n' +
        '            <th>部门</th>\n' +
        '        </tr>\n' +
        '    </thead>'
})

Vue.component('place-head', {
    template: ' <thead>\n' +
        '        <tr>\n' +
        '            <th>职位编号</th>\n' +
        '            <th>职位</th>\n' +
        '            <th>基本薪资</th>\n' +
        '            <th>所属部门</th>\n' +
        '        </tr>\n' +
        '    </thead>'
})

Vue.component('salary-head', {
    template: ' <thead>\n' +
        '        <tr>\n' +
        '            <th>工资编号</th>\n' +
        // '            <th>姓名</th>\n' +
        '            <th>应发薪水</th>\n' +
        '            <th>加班补贴</th>\n' +
        '            <th>缺勤扣除</th>\n' +
        '            <th>实发薪水</th>\n' +
        '            <th>记录日期</th>\n' +
        '        </tr>\n' +
        '    </thead>'
})


/*person的模态(modal)框*/
Vue.component('person-modal', {
    props: ['id', 'name', 'salary', 'selectvalue', 'datatype', 'person_places', 'pass', 'born', 'age', 'deptname'],
    data: function () {
        return {
            childSelectVal: this.selectvalue,
            childName: this.name,
            childPlaces: this.person_places,
            childPass: this.pass,
            childBorn: this.born,
            childAge: this.age,
            childDeptName: this.deptname
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

            update(vm.dataType[vm.navIdInTb], data)
        },
        deleteData: function () {
            deleteAlert();
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
        '                    <input v-model="childBorn" class="uk-input" :class="{\'uk-form-danger\': !checkDateComp(childBorn)}" id="form-born-text" type="text" uk-tooltip="title: xxxx-xx-xx; pos: top-right">\n' +
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
        '        </form>\n' + '<button class="uk-button uk-button-danger" @click="deleteData">删除</button>' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="update" :disabled="!isEverythingOK">修改</button>' +
        '    </div>'
})

/*dept的模态(modal)框*/
Vue.component('dept-modal', {
    props: ['id', 'name'],
    data: function () {
        return {
            childName: this.name
        }
    },
    watch: {
        name: function (val) {
            this.childName = val
        },
        childName: function (val) {
            this.$emit('on-name-change', val)
        }
    },
    methods: {
        deleteData: function () {
            deleteAlert();
        },
        update: function () {
            data = JSON.stringify({
                id: this.id,
                name: this.name,
            })
            update(vm.dataType[vm.navIdInTb], data)
        }
    },
    template: '<div class="uk-modal-dialog uk-modal-body">\n' +
        '    <h2 class="uk-modal-title">{{id}}</h2>\n' +
        '    <form class="uk-form-stacked">\n' +
        '\n' +
        '    <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">部门</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="childName" class="uk-input" id="form-stacked-text" type="text" placeholder="">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '</form>\n' + '<button class="uk-button uk-button-danger" @click="deleteData">删除</button>' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="update">修改</button>' +
        '</div>'
})

/*place的模态(modal)框*/
Vue.component('place-modal', {
    props: ['id', 'name', 'salary', 'deptselect', 'deptlist'],
    data: function () {
        return {
            childName: this.name,
            childDeptSelect: this.deptselect,
            childDepts: this.deptlist,
            childSalary: this.salary
        }
    },
    methods: {
        deleteData: function () {
            deleteAlert()
        },
        update: function () {
            data = JSON.stringify({
                id: this.id,
                name: this.name,
                deptId: this.childDeptSelect,
                salary: this.childSalary
            })
            update(vm.dataType[vm.navIdInTb], data)
        }
    },
    watch: {
        name: function (val) {
            this.childName = val
        },
        deptselect: function (val) {
            this.childDeptSelect = val
        },
        salary: function (val) {
            this.childSalary = val
        },
        childName: function (val) {
            this.$emit('on-name-change', val)
        },
        childDeptSelect: function (val) {
            this.$emit('on-dept-select-change', val)
        },
        childSalary: function (val) {
            this.$emit('on-salary-change', val)
        }
    },
    template: '<div class="uk-modal-dialog uk-modal-body">\n' +
        '    <h2 class="uk-modal-title">{{id}}</h2>\n' +
        '    <form class="uk-form-stacked">\n' +
        '\n' +
        '    <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">职位</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="childName" class="uk-input" id="form-stacked-text" type="text" placeholder="">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '     <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">基本薪资</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="childSalary" class="uk-input" id="form-stacked-text" type="text" placeholder="">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '     <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="select-dept">所属部门</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <select class="uk-select" id="select-dept" v-model="childDeptSelect">\n' +
        '                <option v-for="dept in deptlist" :value="dept.id">{{dept.name}}</option>\n' +

        '            </select>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '</form>\n' +
        '<button class="uk-button uk-button-danger" @click="deleteData">删除</button>\n' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="update">修改</button>\n' +
        '</div>'
})

/*salary的模态框*/
Vue.component('salary-modal', {
    props: ['id', 'name', 'salary'],
    template: '<div class="uk-modal-dialog uk-modal-body">\n' +
        '    <h2 class="uk-modal-title">{{id}}</h2>\n' +
        '    <form class="uk-form-stacked">\n' +
        '\n' +
        '        <div class="uk-margin">\n' +
        '            <label class="uk-form-label" for="form-stacked-text">姓名</label>\n' +
        '            <div class="uk-form-controls">\n' +
        '                <input :value="name" class="uk-input" id="form-stacked-text" type="text" disabled="disabled">\n' +
        '            </div>\n' +
        '        </div>\n' +
        '\n' +
        '        <div class="uk-margin">\n' +
        '            <label class="uk-form-label" for="form-stacked-text">工资</label>\n' +
        '            <div class="uk-form-controls">\n' +
        '                <input :value="salary" class="uk-input" id="form-stacked-text" type="text" disabled="disabled">\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </form>\n' +
        '</div>'
})


/*person 添加 modal*/
Vue.component('person-add-modal', {
    props: ['person_places', 'currentdate'],
    data: function () {
        return {
            name: '',
            pass: '',
            salary: '',
            born: '',
            sex: '',
            enterTime: this.currentdate,
            age: 0,
            childPlaces: this.person_places,
            childSelectVal: 0
        }
    },
    watch: {
        /*当职位选择发生变化的时候,发送请求获取对应基本底薪显示*/
        childSelectVal: function (val) {
            var _this = this
            $.ajax({
                type: "post",
                url: "/place/" + val,
                dataType: "json",
                success: function (data) {
                    _this.salary = data['salary']
                }
            })
        },
        born: function (val) {
            console.log("born watch!")
            if (this.checkDateComp(val)) {
                console.log("born判断正确")

                this.age = 2018 - val.substring(0, 4)
            }
        },
        person_places: function (val) {
            this.childPlaces = val
        },
        currentdate: function (val) {
            this.enterTime = val
        }
    },
    computed: {
        isEverythingOK: function () {
            return this.name != ''
                && this.pass != ''
                && this.born != ''
                && this.sex != ''
                && this.enterTime != ''
                && this.age != 0
                && this.childSelectVal != 0
        }
    },
    methods: {
        /*点击添加按钮触发函数add()*/
        add: function () {
            data = JSON.stringify({
                name: this.name,
                pass: this.pass,
                salary: this.salary,
                sex: this.sex,
                age: this.age,
                born: this.born,
                enterTime: this.enterTime,
                placeId: this.childSelectVal
            })
            add(vm.dataType[vm.navIdInTb], data)
        },
        checkDateComp: function (date) {
            console.log("checkDateComp: " + checkDate(date))
            return checkDate(date)
        },
    },
    template: '<div class="uk-modal-dialog uk-modal-body">\n' +
        '        <form class="uk-form-stacked">\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-stacked-text">姓名</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="name" class="uk-input" id="form-stacked-text" type="text" autofocus>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin uk-grid-small uk-child-width-auto uk-grid">\n' +
        '                 <label><input class="uk-radio" type="radio" name="radio2" @click="sex = \'男\' ">男</label>\n' +
        '                 <label><input class="uk-radio" type="radio" name="radio2" @click="sex = \'女\' ">女</label>\n' +
        '            </div>' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-age-text">年龄</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="age" class="uk-input" id="form-age-text" type="text" disabled="disabled">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-born-text">出生日期</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="born" class="uk-input" :class="{\'uk-form-danger\': !checkDateComp(born)}" id="form-born-text" type="text" uk-tooltip="title: xxxx-xx-xx; pos: top-right">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-enter-text">入职时间</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="enterTime" class="uk-input" id="form-enter-text" disabled="disabled" type="text" uk-tooltip="title: xxxx-xx-xx; pos: top-right">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-pass-text">密码</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="pass" class="uk-input" id="form-pass-text" type="text">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-stacked-select">职位</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                   <select class="uk-select" id="form-stacked-select" v-model="childSelectVal">\n' +
        '                       <option v-for="place in childPlaces" :value="place.id">{{place.name}}</option>\n' +
        '                   </select>' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-stacked-text-bonus">薪资</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="salary" class="uk-input" disabled="disabled" id="form-stacked-text-bonus" type="text" :placeholder="salary">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </form>\n' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="add" :disabled="!isEverythingOK">添加</button>' +
        '    </div>'
})

/*dept 添加 modal*/
Vue.component('dept-add-modal', {
    data: function () {
        return {
            name: ''
        }
    },
    computed: {
        isEverythingOK: function () {
            return this.name != ''
        }
    },
    methods: {
        add: function () {
            data = JSON.stringify({
                "name": this.name
            })
            add(vm.dataType[vm.navIdInTb], data)
        }
    },
    template: '<div class="uk-modal-dialog uk-modal-body">\n' +
        '        <form class="uk-form-stacked">\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-stacked-text">部门名字</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input v-model="name" class="uk-input" id="form-stacked-text" type="text" autofocus>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </form>\n' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="add" :disabled="!isEverythingOK">添加</button>' +
        '    </div>'
})

/*place 添加 modal*/
Vue.component('place-add-modal', {
    props: ['deptlist'],
    data: function () {
        return {
            placeName: '',
            baseSalary: '',
            deptSelect: 0,
            existInvalidChar: false
        }
    },
    watch: {
        baseSalary: function (val) {
            /*如果不全是数字，existInvalidChar返回true*/
            for (var i = 0; i < val.length; i++) {
                if (isNaN(parseInt(val.charAt(i)))) {
                    this.existInvalidChar = true
                    return
                }
            }
            this.existInvalidChar = false
        }
    },
    computed: {
        isEverythingOK: function () {
            return this.placeName != ''
                && this.baseSalary != ''
                && this.deptSelect != 0
        }
    },
    methods: {
        add: function () {
            data = JSON.stringify({
                "name": this.placeName,
                "salary": this.baseSalary,
                "deptId": this.deptSelect
            })
            add(vm.dataType[vm.navIdInTb], data)
        }
    },
    template: '<div class="uk-modal-dialog uk-modal-body">\n' +
        '    <form class="uk-form-stacked">\n' +
        '\n' +
        '    <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">职位</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="placeName" class="uk-input" id="form-stacked-text" type="text" placeholder="">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '     <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">基本薪资</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="baseSalary" class="uk-input" :class="{\'uk-form-danger\' : existInvalidChar}" id="form-stacked-text" type="text" placeholder="">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '     <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="select-dept">所属部门</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <select class="uk-select" id="select-dept" v-model="deptSelect">\n' +
        '                <option v-for="dept in deptlist" :value="dept.id">{{dept.name}}</option>\n' +

        '            </select>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '</form>\n' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="add" :disabled="!isEverythingOK || existInvalidChar">添加</button>\n' +
        '</div>'
})

/*salary 添加 modal*/
Vue.component('salary-add-modal', {
    props: ['currentdate'],
    data: function () {
        return {
            person_id: null,
            ab_days: 0,
            ov_days: 0,
            person_id_invalid: false,
            ab_days_invalid: false,
            ov_days_invalid: false,
            person_exist: true,
            person_name: '',
        }
    },
    computed: {
        is_everthing_ok:function () {
            return this.person_id != null &&
                this.person_name != '' &&
                this.person_name != null &&
                this.person_id_invalid != true &&
                this.ab_days_invalid != true &&
                this.ov_days_invalid != true
        }
    },
    methods: {
        add:function () {
            // console.log("小青好傻")
            payload = {
                "personId":this.person_id,
                "absenceDays":this.ab_days,
                "overTimeDays":this.ov_days,
                "recordDate":this.currentdate
            }

            $.ajax({
                type:'post',
                url:'/salary/add',
                contentType:'application/json',
                data:JSON.stringify(payload),
                dataType:'json',
                success:function (data) {
                    if (data) {
                        UIkit.modal.dialog('<div class="uk-alert-success" uk-alert>\n' +
                            '    <p>已成功添加</p>\n' +
                            '</div>');
                    } else {
                        UIkit.modal.dialog('<div class="uk-alert-danger" uk-alert>\n' +
                            '    <p>添加失败</p>\n' +
                            '</div>');
                    }
                }
            })
        }
    },
    watch: {
        person_id: function (val) {
            /*如果不全是数字，existInvalidChar返回true*/
            for (var i = 0; i < val.length; i++) {
                if (isNaN(parseInt(val.charAt(i)))) {
                    this.person_id_invalid = true
                    return
                }
            }
            this.person_id_invalid = false

            /*如果全是数字，发送ajax请求获取指定id的名字*/
            if (!this.person_id_invalid) {
                var name
                $.ajax({
                    type: 'post',
                    url: '/person/name/' + this.person_id,
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        name = data.name
                    }
                })
                this.person_name = name
            }
        },
        ab_days:function (val) {
            /*如果不全是数字，existInvalidChar返回true*/
            for (var i = 0; i < val.length; i++) {
                if (isNaN(parseInt(val.charAt(i)))) {
                    this.ab_days_invalid = true
                    return
                }
            }
            this.ab_days_invalid = false

            /*缺勤天数不超过30天*/
            if (val > 30) {
                this.ab_days_invalid = true
            } else {
                this.ab_days_invalid = false
            }
        },
        ov_days: function (val) {
            /*如果不全是数字，existInvalidChar返回true*/
            for (var i = 0; i < val.length; i++) {
                if (isNaN(parseInt(val.charAt(i)))) {
                    this.ov_days_invalid = true
                    return
                }
            }
            this.ov_days_invalid = false

            /*加班天数不超过30天*/
            if (val > 30) {
                this.ov_days_invalid = true
            } else {
                this.ov_days_invalid = false
            }
        }
    },
    template: '<div class="uk-modal-dialog uk-modal-body">\n' +
        '    <form class="uk-form-stacked">\n' +
        '    <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">人员编号</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="person_id" class="uk-input" :class="{\'uk-form-danger\' : person_id_invalid}" id="form-stacked-text" type="text" placeholder="">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">名称</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="person_name" class="uk-input" id="form-stacked-text" type="text" placeholder="" disabled="disabled">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '     <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">缺勤天数</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="ab_days" class="uk-input" :class="{\'uk-form-danger\' : ab_days_invalid}" id="form-stacked-text" type="text" placeholder="">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '     <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">加班天数</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="ov_days" class="uk-input" :class="{\'uk-form-danger\' : ov_days_invalid}" id="form-stacked-text" type="text" placeholder="">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '     <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">记录时间</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="this.currentdate" class="uk-input" id="form-stacked-text" type="text" disabled="disabled">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '</form>\n' +
        '<button class="uk-button uk-button-primary uk-align-right" :disabled="!is_everthing_ok" @click="add">添加</button>\n' +
        '</div>'
})