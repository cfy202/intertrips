var local = window.location;
var contextPath = local.pathname.split("/")[1];
var basePath = local.protocol + "//" + local.host+"/"+contextPath;

var HISTORY_RECORED_SIZE = 10;
var HISTORY_RECORED_EXPIRES = 3600 * 24 * 7;

var memberJson = getCookie("loginMember");
if(memberJson != '' && memberJson != null){
	var member = JSON.parse(memberJson);
}

var logout = function(){
	$.get(basePath + "/logout.do",function(result){
		if(result == 'success'){
			$("#menu-item-312").find("a").attr("href","javascript:void(0);").html("<i class=\"fa fa-sign-in\"></i> Sign in </a>");
			$("#logoutLi").hide();
		}
	});
}

jQuery(document).ready(function($) {

if(member != null && member.account != '' && member.account != null){
	   $("#menu-item-312").html("<a href=\""+basePath+"/member/profile/index.htm\" class=\"theme-login\" title=\"UserCenter\"><i class=\"fa fa-user\"></i>"+member.account+"</a>");
	   $(".navbar-login-icon").click(function(){
		   window.location.href = basePath + "/member/profile/index.htm";
	   });
	   $("#logoutLi").show();
}else{
}

	/**
 //币种切换
 $(".nationalflag").mouseenter(function(){
     $(this).find(".brand_op").show();
 });
 $(".nationalflag").mouseleave(function(){
     $(this).find(".brand_op").hide();
 });
	**/
	 //显示币种
	 var cc = getCookie("cc");
//	 var sshe ='${costCode!}';
//	 alert(sshe);
	 if(cc){
		 //显示cookie币种
		 if(cc=='CNY'){
			 $(".currency-selector").val("CNY");
		 }else if (cc=='CAD') {
			 $(".currency-selector").val("CAD");
		 }else if (cc=='AUD') {
			 $(".currency-selector").val("AUD");
		 }else if (cc=='USD') {
			 $(".currency-selector").val("USD");
		 }else if(cc=="EUR"){
			 $(".currency-selector").val("EUR");
		 }
	 }else {
		//显示销售中心默认币种
		 $(".currency-selector").val("USD");
	 }
	 
	 //头部服务热线
	 $("#phone_lists").hover(function(){
       $(this).find(".tel_list").slideDown("fast");
		  $(this).find(".arrows").removeClass("off").addClass("active");
  },function(){
	      $(this).find(".tel_list").slideUp("fast",function(){
			     $(this).stop(true,true);
		   });
		  $(this).find(".arrows").removeClass("active").addClass("off");	
	 });
	 
	 // 登录
		$('.theme-login').click(function(){
			$('.theme-popover-mask').fadeIn(300);
			$('.theme-popover-register').slideUp(300);//隐藏注册弹出窗
			$('.theme-popover-password').slideUp(300);
			$('.theme-popover').slideDown(400);//显示登陆弹出窗
			$('#loginCode').find(".loginCode").remove().end().append("<img src=\""+basePath+"/captcha.do?d='+new Date().getTime()\" title=\"\" onclick=\"this.src='"+basePath+"/captcha.do?d='+new Date().getTime()\" class=\"yzm-img loginCode\"><div class=\"clear loginCode\"></div>");
		});
		$('.theme-close').click(function(){
			$('.theme-popover-mask').fadeOut(300);
			$('.theme-popover').slideUp(400);
			$('.theme-popover-register').slideUp(300);
			$('.theme-popover-password').slideUp(300);
		});
		
		// 注册
		$('.theme-register').click(function(){
			$('.theme-popover-mask').fadeIn(300);
			$('.theme-popover').slideUp(300);
			$('.theme-popover-register').slideDown(400);
			$('#regestCode').find(".regestCode").remove().end().append("<img src=\""+basePath+"/captcha.do?d='+new Date().getTime()\" title=\"\" onclick=\"this.src='"+basePath+"/captcha.do?d='+new Date().getTime()\" class=\"yzm-img regestCode\"><div class=\"clear regestCode\"></div>");
		});
		
		// 密码找回
		$('.theme-password').click(function(){
			$('.theme-popover-mask').fadeIn(300);
			$('.theme-popover').slideUp(300);
			$('.theme-popover-register').slideUp(300);
			$('.theme-popover-password').slideDown(400);
		});
		
		//手机登陆
	    $(".navbar-login-icon,.theme-login-mobile").click(function(){
			$('.theme-popover-mask-mobile').fadeIn(300);
			$('.theme-popover-register-mobile').slideUp(300);//隐藏注册弹出窗
			$('.theme-popover-password-mobile').slideUp(300);
			$('.theme-popover-mobile').slideDown(400);//显示登陆弹出窗
			$('#loginCode-mobile').find(".loginCode").remove().end().append("<img src=\""+basePath+"/captcha.do?d='+new Date().getTime()\" title=\"\" onclick=\"this.src='"+basePath+"/captcha.do?d='+new Date().getTime()\" class=\"yzm-img loginCode\"><div class=\"clear loginCode\"></div>");			   
		});	
		$('.theme-close-mobile').click(function(){
			$('.theme-popover-mask-mobile').fadeOut(300);
			$('.theme-popover-mobile').slideUp(400);
			$('.theme-popover-register-mobile').slideUp(300);
			$('.theme-popover-password-mobile').slideUp(300);
		});
	    
		// 手机注册
		$('.theme-register-mobile').click(function(){
			$('.theme-popover-mask-mobile').fadeIn(300);
			$('.theme-popover-mobile').slideUp(300);
			$('.theme-popover-register-mobile').slideDown(400);
			$('#regestCode-mobile').find(".regestCode").remove().end().append("<img src=\""+basePath+"/captcha.do?d='+new Date().getTime()\" title=\"\" onclick=\"this.src='"+basePath+"/captcha.do?d='+new Date().getTime()\" class=\"yzm-img regestCode\"><div class=\"clear regestCode\"></div>");
		});
		
		// 手机密码找回
		$('.theme-password-mobile').click(function(){
			$('.theme-popover-mask-mobile').fadeIn(300);
			$('.theme-popover-mobile').slideUp(300);
			$('.theme-popover-register-mobile').slideUp(300);
			$('.theme-popover-password-mobile').slideDown(400);
		});
		
 $("#shoppingCartButton").click(function(){
 	window.location.href = basePath + "/shopping_cart.htm";		
 });
 
 $("#password").focus(function(){
 	$("#theme-login-error").html("");
 });
 
 $("#password_mobile").focus(function(){
	$("#theme-login-error-mobile").html("");
 });
 
 //登录提交
 $("form#loginForm").validate({
 	
     rules: {
         account: {
             required: true,
             email: true,
             remote: {
                 type: "post",
                 url: basePath + "/register/isExist.do",
                 data: {
                     account: function() {
                         return $("#account").val();
                     }
                 },
             }
         },
         
         password: {
             required: true,
         },

         captcha: {
             required: true,
             remote: {
                 type: "post",
                 url: basePath + "/register/isCaptcha.do",
                 data: {
                     captcha: function() {
                         return $("#captcha").val();
                     }
                 },
             }
         }
     },
     messages: {
         account: {
             required: "Enter your email address",
             email: "Invalid email format",
             remote: "Email unregistered",
         },

         password: {
             required: "Enter password",
         },

         captcha: {
             required: "Enter verification code",
             remote: "Wrong verification code"
         },
     },

     submitHandler: function(form) {
         $.ajax({
             url: basePath + "/common/public_key.do",
             type: "GET",
             dataType: "json",
             cache: false,
             beforeSend: function() {
                 $('#sub-login').attr("disabled", true);
                 $("#loading_hint").show();
                 $("#hint").text("Logging in...");
             },
             success: function(data) {
                 var rsaKey = new RSAKey();
                 rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
                 var enPassword = hex2b64(rsaKey.encrypt($("#password").val()));
                 $.ajax({
                     url: $("#loginForm").attr("action"),
                     type: "POST",
                     data: {
                         account: $("#account").val(),
                         enPassword: enPassword,
                     },
                     dataType: "json",
                     cache: false,
                     complete: function(message) {
                         $("#loading_hint").hide();
                         $('#sub-login').attr("disabled", false);
                         var result = eval("(" + message.responseText + ")");
                         if (result.success) {
                            // location.href = basePath + "/";
                            location.reload();
                         } else {
                             $("#theme-login-error").html(result.message);
                         }
                     }
                 });
             }
         });
     }
 });

 //注册表单验证
 $("form#registerForm").validate({
     rules: {
         account_register: {
             required: true,
             email: true,
             remote: {
                 type: "post",
                 url: basePath + "/register/isExist.do",
                 data: {
                     account: function() {
                         return $("#account_register").val();
                     }
                 },
                 dataType: "html",
                 dataFilter: function(data, type) {
                     if (data == "true") {
                         return false;
                     } else {
                         return true;
                     }
                 }
             }
         },
         password_register: {
             required: true,
             minlength: 6,
             maxlength: 20
         },
         confirm_password_register: {
             required: true,
             equalTo: "#password_register"
         },
         captcha_register: {
             required: true,
             remote: {
                 type: "post",
                 url: basePath + "/register/isCaptcha.do",
                 data: {
                     captcha: function() {
                         return $("#captcha_register").val();
                     }
                 },
             }
         }
     },
     messages: {
         account_register: {
             required: "Enter your email address",
             email: "Invalid email format",
             remote: "User name already exists"
         },
         password_register: {
             required: "Enter password",
             minlength: "Your Password's length should be between 6-20 characters",
             maxlength: "Your Password's length should be between 6-20 characters"
         },
         confirm_password_register: {
             required: "Enter confirm password",
             equalTo: " The two passwords you typed do not match"
         },
         captcha_register: {
             required: "Enter verification code",
             remote: "Wrong verification code"
         },
     },
     submitHandler: function(form) {
         $.ajax({
             url: basePath + "/common/public_key.do",
             type: "GET",
             dataType: "json",
             cache: false,
             beforeSend: function() {
                 $('#sub-register').attr("disabled", true);
                 $("#loading_hint").show();
                 $("#hint").text("Being registered...");
             },
             success: function(data) {
                 var rsaKey = new RSAKey();
                 rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
                 var enPassword = hex2b64(rsaKey.encrypt($("#password_register").val()));
                 $.ajax({
                     url: $("#registerForm").attr("action"),
                     type: "POST",
                     data: {
                         account: $("#account_register").val(),
                         enPassword: enPassword,
                     },
                     dataType: "json",
                     cache: false,
                     complete: function(message) {
                         $("#loading_hint").hide();
                         $('#sub-register').attr("disabled", false);
                         var result = eval("(" + message.responseText + ")");
                         if (result.success) {
                             $('.theme-popover-mask').fadeIn(300);
                             $('.theme-popover-register').slideUp(300); //隐藏注册弹出窗
                             $('#theme-popover-password').slideDown(400); //显示注册成功窗口
                             
                         } else {
                         	 $("#theme-register-error").html(result.message);
                         }
                     }
                 });
             }
         });
     }
 });

 //密码修改
 $("form#resetForm").validate({
     rules: {
         account_reset: {
             required: true,
             email: true,
             remote: {
                 type: "post",
                 url: basePath + "/register/isExist.do",
                 data: {
                     account: function() {
                         return $("#account_reset").val();
                     }
                 },
                 dataType: "html",
                 dataFilter: function(data, type) {
                     if (data == "true") {
                         return true;
                     } else {
                         return false;
                     }
                 }
             }
         }
     },

     messages: {
         account_reset: {
             required: "Enter your email address",
             email: "Invalid email format",
             remote: "Email unregistered"
         },
     },
     
     submitHandler: function(form) {
     	$.ajax({
             url: $("#resetForm").attr("action"),
             type: "POST",
             data: {
                 account: $("#account_reset").val(),
             },
             dataType: "json",
             cache: false,
             beforeSend: function() {
                 $('#sub-reset-password').attr("disabled", true);
                 $("#loading_hint").show();
                 $("#hint").text("正在发送...");
             },
             complete: function(message) {
             	 var result = eval("(" + message.responseText + ")");
                  if (result.success) {
                 	$("#loading_hint").hide();
                  	$("#loading_hint").show();
                  	$("#hint").text("发送成功...");
                  	setTimeout(function(){
                  		 $("#loading_hint").hide();
                 	    },2000);
					 } else {  
						$("#loading_hint").hide();
						$("#theme-password-error").html(result.message);
		             }   
                 $('#sub-reset-password').attr("disabled", false);
             }
         });
     }
 });

	 //手机登录提交
	 $("form#mobileLoginForm").validate({
	 	
	     rules: {
	         account: {
	             required: true,
	             email: true,
	             remote: {
	                 type: "post",
	                 url: basePath + "/register/isExist.do",
	                 data: {
	                     account: function() {
	                         return $("#account_mobile").val();
	                     }
	                 },
	             }
	         },
	         
	         password: {
	             required: true,
	         },

	         captcha: {
	             required: true,
	             remote: {
	                 type: "post",
	                 url: basePath + "/register/isCaptcha.do",
	                 data: {
	                     captcha: function() {
	                         return $("#captcha_mobile").val();
	                     }
	                 },
	             }
	         }
	     },
	     messages: {
	         account: {
	             required: "Enter your email address",
	             email: "Invalid email format",
	             remote: "Email unregistered",
	         },

	         password: {
	             required: "Enter password",
	         },

	         captcha: {
	             required: "Enter verification code",
	             remote: "Wrong verification code"
	         },
	     },

	     submitHandler: function(form) {
	         $.ajax({
	             url: basePath + "/common/public_key.do",
	             type: "GET",
	             dataType: "json",
	             cache: false,
	             beforeSend: function() {
	                 $('#sub-login-mobile').attr("disabled", true);
	                 $("#loading_hint").show();
	                 $("#hint").text("Logging in...");
	             },
	             success: function(data) {
	                 var rsaKey = new RSAKey();
	                 rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
	                 var enPassword = hex2b64(rsaKey.encrypt($("#password_mobile").val()));
	                 $.ajax({
	                     url: $("#mobileLoginForm").attr("action"),
	                     type: "POST",
	                     data: {
	                         account: $("#account_mobile").val(),
	                         enPassword: enPassword,
	                     },
	                     dataType: "json",
	                     cache: false,
	                     complete: function(message) {
	                         $("#loading_hint").hide();
	                         $('#sub-login-mobile').attr("disabled", false);
	                         var result = eval("(" + message.responseText + ")");
	                         if (result.success) {
	                            // location.href = basePath + "/";
	                            location.reload();
	                         } else {
	                             $("#theme-login-error-mobile").html(result.message);
	                         }
	                     }
	                 });
	             }
	         });
	     }
	 });

	 //手机注册表单验证
	 $("form#mobileRegisterForm").validate({
	     rules: {
	         account_register: {
	             required: true,
	             email: true,
	             remote: {
	                 type: "post",
	                 url: basePath + "/register/isExist.do",
	                 data: {
	                     account: function() {
	                         return $("#account_register_mobile").val();
	                     }
	                 },
	                 dataType: "html",
	                 dataFilter: function(data, type) {
	                     if (data == "true") {
	                         return false;
	                     } else {
	                         return true;
	                     }
	                 }
	             }
	         },
	         password_register: {
	             required: true,
	             minlength: 6,
	             maxlength: 20
	         },
	         confirm_password_register: {
	             required: true,
	             equalTo: "#password_register_mobile"
	         },
	         captcha_register: {
	             required: true,
	             remote: {
	                 type: "post",
	                 url: basePath + "/register/isCaptcha.do",
	                 data: {
	                     captcha: function() {
	                         return $("#captcha_register_mobile").val();
	                     }
	                 },
	             }
	         }
	     },
	     messages: {
	         account_register: {
	             required: "Enter your email address",
	             email: "Invalid email format",
	             remote: "User name already exists"
	         },
	         password_register: {
	             required: "Enter password",
	             minlength: "Your Password's length should be between 6-20 characters",
	             maxlength: "Your Password's length should be between 6-20 characters"
	         },
	         confirm_password_register: {
	             required: "Enter confirm password",
	             equalTo: " The two passwords you typed do not match"
	         },
	         captcha_register: {
	             required: "Enter verification code",
	             remote: "Wrong verification code"
	         },
	     },
	     submitHandler: function(form) {
	         $.ajax({
	             url: basePath + "/common/public_key.do",
	             type: "GET",
	             dataType: "json",
	             cache: false,
	             beforeSend: function() {
	                 $('#sub-register').attr("disabled", true);
	                 $("#loading_hint").show();
	                 $("#hint").text("Being registered...");
	             },
	             success: function(data) {
	                 var rsaKey = new RSAKey();
	                 rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
	                 var enPassword = hex2b64(rsaKey.encrypt($("#password_register_mobile").val()));
	                 $.ajax({
	                     url: $("#mobileRegisterForm").attr("action"),
	                     type: "POST",
	                     data: {
	                         account: $("#account_register_mobile").val(),
	                         enPassword: enPassword,
	                     },
	                     dataType: "json",
	                     cache: false,
	                     complete: function(message) {
	                         $("#loading_hint").hide();
	                         $('#sub-register').attr("disabled", false);
	                         var result = eval("(" + message.responseText + ")");
	                         if (result.success) {
	                             $('.theme-popover-mask').fadeIn(300);
	                             $('.theme-popover-register').slideUp(300); //隐藏注册弹出窗
	                             $('#theme-popover-password-mobile').slideDown(400); //显示注册成功窗口
	                             
	                         } else {
	                         	 $("#theme-register-error-mobile").html(result.message);
	                         }
	                     }
	                 });
	             }
	         });
	     }
	 });

	 //手机密码修改
	 $("form#resetForm").validate({
	     rules: {
	         account_reset: {
	             required: true,
	             email: true,
	             remote: {
	                 type: "post",
	                 url: basePath + "/register/isExist.do",
	                 data: {
	                     account: function() {
	                         return $("#account_reset").val();
	                     }
	                 },
	                 dataType: "html",
	                 dataFilter: function(data, type) {
	                     if (data == "true") {
	                         return true;
	                     } else {
	                         return false;
	                     }
	                 }
	             }
	         }
	     },

	     messages: {
	         account_reset: {
	             required: "Enter your email address",
	             email: "Invalid email format",
	             remote: "Email unregistered"
	         },
	     },
	     
	     submitHandler: function(form) {
	     	$.ajax({
	             url: $("#resetForm").attr("action"),
	             type: "POST",
	             data: {
	                 account: $("#account_reset").val(),
	             },
	             dataType: "json",
	             cache: false,
	             beforeSend: function() {
	                 $('#sub-reset-password').attr("disabled", true);
	                 $("#loading_hint").show();
	                 $("#hint").text("正在发送...");
	             },
	             complete: function(message) {
	             	 var result = eval("(" + message.responseText + ")");
	                  if (result.success) {
	                 	$("#loading_hint").hide();
	                  	$("#loading_hint").show();
	                  	$("#hint").text("发送成功...");
	                  	setTimeout(function(){
	                  		 $("#loading_hint").hide();
	                 	    },2000);
						 } else {  
							$("#loading_hint").hide();
							$("#theme-password-error").html(result.message);
			             }   
	                 $('#sub-reset-password').attr("disabled", false);
	             }
	         });
	     }
	 }); 
 
 
 $(document).ajaxComplete(function(event, request, settings) {
		var loginStatus = request.getResponseHeader("loginStatus");
		if (loginStatus == "accessDenied") {
			$.redirectLogin(location.href, "Please login");
		}
	});
 
 // 跳转登录
	$.redirectLogin = function (redirectUrl, message) {
		var href = basePath + "/login.htm";
		if (redirectUrl != null) {
			href += "?redirectUrl=" + encodeURIComponent(redirectUrl);
		}
		if (message != null) {
			alert(message);
			setTimeout(function() {
				location.href = href;
			}, 1000);
		} else {
			location.href = href;
		}
	};
});

