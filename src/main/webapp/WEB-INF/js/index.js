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
            update(this.datatype, data)
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
        '                <label class="uk-form-label" for="form-stacked-select">Select</label>\n' +
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

/*定义对象*/
function Person(id, name, salary, placeId) {
    this.id = id
    this.name = name
    this.salary = salary
    this.placeId = placeId
}

Person.prototype.comment = 'person'

function Dept(id, name, fatherId) {
    this.id = id
    this.name = name
    this.fatherId = fatherId
}

Dept.prototype.comment = 'dept'

function Place(id, name, salary, depeId) {
    this.id = id
    this.name = name
    this.salary = salary
    this.deptId = depeId
}

Place.prototype.comment = 'place'

function Bonus(id, personId, rate) {
    this.id = id
    this.personId = personId
    this.rate = rate
}

Bonus.prototype.comment = 'bonus'


var pageCount;
getPageCount('person');

/*页面加载的时候先获取person总页数*/
function getPageCount(dataType) {
    $.post({
        url: "/" + dataType + "/pageCount",
        dataＴype: "json",
        async: false,
        success: function (data) {
            pageCount = data
        }
    })
}

/*第一次加载页面的时候获取person*/
var datalist = new Array()
getData(1, new Person().comment, datalist)

function getData(num, dataType, datalist) {
    $.ajax({
        type: "post",
        url: '/' + dataType + '/page/' + num,
        dataＴype: "json",
        async: false,
        success: function (datas) {
            /*清空datalist中已有的元素再添加*/
            if (datalist != null) {
                dataLen = datalist.length
                for (i = 0; i < dataLen; i++) {
                    datalist.pop()
                }
            }
            /*添加获取的datas到空的datalist中*/
            for (x in datas) {
                datalist.push(datas[x]);
            }
        }
    })
}

/*执行更新
* 当更新成功的时候，自动刷新当前页
* 提示修改成功*/
function update(dataType, data) {
    $.ajax({
        type:"post",
        url:"/"+ dataType + "/update",
        dataType:"json",
        contentType:"application/json",
        data:data,
        success:function (data) {
            vm.refreshCurrentPage()
            UIkit.modal.dialog('<div class="uk-alert-success" uk-alert>\n' +
                '    <a class="uk-alert-close" uk-close></a>\n' +
                '    <p>更新操作已生效</p>\n' +
                '</div>');
            console.log("update操作 " + data)
        }
    })
}

function deleteAlert(){
    UIkit.modal("#deleteModal").show();
}

function deleteData(dataType, id) {
        $.ajax({
            type: "post",
            url: "/" + dataType + "/delete/" + id,
            dataType: "json",
            success: function () {
                vm.refreshCurrentPage()
                console.log("刷新页面操作已完成")
                UIkit.modal.dialog('<div class="uk-alert-success" uk-alert>\n' +
                    '    <a class="uk-alert-close" uk-close></a>\n' +
                    '    <p>删除操作已生效</p>\n' +
                    '</div>');
            }
        })
}


var pagenation = new Vue({
    el: '#pagenation',
    data: {
        pages: pageCount,
        currentPage: 1,
    },
    methods: {
        focusCurrent: function (event) {
            eventNum = parseInt(event.target.text);
            if (eventNum != null)
                this.currentPage = eventNum
        },
        goPrev: function () {
            this.currentPage--
        },
        toNext: function () {
            this.currentPage++
        }
    },
    watch: {
        currentPage: function (val) {
            vm.currentPage = val
        }
    }
})


var navbar = new Vue({
    el: '#navbar',
    data: {
        navId: 0
    },
    watch: {
        navId: function (val, oldVal) {
            console.log("navId in navbar: " + oldVal + " ===> " + val)
            vm.navIdInTb = val
        }
    }
})

var vm = new Vue({
    el: '#my-table',
    data: {
        navIdInTb: navbar.navId,
        currentPage: pagenation.currentPage,
        datas: datalist
    },
    methods: {
        getDataType: function () {
            if (this.navIdInTb == 0)
                return new Person()
            if (this.navIdInTb == 1)
                return new Dept()
            if (this.navIdInTb == 2)
                return new Place()
            if (this.navIdInTb == 3)
                return new Bonus()
        },
        showmodal: function (event) {
            UIkit.modal("#mymodal").show();
            var nodes = event.currentTarget.childNodes
            var dataArr = new Array()
            for (let i = 0; i < nodes.length; i++) {
                data = nodes[i].innerText
                if (data != null) {
                    dataArr.push(data)
                }
            }
            modal.id = dataArr[0]
            modal.name = dataArr[1]
            modal.salary = dataArr[2]
            modal.placeSelectValue = dataArr[3]
            console.log('modal.placeSelectValue: ' + modal.placeSelectValue)
        },
        refreshCurrentPage:function () {
            getData(this.currentPage, this.getDataType().comment, this.datas)
        }
    },
    watch: {
        navIdInTb: function (val, oldVal) {
            datatypeComment = this.getDataType().comment
            getData(1, datatypeComment, this.datas)
            getPageCount(datatypeComment)
            modal.dataType = datatypeComment
            pagenation.pages = pageCount
            modal.navId = val
        },
        currentPage: function (val) {
            getData(val, this.getDataType().comment, this.datas)
            console.log(vm.datas)
        }
    }
})


var modal = new Vue({
    el: '#mymodal',
    data: {
        id: 0,
        name: '',
        salary: 0,
        navId: vm.navIdInTb,
        placeSelectValue: '',
        dataType:vm.getDataType().comment
    },
    methods: {
        onSelectValueChange: function (val) {
            console.log("父组件接收到调用函数")
            this.placeSelectValue = val
            console.log("新的selectValue: " + val)
        },
        onNameChange: function (val) {
            this.name = val
        },
        deleteData:function () {
            deleteData(this.dataType, this.id)
        }
    }
})