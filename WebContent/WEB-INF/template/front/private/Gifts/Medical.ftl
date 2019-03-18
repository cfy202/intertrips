<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>医疗& 体检套餐</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/private_tour_index.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
</head>

<body>
	<#include "/front/include/privateTop.ftl"/>
    <div class="width100" id="pt_Banner">
      <div class="commendation_banner">
      	<div class="commendation_tit">
        	<h2>医疗 & 体检套餐</h2>
        </div>
      </div>
      <div class="clear"></div>
    </div>
    <div class="width100" style="background-color:#fff; position:relative;" id="pt_Con">
    	<div class="widthCenter">
        	<div class="commendation_main">
            	<div class="commendation_main_top">
                	<div class="commendation_img left">
                    	<img src="${ctx!}/assets-web/images/private_tour_new/commendation_8.jpg">
                    </div>
                    <div class="commendation_info left">
                    	<p>目  的 地：<em>圣迭戈</em></p>
                        <p>行程天数：<em>1天</em></p>
                        <p><img src="${ctx!}/assets-web/images/private_tour_new/commendation_icon.png"></p>
                        <p>世界一流设备先进医疗科技，让您预知了解您自己的BODY!</p>
                        <p style="text-align:right;"><img src="${ctx!}/assets-web/images/private_tour_new/commendation_icon_r.png"></p>
                    </div>
                	<div class="clear"></div>
                </div>
                <div class="commendation_main_down">
                	<div class="commendation_hightlight">
                    	<h2>行程简介</h2>
                        <p style="text-indent:2em;">我们将带您前往座落于美国美丽海滨城市圣迭戈的美国斯克利普斯研究所（The Scripps Research Institute，TSRI)下属Scripps医疗检测中心进行身体检查。该研究所一直是位居美国前10名的生物医学研究所。研究所在专业领域盛名在外，阵容十分强大，拥有三位诺贝尔奖级的人物神经学家Gerald Edelman博士，发明核磁共振的生物物理学家Kurt Wuthrich博士和2001年的化学奖获得者Barry Sharpless博士。研究所还拥有19名美国科学院士，16名医学院士和16名科学艺术院士。斯克利普斯研究所仅为新药开发从各个渠道接受多达8000万美元的研究经费。</p>
                        <p style="text-indent:2em;">值得一提的是，该医疗中心进行专人个性化体检已长达34年，不仅致力于治疗，更是发现预防疾病，提倡一种健康生活的态度。</p>
                        <p style="text-indent:2em;"><b>体检内容包括：</b></p>
                        <p style="text-indent:2em;">医学专家与您一对一了解您的个人及家庭健康史，对您的健康问题提出专业建议</p>
                        <p style="text-indent:2em;">基础身体检查： 身高，体重，血压，脉搏和肺活量检测等；</p>
                        <p style="text-indent:2em;">心电图检查（ 评估心脏功能及有无心律失常）；</p>
                        <p style="text-indent:2em;">非影像心脏负荷检查（评估是否有心脏缺血，是否有冠心病的可能）；</p>
                        <p style="text-indent:2em;"><b>实验室检查：</b></p>
                        <p style="text-indent:2em;">全血细胞分析；</p>
                        <p style="text-indent:2em;">尿液分析；</p>
                        <p style="text-indent:2em;">综合新陈代谢分析；</p>
                        <p style="text-indent:2em;">血脂，血糖，肝功能和肾功能检查；</p>
                        <p style="text-indent:2em;">维生素D的检查分析；</p>
                        <p style="text-indent:2em;">女性宫颈刮片检查；</p>
                        <p style="text-indent:2em;">男性PSA前列腺检测早期检测前列腺癌；</p>
                        <p style="text-indent:2em;">身体成分酸碱性体质分析；</p>
                        <p style="text-indent:2em;">体检报告将由医生亲自解读，以电话或电子邮件的方式来告知客人；</p>
                        <p style="text-indent:2em;">个性化的检查报告包括：检查结果、健康状态评估以及医疗建议等；</p>
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