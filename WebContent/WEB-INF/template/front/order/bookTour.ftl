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
                <form id="windowForm" method="POST" action="${ctx!}/confirm_order.htm">
                  <input class="totalPriceInput" type="hidden" name="totalPrice" />
                  <input class="tourPriceIdInput" type="hidden" name="tourPriceId" />
                  <input class="guideServeIdInput" type="hidden" name="guideServeId" />
                  <input class="steamPriceIdInput" type="hidden" name="steamPriceId" />
                  <input class="tourlineIdInput" type="hidden" name="tourlineId" />
                  <input class="voucherNumberInput" type="hidden" name="voucherNumber" value="${(shoppingCart.voucherNumber)!''}"/>
                  <input class="pnumInput" type="hidden" name="pnum" value="${(shoppingCart.pnum)!'0'}"/>
                  <input id="windowDepartureDateInput" type="hidden" class="departureDateInput" name="departureDate" value="${shoppingCart.departureDate?string('MM/dd/yyyy')}"/>
                  <!--
                  <input id="address" type="hidden" name="address" value="${(shoppingCart.departCity)!''}"/>
                  -->
                  <input type="hidden" class="departureIdInput" name="departureId"/>
                  <input type="hidden" class="singleRoomNumber" name="singleRoomNumber"/>
                  <input type="hidden" class="doubleRoomNumber" name="doubleRoomNumber"/>
                  <input type="hidden" class="twinRoomNumber" name="twinRoomNumber"/>
                  <input type="hidden" class="tripleRoomNumber" name="tripleRoomNumber"/>
                  <input type="hidden" class="exchangeScore" name="exchangeScore"/>
                  <input type="hidden" class="couponCode" name="couponCode"/>
                  <div class="tours-tabs-content">
                  
                  <div class="tab-step hidden-xs">
          	<div class="tab-step-main">
            	<div class="tab-step-num 1step tab-step-num-active">
                	<span>1</span>
                </div>
                <div class="tab-step-name">
                	<p>Step 1 </p>
                    <p>Contacts Information</p>
                </div>
                <div class="clear"></div>
            </div>
            <div class="tab-step-main">
            	<div class="tab-step-num 2step">
                	<span>2</span>
                </div>
                <div class="tab-step-name">
                	<p>Step 2 </p>
                    <p>Traveler Passport Information</p>
                </div>
                <div class="clear"></div>
            </div>
            <div class="tab-step-main">
            	<div class="tab-step-num 3step">
                	<span>3</span>
                </div>
                <div class="tab-step-name">
                	<p>Step 3 </p>
                    <p>Accommodation Information</p>
                </div>
                <div class="clear"></div>
            </div>
            <div class="tab-step-main">
            	<div class="tab-step-num 4step">
                	<span>4</span>
                </div>
                <div class="tab-step-name">
                	<p>Step 4 </p>
                    <p id="bookTitle">Additional Services</p>
                </div>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
          </div>
                  
                  
                    <!-- bookingInformation  step 1 -->
                    <h4 class="bookingInformation">Contacts Information</h4>
                    <table class="table form-block-book bookingInformation">
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">First Name</label>
                              <input class="form-block-input" id="windowFirstName"  value="${shoppingCart.firstName}" type="text" maxlength="100" placeholder="First Name">
                              <div class="clear"></div>
                            </div>
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">Last Name</label>
                              <input class="form-block-input" id="windowLastName"  value="${shoppingCart.lastName}" type="text" maxlength="100" placeholder="Last Name">
                              <div class="clear"></div>
                            </div>
                          </td>
                        </tr>
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">Email</label>
                              <input class="form-block-input" id="windowEmail" value="${shoppingCart.email}" maxlength="100" type="text" placeholder="Email">
                              <div class="clear"></div>
                            </div>
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">Phone</label>
                              <input class="form-block-input" id="windowPhone"  value="${shoppingCart.phone}" maxlength="20" type="text" placeholder="Phone">
                              <div class="clear"></div>
                            </div>
                          </td>                          
                        </tr>
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">Adults</label>
                              <input id="windowAdultsNumber" class="form-block-input form-adults" onclick="adjustTotalNumber(this,1);" name="adultsNumber" value="${shoppingCart.totalNumber}" type="number" placeholder="Adults">
                              <span>(&gt; 11 years old)</span>
                              <div class="clear"></div>
                            </div>
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">Children</label>
                              <input id="windowChildrenNumber" class="form-block-input form-adults" onclick="adjustTotalNumber(this,0);" name="childrenNumber" value="0" type="number" placeholder="Children">
                              <span>(2-11 years old)</span>
                              <div class="clear"></div>
                            </div>
                          </td>                         
                        </tr>
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">Infants</label>
                              <input id="windowInfantsNumber" class="form-block-input form-adults" onclick="adjustTotalNumber(this,0);" name="infantsNumber" value="0" type="number" placeholder="Infants">
                              <span>(&lt; 2 years old)</span>
                              <div class="clear"></div>
                            </div>
                          </td>
                          <td width="50%">
                          </td>  
                        </tr>
                        <tr>
                          <td width="50%">
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name" style="float: right;">
                              <input type="button" class="form-block-button" onclick="confirmBookingInformation();" value="Next" style="width: 90px;"></input>
                            </div>
                            <div class="clear"></div>
                          </td>
                        </tr>                        
                    </table>
                    <!-- passenger information  step 2 -->
                    <h4 class="passengerInformation" style="display:none;">Traveler Passport Information</h4>
                    <div id="guestDetails" class="tours-tabs-content passengerInformation" style="display:none;">
                    	<p>Please be aware that we will use this information for air ticket purchasing and potential visa application.
                    	Please make sure that the information you put in match EXACTLY with the information on your passport.InterTrips hold no liability for any loss caused by the input inaccuracy of this information. </p>
	                    <div class="form-info guestRoomInfo">
	                    	
	                    </div>
	                    <div style="float:right; margin-top:20px;">
	                    	<div class="panel-heading" style="float: left; width:120px;"><input class="form-block-button" type="button" onclick="returnBookingInformation();" value="Prev" style="text-align:center;width:90px; background:#bbb;"></input></div>
	                    	<div class="panel-heading" style="float: left; width:120px;"><input class="form-block-button" type="button" onclick="confirmCustomerInformation();" value="Next" style="text-align:center;width:90px;"></input></div>
	                    	<div class="clear"></div>
	                    </div>
                    </div>
                    <!-- accommodation information step 3 -->
                    <h4 class="accommodation" style="display:none;">Accommodation Information</h4>
                    <p class="accommodation" style="margin-bottom: 5px; color: #ff662a;display:none;">Children under 11-years-old have no extra bed, they have to share beds with Parents.</p>
                    <div class="accommodation" style="display:none;">Room Types:</div>
                    <table class="table form-block-book accommodation" style="display:none;">
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">Triple Room</label>
                              <div class="clear"></div>
                              <p style="margin:0;"><img src="${ctx!}/assets-web/images/bed1.png"> (Two twin size beds, max 3 adults.)</p>                              
                            </div>
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">Twin Room</label>
                              <div class="clear"></div>
                              <p style="margin:0;"><img src="${ctx!}/assets-web/images/bed1.png"> (Two twin size beds, max 2 adults.)</p>               
                            </div>
                          </td>
                        </tr>
                        <tr>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">Double Room</label>
                              <div class="clear"></div>
                              <p style="margin:0;"><img src="${ctx!}/assets-web/images/bed2.png"> (One queen size bed, max 2 adults.)</p>                              
                            </div>
                          </td>
                          <td width="50%">
                          	<div class="form-block-book-name">
                              <label class="form-block-label">Single Room</label>
                              <div class="clear"></div>
                              <p style="margin:0;"><img src="${ctx!}/assets-web/images/bed3.png"> (One queen size bed, max 1 adults.)</p>                  
                            </div>
                          </td>
                        </tr>
                        <tr>
                        	<td colspan="2">
                        		<div class="form-block-book-name" style="text-align:right;">
                        			Please Choose Your Room Type  > 
				                    <input type="button" class="form-block-button addRoomButton" onclick="addRoom(this);" value="ADD ROOM" style="width: 120px;">
			                    </div>
			                    <div id="roomChoosePassenger" class="choose-main">
			                        <div class="people-box">
			                            <div class="people-choose">
			                              <label class="people-choose-label">Room Type</label>
			                              <select class="people-choose-select roomTypeSelect">
			                              </select>
			                              <div class="clear"></div>
			                            </div>
			                        </div>
			                        <div class="saveRoomDiv form-block-book-name" style="margin:30px 0 20px 0; text-align:right;">
			                            <input type="button" class="form-block-button" onclick="saveRoom(this);" value="SAVE" style="width: 120px;">
			                        </div>
				                </div>
				                <div id="roomPassengerInfoDiv" class="people-choose-information">
			                    </div>    
                        	</td>
                        </tr>
                        <tr>
                          <td width="50%"></td>
                          <td width="50%">
                          	<div class="form-block-book-name" style="float: right;">
                          	  <div class="panel-heading" style="float: left; width:120px;"><input type="button" class="form-block-button" onclick="returnCustomerInformation();" value="Prev" style="background:#BBBBBB;"></input></div>
                              <div class="panel-heading" style="float: left; width:120px;"><input type="button" class="form-block-button" onclick="confirmAccommodationInformation();" value="Next"></input></div>
                              <div class="clear"></div>
                            </div>
                          </td>
                        </tr>  
                    </table>
                    <!-- addtional Service information step 4 -->
                    <h4 class="additionalServices" style="display:none;">Additional Services</h4>
					<div id="windowOptionalTour" class="information-list-main optionalTour additionalServices" style="display:none;">
						
					</div>
					
					<!-- additional product information step 5 -->
                    <h4 class="additionalProducts" style="display:none;">Additional Products</h4>
					<div id="windowAdditional" class="information-list-main additionalProduct additionalProducts" style="display:none;">
							
					</div>
                    <!-- information summary step 6 -->
                    <h4 class="confirmInformation" style="display:none;">Information Summary</h4>
					<div class="information-list-main confirmInformation" style="display:none;">
						<!-- 预留信息 -->
						<div class="pay_messages steps_dd pay_un_check pay_bg" >
							<div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Reservation Information</span><b class="pay_messages_tit_b"></b></div>
							<div class="airport_list pay_messages_list_1">
								<div class="information-list">
									<div class="information-list-1">
										<div class="information-list-box-1">
											<span class="information-list-1-span" style="width: 20%;">Package Name:</span><span class="people-list2 showPackageName">${(shoppingCart.product.name)!}</span>
										</div>
										<div class="clear"></div>
									</div>                                                               
								</div>
								<div class="information-list">
									<div class="information-list-1">
										<div class="information-list-box left">
											<span class="information-list-1-span">Departure Gate Way:</span><span class="showDepartureGateWay"></span>
										</div>
										<div class="clear"></div>
									</div>                                                               
								</div>
								<div class="information-list">
									<div class="information-list-1">
										<div class="information-list-box left">
											<span class="information-list-1-span">Departure Date:</span><span class="people-list2 showDate"></span>
										</div>
										<div class="information-list-box left">
											<span class="information-list-1-span">Return Date:</span><span class="people-list2 showReturnDate"></span>
										</div>
										<div class="clear"></div>
									</div>                                                               
								</div>	
								<div class="information-list">
									<div class="information-list-1">
										<div class="information-list-box left">
											<span class="information-list-1-span">Transfer:</span><span class="people-list2 showTransfer"></span>
										</div>
										<div class="information-list-box left">
											<span class="information-list-1-span">Airtickets:</span><span class="people-list2 showAirtickets"></span>
										</div>
										<div class="clear"></div>
									</div>                                                               
								</div>																	
								<div class="information-list">
									<div class="information-list-1">
										<div class="information-list-box left">
											<span class="information-list-1-span">Adults:</span><span class="showAdults"></span>
										</div>
										<div class="information-list-box left">
											<span class="information-list-1-span">Children:</span><span class="people-list2 showChildren"></span>
										</div>										
										<div class="clear"></div>
									</div>                                                               
								</div>
								<div class="information-list">
									<div class="information-list-1">
										<div class="information-list-box left">
											<span class="information-list-1-span">Infants:</span><span class="showInfants"></span>
										</div>
										<div class="clear"></div>
									</div>                                                               
								</div>								
							</div>
							<div class="clear"></div>
						</div>
						<!-- 联系人信息 -->
						<div class="pay_messages steps_dd pay_un_check pay_bg" >
							<div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Contacts Information</span><b class="pay_messages_tit_b"></b></div>
							<div class="airport_list pay_messages_list_1">
								<div class="information-list">
									<div class="information-list-1">
										<div class="information-list-box left">
											<span class="information-list-1-span">First Name:</span><input name="firstName" maxlength="100" class="people-list2 showFirstName"></input>
										</div>
										<div class="information-list-box left">
											<span class="information-list-1-span">Last Name:</span><input name="lastName" maxlength="100" class="showLastName"></input>
										</div>
										<div class="clear"></div>
									</div>                                                               
								</div>
								<div class="information-list">
									<div class="information-list-1">
										<div class="information-list-box left">
											<span class="information-list-1-span">Email:</span><input name="email" maxlength="100" class="people-list2 showEmail"></input>
										</div>
										<div class="information-list-box left">
											<span class="information-list-1-span">Phone:</span><input name="phone" maxlength="20" class="showPhone"></input>
										</div>
										<div class="clear"></div>
									</div>                                                               
								</div>
							</div>
							<div class="clear"></div>
						</div>
						<!-- 客人信息 -->
						<div class="pay_messages steps_dd pay_un_check pay_bg customerInfo_div" >
							<div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Traveler Passport Information</span><b class="pay_messages_tit_b"></b></div>
							<div id="showPassengerInformation" class="airport_list pay_messages_list_1">
								
							</div>
							<div class="clear"></div>
						</div>
						<!-- 住房信息 -->
						<div class="pay_messages steps_dd pay_un_check pay_bg customerInfo_div">
							<div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Accommodation Information</span><b class="pay_messages_tit_b"></b></div>
							<div id="showRoomInformation" class="airport_list pay_messages_list_1">
							
							</div>
							<div class="clear"></div>
						</div>
						<!-- 其他特殊要求信息 -->
						<div class="pay_messages steps_dd pay_un_check pay_bg customerInfo_div">
							<div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Special Requirements</span><b class="pay_messages_tit_b"></b></div>
							<div class="airport_list pay_messages_list_1">
							<textarea class="form-control " name="specialrequest" id="specialrequest"  rows="5"  style="background-color: white;border:1px dashed red"></textarea>
							</div>
							<div class="clear"></div>
						</div>
						<!-- 自费项 -->
						<div class="pay_messages steps_dd pay_un_check pay_bg optionalInfo_div" style="display:none;">
                            <div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Additional Services Information</span><b class="pay_messages_tit_b"></b></div>
                            <div class="airport_list pay_messages_list_1 optionalTourInformation" >

                            </div>
                            <div class="clear"></div>
                        </div>	
						<!-- 用户自定义的产品显示 -->
						<div class="pay_messages steps_dd pay_un_check pay_bg customerInfo_div" style="display:none;">
                            <div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Additional Products Information</span><b class="pay_messages_tit_b"></b></div>
                            <div class="airport_list pay_messages_list_1 additionalProductInformation" >

                            </div>
                            <div class="clear"></div>
                        </div>						
						<!-- 优惠券 -->
						<h4>Preferential way</h4>
						<div class="information-list-main">
						  <div class="pay_mess_main">
						    <div class="pay_yh" style="display:none;">
						      <div class="yh_left left">
						        <label class="pay_d1">Integral</label>
						        <select class="pay_yhfs">
						          <option value="0">Gate B4, Frankfurt Airport - Terminal 1</option>
						          <option value="1">Gate B4, Frankfurt Airport - Terminal 1</option>
						          <option value="2">Gate B4, Frankfurt Airport - Terminal 1</option>
						        </select>
						      </div>
						      <div class="yh_right right">-￥123</div>
						      <div class="clear"></div>
						    </div>
						    <div class="pay_yh">
						      <div class="yh_left left">
						        <label class="pay_d1">Coupon</label>
						        <input type="text" class="pay_jf couponInput">
						        <input type="button" onclick="confirmCoupon(this)" class="pay_jf_btn" value="Redeem">
						      </div>
						      <div class="yh_right right couponsDetail"></div>
						      <div class="clear"></div>
						    </div>
						    <div class="pay_yh couponInfo" style="display:none;">
						    	<div class="yh_left left">
						        	<label class="pay_d1">Security Code</label>
						        	<input type="text" name="securityCode" class="pay_jf">
						      	</div>
						      	<div class="clear"></div>							    	
						    </div>
						    <div class="pay_yh couponInfo" style="display:none;">  
						      <div class="yh_left left">
						        <label class="pay_d1">Voucher Code</label>
						        <input type="text" name="voucherCode" class="pay_jf">
						      </div>
						      <div class="clear"></div>
						    </div>						    
						  </div>
						</div>
						<div class="form-block-book-name" style="float: right;">
							<div class="panel-heading" style="float: left; width:120px;"><input class="form-block-button" onclick="returnAccommodationInformation();" value="Prev" style="background:#BBBBBB;" type="button"></div>  
							<div class="panel-heading" style="float: left; width:120px;"><input type="button" class="form-block-button" onclick="submitForm()" value="Check out"></input></div>                      
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
					</div>
                  </div>
                </form>
            </div>
          </div>
          <!--手机-->
          <div class="panel-group visible-xs tabs-accordion" id="mobileDiv">
          	<div id="mobileBookingDiv" class="panel panel-default">
            	<div class="panel-heading panel-box">Book</div>
                <div class="panel-collapse" id="show_content_1" style="display:none;">
                	<div class="panel-body">
                    	 <form id="mobileForm" method="POST" action="${ctx!}/confirm_order.htm">
			                  <input class="totalPriceInput" type="hidden" name="totalPrice" />
			                  <input class="tourPriceIdInput" type="hidden" name="tourPriceId"/>
			                  <input class="guideServeIdInput" type="hidden" name="guideServeId" />
			                  <input class="steamPriceIdInput" type="hidden" name="steamPriceId" />
			                  <input class="tourlineIdInput" type="hidden" name="tourlineId"/>
			                  <input class="voucherNumberInput" type="hidden" name="voucherNumber" value="${(shoppingCart.voucherNumber)!''}"/>
                  			  <input class="pnumInput" type="hidden" name="pnum" value="${(shoppingCart.pnum)!'0'}"/>
			                  <input id="mobileDepartureDateInput" type="hidden" class="departureDateInput" name="departureDate" value="${shoppingCart.departureDate?string('MM/dd/yyyy')}"/>
			                  <!--
			                  <input id="address" type="hidden" name="address" value="${(shoppingCart.departCity)!''}"/>
							  -->
							  <input type="hidden" class="departureIdInput" name="departureId" />
			                  <input type="hidden" class="singleRoomNumber" name="singleRoomNumber"/>
			                  <input type="hidden" class="doubleRoomNumber" name="doubleRoomNumber"/>
			                  <input type="hidden" class="twinRoomNumber" name="twinRoomNumber"/>
			                  <input type="hidden" class="tripleRoomNumber" name="tripleRoomNumber"/>	
			                  <input type="hidden" class="exchangeScore" name="exchangeScore"/>
                  			  <input type="hidden" class="couponCode" name="couponCode"/>                           
                             <div class="tours-tabs-content">
                              	<!-- bookingInformation （mobile） step 1 -->
                                <h4 class="mobileBookingInformation">Contacts Information</h4>
                                <table class="table form-block-book mobileBookingInformation">
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">First Name</label>
                                          <input class="form-block-input-m" id="mobileFirstName"  value="${shoppingCart.firstName}" type="text" placeholder="First Name">
                                        </div>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">Last Name</label>
                                          <input class="form-block-input-m" id="mobileLastName" value="${shoppingCart.lastName}" type="text" placeholder="Last Name">
                                        </div>
                                      </td>
                                    </tr>   
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">Email</label>
                                          <input class="form-block-input-m" id="mobileEmail"  value="${shoppingCart.email}" type="text" placeholder="Email">
                                        </div>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">Phone</label>
                                          <input class="form-block-input-m" id="mobilePhone"  value="${shoppingCart.phone}" type="text" placeholder="Phone">
                                        </div>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">Adults</label>
                                          <input class="form-block-input-m" id="mobileAdultsNumber" name="adultsNumber" value="${shoppingCart.totalNumber}" onclick="adjustTotalNumber(this,1);" type="number" placeholder="Adults">
                                          <span>(&gt; 11 years old)</span>
                                        </div>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">Children</label>
                                          <input class="form-block-input-m" id="mobileChildrenNumber" name="childrenNumber" value="0" type="number" onclick="adjustTotalNumber(this,0);" placeholder="Children">
                                          <span>(2-11 years old)</span>
                                        </div>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">Infants</label>
                                          <input class="form-block-input-m" id="mobileInfantsNumber" name="infantsNumber" value="0" type="number" onclick="adjustTotalNumber(this,0);" placeholder="Infants">
                                          <span>(&lt; 2 years old)</span>
                                        </div>
                                      </td>
                                    </tr>
			                       <tr>
			                          <td>
			        			        <div class="form-block-book-name" style="text-align: center;">
			                              	<input type="button" class="form-block-button" onclick="confirmBookingInformationMobile();" value="Next" style="width:47%;"></input>
			                            </div>                    
			                          </td>
			                        </tr>  
                                </table>
                                <!-- passenger information（mobile） step 2 -->
                    			<h4 class="mobilePassengerInformation" style="display:none;">Traveler Passport Information</h4>
                                <div id="mobileGuestDetails" class="mobilePassengerInformation" style="display:none; padding-top:10px;s">
                                	<p>Please be aware that we will use this information for air ticket purchasing and potential visa application.
									Please make sure that the information you put in match EXACTLY with the information on your passport.InterTrips hold no liability for any loss caused by the input inaccuracy of this information. </p>
	                                <div class="form-info mobileGuestRoomInfo">
	                                	
	                                </div>
				                   	<div style="margin-top:20px; text-align:center;">
				                   		<input class="form-block-button" onclick="returnBookingInformationMobile();" value="Prev" style="width:47%; background:#bbb; margin-right:10px;cursor: pointer;"></input>
				                   		<input class="form-block-button" onclick="confirmCustomerInformationMobile();" value="Next" style="width:47%;cursor: pointer; "></input>
				                    	<div class="clear"></div>
				                    </div>           
                                </div>
                                <!-- accommodation information（mobile） step 3 -->
                                <h4 class="mobileAccommodation" style="display:none;">Accommodation Information</h4>
                                <p class="mobileAccommodation" style="margin-bottom: 5px; color: #ff662a;display:none;">Children under 11-years-old have no extra bed, they have to share beds with Parents.</p>
                                <div class="mobileAccommodation" style="display:none;">Room Types:</div>
                                <table class="table form-block-book mobileAccommodation" style="display:none;">
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">Triple Room</label>
                                          <div style="margin-top:8px;"><img src="${ctx!}/assets-web/images/bed1.png" style="float:left; margin-right:5px;"><p style="line-height: 20px; margin-bottom:0; font-size:12px; color:#939393;"> (Two twin size beds, max 3 adults.)</p><div class="clear"></div></div>
                                        </div>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">Twin Room</label>
                                          <div style="margin-top:8px;"><img src="${ctx!}/assets-web/images/bed1.png" style="float:left; margin-right:5px;"><p style="line-height: 20px; margin-bottom:0; font-size:12px; color:#939393;"> (Two twin size beds, max 2 adults.)</p><div class="clear"></div></div>
                                        </div>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">Double Room</label>
                                          <div style="margin-top:8px;"><img src="${ctx!}/assets-web/images/bed2.png" style="float:left; margin-right:5px;"><p style="line-height: 20px; margin-bottom:0; font-size:12px; color:#939393;"> (One queen size bed, max 2 adults.)</p><div class="clear"></div></div>
                                        </div>
                                      </td>
                                    </tr>
                                    <tr>
                                      <td>
                                        <div class="form-block-book-name">
                                          <label class="form-block-label-m">Single Room</label>
                                          <div style="margin-top:8px;"><img src="${ctx!}/assets-web/images/bed3.png" style="float:left; margin-right:5px;"><p style="line-height: 20px; margin-bottom:0; font-size:12px; color:#939393;"> (One queen size bed, max 1 adults.)</p><div class="clear"></div></div>
                                        </div>
                                      </td>
                                    </tr>
			                        <tr>
			                        	<td colspan="2">
			                        		<div class="form-block-book-name" style="text-align:right;">
			                                    <input type="button" class="form-block-button mobileAddRoomButton" onclick="addRoom(this);" value="ADD ROOM" style="width: 120px;">
			                                </div>
			                                <div id="mobileRoomChoosePassenger" class="choose-main">
			                                    <div class="people-box">
			                                        <div class="people-choose">
			                                          <label class="people-choose-label">Room Type</label>
			                                          <select class="people-choose-select roomTypeSelect">
			                                          </select>
			                                          <div class="clear"></div>
			                                        </div>
			                                    </div>
			                                    <div class="saveRoomDiv form-block-book-name" style="margin:30px 0 20px 0; text-align:right;">
			                                        <input type="button" class="form-block-button" onclick="saveRoom(this);" value="SAVE" style="width: 120px;">
			                                    </div>
			                                </div>
			                                <div id="mobileRoomPassengerInfoDiv" class="people-choose-information">
			                                </div>
			                        	</td>
			                        </tr>
			                        <tr>
			                          <td>
			              			    <div class="form-block-book-name" style="text-align: center;">
			                          	  <input type="button" class="form-block-button" onclick="returnCustomerInformationMobile();" value="Prev" style="width:46%; display: inline-block; margin-right: 10px; background:#bbb;"></input>
			                              <input type="button" class="form-block-button" onclick="confirmAccommodationInformationMobile();" value="Next" style="width:46%; display: inline-block;"></input>
			                            </div>              
			                          </td>
			                        </tr> 
                                </table> 
                                
                                <!-- additional Services（mobile） step 4 -->
                                <h4 class="mobileAdditionalServices" style="display:none;">Additional Services</h4>
                                <div class="information-list-main optionalTour mobileAdditionalServices" style="display:none;">
									  <div class="panel-collapse" >
									    <div class="panel-body">
									      <div id="mobileOptionalTourTab">
									      	
									      </div>
									    </div>
									  </div>
								</div>  
								
								<!-- additional product（mobile） step 5 -->                             
                                <h4 class="mobileAdditionalProducts" style="display:none;">Additional Products</h4>
                                <div class="information-list-main additionalProduct mobileAdditionalProducts" style="display:none;">
								  <div class="panel-collapse" >
								    <div class="panel-body">
								      <div id="mobileCustomTab">
								      </div>
								    </div>
								  </div>
								</div>         
                                
                                <!-- information summary（mobile） step 6 -->   
                                <h4 class="mobileConfirmInformation" style="display:none;">Information Summary</h4>
                                <div class="information-list-main mobileConfirmInformation" style="display:none;">
									<div class="information-list-main">
									  <!-- 预留信息 -->
							          <div class="pay_messages steps_dd pay_un_check pay_bg customerInfo_div" >
							            <div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Reservation Information</span><b class="pay_messages_tit_b"></b></div>
							            <div class="airport_list pay_messages_list_1">
							              <div class="information-list">
							                <div class="information-list-1">
							                  <div class="information-list-box"> <span class="information-list-1-span">Package Name:</span><span class="people-list2 showPackageName">${(shoppingCart.product.name)!}</span> </div>
							                  <div class="clear"></div>
							                </div>
							              </div>
							              <div class="information-list">
							                <div class="information-list-1">
							                  <div class="information-list-box"> <span class="information-list-1-span">Departure Gate Way:</span><span class="showDepartureGateWay"></span> </div>
							                  <div class="clear"></div>
							                </div>
							              </div>
							              <div class="information-list">
							                <div class="information-list-1">
							                  <div class="information-list-box"> <span class="information-list-1-span">Departure Date:</span><span class="people-list2 showDate"></span> </div>
							                  <div class="information-list-box"> <span class="information-list-1-span">Return Date:</span><span class="people-list2 showReturnDate"></span> </div>
							                  <div class="clear"></div>
							                </div>
							              </div>	
							              <div class="information-list">
							                <div class="information-list-1">
							                  <div class="information-list-box"> <span class="information-list-1-span">Transfer:</span><span class="people-list2 showTransfer"></span> </div>
							                  <div class="information-list-box"> <span class="information-list-1-span">Airtickets:</span><span class="people-list2 showAirtickets"></span> </div>
							                  <div class="clear"></div>
							                </div>
							              </div>									              						              
							              <div class="information-list">
							                <div class="information-list-1">
							                  <div class="information-list-box "> <span class="information-list-1-span">Adults:</span><span class="showAdults"></span> </div>
							                  <div class="information-list-box"> <span class="information-list-1-span">Children:</span><span class="people-list2 showChildren"></span> </div>
							                  <div class="clear"></div>
							                </div>
							              </div>
							              <div class="information-list">
							                <div class="information-list-1">
							                  <div class="information-list-box"> <span class="information-list-1-span">Infants:</span><span class="showInfants"></span> </div>
							                  <div class="clear"></div>
							                </div>
							              </div>
							            </div>
							            <div class="clear"></div>
							          </div>									  
									  <!-- 联系信息 -->
							          <div class="pay_messages steps_dd pay_un_check pay_bg customerInfo_div" >
							            <div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Contacts Information</span><b class="pay_messages_tit_b"></b></div>
							            <div class="airport_list pay_messages_list_1">
							              <div class="information-list">
							                <div class="information-list-1" style="margin-bottom:5px;">
							                  <div class="information-list-box"> <span class="" style="display:block;">First Name:</span><input name="firstName" maxlength="100" class="people-list2 showFirstName" style="width:100%;"></input> </div>
							                  <div class="information-list-box"> <span class="" style="display:block;">Last Name:</span><input name="lastName" maxlength="100" class="showLastName" style="width:100%;"></input> </div>
							                  <div class="clear"></div>
							                </div>
							              </div>
							              <div class="information-list">
							                <div class="information-list-1" style="margin-bottom:5px;">
							                  <div class="information-list-box"> <span class="" style="display:block;">Email:</span><input name="email" maxlength="100" class="people-list2 showEmail" style="width:100%;"></input> </div>
							                  <div class="information-list-box"> <span class="" style="display:block;">Phone:</span><input name="phone" maxlength="20" class="showPhone" style="width:100%;"></input> </div>
							                  <div class="clear"></div>
							                </div>
							              </div>
							            </div>
							            <div class="clear"></div>
							          </div>
							          <!-- 客人信息 -->
							          <div class="pay_messages steps_dd pay_un_check pay_bg customerInfo_div">
							            <div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Traveler Passport Information</span><b class="pay_messages_tit_b"></b></div>
							            <div id="showPassengerInformationMobile" class="airport_list pay_messages_list_1">
							            </div>
							            <div class="clear"></div>
							          </div>
							          <!-- 住房信息 -->
							          <div class="pay_messages steps_dd pay_un_check pay_bg customerInfo_div">
							            <div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Accommodation Information</span><b class="pay_messages_tit_b"></b></div>
							            <div id="showRoomInformationMobile" class="airport_list pay_messages_list_1">
							 
							            </div>
							            <div class="clear"></div>
							          </div>
							          <!-- 其他特殊要求信息 -->
									<div class="pay_messages steps_dd pay_un_check pay_bg customerInfo_div">
										<div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Special Requirements</span><b class="pay_messages_tit_b"></b></div>
										<div class="airport_list pay_messages_list_1">
										<textarea class="form-control " name="specialrequest" id="specialrequest"  rows="5"  style="background-color: white;border:1px dashed red"></textarea>
										</div>
										<div class="clear"></div>
									</div>
							          <!-- 自费项 -->
							          <div id="optionalTourShow" class="pay_messages steps_dd pay_un_check pay_bg optionalTourInfo_div" style="display:none;">
			                            <div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Additional Services Information</span><b class="pay_messages_tit_b"></b></div>
			                            <div class="airport_list pay_messages_list_1 optionalTourInformation">
			                            
			                            </div>
			                            <div class="clear"></div>
			                          </div>
							          <!-- 自定义产品 -->
							          <div id="customProductShow" class="pay_messages steps_dd pay_un_check pay_bg customerInfo_div" style="display:none;">
			                            <div class="pay_messages_tit pay_messages_steps"><span class="pay_span">Additional Product Information</span><b class="pay_messages_tit_b"></b></div>
			                            <div class="airport_list pay_messages_list_1 additionalProductInformation">
			                            
			                            </div>
			                            <div class="clear"></div>
			                          </div>
							        </div>
							        <!-- 优惠券 -->
							        <h4>Preferential way</h4>
									<div class="information-list-main">
									  <div class="pay_mess_main">
									    <div class="pay_yh" style="display:none;">
									      <div class="yh_left">
									        <p>Integral</p>
									        <select class="pay_yhfs_m">
									          <option value="0">Gate B4, Frankfurt Airport - Terminal 1</option>
									          <option value="1">Gate B4, Frankfurt Airport - Terminal 1</option>
									          <option value="2">Gate B4, Frankfurt Airport - Terminal 1</option>
									        </select>
									      </div>
									      <div class="yh_right">-￥123</div>
									    </div>
									    <div class="pay_yh">
									      <div class="yh_left">
									        <p>Coupon</p>
									        <input type="text" class="pay_jf_m couponInput">
									        <input type="button" onclick="confirmCoupon(this);" class="pay_jf_btn" value="Redeem">
									        <div class="clear"></div>
									      </div>
									      <div class="yh_right couponsDetail"></div>
									    </div>
                                        <div class="pay_yh couponInfo" style="display:none;">
									      <div class="yh_left"> 
									        <p>Security Code</p>
									        <input type="text" name="securityCode" class="pay_jf_m couponInput">									        
									        <div class="clear"></div>
									      </div>
									      <div class="yh_right couponsDetail"></div>
									    </div>
									    <div class="pay_yh couponInfo" style="display:none;">
									      <div class="yh_left">
									        <p>Voucher Code</p>
									        <input type="text" name="voucherCode" class="pay_jf_m couponInput">
									        <div class="clear"></div>
									      </div>
									      <div class="yh_right couponsDetail"></div>
									    </div>									    
									  </div>
									</div>
									<div style="margin-top:20px; text-align:center;">
				                   		<input class="form-block-button" onclick="returnAccommodationInformationMobile();" value="Prev" style="width:47%; background:#bbb;cursor: pointer;"></input>
				                   		<input type="button" class="form-block-button" onclick="submitForm();" value="Check out" style="width:46%; display: inline-block;"></input>
				                    	<div class="clear"></div>
				                    </div> 	
                                </div>    
                            </div>
                         </form>      
					</div>
        		</div>
        	</div>
          	<div id="mobileTourlineDetailTab" class="panel panel-default">
        	</div>
            <div id="mobileItineraryTab" class="panel panel-default">
            </div>
            <div id="mobileDepartureTab" class="panel panel-default">
            </div>
            <div id="mobileGalleryTab" class="panel panel-default">
            </div>            
            <div class="panel panel-default">
            	<div class="panel-heading"><a href="#tourBooking" >Check Out</a></div>
                <div id="undefined-collapse" class="panel-collapse">
                	<div class="panel-body js-tabcollapse-panel-body"></div>
                </div>
            </div>
          </div>
          <!-- .tab-content --> 
        </div>
        <!-- .tour-tabs --> 
      </main>
      <div id="promotionShow" class="price-decoration-label-round" style="display:none;background-color:#ff662a;"><span>Last Minute</span></div>
      <a name="tourBooking"></a>
      <aside class="col-md-3 sidebar" role="complementary">
        <div class="price-decoration block-after-indent">
          <div class="price-decoration-value"> <ins id="priceShow"></ins> <span>One Person</span>
            <div class="clear"></div>
          </div>
        </div>
        <div class="form-block">
          <form method="POST">
          	<table class="form-book-tab" id="allPriceShow" style="display:none;">
          	    <tr>
                	<td class="form-book-tab-left">Base fare</td>
                    <td id="tourPrice" class="form-book-tab-right"></td>
                </tr>
                <tr>
                	<td class="form-book-tab-left">Single Supplements fees</td>
                    <td id="singleSupplementsPrice" class="form-book-tab-right"></td>	
                </tr>
                <tr>
                	<td class="form-book-tab-left">Guides service fees</td>
                    <td id="tipPrice" class="form-book-tab-right"></td>
                </tr>
                <tr>
                	<td class="form-book-tab-left">Optional attractions and performances</td>
                    <td id="steamPrice" class="form-book-tab-right"></td>
                </tr>
                <tr>
                	<td class="form-book-tab-left">Airport transfer fees</td>
                    <td id="transferPrice" class="form-book-tab-right"></td>
                </tr>
                <tr>
                	<td class="form-book-tab-left">Air fares</td>
                    <td id="airPrice" class="form-book-tab-right"></td>
                </tr>
                <tr>
                	<td class="form-book-tab-left">Integral</td>
                	<td id="integral" class="form-book-tab-right"></td>
                </tr>
                <tr>
                	<td class="form-book-tab-left">Coupon</td>
                    <td id="coupon" class="form-book-tab-right"></td>
                </tr>
                <tr>
                	<td class="form-book-tab-left">Total</td>
                    <td class="form-book-tab-right voted"><b id="totalPrice"></b></td>
                </tr>
            </table>
            <div class="form-block-price-details" data-role="price-explanation"></div>
            <input id="submitButton" class="form-block-button" type="button" value="CHECK OUT">
          </form>
        </div>
      </aside>
    </div>
  </div>
