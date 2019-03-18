<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index.css" rel="stylesheet" />
<title>Couponse</title>
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
			            <li><a href="${ctx!}/member/profile/user_couponse.htm">My couponse</a></li>
			          </ul>
			        </div>
			      <#include "/front/include/user_left.ftl"/>
	            </div>
        </div>
        <div class="span16">
            <div class="uc-box uc-main-box">
                <div class="uc-content-box portal-content-box" style="margin-bottom: 30px;">
                    <div class="box-hd">
                    	<h1 class="title">Coupons</h1>
                        <div class="clear"></div>
                    </div>
                    <div class="box-bd">
                        <div id="J_orderList" style="display:none;"><p class="empty">Currently there is no coupons.</p></div>
                        <div id="J_orderListPages">
                        <#if couponseList?size &gt; 0>
                        	<table cellpadding="0" cellspacing="0" width="100%">
                            	<tr class="uc-content-order-Header" height="35" bgcolor="#f0f0f0">
                            	
                                	<td width="40%" style="padding-left:40px; text-align:left;">Coupons</td>
                                	<td width="15%">Code</td>
                                	<td width="15%">Acquisition time</td>
                                    <td width="15%">Expiration time</td>
                                    <td width="15%">Using a state</td>
                                    
                                </tr>
                                <#list couponseList as couponse>
                                <tr class="uc-content-order-list">
                                	<td style="padding-left:40px; text-align:left;">${(couponse.couponsname)!}</td>
                                    <td>${(couponse.code)!}</td>
                                    <td>${(couponse.winningtime)!}</td>
                                     <td>${(couponse.expirationdate)!}</td>
                                    <td>${(couponse.useStatus)!}</td>
                                </tr>
                               </#list>
                            </table>
                            </#if>
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

<!-- Dynamic page generated in 0.606 seconds. --> 
<!-- Cached page generated by WP-Super-Cache on 2015-11-09 15:27:54 --> 

<!-- Compression = gzip -->
</body>
</html>