<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html>
	<head>
		<title>订单查询页面</title>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	</head>
	<body>
		<form id="form" action="queryOrdersByOrderNoAndEmail.do" method="POST" >
			请输入订单编号:<input id="orderNo" name="orderNo" />或联系人邮箱：<input id="email" name="email" /><br/>
			<input id="queryButton" type="submit" value="查询"/>
		</form>
		<div id="ordersInfo" style="display:none">
			订单编号:	<i class="orderNo"></i>
			产品数量: <i class="quantity"></i>
			订单总价: <i class="totalprice"></i>
			订单状态: <i class="status"></i><br/>
			联系人名称：<i class="name"></i>
			联系人电话：<i class="tel"></i>
			联系人邮箱：<i class="email"></i>
			<table id="orderDetailList">
				
			</table>
		</div>
	</body>
	<script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#form").validate({
				rules: {
				   orderNo: {
				  	 required: true
				   },
				   email: {
				    required: true,
				    email: true
				   }
				},	
				messages: {
					orderNo: {
						required: "请输入订单编号:"
					},
					email: {
						required: "请输入邮箱地址",
						email: "请输入正确的email地址"
					}
				},
				submitHandler: function(form){
					var $form = $("#form");
					var $ordersInfo = $("#ordersInfo");
					var $orderDetailList = $("#orderDetailList");
					$(".noOrderWarn").remove();
					$orderDetailList.empty();
					
			    	$.post($form.attr("action"),$form.serialize(),function(orders){
			    		if(orders != ''){
				    		$ordersInfo.find(".orderNo").html(orders.orderno);
				    		$ordersInfo.find(".quantity").html(orders.quantity);
				    		$ordersInfo.find(".totalprice").html(orders.totalprice);
				    		$ordersInfo.find(".status").html(orders.orderStatus.name);
				    		$ordersInfo.find(".name").html(orders.orderContacter.name);
				    		$ordersInfo.find(".tel").html(orders.orderContacter.tel);
				    		$ordersInfo.find(".email").html(orders.orderContacter.email);
				    		
				    		$orderDetailList.append('<tr><td>产品名称</td><td>产品编号</td><td>出发日期</td><td>结束日期</td></tr>');
				    		$.each(orders.orderdetails,function(index,orderdetail){
				    			var productInfo = '<tr><td>'+ orderdetail.product.name +'</td><td>'+ orderdetail.product.code +'</td><td>'+ orderdetail.departuredate +'</td><td>'+ orderdetail.enddate +'</td></tr>';
				    			$orderDetailList.append(productInfo);
				    			$orderDetailList.append('<tr><tr><td>姓</td><td>名</td><td>性别</td><td>国籍</td></tr></tr>');
				    			$.each(orderdetail.tourPassengerList,function(index,tourPassenger){
				    				var passengerInfo = '<tr><tr><td>'+ tourPassenger.lastName +'</td><td>'+ tourPassenger.firstName +'</td><td>';
				    				if(tourPassenger.gender == 0){
				    					passengerInfo += '男';
				    				}else{
				    					passengerInfo += '女';
				    				}
				    				passengerInfo += '</td><td>'+ tourPassenger.nationality +'</td></tr></tr>';
				    				$orderDetailList.append(passengerInfo);
				    			});
				    		});
				    		$ordersInfo.show();	
			    		}else{
			    			$ordersInfo.after('<div class="noOrderWarn">没有查到此订单</div>');
			    		}
			    	});
				}
			});	
		});
	</script>
	<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " https://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256204415'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1256204415%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
	
</html>