</section>
<section id="reviewSection" class="featured-destinations" style="background-color:#fff;">
</section>
<#include "/front/include/staticBottom.ftl"/>
<div style="display:none;">
    <div id="guestInputTemplate" class="form-info-box">
	    <div class="form-info-left">
	        <p class="form-info-num"></p>
	        <p class="form-info-classes"></p>
	        <div class="clear"></div>
	    </div>
	    <table class="table form-info-book">
	        <tr>
	          <td width="50%">
	            <div class="form-block-book-name">
	              <label class="form-block-label label-s">Surname<br>Last Name</label>
	              <input class="form-block-input input-s lastName" maxlength="100" type="text" placeholder=""><img src="${ctx}/assets-web/images/icon_a2.png">
	              <div class="clear"></div>
	            </div>
	          </td>
	          <td width="50%">
	            <div class="form-block-book-name">
	              <label class="form-block-label label-s">Given Name<br>First Name</label>
	              <input class="form-block-input input-s firstName" maxlength="100" type="text" placeholder=""><img src="${ctx}/assets-web/images/icon_a2.png">
	              <div class="clear"></div>
	            </div>
	          </td>
	        </tr>
	        <tr>
	          <td width="50%">
	            <div class="form-block-book-name">
	              <label class="form-block-label label-s">Middle Name</label>
	              <input class="form-block-input input-s middleName" maxlength="100" type="text" placeholder=""><img src="${ctx}/assets-web/images/icon_a2.png">
	              <div class="clear"></div>
	            </div>
	          </td>
	          <td width="50%">
	            <div class="form-block-book-name">
	              <label class="form-block-label label-s label-m">Gender</label>
	              <select class="form-block-input input-s gender">
	                <option value="0">Male</option>
	                <option value="1">Female</option>
	              </select>
	              <div class="clear"></div>
	            </div>
	          </td>
	        </tr>
	        <tr>
	          <td width="50%">
	            <div class="form-block-book-name">
	              <label class="form-block-label label-s label-m">Date of Birth</label>
	              <input class="form-block-input input-s birthday Wdate" type="text" placeholder="MM/dd/yyyy"><img class="login" src="${ctx}/assets-web/images/icon_a2.png">
	              <div class="clear"></div>
	            </div>
	          </td>
	          <td width="50%">
	            <div class="form-block-book-name">
	              <label class="form-block-label label-s label-m">Nationality</label>
	              <input class="form-block-input input-s nationality" value="" type="text" maxlength="30" placeholder=""><!--<img class="login" src="${ctx}/assets-web/images/icon_a2.png">-->
	              <div class="clear"></div>
	            </div>
	          </td>
	        </tr>
	        <tr>
	          <td width="50%">
	            <div class="form-block-book-name">
	              <label class="form-block-label label-s label-m">Passport No.</label>
	              <input class="form-block-input input-s passportNo" value="" type="text" maxlength="30" placeholder=""><img class="login" src="${ctx}/assets-web/images/icon_a2.png">
	              <div class="clear"></div>
	            </div>
	          </td>	    	        
	          <td width="50%">
	            <div class="form-block-book-name">
	              <label class="form-block-label label-s label-m">Date of Expiry</label>
	              <input class="form-block-input input-s passportNoExpiryDate Wdate"  onClick="WdatePicker({skin:'twoer',lang:'en',dateFmt:'MM/dd/yyyy',minDate:'%y-%M-%d'});" value="" type="text" placeholder="MM/dd/yyyy">
	              <img class="login" src="${ctx}/assets-web/images/icon_a2.png">
  	        	  <input class="identityInput" type="hidden"/>
	        	  <input class="roomTypeInput" type="hidden"/>
	        	  <input class="roomNumberInput" type="hidden"/>
	              <div class="clear"></div>
	            </div>
	          </td>
	        </tr>
	        <tr>
            	<input class="identityInput" type="hidden"/>
	        	<input class="roomTypeInput" type="hidden"/>
	        	<input class="roomNumberInput" type="hidden"/>
            </tr>
	    </table>
	    <div class="clear"></div>
	</div>
    <div id="mobileGuestInputTemplate" class="form-info-box">
        <div class="form-info-left">
            <p class="form-info-num"></p>
            <p class="form-info-classes"></p>
        </div>
        <table class="table">
            <tr>
              <td>
                <div class="form-block-book-name">
                  <label class="form-block-label-m">Surname/Last Name</label><img src="${ctx}/assets-web/images/icon_a2.png">
                  <input class="form-block-input-m lastName"  value="" maxlength="100" type="text" placeholder="">
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <div class="form-block-book-name">
                  <label class="form-block-label-m">Given Name/First Name</label><img src="${ctx}/assets-web/images/icon_a2.png">
                  <input class="form-block-input-m firstName"  value="" maxlength="100" type="text" placeholder="">
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <div class="form-block-book-name">
                  <label class="form-block-label-m">Middle Name</label><img src="${ctx}/assets-web/images/icon_a2.png">
                  <input class="form-block-input-m middleName"  value="" maxlength="100" type="text" placeholder="">
                </div>
              </td>
            </tr>            
            <tr>
              <td>
                <div class="form-block-book-name">
                  <label class="form-block-label-m">Gender</label>
                  <select class="form-block-input-m gender">
	                <option value="0">Male</option>
	                <option value="1">Female</option>
                  </select>
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <div class="form-block-book-name">
                  <label class="form-block-label-m ">Date Of Birth</label><img class="login" src="${ctx}/assets-web/images/icon_a2.png">
                  <input class="form-block-input-m birthday Wdate" value="" type="text" placeholder="MM/dd/yyyy">
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <div class="form-block-book-name">
                  <label class="form-block-label-m">Nationality</label><!--<img class="login" src="${ctx}/assets-web/images/icon_a2.png">-->
                  <input class="form-block-input-m nationality" value="" maxlength="30" type="text" placeholder="">
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <div class="form-block-book-name">
                  <label class="form-block-label-m">Passport No.</label><img class="login" src="${ctx}/assets-web/images/icon_a2.png">
                  <input class="form-block-input-m passportNo" value="" maxlength="30" type="text" placeholder="">
                </div>
              </td>
            </tr>
            <tr>
              <td>
                <div class="form-block-book-name">
                  <label class="form-block-label-m">Date of Expiry</label><img class="login" src="${ctx}/assets-web/images/icon_a2.png">
                  <input class="form-block-input-m passportNoExpiryDate Wdate" value="" type="text" placeholder="MM/dd/yyyy" onClick="WdatePicker({skin:'twoer',lang:'en',dateFmt:'MM/dd/yyyy',minDate:'%y-%M-%d'});">
                </div>
              </td>
            </tr>
           
            <tr>
            	<input class="identityInput" type="hidden"/>
	        	<input class="roomTypeInput" type="hidden"/>
	        	<input class="roomNumberInput" type="hidden"/>
            </tr>
        </table>
        <div class="clear"></div>
    </div>
