<#--
<#assign ctx = request.contextPath />
<script src="${ctx!}/assets-web/js/jquery.lazyload.min.js"></script>
    <script type="text/javascript" charset="utf-8">
	      $(function() {
	              $("img").show().lazyload();
		          $("img").lazyload({ 
					  placeholder : "${ctx!}/assets-web/images/loading.gif",
					  container: $(".list_cont_box_li"),
					  event : "click",
		              effect: "effect"
		           });  
		      });
   </script>	
<ul>	
	<#list tourlinelist as tourlinelist>
	<li class="list_cont_box_li">
		<div class="list_img_box">
	    	<a href="${ctx!}${(tourlinelist.productProductid.pagePageid.filepath)!}" class="list_cont_img">
	        	<img src="${ctx!}${(tourlinelist.coverimageurl)!}" >
	        </a>
	        <div class="clear"></div>
	        <div class="ff14 list_nums colorBlue">产品编号：${(tourlinelist.productProductid.code)!}</div>
        </div>
        <div class="list_cont_main">
            <h2>
                <a href="${ctx!}${(tourlinelist.productProductid.pagePageid.filepath)!}" class="a2">${(tourlinelist.tourname)!}</a>
            </h2>
            <div class="clear"></div>
            <#if tourlinelist.tagList?has_content || tourlinelist.promotionList?has_content>
            <p class="list_cont_nr">
            	<#if tourlinelist.tagList?has_content>
            		<#list tourlinelist.tagList as tag>
            	<span class="list_cont_discount" style="background:#${(tag.bgcolor)!};color:#${(tag.textcolor)!}">${(tag.name)!}</span>
            		</#list>
            	</#if>
            	<#if tourlinelist.promotionList?has_content>
            		<#list tourlinelist.promotionList as promotion>
            	<span class="list_cont_discount" style="background:#ff5500;color:#ffffff">${(promotion.title)!}</span>
            		<#break>
            		</#list>
            	</#if>
            </p>
            </#if>
            <p class="list_cont_nr">
            	<span class="list_cont_message">行程天数：</span>
                <span class="list_cont_day">${(tourlinelist.days)!}天</span>
            </p>
            <p class="list_cont_nr">
            	<span class="list_cont_message">参团地：</span>
                <span>${(tourlinelist.startDestination)!}</span>
                <span class="list_cont_message_2">离团地：</span>
                <span>${(tourlinelist.endDestination)!}</span>
            </p>
            <p class="list_cont_nr">
            	<span class="list_cont_message">行程特色：</span>
                <span class="list_cont_message_3" title="${(tourlinelist.highlights)!}">${(tourlinelist.highlights)!}</span>
            </p>
            
            <#--出发日期显示->
            <div class="left" style="position:relative; margin-top:10px; width:490px;" onmouseout="hideLabelsDetail(1,'youhui',${tourlinelist_index})" onmouseover="showLabelsDetail(1,'youhui',${tourlinelist_index})" >
                 <span class="list_cont_message left list_cont_bg" >出团安排：</span>
                 <div class="label_tips_d" id="list_1_youhui_${tourlinelist_index}" style="left: 0px; top: 30px; display:none ;">
                   <div class="icon_arrow"><img style="margin-left: 19%;" src="${ctx!}/assets-web/images/label_tips_d_arrow.png"></div>
                   <div style="margin:20px;">
               	<#list tourlinelist.tourdatesTourlineid as tourdate>
            		<#if tourdate.days?? && tourdate.days=0>
                    	<#if tourdate.dateweek?? && tourdate.dateweek="0">
                       	 	<span class="list_cont_date">${(tourdate.startdatestr)!}至${(tourdate.enddatestr)!}：每日发团</span>
                        <#elseif tourdate.dateweek?? &&  tourdate.dateweek!="8">
                        	<span class="list_cont_date">${(tourdate.startdatestr)!}至${(tourdate.enddatestr)!}：
                        <#if "${(tourdate.dateweek)!}"?index_of("1")!=-1>周一&nbsp;&nbsp;</#if>
						<#if "${(tourdate.dateweek)!}"?index_of("2")!=-1>周二&nbsp;&nbsp;</#if>
						<#if "${(tourdate.dateweek)!}"?index_of("3")!=-1>周三&nbsp;&nbsp;</#if>
						<#if "${(tourdate.dateweek)!}"?index_of("4")!=-1>周四&nbsp;&nbsp;</#if>
						<#if "${(tourdate.dateweek)!}"?index_of("5")!=-1>周五&nbsp;&nbsp;</#if>
						<#if "${(tourdate.dateweek)!}"?index_of("6")!=-1>周六&nbsp;&nbsp;</#if>
						<#if "${(tourdate.dateweek)!}"?index_of("7")!=-1>周日&nbsp;</#if>  
							</span>
                        <#elseif tourdate.dateweek?? &&  tourdate.dateweek="8">
                        	<span class="list_cont_date">${(tourdate.startdatestr)!}发团&nbsp;&nbsp;</span>
                        </#if>
                    </#if>
                	<#if tourdate.days?? && tourdate.days!=0>
						<span class="list_cont_date">${(tourdate.startdatestr)!}至${(tourdate.enddatestr)!}：每隔${(tourdate.days)!}日发团</span>
					</#if>
            	</#list>
                   </div>
                </div>
           </div>
           <#->
            
         
        </div>
        <div class="list_cont_price">
        	<p>
        	  <#if tourlinelist.lowsprice?? && tourlinelist.lowsprice=0>
        	  <span class="p14"><b>please call</b> 
        	  <#else>
        	  <span class="p14">${(tourlinelist.cost.code)!}</span>
              <span class="p26"><b>${(tourlinelist.lowsprice)!}</b></span>
                        起
              </#if>
            </p>
            <p>
            	<a href="${ctx!}${(tourlinelist.productProductid.pagePageid.filepath)!}" class="btn_check">查看详情</a>
            </p>
        </div>
    </li>
    </#list>
