<div class="turn_page">
    <p>
    	<#if page.pageNow gt 1>
        <a class="prev" href="javascript:goPage(${page.pageNow - 1})"></a>
        <#else>
        <a class="prev"></a>
		</#if>
		 <#assign start=1>
		 <#if (page.pageNow-2 > 0)>
		   <#assign start=(page.pageNow - 2)>
		 </#if>
		 <#assign end=(start + 4)>
		 <#if (end > page.totalPageCount)>
		    <#assign end=page.totalPageCount>
		 </#if>
		 <#if page.totalPageCount==0>
		    <#assign end=1>
		 </#if>
		 <#list start..end as i>
		    <#if (page.pageNow==i)>
		    <a class="cur">${i}</a>
		    <#else>
		    <a class="ajax-handle" href="javascript:goPage(${i})">${i}</a>     
		    </#if>
		 </#list>
        <#if page.pageNow lt page.totalPageCount>
		<a class="next" href="javascript:goPage(${page.pageNow + 1})"></a>
		<#else>
		<a class="next"></a>
		</#if>
        <span>到</span>
        <input type="text" id="next_page" value="2" class="text">
        <span>页</span>
        <a href="javascript:selectPage()" class="confirm redirConfirm ajax-handle">确定</a>
    </p>
    <div class="clear"></div>
    <input type ="hidden" name="totalPageCount" value="${page.totalPageCount}" id="tpc">
</div>