// 登录页再次激活
function againActivate () {
	var account = $("#account").val();
	window.location.href= basePath + "/register/user_activate.htm?account=" + account;
	/**$.ajax({
     url: basePath + "/register/again_activate.do",
     type: "post",
     data: {account: $("#account").val()},
     dataType: "json",
     cache: false,
     beforeSend: function() {
         $('#sub-login').attr("disabled", true);
         $("#loading_hint").show();
         $("#hint").text("请稍等...");
     },
     success: function(data) {
     	$("#loading_hint").hide();
         $('#sub-login').attr("disabled", false);
         if (data.success) {
             $('.theme-popover-mask').fadeIn(300);
             $('.theme-popover').slideUp(300); //隐藏登录弹出窗
             $('.theme-popover-success').slideDown(400); //显示注册成功窗口
             $("#email-success-account").text(data.message);
         } else {
         	alert(data.message);
         }
     }
 });*/
}

//注册成功页再次激活
function activate () {
	var account = $("#email-success-account").text();
	window.location.href= basePath + "/register/user_activate.htm?account=" + account;
	/**
	$.ajax({
     url: basePath + "/register/again_activate.do",
     type: "post",
     data: {account: $("#email-success-account").text()},
     dataType: "json",
     cache: false,
     beforeSend: function() {
         $("#loading_hint").show();
         $("#hint").text("请稍等...");
     },
     success: function(data) {
     	$("#loading_hint").hide();
         if (data.success) {
         	alert("激活邮件发送成功。");
             $("#email-success-account").text(data.message);
         } else {
             alert(data.message);
         }
     }
 });
  */
}

