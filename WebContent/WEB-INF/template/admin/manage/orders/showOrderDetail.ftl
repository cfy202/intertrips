<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>产品分类添加</title>
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
			       $.ajax({
		             type: "post",
		             url: "${ctx!}/admin/region/getsort.do",
		             data: {costnumber:$("#costnumber").val()},
		             dataType: "json",
		             success: function(data){
		                         $('#upid').empty();   //清空upid里面的所有内容
		                         var html = "<option value=\"root\">------- 请选择  ------</option>"; 
		                         $.each(data.regions, function(commentIndex, region){
		                             html +="<option value=\""+region.id+"\" >"+region.name+"</option>";
		                         });
		                         $('#upid').html(html);
		                         $('#sort').val(data.maxOrderId);
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
					<h1 class="title">子订单信息详情</h1>
					<p class="description"></p>
				</div>
					<div class="breadcrumb-env">
								<ol class="breadcrumb bc-1">
									<li>
							<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
						</li>
								<li>
										<a href="${ctx!}/admin/orders/list.do">订单信息</a>
								</li>
							<li class="active">
										<strong>子订单信息详情</strong>
								</li>
								</ol>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
								子订单信息:
									编号:${orderDetail.id}&nbsp;订单ID:${orderDetail.orderid}&nbsp;行程天数:${(orderDetail.days)!}&nbsp;出发日期:${(orderDetail.departuredate)!}&nbsp;结束日期:${(orderDetail.enddate)!}&nbsp;
									大人人数:${(orderDetail.adults)!}&nbsp;小孩人数:${(orderDetail.children)!}&nbsp;婴儿人数:${(orderDetail.babies)!}
								产品信息:
									产品编号:${(orderDetail.product.code)!}&nbsp;产品名称:${(orderDetail.product.name)!}&nbsp;产品描述:${(orderDetail.product.briefinfo)!}&nbsp;
								<#if (orderDetail.product.promotions)??>
								促销信息:
									<#list orderDetail.product.promotions as promotion>
										促销标题:${promotion.title}&nbsp;折扣:${promotion.discount}&nbsp;活动描述:${promotion.description}&nbsp;
									</#list>
								</#if>	
								线路信息:
									线路名称:${(orderDetail.tourline.tourname)!}&nbsp;天数:${(orderDetail.tourline.days)!}&nbsp;注意须知:${orderDetail.tourline.notice}&nbsp;区域名称:${orderDetail.tourline.region.name}&nbsp;
									<#if (orderDetail.tourline.selfpaylist)??>
									自费项目列表:${(orderdetail.tourline.selfpaylist)!}&nbsp;
									</#if>
									<#if (orderdetail.tourline.itinerarys)??>
									行程展示:
										<#list orderdetail.tourline.itinerarys as itinerary>
											第${itinerary.day}天,标题：${itinerary.title},酒店：${itinerary.hotel}
										</#list>
									</#if>	
									<#if (orderdetail.tourline.destinations)??>
									目的地展示：
										<#list orderdetail.tourline.destinations as destination>
											${destination.name}
												<#if (destination.attractions)??>
													景点列表:
													<#list destination.attractions as attraction>
														景点名称:${attraction.name}
													</#list>
												</#if>
												<#if (destination.hotels)??>
													酒店列表:
													<#list destination.hotels as hotel>
														酒店名称:${hotel.hotelname},酒店地址:${hotel.hoteladdress}
													</#list>
												</#if>
										</#list>
									</#if>	
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