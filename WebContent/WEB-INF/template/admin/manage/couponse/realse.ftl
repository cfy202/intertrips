<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>生成及发放优惠券</title>
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
		      $("#costnumber").change(function(){
		        if($(this).val()!='请选择'){
		          uusee($(this).val())
		        }
		       
		      });
		});
				
		function uusee(id){
		 $.ajax({
			type: "POST",
			url: "${ctx!}/admin/level/updateplace.do",
			data: "id="+id,
			cache:false,
			success: function(data) {
			 
			  $("#place").val(data);
			},
			error: function(e) {
				alert(e);
			},
		});
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
					<h1 class="title">生成及发放优惠券</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
										<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
									</li>
									<li>
									    <a href="${ctx!}/admin/couponse/list.do">优惠券管理</a>
									</li>
							<li class="active">
						
										<strong>生成及发放优惠券</strong>
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
							
							<form role="form" class="form-horizontal" action="${ctx!}/admin/couponse/give.do" method="post">
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="costnumber">优惠券等级</label>
									<div class="col-sm-10">
									    <select name="levelid" class="form-control" id="costnumber">
									      <option value="请选择">请选择</option>
									       <#list couponslevel as couponslevel>
									          <option value="${(couponslevel.id)!}">${couponslevel.name}</option>
									       </#list>
									    </select>
									    <input type = "hidden" name ="place" id = "place" value="">
									</div>
								</div>
								<div class="form-group-separator"></div>
								<#if couponse.type??&&couponse.type==1>
								<div  class="panel panel-default" >
										
										<div class="panel-body">
											
											
												
												<div class="form-group">
													<label class="col-sm-3 control-label" for="tagsinput-1">选择会员</label>
													
													<div class="col-sm-9">
														
														<script type="text/javascript">
															jQuery(document).ready(function($)
															{  
															   var checd = 0;
																$("#multi-select").multiSelect({
																    
																	afterInit: function()
																	{
															           // Add alternative scrollbar to list
																		this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar();
																	    
																	},
																	
																	afterSelect: function()
																	{
																	   checd = $("#multi-select").val().length;
																	  
																	    // Update scrollbar size
																		this.$selectableContainer.add(this.$selectionContainer).find('.ms-list').perfectScrollbar('update');
																	    
																	}
																});
																
															});
														</script>
														<select class="form-control" multiple="multiple" id="multi-select" name="my-select[]">
															  <#list memberlist  as memberlist  >
					            									<option value="${(memberlist.account)!}/${(memberlist.id)!}" >${(memberlist.account)!}</option>
					            								</#list>
															
														</select>
														
													</div>
												</div>
											
											
										
										</div>
									</div>
								
								<div class="form-group-separator"></div>
								</#if>
								<div class="form-group">
									<div class="col-sm-10">
										<input name="couponseid" type="hidden" id="id" value="${id!}">
										<input type="submit" name="button" id="button" value="提交" class="btn btn-secondary btn-sm btn-icon icon-left">
										<input type="button" name="name" value="取消" onClick="location.href='${ctx!}/admin/couponse/list.do';" class="btn btn-secondary btn-sm btn-icon icon-left">
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