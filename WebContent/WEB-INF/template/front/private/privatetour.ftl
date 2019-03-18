<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>包团定制</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/private_tour_index.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/jQuery.hhShare.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/slider.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/slider_2.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/privateMy97DatePicker/WdatePicker.js"></script>
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
   <#include "/front/include/privateTop.ftl"/>
    
    <div class="width100 left" id="pt_Banner">
      <div id="banner_tabs" class="private_tour_flexslider">
            <ul class="private_tour_slides">
                <li>
                    
                        <img width="100%" height="581" alt="" style="background: url(${ctx!}/assets-web/images/private_tour_new/private_tour_banner.jpg) no-repeat center;" src="${ctx!}/assets-web/images/alpha.png">
                   
                </li>
            </ul>
            <form method="GET">
                <div class="private_tour_searchbox width100">
                    <div class="widthCenter">
                        <div class="private_tour_search_forms">
                            <div class="private_tour_search_bar_wrap">
                            	<input name="q" class="private_tour_search_input " type="text" tabindex="2" placeholder="城市" autocomplete="off">
                            </div>
                            <div class="private_tour_search_bar_wrap">
                            	<input class="Wdate" onClick="WdatePicker();" placeholder="日期">
                            </div>
                            <div class="private_tour_search_bar_wrap">
                            	<input class="private_tour_btn" type="submit" onmousedown="ta.store('ta.pages.home.find.clicked', true);" value="搜索">
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="clear"></div>
    <div class="our_advance">
        <ul class="widthCenter">
            <li>
            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon3.png">
            </li>
            <li>
            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon4.png">
            </li>
            <li>
            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon5.png">
            </li>
            <li>
            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon6.png">
            </li>
            <div class="clear"></div>
        </ul>
    </div>
    <div class="clear"></div>
    <div class="width100" style="background-color:#fff; position:relative;" id="pt_Con">
    	<div class="widthCenter">
        	<div class="private_tour_box">
            	<div class="private_tour_tit">
                	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_tit1.png">
                </div>
                <div class="private_tour_main">
                	<ul class="private_tour_ul">
                    	<li style="margin-right:22px;">
                        	<a href="${ctx!}/private-tour/special.htm" class="private_tour_thumb">
                            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_pic1.jpg">
                                <span class="private_tour_caption">个性体验</span>
                            </a>
                        </li>
                        <li>
                        	<a href="" class="private_tour_thumb_2">
                            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_pic2.jpg">
                                <span class="private_tour_caption">商务定制</span>
                            </a>
                        </li>
                        <div class="clear"></div>
                    </ul>
                    <ul  class="private_tour_ul" style="margin-top:22px;">
                    	<li style="margin-right:22px;">
                        	<a href="${ctx!}/private-tour/sanfrancisco-bus.htm" class="private_tour_thumb_3">
                            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_pic3.jpg">
                                <span class="private_tour_caption">包车服务</span>
                            </a>
                        </li>
                        <li style="margin-right:22px;">
                        	<a href="" class="private_tour_thumb_3">
                            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_pic4.jpg">
                                <span class="private_tour_caption">游学组团</span>
                            </a>
                        </li>
                        <li>
                        	<a href="" class="private_tour_thumb_3">
                            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_pic5.jpg">
                                <span class="private_tour_caption">特惠门票</span>
                            </a>
                        </li>
                        <div class="clear"></div>
                    </ul>
                    <div class="clear"></div>
                </div>
            
            </div>
            <div class="private_tour_box">
            	<div class="private_tour_tit">
                	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_tit2.png">
                </div>
                <div class="private_tour_main" >
                	<div id="demo01" class="flexslider">
                        <ul class="slides">
                            <li><div class="img"><img src="${ctx!}/assets-web/images/private_tour_new/SanFrancisco.jpg" height="522" width="1100" alt="" /></div></li>
                            <li><div class="img"><img src="${ctx!}/assets-web/images/private_tour_new/LosAngeles.jpg" height="522" width="1100" alt="" /></div></li>
                            <li><div class="img"><img src="${ctx!}/assets-web/images/private_tour_new/Seattle.jpg" height="522" width="1100" alt="" /></div></li>
                           
                        </ul>
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="private_tour_box">
            	<div class="private_tour_tit">
                	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_tit3.png">
                </div>
                <div class="private_tour_main">
                    <ul class="private_tour_people">
                     
                    	<li style="margin-right:25px;">
                        	<div class="private_tour_p_pic">
                            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_pic6.png" >
                            </div>
                            <div class="private_tour_p_main">
                            	<p><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon_left.png"></p>
                                <span class="p_main">我旅居美国近10年，我能知晓赴美旅游您的需求，什么才是中国式的私人订制游。让身为行程设计师的我来陪您聊聊您的假期，您的旅程！</span>
                                <p align="right"><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon_right.png"></p>
                            </div>
                            <p class="pt_designer_slogan"><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon7.png"></p>
                            <p class="pt_designer_name">行程设计师 Gigi</p>
                        </li>
                        <li style="margin-right:25px;">
                        	<div class="private_tour_p_pic">
                            	<img src="${ctx!}/assets-web/images/private_tour_new/FullSizeRender.png" >
                            </div>
                            <div class="private_tour_p_main">
                            	<p><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon_left.png"></p>
                                <span class="p_main"> 不仅仅是专业行程设计师，更从您的角度着手，悉心了解您的需要，像设计自己行程一样为您打造您独特的旅程。</span>
                                <p align="right"><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon_right.png"></p>
                            </div>
                            <p class="pt_designer_slogan"><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon7.png"></p>
                            <p class="pt_designer_name">行程设计师 Jessica </p>
                        </li>
                        <#--
                        <li style="margin-right:25px;">
                        	<div class="private_tour_p_pic">
                            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_pic7.png">
                            </div>
                            <div class="private_tour_p_main">
                            	<p><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon_left.png"></p>
                                <span class="p_main">长期居住于北美，足迹踏遍全球。好的旅行应该是有温度的。温度，来自不一样的风景，来自陪伴或遇见的人。当然，也来自我们为您悉心定制的每一个细节中。</span>
                                <p align="right"><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon_right.png"></p>
                            </div>
                            <p class="pt_designer_slogan"><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon7.png"></p>
                            <p class="pt_designer_name">行程设计师 Faith</p>
                        </li>
                        <li>
                        	<div class="private_tour_p_pic">
                            	<img src="${ctx!}/assets-web/images/private_tour_new/private_tour_pic6.png">
                            </div>
                            <div class="private_tour_p_main">
                            	<p><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon_left.png"></p>
                                <span class="p_main">长期居住于北美，足迹踏遍全球。好的旅行应该是有温度的。温度，来自不一样的风景，来自陪伴或遇见的人。当然，也来自我们为您悉心定制的每一个细节中。</span>
                                <p align="right"><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon_right.png"></p>
                            </div>
                            <p class="pt_designer_slogan"><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_icon7.png"></p>
                            <p class="pt_designer_name">行程设计师 Faith</p>
                        </li>
                        <div class="clear"></div>
                        -->
                    </ul>
                    <div class="clear"></div>
                </div>
            
            </div>
        
        </div>
        <div class="private_tour_consultbox" style="display:none;">
		         <div class="private_tour_consult">
                	<div class="pt_name">Gigi</div>
                    <div class="pt_img"><img src="${ctx!}/assets-web/images/private_tour_new/private_tour_pic6.png" style="width:100px; height:100px;"></div>
                    <div class="pt_message">
                    	<p>Tel: 626-478-3519</p>
                        <p>QQ: 2790820042</p>
                        <p>Email: Gigi.w@wenjing.com</p>
                    </div>
                </div>
                <div class="private_tour_consult">
                	<div class="pt_name">Jessica</div>
                    <div class="pt_img"><img src="${ctx!}/assets-web/images/private_tour_new/FullSizeRender.png" style="width:100px; height:100px;"></div>
                    <div class="pt_message">
                    	<p>Tel: 626-478-3519</p>
                        <p>QQ: 1467301533</p>
                        <p>Email: Jessica.z@wenjing.com</p>
                    </div>
                </div>
                
        </div>
    </div>
    <div class="clear"></div>
    <#include "/front/include/bottom.ftl"/>
    
