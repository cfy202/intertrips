<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>目的地修改</title>
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
	$(document).ready(function() {
		$("#uploadify").uploadify({
	        'uploader' :'${ctx!}/assets/script/swf/uploadify.swf',
	        'script' : '${ctx!}/upload.do?path=images/destination',
	        'cancelImg' :'${ctx!}/assets/script/cancel.png',
	        'queueID' : 'fileQueue',
	        'queueSizeLimit'  :10,
	        'fileDesc' : 'jpg、gif、swf文件、png',            
	        'fileExt' : '*.jpg;*.gif;*.swf;*.png',
	        'auto' : true,
	        'multi' : false,
	        'simUploadLimit' : 10,
	        'buttonText' : 'upload',
	        'displayData' : 'percentage',
	        onComplete: function (evt, queueID, fileObj, response, data) {
	         $("#changephoto").val(response);
	          $("#imgshow").attr("src","${ctx!}"+response);
	        }
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
					<h1 class="title">目的地修改</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/destination/list.do">目的地管理</a>
								</li>
							<li class="active">
						
										<strong>目的地修改</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-body">
							
							<form role="form" class="form-horizontal" action="${ctx!}/admin/destination/save.do" method="post">
							<#--	
								<div class="form-group">
									<label class="col-sm-2 control-label">运营中心</label>									
									<div class="col-sm-10">
										<input type="text"  value="${(costlist.name)!}" class="form-control" id="field-1" readonly="readonly"/>
										<input type="hidden" name="costnumber" value="${(destination.costnumber)!}" class="form-control" id="field-1">
									</div>
								</div>
							-->	
								<div class="form-group">
									<label class="col-sm-2 control-label">等级</label>									
									<div class="col-sm-10">
										<select class="form-control" name="level" id="level">
											<#list destinationLevel as destinationLevel>
            									<option value="${(destinationLevel.level)!}" <#if destination.level?? && destination.level = destinationLevel.level>selected="selected"</#if>>${(destinationLevel.description)!}</option>
            								</#list>
										</select>
									</div>
								</div>
							
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">名称</label>								
									<div class="col-sm-10">
										<input type="text" name="name" value="${(destination.name)!}" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">中文</label>									
									<div class="col-sm-10">
										<input type="text" name="namecn" value="${(destination.namecn)!}" class="form-control" id="field-2" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">拼音首字母</label>									
									<div class="col-sm-10">
										<input type="text" name="namepy" value="${(destination.namepy)!}" class="form-control" placeholder="例如：a,b,c等">
									</div>
								</div>
								<#--
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">简写</label>								
									<div class="col-sm-10">
										<input type="text" name="myshort" value="${(destination.myshort)!}" class="form-control" id="field-1" placeholder="">
									</div>
								</div>																																											
								-->
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">上级</label>									
									<div class="col-sm-10">
										<select class="form-control" name="upid" id="upId">
											<option value="0">请选择上级</option>
											<#list destination2 as destination2>
            									<option value="${(destination2.id)!}" <#if destination.upid?? && destination.upid = destination2.id>selected="selected"</#if>>${(destination2.name)!}-${(destination2.namecn)!}</option>
            								</#list>
										</select>
									</div>
								</div>
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#upId").select2({
										//		placeholder: '请选择产品',
												allowClear: false
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
										});
								</script>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">类型</label>
									<div class="col-sm-10">
										<p>
											<label class="checkbox-inline">
												<input type="radio" name="typeid" value="1" <#if destination.typeid?? && destination.typeid=1>checked="checked"</#if>>
												洲
											</label>
											<label class="checkbox-inline">
												<input type="radio" name="typeid" value="2" <#if destination.typeid?? && destination.typeid=2>checked="checked"</#if>>
												国家
											</label>
											<label class="checkbox-inline">
												<input type="radio" name="typeid" value="3" <#if destination.typeid?? && destination.typeid=3>checked="checked"</#if>>
												省/州
											</label>
											<label class="checkbox-inline">
												<input type="radio" name="typeid" value="4" <#if destination.typeid?? && destination.typeid=4>checked="checked"</#if>>
												城市
											</label>
										</p>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">热点</label>
									<div class="col-sm-10">
										<p>
											<label class="checkbox-inline">
												<input type="checkbox" name="ishot" value="1" <#if destination.ishot?? && destination.ishot=1>checked="checked"</#if>>
											</label>											
										</p>
									</div>
								</div>	
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="uploadify">图片：</label>
									<div class="col-sm-10">
									    <img src="${ctx!}${(destination.imageUrl)!}" id="imgshow" width="80%"><br/>
									    <div class="form-group-separator"></div>
									    <input type="file" class="form-control" id="uploadify" name="uploadify">
									</div>
									<div class="col-sm-10">
										<input type="hidden" name="imageUrl" id="changephoto" value="${(destination.imageUrl)!}"/>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">排序</label>									
									<div class="col-sm-10">
										<input type="text" name="sort" value="${((destination.sort)?c)!}" class="form-control" id="field-2" placeholder="">
									</div>
								</div> 
								 
								<div class="form-group-separator"></div> 
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input name="id" type="hidden" id="id" value="${(destination.id)!}">
										<input type="submit" name="button" class="btn btn-secondary btn-sm btn-icon icon-left" id="button" value="提交">
										<input type="button" name="name" class="btn btn-secondary btn-sm btn-icon icon-left" value="取消" onClick="location.href='${ctx!}/admin/destination/list.do';">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	

	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/datatables/dataTables.bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/select2/select2.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/select2/select2-bootstrap.css">
	<link href="${ctx!}/assets/css/uploadify.css" rel="stylesheet" type="text/css" />
	

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
	<script src="${ctx!}/assets/js/select2/select2.min.js"></script>
	<script type="text/javascript" src="${ctx!}/assets/script/jquery.uploadify.v2.1.0.min.js"></script>
	<script type="text/javascript" src="${ctx!}/assets/script/swfobject.js"></script> 


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>