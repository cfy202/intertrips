<!DOCTYPE html>
<html lang="en-US" >
<head>
<#assign ctx = request.contextPath />
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><#if tourListPage?? && tourListPage.metatitle?has_content>${(tourListPage.metatitle)!}<#else>Search for the tours</#if></title>
<meta name="keywords" content="${(tourListPage.metakeywords)!}" />
<meta name="description" content="${(tourListPage.metadescription)!}" />
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
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
<section class="hero small-hero" style="background-image: url(<#if tourListPage?? && tourListPage.imageurl?has_content>${ctx!}${(tourListPage.imageurl)!}<#elseif (destination.imageUrl)?? && (destination.imageUrl)?has_content >${ctx!}${(destination.imageUrl)!}<#else>${ctx!}/assets-web/images/tourlist_bg.jpg</#if>) ;color: #fff;position: relative;background-color:#6c6e73;background-position: center;background-size: cover;background-repeat: no-repeat;">
  <div class="bg-overlay">
    <div class="container" style="">
      <div class="breadcrumbs">
          <ul>
              <li><a href="${ctx!}/">Home</a></li>
              <li><a href="">Tours</a></li>
          </ul>
      </div>
      <div class="list-intro-wrap">
        <h1 class="intro-title" style="text-align:center;"><#if tourListPage?? && tourListPage.metatitle?has_content>${(tourListPage.metatitle)!}</#if></h1>
        
      </div>
    </div>
  </div>
</section>

