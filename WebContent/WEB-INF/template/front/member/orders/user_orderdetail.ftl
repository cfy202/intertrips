<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<title>Orderdetail</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="apple-touch-icon-precomposed" href="apple-touch-icon.png">
<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
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
                <div class="uc-content-box portal-content-box">
                    <div class="box-hd">
                    	<h1 class="title">Order details</h1>
                        <div class="clear"></div>
                    </div>
                    <div class="box-bd">
                        <div id="J_orderListPages">
                        	<div class="uc-content-Orderdetails-top">
                            	<p class="uc-content-Orderdetails-top-Status"><span>Order Status: </span><span>${orders.orderStatus.name}</span><p>
                            	<p>
                                <#if (orders.orderStatus.code)?? && ((orders.orderStatus.code)==1||(orders.orderStatus.code)==2)>
		                		    <a target="_blank" class="uc-content-Orderdetails-top-btn" href="${ctx}/payment.htm?orderId=${orders.id}">Payment</a>
		                		</#if>
		                		    <a href="#" class="uc-content-Orderdetails-top-btn" onClick="javascript :history.go(-1);">Back</a>
                                </p>
                            </div>
                            
                            <div class="uc-content-Orderdetails-down">
                            	<div class="info">
                                    <p>Order Information</p>
                                    <ul class="order-info-ul">
                                        <li>Tour Code: ${orders.orderno}</li>
                                        <li>Qty: ${orders.quantity}</li>
                                        <li>Total Price: ${orders.orderdetails[0].currencySign} ${orders.totalprice}</li>
                                        <li>Add Time: ${orders.createtime?string('yyyy-MM-dd HH:mm:ss')}</li>
                                        <#if (orders.timeLineStatus??)>
                                        <li>Order Status: ${orders.timeLineStatus}</li>
                                        </#if>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="order_line_dash"></div>
                                <div class="info">
                                    <p>Contact Information</p>
                                    <ul class="order-info-ul">
                                        <li>Contact Name: ${(orders.orderContacter.name)!}</li>
                                        <li>Contact Tel: ${(orders.orderContacter.tel)!}</li>
                                        <li>Contact Email: ${(orders.orderContacter.email)!}</li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="order_line_dash"></div>
                                <div class="info">
                                    <p>Order Detail</p>
                                    <#list orders.orderdetails as eachProduct>
                                    <ul class="order-info-ul">
                                        <li style="width:100%;">Product Name: <a href="${ctx!}${eachProduct.product.pagePageid.filepath}" class="a5" target="_blank">${eachProduct.product.name}</a></li>
                                        <li>Product Code: ${eachProduct.product.code}</li>
                                        <li>Depature Date: ${eachProduct.departuredate}</li>
                                        <li>Return Date: ${eachProduct.enddate}</li>
                                        <li>Days: ${eachProduct.days}</li>
                                        <li>Rooms: ${(eachProduct.roomcount)!}</li>
                                        <#if eachProduct.adults?? && eachProduct.adults!=0><li>Adult: ${(eachProduct.adults)!}</li></#if>
				                        <#if eachProduct.children?? && eachProduct.children!=0><li>Child: ${(eachProduct.children)!}</li></#if>
				                        <#if eachProduct.babies?? && eachProduct.babies!=0><li>Baby：${(eachProduct.babies)!}</li></#if>
				                        <#if eachProduct.depostidate??><li>Final Pay Date: ${(eachProduct.depostidate)!}</li></#if>
                                        <li> Paid Amount: ${orders.orderdetails[0].currencySign} ${(eachProduct.despotprice)!}</li>
					                    <#-- <#if eachProduct.finalpaydate??><li>最终付款日期: ${(eachProduct.finalpaydate)!}</li></#if>-->
					                    <li>Unpaid Amount: ${orders.orderdetails[0].currencySign} ${(eachProduct.finalprice)!}</li>
					                    <#if eachProduct.specialrequest??><li style="width:100%;">Special Requirements: ${eachProduct.specialrequest}</li></#if>
                                        <div class="clear"></div>
                                    </ul>
                                   </#list>
                                </div>
                                <div class="order_line_dash"></div>
                                <#assign orderdetail = orders.orderdetails[0]/>
                                <#if optionalTourList?has_content>
                                  <div class="info">
                                    <p>Selected Optional Tours</p> 
	                                 <div id="J_orderListPages">
			                        	<table cellpadding="0" cellspacing="0" width="100%">
			                            	<tr class="uc-content-order-Header" height="35" bgcolor="#f0f0f0">
			                                	<td width="55%">Name</td>
			                                	<td width="15%">Date</td>
			                                    <td width="10%">Price</td>
			                                    <td width="5%">Qty</td>
			                                    <td width="15%">Total Price</td>
			                                  <#list optionalTourList as optionalTour>
				                                <tr class="uc-content-order-list">
				                                	<td style="text-align:left">${optionalTour.name}</td>
													<td><#if (optionalTour.date)??>${optionalTour.date?string('yyyy-MM-dd')}</#if></td>
													<td>${(orderdetail.currencySign)!}${optionalTour.unitcost}</td>
													<td>${(optionalTour.quantity)!}</td>
													<td>${(orderdetail.currencySign)!}${optionalTour.price}</td>
				                                </tr>
				                               </#list>
			                                </tr>
			                               
			                            </table>
			                        </div>
			                     </div>
                                <div class="order_line_dash"></div>
                                </#if>
                                <#if optionalTourInTourlineList?has_content>
                                  <div class="info">
                                    <p>Optional Tour selection information for tour</p> 
	                                 <div id="J_orderListPages">
			                        	<table cellpadding="0" cellspacing="0" width="100%">
			                            	<tr class="uc-content-order-Header" height="35" bgcolor="#f0f0f0">
			                                	<td width="55%">Name</td>
			                                	<td width="15%">Date</td>
			                                    <td width="10%">Price</td>
			                                    <td width="5%">Qty</td>
			                                    <td width="15%">Total Price</td>
			                                  <#list optionalTourInTourlineList as optionalTourInTourline>
				                                <tr class="uc-content-order-list">
				                                	<td style="text-align:left">${optionalTourInTourline.name}</td>
													<td><#if (optionalTourInTourline.date)??>${optionalTourInTourline.date?string('yyyy-MM-dd')}</#if></td>
													<td>${(orderdetail.currencySign)!}${optionalTourInTourline.unitcost}</td>
													<td>${(optionalTourInTourline.quantity)!}</td>
													<td>${(orderdetail.currencySign)!}${optionalTourInTourline.price}</td>
				                                </tr>
				                               </#list>
			                                </tr>
			                               
			                            </table>
			                        </div>
			                     </div>
                                <div class="order_line_dash"></div>
                                </#if>
                                <div class="info">
                                    <p>Passenger information</p>
                               <#list orders.orderdetails as eachProduct>
	            		              <#list eachProduct.tourPassengerList as tourPassenger>
	                                    <div class="mb">
	                                        <span class="left">Passenger ${tourPassenger_index+1}</span>
	                                        <ul class="order-info-ul left">
	                                            <li>Full Name: ${(tourPassenger.lastName)!} ${(tourPassenger.firstName)!}</li>
	                                            <li>Gender: ${(tourPassenger.gender==1)?string("female","male")}</li>
	                                            <li>Birthday: ${(tourPassenger.birthday?string("yyyy-MM-dd"))!}</li>
	                                            <li>Nationality: ${(tourPassenger.nationality)!}</li>
	                                            <li>Passport No: ${(tourPassenger.passportNo)!}</li>
	                                            <li>Passport Expiration Date: ${(tourPassenger.passportNoExpiryDate?string("yyyy-MM-dd"))!}</li>
	                                            <li>Phone: ${(tourPassenger.mobileNumber)!}</li>
	                                            <div class="clear"></div>
	                                        </ul>
	                                        <div class="clear"></div>
	                                    </div>
                                    
                                      </#list>
                                </#list>
                                </div>
                                <div class="order_line_dash"></div>
                                <div class="info">
                                    <p><button type="button" class="uc-content-Orderdetails-top-btn" id="uploadInput">Upload files</button></p>
                                    <div class="mb" id="fileList">
                                    <#list orderAttachmentList as orderAttachment>
                                    	<a href="${ctx!}${(orderAttachment.url)!}">${(orderAttachment.name)!}</a> &nbsp;&nbsp;|&nbsp;&nbsp;
                                    </#list>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

		
	</div>
  </div>
