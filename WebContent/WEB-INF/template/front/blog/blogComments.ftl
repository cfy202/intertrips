<#assign ctx=request.contextPath>
<#if totalNumber gt 0>
<div class="blog-reply">
		<h4>${totalNumber} <#if totalNumber gt 1>Comments<#else>Comment</#if></h4>
        <div id="commentsDiv" class="blog-comments">
        	<ol>
        		<#list commentsList as blogComment>
            	<li class="blog-list">
                    <div class="comment-content">
                        <div class="blog-reviews"> 
                        	<img alt="" <#if (blogComment.member.memberinformation.imageurl)??>src="${ctx!}${(blogComment.member.memberinformation.imageurl)!}"><#else>src="${ctx!}/assets-web/images/people.jpg"</#if> class="" height="50" width="50">
                      	</div>
                        <div class="blog-right">
                            <div class="blog-info">
                                <div><span style="color:#f90; margin-right:10px;"><b>${(blogComment.member.account)!}</b></span>${blogComment.content}</div>
                                <p><a rel="nofollow" class="comment-reply-link" isShow="0" onclick="showCommentsInput(this,'${blogComment.id}');" aria-label="Reply to Anna Smith"  style="padding-right:12px;">Reply</a><span style="color:#9f979f;">${blogComment.englishCreateTime}</span></p>
                            </div>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <#list blogComment.commentsToBlogList as replyCommentsToBlog>
                    <div class="blog-reply-main">
                        <div class="comment-content blog-margin">
                            <div class="blog-reviews"> 
                                <img alt="" <#if (replyCommentsToBlog.member.memberinformation.imageurl)??>src="${ctx!}${(replyCommentsToBlog.member.memberinformation.imageurl)!}"><#else>src="${ctx!}/assets-web/images/people.jpg"</#if> class="" height="40" width="40">
                            </div>
                            <div class="blog-right">
                                <div class="blog-info">
                                    <div><span style="color:#f90; margin-right:10px;"><b>${replyCommentsToBlog.member.account}</b></span><#if replyCommentsToBlog.level gt 1><span style="color:#f90; margin-right:10px;"><b>${replyCommentsToBlog.parentAdminName}</b></span></#if>${(replyCommentsToBlog.content)!}</div>
                                    <p><a rel="nofollow" class="comment-reply-link" isShow="0" onclick="showCommentsInput(this,'${replyCommentsToBlog.id}');" aria-label="Reply to Anna Smith"  style="padding-right:12px;">Reply</a><span style="color:#9f979f;">${replyCommentsToBlog.englishCreateTime}</span></p>
                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                    </#list>
                </li>
                </#list>
			</ol>
		</div>
</div>
<div class="">		
	<#include "/include/downPaging.ftl"/>
</div>
</#if>