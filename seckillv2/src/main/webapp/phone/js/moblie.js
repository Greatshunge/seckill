$(function() {

	//	个人资料页面点击
	$('.ineaclose').click(function() {
		$('.vihide').show();
		$('.vishow').hide();
	})
	//	昵称

	$('.niname').click(function() {
		$('.vihide').hide();
		$('.inameshow').show();
	})
	//	性别
	$('.personal-sex').click(function() {
		$('.vihide').hide();
		$('.sexshow').show();
	})
	$('.sexshow-man').click(function() {
		$(this).attr("src", "img/man-sel@2x.png");
		$('.sexshow-woman').attr("src", "img/lady@2x.png");
	})
	$('.sexshow-woman').click(function() {
		$('.sexshow-man').attr("src", "img/man@2x.png");
		$(this).attr("src", "img/lady-sel@2x.png");
	})
	//	出生日期
	$('.personal-day').click(function() {
		$('.vihide').hide();
		$('.datebirth').show();
	})
	
	//	学历
	$('.personal-quali').click(function() {
		$('.vihide').hide();
		$('.qualifica').show();
	})

	//	血型
	$('.personal-blood').click(function() {
		$('.vihide').hide();
		$('.mebool').show();
	})
	//	星座
	$('.personal-const').click(function() {
		$('.vihide').hide();
		$('.meconst').show();
	})
	//	民族
	$('.personal-nat').click(function() {
		$('.vihide').hide();
		$('.menat').show();
	})
	//	小学
	$('.personal-samsool').click(function() {
		$('.vihide').hide();
		$('.samsool').show();
	})
	//	中学
	$('.personal-midsool').click(function() {
		$('.vihide').hide();
		$('.midsool').show();
	})
	//	高中
	$('.personal-higsool').click(function() {
		$('.vihide').hide();
		$('.higsool').show();
	})
	//	大学
	$('.personal-bigsool').click(function() {
		$('.vihide').hide();
		$('.bigsool').show();
	})
	//	出生地
	$('.personal-city').click(function() {
		$('.vihide').hide();
		$('.borncit').show();
	})

	//	居住情况
	$('.personal-live').click(function() {
		$('.vihide').hide();
		$('.melive').show();
	})
	//	婚姻
	$('.personal-marr').click(function() {
		$('.vihide').hide();
		$('.memarr').show();
	})
	//	月薪
	$('.personal-monthly').click(function() {
		$('.vihide').hide();
		$('.monthly').show();
	})
	//	购车情况
	$('.personal-buye').click(function() {
		$('.vihide').hide();
		$('.buye').show();
	})
	//	经济状况
	$('.personal-econ').click(function() {
		$('.vihide').hide();
		$('.econ').show();
	})
	//	经济状况里面选中
	$('.elrspanc img').click(function() {
		if($(this).attr("src") == "img/yes@2x.png") {
			$(this).attr("src", "img/no@2x.png");
		} else {
			$(this).attr("src", "img/yes@2x.png");
		}
	})
	//	职业
	$('.personal-occupa').click(function() {
		$('.vihide').hide();
		$('.occupa').show();
	})
	//	所在院校
	$('.personal-colleges').click(function() {
		$('.vihide').hide();
		$('.colleges').show();
	})
	//	最喜爱的体育活动
	$('.personal-physical').click(function() {
		$('.vihide').hide();
		$('.physical').show();
	})
	//	最喜爱的食物
	$('.personal-food').click(function() {
		$('.vihide').hide();
		$('.food').show();
	})
	//	最喜爱的电影
	$('.personal-cream').click(function() {
		$('.vihide').hide();
		$('.cream').show();
	})
	//	最喜爱的节目
	$('.personal-gig').click(function() {
		$('.vihide').hide();
		$('.gig').show();
	})
	//	休闲娱乐
	$('.personal-gLeisure').click(function() {
		$('.vihide').hide();
		$('.gLeisure').show();
	})
	//	业余爱好
	$('.personal-hobby').click(function() {
		$('.vihide').hide();
		$('.hobby').show();
	})
	//	真实姓名
	$('.personal-mename').click(function() {
		$('.vihide').hide();
		$('.mename').show();
	})
	//	身份证号
	$('.personal-card').click(function() {
		$('.vihide').hide();
		$('.carda').show();
	})

	//	我的团队人员弹框
	$('.mecont').click(function() {
		$('.teampopbz').show();
		$('.teampopbbz').show();
	})
	//	我的团队人员弹框取消	
	$('.mobteam-input').click(function() {
		$('.teampopbz').hide();
		$('.teampopbbz').hide();
		//		领取奖励弹框消失
		$('.overlayer').hide();
		$('.teampop').hide();

	})

	//	财富团队分享我的链接
	$('.shareLink').click(function() {
		$('.teampopbz').show();
		$('.teampopShare').show();
	})
	//	财富团队分享我的链接取消	
	$('.shareClose').click(function() {
		$('.teampopbz').hide();
		$('.teampopShare').hide();
	})
	//	我的收入转账下一步
	$('.transferNextbtn').click(function() {
		$('.tranferFist').hide();
		$('.tranferSecond').show();
	})
	//	我的收入提示框

	//记得原支付密码	
	$('.payClickrem').click(function() {
		$('.viewFist').hide();
		$('.viewModify').show();
	})
	$('.payClickmber').click(function() {
		$('.viewFist').hide();
		$('.viewReset').show();
	})
	//重置支付密码获取验证码点击
	$('.resetBtnone').click(function() {
		$(this).css("background", "#ddd").css("border", "0px").css("color", "#fff");
		$(this).val("还剩100秒");
	})
	$('.transferbtntwo').click(function() {
		$('.upDown').hide();
		$('.upNext').show();
	})
	//我要发布下一步
	$('.pubilnext').click(function() {
		$('.viewpubli').hide();
		$('.viewTime').show();
	})
	//匿名发布
	$('.publiAnon img').click(function() {
		if($(this).attr("src") == "img/publi-no@2x.png") {
			$(this).attr("src", "img/publi-yes@2x.png");
		} else {
			$(this).attr("src", "img/publi-no@2x.png");
		}
	})
	//选择发布时间的下一步
	$('.viewtimeNext').click(function() {
		$('.viewTime').hide();
		$('.viewReceive').show();
	})

	//选择接收人群里下
	//取消
	$('.receiveclose').click(function() {
		$('.teampopbz').hide();
		$('.vishow').hide();
	})
	//	性别
	$('.receive-sex').click(function() {
		$('.teampopbz').show();
		$('.sexshow').show();
	})
	//	年龄
	$('.receive-day').click(function() {
		$('.teampopbz').show();
		$('.day').show();
	})
	//	学历
	$('.receive-quali').click(function() {
		$('.teampopbz').show();
		$('.qualifica').show();
	})

	//	血型
	$('.receive-blood').click(function() {
		$('.teampopbz').show();
		$('.mebool').show();
	})
	//	星座
	$('.receive-const').click(function() {
		$('.teampopbz').show();
		$('.meconst').show();
	})
	//	民族
	$('.receive-nat').click(function() {
		$('.teampopbz').show();
		$('.menat').show();
	})
	//	小学
	$('.receive-samsool').click(function() {
		$('.teampopbz').show();
		$('.samsool').show();
	})
	//	中学
	$('.receive-midsool').click(function() {
		$('.teampopbz').show();
		$('.midsool').show();
	})
	//	高中
	$('.receive-higsool').click(function() {
		$('.teampopbz').show();
		$('.higsool').show();
	})
	//	大学
	$('.receive-bigsool').click(function() {
		$('.teampopbz').show();
		$('.bigsool').show();
	})

	//	居住情况
	$('.receive-live').click(function() {
		$('.teampopbz').show();
		$('.melive').show();
	})
	//	婚姻
	$('.receive-marr').click(function() {
		$('.teampopbz').show();
		$('.memarr').show();
	})
	//	月薪
	$('.receive-monthly').click(function() {
		$('.teampopbz').show();
		$('.monthly').show();
	})
	//	购车情况
	$('.receive-buye').click(function() {
		$('.teampopbz').show();
		$('.buye').show();
	})
	//	经济状况
	$('.receive-econ').click(function() {
		$('.teampopbz').show();
		$('.econ').show();
	})
	//	职业
	$('.receive-occupa').click(function() {
		$('.teampopbz').show();
		$('.occupa').show();
	})
	//	所在院校
	$('.receive-colleges').click(function() {
		$('.teampopbz').show();
		$('.colleges').show();
	})
	//	最喜爱的体育活动
	$('.receive-physical').click(function() {
		$('.teampopbz').show();
		$('.physical').show();
	})
	//	最喜爱的食物
	$('.receive-food').click(function() {
		$('.teampopbz').show();
		$('.food').show();
	})
	//	最喜爱的电影
	$('.receive-cream').click(function() {
		$('.teampopbz').show();
		$('.cream').show();
	})
	//	最喜爱的节目
	$('.receive-gig').click(function() {
		$('.teampopbz').show();
		$('.gig').show();
	})
	//	休闲娱乐
	$('.receive-gLeisure').click(function() {
		$('.teampopbz').show();
		$('.gLeisure').show();
	})
	//	业余爱好
	$('.receive-hobby').click(function() {
		$('.teampopbz').show();
		$('.hobby').show();
	})
	//	选择接收人群下一步
	$('.receNext').click(function() {
		$('.viewReceive').hide();
		$('.viewPay').show();
	})

	//	支付的选择
	$('.paySpan img').click(function() {
		$(".icomeTransfer .paySpan img").attr('src', 'img/uncheck@2x.png');
		$(this).attr('src', 'img/check@2x.png');
	})

	//	悬浮按钮
	$('.floating-button').click(function() {
		$('.icon-plusa').toggleClass('icon-botton');
		$('.speed-button').toggle();
	})

	//	未读已读转换
	$('.informahomeUl li').click(function() {
		$(this).addClass('inmaSelect').siblings().removeClass('inmaSelect');
	})
	//	未读信息
	$('.unread').click(function() {
		$('.unreadinfor').show();
		$('.readinfor').hide();
	})
	//	已读信息
	$('.read').click(function() {
		$('.readinfor').show();
		$('.unreadinfor').hide();
	})

	//	信息详情
	//	回复获取焦点
	$('.detaiInput').focus(function() {
		$(this).css("border", "1px solid #E8615E");
		$('.detaiImg').attr("src", "img/send2@2x.png");
	})
	$('.detaiInput').blur(function() {
		$(this).css("border", "1px solid #848484");
		$('.detaiImg').attr("src", "img/send@2x.png");
	})

	//	点击领取奖励
	var countdown = 5;
	$('.receiveAward').click(function settime(val) {

		if(countdown == 0) {
			$('.overlayer').show();
			$('.teamgB').show();
			$('.receiveAward').attr("src", "img/btn-havetoreceive@2x.png");
			//		领取奖励弹框倒计时文字消失
			$('.detailCount').hide();
			return;
		} else {
			$('.receiveAward').attr("src", "img/btn-waittime@2x.png");
			$('.detailCount').show();
			countdown--;
		}
		setTimeout(function() {
			settime(val);
		}, 1000);
	});
//	打开APP提示关闭
	$('.proclose').click(function(){
		$('.appPrompted').hide();
	})
	
//	忘记密码
	$('.forgotPassbtn').click(function(){
		$('.forgotPassa').hide();
		$('.forgotPassb').show();
	})
	
//	注册填写资料下一步 上一步
//	var nexta = 0;
//	$('.close-inameshow').click(function(){
//		nexta--;
//		switch (nexta){
//			case 0:
//				$('.inameshow').show();
//				break;
//			case 1:
//				$('.sexshow').show();
//				break;
//			case 2:
//				$('.datebirth').show();
//				break;
//			case 3:
//				$('.qualifica').show();
//				alert(0);
//				break;
//			default:
//				break;
//		}
//	})
//	下一步(确认) 
//	$('.shure-sexshow').click(function(){
//		nexta++;
//		switch (nexta){
//			case 0:
//				$('.inameshow').show();
//				break;
//			case 1:
//				$('.sexshow').show();
//				$('.inameshow').hide();
//				break;
//			case 2:
//				$('.datebirth').show();
//				$('.sexshow').hide();
//				break;
//			case 3:
//				$('.qualifica').show();
//				$('.datebirth').hide();
//				break;
//			default:
//				break;
//		}
//	})
	
	
})