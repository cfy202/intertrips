 <tr bgcolor="#eaf6fe" height="40">
    <td width="40%"></td>
    <td width="15%">一人一间</td>
    <td width="15%">两人一间</td>
<#if tourdatelist[0].tourprice.threesellingprice != 0>    
    <td width="15%">三人一间</td>
</#if>    
<#if tourdatelist[0].tourprice.foursellingprice != 0> 
    <td width="15%">四人一间</td>
</#if>
</tr>
<#list tourdatelist as tourdatelist>
<tr height="40">
    <td>
    <#if tourdatelist.days?? && tourdatelist.days=0>
    	<#if tourdatelist.dateweek?? && tourdatelist.dateweek="0">
    ${(tourdatelist.startdatestr)!}至${(tourdatelist.enddatestr)!}：每日发团
    	<#elseif tourdatelist.dateweek?? &&  tourdatelist.dateweek!="8">
    ${(tourdatelist.startdatestr)!}至${(tourdatelist.enddatestr)!}：
    		<#if "${(tourdatelist.dateweek)!}"?index_of("1")!=-1>周一&nbsp;&nbsp;</#if>
			<#if "${(tourdatelist.dateweek)!}"?index_of("2")!=-1>周二&nbsp;&nbsp;</#if>
			<#if "${(tourdatelist.dateweek)!}"?index_of("3")!=-1>周三&nbsp;&nbsp;</#if>
			<#if "${(tourdatelist.dateweek)!}"?index_of("4")!=-1>周四&nbsp;&nbsp;</#if>
			<#if "${(tourdatelist.dateweek)!}"?index_of("5")!=-1>周五&nbsp;&nbsp;</#if>
			<#if "${(tourdatelist.dateweek)!}"?index_of("6")!=-1>周六&nbsp;&nbsp;</#if>
			<#if "${(tourdatelist.dateweek)!}"?index_of("7")!=-1>周日&nbsp;</#if>  
    	<#elseif tourdatelist.dateweek?? &&  tourdatelist.dateweek="8">
    ${(tourdatelist.startdatestr)!}发团&nbsp;&nbsp;
    	</#if>
	</#if>
	<#if tourdatelist.days?? && tourdatelist.days!=0>
	<option value="0">${(tourdatelist.startdatestr)!}至${(tourdatelist.enddatestr)!}：每隔${(tourdatelist.days)!}日发团</option>
	</#if>
    </td>
	<#assign onePrice = tourdatelist.tourprice.singleroomprice+tourdatelist.tourprice.sellingprice>
	<#if onePrice?? && onePrice!=0>
    <td>${(cost.code)!}&nbsp;${onePrice!}</td>
    <#else>
    <td>Please call</td>
    </#if>
    <#if tourdatelist.tourprice.sellingprice?? && tourdatelist.tourprice.sellingprice!=0>
    <td>${(cost.code)!}&nbsp;${(tourdatelist.tourprice.sellingprice)!}</td>
    <#else>
    <td>Please call</td>
    </#if>
	<#if tourdatelist.tourprice.threesellingprice != 0>    
    <td>${(cost.code)!}&nbsp;${(tourdatelist.tourprice.threesellingprice)!}</td>
	</#if>    
	<#if tourdatelist.tourprice.foursellingprice != 0>    
    <td>${(cost.code)!}&nbsp;${(tourdatelist.tourprice.foursellingprice)!}</td>
	</#if>
</tr>
</#list>