<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<#if request??>
	<#assign ctx = request.contextPath />
<#else>
	<#assign ctx = contextPath />
</#if>

<title>${(tourline.productProductid.pagePageid.metatitle)!}</title>
<meta name="keywords" content="${(tourline.productProductid.pagePageid.metakeywords)!}" />
<meta name="description" content="${(tourline.productProductid.pagePageid.metadescription)!}" />
<link rel="shortcut icon" href="${ctx!}/favicon.ico">
<link rel="apple-touch-icon-precomposed" href="${ctx!}/apple-touch-icon.png">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/fonts/linecons/css/linecons-min.css" type='text/css' media='all' />
<link rel='stylesheet' id='font-awesome-css' href="https://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" type='text/css' media='all' />
<#--
<link href="https://fonts.googleapis.com/css?family=Roboto|PT Sans|021-CAI978|Helvetica|Cabin|Lato" rel="stylesheet" type="text/css" />
-->
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv-printshiv.min.js?ver=4.3.1'></script> <![endif]-->
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js?ver=4.3.1'></script> 
<![endif]-->
<style id="fit-vids-style">
.fluid-width-video-wrapper { width: 100%; position: relative; padding: 0; }
.fluid-width-video-wrapper iframe, .fluid-width-video-wrapper object, .fluid-width-video-wrapper embed { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
</style>
<style id="ShrinkMenu">
.menu-shrink { top : -49px !important; }
</style>
<meta name="generator">
</head>
<body class="blog">
<div></div>
  <#--top-->
  <#include "/front/include/top.ftl"/>
  <#assign bgUrl="/assets-web/images/details_bg.jpg">
  <#if tourline.mapurl?has_content>
   <#assign bgUrl=tourline.mapurl>
  </#if>
<section  id="sectionHtml" class="hero small-hero" style="background-image: url(${ctx!}${bgUrl}) ;color: #fff;position: relative;background-color:#6c6e73;background-position: center;background-size: cover;background-repeat: no-repeat;">
  <div class="bg-overlay">
    <div class="container" style="">
      <div class="breadcrumbs">
        <ul>
          <li><a href="/">Home</a></li>
          <li><a href="${(region.url)!}">${(region.name)!}</a></li>
        </ul>
      </div>
      <div class="list-intro-wrap">
        <h1 class="intro-title" style="text-align:center;">${tourline.tourname} - ${tourline.days} Days</h1>
        <div id="upReviewScoreOuter" class="intro-text">
        	<div class="atgrid-item-rating">
				 <#list  1..starNumber as i>
				 <i class="fa fa-star voted"></i>
				 </#list> 
				 <#if reviewNumber = 1>
				 <a href="" class="">(${reviewNumber} review)</a>
				 </#if>
				 <#if reviewNumber gt 1>
				 <a href="" class="">(${reviewNumber} reviews)</a>
				 </#if>
			</div>
        </div>
      </div>
    </div>
  </div>
</section>
<section class="featured-destinations" style="background-color:#f5f6f6; padding-bottom:20px;">
  <div class="container margin-bottom padding-top">
    <div class="row tour-single-rise">
      <main class="col-md-9">
        <div class="tours-tabs">
          <ul id="windowUl" class="nav nav-tabs hidden-xs">
            <li class="active">Details</li>
            <li>Itinerary</li>
            <li class="departureDateDiv">Dates & Rates</li>
            <#if itineraryListWithSelfPay?has_content || optionalFeeList?has_content>
            <li class="optionalTour">Optional</li>
            </#if>
            <#if itineraryListWithAdditionalProduct?? && itineraryListWithAdditionalProduct?has_content>
            <li id="additionalProductLi">Custom</li>
            </#if>
            <li class="galleryDiv">Gallery</li>
            <li class="visible-xs booking-form-scroller"><a href="">Book the tour</a></li>
          </ul>
          
          <div class="tab-content hidden-xs">
            <div id="tourlineDetailTab" class="tab-pane" style="display:block;">
              <div class="tours-tabs-info">
                <div class="tours-tabs-info-item">
                  <div class="tours-tabs-info-item-content">
                    <div class="tours-tabs-info-item-icon"><i class="fa fa-clock-o"></i></div>
                    <div class="tours-tabs-info-item-title">${tourline.days} days</div>
                    <div class="tours-tabs-info-item-description">Duration</div>
                  </div>
                </div>
                <div class="tours-tabs-info-item">
                  <div class="tours-tabs-info-item-content">
                    <div class="tours-tabs-info-item-icon"><i class="fa fa-map-marker"></i></div>
                    <div class="tours-tabs-info-item-title">${(tourline.startDestination)!}/${(tourline.endDestination)!}</div>
                    <div class="tours-tabs-info-item-description">Start /End City</div>
                  </div>
                </div>
                <div class="tours-tabs-info-item">
                  <div class="tours-tabs-info-item-content">
                    <div class="tours-tabs-info-item-icon"><i class="fa fa-building-o"></i></div>
                    <div class="tours-tabs-info-item-title">Hotel <#if hotelAssociationNumber == 0>Not Included<#else>Included</#if></div>
                    <div class="tours-tabs-info-item-description">Hotel</div>
                  </div>
                </div>
                <div class="tours-tabs-info-item">
                  <div class="tours-tabs-info-item-content">
                    <div class="tours-tabs-info-item-icon"><i class="fa fa-plane"></i></div>
                    <div class="tours-tabs-info-item-title">Air Tickets <#if airticketAssociationNumber == 0>Not Included<#else>Included</#if></div>
                    <div class="tours-tabs-info-item-description">Air Tickets</div>
                  </div>
                </div>
              </div>
              <div class="tours-tabs-content">
                <ul class="list-block list-block-tour-tabs">
				<#if tagList?has_content>
					<#list tagList as tag>
					<li style="background:#${(tag.bgcolor)!};"><a href="">${(tag.name)!}</a></li>
					</#list>
				</#if>
                </ul>
                <h4>About this listing</h4>
                <p>${tourline.productProductid.briefinfo}</p>
                <table class="table table-bordered tours-tabs-table" style="height: 233px;" width="862">
                  <tbody>
                    <tr>
                      <td class="table-width"><strong>ARRIVAL/RETURN LOCATION</strong></td>
                      <td>${(tourline.startDestination)!}/${(tourline.endDestination)!}</td>
                    </tr>
                    <tr>
                      <td><strong>Departure Notice</strong></td>
                      <td>Please check at the airport at lease 3 hours before departure.</td>
                    </tr>
					 <tr>
                      <td><strong>Spanish Group Available</strong></td>
                      <td>Please send your request to latino@intertrips.com or call 1(949)-771-9777. </td>
                    </tr>
                    <tr>
                      <td><strong>INCLUDED</strong></td>
                      <td><table class="table table-bordered" id="includeTable" style="height: 62px;" width="300">
                          <tbody>
                          	<#list inludeItems as serviceItem>
                          		<#if serviceItem_index%2 = 0>
                          			<tr>
                          		</#if>
                          			<td><i class="fa icon-tick icon-tick-on ${serviceItem.ico}"></i>${serviceItem.tittle}</td>
                          		<#if serviceItem_index%2 = 1 || !serviceItem_has_next >
                          			</tr>
                          		</#if>
                          	</#list>
                          </tbody>
                        </table></td>
                    </tr>
                    <tr>
                      <td><strong>NOT INCLUDED</strong></td>
                      <td><table class="table table-bordered" id="notIncludeTable" style="height: 62px;" width="300">
                          <tbody>
                            <#list notIncludeItems as serviceItem>
                          		<#if serviceItem_index%2 = 0>
                          			<tr>
                          		</#if>
                          			<td><i class="fa icon-tick icon-tick-on ${serviceItem.ico}"></i>${serviceItem.tittle}</td>
                          		<#if serviceItem_index%2 = 1 || !serviceItem_has_next >
                          			</tr>
                          		</#if>
                          	</#list>
                          </tbody>
                        </table></td>
                    </tr>
                    <tr>
                      <td><b>HIGHLIGHT</b></td>
                      <td><table class="table table-bordered" style="height: 62px;" width="300">
                          <tbody>
                          	<#list tourline.highlights?split("^^") as highlight>  
                            <tr>
                              <td><i class="fa fa-star icon-tick icon-tick-star"></i>${highlight}</td>
                            </tr>
                            </#list>
                          </tbody>
                        </table></td>
                    </tr>
                    <tr>
                      <td><b>NOTICE</b></td>
                      <td>${(tourline.notice)!}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div id="itineraryTab" class="tab-pane">
              <div class="timeline">
              <#if itineraryList?has_content>
              	<#list itineraryList as itinerary>
                <div class="timeline-item">
                  <div class="timeline-item-icon-wrap">
                    <div class="timeline-item-icon"></div>
                  </div>
                  <div class="timeline-item-content"> <span class="timeline-item-day-num">${(itinerary.day)!}</span>
                    <div class="timeline-item-day-inf"> <#--<span class="timeline-item-day-day">DAY</span>-->
                      <div class="timeline-item-day-dest-list">${(itinerary.title)!}</div>
                    </div>
                    <div class="timeline-item-description">
                    	<div>${(itinerary.content)!}</div>
                    	<#if (itinerary.hotel)??>
                    	<div class="timeline-hotel">
                            <span class="left"><i class="fa icon-tick icon-tick-on fa-building"></i></span>
                            <span style="display:inline-block;width:85%;" class="left">
                            	<span style="display:block;">${(itinerary.hotel.hotelname)!}</span>
                              	<span style="display:block;">${(itinerary.hotel.hoteldescription)!}</span>
  							</span>
                            <div class="clear"></div>
                        </div>
                        </#if>
                        <#if (itinerary.attractions)??>
                        <div class="timeline-scenic">
                            <span class="left"><i class="fa icon-tick icon-tick-on fa-camera-retro"></i></span>
                            <div class="timeline-scenic-box left">
                            	<#list itinerary.attractions as attraction>
                            	<#if attraction_index != 0 && attraction_index % 3 == 0>
                                	<div class="clear"></div>
                                </#if>
                            	<div class="timeline-scenic-img-box left">
                                	<p class="timeline-scenic-img"><img src="${(attraction.imageurl)!}"></p>
                                    <p class="timeline-scenic-tit">${(attraction.name)!}</p>
                                </div>
                                </#list>
                            </div>
                            <div class="clear"></div>
                        </div>
                        </#if>
                    </div>
                  </div>
                </div>
                </#list>
                </#if>
                <#--
                <div class="timeline-item">
                  <div class="timeline-item-icon-wrap">
                    <div class="timeline-item-icon"></div>
                  </div>
                  <div class="timeline-item-content"> <span class="timeline-item-day-num">2</span>
                    <div class="timeline-item-day-inf"> <span class="timeline-item-day-day">DAY</span>
                      <div class="timeline-item-day-dest-list">Departure</div>
                    </div>
                    <div class="timeline-item-description">Tortor elementum egestas metus potenti habitasse tempus natoque senectus commodo rutrum quisque fermentum. Nisi velit primis dapibus odio consequat facilisi sollicitudin porta nulla tellus sagittis platea tempor sed parturient convallis consectetuer Vulputate curae; pharetra.</div>
                  </div>
                </div>
                <div class="timeline-item">
                  <div class="timeline-item-icon-wrap">
                    <div class="timeline-item-icon"></div>
                  </div>
                  <div class="timeline-item-content"> <span class="timeline-item-day-num">3</span>
                    <div class="timeline-item-day-inf"> <span class="timeline-item-day-day">DAY</span>
                      <div class="timeline-item-day-dest-list">Departure</div>
                    </div>
                    <div class="timeline-item-description">Tortor elementum egestas metus potenti habitasse tempus natoque senectus commodo rutrum quisque fermentum. Nisi velit primis dapibus odio consequat facilisi sollicitudin porta nulla tellus sagittis platea tempor sed parturient convallis consectetuer Vulputate curae; pharetra.</div>
                  </div>
                </div>
                <div class="timeline-item">
                  <div class="timeline-item-icon-wrap">
                    <div class="timeline-item-icon"></div>
                  </div>
                  <div class="timeline-item-content"> <span class="timeline-item-day-num">4</span>
                    <div class="timeline-item-day-inf"> <span class="timeline-item-day-day">DAY</span>
                      <div class="timeline-item-day-dest-list">Departure</div>
                    </div>
                    <div class="timeline-item-description">Tortor elementum egestas metus potenti habitasse tempus natoque senectus commodo rutrum quisque fermentum. Nisi velit primis dapibus odio consequat facilisi sollicitudin porta nulla tellus sagittis platea tempor sed parturient convallis consectetuer Vulputate curae; pharetra.</div>
                  </div>
                </div>
                -->
              </div>
            </div>
            <div id="departureTab" class="tab-pane">
              <div class="tours-tabs-content">                    
				<!--               
                <h4>Dates & Rates</h4>
                -->
                <div class="choose-main">
                	<#if departureList??>
                    <div class="people-box">
                        <div class="people-choose">
                          <label class="people-choose-departure">Please select your departure city</label>
                          <select id="windowDepartureSelect" class="people-departure-select">
                            <option value="12345">Please click here to choose your departure city</option>
                          	<#list departureList as departure>
                            <option value="${departure.id}" <#if departure_index == 0>checked="checked"</#if>>${departure.name} ${departure.city}</option>
                            </#list>
                          </select>
                          <div class="clear"></div>
                        </div>
                    </div>
                    </#if>
                    <div class="people-box">
                    	<div class="people-choose-date-tit">
						Please select a date below (For air-inclusive package, the date showing below is the departure date; for LandOnly package, the date showing below is the arrival date).
						</div>
                    	<div id="windowDatesDiv" class="people-choose-date dateMain">
      						
                        </div>
                        <div class="poeple-choose-btn right">
                        	<div class="people-choose left" style="margin-left:30px;">
                            	<label class="choose-page" style="display:none">Click this arrow for more departure dates</label>
                            </div>
                        	<div class="people-choose-jiantou left lastMonth" style="margin-right:30px;">
                            	<a href="javascript:;" onclick="lastMonth(this);" class="jiantou-left"><i class="fa fa-chevron-left"></i></a>
                            </div>
                            <div class="people-choose-jiantou right nextMonth">
                            	<a href="javascript:;" onclick="nextMonth(this);" class="jiantou-right"><i class="fa fa-chevron-right"></i></a>
                            </div>
                        </div>     
                        <#if transferFeeList?has_content>
                        <div class="people-choose">
                          <label class="people-choose-departure">Transfer</label>
                          <select id="windowTransferSelect" class="people-departure-select">
                          	<option value="">-- No Transfer --</option>
                          	<#list transferFeeList as transfer>
                            <option value="${transfer.id}" price="${transfer.price}">${transfer.name} <#if (transfer.price)?? && transfer.price = 0>Free shuttle<#else>${currencySign} ${transfer.price}</#if></option>
                            </#list>
                          </select>
                          <div class="clear"></div>
                        </div>   
						</#if> 
                        <div class="people-choose" style="display:none;">
                          <label class="people-choose-departure">Airtickets</label>
                          <select id="windowAirticketsSelect" class="people-departure-select airticketsSelect">
                          	
                          </select>
                          <div class="clear"></div>
                        </div>                      
                        <div class="clear"></div>
                    </div>
                </div>
                <#if tourline.flightnotice??>
                <div class="people-choose" style="color:#F90">
                  <!--<h4>Airline description:</h4>--><br/>
                  <p>Airline description:<br/>
                   <#list tourline.flightnotice?split("^^") as fnotice>  
				       ${fnotice}<br/>
				   </#list>
                  </p>
                  <div class="clear"></div>
                </div>
                </#if>
             </div>
            </div>
            <#if itineraryListWithSelfPay?has_content || optionalFeeList?has_content>
			<div id="optionalTourTab" class="tab-pane optionalTour">
			  <#if itineraryListWithSelfPay?has_content>
			  <#list itineraryListWithSelfPay as itinerary>
		      <#if itinerary.day lt 10>
		      	  <#assign day = '0' + itinerary.day>	
		      <#else>
		      	  <#assign day = itinerary.day>
		      </#if>
			  <div class="timeline selfpayitinerary" itineraryId="${itinerary.id}">
			    <div class="pay_messages steps_dd pay_un_check pay_bg">
			      <div class="pay_messages_tit itineraryTab"> <span class="pay_span daynumber">Day ${day}</span><b class="pay_messages_tit_b"></b> </div>
			      <div class="airport_list itineraryBody">
			      	<#list itinerary.destinations as destination>	
				     <div class="Custom-list-box destination" destinationId="${destination.id}">
			      		<h4 class="Custom-item-city">${destination.name}</h4>
						<#list destination.selfpayList as selfpay>			      		
				        <div productId="${selfpay.id}" class="product" style="margin-bottom:20px;">		
				          <div class="atgrid-item" style="padding-bottom: 20px;">
				            <div class="left" style="width: 30%;margin: 20px 0 0 10px;"> 
		                    	<a class="Custom-item-top-image" style=" background-image:url(<#if (selfpay.imgUrl)??>${ctx!}${selfpay.imgUrl?split(",")[0]}<#else>${ctx!}/assets-web/images/default_bg.jpg</#if>);">
				              		<div class="featured-img-inner"></div>
				             	</a>
				            </div>
				            <div class="" style="padding: 20px 10px 0 20px; float: left;width: 50%;">
				              <h4 class="atgrid-item-title"> <a class="productName" title="${selfpay.name}">${selfpay.name}</a> </h4>
				              <div class="atgrid-item-description-pc">
		                   		 <p>${(selfpay.remark)!}</p>	
		                      </div>
				            </div>
				            <div class="item-attributes-pc left" style="">
				              <div class="tour-date-tab-pc">
				                <div class="tour-date-tab-down-pc">
		                          <span class="amount" price="${selfpay.price}"><sup>${currencySign}</sup>${selfpay.price}</span>
				                  <div class="featured-list">
				                    <div class="special-checkbox">
				                     <input type="checkbox" name="optionalbox" class="special-input" />
				                    </div>
				                    <ul class="traveler" style="display:none">
				                      <li>
				                        <div class="button-content"> <i class="button-minus button-off"></i>
				                          <input type="text" name="" class="quantity" min="0" value="0" readonly>
				                          <i class="button-plus"></i> </div>
				                      </li>
				                    </ul>
				                  </div>
				                </div>
				              </div>
				            </div>
		                    <div class="clear"></div>
				          </div>
				        </div>
				        </#list>
			        </div>
			        <div class="clear"></div>
			        </#list>
			      </div>
			      <div class="clear"></div>
			    </div>
			  </div>
			  </#list>	
			  </#if>	
			  <#if optionalFeeList?has_content>
			  <div class="timeline selfpay">
			    <div class="pay_messages steps_dd pay_un_check pay_bg">
			      <div class="pay_messages_tit itineraryTab"> <span class="pay_span daynumber">Other Additional Services</span><b class="pay_messages_tit_b"></b> </div>
			      <div class="airport_list itineraryBody">
			      	<div class="Custom-list-box destination">
			      		<h4 class="Custom-item-city"></h4>
			      		<#list optionalFeeList as optionalFee>		
				        <div productId="${optionalFee.id}" class="product" style="margin-bottom:20px;">		
				          <div class="atgrid-item" style="padding-bottom: 20px;">
				            <div class="left" style="width: 30%;margin: 20px 0 0 10px;"> 
                            	<a class="Custom-item-top-image" style=" background-image:url(<#if (optionalFee.imgUrl)??>${ctx!}${optionalFee.imgUrl?split(",")[0]}<#else>${ctx!}/assets-web/images/default_bg.jpg</#if>);">
				              <div class="featured-img-inner"></div>
				              </a>
				            </div>
				            <div class="" style="padding: 20px 10px 0 20px; float: left;width: 50%;">
				              <h4 class="atgrid-item-title"> <a class="productName" title="${optionalFee.name}">${optionalFee.name}</a> </h4>
				              <div class="atgrid-item-description-pc" >
                              	<p>${optionalFee.remark?if_exists}</p>
                              </div>
				            </div>
				            <div class="item-attributes-pc left" style="">
				              <div class="tour-date-tab-pc">
				                <div class="tour-date-tab-down-pc">
                                  <span  class="amount" price="${optionalFee.price}"><sup>${currencySign}</sup>${optionalFee.price}</span>
				                  <div class="featured-list">
				                  	<div class="special-checkbox">
				                     <input type="checkbox" name="optionalbox" class="special-input" />
				                    </div>
				                    <ul class="traveler" style="display:none">
				                      <li>
				                        <div class="button-content"> <i class="button-minus button-off"></i>
				                          <input type="text" name="" class="quantity" min="0" value="0" readonly>
				                          <i class="button-plus"></i> </div>
				                      </li>
				                    </ul>
				                  </div>
				                </div>
				              </div>
				            </div>
                            <div class="clear"></div>
				          </div>
				        </div>
				        </#list>
			        </div>
			        <div class="clear"></div>
			      </div>
			      <div class="clear"></div>
			    </div>
			  </div>
			  </#if>	  
			</div> 
			</#if>
			<#if itineraryListWithAdditionalProduct?? && itineraryListWithAdditionalProduct?has_content>           
			<div id="customTab" class="tab-pane additionalProduct">
			  <#list itineraryListWithAdditionalProduct as itinerary>
		      <#if itinerary.day lt 10>
		      	  <#assign day = '0' + itinerary.day>	
		      <#else>
		      	  <#assign day = itinerary.day>
		      </#if>
			  <div class="timeline" itineraryId="${itinerary.id}">
			    <div class="pay_messages steps_dd pay_un_check pay_bg">
			      <div class="pay_messages_tit itineraryTab"> <span class="pay_span daynumber">Day ${day}</span><b class="pay_messages_tit_b"></b> </div>
			      <div class="airport_list itineraryBody">
			      	<#if (itinerary.destinations)??>
			      	<#list itinerary.destinations as destination>			      	
			      	<div class="Custom-list-box destination" destinationId="${destination.id}">
			      		<h4 class="Custom-item-city">${destination.name}</h4>
			      		<#if (destination.admissiontickets)?? && (destination.admissiontickets)?has_content>
			      		<#list destination.admissiontickets as admissionticket>
				        <div productId="${admissionticket.productId}" class="product" type="1" style="margin-bottom:20px;">		
				          <div class="atgrid-item" style="padding-bottom: 20px;">
				            <div class="left" style="width: 30%;margin: 20px 0 0 10px;"> 
		                    	<a class="Custom-item-top-image" style=" background-image:url(<#if (admissionticket.imageurl)??>${ctx!}${admissionticket.imageurl?split(",")[0]}<#else>${ctx!}/assets-web/images/default_bg.jpg</#if>);">
				              		<div class="featured-img-inner"></div>
				             	</a>
				            </div>
				            <div class="" style="padding: 20px 10px 0 20px; float: left;width: 50%;">
				              <h4 class="atgrid-item-title"> <a class="productName" title="${admissionticket.ticketname}">${admissionticket.ticketname}</a> </h4>
				              <div class="atgrid-item-description-pc" title="${admissionticket.ticketdescription}">
		                   		 <p>${admissionticket.ticketdescription}</p>	
		                      </div>
				            </div>
				            <div class="item-attributes-pc left" style="">
				              <div class="tour-date-tab-pc">
				                <div class="tour-date-tab-down-pc">
		                          <span  class="amount" price="${admissionticket.price}"><sup>${currencySign}</sup>${admissionticket.price}</span>
				                  <div class="featured-list">
				                  	<div class="special-checkbox">
				                     <input type="checkbox" name="optionalbox" class="special-input" />
				                    </div>
				                    <ul class="traveler" style="display:none">
				                      <li>
				                        <div class="button-content"> <i class="button-minus button-off"></i>
				                          <input type="text" name="" class="quantity" min="0" value="0" readonly="">
				                          <i class="button-plus"></i> </div>
				                      </li>
				                    </ul>
				                  </div>
				                </div>
				              </div>
				            </div>
		                    <div class="clear"></div>
				          </div>
				        </div>				        
				        </#list>
				        </#if>
				        <#if (destination.foods)?? && (destination.foods)?has_content>
			      		<#list destination.foods as food>
				        <div productId="${food.productId}" type="2" class="product" style="margin-bottom:20px;">		
				          <div class="atgrid-item" style="padding-bottom: 20px;">
				            <div class="left" style="width: 30%;margin: 20px 0 0 10px;"> 
		                    	<a class="Custom-item-top-image" style=" background-image:url(<#if (food.imageurl)??>${ctx!}${food.imageurl?split(",")[0]}<#else>${ctx!}/assets-web/images/default_bg.jpg</#if>);">
				              		<div class="featured-img-inner"></div>
				             	</a>
				            </div>
				            <div class="" style="padding: 20px 10px 0 20px; float: left;width: 50%;">
				              <h4 class="atgrid-item-title"> <a class="productName" title="${food.foodname}">${food.foodname}</a> </h4>
				              <div class="atgrid-item-description-pc" title="${food.fooddescription}">
		                   		 <p>${food.fooddescription}</p>	
		                      </div>
				            </div>
				            <div class="item-attributes-pc left" style="">
				              <div class="tour-date-tab-pc">
				                <div class="tour-date-tab-down-pc">
		                          <span class="amount" price="${food.price}"><sup>${currencySign}</sup>${food.price}</span>
				                  <div class="featured-list">
				                  	<div class="special-checkbox">
				                     <input type="checkbox" name="optionalbox" class="special-input" />
				                    </div>
				                    <ul class="traveler" style="display:none">
				                      <li>
				                        <div class="button-content"> <i class="button-minus button-off"></i>
				                          <input type="text" name="" class="quantity" min="0" value="0" readonly>
				                          <i class="button-plus"></i> </div>
				                      </li>
				                    </ul>
				                  </div>
				                </div>
				              </div>
				            </div>
		                    <div class="clear"></div>
				          </div>
				        </div>				        
				        </#list>
				        </#if>		        
			        </div>
			        <div class="clear"></div>
			        </#list>
			        </#if>
			      </div>
			      <div class="clear"></div>
			    </div>
			  </div>
			  </#list>			  
			</div>
			</#if>
            <div id="galleryTab" class="tab-pane">
              <div class="tours-tabs-content padding-all">
              	<#--
                <div id="googleMapCanvas1" class="google-map" style="height: 400px;"></div>
                -->
	               <#if videoList?has_content >
	                  <#list videoList as video>
	                    <embed src="${(video.url)!}" allowFullScreen="true" quality="high" width="920" height="518" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash"></embed>  
	                  </#list>
	               </#if>
              </div>
              <div class="tours-tabs-content">
                <div class="row">
                  <div class="col-md-12 galleryImage">
                  </div>
                </div>
                <#--
                <div class="row">
                  <#list tUrl as imagUrl>
                  <#if imagUrl_index gt 0>
	                  <div class="col-sm-3 col-xs-4 product-thumbnails-item"> 
	                  	<a href="${ctx!}${imagUrl}" class="swipebox first" title="Boat on the Amazon River">
	                    	<img width="180" height="120"  src="${ctx!}${imagUrl}" class="" alt="Boat on the Amazon River">
	                    </a>
	                  </div>
                  </#if>
                  </#list>
                  <#--
                  <div class="col-sm-3 col-xs-4 product-thumbnails-item"> 
                  	<a href="images/tropical-rainforest-parrot1.jpg" class="swipebox first" title="Boat on the Amazon River">
                    	<img width="180" height="120"  src="${ctx!}/assets-web/images/tropical-rainforest-parrot1-180x120.jpg" class="" alt="Boat on the Amazon River">
                    </a>
                  </div>
                  <div class="col-sm-3 col-xs-4 product-thumbnails-item"> 
                  	<a href="images/tropical-rainforest-parrot1.jpg" class="swipebox first" title="Boat on the Amazon River">
                    	<img width="180" height="120"  src="${ctx!}/assets-web/images/tropical-rainforest-parrot1-180x120.jpg" class="" alt="Boat on the Amazon River">
                    </a>
                  </div>
                  <div class="col-sm-3 col-xs-4 product-thumbnails-item"> 
                  	<a href="images/tropical-rainforest-parrot1.jpg" class="swipebox first" title="Boat on the Amazon River">
                    	<img width="180" height="120"  src="${ctx!}/assets-web/images/costa-rica-volcano1-180x120.jpg" class="" alt="Boat on the Amazon River">
                    </a>
                  </div>
                </div>
                -->
              </div>
            </div>			
          </div>
          <!--手机-->
          <div class="panel-group visible-xs tabs-accordion wholeDiv" id="mobileDiv">
          	<div id="mobileTourlineDetailTab" class="panel panel-default">
            	<div class="panel-heading panel-box">Details</div>
                <div class="panel-collapse" style="display:none;">
                	<div class="panel-body">
                    	<div class="tours-tabs-info">
                            <div class="tours-tabs-info-item">
                              <div class="tours-tabs-info-item-content">
                                <div class="tours-tabs-info-item-icon"><i class="fa fa-clock-o"></i></div>
                                <div class="tours-tabs-info-item-title">${tourline.days} days</div>
                                <div class="tours-tabs-info-item-description">Duration</div>
                              </div>
                            </div>
                            <div class="tours-tabs-info-item">
                              <div class="tours-tabs-info-item-content">
                                <div class="tours-tabs-info-item-icon"><i class="fa fa-map-marker"></i></div>
                                <div class="tours-tabs-info-item-title">${(tourline.startDestination)!}/${(tourline.endDestination)!}</div>
                                <div class="tours-tabs-info-item-description">Start /End City</div>
                              </div>
                            </div>
                            <div class="tours-tabs-info-item">
                              <div class="tours-tabs-info-item-content">
                                <div class="tours-tabs-info-item-icon"><i class="fa fa-building-o"></i></div>
                                <div class="tours-tabs-info-item-title">Hotel <#if hotelAssociationNumber == 0>Not Include<#else>Include</#if></div>
                                <div class="tours-tabs-info-item-description">Hotel</div>
                              </div>
                            </div>
                            <div class="tours-tabs-info-item">
                              <div class="tours-tabs-info-item-content">
                                <div class="tours-tabs-info-item-icon"><i class="fa fa-plane"></i></div>
                                <div class="tours-tabs-info-item-title">Air Ticket <#if airticketAssociationNumber == 0>Not Include<#else>Include</#if></div>
                                <div class="tours-tabs-info-item-description">Air Ticket</div>
                              </div>
                            </div>
                        </div>       
                    	<div class="tours-tabs-content">
							<ul class="list-block list-block-tour-tabs">
							<#if tagList?has_content>
								<#list tagList as tag>
								<li style="background:#${(tag.bgcolor)!};"><a href="">${(tag.name)!}</a></li>
								</#list>
							</#if>
                            </ul>
                         	<p>${tourline.productProductid.briefinfo}</p>
                            <table class="table table-bordered tours-tabs-table" style="height: 233px;" width="862">
                                  <tr>
                                    <td><strong>ARRIVAL/RETURN LOCATION</strong></td>
                                    <td>${(tourline.startDestination)!}/${(tourline.endDestination)!}</td>
                                  </tr>
                                  <tr>
                                    <td><strong>Departure Notice</strong></td>
                                    <td>Please check at the airport at lease 3 hours before departure.</td>
                                  </tr>
                                  <tr>
                                    <td><strong>INCLUDED</strong></td>
                                    <td>
                                      <table class="table table-bordered" id="mobileIncludeTable" style="height: 62px;" width="300">
				                          <tbody>
				                          	<#list inludeItems as serviceItem>
				                          		<#if serviceItem_index%2 = 0>
				                          			<tr>
				                          		</#if>
				                          			<td><i class="fa icon-tick icon-tick-on ${serviceItem.ico}"></i>${serviceItem.tittle}</td>
				                          		<#if serviceItem_index%2 = 1 || !serviceItem_has_next >
				                          			</tr>
				                          		</#if>
				                          	</#list>
				                          </tbody>
                                      </table>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td><strong>NOT INCLUDED</strong></td>
                                    <td>
                                      <table class="table table-bordered" id="mobileNotIncludeTable" style="height: 62px;" width="300">
				                          <tbody>
				                            <#list notIncludeItems as serviceItem>
				                          		<#if serviceItem_index%2 = 0>
				                          			<tr>
				                          		</#if>
				                          			<td><i class="fa icon-tick icon-tick-on ${serviceItem.ico}"></i>${serviceItem.tittle}</td>
				                          		<#if serviceItem_index%2 = 1 || !serviceItem_has_next >
				                          			</tr>
				                          		</#if>
				                          	</#list>
				                          </tbody>
                                      </table>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td><b>HIGHLIGHT</b></td>
                                    <td>
                                      <table class="table table-bordered" style="height: 62px;" width="300">
				                          <tbody>
				                          	<#list tourline.highlights?split("^^") as highlight>  
				                            <tr>
				                              <td><i class="fa fa-star icon-tick icon-tick-star"></i>${highlight}</td>
				                            </tr>
				                            </#list>
				                          </tbody>
                                      </table>
                                    </td>
                                  </tr>
                                  <tr>
				                      <td><b>NOTICE</b></td>
				                      <td>${(tourline.notice)!}</td>
				                    </tr>
                                </tbody>
                              </table>
                            <#-- 
                            <p>Potenti et sociosqu interdum condimentum lorem facilisi litora, sociosqu. Eros etiam luctus turpis sodales laoreet, tristique commodo et id cras habitant sollicitudin. Viverra, rutrum ultrices imperdiet laoreet sociosqu id dictum.</p>
                            <p>Dignissim inceptos nonummy iaculis parturient velit praesent tristique pharetra netus semper auctor quisque ultrices diam. Lectus aliquam sed pretium sem egestas ad sodales aenean cras litora est Aptent mattis praesent.</p>
                            -->
						</div>
	        		</div>
	        	</div>            
	        </div>
	        <div id="mobileItineraryTab" class="panel panel-default">
            	<div class="panel-heading panel-box">Itinerary</div>
                <div class="panel-collapse" style="display:none;">
                	<div class="panel-body">
                    	<div class="tours-tabs-content">
                        	<div class="timeline">
                        	  <#if itineraryList?has_content>
				              	<#list itineraryList as itinerary>
					                <div class="timeline-item">
					                  <div class="timeline-item-icon-wrap">
					                    <div class="timeline-item-icon"></div>
					                  </div>
					                  <div class="timeline-item-content">
					                    <div class="timeline-item-day-inf"> <span class="timeline-item-day-day">DAY ${(itinerary.day)!}</span>
					                      <div class="timeline-item-day-dest-list">${(itinerary.title)!}</div>
					                    </div>
					                    <div class="timeline-item-description">${(itinerary.content)!}</div>
					                    <#if (itinerary.hotel)??>
					                    <div class="timeline-hotel">
					                    <span class="left"><i class="fa icon-tick icon-tick-on fa-building"></i></span>
									        <span style="display:inline-block;width:85%;" class="left">
				                            	<span style="display:block;">${(itinerary.hotel.hotelname)!}</span>
				                              	<span style="display:block;">${(itinerary.hotel.hoteldescription)!}</span>
				  							</span>
				                            <div class="clear"></div>
				                        </div>
				                        </#if>
				                        <#if (itinerary.attractions)??>
				                        <div class="timeline-scenic">
				                            <span class="left"><i class="fa icon-tick icon-tick-on fa-camera-retro"></i></span>
				                            <div class="timeline-scenic-box left">
								                <#list itinerary.attractions as attraction>
												<#if attraction_index != 0 && attraction_index % 3 == 0>
				                                	<div class="clear"></div>
				                                </#if>
				                            	<div class="timeline-scenic-img-box left">
				                                	<p class="timeline-scenic-img"><img src="${(attraction.imageurl)!}"></p>
				                                    <p class="timeline-scenic-tit">${(attraction.name)!}</p>
				                                </div>
				                                </#list>
				                            </div>
				                            <div class="clear"></div>
				                        </div>
				                        </#if>
					                  </div>
					                </div>
					                </#list>
				                </#if>
				                <#--
                                <div class="timeline-item">
                                  <div class="timeline-item-icon-wrap">
                                    <div class="timeline-item-icon"></div>
                                  </div>
                                  <div class="timeline-item-content"> <span class="timeline-item-day-num">1</span>
                                    <div class="timeline-item-day-inf"> <span class="timeline-item-day-day">DAY</span>
                                      <div class="timeline-item-day-dest-list">Departure</div>
                                    </div>
                                    <div class="timeline-item-description">Tortor elementum egestas metus potenti habitasse tempus natoque senectus commodo rutrum quisque fermentum. Nisi velit primis dapibus odio consequat facilisi sollicitudin porta nulla tellus sagittis platea tempor sed parturient convallis consectetuer Vulputate curae; pharetra.</div>
                                  </div>
                                </div>
                                <div class="timeline-item">
                                  <div class="timeline-item-icon-wrap">
                                    <div class="timeline-item-icon"></div>
                                  </div>
                                  <div class="timeline-item-content"> <span class="timeline-item-day-num">2</span>
                                    <div class="timeline-item-day-inf"> <span class="timeline-item-day-day">DAY</span>
                                      <div class="timeline-item-day-dest-list">Departure</div>
                                    </div>
                                    <div class="timeline-item-description">Tortor elementum egestas metus potenti habitasse tempus natoque senectus commodo rutrum quisque fermentum. Nisi velit primis dapibus odio consequat facilisi sollicitudin porta nulla tellus sagittis platea tempor sed parturient convallis consectetuer Vulputate curae; pharetra.</div>
                                  </div>
                                </div>
                                <div class="timeline-item">
                                  <div class="timeline-item-icon-wrap">
                                    <div class="timeline-item-icon"></div>
                                  </div>
                                  <div class="timeline-item-content"> <span class="timeline-item-day-num">3</span>
                                    <div class="timeline-item-day-inf"> <span class="timeline-item-day-day">DAY</span>
                                      <div class="timeline-item-day-dest-list">Departure</div>
                                    </div>
                                    <div class="timeline-item-description">Tortor elementum egestas metus potenti habitasse tempus natoque senectus commodo rutrum quisque fermentum. Nisi velit primis dapibus odio consequat facilisi sollicitudin porta nulla tellus sagittis platea tempor sed parturient convallis consectetuer Vulputate curae; pharetra.</div>
                                  </div>
                                </div>
                                <div class="timeline-item">
                                  <div class="timeline-item-icon-wrap">
                                    <div class="timeline-item-icon"></div>
                                  </div>
                                  <div class="timeline-item-content"> <span class="timeline-item-day-num">4</span>
                                    <div class="timeline-item-day-inf"> <span class="timeline-item-day-day">DAY</span>
                                      <div class="timeline-item-day-dest-list">Departure</div>
                                    </div>
                                    <div class="timeline-item-description">Tortor elementum egestas metus potenti habitasse tempus natoque senectus commodo rutrum quisque fermentum. Nisi velit primis dapibus odio consequat facilisi sollicitudin porta nulla tellus sagittis platea tempor sed parturient convallis consectetuer Vulputate curae; pharetra.</div>
                                  </div>
                                </div>
                                -->
                              </div>
						</div>
					</div>
        		</div>
            </div>
            <div id="mobileDepartureTab" class="panel panel-default">
            	<div class="panel-heading panel-box departureDateDiv">Dates & Rates</div>
                <div class="panel-collapse" style="display:none;">
                	<div class="panel-body">
                    	<div class="tours-tabs-content">
                        	<div class="choose-main">
                        		<#if departureList??>
                                <div class="people-box">
                                    <div class="people-choose">
                                      <label class="people-choose-departure">Please select your departure city</label>
                                      <select id="mobileDepartureSelect" class="people-departure-select">
                                        <option value="12345">Please click here to choose your departure city</option>
                                      	<#list departureList as departure>
                                        <option value="${departure.id}" <#if departure_index == 0>checked="checked"</#if>>${departure.name} ${departure.city}</option>
                                        </#list>
                                      </select>
                                      <div class="clear"></div>
                                    </div>
                                </div>
                                </#if>
                                <div class="people-box">
                                    <div class="people-choose-date-tit">
                                    Please select a date below (For air-inclusive package, the date showing below is the departure date; for LandOnly package, the date showing below is the arrival date).
                                    </div>
                                    <div id="mobileDatesDiv" class="people-choose-date dateMain">
                                    </div>
                                    <div class="poeple-choose-btn right">
		                            	<div class="people-choose-jiantou left lastMonth" style="margin-right:30px;">
		                                	<a href="javascript:;" onclick="lastMonth(this);" class="jiantou-left"><i class="fa fa-chevron-left"></i></a>
		                                </div>
		                                <div class="people-choose-jiantou right nextMonth">
		                                	<a href="javascript:;" onclick="nextMonth(this);" class="jiantou-right"><i class="fa fa-chevron-right"></i></a>
		                                </div>
		                            </div>                                    
                                    <#if transferFeeList?has_content>
                                    <div class="people-choose">
                                      <label class="people-choose-departure">Transfer</label>
                                      <select id="mobileTransferSelect" class="people-departure-select">
                                      	<option value="">-- No Transfer --</option>
                                      	<#list transferFeeList as transfer>
                                        <option value="${transfer.id}" price="${transfer.price}">${transfer.name} <#if (transfer.price)?? && transfer.price = 0>Free shuttle<#else>${currencySign} ${transfer.price}</#if></option>
                                        </#list>
                                      </select>
                                      <div class="clear"></div>
                                    </div>   
									</#if> 
                                    <div class="people-choose" style="display:none;">
                                      <label class="people-choose-departure">Airtickets</label>
                                      <select id="mobileAirticketsSelect" class="people-departure-select">
                                      	
                                      </select>
                                      <div class="clear"></div>
                                    </div>                                
		                            <div class="clear"></div>
                                </div>
                            </div>
                            <#if tourline.flightnotice??>
				                <div class="people-choose" style="color:#F90">
				                  <!--<h4>Airline description:</h4>--><br/>
				                  <p>Airline description:<br/>
				                   <#list tourline.flightnotice?split("^^") as fnotice>  
								       ${fnotice}<br/>
								   </#list>
				                  </p>
				                  <div class="clear"></div>
				                </div>
			                </#if>
						</div>
					</div>
        		</div>
            </div>            
            <#if itineraryListWithSelfPay?has_content || optionalFeeList?has_content>
			<div class="panel panel-default optionalTour">
			  <div class="panel-heading panel-box">Additional Services</div>
			  <div class="panel-collapse" style="display:none;">
			    <div class="panel-body">
			      <div id="mobileOptionalTourTab" class="tours-tabs-content">
			      	<#if itineraryListWithSelfPay?has_content>
			      	<#list itineraryListWithSelfPay as itinerary>
			      	<#if itinerary.day lt 10>
			      		<#assign day = '0' + itinerary.day>	
			      	<#else>
			      		<#assign day = itinerary.day>
			      	</#if>
			        <div class="timeline selfpayitinerary" itineraryId="${itinerary.id}">
			          <div class="steps_dd pay_un_check pay_bg" >
			            <div class="pay_messages_tit itineraryTabMobile"> <span class="pay_span daynumber">Day ${day}</span><b class="pay_messages_tit_b"></b> </div>
			            <div class="airport_list itineraryBodyMobile" <#if itinerary_index != 0>style="display:none"</#if>>
			              <#list itinerary.destinations as destination>
			              <div destinationId="${destination.id}" class="Custom-list-box destination">
			              	  <h4 class="Custom-item-city" >${destination.name}</h4>
			              	  <#list destination.selfpayList as selfpay>
				              <div class="product" productId="${selfpay.id}" style="visibility: visible; margin-bottom:20px;">
				                <div class="atgrid-item">
				                  <div class="atgrid-item-top"> <a class="Custom-item-top-image" style=" background-image:url(<#if (selfpay.imgUrl)??>${ctx!}${selfpay.imgUrl?split(",")[0]}<#else>${ctx!}/assets-web/images/default_bg.jpg</#if>);">
				                    <div class="featured-img-inner"></div>
				                    </a>
				                    <div class="atgrid-item-price">
				                      <div class="atgrid-item-price-button"> <ins><span class="amount" price="${selfpay.price}">${currencySign}${selfpay.price}</span></ins> </div>
				                    </div>
				                  </div>
				                  <div class="Custom-item-content">
				                    <h4 class="atgrid-item-title"> <a class="productName" title="${selfpay.name}">${selfpay.name}</a> </h4>
				                    <div class="atgrid-item-description-pc"><p>${(selfpay.remark)!}</p></div>
				                  </div>
				                  <div class="item-attributes">
				                    <div class="tour-date-tab">
				                      <div class="tour-date-tab-down">
				                        <div class="featured-list">
				                        	<div class="special-checkbox">
						                     <input type="checkbox" name="optionalbox" class="special-input" />
						                    </div>
				                          <ul class="traveler" style="display:none">
				                            <li>
				                              <div class="button-content"> <i class="button-minus button-off"></i>
				                                <input type="text" name="" class="quantity" min="0" value="0" readonly>
				                                <i class="button-plus"></i> </div>
				                            </li>
				                          </ul>
				                        </div>
				                      </div>
				                    </div>
				                  </div>
				                </div>
				              </div>
				              </#list>
				          </div>
				          </#list>
			            </div>
			            <div class="clear"></div>
			          </div>
			        </div>
			        </#list>
			        </#if>
			        <#if optionalFeeList?has_content>
			        <div class="timeline selfpay">
			          <div class="steps_dd pay_un_check pay_bg" >
			            <div class="pay_messages_tit itineraryTabMobile"> <span class="pay_span daynumber">Other Additional Services</span><b class="pay_messages_tit_b"></b> </div>
			            <div class="airport_list itineraryBodyMobile" style="display:none">
			              <div class="Custom-list-box">
			              	  <h4 class="Custom-item-city" ></h4>
			              	  <#list optionalFeeList as optionalFee>
				              <div class="product" productId="${optionalFee.id}" style="visibility: visible; margin-bottom:20px;">
				                <div class="atgrid-item">
				                  <div class="atgrid-item-top"> <a class="Custom-item-top-image" style=" background-image:url(<#if (optionalFee.imgUrl)??>${ctx!}${optionalFee.imgUrl?split(",")[0]}<#else>${ctx!}/assets-web/images/default_bg.jpg</#if>);">
				                    <div class="featured-img-inner"></div>
				                    </a>
				                    <div class="atgrid-item-price">
				                      <div class="atgrid-item-price-button"> <ins><span class="amount" price="${optionalFee.price}">${currencySign}${optionalFee.price}</span></ins> </div>
				                    </div>
				                  </div>
				                  <div class="Custom-item-content">
				                    <h4 class="atgrid-item-title"> <a class="productName" title="${optionalFee.name}">${optionalFee.name}</a> </h4>
				                    <div class="atgrid-item-description-pc"><p>${optionalFee.remark?if_exists}</p></div>
				                  </div>
				                  <div class="item-attributes">
				                    <div class="tour-date-tab">
				                      <div class="tour-date-tab-down">
				                        <div class="featured-list">
			                        	  <div class="special-checkbox">
					                       <input type="checkbox" name="optionalbox" class="special-input" />
					                      </div>
					                      <ul class="traveler" style="display:none">
				                            <li>
				                              <div class="button-content"> <i class="button-minus button-off"></i>
				                                <input type="text" name="" class="quantity" min="0" value="0" readonly>
				                                <i class="button-plus"></i> </div>
				                            </li>
				                          </ul>
				                        </div>
				                      </div>
				                    </div>
				                  </div>
				                </div>
				              </div>
				              </#list>
				          </div>
			            </div>
			            <div class="clear"></div>
			          </div>
			        </div>	
			        </#if>
			      </div>
			    </div>
			  </div>
			</div> 
			</#if>
			<#if itineraryListWithAdditionalProduct?? && itineraryListWithAdditionalProduct?has_content>              
			<div class="panel panel-default additionalProduct">
			  <div class="panel-heading panel-box">Additional Products</div>
			  <div class="panel-collapse" style="display:none;">
			    <div class="panel-body">
			      <div id="mobileCustomTab" class="tours-tabs-content">
			      	<#list itineraryListWithAdditionalProduct as itinerary>
			      	<#if itinerary.day lt 10>
			      		<#assign day = '0' + itinerary.day>	
			      	<#else>
			      		<#assign day = itinerary.day>
			      	</#if>
			        <div class="timeline" itineraryId="${itinerary.id}">
			          <div class="steps_dd pay_un_check pay_bg">
			            <div class="pay_messages_tit itineraryTabMobile"> <span class="pay_span daynumber">Day ${day}</span><b class="pay_messages_tit_b"></b> </div>
			            <div class="airport_list itineraryBodyMobile" <#if itinerary_index != 0>style="display:none"</#if>>
			              <#if (itinerary.destinations)??>
			              <#list itinerary.destinations as destination>
			              <div destinationId="${destination.id}" class="Custom-list-box destination">
			              	  <h4 class="Custom-item-city" >${destination.name}</h4>
			              	  <#if (destination.admissiontickets)?? && (destination.admissiontickets)?has_content>
			              	  <#list destination.admissiontickets as admissionticket>
				              <div class="product" productId="${admissionticket.productId}" type="1" style="visibility: visible; margin-bottom:20px;">
				                <div class="atgrid-item">
				                  <div class="atgrid-item-top"> <a class="Custom-item-top-image" style=" background-image:url(<#if (admissionticket.imageurl)??>${ctx!}${admissionticket.imageurl?split(",")[0]}<#else>${ctx!}/assets-web/images/default_bg.jpg</#if>);">
				                    <div class="featured-img-inner"></div>
				                    </a>
				                    <div class="atgrid-item-price">
				                      <div class="atgrid-item-price-button"> <ins><span class="amount" price="${admissionticket.price}">${currencySign}${admissionticket.price}</span></ins> </div>
				                    </div>
				                  </div>
				                  <div class="Custom-item-content">
				                    <h4 class="atgrid-item-title"> <a class="productName" title="${admissionticket.ticketname}">${admissionticket.ticketname}</a> </h4>
				                    <div class="atgrid-item-description-pc" title="${admissionticket.ticketdescription}"><p>${admissionticket.ticketdescription}</p></div>
				                  </div>
				                  <div class="item-attributes">
				                    <div class="tour-date-tab">
				                      <div class="tour-date-tab-down">
				                        <div class="featured-list">
				                        	<div class="special-checkbox">
						                     <input type="checkbox" name="optionalbox" class="special-input" />
						                    </div>
						                    <ul class="traveler" style="display:none">
					                            <li>
					                              <div class="button-content"> <i class="button-minus button-off"></i>
					                                <input type="text" name="" class="quantity" min="0" value="0" readonly>
					                                <i class="button-plus"></i> </div>
					                            </li>
					                          </ul>
				                        </div>
				                      </div>
				                    </div>
				                  </div>
				                </div>
				              </div>
				              </#list>
				              </#if>
				              <#if (destination.foods)?? && (destination.foods)?has_content>
			              	  <#list destination.foods as food>
				              <div class="product" productId="${food.productId}" type="2" style="visibility: visible; margin-bottom:20px;">
				                <div class="atgrid-item">
				                  <div class="atgrid-item-top"> <a class="Custom-item-top-image" style=" background-image:url(<#if (food.imageurl)??>${ctx!}${food.imageurl?split(",")[0]}<#else>${ctx!}/assets-web/images/default_bg.jpg</#if>);">
				                    <div class="featured-img-inner"></div>
				                    </a>
				                    <div class="atgrid-item-price">
				                      <div class="atgrid-item-price-button"> <ins><span class="amount" price="${food.price}">${currencySign}${food.price}</span></ins> </div>
				                    </div>
				                  </div>
				                  <div class="Custom-item-content">
				                    <h4 class="atgrid-item-title"> <a class="productName" title="${food.foodname}">${food.foodname}</a> </h4>
				                    <div class="atgrid-item-description-pc" title="${food.fooddescription}"><p>${food.fooddescription}</p></div>
				                  </div>
				                  <div class="item-attributes">
				                    <div class="tour-date-tab">
				                      <div class="tour-date-tab-down">
				                        <div class="featured-list">
				                        <div class="special-checkbox">
					                     <input type="checkbox" name="optionalbox" class="special-input" />
					                    </div>
					                    <ul class="traveler" style="display:none">
				                            <li>
				                              <div class="button-content"> <i class="button-minus button-off"></i>
				                                <input type="text" name="" class="quantity" min="0" value="0" readonly>
				                                <i class="button-plus"></i> </div>
				                            </li>
				                          </ul>
				                        </div>
				                      </div>
				                    </div>
				                  </div>
				                </div>
				              </div>
				              </#list>
				              </#if>				              
				          </div>
				          </#list>
				          </#if>
			            </div>
			            <div class="clear"></div>
			          </div>
			        </div>
			        </#list>			        
			      </div>
			    </div>
			  </div>
			</div>
			</#if>            
            <div id="mobileGalleryTab" class="panel panel-default">
            	<div class="panel-heading panel-box galleryDiv">Gallery</div>
                <div class="panel-collapse" style="display:none;">
                	<div class="panel-body">
                    	<div class="tours-tabs-content">				
                            <#--
                            <div class="row">
                              <div class="col-md-12">
				               <#if videoList?has_content >
				                  <#list videoList as video>
				                    <embed src="${(video.url)!}" allowFullScreen="true" quality="high" width="278" height="156" align="middle" allowScriptAccess="always" type="application/x-shockwave-flash"></embed>  
				                  </#list>
				               </#if>
                              </div>
                            </div>  
                            -->                  		
                            <div class="row">
                              <div class="col-md-12 galleryImage">

                              	<#--
                                <a href="images/costa-rica.jpg" title="Caption for image A"><img src="images/costa-rica.jpg"></a>
                                -->
                              </div>
                            </div>
                            <#--
                            <div class="row">
                        	   <#list tUrl as imagUrl>
				                <#if imagUrl_index gt 0>
					                  <div class="col-sm-3 col-xs-4 product-thumbnails-item"> 
					                  	<a href="${ctx!}${imagUrl}" class="swipebox first" title="Boat on the Amazon River">
					                    	<img width="180" height="120"  src="${ctx!}${imagUrl}" class="" alt="Boat on the Amazon River">
					                    </a>
					                  </div>
				                </#if>
				                </#list>
					           <#--     
                              <div class="col-sm-3 col-xs-4 product-thumbnails-item"> 
                                <a href="images/costa-rica-volcano1.jpg" class="swipebox first" title="Boat on the Amazon River">
                                    <img width="180" height="120"  src="images/costa-rica-volcano1-180x120.jpg" class="" alt="Boat on the Amazon River">
                                </a>
                              </div>
                              <div class="col-sm-3 col-xs-4 product-thumbnails-item"> 
                                <a href="images/tropical-rainforest-parrot1.jpg" class="swipebox first" title="Boat on the Amazon River">
                                    <img width="180" height="120"  src="images/tropical-rainforest-parrot1-180x120.jpg" class="" alt="Boat on the Amazon River">
                                </a>
                              </div>
                              <div class="col-sm-3 col-xs-4 product-thumbnails-item"> 
                                <a href="images/tropical-rainforest-parrot1.jpg" class="swipebox first" title="Boat on the Amazon River">
                                    <img width="180" height="120"  src="images/tropical-rainforest-parrot1-180x120.jpg" class="" alt="Boat on the Amazon River">
                                </a>
                              </div>
                              <div class="col-sm-3 col-xs-4 product-thumbnails-item"> 
                                <a href="images/tropical-rainforest-parrot1.jpg" class="swipebox first" title="Boat on the Amazon River">
                                    <img width="180" height="120"  src="images/costa-rica-volcano1-180x120.jpg" class="" alt="Boat on the Amazon River">
                                </a>
                              </div>
                            </div>
                            -->
                          </div>
					</div>
        		</div>
            </div>            
            <div class="panel panel-default">
            	<div class="panel-heading"><a href="#tourBooking" >Book the tour</a></div>
                <div id="undefined-collapse" class="panel-collapse">
                	<div class="panel-body js-tabcollapse-panel-body"></div>
                </div>
            </div>
          <!-- end mobile -->
          <!-- .tab-content --> 
        </div>
        </div>
        <!-- .tour-tabs --> 
      </main>
      
      <div id="promotionShow" class="price-decoration-label-round" style="display:none;background-color:#ff662a;"><span>Last Minute</span></div>
      <a name="tourBooking"></a>
      <aside id="showDetailsDiv" class="col-md-3 sidebar" role="complementary">
        <div class="price-decoration block-after-indent">
          <div class="price-decoration-value"> <ins id="priceShow"></ins><span>One Person</span>
            <div class="clear"></div>
          </div>
        </div>        
        <div class="form-block">
          <h3 class="form-block-title">Book the tour</h3>
          <form id="tourBookingForm" method="POST" action="${ctx!}/book_tour.htm">
            <div class="form-block-item form-block-field-name">
              <input id="firstName" name="firstName" value="" type="text" placeholder="First Name">
              <i class="fa fa-user"></i> </div>
            <div class="form-block-item form-block-field-name">
              <input id="lastName" name="lastName" value="" type="text" placeholder="Last Name">
              <i class="fa fa-user"></i> </div>
            <div class="form-block-item form-block-field-email">
              <input id="email" name="email" value="" type="text" placeholder="Email address">
              <i class="fa fa-envelope"></i> </div>
            <div class="form-block-item form-block-field-phone">
              <input id="phone" name="phone" value="" type="text" placeholder="Phone number">
              <i class="fa fa-phone"></i> </div>
            <div id="dateInputDiv" class="form-block-item form-block-field-date">
		      <input id="dateInput" type="text" name="departureDate" readOnly="readOnly" class="Wdate"/>
		      <i class="fa fa-calendar"></i>              
            </div>
            <div class="form-block-item form-block-field-quantity">
              <input id="totalNumberInput" name="totalNumber" value="1" type="number" placeholder="Guests" onclick="adjustTotalNumber(this,1);">
              <i class="fa fa-user-plus"></i> </div>
              <!--
            <div class="form-block-item form-block-field-email">
              <input id="departCity" name="departCity" value="" type="text" placeholder="Depart city">
              <i class="fa fa-map-marker"></i> </div>
              -->
            <div id="agentCodeInput" class="form-block-item form-block-field-email">
              <input name="agentCode" type="text" placeholder="Agent Code(Not Required)">
              <i class="fa fa-user-plus"></i> </div>  
            <div class="form-block-price-details" data-role="price-explanation"></div>
           	<input id="tourDateIdInput" type="hidden" name="tourDateId">
           	<input id="productId" type="hidden" name="productId" value="${(tourline.productid)!}">
           	<input id="departureIdInput" type="hidden" name="departureId" />
            <input class="form-block-button" id="submitButton" type="button" value="Book Now">
          </form>
        </div>
      </aside>
    </div>
  </div>
</section>
<section id="reviewSection" class="featured-destinations" style="background-color:#fff;">
  <div class="container">
    <main class="col-md-9">
      <div id="comments" class="tour-reviews">
      	<#if reviewNumber gt 0>
			<div class="tour-reviews-rating-total">
			  <div class="tour-reviews-rating-total-stars">
			  	  <#list 1..starNumber as i> 
			  		<i class="fa fa-star voted"></i>
			  	  </#list> 
			  </div>
			  <#if reviewNumber = 1>
			  <div class="tour-reviews-rating-total-text">${avgScore} based on ${reviewNumber} review</div>  
			  </#if>
			  <#if reviewNumber gt 1>
			  <div class="tour-reviews-rating-total-text">${avgScore} based on ${reviewNumber} reviews</div>	
			  </#if>
			</div>
		</#if>
        <div class="tour-reviews-items">
          <div id="commentDiv" class="tour-reviews-item margin-left margin-right padding-top padding-bottom">
          </div>
          <!-- #comment-## --> 
        </div>
        <div id="tour-leave-review" class="tour-reviews-form padding-all">
          <h3 class="tour-reviews-form-title">Leave a Review</h3>
          <div id="respond" class="comment-respond">
            <form action="${ctx!}/front/review/submitReview.do" method="post" id="commentform" class="comment-form">
              <div class="tour-reviews-form-item">
                <input id="title" name="title" type="text" placeholder="Title" value="" size="30" class="form-validation-item" data-original-title="" title="">
              </div>
              <div class="tour-reviews-form-item">
                <input id="reviewEmail" name="email" type="text" placeholder="Email" value="" size="30" class="form-validation-item" data-original-title="" title="">
              </div>
              <div class="tour-reviews-form-rating"> 
                <div class="tour-reviews-form-rating-label">Intertrips</div>
                <div class="star" id="vote-star1">
                    <input type="hidden" value="5" id="starsResult1" name="wenjingScore">
                    <a id="star1-1" href="javascript:;" class=""><i class="fa fa-star"></i></a>
                    <a id="star1-2" href="javascript:;" class=""><i class="fa fa-star"></i></a>
                    <a id="star1-3" href="javascript:;" class=""><i class="fa fa-star"></i></a>
                    <a id="star1-4" href="javascript:;" class=""><i class="fa fa-star"></i></a>
                    <a id="star1-5" href="javascript:;" class=""><i class="fa fa-star"></i></a>
                </div>
               	<div id="star-tip1" class="tip">Perfect</div>
                
                <select name="rating" id="rating" style="display: none;">
                  <option value="">Rate…</option>
                  <option value="5">Perfect</option>
                  <option value="4">Good</option>
                  <option value="3">Average</option>
                  <option value="2">Not that bad</option>
                  <option value="1">Very Poor</option>
                </select>
                <div class="clear"></div>
                
                <div class="tour-reviews-form-rating-label">Tour Group</div>
                <div class="star" id="vote-star2">
                    <input type="hidden" value="5" id="starsResult2" name="tourGroupScore">
                    <a id="star2-1" href="javascript:;" class=""><i class="fa fa-star"></i></a>
                    <a id="star2-2" href="javascript:;" class=""><i class="fa fa-star"></i></a>
                    <a id="star2-3" href="javascript:;" class=""><i class="fa fa-star"></i></a>
                    <a id="star2-4" href="javascript:;" class=""><i class="fa fa-star"></i></a>
                    <a id="star2-5" href="javascript:;" class=""><i class="fa fa-star"></i></a>
                </div>
               	<div id="star-tip2" class="tip">Perfect</div>
                <select name="rating" id="rating" style="display: none;">
                  <option value="">Rate…</option>
                  <option value="5">Perfect</option>
                  <option value="4">Good</option>
                  <option value="3">Average</option>
                  <option value="2">Not that bad</option>
                  <option value="1">Very Poor</option>
                </select>
                <div class="clear"></div>
              </div>
              <div class="tour-reviews-form-item">
                <textarea id="reviewComment" name="content" placeholder="Your Review" class="form-validation-item" data-original-title="" title=""></textarea>
              </div>
              <p class="form-submit">
              	<input type="hidden" id="memberIdInput" name="memberId"/>
              	<input type="hidden" name="productId" value="${(tourline.productid)!}" />
                <input name="submit" type="button" id="submit" class="atbtn" value="Submit">
              </p>
            </form>
          </div>
          <!-- #respond --> 
        </div>
      </div>
    </main>
  </div>
</section>
<#--
<section class="regular" style="background-image: url(${ctx!}/assets-web/background/newsletter_bg.jpg);">
  <div class="container">
    <div class="row">
      <div class="title-row" style=" text-align:center;">
        <h3 class="title-entry-2" style="font-size:20px; padding-right:0; color:#fff;">JOIN THE NEWSLETTER</h3>
      </div>
      <div class="col-xs-12" >
        <div class="home-search-field">
          <form class="big-search" role="search" method="get" action="">
            <input type="text" name="s" placeholder="Your Email..." value="">
            <button type="submit">Subscribe</button>
            <div class="clear"></div>
          </form>
        </div>
        <div class="clear"></div>
      </div>
    </div>
  </div>
</section>
-->
<#--bottom-->
<#include "/front/include/bottom.ftl"/>
<#--bottom-->
<div style="display:none;">
<input type="hidden" id="inputTourlineId" value="${(tourline.id)!}"/>
<input type="hidden" id="inputProductId" value="${(tourline.productid)!}"/>
<input type="hidden" id="inputCostNumber" value="${(tourline.cost.id)!}"/>
</div>
<script type="text/javascript">
	var tourlineId = "${(tourline.id)!}";
	var productId = "${(tourline.productid)!}";
	var costnumber = "${(tourline.cost.id)!}";
</script>
<script type='text/javascript' src="${ctx!}/assets-web/js/order-min.js"></script>
<script type='text/javascript' src='${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js'></script>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-38017454-1', 'auto');
  ga('send', 'pageview');

</script>

<script type="text/javascript">
	$(function () {
		$("img").delayLoading({
			defaultImg: "${ctx!}/assets-web/images/loading.jpg",           // 预加载前显示的图片
			errorImg: "",                        // 读取图片错误时替换图片(默认：与defaultImg一样)
			imgSrcAttr: "originalSrc",           // 记录图片路径的属性(默认：originalSrc，页面img的src属性也要替换为originalSrc)
			beforehand: 0,                       // 预先提前多少像素加载图片(默认：0)
			event: "scroll",                     // 触发加载图片事件(默认：scroll)
			duration: "normal",                  // 三种预定淡出(入)速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认:"normal"
			container: window,                   // 对象加载的位置容器(默认：window)
			success: function (imgObj) { },      // 加载图片成功后的回调函数(默认：不执行任何操作)
			error: function (imgObj) { }         // 加载图片失败后的回调函数(默认：不执行任何操作)
		});
	});
</script>
<script type="text/javascript">
if(window.location.href.split("?")[1] == 'soldout'){
    alertWarn('The number of your passengers is not available.');
}
if(window.location.href.split("?")[1] == 'invalid'){
    alertWarn('This booking is expired, please book again.');
}

var $firstNameInput = $("#firstName");
var $lastNameInput = $("#lastName");
var $emailInput = $("#email");
var $phoneInput = $("#phone");
var $dateInput = $("#dateInput");
var $tourDateIdInput = $("#tourDateIdInput");
var $totalNumberInput = $("#totalNumberInput");
var $departureIdInput = $("#departureIdInput");

member = getMember();
if(member != null && member != ''){
	if (member.memberinformation.firstName != null) {
		$firstNameInput.val(member.memberinformation.firstName);
	}	
	if (member.memberinformation.lastName != null) {
		$lastNameInput.val(member.memberinformation.lastName);
	}
	$emailInput.val(member.memberinformation.email);
	$phoneInput.val(member.memberinformation.usermobile);
	$("#reviewEmail").val(member.memberinformation.email);
	$("#memberIdInput").val(member.id);
}

var galleryImageHtml = "";
<#if tUrl?has_content>
	<#list tUrl as imagUrl>
	galleryImageHtml += "<img src='${ctx!}${imagUrl}'/>";
	</#list>	
</#if> 	
	var guideServiceId = "${(guideServiceFee.id)!}";
	var steamId = "${(steamFee.id)!}";
	
	var guideServiceFee = ${(guideServiceFee.price)!0};
	var steamFee = ${(steamFee.price)!0};
	
	var $transferSelect;
	var $airticketsSelect;
	
	var chooseTourdateId;
	var chooseTourPriceId;
	
	var chooseSellingPrice;
	var chooseSingleroomprice;
	var chooseChildPrice;
	var chooseBabyPrice;
	
	var isLocked = 0;
//初始化加载数据
$(function(){
	$(".special-input").hide();
	$('input').customInput();
	if(!$("#mobileDiv").is(":hidden")){
		$("html,body").animate({scrollTop:0}, 200);
	}
	
	//导航的tab切换 
	$('.tours-tabs .nav-tabs li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$('.tours-tabs .tab-content>div:eq('+$(this).index()+')').show().siblings().hide();	
	});
	
	$(".panel-box:eq(0)").click();
	
	$.post("${ctx!}/front/tourlineDetails/getPrice.do",{tourlineId:"${tourline.id}"},function(price){
		if(price.lowsprice != 0){
			$("#priceShow").html('${currencySign}' + price.discountPrice);
		}
		if(price.discountPrice != price.lowsprice){	//参加促销活动
			$("#promotionShow").show();
		}
	});

	$(".button-off").click(function(){
		var $numberInput = $(this).next();
		if($numberInput.val() > 0){
			var nowQuantity = $numberInput.val() - 1;
			$numberInput.val(nowQuantity);
			if(nowQuantity == 0){
				$numberInput.attr('choosed',0);
			}
		}
	});
	
	$(".button-plus").click(function(){
	    var $numberInput = $(this).prev();
	    $numberInput.val($numberInput.val() * 1 + 1);
	    $numberInput.attr('choosed',1);
	});
	
	//如果是window
	if($("#mobileDiv").is(":hidden")){
		dateNumber = 21;
		$departureSelect = $("#windowDepartureSelect");	
		$transferSelect = $("#windowTransferSelect");
		$airticketsSelect = $("#windowAirticketsSelect");	
	}else{
		isMobile = true;
		dateNumber = 9;
		$departureSelect = $("#mobileDepartureSelect");
		$transferSelect = $("#mobileTransferSelect");
		$airticketsSelect = $("#mobileAirticketsSelect");	
		$("#agentCodeInput").hide();
	}
	
	//日期输入框获取焦点时
	$("#dateInput").focus(function(){
		if($("#mobileDiv").is(":hidden")){
			$("#windowUl").find("li:eq(2)").click();
		}else{
			var delayed = false;
			$(".panel-box").each(function(index){
				if($(this).hasClass("panel-heading") && $(this).hasClass("departureDateDiv") ){
					delayed = true;
					$(this).click();
				}
			});
			if(delayed){
				setTimeout(adjustDateSroll,350);	
			}else{
				adjustDateSroll();
			}	
		}
	});
	//提交订单	
	$("#submitButton").click(function(){
	   var member = getMember();
		if($firstNameInput.val().trim() == ''){
			alertWarn('Please fill in your first name.');
			addWarnningShow($firstNameInput);
			return;
		}
		if($lastNameInput.val().trim() == ''){
			alertWarn('Please fill in your last name.');
			addWarnningShow($lastNameInput);
			return;
		}
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
		if($phoneInput.val().trim() == ''){
			alertWarn('Please fill in the phone number.');
			addWarnningShow($phoneInput);
			return;
		}
		if(!numberRule.test($phoneInput.val().trim())){
			alertWarn('The phone No. must be made up of 8 to 12 digits.');
			addWarnningShow($phoneInput);
			return false;	
		}
		if(isNaN(parseInt($phoneInput.val().trim()))){
			alertWarn('The phone number must be numbers.');
			addWarnningShow($phoneInput);
			return;
		}
		if($dateInput.val().trim() == ''){
			alertWarn('Please fill in the departure date.');
			addWarnningShow($dateInput);
			return;
		}
		if($tourDateIdInput.val().trim() == ''){
			alertWarn('The network is a bit slow, please wait for a moment.');
			return;
		}
		var $submitForm = $("#tourBookingForm");
		$(".optionalTour .selfpayitinerary input.quantity[choosed='1']").each(function(index){
			var $product = $(this).closest(".product");
			var $destination = $product.closest(".destination");
			var $itinery = $destination.closest(".timeline");	
			
			var selfpayId = $product.attr("productid");
			var destinationId = $destination.attr("destinationid");
			var itineryId = $itinery.attr("itineraryid");	
			var quantity = $(this).val() * 1;
			
			$submitForm.append('<input value="'+ selfpayId +'" name="selfPayList['+ index +'].productId" type="hidden">');
			$submitForm.append('<input value="'+ destinationId +'" name="selfPayList['+ index +'].destinationId" type="hidden">');
			$submitForm.append('<input value="'+ itineryId +'" name="selfPayList['+ index +'].itineryId" type="hidden">');
			$submitForm.append('<input value="'+ quantity +'" name="selfPayList['+ index +'].quantity" type="hidden">');
		});	
		
		$(".optionalTour .selfpay input.quantity[choosed='1']").each(function(index){
			var $product = $(this).closest(".product");
			
			var selfpayId = $product.attr("productid");
			var quantity = $(this).val() * 1;
			
			$submitForm.append('<input value="'+ selfpayId +'" name="selfPayInTourline['+ index +'].productId" type="hidden">');
			$submitForm.append('<input value="'+ quantity +'" name="selfPayInTourline['+ index +'].quantity" type="hidden">');
		});	
			
		$(".additionalProduct input.quantity[choosed='1']").each(function(index){
			var $product = $(this).closest(".product");
			var $destination = $product.closest(".destination");
			var $itinery = $destination.closest(".timeline");	
			
			var productId = $product.attr("productid");
			var destinationId = $destination.attr("destinationid");
			var itineryId = $itinery.attr("itineraryid");	
			var quantity = $(this).val() * 1;
			
			$submitForm.append('<input value="'+ productId +'" name="additionalProductList['+ index +'].productId" type="hidden">');
			$submitForm.append('<input value="'+ destinationId +'" name="additionalProductList['+ index +'].destinationId" type="hidden">');
			$submitForm.append('<input value="'+ itineryId +'" name="additionalProductList['+ index +'].itineryId" type="hidden">');
			$submitForm.append('<input value="'+ quantity +'" name="additionalProductList['+ index +'].quantity" type="hidden">');
		});
		var submitInfo = setInterval(function(){
			if(isLocked == 0){
				clearInterval(submitInfo);
				storeCommonInfo();
				if(sessionStorage.getItem("chooseSellingPrice") == 'undefined'){
					alertWarn('Please fill in the departure date again.');
					return;
				}
			    $submitForm.submit();
			}		
		},300);
	});	
});
	//选择日期
	var chooseDate = function(startTime,singleRoomPrice,sellingPrice,childPrice,babyPrice,tourdateId,tourPriceId){
		isLocked = 1;
		$("#tourDateIdInput").val(tourdateId);
		$("#dateInput").val(startTime);
		$("#priceShow").html('${currencySign}' + formatPrice(sellingPrice));
		$("#departureIdInput").val(chooseDepartureId);
		
		chooseTourdateId = tourdateId;
		chooseTourPriceId = tourPriceId;
		
		chooseSellingPrice = sellingPrice;
		chooseSingleroomprice = singleRoomPrice;
		chooseChildPrice = childPrice;
		chooseBabyPrice = babyPrice;
		
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
					var $flightTicksOption = '<option value="'+ flightTickPrice.id +'" price="'+ flightTickPrice.price +'">' + flightTickPrice.departureName + ' ${currencySign}' + flightTickPrice.price + '</option>';
					$airticketsSelect.append($flightTicksOption);
				});
				if($airticketsSelect.find("option").size() > 1){
					$airticketsSelect.parent().show();
				}else{
					$airticketsSelect.parent().hide();
				}
				isLocked = 0;
	        }
	    });
   		if(!$("#mobileDiv").is(":hidden")){
   			 adjustBookingSroll();
   		}
	}
	
	//调整移动版的页面停留至下单
	var adjustBookingSroll = function(){  
	    adjustMobileSroll($("#showDetailsDiv"));
	}
	
	//调整移动版的页面停留至选择日期
	var adjustDateSroll = function(){
        adjustMobileSroll($("#mobileDepartureTab"));
	}
	
	//保存和下单页面共有的一部分参数
	var storeCommonInfo = function(){
		sessionStorage.clear();
	
		var topHtml = $("#top").html();
		sessionStorage.setItem("top",topHtml);
		
		var bgUrl=$("#sectionHtml").css("background-image");
		sessionStorage.setItem("bgUrl",bgUrl);
		
		var sectionHtml = $("#sectionHtml").html();
		sessionStorage.setItem("section",sectionHtml);
		
		var windowTourlineDetailHtml = $('#tourlineDetailTab').html();
		sessionStorage.setItem("windowTourlineDetail",windowTourlineDetailHtml);
		
		var windowItineraryHtml = $("#itineraryTab").html();
		sessionStorage.setItem("windowItinerary",windowItineraryHtml);
		
		var windowDepartureHtml = $("#departureTab").html();
		sessionStorage.setItem("windowDeparture",windowDepartureHtml);
		
		var windowGalleryHtml = $("#galleryTab").html();
        sessionStorage.setItem("windowGallery",windowGalleryHtml);
        
        var $windowOptionalTourItinerarys = $("#optionalTourTab").find(".timeline");
        sessionStorage.setItem("itineraryOptionalTourSize",$windowOptionalTourItinerarys.size());
        
        $windowOptionalTourItinerarys.each(function(index){
			sessionStorage.setItem("windowOptionalTourItinerary_" + index, $(this).prop("outerHTML"));	
        });      
        
        var $windowCustomItinerarys = $("#customTab").find(".timeline");
        sessionStorage.setItem("itinerarySize",$windowCustomItinerarys.size());
        
        $windowCustomItinerarys.each(function(index){
			sessionStorage.setItem("windowItinerary_" + index, $(this).prop("outerHTML"));	
        });
        
        var	mobileTourlineDetailHtml = $("#mobileTourlineDetailTab").html();
        sessionStorage.setItem("mobileTourlineDetail",mobileTourlineDetailHtml);
        
        var mobileItineraryHtml = $("#mobileItineraryTab").html();
		sessionStorage.setItem("mobileItinerary",mobileItineraryHtml);
		
		var mobileGalleryHtml = $("#mobileGalleryTab").html(); 
		sessionStorage.setItem("mobileGallery",mobileGalleryHtml);
		
		var mobileSelfPayHtml = $("#mobileGalleryTab").html();
		
		var $mobileOptionalTourItinerarys = $("#mobileOptionalTourTab").find(".timeline");
		$mobileOptionalTourItinerarys.each(function(index){
			sessionStorage.setItem("mobileOptionalTourItinerary_" + index, $(this).prop("outerHTML"));			
		});		
		
		var $mobileCustomItinerarys = $("#mobileCustomTab").find(".timeline");
		$mobileCustomItinerarys.each(function(index){
			sessionStorage.setItem("mobileItinerary_" + index, $(this).prop("outerHTML"));			
		});
		
		var mobileDepartureHtml = $("#mobileDepartureTab").html();
		sessionStorage.setItem("mobileDeparture",mobileDepartureHtml);
		
		var downNavigationHtml = $("#downNavigation").html();
		sessionStorage.setItem("downNavigation",downNavigationHtml);
		
		var mobileDownNavigationHtml = $("#mobileDownNavigation").html();
		sessionStorage.setItem("mobileDownNavigation",mobileDownNavigationHtml);
		
		var reviewSectionHtml = $("#reviewSection").html();
		sessionStorage.setItem("reviewSection",reviewSectionHtml);
		
		var promotionIsHidden = $("#promotionShow").is(":hidden");
		if(promotionIsHidden){
			sessionStorage.setItem("promotionIsHidden",0);
		}else{
			sessionStorage.setItem("promotionIsHidden",1);
		}
		
		if(viewLoaded){
			sessionStorage.setItem("viewLoaded",1);
		}else{
			sessionStorage.setItem("viewLoaded",0);
		}
		
		if(imagesLoaded){
			sessionStorage.setItem("imagesLoaded",1);
		}else{
			sessionStorage.setItem("imagesLoaded",0);
			sessionStorage.setItem("galleryImageHtml",galleryImageHtml);
		}
		
		sessionStorage.setItem("chooseDepartureInfo",chooseDepartureInfo);
		sessionStorage.setItem("tourlineName","${tourline.tourname}");
		sessionStorage.setItem("guideServiceId",guideServiceId);
		sessionStorage.setItem("steamId",steamId);
		sessionStorage.setItem("guideServiceFee",guideServiceFee);
		sessionStorage.setItem("steamFee",steamFee);
		sessionStorage.setItem("chooseTourdateId",chooseTourdateId);
		sessionStorage.setItem("chooseTourPriceId",chooseTourPriceId);
		sessionStorage.setItem("tourlineId","${tourline.id}");
		sessionStorage.setItem("productId","${(tourline.productid)!}");
		sessionStorage.setItem("costId","${(tourline.cost.id)!}");
		sessionStorage.setItem("chooseSellingPrice",chooseSellingPrice);
		sessionStorage.setItem("chooseSingleroomprice",chooseSingleroomprice);
		sessionStorage.setItem("chooseChildPrice",chooseChildPrice);
		sessionStorage.setItem("chooseBabyPrice",chooseBabyPrice);
		sessionStorage.setItem("days","${(tourline.days)!0}");
		sessionStorage.setItem("sign","${(tourline.cost.sign)!}");
		sessionStorage.setItem("code","${(frontCode)!}");
//        sessionStorage.setItem("haveChina","${haveChina?string('true', 'false')}");
		if($transferSelect.size() > 0){
			sessionStorage.setItem("selectTransferId",$transferSelect.val());
		}
		if($airticketsSelect.size() > 0){
			sessionStorage.setItem("selectAirticketId",$airticketsSelect.val());
		}
	}
</script> 
</script>
<script type="text/javascript" async=""> 
;(function(o,l,a,r,k,y){if(o.olark)return; 	r="script";y=l.createElement(r);r=l.getElementsByTagName(r)[0]; y.async=1;y.src="//"+a;r.parentNode.insertBefore(y,r); y=o.olark=function(){k.s.push(arguments);k.t.push(+new Date)}; y.extend=function(i,j){y("extend",i,j)}; y.identify=function(i){y("identify",k.i=i)}; y.configure=function(i,j){y("configure",i,j);k.c[i]=j}; k=y._={s:[],t:[+new Date],c:{},l:a}; })(window,document,"static.olark.com/jsclient/loader.js");
/* custom configuration goes here (www.olark.com/documentation) */
olark.identify('4785-162-10-1638');
</script>
<script type="text/javascript">
/* <![CDATA[ */
var olark_vars = {"site_ID":"4785-162-10-1638","expand":"0","float":"0","override_lang":null,"lang":"en-US","api":"","mobile":"0","woocommerce":"1","woocommerce_version":"3.2.6","enable_cartsaver":"0"};
/* ]]> */
</script>
</body>
</html>