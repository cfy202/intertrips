<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>订单详情</title>
	
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/bootstrap.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-core.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-components.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="${ctx!}/assets/css/custom.css">

	<script src="${ctx!}/assets/js/jquery-1.11.1.min.js"></script>
</head>
<body class="page-body">
	<div class="page-container">
		<#include "/admin/include/left.ftl"/>
		<div class="main-content">
	    <#include "/admin/include/man.ftl"/>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">订单信息详情</h1>
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
								<strong>订单信息详情</strong>
							</li>
						</ol>
		  	   </div>
			</div>
			<div class="row">
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-body">
						   <div class="row">
								<div class="col-sm-12">
								    <strong>一、总订单信息：</strong>
								    <table class="table responsive">
										<thead>
											<tr>
												<th>订单编号：${orders.orderno}</th>
												<th>商品数量：${orders.quantity}</th>
												<th>总价（应付款）：<#if (orders.orderdetails[0].currencySign)??>${orders.orderdetails[0].currencySign}</#if> ${orders.totalprice}</th>
												<th>生成时间：<#if (orders.createtime)??>${orders.createtime?string("yyyy-MM-dd HH:mm:ss")}</#if></th>
											</tr>
										</thead>
										
										<tbody>
											<#if  (orders.orderdetails[0].roomInfo)??>
											<tr>
											    <td>订单状态：${orders.orderStatus.name}</td>
												<td>付款方式：${(orders.payWayDetail)!}</td>
												<td>订单积分：${(orders.score)!}</td>
												<td>coupon:${(orders.orderdetails[0].roomInfo)!}</td>
											</tr>
											<tr>
											    <td>Security Code: ${(orders.remark)!}</td>
												<td>Voucher Code: ${(orders.receiveway)!}</td>
												<td><#if (orders.synchronizedOrderNo)??>同步订单号:${orders.synchronizedOrderNo}</#if></td>
												<td></td>	
											</tr>
											<#else>
												<tr>
												    <td>订单状态：${orders.orderStatus.name}</td>
													<td>付款方式：${(orders.payWayDetail)!}</td>
													<td>订单积分：${(orders.score)!}</td>
													<td><#if (orders.synchronizedOrderNo)??>同步订单号:${orders.synchronizedOrderNo}</#if></td>
												</tr>
											</#if>
											<#if orderAttachmentList?has_content>
											<tr>
												<td colspan="4"><strong>上传文件:</strong></td>
											</tr>
											<tr>
											    <td colspan="4"> 
											    <#list orderAttachmentList as orderAttachment>
			                                    	<a style="color: #0db4ff;" href="${ctx!}${(orderAttachment.url)!}">${(orderAttachment.name)!}</a> &nbsp;&nbsp;|&nbsp;&nbsp;
			                                    </#list>
											    </td>
											</tr>
											</#if>
										</tbody>
									</table>
									
									<strong>二、联系人信息：</strong>
								    <table class="table responsive">
										<thead>
											<tr>
												<th>联系人名称</th>
												<th>联系电话</th>
												<th>联系邮箱</th>
												<th>出发城市</th>
											</tr>
										</thead>
										
										<tbody>
											<tr>
												<td><#if (orders.orderContacter.firstName)??>${orders.orderContacter.firstName}</#if> <#if (orders.orderContacter.lastName)??>${orders.orderContacter.lastName}</#if></td>
												<td><#if (orders.orderContacter.tel)??>${orders.orderContacter.tel}</#if></td>
												<td><#if (orders.orderContacter.email)??>${orders.orderContacter.email}</#if></td>
												<!--
												<td><#if (orders.orderContacter.address)??>${orders.orderContacter.address}</#if></td>
												-->
											</tr>
										</tbody>
									</table>
									
									<strong>三、子订单列表：</strong>
								    <table class="table responsive">
								        <#assign orderdetail = orders.orderdetails[0]/>
										<thead>
											<tr>
												<th>产品编号：${orderdetail.product.code}</th>
												<th colspan="4">产品名称：<a href="${ctx!}${orderdetail.product.pagePageid.filepath}" target="_blank">${orderdetail.product.name}</a></th>
												<th>
												   <#if orderdetail.days??>
												                天数：${orderdetail.days}
												   </#if>
												</th>
												<th></th>
											</tr>
										</thead>
										
										<tbody>
										    <tr>
												<td>支付状态：
												   <#if orderdetail.payStatus=0>
												        未支付
												   <#elseif orderdetail.payStatus=1>
												        支付订金
												   <#elseif orderdetail.payStatus=2>
												        支付定金、部分尾款
												   <#else>
												         已支付
												   </#if>
												</td>	
												<td>
													出发城市：${(orderdetail.departure.name)!}
												</td>
												<td></td>
												<td colspan="4"></td>
											</tr>
											<tr>
												<td>
												<#if orderdetail.departuredate??>
												             出发日期：${orderdetail.departuredate}
											    </#if>
												</td>
												<td>
												<#if orderdetail.enddate??>
													结束日期：${orderdetail.enddate}
											    </#if>
												</td>
												<td></td>	
												<td colspan="4"></td>
											</tr>
											<tr>
												<td>
												<#if orderdetail.adults??>
													大人人数：${orderdetail.adults}
												</#if>
												</td>
												<td>
												<#if orderdetail.children??>
													小孩人数：${orderdetail.children}
												</#if>
												</td>
												<td colspan="4">
												<#if orderdetail.babies??>
													婴儿人数：${(orderdetail.babies)!}
												</#if>
												</td>
												<td>
											    <#if orderdetail.roomcount??>
											   		 选择房间数：${orderdetail.roomcount}
										   		 </#if>
											    </td>
											</tr>
											<tr>
												<td>已付金额：${(orderdetail.currencySign)!} ${(orderdetail.despotprice)!}</td>
												<td>未付金额：${(orderdetail.currencySign)!} ${(orderdetail.finalprice)!}</td>
												<td>
											    <#if orderdetail.depostidate??>
												       最后付款日期：${(orderdetail.depostidate)!}
												</#if>
												</td>
												<td colspan="4"></td>
											</tr>
											<#if orderdetail.specialrequest??>
											<tr>
												<td>特殊要求：</td>
												<td colspan="6">${orderdetail.specialrequest}</td>
											</tr>
											</#if>
											<#if (orderdetail.promotion)??>
												<#assign promotion = orderdetail.promotion />
												<tr>
													<td>促销标题：${promotion.title}</td>
													<td>
													  <#if promotion.discount??>折扣：${promotion.discount}</#if>
													  <#if promotion.full??>消费满：${promotion.full}</#if>
													  <#if promotion.reduce??>价格减：${promotion.reduce}</#if>
													</td>
													<td>活动描述：${promotion.description}</td>
													<td></td>
												</tr>
											</#if>
											<#if optionalTourList?has_content>
												<tr>
													<td><strong>附加服务（与目的地有关）:</strong></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td>名称</td>
													<td>日期</td>
													<td>单价</td>
													<td>数量</td>
													<td>总价</td>
												</tr>
												<#list optionalTourList as optionalTour>
												<tr>
													<td>${optionalTour.name}</td>
													<td><#if (optionalTour.date)??>${optionalTour.date?string('yyyy-MM-dd')}</#if></td>
													<td>${(orderdetail.currencySign)!}${optionalTour.unitcost}</td>
													<td>${(optionalTour.quantity)!}</td>
													<td>${(orderdetail.currencySign)!}${optionalTour.price}</td>
												</tr>
												</#list>
												<tr>
													<td></td>
													<td></td>
													<td></td>
													<td>合计：</td>
													<td>${(orderdetail.currencySign)!}${optionalTourFee}</td>
												</tr>	
											</#if>
											<#if optionalTourInTourlineList?has_content>
												<tr>
													<td><strong>附加服务（与目的地无关）:</strong></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td>名称</td>
													<td>单价</td>
													<td>数量</td>
													<td>总价</td>
												</tr>
												<#list optionalTourInTourlineList as optionalTourInTourline>
												<tr>
													<td>${optionalTourInTourline.name}</td>
													<td>${(orderdetail.currencySign)!}${optionalTourInTourline.unitcost}</td>
													<td>${(optionalTourInTourline.quantity)!}</td>
													<td>${(orderdetail.currencySign)!}${optionalTourInTourline.price}</td>
												</tr>
												</#list>
												<tr>
													<td></td>
													<td></td>
													<td>合计：</td>
													<td>${(orderdetail.currencySign)!}${optionalTourInTourlineFee}</td>
												</tr>	
											</#if>
											<#if additionalProductList?has_content>
												<tr>
													<td><strong>附加产品:</strong></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
												<tr>
													<td>名称</td>
													<td>日期</td>
													<td>单价</td>
													<td>数量</td>
													<td>总价</td>	
												</tr>										
												<#list additionalProductList as additionalProduct>
												<tr>
													<td>${additionalProduct.name}</td>
													<td><#if (additionalProduct.date)??>${additionalProduct.date?string('yyyy-MM-dd')}</#if></td>
													<td>${(orderdetail.currencySign)!}${additionalProduct.unitcost}</td>
													<td>${(additionalProduct.quantity)!}</td>
													<td>${(orderdetail.currencySign)!}${additionalProduct.price}</td>
												</tr>
												</#list>
												<tr>
													<td></td>
													<td></td>
													<td></td>
													<td>合计：</td>
													<td>${(orderdetail.currencySign)!}${additionalProductFee}</td>
												</tr>													
											</#if>
											<#if ((orders.orderdetails[0]).tourPassengerList)?has_content>
											<tr>
											    <td><strong>旅客信息:</strong></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>	
											<tr>
												<td>姓名</td>
												<td>性别</td>
												<td>生日</td>
												<td>国籍</td>
												<td>护照号</td>
												<td>护照有效期</td>
												<td>联系电话</td>
												<td>客人类型</td>
												<td>房型</td>
												<td>房号</td>
											</tr>
											<#list orderdetail.tourPassengerList as tourpassenger>
												<tr>
													<td>${tourpassenger.lastName?if_exists?default("")} ${tourpassenger.middleName?if_exists?default("")} ${tourpassenger.firstName?if_exists?default("")}</td>
													<td>${(tourpassenger.gender==1)?string("女","男")}</td>
													<td><#if (tourpassenger.birthday)??>${tourpassenger.birthday?string("yyyy-MM-dd")}</#if></td>
													<td>${(tourpassenger.nationality)!}</td>
													<td>${(tourpassenger.passportNo)!}</td>
													<td><#if (tourpassenger.passportNoExpiryDate)??>${(tourpassenger.passportNoExpiryDate?string("yyyy-MM-dd"))!}</#if></td>
													<td>${(tourpassenger.mobileNumber)!}</td>
													<td>${(tourpassenger.identity)!}</td>
													<td>${(tourpassenger.roomType)!}</td>
													<td>${(tourpassenger.roomNumber)!}</td>
												</tr>
											</#list>
										</#if>
										</tbody>
									</table>	
								</div>
							</div>
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