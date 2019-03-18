<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>预订详情</title>
	
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
					<h1 class="title">预订详情</h1>
					<p class="description"></p>
				</div>
				<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
							<li>
								<a href="${ctx!}/admin/list.do"><i class="fa-home"></i>首页</a>
							</li>
							<li>
								<a href="${ctx!}/admin/prebookingofagent/list.do">Agent预订列表</a>
							</li>
							<li class="active">
								<strong>预订详情</strong>
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
								    <strong>预订编号:${(preBookingOfAgent.bookingNo)!}</strong>
								    <table class="table responsive">
										<thead>
											<tr>
												<th>name:${(preBookingOfAgent.name)!}</th>
												<th>phoneNo:${(preBookingOfAgent.phoneno)!}</th>
												<th>email:${(preBookingOfAgent.email)!}</th>
												<th>pax:${(preBookingOfAgent.pax)!}</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Product Name：${(preBookingOfAgent.productname)!}</td>
												<td>Product Code:${(preBookingOfAgent.productcode)!}</td>
											    <td>GateWay:${(preBookingOfAgent.gateway)!}</td>
												<td>DepartureDate:${(preBookingOfAgent.departuredate)?string('yyyy-MM-dd')}</td>
											</tr>
											<tr>
											    <td>CreditCardNo:${(preBookingOfAgent.creditcardno)!}</td>
												<td>ExpirationDate:${(preBookingOfAgent.expirationdate)!}</td>
												<td>SecurityCode:${(preBookingOfAgent.securitycode)!}</td>
												<td>Total:${(preBookingOfAgent.currencysign)!}${(preBookingOfAgent.total)!}</td>
											</tr>
											<tr>
											    <td>CreateTime:${(preBookingOfAgent.createtime)?string('yyyy-MM-dd HH:mm:dd')}</td>
												<td>AgentCode:${(preBookingOfAgent.agentcode)!}</td>
												<td><#if preBookingOfAgent.isSynchronize>${('ERP No.'+preBookingOfAgent.synchronizeNo)!}</#if></td>
												<td></td>
											</tr>	
											<tr>
												<td colspan="4"> Remark:${(preBookingOfAgent.remarks)!}</td>												
											</tr>										
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