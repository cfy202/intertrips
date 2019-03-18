Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};

Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

var beginDaysJsonCookieName = "lastMonthFirstDayStringCookieName";

//电话号码的正则表达式
var numberRule = /^\d{8,12}$/;

//数字的正则表达式
var numberRul = /^[0-9]+$/;

//邮箱校验的正则表达式
var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
//初始化的开关
var init = 0;
//加载出发日期的数目
var dateNumber = 21;
//出发地Id
var chooseDepartureId;
//是否移动端
var isMobile = false;
//出发地信息	
var chooseDepartureInfo = '';
//出发日期选项框
var $departureSelect;
//锁定tab
var tabIsLocked = false;
//评论已被加载
var viewLoaded = false;
//出发地已被加载
var departureLoaded = false;
//图片已被加载
var imagesLoaded = false;

$(function() {
	//手机版的tab切换
	$(".panel-box").toggle(function(){
		$(this).addClass("collapsed").removeClass("panel-heading");	
		$(this).next().stop(true,true).slideDown(300);
	},function(){
		$(this).addClass("panel-heading").removeClass("collapsed");
		$(this).next().stop(true,true).slideUp(300);
	});
	
	//给详细信息预览模块增加下拉动作
	$(".pay_messages_steps").click(function(){
		$(this).next(".pay_messages_list_1").slideToggle("2000");
	});
	
	//
	$(".itineraryTab").click(function(){
		$(this).next(".itineraryBody").slideToggle("2000");
	});
	
	var $optionalTourItineraryList = $("#mobileOptionalTourTab").find(".itineraryTabMobile");
	$optionalTourItineraryList.click(function(){
		currentIndex = $optionalTourItineraryList.index($(this));
		$body = $(this).next(".itineraryBodyMobile");
		
		if($body.is(":hidden")){
			$optionalTourItineraryList.each(function(index){
				if(index != currentIndex){
					$(this).next(".itineraryBodyMobile").slideUp("2000");
				}
			});
			$body.slideDown("2000",function(){
				adjustMobileSroll($(this).closest(".timeline"));
			});
		}else{
			$body.slideUp("2000");
		}
	});
	
	var $customItineraryList = $("#mobileCustomTab").find(".itineraryTabMobile");
	$customItineraryList.click(function(){
		currentIndex = $customItineraryList.index($(this));
		$body = $(this).next(".itineraryBodyMobile");
		if($body.is(":hidden")){
			$customItineraryList.each(function(index){
				if(index != currentIndex){
					$(this).next(".itineraryBodyMobile").slideUp("2000");
				}
			});
			$body.slideDown("2000",function(){
				adjustMobileSroll($(this).closest(".timeline"));
			});
		}else{
			$body.slideUp("2000");
		}
	});	
	
	var $includeTable = $("#includeTable");
	var $notIncludeTable = $("#notIncludeTable");
	var $mobileIncludeTable = $("#mobileIncludeTable");
	var $mobileNotIncludeTable = $("#mobileNotIncludeTable");
	
	if($includeTable.find("tr").size() == 0){
		$includeTable.parent().parent().hide();	
	}
	if($notIncludeTable.find("tr").size() == 0){
		$notIncludeTable.parent().parent().hide();
	}
	if($mobileIncludeTable.find("tr").size() == 0){
		$mobileIncludeTable.parent().parent().hide();
	}
	if($mobileNotIncludeTable.find("tr").size() == 0){
		$mobileNotIncludeTable.parent().parent().hide();
	}
	
	function starOver(m, n) {
		/* 把第n个star之前的包括第n个的class设置为空 */
		for (var i = 1; i <= n; i++) {
			$("#star" + m + '-' + i).removeClass("no").find("i").removeClass(
					"fa-star-o").addClass("fa-star");
		}
		/* 把第n个star之后的star的class设置为no */
		for (var i = n + 1; i <= 5; i++) {
			$("#star" + m + '-' + i).addClass("no").find("i").removeClass(
					"fa-star").addClass("fa-star-o");
		}
		if (n == 1) {
			$("#star-tip" + m).html("Very Poor");
		}
		if (n == 2) {
			$("#star-tip" + m).html("Not that bad");
		}
		if (n == 3) {
			$("#star-tip" + m).html("Average");
		}
		if (n == 4) {
			$("#star-tip" + m).html("Good");
		}
		if (n == 5) {
			$("#star-tip" + m).html("Perfect");
		}
		$("#starsResult" + m).val(n);
	}
	;

	function starInit(n) {
		$('[id^=star' + n + ']').each(function(i) {
			var me = $(this);
			me.click(function() {
				starOver(n, i + 1);
			});
		});
	}
	starInit(1);
	starInit(2);
	
	$(window).scroll(function(){
		if(!viewLoaded){
			var Windowheight = $(window).scrollTop() + $(window).height();
			var commentSroll = $("#commentDiv").offset().top;
			if(commentSroll < Windowheight){
				goPage(1);
				viewLoaded = true;
			}
		}
	});
	
	// 评论提交
	$("#submit").click(function() {
		var $commentForm = $("#commentform");
		var $title = $("#title");
		var $reviewComment = $("#reviewComment");
		var $email = $("#reviewEmail");
		if (getMember() == null) {
			alertWarn('Please login in.');
			return;
		}
		if ($title.val().trim() == '') {
			alertWarn('Please fill in your title');
			$title.focus();
			return;
		}
		if ($email.val().trim() == '') {
			alertWarn('Please login in your email');
			$email.focus();
			return;
		}
		if ($reviewComment.val().trim() == '') {
			alertWarn('Please fill in your review.');
			$reviewComment.focus();
			return;
		}
		jQuery.ajax({
			url : $commentForm.attr("action"),
			data : $commentForm.serialize(),
			type : "POST",
			beforeSend : function() {
			},
			success : function(result) {
				if (result > 0) {
					$title.val("");
					$reviewComment.val("");
					$("#star1-5").click();
					$("#star2-5").click();
					alertWarn('under review...');
				}
			}
		});
	});
	
	$(".departureDateDiv").click(function(){
		if(!departureLoaded){
			//如果线路有出发地
			if($departureSelect.size() > 0){
				//出发地变动时重新加载日期
				addDepartureChange($departureSelect);
				$departureSelect.change();
			}else{
				var $dateDiv;
				if(!isMobile){
					$dateDiv = $("#windowDatesDiv");
				}else{
					$dateDiv = $("#mobileDatesDiv");
				}
				chooseDepartureId = "";
				chooseDepartureInfo = "";
				fillInitDates($dateDiv,chooseDepartureId,dateNumber);
			}	
			departureLoaded = true;
		}
	});
	
	$(".galleryDiv").click(function(){
		if(!imagesLoaded){
			$(".galleryImage").append($(galleryImageHtml));
			imagesLoaded = true;
		}
	});
});

