<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${orders.orderdetails[0].product.name}</title>
</head>
<body style="margin:0; padding:0;background:#f3f3f3;">
<div>
	<div style="height:40px;"></div>
	<div style="width:780px; margin:0 auto;background:#fff; border:1px solid #e3e3e3;font-family: Tahoma,Arial,Helvetica,STHeiti; font-size:13px; color:#333;">
    	<table cellpadding="0" cellspacing="0">
        	<tr>
            	<td width="25"></td>
                <td width="730"><img src="https://www.intertrips.com/assets-web/images/intertrips-email-bg.jpg"></td>
                <td width="25"></td>
            </tr>
        	<tr>
            	<td width="25"></td>
                <td width="730"><img width="200" src="https://www.intertrips.com/assets-web/images/InterTrips.png"></td>
                <td width="25"></td>
            </tr>            
        </table>
        <table cellpadding="0" cellspacing="0">
        	<tr height="20"><td colspan="3"></td></tr>
            <tr>
                <td width="25"></td>
                <td width="730">
                	<p style="margin-bottom:0; font-weight:bold; font-size:15px;">Hello <span>${orderContacter.firstName} ${orderContacter.lastName}</span>:</p>
                    <p style="margin-bottom:0;">Thank you for travelling with InterTrips, You Ordered <a href="https://www.intertrips.com${orders.orderdetails[0].product.pagePageid.filepath}" target="_blank" style="color:#333;">${orders.orderdetails[0].product.name}</a> online. <#if departure??>The departure city is ${(departure.name)!}.</#if>This is a confirmation email for your order's successful submission.</p>
                </td>
                <td width="25"></td>
            </tr>
            <tr height="10"><td colspan="3"></td></tr>
            <tr>
                <td width="25"></td>
                <td width="730">
                	<table cellpadding="0" cellspacing="0" width="100%">
                    	<tr>
                        	<td><b>Order No.：<span>${orders.orderno}</span></b></td>
                            <td><b>Status：<span>Paid</span></b></td>
                            <td><b>Total Amount：<span>${orders.cost.sign}${orders.totalprice}</span></b></td>
                        </tr>
                    </table>
                </td>
                <td width="25"></td>
            </tr>
            <tr height="10"><td colspan="3"></td></tr>
        	<tr>
                <td width="25"></td>
                <td width="730">
                    <table width="100%" cellpadding="0" cellspacing="0" style="border-left:1px solid #e3e3e3;border-top:1px solid #e3e3e3;">
                        <tr height="25">
                            <th style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; text-align:center; width:12%;">Date</th>
                            <th style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; text-align:center; width:30%;">Product description</th>
                            <th style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; text-align:center; width:12%;">Quantity</th>
                            <th style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; text-align:center; width:23%;">Adults/Children/Infants</th>
                            <th style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; text-align:center; width:23%;">Price</th>
                        </tr>
                        <tr>
                        	<td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${departureString!}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${orders.orderdetails[0].product.name}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${orders.quantity}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">
                            	<p style="margin-bottom:0;">${orders.orderdetails[0].adults} Adults</p>
                                <p>${orders.orderdetails[0].children} Children</p>
                                <p>${orders.orderdetails[0].babies} infants</p>
                            </td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding-left:10px;">
                            	<p>Base fare:&nbsp;&nbsp;${orders.cost.sign}${tourFee}</p>
                                <#if singleSupplementsFee??>
                                   <p class="Payment_td_p">Single supplements fee:&nbsp;&nbsp;${orders.cost.sign}${singleSupplementsFee}</p>
						        </#if>
						        <#if guideServeFee??>   
						           <p class="Payment_td_p">Guides service fees:&nbsp;&nbsp;${orders.cost.sign}${guideServeFee}</p>
						        </#if>
						        <#if steamFee??>  
						        	<p class="Payment_td_p">Optional attractions and performances:&nbsp;&nbsp;${orders.cost.sign}${steamFee}</p>  
						        </#if>
						        <#if transferFee??>    
						            <p class="Payment_td_p">Airport transfer fees:&nbsp;&nbsp;${orders.cost.sign}${transferFee}</p>
						        </#if>
						        <#if airticketFee??>    
						            <p class="Payment_td_p">Air fares:&nbsp;&nbsp;${orders.cost.sign}${airticketFee}</p>   
						        </#if>
						        <#if bonusPointsFee??>
						        	<p class="Payment_td_p">Bonus Points:&nbsp;&nbsp;-${orders.cost.sign}${bonusPointsFee}</p>
						        </#if>   
						        <#if couponsFee??>
						        	<p class="Payment_td_p">Coupon:&nbsp;&nbsp;-${orders.cost.sign}${couponsFee}</p>
						        </#if> 						         
                            </td>
                        </tr>
		                <#if (optionalTourList)?has_content>
		                <#list optionalTourList as additionalProduct>
                        <tr>
                        	<td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.dayString}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.name}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.quantity}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;"></td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding-left:10px;">
                            	<p>${orders.cost.sign}${additionalProduct.unitcost * additionalProduct.quantity}</p>
                            </td>
                        </tr> 
		                </#list>
		                </#if>
		                <#if (optionalTourInTourlineList)?has_content>
		                <#list optionalTourInTourlineList as additionalProduct>
                        <tr>
                        	<td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;"></td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.name}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.quantity}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;"></td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding-left:10px;">
                            	<p>${orders.cost.sign}${additionalProduct.unitcost * additionalProduct.quantity}</p>
                            </td>
                        </tr> 
		                </#list>
		                </#if>		                		                
		                <#if (additionalProductList)?has_content>
		                <#list additionalProductList as additionalProduct>
                        <tr>
                        	<td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.dayString}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.name}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.quantity}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;"></td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding-left:10px;">
                            	<p>${orders.cost.sign}${additionalProduct.unitcost * additionalProduct.quantity}</p>
                            </td>
                        </tr> 
		                </#list>
		                </#if>
                    </table>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
                <td width="25"></td>
                <td width="730" align="right">
                	<p style="margin-bottom:0;"><b>Total price:<span style="color:red;">${orders.cost.sign}${orders.totalprice}</span></b></p>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
                	<p style="margin-bottom:0;">If you have any question, please contact our customer service center via phone:
                		  888-410-4111 or via email: 
                    	  <a href="mailto:Info@InterTrips.com" target="_blank" style="color:#333;">Info@InterTrips.com</a>.
                    </p>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
                	<p style="margin-bottom:0;">Thank you for choosing <a href="https://www.intertrips.com/" target="_blank" style="color:#333;">InterTrips.com</a>.</p>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
                	<p style="border-bottom:1px solid #e3e3e3;"></p>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
                	<p style="margin:0;">By Placing your order, you agree to InterTrips' Terms of Use.</p>
	                    <p style="margin-bottom:0;">Delivered by <a href="https://www.intertrips.com/" target="_blank" style="color:#333;">InterTrips</a>, 680 Brea Canyon Rd, Suite 278, Diamond Bar, CA, 91789, USA.</p>
	                	<p>California Seller of Travel #2080370-40</p>
                </td>
                <td width="25"></td>
            </tr>
           <tr>
            	<td width="25"></td>
            	<td width="730">
	                <p style="margin-bottom:0;">1. Your travel agent will contact you via email within 48 hours after you book our trips on-line.</p>
  			<p>2.Your flights will be finalized 30 days prior to the trip and Final itinerary will be provided 21 days prior. Please book all the extension packages at least 90 days prior and all other optional excursion 30 days prior.</p>
			<p>3.Travel Insurance:Your travel agent will send all the coverage details and link for to purchase it through email.</p>
  			<p>4.Additional Cost for upgrade to premium economy or business class.Please let us know if you decide to upgrade at least 60 days prior.</p>
  			<p>
 			 **Premium economy: $400 one-way  &amp; $800 round-trip <br>
        		 ** Business class:       $1,500 one-way &amp;$3,000 round-trip
			</p>
  			<p> 5.Please provide copy of your passport for us to verify all passport info to book the flights.</p>
		</td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
            		<#--
                	<span style="font-family:Tahoma,Arial,Helvetica,STHeiti;font-size:15px;">
                    	<a style="text-decoration:none;color:#ffffff;" href="" target="_blank">
                        	<div style="padding:10px 25px; width:70px;border-radius:3px;text-align:center;text-decoration:none;background-color:#ffa800;color:#ffffff;font-size:15px;margin:0;">Pay Now</div>
                        </a>
                    </span>
                    -->
                </td>
                <td width="25"></td>
            </tr>
            <tr height="30"><td colspan="3"></td></tr>
        </table>
    </div>
</div>
</body>
</html>
