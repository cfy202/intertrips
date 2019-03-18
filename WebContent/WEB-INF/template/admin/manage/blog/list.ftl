<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<#assign ctx = request.contextPath />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<meta name="description" content="Xenon Boostrap Admin Panel" />
		<meta name="author" content="" />

		<title>博客管理</title>
		<!--<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic"> -->

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
			//分页
			function goPage(pageNow){
			    window.location.href="${ctx!}/admin/tourline/list.do?TpageNow="+pageNow;
			}
		</script>
	</head>
	<body class="page-body">
		<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->

			<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
			<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
			<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
			<#include "/admin/include/left.ftl"/>


			<div class="main-content">

				<!-- User Info, Notifications and Menu Bar -->
				<#include "/admin/include/man.ftl"/>

				<div class="page-title">
					<div class="title-env">
						<h1 class="title">博客管理</h1>
					</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
							<li>
								<a href="${ctx!}/admin/list.do">
									<i class="fa-home"></i>
									首页
								</a>
							</li>
							<li>
								<a href="${ctx!}/admin/blog/list.do">博客管理</a>
							</li>
							<li class="active">
								<strong>博客列表</strong>
							</li>
						</ol>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<button class="btn btn-turquoise" onclick="window.location.href='${ctx!}/admin/blog/add.do'">添加博客</button>
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{   
								$("#s2example-2").select2({
									placeholder: '选择标签',
									allowClear: true
								}).on('select2-open', function()
								{
									// Adding Custom Scrollbar
									$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
								});
							});
							function bachTags(){
							  var $checkedTags = $("#myfrom").find(":checkbox:checked");
							  var $checkedBlog = $("#s2example-2").find("option:checked[value!='remove']");
							  if($checkedTags.size() == 0){
							  	  alert('请选择博客.');
							  	  return;
							  }
							  $("#tagIdInput").val($("#s2example-2").val());
							  myfrom.action="${ctx!}/admin/blog/addTags.do";
							  myfrom.submit();
							}
						</script>
						<div class="col-sm-3">
							<div style="float:left">
								<select class="form-control" name="tourlineC" id="s2example-2" multiple>
									<option value="remove">移除标签</option>
									<#if blogTagList??>
									<#list blogTagList as blogTag>
									<option value="${(blogTag.id)!}">${(blogTag.name)!}</option>
									</#list>
									</#if>
								</select>
							</div>
							<div style="float:left">
								<button class="btn btn-info" onclick="bachTags()">批量添加标签或更新标签</button>
							</div>
						</div>
					</div>
					<nav class="navbar navbar-inverse" role="navigation">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
								<span class="sr-only">Toggle navigation</span>
								<i class="fa-bars"></i>
							</button>
							<#--
							<a class="navbar-brand" href="javascript:clearAllParam();">全部</a>
							-->
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
							<#--
							<form class="navbar-form navbar-left" action="${ctx!}/admin/tourline/list.do" method="POST" role="search">
								<div class="form-group">
									<input type="text" class="form-control" onFocus="realse()" placeholder="线路名称、编号、天数、分类" id="tsearch" value="${Tsearch!}" name="Tsearch">
									<input type="hidden" name="TpageNow" value="${(page.pageNow)!}">
									<input type="hidden" name="costId" id="costId" value="${costIdo!}">
								</div>
								<button type="submit" class="btn btn-white">搜索</button>
							</form>
							-->
							<ul class="nav navbar-nav navbar-right">
								<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										每页显示${page.pageSize}条记录
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu dropdown-primary">
										<li>
											<a href="${ctx!}/admin/blog/list.do?pageSize=10">10</a>
										</li>
										<li>
											<a href="${ctx!}/admin/blog/list.do?pageSize=20">20</a>
										</li>
										<li>
											<a href="${ctx!}/admin/blog/list.do?pageSize=50">50</a>
										</li>
										<li>
											<a href="${ctx!}/admin/blog/list.do?pageSize=100">100</a>
										</li>
										<li>
											<a href="${ctx!}/admin/blog/list.do?pageSize=${(page.totalCount)?c}">全部</a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
						<!-- /.navbar-collapse -->
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
							    //'bSort': false,
							    //'sScrollX': "100%",
								//'sScrollXInner': "101%",
								//'bScrollCollapse': true,
								//'sScrollY': 800,//竖向滚动条 tbody区域的高度    
								aLengthMenu: [
									[10, 25, 50, 100, -1], [10, 25, 50, 100, "All"]
								]
							});
							// Replace checkboxes when they appear
							var $state = $("#example-3 thead input[type='checkbox']");
							
							$("#example-3").on('draw.dt', function()
							{
								cbr_replace();
								$state.trigger('change');
							});
							
							// Script to select all checkboxes
							$state.on('change', function(ev)
							{
								var $chcks = $("#example-3 tbody input[type='checkbox']");
								
								if($state.is(':checked'))
								{
									$chcks.prop('checked', true).trigger('change');
								}
								else
								{
									$chcks.prop('checked', false).trigger('change');
								}
							});
						});
						</script>
						<form role="forl" action="${ctx!}/admin/blog/create.do" id="myfrom" method="post">
							<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th class="no-sorting">
											<input type="checkbox" class="cbr">
										</th>
										<th>标题</th>
										<th>标签</th>
										<th>封面</th>
										<th>评论数</th>
										<th>博客状态</th>
										<th>是否推荐</th>
										<th>评论状态</th>
										<th>共享状态</th>
										<th>首页显示</th>
										<th>类别</th>
										<th>发布人</th> 
										<th>发布时间</th>
										<th>最后更新时间</th>
										<th>生成状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
								  <#list blogList as blog>
								   <tr>
								        <th class="no-sorting">
									        <input type="checkbox" name="blogIds" value="${(blog.id)!}" class="cbr cbr-success">
										</th>
										<td>
											${blog.tittle}
										</td>
										<td>
											<div class="vertical-top">
											 <#if (blog.blogTagList)??>
											 <#list blog.blogTagList as blogTag>	
											 <button class="btn" <#if blogTag.isHot = 1>style="background-color:red"</#if>>${(blogTag.name)!}</button>
											 </#list>
											 </#if>
											</div>
										</td>
										<td>
										   <div>
											    <a href="" ><img src="${ctx!}${(blog.coverImageUrl)!}" height="18px" width="40px" id="img"/></a>
										   </div>
										</td>
										<td>
											${blog.numberOfComments}
										</td>
										<td>${blog.statusInfo}</td>
										<td><#if blog.isRecommended = 1>是<#else>否</#if></td>
										<td><#if blog.commentStatus = 1>允许<#else>禁止</#if></td>
										<td><#if blog.pingStatus = 1>允许<#else>禁止</#if></td>
										<td><#if (blog.sticky)?? && blog.sticky = 1>是<#else>否</#if></td>
										<td>${blog.blogCategory.name}</td>
										<td>${blog.releaseAdmin.username}</td>
										<td>${blog.releaseTime?string('yyyy-MM-dd HH:mm:ss')}</td>
										<td>${blog.lastUpdateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
										<#if blog.isCreate = 1>
										<td>已生成</td>
										<#else>
										<td>未生成</td>
										</#if>
										<td>
										  <ul class="nav navbar-nav">
											<li class="dropdown">
												<a href="#" class="dropdown-toggle" data-toggle="dropdown">操作 <b class="caret"></b></a>
												<ul class="dropdown-menu">
													<li>
														<a href="${ctx!}/admin/blog/edit.do?id=${(blog.id)!}">
										              	    修改博客
										                </a>
													</li>
												    <li>
														<a href="${ctx!}/admin/blog/delete.do?id=${(blog.id)!}" onClick="return confirm('确定要删除?');">
										              	   删除博客
										                </a>
													</li>
													<li>
														<a href="${ctx!}/admin/blog/view.do?id=${(blog.id)!}" target="_blank">
										              	   博客预览
										                </a>
													</li>
												    <li>
														<a href="${ctx!}/admin/blog/create.do?id=${(blog.id)!}">
										              	   页面生成
										                </a>
													</li>
												</ul>
											</li>
										  </ul>
										</td>
								   </tr>
								  </#list>
								</tbody>
							</table>
							<input id="tagIdInput" type="hidden" name="blogTagIds"/>
						</form>
						<#include "/admin/include/pagination.ftl"/>
					</div>
				</div>
				<footer class="main-footer sticky footer-type-1">
					<div class="footer-inner">
						<div class="footer-text">
						&copy;2015
							<a href="#" target="_blank" title="西安淘游网络科技有限公司">西安淘游网络科技有限公司</a>
						</div>
						<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
						<div class="go-up">
							<a href="#" rel="go-top">
								<i class="fa-angle-up"></i>
							</a>
						</div>
					</div>
				</footer>
			</div>
		</div>
		<!-- 显示需要替换的出发地 -->
		<!-- Modal 6 (Long Modal) -->
		<div class="modal fade" id="modal-6">
			<div class="modal-dialog">
				<div class="modal-content">
					<form action="${ctx!}/admin/attraction/replaceAttraction.do" method="post">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="modal-body"></div>
						<div id="regionid"></div>
						<div class="modal-footer">
							<input type="hidden" id="tourlineId" class="tourlineId" />
							<button type="button" class="btn btn-info" onclick="submitYouFrom()">提交</button>
							<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			$(document).ready(function(){
				<#if noticeMessage??>
				alert("${noticeMessage}");
				</#if>
			});
		</script>
		<!-- Imported styles on this page -->
		<link rel="stylesheet" href="${ctx!}/assets/js/datatables/dataTables.bootstrap.css" />
		<link rel="stylesheet" href="${ctx!}/assets/js/select2/select2.css" />
		<link rel="stylesheet" href="${ctx!}/assets/js/select2/select2-bootstrap.css" />
		<link rel="stylesheet" href="${ctx!}/assets/js/multiselect/css/multi-select.css" />
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
		<script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
		<script src="${ctx!}/assets/js/select2/select2.min.js"></script>

		<!-- JavaScripts initializations and stuff -->
		<script src="${ctx!}/assets/js/xenon-custom.js"></script>
	</body>
</html>