</ul>
<script>
	//select美化
	$(function(){
		$(".select_day").each(function(){
			var s=$(this);
			var z=parseInt(s.css("z-index"));
			var dt=$(this).children("dt");
			var dd=$(this).children("dd");
			var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
			var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
			dt.click(function(){dd.is(":hidden")?_show():_hide();});
			dd.find("a").click(function(){dt.html($(this).html());_hide();});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
			$("body").click(function(i){ !$(i.target).parents(".select_day").first().is(s) ? _hide():"";});
		})
	})
					
	//列表页查看日期效果
	function showLabelsDetail(tourindex, labelname, labelindex) {
	    $("#list_" + tourindex + "_" + labelname + "_" + labelindex).show()
	}
	function hideLabelsDetail(tourindex, labelname, labelindex) {
	    $("#list_" + tourindex + "_" + labelname + "_" + labelindex).hide()
	}
</script>

-->

<#if tourlinelist?has_content>
	<#assign ctx = request.contextPath />
	
	<#list tourlinelist as tourlinelist>
	
	<div class="col-md-6 col-sm-6" style="visibility: visible; margin-bottom:20px;">
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
	                    <del><span class="amount">${(tourlinelist.cost.sign)!}${(tourlinelist.lowmprice)!}</span></del> 
	                    <ins><span class="amount">From ${(tourlinelist.cost.sign)!}${(tourlinelist.lowsprice)!}</span></ins>
                    <#else>
                    	<del><span class="amount">${(tourlinelist.cost.sign)!}${(tourlinelist.lowsprice)!}</span></del> 
	                    <ins><span class="amount">From ${(tourlinelist.cost.sign)!}${(tourlinelist.discountPrice)!}</span></ins>
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
	</#list>
</#if>

<script type="text/javascript">
/**
	$(document).ready(function(){
		//限制字符个数
		$(".atgrid-item-description").each(function(){
			var maxwidth=40;
			if($(this).text().length>maxwidth){
				$(this).text($(this).text().substring(0,maxwidth));
				$(this).html($(this).html()+'…');
			}
		});
	});
	
*/
</script> 