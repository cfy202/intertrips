<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>德美古拉搭乘热气球观日出品酒餐美味</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/private_tour_index.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/jQuery.hhShare.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/slider_2.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/privateMy97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
</head>

<body>
<#include "/front/include/privateTop.ftl"/>
	
    <div class="width100" id="pt_Banner">
      <div class="commendation_banner">
      	<div class="commendation_tit">
        	<h2>德美古拉搭乘热气球观日出品酒餐美味</h2>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="width100" style="background-color:#fff; position:relative;" id="pt_Con">
    	<div class="widthCenter">
        	<div class="commendation_main">
            	<div class="commendation_main_top">
                	<div class="commendation_img left">
                    	<img src="${ctx!}/assets-web/images/private_tour_new/commendation_1.jpg">
                    </div>
                    <div class="commendation_info left">
                    	<p>目  的 地：<em>德美古拉</em></p>
                        <p>行程天数：<em>1天</em></p>
                        <p><img src="${ctx!}/assets-web/images/private_tour_new/commendation_icon.png"></p>
                        <p>黎明的第一缕阳光为您绽放 热气球带您窥探地面的生灵</p>
                        <p style="text-align:right;"><img src="${ctx!}/assets-web/images/private_tour_new/commendation_icon_r.png"></p>
                    </div>
                	<div class="clear"></div>
                </div>
                <div class="commendation_main_down">
                	<div class="commendation_hightlight">
                    	<h2>行程简介</h2>
                        <p style="text-indent:2em;">酒庄的美景可以置身其中,也可以搭乘热气球(Hot air balloon)高空俯瞰。从天空观赏野生动物，柳橙果园，满山坡的葡萄架，并品尝香槟和果汁。 搭乘热气球最热门的时间是日出和夕阳,若要体验欣赏日出可要有早起的准备,通常需要凌晨5点半到6点半就出发了。 搭乘热气球切勿心急,放飞热气球会受到地势和风向很大的限制,因此驾驶员在准备放热气球之前,要花许久时间找寻最佳起飞地点,地点选定之后,升起热气球也是另一大挑战,不过对于没有乘坐过热汽球的旅客,看热气球如何被升起也是一个很难得的经验。 热气球升空之后就跟在陆地上一样平稳,但是高度会比想象中高一些,唯有在落地的时候会比较颠颇。 热气球飞行时间约为一个小时,落地之后驾驶员还会请每一位乘客喝香槟和颁发一张证书,算是给大家的勇气一种奖励。</p>
                    </div>
                
                </div>
            </div>
        	<div class="clear"></div>
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

	//导航下拉标签效果
	$(function () {
		var navLi=$(".private_tour_nav .private_tour_nav_ul li");
		navLi.mouseover(function () {
		//alert($(this).offset().left);
		$('.private_tour_sub_in').css('margin-left',$(this).offset().left);
		$(this).find("a.private_tour_nav_ul_bg").addClass("private_tour_nav_ul_bg_2");
		$(this).find(".private_tour_sub_nav_new").stop().slideDown(0);
		})
		navLi.mouseleave(function(){
		$(this).find("a.private_tour_nav_ul_bg").removeClass("private_tour_nav_ul_bg_2");
		$(this).find(".private_tour_sub_nav_new").stop().slideUp(0);
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
		var navH=headerH+bannerH;   //计算页面划到导航时 悬浮
        
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