</div>
<div style="display:none;">
    <div id="passengerSelectTemplate" class="people-box passengerOption">
        <div class="people-choose">
          <label class="people-choose-label">Passenger <b class="customerNumber"></b></label>
          <select class="people-choose-select passengerSelect">
          </select>
          <div class="clear"></div>
        </div>
    </div>
    <div id="roomPassengerInfo" class="people-choose-list">
    	<div class="people-choose-list-room left">
        	<span class="people-list1">Room Type:</span><span class="people-list2 roomTypeInfo"></span>
        </div>
        <div class="people-choose-list-name left">
        	<span class="people-list1 left passengerInfo">Passenger:</span>
        </div>
        <div class="people-choose-list-btn right">
        	<button type="button" class="people-list-del" onclick="deleteRoom(this)">DEL</button>
        </div>
    	<div class="clear"></div>
    </div>
    <div id="mobileRoomPassengerInfo" class="people-choose-list">
        <div class="people-choose-list-room">
            <span class="people-list1">Room Type:</span><span class="people-list2 roomTypeInfo"></span>
        </div>
        <div class="people-choose-list-name">
            <span class="people-list1 passengerInfo">Passenger:</span>
        </div>
        <div class="people-choose-list-btn">
            <button type="button" class="people-list-del" onclick="deleteRoom(this)">DEL</button>
        </div>
        <div class="clear"></div>
     </div>    
	<div id="showPassengerInfoTemplate" class="information-list">
		<h4 class="information-list-h4"></h4>
		<div class="information-list-1">
			<div class="information-list-box left">
				<p class="information-list-box-p"><span class="information-list-1-span">Surname/Last Name:</span><span class="people-list2 showSirName"></span></p>
				<p class="information-list-box-p"><span class="information-list-1-span">Middle Name:</span><span class="people-list2 showMiddleName"></span></p>
				<p class="information-list-box-p"><span class="information-list-1-span">Nationality:</span><span class="people-list2 showNationality"></span></p>
				<p class="information-list-box-p"><span class="information-list-1-span">Date of Expiry:</span><span class="people-list2 showExpiryDate"></span></p>
				
			</div>
			<div class="information-list-box left">
				<p class="information-list-box-p"><span class="information-list-1-span">Given Name/First Name:</span><span class="people-list2 showGivenName"></span></p>
				<p class="information-list-box-p"><span class="information-list-1-span">Gender:</span><span class="people-list2 showGender"></span></p>
				<p class="information-list-box-p"><span class="information-list-1-span">Date of Birth:</span><span class="people-list2 showBirthDate"></span></p>
				<p class="information-list-box-p"><span class="information-list-1-span">Passport No.:</span><span class="people-list2 showPassportNo"></span></p>
				
			</div>
			<div class="clear"></div>
		</div>                                                               
	</div>
	<div id="showRoomInfoTemplate" class="information-list">
		<div class="information-list-1">
			<div class="information-list-box left">
				<span class="information-list-1-span">Room Type:</span><span class="people-list2 showRoomType"></span>
			</div>
			<div class="information-list-box left">
				<span class="information-list-1-span">Passenger:</span><span class="showFirstPassenger"></span>
			</div>
			<div class="clear"></div>
		</div>                                                               
	</div>
    <div id="additionalProductShowTemplate" class="information-list">
        <h4 class="information-list-h4"></h4>
        <div class="information-list-1">
            <table cellpadding="0" cellspacing="0" class="custom-info-tab">
            	<tr height="25">
                	<td width="70%">Option</td>
                    <td width="15%">Quantity</td>
                    <td width="15%">Unit Price</td>
                </tr>
                <tr class="singleProduct" height="25">
                	<td class="productName"></td>
                    <td class="quantity"></td>
                    <td class="price"></td>
                </tr>
            </table>
        </div>                                                               
    </div>	