</body>
<script>
			
	$(function(){
		$(this).find(".private_tour_nav_left_pos").hide();
		//$(this).find(".nav_left_pos").stop(false, false).slideUp(300);
	
		//nav下拉
	    $(".private_tour_nav_left").mouseenter(function () {
	        $(this).find(".private_tour_nav_left_pos").stop(false, false).slideDown(0);
	    });
	    $(".private_tour_nav_left").mouseleave(function () {
	        $(this).find(".private_tour_nav_left_pos").stop(false, false).slideUp(0);
	    });
	    
	    //nav更多下拉
	    $(".private_tour_nav_left_pos li").mouseenter(function () {
	        $(this).find(".private_tour_nav_left_more").stop(false, false).fadeIn(0);
	    });
	    $(".private_tour_nav_left_pos li").mouseleave(function () {
	        $(this).find(".private_tour_nav_left_more").stop(false, false).fadeOut(0);
	    });
    });

	
	$(function () {
		var navLi=$(".private_tour_nav .private_tour_nav_ul li");
		navLi.mouseover(function () {
			//alert($(this).offset().left);
			//$('.sub_in').css('margin-left',$(this).offset().left);
			$(this).find("a.private_tour_nav_ul_bg").addClass("private_tour_nav_ul_bg_2");
			$(this).find(".private_tour_sub_in").stop().slideDown(0);
		})
		navLi.mouseleave(function(){
			$(this).find("a.private_tour_nav_ul_bg").removeClass("private_tour_nav_ul_bg_2");
			$(this).find(".private_tour_sub_in").stop().slideUp(0);
		})
	})
	
	//币种切换
	
	$(".choose_gq").click(function(){
    	var div = $(".brand_op");
    	if ( div.css("display") === "none" ) {
        	div.show();
    	}
	})

    //切换
    var on = 0;
    $(".F_right_po_jt_left").click(function () {
        var posDiv = $(this).parent().parent().find(".F_right_po");
        var Len = posDiv.find(".F_one").length;
        if (on == 0) {
            var lastLeft = -(Len - 1) * 810;
            posDiv.stop(false, false).animate({
                left: lastLeft
            }, 500);
            on = Len - 1;
        } else {
            on--;
            var changeLeft = -on * 810;
            posDiv.stop(false, false).animate({
                left: changeLeft
            }, 500);
        }
    });
    $(".F_right_po_jt_right").click(function () {
        var posDiv = $(this).parent().parent().find(".F_right_po");
        var Len = posDiv.find(".F_one").length;
        if (on == Len - 1) {
            posDiv.stop(false, false).animate({
                left: "0px"
            }, 500);
            on = 0;
        } else {
            on++;
            var changeLeft = -on * 810;
            posDiv.stop(false, false).animate({
                left: changeLeft
            }, 500);
        }
    });
    
    $(".F_right_title .left span").click(function () {
        var Index = $(this).index();
        $(this).parent().find("span").removeClass("F_right_span_active");
        $(this).addClass("F_right_span_active");
        $(this).parents(".F_right").find(".F_right_main").hide();
        $(this).parents(".F_right").find(".F_right_main:eq(" + Index + ")").show();
    });
	<#--
	$(function(){
	//右侧浮动特效
	$('#asid_share').hhShare({
		cenBox     : 'asid_share_box',  //里边的小层
		icon       : 'adid_icon',
		addClass   : 'red_bag',
		titleClass : 'asid_title',
		triangle   : 'asid_share_triangle', //鼠标划过显示图层，边上的小三角
		showBox    : 'asid_sha_layer' //鼠标划过显示图层
	});
	});
	-->
	//个性定制滚动图
	$(function(){

		$('#demo01').flexslider({
			animation: "slide",
			direction:"horizontal",
			easing:"swing"
		});
		
	});
	//获取要定位元素距离浏览器顶部的距离
		var headerH=$("#pt_header").height();
		var bannerH=$("#pt_Banner").height();
		var navH=headerH+bannerH+85;   //计算页面划到导航时 悬浮
        
        //计算left值
        var zhi = (($(window).width() - 1100)/2)+1110;
        
        //滚动条事件
        $(window).scroll(function(){
			
            //获取滚动条的滑动距离
            var scroH = $(this).scrollTop();
            //滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
            if (scroH >= navH) {
				$(".private_tour_consultbox").show(300);
                $(".private_tour_consultbox").css({
                    "position": "fixed",
                    "top": 0,
                    "left":zhi
                });
            } else if (scroH < navH) {
				$(".private_tour_consultbox").hide();
                $(".private_tour_consultbox").css({
                    "position": "static"
                });
            }
        });
</script>

</html>