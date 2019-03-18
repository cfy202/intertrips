<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	<title>博客添加</title>
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
    <script type="text/javascript" src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/ckeditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/ckeditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="${ctx!}/assets/ckeditor/lang/zh-cn/zh-cn.js"></script>
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<script type="text/javascript">
	var uploadImageUrl = null;
	var uploadImageId = null;
	
	var chooseImageId = null;	
	var chooseImageUrl = null;
	
	$(document).ready(function() {
		var editor = UE.getEditor('content');
		
		$("#uploadify1").uploadify({
			'uploader': '${ctx!}/assets/script/swf/uploadify.swf',
			// uploadify.swf 文件的相对路径
			'script': "${ctx!}/upload.do?path=images/blog",
			//要提交到处理文件上传的Controller
			'cancelImg': '${ctx!}/assets/script/cancel.png',
			//选择文件到文件队列中后的每一个文件上的关闭按钮图标
			'queueID': 'fileQueue',
			//文件队列的ID，该ID与存放文件队列的div的ID一致
			'queueSizeLimit': 10,
			//队列中同时存在的文件个数限制 
			'fileDesc': 'jpg',
			//如果配置了以下的'fileExt'属性，那么这个属性是必须的           
			'fileExt': '*.jpg',
			//允许的格式 
			'auto': true,
			//是否自动开始  
			'multi': true,
			//是否支持多文件上传
			'simUploadLimit': 10,
			//一次同步上传的文件数目 
			'buttonText': 'upload',
			//按钮上的文字  
			'displayData': 'percentage',
			//进度条的显示方式 
			onComplete: function(evt, queueID, fileObj, response, data) {
				//    	aturl+=response+",";
				//    	alert(aturl);	
				saveUserPictproal(response);
			}
		});
		
		var submit = false;
		$("#blogForm").submit(function(){
			return submit;		
		});
		
		$("#submitButton").click(function(){
			if(chooseImageId != null){
				$("#coverImageIdInput").val(chooseImageId);
				$("#coverImageUrlInput").val(chooseImageUrl);		
			}else{
				$("#coverImageIdInput").val(uploadImageId);
				$("#coverImageUrlInput").val(uploadImageUrl);	
			}
			if(!$("#stickyInput").is(":checked")){
				$("#blogForm").append("<input type='hidden' name='sticky' value='0'/>");
			}
			var $tittle = $("#tittle");
			var content;
			var $generalFilePathInput = $("#generalFilePathInput");
			editor.ready(function(){
				content = UE.getEditor('content').getContent();
			});
			if($tittle.val().trim() == ''){
				alert('请输入标题.');
				return false;
			}
			if(content.trim() == ''){
				alert('请输入内容.');
				return false;
			}
			if($generalFilePathInput.val().trim() == ''){
				alert('请输入页面生成路径.');
				return false;	
			}else{
				var isExit = false;
				$.ajax({
					type: "POST",
					async:false,
					url: "${ctx!}/admin/blogPage/checkFilePath.do",
					data: {filePath:$generalFilePathInput.val()},
					cache:false,
					success: function(data) {
						if(data == 'no'){
							isExit = true;
						}
					},
					error: function(e) {
						alert(e);
					},
				});
				if(isExit){
					alert('页面路径已存在.');
					return false; 
				}
			}
			$("#contentInput").html(content);
			submit = true;
			$("#blogForm").submit();
		});
	});
	
	//上传图片后返回并添加图片路径和id
	function saveUserPictproal(response) {
	  var costnumber = $("#costnumber").val();
		$.ajax({
			type: "POST",
			url: "${ctx!}/admin/blog/savepic.do",
			data: "picaddress=" + response,
			cache:false,
			success: function(data) {
			//	var num = 0;
				var imid = data.id;
				uploadImageId = data.id; 
				uploadImageUrl = data.url;
				var atturl = data.url;
				$("#IMAGEID").append("<input type=\"hidden\" id=\"imageid\" name=\"imageid\" value=\"" + imid + "\" class=\"" + imid + "\"/>");
				$("#IMAGEURL").append("<img src=\"${ctx!}" + atturl + "\" alt=\"\" height=\"100\" width=\"150\" onclick=\"cancel(this)\" class=\"" + imid + "\">"+"&nbsp;");
				$("#IMAGEID").append("<input type=\"hidden\" id=\"imageurl\" name=\"imageurl\" value=\"" + atturl + "\" class=\"" + imid + "\"/>");
			},
			error: function(e) {
				alert(e);
			},
		});
	}
	
	//传完图片后点击图片移除图片路径和id
	function cancel(obj){
		if(confirm("确定删除图片？")){
			var classvalue = $(obj).attr("class");
			$("."+classvalue).remove();
		};
	}
	
	//分页函数
     function goPage(pageNow){
        showImages(pageNow);
     }
	     
	//图库选图
	function showImages(pageNow){
	    var costnumber = $("#costnumber").val();
	    var type = "tourline";
	    var type1 = "atractions";
	    var searcher = $("#searchbyTitle").val();
	    if(searcher=='undefined'){
	     searcher = "";
	    }else if(searcher==''){
	      searcher = "";
	    }
        $.ajax({
           type:"post",
            url:"${ctx!}/admin/slider/image.do",
            data:{pageNow:pageNow,costnumber:costnumber,usetype:type,usetype1:type1,search:searcher},
            dataType:"json",
            success: function(data){
              $('#IMG').empty();   //清空里面的所有内容
              var html = "<div class=\"input-group\">"+
						"<input type=\"text\" id=\"searchbyTitle\" placeholder=\"景点名称\" value=\"\" class=\"form-control no-right-border form-focus-purple\">"+
							"<span class=\"input-group-btn\">"+
							 "<button class=\"btn btn-purple\" onclick=\"showImages(${(page.pageNow)!})\" type=\"button\">搜索</button>"+
							"</span>"+
						"</div><br/>";
              $.each(data.allImages, function(i, e) {
                 html += "<div class=\"col-md-3 col-sm-4 col-xs-6\">"+
										"<div class=\"album-image\">"+
											"<a href=\"#\" class=\"thumb\" data-action=\"edit\">"+
												"<img src=\"${ctx!}"+e.url+"\" class=\"img-responsive\" />"+
												"<span>"+e.title+"</span>"+
											"</a>"+
										    "<div class=\"image-options\">"+
										    "</div>"+
										    "<div class=\"image-checkbox\">"+
											   "<input type=\"checkbox\" class=\"imgcheckbox\" name=\"checkbox\" value=\""+e.url+"\" id=\""+e.id+"\" onclick=\"selectidp(this)\"/>"+
										    "</div>"+
									    "</div>"+
								    "</div>";
			});
			var html2 = data.pageContent;
			$("#page").html(html2);
			$("#seleimg").hide();
			$("#IMG").html(html);
			
			//遍历checkbox和input,比对勾选复选框
			var imginput = $(".imginput");
			var imgcheckbox = $(".imgcheckbox");
			$.each(imgcheckbox,function(i,n){	
				$.each(imginput,function(r,m){
					if(n.id==m.id){
						$(n).prop("checked", true);
					};
				})
			});
            }
        });
	}
		
	//选择图片时添加或移除相应图片id
	function selectidp(obj){
		var imgid = $(obj).attr("id");
		var url = $(obj).attr("value");
		var im = $(obj).prop('checked');
		if(im==true){
//			alert(imgid);
			$(".imgcheckbox").prop('checked',false);
			$(obj).prop('checked',true);
			chooseImageId = imgid;
			chooseImageUrl = url;
			$("#IMAGEID").append("<input type=\"hidden\" class=\"imginput\" name=\"imageid\" value=\"" + imgid + "\" id=\"" + imgid + "\"/>");
			$("#IMAGEID").append("<input type=\"hidden\" class=\"" + imgid + "\" name=\"imageurl\" value=\"" + url + "\"/>");
		}else{
			chooseImageId = null;
			chooseImageUrl = null;
			$("#"+imgid).remove();
			$("."+imgid).remove();
		}
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
					<h1 class="title">博客添加</h1>
					<p class="description"></p>
				</div>
				<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						  <li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						  </li>
						  <li>
							<a href="${ctx!}/admin/blog/list.do">博客管理</a>
						  </li>
							<li class="active">
								<a href="${ctx!}/admin/blog/add.do"><strong>博客添加</strong></a>
							</li>
						</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form id="blogForm" role="form" class="form-horizontal" action="${ctx!}/admin/blog/save.do" method="post">
								<div class="form-group-separator"></div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">标题：</label>
									<div class="col-sm-10">
									   <input type="text" class="form-control" id="tittle" name="tittle">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="row">
									<div class="form-group">
											<label class="col-sm-2 control-label" for="field-4">上传图片：</label>
											<div class="col-sm-10">
											   <div id="fileQueue"></div>
											   <input type="file" class="col-sm-2 control-label" name="uploadify1" id="uploadify1" />
								        		<p>
			      									<a href="javascript:$('#uploadify1').uploadifyUpload()">上传</a>| 
			      									<a href="javascript:$('#uploadify1').uploadifyClearQueue()">取消上传</a>
								        		</p>
											</div>
										</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="row">		
										<div class="form-group">
											<label class="col-sm-2 control-label" for="field-2">上传图片预览<br/>(点击可删除):</label>
											<div class="col-sm-10" id="IMAGEURL"></div>
										</div>
										<div id="IMAGEID">
										</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="row">	
										<div class="form-group">
											<label class="col-sm-2 control-label" for="field-2">选择图片：</label>									
											<div class="col-sm-10" id="seleimg">
												<input type="button" value="选择图片" onclick="showImages();" class="btn btn-turquoise"/>
											</div>
											<div class="col-sm-9 gallery-right">
											<!-- Album Images -->
												<div class="album-images row" id="IMG"></div>
												<div id="page"></div>
											</div>
										</div>
								</div>
							   
							   <div class="form-group-separator"></div>
							   <div class="form-group">
									<label class="col-sm-2 control-label">状态</label>
									<div class="col-sm-10">
										<select class="form-control" name="status">
											<option value="0">已发布</option>
											<option value="2">草稿</option>
											<option value="1">等待审核</option>
										</select>
									</div>
							   </div>
								
								<div id="showIndex">
									<div class="form-group-separator"></div>
									<div class="form-group">
										<label class="col-sm-2 control-label">首页显示：</label>
										<div class="col-sm-10">
											<p>
												<label class="checkbox-inline">
													<input id="stickyInput" type="checkbox" name="sticky" value="1">显示
												</label>											
											</p>
										</div>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">是否推荐：</label>
									<div class="col-sm-10">
										<p>
											<label class="checkbox-inline">
												<input type="checkbox" name="isRecommended" value="1" checked="checked">推荐
											</label>											
										</p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">评论状态</label>
									<div class="col-sm-10">
										<p>
											<label class="checkbox-inline">
												<input type="radio" name="commentStatus" value="1" checked="checked">
												允许评论
											</label>
											<label class="checkbox-inline">
												<input type="radio" name="commentStatus" value="0">
												禁止评论
											</label>
										</p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label">共享状态</label>
									<div class="col-sm-10">
										<p>
											<label class="checkbox-inline">
												<input type="radio" name="pingStatus" value="1" checked="checked">
												允许共享
											</label>
											<label class="checkbox-inline">
												<input type="radio" name="pingStatus" value="0">
												禁止共享
											</label>
										</p>
									</div>
								</div>
								
								<#--
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">内容：</label>
									<div class="col-sm-10">
										<textarea class="form-control ckeditor" rows="10" name="content">
										
					                    </textarea>
									</div>
								</div>
								-->
								
								<div class="form-group" style="margin-left:80px">
									<label class="col-sm-1 control-label" for="field-5">内容：</label>
									<div class="col-sm-11">
										<script type="text/plain" id="content" style="width:100%"></script>
									</div>
								</div>
										
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">发布日期：</label>
									<div class="col-sm-8">
										<input type="text" id="beginDate" name="releaseTime" class="text Wdate" value="${currentDate?string('yyyy-MM-dd HH:mm:dd')}" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" />
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="type">博客类型:</label>
									<div class="col-sm-10">
										<select name="categoryId" class="form-control" id="type">
										  <#list blogCategoryList as blogCategory>
								              <option value="${blogCategory.id}">${blogCategory.name}</option>
								          </#list>
									    </select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">页面标题</label>								
									<div class="col-sm-10">
										<input type="text" name="page.metatitle" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-5">meta关键字</label>
									<div class="col-sm-10">
										<textarea class="form-control" name="page.metakeywords"  cols="5" id="field-5"></textarea>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-5">meta描述</label>
									<div class="col-sm-10">
										<textarea class="form-control" name="page.metadescription" cols="5" id="field-5"></textarea>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">模板路径</label>									
									<div class="col-sm-10">
										<input type="text" name="page.templateUrl" class="form-control" value="/front/blog.ftl" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">生成路径</label>									
									<div class="col-sm-10">
										<input type="text" name="page.filepath" class="form-control" id="generalFilePathInput" placeholder="">
									</div>
								</div>	
								
								<div style="display:none;">
									<textarea id="contentInput" name="content"></textarea>
									<input id="coverImageIdInput" type="hidden" name="coverImageId"/>
									<input id="coverImageUrlInput" type="hidden" name="coverImageUrl"/>	
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<input id="submitButton" type="submit" value="提交" class="btn btn-success">
										<input type="button" value="取消" class="btn btn-success" onClick="location.href='${ctx!}/admin/blog/list.do';">
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
	<script src="${ctx!}/assets/js/datepicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${ctx!}/assets/js/ckeditor/ckeditor.js"></script>
	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>
</body>
</html>