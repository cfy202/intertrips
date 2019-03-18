<#assign ctx = request.contextPath />
<#--
<select class="list_cont_up">
<#list tourdatelist as tourdate>
	<#if tourdate.days?? && tourdate.days=0>
		<#if tourdate.dateweek?? && tourdate.dateweek="0">
		<option value="0">${(tourdate.startdatestr)!}至${(tourdate.enddatestr)!}：每日发团</option>
		<#elseif tourdate.dateweek?? &&  tourdate.dateweek!="8">
		<option value="0">${(tourdate.startdatestr)!}至${(tourdate.enddatestr)!}：
			<#if "${(tourdate.dateweek)!}"?index_of("1")!=-1>周一&nbsp;&nbsp;</#if>
			<#if "${(tourdate.dateweek)!}"?index_of("2")!=-1>周二&nbsp;&nbsp;</#if>
			<#if "${(tourdate.dateweek)!}"?index_of("3")!=-1>周三&nbsp;&nbsp;</#if>
			<#if "${(tourdate.dateweek)!}"?index_of("4")!=-1>周四&nbsp;&nbsp;</#if>
			<#if "${(tourdate.dateweek)!}"?index_of("5")!=-1>周五&nbsp;&nbsp;</#if>
			<#if "${(tourdate.dateweek)!}"?index_of("6")!=-1>周六&nbsp;&nbsp;</#if>
			<#if "${(tourdate.dateweek)!}"?index_of("7")!=-1>周日&nbsp;</#if>  
		</option>
		<#elseif tourdate.dateweek?? &&  tourdate.dateweek="8">
		<option value="0">${(tourdate.startdatestr)!}发团&nbsp;&nbsp;</option>
		</#if>
	</#if>
	<#if tourdate.days?? && tourdate.days!=0>
	<option value="0">${(tourdate.startdatestr)!}至${(tourdate.enddatestr)!}：每隔${(tourdate.days)!}日发团</option>
	</#if>
</#list>
</select>
-->
<#--
<dl class="select_day right" style="margin-top:0;">
	<dt>
	<#assign tourdateFirst = tourdatelist[0]>
	<#if tourdateFirst.days?? && tourdateFirst.days=0>
    	<#if tourdateFirst.dateweek?? && tourdateFirst.dateweek="0">
       	 	${(tourdateFirst.startdatestr)!}至${(tourdateFirst.enddatestr)!}：每日发团
        <#elseif tourdateFirst.dateweek?? &&  tourdateFirst.dateweek!="8">
        	${(tourdateFirst.startdatestr)!}至${(tourdateFirst.enddatestr)!}：
	        <#if "${(tourdateFirst.dateweek)!}"?index_of("1")!=-1>周一&nbsp;&nbsp;</#if>
			<#if "${(tourdateFirst.dateweek)!}"?index_of("2")!=-1>周二&nbsp;&nbsp;</#if>
			<#if "${(tourdateFirst.dateweek)!}"?index_of("3")!=-1>周三&nbsp;&nbsp;</#if>
			<#if "${(tourdateFirst.dateweek)!}"?index_of("4")!=-1>周四&nbsp;&nbsp;</#if>
			<#if "${(tourdateFirst.dateweek)!}"?index_of("5")!=-1>周五&nbsp;&nbsp;</#if>
			<#if "${(tourdateFirst.dateweek)!}"?index_of("6")!=-1>周六&nbsp;&nbsp;</#if>
			<#if "${(tourdateFirst.dateweek)!}"?index_of("7")!=-1>周日&nbsp;</#if>  
        <#elseif tourdateFirst.dateweek?? &&  tourdateFirst.dateweek="8">
        	${(tourdateFirst.startdatestr)!}发团&nbsp;&nbsp;
        </#if>
    </#if>
	<#if tourdateFirst.days?? && tourdateFirst.days!=0>
		${(tourdateFirst.startdatestr)!}至${(tourdateFirst.enddatestr)!}：每隔${(tourdateFirst.days)!}日发团
	</#if>
	</dt>
	<dd>
		<ul>
		<#list tourdatelist as tourdate>
    		<#if tourdate.days?? && tourdate.days=0>
            	<#if tourdate.dateweek?? && tourdate.dateweek="0">
               	 	<li class="option_li"><a>${(tourdate.startdatestr)!}至${(tourdate.enddatestr)!}：每日发团</a></li>
                <#elseif tourdate.dateweek?? &&  tourdate.dateweek!="8">
                	<li class="option_li"><a>${(tourdate.startdatestr)!}至${(tourdate.enddatestr)!}：
	                <#if "${(tourdate.dateweek)!}"?index_of("1")!=-1>周一&nbsp;&nbsp;</#if>
					<#if "${(tourdate.dateweek)!}"?index_of("2")!=-1>周二&nbsp;&nbsp;</#if>
					<#if "${(tourdate.dateweek)!}"?index_of("3")!=-1>周三&nbsp;&nbsp;</#if>
					<#if "${(tourdate.dateweek)!}"?index_of("4")!=-1>周四&nbsp;&nbsp;</#if>
					<#if "${(tourdate.dateweek)!}"?index_of("5")!=-1>周五&nbsp;&nbsp;</#if>
					<#if "${(tourdate.dateweek)!}"?index_of("6")!=-1>周六&nbsp;&nbsp;</#if>
					<#if "${(tourdate.dateweek)!}"?index_of("7")!=-1>周日&nbsp;</#if>  
					</a></li>
                <#elseif tourdate.dateweek?? &&  tourdate.dateweek="8">
                	<li class="option_li"><a>${(tourdate.startdatestr)!}发团&nbsp;&nbsp;</a></li>
                </#if>
            </#if>
        	<#if tourdate.days?? && tourdate.days!=0>
				<li class="option_li"><a>${(tourdate.startdatestr)!}至${(tourdate.enddatestr)!}：每隔${(tourdate.days)!}日发团</a></li>
			</#if>
    	</#list>
		</ul>
	</dd>
</dl>
-->
<#--出发日期显示-->
<div class="left" style="position:relative; width:490px;" onmouseout="hideLabelsDetail(1,'youhui',1)" onmouseover="showLabelsDetail(1,'youhui',1)" >
	<span class="p_one_2 colorLightGray left list_cont_bg" style="padding-right: 31px;">出团安排：</span>
    <div class="label_tips_d" id="list_1_youhui_1" style="left: 0px; top: 30px; display:none ;">
     	<div class="icon_arrow"><img style="margin-left: 25%;" src="${ctx!}/assets-web/images/label_tips_d_arrow.png"></div>
     	<div style="margin:20px;">
     		<#list tourdatelist as tourdate>
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
					<div class="clear"></div>
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
<div class="clear"></div>
<#---->
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