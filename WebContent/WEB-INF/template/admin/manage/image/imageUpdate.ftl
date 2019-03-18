<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>图片修改</title>
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
					<h1 class="title">图片修改</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/image/list.do">图库管理</a>
								</li>
							<li class="active">
						
										<strong>图片修改</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-body">
							
							<form role="form" class="form-horizontal" action="${ctx!}/admin/image/saveimage.do" method="post">
							<!--
								<div class="form-group">
									<label class="col-sm-2 control-label">运营中心</label>									
									<div class="col-sm-10">
										<input type="text"  value="${(costlist.name)!}" class="form-control" id="field-1" readonly="readonly"/>
										<input type="hidden" name="costnumber" value="${(image1.costnumber)!}" class="form-control" id="field-1">
									</div>
								</div>
							-->	
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">Title</label>								
									<div class="col-sm-10">
										<input type="text" name="title" value="${(image1.title)!}" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">useType</label>									
									<div class="col-sm-10">
										<select class="form-control" name="usetype" id="usetype">
											<option value="atractions" <#if image1.usetype?? && image1.usetype="atractions">selected="selected"</#if>>景点图片</option>
											<option value="tourline" <#if image1.usetype?? && image1.usetype="tourline">selected="selected"</#if>>线路图片</option>
        									<option value="itinerary" <#if image1.usetype?? && image1.usetype="itinerary">selected="selected"</#if>>线路行程图片</option>
        									<option value="page" <#if image1.usetype?? && image1.usetype="page">selected="selected"</#if>>单页面图片</option>
        									<option value="slide" <#if image1.usetype?? && image1.usetype="slide">selected="selected"</#if>>幻灯片图片</option>
            								<option value="visa" <#if image1.usetype?? && image1.usetype="visa">selected="selected"</#if>>签证图片</option>
        									<option value="other" <#if image1.usetype?? && image1.usetype="other">selected="selected"</#if>>其他</option>
										</select>
				<!--						<input type="text" name="usetype" value="${(image1.usetype)!}" class="form-control" id="field-2" placeholder="">   -->
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">图片预览</label>									
									<div class="col-sm-10">
										<img src="${ctx!}${(image1.url)!}" width="80%"/>
									</div>
								</div>
								
								<div class="form-group-separator"></div> 
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input name="id" type="hidden" id="id" value="${(image1.id)!}">
										<input type="submit" name="button" id="button" value="提交" class="btn btn-secondary btn-sm btn-icon icon-left">
										<input type="button" name="name" value="取消" onClick="location.href='${ctx!}/admin/image/list.do';" class="btn btn-secondary btn-sm btn-icon icon-left">
									</div>								
								</div>     		
							</form>
						</div>
					</div>
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
</html>