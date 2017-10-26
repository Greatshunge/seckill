function id(obj){//获取id
	return document.getElementById(obj);
}

function bind(obj,ev,fn){
	if (obj,addEventListener) {
		obj.addEventListener(ev,fn,false);
	}else{
		obj.attachEvent('on'+ev,function(){
			fn.call(obj);
		});
	}
}

function view(){//获取可视区的宽高
	return{
		w:document.documentElement.clientWidth,
		h:document.documentElement.clientHeight
	};
}

function addclass(obj,sClass){//添加CLASS
	var aclass=obj.className.split('');
	if (!obj.className) {
		obj.className=sClass;
		return;
	};
	for (var i=0;i<aclass.length;i++) {
		if (aclass[i]==sClass) {
			return;
		}
	}
		obj.className+=''+sClass;
}

function removeclass(obj,sClass){//删除class
	var aclass=obj.className.split('');
	if (!obl.className) {
		return;
	}
	for (var i=0;i<aclass.length;i++) {
		if (aclass[i]==sClass) {
			aclass.split(i,1);
			obj.className=aclass.json('');
			break;
		}
	}
}

function fnLoad(){
	var ow= id('weclome');
	var arr=[""];
	for (vari=0;i<arr.length;i++) {
		var oimg=new Image();
		oimg.src=arr[i];
		oimg.onload=function(){
			
		}
	}
}











