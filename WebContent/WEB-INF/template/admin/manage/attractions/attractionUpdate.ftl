<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>景点修改</title>
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
		//$("#IMAGEURL").empty();
		//$("#IMAGEID").empty();
		$("#uploadify1").uploadify({
			'uploader': '${ctx!}/assets/script/swf/uploadify.swf',
			// uploadify.swf 文件的相对路径
			'script': "${ctx!}/upload.do?path=images/attraction",
			//要提交到处理文件上传的Controller
			'cancelImg': '${ctx!}/assets/script/cancel.png',
			//选择文件到文件队列中后的每一个文件上的关闭按钮图标
			'queueID': 'fileQueue',
			//文件队列的ID，该ID与存放文件队列的div的ID一致
			'queueSizeLimit': 10,
			//队列中同时存在的文件个数限制 
			'fileDesc': 'jpg、gif、swf文件、png',
			//如果配置了以下的'fileExt'属性，那么这个属性是必须的           
			'fileExt': '*.jpg;*.gif;*.swf;*.png',
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

	});
	
	//上传图片后返回并添加图片路径和id
	function saveUserPictproal(response) {
		var costnumber = $("#costnumber").val();
		var namecn = $("#namecn").val();
		$.ajax({
			type: "POST",
			url: "${ctx!}/admin/attraction/savepic.do",
			data: "picaddress=" + response+"&namecn="+namecn,
			success: function(data) {
				var imid = data.id;
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
	
	//选择图片时添加或移除相应图片id
	function selectid(obj){
		var imgid = $(obj).attr("imgid");
		var url = $(obj).attr("value");
		var im = $(obj).prop('checked');
		if(im==true){
//			alert(imgid);
			$("#IMAGEID").append("<input type=\"hidden\" class=\"imginput\" name=\"imageid\" value=\"" + imgid + "\" id=\"" + imgid + "\"/>");
			$("#IMAGEID").append("<input type=\"hidden\" class=\"" + imgid + "\" name=\"imageurl\" value=\"" + url + "\"/>");
		}else{
			$("#"+imgid).remove();
			$("."+imgid).remove();
		}
	}
	
	//分页函数
      function goPage(pageNow){
        showImages(pageNow);
     }
     
//图库选图
function showImages(pageNow){
		var costnumber = $("#costnumber").val();
		var type = "atractions";
		 var searcher = $("#searchbyTitle").val();
		    if(searcher=='undefined'){
		     searcher = "";
		    }else if(searcher==''){
		      searcher = "";
		    }
        $.ajax({
           type:"post",
            url:"${ctx!}/admin/slider/image.do",
            data:{pageNow:pageNow,costnumber:costnumber,usetype:type,search:searcher},
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
											   "<input type=\"checkbox\" class=\"imgcheckbox\" name=\"checkbox\" value=\""+e.url+"\" imgid=\""+e.id+"\" onclick=\"selectid(this)\"/>"+
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
				var idd = $(n).attr("imgid");	
				$.each(imginput,function(r,m){
					if(idd==m.id){
						$(n).prop("checked", true);
					};
				})
			});
            }
        });
	}
	
	//点击图片并删除
	function deleteimg(obj){
		if(confirm("确定删除图片？")){
			var url = $(obj).attr("url");
			var attractionid = $("#attractionid").val();
			var imgclass = $(obj).attr("class");
			$.ajax({
				type:"post",
				url:"${ctx!}/admin/attraction/deletebyimageid.do",
				data:"url="+url+"&attractionid="+attractionid,
				success:function(){
					$("."+imgclass).remove();
				},
				error:function(e){
					alert("error");
				},
			});
		};
	}
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
					<h1 class="title">景点修改</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/attraction/list.do">景点管理</a>
								</li>
							<li class="active">
						
										<strong>景点修改</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			<div class="row">
				<div class="col-sm-12">
					
					<div class="panel panel-default">
						<div class="panel-body">
							<form role="form" class="form-horizontal" action="${ctx!}/admin/attraction/save.do" method="post">
								<#--
								<div class="form-group">
									<label class="col-sm-2 control-label">运营中心</label>									
									<div class="col-sm-10">
										<input type="text"  value="${(costlist.name)!}" class="form-control" id="field-1" readonly="readonly"/>
										<input type="hidden" name="costnumber" value="${(attraction.costnumber)!}" class="form-control" id="costnumber">
									</div>
								</div>
								-->
								<div class="form-group">
									<label class="col-sm-2 control-label">类型</label>									
									<div class="col-sm-10">
										<select class="form-control" name="attractiontypeid" id="attractiontypeid">
											<#list attractiontype as attractiontype>
            									<option value="${(attractiontype.id)!}" <#if attraction.attractiontypeid?? && attraction.attractiontypeid = attractiontype.id>selected="selected"</#if>>${(attractiontype.name)!}</option>
            								</#list>
										</select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">等级</label>									
									<div class="col-sm-10">
										<select class="form-control" name="level" id="level">
											<#list attractionLevel as attractionLevel>
            									<option value="${(attractionLevel.level)!}" <#if attraction.level?? && attraction.level = attractionLevel.level>selected="selected"</#if>>${(attractionLevel.description)!}</option>
            								</#list>
										</select>
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">英文名称</label>								
									<div class="col-sm-10">
										<input type="text" name="name" value="${(attraction.name)!}" class="form-control" id="field-1" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">中文名称</label>									
									<div class="col-sm-10">
										<input type="text" name="namecn" value="${(attraction.namecn)!}" class="form-control" id="namecn" placeholder="">
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">拼音首字母</label>									
									<div class="col-sm-10">
										<input type="text" name="namepy" value="${(attraction.namepy)!}" class="form-control" placeholder="例如a,b,c">
									</div>
								</div>
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label">城市</label>									
									<div class="col-sm-10">
										<select class="form-control" name="destinationid" id="destinationid">
											<#list destination as destination>
            									<option value="${(destination.id)!}" <#if attraction.destinationid?? && attraction.destinationid = destination.id>selected="selected"</#if>>${(destination.name)!}-${(destination.namecn)!}</option>
            								</#list>
										</select>
									</div>
								</div>
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#destinationid").select2({
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
									<label class="col-sm-2 control-label">热点</label>
									<div class="col-sm-10">
										<p>
											<label class="checkbox-inline">
												<input type="checkbox" name="ishot" value="1" <#if attraction.ishot?? && attraction.ishot=1>checked="checked"</#if>>
											</label>											
										</p>
									</div>
								</div>	
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">排序</label>									
									<div class="col-sm-10">
										<input type="text" name="sort" value="${((attraction.sort)?c)!}" class="form-control" id="field-2" placeholder="">
									</div>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">图片预览<br/>(点击可删除)</label>
									<#if img?? && img=1>									
										<div class="col-sm-10">
										    <#assign a=0>
											<#list imgurl as imgurl>
											     <#assign a=a+1>
												<img src="${ctx!}${imgurl!}" height="100px" width="150px" class="img${a}" url="${imgurl!}" onclick="deleteimg(this)"/>
											</#list>
										</div>
									</#if>
								</div>
								
								<div class="form-group-separator"></div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-4">上传图片</label>
									<div class="col-sm-10">
										<div id="fileQueue"></div>
										<input type="file" class="col-sm-2 control-label" name="uploadify1" id="uploadify1" />
										<#--
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
									<div class="col-sm-10" id="IMAGEURL"></div>
								</div>
								<div id="IMAGEID"></div>
								
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">选择图片</label>									
									<div class="col-sm-10" id="seleimg">
										<input type="button" value="选择图片" onclick="showImages();" class="btn btn-turquoise"/>
									</div>
									<div class="col-sm-9 gallery-right">
									<!-- Album Images -->
									<div class="album-images row" id="IMG"></div>
									<div id="page"></div>
								</div>
								
								<div class="form-group-separator"></div> 
								<div class="form-group">
									<div class="col-sm-10" align="center">
										<#if img?? && img=1>
											<#assign a=0>
											<#list imgurl as imgurl>
												<#assign a=a+1>
												<input name="imageurl" type="hidden"  value="${imgurl!}"  class="img${a}"/>	
											</#list>
										</#if>
										<input name="imageid" type="hidden" id="imageid" value=""/>
										<input name="id" type="hidden" id="attractionid" value="${(attraction.id)!}">
										<input type="submit" name="button" id="button" value="完成" class="btn btn-secondary btn-sm btn-icon icon-left">
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


	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/datatables/dataTables.bootstrap.js"></script>
	<script src="${ctx!}/assets/js/datatables/yadcf/jquery.dataTables.yadcf.js"></script>
	<script src="${ctx!}/assets/js/datatables/tabletools/dataTables.tableTools.min.js"></script>
	<script src="${ctx!}/assets/js/select2/select2.min.js"></script>


	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>