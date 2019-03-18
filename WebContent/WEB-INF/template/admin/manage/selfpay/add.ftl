<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>自费项目添加</title>
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
	
	<script type="text/javascript">
	$(document).ready(function() {
		 var editor =  UE.getEditor('remark');
	
	      //根据运营中心异步查询sort
		  $('#costnumber').bind('change', function () {
			    $("#showimage").empty();
			    $("#page").empty();
			    $('#uppic').show();
			  
		  });
		
	
		$("#uploadify").uploadify({
				        'uploader' :'${ctx!}/assets/script/swf/uploadify.swf',
				        'script' : '${ctx!}/upload.do?path=images/slider',
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
	    <#--
	    var costnumber = $("#costnumber").val();
	    var type = "slide";
	    var searcher = $("#searchbyTitle").val();
		    if(searcher=='undefined'){
		     searcher = "";
		    }else if(searcher==''){
		      searcher = "";
		   }
		 -->
        $.ajax({
           type:"post",
            url:"${ctx!}/admin/slider/image.do",
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
												"<span>"+e.title+"</span>"+
											"</label>"+
											"<a href=\"#\" class=\"name\">"+
												"<span></span>"+
												"<em></em>"+
											"</a>"+
										    "<div class=\"image-options\">"+
										    "</div>"+
										    "<div class=\"\">"+
											   "<input type=\"radio\" id=\"imgUrl"+a+"\" name=\"imgUrl\" value=\""+e.url+"\"/>"+
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
	   var html = "<input type=\"hidden\" name=\"imgUrl\" id=\"changephoto\" value=\"\"/>";
				 
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
	<style>
		form label.error{
		margin-top: 10px;
		top: 10px;
		right: -110px;
		border-radius: 4px;
		color: #f60;
		width: 100px;
		z-index: 7;
		font-size:12px;
	  }
	</style>
	<script type="text/javascript">
	$(document).ready(function() {
	 //校验
		 $("form#loginForm").validate({
			rules: {
				adultprice:{
				    required:true,           
					number: true
				},
				childprice:{
				    required:true,
					number: true
				},
				city:{
				   required:true,
				}
			},	
			messages: {
				adultprice:{
				    required:"请输入价格",
					number: "请输入合法的数字"
				},
				childprice:{
				    required:"请输入价格",
					number: "请输入合法的数字"
				},
				city:{
				   required:"请选择所在城市",
				}
			},
		  });
		  
		  $('#loginForm').submit(function(){
		     var type = $("#type").val();
		     if(type!=1){
		      var checkText=$("#city").find("option:selected").text(); 
	          $("#destinationName").val(checkText);
		     }else{
		      $("#destinationName").val("");
		      alert("dddd");
		     }
		     
		     
		  });
		  
		   $("#type").change(function(){
				       // var sell = $("#destinationid").find("option:selected").attr("emoney").trim();
				       var sell = $("#type").val();
				        if(sell!='0'){
				         $("#udpk").hide();
				         
				        }else{
				          $("#udpk").show();
				       
				        }
						    
			}); 
		  
		   //根据运营中心异步查询orderid和上级
		  $('#costnumber').bind('change', function () {
		       $.ajax({
	             type: "post",
	             url: "${ctx!}/admin/selfpay/getsort.do",
	             data: {costnumber:$("#costnumber").val()},
	             dataType: "json",
	             success: function(data){
	                         $('#city').empty();   //清空upid里面的所有内容
	                         var html = "<option value=\"\">------- 请选择所在城市  ------</option>"; 
	                         $.each(data.destinations, function(commentIndex, destination){
	                             html +="<option value=\""+destination.name+"\" >"+destination.name+"</option>";
	                         });
	                         $('#city').html(html);
	                         $('#sort').val(data.maxSort);
	                      }
	         });
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
					<h1 class="title">自费项目添加</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/selfpay/list.do">自费项目管理</a>
						  </li>
							<li class="active">
								<a href="${ctx!}/admin/selfpay/add.do"><strong>自费项目添加</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/selfpay/save.do" method="post" id="loginForm">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">项目名称：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="field-1" name="name">
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
									<label class="col-sm-2 control-label" for="type">自选项类型：</label>
									<div class="col-sm-10">
									    <select name="type" class="form-control" id="type">
									          <option value="0">目的地关联服务项</option>
									          <option value="1">导游服务费</option>
									          <option value="2">行程外观光</option>
									          <option value="3">接送机</option>
									          <option value="4">其他</option>
									    </select>
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="price">价格：</label>
									<div class="col-sm-10">
										 <input class="form-control" name="price" id="price" data-validate="number" value="0"/>
									</div>
								</div>
							  
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="uploadify">图片：</label>
									<div class="col-sm-10">
										  <div id="uppic">
										     <img src="" id="imgshow" style="display:none;width:800px!important;height:500px!important"/><br/>
											 <input type="file" class="form-control" id="uploadify" name="uploadify">
											 <input type="button" class="btn btn-turquoise" onclick="showImages()" value="图库选取">
										  </div>
										  <div id="upurl">
										     <input type="hidden" name="imgUrl" id="changephoto" value="${(slider.url)!}"/>
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
									<label class="col-sm-2 control-label" for="city">所在城市：</label>
									<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#city").select2({
												placeholder: '请选择所在城市',
												allowClear: true
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
											
										});
									</script>
									<div class="col-sm-10" id = "udpk">
										<select name="city" class="form-control" id="city">
										   <option value="">请选择</option>
										   <optgroup label="城市名称">
									       <#list destinations as destination>
									            <option value="${(destination.id)!}" >${(destination.name)!} - ${(destination.namecn)!}</option>
									       </#list>
									       </optgroup>
									    </select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-4">说明：</label>
									<div class="col-sm-10">
										<script type="text/plain" id="remark" name="remark" style="width:100%"></script>
										
										</div>									    
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="sort">排序：</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="sort" name="sort" value="${maxSort?c}">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input type="submit" value="提交" class="btn btn-success">
										<input type = "hidden" id= "destinationName" name = "destinationName" value="">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/selfpay/list.do';">
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

	<!-- Bottom Scripts -->
	<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
	<script src="${ctx!}/assets/js/TweenMax.min.js"></script>
	<script src="${ctx!}/assets/js/resizeable.js"></script>
	<script src="${ctx!}/assets/js/joinable.js"></script>
	<script src="${ctx!}/assets/js/xenon-api.js"></script>
	<script src="${ctx!}/assets/js/xenon-toggles.js"></script>
	<script src="${ctx!}/assets/js/datatables/js/jquery.dataTables.min.js"></script>
	<script src="${ctx!}/assets/js/select2/select2.min.js"></script>
	
	<!-- Imported scripts on this page -->
	
	<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${ctx!}/assets/script/jquery.uploadify.v2.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/script/swfobject.js"></script> 
    <script type="text/javascript" src="${ctx!}/assets/ckeditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/ckeditor/ueditor.all.js"></script>    
    
	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>