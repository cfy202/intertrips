<!DOCTYPE html>
<html lang="en-US" >
<head>
<#assign ctx = request.contextPath />
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${(promotion.title)!}</title>
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<link rel="shortcut icon" href="${ctx!}/favicon.ico">
<link rel="apple-touch-icon-precomposed" href="apple-touch-icon.png">
<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
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
<section class="hero small-hero" style="background-image: url(${ctx!}/assets-web/images/deal_image.jpg) ;color: #fff;position: relative;background-color:#6c6e73;background-position: center;background-size: cover;background-repeat: no-repeat;">
  <div class="bg-overlay">
    <div class="container" style="">
      <div class="breadcrumbs">
          <ul>
              <li><a href="${ctx!}/">Home</a></li>
              <li><a href="">Tours</a></li>
          </ul>
      </div>
      <div class="list-intro-wrap">
        <h1 class="intro-title" style="text-align:center;"></h1>
        <div class="intro-text"></div>
      </div>
    </div>
  </div>
</section>

<section class="featured-destinations" style="background-color:#f5f6f6;">
  <div class="container">
    <div class="" style="margin-top:20px;">
		<div class="tour-list">
			<#--线路列表循环开始-->
			<div class="tour-list row" id="">
			<#if tourlinelist?has_content>
				<#assign i = 1>
				<#list tourlinelist as tourlinelist>
						<#if i%30=1 && i!=1>
			</div>
			<div class="tour-list row" id="" style="display:none">
						</#if>
				<div class="col-md-4 col-sm-6" style="visibility: visible; margin-bottom:20px;">
				    <div class="atgrid-item">
				        <div class="atgrid-item-top">
				            <a href="${ctx!}${(tourlinelist.productProductid.pagePageid.filepath)!}" target="_blank" class="atgrid-item-top-image" style=" background-image:url(${ctx!}${(tourlinelist.coverimageurl)!});">
				                <div class="featured-img-inner"></div>
				            </a>	
				            <div class="atgrid-item-angle-wrap">
				        	<#if tourlinelist.promotionList?has_content>
				        		<#list tourlinelist.promotionList as promotion>
				                <div class="atgrid-item-angle" style="background-color:#ed6f42;">${(promotion.title)!}</div>
				        		<#break>
				        		</#list>
				        	</#if>						
				            </div>							
				            <div class="atgrid-item-price">
				                <div class="atgrid-item-price-button">
				                <#if tourlinelist.discountPrice?? && tourlinelist.lowsprice?? && tourlinelist.discountPrice = tourlinelist.lowsprice>
				                    <ins><span class="amount">${(tourlinelist.cost.sign)!}${(tourlinelist.lowsprice)!}</span></ins>
			                    <#else>
			                    	<del><span class="amount">${(tourlinelist.cost.sign)!}${(tourlinelist.lowsprice)!}</span></del> 
				                    <ins><span class="amount">${(tourlinelist.cost.sign)!}${(tourlinelist.discountPrice)!}</span></ins>
			                    </#if>
				                </div>
				            </div>	
				        </div>
				
				        <div class="atgrid-item-content">
				            <h4 class="atgrid-item-title">
				                <a href="${ctx!}${(tourlinelist.productProductid.pagePageid.filepath)!}" target="_blank">${(tourlinelist.tourname)!}</a>
				            </h4>
				            <div class="atgrid-item-description">
				            	<#if tourlinelist.highlights?has_content>
					            	<#assign highlightsList = tourlinelist.highlights?split("^^")>
						            <#list highlightsList as highlight>
						            	<#if highlight_index < 2>
						              	${highlight}<br/>
						              	</#if>
						            </#list>
						            <#if highlightsList?size = 1>
						            <br/>
						            </#if>
					            <#else>
					            <br/><br/>
					            </#if>
				            </div>
				            <div class="atgrid-item-icons">
				            	<#assign serviceItemList = tourlinelist.serviceItemList>
				            	<#if serviceItemList?has_content>
				            		<#list serviceItemList as serviceItem>
				                <i title="${(serviceItem.tittle)!}" class="td-icon1"><img src="${ctx!}${(serviceItem.icoUrl)!}"></i>
				                	</#list>
				                </#if>
				            </div>	
				            <div class="atgrid-item-rating">
				            	<#list 1..5 as n>
				            		<#if tourlinelist.rate?? && (tourlinelist.rate >= n)>		
				            	<i class="fa fa-star voted"></i>
				            		<#else>
				            	<i class="fa fa-star-o"></i>	
				            		</#if>
				                </#list>
				                <#if tourlinelist.reviewCount?? && tourlinelist.reviewCount !=0>
				                <a href="${ctx!}${(tourlinelist.productProductid.pagePageid.filepath)!}" target="_blank" class="">(${(tourlinelist.reviewCount)!})</a>
				                </#if>
				            </div>
				        </div>
				        <div class="item-attributes">
				            <div class="item-attributes-item">
				                <div class="item-attributes-item-content">
				                    <div class="item-attributes-item-content-item">
				                        <i class="fa fa-clock-o"></i>
				                    </div>
				                    <div class="item-attributes-item-content-item">
				                        <span>${(tourlinelist.days)!} days</span>
				                    </div>
				                </div>
				            </div>
				          <#assign departures ="dddd">
				            <#assign stemp =0>
				              <#list tourlinelist.departure?split("','") as departures > 
				                <#if departures?date("yyyy-MM-dd") gt now?date("yyyy-MM-dd")>
				                   <#if stemp =0>
						                 <#assign departures = departures > 
						                 <#assign stemp =1>
						            </#if>
				                </#if>
				            </#list>
				            
				            <div class="item-attributes-item">
				                <div class="item-attributes-item-content">
				                    <div class="item-attributes-item-content-item">
				                        <i class="fa fa-calendar"></i>
				                    </div>
				                    <div class="item-attributes-item-content-item">
				                       <input id="d46" type="text" value=${departures!} class="Wdate" readonly="true" onFocus="WdatePicker({skin:'twoer',lang:'en',minDate:'%y-%M-{%d+1}',opposite:true,disabledDates:['${(tourlinelist.departure)!}']})"/>
				                    </div>
				                </div>
				            </div>				
				            <div class="item-attributes-item">
				                <a href="${ctx!}${(tourlinelist.productProductid.pagePageid.filepath)!}" target="_blank" class="item-attributes-link"><i class="fa fa-long-arrow-right"></i></a>
				            </div>
				        </div>
				    </div>
				</div>
				<#assign i = i+1>
				</#list>
			</#if>
			<#--线路列表循环结束-->
			</div>
			<#--页数
			<div class="text-center" id ="downPaging">
			<#if tourlinelist?has_content>
			  	<#assign amount = tourlinelist?size>
	            <#assign pageAmount = 1>
	            <#if amount%10 = 0>
	            	<#assign pageAmount = amount/10>
	            <#else>
					<#assign pageAmount = amount/10 +1>
	            </#if>
            	<ul class="page-numbers">
            	<#list 1..pageAmount as index>
        			<#if index = 1>
                    <li><span class="page-numbers current">1</span></li>
                    <#else>
                    <li><a class="page-numbers" href="#">${index!}</a></li>
                    </#if>
                </#list>
            	</ul>
			</#if>
			</div>	
			-->
			<#--页数-->
					
		</div>
	</div>
  </div>
</section>
<#--bottom-->
<#include "/front/include/bottom.ftl"/>
<script type='text/javascript' src='${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js'></script>
<#--bottom-->
<script type="text/javascript">
if (typeof jQuery.fn.fitVids === "undefined") { document.write("<script src='${ctx!}/assets-web/js/jquery.fitvids.min.js'>\x3C/script>"); }
</script> 
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-38017454-1', 'auto');
  ga('send', 'pageview');

</script>
<script>
//点击页数效果
$("#downPaging li").click(function(){
	var liPage = $("#downPaging li");
	$.each(liPage,function(i,n){
		var pageNum = $(n).text();
		$(n).html("<a class=\"page-numbers\" href=\"#\">"+pageNum+"</a>");
	})
	$(this).html("<span class=\"page-numbers current\">"+$(this).text()+"</span>");
   	var index = $(this).index();
    $(".tour-list").hide();
    $(".tour-list:eq(" + index + ")").show();
})
</script>
<!-- Dynamic page generated in 0.606 seconds. --> 
<!-- Cached page generated by WP-Super-Cache on 2015-11-09 15:27:54 --> 

<!-- Compression = gzip -->
</body>
</html>