//上传图片
$(function() {
	"use strict";
	$('#content').artEditor({
		imgTar: '#imageUpload',
		limitSize: 50, // 兆
		showServer: true,
		uploadUrl: 'http://localhost/PROJECT_OWN/NodeJS/artEditor/service/service.php',
		data: {},
		uploadField: 'image',
		placeholader: '<p>请输入文章正文内容</p>',
		validHtml: ["<br/>"],
		formInputId: 'target',
		uploadSuccess: function(res) {
			// 这里是处理返回数据业务逻辑的地方
			// `res`为服务器返回`status==200`的`response`
			// 如果这里`return <path>`将会以`<img src='path'>`的形式插入到页面
			// 如果发现`res`不符合业务逻辑
			// 比如后台告诉你这张图片不对劲
			// 麻烦返回 `false`
			// 当然如果`showServer==false`
			// 无所谓咯
			var result = JSON.parse(res)
			if(result['code'] == '100') {
				return result['data']['url'];
			} else {
				switch(result['code']) {
					case '101':
						{
							alert('图片太大之类的')
						}
				}
			}
			return false;
		},
		uploadError: function(status, error) {
			//这里做上传失败的操作
			//也就是http返回码非200的时候
			alert('网络异常' + status)
		}
	});
});

//选择则发布时间
 var selectDateDom = $('#selectDate');
    var showDateDom = $('#showDate');
    // 初始化时间
    var now = new Date();
    var nowYear = now.getFullYear();
    var nowMonth = now.getMonth() + 1;
    var nowDate = now.getDate();
    showDateDom.attr('data-year', nowYear);
    showDateDom.attr('data-month', nowMonth);
    showDateDom.attr('data-date', nowDate);
    // 数据初始化
    function formatYear (nowYear) {
        var arr = [];
        for (var i = nowYear - 5; i <= nowYear + 5; i++) {
            arr.push({
                id: i + '',
                value: i + '年'
            });
        }
        return arr;
    }
    function formatMonth () {
        var arr = [];
        for (var i = 1; i <= 12; i++) {
            arr.push({
                id: i + '',
                value: i + '月'
            });
        }
        return arr;
    }
    function formatDate (count) {
        var arr = [];
        for (var i = 1; i <= count; i++) {
            arr.push({
                id: i + '',
                value: i + '日'
            });
        }
        return arr;
    }
    var yearData = function(callback) {
        callback(formatYear(nowYear))
    }
    var monthData = function (year, callback) {
        callback(formatMonth());
    };
    var dateData = function (year, month, callback) {
        if (/^(1|3|5|7|8|10|12)$/.test(month)) {
            callback(formatDate(31));
        }
        else if (/^(4|6|9|11)$/.test(month)) {
            callback(formatDate(30));
        }
        else if (/^2$/.test(month)) {
            if (year % 4 === 0 && year % 100 !==0 || year % 400 === 0) {
                callback(formatDate(29));
            }
            else {
                callback(formatDate(28));
            }
        }
        else {
            throw new Error('month is illegal');
        }
    };
    var hourData = function(one, two, three, callback) {
        var hours = [];
        for (var i = 0,len = 24; i < len; i++) {
            hours.push({
                id: i,
                value: i + '时'
            });
        }
        callback(hours);
    };
    var minuteData = function(one, two, three, four, callback) {
        var minutes = [];
        for (var i = 0, len = 60; i < len; i++) {
            minutes.push({
                id: i,
                value: i + '分'
            });
        }
        callback(minutes);
    };
    selectDateDom.bind('click', function () {
        var oneLevelId = showDateDom.attr('data-year');
        var twoLevelId = showDateDom.attr('data-month');
        var threeLevelId = showDateDom.attr('data-date');
        var fourLevelId = showDateDom.attr('data-hour');
        var fiveLevelId = showDateDom.attr('data-minute');
        var iosSelect = new IosSelect(5, 
            [yearData, monthData, dateData, hourData, minuteData],
            {
                title: '选择发布时间',
                itemHeight: 35,
                relation: [1, 1, 0, 0],
                itemShowCount: 4,
                oneLevelId: oneLevelId,
                twoLevelId: twoLevelId,
                threeLevelId: threeLevelId,
                fourLevelId: fourLevelId,
                fiveLevelId: fiveLevelId,
                callback: function (selectOneObj, selectTwoObj, selectThreeObj, selectFourObj, selectFiveObj) {
                    showDateDom.attr('data-year', selectOneObj.id);
                    showDateDom.attr('data-month', selectTwoObj.id);
                    showDateDom.attr('data-date', selectThreeObj.id);
                    showDateDom.attr('data-hour', selectFourObj.id);
                    showDateDom.attr('data-minute', selectFiveObj.id);
                    showDateDom.html(selectOneObj.value + ' ' + selectTwoObj.value + ' ' + selectThreeObj.value + ' ' + selectFourObj.value + ' ' + selectFiveObj.value);
                }
        });
    });