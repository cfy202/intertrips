<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link http-equiv="Pragma" content="no-cache"/>
<link http-equiv="Cache-Control" content="no-cache">
<link http-equiv="Expires" content="0">
<title>Tour book</title>
<link rel="shortcut icon" href="${ctx!}/favicon.ico">
<link rel="apple-touch-icon-precomposed" href="${ctx!}/apple-touch-icon.png">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<link rel='stylesheet' id='font-awesome-css' href="https://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" type='text/css' media='all' />
<meta name="generator">
<style id="fit-vids-style">
.fluid-width-video-wrapper { width: 100%; position: relative; padding: 0; }
.fluid-width-video-wrapper iframe, .fluid-width-video-wrapper object, .fluid-width-video-wrapper embed { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
</style>
<style id="ShrinkMenu">
.menu-shrink { top : -49px !important; }
</style>
</head>
<body class="blog">
<div></div>
<div id="top" class="navbar-wrapper">
</div>
<section id="sectionHtml" class="hero small-hero" style="background-image: url(${ctx!}/assets-web/images/details_bg.jpg);color: #fff;position: relative;background-color:#6c6e73;background-position: center;background-size: cover;background-repeat: no-repeat;">
</section>
<section class="featured-destinations" style="background-color:#f5f6f6; padding-bottom:20px;">
  <div class="container margin-bottom padding-top" >
    <div class="row tour-single-rise">
      <main class="col-md-9">
        <div class="tours-tabs">
          <ul id="chooseDiv" class="nav nav-tabs hidden-xs">
            <li>Details</li>
            <li>Itinerary</li>
            <li class="departureDateDiv">Dates & Rates</li>
            <li class="galleryDiv">Gallery</li>
            <li class="active booking">Book</li>
          </ul>
          <div id="windowDiv" class="tab-content hidden-xs">
            <div id="tourlineDetailTab" class="tab-pane">
            </div>
            <div id="itineraryTab" class="tab-pane">
            </div>
            <div id="departureTab" class="tab-pane">
            </div>
            <div id="galleryTab" class="tab-pane">
            </div>			         
            <div class="tab-pane" style="display:block;">
                <form id="windowForm" method="POST" action="${ctx!}/agent_booking.htm">
                  <input class="tourPriceIdInput" type="hidden" name="tourpriceid" />
                  <input class="tourlineIdInput" type="hidden" name="tourlineId" />
                  <input type="hidden" class="departureIdInput" name="departureid"/>
                  <input type="hidden" id="tourdateIdInput" name="tourdateid" value="${shoppingCart.tourDateId}">
                  <input type="hidden" name="agentcode" value="${shoppingCart.agentCode}"/>
                  <div class="tours-tabs-content">
                    <!-- bookingInformation  step 1 -->
                    <h4 class="bookingInformation">Booking Information</h4>
                    <table style="display: table;" class="table form-block-book bookingInformation">
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label" style="width:25%;">Name</label>
                              <input class="form-block-input" name="name"  maxlength="100" placeholder="" value="${shoppingCart.firstName} ${shoppingCart.lastName}" type="text">
                              <div class="clear"></div>
                            </div>
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label" style="width:25%;">Phone No.</label>
                              <input class="form-block-input" name="phoneno" value="${shoppingCart.phone}" maxlength="20" placeholder="" type="text">
                              <div class="clear"></div>
                            </div>
                          </td>
                        </tr>
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label" style="width:25%;">Email</label>
                              <input class="form-block-input" name="email" value="${shoppingCart.email}" maxlength="100" placeholder="" type="text">
                              <div class="clear"></div>
                            </div>
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label" style="width:25%;">Pax</label>
                              <input class="form-block-input" type="number" name="pax" value="${shoppingCart.totalNumber}" maxlength="20" placeholder="" type="text"></i>
                              <div class="clear"></div>
                            </div>
                          </td>                          
                        </tr>
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label" style="width:25%;">Gateway</label>
                              <input class="form-block-input" id="gateWayInput" name="gateway" value="" maxlength="100" readOnly="readOnly" placeholder="" type="text">
                              <div class="clear"></div>
                            </div>
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label" style="width:25%;">Creditcard No.</label>
                              <input class="form-block-input" name="creditcardno" value="" maxlength="40" placeholder="" type="text">
                              <div class="clear"></div>
                            </div>
                          </td>                          
                        </tr>
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label" style="width:25%;">Expiration Date</label>
                              <select class="form-block-input" style="width:29%; margin-right:2%;" name="expirationDateMonth" value="" maxlength="100" placeholder="MM" type="text">
                              	<option >01</option>
                                <option >02</option>
                                <option >03</option>
                                <option >04</option>
                                <option >05</option>
                                <option >06</option>
                                <option >07</option>
                                <option >08</option>
                                <option >09</option>
                                <option >10</option>
                                <option >11</option>
                                <option >12</option>
                              </select>
                              <select class="form-block-input" style="width:29%; margin-right:2%;" name="expirationDateYear"  type="text">
                              	<option value="2016">16</option>
                                <option value="2017">17</option>
                                <option value="2018">18</option>
                                <option value="2019">19</option>
                                <option value="2020">20</option>
                                <option value="2021">21</option>
                                <option value="2022">22</option>
                                <option value="2023">23</option>
                                <option value="2024">24</option>
                                <option value="2025">25</option>
                                <option value="2026">26</option>
                                <option value="2027">27</option>
                                <option value="2028">28</option>
                                <option value="2029">29</option>
                                <option value="2030">30</option>
                              </select>
                              <div class="clear"></div>
                            </div>
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label" style="width:25%;">Security Code</label>
                              <input class="form-block-input" name="securitycode" value="" maxlength="5" placeholder="" type="text">
                              <div class="clear"></div>
                            </div>
                          </td>                          
                        </tr>
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label" style="width:25%;">Total</label>
                              <input class="form-block-input" id="totalInput" name="total" maxlength="10" placeholder="" type="text">
                              <div class="clear"></div>
                            </div>
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label" style="width:25%;">Departure Date</label>
                              <input class="form-block-input departureDateInput" name="departuredate" value="${shoppingCart.departureDate?string('MM/dd/yyyy')}" readOnly="readOnly" maxlength="20" placeholder="" type="text">
                              <div class="clear"></div>
                            </div>
                          </td>                          
                        </tr>
                        <tr>
                          <td colspan="4">
                            <div class="form-block-book-name">
                              <label style="width:12%;" class="form-block-label">Remarks</label>
                              <textarea type="text" placeholder="" maxlength="200" name="remarks" value="" id="windowRemark" style="width:81%; height:90px;" class="form-block-input"></textarea>
                              <div class="clear"></div>
                            </div>
                          </td>
                        </tr>
                        <tr>
                          <td width="50%">
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name" style="float: right;">
                              <input class="form-block-button" onclick="windowconfirm(0);" value="Submit" style="width: 90px;" type="button">
                              <input class="form-block-button" onclick="windowconfirm();" value="Submit&Pay" style="width: 90px;" type="button">
                            </div>
                            <div class="clear"></div>
                          </td>
                        </tr>                        
                    </table>
                  </div>
                </form>
            </div>
          </div>
        </div>
        <!-- .tour-tabs --> 
      </main>
      <div id="promotionShow" class="price-decoration-label-round" style="display:none;background-color:#ff662a;"><span>Last Minute</span></div>
      <a name="tourBooking"></a>
      <#--
      <aside class="col-md-3 sidebar" role="complementary">
        <div class="price-decoration block-after-indent">
          <div class="price-decoration-value"> <ins id="priceShow"></ins> <span>One Person</span>
            <div class="clear"></div>
          </div>
        </div>
        <div class="form-block">
        </div>
      </aside>
      -->
    </div>
  </div>
</section>
<section id="reviewSection" class="featured-destinations" style="background-color:#fff;">
</section>
<#include "/front/include/staticBottom.ftl"/>
<div style="display:none;">
</div>
<#include "/front/include/alertFrame.ftl"/>
<script type='text/javascript' src='${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js'></script>
<#--
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-38017454-1', 'auto');
  ga('send', 'pageview');
</script>
-->
<script>
   var topHtml = sessionStorage.getItem("top");
   $("#top").html(topHtml);
   
   var sectionHtml = sessionStorage.getItem("section");
   $("#sectionHtml").html(sectionHtml);
   
   var windowTourlineDetailHtml = sessionStorage.getItem("windowTourlineDetail");
   $("#tourlineDetailTab").html(windowTourlineDetailHtml);
	
	var windowItineraryHtml = sessionStorage.getItem("windowItinerary");
	$("#itineraryTab").html(windowItineraryHtml);
	
	var windowDepartureHtml = sessionStorage.getItem("windowDeparture");
	$("#departureTab").html(windowDepartureHtml);
	
	var windowGalleryHtml = sessionStorage.getItem("windowGallery");
	$("#galleryTab").html(windowGalleryHtml);
    
	var downNavigationHtml = sessionStorage.getItem("downNavigation");
	$("#downNavigation").html(downNavigationHtml);
	
	var reviewSectionHtml = sessionStorage.getItem("reviewSection");
	$("#reviewSection").html(reviewSectionHtml);
    
    var tourlineId = sessionStorage.getItem("tourlineId");
	$(".tourlineIdInput").val(tourlineId);

	var tourPriceId = sessionStorage.getItem("chooseTourPriceId");
	$(".tourPriceIdInput").val(tourPriceId);

	var guideServiceId = sessionStorage.getItem("guideServiceId");
	$(".guideServeIdInput").val(guideServiceId);
	
	var steamId = sessionStorage.getItem("steamId");
	$(".steamPriceIdInput").val(steamId);	

	var $mobileCustom = $("#mobileCustomTab");
	var itinerarySize = sessionStorage.getItem("itinerarySize");
	var hasAdditionalProduct = false;
    for(var i=0; i<itinerarySize; i++){
    	$windowCustom.append(sessionStorage.getItem("windowItinerary_" + i));
		$mobileCustom.append(sessionStorage.getItem("mobileItinerary_" + i)); 
		hasAdditionalProduct = true;
    }	
    
	var promotionIsHidden = sessionStorage.getItem("promotionIsHidden");
	if(promotionIsHidden == 1){
		$("#promotionShow").show();
	}	
	
	if(sessionStorage.getItem("viewLoaded") == 1){
		viewLoaded = true;
	}else{
		viewLoaded = false;
	}
	
	if(sessionStorage.getItem("imagesLoaded") == 1){
	    imagesLoaded = true;
	}else{
		imagesLoaded = false;
		var galleryImageHtml = sessionStorage.getItem("galleryImageHtml");
	}
	
	var sign = sessionStorage.getItem("sign");
	
	var tourlineId = sessionStorage.getItem("tourlineId");
	var costnumber = sessionStorage.getItem("costId");
	var productId = sessionStorage.getItem("productId");
</script>
<script type='text/javascript' src="${ctx!}/assets-web/js/order-min.js"></script>
<script type="text/javascript">
   init = 1;
   departureLoaded = true;
   
   chooseDepartureInfo = sessionStorage.getItem("chooseDepartureInfo");
   var tourlineName = sessionStorage.getItem("tourlineName");
   
   var $transferSelect;
   var $airticketsSelect;
   
	//用户所添加的附加产品
	var additionalProductFees = {};
	
	//双人价
	var sellingPrice = sessionStorage.getItem("chooseSellingPrice");
	//单房差
	var singleroomprice = sessionStorage.getItem("chooseSingleroomprice");
	//儿童价
	var childPrice = sessionStorage.getItem("chooseChildPrice");
	//婴儿价
	var babyPrice = sessionStorage.getItem("chooseBabyPrice");
	
	//每人的导服费
	var perTipPrice = sessionStorage.getItem("guideServiceFee");
	//每人的行程外精彩景点观光
	var perSteamPrice = sessionStorage.getItem("steamFee");
	//每人的接送机费用
	var perTransferPrice = 0;
	//每人的的机票费用
	var perAirTicketsPrice = 0;
	
	if(isNaN(perTipPrice)){
		perTipPrice = 0;	
	}
	if(isNaN(perSteamPrice)){
		perSteamPrice = 0;	
	}

	//客人总人数
	var totalNumber = parseFloat(cancelFormat('${shoppingCart.totalNumber}'));
	//成人数量
	var adultsNumber = totalNumber;
	//儿童数量
	var childrenNumber = 0;
	//婴儿数量
	var infantsNumber = 0;
	
	//团费
	var tourFee = sellingPrice * totalNumber;
	//单房差
	var singleSupplementsFee = 0;
	//导服费
	var tipFee = perTipPrice * totalNumber;
	//行程外观光	
	var steamFee = perSteamPrice * totalNumber;
	//接送机费用
	var transferFee = 0;
	//机票费用
	var airTicketsFee = 0;
	//积分兑换的费用
	var integralFee = 0;
	//优惠券减免费用
	var couponFee = 0;
	
	//总价格
	var totalFee = 0;
	
	//选择房间的客人数量
	var choosedRoomPeopleNumber = 0;
	
	//单人间的数量
	var singleRoomNumber = 0;
	
	//房间信息
	var passengerInformation = new Array();
	
	//步骤
	var step = 1;
	
	//展示自定义产品
	var $additionalProduct;
	
	var $transferSelect;
	var $airticketsSelect;
	
	var transferText = '';
	var airticketsText = ''; 
	
	var ignorePassportStatus = false;
	
//初始化加载数据
$(function(){
	$(".panel-box").each(function(index){
		if($(this).hasClass("collapsed") && index != 0){
			$(this).removeClass("collapsed").addClass("panel-heading").next().hide();
		}
	});

	chooseDepartureId = '${(shoppingCart.departureId)!}';
	$(".people-departure-select").val(chooseDepartureId);
	
	$('input').customInput();
	
	//pc端tab切换 
	$('.tours-tabs .nav-tabs li').click(function(){
		if(tabIsLocked){
			alertWarn("Please choose the departure date.");
			return;
		}	
		$(this).addClass('active').siblings().removeClass('active');
		$('.tours-tabs .tab-content>div:eq('+$(this).index()+')').show().siblings().hide();	
	});
	
	$(".panel-box:eq(0)").click();
	
		dateNumber = 21;
		$departureSelect = $("#windowDepartureSelect");	
		$additionalProduct = $("#windowAdditional");
		$transferSelect = $("#windowTransferSelect");
		$airticketsSelect = $("#windowAirticketsSelect");	
	
	if($transferSelect.size() > 0){
		$transferSelect.val(sessionStorage.getItem("selectTransferId"));
		if($transferSelect.val() != ''){
			var $chooseOption = $transferSelect.find("option:selected");
			perTransferPrice = $chooseOption.attr("price") * 1;
			transferFee = perTransferPrice * totalNumber;
			transferText = $chooseOption.html();
		}
	}
	if($airticketsSelect.size() > 0){
		$airticketsSelect.val(sessionStorage.getItem("selectAirticketId"));
		if($airticketsSelect.val() != ''){
			var $chooseOption = $airticketsSelect.find("option:selected");
			perAirTicketsPrice = $chooseOption.attr("price") * 1;
			airTicketsFee = perAirTicketsPrice * totalNumber;
			airticketsText = $chooseOption.html();
		}
	}	
	addDepartureChange($departureSelect);
	
	$transferSelect.change(function(){
		if($(this).val() != ''){
			var $chooseOption = $(this).find("option:selected");
			perTransferPrice = $chooseOption.attr("price") * 1;
			transferText = $chooseOption.html();
		}else{
			perTransferPrice = 0;
			transferText = '';
		}
		showOrHideTransferAndAirtickets();
	});
	
	$airticketsSelect.change(function(){
		if($(this).val() != ''){
			var $chooseOption = $(this).find("option:selected");
			perAirTicketsPrice = $chooseOption.attr("price") * 1;
			airticketsText = $chooseOption.html();
		}else{
			perAirTicketsPrice = 0;
			airticketsText = '';
		}
		showOrHideTransferAndAirtickets();
	});	
	
	//显示单价
	$("#priceShow").html(sign + formatPrice(sellingPrice));
	
	var $selectedOption = $("#windowDepartureSelect").find("option:selected");
	var departureId = $selectedOption.val();
	var departureName = $selectedOption.html();
	$("#gateWayInput").val(departureName);
	$(".departureIdInput").val(departureId);
	$("#totalInput").val(tourFee);
	
});
	//选择日期
	var chooseDate = function(startTime,singleRoomPriceInput,sellingPriceInput,childPriceInput,babyPriceInput,tourdateId,tourPriceId){
		tabIsLocked = false;
		$(".departureDateInput").val(startTime);
		var $selectedOption = $("#windowDepartureSelect").find("option:selected");
		var departureId = $selectedOption.val();
		var departureName = $selectedOption.html();
		$("#gateWayInput").val(departureName);
		$(".departureIdInput").val(departureId);
		$("#tourdateIdInput").val(tourdateId);
		$("#tourPriceIdInput").val(tourPriceId);
		alertWarn('You have set ' + startTime + ' as your departure date.');
		$("#chooseDiv").find("li").removeClass("active").eq(4).addClass('active');
		$("#windowDiv").find("div.tab-pane").hide().eq(4).show();
	}
	
	//提交
	var windowconfirm = function(type){
	    var $form = $("#windowForm");
	    
	    var	$nameInput = $form.find("input[name='name']");
		var	$phoneNoInput = $form.find("input[name='phoneno']");
		var	$emailInput = $form.find("input[name='email']");
		var	$paxInput = $form.find("input[name='pax']");
		var	$creditcardnoInput = $form.find("input[name='creditcardno']");
	    var	$securitycodeInput = $form.find("input[name='securitycode']");
		var	$totalInput = $("#totalInput");
		
		//name空值校验
		if($nameInput.val().trim() == ''){
			alertWarn('Please fill in name.');
			addWarnningShow($nameInput);
			return;
		}
		if($phoneNoInput.val().trim() == ''){
			alertWarn('Please fill in phone No.');
			addWarnningShow($phoneNoInput);
			return;
		}
		if(!numberRule.test($phoneNoInput.val().trim())){
			alertWarn('The phone No. must be made up of 8 to 12 digits.');
			addWarnningShow($phoneNoInput);
			return;	
		}		
		//email空值校验
		if($emailInput.val().trim() == ''){
			alertWarn('Please fill in email.');
			addWarnningShow($emailInput);
			return;
		}
		if(!reg.test($emailInput.val().trim())){
			alertWarn('Please enter the valid email.');
			addWarnningShow($emailInput);
			return;
		}
		
		if($paxInput.val().trim() == ''){
			alertWarn('Please fill in pax.');
			addWarnningShow($paxInput);
			return;
		}
		if(!numberRul.test($paxInput.val().trim())){
			alertWarn('The pax must be number.');
			addWarnningShow($paxInput);
			return;	
		}	
		if(!($paxInput.val().trim() > 0)){
		    alertWarn('The pax must be greater than 0.');
		    addWarnningShow($paxInput);
		    return ;
		}	
		if($creditcardnoInput.val().trim() == ''){
			alertWarn('Please fill in credit card No.');
			addWarnningShow($creditcardnoInput);
			return;
		}
		if(!numberRul.test($creditcardnoInput.val().trim())){
			alertWarn('The credit card No must be number.');
			addWarnningShow($creditcardnoInput);
			return;	
		}	
		if($securitycodeInput.val().trim() == ''){
			alertWarn('Please fill in security code.');
			addWarnningShow($securitycodeInput);
			return;
		}
		if(!numberRul.test($securitycodeInput.val().trim())){
			alertWarn('The security code must be number.');
			addWarnningShow($securitycodeInput);
			return;	
		}	
		if($totalInput.val().trim() == ''){
			alertWarn('Please fill in total.');
			addWarnningShow($totalInput);
			return;
		}
		if(!numberRul.test($totalInput.val().trim())){
			alertWarn('The total must be number.');
			addWarnningShow($totalInput);
			return;	
		}	
		if(type == 0){
			$form.append('<input type="hidden" name="type" value="1"/>');	
		}
		$form.submit();
	}
</script> 
</body>
</html>