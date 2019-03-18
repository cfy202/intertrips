  <#if page.pageNow gt 1>
  <a href="javascript:goPage(${page.pageNow - 1})" class="prev_paging"><i class="page_arrow_p"></i></a>
  <#else>
  <a class="prev_paging"><i class="page_arrow_p"></i></a>
  </#if>
  <span class="page_num">${page.pageNow!}/<#if page.totalPageCount?? && page.totalPageCount=0>1<#else>${(page.totalPageCount)!}</#if></span> 
  <#if page.pageNow lt page.totalPageCount>
  <a href="javascript:goPage(${page.pageNow+1})" class="next_paging"><i class="page_arrow_n"></i></a>
  <#else>
  <a class="next_paging"><i class="page_arrow_n"></i></a>
  </#if>