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
					<h1 class="title">线路封面图片管理</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/tourline/list.do">线路管理</a>
								</li>
							<li class="active">
						
										<strong>线路封面图片管理</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<script type="text/javascript">
			// Sample Javascript code for this page
			jQuery(document).ready(function($)
			{
				
				
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
			
			function updatePic (){
			    var imageurl ="";
			    var id = '${(tourline.productid)!}';
			    var tourlineId ='${(tourline.id)!}'
			   $('.album-image img').each(function(){
		                var url=$(this).attr("uur");
		                imageurl +=","+url;
		                });
		        $("#imageurl").val(imageurl.substring(1)); 
		        $("#productId").val(id);
		        $("#tourlineId").val(tourlineId);;
			    $("#myfrom").submit();
			    
			}
			</script>
			
			<section class="gallery-env">
			
				<div class="row">
				
					<!-- Gallery Album Optipns and Images -->
					<div class="col-sm-12 gallery-left">
						
						<!-- Album Header -->
						<div class="album-header">
							<h2></h2>
							
							<ul class="album-options list-unstyled list-inline">
							    <li>
									第一个为线路封面图片
								</li>
								<li>
									<a href="#" data-action="sort">
										<i class="fa-arrows"></i>
										排序
									</a>
								</li>
								
							</ul>
						</div>
						
						<!-- Sorting Information -->
						<div class="album-sorting-info">
							<div class="album-sorting-info-inner clearfix">
								<a href="javascript:;" onclick="updatePic()" class="btn btn-secondary btn-xs btn-single btn-icon btn-icon-standalone pull-right" data-action="sort">
									<i class="fa-save"></i>
									<span>保存修改</span>
								</a>
								
								<i class="fa-arrows-alt"></i>
								设置线路封面图片
							</div>
						</div>
						
						<!-- Album Images -->
						<div class="album-images row">
							<!-- Album Image -->
							
							<#if tourline.productProductid.imageurl?has_content>
											 <#list tourline.productProductid.imageurl?split(",") as urls>
											   <div class="col-md-3 col-sm-4 col-xs-6">
													<div class="album-image">
														<a href="" class="thumb" >
															<img src="${ctx!}${urls}" uur = "${urls}" width="300" height="200"  />
														</a>
													</div>
												</div>
											</#list>
							</#if>
							
						</div>
						<div>
						
						</div>
						</div>
						<form role="forl" action="${ctx!}/admin/tourline/updatePc.do" id="myfrom" method="post">
						 <input type="hidden" name ="imageurl" id="imageurl" value="">
						 <input type="hidden" name ="id" id="productId" value="">
						 <input type="hidden" name ="tourlineId" id="tourlineId" value="">
						</from>
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