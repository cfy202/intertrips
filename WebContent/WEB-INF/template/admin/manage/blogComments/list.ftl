<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>博客评价管理</title>
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">
	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".isShow").click(function(){
				var commentsId = $(this).attr("id").split("_")[0];	
				var isShow = $(this).prop("checked") == true?1:0;	
   				$.ajax({
					type: "post",
					url: "${ctx!}/admin/blogComments/changeIsShow.do",
					data:{id:commentsId,isShow:isShow},
					datatype:"json",
					success:function(data){
						if(data == 'success'){
						}
					}
			    });
			});	
			$("input[name='blogName']").focus(function(){
				addCookie('searchBlogName',null,{path:"/"});			
			});
		});
		
		//清除审核状态
		function clearStatus(){
			addCookie('blogCommentsStatus',null,{path:"/"});
			window.location.href="${ctx!}/admin/blogComments/list.do";
		}
		
		//清楚所有的查询参数
		function clearAllParam(){
			addCookie('searchBlogName',null,{path:"/"});
			addCookie('blogCommentsStatus',null,{path:"/"});
			addCookie('blogCommentsPageNow',null,{path:"/"});
			addCookie('blogCommentsPageSize',null,{path:"/"});
			window.location.href="${ctx!}/admin/blogComments/list.do";
		}
	
	   	// 添加Cookie
		function addCookie(name, value, options) {
			if (arguments.length > 1 && name != null) {
				if (options == null) {
					options = {};
				}
				if (value == null) {
					options.expires = -1;
				}
				if (typeof options.expires == "number") {
					var time = options.expires;
					var expires = options.expires = new Date();
					expires.setTime(expires.getTime() + time * 1000);
				}
				document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path="
				 + options.path:"") + (options.domain ? "; domain=" + options.domain: ""),(options.secure ? "; secure": "");
			}
		}
		
		//分页
		function goPage(pageNow){
			window.location.href="${ctx!}/admin/blogComments/list.do?pageNow="+pageNow;
		}
	</script>
	<style>
	  .list_cont_message {
		    overflow: hidden;
		    text-overflow: ellipsis;
		    white-space: nowrap;
		    width: 128px;
		    float: left;
		}
	</style>
