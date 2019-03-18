<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/css/index-min.css" rel="stylesheet" />
<title>个人信息</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="apple-touch-icon-precomposed" href="apple-touch-icon.png">
<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
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
			            <li><a href="/">Home</a></li>
			            <li><a href="">Tours</a></li>
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
                                  <div class="na-img-bg-area"></div>
                                </div>
                                <div class="naImgLink">
                                    <a class="color4a9" href="" title="设置头像">设置头像</a>
                                </div>
                              </div>        
                            </div>
                            <div class="main_r">
                                <h3 class="fdata">Basic data</h3> 
                                <div class="forms">
                                  <form name="kform" method="post">
                                      <div class="control-group">
                                          <label for="email" class="name">Email <span class="fc03">*</span></label>
                                          <div class="field">
                                              <div>
                                                  <input type="text" id="email" name="email" class="input-large" value="" >
                                                  <a class="ml5" href="">申请修改</a>
                                              </div>
                                          </div>
                                      </div>
                                      <div class="control-group control-tel" id="Juphone">
                                          <label for="email" class="name">Phone NO. <span class="fc03">*</span></label>
                                          <div class="field">
                                              <div>
                                                  <input type="text" name="phone" class="input-large">
                                              </div>
                                              <div class="ml5">手机号可用于登录，接收短信通知等</div>
                                          </div>
                                      </div>
                                      <div class="control-group">
                                          <label for="name" class="name">Last name <span class="fc03">*</span></label>
                                          <div class="field"><input type="text" id="name" name="name" class="input-large" placeholder="" value=""></div>
                                      </div>
                                      <div class="control-group">
                                          <label for="name" class="name">Frist name <span class="fc03">*</span></label>
                                          <div class="field"><input type="text" id="name" name="name" class="input-large" placeholder="" value=""></div>
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
                                              <select name="gender" class="select-large">
                                                  <option value=""></option>
                                                  <option value="0">女</option>
                                                  <option value="1">男</option>
                                              </select>
                                          </div>
                                      </div>
                                      <div class="control-group">
                                          <label for="birth" class="name">Birthday <span class="fc03">*</span></label>
                                          <div class="field"><input type="text" id="birth" name="birth" class="input-large hasDatepicker" placeholder="" value=""></div>
                                      </div>
                                      <div class="control-group">
                                          <label for="country" class="name">Country <span class="fc03">*</span></label>
                                          <div class="field"><input type="text" id="country" name="country" class="input-large" placeholder="" value=""></div>
                                      </div>
                                      <div class="control-group">
                                          <label for="state" class="name">State / Province</label>
                                          <div class="field"><input type="text" id="state" name="state" class="input-large" placeholder="" value=""></div>
                                      </div>
                                      <div class="control-group">
                                          <label for="city" class="name">City</label>
                                          <div class="field"><input type="text" id="city" name="city" class="input-large" placeholder="" value=""></div>
                                      </div>
                                      <div class="control-group">
                                          <div class="field"><input type="submit" name="button" id="submit_button" value="Update Profile" class="btn btn-primary"></div>
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
$(function(){
	$('input').customInput();
});
</script>
<script type="text/javascript">
if (typeof jQuery.fn.fitVids === "undefined") { document.write("<script src='${ctx!}/assets-web/css/js/jquery.fitvids.min.js'>\x3C/script>"); }

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