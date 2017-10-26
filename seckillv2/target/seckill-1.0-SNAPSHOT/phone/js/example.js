//出生日期
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
function formatYear(nowYear) {
	var arr = [];
	for(var i = nowYear - 5; i <= nowYear + 5; i++) {
		arr.push({
			id: i + '',
			value: i + '年'
		});
	}
	return arr;
}

function formatMonth() {
	var arr = [];
	for(var i = 1; i <= 12; i++) {
		arr.push({
			id: i + '',
			value: i + '月'
		});
	}
	return arr;
}

function formatDate(count) {
	var arr = [];
	for(var i = 1; i <= count; i++) {
		arr.push({
			id: i + '',
			value: i + '日'
		});
	}
	return arr;
}
var yearData = function(callback) {
	setTimeout(function() {
		callback(formatYear(nowYear))
	})
}
var monthData = function(year, callback) {
	setTimeout(function() {
		callback(formatMonth());
	});
};
var dateData = function(year, month, callback) {
	setTimeout(function() {
		if(/^1|3|5|7|8|10|12$/.test(month)) {
			callback(formatDate(31));
		} else if(/^4|6|9|11$/.test(month)) {
			callback(formatDate(30));
		} else if(/^2$/.test(month)) {
			if(year % 4 === 0 && year % 100 !== 0 || year % 400 === 0) {
				callback(formatDate(29));
			} else {
				callback(formatDate(28));
			}
		} else {
			throw new Error('month is illegal');
		}
	});
	// ajax请求可以这样写
	/*
	$.ajax({
	    type: 'get',
	    url: '/example',
	    success: function(data) {
	        callback(data);
	    }
	});
	*/
};
selectDateDom.bind('click', function() {
	var oneLevelId = showDateDom.attr('data-year');
	var twoLevelId = showDateDom.attr('data-month');
	var threeLevelId = showDateDom.attr('data-date');
	var iosSelect = new IosSelect(3, [yearData, monthData, dateData], {
		title: '出生日期',
		itemHeight: 35,
		relation: [1, 1],
		oneLevelId: oneLevelId,
		twoLevelId: twoLevelId,
		threeLevelId: threeLevelId,
		showLoading: true,
		callback: function(selectOneObj, selectTwoObj, selectThreeObj) {
			showDateDom.attr('data-year', selectOneObj.id);
			showDateDom.attr('data-month', selectTwoObj.id);
			showDateDom.attr('data-date', selectThreeObj.id);
			showDateDom.html(selectOneObj.value + ' ' + selectTwoObj.value + ' ' + selectThreeObj.value);
		}
	});
});

//学历
var showBankDom = document.querySelector('#showBank');
var bankIdDom = document.querySelector('#bankId');
showBankDom.addEventListener('click', function() {
	var bankId = showBankDom.dataset['id'];
	var bankName = showBankDom.dataset['value'];

	var bankSelect = new IosSelect(1, [data], {
		container: '.container',
		title: '学历',
		itemHeight: 80,
		itemShowCount: 3,
		oneLevelId: bankId,
		callback: function(selectOneObj) {
			bankIdDom.value = selectOneObj.id;
			bankIdDom.innerHTML = selectOneObj.value;
			showBankDom.dataset['id'] = selectOneObj.id;
			showBankDom.dataset['value'] = selectOneObj.value;
		}
	});
});

