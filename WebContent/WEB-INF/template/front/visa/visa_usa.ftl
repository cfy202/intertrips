<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>${(visa.productProductid.name)!}</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/style.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/common.js"></script>
    <script type="text/javascript" src="${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctx!}/assets-web/downloadr/downloadr.css" media="screen" rel="stylesheet" type="text/css"/>
	<script src="${ctx!}/assets-web/downloadr/jqbrowser.js" type="text/javascript"></script>
	<script src="${ctx!}/assets-web/downloadr/downloadr.js" type="text/javascript"></script>
  <script type="text/javascript">
			$(document).ready(function() {

			  $('a[rel*=downloadr]').downloadr();
			  
			})
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
    <div class="widthCenter place">当前位置：<a href="${ctx!}/" class="a1">首页</a> &gt; <a href="${ctx!}/visa.htm" class="a1">签证</a>&gt;${(visa.productProductid.name)!}</div>
    <div class=" widthCenter line_filter shadow">
    	<div class="tour_line">
            <div class="line_tit">
            	<div class="visa_title_div mb"> 
                    <h2>${(visa.productProductid.name)!}</h2> 
                </div>
                <div id="topMain" class="visa_top_main">
                  <div class="visa_top_main_left left">
                        <div class="visa_top_img"> 
                          <img src="${ctx!}${(visa.productProductid.imageurl)!}" width="310" height="174" alt="">
                        </div>
                        <ul class="visa_info_list">
                          <li class="left">
                              <span class="visa_t_1">产品编号：<em>${(visa.productProductid.code)!}</em></span>
                          </li>
                          <li class="right">
                              <span class="visa_info_span_1">价格：</span> 
                              <em class="visa_info_em_1 colorOrange">¥</em>
                              <em class="visa_info_em_2 colorOrange">${(visa.productProductid.minprice)?c}</em>
                              <span class="visa_info_span_1">/人</span> 
                          </li>
                        </ul>
                    </div>
                  <div class="visa_top_main_right left">
                  	<ul class="visa_handle">
                        <li class="visa_handle_li"><b>签证类型：</b><span class="visa_handle_span_1">${(visa.visatype)!}</span></li>
                        <li class="visa_handle_li"><b>入境次数：</b><span class="visa_handle_span_1">${(visa.numberentries)!}</span></li>
                        <li class="visa_handle_li"><b>可停留天数：</b><span class="visa_handle_span_1" >${(visa.stayingdays)!}</span></li>
                        <li class="visa_handle_li"><b>签证有效期：</b><span class="visa_handle_span_1">${(visa.visavalid)!}</span></li>
                    </ul>
                    <div class="clear"></div>
                    <p><b>受理范围：</b>${(visa.acceptancerange)!}</p>
                    <p><b>办理时长：</b>${(visa.handlelength)!}</p>
                    <p><b>签证办理流程：</b>${(visa.bookingProcess)!}</p>
                    <div class="visa_top_line"></div>
                    <div class="visa_handle_bottom">
                    	<div class="visa_condition left">
                            <span class="visa_handle_bottom_title">选择人数：</span>
                            <div class="visa_button_content"> 
                                <i id="i" class="visa_button_minus"></i> 
                                <input type="text" name="" id="adults" class="J-Number" min="1" value="2"> 
                                <i id="ii" class="visa_button_plus"></i> 
                            </div>
                        </div>
                        <div class="visa_condition left">
                            <span class="visa_handle_bottom_title">出行时间：</span>
                            <input placeholder="出行时间" class="J-time" id="startTime"  onClick="WdatePicker({minDate:'%y-%M-{%d+14}'});" readOnly="readOnly" class="pay_mess_input Wdate "  type="text" >
                        </div>
                        <div class="clear"></div>
                        <div class="total_btn">
		                        <div class="total_btn_pay">
		                            <a href="javascript:fnBookOrder();" class="btn_pay">立即预定</a>
		                        </div>
		                       <div class="total_btn_pay">
		                            <a href="${ctx!}/resources-visa/visa-japan.zip" class="btn_pay">表格资料下载</a>
		                        </div>
                        </div>
                    </div>
                  </div>
                  <div class="clear"></div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class=" widthCenter line_filter shadow">
    	<div class="tour_line">
            <div class="specific_itinerary">
                <div class="daily_itinerary" style="position: relative;">
                    <div class="left daily_icon">
                        <i class="visa_icon_cl"></i>
                        <p>签证材料</p>
                        <div class="clear"></div>
                    </div>
                    <!--end daily_navs-->
                    <div class="left daily_cost">
                    	<p>以下材料为客人去使馆面签时所需并携带资料，均需原件。</p>
                        <div class="detail_visa_list">
                            <div class="detail_visa_tips">
                                <ul id="visa_tips">
                                <#list visaocctype as visaocctype >
                                    <li>${(visaocctype.typeName)!}</li>
                                </#list>
                                </ul>
                            </div>
                            <div class="detail_visa_content">
                           <#list visaocctype as visaocctype >
                             <div id="tab_content_${visaocctype_index}">
                                    <table class="visa_list">
                                        <tr height="60">
                                            <td colspan="2" style="border:none;">在面试当天，建议您携带的材料，已备使馆审核（如以下部分材料不能提供，不影响您的送签，但如果使馆抽查时您无法提供可能会影响您的签证结果</td>
                                        </tr>
                                      <#list visaocclist as visaocclist >
                                         <#if visaocctype.typeName=visaocclist.typeName>
	                                         <tr>
	                                            <th>${(visaocclist.title)!}</th>
	                                            <td>${(visaocclist.content)!}</td>
	                                        </tr>
                                         </#if>
		                                   
		                              
                                    </#list>
                                    </table>
                                </div>
                           </#list>
                            </div>
                        </div>
                        
                    </div>
                    <!--end daily_detail-->
                    <div class="clear"></div>
                </div>
                <!--end daily_itinerary-->
                <div class="clear"></div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div class=" widthCenter line_filter shadow">
    	<div class="tour_line">
            <div class="specific_itinerary">
                <div class="daily_itinerary" style="position: relative;">
                    <div class="left daily_icon">
                        <i class="daily_icon_bg b2"></i>
                    	<p>预订须知</p>
                        <div class="clear"></div>
                    </div>
                    <!--end daily_navs-->
                    <div class="left daily_cost">
                    <a id="tour_daily_2"></a>
                    <p class="colorOrange" style="margin-bottom:20px;">行程顺序会因政治，交通，遊客人数和季节变化稍作调整，参观景点一样，恕不另行通知。</p>
                    <div class="mb">
                        <div class="left mr colorBlue">预定须知</div>
                        <div class="left daily_cost_box">
                            <ol>
                               <li>${(visa.bookingPolicy)!}
                                
                            </ol>
                        </div>
                        <div class="clear"></div>
                    </div>
                    
                    
                    <div class="clear"></div>

                    <div class="clear"></div>

                </div>
                    <!--end daily_detail-->
                    <div class="clear"></div>
                </div>
                <!--end daily_itinerary-->
                <div class="clear"></div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    
    <div class="clear"></div>
    <form id="bookOrder" action="${ctx!}/front/visaorders/bookOrder.do" method="post" style="display:none">
    	<input id="productIdInput" name="productId" value="${(visa.productid)!}" type="hidden">
    	<input name="productCode" value="${(visa.productProductid.code)!}" type="hidden">
    	<input id="departureDateInput" name="departureDate" type="hidden">
    	<input id="adultNumInput" name="adultNum" type="hidden">
    	
    </form>
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
	
	//增加减少人数
	function selectthis(obj) {
	$('#div_'+obj.value+'>input[name="cbBtnId"]').each(function(index, element) {
		this.checked = obj.checked;
    });
}
$(document).ready(function(){
    $("#i").click(function(){
		  var n=$("#adults").val();
		  var num=parseInt(n)-1;
		 if(num==1){
		     $("#i").attr("class","visa_button_minus visa_button_off");
		 }
		 if(num==0){
		    return;
		 }
	         $("#adults").val(num);
    });
	$("#ii").click(function(){
		  var n=$("#adults").val();
		  var num=parseInt(n)+1;
	     if(num>0){ 
		   $("#i").attr("class","visa_button_minus");
		 }
		 if(num>10){ 
		    return;
		 }
		   $("#adults").val(num);
    });
 });
$(function(){
	$('#visa_tips li').each(function(index, element) {		
		$(this).click(function(){
			$('#visa_tips li').each(function(index, element) {
				$(this).removeClass('li'+'_current').addClass('li');
			});
			$(this).removeClass('li').addClass('li'+'_current');
			for(var i=0;i<6;i++) {				
				$('#tab_content_'+i).hide();	
			}
			$('#tab_content_'+index).show();
		});
		if(index==0) {
			$(this).removeClass('li').addClass('li'+'_current');
		}
    });
});
 //给form设置值
    var fnSetValue = function(startTime,adults){
    	$("#departureDateInput").val(startTime);
    	$("#adultNumInput").val(adults);
    	
    }
    
    //立即订购
    var fnBookOrder = function(){
        var startTime = $("#startTime").val();
        var adults = $("#adults").val();;
    	if(startTime==''){
    		alert('请选择出发日期.');
    		return;
    	}
		fnSetValue(startTime,adults);
    	$("#bookOrder").submit();
    }
    
   
</script>

</html>