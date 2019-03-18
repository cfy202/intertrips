<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>服务项添加</title>
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
			    getsort();
		  });
		  //根据类型异步查询sort
		  $('#type').bind('change', function () {
		       getsort();
		  });
	
		$("#uploadify").uploadify({
				        'uploader' :'${ctx!}/assets/script/swf/uploadify.swf',
				        'script' : '${ctx!}/upload.do?path=images/ico',
				        'cancelImg' :'${ctx!}/assets/script/cancel.png',
				        'queueID' : 'fileQueue',
				        'queueSizeLimit'  :10,
				        'fileDesc' : 'jpg、gif、swf文件、png、wmv',            
				        'fileExt' : '*.jpg;*.gif;*.swf;*.png;*.wmv',
				        'auto' : true,
				        'multi' : false,
				        'simUploadLimit' : 10,
				        'buttonText' : 'Upload',
				        'displayData' : 'percentage',
				        onComplete: function (evt, queueID, fileObj, response, data) {
				         $("#changephoto").val(response);
				         $("#imgshow").attr("src","${ctx!}"+response);
				        // $("#imgshow").attr("style","display:block");
				         $("#imgshow").show();
				         alert("上传成功");
				        }
		  }); 
	}); 
				
	 //分页函数
      function goPage(pageNow){
        showImages(pageNow);
     }
	//图库选图
	function showImages(pageNow){
	   
        $.ajax({
           type:"post",
            url:"${ctx!}/admin/slider/sliderimage.do",
            <#--data:{pageNow:pageNow,costnumber:costnumber,usetype:type,search:searcher},-->
            data:{pageNow:pageNow},
            dataType:"json",
            success: function(data){
              $('#showimage').empty();   //清空里面的所有内容
              var html = "";
              <#--
               var html = "<div class=\"input-group\">"+
						"<input type=\"text\" id=\"searchbyTitle\" placeholder=\"景点名称\" value=\"\" class=\"form-control no-right-border form-focus-purple\">"+
							"<span class=\"input-group-btn\">"+
							 "<button class=\"btn btn-purple\" onclick=\"showImages(${(page.pageNow)!})\" type=\"button\">搜索</button>"+
							"</span>"+
						"</div><br/>";
			   -->
              var a = 0;
              $.each(data.allImages, function(i, e) {
                 a++;
                 html += "<div class=\"col-md-3 col-sm-4 col-xs-6\">"+
										"<div class=\"album-image\">"+
											"<label for=\"url"+a+"\">"+
												"<img src=\"${ctx!}"+e.url+"\" class=\"img-responsive\"/>"+
												"<span>"+e.description+"</span>"+
											"</label>"+
											"<a href=\"#\" class=\"name\">"+
												"<span></span>"+
												"<em></em>"+
											"</a>"+
										    "<div class=\"image-options\">"+
										    "</div>"+
										    "<div class=\"\">"+
											   "<input type=\"radio\" id=\"url"+a+"\" name=\"icoUrl\" value=\""+e.url+"\"/>"+
										    "</div>"+
									    "</div>"+
								    "</div>";
			});
			var html2 = data.pageContent;
		    html2 += "</br><input type=\"button\" class=\"btn btn-turquoise\" onclick=\"upload()\" value=\"upload\">";
			$('#page').html(html2);
			$('#uppic').hide();
			$('#upurl').empty();
			$('#showimage').html(html);
            }
        });
	}	 
	
	function upload(){
	   $('#showimage').empty();
	   $('#page').empty();
	   $('#uppic').show();
	   var html = "<input type=\"hidden\" name=\"icoUrl\" id=\"changephoto\" value=\"\"/>";
				 
	   $('#upurl').html(html);
	} 
	//获取排序号
	function getsort(){
	    var costnumber = $("#costnumber").val();
	    var type = $("#type").val();
        $.ajax({
             type: "post",
             url: "${ctx!}/admin/slider/getsort.do",
             data: {costnumber:costnumber, type:type},
             dataType: "json",
             success: function(data){
                        $('#sort').val(data);
             		}
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
					<h1 class="title">服务项添加</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/serviceitem/list.do">服务项管理</a>
						  </li>
							<li class="active">
								<a href="${ctx!}/admin/serviceitem/add.do"><strong>服务项添加</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/serviceitem/save.do" method="post">
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">标题：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="field-1" name="tittle">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">图标：</label>
									<div class="col-sm-10">
									    <label>  
									       <input type="radio" value="fa-plane" name="ico" checked>&nbsp;&nbsp;&nbsp;
									       <i class="fa-circle-o "></i></span>
									    </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									    <label>
									    <label>  
									       <input type="radio" value="fa-plane" name="ico" checked>&nbsp;&nbsp;&nbsp;
									       <i class="fa-plane "></i></span>
									    </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									    <label>  
									       <input type="radio" value="linecons-food" name="ico" >&nbsp;&nbsp;&nbsp;
									       <i class="linecons-food"></i></span>
									    </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									    <label>  
									       <input type="radio" value="fa-anchor" name="ico" >&nbsp;&nbsp;&nbsp;
									       <i class="fa-anchor"></i></span>
									    </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									    <label>  
									       <input type="radio" value="fa-building" name="ico" >&nbsp;&nbsp;&nbsp;
									       <i class="fa-building"></i></span>
									    </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									    <label>  
									       <input type="radio" value="fa-car" name="ico" >&nbsp;&nbsp;&nbsp;
									       <i class="fa-car"></i></span>
									    </label>
									    
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="uploadify">图片图标：</label>
									<div class="col-sm-10">
										  <div id="uppic">
										     <img src="" id="imgshow" style="display:none;width:800px!important;height:500px!important"/><br/>
											 <input type="file" class="form-control" id="uploadify" name="uploadify">
											 <input type="button" class="btn btn-turquoise" onclick="showImages()" value="图库选取">
										  </div>
										  <div id="upurl">
										     <input type="hidden" name="icoUrl" id="changephoto" value="${(slider.url)!}"/>
										  </div>
									</div>
									
									<div class="col-sm-9 gallery-right">
										<!-- Album Images -->
										<div class="album-images row" id="showimage">
										      
										</div>
										<div id="page">
										
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/serviceitem/list.do';">
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>


	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/datatables/dataTables.bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/colorpicker/pick-a-color-1.2.3.min.css">

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
    <script src="${ctx!}/assets/js/colorpicker/tinycolor-0.9.15.min.js"></script>
    <script src="${ctx!}/assets/js/colorpicker/pick-a-color-1.2.3.min.js"></script>
    

	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>