</head>
<body class="page-body">
	<div class="page-container">
		<#include "/admin/include/left.ftl"/>
		<div class="main-content">
		    <#include "/admin/include/man.ftl"/>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">博客评价管理</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
							<li>
							   <a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						    </li>
							<li>
								<a href="#">博客评价管理</a>
							</li>
							<li class="active">
								<strong>博客评价列表</strong>
							</li>
						</ol>
				</div>
			</div>	
			<div class="panel panel-default">
			    <nav class="navbar navbar-inverse" role="navigation">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle " data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
								<span class="sr-only">Toggle navigation</span>
								<i class="fa-bars"></i>
							</button>
							<a class="navbar-brand" href="javascript:clearAllParam();">全部</a>
						</div>
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
							<form class="navbar-form navbar-left" id="searchForm" action="${ctx!}/admin/blogComments/list.do" method="POST" role="search">
								<div class="form-group">
									<input type="text" class="form-control searchInput" style="width:350px;" placeholder="博客名称" name="blogName" value="${blogName!}">
								    <input type="hidden" id="pageNowInput" name="pageNow" value="${(page.pageNow)!}">
								</div>
								<button type="submit" class="btn btn-white">搜索</button>
							</form>
							<ul class="nav navbar-nav navbar-left">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										<#if status?? && status == '0'>
										          未审核
										<#elseif status?? && status == '1'>
										          已审核            
										<#else>
											 审核状态（全部） 
										</#if>
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu dropdown-primary">
										<li>
											<a href="javascript:clearStatus();">审核状态（全部）</a>
										</li>
										<li>
											<a href="${ctx!}/admin/blogComments/list.do?status=1">已审核</a>
										</li>
										<li>
											<a href="${ctx!}/admin/blogComments/list.do?status=0">未审核</a>
										</li>
									</ul>
								</li>
							</ul>
							<ul class="nav navbar-nav navbar-right">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										每页显示${page.pageSize}条记录
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu dropdown-primary">
										<li>
											<a href="${ctx!}/admin/blogComments/list.do?pageSize=10">10</a>
										</li>
										<li>
											<a href="${ctx!}/admin/blogComments/list.do?pageSize=20">20</a>
										</li>
										<li>
											<a href="${ctx!}/admin/blogComments/list.do?pageSize=50">50</a>
										</li>
										<li>
											<a href="${ctx!}/admin/blogComments/list.do?pageSize=100">100</a>
										</li>
										<li>
											<a href="${ctx!}/admin/blogComments/list.do?pageSize=${(page.totalCount)?c}">全部</a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
					</nav>
			    
				<div class="panel-body">
					<script type="text/javascript">
					jQuery(document).ready(function($)
						{
							$("#example-3").dataTable({
							    'bStateSave': true,
							    'bLengthChange': true,
							    'bFilter':false,
							    'bPaginate': false,
							    'bInfo': false,
							    'bSort': false,
							    //'sScrollX': "100%",
								//'sScrollXInner': "101%",
								//'bScrollCollapse': true,
								//'sScrollY': 800,//竖向滚动条 tbody区域的高度    
								
								aLengthMenu: [
									[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]
								]
							});
							
							$("#example-3").on('draw.dt', function()
							{
								cbr_replace();
							});
						});
					</script>					
					<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>博客标题</th>
								<th>评论时间</th>
								<th>是否审核</th>
								<th>是否显示</th>
								<th>评论用户</th>
								<th>操作</th>
							</tr>
						</thead>															
						<tbody>
						<#list commentsList as comments>
							<tr>
								<td>${(comments.blog.tittle)!}</td>
								<td>	
									${(comments.createTime?string('yyyy-MM-dd HH:mm:ss'))!}
								</td>
								<td>
								    ${(comments.status=1)?string('<span>已审核</span>','<span style="color:red;">未审核</span>')}
								</td>
								<td>
									<input type="checkbox" id="${(comments.id)!}_id" class="isShow" value="${comments.isShow}" <#if comments.isShow=1>checked="checked"</#if>><label id="${(comments.id)!}_show" for="${(comments.id)!}_id">&nbsp;&nbsp;显示</label> 
								</td>
								<td>
									${(comments.member.account)!}
								</td>
								<td>
									<a href="${ctx!}/admin/blogComments/view.do?id=${(comments.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">
										详情
									</a>
									<a href="${ctx!}/admin/blogComments/delete.do?id=${(comments.id)!}" class="btn btn-danger btn-sm btn-icon icon-left" onClick="return confirm('确定要删除?');">
										删除
									</a>
								</td>
							</tr>	
							</#list>							
						</tbody>
					</table>					
				</div>
				<#include "/admin/include/pagination.ftl"/>
			</div>
			
			<footer class="main-footer sticky footer-type-1">				
				<div class="footer-inner">				
					<div class="footer-text">
						&copy; 2015
					   <a href="#" target="_blank" title="西安淘游网络科技有限公司">西安淘游网络科技有限公司</a> 
					</div>
					<div class="go-up">
						<a href="#" rel="go-top">
							<i class="fa-angle-up"></i>
						</a>						
					</div>					
				</div>				
			</footer>
		</div>
	</div>	

	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/datatables/dataTables.bootstrap.css">

	<!-- Bottom Scripts -->
	<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
	<script src="${ctx!}/assets/js/TweenMax.min.js"></script>
	<script src="${ctx!}/assets/js/resizeable.js"></script>
	<script src="${ctx!}/assets/js/joinable.js"></script>
	<script src="${ctx!}/assets/js/xenon-api.js"></script>
	<script src="${ctx!}/assets/js/xenon-toggles.js"></script>
	<script src="${ctx!}/assets/js/datatables/js/jquery.dataTables.min.js"></script>

	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${ctx!}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="${ctx!}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>

	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>
</body>