<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>图片添加</title>
    <!--<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">-->
	
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/multiselect/css/multi-select.css">
	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
	<link href="${ctx!}/assets/css/uploadify.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx!}/assets/script/jquery.uploadify.v2.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/script/swfobject.js"></script> 

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
<script type="text/javascript">
$(document).ready(function() {
	$("#uploadify1").uploadify({
			        'uploader' :'${ctx!}/assets/script/swf/uploadify.swf', // uploadify.swf 文件的相对路径
			        'script' : "${ctx!}/upload.do?path=images", //要提交到处理文件上传的Controller
			        'cancelImg' :'${ctx!}/assets/script/cancel.png', //选择文件到文件队列中后的每一个文件上的关闭按钮图标
			        'queueID' : 'fileQueue', //文件队列的ID，该ID与存放文件队列的div的ID一致
			        'queueSizeLimit'  :20, //队列中同时存在的文件个数限制 
			        'fileDesc' : 'jpg、gif、swf文件、png', //如果配置了以下的'fileExt'属性，那么这个属性是必须的           
			        'fileExt' : '*.jpg;*.gif;*.swf;*.png', //允许的格式 
			        'auto' : true, //是否自动开始  
			        'multi' : true, //是否支持多文件上传
			        'simUploadLimit' : 20, //一次同步上传的文件数目 
			        'buttonText' : 'upload', //按钮上的文字  
			        'displayData' : 'percentage', //进度条的显示方式 
			        onComplete: function (evt, queueID, fileObj, response, data) {
			          saveUserPictproal(response);
			        }
			    }); 
			}); 
var ii = 1;	
function saveUserPictproal(response){
	$("#IMAGE").append("<img src=\"${ctx!}" + response + "\" alt=\"\" height=\"100\" width=\"150\" onclick=\"cancel(this)\" class=\""+ii+"\">"+"&nbsp;");
	$("#URL").append("<input type=\"hidden\" id=\"imageurl\" name=\"url\" value=\"" + response + "\" class=\"" + ii + "\"/>");
	ii = ii+1;
//	var usetype = $("#usetype").val();
//	var costnumber = $("#costnumber").val();
//	$.ajax({
//		   type: "POST",
//		   url: "${ctx!}/admin/image/save.do",
//         data:"picaddress="+response+"&usetype="+usetype+"&costnumber="+costnumber,
//		   success: function(){
//			
//				}
//			});
	}
	//点击删除图片
	function cancel(obj){
		if(confirm("确定删除图片？")){
			var n = $(obj).attr("class");
			$("."+n).remove();
		};
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
					<h1 class="title">图片添加</h1>
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
						
										<strong>图片添加</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-body">
							
							<form role="form" class="form-horizontal" action="${ctx!}/admin/image/addsave.do" method="post">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">图片用途</label>									
									<div class="col-sm-10">
										<select class="form-control" name="usetype" id="usetype">
												<option value="atractions">景点图片</option>
												<option value="tourline">线路图片</option>
            									<option value="itinerary">线路行程图片</option>
            									<option value="page">单页面图片</option>
            									<option value="slide">幻灯片图片</option>
            									<option value="visa">签证图片</option>
            									<option value="other">其他</option>
										</select>
							<!--			<input type="text" name="usetype"  value="" class="form-control" id="usetype" placeholder="">	-->
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-4">上传图片</label>
									
									<div class="col-sm-10">
									   <div id="fileQueue"></div>
									   <input type="file" class="col-sm-2 control-label" name="uploadify1" id="uploadify1" />
    						        <!--
    						        <p>
		      							<a href="javascript:$('#uploadify1').uploadifyUpload()">上传</a>| 
		      							<a href="javascript:$('#uploadify1').uploadifyClearQueue()">取消上传</a>
    						        </p>
    						        -->
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">上传图片预览<br/>(点击可删除)</label>
									<div class="col-sm-10" id="IMAGE"></div>
									<div class="col-sm-10" id="URL"></div>
								</div>
								<div class="form-group-separator"></div> 
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input name="url" type="hidden" id="url" value=""/>
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