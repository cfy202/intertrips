<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${bookInfo.product.name}</title>
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
                	<p style="margin-bottom:0; font-weight:bold; font-size:15px;">Hello <span>${bookInfo.firstName} ${bookInfo.lastName}</span>:</p>
                    <p style="margin-bottom:0;">Thank you for travelling with InterTrips, You Ordered <a href="https://www.intertrips.com${bookInfo.product.pagePageid.filepath}" target="_blank" style="color:#333;">${bookInfo.product.name}</a> online. This is a confirmation email for your order's successful submission.</p>
                </td>
                <td width="25"></td>
            </tr>
            <tr height="10"><td colspan="3"></td></tr>
            <tr>
                <td width="25"></td>
                <td width="730">
                	<table cellpadding="0" cellspacing="0" width="100%">
                    	<tr>
                        	<td><b>Order No.：<span>${bookInfo.orderNumber}</span></b></td>
                            <td><b>Status：<span>Unpaid</span></b></td>
                            <td><b>Total Amount：<span>${bookInfo.cost.sign}${bookInfo.totalPrice}</span></b></td>
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
                        	<td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${bookInfo.departureString}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${bookInfo.product.name}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${bookInfo.quantity}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">
                            	<p style="margin-bottom:0;">${bookInfo.adultsNumber} Adults</p>
                                <p>${bookInfo.childrenNumber} Children</p>
                                <p>${bookInfo.infantsNumber} infants</p>
                            </td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding-left:10px;">
                            	<p>Base fare:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.tourFee}</p>
                            	<#if bookInfo.singleSupplementsFee??>
                            	   <p class="Payment_td_p">Single Supplements fee:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.singleSupplementsFee}</p>
                            	</#if>
						        <#if bookInfo.guideServeFee??>   
						           <p class="Payment_td_p">Guides service fees:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.guideServeFee}</p>
						        </#if>
						        <#if bookInfo.steamFee??>  
						        	<p class="Payment_td_p">Optional attractions and performances:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.steamFee}</p>  
						        </#if>
						        <#if bookInfo.transferFee??>    
						            <p class="Payment_td_p">Airport transfer fees:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.transferFee}</p>
						        </#if>
						        <#if bookInfo.airticketFee??>    
						            <p class="Payment_td_p">Air fares:&nbsp;&nbsp;${bookInfo.cost.sign}${bookInfo.airticketFee}</p>   
						        </#if> 
                            </td>
                        </tr>
		                <#if (bookInfo.selfPayList)??>
		                <#list bookInfo.selfPayList as additionalProduct>
                        <tr>
                        	<td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.dayString}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.productName}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.quantity}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;"></td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding-left:10px;">
                            	<p>${bookInfo.cost.sign}${additionalProduct.totalFee}</p>
                            </td>
                        </tr> 
		                </#list>
		                </#if>    
		                <#if (bookInfo.selfPayInTourline)??>
		                <#list bookInfo.selfPayInTourline as additionalProduct>
                        <tr>
                        	<td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;"></td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.productName}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.quantity}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;"></td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding-left:10px;">
                            	<p>${bookInfo.cost.sign}${additionalProduct.totalFee}</p>
                            </td>
                        </tr> 
		                </#list>
		                </#if>   
		                <#if (bookInfo.additionalProductList)??>
		                <#list bookInfo.additionalProductList as additionalProduct>
                        <tr>
                        	<td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.dayString}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.productName}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;">${additionalProduct.quantity}</td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding:0 10px;"></td>
                            <td style="border-right:1px solid #e3e3e3;border-bottom:1px solid #e3e3e3; padding-left:10px;">
                            	<p>${bookInfo.cost.sign}${additionalProduct.totalFee}</p>
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
                	<p style="margin-bottom:0;"><b>Total price:<span style="color:red;">${bookInfo.cost.sign}${bookInfo.totalPrice}</span></b></p>
                </td>
                <td width="25"></td>
            </tr>
            <tr>
            	<td width="25"></td>
            	<td width="730">
                	<p style="margin-bottom:0;">If you have any question, please contact our customer service center via phone: 888-410-4111 or via email: 
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
