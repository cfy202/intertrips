<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>修改线路</title>

	<!--
	 <link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">
	-->
  	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css"/>
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
			var editor =  UE.getEditor('notice');
		});
	</script>
	
	<script type="text/javascript">
	$(document).ready(function() {
		//$("#IMAGEURL").empty();
		//$("#IMAGEID").empty();
		$("#uploadify1").uploadify({
			'uploader': '${ctx!}/assets/script/swf/uploadify.swf',
			// uploadify.swf 文件的相对路径
			'script': "${ctx!}/upload.do?path=images",
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
		
		//点击添加接送机
    	$(".check_more_one").click(function () {
    	      var $airportTable = $("#airportTable");
		      var $airportTitle = $("#airportTitle");
			  var trHtml = 
					"<tr class=\"airportTr\">"+
						"<th>"+
							"<input type=\"text\"  class=\"form-control\" name=\"airportTitle\" data-validate=\"required\"\/>"+
						"<\/th>"+
						"<td>"+
							"<input type=\"text\"  class=\"form-control\" name=\"airportPrice\" digits=\"true\" value=\"0\"\/>"+
						"<\/td>"+
						"<td>"+
							"<a href=\"javascript:;\" id=\"deleteProduct\" class=\"btn btn-white\" onclick=\"deleteAirport(this)\">删除<\/a>"+
						"<\/td>"+
					"<\/tr>";
			 $airportTitle.show();
			 $airportTable.append(trHtml);
        })
	});
	
	//删除接送机
	function deleteAirport(nowtr){
	     if(confirm("确定删除吗？")){
		     $(nowtr).closest("tr").remove();
			if ($("#airportTable").find("tr.airportTr").size() <= 0) {
		    	$("#airportTitle").hide();
			}
	     }
	}
	
	//上传图片后返回并添加图片路径和id
	function saveUserPictproal(response) {
		var costnumber = $("#costnumber").val();
		$.ajax({
			type: "POST",
			url: "${ctx!}/admin/tourline/savepic.do",
			data: "picaddress=" + response+"&costnumber="+costnumber,
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
		var type = "tourline";
		var type1 = "atractions";
		var tourlineid = '${(tourline.id)!}'
        $.ajax({
           type:"post",
            url:"${ctx!}/admin/slider/imageTourlineUp.do",
            data:{pageNow:pageNow,costnumber:costnumber,tourlineid:tourlineid},
            dataType:"json",
            success: function(data){
              $('#IMG').empty();   //清空里面的所有内容
              var html = "";
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
			var tourlineId = $("#id").val();
			var productId = $("#productId").val();
			var imgclass = $(obj).attr("class");
			$.ajax({
				type:"post",
				url:"${ctx!}/admin/tourline/deletebyimageid.do",
				data:"url="+url+"&tourlineId="+tourlineId,
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
<script type="text/javascript">
	$(document).ready(function() {
	    var costnumber = $("#costnumber").val();
	    var region = "${(tourline.regionid)}";
	    updateRegion(costnumber,region);
		$("#uploadify").uploadify({
				        'uploader' :'${ctx!}/assets/script/swf/uploadify.swf',
				        'script' : '${ctx!}/upload.do?path=images',
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
				          $("#imgshow").attr("width","200");
				          $("#imgshow").attr("height","150");
				        }
				    }); 
				   $("#costnumber").change(function(){
				   			validateProductNo();
							 var costnumber = $("#costnumber").val();
							    var region = "${(tourline.regionid)}";
							    updateRegion(costnumber,region);
							    <#--
							    updateSelf(costnumber);
							    -->
											}); 
				}); 

		  function updateRegion(costnumber,region){
		  
		   $.ajax({
			type: "POST",
			url: "${ctx!}/admin/tourline/updateRegion.do",
			data: "costnumber="+costnumber+"&type=1",
			cache:false,
			success: function(data) {
			   $("#regionid").empty();
			   $.each(data, function(i, des) {
			    if(des['id']==region){

			    	$("#regionid").append("<option url =\""+des['url']+"\" value=\""+des['id']+"\" selected=\"selected\">"+des['levelstr']+""+des['name']+"</option>");
			    	$("#regionid").val("${(tourline.regionid)}").trigger("change");

			    }else{
                	$("#regionid").append("<option url =\""+des['url']+"\" value=\""+des['id']+"\">"+des['levelstr']+""+des['name']+"</option>");
                	//$("#regionid").val("${(tourline.regionid)}").trigger("change");
			    }
			    
			});
			},
			error: function(e) {
				alert(e);
			},
		});
	 }
	 
	 <#--	  
	 function updateSelf(costnumber){
		   $.ajax({
			type: "POST",
			url: "${ctx!}/admin/tourline/updateSelf.do",
			data: "costnumber="+costnumber,
			cache:false,
			success: function(data) {
			   $.each(data, function(i, self) {
			    var id = self["id"];
                $("#selfpay").append('<label><input type="checkbox" name ="self" value="'+self["id"]+'" class="cbr"><span>'+self["name"]+'</span></label><br />');
                <#if tourlinese??>
                <#list tourlinese as item>
                 var ss = "${item.selfpayid}";
                 if(ss==id){
                 $("input:checkbox[value='"+id+"']").attr('checked','true');
                 }
                </#list>
                </#if>
			});
			},
			error: function(e) {
				alert(e);
			},
		});
		 }	
	-->
		   
   </script>
   <script type="text/javascript">
       var productNoValidated = true;
		$(function(){
		            //获取选中值
		        $('#rootwizard').submit(function(){
		           if(!productNoValidated){
		        		alert('必须输入正确的产品系统编号！');
		        		return false;
		        	}
		           //处理是否热推
		            if($("input[name='ishot']").is(':checked')){
		               $("#pishot").val(1);
		            }
		            //处理是否显示
		             if($("input[name='isshow']").is(':checked')){
		               $("#pisshow").val(1);
		            }
		            //处理是否首页显示
		             if($("input[name='indexShow']").is(':checked')){
		               $("#pindexShow").val(1);
		            }
		            var comit = false;
		            var attrlist = "";
		            var selflist ="";
		            $('input[name="attrs"]:checked').each(function(){
		                var sfruit=$(this).next().html();
		                attrlist +=","+sfruit;
		                });
		                $("#attractionlist").val(attrlist.substring(1));
		                $('input[name="self"]:checked').each(function(){
		                var sfruit=$(this).next().html();
		                selflist +=","+sfruit;
		                });
		                $("#selfpaylist").val(selflist.substring(1));
		               
		               $("#offeredTo1").val(startcitys);
		               $("#fromTheCorporationTo1").val(endcitys);
		           var dename  = $("input[name='denames']").length; 
		           var region =  $("#regionid").val();
		           var days = $("#field-2").val();
		           if(region==-1){
		              alert("请选择线路分类！");
		           }else if(dename==0){
		             alert("请选择目的地！");
		           }else if(days==null||days==''){
		             alert("行程天数为必填项！");
		           }else if(isNaN(val)){
		             alert("天数必须是整数！");
		            }else if(!productNoValidated){ 
		            	alert("必须输入正确的产品系统编号！");
		           }else{
		            tomit= ture;
		           }
		          return comit;
		         });    
		         
		    })
		    
		    
</script> 
<script type="text/javascript"> 
//校验产品编号是否存在			
	 function validate(){
	 
	  var numbers = $("#door_code").val();
	  	if(numbers == "${(product.code)!}"){
	  		$("#mess").text("");	
	  		return;
	  	}
		   $.ajax({
			type: "POST",
			url: "${ctx!}/admin/tourline/validateCode.do",
			data: {"numbers":numbers},
			dataType: "json",
			cache:false,
			success: function(data) {
			  if(data){
			    $("#mess").text("该产品编号没有被占用！");	
			  }else{
			    $("#mess").text("该产品编号已经存在！");
			    $("#door_code").empty();
			    $("#door_code").focus();
			  }
			
			},
			error: function(e) {
				alert(e);
			},
		});
		
		 }	
		 
	var validateProductNo = function(){
		var $productNoInput = $("#productNoInput");
		var inputProductNo = $productNoInput.val().trim();
		var costnumber = $("#costnumber").val();
		if(inputProductNo == '' || (inputProductNo == '${(product.productNo)!}'.trim() &&  costnumber == "${product.costnumber}")){
			productNoValidated = true;
			$("#productNoNotice").text("");
			return;
		}
		$.post("${ctx!}/admin/tourline/validateProductNo.do",{"productNo":inputProductNo,"costnumber":costnumber},function(result){
			//验证通过
			if(result == 1){
				$("#productNoNotice").text("该产品系统编号没有被占用！");
				productNoValidated = true;
			}else{
				$("#productNoNotice").text("该产品系统编号在当前运营中心下已存在！");
				productNoValidated = false;
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
					<h1 class="title">线路修改</h1>
				</div>
				
					<div class="breadcrumb-env">
					
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
						
										<a href="${ctx!}/admin/tourline/list.do">线路管理</a>
								</li>
							<li class="active">
						
										<strong>修改线路</strong>
								</li>
								</ol>
								
				</div>
					
			</div>
			
			
			
			<div class="vspacer v3"></div>
			
			
			<!-- Form wizard with validation starts here -->
			
			
			<script type="text/javascript">
				function pushine(){
				 var name = $("#prname").val();
				 $("#tourname").val(name);
				 $("#metatitle").val(name);
				 
				
				}
			</script>
			
			<form role="forl" action="${ctx!}/admin/tourline/save.do" id="rootwizard" method="post" class="form-wizard validate">
				
				<ul class="tabs">
					<li class="active">
						<a href="#fwv-1" data-toggle="tab">
							产品信息
							<span>1</span>
						</a>
					</li>					
					<li>
						<a href="#fwv-2" data-toggle="tab">
							线路信息
							<span>2</span>
						</a>
					</li>
					<li>
						<a href="#fwv-3" data-toggle="tab">
							关联信息
							<span>3</span>
						</a>
					</li>
					<li>
						<a href="#fwv-4" data-toggle="tab">
							页面信息
							<span>4</span>
						</a>
					</li>
				</ul>
				
				<div class="progress-indicator">
					<span></span>
				</div>
				
				<div class="tab-content no-margin">
					
					<!-- Tabs Content -->
					<div class="tab-pane with-bg active" id="fwv-1">
					<div class="row">
						   <div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="street">产品名称：</label><i class="fa-bell-o"></i>
									<input class="form-control" name="name" value="${(product.name)!}" id="prname" onBlur="pushine()" data-validate="required" placeholder="请填写产品名称" />
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="door_no">产品编号：</label><i class="fa-bell-o"></i><span id="mess"></span>
									<input class="form-control" name="code" onBlur="validate()" value="${(product.code)!}" id="door_code" data-validate="required" digits="true" minlength="2" maxlength="8" placeholder="请填写产品编号" />
								</div>
							</div>
					</div>
					<div class="row">
					        <div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="door_no">产品积分：</label><i class="fa-bell-o"></i>
									<input class="form-control" name="score"  value="${((product.score)?c)!}" data-validate="required" digits="true"  placeholder="请填写产品积分" />
								</div>
							</div>
					        <div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="street">产品系统编号:</label><i class="fa-bell-o"></i><span id="productNoNotice"></span>
									<input class="form-control" id="productNoInput" value="${(product.productNo)!}"  name="productNo" placeholder="请填写产品系统编号" onBlur="validateProductNo(this);"/>
								</div>
						     </div>						    
					
					</div>
					<div class="row">
					   <#if (cost?size==1)>
						<input type="hidden" name="costnumber" id="costnumber" value="${cost[0].id}">
						<#else>
							 <div class="col-md-4">
								<div class="form-group">
									<label class="control-label" for="state">选择运营中心</label>
										<select name="costnumber" id="costnumber" class="selectboxit">
											   <#list cost as cost>
											    <option value="${cost.id}" <#if product.costnumber??&& product.costnumber=cost.id>selected="selected"</#if> >${cost.name}</option>
											   </#list>
										</select>
								</div>
							</div>
					</#if>
					</div>
					<div class="form-group-separator"></div>
					
					  <#if admin.username??&&admin.username='admin'>
						<div class="row">	
							<div class="col-md-6">
								<div class="form-group">
								    <label class="control-label">状态：</label>
									<label class="control-label">是否热推</label>
									<input type="checkbox" name ="ishot" value="" id="pishot"   class="iswitch iswitch-purple" onclick="return false;"<#if product.ishot??&&product.ishot=1 >checked</#if> >
									<label class="control-label">是否显示</label>
								    <input type="checkbox" name ="isshow" value="" id="pisshow"   class="iswitch iswitch-purple" onclick="return false;" <#if product.ishot??&&product.isshow=1 >checked</#if>>
									<label class="control-label">是否首页显示 </label>
								    <input type="checkbox" name ="indexShow"  value="" id="pindexShow" class="iswitch iswitch-purple" onclick="return false;" <#if product.ishot??&&product.indexShow=1 > checked</#if> >
								</div>
							</div>
						</div>
						<div class="form-group-separator"></div>
						<#else>
						<div class="row">	
							<div class="col-md-6">
								<div class="form-group">
								    <label class="control-label">状态：</label>
									<label class="control-label">是否热推</label>
									<input type="checkbox" name ="ishot" value="" id="pishot"   class="iswitch iswitch-purple"  <#if product.ishot??&&product.ishot=1 >checked</#if> >
									<label class="control-label">是否显示</label>
								    <input type="checkbox" name ="isshow" value="" id="pisshow"   class="iswitch iswitch-purple" <#if product.ishot??&&product.isshow=1 >checked</#if> >
									<label class="control-label">是否首页显示 </label>
								    <input type="checkbox" name ="indexShow"  value="" id="pindexShow" class="iswitch iswitch-purple" <#if product.ishot??&&product.indexShow=1 > checked</#if> >
								</div>
							</div>
						</div>
						<div class="form-group-separator"></div>
					</#if>
					<div class="row">
						<div class="col-md-12">	
							<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">图片预览<br/>(点击可删除)：</label>
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
						</div>
					 </div>
					 <div class="form-group-separator"></div>
					 <div class="row">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="field-4">上传图片：</label>
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
					</div>
					<div class="form-group-separator"></div>
					<div class="row">	
						<div class="form-group">
							<label class="col-sm-2 control-label" for="field-2">上传图片预览<br/>(点击可删除)：</label>
							<div class="col-sm-10" id="IMAGEURL"></div>
						</div>
						<div id="IMAGEID"></div>
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
					
				    <div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label" for="about">产品描述：</label>
							    <textarea class="form-control" name="briefinfo" id="metadescription" placeholder="填写产品描述">${(product.briefinfo)!}</textarea>
							</div>
						</div>
					</div>
				</div>
				
					
				<div class="tab-pane with-bg" id="fwv-2">
					<div class="row">
						<div class="form-group">
							<label class="col-sm-1 control-label">选择分类：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa-bell-o"></i></label>									
							<div class="col-sm-11">
								<select class="form-control" id = "regionid" name = "regionid">
											
								</select>
							</div>
						</div>
						<script type="text/javascript">
								jQuery(document).ready(function($)
										{
											$("#regionid").select2({
												placeholder: '请选择分类',
												allowClear: false
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
											
											
											$("#regionid").change(function(){
											  var tourcode = $("#door_code").val();
											  var url = $("#regionid").find("option:selected").attr("url").replace(".htm","/tour"+tourcode+".htm");
											  $("#filepath").val(url);
											});
											
										});
						 </script>
				   </div>
				   <br/>
				   <div class="form-group-separator"></div>
				   <div class="row">
					   <div class="form-group">
							<label class="col-sm-1 control-label" for="field-2">线路名称：</label>									
							<div class="col-sm-11">
								<input type="text" name="tourname" value="${(tourline.tourname)!}"  class="form-control" id="tourname" placeholder="">
							</div>
					   </div> 
				  </div>
				  <br/>
				  <div class="form-group-separator"></div>
					<div class="row">
						<div class="form-group">
							<label class="col-sm-1 control-label">行程天数：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa-bell-o"></i></label>
								<div class="col-sm-11">
									<input type="text" name="days"  class="form-control" id="field-2" value="${(tourline.days)!}"  data-validate="required" digits="true"  placeholder="">
								</div>
						</div>
					</div>
					<br />
					<div class="form-group-separator"></div>
					 <div class="row">
								<div class="form-group">
									<label class="col-sm-1 control-label">抵达时差：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa-bell-o"></i></label>
									<div class="col-sm-11">
                                        <select class="form-control"   name = "interval">
                                            <option value="0" <#if tourline.interval??&&tourline.interval=0>selected ="selected"</#if>>无延时</option>
                                            <option value="1" <#if tourline.interval??&&tourline.interval=1>selected ="selected"</#if>>延时一天</option>
                                            <option value="2" <#if tourline.interval??&&tourline.interval=2>selected ="selected"</#if>>延时两天</option>
                                        </select>
									</div>
								</div>
							</div>
						 <br />
						<div class="form-group-separator"></div>	
					<div class="row">
							<div class="form-group">
								<label class="col-sm-1 control-label">选择星级：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa-bell-o"></i></label>									
									<div class="col-sm-11">
									   <select class="form-control"   name = "rate">
										 <option value="1" <#if tourline.rate??&&tourline.rate=1>selected ="selected"</#if>>一颗星</option>
										 <option value="2" <#if tourline.rate??&&tourline.rate=2>selected ="selected"</#if>>两颗星</option>
										 <option value="3" <#if tourline.rate??&&tourline.rate=3>selected ="selected"</#if>>三颗星</option>
										 <option value="4" <#if tourline.rate??&&tourline.rate=4>selected ="selected"</#if>>四颗星</option>
										 <option value="5" <#if tourline.rate??&&tourline.rate=5>selected ="selected"</#if>>五颗星</option>
										</select>
									</div>
							</div>
					</div> 
				     <br />
					<div class="form-group-separator"></div> 
						<div class="row">
							<div class="form-group">
								<label class="col-sm-1 control-label" for="field-5">行程亮点：</label>
								<div class="col-sm-11">
								   <textarea class="form-control" name="highlights" id="metadescription" rows="6"
	                                   placeholder="填写线路亮点" >${(tourline.highlights)!}</textarea>
								</div>
							</div>
						</div>
					<br />
					<div class="row">
						   <div class="col-md-6">
							       <div class="form-group" id = "opationSelect">
										<label class="col-sm-2 control-label" for="tagsinput-1">选择包含项</label>
											<div class="col-sm-9">
														
												<script type="text/javascript">
											    	jQuery(document).ready(function($)
															{
																$("#multi-selectA").multiSelect({
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
														<select class="form-control" multiple="multiple" id="multi-selectA" name="include">
															  <#list serviceItemList as serviceItem>
																 <option value="${serviceItem.id}" <#if (tourline.include?? && tourline.include?index_of("${serviceItem.id}")!=-1)>selected="selected"</#if> >${serviceItem.tittle}</option>
															 </#list>
															
														</select>
														
													</div>
												</div>
										</div>
										<div class="col-md-6">
											<div class="form-group" id = "opationSelect">
										<label class="col-sm-2 control-label" for="tagsinput-1">选择不包含项</label>
											<div class="col-sm-9">
														
												<script type="text/javascript">
											    	jQuery(document).ready(function($)
															{
																$("#multi-selectB").multiSelect({
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
														<select class="form-control" multiple="multiple" id="multi-selectB" name="exclude">
															  <#list serviceItemList as serviceItem>
																 <option value="${serviceItem.id}" <#if (tourline.exclude?? && tourline.exclude?index_of("${serviceItem.id}")!=-1)>selected="selected"</#if> >${serviceItem.tittle}</option>
															 </#list>
															
														</select>
														
													</div>
												</div>	
						           </div>
						   
					    </div>
					<br />
					<div class="form-group-separator"></div>
					<div class="row">
						<div class="form-group">
								<label class="col-sm-1 control-label" for="field-5">航空公司说明：</label>
								<div class="col-sm-11">
	                                <textarea class="form-control" name="flightnotice" id="flightnotice" rows="6" >${(tourline.flightnotice)!}</textarea>
								</div>
						</div>
					</div>
					<br />
					<div class="row">
						<div class="form-group">
								<label class="col-sm-1 control-label" for="field-5">注意事项：</label>
								<div class="col-sm-11">
								<script type="text/plain" id="notice" name="notice" style="width:100%">${(tourline.notice)!}</script>
								
								</div>
						</div>
					</div>
					<br />
					<div class="form-group-separator"></div>
					<div class="row">
						<div class="form-group">
							<label class="col-sm-1 control-label" for="uploadify">图像：</label>
							<div class="col-sm-11">
								<div>
								    <img src="${ctx!}${(tourline.mapurl)!}"  id="imgshow">
								</div>
								<div>
							    	<input type="file" class="form-control" id="uploadify" name="uploadify">
									<input type="hidden" name="mapurl" id="changephoto" value="${(tourline.mapurl)!}"/>
								</div>
							</div>
						</div>
					</div>
						<!--
						<div class="form-group-separator"></div>
						<div class="row">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-2">排序</label>									
									<div class="col-sm-10">
										<input type="text" name="sort" value="${(tourline.sort)!}" class="form-control" id="field-2" placeholder="">
									</div>
								</div> 
						</div>
						-->
						<input type="hidden" name="sort" value="${(tourline.sort)!}" class="form-control" id="field-2" placeholder="">
					</div>
					
					<div class="tab-pane with-bg" id="fwv-3">
						<br />
						<div class="row">
						    <div class="form-group">
									<label class="col-sm-2 control-label">选择城市<i class="fa-bell-o"></i></label>									
									<div class="col-sm-10">
										<select class="form-control" name = "destinationsmo"  id="s2example-2">
										  <#list delist as delist>
										     <#if destinations??>
										     	<#assign isChoose = false>
												<#list destinations as destinations >
												   <#if delist.id == destinations.id>
	          									    	<#assign isChoose = true>	
												   </#if>
									 		    </#list>
									 		    <#if !isChoose>
									 		     	<option id="${(delist.id)!}" value="${(delist.id)!}" name="${(delist.name)!}" namecn="${(delist.namecn)!}" >${(delist.name)!}-${(delist.namecn)!}</option>
									 		    </#if>
									 	 	 <#else>
									 	         <option id="${(delist.id)!}" value="${(delist.id)!}" name="${(delist.name)!}" namecn="${(delist.namecn)!}">${(delist.name)!}-${(delist.namecn)!}</option>
									 	  	 </#if>
										  </#list>	
										</select>
									</div>
						    </div>
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#s2example-2").select2({
										//		placeholder: '选择城市',
												allowClear: false
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
											$("#s2example-2").change(function(){
											 ids = $("#s2example-2").val(); 
											 if(ids==""||ids==null){
											
											 }else{
											 var $chooseOption = $("#s2example-2").find("option:selected");
											 var checkText = $chooseOption.text();
											 var name = $chooseOption.attr("name");
											 var namecn = $chooseOption.attr("namecn");
											  <#list selflist as selflist>
											   var selfname = '${(selflist.city)!}';
											   var id = '${(selflist.id)!}';
											   if(selfname==name){
											     $("#selfpay").append("<label id=\"Se"+ids+"\"><input type=\"checkbox\" name =\"self\" value=\"${(selflist.id)!}\" class=\"cbr\"><span>${(selflist.name)!}</span></label><br />");
											     <#if tourlinese??>
											     <#list tourlinese as item>
									                 var ss = "${item.selfpayid}";
									                 if(ss==id){
									                 $("input:checkbox[value='"+id+"']").attr('checked','true');
									                 }
									              </#list>
												  </#if>									              
											   }
											 </#list>
											 html ="<a data-action=\"trash\" href=\"javascript:remove('"+ids+"');\" id=\""+ids+"\" >"+name+"<i class=\"fa-trash\"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;"+
													 "<input name=\"destions\" type=\"hidden\" id=\"in"+ids+"\" value=\""+ids+"\">"+
													 "<input name=\"denames\" type=\"hidden\" id=\"de"+ids+"\" value=\""+name+"\">";
											
											 $("#citys").html($("#citys").html()+html);
											 $("#destinationlist").val(checkText);
											  updatedestination(ids);
											   $("#s2example-2 option[value='"+ids+"']").appendTo($("#destinationCache")); 
                                               var starts = '${(tourline.startDestination)!}';
											   var ends ='${(tourline.endDestination)!}';
											    if(starts.indexOf(name) != -1)
													$("#offeredTo").append('<option namecn="' + name + '" value="' + name + '" selected>' + name + '</option>').trigger('change');
												else
													$("#offeredTo").append('<option namecn="' + name + '" value="' + name + '">' + name + '</option>');
												if(ends.indexOf(name) != -1)
													$("#fromTheCorporationTo").append('<option namecn="' + name + '" value="' + name + '" selected>' + name + '</option>').trigger('change');
												else
													$("#fromTheCorporationTo").append('<option namecn="' + name + '" value="' + name + '">' + name + '</option>');
												}
											}) 
										});
										
										function updatedestination(ids){
										  $.ajax({
											   type: "POST",
											   url: "${ctx!}/admin/tourline/destUplist.do",
											   dataType:"json",
											   data: "ids=" + ids ,
											   success: function(msg){
										         var htmlx = "wew";
										         var htmly = "weweww";
										         var html = "";
												 $.each(msg, function (index, entry) {
										         var x = 1;
										         var y = 1;
												  html +="<div class=\"row\" id=\"ah"+entry['id']+"\" >"
												     
												    $.each(entry['attractions'], function (nub, attr) {
												     
												      if(x==1){
												       html +="<div class=\"form-group\" >"+
																"<label class=\"col-sm-2 control-label\">"+entry['namecn']+"--景点</label>"+
																"<div class=\"col-sm-10\">"+
																"<div class=\"col-sm-10\">";
																x++;
												      }
												      html +="<label>"+
												             "<input type =\"checkbox\" name =\"attrs\" value= \""+attr['id']+"\"><span>"+attr['name']+"</span></label>"+
															 "&nbsp;&nbsp;";
												    });
												    html +="</div></div></div>";
												    $.each(entry['hotels'], function (index, hotel) {
												      if(y==1){
												       html +="<div class=\"form-group\" >"+
																"<label class=\"col-sm-2 control-label\">"+entry['name']+"--酒店</label>"+
																"<div class=\"col-sm-10\">"+
																"<div class=\"col-sm-10\">";
																y++;
												      }
												      
												      html +="<label>"+
												             "<input type = \"checkbox\" name = \"hotels\" value =\""+hotel['id']+"\" >"+hotel['hotelname']+"</label>"+
															 "&nbsp;&nbsp;";
												    
												    });
												    html +="</div></div></div>";
												    html +="</div>";
												 });
												 html +="<div class=\"form-group-separator\"></div>";
												$("#mores").html($("#mores").html()+html);
												
											   }
											}); 
										}
										function remove(id){
										 var startcity = $("#offeredTo").val();
										 var endcity = $("#fromTheCorporationTo").val();
										 $("#Se"+id).text("");//清除对应目的地的自费
										var $appenderDestination = $("#destinationCache option[value='"+ id +"']");
										$appenderDestination.appendTo($("#s2example-2"));
										var destinationName = $appenderDestination.attr("name");
											if(startcity != null && startcity.indexOf(destinationName)!=-1){
											  var x = startcity.indexOf(destinationName);
											  startcity.splice(x,1);
											  $("#offeredTo").val(startcity).trigger("change");
											}
											if(endcity != null && endcity.indexOf(destinationName)!=-1){
											  var x = endcity.indexOf(destinationName);
											  endcity.splice(x,1);
											  $("#fromTheCorporationTo").val(endcity).trigger("change");
											}
										 $("#offeredTo option[value='"+destinationName+"']").remove();
										 $("#fromTheCorporationTo option[value='"+destinationName+"']").remove();
										 $("a").remove("#"+id);
										 $("div").remove("#ah"+id);
										 $("input").remove("#in"+id);
										 $("input").remove("#de"+id);
									  }
								</script>
					</div>
					</br>
					<div class="form-group-separator"></div>
					<div class="row">
					   <div class="form-group">
						    <label class="col-sm-2 control-label">城市 </label>
							<div class="col-sm-10" id="citys">
						  <#if destinations??>
							<#list destinations as destinations >
							       <a data-action="trash" href="javascript:remove('${(destinations.id)!}');" id="${(destinations.id)!}" >${(destinations.name)!}<i class="fa-trash"></i></a>
								   <input name="destions" type="hidden" id="in${(destinations.id)!}" value="${(destinations.id)!}">
								   <input name="denames" type="hidden" id="de${(destinations.id)!}" value="${(destinations.name)!}">
				 		     </#list>
				 		     </#if>
							</div>
						</div>
					</div>
					<div class="form-group-separator"></div>
					<div class="row">
						<div id = "mores">
						<#if destinations??>
						  	<#list destinations as destinations >
								<#assign x=1>
							    <#assign y=1> 
								<div class="row" id="ah${(destinations.id)!}" >
								  <#if (destinations.attractions)?has_content>
									<#list destinations.attractions as attractions>
									    <#if x==1>
									    <div class="form-group" >
											<label class="col-sm-2 control-label">${(destinations.name)!}--景点</label>
											<div class="col-sm-10">
											<div class="col-sm-10">
											<#assign x=10000>
									    </#if>
									    <label><input type ="checkbox" name ="attrs" value= "${(attractions.id)!}" <#if (attrs?? && attrs?index_of("${(attractions.id)!}")!=-1)>checked="checked"</#if> ><span>${(attractions.name)!}</span></label>
									 </#list>
								 </div></div></div>
								
								</#if>
								<#if (destinations.hotels)?has_content>
								    <#list destinations.hotels as hotel>
									    <#if y==1>
									    <div class="form-group" >
											<label class="col-sm-2 control-label">${(destinations.name)!}--酒店</label>
											<div class="col-sm-10">
											<div class="col-sm-10">
											<#assign y=10000>
									    </#if>
									    <label><input type ="checkbox" name ="hotels" value= "${(hotel.id)!}"  <#if (checkedHot?? && checkedHot?index_of("${(hotel.id)!}")!=-1)>checked="checked"</#if>  ><span>${(hotel.hotelname)!}</span></label>
									 </#list>
								 </div></div></div>
								</#if>
								</div>
								<div class="form-group-separator"></div>
							</#list>
					      </#if>
						</div>
					</div>
					<#if tourlineSE??>
					<div class="form-group-separator"></div>
					<div class="row">
						<div class="form-group">
							<label class="col-sm-2 control-label">自费选项</label>
								<div class="col-sm-10">
								      <div class="form-block" id = "selfpay">
								         <#list selflist as selflist>
								          <label><input type="checkbox" name ="self" value="${(selflist.id)!}" class="cbr" 
								          <#list tourlineSE as tourlineSE>
								           <#if selflist.id=tourlineSE.selfpayid >checked="checked"</#if>
								         </#list>    
								            ><span>${(selflist.name)!}</span></label><br />
								         
								          
								         </#list>
									  </div>
								</div>
						</div>
					</div>
					</#if>
				<div class="form-group-separator"></div>
						<div class="row">
						<div class="form-group">
									<label class="col-sm-2 control-label">参团城市：</label>									
									<div class="col-sm-10">
										<select class="form-control" name="startDestination" id = "offeredTo" multiple >
										   <#if destinations??>
										     <#list destinations as destinations >
											 <option value="${(destinations.name)!}"<#if (tourline.startDestination??&&tourline.startDestination?index_of("${destinations.name}")!=-1)>selected="selected"</#if>>${(destinations.name)!}</option>
											</#list>
										   </#if>
										</select>
									</div>
								</div>
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#offeredTo").select2({
										//		placeholder: '请选择参团城市',
												allowClear: true
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
										});
								</script>
							
						 </div> 
						
						 <div class="form-group-separator"></div> 
						 <div class="row">
						<div class="form-group">
									<label class="col-sm-2 control-label">离团城市：</label>									
									<div class="col-sm-10">
										<select class="form-control" name = "endDestination" id = "fromTheCorporationTo" multiple >
										   <#if destinations??>
										     <#list destinations as destinations >
											 <option value="${(destinations.name)!}"<#if (tourline.endDestination??&&tourline.endDestination?index_of("${destinations.name}")!=-1)>selected</#if>>${(destinations.name)!}</option>
											</#list>
										   </#if>
											
										</select>
									</div>
								</div>
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#fromTheCorporationTo").select2({
										//		placeholder: '请选择离团城市',
												allowClear: true
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
										});
								</script>
							
						 </div>
					
					
					</br>
						<div class="form-group-separator"></div> 
						<div class="row">
						<div class="form-group">
									<label class="col-sm-2 control-label">出发城市：</label>									
									<div class="col-sm-10">
										<select class="form-control" name="departureIds" id = "departureCity" multiple >
											 <#list departures as departures>
												 <option value="${departures.id}" <#if (product.departureIds??&&product.departureIds?index_of("${departures.id}")!=-1)>selected</#if>>${departures.name}</option>
											 </#list>
										</select>
									</div>
								</div>
								
								<script type="text/javascript">
										jQuery(document).ready(function($)
										{
											$("#departureCity").select2({
										//		placeholder: '请选择出发城市',
												allowClear: true
											}).on('select2-open', function()
											{
												// Adding Custom Scrollbar
												$(this).data('select2').results.addClass('overflow-hidden').perfectScrollbar();
											});
										});
								</script>
							
						 </div>
						<div class="form-group-separator"></div> 
						
						
				</div>

					<div class="tab-pane with-bg" id="fwv-4">
						<div class="row">
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="full_name">页面标题</label>
									<input class="form-control" name="metatitle" value="${(page.metatitle)!}" id="metatitle"  placeholder="请填写页面标题" />
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="birthdate">搜索关键字</label>
									<input class="form-control" name="metakeywords" value="${(page.metakeywords)!}" id="metakeywords"   placeholder="请填写页面搜索关键字" />
								</div>
							</div>
							
						</div>
						
						<div class="row">
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="full_name">页面路径</label>
									<input class="form-control" name="filepath" value="${(page.filepath)!}" id="filepath"  placeholder="请填写页面访问路径" />
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="form-group">
									<label class="control-label" for="birthdate">模板路径</label>
									<input class="form-control" name="templateUrl" value="${(page.templateUrl)!}" id="templateUrl"  placeholder="请填写页面模板路径" />
								</div>
							</div>
							
						</div>
						
						<div class="row">
							
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" for="about">页面描述</label>
									<textarea class="form-control" name="metadescription" id="metadescription"  placeholder="填写页面描述" >${(page.metadescription)!}</textarea>
								</div>
							</div>
			
						</div>
						
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label" for="about">页面内容</label>
									<textarea class="form-control" name="content"  id="content"  placeholder="填写页面内容"> ${(page.content)!}</textarea>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
								   <#if img?? && img=1>
											<#assign a=0>
											<#list imgurl as imgurl>
												<#assign a=a+1>
												<input name="imageurl" type="hidden"  value="${imgurl!}"  class="img${a}"/>	
											</#list>
									</#if>
									<input name="imageid" type="hidden" id="imageid" value=""/>
								    <input name="destinationlist" type="hidden" id="destinationlist" value="">
								    <input name="attractionlist" type="hidden" id="attractionlist" value="">
								    <input name="selfpaylist" type="hidden" id="selfpaylist" value="">
								    <input name="costnumberids" type="hidden" id="costnumberIds" value="${(tourline.costnumberids)!}">
								    <input name="id" type="hidden" id="id" value="${(tourline.id)!}">
								    <input name="productid" type="hidden" id="productId" value="${(tourline.productid)!}">
								    <input name="pageid" type="hidden" id="id" value="${(product.pageid)!}">
									<button type="submit" class="btn btn-primary">完成提交</button>
								</div>
							</div>
						</div>
					</div>
					
					
					
					<!-- Tabs Pager -->
					
					<ul class="pager wizard">
						<li class="previous">
							<a href="#">上一步<i class="entypo-left-open"></i> </a>
						</li>
						
						<li class="next">
							<a href="#">下一步 <i class="entypo-right-open"></i></a>
						</li>
					</ul>
					
				</div>
				
			</form>
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<footer class="main-footer sticky footer-type-1">
				
				<div class="footer-inner">
				
					<!-- Add your copyright text here -->
					<div class="footer-text">
						&copy; 2015
					   <a href="" target="_blank" title="西安淘游网络科技有限公司">西安淘游网络科技有限公司</a> 
					</div>
					
					
					<!-- Go to Top Link, just add rel="go-top" to any link to add this functionality -->
					<div class="go-up">
					
						<a href="#" rel="go-top">
							<i class="fa-angle-up"></i>
						</a>
						
					</div>
					
				</div>
				
			</footer>
		</div>
		
			
		<!-- start: Chat Section -->
		<div id="chat" class="fixed">
			
			<div class="chat-inner">
			
				
				<h2 class="chat-header">
					<a href="#" class="chat-close" data-toggle="chat">
						<i class="fa-plus-circle rotate-45deg"></i>
					</a>
					
					Chat
					<span class="badge badge-success is-hidden">0</span>
				</h2>
				
				<script type="text/javascript">
				// Here is just a sample how to open chat conversation box
				jQuery(document).ready(function($)
				{
					var $chat_conversation = $(".chat-conversation");
					
					$(".chat-group a").on('click', function(ev)
					{
						ev.preventDefault();
						
						$chat_conversation.toggleClass('is-open');
						
						$(".chat-conversation textarea").trigger('autosize.resize').focus();
					});
					
					$(".conversation-close").on('click', function(ev)
					{
						ev.preventDefault();
						$chat_conversation.removeClass('is-open');
					});
				});
				</script>
				
				
				<div class="chat-group">
					<strong>Favorites</strong>
					
					<a href="#"><span class="user-status is-online"></span> <em>Catherine J. Watkins</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Nicholas R. Walker</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Susan J. Best</em></a>
					<a href="#"><span class="user-status is-idle"></span> <em>Fernando G. Olson</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Brandon S. Young</em></a>
				</div>
				
				
				<div class="chat-group">
					<strong>Work</strong>
					
					<a href="#"><span class="user-status is-busy"></span> <em>Rodrigo E. Lozano</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Robert J. Garcia</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Daniel A. Pena</em></a>
				</div>
				
				
				<div class="chat-group">
					<strong>Other</strong>
					
					<a href="#"><span class="user-status is-online"></span> <em>Dennis E. Johnson</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Stuart A. Shire</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Janet I. Matas</em></a>
					<a href="#"><span class="user-status is-online"></span> <em>Mindy A. Smith</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Herman S. Foltz</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Gregory E. Robie</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>Nellie T. Foreman</em></a>
					<a href="#"><span class="user-status is-busy"></span> <em>William R. Miller</em></a>
					<a href="#"><span class="user-status is-idle"></span> <em>Vivian J. Hall</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Melinda A. Anderson</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Gary M. Mooneyham</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Robert C. Medina</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Dylan C. Bernal</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Marc P. Sanborn</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Kenneth M. Rochester</em></a>
					<a href="#"><span class="user-status is-offline"></span> <em>Rachael D. Carpenter</em></a>
				</div>
			
			</div>
			
			<!-- conversation template -->
			<div class="chat-conversation">
				
				<div class="conversation-header">
					<a href="#" class="conversation-close">
						&times;
					</a>
					
					<span class="user-status is-online"></span>
					<span class="display-name">Arlind Nushi</span> 
					<small>Online</small>
				</div>
				
				<ul class="conversation-body">	
					<li>
						<span class="user">Arlind Nushi</span>
						<span class="time">09:00</span>
						<p>Are you here?</p>
					</li>
					<li class="odd">
						<span class="user">Brandon S. Young</span>
						<span class="time">09:25</span>
						<p>This message is pre-queued.</p>
					</li>
					<li>
						<span class="user">Brandon S. Young</span>
						<span class="time">09:26</span>
						<p>Whohoo!</p>
					</li>
					<li class="odd">
						<span class="user">Arlind Nushi</span>
						<span class="time">09:27</span>
						<p>Do you like it?</p>
					</li>
				</ul>
				
				<div class="chat-textarea">
					<textarea class="form-control autogrow" placeholder="Type your message"></textarea>
				</div>
				
			</div>
			
		</div>
		<!-- end: Chat Section -->
		
		
	</div>
	
	<div style="display:none">
		<select id="destinationCache">
			  <#list destinations as destinations>
			    <option value="${(destinations.id)!}" name="${(destinations.name)!}" namecn="${(destinations.namecn)!}">${(destinations.name)!}-${(destinations.namecn)!}</option>
			  </#list>	
		</select>
	</div>
	 



	<!-- Imported styles on this page -->
	<link rel="stylesheet" href="${ctx!}/assets/js/multiselect/css/multi-select.css">
    <link href="${ctx!}/assets/css/uploadify.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${ctx!}/assets/js/select2/select2.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/select2/select2-bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/js/multiselect/css/multi-select.css">
    
	<!-- Bottom Scripts -->
	<script src="${ctx!}/assets/js/bootstrap.min.js"></script>
	<script src="${ctx!}/assets/js/TweenMax.min.js"></script>
	<script src="${ctx!}/assets/js/resizeable.js"></script>
	<script src="${ctx!}/assets/js/joinable.js"></script>
	<script src="${ctx!}/assets/js/xenon-api.js"></script>
	<script src="${ctx!}/assets/js/xenon-toggles.js"></script>


	<!-- Imported scripts on this page -->
	<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script src="${ctx!}/assets/js/inputmask/jquery.inputmask.bundle.js"></script>
	<script src="${ctx!}/assets/js/formwizard/jquery.bootstrap.wizard.min.js"></script>
	<script src="${ctx!}/assets/js/datepicker/bootstrap-datepicker.js"></script>
	<script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
	<script src="${ctx!}/assets/js/jquery-ui/jquery-ui.min.js"></script>
	<script src="${ctx!}/assets/js/selectboxit/jquery.selectBoxIt.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/script/jquery.uploadify.v2.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/script/swfobject.js"></script> 
    <script type="text/javascript" src="${ctx!}/assets/ckeditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${ctx!}/assets/ckeditor/ueditor.all.js"></script>
	<script src="${ctx!}/assets/js/multiselect/js/jquery.multi-select.js"></script>
	<script src="${ctx!}/assets/js/select2/select2.js"></script>
	<!-- JavaScripts initializations and stuff -->
	<script src="${ctx!}/assets/js/xenon-custom.js"></script>

</body>
</html>