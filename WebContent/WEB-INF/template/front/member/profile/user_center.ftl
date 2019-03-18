<!DOCTYPE html>
<html lang="en-US" >
<#assign ctx = request.contextPath />
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<title>User center</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="apple-touch-icon-precomposed" href="apple-touch-icon.png">
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
			            <li><a href="${ctx!}/member/profile/index.htm">User center</a></li>
			          </ul>
			        </div>
			      <#include "/front/include/user_left.ftl"/>
	            </div>
	        </div>
        <div class="span16">
            <div class="uc-box uc-main-box">
                <div class="uc-content-box portal-content-box">
                    <div class="box-bd">
                    	<div class="portal-main">
                            <div class="user-card">
                                <h2 class="username">${member.account}</h2>
                                <p class="tip">
                                <#if time == 0>Good morning！</#if>
				                <#if time == 1>Good afternoon！</#if>
				                </p>
                                <a class="link" href="${ctx!}/member/profile/edit.htm?cValue=${frontCode}" target="_blank">Modify personal information &gt;</a>
                                <img class="avatar" src="${ctx!}/assets-web/images/people.jpg" width="150" height="150" alt="">
                            </div>
                            <div class="user-actions">
                                <ul class="action-list">
                                    <li>Bound phone：<span class="tel">${(info.usermobile)!}</span></li>
                                    <li>Bind mailbox：<span class="tel"></span><a class="btn btn-small btn-primary" href="" target="_blank">${(info.email)!}</a></li>
                                </ul>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="clear"></div>
                        <div class="portal-sub">
                            <ul class="info-list clearfix">
                                <li>
                                    <h3>Order to be paid：<span class="num">0</span></h3>
                                    <a href="${ctx!}/member/profile/user_orders.htm">Check for payment orders<i class="fa fa-angle-right"></i></a>
                                    <img src="${ctx!}/assets-web/images/portal-icon-1.png" alt="">
                                </li>
                                <li>
                                    <h3>To evaluate the order：<span class="num">0</span></h3>
                                    <a href="${ctx!}/member/profile/user_orders.htm">View pending order<i class="fa fa-angle-right"></i></a>
                                    <img src="${ctx!}/assets-web/images/portal-icon-2.png" alt="">
                                </li>
                            </ul>
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
<script type="text/javascript">
$(function(){
	$('input').customInput();
});
</script>

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
<!-- Dynamic page generated in 0.606 seconds. --> 
<!-- Cached page generated by WP-Super-Cache on 2015-11-09 15:27:54 --> 

<!-- Compression = gzip -->
</body>
</html>