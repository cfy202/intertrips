<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>Xenon - Extra</title>
    <!--<link rel="stylesheet" href="https://fonts.useso.com/css?family=Arimo:400,700,400italic">-->
	
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
					<h1 class="title">Gallery</h1>
					<p class="description">Gallery albums and images list</p>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="dashboard-1.html"><i class="fa-home"></i>Home</a>
						</li>
								<li>
						
										<a href="extra-gallery.html">Extra</a>
								</li>
							<li class="active">
						
										<strong>Gallery</strong>
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
					ev.preventDefault();
					$("#gallery-image-modal").modal('show');
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
							<h2>General</h2>
							
							<ul class="album-options list-unstyled list-inline">
								<li>
									<input type="checkbox" class="cbr" id="select-all" />
									<label for="select-all">Select all</label>
								</li>
								<li>
									<a href="#">
										<i class="fa-upload"></i>
										Add Images
									</a>
								</li>
								<li>
									<a href="#" data-action="sort">
										<i class="fa-arrows"></i>
										Re-order
									</a>
								</li>
								<li>
									<a href="#" data-action="edit">
										<i class="fa-edit"></i>
										Edit
									</a>
								</li>
								<li>
									<a href="#" data-action="trash">
										<i class="fa-trash"></i>
										Trash
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
							<div class="col-md-3 col-sm-4 col-xs-6">
								<div class="album-image">
									<a href="#" class="thumb" data-action="edit">
										<img src="${ctx!}/assets/images/album-img-1.png" class="img-responsive" />
									</a>
									
									<a href="#" class="name">
										<span>IMG_007.jpg</span>
										<em>28 September 2014</em>
									</a>
									
									<div class="image-options">
										<a href="#" data-action="edit"><i class="fa-pencil"></i></a>
										<a href="#" data-action="trash"><i class="fa-trash"></i></a>
									</div>
									
									<div class="image-checkbox">
										<input type="checkbox" class="cbr" />
									</div>
								</div>
							</div>
							
							<!-- Album Image -->
							<div class="col-md-3 col-sm-4 col-xs-6">
								<div class="album-image">
									<a href="#" class="thumb" data-action="edit">
										<img src="${ctx!}/assets/images/album-img-2.png" class="img-responsive" />
									</a>
									
									<a href="#" class="name">
										<span>IMG_008.jpg</span>
										<em>20 September 2014</em>
									</a>
									
									<div class="image-options">
										<a href="#" data-action="edit"><i class="fa-pencil"></i></a>
										<a href="#" data-action="trash"><i class="fa-trash"></i></a>
									</div>
									
									<div class="image-checkbox">
										<input type="checkbox" class="cbr" />
									</div>
								</div>
							</div>
							
							<!-- Album Image -->
							<div class="col-md-3 col-sm-4 col-xs-6">
								<div class="album-image">
									<a href="#" class="thumb" data-action="edit">
										<img src="${ctx!}/assets/images/album-img-3.png" class="img-responsive" />
									</a>
									
									<a href="#" class="name">
										<span>IMG_060.jpg</span>
										<em>03 September 2014</em>
									</a>
									
									<div class="image-options">
										<a href="#" data-action="edit"><i class="fa-pencil"></i></a>
										<a href="#" data-action="trash"><i class="fa-trash"></i></a>
									</div>
									
									<div class="image-checkbox">
										<input type="checkbox" class="cbr" />
									</div>
								</div>
							</div>
							
							<!-- Album Image -->
							<div class="col-md-3 col-sm-4 col-xs-6">
								<div class="album-image">
									<a href="#" class="thumb" data-action="edit">
										<img src="${ctx!}/assets/images/album-img-4.png" class="img-responsive" />
									</a>
									
									<a href="#" class="name">
										<span>IMG_1008.jpg</span>
										<em>23 August 2014</em>
									</a>
									
									<div class="image-options">
										<a href="#" data-action="edit"><i class="fa-pencil"></i></a>
										<a href="#" data-action="trash"><i class="fa-trash"></i></a>
									</div>
									
									<div class="image-checkbox">
										<input type="checkbox" class="cbr" />
									</div>
								</div>
							</div>
							
							<!-- Album Image -->
							<div class="col-md-3 col-sm-4 col-xs-6">
								<div class="album-image">
									<a href="#" class="thumb" data-action="edit">
										<img src="${ctx!}/assets/images/album-img-5.png" class="img-responsive" />
									</a>
									
									<a href="#" class="name">
										<span>IMG_023.jpg</span>
										<em>30 July 2014</em>
									</a>
									
									<div class="image-options">
										<a href="#" data-action="edit"><i class="fa-pencil"></i></a>
										<a href="#" data-action="trash"><i class="fa-trash"></i></a>
									</div>
									
									<div class="image-checkbox">
										<input type="checkbox" class="cbr" />
									</div>
								</div>
							</div>
							
							<!-- Album Image -->
							<div class="col-md-3 col-sm-4 col-xs-6">
								<div class="album-image">
									<a href="#" class="thumb" data-action="edit">
										<img src="${ctx!}/assets/images/album-img-6.png" class="img-responsive" />
									</a>
									
									<a href="#" class="name">
										<span>IMG_012.jpg</span>
										<em>16 July 2014</em>
									</a>
									
									<div class="image-options">
										<a href="#" data-action="edit"><i class="fa-pencil"></i></a>
										<a href="#" data-action="trash"><i class="fa-trash"></i></a>
									</div>
									
									<div class="image-checkbox">
										<input type="checkbox" class="cbr" />
									</div>
								</div>
							</div>
							
							<!-- Album Image -->
							<div class="col-md-3 col-sm-4 col-xs-6">
								<div class="album-image">
									<a href="#" class="thumb" data-action="edit">
										<img src="${ctx!}/assets/images/album-img-7.png" class="img-responsive" />
									</a>
									
									<a href="#" class="name">
										<span>IMG_207.jpg</span>
										<em>25 June 2014</em>
									</a>
									
									<div class="image-options">
										<a href="#" data-action="edit"><i class="fa-pencil"></i></a>
										<a href="#" data-action="trash"><i class="fa-trash"></i></a>
									</div>
									
									<div class="image-checkbox">
										<input type="checkbox" class="cbr" />
									</div>
								</div>
							</div>
							
							<!-- Album Image -->
							<div class="col-md-3 col-sm-4 col-xs-6">
								<div class="album-image">
									<a href="#" class="thumb" data-action="edit">
										<img src="${ctx!}/assets/images/album-img-8.png" class="img-responsive" />
									</a>
									
									<a href="#" class="name">
										<span>IMG_002.jpg</span>
										<em>24 August 2013</em>
									</a>
									
									<div class="image-options">
										<a href="#" data-action="edit"><i class="fa-pencil"></i></a>
										<a href="#" data-action="trash"><i class="fa-trash"></i></a>
									</div>
									
									<div class="image-checkbox">
										<input type="checkbox" class="cbr" />
									</div>
								</div>
							</div>
							
						</div>
						
						
						<button class="btn btn-white btn-block">
							<i class="fa-bars"></i>
							Load More Images
						</button>
						
					</div>
					
					<!-- Gallery Sidebar -->
					<div class="col-sm-3 gallery-left">
						
						<div class="gallery-sidebar">			
							
							<a href="#" class="btn btn-block btn-secondary btn-icon btn-icon-standalone btn-icon-standalone-right">
								<i class="linecons-photo"></i>
								<span>Create Album</span>
							</a>
							
							<ul class="list-unstyled">
								<li class="active">
									<a href="#">
										<i class="fa-folder-open-o"></i>
										<span>General</span>
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa-folder-o"></i>
										<span>Office</span>
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa-folder-o"></i>
										<span>Las Vegas</span>
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa-folder-o"></i>
										<span>Thailand</span>
									</a>
								</li>
								<li>
									<a href="#">
										<i class="fa-folder-o"></i>
										<span>Nature</span>
									</a>
								</li>
							</ul>
							
						</div>
						
					</div>
				
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
						&copy; 2014 
						<strong>Xenon</strong> 
						More Templates <a href="https://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="https://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a>
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
		
			
		<!-- start: Chat Section -->
		<div id="chat" class="fixed">
			
			<div class="chat-inner">
			
				
				<h2 class="chat-header">
					<a href="#" class="chat-close" data-toggle="chat">
						<i class="fa-plus-circle rotate-45deg"></i>
					</a>
					
					Chat
					<span class="badge badge-success is-hidden">0</span>
				</h2>
				
				<script type="text/javascript">
				// Here is just a sample how to open chat conversation box
				jQuery(document).ready(function($)
				{
					var $chat_conversation = $(".chat-conversation");
					
					$(".chat-group a").on('click', function(ev)
					{
						ev.preventDefault();
						
						$chat_conversation.toggleClass('is-open');
						
						$(".chat-conversation textarea").trigger('autosize.resize').focus();
					});
					
					$(".conversation-close").on('click', function(ev)
					{
						ev.preventDefault();
						$chat_conversation.removeClass('is-open');
					});
				});
				</script>
				
				
				<div class="chat-group">
					<strong>Favorites</strong>
					
					<a href="#"><span class="user-status is-online"></span> <em>Catherine J. Watkins</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Nicholas R. Walker</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Susan J. Best</em></a>
					<a href="#"><span class="user-status is-idle"></span> <em>Fernando G. Olson</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Brandon S. Young</em></a>
				</div>
				
				
				<div class="chat-group">
					<strong>Work</strong>
					
					<a href="#"><span class="user-status is-busy"></span> <em>Rodrigo E. Lozano</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Robert J. Garcia</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Daniel A. Pena</em></a>
				</div>
				
				
				<div class="chat-group">
					<strong>Other</strong>
					
					<a href="#"><span class="user-status is-online"></span> <em>Dennis E. Johnson</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Stuart A. Shire</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Janet I. Matas</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Mindy A. Smith</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Herman S. Foltz</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Gregory E. Robie</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Nellie T. Foreman</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>William R. Miller</em></a>
					<a href="#"><span class="user-status is-idle"></span> <em>Vivian J. Hall</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Melinda A. Anderson</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Gary M. Mooneyham</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Robert C. Medina</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Dylan C. Bernal</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Marc P. Sanborn</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Kenneth M. Rochester</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Rachael D. Carpenter</em></a>
				</div>
			
			</div>
			
			<!-- conversation template -->
			<div class="chat-conversation">
				
				<div class="conversation-header">
					<a href="#" class="conversation-close">
						&times;
					</a>
					
					<span class="user-status is-online"></span>
					<span class="display-name">Arlind Nushi</span> 
					<small>Online</small>
				</div>
				
				<ul class="conversation-body">	
					<li>
						<span class="user">Arlind Nushi</span>
						<span class="time">09:00</span>
						<p>Are you here?</p>
					</li>
					<li class="odd">
						<span class="user">Brandon S. Young</span>
						<span class="time">09:25</span>
						<p>This message is pre-queued.</p>
					</li>
					<li>
						<span class="user">Brandon S. Young</span>
						<span class="time">09:26</span>
						<p>Whohoo!</p>
					</li>
					<li class="odd">
						<span class="user">Arlind Nushi</span>
						<span class="time">09:27</span>
						<p>Do you like it?</p>
					</li>
				</ul>
				
				<div class="chat-textarea">
					<textarea class="form-control autogrow" placeholder="Type your message"></textarea>
				</div>
				
			</div>
			
		</div>
		<!-- end: Chat Section -->
		
		
	</div>
	
	
	<!-- Gallery Modal Image -->
	<div class="modal fade" id="gallery-image-modal">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-gallery-image">
					<img src="assets/images/album-image-full.jpg" class="img-responsive" />
				</div>
				
				<div class="modal-body">
					
					<div class="row">
						<div class="col-md-12">
							
							<div class="form-group">
								<label for="field-1" class="control-label">Title</label>
								
								<input type="text" class="form-control" id="field-1" placeholder="Enter image title">
							</div>	
							
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							
							<div class="form-group">
								<label for="field-1" class="control-label">Description</label>
								
								<textarea class="form-control autogrow" id="field-2" placeholder="Enter image description" style="min-height: 80px;"></textarea>
							</div>	
							
						</div>
					</div>
					
				</div>
				
				<div class="modal-footer modal-gallery-top-controls">
					<button type="button" class="btn btn-xs btn-white" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-xs btn-info">Crop Image</button>
					<button type="button" class="btn btn-xs btn-secondary">Save</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!-- Gallery Delete Image (Confirm)-->
	<div class="modal fade" id="gallery-image-delete-modal" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				
				<div class="modal-header">
					<h4 class="modal-title">Confirm Image Deletion</h4>
				</div>
				
				<div class="modal-body">
				
					Do you really want to delete this image?
					
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-danger">Delete</button>
				</div>
			</div>
		</div>
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