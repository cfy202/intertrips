<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<title>payment</title>
<link rel="shortcut icon" href="${ctx!}/favicon.ico">
<link rel="apple-touch-icon-precomposed" href="${ctx!}/apple-touch-icon.png">
<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv-printshiv.min.js?ver=4.3.1'></script> <![endif]-->
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js?ver=4.3.1'></script> 
<![endif]-->
<meta name="generator">
<style id="fit-vids-style">
.fluid-width-video-wrapper { width: 100%; position: relative; padding: 0; }
.fluid-width-video-wrapper iframe, .fluid-width-video-wrapper object, .fluid-width-video-wrapper embed { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
</style>
<style id="ShrinkMenu">
.menu-shrink { top : -49px !important; }
</style>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-38017454-1', 'auto');
  ga('send', 'pageview');

</script>
</head>
<body class="blog"  style="background-color:#f5f6f6;">
<#--top-->
<#include "/front/include/top.ftl"/>

<#--top-->
<section class="featured-destinations" style="margin-top:5.2rem;">
  <div class="container">
    <div class="cards overlap " style="margin-top:0;">
      <div class="widthCenter hidden-xs">
        <h3 class="Payment_number"><span style="margin-right:100px;">Booking No.：${booking.bookingNo}</span></h3>
        <div class="Payment_Method shadow">
            <table class="Payment_Method_tab" cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <th width="12%" style="border-right: 1px solid #ddd;">Date</th>
                    <th width="30%" style="border-right: 1px solid #ddd;">Product description</th>
                    <th width="12%" style="border-right: 1px solid #ddd;">Agent</th>
                    <th width="30%" style="border-right: 1px solid #ddd;">Price</th>
                </tr>
                <tr class="Payment_Method_tr">
                    <td style="text-align:left;border-right: 1px solid #ddd;">${(booking.departureDate)!}</td>
                    <td style="text-align:left;border-right: 1px solid #ddd;"><a href="https://www.intertrips.com${booking.product.pagePageid.filepath}" class="a4">${booking.product.name}</a></td>
					<td style="text-align:left;border-right: 1px solid #ddd;">${(booking.agentCode.agentName)!}</td>                    
                    <td style="text-align:left;">
                    	<p class="Payment_td_p">Fare:&nbsp;&nbsp;$${booking.total}</p>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" style="text-align:right;">
                    	<h3 style="font-size:1rem; margin:10px 0;">Total price:<b style="color:red; margin-left:20px;">$${booking.total}</b></h3>
                    </td>
                </tr>
            </table>
            <div class="clear"></div>
            <div class="Payment_btn">
            	<a style="cursor:pointer;" href="javascript:toPayment('card');" style="margin-right:10px;"><img src="${ctx!}/assets-web/images/creditcardbutton.jpg"></a>
            	<#--
                <a style="cursor:pointer;" href="javascript:toPayment('Paypal');"><img src="${ctx!}/assets-web/images/paypalbutton.jpg"></a>
                -->
            </div>
        </div>        
        <#--信用卡信息填写表单-->
        <div id="cardPayAero" class="Payment_Method_content shadow" style="display:none;">
		  <div id="payment_credit" class="payments">
		    <div class="f16 paypt30 fb"> <span class="mr10">You have chosen to pay by credit card</span> <img alt="Visa" src="${ctx}/assets-web/images/vmdpay2.png" class="payment_img"> </div>
		    <form name="kcredit" method="post" action="${ctx}/book_pay.htm">
		      <input type="hidden" name="amount" class="CPriceInput"/>
		      <input type="hidden" name="currencyCode" value="USD"/>
			  <input type="hidden" name="orderNo" value="${booking.bookingNo}"/>
			  <input type="hidden" name="productName" value="${booking.product.name}"/>
			  <input type="hidden" name="ordersId" value="${booking.id}"/>
		      <div class="basicInformation" style="">
		        <div class="fl d500">
		          <table class="paytbl1">
		            <tr>
		              <td width="40%"><span class="f14-1">Card Type</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><select class="cardTypeInput" name="cardtype">
		                  <option value="0">Please Select</option>
		                  <option value="Visa">Visa</option>
		                  <option value="MasterCard">MasterCard</option>
		                  <option value="Discover">Discover</option>
		                  <option value="American Express">American Express</option>
		                </select></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Card Number</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" class="cardNumberInput" name="cardnum" value="${(booking.creditcardno)!}"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Card Expiration Date</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><select class="expireMonthSelect" name="expireMonth" class="fl">
		                  <option value="01">01</option>
		                  <option value="02" selected="selected">02</option>
		                  <option value="03">03</option>
		                  <option value="04">04</option>
		                  <option value="05">05</option>
		                  <option value="06">06</option>
		                  <option value="07">07</option>
		                  <option value="08">08</option>
		                  <option value="09">09</option>
		                  <option value="10">10</option>
		                  <option value="11">11</option>
		                  <option value="12">12</option>
		                </select>
		                <select class="expireYearSelect" name="expireYear" class="fr">
		                  <option value="2016" selected="selected">2016</option>
		                  <option value="2017">2017</option>
		                  <option value="2018">2018</option>
		                  <option value="2019">2019</option>
		                  <option value="2020">2020</option>
		                  <option value="2021">2021</option>
		                  <option value="2022">2022</option>
		                  <option value="2023">2023</option>
		                  <option value="2024">2024</option>
		                  <option value="2025">2025</option>
		                  <option value="2026">2026</option>
		                </select>
		                <div class="clear"></div></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Card Security Code</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="authenticationnum" class="input-small cardSecurityCode" value="${(booking.securitycode)!}" maxlength="4"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Country</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%">
		              	<select class="countrySelect" name="country">
		              	  <option value="US">United States</option>
		                </td>
		            </tr>
		          </table>
		        </div>
		        <div class="fr credit_img">
		          <div class="mt30"><img alt="credit" src="${ctx}/assets-web/images/xinyongka.png"></div>
		          <div class="mt20 f12"><img class="mr5" src="${ctx}/assets-web/images/icon_a2.png"> Accept Visa / Master / Discover / American Express payment </div>
		          <div class="f12"><img class="mr5" src="${ctx}/assets-web/images/icon_a2.png"> Make sure that sufficient balance or credit card payment limit, and online payment function </div>
		        </div>
		        <div class="clear"></div>
		      </div>
		      <div class="lupay3" style="display:block;">
		        <div class="f16 paypt30 fb"><span class="mr10">Billing Address</span></div>
		        <div class="d500">
		          <table class="paytbl1">
		            <tr>
		              <td width="40%"><span class="f14-1">First Name</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="firstName" class="input-small firstNameInput"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Last Name</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="lastName" class="input-small lastNameInput"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Street Address</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="streetAddress" class="input-small streetAddressInput"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">City</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="city" class="input-small cityInput"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">(US/CA) State</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><select name="state" class="stateSelect">
		                  <option></option>
		                  <option value="AB">AB</option>
		                  <option value="AK">AK</option>
		                  <option value="AL">AL</option>
		                  <option value="AR">AR</option>
		                  <option value="AZ">AZ</option>
		                  <option value="BC">BC</option>
		                  <option value="CA">CA</option>
		                  <option value="CO">CO</option>
		                  <option value="CT">CT</option>
		                  <option value="DC">DC</option>
		                  <option value="DE">DE</option>
		                  <option value="FL">FL</option>
		                  <option value="GA">GA</option>
		                  <option value="HI">HI</option>
		                  <option value="IA">IA</option>
		                  <option value="ID">ID</option>
		                  <option value="IL">IL</option>
		                  <option value="IN">IN</option>
		                  <option value="KS">KS</option>
		                  <option value="KY">KY</option>
		                  <option value="LA">LA</option>
		                  <option value="MA">MA</option>
		                  <option value="MB">MB</option>
		                  <option value="MD">MD</option>
		                  <option value="ME">ME</option>
		                  <option value="MI">MI</option>
		                  <option value="MN">MN</option>
		                  <option value="MO">MO</option>
		                  <option value="MS">MS</option>
		                  <option value="MT">MT</option>
		                  <option value="NB">NB</option>
		                  <option value="NC">NC</option>
		                  <option value="ND">ND</option>
		                  <option value="NE">NE</option>
		                  <option value="NF">NF</option>
		                  <option value="NH">NH</option>
		                  <option value="NJ">NJ</option>
		                  <option value="NM">NM</option>
		                  <option value="NT">NT</option>
		                  <option value="NS">NS</option>
		                  <option value="NU">NU</option>
		                  <option value="NV">NV</option>
		                  <option value="NY">NY</option>
		                  <option value="OH">OH</option>
		                  <option value="OK">OK</option>
		                  <option value="ON">ON</option>
		                  <option value="OR">OR</option>
		                  <option value="PA">PA</option>
		                  <option value="PE">PE</option>
		                  <option value="QC">QC</option>
		                  <option value="RI">RI</option>
		                  <option value="SC">SC</option>
		                  <option value="SD">SD</option>
		                  <option value="SK">SK</option>
		                  <option value="TN">TN</option>
		                  <option value="TX">TX</option>
		                  <option value="UT">UT</option>
		                  <option value="VA">VA</option>
		                  <option value="VT">VT</option>
		                  <option value="WA">WA</option>
		                  <option value="WI">WI</option>
		                  <option value="WV">WV</option>
		                  <option value="WY">WY</option>
		                  <option value="YK">YK</option>
		                  <option value="AA">AA</option>
		                  <option value="AE">AE</option>
		                  <option value="AP">AP</option>
		                  <option value="AS">AS</option>
		                  <option value="FM">FM</option>
		                  <option value="GU">GU</option>
		                  <option value="MH">MH</option>
		                  <option value="MP">MP</option>
		                  <option value="PR">PR</option>
		                  <option value="PW">PW</option>
		                  <option value="VI">VI</option>
		                </select></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Zip/Post Code</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="postCode" class="input-small zipInput"></td>
		            </tr>
		            <tr>
		               <td width="40%"><span class="f12">Phone Number</span> <span class="right"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		               <td width="60%"><input type="text" name="phoneNumber" class="input-small phoneNumberInput" value="${booking.phoneno}"> </td>
		            </tr>
  		            <tr>
		               <td width="40%"><span class="f12">Email</span> <span class="right"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		               <td width="60%"><input type="text" name="email" class="input-small emailInput" value="${booking.email}"></td>
		            </tr>
		          </table>
		        </div>
		      </div>
		      <div class="visa_button"> <span class="visa_note fl" style="display:none;"><img class="mr5" src="${ctx}/assets-web/images/icon_a2.png"> 2.2% handling charge will be paid on credit card payment </span>
		        <input class="visa_submit fr" type="button" value="Pay Now">
		        <span class="visa_price fr"></span>
		        <div class="clear"></div>
		      </div>
		    </form>
		  </div>
		</div>
        <#--信用卡信息填写表单-->
        
        <div class="Payment_terms">
            <input id="windowAgreement" type="checkbox" value="Y">
           	 I have read and agree to
             <a href="${ctx!}/us/terms_conditions.html" target="_blank">Terms & Conditions</a><#--<a href="" target="_blank">modification & cancellation terms</a>、<a href="${ctx!}${areaSuffix}/privacy_policy.html" target="_blank">terms of use & privacy policy</a>-->        
        </div>
        <div class="Payment_Method_btns">
        	<#--
             <a href="" class="Payment_Method_btn_1">Payment</a>
             <a href="" style="margin-left:10px; padding: 12px 36px; color: #FFF; text-decoration: none; font-size: 14px; font-weight: bold; border-radius: 5px; background: #e0e0e0;">Return</a>
             -->
        </div>
        <div class="clear"></div>
	</div>
	<!--手机-->
	<div id="mobileDiv" class="widthCenter visible-xs">
    </div>
  </div>
</section>
<#--bottom-->
<#include "/front/include/paybottom.ftl"/>
<#--bottom-->
<div id="payForm" style="display:none;">
	<#-- 本地版 
	<form id="paypalForm" action="${paypalGateway!}" method="post" target="_blank">
	    <input type="hidden" name="cmd" value="_xclick"/>
	    <input type="hidden" name="business" value="${paypalAccount!}"/>
	    <input type="hidden" name="item_name" value="${orders.orderdetails[0].product.name}    from www.intertrips.com"/>
        <input type="hidden" name="item_number" value="${orders.orderno}"/> 
		<input type="hidden" name="amount" class="price"/>
		<input type="hidden" name="currency_code" value="${(orders.cost.code)!}"/>
		<input type="hidden" name="invoice" value="${orders.id}"/>
        <input type="hidden" name="cancel_return" value="http://127.0.0.1/intertrips/">
		<input type="hidden" name="return" value="http://127.0.0.1/intertrips/ipnPay/return.do">
	    <input type="hidden" name="notify_url" value="https://www.intertrips.com/ipnPay/notify2.do"/>
	    <input type="hidden" name="custom" class="paytype" value="5"/>
	</form>
	-->
	
	<#-- 外网 -->
	<form id="paypalForm" action="${paypalGateway!}" method="post" target="_blank">
	    <input type="hidden" name="cmd" value="_xclick"/>
	    <input type="hidden" name="business" value="${paypalAccount!}"/>
	    <input type="hidden" name="item_name" value="${booking.product.name}   from www.intertrips.com"/>
        <input type="hidden" name="item_number" value="${booking.bookingNo}"/> 
        <input type="hidden" name="invoice" value="${booking.id}"/>
		<input type="hidden" name="amount" class="price"/>
		<input type="hidden" name="currency_code" value="USD"/>
        <input type="hidden" name="cancel_return" value="https://www.intertrips.com/">
		<input type="hidden" name="return" value="https://www.intertrips.com/ipnPay/bookreturn.do">
	    <input type="hidden" name="notify_url" value="https://www.intertrips.com/ipnPay/booknotify2.do"/>
	    <input type="hidden" name="custom" class="paytype" value="5"/>
	</form>
</div>
<#include "/front/include/alertFrame.ftl"/>
<script type="text/javascript">
<#--
if (typeof jQuery.fn.fitVids === "undefined") { document.write("<script src='${ctx!}/assets-web/js/jquery.fitvids.min.js'>\x3C/script>"); }
-->
var price = "${booking.total?c}";

var $agreement;
var $cardPay;

var bePaying = 0;

$(function(){
	  //根据window版本和mobile版本调整导航栏
	  if($("#mobileDiv").is(":hidden")){
	  	  $('#MainMenu').addClass('scrolled menu-shrink');
	  	  $(window).scroll(function(){
	  	  	 $('#MainMenu').addClass('scrolled menu-shrink');
	  	  });
	  	  $agreement = $("#windowAgreement");
		  $cardPay = $("#cardPayAero");	
	  }else{
	  	 $('#MainMenu').addClass('scrolled');   
		 $agreement = $("#mobileAgreement");
		 $cardPay = $("#mobileCardPayAero");	  
	  }
	  
	$(".visa_price").html('$' + price);
	  var myDate = new Date();
	  var month = myDate.getMonth()+1;       //获取当前月份(2位)
	  var year = myDate.getFullYear();   	//获取完整的年份(4位,1970-????)
	  if(parseInt(month)<10){
		  month = "0"+month;
	  }
	  $(".expireMonthSelect").val("${booking.expirationDateMonth}");
	  $(".expireYearSelect").val(${booking.expirationDateYear});
	  
	 //根据选择的国家判断
	 $(".countrySelect").change(function(){
	    if($(this).val() == "US" || $(this).val() == "CA"){
	    	$(this).closest(".basicInformation").next().fadeIn(150);
	    }else{
	    	$(this).closest(".basicInformation").next().fadeOut(150);
	    }
	 });
	 
	 $(".visa_submit").click(function(){
		 checkPayment();
	 });
});

	<#-- 支付 -->
	function toPayment(payMethod){

		//如果用户没有同意用户条款,则弹出提示
		if(!$agreement.is(":checked")){
			alertWarn('please agree with the user agreement.');
			$agreement.focus();	
			return;
		}

		//给支付form设置支付金额
		var $payForm = $("#payForm");
		$payForm.find("input.price").val(price);
		
		if("Paypal" == payMethod){
			var isCheck = checkStore();
			if(isCheck){
				$("#paypalForm").submit();
			}
		}else if("card" == payMethod){
			$cardPay.slideDown();
		}	
	}
	
	function checkPayment() {
	
		//如果用户没有同意用户条款,则弹出提示
		if(!$agreement.is(":checked")){
			alertWarn('please agree with the user agreement.');
			$agreement.focus();	
			return;
		}	
		$(".CPriceInput").val(price);
		
		var re = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/i;
		
		var $payForm = $cardPay.find("form");
		
		var $cardTypeSelect = $payForm.find(".cardTypeInput");
		var $cardNumber = $payForm.find(".cardNumberInput");
		var $authenticationNum = $payForm.find(".cardSecurityCode");
		var $country = $payForm.find(".countrySelect");
		
		if($cardTypeSelect.val() == 0) {
			alertWarn("Please select the card type.");
			addWarnningShow($cardTypeSelect);
			return;
		} else if ($cardNumber.val().trim() == "") {
			alertWarn("Please fill the card number.");
			addWarnningShow($cardNumber);
			return;
		} else if ($authenticationNum.val().trim() == "") {
			alertWarn("Please fill in the Card Security Code.");
			addWarnningShow($authenticationNum);
			return;
		} else if ($country.val() == 0) {
			alertWarn("Please select country.");
			addWarnningShow($country);
			return;
		} else if ($country.val() == "US" || $country.val() == "CA") {
			var $firstNameInput = $payForm.find(".firstNameInput");
			var $lastNameInput = $payForm.find(".lastNameInput");
			var $streetAddressInput = $payForm.find(".streetAddressInput");
			var $cityInput = $payForm.find(".cityInput");
			var $stateSelect = $payForm.find(".stateSelect");
			var $zipInput = $payForm.find(".zipInput");
			var $phoneNumberInput = $payForm.find(".phoneNumberInput");
			var $emailInput = $payForm.find(".emailInput");
			
			if ($firstNameInput.val().trim() == "") {
				alertWarn("Please fill in the first name.");
				addWarnningShow($firstNameInput);
				return;
			} else if ($lastNameInput.val().trim() == "") {
				alertWarn("Please fill in the last name.");
				addWarnningShow($lastNameInput);
				return;
			} else if ($streetAddressInput.val().trim() == "") {
				alertWarn("Please fill in the street address.");
				addWarnningShow($streetAddressInput);
				return;
			} else if ($cityInput.val().trim() == "") {
				alertWarn("Please fill in the city.");
				addWarnningShow($cityInput);
				return;
			} else if ($stateSelect.val().trim() == "") {
				alertWarn("Please select state.");
				addWarnningShow($stateSelect);
				return;
			} else if ($zipInput.val().trim() == "") {
				alertWarn("Please fill in the zip.");
				addWarnningShow($zipInput);
				return;
			} else if ($phoneNumberInput.val().trim() == ""){
				alertWarn("Please fill in the phone number.");
				addWarnningShow($phoneNumberInput);
				return;
			} else if ($emailInput.val().trim() == ""){
				alertWarn("Please fill in the email.");
				addWarnningShow($emailInput);
				return;
			} else if (!re.test($emailInput.val().trim())){
				alertWarn("Please enter the valid email.");
				addWarnningShow($emailInput);
				return;
			}else {
			    submitBooking($payForm);
				return;
			}
		} else {
			submitBooking($payForm);
			return;
		}
	}
	
	var submitBooking = function($payForm){
		if(bePaying == 0){
			var isCheck = checkStore();
			if(isCheck){
			    bePaying = 1;
				setTimeout(refreshPayingStatus,60000);
				$payForm.submit();
			}
		}
	}
	
	var refreshPayingStatus = function(){
	    bePaying = 0;
	}
	
	//给输入框添加必填的提示以及获取焦点后去除提示
	var addWarnningShow = function($input){
		$input.addClass("pay_mess_input_error");
		$input.focus(function(){
			$(this).removeClass("pay_mess_input_error");
		});
	}
	
	var checkStore = function(){
	    return true;
	}
</script> 
</body>
</html>