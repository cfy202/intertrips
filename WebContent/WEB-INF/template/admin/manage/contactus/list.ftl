<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>ContactUs客户管理</title>
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
	<script src="${ctx!}/assets/js/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js" type="text/javascript"></script>
    <link href="${ctx!}/assets/js/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css" media="screen" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
		$(function() {
		$('#gallery a').lightBox();
		});
		//清除cookie中存在的销售中心
        function clearCostNum(){
            addCookie('contactUsCostId',null,{path:"/"});
            window.location.href = "${ctx!}/admin/master/list.do";
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
					<h1 class="title">ContactUs客户管理</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/master/list.do">Contact Us管理</a>
						  </li>
						  <li class="active">
						     <strong>Contact Us客户管理</strong>
						  </li>
						</ol>
				</div>
			</div>	
			<div class="panel panel-default">
				<div class="panel-heading">
				      <#if (cost?size>1)>
                        <nav class="navbar navbar-inverse" role="navigation">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-2">
                                <span class="sr-only">Toggle navigation</span>
                                <i class="fa-bars"></i>
                            </button>
                        </div>
                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-2">
                            <ul class="nav navbar-nav navbar-left">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <#if (costNumList?size>1)>
                                                       销售中心（全部）
                                        <#else>
                                           <#list cost as cost>
                                                <#if cost.id=costNumList[0]>
                                                   ${(cost.name)!}
                                                   <#break>
                                                </#if>
                                           </#list>
                                        </#if>
                                        <b class="caret"></b>
                                    </a>
                                    <ul class="dropdown-menu dropdown-primary">
                                        <li>
                                           <a href="javascript:clearCostNum();">销售中心（全部）</a>
                                        </li>
                                        <#list cost as cost>
                                            <li>
                                                <a href="${ctx!}/admin/master/list.do?costId=${(cost.id)!}">${(cost.name)!}</a>
                                            </li>
                                        </#list>
                                    </ul>
                                </li>
                            </ul>
                            
                        </div>
                        <!-- /.navbar-collapse -->
                    </nav>
                </#if>
                	<#--
					<button class="btn btn-turquoise" onclick="window.location.href='${ctx!}/admin/navigation/add.do'">新增导航项</button>
					<button class="btn btn-info">生成页面</button>-->
				</div>
				<div class="panel-body">
					
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#example-3").dataTable({
							
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
					<table id="example-3" class="table table-striped table-bordered" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>邮件</th>
								<th>联系人名称</th>
								<th>所属国籍</th>
								<th>电话号码</th>
								<th>主题</th>
								<th>运营中心</th>
								<th>日期</th>
								<th>操作</th>
							</tr>
						</thead>															
						<tbody>
						<#list contactUsList as contactUs>
							<tr>
								<td>${(contactUs.email)!}</td>
								<td>${(contactUs.name)!}</td>
								<td>
									${(contactUs.nationality)!}
								</td>
								<td>
									${(contactUs.phoneNo)!}
								</td>
								<td>${(contactUs.subject)!}</td>
						        <td>${(contactUs.cost.name)!}</td>
						        <td>${contactUs.createDate?string('yyyy-MM-dd HH:mm:ss')}</td>
								<td>
									<a href="${ctx!}/admin/master/view.do?id=${(contactUs.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">
										查看
									</a>
									<a href="${ctx!}/admin/master/delete.do?id=${(contactUs.id)!}" class="btn btn-danger btn-sm btn-icon icon-left" onClick="return confirm('确定要删除?');">
										删除
									</a>
								</td>
							</tr>	
							</#list>							
						</tbody>
					</table>					
				</div>
			</div>
			
			
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