//血型
var showBankDomBlood = document.querySelector('#showBlood');
var bankIdDomBlood = document.querySelector('#bloodId');
showBankDomBlood.addEventListener('click', function() {
	var bankIdBlood = showBankDomBlood.dataset['id'];
	var bankNameBlood = showBankDomBlood.dataset['value'];

	var bankSelect = new IosSelect(1, [bool], {
		container: '.container',
		title: '血型',
		itemHeight: 80,
		itemShowCount: 3,
		oneLevelId: bankIdBlood,
		callback: function(selectOneObj) {
			bankIdDomBlood.value = selectOneObj.id;
			bankIdDomBlood.innerHTML = selectOneObj.value;
			showBankDomBlood.dataset['id'] = selectOneObj.id;
			showBankDomBlood.dataset['value'] = selectOneObj.value;
		}
	});
});
//星座
var showBankDomConst = document.querySelector('#showConst');
var bankIdDomConst = document.querySelector('#constId');
showBankDomConst.addEventListener('click', function() {
	var bankIdConst = showBankDomConst.dataset['id'];
	var bankNameConst = showBankDomConst.dataset['value'];

	var bankSelect = new IosSelect(1, [consta], {
		container: '.container',
		title: '星座',
		itemHeight: 80,
		itemShowCount: 3,
		oneLevelId: bankIdConst,
		callback: function(selectOneObj) {
			bankIdDomConst.value = selectOneObj.id;
			bankIdDomConst.innerHTML = selectOneObj.value;
			showBankDomConst.dataset['id'] = selectOneObj.id;
			showBankDomConst.dataset['value'] = selectOneObj.value;
		}
	});
});
//民族
var showBankDomNat = document.querySelector('#showNat');
var bankIdDomNat = document.querySelector('#natId');
showBankDomNat.addEventListener('click', function() {
	var bankIdNat = showBankDomNat.dataset['id'];
	var bankNameNat = showBankDomNat.dataset['value'];
	var bankSelect = new IosSelect(1, [nat], {
		container: '.container',
		title: '民族',
		itemHeight: 80,
		itemShowCount: 3,
		oneLevelId: bankIdNat,
		callback: function(selectOneObj) {
			bankIdDomNat.value = selectOneObj.id;
			bankIdDomNat.innerHTML = selectOneObj.value;
			showBankDomNat.dataset['id'] = selectOneObj.id;
			showBankDomNat.dataset['value'] = selectOneObj.value;
		}
	});
});
//出生地
var selectContactDom = $('#select_contact');
var showContactDom = $('#show_contact');
var contactProvinceCodeDom = $('#contact_province_code');
var contactCityCodeDom = $('#contact_city_code');
selectContactDom.bind('click', function() {
	var sccode = showContactDom.attr('data-city-code');
	var scname = showContactDom.attr('data-city-name');
	var oneLevelId = showContactDom.attr('data-province-code');
	var twoLevelId = showContactDom.attr('data-city-code');
	var threeLevelId = showContactDom.attr('data-district-code');
	var iosSelect = new IosSelect(3, [iosProvinces, iosCitys, iosCountys], {
		title: '地址选择',
		itemHeight: 35,
		relation: [1, 1, 0, 0],
		callback: function(selectOneObj, selectTwoObj, selectThreeObj) {
			contactProvinceCodeDom.val(selectOneObj.id);
			contactProvinceCodeDom.attr('data-province-name', selectOneObj.value);
			contactCityCodeDom.val(selectTwoObj.id);
			contactCityCodeDom.attr('data-city-name', selectTwoObj.value);
			showContactDom.attr('data-province-code', selectOneObj.id);
			showContactDom.attr('data-city-code', selectTwoObj.id);
			showContactDom.attr('data-district-code', selectThreeObj.id);
			showContactDom.html(selectOneObj.value + ' ' + selectTwoObj.value + ' ' + selectThreeObj.value);
		}
	});
});
//居住情况
var showBankDomLive = document.querySelector('#showlive');
var bankIdDomLive = document.querySelector('#liveId');
showBankDomLive.addEventListener('click', function() {
	var bankIdLive = showBankDomLive.dataset['id'];
	var bankNameLive = showBankDomLive.dataset['value'];
	var bankSelect = new IosSelect(1, [live], {
		container: '.container',
		title: '居住情况',
		itemHeight: 80,
		itemShowCount: 3,
		oneLevelId: bankIdLive,
		callback: function(selectOneObj) {
			bankIdDomLive.value = selectOneObj.id;
			bankIdDomLive.innerHTML = selectOneObj.value;
			showBankDomLive.dataset['id'] = selectOneObj.id;
			showBankDomLive.dataset['value'] = selectOneObj.value;
		}
	});
});
//婚姻情况
var showBankDomMarr = document.querySelector('#showmarr');
var bankIdDomMarr = document.querySelector('#marrId');
showBankDomMarr.addEventListener('click', function() {
	var bankIdMarr = showBankDomMarr.dataset['id'];
	var bankNameMarr = showBankDomMarr.dataset['value'];
	var bankSelect = new IosSelect(1, [Marr], {
		container: '.container',
		title: '居住情况',
		itemHeight: 80,
		itemShowCount: 3,
		oneLevelId: bankIdMarr,
		callback: function(selectOneObj) {
			bankIdDomMarr.value = selectOneObj.id;
			bankIdDomMarr.innerHTML = selectOneObj.value;
			showBankDomMarr.dataset['id'] = selectOneObj.id;
			showBankDomMarr.dataset['value'] = selectOneObj.value;
		}
	});
});
//月薪
var showBankDomMonthly = document.querySelector('#showMonthly');
var bankIdDomMonthly = document.querySelector('#MonthlyId');
showBankDomMonthly.addEventListener('click', function() {
	var bankIdMonthly = showBankDomMonthly.dataset['id'];
	var bankNameMonthly = showBankDomMonthly.dataset['value'];
	var bankSelect = new IosSelect(1, [monthly], {
		container: '.container',
		title: '月薪',
		itemHeight: 80,
		itemShowCount: 3,
		oneLevelId: bankIdMonthly,
		callback: function(selectOneObj) {
			bankIdDomMonthly.value = selectOneObj.id;
			bankIdDomMonthly.innerHTML = selectOneObj.value;
			showBankDomMonthly.dataset['id'] = selectOneObj.id;
			showBankDomMonthly.dataset['value'] = selectOneObj.value;
		}
	});
});
//购车情况
var showBankDomBuye = document.querySelector('#showbuye');
var bankIdDomBuye = document.querySelector('#buyeId');
showBankDomBuye.addEventListener('click', function() {
	var bankIdBuye = showBankDomBuye.dataset['id'];
	var bankNameBuye = showBankDomBuye.dataset['value'];
	var bankSelect = new IosSelect(1, [buye], {
		container: '.container',
		title: '购车情况',
		itemHeight: 80,
		itemShowCount: 3,
		oneLevelId: bankIdBuye,
		callback: function(selectOneObj) {
			bankIdDomBuye.value = selectOneObj.id;
			bankIdDomBuye.innerHTML = selectOneObj.value;
			showBankDomBuye.dataset['id'] = selectOneObj.id;
			showBankDomBuye.dataset['value'] = selectOneObj.value;
		}
	});
});

//职业
var showBankDomOccupa = document.querySelector('#showoccupa');
var bankIdDomOccupa = document.querySelector('#occupaId');
showBankDomOccupa.addEventListener('click', function() {
	var bankIdOccupa = showBankDomOccupa.dataset['id'];
	var bankNameOccupa = showBankDomOccupa.dataset['value'];
	var bankSelect = new IosSelect(1, [occupa], {
		container: '.container',
		title: '职业',
		itemHeight: 80,
		itemShowCount: 3,
		oneLevelId: bankIdOccupa,
		callback: function(selectOneObj) {
			bankIdDomOccupa.value = selectOneObj.id;
			bankIdDomOccupa.innerHTML = selectOneObj.value;
			showBankDomOccupa.dataset['id'] = selectOneObj.id;
			showBankDomOccupa.dataset['value'] = selectOneObj.value;
		}
	});

});