</section>

<#--bottom-->
	<#include "/front/include/bottom.ftl"/>
<#--bottom-->
<script type='text/javascript' src='${ctx!}/assets-web/js/jquery.fitvids.min.js'></script>
<script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
<script type="text/javascript" src="${ctx!}/assets-web/js/ajaxupload.3.6.js"></script>
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
	
	
	   $(function () {
        var button = $('#uploadInput'), interval;
        new AjaxUpload(button, {
            //action: 'upload-test.php',文件上传服务器端执行的地址
            action: '${ctx!}/member/profile/uploadFile.do?orderId=${orders.id}&remark=remark',
            name: 'uploadFile',
            onSubmit: function (file, ext) {
                //if (!(ext && /^(jpg|jpeg|JPG|JPEG)$/.test(ext))) {
                //   alert('图片格式不正确,请选择 jpg 格式的文件!', '系统提示');
                //    return false;
                //}
 
                // change button text, when user selects file
                button.text('Uploading...');
 
                // If you want to allow uploading only 1 file at time,
                // you can disable upload button
                this.disable();
 
                // Uploding -> Uploading. -> Uploading...
                interval = window.setInterval(function () {
                    var text = button.text();
                    if (text.length < 10) {
                       // button.text(text + '|');
                    } else {
                      //  button.text('Uploading...');
                    }
                }, 200);
            },
            onComplete: function (file, response) {
                //file 本地文件名称，response 服务器端传回的信息
                //button.text('上传图片(只允许上传JPG格式的图片,大小不得大于150K)');
                 
                window.clearInterval(interval);
                // enable upload button
                this.enable();
 
                var k = response.replace("<PRE>", "").replace("</PRE>", "");
 
                if (k == '-1') {
                    alert('您上传的文件太大啦!请不要超过150K');
                }
                else {
                    var arr =response.split("/");
                    var url="/"+arr[1]+"/"+arr[2]+"/"+arr[3]+"/"+file;
                    $("#fileList").append('<a href="${ctx!}'+url+'">'+file+'</a> &nbsp;&nbsp;|&nbsp;&nbsp;');
                    button.text('Continue to upload');
                }
            }
        });
 
    });
	
});

</script> 


<!-- Dynamic page generated in 0.606 seconds. --> 
<!-- Cached page generated by WP-Super-Cache on 2015-11-09 15:27:54 --> 

<!-- Compression = gzip -->
</body>
</html>