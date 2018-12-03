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
getData(1, 'person', datalist)

/**
 * 根据 num 指定页数获取 dataType类型的数据存放在datalist中
 * @param num
 * @param dataType
 * @param datalist
 */
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


var Places;
var Depts;
/**
 * 从数据库获取Place信息，生成id和name对应的Place对象
 */
function getDataInfos(dataType) {
    $.ajax({
        type:"post",
        url: "/" + dataType + "/all",
        dataType:"json",
        async: false,
        success:function (data) {
            if(dataType === 'place')
                Places = data
            if(dataType === 'dept')
                Depts = data
        }
    })
}
getDataInfos('place')
getDataInfos('dept')

/**
 * 返回 datas 指定name的id
 * @param name
 * @param datas
 * @returns {*}
 */
function getIdByName(name, datas) {
    for (i in datas) {
        if (datas[i]['name'] === name) {
            return datas[i]['id']
        }
    }
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
            vm.refreshCurrentPage()
            UIkit.modal.dialog('<div class="uk-alert-danger" uk-alert>\n' +
                '    <a class="uk-alert-close" uk-close></a>\n' +
                '    <p>更新操作失败</p>\n' +
                '</div>');
        },
        success: function (data) {
            vm.refreshCurrentPage()
            UIkit.modal.dialog('<div class="uk-alert-success" uk-alert>\n' +
                '    <a class="uk-alert-close" uk-close></a>\n' +
                '    <p>更新操作已生效</p>\n' +
                '</div>');
            console.log("update操作 " + data)
        }
    })
}


/**
 * 打开id为deleteModal提示删除信息
 */
function deleteAlert() {
    UIkit.modal("#deleteModal").show();
}

/**
 * 删除指定id的dataType类型的值
 * @param dataType
 * @param id
 */
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
        },
        error:function () {
            vm.refreshCurrentPage()
            UIkit.modal.dialog('<div class="uk-alert-danger" uk-alert>\n' +
                '    <a class="uk-alert-close" uk-close></a>\n' +
                '    <p>删除操作失败</p>\n' +
                '</div>');
        }
    })
}


/*向后端发起添加数据的请求*/
function add(dataType, data) {
    $.ajax({
        type:"post",
        url:"/"+dataType+"/add",
        contentType:"application/json",
        dataType:"json",
        data:data,
        success:function () {
            vm.refreshCurrentPage()

            /*添加成功后，重新获取页数*/
            getPageCount(dataType)
            pagenation.pages = pageCount

            UIkit.modal.dialog('<div class="uk-alert-success" uk-alert>\n' +
                '    <a class="uk-alert-close" uk-close></a>\n' +
                '    <p>已成功添加</p>\n' +
                '</div>');
        },
        error: function (error) {
            vm.refreshCurrentPage()
            UIkit.modal.dialog('<div class="uk-alert-danger" uk-alert>\n' +
                '    <a class="uk-alert-close" uk-close></a>\n' +
                '    <p>添加数据失败</p>\n' +
                '</div>');
        },
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




/**
 * 分页操作
 */
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
        },
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
    },
    methods:{
        showAddModal:function () {
            UIkit.modal(modal.modalAddType[this.navId]).show()
        }
    }
})

var vm = new Vue({
    el: '#my-table',
    data: {
        navIdInTb: navbar.navId,
        currentPage: pagenation.currentPage,
        datas: datalist,
        dataType: {
            0: 'person',
            1: 'dept',
            2: 'place',
            3: 'salary'
        },
        tableName:{
            0:'人员',
            1:'部门',
            2:'职位',
            3:'工资'
        }
    },
    methods: {
        showmodal: function (event) {
            UIkit.modal(modal.modalType[this.navIdInTb]).show()
            var nodes = event.currentTarget.childNodes
            var dataArr = new Array()
            for (let i = 0; i < nodes.length; i++) {
                data = nodes[i].innerText
                if (data != null) {
                    dataArr.push(data)
                }
            }

            /*当前nav的值为人员*/
            if (this.navIdInTb === 0) {
                modal.id = dataArr[0]
                modal.name = dataArr[1]
                modal.pass = dataArr[4]
                modal.onDutyRate = dataArr[8].substring(0, dataArr[8].length - 1)
                modal.overTimeRate = dataArr[9].substring(0, dataArr[9].length - 1)
                modal.salary = dataArr[10].slice(1)
                modal.placeSelectValue = getIdByName(dataArr[7], Places)
                modal.deleteInfo = '确认删除吗？'
            }
            /*当前nav的值为部门*/
            if (this.navIdInTb === 1) {
                modal.id = dataArr[0]
                modal.name = dataArr[1]
                modal.fatherId = dataArr[2]
                modal.deleteInfo = '确认删除吗？'
            }
            /*当前nav的值为职位*/
            if (this.navIdInTb === 2) {
                modal.id = dataArr[0]
                modal.name = dataArr[1]
                modal.salary = dataArr[2]
                modal.deptSelectValue = getIdByName(dataArr[3], Depts)
                modal.deleteInfo = '确认删除吗？'
            }
            /*当前nav的值为工资*/
            if (this.navIdInTb === 3) {
                modal.id = dataArr[0]
                modal.name = dataArr[1]
                modal.salary = dataArr[2]
            }
            console.log('modal.placeSelectValue: ' + modal.placeSelectValue);
        },
        refreshCurrentPage: function () {
            getData(this.currentPage, this.dataType[this.navIdInTb], this.datas)
        },
    },
    watch: {
        /*当nav改变时，标签页也改变，应重设currentpage为1*/
        navIdInTb: function (val, oldVal) {
            this.currentPage = 1
            datatypeComment = this.dataType[this.navIdInTb]
            getData(this.currentPage, datatypeComment, this.datas)
            getPageCount(datatypeComment)
            modal.dataType = datatypeComment
            pagenation.pages = pageCount
            pagenation.currentPage = this.currentPage
            modal.navId = val

            /*如果切换到职位标签
            * 重新获取dept信息
            * 刷新modal中depts的数据*/
            if (val === 2){
                getDataInfos('dept')
                modal.depts = Depts
            }
        },
        currentPage: function (val) {
            getData(val, this.dataType[this.navIdInTb], this.datas)
            console.log("currentPage改变获取的值： " + vm.datas)
        }
    }
})

/*modal框*/
var modal = new Vue({
    el: '#mainModal',
    data: {
        id: 0,
        name: '',
        salary: 0,
        pass:'',
        places:Places,
        depts:Depts,
        navId: vm.navIdInTb,
        placeSelectValue: '',
        fatherId: 0,
        deptId: 0,
        onDutyRate:0,
        deptSelectValue:0,
        overTimeRate:0,
        deleteInfo: '确认删除吗?',
        dataType: vm.dataType[vm.navIdInTb],
        modalType: {
            0: '#personMo',
            1: '#deptMo',
            2: '#placeMo',
            3: '#salaryMo'
        },
        modalAddType:{
            0:'#personAddMo',
            1:'#deptAddMo'
        }
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
        onFatherIdChange: function (val) {
            this.fatherId = val
        },
        onDeptSelectChange: function (val) {
            this.deptSelectValue = val
        },
        onRateChange: function (val) {
            this.rate = val
        },
        onPassChange: function(val){
            this.pass = val
        },
        deleteData: function () {
            deleteData(this.dataType, this.id)
        },
        onDutyRateChange:function (val) {
            this.onDutyRate = val
        },
        onOverTimeRateChange:function (val) {
            this.overTimeRate = val
        },
        onSalaryChange:function (val) {
            this.salary = val
        }
    }
})