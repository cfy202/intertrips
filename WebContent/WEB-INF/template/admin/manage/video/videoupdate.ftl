<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>视频修改</title>
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
	<script type="text/javascript">
	$(document).ready(function() {
	      //根据运营中心异步查询sort
		  $('#costnumber').bind('change', function () {
			    $("#showimage").empty();
			    $("#page").empty();
			    $('#uppic').show();
			  
		  });
		  //根据类型异步查询sort
		  $('#type').bind('change', function () {
		       getsort();
		  });
	
		$("#uploadify").uploadify({
				        'uploader' :'${ctx!}/assets/script/swf/uploadify.swf',
				        'script' : '${ctx!}/upload.do?path=images/slider',
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
				          alert("上传成功");
				        }
				    }); 
	}); 
	function upload(){
	   $('#showimage').empty();
	   $('#page').empty();
	   $('#uppic').show();
	   var html = "<input type=\"hidden\" name=\"pic\" id=\"changephoto\" value=\"\"/>";
				 
	   $('#upurl').html(html);
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
					<h1 class="title">幻灯片图片修改</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/video/list.do">视频管理</a>
						  </li>
							<li class="active">
								<a href="${ctx!}/admin/video/add.do"><strong>视频修改</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/video/save.do?id=${(video.id)!}" method="post">
							    <#if (cost?size==1)>
								<input type="hidden" name="costnumber" id="costnumber" value="${(video.costnumber)!}">
								<#else>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="costnumber">运营中心：</label>
									<div class="col-sm-10">
									    <select name="costnumber" class="form-control" id="costnumber">
									       <#list cost as cost>
									          <option value="${(cost.id)!}" <#if (cost.id)=(video.costnumber)>selected="selected"</#if>>${cost.name}</option>
									       </#list>
									    </select>
									</div>
								</div>
								</#if>
							    
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="uploadify">视频封面：</label>
									<div class="col-sm-10">
										  <div id="uppic">
										    <img src="${ctx!}${(video.pic)!}" id="imgshow" style="width:800px!important;height:500px!important"/><br/>
											<input type="file" class="form-control" id="uploadify" name="uploadify">
										  </div>
										  <div id="upurl">
										     <input type="hidden" name="pic" id="changephoto" value="${(video.pic)!}"/>
										  </div>
									</div>
									</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">标题：</label>
									<div class="col-sm-10">
									   <input type="text" class="form-control" id="field-3" name="title" value="${(video.title)!}">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">描述：</label>
									<div class="col-sm-10">
								    	<textarea class="form-control" name="description" cols="5" id="field-1">${(video.infor)!}</textarea>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="type">类型：</label>
									<div class="col-sm-10">
									   <select name="type" class="form-control" id="type">
									          <option value="1" <#if video.type?? && video.type==1>selected="selected"</#if>>共享视频</option>
									          <option value="2" <#if video.type?? && video.type==2>selected="selected"</#if>>私有视频</option>
									          <#--
									          <option value="3" <#if slider.type?? && slider.type==3>selected="selected"</#if>>三级页面</option>
									          <option value="4" <#if slider.type?? && slider.type==4>selected="selected"</#if>>四级页面</option>
									          <option value="5" <#if slider.type?? && slider.type==5>selected="selected"</#if>>五级页面</option>
									          <option value="6" <#if slider.type?? && slider.type==6>selected="selected"</#if>>六级页面</option>
									          -->
									    </select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">状态：</label>
									<div class="col-sm-10">
										<div class="checkbox">
											<label>
												<input type="checkbox" name="isshow" value="1" <#if video.isshow=1>checked="checked"</#if>>显示
											</label>
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-3">链接地址：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="field-3" name="url" value="${(video.url)!}">
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/video/list.do';">
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