//给出发地变动增加加载日期
var addDepartureChange = function($departureSelect){
	$departureSelect.change(function(){
		var $dateDiv = $(this).closest(".people-box").next().find(".dateMain");
		chooseDepartureId = $(this).val();
		chooseDepartureInfo = $(this).find("option:selected").html();
		if(init != 0){
			tabIsLocked = true;
		}
		fillInitDates($dateDiv,chooseDepartureId,dateNumber);
	});
}

//初始化填充日历
var fillInitDates = function($dateDiv,chooseDepartureId,dateNumber){
   $.ajax({
		type: "post",
		async:false ,
        url: basePath + "/front/tourlineDetails/getDepartureDatesByDeparture.do",
        data:{tourlineId:tourlineId,departureId:chooseDepartureId,dateNumber:dateNumber},
        dataType:"html",
        success: function(data){
       		$dateDiv.empty();
	    	$dateDiv.append($(data));
			$dateDiv.find(".after_today_date").each(function(){
				$(this).mouseenter(function(){
					$(this).addClass("after_today_date_on");
				}).mouseleave(function(){
					$(this).removeClass("after_today_date_on");
				});
			});
			$dateDiv.find("img.hotImg").attr("src",basePath + "/assets-web/images/hot.gif");	
			
			//如果不是首次加载日历,则清空显示价格和出发日期
			if(init == 1){
				$("#priceShow").html('');
				$("#dateInput").val('');
			}else{
				init = 1;
			}
			
			clearBeginDates();
			//隐藏上一页
			$dateDiv.next().find(".lastMonth").hide();
			//隐藏下一页		
			if($dateDiv.find("table").attr("ishasnext") == 0){
				$dateDiv.next().find(".nextMonth").hide();
				$(".choose-page").hide();
			}else{
				$dateDiv.next().find(".nextMonth").show();
				$(".choose-page").show();
			}  
        },
        error:function(e){
        },
	});
}

