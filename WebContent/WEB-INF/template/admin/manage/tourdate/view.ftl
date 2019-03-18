<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>售出管理</title>

	<link rel="stylesheet" href="https://fonts.useso.com/css?family=Arimo:400,700,400italic">
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
					<h1 class="title">${(tourline.tourname)!}</h1>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						   <li>
							  <a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						   </li>
						   <li>
							   <a href="${ctx!}/admin/tourdate/list.do?productId=${(product.id)!}">出发日期及价格管理</a>
						   </li>
						   <li class="active">
							  <a href="${ctx!}/admin/tourdate/inventory.do?productId=${product.id}"><strong>售出管理</strong></a>
						   </li>
						</ol>
				   </div>
			</div>
			<script type="text/javascript">
			// Calendar Initialization
			jQuery(document).ready(function($)
			{
				// Form to add new event
				var colors = ['red', 'blue', 'primary', 'success', 'warning', 'info', 'danger', 'purple', 'black', 'gray'];
				
				$("#add_event_form").on('submit', function(ev)
				{
					ev.preventDefault();
					
					var $event = $(this).find('.form-control'),
						event_name = $event.val().trim();
					
					if(event_name.length >= 3)
					{
						var color = colors[Math.floor(Math.random()*colors.length)];
						
						// Create Event Entry
						$("#events-list").append(
							'<li>\
								<a href="#" data-event-class="event-color-' + color + '">\
									<span class="badge badge-' + color + ' badge-roundless upper">' + event_name + '</span>\
								</a>\
							</li>'
						);
						
						
						// Reset draggable
						$("#events-list li").draggable({
							revert: true,
							revertDuration: 50,
							zIndex: 999
						});
						
						// Reset input
						$event.val('').focus();
					}
					else
					{
						$event.focus();
					}
				});
				
				
				// Calendar Initialization
				$('#calendar').fullCalendar({
					header: {
						left: 'title',
						center: '',
						right: 'month,agendaWeek,agendaDay prev,next'
					},
					buttonIcons: {
						prev: 'prev fa-angle-left',
						next: 'next fa-angle-right',
					},
					defaultDate: '${startStr!}',
					editable: true,
					eventLimit: true,
					events: [
					   <#list tourdatelist as tourdatelist>
					     
					     <#if tourdatelist_index = count >
					      {
							title: '售出数量：${(tourdatelist.soldnum)!}',
							
							start: '${(tourdatelist.remark)!}'
						  },
						   {
							title: '库存数量：${(tourdatelist.remainnum)!}',
							
							start: '${(tourdatelist.remark)!}'
						  },
						   {
							title: '计划数量：${(tourdatelist.totalnum)!}',
							
							start: '${(tourdatelist.remark)!}'
						  },
					      {
							title: '销售价格：${(tourdatelist.tourprice.sellingprice)!}',
							
							start: '${(tourdatelist.remark)!}'
						  }
						  
						  <#else>
						   {
							title: '售出数量：${(tourdatelist.soldnum)!}',
							
							start: '${(tourdatelist.remark)!}'
						    },
						     {
							title: '计划数量：${(tourdatelist.totalnum)!}',
							
							start: '${(tourdatelist.remark)!}'
						     },
						    {
							title: '库存数量：${(tourdatelist.remainnum)!}',
							
							start: '${(tourdatelist.remark)!}'
						    },
						   {
							title: '销售价格：${(tourdatelist.tourprice.sellingprice)!}',
							
							start: '${(tourdatelist.remark)!}'
						    }, 
						    
					     </#if>
					     
					   </#list>
						
						
					],
					droppable: true,
					drop: function(date) {
						
						var $event = $(this).find('a'),
							eventObject = {
								title: $event.find('.badge').text(),
								start: date,
								className: $event.data('event-class')
							};
						
						$('#calendar').fullCalendar('renderEvent', eventObject, true);
						
						// Remove event from list
						$(this).remove();
					}
				});
				
				
				// Draggable Events
				$("#events-list li").draggable({
					revert: true,
					revertDuration: 50,
					zIndex: 999
				});
			});
			</script>
			
			<section class="calendar-env">
				
				<div class="col-sm-9 calendar-right">
					
					<div class="calendar-main">
						
						<div id="calendar"></div>
						
					</div>
					
				</div>
				
				<div class="col-sm-3 calendar-left">
					
					<div class="calendar-sidebar">
						
						<form method="post" id="add_event_form">
							<input type="text" class="form-control" placeholder="Add new event..." />
						</form>
						
						<br />
						<#if departureList?has_content>
						  <ul class="list-unstyled calendar-list" id="events-list">
						   <#list departureList as departureList>
						    
								<li>
									<a href="${ctx!}/admin/tourdate/inventory.do?productId=${product.id}&departureId=${(departureList.id)!}" data-event-class="event-color-success">
										<span class="badge badge-success badge-roundless upper">${(departureList.name)!}</span>
									</a>
							    </li>
						   </#list>
						   </ul>
						</#if>	
						
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
						&copy; 2015
					   <a href="" target="_blank" title="西安淘游网络科技有限公司">西安淘游网络科技有限公司</a> 
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
	
	
	




	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/fullcalendar/fullcalendar.css">

	<!-- Bottom Scripts -->
	<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
	<script src="${ctx!}/assets/js/TweenMax.min.js"></script>
	<script src="${ctx!}/assets/js/resizeable.js"></script>
	<script src="${ctx!}/assets/js/joinable.js"></script>
	<script src="${ctx!}/assets/js/xenon-api.js"></script>
	<script src="${ctx!}/assets/js/xenon-toggles.js"></script>
	<script src="${ctx!}/assets/js/moment.min.js"></script>


	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/fullcalendar/fullcalendar.min.js"></script>
	<script src="${ctx!}/assets/js/jquery-ui/jquery-ui.min.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>