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


/**
 * 执行更新，自动刷新当前页 提示修改成功或失败
 * @param dataType
 * @param data
 */
function update(dataType, data) {
    $.ajax({
        type:"post",
        url:"/"+ dataType + "/update",
        dataType:"json",
        contentType:"application/json",
        data:data,
        error:function(error){
            vm.refreshCurrentPage()
            UIkit.modal.dialog('<div class="uk-alert-danger" uk-alert>\n' +
                '    <a class="uk-alert-close" uk-close></a>\n' +
                '    <p>更新操作失败</p>\n' +
                '</div>');
        },
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

/**
 * 提示删除信息
 */
function deleteAlert(){
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
        datas: datalist,
        dataType:{
            0:'person',
            1:'dept',
            2:'place',
            3:'bonus'
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
            if(this.navIdInTb === 0) {
                modal.id = dataArr[0]
                modal.name = dataArr[1]
                modal.salary = dataArr[2]
                modal.placeSelectValue = dataArr[3]
            }
            if(this.navIdInTb === 1){
                modal.id = dataArr[0]
                modal.name = dataArr[1]
                modal.fatherId = dataArr[2]
            }
            console.log('modal.placeSelectValue: ' + modal.placeSelectValue)
        },
        refreshCurrentPage:function () {
            getData(this.currentPage, this.dataType[this.navIdInTb], this.datas)
        }
    },
    watch: {
        navIdInTb: function (val, oldVal) {
            datatypeComment = this.dataType[this.navIdInTb]
            getData(1, datatypeComment, this.datas)
            getPageCount(datatypeComment)
            modal.dataType = datatypeComment
            pagenation.pages = pageCount
            modal.navId = val
        },
        currentPage: function (val) {
            getData(val, this.dataType[this.navIdInTb], this.datas)
            console.log(vm.datas)
        }
    }
})


var modal = new Vue({
    el: '#mainModal',
    data: {
        id: 0,
        name: '',
        salary: 0,
        navId: vm.navIdInTb,
        placeSelectValue: '',
        fatherId:0,
        dataType:vm.dataType[this.navId],
        modalType:{
            0:'#personMo',
            1:'#deptMo',
            2:'#placeMo',
            3:'#bonusMo'
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
        onFatherIdChange:function(val){
            this.fatherId = val
        },
        deleteData:function () {
            deleteData(this.dataType, this.id)
        }
    }
})