<section class="featured-destinations" style="background-color:#f5f6f6;">
  <div class="container">
    <div class="row" style="margin-top:20px;">
		<aside class="col-lg-3 col-md-3">
			<div id="search_results"><span id="TotalCount"></span> Results found</div>
            <div id="modify_search">
                <div id="modify_col_bt" class=""><i class="fa fa-search"></i> Modify Search</div>
                <div class="collapse in" id="collapseModify_search" aria-expanded="true">
                    <div class="modify_search_wp">
                        <form role="search" method="get" id="search-tour-form" action="">
                            <input type="hidden" name="costnumberF" id='costnumberF' value="${(costFresh.id)!}">
                            <input type="hidden" name="post_type" value="tour">
                            <div class="form-group">
                                <label>Destination</label>
                                <input type="text" class="form-control" id="DES" name="destination" placeholder="Type your search terms..." value="${(destination.name)!}">
                            </div>
                            <div class="form-group">
                                <label>Style</label>
                                <select class="form-control" name="tour_types" id="STYLE">
                                	<option value="" selected="">All tours</option>
                                	<#list regionList as region>
                                	<option value="${(region.id)!}" <#if region.id?? && regioId?? && regioId=region.id>selected="selected"</#if>>${(region.name)!}</option>
                                	</#list>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Keyword</label>
                                <input type="text" class="form-control" id="KW" name="s" placeholder="Search..." value="${keyword!}">
                            </div>
                            <div class="form-group">
                                <label><i class="icon-calendar-7"></i> Date</label>
                                <input class="date-pick form-control" id="dateFrom" value="${dateFrom!}" data-date-format="mm/dd/yyyy" type="text" name="date" placeholder="From..." style="margin-bottom:15px;" onClick="WdatePicker({skin:'twoer',lang:'en'})">
                                <input class="date-pick form-control" id="dateTo" data-date-format="mm/dd/yyyy" type="text" name="date" placeholder="To..." onClick="WdatePicker({skin:'twoer',lang:'en'})">
                            </div>
                            <button type="button" class="btn_1" onclick="showTourline(1)">Search</button>
                        </form>
                    </div>
                </div><!--End collapse -->
            </div>

            <div id="filters_col">
                <div id="filters_col_bt"><i class="fa fa-gear"></i> Filters</div>
                <div class="collapse in" id="collapseFilters">
                	<div class="filter_type">
                    	<h6>Price</h6>
                        <form action="#" method="post">	
                            <fieldset>
                                <input type="checkbox" value="0-200" id="check-1" name="Price" class="PRICE"><label for="check-1" >${frontCode!} 0 - ${frontCode!} 200</label>
                                <input type="checkbox" value="201-500" id="check-2" name="Price" class="PRICE"><label for="check-2" class="">${frontCode!} 201 - ${frontCode!} 500</label>
                                <input type="checkbox" value="501-1000" id="check-3" name="Price" class="PRICE"><label for="check-3" class="">${frontCode!} 501 - ${frontCode!} 1000</label>
                                <input type="checkbox" value="1001-" id="check-4" name="Price" class="PRICE"><label for="check-4" class="">${frontCode!} 1001 +</label>
							</fieldset>
                        </form>
                    </div>
                    <div class="filter_type">
                    	<h6>Rating</h6>
                        <form action="#" method="post">	
                            <fieldset>
                                <input type="checkbox" id="check-5" name="Rating" value="5" class="RATE">
                                <label for="check-5" class="">
                                	<span class="input-rating">
                                    	<i class="fa fa-star voted"></i>
                                        <i class="fa fa-star voted"></i>
                                        <i class="fa fa-star voted"></i>
                                        <i class="fa fa-star voted"></i>
                                        <i class="fa fa-star voted"></i>
                                    </span>
                                </label>
                                <input type="checkbox" id="check-6" name="Rating" value="4" class="RATE">
                                <label for="check-6" class="">
                                	<span class="input-rating">
                                    	<i class="fa fa-star voted"></i>
                                        <i class="fa fa-star voted"></i>
                                        <i class="fa fa-star voted"></i>
                                        <i class="fa fa-star voted"></i>
                                        <i class="fa fa-star-o"></i>
                                    </span>
                                </label>
                                <input type="checkbox" id="check-7" name="Rating" value="3" class="RATE">
                                <label for="check-7" class="">
                                	<span class="input-rating">
                                    	<i class="fa fa-star voted"></i>
                                        <i class="fa fa-star voted"></i>
                                        <i class="fa fa-star voted"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </span>
                                </label>
                                <input type="checkbox" id="check-8" name="Rating" value="2" class="RATE">
                                <label for="check-8" class="">
                                	<span class="input-rating">
                                    	<i class="fa fa-star voted"></i>
                                        <i class="fa fa-star voted"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </span>
                                </label>
                                <input type="checkbox" id="check-9" name="Rating" value="1" class="RATE">
                                <label for="check-9" class="">
                                	<span class="input-rating">
                                    	<i class="fa fa-star voted"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                        <i class="fa fa-star-o"></i>
                                    </span>
                                </label>
							</fieldset>
                        </form>
                    </div>
                    
                </div><!--End collapse -->
            </div><!--End filters col-->
		</aside><!--End aside -->

		<div class="col-lg-9 col-md-8">
			<#--排序start-->
			<div id="tools">
				<div class="row">
					<div class="col-md-3 col-sm-3 col-xs-6">
						<div class="styled-select-filters">
							<select name="sort_price" id="sort_price" >
								<option value="" selected="">Sort by price</option>
								<option value="pricedowntotop">Lowest price</option>
								<option value="pricetoptodown">Highest price</option>
							</select>
						</div>
					</div>
					<div class="col-md-3 col-sm-3 col-xs-6">
						<div class="styled-select-filters">
							<select name="sort_rating" id="sort_rating">
								<option value="" selected="">Sort by rating</option>
								<option value="ratedowntotop">Lowest rating</option>
								<option value="ratetoptodown">Highest rating</option>
							</select>
						</div>
					</div>
					<#if promotionList?has_content>
					<div class="col-md-3 col-sm-3 col-xs-6">
						<div class="styled-select-filters">
							<select name="sort_rating" id="Prom">
								<option value="" selected="">Deals and discounts</option>
								<#list promotionList as promotion>
								<option value="${(promotion.id)!}">${(promotion.title)!}</option>
								</#list>
							</select>
						</div>
					</div>
					</#if>
					<div class="col-md-3 col-sm-3 col-xs-6">
						<div class="styled-select-filters">
							<select name="sort_rating" id="SIZE">
								<option value="4" >Shows 4 records</option>
								<option value="8" >Shows 8 records</option>
								<option value="12" selected ="selected">Shows 12 records</option>
							</select>
						</div>
					</div>
					<input type="hidden" id="SORT" value=""/>
					<input type="hidden" id="isreload" value="${isreload!}"/>
					<input type="hidden" id="SORT" value=""/>
					<#--
					<div class="col-md-6 col-sm-6 hidden-xs text-right">
						<a href="" class="bt_filters" title="Grid View"><i class="icon-th"></i></a>
						<a href="" class="bt_filters" title="List View"><i class="icon-list"></i></a>
					</div>
					-->
				</div>
			</div>
			<#--排序end-->
			
			<#--线路列表start-->
			<div class="tour-list row" id="TOUR">
				<#include "/front/include/tourList.ftl"/>
			</div>
			<#--线路列表end-->
			
			<#--分页start-->
			<div class="text-center" id ="downPaging">
				<#include "/front/include/downPaging.ftl"/>
			</div>	
			<#--分页end-->		
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
<script type="text/javascript">
$(function(){
	$('input').customInput();
});
</script>
<script>
	var isreload = $("#isreload").val();
	var costnumber = $("#costnumberF").val();
	if(isreload!=''&& isreload==1){
	 showTourline(1);
	}
	
	//排序
	$("#sort_price,#sort_rating").change(function(){
		var sort = $(this).val();
		$("#SORT").val(sort);
		showTourline(1);
	})
	//每页显示数量，促销活动
	$("#SIZE,#Prom").change(function(){
		showTourline(1);
	})
	//点击评级
	$(".RATE").click(function(){
		var thisValue = $(this).val();
		var rateCheckbox = $("input[name='Rating']");
		$.each(rateCheckbox,function(i,n){
			if($(n).val() != thisValue ){
				$(n).attr("checked",false);
			}
		})
		$(rateCheckbox).customInput();
		showTourline(1);
	})
	//点击价格
	$(".PRICE").click(function(){
		var thisValue = $(this).val();
		var priceCheckbox = $("input[name='Price']");
		$.each(priceCheckbox,function(i,n){
			if($(n).val() != thisValue ){
				$(n).attr("checked",false);
			}
		})
		$(priceCheckbox).customInput();
		showTourline(1);
	})
	//下一页
	function goPage(pageNow){
		showTourline(pageNow);
	}
	//线路分页查询
	function showTourline(pageNow){
		var destination = $("#DES").val();
		var regionId = $("#STYLE").val();
		var keyword = $("#KW").val();
		var dateFrom = $("#dateFrom").val();
		var dateTo = $("#dateTo").val();
		var sort = $("#SORT").val();
		var size = $("#SIZE").val();
		var promotionId = $("#Prom").val();
		
		//线路评级
		var selectedRate = $("input[name='Rating']:checked");	//选中的评级
		var arrayRate = new Array();							//评级数组
		$.each(selectedRate,function(i,n){
			arrayRate.push($(n).val());							//添加评级
		})
		var rateString = arrayRate.join(",");					//转化为字符串
//		alert(rateString);

		//线路价格
		var selectedPrice = $("input[name='Price']:checked");	//选中的价格
		var arrayPrice = new Array();							//价格数组
		$.each(selectedPrice,function(i,n){
			arrayPrice.push($(n).val());						//添加价格
		})
		var priceString = arrayPrice.join(",");					//转化为字符串
//		alert(priceString);
		
		$.ajax({
   			type:"post",
            url:"${ctx!}/front/tourlinelist/tourlinePage.do",
            data:{pageNow:pageNow,costnumberF:costnumber,destination:destination,
            regionId:regionId,keyword:keyword,dateFrom:dateFrom,dateTo:dateTo,sort:sort,
            rateString:rateString,priceString:priceString,size:size,promotionId:promotionId},
            dataType:"json",
            success: function(data){
				$("#TotalCount").html(data.totalCount);
				$("#TOUR").html(data.tourContent);
				if(data.totalCount != 0){
					$("#tools").show();
					$("#downPaging").html(data.downPageContent);
				}else{
					$("#downPaging").html("No record found!");
					$("#tools").hide();
				}
            }
           
       })  
	}
</script>
<!-- Dynamic page generated in 0.606 seconds. --> 
<!-- Cached page generated by WP-Super-Cache on 2015-11-09 15:27:54 --> 

<!-- Compression = gzip -->
</body>
</html>