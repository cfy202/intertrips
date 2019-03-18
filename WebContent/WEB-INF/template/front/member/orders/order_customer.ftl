<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index.css" rel="stylesheet" />
<title>Traveler Passport Information</title>
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
                <div class="uc-content-box portal-content-box" style="margin-bottom: 30px;">
                    <div class="box-hd">
                    	<h1 class="title">Traveler Passport Information</h1>
                        
                        <div class="clear"></div>
                    </div>
                    <div class="box-bd">
                        <div id="J_orderList" style="display:none;"><p class="empty">Currently there is no trade order.</p></div>
                        <div id="J_orderListPages">
                         
                          <#assign startolde = 0>
                          <#assign endolde = 0>
                        	<#list tourpassengers as tourpassengers>
                        	<#if tourpassengers.identity?has_content&&tourpassengers.identity='ADULT'>
                        	  <#assign startolde = 111>
                        	  <#assign endolde = 11>
                        	  <#elseif tourpassengers.identity?has_content&&tourpassengers.identity='CHILDREN' >
                        	  <#assign startolde = 2>
                        	  <#assign endolde = 6>
                        	  <#else>
                        	  <#assign startolde = 0>
                        	  <#assign endolde = 2>
                        	</#if>
                        	<#assign read = "">
                        	 <div class="form-info-left">
					            <p class="form-info-num">No.${tourpassengers_index+1}--${(tourpassengers.identity)!}</p>
					             
					         </div>
                        	<#if tourpassengers.passportNo?has_content&&tourpassengers.passportNoExpiryDate?has_content>
                        	     <#assign read = "readOnly='true'">
			                </#if>    
			                      <table class="table form-block-book bookingInformation">
			                           <tr>
								          <td width="50%">
								            <div class="form-block-book-name">
								              <label class="form-block-label label-s">Sur Name</label>
								              <input class="form-block-input input-s lastName" value="${(tourpassengers.lastName)!}" type="text" readOnly="true" placeholder="">
								              <div class="clear"></div>
								            </div>
								          </td>
								          <td width="50%">
								            <div class="form-block-book-name">
								              <label class="form-block-label label-s">Given Name</label>
								              <input class="form-block-input input-s firstName" value="${(tourpassengers.firstName)!}" type="text" readOnly="true" placeholder="">
								              <div class="clear"></div>
								            </div>
								          </td>
								        </tr>
								        <tr>
								          <td width="50%">
								            <div class="form-block-book-name">
								              <label class="form-block-label label-s">Gender</label>
								              <select class="form-block-input input-s gender" id="${(tourpassengers.id)!}gender" ${read!} >
								                <option value="0" <#if tourpassengers.gender??&&tourpassengers.gender=0>selected</#if>>Male</option>
								                <option value="1" <#if tourpassengers.gender??&&tourpassengers.gender=1>selected</#if>>Female</option>
								              </select>
								              <div class="clear"></div>
								            </div>
								          </td>
								          <td width="50%">
								            <div class="form-block-book-name">
								              <label class="form-block-label label-s">Date of Birth</label>
								              <input class="form-block-input input-s birthday Wdate" ${read!} onClick="WdatePicker({skin:'twoer',lang:'en',maxDate:'{%y-${endolde}}-%M-%d',minDate:'{%y-${startolde}}-%M-%d'});"  <#if tourpassengers.birthday?has_content>value="${(tourpassengers.birthday)?string("yyyy-MM-dd")}"</#if>  type="text"  placeholder="">
								              <div class="clear"></div>
								            </div>
								          </td>
								        </tr>
								        <tr>
								          <td width="50%">
								            <div class="form-block-book-name">
								              <label class="form-block-label label-s">Nationality</label>
								              <input class="form-block-input input-s nationality" ${read!} value="${(tourpassengers.nationality)!}" id="${(tourpassengers.id)!}nationality" type="text" placeholder="">
								              <div class="clear"></div>
								            </div>
								          </td>
								          <td width="50%">
								            <div class="form-block-book-name">
								              <label class="form-block-label label-s">Passport No.</label>
								              <input class="form-block-input input-s passportNo" ${read} value="${(tourpassengers.passportNo)!}" id="${(tourpassengers.id)!}passportNo" type="text" placeholder="">
								              <div class="clear"></div>
								            </div>
								          </td>	          
								        </tr>
								        <tr>
								          <td width="50%">
								            <div class="form-block-book-name">
								              <label class="form-block-label label-s">Date of Expiry</label>
								              <input class="form-block-input input-s passportNoExpiryDate Wdate" ${read!} onClick="WdatePicker({skin:'twoer',lang:'en',minDate:'%y-%M-%d'});" <#if tourpassengers.passportNoExpiryDate?has_content>value="${(tourpassengers.passportNoExpiryDate)?string("yyyy-MM-dd")}"</#if> type="text" id="${(tourpassengers.id)!}passportNoExpiryDate" placeholder="">
								              <div class="clear"></div>
								            </div>
								          </td>
								       
								        <td width="50%">
								         <div class="form-block-book-name">
								              <label class="form-block-label label-s"></label>
								              
								              <div class="clear"></div>
								              <#if read=''>
								                 <button class="uc-content-Orderdetails-top-btn"  onClick="tourpassegerUpdate('${(tourpassengers.id)!}')">update</button>
								              </#if>
								              
								            </div>
								        </td>
								       </tr> 
			                      </table>
                        	
                        	 
	                        	 
		                    </#list>  
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
<#-- alert -->
   <#include "/front/include/alertFrame.ftl"/> 
<#-- alert -->

<script type='text/javascript' src='https://cdn.bootcss.com/fitvids/1.1.0/jquery.fitvids.min.js'></script>
<script type='text/javascript' src='${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js'></script>
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
//上传图片后返回并添加图片路径和id
	function tourpassegerUpdate(tourpassegerid) {
	var gender = $("#"+ tourpassegerid +"gender").val();
	var nationality = $("#"+ tourpassegerid + "nationality").val();
	var passportNo = $("#"+tourpassegerid +"passportNo").val();
	var passportNoExpiryDate = $("#"+ tourpassegerid +"passportNoExpiryDate").val();
	
		$.ajax({
			type: "POST",
			url: "${ctx!}/member/profile/tourpassegerUpdate.do",
			data: "tourpassegerid="+tourpassegerid+"&gender=" + gender +"&nationality="+nationality+"&passportNo="+passportNo+"&passportNoExpiryDate="+passportNoExpiryDate,
			cache:false,
			success: function(data) {
			   if(data){
			     alertWarn("The update is successful!");
			   }
			}
		});
	}



</script> 
<script type='text/javascript' src='${ctx!}/assets-web/js/jquery.fitvids.min.js'></script>
<script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>

<!-- Dynamic page generated in 0.606 seconds. --> 
<!-- Cached page generated by WP-Super-Cache on 2015-11-09 15:27:54 --> 

<!-- Compression = gzip -->
</body>
</html>