</div>
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
   
   var bgUrl=sessionStorage.getItem("bgUrl");
   $("#sectionHtml").css("background-image",bgUrl);
   
   var windowTourlineDetailHtml = sessionStorage.getItem("windowTourlineDetail");
   $("#tourlineDetailTab").html(windowTourlineDetailHtml);
	
	var windowItineraryHtml = sessionStorage.getItem("windowItinerary");
	$("#itineraryTab").html(windowItineraryHtml);
	
	var windowDepartureHtml = sessionStorage.getItem("windowDeparture");
	$("#departureTab").html(windowDepartureHtml);
	
	var windowGalleryHtml = sessionStorage.getItem("windowGallery");
	$("#galleryTab").html(windowGalleryHtml);
    
    var	mobileTourlineDetailHtml = sessionStorage.getItem("mobileTourlineDetail");
    $("#mobileTourlineDetailTab").html(mobileTourlineDetailHtml);
    
    var mobileItineraryHtml = sessionStorage.getItem("mobileItinerary");
    $("#mobileItineraryTab").html(mobileItineraryHtml);
	
	var mobileGalleryHtml = sessionStorage.getItem("mobileGallery");
	$("#mobileGalleryTab").html(mobileGalleryHtml); 
	
	var mobileDepartureHtml = sessionStorage.getItem("mobileDeparture");
	$("#mobileDepartureTab").html(mobileDepartureHtml);
	
	var downNavigationHtml = sessionStorage.getItem("downNavigation");
	$("#downNavigation").html(downNavigationHtml);
	
	var mobileDownNavigationHtml = sessionStorage.getItem("mobileDownNavigation");
	$("#mobileDownNavigation").html(mobileDownNavigationHtml);
	
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

//   var haveChina = sessionStorage.getItem("haveChina");

	var $windowCustom = $("#windowAdditional");
	var $mobileCustom = $("#mobileCustomTab");
	var itinerarySize = sessionStorage.getItem("itinerarySize");
	var hasAdditionalProduct = false;
    for(var i=0; i<itinerarySize; i++){
    	$windowCustom.append(sessionStorage.getItem("windowItinerary_" + i));
		$mobileCustom.append(sessionStorage.getItem("mobileItinerary_" + i)); 
		hasAdditionalProduct = true;
    }	
    if(hasAdditionalProduct){
        var windowAdditionalButton = '<div style="float:right; margin-top:20px;">'
                	+ '<div class="panel-heading" style="float: left; width:120px;"><input class="form-block-button" type="button" onclick="quitAdditionalProductInformation();" value="Prev" style="text-align:center;width:90px; background:#bbb;"></input></div>'
                	+ '<div class="panel-heading" style="float: left; width:120px;"><input class="form-block-button" type="button" onclick="confirmAdditionalProductInformation();" value="Next" style="text-align:center;width:90px;"></input></div>'
                	+ '<div class="clear"></div>'
                +'</div>';
		var mobileAdditionalButton = '<div style="margin-top:20px; text-align:center;">'
               		+'<input class="form-block-button" onclick="quitAdditionalProductInformationMobile();" value="Prev" style="width:47%; background:#bbb; margin-right:10px;cursor: pointer;"></input>'
               		+'<input class="form-block-button" onclick="confirmAdditionalProductInformationMobile();" value="Next" style="width:47%;cursor: pointer; "></input>'
                	+'<div class="clear"></div>'
                +'</div>';                   
    	$windowCustom.append($(windowAdditionalButton));
    	$mobileCustom.append($(mobileAdditionalButton));	
    }	
    
	var $windowOptionalTour = $("#windowOptionalTour");
	var $mobileOptionalTour = $("#mobileOptionalTourTab");
	var itineraryOptionalTourSize = sessionStorage.getItem("itineraryOptionalTourSize");
	var hasOptionalTour = false;
	for(var i=0; i<itineraryOptionalTourSize; i++){
		$windowOptionalTour.append(sessionStorage.getItem("windowOptionalTourItinerary_" + i));
		$mobileOptionalTour.append(sessionStorage.getItem("mobileOptionalTourItinerary_" + i));
		hasOptionalTour = true;
	}
	$(".special-input").show();
	
	$("[name='optionalbox']").each(function () {  
		$(this).click(function(){
            if(this.checked==true){
                for(var i=0; i<adultsNumber; i++){
                	$(this).closest(".featured-list").find(".button-plus").click();
                }
            }else{
               for(var i=0; i<adultsNumber; i++){
                	$(this).closest(".featured-list").find(".button-off").click();
                }
            }
         });
    });  
            	
	if(hasOptionalTour){
        var windowAdditionalButton = '<div style="float:right; margin-top:20px;">'
                	+ '<div class="panel-heading" style="float: left; width:120px;"><input class="form-block-button" type="button" onclick="quitAdditionalServiceInformation();" value="Prev" style="text-align:center;width:90px; background:#bbb;"></input></div>'
                	+ '<div class="panel-heading" style="float: left; width:120px;"><input class="form-block-button" type="button" onclick="confirmAdditionalServiceInformation();" value="Next" style="text-align:center;width:90px;"></input></div>'
                	+ '<div class="clear"></div>'
                +'</div>';
		var mobileAdditionalButton = '<div style="margin-top:20px; text-align:center;">'
               		+'<input class="form-block-button" onclick="quitAdditionalServiceInformationMobile();" value="Prev" style="width:47%; background:#bbb; margin-right:10px;cursor: pointer;"></input>'
               		+'<input class="form-block-button" onclick="confirmAdditionalServiceInformationMobile();" value="Next" style="width:47%;cursor: pointer; "></input>'
                	+'<div class="clear"></div>'
                +'</div>';   	
		$windowOptionalTour.append($(windowAdditionalButton));
		$mobileOptionalTour.append($(mobileAdditionalButton));
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
	
	var costCode = sessionStorage.getItem("code");
	
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
	
	if($("#mobileDiv").is(":hidden")){
		dateNumber = 21;
		$departureSelect = $("#windowDepartureSelect");	
		$additionalProduct = $("#windowAdditional");
		$transferSelect = $("#windowTransferSelect");
		$airticketsSelect = $("#windowAirticketsSelect");	
	}else{
		isMobile = true;
		dateNumber = 9;
		$departureSelect = $("#mobileDepartureSelect");
		$additionalProduct = $("#mobileCustomTab");	
	    $transferSelect = $("#mobileTransferSelect");
	    $airticketsSelect = $("#mobileAirticketsSelect");
		adjustMobileScroll();
	}
	
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
	
	chooseAdditionalProduct();
	chooseOptionalTour();
	chooseOptionalTourInTourline();
	
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
		showFees();	
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
		showFees();			
	});	
	
	//绑定提交函数
	$("#submitButton").removeAttr("onclick").attr("onclick","submitForm();").hide();
	
	//显示单价
	if($(".voucherNumberInput").val()!=''){
	  $("#priceShow").html(sign + formatPrice(0));
	}else{
	  $("#priceShow").html(sign + formatPrice(sellingPrice));
	}

