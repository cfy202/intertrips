<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>办公室联系信息编辑</title>
    <!--<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">-->
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">
	<link href="${ctx!}/assets/css/uploadify.css" rel="stylesheet" type="text/css" />
	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${ctx!}/assets/script/jquery.uploadify.v2.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/script/swfobject.js"></script> 
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
				<#--
				<div class="title-env">
					<h1 class="title">幻灯片图片添加</h1>
					<p class="description"></p>
				</div>
				-->
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/officeContacts/list.do">办公室联系信息管理</a>
						  </li>
							<li class="active">
								<a href="${ctx!}/admin/officeContacts/edit.do?id=${officeContacts.id}"><strong>办公室联系信息编辑</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/officeContacts/update.do" method="post">
								<input type="hidden" name="id" value="${officeContacts.id}">
							    <#if (costList?size==1)>
								<input type="hidden" name="costnumber" value="${costList[0].id}">
								<#else>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="costnumber">运营中心：</label>
									<div class="col-sm-10">
									    <select name="costnumber" class="form-control">
									       <#list costList as cost>
									          <option value="${(cost.id)!}" <#if cost.id = officeContacts.costnumber>selected="selected"</#if>>${cost.name}</option>
									       </#list>
									    </select>
									</div>
								</div>
								</#if>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">办公室名称：</label>
									<div class="col-sm-10">
									   <input type="text" class="form-control" id="field-3" name="officeName" value="${officeContacts.officeName}">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">联系电话：</label>
									<div class="col-sm-10">
									    <input type="text" class="form-control" id="field-3" name="tel" value="${officeContacts.tel}"/>
									</div>
								</div>

								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">传真：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="field-3" name="fax" value="${officeContacts.fax}">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-3">邮箱：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="field-3" name="email" value="${officeContacts.email}"/>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">地址：</label>
									<div class="col-sm-10">
									    <textarea class="form-control" name="address" cols="5" id="field-1">${officeContacts.address}</textarea>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/officeContacts/list.do';">
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