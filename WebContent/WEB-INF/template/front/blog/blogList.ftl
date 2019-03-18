<!DOCTYPE html>
<html lang="en-US" >
<#assign ctx = request.contextPath />
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="${(page.metakeywords)!}" />
<meta name="description" content="${(page.metadescription)!}" />
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<title>${(page.metatitle)!}</title>
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
<section class="hero small-hero" style="background-image: url(<#if page?? && page.imageurl?has_content>${ctx!}${(page.imageurl)!}<#else>${ctx!}/assets-web/images/tourlist_bg.jpg</#if>) ;color: #fff;position: relative;background-color:#6c6e73;background-position: center;background-size: cover;background-repeat: no-repeat;">
  <div class="bg-overlay">
    <div class="container" style="">
      <div class="breadcrumbs">
          <ul>
              <li><a href="/">Home</a></li>
              <li><a href="${ctx!}${(navigationPage.filepath)!}">Blog</a></li>
          <#if blogCategory??>
          	  <li><a href="">${(blogCategory.name)!}</a></li>
          </#if>
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
        	<div id="search-2" class="blog_search">
            	<form method="get" class="searchform" action="">
                    <div class="input-group">
                        <input id="searchInput" type="text" class="form-control" placeholder="Search..." value="" name="s">
                        <span class="input-group-btn">
                            <button class="btn btn-default" id="searchButton" type="button" style="margin-left:0;"><i class="fa fa-search"></i></button>
                        </span>
                    </div>
            	</form>
            </div>
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
            <#if blogTagList?size gt 0>
            <div id="tag_cloud-2" class="blog blog_tags">
            	<h4>TAGS</h4>
                <div class="tagcloud">
                	<#list blogTagList as blogTag>
                		<a target="_blank" href="${ctx!}${(blogTag.page.filepath)!}" class="tag-link-7" title="1 topic" style="font-size: 8pt;">${blogTag.name}</a>
                	</#list>
				</div>
			</div>
			</#if>
            <hr>
        </aside>
        <div id="blogContent" class="blog-content col-md-9">
   		</div>        
	</div>
  </div>
</section>
<#include "/front/include/bottom.ftl"/>
<script type='text/javascript' src='${ctx!}/assets-web/js/My97DatePicker/WdatePicker.js'></script>
<script type="text/javascript">
if (typeof jQuery.fn.fitVids === "undefined") { document.write("<script src='${ctx!}/assets-web/js/jquery.fitvids.min.js'>\x3C/script>"); }

$(document).ready(function(){
	//限制字符个数
	$(".atgrid-item-description").each(function(){
		var maxwidth=110;
			if($(this).text().length>maxwidth){
			$(this).text($(this).text().substring(0,maxwidth));
			$(this).html($(this).html()+'…');
		}
	});
	
	goPage(1);
	
	$("#searchButton").click(function(){
		goPage(1);	
	});
});
	function goPage(pageNow,blogTagId){
	   	$("#blogContent").load('${ctx!}/front/blog/getBlogContent.do',{blogTagId:"${(blogTag.id)!}",search:$("#searchInput").val(),blogCategoryId:"${(blogCategory.id)!}",pageNow:pageNow});
	}
</script> 
</body>
</html>