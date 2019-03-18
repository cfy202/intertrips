<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>签证</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/style.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script src="${ctx!}/assets-web/js/jquery.slides.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery.kxbdmarquee.js"></script>
   <script type="text/javascript">
	$(function(){
	  			
		 $.ajax({
			type: "POST",
			url: "${ctx!}/front/visaindex/visaOrders.do",
			cache:false,
			success: function(data) {
			   var html = "<ul>";
			   $.each(data, function(i, orders) {
			   if(orders['userid']!='null'&&orders['receiveway']!='null'&&orders['remark']!='null'){
			   html+="<li>"+
		         		"<span class=\"res_user\">"+orders['userid']+" 在"+orders['receiveway']+"前预订了</span>"+
		          		"<a class=\"res_info a4\" href=\"#\" title=\"\" target=\"_blank\">"+orders['remark']+"</a>"+  
		         	 "</li>";
			   }
			});
			  $("#marquee4").html(html+"</ul>");
			  $("#marquee4").kxbdMarquee({direction:"up",isEqual:false});
			},
			error: function(e) {
				alert(e);
			},
		});
	});

	
</script>
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
    <div class="widthCenter place">当前位置：<a href="${ctx!}/" class="a1">首页</a> &gt; 签证</div>
    <div class=" widthCenter line_filter shadow">
    	<div class="tour_line">
            <div class="visa_left">
                <ul>
                        <#assign idd = "">
						<#assign nn = 0>
						<#assign i = 0>
						<#assign j = 0>
						<#list regionvisa as regionvisa >
						<#if 'root'==regionvisa.upid >	                        	
	                        	<#if idd!="" || j!=0 >
	                        	</div>
                                <div class="clear"></div>
                                </li>
                                </#if>
	                        <li>
	                        	<#assign nn = nn+1>
	                        	<div class="visa_icon">
                        	     <i class="visa_icon_${nn}"></i>
                                </div>
                                <div class="visa_continent">${regionvisa.name}</div>
	                                 <div class="visa_country">
                                 <#assign j = j + 1> 
                                 </#if>
                                 <#if 'root'!=regionvisa.upid>
	                                <#assign i = i + 1 >
			                        	<a href="${ctx!}${regionvisa.url}" class="a1">${regionvisa.name}</a>
			                         <#assign idd = regionvisa.id >
	                              </#if>
						</#list>
					</div>	
                   <div class="clear"></div>
                 </li>    
                </ul>
            </div>
        	<div class="visa_right">
            	<div class="visa_gg" id="visa_gg">
                    <div class="visa_ggs">
                        <div class="visa_ggBox" id="visa_ggBox">
                           <#list slider as slider >
                             <#if slider_index=0>
                                <a href="${ctx!}${slider.link}"  title="" style="z-index: 3; opacity: 4;">
                                <img src="${ctx!}${slider.url}" alt="" /></a>
                              <#else>
                                <a href="${ctx!}${slider.link}"  title="" >
                                <img src="${ctx!}${slider.url}" alt="" /></a>
                              </#if>
                           </#list>
                        
                        </div>
                    </div>
                    <div class="visa_b">
                        <div class="visa_Btns" id="visa_Btns">
                            <#list slider as slider>
                             <#if slider_index=0>
                             <a title="${(slider.description)!}" href='javascript:void(0)'><em>${(slider.description)!}</em></a>
                             <#else>
                             <a title="${(slider.description)!}" href='javascript:void(0)' style="border-right:none;"><em>${(slider.description)!}</em></a>
                             </#if>
                            </#list>
                            
                        </div>
                    </div>
        		</div>            
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="clear"></div>
    <div class="widthCenter line_filter top1">
        <div class="visa_left_frame shadow">
        	<div class="visa_pic">
            	<img src="${ctx!}/assets-web/images/visa.png" width="112" height="115">
            </div>
            <div class="visa_data">
            	<p class="visa_data_1">出签成功率：</p>
                <p class="visa_data_2 colorOrange">99.8%</p>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
            <div class="visa_strengths">
            	<h4>文景假期优势</h4>
                <div class="visa_line"></div>
                <div class="visa_strengths_list">
                	<ul>
                    	<li>签证专家一对一服务</li>
                        <li>签证专家一对一服务</li>
                        <li>签证专家一对一服务</li>
                        <li>签证专家一对一服务</li>
                    </ul>
                </div>
            </div>
            <div class="visa_strengths">
            	<h4>最新预订</h4>
                <div class="visa_line"></div>
                <div class="visa_details" id="marquee4">
                	
                </div>
            </div>
        </div>
        <div class="visa_right_frame">
        	<div class="visa_rm shadow">
            	<div class="visa_rm_tit">
                	<span class="visa_rm_tit_1">热门签证</span>
                    <!--<a href="" class="visa_rm_tit_a">更多签证 &gt;</a>-->
                    <div class="clear"></div>
                </div>
                
                <ul class="visa_rmbox">
                   <#list productlist as productlist >
                     <#assign stylestr = "" >
                     <#if productlist_index !=0 &&productlist_index %4=0>
                     <#assign stylestr = " style=\"margin-right:0;\"" >
                     </#if>
                      <li>
                    	<a href="${ctx!}${(productlist.pagePageid.filepath)!}"  ${stylestr}>
							<span class="visa_cname">${productlist.name}</span>
							<span class="visa_ename"><em class="left_hyphen"></em>${productlist.minprice}<em class="left_hyphen">起</em></span>
							 <#if productlist.imageurl?has_content>
							  <span class="visa_img"><img src="${ctx!}${(productlist.imageurl)?substring(0,(productlist.imageurl)?index_of('.'))}-s.png" width="75" height="50"></span>
							 </#if>
						</a>
					</li>                     
                   </#list>
                  	
                </ul>
                <div class="clear"></div>
            </div>
        
        </div>
        <div class="visa_right_frame visa_top">
        	<div class="visa_rm shadow">
            	<div class="visa_rm_tit">
                	<span class="visa_rm_tit_1">签证办理流程</span>
                    <div class="clear"></div>
                </div>
                <img class="visa_liucheng" src="${ctx!}/assets-web/images/visa_liucheng.png" width="694" height="65">
                <ul class="visa_liucheng_main">
                	<li class="visa_liucheng_1">签证咨询服务</li>
                    <li class="visa_liucheng_2">支付签证费用</li>
                    <li class="visa_liucheng_3">填写DS160表格</li>
                    <li class="visa_liucheng_4">预约面谈</li>
                    <li class="visa_liucheng_5">核审材料</li>
                    <li class="visa_liucheng_6">面谈前培训</li>
                    <li class="visa_liucheng_7">领取签证</li>
                </ul>
                <div class="clear"></div>
            </div>
        
        </div>
        <div class="visa_right_frame visa_top">
        	<div class="visa_rm shadow">
            	<div class="visa_rm_tit visa_rm_tit1">
                	<span class="visa_rm_tit_1 visa_rm_tit_active"><b>签证必读</b></span>
                    <span class="visa_rm_tit_1 visa_rm_tit_2"><b>常见问题</b></span>	
                    <a href="#" class="visa_rm_tit1_a">更多></a>
                    <div class="clear"></div>
                </div>
                <div class="quesation_mian">
                	<ul>
                    	<li>
                        	<span>></span>
                            <a href="#" title="" name="">美国面签</a>
                        </li>
                        <li>
                        	<span>></span>
                            <a href="#" title="" name="">美国签证资料提交</a>
                        </li>
                        <li>
                        	<span>></span>
                            <a href="#" title="" name="">日本签证注意事项</a>
                        </li>
                        <li>
                        	<span>></span>
                            <a href="#" title="" name="">日本签证注意事项</a>
                        </li>
                        
                    </ul>
                    <!--<ul>
                    	<li>
                        	<span>></span>
                            <a href="#" title="" name="">2121212121221</a>
                        </li>
                        <li>
                        	<span>></span>
                            <a href="#" title="" name="">2121212121221</a>
                        </li>
                        <li>
                        	<span>></span>
                            <a href="#" title="" name="">2121212121221</a>
                        </li>
                        <li>
                        	<span>></span>
                            <a href="#" title="" name="">2121212121221</a>
                        </li>
                        
                    </ul>-->
                </div>
                <div class="quesation_mian" style="display:none;">
                	<ul>
                    	<li>
                        	<span>></span>
                            <a href="#" title="" name="">日本签证资料提交</a>
                        </li>
                        <li>
                        	<span>></span>
                            <a href="#" title="" name="">美国面签</a>
                        </li>
                        <li>
                        	<span>></span>
                            <a href="#" title="" name="">美国签证资料提交</a>
                        </li>
                        <li>
                        	<span>></span>
                            <a href="#" title="" name="">日本签证注意事项</a>
                        </li>
                        
                    </ul>
                <!--   <ul>
                    	<li>
                        	<span>></span>
                            <a href="#" title="" name="">2121212121221</a>
                        </li>
                    </ul> --> 
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="clear"></div>
    <#include "/front/include/bottom.ftl"/>