//cookie添加币种code
function setCurrency(currency) {
	   addCookie("cc", currency, {path:"/", expires:3600});
	   var uusd = currency.substring(0, 2).toLowerCase();
	   var url = window.location.href;
		   if(url.indexOf("cn/")){
			   url = url.replace("cn/",uusd+"/");
		   }else if(url.indexOf("us/")){
			   url = url.replace("us/",uusd+"/");
		   }else if(url.indexOf("ca/")){
			   url = url.replace("ca/",uusd+"/");
		   }else if(url.indexOf("au/")){
			   url = url.replace("au/",uusd+"/");
		   }else if(url.indexOf("eu/")){
			   url = url.replace("eu/",uusd+"/");
		   }
	  
	   window.location=basePath+"/"+uusd;
	}


//添加Cookie
function addCookie(name, value, options) {
 if (arguments.length > 1 && name != null) {
     if (options == null) {
         options = {};
     }
     if (value == null) {
         options.expires = -1;
     }
     if (typeof options.expires == "number") {
         var time = options.expires;
         var expires = options.expires = new Date();
         expires.setTime(expires.getTime() + time * 1000);
     }
     document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path: "") + (options.domain ? "; domain=" + options.domain: ""),
     (options.secure ? "; secure": "");
 }
}

//获取Cookie
function getCookie(name) {
 if (name != null) {
     var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
     return value ? decodeURIComponent(value[1]) : null;
 }
}

