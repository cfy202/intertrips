<!DOCTYPE html>
<html lang="en-US" >
<head>
<#assign ctx=request.contextPath />
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<title>${(blog.page.title)!}</title>
<meta name="keywords" content="${(blog.page.metakeywords)!}" />
<meta name="description" content="${(blog.page.metadescription)!}" />
<link rel="shortcut icon" href="${ctx!}/favicon.ico">
<link rel="apple-touch-icon-precomposed" href="${ctx!}/apple-touch-icon.png">
<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
<#--
<link rel='stylesheet' id='google-font-css'  href='https://fonts.googleapis.com/css?family=Lato:400,400italic,700,700italic|Cabin:600,600italic,400,400italic,700,700italic&#038;subset=latin,latin-ext' type='text/css' media='all' />
-->
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv-printshiv.min.js?ver=4.3.1'></script> <![endif]-->
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js?ver=4.3.1'></script> 
<![endif]-->

<meta name="generator">
<style id="fit-vids-style">
.fluid-width-video-wrapper { width: 100%; position: relative; padding: 0; }
.fluid-width-video-wrapper iframe, .fluid-width-video-wrapper object, .fluid-width-video-wrapper embed { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
</style>
<style id="ShrinkMenu">
.menu-shrink { top : -49px !important; }
</style>
</head>
<body class="blog">
<div id="top"></div>
<#include "/front/include/top.ftl"/>
<section class="hero small-hero" style="background-image: url(${ctx!}/assets-web/images/blog-2-bg.jpg);color: #fff;position: relative;background-color:#6c6e73;background-position: center;background-size: cover;background-repeat: no-repeat;">
  <div class="bg-overlay">
    <div class="container" style="">
      <div class="breadcrumbs">
          <ul>
              <li><a href="/">Home</a></li>
              <li><a href="${ctx!}${pageNavigationUrl!}">Blog</a></li>
          </ul>
      </div>
      <div class="list-intro-wrap">
        <h1 class="intro-title" style="text-align:center; margin-top:0;">BLOG</h1>
        <div class="intro-text"> Discover your next great adventure, become an explorer to get started!</div>
      </div>
    </div>
  </div>
</section>
<section class="featured-destinations" style="background-color:#f5f6f6;">
  <div class="container">
    <div class="row" style="margin-top:20px;">
		<aside class="col-md-3">
        	<article id="text-3" class="widget widget_text">
            	<h4 class="um_title">About me</h4>			
                <div class="textwidget">
                	<p><img src="${ctx!}${(blog.releaseAdmin.imageurl)!}"></p>
					<p>${(blog.releaseAdmin.remark)!}</p>
				</div>
			</article>
            <hr>
            <div id="recent-posts-2" class="blog">
            	<h4>Recent Posts</h4>
                <ul class="blog-ul-1">
                	<#list recentBlogList as blog>
                	<li><a target="_blank" href="${ctx!}${blog.page.filepath}">${blog.tittle}</a></li>
                	</#list>
				</ul>
			</div>
            <hr>
            <div id="categories-2" class="blog blog_categories">
            	<h4>Categories</h4>
                <ul class="blog-ul-2">
                	<#list blogCategoryList as bca>
                	<li class=""><a <#if blogCategory?? &&  blogCategory.id = bca.id> class="blog_current"</#if> href="${ctx!}${(bca.page.filepath)!}" title="">${bca.name}</a></li>
                	</#list>
				</ul>
			</div>
            <hr>
            <div id="tag_cloud-2" class="blog blog_tags">
            	<h4>TAGS</h4>
                <div class="tagcloud">
                	<#list blogTagList as blogTag>
                		<a target="_blank" href="${ctx!}${(blogTag.page.filepath)!}" class="tag-link-7" title="1 topic" style="font-size: 8pt;">${blogTag.name}</a>
                	</#list>
				</div>
			</div>
            <hr>
        </aside>
        <div class="blog-content col-md-9">
            <div id="blogDetails" class="blog_style_1">
                <div class="post">
                	<img src="${ctx!}${(blog.coverImageUrl)!}" width="858px" height="375" class="img-responsive wp-blog-image" alt="blog-2">
                    <div class="blog_info">
                        <div class="blog-left left">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i>&nbsp;&nbsp;On <span>${(blog.releaseTimeString)!}</span></li>
                                <li><i class="fa fa-user"></i>&nbsp;&nbsp;by<img <#if (blog.releaseAdmin.imageurl)??>src="${ctx}${blog.releaseAdmin.imageurl}"<#else>src="${ctx}/assets-web/images/people.jpg"</#if>>${(blog.releaseAdmin.username)!}</li>
                                <#if (blog.blogTagList)?? && blog.blogTagList?size gt 0><li><i class="fa fa-tags"></i>&nbsp;&nbsp;Tags: <#list blog.blogTagList as blogTag><a href="${ctx!}${blogTag.page.filepath}" rel="tag" style="color:#999;">${blogTag.name}</a></#list></li></#if>
                            </ul>
                        </div>
                        <div class="blog-right right"><i class="fa fa-comment"></i> ${(blog.numberOfComments)!} Comments</div>
                        <div class="clear"></div>
                    </div>
                    <h2>${(blog.tittle)!}</h2>                             
                    <div class="theContent">
                    	${(blog.content)!}
                    	<#--
                        <p class="blog-font">The her hand, we denounce with righteous indignation and dislike men who are so beguiled and demo ralized by the char ms of pleasure of the moment, so blinded by des;ire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. New York City’s skyline is truly awe-inspiring. The iconic skyscrapers, bridges, waterways, islands and monuments create a breathtaking.</strong></p>
                        <div id="attachment_34" class="blog-img">
                        	<img class="wp-image-34 size-full" src="https://themes.themegoods2.com/letsblog/wp-content/uploads/2015/07/33dca3b1ecb01a7d-2012_WeAre-TheRhoads_oct_KinfolkVol5_10_o.jpg" alt="" width="1440" height="960">
                            <p class="blog-text">Travelling all around the world.</p>
                        </div>
                        <p>Aspiring to beauty in our designs is admirable. But it doesn’t guarantee usability, nor is it a product or marketing strategy. Like “simple” and “easy” before it, “beautiful” says very little about the product. How many people, fed up with PowerPoint, cry out in frustration, “If only it were more beautiful”? At best, the problem is simple: No one has figured out how to describe their product effectively. For example, Write, a note-taking app, describes itself as “a beautiful home for all your notes,” which doesn’t say much about why one might want it. Macworld describes it as “Easy Markdown Writing for Dropbox Users.” That’s both concise and specific: If you like Markdown and use Dropbox, you’ll read more.</p>
                        <p>Digital product design discourse over the last few years has become literally superficial. Much (most?) of the attention has been on issues like ‘flat’ vs ‘skeuomorphic’, the color scheme of iOS 7, parallax scrolling, or underlining links. And not that these things aren’t important or worth discussing, but as someone who came up in design by way of usability and information architecture, I’ve been disappointed how the community has willfully neglected the deeper concerns of systems and structure in favor of the surface.I mean, how many pixels need to be spilled on iOS 7.1’s redesigned shift key?</p>
                        -->
                    </div>
                    <hr>
                    <#if (blog.pingStatus)?? && blog.pingStatus = 1>
                    <div class="blog_Share_list">
						<div class="left"><i class="fa fa-share-alt"></i>Share</div>
                        <ul class="blog_share right">
                        	<li><a href="https://www.facebook.com/share.php?u=https://www.blog.umbrella.al/1/easily-understandable-way-to-new-visitors/&amp;title=Easily understandable way to new visitors" target="_blank" class="um_socialButton animated" id="facebook"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="https://twitter.com/home?status=Easily understandable way to new visitors+https://www.blog.umbrella.al/1/easily-understandable-way-to-new-visitors/" target="_blank" class="um_socialButton animated" id="twitter"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="https://plus.google.com/share?url=https://www.blog.umbrella.al/1/easily-understandable-way-to-new-visitors/" target="_blank" class="um_socialButton animated" id="google"><i class="fa fa-google-plus"></i></a></li>
                        </ul>
                        <div class="clear"></div>	
					</div>
					</#if>
                </div>
        	</div><!-- end box_style_1 -->
            <!-- blog-reply -->
            
            <#if blog.commentStatus = 1>
            <div id="blogCommentsDiv" class="post-comment">
                <div class="citytours-box">
                	<div id="respond" class="comment-respond">
                        <h4>Leave a Comment <small><a rel="nofollow" href="" style="display: none;">Cancel reply</a></small></h4>
                        <form action="" method="post" id="commentform" class="comment-form">
                            <div class="form-group"> <textarea name="content" class="form-control blogContent" style="height:150px;" placeholder="Message"></textarea> </div>		
                            <input type="hidden" name="blogId" value="${blog.id}"/>
                            <p class="form-submit"><input name="submit" type="button" class="commentSubmit" value="Post Comment"></p>
                        </form>
                    </div><!-- #respond -->
                </div>
            </div>
            </#if>
   		</div>        
	</div>
  </div>
</section>
<div style="display:none">
   <div id="replyDiv" class="reviewinput comment-content blog-margin">
        <div class="blog-reviews"> 
            <img alt="" src="${ctx!}/assets-web/images/people.jpg" class="loginMemberImage" height="40" width="40">
        </div>
        <div class="blog-right width80">
	        <form>	
	            <input type="text" name="content" class="form-control style_2" style="height:40px; width:100%; background:#fff;" placeholder="Message"></textarea>
	            <input type="hidden" class="parentIdInput" name="parentId" />
	            <input type="hidden" name="blogId" value="${blog.id}"/>
	            <div style="margin-top:5px; text-align:right;"><a onclick="submitReview(this);">Review</a></div>
	        </form>    
        </div>
        <div class="clear"></div>
    </div>
</div>
<#include "/front/include/bottom.ftl"/>
<#include "/front/include/alertFrame.ftl"/>
<script type='text/javascript' src='${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js'></script>
<script type="text/javascript">
$(function(){
	$('input').customInput();
});
</script>
<script type="text/javascript">
if (typeof jQuery.fn.fitVids === "undefined") { document.write("<script src='${ctx!}/assets-web/js/jquery.fitvids.min.js'>\x3C/script>"); }

isLogin = false;

$(document).ready(function(){
	//限制字符个数
	$(".atgrid-item-description").each(function(){
		var maxwidth=110;
			if($(this).text().length>maxwidth){
			$(this).text($(this).text().substring(0,maxwidth));
			$(this).html($(this).html()+'…');
		}
	});
	
	getPage(1);
	
	//判断用户是否登陆
	$.ajax({
		url: "${ctx!}/login/getMember.do",
		type: "GET",
		dataType: "json",
		cache: false,
		success: function(member) {
			if(member != null){
				isLogin = true;
				if(member.memberinformation.imageurl != null){
					$(".loginMemberImage").attr("src","${ctx!}" + member.memberinformation.imageurl);
				}
			}
		}
	});
	
	//针对blog评论提交
	$(".commentSubmit").click(function(){
		if(!isLogin){
			alertWarn('Please login in.');
			return;
		}
		var $submitForm = $(this).parent().parent();
			
		var blogContent = $submitForm.find(".blogContent").val();
		if(blogContent.trim() == ''){
			alertWarn('Please fill in your review.');
			return;
		}
		$.post("${ctx!}/front/blog/submitBlogComments.do",$submitForm.serialize(),function(data){
			if(data == 'success'){
				alertWarn('under review...');
				$submitForm.find(".blogContent").val('');
			}else{
				alertWarn('The network is busy now, please try again later.');
			}		
		});									
	});
});

//评论分页
function getPage(pageNow){
    $.post("${ctx!}/front/blog/getBlogComments.do",{blogId:"${blog.id}",pageNow:pageNow},function(data){
    	$("#blogDetails").after(data.html);		
    });	
}

//点击评论回复按钮事件
function showCommentsInput(clickButton,commentsId){	
	if(isLogin){
		var isShow = $(clickButton).attr("isShow");
		//用户回复评论
		if(isShow == 0){
			$("#commentsDiv").find(".reviewinput").remove();
			var $replyDiv = $("#replyDiv").clone(true).removeAttr("id");
			$replyDiv.find(".parentIdInput").val(commentsId);
			$(clickButton).closest(".comment-content").after($replyDiv);
			$(clickButton).attr("isShow",1);
		//用户回复blog
		}else{
			$("#commentsDiv").find(".reviewinput").remove();
			$(clickButton).attr("isShow",0);
		}
	}else{
		alertWarn('Please login in.');
	}	
}

//针对评论的评论
function submitReview(button){
	var $submitForm = $(button).parent().parent();
	$.post("${ctx!}/front/blog/submitBlogComments.do",$submitForm.serialize(),function(data){
		if(data == 'success'){
			$("#commentsDiv").find(".reviewinput").remove();
			alertWarn('under review...');
		}else{
			alertWarn('The network is busy now, please try again later.');
		}		
	});		
}
</script> 
</body>
</html>