</body>
<script>
    $(function () {
        $(this).find(".nav_left_pos").hide();
        //$(this).find(".nav_left_pos").stop(false, false).slideUp(300);

        //nav下拉
        $(".nav_left").mouseenter(function () {
            $(this).find(".nav_left_pos").stop(false, false).slideDown(300);
        });
        $(".nav_left").mouseleave(function () {
            $(this).find(".nav_left_pos").stop(false, false).slideUp(300);
        });
        //nav更多下拉
        $(".nav_left_pos li").mouseenter(function () {
            $(this).find(".nav_left_more").stop(false, false).fadeIn(300);
        });
        $(".nav_left_pos li").mouseleave(function () {
            $(this).find(".nav_left_more").stop(false, false).fadeOut(300);
        });

    });
	
	$(function () {
    var $con = $('#visa_gg'), $box = $con.find('#visa_ggBox'), $btns = $con.find('#visa_Btns'), i = 0, autoChange = function () {
        i += 1;
        if (i === 4) { i = 0; }
        $btns.find('a:eq(' + i + ')').addClass('visa_On').siblings().removeClass('visa_On');
        var curr = $box.find('a:eq(' + i + ')'), prev = curr.siblings();
        prev.css('z-index', 2);
        curr.css('z-index', 3).animate({
            'opacity': 1
        }, 150, function () {
            prev.css({
                'z-index': 1, 'opacity': 0.1
            });
        });
    }, loop = setInterval(autoChange, 2000);
    $con.hover(function () {
        clearInterval(loop);
    }, function () {
        loop = setInterval(autoChange, 2000);
    });
    $btns.find('a').click(function () {
        i = $(this).index() - 1;
        autoChange();
    });
	
	//问题切换
	$(".visa_rm_tit1 span").click(function(){
		var Index = $(this).index();
		$(".visa_rm_tit1 span").removeClass("visa_rm_tit_active");
		$(this).addClass("visa_rm_tit_active");
		$(".quesation_mian").hide();
		$(".quesation_mian:eq("+Index+")").show();
	});
});

</script>

</html>