//移除Cookie
function removeCookie(name, options) {
 addCookie(name, null, options);
}

//验证码
function checkCapacha(captcha) {
	var flag = false;
	$.ajax({
     url:  basePath + "/register/isCaptcha.do",
     type: "post",
     data: {captcha: captcha},
     dataType: "json",
     async: false,
     success: function(data) {
     	if(data){
     		flag = true;
     	}
     }
	});
	return flag;
}

//记录浏览历史
var recordVisitedHistory = function(tourname,mprice,firstImageUrl){
	var code = getCookie("cc");
	var historyRecordCookieName = 'visitedHistory_' + code;
	var currentPageUrl = window.location.href;
	
	var visitedHistoryJson = getCookie(historyRecordCookieName);
	var historyRecordArray;
	if(visitedHistoryJson != null && visitedHistoryJson != ''){
		historyRecordArray = JSON.parse(visitedHistoryJson);
	}else{
		historyRecordArray = new Array();
	}
	for(var i=0; i<historyRecordArray.length; i++){
		if(historyRecordArray[i].url == currentPageUrl){
			historyRecordArray.splice(i,1);
		}
	}
	historyRecordArray.unshift(new HistoryRecord(tourname,code,mprice,firstImageUrl,currentPageUrl));
	if(historyRecordArray.length > HISTORY_RECORED_SIZE){
		historyRecordArray =  historyRecordArray.slice(0, HISTORY_RECORED_SIZE);
	}
	visitedHistoryJson = JSON.stringify(historyRecordArray);
	addCookie(historyRecordCookieName, visitedHistoryJson, {path:"/", expires:HISTORY_RECORED_EXPIRES}); 
};

