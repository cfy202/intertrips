<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
    <#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>Xenon - Skin Generator</title>

	<link rel="stylesheet" href="https://fonts.useso.com/css?family=Arimo:400,700,400italic">
	<link rel="stylesheet" href="${ctx!}assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}assets/css/custom.css">

	<script src="assets/js/jquery-1.11.1.min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	
	
</head>
<body class="page-body">

	<div class="settings-pane">
			
		<a href="#" data-toggle="settings-pane" data-animate="true">
			&times;
		</a>
		
		<div class="settings-pane-inner">
			
			<div class="row">
				
				<div class="col-md-4">
					
					<div class="user-info">
						
						<div class="user-image">
							<a href="extra-profile.html">
								<img src="assets/images/user-2.png" class="img-responsive img-circle" />
							</a>
						</div>
						
						<div class="user-details">
							
							<h3>
								<a href="extra-profile.html">John Smith</a>
								
								<!-- Available statuses: is-online, is-idle, is-busy and is-offline -->
								<span class="user-status is-online"></span>
							</h3>
							
							<p class="user-title">Web Developer</p>
							
							<div class="user-links">
								<a href="extra-profile.html" class="btn btn-primary">Edit Profile</a>
								<a href="extra-profile.html" class="btn btn-success">Upgrade</a>
							</div>
							
						</div>
						
					</div>
					
				</div>
				
				<div class="col-md-8 link-blocks-env">
					
					<div class="links-block left-sep">
						<h4>
							<span>Notifications</span>
						</h4>
						
						<ul class="list-unstyled">
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk1" />
								<label for="sp-chk1">Messages</label>
							</li>
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk2" />
								<label for="sp-chk2">Events</label>
							</li>
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk3" />
								<label for="sp-chk3">Updates</label>
							</li>
							<li>
								<input type="checkbox" class="cbr cbr-primary" checked="checked" id="sp-chk4" />
								<label for="sp-chk4">Server Uptime</label>
							</li>
						</ul>
					</div>
					
					<div class="links-block left-sep">
						<h4>
							<a href="#">
								<span>Help Desk</span>
							</a>
						</h4>
						
						<ul class="list-unstyled">
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Support Center
								</a>
							</li>
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Submit a Ticket
								</a>
							</li>
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Domains Protocol
								</a>
							</li>
							<li>
								<a href="#">
									<i class="fa-angle-right"></i>
									Terms of Service
								</a>
							</li>
						</ul>
					</div>
					
				</div>
				
			</div>
		
		</div>
		
	</div>
	
	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
			
		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<div class="sidebar-menu toggle-others fixed">
			
			<div class="sidebar-menu-inner">	
				
				<header class="logo-env">
					
					<!-- logo -->
					<div class="logo">
						<a href="dashboard-1.html" class="logo-expanded">
							<img src="assets/images/logo@2x.png" width="80" alt="" />
						</a>
						
						<a href="dashboard-1.html" class="logo-collapsed">
							<img src="assets/images/logo-collapsed@2x.png" width="40" alt="" />
						</a>
					</div>
					
					<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
					<div class="mobile-menu-toggle visible-xs">
						<a href="#" data-toggle="user-info-menu">
							<i class="fa-bell-o"></i>
							<span class="badge badge-success">7</span>
						</a>
						
						<a href="#" data-toggle="mobile-menu">
							<i class="fa-bars"></i>
						</a>
					</div>
					
					<!-- This will open the popup with user profile settings, you can use for any purpose, just be creative -->
					<div class="settings-icon">
						<a href="#" data-toggle="settings-pane" data-animate="true">
							<i class="linecons-cog"></i>
						</a>
					</div>
					
								
				</header>
						
				
						
				<ul id="main-menu" class="main-menu">
					<!-- add class "multiple-expanded" to allow multiple submenus to open -->
					<!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
					<li class="opened active">
						<a href="dashboard-1.html">
							<i class="linecons-cog"></i>
							<span class="title">Dashboard</span>
						</a>
						<ul>
							<li>
								<a href="dashboard-1.html">
									<span class="title">Dashboard 1</span>
								</a>
							</li>
							<li>
								<a href="dashboard-2.html">
									<span class="title">Dashboard 2</span>
								</a>
							</li>
							<li>
								<a href="dashboard-3.html">
									<span class="title">Dashboard 3</span>
								</a>
							</li>
							<li>
								<a href="dashboard-4.html">
									<span class="title">Dashboard 4</span>
								</a>
							</li>
							<li class="active">
								<a href="skin-generator.html">
									<span class="title">Skin Generator</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="layout-variants.html">
							<i class="linecons-desktop"></i>
							<span class="title">Layouts</span>
						</a>
						<ul>
							<li>
								<a href="layout-variants.html">
									<span class="title">Layout Variants &amp; API</span>
								</a>
							</li>
							<li>
								<a href="layout-collapsed-sidebar.html">
									<span class="title">Collapsed Sidebar</span>
								</a>
							</li>
							<li>
								<a href="layout-static-sidebar.html">
									<span class="title">Static Sidebar</span>
								</a>
							</li>
							<li>
								<a href="layout-horizontal-menu.html">
									<span class="title">Horizontal Menu</span>
								</a>
							</li>
							<li>
								<a href="layout-horizontal-plus-sidebar.html">
									<span class="title">Horizontal &amp; Sidebar Menu</span>
								</a>
							</li>
							<li>
								<a href="layout-horizontal-menu-click-to-open-subs.html">
									<span class="title">Horizontal Open On Click</span>
								</a>
							</li>
							<li>
								<a href="layout-horizontal-menu-min.html">
									<span class="title">Horizontal Menu Minimal</span>
								</a>
							</li>
							<li>
								<a href="layout-right-sidebar.html">
									<span class="title">Right Sidebar</span>
								</a>
							</li>
							<li>
								<a href="layout-chat-open.html">
									<span class="title">Chat Open</span>
								</a>
							</li>
							<li>
								<a href="layout-horizontal-sidebar-menu-collapsed-right.html">
									<span class="title">Both Menus &amp; Collapsed</span>
								</a>
							</li>
							<li>
								<a href="layout-boxed.html">
									<span class="title">Boxed Layout</span>
								</a>
							</li>
							<li>
								<a href="layout-boxed-horizontal-menu.html">
									<span class="title">Boxed &amp; Horizontal Menu</span>
								</a>
							</li>
							<li>
								<a href="https://www.cssmoban.com">
									<span class="title">weidea.net</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="ui-panels.html">
							<i class="linecons-note"></i>
							<span class="title">UI Elements</span>
						</a>
						<ul>
							<li>
								<a href="ui-panels.html">
									<span class="title">Panels</span>
								</a>
							</li>
							<li>
								<a href="ui-buttons.html">
									<span class="title">Buttons</span>
								</a>
							</li>
							<li>
								<a href="ui-tabs-accordions.html">
									<span class="title">Tabs &amp; Accordions</span>
								</a>
							</li>
							<li>
								<a href="ui-modals.html">
									<span class="title">Modals</span>
								</a>
							</li>
							<li>
								<a href="ui-breadcrumbs.html">
									<span class="title">Breadcrumbs</span>
								</a>
							</li>
							<li>
								<a href="ui-blockquotes.html">
									<span class="title">Blockquotes</span>
								</a>
							</li>
							<li>
								<a href="ui-progressbars.html">
									<span class="title">Progress Bars</span>
								</a>
							</li>
							<li>
								<a href="ui-navbars.html">
									<span class="title">Navbars</span>
								</a>
							</li>
							<li>
								<a href="ui-alerts.html">
									<span class="title">Alerts</span>
								</a>
							</li>
							<li>
								<a href="ui-pagination.html">
									<span class="title">Pagination</span>
								</a>
							</li>
							<li>
								<a href="ui-typography.html">
									<span class="title">Typography</span>
								</a>
							</li>
							<li>
								<a href="ui-other-elements.html">
									<span class="title">Other Elements</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="ui-widgets.html">
							<i class="linecons-star"></i>
							<span class="title">Widgets</span>
						</a>
					</li>
					<li>
						<a href="mailbox-main.html">
							<i class="linecons-mail"></i>
							<span class="title">Mailbox</span>
							<span class="label label-success pull-right">5</span>
						</a>
						<ul>
							<li>
								<a href="mailbox-main.html">
									<span class="title">Inbox</span>
								</a>
							</li>
							<li>
								<a href="mailbox-compose.html">
									<span class="title">Compose Message</span>
								</a>
							</li>
							<li>
								<a href="mailbox-message.html">
									<span class="title">View Message</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="tables-basic.html">
							<i class="linecons-database"></i>
							<span class="title">Tables</span>
						</a>
						<ul>
							<li>
								<a href="tables-basic.html">
									<span class="title">Basic Tables</span>
								</a>
							</li>
							<li>
								<a href="tables-responsive.html">
									<span class="title">Responsive Table</span>
								</a>
							</li>
							<li>
								<a href="tables-datatables.html">
									<span class="title">Data Tables</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="forms-native.html">
							<i class="linecons-params"></i>
							<span class="title">Forms</span>
						</a>
						<ul>
							<li>
								<a href="forms-native.html">
									<span class="title">Native Elements</span>
								</a>
							</li>
							<li>
								<a href="forms-advanced.html">
									<span class="title">Advanced Plugins</span>
								</a>
							</li>
							<li>
								<a href="forms-wizard.html">
									<span class="title">Form Wizard</span>
								</a>
							</li>
							<li>
								<a href="forms-validation.html">
									<span class="title">Form Validation</span>
								</a>
							</li>
							<li>
								<a href="forms-input-masks.html">
									<span class="title">Input Masks</span>
								</a>
							</li>
							<li>
								<a href="forms-file-upload.html">
									<span class="title">File Upload</span>
								</a>
							</li>
							<li>
								<a href="forms-editors.html">
									<span class="title">Editors</span>
								</a>
							</li>
							<li>
								<a href="forms-sliders.html">
									<span class="title">Sliders</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="extra-gallery.html">
							<i class="linecons-beaker"></i>
							<span class="title">Extra</span>
							<span class="label label-purple pull-right hidden-collapsed">New Items</span>
						</a>
						<ul>
							<li>
								<a href="extra-icons-fontawesome.html">
									<span class="title">Icons</span>
									<span class="label label-warning pull-right">4</span>
								</a>
								<ul>
									<li>
										<a href="extra-icons-fontawesome.html">
											<span class="title">Font Awesome</span>
										</a>
									</li>
									<li>
										<a href="extra-icons-linecons.html">
											<span class="title">Linecons</span>
										</a>
									</li>
									<li>
										<a href="extra-icons-elusive.html">
											<span class="title">Elusive</span>
										</a>
									</li>
									<li>
										<a href="extra-icons-meteocons.html">
											<span class="title">Meteocons</span>
										</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="extra-maps-google.html">
									<span class="title">Maps</span>
								</a>
								<ul>
									<li>
										<a href="extra-maps-google.html">
											<span class="title">Google Maps</span>
										</a>
									</li>
									<li>
										<a href="extra-maps-advanced.html">
											<span class="title">Advanced Map</span>
										</a>
									</li>
									<li>
										<a href="extra-maps-vector.html">
											<span class="title">Vector Map</span>
										</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="extra-gallery.html">
									<span class="title">Gallery</span>
								</a>
							</li>
							<li>
								<a href="extra-calendar.html">
									<span class="title">Calendar</span>
								</a>
							</li>
							<li>
								<a href="extra-profile.html">
									<span class="title">Profile</span>
								</a>
							</li>
							<li>
								<a href="extra-login.html">
									<span class="title">Login</span>
								</a>
							</li>
							<li>
								<a href="extra-lockscreen.html">
									<span class="title">Lockscreen</span>
								</a>
							</li>
							<li>
								<a href="extra-login-light.html">
									<span class="title">Login Light</span>
								</a>
							</li>
							<li>
								<a href="extra-timeline.html">
									<span class="title">Timeline</span>
								</a>
							</li>
							<li>
								<a href="extra-timeline-center.html">
									<span class="title">Timeline Centerd</span>
								</a>
							</li>
							<li>
								<a href="extra-notes.html">
									<span class="title">Notes</span>
								</a>
							</li>
							<li>
								<a href="extra-image-crop.html">
									<span class="title">Image Crop</span>
								</a>
							</li>
							<li>
								<a href="extra-portlets.html">
									<span class="title">Portlets</span>
								</a>
							</li>
							<li>
								<a href="blank-sidebar.html">
									<span class="title">Blank Page</span>
								</a>
							</li>
							<li>
								<a href="extra-search.html">
									<span class="title">Search</span>
								</a>
							</li>
							<li>
								<a href="extra-invoice.html">
									<span class="title">Invoice</span>
								</a>
							</li>
							<li>
								<a href="extra-not-found.html">
									<span class="title">404 Page</span>
								</a>
							</li>
							<li>
								<a href="extra-tocify.html">
									<span class="title">Tocify</span>
								</a>
							</li>
							<li>
								<a href="extra-loader.html">
									<span class="title">Loading Progress</span>
								</a>
							</li>
							<li>
								<a href="extra-page-loading-ol.html">
									<span class="title">Page Loading Overlay</span>
								</a>
							</li>
							<li>
								<a href="extra-notifications.html">
									<span class="title">Notifications</span>
								</a>
							</li>
							<li>
								<a href="extra-nestable-lists.html">
									<span class="title">Nestable Lists</span>
								</a>
							</li>
							<li>
								<a href="extra-scrollable.html">
									<span class="title">Scrollable</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="charts-main.html">
							<i class="linecons-globe"></i>
							<span class="title">Charts</span>
						</a>
						<ul>
							<li>
								<a href="charts-main.html">
									<span class="title">Chart Variants</span>
								</a>
							</li>
							<li>
								<a href="charts-range.html">
									<span class="title">Range Selector</span>
								</a>
							</li>
							<li>
								<a href="charts-sparklines.html">
									<span class="title">Sparklines</span>
								</a>
							</li>
							<li>
								<a href="charts-map.html">
									<span class="title">Map Charts</span>
								</a>
							</li>
							<li>
								<a href="charts-gauges.html">
									<span class="title">Circular Gauges</span>
								</a>
							</li>
							<li>
								<a href="charts-bar-gauges.html">
									<span class="title">Bar Gauges</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">
							<i class="linecons-cloud"></i>
							<span class="title">Menu Levels</span>
						</a>
						<ul>
							<li>
								<a href="#">
									<i class="entypo-flow-line"></i>
									<span class="title">Menu Level 1.1</span>
								</a>
								<ul>
									<li>
										<a href="#">
											<i class="entypo-flow-parallel"></i>
											<span class="title">Menu Level 2.1</span>
										</a>
									</li>
									<li>
										<a href="#">
											<i class="entypo-flow-parallel"></i>
											<span class="title">Menu Level 2.2</span>
										</a>
										<ul>
											<li>
												<a href="#">
													<i class="entypo-flow-cascade"></i>
													<span class="title">Menu Level 3.1</span>
												</a>
											</li>
											<li>
												<a href="#">
													<i class="entypo-flow-cascade"></i>
													<span class="title">Menu Level 3.2</span>
												</a>
												<ul>
													<li>
														<a href="#">
															<i class="entypo-flow-branch"></i>
															<span class="title">Menu Level 4.1</span>
														</a>
													</li>
												</ul>
											</li>
										</ul>
									</li>
									<li>
										<a href="#">
											<i class="entypo-flow-parallel"></i>
											<span class="title">Menu Level 2.3</span>
										</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-line"></i>
									<span class="title">Menu Level 1.2</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-line"></i>
									<span class="title">Menu Level 1.3</span>
								</a>
							</li>
						</ul>
					</li>
				</ul>
						
			</div>
			
		</div>
		
		<div class="main-content">
					
			<!-- User Info, Notifications and Menu Bar -->
			<nav class="navbar user-info-navbar" role="navigation">
				
				<!-- Left links for user info navbar -->
				<ul class="user-info-menu left-links list-inline list-unstyled">
					
					<li class="hidden-sm hidden-xs">
						<a href="#" data-toggle="sidebar">
							<i class="fa-bars"></i>
						</a>
					</li>
					
					<li class="dropdown hover-line">
						<a href="#" data-toggle="dropdown">
							<i class="fa-envelope-o"></i>
							<span class="badge badge-green">15</span>
						</a>
							
						<ul class="dropdown-menu messages">
							<li>
									
								<ul class="dropdown-menu-list list-unstyled ps-scrollbar">
								
									<li class="active"><!-- "active" class means message is unread -->
										<a href="#">
											<span class="line">
												<strong>Luc Chartier</strong>
												<span class="light small">- yesterday</span>
											</span>
											
											<span class="line desc small">
												This ain’t our first item, it is the best of the rest.
											</span>
										</a>
									</li>
									
									<li class="active">
										<a href="#">
											<span class="line">
												<strong>Salma Nyberg</strong>
												<span class="light small">- 2 days ago</span>
											</span>
											
											<span class="line desc small">
												Oh he decisively impression attachment friendship so if everything. 
											</span>
										</a>
									</li>
									
									<li>
										<a href="#">
											<span class="line">
												Hayden Cartwright
												<span class="light small">- a week ago</span>
											</span>
											
											<span class="line desc small">
												Whose her enjoy chief new young. Felicity if ye required likewise so doubtful.
											</span>
										</a>
									</li>
									
									<li>
										<a href="#">
											<span class="line">
												Sandra Eberhardt
												<span class="light small">- 16 days ago</span>
											</span>
											
											<span class="line desc small">
												On so attention necessary at by provision otherwise existence direction.
											</span>
										</a>
									</li>
									
									<!-- Repeated -->
									
									<li class="active"><!-- "active" class means message is unread -->
										<a href="#">
											<span class="line">
												<strong>Luc Chartier</strong>
												<span class="light small">- yesterday</span>
											</span>
											
											<span class="line desc small">
												This ain’t our first item, it is the best of the rest.
											</span>
										</a>
									</li>
									
									<li class="active">
										<a href="#">
											<span class="line">
												<strong>Salma Nyberg</strong>
												<span class="light small">- 2 days ago</span>
											</span>
											
											<span class="line desc small">
												Oh he decisively impression attachment friendship so if everything. 
											</span>
										</a>
									</li>
									
									<li>
										<a href="#">
											<span class="line">
												Hayden Cartwright
												<span class="light small">- a week ago</span>
											</span>
											
											<span class="line desc small">
												Whose her enjoy chief new young. Felicity if ye required likewise so doubtful.
											</span>
										</a>
									</li>
									
									<li>
										<a href="#">
											<span class="line">
												Sandra Eberhardt
												<span class="light small">- 16 days ago</span>
											</span>
											
											<span class="line desc small">
												On so attention necessary at by provision otherwise existence direction.
											</span>
										</a>
									</li>
									
								</ul>
								
							</li>
							
							<li class="external">
								<a href="blank-sidebar.html">
									<span>All Messages</span>
									<i class="fa-link-ext"></i>
								</a>
							</li>
						</ul>
					</li>
					
					<li class="dropdown hover-line">
						<a href="#" data-toggle="dropdown">
							<i class="fa-bell-o"></i>
							<span class="badge badge-purple">7</span>
						</a>
							
						<ul class="dropdown-menu notifications">
							<li class="top">
								<p class="small">
									<a href="#" class="pull-right">Mark all Read</a>
									You have <strong>3</strong> new notifications.
								</p>
							</li>
							
							<li>
								<ul class="dropdown-menu-list list-unstyled ps-scrollbar">
									<li class="active notification-success">
										<a href="#">
											<i class="fa-user"></i>
											
											<span class="line">
												<strong>New user registered</strong>
											</span>
											
											<span class="line small time">
												30 seconds ago
											</span>
										</a>
									</li>
									
									<li class="active notification-secondary">
										<a href="#">
											<i class="fa-lock"></i>
											
											<span class="line">
												<strong>Privacy settings have been changed</strong>
											</span>
											
											<span class="line small time">
												3 hours ago
											</span>
										</a>
									</li>
									
									<li class="notification-primary">
										<a href="#">
											<i class="fa-thumbs-up"></i>
											
											<span class="line">
												<strong>Someone special liked this</strong>
											</span>
											
											<span class="line small time">
												2 minutes ago
											</span>
										</a>
									</li>
									
									<li class="notification-danger">
										<a href="#">
											<i class="fa-calendar"></i>
											
											<span class="line">
												John cancelled the event
											</span>
											
											<span class="line small time">
												9 hours ago
											</span>
										</a>
									</li>
									
									<li class="notification-info">
										<a href="#">
											<i class="fa-database"></i>
											
											<span class="line">
												The server is status is stable
											</span>
											
											<span class="line small time">
												yesterday at 10:30am
											</span>
										</a>
									</li>
									
									<li class="notification-warning">
										<a href="#">
											<i class="fa-envelope-o"></i>
											
											<span class="line">
												New comments waiting approval
											</span>
											
											<span class="line small time">
												last week
											</span>
										</a>
									</li>
								</ul>
							</li>
							
							<li class="external">
								<a href="#">
									<span>View all notifications</span>
									<i class="fa-link-ext"></i>
								</a>
							</li>
						</ul>
					</li>
					
				</ul>
				
				
				<!-- Right links for user info navbar -->
				<ul class="user-info-menu right-links list-inline list-unstyled">
					
					<li class="search-form"><!-- You can add "always-visible" to show make the search input visible -->
						
						<form method="get" action="extra-search.html">
							<input type="text" name="s" class="form-control search-field" placeholder="Type to search..." />
							
							<button type="submit" class="btn btn-link">
								<i class="linecons-search"></i>
							</button>
						</form>
						
					</li>
					
					<li class="dropdown user-profile">
						<a href="#" data-toggle="dropdown">
							<img src="assets/images/user-4.png" alt="user-image" class="img-circle img-inline userpic-32" width="28" />
							<span>
								Arlind Nushi
								<i class="fa-angle-down"></i>
							</span>
						</a>
						
						<ul class="dropdown-menu user-profile-menu list-unstyled">
							<li>
								<a href="#edit-profile">
									<i class="fa-edit"></i>
									New Post
								</a>
							</li>
							<li>
								<a href="#settings">
									<i class="fa-wrench"></i>
									Settings
								</a>
							</li>
							<li>
								<a href="#profile">
									<i class="fa-user"></i>
									Profile
								</a>
							</li>
							<li>
								<a href="#help">
									<i class="fa-info"></i>
									Help
								</a>
							</li>
							<li class="last">
								<a href="extra-lockscreen.html">
									<i class="fa-lock"></i>
									Logout
								</a>
							</li>
						</ul>
					</li>
					
					<li>
						<a href="#" data-toggle="chat">
							<i class="fa-comments-o"></i>
						</a>
					</li>
					
				</ul>
				
			</nav>
			<div class="page-title">
				
				<div class="title-env">
					<h1 class="title">Skin Selector</h1>
					<p class="description">Select sidebar skins from predefined color palettes</p>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="dashboard-1.html"><i class="fa-home"></i>Home</a>
						</li>
								<li>
						
										<a href="dashboard-1.html">Dashboard</a>
								</li>
							<li class="active">
						
										<strong>Skin Generator</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<script type="text/javascript">
			jQuery(document).ready(function($)
			{
				$("[data-skin]").each(function(i, el)
				{
					var $el = $(el),
						skin = $el.data('skin');
					
					$el.find('a').attr('data-set-skin', skin).attr('href', '#setSkin:' + skin);
				});
				
				$("[data-skin-horizontal]").each(function(i, el)
				{
					var $el = $(el),
						skin = $el.data('skin-horizontal');
					
					$el.find('a').attr('data-set-skin-horizontal', skin).attr('href', '#setHorizontalSkin:' + skin);
				});
				
				$('[data-set-skin]').on('click', function(ev)
				{
					ev.preventDefault();
					
					var skin = $(this).data('set-skin'),
						skin_name = skin ? (' skin-'+skin) : '';
					
					var body_classes = public_vars.$body.attr('class').replace(/skin-[a-z]+/i, '');
					
					public_vars.$body.attr('class', body_classes).addClass(skin_name);
					
					Cookies.set('current-skin', skin);
					
					if($(this).is('.is-login'))
					{
						window.open('extra-login.html');
					}
				});
				
				$('[data-set-skin-horizontal]').on('click', function(ev)
				{
					ev.preventDefault();
					
					var skin = $(this).data('set-skin-horizontal'),
						horizontal_skin_name = skin ? (' horizontal-menu-skin-'+skin) : '',
						user_info_navbar_skin_name = skin ? (' user-info-navbar-skin-'+skin) : '';
					
					var body_classes = public_vars.$body.attr('class').replace(/horizontal-menu-skin-[a-z]+/i, '').replace(/user-info-navbar-skin-[a-z]+/i, '');
					
					
					if($(this).is('.is-userinfo'))
					{
						Cookies.set('current-user-info-navbar-skin', skin);
						public_vars.$body.attr('class', body_classes).addClass(user_info_navbar_skin_name);
						
						$('[rel="go-top"]').click();
					}
					else
					{
						Cookies.set('current-horizontal-skin', skin);
						window.open('layout-horizontal-menu.html');
					}
				});
				
				$(".reset-skin").on('click', function(ev)
				{
					ev.preventDefault();
					
					var body_classes = public_vars.$body.attr('class').replace(/(\sskin-[a-z]+)/gi, '').replace(/(\shorizontal-menu-skin-[a-z]+)/gi, '').replace(/(\suser-info-navbar-skin-[a-z]+)/gi, '');
					
					public_vars.$body.attr('class', body_classes);
					
					Cookies.set('current-skin', '');
					Cookies.set('current-horizontal-skin', '');
					Cookies.set('current-user-info-navbar-skin', '');
				});
			});
			</script>
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Sidebar, Settings Pane and Login/Lockstreen</h3>
					<div class="panel-options">
						<a href="#" data-toggle="panel">
							<span class="collapse-icon">&ndash;</span>
							<span class="expand-icon">+</span>
						</a>
						<a href="#" data-toggle="remove">
							&times;
						</a>
					</div>
				</div>
				<div class="panel-body">
				
					<table class="table table-hover middle-align">
						<thead>
							<tr>
								<th>Skin Name</th>
								<th width="300">Color Palette</th>
								<th width="300">Skin Activation</th>
							</tr>
						</thead>
						<tbody>
							<tr data-skin="">
								<td>
									<a href="#" class="skin-name-link">Default Skin</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette" data-set-skin="">
										<span style="background-color: #2c2e2f"></span>
										<span style="background-color: #EEE"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #68b828"></span>
										<span style="background-color: #27292a"></span>
										<span style="background-color: #323435"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="aero">
								<td>
									<a href="#" class="skin-name-link">Aero</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #558C89"></span>
										<span style="background-color: #ECECEA"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #5F9A97"></span>
										<span style="background-color: #558C89"></span>
										<span style="background-color: #255E5b"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="navy">
								<td>
									<a href="#" class="skin-name-link">Navy</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #2c3e50"></span>
										<span style="background-color: #a7bfd6"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #34495e"></span>
										<span style="background-color: #2c3e50"></span>
										<span style="background-color: #ff4e50"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="facebook">
								<td>
									<a href="#" class="skin-name-link">Facebook</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #3b5998"></span>
										<span style="background-color: #8b9dc3"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #4160a0"></span>
										<span style="background-color: #3b5998"></span>
										<span style="background-color: #8b9dc3"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="turquoise">
								<td>
									<a href="#" class="skin-name-link">Turquise</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #16a085"></span>
										<span style="background-color: #96ead9"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #1daf92"></span>
										<span style="background-color: #16a085"></span>
										<span style="background-color: #0f7e68"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="lime">
								<td>
									<a href="#" class="skin-name-link">Lime</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #8cc657"></span>
										<span style="background-color: #ffffff"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #95cd62"></span>
										<span style="background-color: #8cc657"></span>
										<span style="background-color: #70a93c"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="green">
								<td>
									<a href="#" class="skin-name-link">Green</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #27ae60"></span>
										<span style="background-color: #a2f9c7"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #2fbd6b"></span>
										<span style="background-color: #27ae60"></span>
										<span style="background-color: #1c954f"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="purple">
								<td>
									<a href="#" class="skin-name-link">Purple</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #795b95"></span>
										<span style="background-color: #c2afd4"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #795b95"></span>
										<span style="background-color: #27ae60"></span>
										<span style="background-color: #5f3d7e"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="white">
								<td>
									<a href="#" class="skin-name-link">White</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #FFF"></span>
										<span style="background-color: #666"></span>
										<span style="background-color: #95cd62"></span>
										<span style="background-color: #EEE"></span>
										<span style="background-color: #95cd62"></span>
										<span style="background-color: #555"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="concrete">
								<td>
									<a href="#" class="skin-name-link">Concrete</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #a8aba2"></span>
										<span style="background-color: #666"></span>
										<span style="background-color: #a40f37"></span>
										<span style="background-color: #b8bbb3"></span>
										<span style="background-color: #a40f37"></span>
										<span style="background-color: #323232"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="watermelon">
								<td>
									<a href="#" class="skin-name-link">Watermelon</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #b63131"></span>
										<span style="background-color: #f7b2b2"></span>
										<span style="background-color: #FFF"></span>
										<span style="background-color: #c03737"></span>
										<span style="background-color: #b63131"></span>
										<span style="background-color: #32932e"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
							<tr data-skin="lemonade">
								<td>
									<a href="#" class="skin-name-link">Lemonade</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette">
										<span style="background-color: #f5c150"></span>
										<span style="background-color: #ffeec9"></span>
										<span style="background-color: #FFF"></span>
										<span style="background-color: #ffcf67"></span>
										<span style="background-color: #f5c150"></span>
										<span style="background-color: #d9a940"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-login">Login Page</a>
								</td>
							</tr>
						</tbody>
					</table>
					
					<hr />
					
					<h4>Generating your own skin</h4>
					
					<p>Using LessCSS compiler, you can create your own skin inside <code>xenon.less</code> file using this mixin:</p>
					
					<br />
					
					<pre>.xenon-sidebar-skin-variant(@bg-color; @text-color; @text-active-color; @border-color; @primary; @secondary; @scroll-color: #fff);</pre>
					
				</div>
			</div>
			
			
			
			
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Horizontal and User Info Navbar</h3>
					<div class="panel-options">
						<a href="#" data-toggle="panel">
							<span class="collapse-icon">&ndash;</span>
							<span class="expand-icon">+</span>
						</a>
						<a href="#" data-toggle="remove">
							&times;
						</a>
					</div>
				</div>
				<div class="panel-body">
				
					<table class="table table-hover middle-align">
						<thead>
							<tr>
								<th>Skin Name</th>
								<th width="300">Color Palette</th>
								<th width="300">Skin Activation</th>
							</tr>
						</thead>
						<tbody>
							<tr data-skin-horizontal="">
								<td>
									<a href="#" class="skin-name-link">Default Skin</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four" data-set-skin="">
										<span style="background-color: #2c2e2f"></span>
										<span style="background-color: #EEE"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #68b828"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="aero">
								<td>
									<a href="#" class="skin-name-link">Aero</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #558C89"></span>
										<span style="background-color: #ECECEA"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #5f9a97"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="navy">
								<td>
									<a href="#" class="skin-name-link">Navy</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #2c3e50"></span>
										<span style="background-color: #a7bfd6"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #34495e"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="facebook">
								<td>
									<a href="#" class="skin-name-link">Facebook</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #3b5998"></span>
										<span style="background-color: #8b9dc3"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #4160a0"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="turquoise">
								<td>
									<a href="#" class="skin-name-link">Turquise</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #16a085"></span>
										<span style="background-color: #96ead9"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #1daf92"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="lime">
								<td>
									<a href="#" class="skin-name-link">Lime</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #8cc657"></span>
										<span style="background-color: #ffffff"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #95cd62"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="green">
								<td>
									<a href="#" class="skin-name-link">Green</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #27ae60"></span>
										<span style="background-color: #a2f9c7"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #2fbd6b"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="purple">
								<td>
									<a href="#" class="skin-name-link">Purple</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #795b95"></span>
										<span style="background-color: #c2afd4"></span>
										<span style="background-color: #FFFFFF"></span>
										<span style="background-color: #795b95"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="white">
								<td>
									<a href="#" class="skin-name-link">White</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #FFF"></span>
										<span style="background-color: #666"></span>
										<span style="background-color: #95cd62"></span>
										<span style="background-color: #EEE"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="concrete">
								<td>
									<a href="#" class="skin-name-link">Concrete</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #a8aba2"></span>
										<span style="background-color: #666"></span>
										<span style="background-color: #a40f37"></span>
										<span style="background-color: #b8bbb3"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="watermelon">
								<td>
									<a href="#" class="skin-name-link">Watermelon</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #b63131"></span>
										<span style="background-color: #f7b2b2"></span>
										<span style="background-color: #FFF"></span>
										<span style="background-color: #c03737"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
							<tr data-skin-horizontal="lemonade">
								<td>
									<a href="#" class="skin-name-link">Lemonade</a>
								</td>
								<td>
									<a href="#" class="skin-color-palette skin-color-palette-four">
										<span style="background-color: #f5c150"></span>
										<span style="background-color: #ffeec9"></span>
										<span style="background-color: #FFF"></span>
										<span style="background-color: #ffcf67"></span>
									</a>
								</td>
								<td>
									<a href="#" class="btn btn-sm btn-secondary">Try this skin</a>
									<a href="#" class="btn btn-sm btn-white is-userinfo">User Info Navbar</a>
								</td>
							</tr>
						</tbody>
					</table>
					
					<hr />
					
					<h4>Generating your own skin</h4>
					
					<p>Using LessCSS compiler, you can create your own skin for horizontal menu inside <code>xenon.less</code> file using this mixin:</p>
					
					<br />
					
					<pre>.xenon-horizontal-menu-skin-variant(@bg-color; @text-color; @text-active-color; @border-color);</pre>
					
					and for user info navbar
					
					<pre>.xenon-user-info-navbar-skin-variant(@bg-color; @text-color; @text-active-color; @border-color);</pre>
					
				</div>
			</div>
			
			<a class="btn btn-secondary reset-skin">Reset Skin Settings</a>
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
	
	
	



	<!-- Bottom Scripts -->
	<script src="${ctx!}assets/js/bootstrap.min.js"></script>
	<script src="${ctx!}assets/js/TweenMax.min.js"></script>
	<script src="${ctx!}assets/js/resizeable.js"></script>
	<script src="${ctx!}assets/js/joinable.js"></script>
	<script src="${ctx!}assets/js/xenon-api.js"></script>
	<script src="${ctx!}assets/js/xenon-toggles.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="assets/js/xenon-custom.js"></script>

</body>
</html>