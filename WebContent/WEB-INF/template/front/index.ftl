<!DOCTYPE html>
<html lang="en-US" >
	<head>
	<meta charset="UTF-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="keywords" content="${(page.metakeywords)!}" />
	<meta name="description" content="${(page.metadescription)!}"/>
	<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
	<title>${(page.metatitle)!}</title>
	<link rel="shortcut icon" href="${ctx!}/favicon.ico">
	<link rel="apple-touch-icon-precomposed" href="apple-touch-icon.png">
	<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/fonts/fontawesome/css/font-awesome.min.css" type='text/css' media='all' />
	<#--
	<link rel='stylesheet' id='google-font-css'  href='https://fonts.googleapis.com/css?family=Lato:400,400italic,700,700italic|Cabin:600,600italic,400,400italic,700,700italic&#038;subset=latin,latin-ext' type='text/css' media='all' />
	-->
	
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
	<#--top-->
	<#include "/front/include/top.ftl"/>
	<#--top-->
	<section class="hero  large-hero hero-overlap" style="background-image: url(${ctx!}${(page.imageurl)!}) ;color: #fff;
	    position: relative;
	    background-color: #6c6e73;
	    background-position: center;
	    background-size: cover;
	    background-repeat: no-repeat;">
	  <div class="bg-overlay">
	    <div class="container" style="">
	      <div class="intro-wrap">
	        <#--
	        <h1 class="intro-title" style="font-size:42px;margin-bottom:0;">Where your journey begins.</h1>
	        <div class="intro-text"> Discover your next great adventure, become an explorer to get started!</div>
	        <div class="banner-search">
	        	<form action="${ctx!}/search.htm" method="post">
	                <div class="search-where">
	                	<input class="search-input" type="text" id="tt" name="destination" placeholder="Where are you going...">
	                    <i class="fa fa-map-marker" ></i>
	                </div>
	                <div class="search-when hidden-xs">
	                	<input class="date-pick search-input" id="dateFrom" data-date-format="mm/dd/yyyy" type="text" name="dateFrom" placeholder="When..." onClick="WdatePicker({skin:'twoer',lang:'en',minDate:'%y-%M-{%d+1}'})">
	                    <i class="fa fa-calendar" ></i>
	                </div>
	                <div class="search-btn">
	                    <input type="hidden" name ="costnumberF" value="${(costFresh.id)!}">
	                	<input type="submit" value="" class="button-search">	    
	                </div>
	            </form>
	            <div class="clear"></div>
	      	</div>
	      	-->
	      </div>
	    </div>
	  </div>
	</section>
	
	<#--Featured Destinations-->
	<section class="featured-destinations" style="background-color:#f5f6f6;">
	  <div class="container">
	    <div class="cards overlap">
	      <div class="title-row">
	        <h3 class="title-entry">Featured Destinations</h3>
	        <a href="${ctx!}/findMore.htm?costnumberF=${(costFresh.id)!}" class="btn btn-primary btn-xs">Find More &nbsp; <i class="fa fa-angle-right"></i></a></div>
	      <div class="row">
	      	<#list destinationUp as destinationUp>
      			<#if destinationUp_index !=0 && destinationUp_index%3 = 0>
  		  </div>
	      <div class="row">	
	      		</#if>
	        <div class="col-md-4 col-sm-6">
	          <article class="card"> <a href="${ctx!}${(destinationUp.desUrl)!}" class="featured-image" style=" background-image:url(${ctx!}${(destinationUp.imageUrl)!});">
	            <div class="featured-img-inner"></div>
	            </a>
	            <div class="card-details">
	              <h4 class="card-title"><a href="${ctx!}${(destinationUp.desUrl)!}">${(destinationUp.name)!}<#if destinationUp.upName?has_content>,&nbsp;${(destinationUp.upName)!}</#if></a></h4>
	              <#--
	              <div class="meta-details clearfix">
	                <ul class="hierarchy">
	                  <li class="symbol"><i class="fa fa-map-marker"></i></li>
	                  <li><a href="">Oceania</a></li>
	                </ul>
	              </div>
	              -->
	            </div>
	          </article>
	        </div>
	        </#list>
	        <#--
	        <div class="col-md-3 col-sm-6">
	          <article class="card"> <a href="" class="featured-image" style="background-image: url(${ctx!}/assets-web/background/img_4.jpg)">
	            <div class="featured-img-inner"></div>
	            </a>
	            <div class="card-details">
	              <h4 class="card-title"><a href="">San Francisco, USA</a></h4>
	              <div class="meta-details clearfix">
	                <ul class="hierarchy">
	                  <li class="symbol"><i class="fa fa-map-marker"></i></li>
	                  <li><a href="">North America</a></li>
	                </ul>
	              </div>
	            </div>
	          </article>
	        </div>
	        -->
	      </div>
	    </div>
	  </div>
	</section>
	<#--Featured Destinations--> 
	
	<#--deals and discounts-->
	<#if promotionList?has_content>
	<section class="featured-destinations" style="background-color:#f5f6f6;">
	  <div class="container">
	    <div class="cards overlap" style="margin-top:0;">
	      <div class="title-row">
	        <h3 class="title-entry-2" style="font-size:20px;">Deals And Discounts</h3>
	        <a href="${ctx!}/front/tourlinelist/list.do" class="btn btn-primary btn-xs">Find More &nbsp; <i class="fa fa-angle-right"></i></a>
	       <#-- <p class="title-main">Sagittis vulputate magna sagittis sagittis erat feugiat nullam </p>-->
	      </div>
	      <div class="row">
	      	<#list promotionList as promotion>
	      		<#if promotion_index !=0 && promotion_index%3 = 0>
  		  </div>
	      <div class="row">	
	      		</#if>
	        <div class="col-md-4 col-sm-6">
	          <article class="card">
	          	<div class="featured-top"> 
	            	<a href="${ctx!}${(promotion.filePath)!}" class="price-round" style="">
	                	<div class="price-round-bg"></div>
	                	<span class="price-round-content">
	                		<#--
	                    	<del><span class="amount">$2,500</span></del> 
	                        <ins><span class="amount">$2,200</span></ins>
	                        -->
                         	<#if promotion.type?? && promotion.type=1>
	                        <ins><span class="amount">
	                        <#if promotion.discount?has_content>
	                        <#assign discount=(1-promotion.discount)>
	                         ${discount?string("percent")}Off
	                        </#if>
	                        </span></ins>
	                        </#if>
	                    	<#if promotion.type?? && promotion.type=2>
	                        <ins><span class="amount">${(promotion.reduce)!}${costSign!}Off</span></ins>
	                        </#if>
	                    </span>
	                </a>
	                <a href="${ctx!}${(promotion.filePath)!}" class="featured-image" style="background-image: url(${ctx!}${(promotion.imageurl)!})">
	                	<div class="featured-img-inner"></div>
	                </a>
	            </div>
	            <div class="card-details card-height">
	              <h4 class="card-title"><a href="${ctx!}${(promotion.filePath)!}">${(promotion.title)!}</a></h4>
	              <div class="meta-details clearfix">
	              	<p class="meta-details-main">${(promotion.description)!}</p>
	              </div>
	            </div>
	          </article>
	        </div>
	        </#list>
	        <#--
	        <div class="col-md-3 col-sm-6">
	          <article class="card"> 
	          	<div class="featured-top"> 
	            	<a href="" class="price-round" style="">
	                	<div class="price-round-bg"></div>
	                	<span class="price-round-content">
	                    	<del><span class="amount">$2,500</span></del> 
	                        <ins><span class="amount">$2,200</span></ins>
	                    </span>
	                </a>
	                <a href="" class="featured-image" style="background-image: url(${ctx!}/assets-web/background/img_6.jpg )">
	                	<div class="featured-img-inner"></div>
	                </a>
	                
	            </div>
	            <div class="card-details">
	              <h4 class="card-title"><a href="">Yellowstone, USA</a></h4>
	              <div class="meta-details clearfix">
	                <p class="meta-details-main">Semper penatibus bibendum lorem magnis aenean rutrum</p>
	              </div>
	            </div>
	          </article>
	        </div>
	        -->
	      </div>
	    </div>
	  </div>
	</section>
	</#if>
	<#--deals and discounts-->
	
	<#--Explore the world-->
	<section class="featured-destinations" style="background-color:#ffffff;">
	  <div class="container">
	    <div class="cards overlap" style="margin-top:0;">
	      <div class="title-row" style=" text-align:center;">
	        <h3 class="title-entry-2" style="font-size:20px; padding-right:0;">Explore the world</h3>
	        <p class="title-main" style="margin-top:0; padding-right:0;">See where people are traveling, all around the world.</p>
	      </div>
	      
	      <div class="row">
	      	<#assign n = 1>
	      	<#list destinationDown as destinationDown>
	      		<#if n = 1>
	        <div class="col-md-8 col-sm-6">
	          <article class="card">
	              <a href="${ctx!}${(destinationDown.desUrl)!}" class="featured-image featured-image-2" style="background-image: url(${ctx!}${(destinationDown.imageUrl)!} ); text-align:center; text-decoration:none;">
	                  <span class="featured-place"><strong>${(destinationDown.name)!}</strong></span>
	                  <div class="featured-img-inner"></div>
	              </a>
	          </article>
	        </div>
	        	</#if>
	        	<#if n != 1>
	        		<#if n%3 = 0>
	 	  </div>
	 	  <div class="row">
	        		</#if>
	        <div class="col-md-4 col-sm-6">
	          <article class="card"> 
	          	<a href="${ctx!}${(destinationDown.desUrl)!}" class="featured-image featured-image-2" style="background-image: url(${ctx!}${(destinationDown.imageUrl)!}  ); text-align:center; text-decoration:none;">
	                <span class="featured-place"><strong>${(destinationDown.name)!}</strong></span>
	                <div class="featured-img-inner"></div>
	            </a>
	          </article>
	        </div>
	        	</#if>
	        	<#assign n=n+1>
        	</#list>
	      </div>
	      
	      
	    </div>
	  </div>
	</section>
	<#--Explore the world-->
	
	<#--bottom-->
	<#include "/front/include/bottom.ftl"/>
	<#--bottom-->
    <script type='text/javascript' src='${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js'></script>
	<script type="text/javascript">
	if (typeof jQuery.fn.fitVids === "undefined") { document.write("<script src='${ctx!}/assets-web/js/jquery.fitvids.min.js'>\x3C/script>"); }</script> 
	<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-38017454-1', 'auto');
  ga('send', 'pageview');

