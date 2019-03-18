<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<title>My order</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="apple-touch-icon-precomposed" href="apple-touch-icon.png">
<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
<link rel="stylesheet" href="${ctx!}/assets-web/css/timeline.css">
<script type='text/javascript' src='${ctx!}/assets-web/js/jquery-1.10.2.min.js'></script>
<script type="text/javascript">
$(function(){
	$('input').customInput();
});
</script>
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
</head>
<body class="blog">
<div id="top"></div>
<#include "/front/include/top.ftl"/>


<section class="featured-destinations" style="background-color:#f5f6f6;padding-top: 140px;">

		  <div class="container">
		    <div class="row">
		    	<div class="breadcrumbs">
		              <ul>
			            <li><a href="${ctx!}/">Home</a></li>
			            <li><a href="${ctx!}/member/profile/user_orders.htm">My order</a></li>
			          </ul>
		        </div>
		<#include "/front/include/user_left.ftl"/>
            </div>
        </div>
        <div class="span16">
            <div class="uc-box uc-main-box">
                <div class="uc-content-box portal-content-box" style="margin-bottom: 30px;">
                    <div class="box-hd">
                    	<h4 class="">
                    	<#list orders as order>
                    	 	<#if order_index == 0>
   							<#list order.product as product>
					            <a href="${ctx!}${product.pagePageid.filepath}" target="_blank">${product.name}</a>&nbsp;
					            <#assign productName="${product.name}" />
					         </#list>
					         <#assign orderNo="${order.orderno}" />
					         <#assign orderId="${order.id}" />
					         <#assign timeLineStatus="${order.timeLineStatus!''}" />
					         <#break>
  							</#if>
  						</#list>
  						</h4>
                        <div class="clear"></div>
                    </div>
                    <div class="box-bd" style="padding-top:0px;">
                        <div id="J_orderList" style="display:none;"><p class="empty">Currently there is no trade order.</p></div>
                        <div id="J_orderListPages">
                        	<section class="cd-horizontal-timeline">
								<div class="timeline">
									<div class="events-wrapper">
										<div class="events" style="width: 100%;">
											<ol>
												<li><a href="#0" data-date="16/12/2013" style="left: 5%;" <#if (timeLineStatus=="New Order")>class="selected"</#if> title="New Order">0</a></li>
												<li><a href="#0" data-date="16/01/2014" style="left: 15%;" <#if (timeLineStatus=="Order Confirmed")>class="selected"</#if> title="Order Confirmed">1</a></li>
												<li><a href="#0" data-date="28/03/2014" style="left: 30%;" <#if (timeLineStatus=="Traveler's Info")>class="selected"</#if> title="Traveler's Info">2</a></li>
												<li><a href="#0" data-date="20/05/2014" style="left: 45%;" <#if (timeLineStatus=="Tickets")>class="selected"</#if> title="Tickets">3</a></li>
												<li><a href="#0" data-date="28/07/2014" style="left: 60%;" <#if (timeLineStatus=="VISA")>class="selected"</#if> title="VISA">4</a></li>
												<li><a href="#0" data-date="09/10/2014" style="left: 75%;" <#if (timeLineStatus=="Final")>class="selected"</#if> title="Final">5</a></li>
												<li><a href="#0" data-date="15/12/2014" style="left: 90%;" <#if (timeLineStatus=="Review")>class="selected"</#if> title="Review">6</a></li>
											</ol>
							
											<span class="filling-line" aria-hidden="true"></span>
										</div> <!-- .events -->
									</div> <!-- .events-wrapper -->
										
									<ul class="cd-timeline-navigation">
										<li><a href="#0" class="prev inactive">Prev</a></li>
										<li><a href="#0" class="next">Next</a></li>
									</ul> <!-- .cd-timeline-navigation -->
								</div> <!-- .timeline -->
							
								<div class="events-content">
									<ol>
									   <li <#if (timeLineStatus=="New Order")>class="selected"</#if> data-date="16/12/2013">
											<h3>Booking Received</h3><br/>
											<p>	
											    Thank you for booking with InterTrips. <a href="${ctx!}/member/profile/orderdetail.htm?orderid=${orderId}&cValue=${frontCode!}">More details</a>
											</p>
										</li>
									
										<li <#if (timeLineStatus=="Order Confirmed")>class="selected"</#if> data-date="16/01/2014">
											<h3>Order Confirmed</h3><br/>
											<p>	
											    Thank you for booking with InterTrips,Your "${productName}" is confirmed and your confirmation ID is <a href="${ctx!}/member/profile/orderdetail.htm?orderid=${orderId}&cValue=${frontCode!}">${orderNo}</a>.
											</p>
										</li>
							
										<li <#if (timeLineStatus=="Traveler's Info")>class="selected"</#if> data-date="28/03/2014">
											<h3>Traveler's Info</h3><br/>
											<p>	
											    Please confirm the passport info of the travelers . <a href="${ctx!}/member/profile/orderdetail.htm?orderid=${orderId}&cValue=${frontCode!}">More details</a>
											</p>
										</li>
							
										<li <#if (timeLineStatus=="Tickets")>class="selected"</#if> data-date="20/05/2014">
											<h3>Tickets</h3><br/>
											<p>	
											    you confrimed your passport info of the travelers. We will issue your tickets shortly. <a href="${ctx!}/member/profile/orderdetail.htm?orderid=${orderId}&cValue=${frontCode!}">More details</a>
											</p>
										</li>
							
										<li <#if (timeLineStatus=="VISA")>class="selected"</#if> data-date="28/07/2014">
											<h3>Visa</h3><br/>
											<p>	
											    Your tickets have been issued.If you don't need any entry visa,just wait for us to finalize your group. <a href="${ctx!}/member/profile/orderdetail.htm?orderid=${orderId}&cValue=${frontCode!}">More details</a>
											</p>
										</li>
							
										<li <#if (timeLineStatus=="Final")>class="selected"</#if> data-date="09/10/2014">
											<h3>Final</h3><br/>
											<p>	
											    Great!You are all set!We have send you the final itinerary.Enjoy your vacation time! <a href="${ctx!}/member/profile/orderdetail.htm?orderid=${orderId}&cValue=${frontCode!}">More details</a>
											</p>
										</li>
							
										<li <#if (timeLineStatus=="Review")>class="selected"</#if> data-date="15/12/2014">
											<h3>Review</h3><br/>
											<p>	
											    Thank you for traveling with InterTrips. <a href="${ctx!}/member/profile/orderdetail.htm?orderid=${orderId}&cValue=${frontCode!}">More details</a>
											</p>
										</li>
							
									</ol>
								</div> <!-- .events-content -->
							</section>
							<div>
							   Click <a href="${ctx!}/member/profile/user_orders_more.htm?cValue=${frontCode}">here</a> to see other tour packages.
							</div>
                        </div>
                    </div>
                </div>
                <#--
                <div class="text-center">
                    <ul class="page-numbers">
                        <li><span class="page-numbers current">1</span></li>
                        <li><a class="page-numbers" href="">2</a></li>
                        <li><a class="next page-numbers" href="">Next</a></li>
                    </ul>
                </div>
                -->
            </div>
            <div class="uc-main-box-notice">
				  <p><b>Important notice:</b> Payment need to received within 10 minutes of booking completion. Otherwise your booking is subject to cancel and we will not able to guarantee the tour price and  availability! </p>
				</div>
        </div>
	</div>
  </div>
</section>

<#--bottom-->
	<#include "/front/include/bottom.ftl"/>
<#--bottom-->

<script type="text/javascript">
if (typeof jQuery.fn.fitVids === "undefined") { document.write("<script src='${ctx!}/assets-web/js/jquery.fitvids.min.js'>\x3C/script>"); }

$(document).ready(function(){
	//限制字符个数
	$(".atgrid-item-description").each(function(){
		var maxwidth=110;
			if($(this).text().length>maxwidth){
			$(this).text($(this).text().substring(0,maxwidth));
			$(this).html($(this).html()+'…');
		}
	});
});

</script> 
<script type='text/javascript' src='${ctx!}/assets-web/js/jquery.fitvids.min.js'></script>
<script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
<script type="text/javascript" src="${ctx!}/assets-web/js/jquery.mobile.custom.min.js"></script>
<script type="text/javascript" src="${ctx!}/assets-web/js/main.js"></script>

<!-- Dynamic page generated in 0.606 seconds. --> 
<!-- Cached page generated by WP-Super-Cache on 2015-11-09 15:27:54 --> 

<!-- Compression = gzip -->
</body>
</html>