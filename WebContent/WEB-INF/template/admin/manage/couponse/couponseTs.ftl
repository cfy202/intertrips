<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>优惠券_文景假期</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/style.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/script.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
    <script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	  ga('create', 'UA-67304615-1', 'auto');
	  ga('send', 'pageview');
	
	</script>
</head>

<body>
    <#include "/front/include/top.ftl"/>
    <div class="clear"></div>
    <div class="widthCenter place">当前位置：<a href="${ctx!}/" class="a1">首页</a> &gt;
    <#if keyword?has_content>
    	“<span style="color:#e60012;font-size:16px">${keyword!}</span>”可使用优惠券线路
	<#else>可使用优惠券线路
	</#if>
    </div>
    <div class=" widthCenter line_filter shadow">
    
	<#--
	<ul class="select">
    	<#if attractions?has_content || start?has_content>	根据是否搜到线路判断页面显示
        	<li class="select_result">
                <dl>
                    <dt>您已选择：</dt>
                    <dd class="select_no">暂时没有选择过滤条件</dd>
                    
                </dl>
                <p class="select_colse">
                    	<a href="">全部清除</a>
                </p>
                <div class="clear"></div>
            </li>
           	<li class="select_list">
                <dl id="select1">
                    <dt>出发城市：</dt>
                    <p class="select_all selected"><a >全部</a></p>
                    <div class="frca" style="display:none">
                    	<#list start as start>
                        <dd><a>${start!}</a></dd>
                        </#list>
                    </div>
                </dl>
                <div id="City">
	                <p class="select_colse" id="frca">
	                    <a class="frc" onclick="more(this)"><i class="select_arrow"></i>更多</a>
	                </p>
                </div>
            </li>
            <li class="select_list">
                <dl id="select2">
                    <dt>游玩地区：</dt>
                    <p class="select_all selected"><a >全部</a></p>
                    <div class="fraa" style="display:none">
                    	<#list attractions as attractions>
                        <dd><a>${attractions!}</a></dd>
                        </#list>
                    </div>
                </dl>
                <div id="Attr">
	                <p class="select_colse" >
	                    <a class="fra" onclick="more(this)"><i class="select_arrow"></i>更多</a>
	                </p>
                </div>
            </li>
            <li class="select_list">
                <dl id="select3">
                    <dt>行程天数：</dt>
                    <p class="select_all selected"><a >全部</a></p>
                    <div class="fr">
                        <dd><a>1天以内</a></dd>
                        <dd><a>2-3天</a></dd>
                        <dd><a>4-6天</a></dd>
                        <dd><a>7-10天</a></dd>
                        <dd><a>11天以上</a></dd>
                    </div>
                </dl>
            </li>
            <li class="select_list">
                <dl id="select4">
                    <dt>价格：</dt>
                    <p class="select_all selected"><a >全部</a></p>
                    <div class="fr">
                        <dd><a>100以内</a></dd>
                        <dd><a>101-200</a></dd>
                        <dd><a>201-500</a></dd>
                        <dd><a>501-1000</a></dd>
                        <dd><a>1001以上</a></dd>
                    </div>
                </dl>
            </li>
        -->
        <#if tourlinelist?has_content>	<#--根据是否搜到线路判断页面显示-->
        <#else>
	 <ul class="select">
         <li class="select_list">
            <dl style="font-size:16px">
            	很抱歉，没有搜索到相关的产品。
            </dl>
        </li>
	</ul>
        </#if>    

    </div>
    <div class="clear"></div>
    <div class="widthCenter line_filter">
    	<div class="list_cont_left">
    	<#if tourlinelist?has_content>	<#--根据是否搜到线路判断页面显示-->
        	<div class="list_cont_sort shadow">
               <div class="list_cont_sort_1">
                  <a title="推荐" class="a1" id="hot">推荐</a>
                  <a title="按天数从少到多排序" class="a1" id="daydowntotop">天数<i class="list_icon"></i></a>
                  <a title="按天数从多到少排序" class="a1" id="daytoptodown" style="display:none">天数<i class="list_icon_1"></i></a>
                  <a title="按价格从低到高排序" class="a1" id="pricedowntotop">价格<i class="list_icon"></i></a>
                  <a title="按价格从高到低排序" class="a1" id="pricetoptodown" style="display:none">价格<i class="list_icon_1"></i></a>
                  <input type="hidden" id="sort" value="hot"/>
                  <div class="clear"></div>
               </div> 
               <div class="list_cont_page" id="upPaging">
               
               </div>
              
            </div>
            <div class="clear"></div>
            <div class="list_cont_box line_filter" id="TOUR">
        		<#include "/front/include/tourList.ftl"/>	
            </div>
            <div id ="downPaging"></div>
        </#if>
        </div>
        <div class="list_cont_right">
        	<div class="side_column shadow">
                <h3>文景假期旅游特色</h3>
                <p class="flag"><i class="i1"></i>13年专注服务海外华人</p>
                <p class="tel"><i class="i2"></i>50万人次选择文景假期出游</p>
                <p class="book"><i class="i3"></i>覆盖美欧亚三大洲的线下零售服务网点</p>
                <p class="tel"><i class="i2"></i>上千条优质跟团游线路</p>
                <p class="tel"><i class="i2"></i>国粤英西4语客户服务</p>
            </div>
            <#--
            <div class="ad_custom shadow">
            	<img class="ad_custom_img" src="${ctx!}/assets-web/images/custom.jpg" width="179" height="65">
            </div>
            -->
            <div class="ad_custom shadow">
            	<h3>热门产品</h3>
                <ul class="hot_pro">
                <#if tourlinehot?has_content>
                	<#list tourlinehot as tourlinehot>
                    <li>
                        <a href="${ctx!}${(tourlinehot.productProductid.pagePageid.filepath)!}">
                        <img src="${ctx!}${(tourlinehot.coverimageurl)!}" width="185" height="125" style="display: inline;">
                        </a>
                        <a href="${ctx!}${(tourlinehot.productProductid.pagePageid.filepath)!}" class="a1">${(tourlinehot.tourname)!}</a>
                        <p class="hot_price">${(tourlinehot.cost.code)!}&nbsp;${(tourlinehot.lowsprice)!}<em>起</em></p>
                    </li>
                    </#list>
                </#if>
    			</ul>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <#include "/front/include/bottom.ftl"/>