//删除历史记录
var removeVisitedHistory = function(i){
	var code = getCookie("cc");
	var historyRecordCookieName = 'visitedHistory_' + code;
	var visitedHistoryJson = getCookie(historyRecordCookieName);
	var historyRecordArray = JSON.parse(visitedHistoryJson);
	historyRecordArray.splice(i,1);
	visitedHistoryJson = JSON.stringify(historyRecordArray);
	addCookie(historyRecordCookieName, visitedHistoryJson, {path:"/", expires:HISTORY_RECORED_EXPIRES}); 
};

//获得历史记录
var getVisitedHistory = function(){
	var code = getCookie("cc");
	var historyRecordCookieName = 'visitedHistory_' + code;
	var visitedHistoryJson = getCookie(historyRecordCookieName);
	var historyRecordArray;
	if(visitedHistoryJson != null && visitedHistoryJson != ''){
		historyRecordArray = JSON.parse(visitedHistoryJson);
	}else{
		historyRecordArray = new Array();
	}
	return historyRecordArray;
};

//历史记录对象
function HistoryRecord(tourname,code,mprice,firstImageUrl,url){
	//线路名称
	this.tourname = tourname;
	//销售中心的币种
	this.code = code;
	//最低价格
	this.mprice = mprice;
	//首张图片的url
	this.firstImageUrl = firstImageUrl;
	//线路详情页的url
	this.url = url;
};