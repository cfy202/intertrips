<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<title>Personal information</title>
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
                    	<div class="uinfo c_b">
                            <div class="main_l">
                              <div class="naInfoImgBox t_c">
                                <div class="na-img-area marauto">
                                  <div class="na-img-bg-area"><img src="${ctx!}/assets-web/images/people.jpg"></div>
                                </div>
                                <div class="naImgLink">
                                    <a class="color4a9" href="" title="Set head">Set head</a>
                                </div>
                              </div>        
                            </div>
                            <div class="main_r">
                                <h3 class="fdata">Basic data</h3> 
                                <#if result??>
			                 	    <#if result.success>
			                 	    <div class="alert alert-success">
						            	<button class="close" type="button">×</button>
						                <div class="mt5">
						                    ${(result.message)!}<br>
						                </div>
					                </div>
					                <#else>
					                <div class="alert alert-error">
						            	<button class="close" type="button">×</button>
						                <h4>请注意</h4>
						                <div class="mt5">
						                   ${(result.message)!}<br>
						                </div>
					                </div>
					                </#if>
			                 	</#if>
                                <div class="forms">
                                  <form name="kform" method="post" id="user_profileForm" action="${ctx!}/member/profile/update.do">
                                      <div class="control-group">
                                          <label for="email" class="name">Email <span class="fc03">*</span></label>
                                          <div class="field">
                                              <div>
                                                  <input type="text" id="email" name="account" class="input-large" value="${(info.email)!}" >
                                                  <a class="ml5" href="">To apply for modifying</a>
                                              </div>
                                          </div>
                                      </div>
                                      <div class="control-group control-tel" id="Juphone">
                                          <label for="email" class="name">Phone NO. <span class="fc03">*</span></label>
                                          <div class="field">
                                              <div>
                                                  <input type="text" name="usermobile" value="${(info.usermobile)!}" class="input-large">
                                              </div>
                                              <div class="ml5">Mobile phone number can be used to log in, receive text messages, etc</div>
                                          </div>
                                      </div>
                                      <div class="control-group">
                                          <label for="name" class="name">Last name <span class="fc03">*</span></label>
                                          <div class="field"><input type="text" id="name" name="lastName" class="input-large" placeholder="" value="${(info.lastName)!}"></div>
                                      </div>
                                      <div class="control-group">
                                          <label for="name" class="name">Frist name <span class="fc03">*</span></label>
                                          <div class="field"><input type="text" id="name" name="firstName" class="input-large" placeholder="" value="${(info.firstName)!}"></div>
                                      </div>
                                      <div class="control-group">
                                          <label for="age" class="name">Age</label>
                                          <div class="field">
                                              <select name="agegroup" class="select-large">
                                                  <option value=""></option>
                                                  <option value="0-24">&lt;25</option>
                                                  <option value="25-35">25-35</option>
                                                  <option value="36-45">36-45</option>
                                                  <option value="46-55">46-55</option>
                                                  <option value="56+">56+</option>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="control-group">
                                          <label for="gender" class="name">Sex</label>
                                          <div class="field">
                                              <select name="usersex" class="select-large">
                                                 <option value="0" <#if (info.usersex)?? && info.usersex="0">selected="selected"</#if>>A secret</option>
			                                     <option value="1" <#if (info.usersex)?? && info.usersex="1">selected="selected"</#if>>male</option>
			                                     <option value="2" <#if (info.usersex)?? && info.usersex="2">selected="selected"</#if>>female</option>
                                              </select>
                                               
                                          </div>
                                      </div>
                                      <div class="control-group">
                                          <label for="birth" class="name">Birthday <span class="fc03">*</span></label>
                                          <div class="field"><input type="text" id="birth" name="birthday" value="${(info.birthday?string("yyyy-MM-dd"))!}" style="width:170px" onClick="WdatePicker();" readOnly="readOnly" class="input-large hasDatepicker" placeholder="" value=""></div>
                                         
                                      </div>
                                      <div class="control-group">
                                          <label for="country" class="name">Country <span class="fc03">*</span></label>
                                          <div class="field"><input type="text" id="sCountry" value="${(info.country)!}" name="country" class="input-large" placeholder="" value=""></div>
                                      </div>
                                      <div class="control-group">
                                          <label for="state" class="name">State / Province</label>
                                          <div class="field"><input type="text" id="sProvince" value="${(info.province)!}" name="province" class="input-large" placeholder="" value=""></div>
                                      </div>
                                      <div class="control-group">
                                          <label for="city" class="name">City</label>
                                          <div class="field"><input type="text" id="sCity" value="${(info.city)!}" name="city" class="input-large" placeholder="" value=""></div>
                                      </div>
                                      <div class="control-group">
                                          <div class="field">
                                          <input type="submit" name="button" id="submit_button" value="Update Profile" class="btn btn-primary">
                                          <input type="hidden" name="id" value="${info.id}">
							              <input type="hidden" name="memberid" value="${info.memberid}">
                                          </div>
                                      </div>
                                  </form>
                  				</div>   
                           
                            </div>
                            <div class="clear"></div>
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
<script type="text/javascript" src="${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js"></script>
 <script type="text/javascript">
    var Country = "${(info.country)!}";
	var Province = "${(info.province)!}";
    var City = "${(info.city)!}";
	var Nationality = "${(info.nationality)!}";
	 $(document).ready(function() {
			$("form#user_profileForm").validate({
				rules: {
					usertel: {
		                number : true
					},
					usermobile: {
					    number : true
					},
					postalcode: {
					    number: true
					},
					passportNo: {
					    number: true
					}
				},
					
				messages: {
					usertel: {
		                number : "* 请输入正确电话号码"
					},
					usermobile: {
					    number : "* 请输入正确手机号码"
					},
					postalcode: {
					    number: "* 请输入正确邮编"
					},
					passportNo: {
					    number: "* 请输入正确护照号"
					}
				},
			})
			
		$('.close').click(function() {
		   $(".alert-success").hide();
		   $(".alert-error").hide();
	    })
	});
</script>
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