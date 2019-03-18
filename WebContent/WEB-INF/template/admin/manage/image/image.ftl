<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>图库管理</title>
    <!--<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">-->
	
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">

	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript">
		//分页
		function goPage(pageNow){
			window.location.href="${ctx!}/admin/image/list.do?pageNow="+pageNow+"&usetype=${usetype!}";
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
					<h1 class="title">图库管理</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="#">资源管理</a>
								</li>
							<li class="active">
						
										<strong>图库管理</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<script type="text/javascript">
			// Sample Javascript code for this page
			jQuery(document).ready(function($)
			{
				// Sample Select all images
				$("#select-all").on('change', function(ev)
				{
					var is_checked = $(this).is(':checked');
					
					$(".album-image input[type='checkbox']").prop('checked', is_checked).trigger('change');
				});
				
				// Edit Modal
				$('.gallery-env a[data-action="edit"]').on('click', function(ev)
				{
				//	$.ajax({
				//	   type: "POST",
				//	   url: "${ctx!}/image/update.do",
			    //       data:"id="+"",
				//	   success: function(){
				//			}
				//		});
					
					ev.preventDefault();
					$("#gallery-image-modal").modal('show');
				//	ev.preventDefault();
				//	$("#field-1").val($(this).);
				//	$("#gallery-image-modal").modal('show');
				});
				
				// Delete Modal
				$('.gallery-env a[data-action="trash"]').on('click', function(ev)
				{
					ev.preventDefault();
					$("#gallery-image-delete-modal").modal('show');
				});
				
				
				// Sortable
				
				$('.gallery-env a[data-action="sort"]').on('click', function(ev)
				{
					ev.preventDefault();
					
					var is_sortable = $(".album-images").sortable('instance');
					
					if( ! is_sortable)
					{
						$(".gallery-env .album-images").sortable({
							items: '> div'
						});
						
						$(".album-sorting-info").stop().slideDown(300);
					}
					else
					{
						$(".album-images").sortable('destroy');
						$(".album-sorting-info").stop().slideUp(300);
					}
				});
			});
			</script>
			
			<section class="gallery-env">
			
				<div class="row">
				
					<!-- Gallery Album Optipns and Images -->
					<div class="col-sm-9 gallery-right">
						
						<!-- Album Header -->
						<div class="album-header">
							<h2></h2>
							
							<ul class="album-options list-unstyled list-inline">
							    <li>
									<input type="checkbox" class="cbr" id="select-all" />
									<label for="select-all">全选</label>
								</li>
								<li>
									<a href="${ctx!}/admin/image/add.do">
										<i class="fa-upload"></i>
										添加图片
									</a>
								</li>
								<li>
									<a href="#" data-action="sort">
										<i class="fa-arrows"></i>
										排序
									</a>
								</li>
								<li>
									<a onclick="window.location.href='javascript:myfrom.submit()'">
										<i class="fa fa-reddit"></i>
										批量水印处理
									</a>
								</li>
							</ul>
						</div>
						
						<!-- Sorting Information -->
						<div class="album-sorting-info">
							<div class="album-sorting-info-inner clearfix">
								<a href="#" class="btn btn-secondary btn-xs btn-single btn-icon btn-icon-standalone pull-right" data-action="sort">
									<i class="fa-save"></i>
									<span>Save Current Order</span>
								</a>
								
								<i class="fa-arrows-alt"></i>
								Drag images to sort them
							</div>
						</div>
						
						<!-- Album Images -->
						<div class="album-images row">
							<!-- Album Image -->
							<form role="forl" action="${ctx!}/admin/image/addWatermark.do" id="myfrom" method="post">
							<#list allImages as image>
								<div class="col-md-3 col-sm-4 col-xs-6">
									<div class="album-image">
										<a href="${ctx!}/admin/image/update.do?id=${image.id}" class="thumb" >
											<img src="${ctx!}${(image.url)!}" class="img-responsive" />
										</a>
										
										<a href="#" class="name">
											<span>${(image.title)!}</span><br>
											<em>使用次数（${(image.imageUseCount)!}）</em><br/>
											<em>${(image.createtime)!}</em>
										</a>
										
										<div class="image-options">
											<a href="${ctx!}/admin/image/update.do?id=${image.id}" ><i class="fa-pencil"></i></a>
											<a href="${ctx!}/admin/image/delete.do?id=${image.id}" onClick="return confirm('确定要删除?');"><i class="fa-trash"></i></a>
										</div>
										
										<div class="image-checkbox">
											<input type="checkbox" name = "imageids" value="${(image.id)!}" class="cbr" />
										</div>
									</div>
								</div>
							</#list>
						</div>
						<div>
							<#include "/admin/include/pagination.ftl"/>
						</div>
						</div>
						<!-- Gallery Sidebar -->
						<div class="col-sm-3 gallery-left">
							<div class="gallery-sidebar">			
								<ul class="list-unstyled">
									<li <#if usetype?? && usetype="all">class="active" </#if>>
										<a href="${ctx!}/admin/image/list.do?usetype=all">
											<#if usetype?? && usetype="all">
											<i class="fa-folder-open-o"></i>
											<#else>
											<i class="fa-folder-o"></i>
											</#if>
											<span>所有图片</span>
										</a>
									</li>
									<li <#if usetype?? && usetype="atractions">class="active" </#if>>
										<a href="${ctx!}/admin/image/list.do?usetype=atractions">
											<#if usetype?? && usetype="atractions">
											<i class="fa-folder-open-o"></i>
											<#else>
											<i class="fa-folder-o"></i>
											</#if>
											<span>景点图片</span>
										</a>
									</li>
									<li <#if usetype?? && usetype="tourline">class="active" </#if>>
										<a href="${ctx!}/admin/image/list.do?usetype=tourline">
											<#if usetype?? && usetype="tourline">
											<i class="fa-folder-open-o"></i>
											<#else>
											<i class="fa-folder-o"></i>
											</#if>
											<span>线路图片</span>
										</a>
									</li>
									<li <#if usetype?? && usetype="itinerary">class="active" </#if>>
										<a href="${ctx!}/admin/image/list.do?usetype=itinerary">
											<#if usetype?? && usetype="itinerary">
											<i class="fa-folder-open-o"></i>
											<#else>
											<i class="fa-folder-o"></i>
											</#if>
											<span>行程图片</span>
										</a>
									</li>
									<li <#if usetype?? && usetype="visa">class="active" </#if>>
										<a href="${ctx!}/admin/image/list.do?usetype=visa">
											<#if usetype?? && usetype="visa">
											<i class="fa-folder-open-o"></i>
											<#else>
											<i class="fa-folder-o"></i>
											</#if>
											<span>签证图片</span>
										</a>
									</li>
									<li <#if usetype?? && usetype="slide">class="active" </#if>>
										<a href="${ctx!}/admin/image/list.do?usetype=slide">
											<#if usetype?? && usetype="slide">
											<i class="fa-folder-open-o"></i>
											<#else>
											<i class="fa-folder-o"></i>
											</#if>
											<span>幻灯片图片</span>
										</a>
									</li>
									<li <#if usetype?? && usetype="page">class="active" </#if>>
										<a href="${ctx!}/admin/image/list.do?usetype=page">
											<#if usetype?? && usetype="page">
											<i class="fa-folder-open-o"></i>
											<#else>
											<i class="fa-folder-o"></i>
											</#if>
											<span>单页面图片</span>
										</a>
									</li>
									<li <#if usetype?? && usetype="other">class="active" </#if>>
										<a href="${ctx!}/admin/image/list.do?usetype=other">
											<#if usetype?? && usetype="other">
											<i class="fa-folder-open-o"></i>
											<#else>
											<i class="fa-folder-o"></i>
											</#if>
											<span>其他图片</span>
										</a>
									</li>
								</ul>
							</div>
						</div>
						<!-- Gallery Sidebar -->
					</div>
			</section>
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<footer class="main-footer sticky footer-type-1">
				<div class="footer-inner">
					<!-- Add your copyright text here -->
					<div class="footer-text">
					西安淘游网络科技有限公司
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
	<!-- Bottom Scripts -->
	<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
	<script src="${ctx!}/assets/js/TweenMax.min.js"></script>
	<script src="${ctx!}/assets/js/resizeable.js"></script>
	<script src="${ctx!}/assets/js/joinable.js"></script>
	<script src="${ctx!}/assets/js/xenon-api.js"></script>
	<script src="${ctx!}/assets/js/xenon-toggles.js"></script>


	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/jquery-ui/jquery-ui.min.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>