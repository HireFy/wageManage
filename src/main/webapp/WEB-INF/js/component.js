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
        '            <th>缺勤</th>\n' +
        '            <th>加班</th>\n' +
        '            <th>薪资</th>\n' +
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
        '            <th>姓名</th>\n' +
        '            <th>薪水</th>\n' +
        '        </tr>\n' +
        '    </thead>'
})


/*person的模态(modal)框*/
Vue.component('person-modal', {
    props: ['id', 'name', 'salary', 'selectvalue', 'datatype', 'person_places', 'pass', 'on_duty_rate', 'over_time_rate'],
    data: function () {
        return {
            childSelectVal: this.selectvalue,
            childName: this.name,
            childPlaces: this.person_places,
            childPass: this.pass,
            childOnDutyRate: this.on_duty_rate,
            childOverTimeRate: this.over_time_rate
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
        on_duty_rate: function (val) {
            this.childOnDutyRate = val
        },
        childOnDutyRate: function (val) {
            this.$emit('on-duty-rate-change', val)
        },
        over_time_rate: function (val) {
            this.childOverTimeRate = val
        },
        childOverTimeRate: function (val) {
            this.$emit('on-over-time-rate-change', val)
        }
    },
    methods: {
        update: function () {
            data = JSON.stringify({
                id: this.id,
                name: this.name,
                placeId: this.childSelectVal,
                pass: this.pass,
                onDutyRate: this.childOnDutyRate / 100,
                overTimeRate: this.childOverTimeRate / 100,
            })

            update(vm.dataType[vm.navIdInTb], data)
        },
        deleteData: function () {
            deleteAlert();
        }
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
        '                <label class="uk-form-label" for="form-on-duty-text">缺勤率</label>\n' +
        '                <div class="uk-form-controls uk-inline" style="width: 100%">' +
        '                    <input v-model="childOnDutyRate" :class="{\'uk-form-danger\': childOnDutyRate > 10}" class="uk-input" id="form-on-duty-text" type="text" uk-tooltip="title: 不超过10%; pos: top-right;">' +
        '                    <span class="uk-form-icon uk-form-icon-flip">%</span>'+
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-over-time-text">加班率</label>\n' +
        '                <div class="uk-form-controls uk-inline" style="width: 100%">\n' +
        '                    <input v-model="childOverTimeRate" :class="{\'uk-form-danger\': childOverTimeRate > 100}" class="uk-input" id="form-over-time-text" type="text">\n' +
        '                    <span class="uk-form-icon uk-form-icon-flip">%</span>'+
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
        '                <label class="uk-form-label" for="form-stacked-text-bonus">薪资</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input class="uk-input" disabled="disabled" id="form-stacked-text-bonus" type="text" :placeholder="salary">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </form>\n' + '<button class="uk-button uk-button-danger" @click="deleteData">删除</button>' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="update" :disabled="(childOnDutyRate > 10) || (childOverTimeRate > 100)">修改</button>' +
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
            childDepts:this.deptlist,
            childSalary:this.salary
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
        salary:function(val){
            this.childSalary = val
        },
        childName: function (val) {
            this.$emit('on-name-change', val)
        },
        childDeptSelect: function (val) {
            this.$emit('on-dept-select-change', val)
        },
        childSalary:function (val) {
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
    props: ['person_places'],
    data: function () {
        return {
            name: '',
            pass: '',
            salary: '',
            born:'',
            sex:'',
            enterTime:'',
            age:0,
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
        born:function (val) {
            console.log("born watch!")
            if(this.checkDateComp(val)){
                console.log("born判断正确")

                this.age = 2018 - val.substring(0, 4)
            }
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
                born:this.born,
                enterTime:this.enterTime,
                placeId: this.childSelectVal
            })
            add(vm.dataType[vm.navIdInTb], data)
        },
        checkDateComp:function (date) {
            console.log("checkDateComp: " + checkDate(date))
            return checkDate(date)
        }
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
        '                    <input v-model="enterTime" class="uk-input" :class="{\'uk-form-danger\': !checkDateComp(enterTime)}" id="form-enter-text" type="text" uk-tooltip="title: xxxx-xx-xx; pos: top-right">\n' +
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
        '<button class="uk-button uk-button-primary uk-align-right" @click="add">添加</button>' +
        '    </div>'
})