//上一页
var lastMonth = function(button){
	var $dateDiv = $(button).closest(".poeple-choose-btn").prev();
	var beginDateString = getBeginDate();				
	$.ajax({
		type: "post",
        url: basePath + "/front/tourlineDetails/beforeDates.do",
        data:{tourlineId:tourlineId,departureId:chooseDepartureId,dateNumber:dateNumber,firstDateString:beginDateString},
        dataType:"html",
        success: function(data){
        	$dateDiv.empty();
        	$dateDiv.append($(data));
   			$dateDiv.find(".after_today_date").each(function(){
				$(this).mouseenter(function(){
					$(this).addClass("after_today_date_on");
				}).mouseleave(function(){
					$(this).removeClass("after_today_date_on");
				});
			});
			$dateDiv.find("img.hotImg").attr("src", basePath + "/assets-web/images/hot.gif");	
			showOrHideBeforeAndNext($dateDiv);          	
        },
        error:function(e){
        },
	});
}

//下一页
var nextMonth = function(button){
	var $dateDiv = $(button).closest(".poeple-choose-btn").prev();
	var $dateTable = $dateDiv.find("table");
	var beginDateString = $dateTable.attr("beginDate");
	var lastDateString = $dateTable.attr("lastDate");
	storeBeginDate(beginDateString);
	$.ajax({
		type: "post",
        url: basePath + "/front/tourlineDetails/nextDates.do",
        data:{tourlineId:tourlineId,departureId:chooseDepartureId,dateNumber:dateNumber,lastDateString:lastDateString},
        dataType:"html",
        success: function(data){
        	$dateDiv.empty();
        	$dateDiv.append($(data));
   			$dateDiv.find(".after_today_date").each(function(){
				$(this).mouseenter(function(){
					$(this).addClass("after_today_date_on");
				}).mouseleave(function(){
					$(this).removeClass("after_today_date_on");
				});
			});
			$dateDiv.find("img.hotImg").attr("src", basePath + "/assets-web/images/hot.gif");
			showOrHideBeforeAndNext($dateDiv);	        	
        },
        error:function(e){
        },
	});
}

//判断是否含有上一页出发日期
var ifHasBeforeDates = function(){
	var beginDatesJson = getCookie(beginDaysJsonCookieName);
	var beginDatesArray = JSON.parse(beginDatesJson);
	if(beginDatesArray.length > 0){
		return true;
	}else{
		return false;
	}
}

//清除出发日期所有的起始日期
var clearBeginDates = function(){
	var beginDatesArray = new Array();
	var beginDatesJson = JSON.stringify(beginDatesArray);
	addCookie(beginDaysJsonCookieName,beginDatesJson,{path:"/"});
}

//获取当前出发日期上一页的起始日期
var getBeginDate = function(){
	var beginDatesJson = getCookie(beginDaysJsonCookieName);
	var beginDatesArray;
	var beginDate;
	if(beginDatesJson != null && beginDatesJson != ''){
		beginDatesArray = JSON.parse(beginDatesJson);
		beginDate = beginDatesArray.pop();
		beginDatesJson = JSON.stringify(beginDatesArray);
		addCookie(beginDaysJsonCookieName,beginDatesJson,{path:"/"});
		return beginDate;
	}else{
		window.location.href = window.location.href;
		return;
	}
}

//记录当前出发日期页的起始日期
var storeBeginDate = function(beginDateString){
	var beginDatesJson = getCookie(beginDaysJsonCookieName);
	var beginDatesArray;
	if(beginDatesJson != null && beginDatesJson != ''){
		beginDatesArray = JSON.parse(beginDatesJson);
	}else{
		beginDatesArray = new Array();
	}
	beginDatesArray.push(beginDateString);
	beginDatesJson = JSON.stringify(beginDatesArray);
	addCookie(beginDaysJsonCookieName,beginDatesJson,{path:"/"});
}