</body>
<script>
$(function(){
	$(this).find(".nav_left_pos").hide();
	//$(this).find(".nav_left_pos").stop(false, false).slideUp(300);

	//nav下拉
    $(".nav_left").mouseenter(function () {
        $(this).find(".nav_left_pos").stop(false, false).slideDown(0);
    });
    $(".nav_left").mouseleave(function () {
        $(this).find(".nav_left_pos").stop(false, false).slideUp(0);
    });
    //nav更多下拉
    $(".nav_left_pos li").mouseenter(function () {
        $(this).find(".nav_left_more").stop(false, false).fadeIn(0);
    });
    $(".nav_left_pos li").mouseleave(function () {
        $(this).find(".nav_left_more").stop(false, false).fadeOut(0);
    });
	
});

//下一页
function goPage(pageNow){
	showTourline(pageNow);
}

//线路展示页面输入页数直接跳转
function selectPage(){
	var page = $("#next_page").val();
	var totalPageCount = $("#tpc").val();
	if(parseInt(page)>parseInt(totalPageCount)){
		page = totalPageCount;
	}
	goPage(page);
}
	
//线路分页查询
function showTourline(pageNow){
	var startCity = $(".citySelect a").text();	//获取选择的出发城市
	var days = $(".itinerarySelect a").text();	//获取选取天数区间
	var price = $(".priceSelect a").text();		//获取选取的价格区间
	var sort = $("#sort").val();				//获取排序条件
	var size = $("#pageSize").val();			//获取页面显示线路个数
	var array = new Array();					//选择的景点的数组
	var att = $(".attractSelect a");			//获取选取的景点
	$.each(att,function(i,n){
		array.push($(n).text());				//添加选择的景点
	})
	var attrs = array.join(",");				//景点数组转换为字符串
	
//	var arrayD = new Array();					//选择的目的地的数组
//	var dest = $(".destinationSelect a");		//获取选取的目的地
//	$.each(dest,function(i,n){
//		arrayD.push($(n).text());				//添加选择的目的地
//	})
//	var dests = arrayD.join(",");				//目的地数组转换为字符串
	$.ajax({
           type:"post",
            url:"${ctx!}/front/tourlinelist/tourPage.do",
            data:{pageNow:pageNow,costnumber:"${costnumber!}",keyword:"${keyword!}",attrs:attrs,
            startCity:startCity,days:days,price:price,sort:sort,size:size},
            dataType:"json",
            success: function(data){
				$("#TOUR").html(data.tourContent);
				$("#downPaging").html(data.downPageContent);
				$("#upPaging").html(data.upPageContent);
            },
            error:function(e){
            },
          })  
}

//线路展示根据价格或者天数等排序
$(".list_cont_sort_1 a").click(function(){
	var ss = $(this).attr("id");
	if(ss=="hot"){
		$("#sort").val("hot");
	}else if(ss=="daydowntotop"){
		$("#sort").val("daydowntotop");
		$(this).hide();
		$("#daytoptodown").show();		
	}else if(ss=="daytoptodown"){
		$("#sort").val("daytoptodown");
		$(this).hide();
		$("#daydowntotop").show();	
	}else if(ss=="pricetoptodown"){
		$("#sort").val("pricetoptodown");
		$(this).hide();
		$("#pricedowntotop").show();	
	}else if(ss=="pricedowntotop"){
		$("#sort").val("pricedowntotop");
		$(this).hide();
		$("#pricetoptodown").show();	
	}
	showTourline(1);
})

//页面显示线路条数
$(".list_cont_num a").click(function(){
	var size = $(this).attr("size");
	$(".list_cont_num a").attr("class","bbb");
	$(this).attr("class","list_cont_num_current");
	$("#pageSize").val(size);
	showTourline(1);
})

 //出发城市，景点更多，收起
 function more(obj){
 	var cla = $(obj).attr("class");
 	$("."+cla+"a").css("height","auto");
 	$(obj).attr("onclick","less(this)");
 	$(obj).html("<i class=\"select_arrowup\"></i>收起");
 }
  function less(obj){
 	var cla = $(obj).attr("class");
 	$("."+cla+"a").css("height","58px");
 	$(obj).attr("onclick","more(this)");
 	$(obj).html("<i class=\"select_arrow\"></i>更多");
 }
 
 $(document).ready(function(){
 	var fraa = $(".fraa").height();
 	var frca = $(".frca").height();
// 	var frda = $(".frda").height();
 	if(parseInt(frca)<=58){
 		$("#City").hide()
 	}else{
 		$(".frca").css("height","58px");
 	}
 	if(parseInt(fraa)<=58){
 		$("#Attr").hide()
 	}else{
 		$(".fraa").css("height","58px");
 	}
 	$(".frca").show();
 	$(".fraa").show();
// 	if(parseInt(frda)<=58){
// 		$("#Dest").hide()
// 	}else{
// 		$(".frda").css("height","58px");
// 	}
 })

</script>

</html>