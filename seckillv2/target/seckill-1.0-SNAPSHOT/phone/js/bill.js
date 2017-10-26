var showGeneralDom = document.querySelector('#showGeneral');
var suIdDom = document.querySelector('#suId');
var weiIdDom = document.querySelector('#weiId');
showGeneralDom.addEventListener('click', function() {
	var suId = showGeneralDom.dataset['su_id'];
	var suValue = showGeneralDom.dataset['su_value'];
	var weiId = showGeneralDom.dataset['wei_id'];
	var weiValue = showGeneralDom.dataset['wei_value'];
	var sanguoSelect = new IosSelect(2, [suData, weiData], {
		itemHeight: 35,
		oneLevelId: suId,
		twoLevelId: weiId,
		callback: function(selectOneObj, selectTwoObj) {
			suIdDom.value = selectOneObj.id;
			weiIdDom.value = selectTwoObj.id;
			showGeneralDom.dataset['su_id'] = selectOneObj.id;
			showGeneralDom.dataset['su_value'] = selectOneObj.value;
			showGeneralDom.dataset['wei_id'] = selectTwoObj.id;
			showGeneralDom.dataset['wei_value'] = selectTwoObj.value;
		}
	});
});