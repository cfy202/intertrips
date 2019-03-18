<!DOCTYPE html>
<html lang="en-US" >
<#assign ctx = request.contextPath />
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--<link type="text/css" media="all" href="css/index.css" rel="stylesheet" />-->
<title>fail</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="apple-touch-icon-precomposed" href="apple-touch-icon.png">
<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv-printshiv.min.js?ver=4.3.1'></script> <![endif]-->
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js?ver=4.3.1'></script> 
<![endif]-->

<meta name="generator">
<style>
body {
    font-family: Lato, "Helvetica Neue", Helvetica, Arial, sans-serif;
    font-size: 15px;
    line-height: 1.6;
    color: #565a5c;
    background-color: #fff;
	margin:0;
}
.Payment_Method {
    background: #fff;
    padding: 20px;
    margin-top: 10px;
    margin-bottom: 20px;
}
body #MainMenu.navbar.scrolled {
    -webkit-transition: all .3s ease 0s;
    -o-transition: all .3s ease 0s;
    transition: all .3s ease 0s;
    background: #363c48;
    -webkit-box-shadow: 0 2px 10px rgba(0,0,0,0.3);
    box-shadow: 0 2px 10px rgba(0,0,0,0.3);
}
.navbar-wrapper .navbar-fixed-top {
    margin-top: 0;
}
.navbar {
    position: relative;
    min-height: 72px;
    margin-bottom: 26px;
    border: 1px solid transparent;
}
.navbar-fixed-top {
    position: fixed;
    right: 0;
    left: 0;
	top:0;
    z-index: 300;
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
}
.container-fluid{
    width: 1240px;
	margin-right: auto;
    margin-left: auto;
    padding-left: 20px;
    padding-right: 20px;
}
.clear{ clear:both;}
.navbar-header {float: left;}
.navbar-brand {
    font-weight: 300;
    padding: 16px 20px;
    line-height: 40px;
	float:left;
	margin-left: -20px;
}
.navbar-brand img {
    vertical-align: middle;
}
.navbar-collapse {
    overflow-x: visible;
    padding-right: 20px;
    padding-left: 20px;
    box-shadow: inset 0 1px 0 rgba(255,255,255,0.1);
    -webkit-overflow-scrolling: touch;
}
#navbar-main .navbar-nav {
    -webkit-transition: all .3s ease .1s;
    -o-transition: all .3s ease .1s;
    transition: all .3s ease .1s;
}
.navbar-right {
    float: right;
	margin:0;
	margin-right:-20px;
	list-style: none;
}
.menu-item{
	float:left;
}
.menu-item a{display: block;padding: 23px 16px;line-height: 26px;
color:#fff;border-top: solid 2px transparent; text-decoration:none;
}
.menu-item a:hover { color: #d9d9d9; background-color: rgba(0,0,0,0.26);border-top:2px solid #0db4ff; }
</style>
<style id="fit-vids-style">
.fluid-width-video-wrapper { width: 100%; position: relative; padding: 0; }
.fluid-width-video-wrapper iframe, .fluid-width-video-wrapper object, .fluid-width-video-wrapper embed { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
</style>
<style id="ShrinkMenu">
.menu-shrink { top : -49px !important; }
</style>
</head>
<body class="blog"  style="background-color:#f5f6f6;">
<div id="top"></div>
<div class="navbar-wrapper">
  <header class="navbar navbar-default navbar-fixed-top scrolled" id="MainMenu" role="navigation">
    <div class="container-fluid collapse-md" id="navbar-main-container">
      <div class="navbar-header">
      	<a href="javascript:void(0);" title="" rel="home" class="navbar-brand"> <img src="${ctx!}/assets-web/images/logo.png" alt=""></a>
      </div>
      <nav class="navbar-collapse collapse" id="navbar-main">
        <ul id="menu-main-menu" class="nav navbar-nav navbar-right">
          <li class="menu-item"><a href="javascript:void(0);">HOME</a></li>
          <li class="menu-item"><a href="javascript:void(0);">TOURS</a></li>
          <li class="menu-item"><a href="javascript:void(0);">PAGES</a></li>
          <li class="menu-item"><a href="javascript:void(0);">CONTACT US</a></li>
        </ul>
        <div class="clear"></div>
      </nav>
      <div class="clear"></div>
    </div>
  </header>
  <div class="clear"></div>
</div>
<section class="container-fluid" style="margin-top:8rem;">
  <div class="container">
    <div class="cards overlap" style="margin-top:0;">
    	<div class="pay_finish_tit">
        	<h2 style="display: inline-block;height: 30px;line-height: 30px;background: url(${ctx!}/assets-web/background/pay_icon_2.png) left top no-repeat;padding-left: 35px; font-size:24px;">${noticeString!}</h2>
        </div>
        <div class="Payment_Method shadow">
            <div class="pay_finish_info">
            	<#--
                <h2 style="font-size:24px;">订单编号：123456789987</h2>
                -->
                <p style="font-size:14px; margin-bottom:0; line-height:24px;">${payExceptionCode!}</p>
                <#--
                <p style="font-size:14px; margin-bottom:0; line-height:24px;">收到您的付款后两个工作日内我们会将最终确认的“电子旅行凭证”发到您的邮箱。</p>
                -->
            </div>
            <div style="margin-top:30px;">
            	<a href="https://www.intertrips.com" style=" display:inline-block; padding: 12px 36px;color: #FFF;text-decoration: none !important;font-size: 14px;font-weight: bold;border-radius: 5px;background: #50b5f7;">Return</a>
            </div>
        </div>
      
    </div>
  </div>
</section>

</body>
</html>