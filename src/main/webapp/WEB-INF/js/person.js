var Person

var Places

var Datas = new Array()

/**
 * 从数据库获取Place信息，生成id和name对应的Place对象
 */
function getDataInfos(dataType) {
    $.ajax({
        type: "post",
        url: "/" + dataType + "/all",
        dataType: "json",
        async: false,
        success: function (data) {
            if (dataType === 'place')
                Places = data
            if (dataType === 'dept')
                Depts = data
        }
    })
}

getDataInfos('place')

/**
 * 获得指定id的用户的salary信息
 * 将获得的数据放到参数datalist中
 * @param personId 指定的用户的id
 * @param pageNum 指定页数
 * @param datalist 存放获得的数据到datalist中
 */
function getSalaryByPersonId(personId, pageNum, datalist) {
    $.ajax({
        type: 'post',
        url: '/salary/person/' + personId + '/' + pageNum,
        dataType: "json",
        async: false,
        success: function (datas) {
            if (datas.code === "404") {
                /*清空datalist中已有的元素再添加*/
                if (datalist != null) {
                    dataLen = datalist.length;
                    for (i = 0; i < dataLen; i++) {
                        datalist.pop()
                    }
                }
            } else {
                /*清空datalist中已有的元素再添加*/
                if (datalist != null) {
                    dataLen = datalist.length;
                    for (i = 0; i < dataLen; i++) {
                        datalist.pop()
                    }
                }
                /*添加获取的datas到空的datalist中*/
                for (x in datas.data) {
                    datalist.push(datas.data[x]);
                }
            }
        }

    })
}


/*根据id获得人员信息*/
function getPersonById(personId) {
    $.ajax({
        type: 'post',
        url: '/person/get/' + personId,
        dataType: 'json',
        async: false,
        success: function (data) {
            Person = data
            console.log("ajax中:" + Person.name);
        }
    })
}

