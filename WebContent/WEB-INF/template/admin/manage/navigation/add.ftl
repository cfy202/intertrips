<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>导航添加</title>
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
		 //根据运营中心异步查询orderid和上级
		  $('#costnumber').bind('change', function () {
		       var costnumber = $("#costnumber").val();
		       var type = $("#type").val();
		       $.ajax({
	             type: "post",
	             url: "${ctx!}/admin/navigation/getsort.do",
	             data: {costnumber:costnumber, type:type},
	             dataType: "json",
	             success: function(data){
	                         $('#upid').empty();   //清空upid里面的所有内容
	                         var html = "<option value=\"root\">根目录</option>"; 
	                         $.each(data.navigations, function(commentIndex, navigation){
	                             html +="<option value=\""+navigation.id+"\" >" + navigation.levelstr + "" + navigation.name + "</option>";
	                         });
	                         $('#upid').html(html);
	                      }
	         });
		  });
		  
		  //选择上导航和下导航,异步查询对应的上级
		  $('#type').bind('change', function () {
		       var costnumber = $("#costnumber").val();
		       var type = $("#type").val();
		       $.ajax({
	             type: "post",
	             url: "${ctx!}/admin/navigation/getsort.do",
	             data: {costnumber:costnumber, type:type},
	             dataType: "json",
	             success: function(data){
	                         $('#upid').empty();   //清空upid里面的所有内容
	                         var html = "<option value=\"root\">根目录</option>"; 
	                         $.each(data.navigations, function(commentIndex, navigation){
	                             html +="<option value=\""+navigation.id+"\" >" + navigation.levelstr + "" + navigation.name + "</option>";
	                         });
	                         $('#upid').html(html);
	                      }
	         });
		  });
		  
		   $('#rootwizard').submit(function(){
		           var checkText=$("#upid").find("option:selected").text();
		           checkText = checkText.replace(/[ ]/g, "");
		           checkText = checkText.replace(/\|--/g, "").trim();
		           $("#pname").val(checkText);
		                return true;
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
					<h1 class="title">导航添加</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/navigation/list.do">导航管理</a>
						  </li>
							<li class="active">
								<a href="${ctx!}/admin/navigation/add.do"><strong>导航添加</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" id="rootwizard" class="form-horizontal" action="${ctx!}/admin/navigation/save.do" method="post">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">名称：</label>
									<div class="col-sm-10">
									   <input type="text" class="form-control" id="field-1" name="name">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">链接地址：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="field-2" name="url">
									</div>
								</div>
								
								<#if (costs?size==1)>
								<input type="hidden" name="costnumber" id="costnumber" value="${costs[0].id}">
								<#else>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="costnumber">运营中心：</label>
									<div class="col-sm-10">
									    <select name="costnumber" class="form-control" id="costnumber">
									       <#list costs as cost>
									          <option value="${(cost.id)!}">${cost.name}</option>
									       </#list>
									    </select>
									</div>
								</div>
								</#if>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="type">导航类型：</label>
									<div class="col-sm-10">
									   <select name="type" class="form-control" id="type">
									       <option value="1">上导航</option>
									       <option value="2">下导航</option>
									    </select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">上级导航：</label>									
									<div class="col-sm-10">
										<select class="form-control" name="upid" id="upid">
										    <option value="root">根目录</option>
											<#list navigation2 as navigation2>
            									<option value="${(navigation2.id)!}" >${(navigation2.levelstr)!}${(navigation2.name)!}</option>
            								</#list>
										</select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">状态：</label>
									<div class="col-sm-10">
										<p>
											<label class="checkbox-inline">
												<input type="checkbox" name="isShow" value="1" checked="checked">显示
											</label>											
										</p>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
									    <input name="pname" type="hidden" id="pname" value="">
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/navigation/list.do';">
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