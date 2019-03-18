<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>目的地添加</title>
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
	<style>
	form label.error{
//	position: absolute;
	margin-top: 10px;
	top: 10px;
	right: -110px;
	border-radius: 4px;
	color: #f60;
	width: 95px;
	z-index: 7;
	font-size:12px;
}
	</style>
	<script type=text/javascript>
		$(document).ready(function(){
			//目的地传图
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
		          $("#imgshow").attr("style","display:block");
		        }
		    }); 
		
			$("#costnumber").change(function(){
				selectCost();
			})
		})
		
		function selectCost(){
			var conum = $("#costnumber").val();
			$.ajax({
			type: "POST",
			url: "${ctx!}/admin/destination/addbycost.do",
			data: "conum=" + conum,
			success: function(data) {
	//			alert(data[0]);
	//			alert(data[1]);
				$("#SORT").val(data[0]+1);
				$("#UPID").empty();
				$("#UPID").append("<option value=\"0\">请选择上级</option>");
				$.each(data[1],function(i,n){
			//		alert(n.namecn);
			//		alert(n.id);
					$("#UPID").append("<option value=\"" + n.id + "\">"+n.name+"-"+n.namecn+"</option>");
				});
			},
			error: function(e) {
				alert(e);
			},
			});
		}
		
	//校验中英文名字是否存在	
jQuery(document).ready(function($) {
	$("form#desForm").validate({
		rules: {
			name: {
				required: true,
				remote: {
					type: "post",
					url: "${ctx!}/admin/destination/isExist.do",
					data: {
						name: function() {
							return $("#name").val();
						},
						costnumber: function() {
							return $("#costnumber").val();
						},
					},
					dataType: "html",
					dataFilter: function(data, type) {
						// alert(data);
						if (data == "true") {
							return false;
						} else {
							return true;
						}
					}
				}
			},
			namecn: {
				required: true,
				remote: {
					type: "post",
					url: "${ctx!}/admin/destination/isExist.do",
					data: {
						name: function() {
							return $("#namecn").val();
						},
						costnumber: function() {
							return $("#costnumber").val();
						},
					},
					dataType: "html",
					dataFilter: function(data, type) {
						// alert(data);
						if (data == "true") {
							return false;
						} else {
							return true;
						}
					}
				}
			},
		},

		messages: {
			name: {
				required: "请输入英文名称",
				remote: "名称已存在"
			},
			namecn: {
				required: "请输入中文名称",
				remote: "名称已存在"
			},

		},
	});
});
	</script>
</head>
<body class="page-body">
	<div class="page-container">
		<!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<#include "/admin/include/left.ftl"/>
		
		
		<div class="main-content">
					
			<!-- User Info, Notifications and Menu Bar -->
			 <#include "/admin/include/man.ftl"/>
			
			<div class="page-title">
				
				<div class="title-env">
					<h1 class="title">目的地添加</h1>
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
						
										<strong>目的地添加</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-body">
							
							<form role="form" class="form-horizontal" id="desForm" action="${ctx!}/admin/destination/save.do" method="post">
							<#--
								<div class="form-group">
									<label class="col-sm-2 control-label">运营中心</label>									
									<div class="col-sm-10">
										<select class="form-control" name="costnumber" id="costnumber">
											<#list costlist as costlist>
            									<option value="${(costlist.id)!}">${(costlist.name)!}</option>
            								</#list>
										</select>
									</div>
								</div>
							-->
								<div class="form-group">
									<label class="col-sm-2 control-label">等级</label>									
									<div class="col-sm-10">
										<select class="form-control" name="level" id="level">
											<#list destinationLevel as destinationLevel>
											   <#if destinationLevel.level??&&destinationLevel.level=5>
											     <option value="${(destinationLevel.level)!}" selected="selected">${(destinationLevel.description)!}</option>
												 <#else>
												 <option value="${(destinationLevel.level)!}">${(destinationLevel.description)!}</option>
											   </#if>
            								</#list>
										</select>
									</div>
								</div>
							
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">名称</label>								
									<div class="col-sm-10">
										<input type="text" name="name" class="form-control" id="name" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">中文</label>									
									<div class="col-sm-10">
										<input type="text" name="namecn" value="" class="form-control" id="namecn" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">拼音首字母</label>									
									<div class="col-sm-10">
										<input type="text" name="namepy" value="" class="form-control" placeholder="例如：a,b,c等"/>
									</div>
								</div>
								<#--
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">简写</label>								
									<div class="col-sm-10">
										<input type="text" name="myshort" value="" class="form-control" id="field-1" placeholder="">
									</div>
								</div>																																											
								-->
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">上级</label>									
									<div class="col-sm-10" id="D1">
										<select class="form-control" name="upid" id="UPID">
											<option value="0">请选择上级</option>
											<#list destination2 as dst>	
            									<option value="${(dst.id)!}">${(dst.name)!}-${(dst.namecn)!}</option>
            								</#list> 
										</select>
									</div>
								</div>
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#UPID").select2({
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
												<input type="radio" name="typeid" value="1">
												洲
											</label>
											<label class="checkbox-inline">
												<input type="radio" name="typeid" value="2">
												国家
											</label>
											<label class="checkbox-inline">
												<input type="radio" name="typeid" value="3">
												省/州
											</label>
											<label class="checkbox-inline">
												<input type="radio" name="typeid" value="4">
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
												<input type="checkbox" name="ishot" value="1">
											</label>											
										</p>
									</div>
								</div>	
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="uploadify">图片：</label>
									<div class="col-sm-10">
									    <img src="${ctx!}${(destination.imageUrl)!}" id="imgshow" style="display:none" width="80%"><br/>
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
										<input type="text" name="sort" value="${((destination.sort)?c)!}" class="form-control" id="SORT" placeholder="" data-validate="number">
									</div>
								</div> 
								
								<div class="form-group-separator"></div> 
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input name="id" type="hidden" id="id" value="${(destination.id)!}">
										<input type="submit" name="button" id="button" value="提交" class="btn btn-secondary btn-sm btn-icon icon-left">
										<input type="button" name="name" value="取消" onClick="location.href='${ctx!}/admin/destination/list.do';" class="btn btn-secondary btn-sm btn-icon icon-left">
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
	<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script> 
	<script type="text/javascript" src="${ctx!}/assets/script/jquery.uploadify.v2.1.0.min.js"></script>
	<script type="text/javascript" src="${ctx!}/assets/script/swfobject.js"></script> 


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>