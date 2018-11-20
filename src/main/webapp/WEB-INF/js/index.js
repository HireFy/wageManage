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
                return 'person'
            if (this.navIdInTb == 1)
                return 'dept'
            if (this.navIdInTb == 2)
                return 'place'
            if (this.navIdInTb == 3)
                return 'bonus'
        }
    },
    watch: {
        navIdInTb: function (val, oldVal) {
            console.log("vm watch navIdInTb: " + this.getDataType())
            getData(1, this.getDataType(), this.datas)
            getPageCount(this.getDataType())
            console.log("pageCount:" + pageCount)
            pagenation.pages = pageCount
        },
        currentPage: function (val) {
            getData(val, this.getDataType(), this.datas)
            console.log(vm.datas)
        }
    }
})