//	if(haveChina == 'false'){
        //减少自定义产品数量
        $(".button-off").click(function(){
            var productId = $(this).closest(".product").attr("productId");
            var additionalProductInfo = additionalProductFees[productId];

            var $numberInput = $(this).next();
            if($numberInput.val() > 0){
                var nowQuantity = $numberInput.val() - 1;
                $numberInput.val(nowQuantity);
                additionalProductInfo.quantity = additionalProductInfo.quantity - 1;
                additionalProductInfo.totalFee = additionalProductInfo.unitcost * additionalProductInfo.quantity;
                if(nowQuantity == 0){
                    $numberInput.attr('choosed',0);
                    if(additionalProductInfo.quantity == 0){
                        delete additionalProductFees[productId];
                    }
                }
            }
            showFees();
            addOptionalTour();
            addAdditionalProduct();
        });

        //增加自定义产品数量
        $(".button-plus").click(function(){
            var $product = $(this).closest(".product");

            var $numberInput = $(this).prev();
            var nowQuantity = $numberInput.val() * 1 + 1;
            $numberInput.val(nowQuantity);
            $numberInput.attr('choosed',1);

            var productId = $product.attr("productId");
            var unitcost = parseFloat(cancelFormat($product.find("span.amount").attr("price"))) * 1;
            var productName = $product.find("a.productName").html();

            addProduct(productId,productName,unitcost,1);
            showFees();
            addOptionalTour();
            addAdditionalProduct();
        });
 //   }

	//显示价格
	showFees();
});
	//复选额外的产品,再将产品
	var chooseAdditionalProduct = function(){
		var choosedItineraryId;
		var choosedDestinationId;
		var choosedProductId;
		var choosedQuantity;
		
		<#if (shoppingCart.additionalProductList)??>
		<#list shoppingCart.additionalProductList as additionalProductVO>
			choosedItineraryId = '${additionalProductVO.itineryId}';
			choosedDestinationId = '${additionalProductVO.destinationId}';
			choosedProductId = '${additionalProductVO.productId}';
			choosedQuantity = parseInt('${additionalProductVO.quantity}');
			<#if additionalProductVO_index = 0>
				$("#mobileCustomTab .itineraryBodyMobile").hide();
				$(".timeline[itineraryid=" + choosedItineraryId + "]").find(".itineraryTabMobile").next(".itineraryBodyMobile").show();
			</#if>
			$choosedProduct = $(".additionalProduct .timeline[itineraryid=" + choosedItineraryId + "]").find(".destination[destinationid=" + choosedDestinationId + "]").find(".product[productid=" + choosedProductId + "]");
			$choosedProduct.find("input.quantity").val(choosedQuantity).attr("choosed","1");
			
			productName = $choosedProduct.find("a.productName").html();
			unitcost = parseFloat(cancelFormat($choosedProduct.find("span.amount").attr("price"))) * 1;
			addProduct(choosedProductId,productName,unitcost,choosedQuantity);
		</#list>
		</#if>
	}
	
	//复选自费项目
	var chooseOptionalTour = function(){
		var choosedItineraryId;
		var choosedDestinationId;
		var choosedProductId;
		var choosedQuantity;
		
		<#if (shoppingCart.selfPayList)??>
		<#list shoppingCart.selfPayList as additionalProductVO>
			choosedItineraryId = '${additionalProductVO.itineryId}';
			choosedDestinationId = '${additionalProductVO.destinationId}';
			choosedProductId = '${additionalProductVO.productId}';
			choosedQuantity = parseInt('${additionalProductVO.quantity}');
			
			<#if additionalProductVO_index = 0>
				$("#mobileOptionalTourTab .itineraryBodyMobile").hide();
				$(".timeline[itineraryid=" + choosedItineraryId + "]").find(".itineraryTabMobile").next(".itineraryBodyMobile").show();				
			</#if>
			$choosedProduct = $(".optionalTour .selfpayitinerary[itineraryid=" + choosedItineraryId + "]").find(".destination[destinationid=" + choosedDestinationId + "]").find(".product[productid=" + choosedProductId + "]");
			$choosedProduct.find("input.quantity").val(choosedQuantity).attr("choosed","1");
			
			productName = $choosedProduct.find("a.productName").html();
			unitcost = parseFloat(cancelFormat($choosedProduct.find("span.amount").attr("price"))) * 1;
			addProduct(choosedProductId,productName,unitcost,choosedQuantity);
		</#list>
		</#if>
	}
	
	//复选线路下的自费项目
	var chooseOptionalTourInTourline = function(){
		<#if (shoppingCart.selfPayInTourline)??>
		<#list shoppingCart.selfPayInTourline as additionalProductVO>
			choosedProductId = '${additionalProductVO.productId}';
			choosedQuantity = parseInt('${additionalProductVO.quantity}');
			
			$choosedProduct = $(".optionalTour .selfpay").find(".product[productid=" + choosedProductId + "]");
			$choosedProduct.find("input.quantity").val(choosedQuantity).attr("choosed","1");
			
			productName = $choosedProduct.find("a.productName").html();
			unitcost = parseFloat(cancelFormat($choosedProduct.find("span.amount").attr("price"))) * 1;
			addProduct(choosedProductId,productName,unitcost,choosedQuantity);
		</#list>
		</#if>
	}
	
	//选择日期
	var chooseDate = function(startTime,singleRoomPriceInput,sellingPriceInput,childPriceInput,babyPriceInput,tourdateId,tourPriceId){
	    tabIsLocked = false;
	    sellingPrice = sellingPriceInput;
		singleroomprice = singleRoomPriceInput;
		childPrice = childPriceInput;
		babyPrice = babyPriceInput;
		var $departureInput;
	    
	    //当是桌面版时
		if(!isMobile){
			$departureInput = $("#windowDepartureDateInput");
		//当是移动端时	
		}else{
			$departureInput = $("#mobileDepartureDateInput");
		}
		
		//如果出发日期发生变化
		if(!($departureInput.val() == startTime && !tabIsLocked)){
			$departureInput.val(startTime);
			
			if($(".voucherNumberInput").val()!=''){
			  $("#priceShow").html(sign + formatPrice(0));
			}else{
			  $("#priceShow").html(sign + formatPrice(sellingPrice));
			}
			//$("#priceShow").html(sign + formatPrice(sellingPrice));	
			$(".tourPriceIdInput").val(tourPriceId);
			
		    // 异步获取机票价格选项 
		    $.ajax({
		        async: false,
		        type : "POST",
		        url : '${ctx!}/front/tourlineDetails/getAirTicketPrices.do',
		        data: {"tourPriceId":tourPriceId},
		        success : function(result) {
					$airticketsSelect.empty();
					$airticketsSelect.append("<option value=''>-- No Airtickets --</option>");
					$.each(result,function(index,flightTickPrice){  
						var $flightTicksOption = '<option value="'+ flightTickPrice.id +'" price="'+ flightTickPrice.price +'">' + flightTickPrice.departureName + ' '+ sign + flightTickPrice.price + '</option>';
						$airticketsSelect.append($flightTicksOption);
					});
					if($airticketsSelect.find("option").size() > 1){
						$airticketsSelect.parent().show();
					}else{
						$airticketsSelect.parent().hide();
					}
		        }
		    });
		    
		    $(".showDate").html(startTime);
		    $(".showReturnDate").html(getReturnDateString(startTime));
			
			perAirTicketsPrice = 0;
			airTicketsFee = 0;
			airticketsText = '';
			showOrHideTransferAndAirtickets();
			
			$(".showDepartureGateWay").html(chooseDepartureInfo);
			showFees();
		}
		$(".departureDateInput").val(startTime);
	}
		
	//确认订购基础信息(step 1 ~ step 2)
	var confirmBookingInformation = function(){
		var $firstNameInput = $("#windowFirstName");
		var $lastNameInput = $("#windowLastName");
		var $emailInput = $("#windowEmail");
		var $phoneInput = $("#windowPhone");
		var $adultsNumber = $("#windowAdultsNumber");
		var $childrenNumber = $("#windowChildrenNumber");
		var $infantsNumber = $("#windowInfantsNumber");
		
		//name空值校验
		if($lastNameInput.val().trim() == ''){
			alertWarn('Please fill in your surname.');
			addWarnningShow($lastNameInput);
			return;
		}
		if($firstNameInput.val().trim() == ''){
			alertWarn('Please fill in your given name.');
			addWarnningShow($firstNameInput);
			return;
		}
		//email空值校验
		if($emailInput.val().trim() == ''){
			alertWarn('Please fill in the email.');
			addWarnningShow($emailInput);
			return;
		}
		if(!reg.test($emailInput.val().trim())){
			alertWarn('Please enter the valid email.');
			addWarnningShow($emailInput);
			return;
		}
		//电话空值检验
		if($phoneInput.val().trim() == ''){
			alertWarn('Please fill in the phone number.');
			addWarnningShow($phoneInput);
			return;
		}
		//电话格式校验
		if(!numberRule.test($phoneInput.val().trim())){
			alertWarn('The phone No. must be made up of 8 to 12 digits.');
			addWarnningShow($phoneInput);
			return false;	
		}
		
		//大人数量校验
		if($adultsNumber.val().trim() == '' || !numberRul.test($adultsNumber.val().trim())){
			alertWarn('The adults number must be made up of digits.');
			addWarnningShow($adultsNumber);
			return false;			
		}
		//儿童人数校验
		if($childrenNumber.val().trim() == '' || !numberRul.test($childrenNumber.val().trim())){
			alertWarn('The children number must be made up of digits.');
			addWarnningShow($childrenNumber);
			return false;		
		}
		//婴儿人数校验		
		if($infantsNumber.val() == '' || !numberRul.test($infantsNumber.val().trim())){
			alertWarn('The infant number must be made up of digits.');
			addWarnningShow($infantsNumber);
			return false;		
		}
		
		adultsNumber = $adultsNumber.val() * 1;
		childrenNumber = $childrenNumber.val() * 1;
		infantsNumber = $infantsNumber.val() * 1;
		totalNumber = adultsNumber + childrenNumber + infantsNumber;
		
		showFees();
		
		<#-- 如果总人数匹配  -->
		$(".bookingInformation").hide();
		$(".passengerInformation").show();
		$(".1step").removeClass("tab-step-num-active");
		$(".2step").addClass("tab-step-num-active");
		step = 2;
		
		var $guestRoomInfo = $(".guestRoomInfo");
		$guestRoomInfo.empty();
		<#--将联系人信息设置为第一位客人  -->
		var $guestInfoInputFirst = $("#guestInputTemplate").clone(true).removeAttr("id");
		$guestInfoInputFirst.find("p.form-info-num").html('No.' + 1);
		$guestInfoInputFirst.find("p.form-info-classes").html('ADULT');
		$guestInfoInputFirst.find(".birthday").attr("onclick","WdatePicker({skin:'twoer',lang:'en',dateFmt:'MM/dd/yyyy',startDate:'{%y-20}-01-01',maxDate:'{%y-11}-12-31'});");	
		$guestInfoInputFirst.find(".lastName").val($lastNameInput.val());
		$guestInfoInputFirst.find(".firstName").val($firstNameInput.val());
		setPassengerNames($guestInfoInputFirst,0,0);
		$guestRoomInfo.append($guestInfoInputFirst);
		
		for(var i=2; i<=adultsNumber; i++){
			var $guestInfoInput = $("#guestInputTemplate").clone(true).removeAttr("id");
			$guestInfoInput.find("p.form-info-num").html('No.' + i);
			$guestInfoInput.find("p.form-info-classes").html('ADULT');
			$guestInfoInput.find(".birthday").attr("onclick","WdatePicker({skin:'twoer',lang:'en',dateFmt:'MM/dd/yyyy',startDate:'{%y-20}-01-01',maxDate:'{%y-11}-12-31'});");	
			setPassengerNames($guestInfoInput,i-1,0);
			$guestRoomInfo.append($guestInfoInput);
		}
		for(var i=adultsNumber+1; i<=adultsNumber + childrenNumber; i++){
			var $guestInfoInput = $("#guestInputTemplate").clone(true).removeAttr("id");
			$guestInfoInput.find("p.form-info-num").html('No.' + i);
			$guestInfoInput.find("p.form-info-classes").html('CHILDREN');
			$guestInfoInput.find(".birthday").attr("onclick","WdatePicker({skin:'twoer',lang:'en',dateFmt:'MM/dd/yyyy',startDate:'{%y-10}-01-01',minDate:'{%y-10}-01-01',maxDate:'{%y-2}-12-31'});");	
			setPassengerNames($guestInfoInput,i-1,1);
			$guestRoomInfo.append($guestInfoInput);
		}
		for(var i=adultsNumber + childrenNumber + 1; i<=totalNumber; i++){
			var $guestInfoInput = $("#guestInputTemplate").clone(true).removeAttr("id");
			$guestInfoInput.find("p.form-info-num").html('No.' + i);
			$guestInfoInput.find("p.form-info-classes").html('INFANTS');
			$guestInfoInput.find(".birthday").attr("onclick","WdatePicker({skin:'twoer',lang:'en',dateFmt:'MM/dd/yyyy',startDate:'{%y-1}-01-01',minDate:'{%y-1}-01-01',maxDate:'%y-%M-%d'});");					
			setPassengerNames($guestInfoInput,i-1,2);
			$guestRoomInfo.append($guestInfoInput);
		}
		adjustWindowScroll();
		$("#guestDetails").find(".ignorePassportDiv").remove().end().find(".guestRoomInfo").before($('<div class="ignorePassportDiv" style="display:none"><input type="checkbox" onchange="ignorePassport(this);"></input>  I don\'t have passport with me at this moment(you can fill this in later in "My Account")</div>'));
		$("img.login").show();
		ignorePassportStatus = false;
		return false;		
	}
	
	//返回订购基础信息(step 2 ~ step 1)
	var returnBookingInformation = function(){
		if(confirm('Are you sure to reture? You will lost guest information you entered!')){
			$(".passengerInformation").hide();
			$(".bookingInformation").show();
			step = 1;
			adjustWindowScroll();			
		}
	}
	
	//确认客人护照信息(step 2 ~ step 3)
	var confirmCustomerInformation = function(){
		var $guestRoomInfo = $(".guestRoomInfo");
		var isError = false;
		
		passengerInformation = new Array();
	    $guestRoomInfo.find(".form-info-box").each(function(index){
	    	var $sirName = $(this).find("input.lastName");
	    	var $givenName = $(this).find("input.firstName");
	    	var $middleName = $(this).find("input.middleName");
	    	var $birthday = $(this).find("input.birthday");
	    	var $nationality = $(this).find("input.nationality");
	    	var $passport = $(this).find("input.passportNo");
	    	var $passportExpiry = $(this).find("input.passportNoExpiryDate");
	    	
	    	if($sirName.val().trim() == ''){
	    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Surname.');
	    		addWarnningShow($sirName);
	    		isError = true;
	    		return false;
	    	}
	    	if($givenName.val().trim() == ''){
	    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Given name.');
	    		addWarnningShow($givenName);
	    		isError = true;
	    		return false;
	    	}
	    	if($middleName.val().trim() == ''){
	    		alertWarn('Please input N/A if you don’t have middle name.');
	    		addWarnningShow($middleName);
	    		isError = true;
	    		return false;
	    	}
	    	/*
	       	if (!ignorePassportStatus) {
		       	if($birthday.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Birthday.');
		    		addWarnningShow($birthday);
		    		isError = true;
		    		return false;
		    	}
		    	if($nationality.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Nationality.');
		    		addWarnningShow($nationality);
		    		isError = true;
		    		return false;
		    	}
		    	if($passport.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Passport No.');
		    		addWarnningShow($passport);
		    		isError = true;
		    		return false; 
		    	}
		    	if($passportExpiry.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Date of Expiry.');
		    		addWarnningShow($passportExpiry);
		    		isError = true;
		    		return false;
		    	}
			}
			*/
			if($birthday.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Birthday.');
		    		addWarnningShow($birthday);
		    		isError = true;
		    		return false;
		    	}
			if($passport.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Passport No.');
		    		addWarnningShow($passport);
		    		isError = true;
		    		return false; 
		    	}
		    	if($passportExpiry.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Date of Expiry.');
		    		addWarnningShow($passportExpiry);
		    		isError = true;
		    		return false;
		    	}
			var identify = $(this).find("input.identityInput").val();
	    	if(identify == 'ADULT' || identify == 'CHILDREN'){
	    		passengerInformation.push(identify + '[*]' + $givenName.val() + ' ' + $sirName.val());
	    	}
	    });
	    if(isError){
	    	return;
	    }
		$(".passengerInformation").hide();
		$(".accommodation").show();
		$(".2step").removeClass("tab-step-num-active");
		$(".3step").addClass("tab-step-num-active");
		step = 3;
		
		//隐藏选择客人选择房间的DIV
		$("table.accommodation").find("div.choose-main").hide();
		adjustWindowScroll();
	}
	
	//返回客人护照信息(step 3 ~ step 2)
	var returnCustomerInformation = function(){
		if(confirm('Are you sure to reture? You will lost room information you selected!')){
			$(".accommodation").hide();
			$(".passengerInformation").show();
			step = 2;
			//清空房间类型和客人的展示
			$("#roomPassengerInfoDiv").empty();
			//重置已选择客人的数量
			choosedRoomPeopleNumber = 0;
			$(".addRoomButton").show();
			adjustWindowScroll();
		}
	}
	
	//确认客人住房信息(step 3 ~ step 4(step 5,step 6))
	var confirmAccommodationInformation = function(){
		var $chooseDiv = $("#roomChoosePassenger");
		
	    //如果填写房型和为客人分房没有结束或者还有客人没有分得房间则提示用户
		if((!$chooseDiv.is(":hidden")) || (!$chooseDiv.prev().find(".addRoomButton").is(":hidden"))){
			alertWarn('Please complete your room information.');
			return;		
		}
		
		singleRoomNumber = 0;
		$("#roomPassengerInfoDiv").find(".people-choose-list").each(function(index){
			var roomType = $(this).find("span.roomTypeInfo").html();
			if(roomType == "Single Room"){
				singleRoomNumber++;
			}	
		});		
		showFees();
		
		$(".accommodation").hide();
		if(hasOptionalTour){
			$(".additionalServices").show();
			$(".3step").removeClass("tab-step-num-active");
			$(".4step").addClass("tab-step-num-active");
			$("#bookTitle").html("Additional Services");
			step = 4;
		}else if(hasAdditionalProduct){
			$(".additionalProducts").show();
			$(".3step").removeClass("tab-step-num-active");
			$(".4step").addClass("tab-step-num-active");
			$("#bookTitle").html("Additional Products");
			step = 5;		
		}else{
			showTotalInfo();			
			$(".confirmInformation").show();
			$(".3step").removeClass("tab-step-num-active");
			$(".4step").addClass("tab-step-num-active");
			$("#bookTitle").html("Information Summary");
			step = 6;
		}
		adjustWindowScroll();
	}


	//返回客人住房信息(step 4 ~ step 3)
	var quitAdditionalServiceInformation = function(){
		$(".additionalServices").hide();
		$(".accommodation").show();
		adjustWindowScroll();
	}
	
	//确认自费项目信息(step 4 ~ step 5(step 6))
	var confirmAdditionalServiceInformation = function(){
		$(".additionalServices").hide();
		if(hasAdditionalProduct){
			$(".additionalProducts").show();
			step = 5;
		}else{
			showTotalInfo();
			$(".confirmInformation").show();
			step = 6;
		}
		adjustWindowScroll();
	}
	
	//返回自费项目信息(step 5 ~ step 4(step 3))
	var quitAdditionalProductInformation = function(){
		$(".additionalProducts").hide();	
		if(hasOptionalTour){
			$(".additionalServices").show();	
			step = 4;
		}else{
			$(".accommodation").show();
			step = 3;
		}
		adjustWindowScroll();
	}
	
	//确认订制产品信息(step 5 ~ step 6)
	var confirmAdditionalProductInformation = function(){
		$(".additionalProducts").hide();
		showTotalInfo();
		$(".confirmInformation").show();
		step = 6;
		adjustWindowScroll();
	}	
	
	//返回客人住房信息(step 6 ~ step 5(step 4,step 3))
	var returnAccommodationInformation = function(){
		$(".confirmInformation").hide();
		$("#allPriceShow").hide();
		$("#submitButton").hide();
		if(hasAdditionalProduct){
			$(".additionalProducts").show();	
			step = 5;	
		}else if(hasOptionalTour){
			$(".additionalServices").show();
			step = 4;
		}else{
			$(".accommodation").show();
			step = 3;
		}
		adjustWindowScroll();	
	}	
	
	//确认订购基础信息(step 1 ~ step 2)(mobile)
	var confirmBookingInformationMobile = function(){
        if(mobileCheckDepartureDate()){
			return;        	
        }
		var $firstNameInput = $("#mobileFirstName");
		var $lastNameInput = $("#mobileLastName");
		var $emailInput = $("#mobileEmail");
		var $phoneInput = $("#mobilePhone");
		var $adultsNumber = $("#mobileAdultsNumber");
		var $childrenNumber = $("#mobileChildrenNumber");
		var $infantsNumber = $("#mobileInfantsNumber");
		
		//name空值校验
		if($lastNameInput.val().trim() == ''){
			alertWarn('Please fill in your surname.');
			addWarnningShow($lastNameInput);
			return;
		}
		if($firstNameInput.val().trim() == ''){
			alertWarn('Please fill in your given name.');
			addWarnningShow($firstNameInput);
			return;
		}
		//email空值校验
		if($emailInput.val().trim() == ''){
			alertWarn('Please fill in the email.');
			addWarnningShow($emailInput);
			return;
		}
		if(!reg.test($emailInput.val().trim())){
			alertWarn('Please enter the valid email.');
			addWarnningShow($emailInput);
			return;
		}
		//电话空值检验
		if($phoneInput.val().trim() == ''){
			alertWarn('Please fill in the phone number.');
			addWarnningShow($phoneInput);
			return;
		}
		//电话格式校验
		if(!numberRule.test($phoneInput.val().trim())){
			alertWarn('The phone No. must be made up of 8 to 12 digits.');
			addWarnningShow($phoneInput);
			return false;	
		}
		//大人数量校验
		if($adultsNumber.val().trim() == '' || !numberRul.test($adultsNumber.val().trim())){
			alertWarn('The adults number must be made up of digits.');
			addWarnningShow($adultsNumber);
			return false;			
		}
		//儿童人数校验
		if($childrenNumber.val().trim() == '' || !numberRul.test($childrenNumber.val().trim())){
			alertWarn('The children number must be made up of digits.');
			addWarnningShow($childrenNumber);
			return false;		
		}
		//婴儿人数校验		
		if($infantsNumber.val().trim() == '' || !numberRul.test($infantsNumber.val().trim())){
			alertWarn('The infant number must be made up of digits.');
			addWarnningShow($infantsNumber);
			return false;		
		}
		
		adultsNumber = $adultsNumber.val() * 1;
		childrenNumber = $childrenNumber.val() * 1;
		infantsNumber = $infantsNumber.val() * 1;
		totalNumber = adultsNumber + childrenNumber + infantsNumber; 
		
		showFees();
		
		<#-- 如果总人数匹配  -->
		$(".mobileBookingInformation").hide();
		$(".mobilePassengerInformation").show();
		step = 2;
		
		var $guestRoomInfo = $(".mobileGuestRoomInfo");
		$guestRoomInfo.empty();
		<#-- 将联系人信息设置为第一位客人  -->
		var $guestInfoInputFirst = $("#mobileGuestInputTemplate").clone(true).removeAttr("id");
		$guestInfoInputFirst.find("p.form-info-num").html('No.' + 1);
		$guestInfoInputFirst.find("p.form-info-classes").html('ADULT');
		$guestInfoInputFirst.find(".birthday").attr("onclick","WdatePicker({skin:'twoer',lang:'en',dateFmt:'MM/dd/yyyy',startDate:'{%y-20}-01-01',maxDate:'{%y-11}-12-31'});");	
		$guestInfoInputFirst.find(".lastName").val($lastNameInput.val());
		$guestInfoInputFirst.find(".firstName").val($firstNameInput.val());
		setPassengerNames($guestInfoInputFirst,0,0);
		$guestRoomInfo.append($guestInfoInputFirst);
			
		for(var i=2; i<=adultsNumber; i++){
			var $guestInfoInput = $("#mobileGuestInputTemplate").clone(true).removeAttr("id");
			$guestInfoInput.find("p.form-info-num").html('No.' + i);
			$guestInfoInput.find("p.form-info-classes").html('ADULT');
			$guestInfoInput.find(".birthday").attr("onclick","WdatePicker({skin:'twoer',lang:'en',dateFmt:'MM/dd/yyyy',startDate:'{%y-20}-01-01',maxDate:'{%y-11}-12-31'});");	
			setPassengerNames($guestInfoInput,i-1,0);
			$guestRoomInfo.append($guestInfoInput);
		}
		for(var i=adultsNumber+1; i<=adultsNumber + childrenNumber; i++){
			var $guestInfoInput = $("#mobileGuestInputTemplate").clone(true).removeAttr("id");
			$guestInfoInput.find("p.form-info-num").html('No.' + i);
			$guestInfoInput.find("p.form-info-classes").html('CHILDREN');
			$guestInfoInput.find(".birthday").attr("onclick","WdatePicker({skin:'twoer',lang:'en',dateFmt:'MM/dd/yyyy',startDate:'{%y-10}-01-01',minDate:'{%y-10}-01-01',maxDate:'{%y-2}-12-31'});");	
			setPassengerNames($guestInfoInput,i-1,1);
			$guestRoomInfo.append($guestInfoInput);
		}
		for(var i=adultsNumber + childrenNumber + 1; i<=totalNumber; i++){
			var $guestInfoInput = $("#mobileGuestInputTemplate").clone(true).removeAttr("id");
			$guestInfoInput.find("p.form-info-num").html('No.' + i);
			$guestInfoInput.find("p.form-info-classes").html('INFANTS');
			$guestInfoInput.find(".birthday").attr("onclick","WdatePicker({skin:'twoer',lang:'en',dateFmt:'MM/dd/yyyy',startDate:'{%y-1}-01-01',minDate:'{%y-1}-01-01',maxDate:'%y-%M-%d'});");					
			setPassengerNames($guestInfoInput,i-1,2);
			$guestRoomInfo.append($guestInfoInput);
		}
		adjustMobileScroll();
		$("#mobileGuestDetails").find(".ignorePassportDiv").remove().end().find(".mobileGuestRoomInfo").before($('<div class="ignorePassportDiv" style="display:none"><input type="checkbox" onchange="ignorePassport(this);"></input>  I don\'t have passport with me at this moment(you can fill this in later in "My Account")</div>'));
		$("img.login").show();
		ignorePassportStatus = false;
		return false;		
	}	
	
	//返回订购基础信息(step 2 ~ step 1)(mobile)
	var returnBookingInformationMobile = function(){
		if(confirm('Are you sure to reture? You will lost guest information you entered!')){
			$(".mobilePassengerInformation").hide();
			$(".mobileBookingInformation").show();
			step = 1;
			adjustMobileScroll();		
		}
	}
	
	//确认客人护照信息(step 2 ~ step 3)(mobile)
	var confirmCustomerInformationMobile = function(){
		if(mobileCheckDepartureDate()){
			return;        	
        }
		var $guestRoomInfo = $(".mobileGuestRoomInfo");
		var isError = false;
		
		passengerInformation = new Array();
	    $guestRoomInfo.find(".form-info-box").each(function(index){
	    	var $sirName = $(this).find("input.lastName");
	    	var $givenName = $(this).find("input.firstName");
	    	var $middleName = $(this).find("input.middleName");
	    	var $birthday = $(this).find("input.birthday");
	    	var $nationality = $(this).find("input.nationality");
	    	var $passport = $(this).find("input.passportNo");
	    	var $passportExpiry = $(this).find("input.passportNoExpiryDate");
	    	
	    	if($sirName.val().trim() == ''){
	    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Surname.');
	    		addWarnningShow($sirName);
	    		isError = true;
	    		return false;
	    	}
	    	if($givenName.val().trim() == ''){
	    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Given name.');
	    		addWarnningShow($givenName);
	    		isError = true;
	    		return false;
	    	}
	    	if($middleName.val().trim() == ''){
	    		alertWarn('Please input N/A if you don’t have middle name.');
	    		addWarnningShow($middleName);
	    		isError = true;
	    		return false;
	    	}
	    	/*
	    	if (!ignorePassportStatus) {
		       	if($birthday.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Birthday.');
		    		addWarnningShow($birthday);
		    		isError = true;
		    		return false;
		    	}
		    	if($nationality.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Nationality.');
		    		addWarnningShow($nationality);
		    		isError = true;
		    		return false;
		    	}
		    	if($passport.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Passport No.');
		    		addWarnningShow($passport);
		    		isError = true;
		    		return false; 
		    	}
		    	if($passportExpiry.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Date of Expiry.');
		    		addWarnningShow($passportExpiry);
		    		isError = true;
		    		return false;
		    	}
	    	}
	    	*/
	    	if($birthday.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Birthday.');
		    		addWarnningShow($birthday);
		    		isError = true;
		    		return false;
		    	}
	    	if($passport.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Passport No.');
		    		addWarnningShow($passport);
		    		isError = true;
		    		return false; 
		    	}
		    	if($passportExpiry.val().trim() == ''){
		    		alertWarn('Please fill in No.'+ (index + 1)  +'\'s Date of Expiry.');
		    		addWarnningShow($passportExpiry);
		    		isError = true;
		    		return false;
		    	}
	    	var identify = $(this).find("input.identityInput").val();
	    	if(identify == 'ADULT' || identify == 'CHILDREN'){
	    		passengerInformation.push(identify + '[*]' + $givenName.val() + ' ' + $sirName.val());
	    	}
	    });
	    if(isError){
	    	return;
	    }
		$(".mobilePassengerInformation").hide();
		$(".mobileAccommodation").show();
		step = 3;
		
		//隐藏选择客人选择房间的DIV
		$("table.mobileAccommodation").find("div.choose-main").hide();
		adjustMobileScroll();	
	}
	
	//返回客人护照信息(step 3 ~ step 2)(mobile)
	var returnCustomerInformationMobile = function(){
		if(confirm('Are you sure to reture? You will lost room information you selected!')){
			$(".mobileAccommodation").hide();
			$(".mobilePassengerInformation").show();
			step = 2;
			//清空房间类型和客人的展示
			$("#mobileRoomPassengerInfoDiv").empty();
			//重置已选择客人的数量
			choosedRoomPeopleNumber = 0;
			$(".mobileAddRoomButton").show();
			adjustMobileScroll();
		}
	}
	
	//确认客人住房信息(step 3 ~ step 4)(mobile)
	var confirmAccommodationInformationMobile = function(){
		if(mobileCheckDepartureDate()){
			return;        	
        }
		var $chooseDiv = $("#mobileRoomChoosePassenger");
		
	    //如果填写房型和为客人分房没有结束或者还有客人没有分得房间则提示用户
		if((!$chooseDiv.is(":hidden")) || (!$chooseDiv.prev().find(".addRoomButton,.mobileAddRoomButton").is(":hidden"))){
			alertWarn('Please complete your room information.');
			return;		
		}
		singleRoomNumber = 0;
		$("#mobileRoomPassengerInfoDiv").find(".people-choose-list").each(function(index){
			var roomType = $(this).find("span.roomTypeInfo").html();
			if(roomType == "Single Room"){
				singleRoomNumber++;
			}	
		});	
		showFees();	
		
		$(".mobileAccommodation").hide();
		if(hasOptionalTour){
			$(".mobileAdditionalServices").show();	
			step = 4;		
		}else if(hasAdditionalProduct){
			$(".mobileAdditionalProducts").show();
			step = 5;
		}else{
			showTotalInfoMobile();
			$(".mobileConfirmInformation").show();
			step = 6;
		}	
		adjustMobileScroll();	
	}
	
	//返回客人住房信息(step 4 ~ step 3)
	var quitAdditionalServiceInformationMobile = function(){
		$(".mobileAdditionalServices").hide();
		$(".mobileAccommodation").show();
		adjustMobileScroll();
	}
	
	//确认自费项目信息(step 4 ~ step 5(step 6))
	var confirmAdditionalServiceInformationMobile = function(){
		$(".mobileAdditionalServices").hide();
		if(hasAdditionalProduct){
			$(".mobileAdditionalProducts").show();
			step = 5;
		}else{
			showTotalInfoMobile();
			$(".mobileConfirmInformation").show();
			step = 6;
		}
		adjustMobileScroll();
	}
	
	//返回自费项目信息(step 5 ~ step 4(step 3))
	var quitAdditionalProductInformationMobile = function(){
		$(".mobileAdditionalProducts").hide();	
		if(hasOptionalTour){
			$(".mobileAdditionalServices").show();	
			step = 4;
		}else{
			$(".mobileAccommodation").show();
			step = 3;
		}
		adjustMobileScroll();
	}   
	
	//确认订制产品信息(step 5 ~ step 6)
	var confirmAdditionalProductInformationMobile = function(){
		$(".mobileAdditionalProducts").hide();
		showTotalInfoMobile();
		$(".mobileConfirmInformation").show();
		step = 6;
		adjustMobileScroll();
	}	
	
	//返回客人住房信息(step 6 ~ step 5(step 4,step 3))
	var returnAccommodationInformationMobile = function(){
		$("#allPriceShow").hide();
		$("#submitButton").hide();
		$(".mobileConfirmInformation").hide();
		if(hasAdditionalProduct){
			$(".mobileAdditionalProducts").show();	
			step = 5;
		}else if(hasOptionalTour){
			$(".mobileAdditionalServices").show();
			step = 4;
		}else{
			$(".mobileAccommodation").show();
			step = 3;
		}
		adjustMobileScroll();	
	}	
	
	//客人添加房间
	var addRoom = function(button,isPass){
		var $chooseDiv = $(button).parent().next();
		if(isPass != 1){
			if(!$chooseDiv.is(":hidden")){
				return;
			}
		}
	
		var $roomTypeSelect = $chooseDiv.find("select.roomTypeSelect");
		
		//获取等待被分配房间的客人数量
		var waitChooseRoomNumber = adultsNumber + childrenNumber - choosedRoomPeopleNumber;
					
		//清理房型select的选项
		$roomTypeSelect.empty();
		
		//为为房型select添加选项
		if(waitChooseRoomNumber >= 1){
			$roomTypeSelect.append('<option value="1">Single Room</option>');
		}
		if(waitChooseRoomNumber >= 2){
			$roomTypeSelect.append('<option value="2">Double Room</option>');
			$roomTypeSelect.append('<option value="2">Twin Room</option>');
		}
		if(waitChooseRoomNumber >= 3){
			$roomTypeSelect.append('<option value="3">Triple Room</option>');
		}
		
		$roomTypeSelect.change(function(){
			$chooseDiv = $(this).closest(".choose-main");
			//保存房间信息的按钮
			var $saveRoomDiv = $chooseDiv.find(".saveRoomDiv");				
			//清除所有的客人select
			$chooseDiv.find("div.passengerOption").remove();
			
			//房型所选房型容纳的人数
			var passengerNumber = $(this).val();	
			
			//根据房型容纳人数添加客人select
			for(var i=0;i<passengerNumber;i++){
				var $passengerSelectDiv = $("#passengerSelectTemplate").clone(true).removeAttr("id");
				$passengerSelectDiv.find("b.customerNumber").html(i + 1);
				
				var $passengerSelect = $passengerSelectDiv.find("select");
				$passengerSelect.append('<option value="0">-- Select --<option>');
				$passengerSelect.attr('chooseValue',"0");
				
				//修复执行中出现的莫名的bug
				$passengerSelect.find("option").eq(1).remove();
				
				for(var j=0; j<passengerInformation.length;j++){
				    var pInfos = passengerInformation[j].split('[*]');	
					var pIdentify = pInfos[0];
					var passengerName = pInfos[1];			    	
					$passengerSelect.append('<option identify="'+ pIdentify +'" value="'+ passengerName +'">' + passengerName + '</option>');
				}
				$passengerSelect.change(function(){
					$chooseDiv = $(this).closest(".choose-main");
					$(this).addClass('current');
					
					//退出选中前的option
					var beforeChooseValue = $(this).attr("chooseValue");
					var beforeChooseIdentify = $(this).attr("chooseIdentify");
					if(beforeChooseValue != '0'){
						$chooseDiv.find("select.passengerSelect:not('.current')").append('<option identify="'+ beforeChooseIdentify +'" value="'+ beforeChooseValue +'">'+ beforeChooseValue +'</option>');
					}
					
					//删除选中的option
					var $chooseOption = $(this).find("option:selected");
					var choooseValue = $chooseOption.attr('value');
					var chooseIdentify = $chooseOption.attr('identify');
					if(choooseValue != '0'){
						$chooseDiv.find("select.passengerSelect:not('.current')").each(function(){
							$(this).find('option[value="'+ choooseValue +'"]:not(":selected")').eq(0).remove();
						});
					}
					$(this).removeClass('current');
					$(this).attr("chooseValue",choooseValue);
					$(this).attr("chooseIdentify",chooseIdentify);
				});
				$saveRoomDiv.before($passengerSelectDiv);
			}	
		});
		$roomTypeSelect.change();
		$chooseDiv.slideDown();
		adjustScrollDuringRoom();
	}
	
	//保存房间住客信息
	var saveRoom = function(button){
		var $chooseDiv = $(button).closest(".choose-main");
		var $passengerSelect = $chooseDiv.find("select.passengerSelect");
		
		//如果选择房型时没有选择客人信息时
		var isAlert = false;
		var hasAdult = false;
		$passengerSelect.each(function(){
			var $passengerOption = $(this).find("option:selected");
			var identity = $passengerOption.attr('identify');
			if(identity == 'ADULT'){
				hasAdult = true;	
			}
			if($(this).val() == '0'){
				isAlert = true;
				alertWarn('Please designate your rooms.');				
				return false;
			}
		});
		if(isAlert){
			return;
		}
		if(!hasAdult){
			alertWarn('Min. 1 Adult Per Room.');	
			return;	
		}
		//收拢房型客人信息输入框
		$chooseDiv.slideUp();
		
		//生成房间客人信息显示DIV
		var $roomPassengerDiv;
		var $roomPassengerInfoDiv;
		
		if(!isMobile){
			$roomPassengerDiv  = $("#roomPassengerInfo").clone(true).removeAttr("id");
			$roomPassengerInfoDiv = $("#roomPassengerInfoDiv");
		}else{
			$roomPassengerDiv = $("#mobileRoomPassengerInfo").clone(true).removeAttr("id");
			$roomPassengerInfoDiv = $("#mobileRoomPassengerInfoDiv");
		}
		
		//给房间展示信息添加房型信息
		var $chooseRoomTypeSelect = $chooseDiv.find("select.roomTypeSelect").find("option:selected");
		var chooseRoomType = $chooseRoomTypeSelect.html();
		$roomPassengerDiv.find("span.roomTypeInfo").html(chooseRoomType).data("roomPassengerNumber",$chooseRoomTypeSelect.val());
		
		//增加选择房间的客人数量
		choosedRoomPeopleNumber += $chooseRoomTypeSelect.val() * 1;
				
		if(choosedRoomPeopleNumber == adultsNumber + childrenNumber){
			$chooseDiv.prev().find(".addRoomButton,.mobileAddRoomButton").fadeOut();
		}
		
		//给房间信息添加客人信息
		var passengerInfo = new Array();
		$passengerSelect.each(function(){
			var $passengerOption = $(this).find("option:selected");
			var pInfo = $passengerOption.attr('identify') + '[*]' + $passengerOption.attr('value');
			passengerInfo.push(pInfo);
			passengerInformation.remove(pInfo);
		});
		
		var $position = $roomPassengerDiv.find("span.passengerInfo");	
		var passengerDetails = "";	
		for(var i=0; i<passengerInfo.length; i++){
			var pInfo = passengerInfo[i].split("[*]");	
			var identify = pInfo[0];
			var passengerName = pInfo[1];
			if(i == 0){
				passengerDetails = "<span identify='"+ identify +"' class='people-name-list'>"+ passengerName +"</span>";
			}else{
				passengerDetails += "<span identify='"+ identify +"' class='people-name-list'>"+ passengerName +"</span>";
			}
		}
		$position.after($(passengerDetails));
		$roomPassengerDiv.hide().appendTo($roomPassengerInfoDiv).slideDown();
		adjustScrollDuringRoom();
	}
	
	//删除客人房间
	var deleteRoom = function(button){
		//显示添加按钮
		var $roomInfo = $(button).closest(".people-choose-list");
		
		$roomInfo.closest("td").find(".addRoomButton,.mobileAddRoomButton").fadeIn();
		
		var roomPassengerNumber = $roomInfo.find("span.roomTypeInfo").data("roomPassengerNumber");
		choosedRoomPeopleNumber -= roomPassengerNumber;
		
		$roomInfo.find("span.people-name-list").each(function(){
			var pIdentify = $(this).attr("identify");
			var pName = $(this).html();
			passengerInformation.push(pIdentify + '[*]' + pName);	
		});
		$roomInfo.slideUp(function(){
			$(this).remove();		
		});
		
		
		if(!isMobile){
			addRoom($(".addRoomButton")[0],1);
		}else{
			addRoom($(".mobileAddRoomButton")[0],1);
		}
		adjustScrollDuringRoom();
	}
	
	//检查出发日期是否选中
	var mobileCheckDepartureDate = function(){
		if(tabIsLocked){
			alertWarn("Please choose the departure date.");
			return true;
		}else{
			return false;
		}	
	}
	
	//显示所有信息(window)
	var showTotalInfo = function(){
		//预留信息
		var $departureDateInput = $("#windowDepartureDateInput");
		var $adultsNumber = $("#windowAdultsNumber");
		var $childrenNumber = $("#windowChildrenNumber");
		var $infantsNumber = $("#windowInfantsNumber");	
		
		$(".showPackageName").html(tourlineName);
		$(".showDepartureGateWay").html(chooseDepartureInfo);
		$(".showDate").html($departureDateInput.val());
		$(".showReturnDate").html(getReturnDateString($departureDateInput.val()));
		$(".showAdults").html($adultsNumber.val());
		$(".showChildren").html($childrenNumber.val());
		$(".showInfants").html($infantsNumber.val());
		showOrHideTransferAndAirtickets();
		//联系人信息
		var $firstNameInput = $("#windowFirstName");
		var $lastNameInput = $("#windowLastName");
		var $emailInput = $("#windowEmail");
		var $phoneInput = $("#windowPhone");

		$(".showFirstName").val($firstNameInput.val());
		$(".showLastName").val($lastNameInput.val());
		$(".showEmail").val($emailInput.val());
		$(".showPhone").val($phoneInput.val());
		
		//客人信息
		var $showPassengerDiv = $("#showPassengerInformation");
		$showPassengerDiv.empty();
		var $guestRoomInfo = $(".guestRoomInfo");
		$guestRoomInfo.find(".form-info-box").each(function(){
			var passengerNo = $(this).find("p.form-info-num").html();
			var passengerType =	$(this).find("p.form-info-classes").html();
			var $showPassengerInfo = $("#showPassengerInfoTemplate").clone(true).removeAttr("id");
			$showPassengerInfo.find(".information-list-h4").html(passengerNo + ' ' + passengerType);
			$showPassengerInfo.find(".showSirName").html($(this).find("input.lastName").val());
			$showPassengerInfo.find(".showGender").html($(this).find("select.gender").find("option:selected").html());
			//$showPassengerInfo.find(".showNationality").html($(this).find("input.nationality").val());
			$showPassengerInfo.find(".showExpiryDate").html($(this).find("input.passportNoExpiryDate").val());
			$showPassengerInfo.find(".showGivenName").html($(this).find("input.firstName").val());
			$showPassengerInfo.find(".showMiddleName").html($(this).find("input.middleName").val());
			//$showPassengerInfo.find(".showBirthDate").html($(this).find("input.birthday").val());
			$showPassengerInfo.find(".showPassportNo").html($(this).find("input.passportNo").val());
			$showPassengerInfo.appendTo($showPassengerDiv);
		});
		
		//住房
		var $showRoomDiv = $("#showRoomInformation");
		$showRoomDiv.empty();
		$("#roomPassengerInfoDiv").find(".people-choose-list").each(function(index){
			var $showRoomInfo = $("#showRoomInfoTemplate").clone(true).removeAttr("id");
			
			var roomType = $(this).find("span.roomTypeInfo").html();
			$showRoomInfo.find("span.showRoomType").html(roomType);
			
			var passengerArray = new Array();
			$(this).find("span.people-name-list").each(function(index){
				passengerArray.push($(this).html());			
			});
			
			var $basicPosition = $showRoomInfo.find("span.showFirstPassenger");
			
			var appender = "";
			for(var i=0; i<passengerArray.length; i++){
				if(i == 0){
					appender += '<span>'+ passengerArray[i] +'</span>';
				}else{
					appender += '/<span>'+ passengerArray[i] +'</span>';
				}
			}
			$basicPosition.after($(appender));
			$showRoomInfo.appendTo($showRoomDiv);
		});
		addOptionalTour();
		addAdditionalProduct();
		$("#allPriceShow").show();
		$("#submitButton").show();
	}
	
	
	//显示所有信息(mobile)
	var showTotalInfoMobile = function(){
		//预留信息
		var $departureDateInput = $("#mobileDepartureDateInput");
		var $adultsNumber = $("#mobileAdultsNumber");
		var $childrenNumber = $("#mobileChildrenNumber");
		var $infantsNumber = $("#mobileInfantsNumber");
		
		$(".showPackageName").html(tourlineName);
		$(".showDepartureGateWay").html(chooseDepartureInfo);
		$(".showDate").html($departureDateInput.val());
		$(".showReturnDate").html(getReturnDateString($departureDateInput.val()));
		$(".showAdults").html($adultsNumber.val());
		$(".showChildren").html($childrenNumber.val());
		$(".showInfants").html($infantsNumber.val());
		showOrHideTransferAndAirtickets();
		//基础信息
		var $firstNameInput = $("#mobileFirstName");
		var $lastNameInput = $("#mobileLastName");
		
		var $emailInput = $("#mobileEmail");
		var $phoneInput = $("#mobilePhone");
			
		$(".showFirstName").val($firstNameInput.val());
		$(".showLastName").val($lastNameInput.val());
		$(".showEmail").val($emailInput.val());
		$(".showPhone").val($phoneInput.val());
		
		//客人信息
		var $showPassengerDiv = $("#showPassengerInformationMobile");
		$showPassengerDiv.empty();
		var $guestRoomInfo = $(".mobileGuestRoomInfo");
		$guestRoomInfo.find(".form-info-box").each(function(){
			var passengerNo = $(this).find("p.form-info-num").html();
			var passengerType =	$(this).find("p.form-info-classes").html();
		
			var $showPassengerInfo = $("#showPassengerInfoTemplate").clone(true).removeAttr("id");
			$showPassengerInfo.find(".information-list-h4").html(passengerNo + ' ' + passengerType);
			$showPassengerInfo.find(".showSirName").html($(this).find("input.lastName").val());
			$showPassengerInfo.find(".showGender").html($(this).find("select.gender").find("option:selected").html());
			//$showPassengerInfo.find(".showNationality").html($(this).find("input.nationality").val());
			$showPassengerInfo.find(".showExpiryDate").html($(this).find("input.passportNoExpiryDate").val());
			$showPassengerInfo.find(".showGivenName").html($(this).find("input.firstName").val());
			$showPassengerInfo.find(".showMiddleName").html($(this).find("input.middleName").val());
			//$showPassengerInfo.find(".showBirthDate").html($(this).find("input.birthday").val());
			$showPassengerInfo.find(".showPassportNo").html($(this).find("input.passportNo").val());
			$showPassengerInfo.appendTo($showPassengerDiv);
		});
		
		//住房
		var $showRoomDiv = $("#showRoomInformationMobile");
		$showRoomDiv.empty();
		$("#mobileRoomPassengerInfoDiv").find(".people-choose-list").each(function(index){
			var $showRoomInfo = $("#showRoomInfoTemplate").clone(true).removeAttr("id");
			
			$showRoomInfo.find(".information-list-box").removeClass("left");
			
			var roomType = $(this).find("span.roomTypeInfo").html();
			$showRoomInfo.find("span.showRoomType").html(roomType);
			
			var passengerArray = new Array();
			$(this).find("span.people-name-list").each(function(index){
				passengerArray.push($(this).html());			
			});
			
			var $basicPosition = $showRoomInfo.find("span.showFirstPassenger");
			
			var appender = "";
			for(var i=0; i<passengerArray.length; i++){
				if(i == 0){
					appender += '<span>'+ passengerArray[i] +'</span>';
				}else{
					appender += '/<span>'+ passengerArray[i] +'</span>';
				}
			}
			$basicPosition.after($(appender));
			$showRoomInfo.appendTo($showRoomDiv);
		});
		addOptionalTour();
		addAdditionalProduct();
		$("#allPriceShow").show();
		$("#submitButton").show();
	}
	
	//展示自费项目
	var addOptionalTour = function(){
		if($(".optionalTour").size() == 0){
			return;
		}
		var beforeScrollTop = $(window).scrollTop();
		var $additionalProduct;
		
		if(!isMobile){
		   $additionalProduct = $("#windowOptionalTour");
		}else{
		   $additionalProduct = $("#mobileOptionalTourTab");
		}
		 
		var beforeShowScroll = $additionalProduct.offset().top;
		
		$(".optionalTourInformation").empty();
		var isEmpty = 1;
		//与目的地有关联的自费项目
		$additionalProduct.find(".selfpayitinerary input.quantity[choosed='1']").each(function(index){
			isEmpty = -10;
		
			var $product = $(this).closest(".product");
			var $destination = $product.closest(".destination");
			var $itinery = $destination.closest(".timeline");	
			
			var price = $product.find("span.amount").attr("price");
			var productName = $product.find("a.productName").html();
			var destinationName = $destination.find(".Custom-item-city").html();
			var itineryDay = $itinery.find(".daynumber").html();
			var quantity = $(this).val() * 1;
			
			var $additionalProductInfo = $("#additionalProductShowTemplate").clone(true).removeAttr("id");
			var title = itineryDay + ' - ' + destinationName;
			
			var $existProduct = $(".optionalTourInformation").find(".information-list-h4:contains("+ title +")");
			if($existProduct.size() > 0){
				var $singleProduct = $additionalProductInfo.find(".singleProduct");
				$singleProduct.find(".productName").html(productName);
				$singleProduct.find(".quantity").html(quantity);
				$singleProduct.find(".price").html(sign + price);
				$existProduct.next().find("table").append($singleProduct);
			}else{
				$additionalProductInfo.find(".information-list-h4").html(title);
				$additionalProductInfo.find(".productName").html(productName);
				$additionalProductInfo.find(".quantity").html(quantity);
				$additionalProductInfo.find(".price").html(sign + price);
				$(".optionalTourInformation").append($additionalProductInfo);
			}
		});
		//与目的地非关联的自费项目
		$additionalProduct.find(".selfpay input.quantity[choosed='1']").each(function(index){
			isEmpty = -10;
		
			var $product = $(this).closest(".product");
			
			var price = $product.find("span.amount").attr("price");
			var productName = $product.find("a.productName").html();
			var quantity = $(this).val() * 1;
			
			var $additionalProductInfo = $("#additionalProductShowTemplate").clone(true).removeAttr("id");
			var title = productName;
			
			var $existProduct = $(".optionalTourInformation").find(".information-list-h4:contains("+ title +")");
			if($existProduct.size() > 0){
				var $singleProduct = $additionalProductInfo.find(".singleProduct");
				$singleProduct.find(".productName").html(productName);
				$singleProduct.find(".quantity").html(quantity);
				$singleProduct.find(".price").html(sign + price);
				$existProduct.next().find("table").append($singleProduct);
			}else{
				$additionalProductInfo.find(".information-list-h4").html(title);
				$additionalProductInfo.find(".productName").html(productName);
				$additionalProductInfo.find(".quantity").html(quantity);
				$additionalProductInfo.find(".price").html(sign + price);
				$(".optionalTourInformation").append($additionalProductInfo);
			}
		});		
		if(isEmpty == 1){
			$(".optionalTourInformation").parent().hide();
		}else{
			$(".optionalTourInformation").parent().show();
		}
		var afterShowScroll = $additionalProduct.offset().top;
		var changedScroll = afterShowScroll - beforeShowScroll;
		var afterScrollTop = beforeScrollTop + changedScroll;
		if(step == 4){
			$(window).scrollTop(afterScrollTop);
		}
	}
		
	//展示私人订制产品
	var addAdditionalProduct = function(){
		if($(".additionalProduct").size() == 0){
			return;
		}
		var beforeScrollTop = $(window).scrollTop();
		var $additionalProduct;
		
		if(!isMobile){
		   $additionalProduct = $("#windowAdditional");
		}else{
		   $additionalProduct = $("#mobileCustomTab");
		}
		 
		var beforeShowScroll = $additionalProduct.offset().top;
		
		$(".additionalProductInformation").empty();
		var isEmpty = 1;
		$additionalProduct.find("input.quantity[choosed='1']").each(function(index){
			isEmpty = -10;
		
			var $product = $(this).closest(".product");
			var $destination = $product.closest(".destination");
			var $itinery = $destination.closest(".timeline");	
			
			var price = $product.find("span.amount").attr("price");
			var productName = $product.find("a.productName").html();
			var destinationName = $destination.find(".Custom-item-city").html();
			var itineryDay = $itinery.find(".daynumber").html();
			var quantity = $(this).val() * 1;
			
			var $additionalProductInfo = $("#additionalProductShowTemplate").clone(true).removeAttr("id");
			var title = itineryDay + ' - ' + destinationName;
			
			var $existProduct = $(".additionalProductInformation").find(".information-list-h4:contains("+ title +")");
			if($existProduct.size() > 0){
				var $singleProduct = $additionalProductInfo.find(".singleProduct");
				$singleProduct.find(".productName").html(productName);
				$singleProduct.find(".quantity").html(quantity);
				$singleProduct.find(".price").html(sign + price);
				$existProduct.next().find("table").append($singleProduct);
			}else{
				$additionalProductInfo.find(".information-list-h4").html(title);
				$additionalProductInfo.find(".productName").html(productName);
				$additionalProductInfo.find(".quantity").html(quantity);
				$additionalProductInfo.find(".price").html(sign + price);
				$(".additionalProductInformation").append($additionalProductInfo);
			}
		});
		if(isEmpty == 1){
			$(".additionalProductInformation").parent().hide();
		}else{
			$(".additionalProductInformation").parent().show();
		}
		var afterShowScroll = $additionalProduct.offset().top;
		var changedScroll = afterShowScroll - beforeShowScroll;
		var afterScrollTop = beforeScrollTop + changedScroll;
		if(step == 4){
			$(window).scrollTop(afterScrollTop);
		}
	}
		
	//展示价格
	var showFees = function(Ignore){
		if(!Ignore){
			clearCouponInformation();
		}
		var additionalProductTotalPrice = 0;
		$(".additionalProductShow").remove();
		for(var productId in additionalProductFees){
			additionalProduct = additionalProductFees[productId];
			additionalProductTotalPrice += additionalProduct.totalFee;
			$("#integral").parent().before($('<tr class="additionalProductShow"><td class="form-book-tab-left">'+ additionalProduct.productName +'</td><td class="form-book-tab-right">'+ sign +formatPrice(additionalProduct.totalFee) +'</td></tr>'));
		}
		var optionalTourTotalPrice = 0;
		
		if($(".voucherNumberInput").val()!=''){
		  tourFee=sellingPrice * (adultsNumber-$(".pnumInput").val()) + childPrice * childrenNumber + babyPrice * infantsNumber;
		}else{
		  tourFee = sellingPrice * adultsNumber + childPrice * childrenNumber + babyPrice * infantsNumber;
		}
		
		singleSupplementsFee = singleRoomNumber * singleroomprice;
		
		tipFee = perTipPrice * totalNumber;
		
		steamFee = perSteamPrice * totalNumber;
		
		transferFee = perTransferPrice * totalNumber;
		
		airTicketsFee = perAirTicketsPrice * totalNumber;
		
		totalFee = tourFee + singleSupplementsFee + tipFee + steamFee + transferFee + airTicketsFee + additionalProductTotalPrice - integralFee - couponFee;
		
		//团费显示
		$("#tourPrice").html(sign + formatPrice(tourFee));
		
		//单房差显示
		if(singleSupplementsFee != 0){
			$("#singleSupplementsPrice").html(sign + formatPrice(singleSupplementsFee)).parent().show();
		}else{
			$("#singleSupplementsPrice").parent().hide();
		}
		
		if(tipFee != 0){
			//导服费显示
			$("#tipPrice").html(sign + formatPrice(tipFee)).parent().show();
		}else{
			$("#tipPrice").parent().hide();		
		}

		if(steamFee != 0){
			//行程外精彩景点观光
			$("#steamPrice").html(sign + formatPrice(steamFee)).parent().show();
		}else{
			$("#steamPrice").parent().hide();
		}
		
		if(transferFee != 0){
			//接送机
			$("#transferPrice").html(sign + formatPrice(transferFee)).parent().show();
		}else{
			$("#transferPrice").parent().hide();
		}
		
		if(airTicketsFee != 0){
			//机票费用
			$("#airPrice").html(sign + formatPrice(airTicketsFee)).parent().show();
		}else{
			$("#airPrice").parent().hide();
		}
		
		if(integralFee != 0){
			//积分兑换的费用
			$("#integral").html('-' + sign + formatPrice(integralFee)).parent().show();
		}else{
			$("#integral").parent().hide();
		}
		
		if(couponFee != 0){
			//优惠券兑换的费用
			$("#coupon").html('-' + sign + formatPrice(couponFee)).parent().show();
		}else{
			$("#coupon").parent().hide();
		}

		//总价
		$("#totalPrice").html(sign + formatPrice(totalFee));
		$(".totalPriceInput").val(totalFee);		
	}
	
	//给tourPassenger设置name
	var setPassengerNames = function($guestInfoInput,index,passengerType){
		$guestInfoInput.find(".lastName").attr("name","tourPassengerList["+ index +"].lastName");
		$guestInfoInput.find(".firstName").attr("name","tourPassengerList["+ index +"].firstName");
		$guestInfoInput.find(".middleName").attr("name","tourPassengerList["+ index +"].middleName");
		$guestInfoInput.find(".gender").attr("name","tourPassengerList["+ index +"].gender");
		$guestInfoInput.find(".birthday").attr("name","tourPassengerList["+ index +"].birthday");
		$guestInfoInput.find(".mobileNumber").attr("name","tourPassengerList["+ index +"].mobileNumber");
		$guestInfoInput.find(".nationality").attr("name","tourPassengerList["+ index +"].nationality");
		$guestInfoInput.find(".passportNo").attr("name","tourPassengerList["+ index +"].passportNo");
		$guestInfoInput.find(".passportNoExpiryDate").attr("name","tourPassengerList["+ index +"].passportNoExpiryDate");
		$guestInfoInput.find(".roomTypeInput").attr("name","tourPassengerList["+ index +"].roomType");
		$guestInfoInput.find(".roomNumberInput").attr("name","tourPassengerList["+ index +"].roomNumber");
		if(passengerType == 0){
			$guestInfoInput.find(".identityInput").attr("name","tourPassengerList["+ index +"].identity").attr("value","ADULT");
		}else if(passengerType == 1){
			$guestInfoInput.find(".identityInput").attr("name","tourPassengerList["+ index +"].identity").attr("value","CHILDREN");
		}else{
			$guestInfoInput.find(".identityInput").attr("name","tourPassengerList["+ index +"].identity").attr("value","INFANTS");
		}
	}
	
	//调整页面位置
	var adjustScrollDuringRoom = function(){
		if(!isMobile){
			adjustWindowScroll();
		}
	}
	
	//调整页面位置(window)
	var adjustWindowScroll = function(){
		dateScrollTop  = $("#chooseDiv").offset().top - 74;
		$(window).scrollTop(dateScrollTop);
	}
	
	//调整页面位置(mobile)
	var adjustMobileScroll = function(){
		var mobileDivScrollTop =  $("#mobileDiv").offset().top;
		$(window).scrollTop(mobileDivScrollTop - 72);
	}
	
	//忽略输入护照号
	var ignorePassport = function(input){
		if($(input).is(':checked')){
			$("img.login").hide();
			ignorePassportStatus = true;
		}else{
			$("img.login").show();
			ignorePassportStatus = false;
		}
	}
	
	//提交信息
	var submitForm = function(){
		var $form;
		var $roomPassengerInfoDiv;
		var $guestRoomInfo;
		var $Content;
		
		//如果是window版
		if(!isMobile){
			$form = $("#windowForm");
			$roomPassengerInfoDiv = $("#roomPassengerInfoDiv");	
			$guestRoomInfo = $(".guestRoomInfo");
			$Content = $("#windowDiv");
		}else{
			if(mobileCheckDepartureDate()){
				return;        	
	        }
			$form = $("#mobileForm");
			$roomPassengerInfoDiv = $("#mobileRoomPassengerInfoDiv");	
			$guestRoomInfo = $(".mobileGuestRoomInfo");
			$Content = $("#mobileDiv");
		}
		
		var	$firstNameInput = $form.find("input[name='firstName']");
		var	$lastNameInput = $form.find("input[name='lastName']");
		var	$emailInput = $form.find("input[name='email']");
		var	$phoneInput = $form.find("input[name='phone']");
		
		//name空值校验
		if($lastNameInput.val().trim() == ''){
			alertWarn('Please fill in your surname.');
			addWarnningShow($lastNameInput);
			return;
		}
		if($firstNameInput.val().trim() == ''){
			alertWarn('Please fill in your given name.');
			addWarnningShow($firstNameInput);
			return;
		}
		//email空值校验
		if($emailInput.val().trim() == ''){
			alertWarn('Please fill in the email.');
			addWarnningShow($emailInput);
			return;
		}
		if(!reg.test($emailInput.val().trim())){
			alertWarn('Please enter the valid email.');
			addWarnningShow($emailInput);
			return;
		}
		//电话空值检验
		if($phoneInput.val().trim() == ''){
			alertWarn('Please fill in the phone number.');
			addWarnningShow($phoneInput);
			return;
		}
		//电话格式校验
		if(!numberRule.test($phoneInput.val().trim())){
			alertWarn('The phone No. must be made up of 8 to 12 digits.');
			addWarnningShow($phoneInput);
			return false;	
		}
		
		var singleRoomNumber = 0;
		var doubleRoomNumber = 0;
		var twinRoomNumber = 0;
		var tripleRoomNumber = 0;
		var passengerRoomTypeInfo = {};
		
		$roomPassengerInfoDiv.find(".people-choose-list").each(function(index){
			var roomType = $(this).find("span.roomTypeInfo").html();
			if(roomType == "Single Room"){
				singleRoomNumber++;
			}else if(roomType == "Double Room"){
				doubleRoomNumber++;
			}else if(roomType == "Twin Room"){
				twinRoomNumber++;
			}else if(roomType == "Triple Room"){
				tripleRoomNumber++;
			}
			$(this).find("span.people-name-list").each(function(){
				passengerRoomTypeInfo[$(this).html()] = roomType + "_" + (index + 1);
			});
		});	
		
		//房型数量
		$form.find(".singleRoomNumber").val(singleRoomNumber);
		$form.find(".doubleRoomNumber").val(doubleRoomNumber);
		$form.find(".twinRoomNumber").val(twinRoomNumber);
		$form.find(".tripleRoomNumber").val(tripleRoomNumber);
		$form.find(".departureIdInput").val(chooseDepartureId);
		
		//房间住房信息
		$guestRoomInfo.find(".form-info-box").each(function(){
			var $sirName = $(this).find("input.lastName");
	    	var $givenName = $(this).find("input.firstName");		
	    	var showName  = $givenName.val() + ' ' + $sirName.val();
	    	
	    	if($(this).find("input.identityInput").val() == 'ADULT'){
	    		var roomInfo = passengerRoomTypeInfo[showName];
	    		var roomTypeNumber = roomInfo.split("_");
	    		$(this).find(".roomTypeInput").val(roomTypeNumber[0]);
	    		$(this).find(".roomNumberInput").val(roomTypeNumber[1]);
	    	}
		});
		
		$Content.find(".optionalTour .selfpayitinerary input.quantity[choosed='1']").each(function(index){
			var $product = $(this).closest(".product");
			var $destination = $product.closest(".destination");
			var $itinery = $destination.closest(".timeline");	
			
			var productId = $product.attr("productid");
			var productName = $product.find("a.productName").html();
			var destinationId = $destination.attr("destinationid");
			var destinationName = $destination.find(".Custom-item-city").html();
			var itineryId = $itinery.attr("itineraryid");	
			var itineryDay = $itinery.find(".daynumber").html();
			var quantity = $(this).val() * 1;
			
			$form.append('<input value="'+ productId +'" name="selfPayList['+ index +'].productId" type="hidden">');
			$form.append('<input value="'+ productName +'" name="selfPayList['+ index +'].productName" type="hidden">');
			$form.append('<input value="'+ destinationId +'" name="selfPayList['+ index +'].destinationId" type="hidden">');
			$form.append('<input value="'+ destinationName +'" name="selfPayList['+ index +'].destinationName" type="hidden">');
			$form.append('<input value="'+ itineryId +'" name="selfPayList['+ index +'].itineryId" type="hidden">');
			$form.append('<input value="'+ itineryDay +'" name="selfPayList['+ index +'].itineryDay" type="hidden">');
			$form.append('<input value="'+ quantity +'" name="selfPayList['+ index +'].quantity" type="hidden">');
		});	
		
		$Content.find(".optionalTour .selfpay input.quantity[choosed='1']").each(function(index){
			var $product = $(this).closest(".product");
			
			var selfpayId = $product.attr("productid");
			var quantity = $(this).val() * 1;
			
			$form.append('<input value="'+ selfpayId +'" name="selfPayInTourline['+ index +'].productId" type="hidden">');
			$form.append('<input value="'+ quantity +'" name="selfPayInTourline['+ index +'].quantity" type="hidden">');
		});	
		//私人订制产品
		$additionalProduct.find("input.quantity[choosed='1']").each(function(index){
			var $product = $(this).closest(".product");
			var $destination = $product.closest(".destination");
			var $itinery = $destination.closest(".timeline");	
			
			var productId = $product.attr("productid");
			var productName = $product.find("a.productName").html();
			var destinationId = $destination.attr("destinationid");
			var destinationName = $destination.find(".Custom-item-city").html();
			var itineryId = $itinery.attr("itineraryid");	
			var itineryDay = $itinery.find(".daynumber").html();
			var quantity = $(this).val() * 1;
			var type = $product.attr("type");
			
			$form.append('<input value="'+ productId +'" name="additionalProductList['+ index +'].productId" type="hidden">');
			$form.append('<input value="'+ productName +'" name="additionalProductList['+ index +'].productName" type="hidden">');
			$form.append('<input value="'+ destinationId +'" name="additionalProductList['+ index +'].destinationId" type="hidden">');
			$form.append('<input value="'+ destinationName +'" name="additionalProductList['+ index +'].destinationName" type="hidden">');
			$form.append('<input value="'+ itineryId +'" name="additionalProductList['+ index +'].itineryId" type="hidden">');
			$form.append('<input value="'+ itineryDay +'" name="additionalProductList['+ index +'].itineryDay" type="hidden">');
			$form.append('<input value="'+ quantity +'" name="additionalProductList['+ index +'].quantity" type="hidden">');
			$form.append('<input value="'+ type +'" name="additionalProductList['+ index +'].type" type="hidden">');
		});
		$form.append('<input value="'+ totalNumber +'" name="totalNumber" type="hidden">');
		if($transferSelect.size() > 0){
			$form.append('<input value="'+ $transferSelect.val() +'" name="transferId" type="hidden">');
		}
		if($airticketsSelect.size() > 0){
			$form.append('<input value="'+ $airticketsSelect.val() +'" name="airticketPriceId" type="hidden">');
		}
		$form.append('<input value="'+ costCode  +'" name="currencyCode" type="hidden">');
		$form.submit();
	}
	
	<#-- 当用户选择积分变化时 
    function scoreChange(){
		var $scoreSelect = $("#scoreSelect");
    	chooseScore = $scoreSelect.find("option:selected").val();
    	exchangedPrice = chooseScore/100 * exchangeRate;
    	exchangedPrice = Math.floor(exchangedPrice);
    	var $showScore = $("#showScore");
    	var code = $showScore.attr("currency");
    	$showScore.html('-' + code + exchangedPrice);
    }
    -->
	
	<#-- 兑换优惠券 -->
	var confirmCoupon = function(button){
	  var $codeInput = $(button).prev();
	  var code = $codeInput.val().trim();
	  if(code == ''){
	  	 alertWarn('Please fill the coupon code.'); 
		 addWarnningShow($codeInput);		  	 
	  	 return;
	  }
      var money = totalFee;
      $.ajax({
            type: "POST",
            url: "${ctx!}/front/action/Exchange.do",
            data: {courrysign:costCode,code:code,totalPrice:money},
            success:function(data){
                var obj = eval(data);
                if(obj.meassage!=null&&obj.meassage!=""){
                  alertWarn(obj.meassage);
                }else{
                  $(".couponCode").val(code);
				  couponFee = obj.releaseprice;    
                  totalPrice = obj.totalprice;
                  $(".couponsDetail").html('-' + sign + couponFee);
                  $codeInput.attr("readonly","readonly");
                  $(button).attr('disabled',true);
                  $(".couponInfo").show();
				  showFees(true);                 
                }
            },
            error:function(e){
            }
        });   
	}

	//清除coupon的信息
	var clearCouponInformation = function(){
		$(".couponCode").val("");
		$(".couponsDetail").html("").prev().find(".couponInput").removeAttr("readonly").val("").next().removeAttr("disabled");
		$(".couponInfo").hide().find("input").val('');
		couponFee = 0;
	}

	//添加自定义产品
    var addProduct = function(productId,productName,unitcost,quantity){
		var existAdditionalProduct = additionalProductFees[productId];
		
		//如果该产品第一次添加
		if(existAdditionalProduct == null){
			totalFee = unitcost * quantity;
			additionalProductFees[productId] = new AdditionalProductFee(productId,productName,unitcost,quantity,totalFee);
		//如果该产品已存在
		}else{
			quantity = quantity + existAdditionalProduct.quantity;
			totalPrice = unitcost * quantity;
			existAdditionalProduct.quantity = quantity;
			existAdditionalProduct.totalFee = totalPrice;		
		}
	}
	
	//获得返回日期
	var getReturnDateString = function(beginDateString){
		var days = sessionStorage.getItem("days");
		var beginDateArray = beginDateString.split("/");
		var date = new Date();
		date.setFullYear(beginDateArray[2]);
		date.setMonth(beginDateArray[0] - 1);
		date.setDate(beginDateArray[1]);
		var endDateStamp = date.getTime() + (days - 1) * 3600 * 24 * 1000;
		date.setTime(endDateStamp);
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var date = date.getDate();
		if(month < 10){
			month = '0' + month;
		}		
		if(date < 10){
		   date = '0' + date;
		}
	    return month + '/' + date + '/' + year;
	}
	
	//显示或隐藏接送机和机票的具体内容
	var showOrHideTransferAndAirtickets = function(){
		if(transferText == '' && airticketsText == ''){
			$(".showTransfer").parent().parent().parent().hide();
		}else{
			$(".showTransfer").parent().parent().parent().show();
			if(transferText == ''){
				$(".showTransfer").parent().hide();	
			}else{
				$(".showTransfer").html(transferText).parent().show();	
			}
			if(airticketsText == ''){
				$(".showAirtickets").parent().hide();	
			}else{
				$(".showAirtickets").html(airticketsText).parent().show();	
			}
		} 
	}
</script> 
</body>
</html>