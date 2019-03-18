<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>添加角色</title>
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
			jQuery(document).ready(function($)
			{
				// Skins
				
				
				// Styles
				$('input.icheck-11').iCheck({
					checkboxClass: 'icheckbox_square-blue',
					radioClass: 'iradio_square-yellow'
				});
				
				$('input.icheck-12').iCheck({
					checkboxClass: 'icheckbox_flat-pink',
					radioClass: 'iradio_flat-grey'
				});
				
				$('input.icheck-13').iCheck({
					checkboxClass: 'icheckbox_futurico',
					radioClass: 'iradio_futurico'
				});
				
				$('input.icheck-14').iCheck({
					checkboxClass: 'icheckbox_polaris',
					radioClass: 'iradio_polaris'
				});
				
				$('input.icheck-15').each(function(i, el)
				{
					var self = $(el),
						label = self.next(),
						label_text = label.text();
					
					label.remove();
					
					self.iCheck({
						checkboxClass: 'icheckbox_line-green',
						radioClass: 'iradio_line-red',
						insert: '<div class="icheck_line-icon"></div>' + label_text
					});
				});
			});
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
					<h1 class="title">角色添加</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/role/list.do">角色管理</a>
								</li>
							<li class="active">
						
										<strong>添加角色</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-heading">
						</div>
						<div class="panel-body">
							
							<form role="form" class="form-horizontal" action="${ctx!}/admin/role/save.do" method="post">
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">名称</label>								
									<div class="col-sm-10">
										<input type="text" name="name" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">标题</label>								
									<div class="col-sm-10">
										<input type="text" name="title" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">编辑人</label>								
									<div class="col-sm-10">
										<input type="text" name="editname" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">选择目录</label>
									<div class="col-sm-10">
									<#list treelist as treelist  >
								      
										<div class="checkbox">
											<#if treelist.level??&&treelist.level =1>
											 <div style="margin-left: 35px;">
											  
											  <#else>
											  <div>
											</#if>
										    <input tabindex="5" type="checkbox" class="icheck-11" name = "treeids" value="${treelist.id}" id="minimal-checkbox-1-11">
									        <label for="minimal-checkbox-1-11">${treelist.name}</label>
											
											<#if treelist.parentid??&&treelist.parentid !='root' >
											     <#list treelist.opationIds?split(",") as urls> 
											       <div style="margin-left: 50px;"  >  
											         <input tabindex="5" type="checkbox" class="icheck-14" name = "${treelist.id}" value="${urls}" id="minimal-checkbox-1-11" checked >
										             <#list treelist.opationNames?split(",") as names>
											             <#if urls_index=names_index>
													     <label for="minimal-checkbox-1-11">${names}</label>
											             </#if>  
										            </#list>
										            </div>
									            </#list>
											</#if>
										</div>
									</#list>	
										
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">选择运营中心</label>
									<div class="col-sm-10">
									<#list costlist as costlist  >
								      
										<div class="checkbox">
											<label>
												<input type="checkbox" name = "costids" value="${costlist.id}">
												${costlist.name}
											</label>
										</div>
									</#list>	
										
									</div>
								</div>
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<div class="col-sm-10">
										<input name="id" type="hidden" id="id" value="${(role.id)!}">
										<input type="submit" name="button" id="button" value="提交" class="btn btn-secondary btn-sm btn-icon icon-left">
										<input type="button" name="name" value="取消" onClick="location.href='${ctx!}/admin/role/list.do';" class="btn btn-secondary btn-sm btn-icon icon-left">
									</div>								
								</div>     		
							</form>
						</div>
					</div>
				</div>
			</div>
	

	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${ctx!}/assets/js/multiselect/css/multi-select.css">
    <link rel="stylesheet" href="${ctx!}/assets/js/icheck/skins/all.css">
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
    <script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
    <script src="${ctx!}/assets/js/icheck/icheck.min.js"></script>

	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>