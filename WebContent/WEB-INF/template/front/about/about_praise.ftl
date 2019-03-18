<!doctype html>
<html>
<head>
<meta charset="utf-8">
<#assign ctx = request.contextPath />
<title>品牌口碑</title>
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
</head><body>
<#include "/front/include/top.ftl"/>
<div class="clear"></div>
<div class="widthCenter place">当前位置：<a href="${ctx!}/" class="a1">首页</a> &gt; 品牌口碑</div>
<div class="widthCenter line_filter">
  <div class="us_aside  shadow">
    <ul class="box_list">
      <li class="about_us "> <em class="aside_icon ico_us"></em> <a href="${ctx!}/about-us.htm " class="aside_btn">关于我们</a> </li>
      <li class="praise current"> <em class="aside_icon ico_brand"></em> <a href="${ctx!}/praise.htm " class="aside_btn">品牌口碑</a> </li>
      <li class="team"> <em class="aside_icon ico_team"></em> <a href="${ctx!}/team.htm " class="aside_btn">专业团队</a> </li>
      <li class="contact"> <em class="aside_icon ico_contact"></em> <a href="${ctx!}/contact-us.htm " class="aside_btn">联系我们</a> </li>
      <li class="contact"> <em class="aside_icon ico_join"></em> <a href="${ctx!}/join-us.htm " class="aside_btn">加入我们</a> </li>
    </ul>
  </div>
  <div class="us_content shadow">
    <div class="us_main">
      <div class="us_main_box">
        <!--<h4>文景假期旗下品牌</h4>-->
        <div class="us_praise">
          <p><img src="${ctx!}/assets-web/images/certificate/pinpai.jpg" alt=""></p>
          <!--
          <p>15年专注服务海外华人</p>
          <p>50万次选择文静假期出游</p>
          <p>覆盖美欧亚三大洲的线下零售服务网点</p>
          <p>上千条优质跟团游线路</p>
          <p>国粤英西5语客户服务</p>
          -->
          <!--<div class="praise_logo"> <img src="${ctx!}/assets-web/images/logo_inter.jpg" width="130" height="37">
            <p>Intertrips美国文景假期一贯本着“旅客至上, 诚信为本, 精益求精, 开拓创新”的经营理念，凭借强大的运营体系和丰富的专业知识经验，为每位客户提供包括项目方案设计、境外日程安排、签证咨询、境外接待、商务咨询、对口商务洽谈及教育文化交流等全方位综合服务。</p>
            <div class="clear"></div>
          </div>
          <div class="praise_logo"> <img src="${ctx!}/assets-web/images/logo_chinatour.jpg" width="130" height="59">
            <p>Intertrips美国文景假期一贯本着“旅客至上, 诚信为本, 精益求精, 开拓创新”的经营理念，凭借强大的运营体系和丰富的专业知识经验，为每位客户提供包括项目方案设计、境外日程安排、签证咨询、境外接待、商务咨询、对口商务洽谈及教育文化交流等全方位综合服务。</p>
            <div class="clear"></div>
          </div>
          <div class="praise_logo"> <img src="${ctx!}/assets-web/images/logo_wenjing.jpg" width="130" height="53">
            <p>Intertrips美国文景假期一贯本着“旅客至上, 诚信为本, 精益求精, 开拓创新”的经营理念，凭借强大的运营体系和丰富的专业知识经验，为每位客户提供包括项目方案设计、境外日程安排、签证咨询、境外接待、商务咨询、对口商务洽谈及教育文化交流等全方位综合服务。</p>
            <div class="clear"></div>
          </div>
          <div class="praise_logo"> <img src="${ctx!}/assets-web/images/logo_echinatours.jpg" width="130" height="59">
            <p>Intertrips美国文景假期一贯本着“旅客至上, 诚信为本, 精益求精, 开拓创新”的经营理念，凭借强大的运营体系和丰富的专业知识经验，为每位客户提供包括项目方案设计、境外日程安排、签证咨询、境外接待、商务咨询、对口商务洽谈及教育文化交流等全方位综合服务。</p>
            <div class="clear"></div>
          </div>
          -->
        </div>
      </div>
      <div class="us_main_time">
        <h4>获得证书</h4>
        <div class="certificate">
          <!--<div class="certificate_left"> <img src="${ctx!}/assets-web/images/certificate_1.jpg" width="256" height="173"> <img src="${ctx!}/assets-web/images/certificate_2.jpg" width="256" height="174"> <img src="${ctx!}/assets-web/images/certificate_3.jpg" width="256" height="174"> </div>
          <div class="certificate_right"> <img src="${ctx!}/assets-web/images/certificate_4.jpg" width="308" height="535"> </div>
          <div class="clear"></div>-->
          <p><img src="${ctx!}/assets-web/images/certificate/01.jpg" alt=""></p>
          <p><img src="${ctx!}/assets-web/images/certificate/02.jpg" alt=""></p>
          <p><img src="${ctx!}/assets-web/images/certificate/03.jpg" alt=""></p>
          <p><img src="${ctx!}/assets-web/images/certificate/04.jpg" alt=""></p>
          <p><img src="${ctx!}/assets-web/images/certificate/05.jpg" alt=""></p>
        </div>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<#include "/front/include/bottom.ftl"/>
</body>
</html>
<script type="text/javascript">
$(function(){
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
</script>