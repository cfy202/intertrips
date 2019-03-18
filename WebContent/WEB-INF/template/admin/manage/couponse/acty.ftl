<!doctype html>
<html xmlns="https://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<#assign ctx = request.contextPath />
<title>${(couponse.name)!}</title>
<link href="${ctx!}/jindan/christmas.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx!}/jindan/jquery.js"></script>
<script type="text/javascript" src="${ctx!}/jindan/lottery.js"></script>
<script type="text/javascript" src="${ctx!}/jindan/jfun.js"></script>
<script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx!}/assets-web/js/jquery.kxbdmarquee.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	auto();
	
	function auto() {
		$.ajax({
			   type: "get",
			   url: "${ctx!}/front/action/couponList.do",
	           success: function(msg){
				   var html ="<ul>";
						$.each(msg, function (index, entry){
							html+="<li><span class=\"couponsname\">"+entry['couponsname']+"</span><span>"+entry['username']+"</span></li>";
						});
				  html+="</ul>";
				 
				  $("#marquee4").html(html);
				  $("#marquee4").kxbdMarquee({direction:"up",isEqual:false}); 
				 
			   }
			   
			}); 
	}	
	
	
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
<div id="top">
  
</div>
<div class="box" id="award_1"> <a class="close" hidefocus="true" href="javascript:;" onclick="effect.zoomOut('award_1',1);"> × </a>
  <div class="pic" id="pic"><img src="${ctx!}/jindan/award/panda_coupon.png"/></div>
  <div class="mc_header">恭喜您！	</div>
  <div class="title" id="won"></div>
  <div class="description">
    <form>
      <p>请填写您的联系方式！</p>
      <p>
        <label>姓名:</label>
        <select style="width:60px;" name="title" id="title">
          <option value="Mr.">Mr.</option>
          <option value="Mrs.">Mrs.</option>
          <option value="Miss.">Miss.</option>
        </select>
        <input style="width:149px;" type="text" name="fulltname" id="name"  />
        <span>*</span> </p>
      <p>
        <label>邮箱地址:</label>
        <input type="text" name="email" id="email" style=" width:213px;"/>
        <span>*</span></p>
      <p>
        <input type="hidden" name="duijiangId" id="duijiangId" value=""/>
        <input type="hidden" name="couponseId" id="couponseId" value="${(couponse.id)!}"/>
        <input class="des_submit" type="button" id="btn_submit" name="submit" value="提交" onclick="sendMail()" />
      </p>
    </form>
  </div>
</div>
<div id="container">
  <div class="banner">
    <div class="banner_top"> <img src="${ctx!}${(couponse.pic)!}" width="396" height="361"> </div>
    <div class="banner_lead">
      ${(couponse.infor)!}
    </div>
    <div class="banner_egg"> 
      <!-- -->
      <object width="909" height="319" align="left" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="https://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" id="lottery">
          <param value="always" name="allowScriptAccess">
          <param value="${ctx!}/jindan/egg.swf" name="movie">
          <param value="high" name="quality">
          <param value="transparent" name="wmode">
          <param name="wmode" value="opaque">
          <param value="false" name="menu">
          <embed width="909" height="319" align="left" pluginspage="https://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" allowscriptaccess="always" wmode="transparent" name="lottery" menu="false" quality="high" src="${ctx!}/jindan/egg.swf">
        </object>
    </div>
  </div>
  <div class="content">
    <div class="mc_left">
      <div class="mc_left_title"><img src="${ctx!}/jindan/award/mc_header.png"></div>
      <ul>
        <#list activityrules as activityrules >
          <li>${(activityrules.content)!}</li>
        </#list>
        
      </ul>
    </div>
    <div class="list" id="marquee4"></div>
  </div>
</div>
</body>
</html>
