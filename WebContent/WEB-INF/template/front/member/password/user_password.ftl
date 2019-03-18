<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index.css" rel="stylesheet" />
<title>Change the password</title>
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

<script type="text/javascript">
	$(document).ready(function() {
		// 表单验证
		$("#user_passwordForm").validate({
			rules: {
				currentPassword: {
					required: true,
					remote: {
						url: "check_current_password.do",
						cache: false
					}
				},
				userpassword: {
					required: true,
					minlength: 6,
					maxlength: 20
				},
				rePassword: {
					required: true,
					equalTo: "#userpassword"
				}
			},
			messages: {
			    currentPassword:{
			        required:"* Please enter the current password",
			        remote:"* Current password error",
			    },
				userpassword: {
					required: "* Please enter a new password",
					minlength: "* Password length can not be less than 6 characters",
					maxlength: "* Password length can not be greater than 20 characters"
				},
				rePassword: {
					required: "* Please enter a confirmation password",
					equalTo: "* Two input passwords are not consistent"
				}
			}
		});
		
		 $('.close').click(function() {
		   $(".alert-success").hide();
		   $(".alert-error").hide();
	    })
	
	});
	</script>
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
			            <li><a href="${ctx!}/member/profile/edit_password.htm">Change password</a></li>
			          </ul>
			        </div>
			<#include "/front/include/user_left.ftl"/>
	            </div>
        </div>
        <div class="span16">
            <div class="uc-box uc-main-box">
                <div class="uc-content-box portal-content-box">
                	<div class="box-hd">
                    	<h1 class="title">Change password</h1>
                        <div class="clear"></div>
                    </div>
                    <#if result??>
                 	    
                 	    <#if result.success>
                 	    <div class="alert alert-success">
			            	<button class="close" type="button">×</button>
			                <div class="mt5">
			                    ${result.message}<br>
			                </div>
		                </div>
		                <#else>
		                <div class="alert alert-error">
			            	<button class="close" type="button">×</button>
			                <h4>Please note</h4>
			                <div class="mt5">
			                   ${result.message}<br>
			                </div>
		                </div>
		                </#if>
                 	</#if>
                    <div class="box-bd">
                    	<div class="uinfo c_b">
                            <div class="main_r">
                                <div class="forms">
                                  <form name="kform" id="user_passwordForm" method="post" action="updatepass.do">
                                  	<input type="hidden" name="cValue" class="input-large" value="${cValue}" >
                                      <div class="control-group">
                                          <label for="email" class="name">Old Password</label>
                                          <div class="field">
                                              <div>
                                                  <input type="password" name="currentPassword" class="input-large" value="" >
                                              </div>
                                          </div>
                                      </div>
                                      <div class="control-group control-tel" id="Juphone">
                                          <label for="email" class="name">New Password</label>
                                          <div class="field">
                                              <div>
                                                  <input type="password" id="userpassword" name="userpassword" class="input-large">
                                              </div>
                                          </div>
                                      </div>
                                      <div class="control-group control-tel" id="Juphone">
                                          <label for="email" class="name">New Password'field</label>
                                          <div class="field">
                                              <div>
                                                  <input type="password" name="rePassword" class="input-large">
                                              </div>
                                          </div>
                                      </div>
                                      <div class="control-group">
                                          <div class="field"><input type="submit" name="button" id="submit_button" value="Confirm" class="btn btn-primary"></div>
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