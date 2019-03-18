<#assign ctx = request.contextPath />
<#if blogList?size gt 0>
<div class="blog_style_1">
	<#list blogList as blog>
    <div class="post">
        <a href="#"><img src="${ctx!}${(blog.coverImageUrl)!}" width="858px" height="375" class="img-responsive wp-blog-image" alt="blog-2"></a>
        <div class="blog_info">
            <div class="blog-left left">
                <ul>
                    <li><i class="fa fa-calendar-o"></i>&nbsp;&nbsp;On <span>${(blog.releaseTimeString)!}</span></li>
                    <li><i class="fa fa-user"></i>&nbsp;&nbsp;by <img src="${ctx!}/assets-web/images/people.jpg"> ${blog.releaseAdmin.username}</li>
                    <#if (blog.blogTagList)?? && blog.blogTagList?size gt 0><li><i class="fa fa-tags"></i>&nbsp;&nbsp;Tags: <#list blog.blogTagList as blogTag><a href="${ctx!}${blogTag.page.filepath}" rel="tag" style="color:#999;">${blogTag.name}</a></#list></li></#if>
                </ul>
            </div>
            <div class="blog-right right"><i class="fa fa-comment"></i> ${blog.numberOfComments} Comment<#if blog.numberOfComments gt 1>s</#if></div>
            <div class="clear"></div>
        </div>
        <h2>${blog.tittle}</h2>
        <p class="atgrid-item-description">${blog.content}</p>
        <a href="${ctx!}${blog.page.filepath}" class="btn_1">Read more</a>
    </div>
    <#if blog_has_next>
    <hr> 
    </#if>
    </#list>
</div>
<#include "/include/downPaging.ftl"/>
<#else>
<div class="blog_style_1 blog-search-main">
	Sorry, no results could match your search criteria.
</div>
</#if>
<#--
<div class="post">
    <img src="${ctx!}/assets-web/images/sydney.jpg" width="858px" height="375" class="img-responsive wp-blog-image" alt="blog-2">
    <div class="blog_info">
        <div class="blog-left left">
            <ul>
                <li><i class="fa fa-calendar-o"></i>&nbsp;&nbsp;On <span>September 29, 2015</span></li>
                <li><i class="fa fa-user"></i>&nbsp;&nbsp;by <a href="" rel="category tag" style="color:#f90;"><img src="${ctx!}/assets-web/images/people.jpg"> Events</a></li>
                <li><i class="fa fa-tags"></i>&nbsp;&nbsp;Tags: <a href="" rel="tag" style="color:#999;">Dolor</a></li>
            </ul>
        </div>
        <div class="blog-right right"><i class="fa fa-comment"></i> 3 Comments</div>
        <div class="clear"></div>
    </div>
    <h2>Lorem ipsum dolor sit amet – 2</h2>
    <p>Ludus albucius adversarium eam eu. Sit eu reque tation aliquip. Quo no dolorum albucius lucilius, hinc eligendi ut sed. Ex nam quot ferri suscipit, mea ne legere alterum repudiandae. Ei pri quaerendum intellegebat, ut vel consequuntur voluptatibus. Et volumus sententiae adversarium duo. Ludus albucius adversarium eam eu. Sit eu reque tation aliquip. Quo no dolorum […]</p>
    <a href="" class="btn_1">Read more</a>
</div><!-- end post -->		