/*根据职位id获得部门名称*/
function getDeptNameByPlaceId(placeId) {
    $.ajax({
        type: 'post',
        url: '/dept/name/place/' + placeId,
        dataType: 'json',
        async: false,
        success: function (data) {
            vm.deptName = data.deptName
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
        type: "post",
        url: "/" + dataType + "/update",
        dataType: "json",
        contentType: "application/json",
        data: data,
        error: function (error) {
            UIkit.modal.dialog('<div class="uk-alert-danger" uk-alert>\n' +
                '    <a class="uk-alert-close" uk-close></a>\n' +
                '    <p>更新操作失败</p>\n' +
                '</div>');
        },
        success: function (data) {
            window.location.reload()
            UIkit.modal.dialog('<div class="uk-alert-success" uk-alert>\n' +
                '    <a class="uk-alert-close" uk-close></a>\n' +
                '    <p>更新操作已生效</p>\n' +
                '</div>');
        }
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

/*根据peronsId和year和month获得工资信息*/
function getSalaryByPersonIdAndYearAndMonth(personId, year, month) {
    urlstr = ''
    if (year === '' && month === '') {
        table.searchSalary()
        return
    }
    if (year === null || year === '') {
        urlstr = '/salary/person/' + personId + '/month/' + month;
    } else if (month === null || month === '') {
        urlstr = '/salary/person/' + personId + '/year/' + year;
    } else {
        urlstr = '/salary/person/' + personId + '/year/' + year + '/month/' + month;
    }
    $.ajax({
        type: "post",
        url: urlstr,
        dataType: "json",
        success: function (result) {

            pagenation.pages = result.count

            /*清空datalist中已有的元素再添加*/
            if (Datas != null) {
                dataLen = Datas.length;
                for (i = 0; i < dataLen; i++) {
                    Datas.pop()
                }
            }
            /*添加获取的datas到空的datalist中*/
            for (x in result.data) {
                Datas.push(result.data[x]);
            }
        }
    });
}

/*获得指定Id的用户的工资报表的年份*/
function getYearByPersonId(personId, years) {
    $.ajax({
        type: "post",
        url: "/salary/person/" + personId + "/year",
        dataType: "json",
        success: function (data) {
            /*清空datalist中已有的元素再添加*/
            if (years != null) {
                dataLen = years.length;
                for (i = 0; i < dataLen; i++) {
                    years.pop()
                }
            }
            /*添加获取的datas到空的datalist中*/
            for (x in data) {
                years.push(data[x]);
            }
            console.log(years)
        }
    })
}

/*获得指定year的所有记录的月份*/
function getMonthsByPersonIdYear(personId, year, months) {
    if (year === null) {
        urlstr = '/salary/person/' + personId + '/month'
    } else
        urlstr = '/salary/person/' + personId + '/year/' + year + '/month'
    $.ajax({
        type: 'post',
        url: urlstr,
        dataType: 'json',
        success: function (result) {
            /*清空datalist中已有的元素再添加*/
            if (months != null) {
                dataLen = months.length;
                for (i = 0; i < dataLen; i++) {
                    months.pop()
                }
            }
            /*添加获取的datas到空的datalist中*/
            for (x in result) {
                months.push(result[x]);
            }
        }
    });
}

/*获得指定id的用户salary记录的条数*/
function getSalaryCountByPersonId(personId) {
    $.ajax({
        type: 'post',
        url: '/salary/person/' + personId + '/count',
        dataType: 'json',
        async: false,
        success: function (data) {
            pagenation.pages = data
        }
    })
}


var navbar = new Vue({
    el: '#navbar',
    data: {
        navId: 0,
        value: '',
    },
    watch: {
        navId: function (val, oldVal) {
            table.navIdInTb = val
        }
    },
    methods: {
    }
})

var table = new Vue({
    el: '#app',
    data: {
        showPerson: true,
        navIdInTb: 0,
        years: new Array(),
        selectYear: '',
        months: new Array(),
        selectMonth: '',
        datas: Datas,
        currentPage: 1,
        personNum: location.href.substring(29, 33),
        ab_days:'',
        ov_days:''
    },
    watch: {
        navIdInTb: function (val) {
            if (val != 0) {
                this.showPerson = false;
                this.searchSalary()
                getSalaryCountByPersonId(this.personNum)
            } else {
                this.showPerson = true
            }
        },
        showPerson: function (val) {
            pagenation.showPagenation = !val
        },
        selectYear: function (val) {
            getSalaryByPersonIdAndYearAndMonth(this.personNum, this.selectYear, this.selectMonth)
            getMonthsByPersonIdYear(this.personNum, val, this.months)
        },
        selectMonth: function (val) {
            getSalaryByPersonIdAndYearAndMonth(this.personNum, this.selectYear, this.selectMonth)
        },
        currentPage: function (val) {
            getSalaryByPersonId(this.personNum, this.currentPage, this.datas);
        }

    },
    methods: {
        showmodal: function (event) {
            UIkit.modal('#mainModal').show()
            var nodes = event.currentTarget.childNodes
            var dataArr = new Array()
            for (let i = 0; i < nodes.length; i++) {
                data = nodes[i].innerText
                if (data != null) {
                    dataArr.push(data)
                }
            }
            vm.id = dataArr[0]
            vm.name = dataArr[1]
            vm.age = dataArr[2]
            vm.pass = dataArr[3]
            vm.bornTime = dataArr[4]
            vm.enterTime = dataArr[5]

            getPersonById(vm.id)
            console.log("person: " + Person)
            vm.placeSelectValue = Person.placeId

            getDeptNameByPlaceId(Person.placeId)
        },
        searchSalary:function () {
            /*如果person存在，工资不存在的情况是新增的用户*/
            getSalaryByPersonId(this.personNum, this.currentPage, this.datas);

            /*如果返回的数据为空，提示没有找到信息*/
            if (this.datas.length === 0) {
                this.showSalary = false;
                pagenation.showPagenation = false
                UIkit.modal.dialog('<div class="uk-alert-danger" uk-alert>\n' +
                    '    <a class="uk-alert-close" uk-close></a>\n' +
                    '    <p>没有找到人员工资信息</p>\n' +
                    '</div>');
                return
            }

            getYearByPersonId(this.personNum, this.years)
            getMonthsByPersonIdYear(this.personNum, null, this.months)
            getSalaryCountByPersonId(this.personNum)
        },
        getReward:function (event) {
            var nodes = event.currentTarget.childNodes
            var dataArr = new Array()
            for (let i = 0; i < nodes.length; i++) {
                data = nodes[i].innerText
                if (data != null) {
                    dataArr.push(data)
                }
            }
            var id = dataArr[0]
            var record_time = dataArr[5]
            var year = record_time.split('-')[0]
            var month = record_time.split('-')[1]
            var _this = this
            $.ajax({
                type:'post',
                url:'/reward/' + this.personNum + '/' + year + '/' + month,
                dataType:'json',
                async:false,
                success:function (data) {
                    _this.ab_days = data.absence + '天'
                    _this.ov_days = data.overtime + '天';
                }
            })
        }
    }
})

var vm = new Vue({
    el: '#mainModal',
    data: {
        id: 0,
        name: '',
        pass: '',
        born: '',
        age: '',
        enterTime: '',
        bornTime:'',
        placeSelectValue: '',
        deptName: '',
        places: Places
    },
    methods: {
        onSelectValueChange: function (val) {
            getDeptNameByPlaceId(val)
            console.log("父组件接收到调用函数")
            this.placeSelectValue = val
            console.log("新的selectValue: " + val)
        },
        onNameChange: function (val) {
            this.name = val
        },
        onPassChange: function (val) {
            this.pass = val
        },
        onBornChange: function (val) {
            this.born = val
        },
        onAgeChange: function (val) {
            this.age = val
        }
    }
})

/**
 * 分页操作
 */
var pagenation = new Vue({
    el: '#pagenation',
    data: {
        pages: null,
        currentPage: 1,
        showPagenation: false,
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
            table.currentPage = val
        }
    }
})


