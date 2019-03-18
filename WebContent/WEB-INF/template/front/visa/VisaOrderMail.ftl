<div style="margin:10px auto;">
    	<div style="width:500px;float:left;">
        	<a target="_blank" href="https://www.wenjing.com"><img border="0" src="https://www.wenjing.com/assets-web/logo/logo.png"></a>
        </div>
        <div style="width:250px; float:right;">
        	<div style="margin:69px 5px 5px 5px;text-align:right; font-family:Verdana, Geneva,sans-serif;">
        	   <a target="_blank" href="https://www.wenjing.com">首页</a> | <a target="_blank" href="https://www.wenjing.com/member/profile/index.htm">用户中心</a>
        	</div>
        </div>
        <div style="height:0; clear:both;"></div>
    </div>
<p style="font-size:12px; font-family: '微软雅黑', Arial, Helvetica, sans-serif; line-height:22px;">亲爱的<span>${(ordercontacter.name)!}</span>:</p>
<p style="font-size:12px; font-family: '微软雅黑', Arial, Helvetica, sans-serif; line-height:22px;">感谢您的预订！</p></br>
<h2 style="font-size:16px; font-family: '微软雅黑', Arial, Helvetica, sans-serif;">订单编号：${orders.orderno}</h2>
<table cellpadding="0" cellspacing="0" style="font-family:'微软雅黑', Arial, Helvetica, sans-serif; font-size:12px; border-left:1px solid #dddddd; border-top:1px solid #dddddd; width:700px;">
	<tr height="40">
    	<th style="border-right:1px solid #dddddd; border-bottom:1px solid #dddddd;">时间</th>
        <th style="border-right:1px solid #dddddd; border-bottom:1px solid #dddddd;">订购内容</th>
        <th style="border-right:1px solid #dddddd; border-bottom:1px solid #dddddd;">人数/数量</th>
        <th style="border-right:1px solid #dddddd; border-bottom:1px solid #dddddd;">费用明细</th>
        <th style="border-right:1px solid #dddddd; border-bottom:1px solid #dddddd;">金额</th>
    </tr>
    <tr>
    	<td style="border-right:1px solid #dddddd; border-bottom:1px solid #dddddd; padding:0 10px; width:15%; text-align:center;">${(orderdetail.departureDate)!}</td>
        <td style="border-right:1px solid #dddddd; border-bottom:1px solid #dddddd; padding:0 10px; width:35%;"><a href="" class="a4">${orderdetail.product.name}</a></td>
        <td style="border-right:1px solid #dddddd; border-bottom:1px solid #dddddd; padding:0 10px; width:10%;text-align:center;">${(orderdetail.adults)!}</td>
        <td style="border-right:1px solid #dddddd; border-bottom:1px solid #dddddd; padding:0 10px; width:30%;">
        	<p>签证服务费:&nbsp;&nbsp;${orderdetail.currencySign}${orderdetail.price}</p>
        
        </td>
        <td style="border-right:1px solid #dddddd; border-bottom:1px solid #dddddd; padding:0 10px; width:5%;text-align:center;">${orderdetail.currencySign}${orderdetail.price}</td>
    </tr>
</table>