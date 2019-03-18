<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>修改管理目录</title>
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
		$(function(){
		          
		             var sell = $("#destinationid").val();
				        if(sell!='root'){
				         $("#opationSelect").show();
				        }else{
				         $("#opationSelect").hide();
				        }
		            //获取选中值
		            
		        $('#rootwizard').submit(function(){
		           var checkText=$("#destinationid").find("option:selected").text();
		           $("#pname").val(checkText);
		                return true;
		            });
		            
		            $("#destinationid").change(function(){
				       // var sell = $("#destinationid").find("option:selected").attr("emoney").trim();
				       var sell = $("#destinationid").val();
				        if(sell!='root'){
				         $("#opationSelect").show();
				         
				        }else{
				         $("#opationSelect").hide();
				       
				        }
						    
						    
					}); 
		    })
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
					<h1 class="title">后台目录修改</h1>
					<p class="description">Plain text boxes, select dropdowns and other basic form elements</p>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/tree/list.do">目录管理</a>
								</li>
							<li class="active">
						
										<strong>修改目录</strong>
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
							
							<form role="form" class="form-horizontal" id="rootwizard" action="${ctx!}/admin/tree/save.do" method="post">
								
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">名称</label>								
									<div class="col-sm-10">
										<input type="text" name="name" value="${(treeus.name)!}" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-url">url路径</label>								
									<div class="col-sm-10">
										<input type="text" name="url" value="${(treeus.url)!}" class="form-control" id="field-url" placeholder="" >
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">上级目录</label>									
									<div class="col-sm-10">
										<select class="form-control" name="parentid" id="destinationid">
											<option value="root">根目录</option>
											<#list treelist as treelist >
            									<option value="${(treelist.id)!}" <#if treeus.parentid?? && treeus.parentid = treelist.id>selected="selected"</#if>>${(treelist.name)!}</option>
            								</#list>
										</select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="panel panel-default">
										
										<div class="panel-body" id="opationSelect">
											
											
												
												<div class="form-group">
													<label class="col-sm-3 control-label" for="tagsinput-1">选择操作权限</label>
													
													<div class="col-sm-9">
														
														<script type="text/javascript">
															jQuery(document).ready(function($)
															{
																$("#multi-select").multiSelect({
																	afterInit: function()
																	{
																		// Add alternative scrollbar to list
																		this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar();
																	},
																	afterSelect: function()
																	{
																		// Update scrollbar size
																		this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar('update');
																	}
																});
															});
														</script>
														<select class="form-control" multiple="multiple" id="multi-select" name="my-select[]">
															  <#list operaterlist  as operaterlist  >
					            									<option value="${(operaterlist.name)!}:${operaterlist.url}" <#if (treeus.opationNames?? && treeus.opationNames?index_of("${operaterlist.name}")!=-1)>selected="selected"</#if> >${(operaterlist.name)!}</option>
					            								</#list>
															
														</select>
														
													</div>
												</div>
										</div>
									</div>
							<!--<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">排序</label>									
									<div class="col-sm-10">
										<input type="text" name="orderid" value="${(treeus.orderid)!}" class="form-control" id="field-2" placeholder="">
									</div>
								</div> 
								 -->
								<div class="form-group-separator"></div> 
								  
								<div class="form-group">
									<div class="col-sm-10">
										<input name="id" type="hidden" id="id" value="${(treeus.id)!}">
										<input name="pname" type="hidden" id="pname" value="">
										<input type="submit" name="button" id="button" value="提交" class="btn btn-secondary btn-sm btn-icon icon-left">
										<input type="button" name="name" value="取消" onClick="location.href='${ctx!}/admin/tree/list.do';" class="btn btn-secondary btn-sm btn-icon icon-left">
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

	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>