</script>
<script type="text/javascript" async=""> 
;(function(o,l,a,r,k,y){if(o.olark)return; 	r="script";y=l.createElement(r);r=l.getElementsByTagName(r)[0]; y.async=1;y.src="//"+a;r.parentNode.insertBefore(y,r); y=o.olark=function(){k.s.push(arguments);k.t.push(+new Date)}; y.extend=function(i,j){y("extend",i,j)}; y.identify=function(i){y("identify",k.i=i)}; y.configure=function(i,j){y("configure",i,j);k.c[i]=j}; k=y._={s:[],t:[+new Date],c:{},l:a}; })(window,document,"static.olark.com/jsclient/loader.js");
/* custom configuration goes here (www.olark.com/documentation) */
olark.identify('4785-162-10-1638');
</script>
<script type="text/javascript">
/* <![CDATA[ */
var olark_vars = {"site_ID":"4785-162-10-1638","expand":"0","float":"0","override_lang":null,"lang":"en-US","api":"","mobile":"0","woocommerce":"1","woocommerce_version":"3.2.6","enable_cartsaver":"0"};
/* ]]> */
</script>
	<!-- Dynamic page generated in 0.606 seconds. --> 
	<!-- Cached page generated by WP-Super-Cache on 2015-11-09 15:27:54 --> 
	
	<!-- Compression = gzip -->
	</body>
</html>