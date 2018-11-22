Vue.component('person-head', {
    template: '<thead>\n' +
        '        <tr>\n' +
        '            <th>id</th>\n' +
        '            <th>姓名</th>\n' +
        '            <th>薪资</th>\n' +
        '            <th>职位</th>\n' +
        '        </tr>\n' +
        '    </thead>\n'
})

Vue.component('dept-head', {
    template: ' <thead>\n' +
        '        <tr>\n' +
        '            <th>id</th>\n' +
        '            <th>部门</th>\n' +
        '            <th>父级部门</th>\n' +
        '        </tr>\n' +
        '    </thead>'
})

Vue.component('place-head', {
    template: ' <thead>\n' +
        '        <tr>\n' +
        '            <th>id</th>\n' +
        '            <th>职位</th>\n' +
        '            <th>基本薪资</th>\n' +
        '            <th>所属部门</th>\n' +
        '        </tr>\n' +
        '    </thead>'
})

Vue.component('bonus-head', {
    template: ' <thead>\n' +
        '        <tr>\n' +
        '            <th>id</th>\n' +
        '            <th>姓名</th>\n' +
        '            <th>奖金</th>\n' +
        '        </tr>\n' +
        '    </thead>'
})



/*person的模态(modal)框*/
Vue.component('person-modal', {
    props: ['id', 'name', 'salary', 'selectvalue','datatype'],
    data: function () {
        return {
            childSelectVal: this.selectvalue,
            childName: this.name
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
        }
    },
    methods:{
        update:function () {
            data = JSON.stringify({
                id:this.id,
                name:this.name,
                placeId:this.childSelectVal
            })
            update(vm.dataType[vm.navIdInTb], data)
        },
        deleteData:function () {
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
        '                <label class="uk-form-label" for="form-stacked-text-bonus">薪资</label>\n' +
        '                <div class="uk-form-controls">\n' +
        '                    <input class="uk-input" disabled="disabled" id="form-stacked-text-bonus" type="text" :placeholder="salary">\n' +
        '                </div>\n' +
        '            </div>\n' +
        '            <div class="uk-margin">\n' +
        '                <label class="uk-form-label" for="form-stacked-select">职位</label>\n' +
        '                <div class="uk-form-controls">\n' + '<select class="uk-select" id="form-stacked-select" v-model="childSelectVal">\n' +
        '    <option value="1">架构师</option>\n' +
        '    <option value="2">前端工程师</option>\n' +
        '    <option value="3">Java工程师</option>\n' +
        '    <option value="4">Python工程师</option>\n' +
        '    <option value="5">测试工程师</option>\n' +
        '    <option value="6">运维工程师</option>\n' +
        '    <option value="7">产品经理</option>\n' +
        '    <option value="8">产品助理</option>\n' +
        '    <option value="9">设计师</option>\n' +
        '    <option value="10">产品运营</option>\n' +
        '    <option value="11">活动策划</option>\n' +
        '    <option value="12">会员运营</option>\n' +
        '    <option value="13">数据运营</option>\n' +
        '    <option value="14">媒体运营</option>\n' +
        '    <option value="15">内容策划</option>\n' +
        '    <option value="16">编辑</option>\n' +
        '</select>' +
        '                </div>\n' +
        '            </div>\n' +
        '\n' +
        '        </form>\n' + '<button class="uk-button uk-button-danger" @click="deleteData">删除</button>' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="update">修改</button>' +
        '    </div>'
})

/*dept的模态(modal)框*/
Vue.component('dept-modal',{
    props:['id', 'name', 'fatherid'],
    data:function(){
        return{
            childName: this.name,
            childFatherId:this.fatherid
        }
    },
    watch:{
        name:function (val) {
            this.childName = val
        },
        fatherid:function (val) {
            this.childFatherId = val
        },
        childName:function (val) {
            this.$emit('on-name-change', val)
        },
        childFatherId:function (val) {
            this.$emit('on-fatherid-change', val)
        }
    },
    methods:{
        deleteData:function () {
            deleteAlert();
        },
        update:function () {
            data = JSON.stringify({
                id:this.id,
                name:this.name,
                fatherId:this.childFatherId
            })
            update(vm.dataType[vm.navIdInTb], data)
        }
    },
    template:'<div class="uk-modal-dialog uk-modal-body">\n' +
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
        '     <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="form-stacked-text">父级部门</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <input v-model="childFatherId" class="uk-input" id="form-stacked-text" type="text" placeholder="">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '</form>\n'  + '<button class="uk-button uk-button-danger" @click="deleteData">删除</button>' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="update">修改</button>' +
        '</div>'
})

/*place的模态(modal)框*/
Vue.component('place-modal',{
    props:['id', 'name', 'salary', 'deptid'],
    data:function(){
        return{
            childName:this.name,
            childDeptId:this.deptId
        }
    },
    methods:{
        deleteData:function () {
            deleteAlert()
        },
        update:function () {
            data = JSON.stringify({
                id:this.id,
                name:this.name,
                deptId:this.childDeptId
            })
            update(vm.dataType[vm.navIdInTb], data)
        }
    },
    watch:{
        name:function (val) {
            this.childName = val
        },
        deptid:function (val) {
            this.childDeptId = val
        },
        childName:function (val) {
            this.$emit('on-name-change', val)
        },
        childDeptId:function (val) {
            this.$emit('on-deptid-change', val)
        }
    },
    template:'<div class="uk-modal-dialog uk-modal-body">\n' +
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
        '            <input :value="salary" class="uk-input" disabled="disabled" id="form-stacked-text" type="text" placeholder="">\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '     <div class="uk-margin">\n' +
        '        <label class="uk-form-label" for="select-dept">所属部门</label>\n' +
        '        <div class="uk-form-controls">\n' +
        '            <select class="uk-select" id="select-dept" v-model="childDeptId">\n' +
        '                <option value="1">技术部门</option>\n' +
        '                <option value="2">产品部门</option>\n' +
        '                <option value="3">运营部门</option>\n' +
        '            </select>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '\n' +
        '</form>\n' +
        '<button class="uk-button uk-button-danger" @click="deleteData">删除</button>\n' +
        '<button class="uk-button uk-button-primary uk-align-right" @click="update">修改</button>\n' +
        '</div>'
})