//显示或隐藏上一页或下一页按钮
var showOrHideBeforeAndNext = function($dateDiv){
	if(ifHasBeforeDates()){
		$dateDiv.next().find(".lastMonth").show();
	}else{
		$dateDiv.next().find(".lastMonth").hide();
	}	
	if($dateDiv.find("table").attr("ishasnext") == 0){
		$dateDiv.next().find(".nextMonth").hide();
	}else{
		$dateDiv.next().find(".nextMonth").show()
	}
}

//人数变动范围
var adjustTotalNumber = function(input,limit){
	var totalNumber = $(input).val();
	if(totalNumber < limit){
		$(input).val(limit);
	}
}

// 评论分页查询
function goPage(pageNow) {
	$.ajax({
		type : "POST",
		url : basePath + "/front/tourlineDetails/getReview.do",
		dataType : "json",
		data : {
			productId : productId,
			costnumber : costnumber,
			pageNow : pageNow
		},
		success : function(data) {
			$("#commentDiv").html(data[0] + data[1]);
		},
		error : function(e) {
		},
	});
}

// 给输入框添加必填的提示以及获取焦点后去除提示
var addWarnningShow = function($input) {
	$input.addClass("pay_mess_input_error");
	$input.focus(function() {
		$(this).removeClass("pay_mess_input_error");
	});
}

/**
 * 格式化数字显示方式 用法 formatNumber(12345.999,'#,##0.00');
 * formatNumber(12345.999,'#,##0.##'); formatNumber(123,'000000');
 * 
 * @param num
 * @param pattern
 */
var formatNumber = function(num, pattern) {

	var strarr = num ? num.toString().split('.') : [ '0' ];
	var fmtarr = pattern ? pattern.split('.') : [ '' ];
	var retstr = '';

	// 整数部分
	var str = strarr[0];
	var fmt = fmtarr[0];
	var i = str.length - 1;
	var comma = false;

	for (var f = fmt.length - 1; f >= 0; f--) {
		switch (fmt.substr(f, 1)) {
		case '#':
			if (i >= 0) {
				retstr = str.substr(i--, 1) + retstr;
			}
			break;
		case '0':
			if (i >= 0) {
				retstr = str.substr(i--, 1) + retstr;
			} else {
				retstr = '0' + retstr;
			}
			break;
		case ',':
			comma = true;
			retstr = ',' + retstr;
			break;
		}
	}
	if (i >= 0) {
		if (comma) {
			var l = str.length;
			for (; i >= 0; i--) {
				retstr = str.substr(i, 1) + retstr;
				if (i > 0 && ((l - i) % 3) == 0) {
					retstr = ',' + retstr;
				}
			}
		} else {
			retstr = str.substr(0, i + 1) + retstr;
		}
	}
	retstr = retstr + '.';

	// 处理小数部分
	str = strarr.length > 1 ? strarr[1] : '';
	fmt = fmtarr.length > 1 ? fmtarr[1] : '';
	i = 0;
	for (var f = 0; f < fmt.length; f++) {
		switch (fmt.substr(f, 1)) {
		case '#':
			if (i < str.length) {
				retstr += str.substr(i++, 1);
			}
			break;
		case '0':
			if (i < str.length) {
				retstr += str.substr(i++, 1);
			} else {
				retstr += '0';
			}
			break;
		}
	}
	return retstr.replace(/^,+/, '').replace(/\.$/, '');
};

/**
 * 将价格转换成#,##0.##的格式
 */
var formatPrice = function(price) {
	return formatNumber(price, '#,##0.##');
};

/**
 * 将有格式的价格转成无格式的价格
 */
var cancelFormat = function(priceString) {
	var priceArray = priceString.split(",");
	if (priceArray.length == 2) {
		priceString = priceArray[0] + priceArray[1];
	}
	return priceString;
}

var adjustMobileSroll = function($show){
	scrollTop = $show.offset().top - 74;
	$("html,body").animate({scrollTop:scrollTop}, 200);
}

//附加产品的数量和价格
function AdditionalProductFee(productId,productName,unitcost,quantity,totalFee){
	//产品Id
	this.productId = productId;
	//产品名称
	this.productName = productName;
	//产品单价
	this.unitcost = unitcost;
	//产品数量
	this.quantity = quantity;
	//产品总价
	this.totalFee = totalFee;
};