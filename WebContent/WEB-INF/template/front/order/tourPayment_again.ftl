<!DOCTYPE html>
<#assign ctx = request.contextPath />
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
<title>payment</title>
<link rel="shortcut icon" href="${ctx!}/favicon.ico">
<link rel="apple-touch-icon-precomposed" href="${ctx!}/apple-touch-icon.png">
<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv-printshiv.min.js?ver=4.3.1'></script> <![endif]-->
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js?ver=4.3.1'></script> 
<![endif]-->
<meta name="generator">
<style id="fit-vids-style">
.fluid-width-video-wrapper { width: 100%; position: relative; padding: 0; }
.fluid-width-video-wrapper iframe, .fluid-width-video-wrapper object, .fluid-width-video-wrapper embed { position: absolute; top: 0; left: 0; width: 100%; height: 100%; }
</style>
<style id="ShrinkMenu">
.menu-shrink { top : -49px !important; }
</style>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-38017454-1', 'auto');
  ga('send', 'pageview');

</script>
</head>
<body class="blog"  style="background-color:#f5f6f6;">
<#--top-->
<#include "/front/include/top.ftl"/>

<#--top-->
<section class="featured-destinations" style="margin-top:5.2rem;">
  <div class="container">
    <div class="cards overlap " style="margin-top:0;">
      <div class="widthCenter hidden-xs">
        <h3 class="Payment_number"><span style="margin-right:100px;">Order No.：${orders.orderno}</span><span>Status:Unpaid</span></h3>
        <div class="Payment_Method shadow">
            <table class="Payment_Method_tab" cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <th width="12%" style="border-right: 1px solid #ddd;">Date</th>
                    <th width="30%" style="border-right: 1px solid #ddd;">Product description</th>
                    <th width="12%" style="border-right: 1px solid #ddd;">Quantity</th>
                    <th width="16%" style="border-right: 1px solid #ddd;">Adults/Children/Infants</th>
                    <th width="30%" style="border-right: 1px solid #ddd;">Price</th>
                </tr>
                <tr class="Payment_Method_tr">
                    <td style="text-align:left;border-right: 1px solid #ddd;">${departureString}</td>
                    <td style="text-align:left;border-right: 1px solid #ddd;"><a href="https://www.intertrips.com${orders.orderdetails[0].product.pagePageid.filepath}" class="a4">${orders.orderdetails[0].product.name}</a></td>
                    <td style="text-align:left;border-right: 1px solid #ddd;">1</td>
                    <td style="text-align:left;border-right: 1px solid #ddd;">
                    	<p class="Payment_td_p">${orders.orderdetails[0].adults} Adults</p>
                        <p class="Payment_td_p">${orders.orderdetails[0].children} Children</p>
                        <p class="Payment_td_p">${orders.orderdetails[0].babies} Infants</p>
                    </td>
                    <td style="text-align:left;">
                    	<p class="Payment_td_p">Base fare:&nbsp;&nbsp;${orders.cost.sign}${tourFee}</p>
				        <#if singleSupplementsFee??>   
				           <p class="Payment_td_p">Single Supplements fee:&nbsp;&nbsp;${orders.cost.sign}${singleSupplementsFee}</p>
				        </#if>
				        <#if guideServeFee??>   
				           <p class="Payment_td_p">Guides service fees:&nbsp;&nbsp;${orders.cost.sign}${guideServeFee}</p>
				        </#if>
				        <#if steamFee??>  
				        	<p class="Payment_td_p">Optional attractions and performances fees:&nbsp;&nbsp;${orders.cost.sign}${steamFee}</p>  
				        </#if>
				        <#if transferFee??>    
				            <p class="Payment_td_p">Airport transfer fees:&nbsp;&nbsp;${orders.cost.sign}${transferFee}</p>
				        </#if>
				        <#if airticketFee??>    
				            <p class="Payment_td_p">Air fares:&nbsp;&nbsp;${orders.cost.sign}${airticketFee}</p>   
				        </#if> 
				        <#if bonusPointsFee??>
				        	<p class="Payment_td_p">Bonus Points:&nbsp;&nbsp;-${orders.cost.sign}${bonusPointsFee}</p>
				        </#if>   
				        <#if couponsFee??>
				        	<p class="Payment_td_p">Coupon:&nbsp;&nbsp;-${orders.cost.sign}${couponsFee}</p>
				        </#if>   
                    </td>
                </tr>
                <#if (optionalTourList)?has_content>
                <#list optionalTourList as additionalProduct>
                <tr class="Payment_Method_tr">
                    <td style="text-align:left;border-right: 1px solid #ddd;">${additionalProduct.dayString}</td>
                    <td style="text-align:left;border-right: 1px solid #ddd;"><a class="a4">${additionalProduct.name}</a></td>
                    <td style="text-align:left;border-right: 1px solid #ddd;">${additionalProduct.quantity}</td>
                    <td style="text-align:left;border-right: 1px solid #ddd;"></td>
                    <td style="text-align:left;">
                    	<p class="Payment_td_p">${orders.cost.sign}${additionalProduct.unitcost * additionalProduct.quantity}</p>
                    </td>
                </tr>
                </#list>
                </#if>     
                <#if (optionalTourInTourlineList)?has_content>
                <#list optionalTourInTourlineList as additionalProduct>
                <tr class="Payment_Method_tr">
                    <td style="text-align:left;border-right: 1px solid #ddd;"></td>
                    <td style="text-align:left;border-right: 1px solid #ddd;"><a class="a4">${additionalProduct.name}</a></td>
                    <td style="text-align:left;border-right: 1px solid #ddd;">${additionalProduct.quantity}</td>
                    <td style="text-align:left;border-right: 1px solid #ddd;"></td>
                    <td style="text-align:left;">
                    	<p class="Payment_td_p">${orders.cost.sign}${additionalProduct.unitcost * additionalProduct.quantity}</p>
                    </td>
                </tr>
                </#list>
                </#if>                   
                <#if (additionalProductList)?has_content>
                <#list additionalProductList as additionalProduct>
                <tr class="Payment_Method_tr">
                    <td style="text-align:left;border-right: 1px solid #ddd;">${additionalProduct.dayString}</td>
                    <td style="text-align:left;border-right: 1px solid #ddd;"><a class="a4">${additionalProduct.name}</a></td>
                    <td style="text-align:left;border-right: 1px solid #ddd;">${additionalProduct.quantity}</td>
                    <td style="text-align:left;border-right: 1px solid #ddd;"></td>
                    <td style="text-align:left;">
                    	<p class="Payment_td_p">${orders.cost.sign}${additionalProduct.unitcost * additionalProduct.quantity}</p>
                    </td>
                </tr>
                </#list>
                </#if>
                <tr>
                    <td colspan="5" style="text-align:right;">
                    	<h3 style="font-size:1rem; margin:10px 0;">Total price:<b style="color:red; margin-left:20px;">${orders.cost.sign}${orders.totalprice}</b></h3>
                    </td>
                </tr>
                <tr>
                    <td colspan="5" style="text-align:left;">
                    	Don't take any risk, travel worry-free and protect your trip with essential benefits! We highly recommend you to add the travel insurance to avoid huge amount extra expense when you are in unexpected situation. The small cost for travel insurance may save you hundreds of dollars or more!
                    </td>
                </tr>
            </table>
            <div class="clear"></div>
            <#if frontCode == 'AUD'>
	            <div class="Payment_btn">
	            	Please choose your Payment Option >
	                <a style="cursor:pointer;" href="javascript:toPayment('Paypal');"><img src="${ctx!}/assets-web/images/paypalbutton.jpg"></a>
	            </div>
            <#elseif frontCode == 'USD'>
	            <div class="Payment_btn">
	            	Please choose your Payment Option >
	            	<a style="cursor:pointer;" href="javascript:toPayment('card');" style="margin-right:10px;"><img src="${ctx!}/assets-web/images/creditcardbutton.jpg"></a>
	                <a style="cursor:pointer;" href="javascript:toPayment('Paypal');"><img src="${ctx!}/assets-web/images/paypalbutton.jpg"></a>
	            </div>
            </#if>
        </div>        
        <#--信用卡信息填写表单-->
        <div id="cardPayAero" class="Payment_Method_content shadow" style="display:none;">
		  <div id="payment_credit" class="payments">
		    <div class="f16 paypt30 fb"> <span class="mr10">You have chosen to pay by credit card</span> <img alt="Visa" src="${ctx}/assets-web/images/vmdpay2.png" class="payment_img"> </div>
		    <form name="kcredit" method="post" action="${ctx}/card_pay.htm">
		      <input type="hidden" name="amount" class="CPriceInput"/>
		      <input type="hidden" name="currencyCode" value="${orders.cost.code}"/>
			  <input type="hidden" name="orderNo" value="${orders.orderno}"/>
			  <input type="hidden" name="productName" value="${orders.orderdetails[0].product.name}"/>
			  <input type="hidden" name="ordersId" value="${orders.id}"/>
		      <div class="basicInformation" style="">
		        <div class="fl d500">
		          <table class="paytbl1">
		            <tr>
		              <td width="40%"><span class="f14-1">Card Type</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><select class="cardTypeInput" name="cardtype">
		                  <option value="0">Please Select</option>
		                  <option value="Visa">Visa</option>
		                  <option value="MasterCard">MasterCard</option>
		                  <option value="Discover">Discover</option>
		                  <option value="American Express">American Express</option>
		                </select></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Card Number</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" class="cardNumberInput" name="cardnum"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Card Expiration Date</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><select class="expireMonthSelect" name="expireMonth" class="fl">
		                  <option value="01">01</option>
		                  <option value="02" selected="selected">02</option>
		                  <option value="03">03</option>
		                  <option value="04">04</option>
		                  <option value="05">05</option>
		                  <option value="06">06</option>
		                  <option value="07">07</option>
		                  <option value="08">08</option>
		                  <option value="09">09</option>
		                  <option value="10">10</option>
		                  <option value="11">11</option>
		                  <option value="12">12</option>
		                </select>
		                <select class="expireYearSelect" name="expireYear" class="fr">
		                  <option value="2016" selected="selected">2016</option>
		                  <option value="2017">2017</option>
		                  <option value="2018">2018</option>
		                  <option value="2019">2019</option>
		                  <option value="2020">2020</option>
		                  <option value="2021">2021</option>
		                  <option value="2022">2022</option>
		                  <option value="2023">2023</option>
		                  <option value="2024">2024</option>
		                  <option value="2025">2025</option>
		                  <option value="2026">2026</option>
		                </select>
		                <div class="clear"></div></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Card Security Code</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="authenticationnum" class="input-small cardSecurityCode" maxlength="4"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Country</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%">
		              	<select class="countrySelect" name="country">
		              	  <option value="US">United States</option>
		                  <#--
		                  <option value="0">Please Select Your Country</option>
		                  <option value="CN">China</option>
		                  <option value="US">United States</option>
		                  <option value="AF">Afganistan</option>
		                  <option value="AL">Albania</option>
		                  <option value="DZ">Algeria</option>
		                  <option value="AS">American Samoa</option>
		                  <option value="AD">Andorra</option>
		                  <option value="AO">Angola</option>
		                  <option value="AI">Anguilla</option>
		                  <option value="AQ">Antarctica</option>
		                  <option value="AG">Antigua and Barbuda</option>
		                  <option value="AR">Argentina</option>
		                  <option value="AM">Armenia</option>
		                  <option value="AW">Aruba</option>
		                  <option value="AU">Australia</option>
		                  <option value="AT">Austria</option>
		                  <option value="AZ">Azerbaijan</option>
		                  <option value="BS">Bahamas</option>
		                  <option value="BH">Bahrain</option>
		                  <option value="BD">Bangladesh</option>
		                  <option value="BB">Barbados</option>
		                  <option value="BY">Belarus</option>
		                  <option value="BE">Belgium</option>
		                  <option value="BZ">Belize</option>
		                  <option value="BJ">Benin</option>
		                  <option value="BM">Bermuda</option>
		                  <option value="BT">Bhutan</option>
		                  <option value="BO">Bolivia</option>
		                  <option value="BA">Bosnia and Herzegowina</option>
		                  <option value="BW">Botswana</option>
		                  <option value="BV">Bouvet Island</option>
		                  <option value="BR">Brazil</option>
		                  <option value="IO">British Indian Ocean Territory</option>
		                  <option value="BN">Brunei Darussalam</option>
		                  <option value="BG">Bulgaria</option>
		                  <option value="BF">Burkina Faso</option>
		                  <option value="BI">Burundi</option>
		                  <option value="KH">Cambodia</option>
		                  <option value="CM">Cameroon</option>
		                  <option value="CA">Canada</option>
		                  <option value="CV">Cape Verde</option>
		                  <option value="KY">Cayman Islands</option>
		                  <option value="CF">Central African Republic</option>
		                  <option value="TD">Chad</option>
		                  <option value="CL">Chile</option>
		                  <option value="CN">China</option>
		                  <option value="CX">Christmas Island</option>
		                  <option value="CC">Cocos (Keeling) Islands</option>
		                  <option value="CO">Colombia</option>
		                  <option value="KM">Comoros</option>
		                  <option value="CG">Congo</option>
		                  <option value="CD">Congo, the Democratic Republic of the</option>
		                  <option value="CK">Cook Islands</option>
		                  <option value="CR">Costa Rica</option>
		                  <option value="CI">Cote d'Ivoire</option>
		                  <option value="HR">Croatia (Hrvatska)</option>
		                  <option value="CU">Cuba</option>
		                  <option value="CY">Cyprus</option>
		                  <option value="CZ">Czech Republic</option>
		                  <option value="DK">Denmark</option>
		                  <option value="DJ">Djibouti</option>
		                  <option value="DM">Dominica</option>
		                  <option value="DO">Dominican Republic</option>
		                  <option value="TP">East Timor</option>
		                  <option value="EC">Ecuador</option>
		                  <option value="EG">Egypt</option>
		                  <option value="SV">El Salvador</option>
		                  <option value="GQ">Equatorial Guinea</option>
		                  <option value="ER">Eritrea</option>
		                  <option value="EE">Estonia</option>
		                  <option value="ET">Ethiopia</option>
		                  <option value="FK">Falkland Islands (Malvinas)</option>
		                  <option value="FO">Faroe Islands</option>
		                  <option value="FJ">Fiji</option>
		                  <option value="FI">Finland</option>
		                  <option value="FR">France</option>
		                  <option value="FX">France, Metropolitan</option>
		                  <option value="GF">French Guiana</option>
		                  <option value="PF">French Polynesia</option>
		                  <option value="TF">French Southern Territories</option>
		                  <option value="GA">Gabon</option>
		                  <option value="GM">Gambia</option>
		                  <option value="GE">Georgia</option>
		                  <option value="DE">Germany</option>
		                  <option value="GH">Ghana</option>
		                  <option value="GI">Gibraltar</option>
		                  <option value="GR">Greece</option>
		                  <option value="GL">Greenland</option>
		                  <option value="GD">Grenada</option>
		                  <option value="GP">Guadeloupe</option>
		                  <option value="GU">Guam</option>
		                  <option value="GT">Guatemala</option>
		                  <option value="GN">Guinea</option>
		                  <option value="GW">Guinea-Bissau</option>
		                  <option value="GY">Guyana</option>
		                  <option value="HT">Haiti</option>
		                  <option value="HM">Heard and Mc Donald Islands</option>
		                  <option value="VA">Holy See (Vatican City State)</option>
		                  <option value="HN">Honduras</option>
		                  <option value="HK">Hong Kong</option>
		                  <option value="HU">Hungary</option>
		                  <option value="IS">Iceland</option>
		                  <option value="IN">India</option>
		                  <option value="ID">Indonesia</option>
		                  <option value="IR">Iran (Islamic Republic of)</option>
		                  <option value="IQ">Iraq</option>
		                  <option value="IE">Ireland</option>
		                  <option value="IL">Israel</option>
		                  <option value="IT">Italy</option>
		                  <option value="JM">Jamaica</option>
		                  <option value="JP">Japan</option>
		                  <option value="JO">Jordan</option>
		                  <option value="KZ">Kazakhstan</option>
		                  <option value="KE">Kenya</option>
		                  <option value="KI">Kiribati</option>
		                  <option value="KP">Korea, Democratic People's Republic of</option>
		                  <option value="KR">Korea, Republic of</option>
		                  <option value="KW">Kuwait</option>
		                  <option value="KG">Kyrgyzstan</option>
		                  <option value="LA">Lao People's Democratic Republic</option>
		                  <option value="LV">Latvia</option>
		                  <option value="LB">Lebanon</option>
		                  <option value="LS">Lesotho</option>
		                  <option value="LR">Liberia</option>
		                  <option value="LY">Libyan Arab Jamahiriya</option>
		                  <option value="LI">Liechtenstein</option>
		                  <option value="LT">Lithuania</option>
		                  <option value="LU">Luxembourg</option>
		                  <option value="MO">Macau</option>
		                  <option value="MK">Macedonia, The Former Yugoslav Republic of</option>
		                  <option value="MG">Madagascar</option>
		                  <option value="MW">Malawi</option>
		                  <option value="MY">Malaysia</option>
		                  <option value="MV">Maldives</option>
		                  <option value="ML">Mali</option>
		                  <option value="MT">Malta</option>
		                  <option value="MH">Marshall Islands</option>
		                  <option value="MQ">Martinique</option>
		                  <option value="MR">Mauritania</option>
		                  <option value="MU">Mauritius</option>
		                  <option value="YT">Mayotte</option>
		                  <option value="MX">Mexico</option>
		                  <option value="FM">Micronesia, Federated States of</option>
		                  <option value="MD">Moldova, Republic of</option>
		                  <option value="MC">Monaco</option>
		                  <option value="MN">Mongolia</option>
		                  <option value="MS">Montserrat</option>
		                  <option value="MA">Morocco</option>
		                  <option value="MZ">Mozambique</option>
		                  <option value="MN">Myanmar</option>
		                  <option value="NA">Namibia</option>
		                  <option value="NR">Nauru</option>
		                  <option value="NP">Nepal</option>
		                  <option value="NL">Netherlands</option>
		                  <option value="AN">Netherlands Antilles</option>
		                  <option value="NC">New Caledonia</option>
		                  <option value="NZ">New Zealand</option>
		                  <option value="NI">Nicaragua</option>
		                  <option value="NE">Niger</option>
		                  <option value="NG">Nigeria</option>
		                  <option value="NU">Niue</option>
		                  <option value="NF">Norfolk Island</option>
		                  <option value="MP">Northern Mariana Islands</option>
		                  <option value="NO">Norway</option>
		                  <option value="OM">Oman</option>
		                  <option value="PK">Pakistan</option>
		                  <option value="PW">Palau</option>
		                  <option value="PA">Panama</option>
		                  <option value="PG">Papua New Guinea</option>
		                  <option value="PY">Paraguay</option>
		                  <option value="PE">Peru</option>
		                  <option value="PH">Philippines</option>
		                  <option value="PN">Pitcairn</option>
		                  <option value="PL">Poland</option>
		                  <option value="PT">Portugal</option>
		                  <option value="PR">Puerto Rico</option>
		                  <option value="QA">Qatar</option>
		                  <option value="RE">Reunion</option>
		                  <option value="RO">Romania</option>
		                  <option value="RU">Russian Federation</option>
		                  <option value="RW">Rwanda</option>
		                  <option value="KN">Saint Kitts and Nevis</option>
		                  <option value="LC">Saint LUCIA</option>
		                  <option value="VC">Saint Vincent and the Grenadines</option>
		                  <option value="WS">Samoa</option>
		                  <option value="SM">San Marino</option>
		                  <option value="ST">Sao Tome and Principe</option>
		                  <option value="SA">Saudi Arabia</option>
		                  <option value="SN">Senegal</option>
		                  <option value="SC">Seychelles</option>
		                  <option value="SL">Sierra Leone</option>
		                  <option value="SG">Singapore</option>
		                  <option value="SK">Slovakia (Slovak Republic)</option>
		                  <option value="SI">Slovenia</option>
		                  <option value="SB">Solomon Islands</option>
		                  <option value="SO">Somalia</option>
		                  <option value="ZA">South Africa</option>
		                  <option value="GS">South Georgia and the South Sandwich Islands</option>
		                  <option value="ES">Spain</option>
		                  <option value="LK">Sri Lanka</option>
		                  <option value="SH">St. Helena</option>
		                  <option value="PM">St. Pierre and Miquelon</option>
		                  <option value="SD">Sudan</option>
		                  <option value="SR">Suriname</option>
		                  <option value="SJ">Svalbard and Jan Mayen Islands</option>
		                  <option value="SZ">Swaziland</option>
		                  <option value="SE">Sweden</option>
		                  <option value="CH">Switzerland</option>
		                  <option value="SY">Syrian Arab Republic</option>
		                  <option value="TW">Taiwan, Province of China</option>
		                  <option value="TJ">Tajikistan</option>
		                  <option value="TZ">Tanzania, United Republic of</option>
		                  <option value="TH">Thailand</option>
		                  <option value="TG">Togo</option>
		                  <option value="TK">Tokelau</option>
		                  <option value="TO">Tonga</option>
		                  <option value="TT">Trinidad and Tobago</option>
		                  <option value="TN">Tunisia</option>
		                  <option value="TR">Turkey</option>
		                  <option value="TM">Turkmenistan</option>
		                  <option value="TC">Turks and Caicos Islands</option>
		                  <option value="TV">Tuvalu</option>
		                  <option value="UG">Uganda</option>
		                  <option value="UA">Ukraine</option>
		                  <option value="AE">United Arab Emirates</option>
		                  <option value="GB">United Kingdom</option>
		                  <option value="US">United States</option>
		                  <option value="UM">United States Minor Outlying Islands</option>
		                  <option value="UY">Uruguay</option>
		                  <option value="UZ">Uzbekistan</option>
		                  <option value="VU">Vanuatu</option>
		                  <option value="VE">Venezuela</option>
		                  <option value="VN">Viet Nam</option>
		                  <option value="VG">Virgin Islands (British)</option>
		                  <option value="VI">Virgin Islands (U.S.)</option>
		                  <option value="WF">Wallis and Futuna Islands</option>
		                  <option value="EH">Western Sahara</option>
		                  <option value="YE">Yemen</option>
		                  <option value="YU">Yugoslavia</option>
		                  <option value="ZM">Zambia</option>
		                  <option value="ZW">Zimbabwe</option>
		                </select>
		                -->
		                </td>
		            </tr>
		          </table>
		        </div>
		        <div class="fr credit_img">
		          <div class="mt30"><img alt="credit" src="${ctx}/assets-web/images/xinyongka.png"></div>
		          <div class="mt20 f12"><img class="mr5" src="${ctx}/assets-web/images/icon_a2.png"> Accept Visa / Master / Discover / American Express payment </div>
		          <div class="f12"><img class="mr5" src="${ctx}/assets-web/images/icon_a2.png"> Make sure that sufficient balance or credit card payment limit, and online payment function </div>
		        </div>
		        <div class="clear"></div>
		      </div>
		      <div class="lupay3" style="display:block;">
		        <div class="f16 paypt30 fb"><span class="mr10">Billing Address</span></div>
		        <div class="d500">
		          <table class="paytbl1">
		            <tr>
		              <td width="40%"><span class="f14-1">First Name</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="firstName" class="input-small firstNameInput"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Last Name</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="lastName" class="input-small lastNameInput"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Street Address</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="streetAddress" class="input-small streetAddressInput"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">City</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="city" class="input-small cityInput"></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">(US/CA) State</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><select name="state" class="stateSelect">
		                  <option></option>
		                  <option value="AB">AB</option>
		                  <option value="AK">AK</option>
		                  <option value="AL">AL</option>
		                  <option value="AR">AR</option>
		                  <option value="AZ">AZ</option>
		                  <option value="BC">BC</option>
		                  <option value="CA">CA</option>
		                  <option value="CO">CO</option>
		                  <option value="CT">CT</option>
		                  <option value="DC">DC</option>
		                  <option value="DE">DE</option>
		                  <option value="FL">FL</option>
		                  <option value="GA">GA</option>
		                  <option value="HI">HI</option>
		                  <option value="IA">IA</option>
		                  <option value="ID">ID</option>
		                  <option value="IL">IL</option>
		                  <option value="IN">IN</option>
		                  <option value="KS">KS</option>
		                  <option value="KY">KY</option>
		                  <option value="LA">LA</option>
		                  <option value="MA">MA</option>
		                  <option value="MB">MB</option>
		                  <option value="MD">MD</option>
		                  <option value="ME">ME</option>
		                  <option value="MI">MI</option>
		                  <option value="MN">MN</option>
		                  <option value="MO">MO</option>
		                  <option value="MS">MS</option>
		                  <option value="MT">MT</option>
		                  <option value="NB">NB</option>
		                  <option value="NC">NC</option>
		                  <option value="ND">ND</option>
		                  <option value="NE">NE</option>
		                  <option value="NF">NF</option>
		                  <option value="NH">NH</option>
		                  <option value="NJ">NJ</option>
		                  <option value="NM">NM</option>
		                  <option value="NT">NT</option>
		                  <option value="NS">NS</option>
		                  <option value="NU">NU</option>
		                  <option value="NV">NV</option>
		                  <option value="NY">NY</option>
		                  <option value="OH">OH</option>
		                  <option value="OK">OK</option>
		                  <option value="ON">ON</option>
		                  <option value="OR">OR</option>
		                  <option value="PA">PA</option>
		                  <option value="PE">PE</option>
		                  <option value="QC">QC</option>
		                  <option value="RI">RI</option>
		                  <option value="SC">SC</option>
		                  <option value="SD">SD</option>
		                  <option value="SK">SK</option>
		                  <option value="TN">TN</option>
		                  <option value="TX">TX</option>
		                  <option value="UT">UT</option>
		                  <option value="VA">VA</option>
		                  <option value="VT">VT</option>
		                  <option value="WA">WA</option>
		                  <option value="WI">WI</option>
		                  <option value="WV">WV</option>
		                  <option value="WY">WY</option>
		                  <option value="YK">YK</option>
		                  <option value="AA">AA</option>
		                  <option value="AE">AE</option>
		                  <option value="AP">AP</option>
		                  <option value="AS">AS</option>
		                  <option value="FM">FM</option>
		                  <option value="GU">GU</option>
		                  <option value="MH">MH</option>
		                  <option value="MP">MP</option>
		                  <option value="PR">PR</option>
		                  <option value="PW">PW</option>
		                  <option value="VI">VI</option>
		                </select></td>
		            </tr>
		            <tr>
		              <td width="40%"><span class="f14-1">Zip/Post Code</span> <span class="fr"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		              <td width="60%"><input type="text" name="postCode" class="input-small zipInput"></td>
		            </tr>
		            <tr>
		               <td width="40%"><span class="f12">Phone Number</span> <span class="right"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		               <td width="60%"><input type="text" name="phoneNumber" class="input-small phoneNumberInput"></td>
		            </tr>
  		            <tr>
		               <td width="40%"><span class="f12">Email</span> <span class="right"><img src="${ctx}/assets-web/images/icon_a2.png"></span></td>
		               <td width="60%"><input type="text" name="email" class="input-small emailInput"></td>
		            </tr>
		          </table>
		        </div>
		      </div>
		      <div class="visa_button"> <span class="visa_note fl" style="display:none;"><img class="mr5" src="${ctx}/assets-web/images/icon_a2.png"> 2.2% handling charge will be paid on credit card payment </span>
		        <input class="visa_submit fr" type="button" value="Pay Now">
		        <span class="visa_price fr"></span>
		        <div class="clear"></div>
		      </div>
		    </form>
		  </div>
		</div>
        <#--信用卡信息填写表单-->
        
        <div class="Payment_terms">
            <input id="windowAgreement1" type="checkbox" value="Y">
             I confirm that I understand that this tour package is non-refundable and I will cover all expenses on my own in the event of an emergency if I do not have travel insurance.</br>
           	 <input id="windowAgreement" type="checkbox" value="Y"> I have read and agree to
             <a href="${ctx!}${areaSuffix}/terms_conditions.html" target="_blank">Terms & Conditions</a><#--<a href="" target="_blank">modification & cancellation terms</a>、<a href="${ctx!}${areaSuffix}/privacy_policy.html" target="_blank">terms of use & privacy policy</a>-->        
        </div>
        <div class="Payment_Method_btns">
        	<#--
             <a href="" class="Payment_Method_btn_1">Payment</a>
             <a href="" style="margin-left:10px; padding: 12px 36px; color: #FFF; text-decoration: none; font-size: 14px; font-weight: bold; border-radius: 5px; background: #e0e0e0;">Return</a>
             -->
        </div>
        <div class="clear"></div>
	</div>
	<!--手机-->
	<div id="mobileDiv" class="widthCenter visible-xs">
        <h3 class="Payment_number">Order No.：${orders.orderno}</h3>
        <h3 class="Payment_number">Status:Unpaid</h3>
        <div class="Payment_Method shadow visible-xs">
        	<h4>Base Product</h4>
        	<div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Date</p>
                <p class="Payment_Method_main_m">${departureString}</p>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Product description</p>
                <p class="Payment_Method_main_m"><a href="https://www.intertrips.com${orders.orderdetails[0].product.pagePageid.filepath}" class="a4">${orders.orderdetails[0].product.name}</a></p>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Quantity</p>
                <p class="Payment_Method_main_m">1</p>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Adults/Children/Infants</p>
                <p class="Payment_Method_main_m">${orders.orderdetails[0].adults} Adults</p>
                <p class="Payment_Method_main_m">${orders.orderdetails[0].children} Children</p>
                <p class="Payment_Method_main_m">${orders.orderdetails[0].babies} Infants</p>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Unit price</p>
            	<p class="Payment_Method_main_m">Base fare:&nbsp;&nbsp;${orders.cost.sign}${tourFee}</p>
    		    <#if singleSupplementsFee??>   
		           <p class="Payment_Method_main_m">Single Supplements fee:&nbsp;&nbsp;${orders.cost.sign}${singleSupplementsFee}</p>
		        </#if>
		        <#if guideServeFee??>   
		           <p class="Payment_Method_main_m">Guides service fees:&nbsp;&nbsp;${orders.cost.sign}${guideServeFee}</p>
		        </#if>
		        <#if steamFee??>  
		        	<p class="Payment_Method_main_m">Optional attractions and performances fees:&nbsp;&nbsp;${orders.cost.sign}${steamFee}</p>  
		        </#if>
		        <#if transferFee??>    
		            <p class="Payment_Method_main_m">Airport transfer fees:&nbsp;&nbsp;${orders.cost.sign}${transferFee}</p>
		        </#if>
		        <#if airticketFee??>    
		            <p class="Payment_Method_main_m">Air fares:&nbsp;&nbsp;${orders.cost.sign}${airticketFee}</p>   
		        </#if>
		        <#if bonusPointsFee??>
		        	<p class="Payment_Method_main_m">Bonus points:&nbsp;&nbsp;-${orders.cost.sign}${bonusPointsFee}</p>
		        </#if>   
		        <#if couponsFee??>
		        	<p class="Payment_Method_main_m">Coupon:&nbsp;&nbsp;-${orders.cost.sign}${couponsFee}</p>
		        </#if>      		                            
            </div>
            <#if optionalTourList?has_content>
            <h4>Additional Services</h4>
        	<div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Date</p>
            	<#list optionalTourList as additionalProduct>
                <p class="Payment_Method_main_m">${additionalProduct.dayString}</p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Product description</p>
            	<#list optionalTourList as additionalProduct>
                <p class="Payment_Method_main_m"><a class="a4">${additionalProduct.name}</a></p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Unit Price</p>
            	<#list optionalTourList as additionalProduct>
                <p class="Payment_Method_main_m">${orders.cost.sign}${additionalProduct.unitcost}</p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Quantity</p>
            	<#list optionalTourList as additionalProduct>
                <p class="Payment_Method_main_m">${additionalProduct.quantity}</p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Total Fee</p>
            	<#list optionalTourList as additionalProduct>
            	<p class="Payment_Method_main_m">${orders.cost.sign}${additionalProduct.unitcost * additionalProduct.quantity}</p>
            	</#list>
            </div>  
            </#if>  
            <#if optionalTourInTourlineList?has_content>
            <h4>Additional Services</h4>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Product description</p>
            	<#list optionalTourInTourlineList as additionalProduct>
                <p class="Payment_Method_main_m"><a class="a4">${additionalProduct.name}</a></p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Unit Price</p>
            	<#list optionalTourInTourlineList as additionalProduct>
                <p class="Payment_Method_main_m">${orders.cost.sign}${additionalProduct.unitcost}</p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Quantity</p>
            	<#list optionalTourInTourlineList as additionalProduct>
                <p class="Payment_Method_main_m">${additionalProduct.quantity}</p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Total Fee</p>
            	<#list optionalTourInTourlineList as additionalProduct>
            	<p class="Payment_Method_main_m">${orders.cost.sign}${additionalProduct.unitcost * additionalProduct.quantity}</p>
            	</#list>
            </div>  
            </#if>  
            <#if additionalProductList?has_content>
            <h4>Additional Products</h4>
        	<div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Date</p>
            	<#list additionalProductList as additionalProduct>
                <p class="Payment_Method_main_m">${additionalProduct.dayString}</p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Product description</p>
            	<#list additionalProductList as additionalProduct>
                <p class="Payment_Method_main_m"><a class="a4">${additionalProduct.name}</a></p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Unit Price</p>
            	<#list additionalProductList as additionalProduct>
                <p class="Payment_Method_main_m">${orders.cost.sign}${additionalProduct.unitcost}</p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Quantity</p>
            	<#list additionalProductList as additionalProduct>
                <p class="Payment_Method_main_m">${additionalProduct.quantity}</p>
                </#list>
            </div>
            <div class="Payment_Method_tab_m">
            	<p class="Payment_Method_title_m">Total Fee</p>
            	<#list additionalProductList as additionalProduct>
            	<p class="Payment_Method_main_m">${orders.cost.sign}${additionalProduct.unitcost * additionalProduct.quantity}</p>
            	</#list>
            </div>  
            </#if>                            
            <div class="Payment_Method_tab_m Payment_r">
            	<h3 style="font-size:1rem; margin:10px 0;">Total price:<b style="color:red; margin-left:20px;">${orders.cost.sign}${orders.totalprice}</b></h3>
            </div>
            <#if frontCode == 'AUD'>
	            <div class="Payment_btn">
	                <a style="cursor:pointer;" href="javascript:toPayment('Paypal')"><img src="${ctx!}/assets-web/images/paypalbutton.jpg"></a>
	            </div>
            <#elseif frontCode == 'USD'>
	            <div class="Payment_btn">
	            	<a style="cursor:pointer;" href="javascript:toPayment('card');" style="margin-right:10px;"><img src="${ctx!}/assets-web/images/creditcardbutton.jpg"></a>
	                <a style="cursor:pointer;" href="javascript:toPayment('Paypal')"><img src="${ctx!}/assets-web/images/paypalbutton.jpg"></a>
	            </div>
            </#if>
            
        </div>
		<div id="mobileCardPayAero" class="Payment_Method_content shadow" style="display:none;">
            <div id="payment_credit" class="payments">
                	<div class="f16 paypt30 fb">
                    	<span class="mr10">You have chosen to pay by credit card</span>
                        <img alt="Visa" src="${ctx!}/assets-web/images/vmdpay2.png" class="payment_img">
                    </div>
                    <form name="kcredit" method="post" action="${ctx!}/card_pay.htm">
                    <input type="hidden" name="amount" class="CPriceInput"/>
                   	<input type="hidden" name="currencyCode" value="${orders.cost.code}"/>
			        <input type="hidden" name="orderNo" value="${orders.orderno}"/>
			        <input type="hidden" name="productName" value="${orders.orderdetails[0].product.name}"/>
			        <input type="hidden" name="ordersId" value="${orders.id}"/>
                    <div class="basicInformation" style="">
                      <div class="">
                        <table class="paytbl1">
                            <tr>
                                <td width="100%">
                                	<span class="f14-1">Card Type</span>
                                    <span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                                </td>
                            </tr>
                            <tr>
                                <td width="100%">
                                	<select class="cardTypeInput" name="cardtype">
                                    	<option value="0">Please Select</option>
                                      <option value="Visa">Visa</option>
                                      <option value="MasterCard">MasterCard</option>
                                      <option value="Discover">Discover</option>
                                      <option value="American Express">American Express</option>
                                  </select>
                                </td>
                            </tr>
                            <tr>
                                <td width="100%">
                                	<span class="f14-1">Card Number</span>
                                    <span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                                </td>
                            </tr>
                            <tr>
                                <td width="100%"><input type="text" class="cardNumberInput" name="cardnum"></td>
                            </tr>
                            <tr>
                                <td width="100%">
                                	<span class="f14-1">Card Expiration Date</span>
                                	<span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                                </td>
                            </tr>
                            <tr>
                                <td width="100%">
                                    <select class="expireMonthSelect" name="expireMonth" class="fl">
                                        <option value="01">01</option>
                                        <option value="02" selected="selected">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                    </select>
                                    <select class="expireYearSelect" name="expireYear" class="fr">
                                        <option value="2016" selected="selected">2016</option>
                                        <option value="2017">2017</option>
                                        <option value="2018">2018</option>
                                        <option value="2019">2019</option>
                                        <option value="2020">2020</option>
                                        <option value="2021">2021</option>
                                        <option value="2022">2022</option>
                                        <option value="2023">2023</option>
                                        <option value="2024">2024</option>
                                        <option value="2025">2025</option>
                                        <option value="2026">2026</option>
                                    </select>
                                    <div class="clear"></div>
                                </td>
                            </tr>
                            <tr>
                                <td width="100%">
                                	<span class="f14-1">Card Security Code</span>
                                    <span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                                </td>
                            </tr>
                            <tr>
                                <td width="100%"><input type="text" name="authenticationnum" class="input-small cardSecurityCode" maxlength="4"></td>
                            </tr>
                            <tr>
                                <td width="100%">
                                	<span class="f14-1">Country</span>
                                    <span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                                </td>
                            </tr>
                            <tr>
                                <td width="100%"><select class="countrySelect" name="country">
                                	<option value="US">United States</option>
                                		<#--
                                    	<option value="0">Please Select Your Country</option>
                                        <option value="CN">China</option>
                                        <option value="US">United States</option>
                                        <option value="AF">Afganistan</option>
                                        <option value="AL">Albania</option>
                                        <option value="DZ">Algeria</option>
                                        <option value="AS">American Samoa</option>
                                        <option value="AD">Andorra</option>
                                        <option value="AO">Angola</option>
                                        <option value="AI">Anguilla</option>
                                        <option value="AQ">Antarctica</option>
                                        <option value="AG">Antigua and Barbuda</option>
                                        <option value="AR">Argentina</option>
                                        <option value="AM">Armenia</option>
                                        <option value="AW">Aruba</option>
                                        <option value="AU">Australia</option>
                                        <option value="AT">Austria</option>
                                        <option value="AZ">Azerbaijan</option>
                                        <option value="BS">Bahamas</option>
                                        <option value="BH">Bahrain</option>
                                        <option value="BD">Bangladesh</option>
                                        <option value="BB">Barbados</option>
                                        <option value="BY">Belarus</option>
                                        <option value="BE">Belgium</option>
                                        <option value="BZ">Belize</option>
                                        <option value="BJ">Benin</option>
                                        <option value="BM">Bermuda</option>
                                        <option value="BT">Bhutan</option>
                                        <option value="BO">Bolivia</option>
                                        <option value="BA">Bosnia and Herzegowina</option>
                                        <option value="BW">Botswana</option>
                                        <option value="BV">Bouvet Island</option>
                                        <option value="BR">Brazil</option>
                                        <option value="IO">British Indian Ocean Territory</option>
                                        <option value="BN">Brunei Darussalam</option>
                                        <option value="BG">Bulgaria</option>
                                        <option value="BF">Burkina Faso</option>
                                        <option value="BI">Burundi</option>
                                        <option value="KH">Cambodia</option>
                                        <option value="CM">Cameroon</option>
                                        <option value="CA">Canada</option>
                                        <option value="CV">Cape Verde</option>
                                        <option value="KY">Cayman Islands</option>
                                        <option value="CF">Central African Republic</option>
                                        <option value="TD">Chad</option>
                                        <option value="CL">Chile</option>
                                        <option value="CN">China</option>
                                        <option value="CX">Christmas Island</option>
                                        <option value="CC">Cocos (Keeling) Islands</option>
                                        <option value="CO">Colombia</option>
                                        <option value="KM">Comoros</option>
                                        <option value="CG">Congo</option>
                                        <option value="CD">Congo, the Democratic Republic of the</option>
                                        <option value="CK">Cook Islands</option>
                                        <option value="CR">Costa Rica</option>
                                        <option value="CI">Cote d'Ivoire</option>
                                        <option value="HR">Croatia (Hrvatska)</option>
                                        <option value="CU">Cuba</option>
                                        <option value="CY">Cyprus</option>
                                        <option value="CZ">Czech Republic</option>
                                        <option value="DK">Denmark</option>
                                        <option value="DJ">Djibouti</option>
                                        <option value="DM">Dominica</option>
                                        <option value="DO">Dominican Republic</option>
                                        <option value="TP">East Timor</option>
                                        <option value="EC">Ecuador</option>
                                        <option value="EG">Egypt</option>
                                        <option value="SV">El Salvador</option>
                                        <option value="GQ">Equatorial Guinea</option>
                                        <option value="ER">Eritrea</option>
                                        <option value="EE">Estonia</option>
                                        <option value="ET">Ethiopia</option>
                                        <option value="FK">Falkland Islands (Malvinas)</option>
                                        <option value="FO">Faroe Islands</option>
                                        <option value="FJ">Fiji</option>
                                        <option value="FI">Finland</option>
                                        <option value="FR">France</option>
                                        <option value="FX">France, Metropolitan</option>
                                        <option value="GF">French Guiana</option>
                                        <option value="PF">French Polynesia</option>
                                        <option value="TF">French Southern Territories</option>
                                        <option value="GA">Gabon</option>
                                        <option value="GM">Gambia</option>
                                        <option value="GE">Georgia</option>
                                        <option value="DE">Germany</option>
                                        <option value="GH">Ghana</option>
                                        <option value="GI">Gibraltar</option>
                                        <option value="GR">Greece</option>
                                        <option value="GL">Greenland</option>
                                        <option value="GD">Grenada</option>
                                        <option value="GP">Guadeloupe</option>
                                        <option value="GU">Guam</option>
                                        <option value="GT">Guatemala</option>
                                        <option value="GN">Guinea</option>
                                        <option value="GW">Guinea-Bissau</option>
                                        <option value="GY">Guyana</option>
                                        <option value="HT">Haiti</option>
                                        <option value="HM">Heard and Mc Donald Islands</option>
                                        <option value="VA">Holy See (Vatican City State)</option>
                                        <option value="HN">Honduras</option>
                                        <option value="HK">Hong Kong</option>
                                        <option value="HU">Hungary</option>
                                        <option value="IS">Iceland</option>
                                        <option value="IN">India</option>
                                        <option value="ID">Indonesia</option>
                                        <option value="IR">Iran (Islamic Republic of)</option>
                                        <option value="IQ">Iraq</option>
                                        <option value="IE">Ireland</option>
                                        <option value="IL">Israel</option>
                                        <option value="IT">Italy</option>
                                        <option value="JM">Jamaica</option>
                                        <option value="JP">Japan</option>
                                        <option value="JO">Jordan</option>
                                        <option value="KZ">Kazakhstan</option>
                                        <option value="KE">Kenya</option>
                                        <option value="KI">Kiribati</option>
                                        <option value="KP">Korea, Democratic People's Republic of</option>
                                        <option value="KR">Korea, Republic of</option>
                                        <option value="KW">Kuwait</option>
                                        <option value="KG">Kyrgyzstan</option>
                                        <option value="LA">Lao People's Democratic Republic</option>
                                        <option value="LV">Latvia</option>
                                        <option value="LB">Lebanon</option>
                                        <option value="LS">Lesotho</option>
                                        <option value="LR">Liberia</option>
                                        <option value="LY">Libyan Arab Jamahiriya</option>
                                        <option value="LI">Liechtenstein</option>
                                        <option value="LT">Lithuania</option>
                                        <option value="LU">Luxembourg</option>
                                        <option value="MO">Macau</option>
                                        <option value="MK">Macedonia, The Former Yugoslav Republic of</option>
                                        <option value="MG">Madagascar</option>
                                        <option value="MW">Malawi</option>
                                        <option value="MY">Malaysia</option>
                                        <option value="MV">Maldives</option>
                                        <option value="ML">Mali</option>
                                        <option value="MT">Malta</option>
                                        <option value="MH">Marshall Islands</option>
                                        <option value="MQ">Martinique</option>
                                        <option value="MR">Mauritania</option>
                                        <option value="MU">Mauritius</option>
                                        <option value="YT">Mayotte</option>
                                        <option value="MX">Mexico</option>
                                        <option value="FM">Micronesia, Federated States of</option>
                                        <option value="MD">Moldova, Republic of</option>
                                        <option value="MC">Monaco</option>
                                        <option value="MN">Mongolia</option>
                                        <option value="MS">Montserrat</option>
                                        <option value="MA">Morocco</option>
                                        <option value="MZ">Mozambique</option>
                                        <option value="MN">Myanmar</option>
                                        <option value="NA">Namibia</option>
                                        <option value="NR">Nauru</option>
                                        <option value="NP">Nepal</option>
                                        <option value="NL">Netherlands</option>
                                        <option value="AN">Netherlands Antilles</option>
                                        <option value="NC">New Caledonia</option>
                                        <option value="NZ">New Zealand</option>
                                        <option value="NI">Nicaragua</option>
                                        <option value="NE">Niger</option>
                                        <option value="NG">Nigeria</option>
                                        <option value="NU">Niue</option>
                                        <option value="NF">Norfolk Island</option>
                                        <option value="MP">Northern Mariana Islands</option>
                                        <option value="NO">Norway</option>
                                        <option value="OM">Oman</option>
                                        <option value="PK">Pakistan</option>
                                        <option value="PW">Palau</option>
                                        <option value="PA">Panama</option>
                                        <option value="PG">Papua New Guinea</option>
                                        <option value="PY">Paraguay</option>
                                        <option value="PE">Peru</option>
                                        <option value="PH">Philippines</option>
                                        <option value="PN">Pitcairn</option>
                                        <option value="PL">Poland</option>
                                        <option value="PT">Portugal</option>
                                        <option value="PR">Puerto Rico</option>
                                        <option value="QA">Qatar</option>
                                        <option value="RE">Reunion</option>
                                        <option value="RO">Romania</option>
                                        <option value="RU">Russian Federation</option>
                                        <option value="RW">Rwanda</option>
                                        <option value="KN">Saint Kitts and Nevis</option>
                                        <option value="LC">Saint LUCIA</option>
                                        <option value="VC">Saint Vincent and the Grenadines</option>
                                        <option value="WS">Samoa</option>
                                        <option value="SM">San Marino</option>
                                        <option value="ST">Sao Tome and Principe</option>
                                        <option value="SA">Saudi Arabia</option>
                                        <option value="SN">Senegal</option>
                                        <option value="SC">Seychelles</option>
                                        <option value="SL">Sierra Leone</option>
                                        <option value="SG">Singapore</option>
                                        <option value="SK">Slovakia (Slovak Republic)</option>
                                        <option value="SI">Slovenia</option>
                                        <option value="SB">Solomon Islands</option>
                                        <option value="SO">Somalia</option>
                                        <option value="ZA">South Africa</option>
                                        <option value="GS">South Georgia and the South Sandwich Islands</option>
                                        <option value="ES">Spain</option>
                                        <option value="LK">Sri Lanka</option>
                                        <option value="SH">St. Helena</option>
                                        <option value="PM">St. Pierre and Miquelon</option>
                                        <option value="SD">Sudan</option>
                                        <option value="SR">Suriname</option>
                                        <option value="SJ">Svalbard and Jan Mayen Islands</option>
                                        <option value="SZ">Swaziland</option>
                                        <option value="SE">Sweden</option>
                                        <option value="CH">Switzerland</option>
                                        <option value="SY">Syrian Arab Republic</option>
                                        <option value="TW">Taiwan, Province of China</option>
                                        <option value="TJ">Tajikistan</option>
                                        <option value="TZ">Tanzania, United Republic of</option>
                                        <option value="TH">Thailand</option>
                                        <option value="TG">Togo</option>
                                        <option value="TK">Tokelau</option>
                                        <option value="TO">Tonga</option>
                                        <option value="TT">Trinidad and Tobago</option>
                                        <option value="TN">Tunisia</option>
                                        <option value="TR">Turkey</option>
                                        <option value="TM">Turkmenistan</option>
                                        <option value="TC">Turks and Caicos Islands</option>
                                        <option value="TV">Tuvalu</option>
                                        <option value="UG">Uganda</option>
                                        <option value="UA">Ukraine</option>
                                        <option value="AE">United Arab Emirates</option>
                                        <option value="GB">United Kingdom</option>
                                        <option value="US">United States</option>
                                        <option value="UM">United States Minor Outlying Islands</option>
                                        <option value="UY">Uruguay</option>
                                        <option value="UZ">Uzbekistan</option>
                                        <option value="VU">Vanuatu</option>
                                        <option value="VE">Venezuela</option>
                                        <option value="VN">Viet Nam</option>
                                        <option value="VG">Virgin Islands (British)</option>
                                        <option value="VI">Virgin Islands (U.S.)</option>
                                        <option value="WF">Wallis and Futuna Islands</option>
                                        <option value="EH">Western Sahara</option>
                                        <option value="YE">Yemen</option>
                                        <option value="YU">Yugoslavia</option>
                                        <option value="ZM">Zambia</option>
                                        <option value="ZW">Zimbabwe</option>
                                        -->
                                    </select>
                                </td>
                            </tr>
                        </table>
                      </div>
                      <div class="credit_img">
                        <div class="mt30"><img alt="credit" src="${ctx!}/assets-web/images/xinyongka.png"></div>
                        <div class="mt20 f12"><img class="mr5" src="${ctx!}/assets-web/images/icon_a2.png"> Accept Visa / Master / Discover / American Express payment </div>
                        <div class="f12"><img class="mr5" src="${ctx!}/assets-web/images/icon_a2.png"> Make sure that sufficient balance or credit card payment limit, and online payment function </div>
                      </div>
                      <div class="clear"></div>
                    </div>
                    <div class="lupay3" style="display:block;">
						<div class="f16 paypt30 fb"><span class="mr10">Billing Address</span></div>
                      <div class="">
                        <table class="paytbl1">
                          <tr>
                            <td width="100%">
                            	<span class="f14-1">First Name</span>
                                <span ><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                            </td>
                          </tr>
                          <tr>
                            <td width="00%"><input type="text" name="firstName" class="input-small firstNameInput"></td>
                          </tr>
                          <tr>
                            <td width="100%">
                            	<span class="f14-1">Last Name</span>
                                <span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                            </td>
                          </tr>
                          <tr>
                            <td width="100%"><input type="text" name="lastName" class="input-small lastNameInput"></td>
                          </tr>
                          <tr>
                            <td width="100%">
                            	<span class="f14-1">Street Address</span>
                                <span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                            </td>
                          </tr>
                          <tr>
                            <td width="100%"><input type="text" name="streetAddress" class="input-small streetAddressInput"></td>
                          </tr>
                          <tr>
                            <td width="100%">
                            	<span class="f14-1">City</span>
                                <span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                            </td>
                          </tr>
                          <tr>
                            <td width="100%"><input type="text" name="city" class="input-small cityInput"></td>
                          </tr>
                          <tr>
                            <td width="100%">
                            	<span class="f14-1">(US/CA) State</span>
                                <span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                            </td>
                          </tr>
                          <tr>
                            <td width="100%">
                              <select name="state" class="stateSelect">
                                    <option></option>
									<option value="AB">AB</option>
									<option value="AK">AK</option>
									<option value="AL">AL</option>
									<option value="AR">AR</option>
									<option value="AZ">AZ</option>
									<option value="BC">BC</option>
									<option value="CA">CA</option>
									<option value="CO">CO</option>
									<option value="CT">CT</option>
									<option value="DC">DC</option>
									<option value="DE">DE</option>
									<option value="FL">FL</option>
									<option value="GA">GA</option>
									<option value="HI">HI</option>
									<option value="IA">IA</option>
									<option value="ID">ID</option>
									<option value="IL">IL</option>
									<option value="IN">IN</option>
									<option value="KS">KS</option>
									<option value="KY">KY</option>
									<option value="LA">LA</option>
									<option value="MA">MA</option>
									<option value="MB">MB</option>
									<option value="MD">MD</option>
									<option value="ME">ME</option>
									<option value="MI">MI</option>
									<option value="MN">MN</option>
									<option value="MO">MO</option>
									<option value="MS">MS</option>
									<option value="MT">MT</option>
									<option value="NB">NB</option>
									<option value="NC">NC</option>
									<option value="ND">ND</option>
									<option value="NE">NE</option>
									<option value="NF">NF</option>
									<option value="NH">NH</option>
									<option value="NJ">NJ</option>
									<option value="NM">NM</option>
									<option value="NT">NT</option>
									<option value="NS">NS</option>
									<option value="NU">NU</option>
									<option value="NV">NV</option>
									<option value="NY">NY</option>
									<option value="OH">OH</option>
									<option value="OK">OK</option>
									<option value="ON">ON</option>
									<option value="OR">OR</option>
									<option value="PA">PA</option>
									<option value="PE">PE</option>
									<option value="QC">QC</option>
									<option value="RI">RI</option>
									<option value="SC">SC</option>
									<option value="SD">SD</option>
									<option value="SK">SK</option>
									<option value="TN">TN</option>
									<option value="TX">TX</option>
									<option value="UT">UT</option>
									<option value="VA">VA</option>
									<option value="VT">VT</option>
									<option value="WA">WA</option>
									<option value="WI">WI</option>
									<option value="WV">WV</option>
									<option value="WY">WY</option>
									<option value="YK">YK</option>
									<option value="AA">AA</option>
									<option value="AE">AE</option>
									<option value="AP">AP</option>
									<option value="AS">AS</option>
									<option value="FM">FM</option>
									<option value="GU">GU</option>
									<option value="MH">MH</option>
									<option value="MP">MP</option>
									<option value="PR">PR</option>
									<option value="PW">PW</option>
									<option value="VI">VI</option>
								</select>
                            </td>
                          </tr>
                          <tr>
                            <td width="100%">
                            	<span class="f14-1">Zip/Post Code</span>
                            	<span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                            </td>
                          </tr>
                          <tr>
                            <td width="100%"><input type="text" name="postCode" class="input-small zipInput"></td>
                          </tr>
                          <tr>
                            <td width="100%">
                            	<span class="f14-1">Phone Number</span>
                            	<span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                            </td>
                          </tr>
                          <tr>
                            <td width="100%"><input type="text" name="phoneNumber" class="input-small phoneNumberInput"></td>
                          </tr>
                          <tr>
                            <td width="100%">
                            	<span class="f14-1">Email</span>
                            	<span><img src="${ctx!}/assets-web/images/icon_a2.png"></span>
                            </td>
                          </tr>
                          <tr>
                            <td width="100%"><input type="text" name="email" class="input-small emailInput"></td>
                          </tr>                                                    
                        </table>
                      </div>
                    </div>
                    <div class="visa_button">
                    	<p class="visa_note f14-1" style="display:none;"><img class="mr5" src="${ctx!}/assets-web/images/icon_a2.png"> 2.2% handling charge will be paid on credit card payment</p>
                        <p style="text-align: right;">
                        	<span class="visa_price"></span>
                            <input class="visa_submit" type="button" value="Pay Now">
                        </p>
                    </div>
                </form>
              </div>
        </div>
        <div class="Payment_terms">
            <input id="mobileAgreement1" type="checkbox" value="Y">
            I confirm that I understand that this tour package is non-refundable and I will cover all expenses on my own in the event of an emergency if I do not have travel insurance.</br>
           	 <input id="mobileAgreement" type="checkbox" value="Y"> I have read and agree to
             <a href="${ctx!}${areaSuffix}/terms_conditions.html" target="_blank">Terms & Conditions</a>.<#--<a href="" target="_blank">modification & cancellation terms</a>、<a href="${ctx!}${areaSuffix}/privacy_policy.html" target="_blank">terms of use & privacy policy</a> -->       
        </div>
        <div class="Payment_Method_btns">
        </div>
        <div class="clear"></div>
		</div>
    </div>
  </div>
</section>
<#--bottom-->
<#include "/front/include/paybottom.ftl"/>
<#--bottom-->
<div id="payForm" style="display:none;">
	<#-- 本地版 
	<form id="paypalForm" action="${paypalGateway!}" method="post" target="_blank">
	    <input type="hidden" name="cmd" value="_xclick"/>
	    <input type="hidden" name="business" value="${paypalAccount!}"/>
	    <input type="hidden" name="item_name" value="${orders.orderdetails[0].product.name}    from www.intertrips.com"/>
        <input type="hidden" name="item_number" value="${orders.orderno}"/> 
		<input type="hidden" name="amount" class="price"/>
		<input type="hidden" name="currency_code" value="${(orders.cost.code)!}"/>
		<input type="hidden" name="invoice" value="${orders.id}"/>
        <input type="hidden" name="cancel_return" value="http://127.0.0.1/intertrips/">
		<input type="hidden" name="return" value="http://127.0.0.1/intertrips/ipnPay/return.do">
	    <input type="hidden" name="notify_url" value="https://www.intertrips.com/ipnPay/notify2.do"/>
	    <input type="hidden" name="custom" class="paytype" value="5"/>
	</form>
	-->

	
	<#-- 外网 -->
	<form id="paypalForm" action="${paypalGateway!}" method="post" target="_blank">
	    <input type="hidden" name="cmd" value="_xclick"/>
	    <input type="hidden" name="business" value="${paypalAccount!}"/>
	    <input type="hidden" name="item_name" value="${orders.orderdetails[0].product.name}   from www.intertrips.com"/>
        <input type="hidden" name="item_number" value="${orders.orderno}"/> 
        <input type="hidden" name="invoice" value="${orders.id}"/>
		<input type="hidden" name="amount" class="price"/>
		<input type="hidden" name="currency_code" value="${(orders.cost.code)!}"/>
        <input type="hidden" name="cancel_return" value="https://www.intertrips.com/">
		<input type="hidden" name="return" value="https://www.intertrips.com/ipnPay/return.do">
	    <input type="hidden" name="notify_url" value="https://www.intertrips.com/ipnPay/notify2.do"/>
	    <input type="hidden" name="custom" class="paytype" value="5"/>
	</form>
	
	
	<#-- 1.5 
	<form id="paypalForm" action="${paypalGateway!}" method="post" target="_blank">
	    <input type="hidden" name="cmd" value="_xclick"/>
	    <input type="hidden" name="business" value="${paypalAccount!}"/>
	    <input type="hidden" name="item_name" value="${orders.orderdetails[0].product.name}   from www.intertrips.com"/>
        <input type="hidden" name="item_number" value="${orders.orderno}"/> 
        <input type="hidden" name="invoice" value="${orders.id}"/>
		<input type="hidden" name="amount" class="price"/>
		<input type="hidden" name="currency_code" value="${(orders.cost.code)!}"/>
        <input type="hidden" name="cancel_return" value="https://192.168.1.5">
		<input type="hidden" name="return" value="https://192.168.1.5/ipnPay/return.do">
	    <input type="hidden" name="notify_url" value="https://www.intertrips.com/ipnPay/notify2.do"/>
	    <input type="hidden" name="custom" class="paytype" value="5"/>
	</form>	
	-->
	 
	<#-- 外网test 
	<form id="paypalForm" action="${paypalGateway!}" method="post" target="_blank">
	    <input type="hidden" name="cmd" value="_xclick"/>
	    <input type="hidden" name="business" value="${paypalAccount!}"/>
	    <input type="hidden" name="item_name" value="${orders.orderdetails[0].product.name}   from www.intertrips.com"/>
        <input type="hidden" name="item_number" value="${orders.orderno}"/> 
        <input type="hidden" name="invoice" value="${orders.id}"/>
		<input type="hidden" name="amount" class="price"/>
		<input type="hidden" name="currency_code" value="${(orders.cost.code)!}"/>
        <input type="hidden" name="cancel_return" value="https://47.88.18.248:8081/intertrips/">
		<input type="hidden" name="return" value="https://47.88.18.248:8081/intertrips/ipnPay/return.do">
	    <input type="hidden" name="notify_url" value="https://47.88.18.248:8081/intertrips/ipnPay/notify2.do"/>
	    <input type="hidden" name="custom" class="paytype" value="5"/>
	</form>	
	-->
	
	<#--
	<form id='authorizeSimForm' method='post' target="_blank" action='${simGateway!}'>
		<input type='hidden' name='x_login' value='${loginId!}' />
		<input type='hidden' name='x_amount' class='price'/>
		<input type='hidden' name='x_currency_code' value='${(orders.cost.code)!}' />
		<input type='hidden' name='x_description' value='Tour Code:${orders.orderdetails[0].product.code} Tour Name:${orders.orderdetails[0].product.name}  Date:${{orders.orderdetails[0].departureDate?string('yyyy-MM-dd')}  Departure:${departureName!}    from www.intertrips.com' />
		<input type='hidden' name='x_invoice_num' value='${orders.orderno}' />
		<input type='hidden' name='x_fp_sequence' id="x_fp_sequence_input" />
		<input type='hidden' name='x_fp_timestamp' id="x_fp_timestamp_input" />
		<input type='hidden' name='x_fp_hash' id="x_fp_hash_input"/>
		<input type='hidden' name='x_test_request' value='false' />
		<input type='hidden' name='x_show_form' value='PAYMENT_FORM' />
		<input type='hidden' name='x_version' value='3.1' />
		<input type='hidden' name='x_relay_response' value='TRUE' />
		<input type='hidden' name='x_relay_url' value='https://www.intertrips.com/authorizeSim/relay.do'/>
	</form>
	-->
</div>
<#include "/front/include/alertFrame.ftl"/>
<script type="text/javascript">
<#--
if (typeof jQuery.fn.fitVids === "undefined") { document.write("<script src='${ctx!}/assets-web/js/jquery.fitvids.min.js'>\x3C/script>"); }
-->
var price = "${orders.totalprice?c}";

var $agreement;
var $agreement1;
var $cardPay;

var bePaying = 0;

$(function(){
	  //根据window版本和mobile版本调整导航栏
	  if($("#mobileDiv").is(":hidden")){
	  	  $('#MainMenu').addClass('scrolled menu-shrink');
	  	  $(window).scroll(function(){
	  	  	 $('#MainMenu').addClass('scrolled menu-shrink');
	  	  });
	  	  $agreement = $("#windowAgreement");
	  	  $agreement1 = $("#windowAgreement1");
		  $cardPay = $("#cardPayAero");	
	  }else{
	  	 $('#MainMenu').addClass('scrolled');   
		 $agreement = $("#mobileAgreement");
		 $agreement1 = $("#mobileAgreement1");
		 $cardPay = $("#mobileCardPayAero");	  
	  }
	  
	<#--
	$("#showPrice").html('${bookInfo.cost.sign} ' + price);
	-->
	$(".visa_price").html('${orders.cost.sign}' + price);
	  var myDate = new Date();
	  var month = myDate.getMonth()+1;       //获取当前月份(2位)
	  var year = myDate.getFullYear();   	//获取完整的年份(4位,1970-????)
	  if(parseInt(month)<10){
		  month = "0"+month;
	  }
	  $(".expireMonthSelect").val(month);
	  $(".expireYearSelect").val(year);
	  
	 //根据选择的国家判断
	 $(".countrySelect").change(function(){
	    if($(this).val() == "US" || $(this).val() == "CA"){
	    	$(this).closest(".basicInformation").next().fadeIn(150);
	    }else{
	    	$(this).closest(".basicInformation").next().fadeOut(150);
	    }
	 });
	 
	 $(".visa_submit").click(function(){
		 checkPayment();
	 });
	 
	 <#--
	 if('${sendMail!}' == '1'){
		 //发送邮件
		 $.get("${ctx!}/front/orders/sendMail.do",function(result){
		 	if(result != 'ok'){
			    alertWarn('Failure sending mail');	 	
		 	}
		 });
	 }
	 -->
});

	<#-- 支付 -->
	function toPayment(payMethod){

		//如果用户没有同意用户条款,则弹出提示
		if(!$agreement.is(":checked")){
			alertWarn('please agree with the user agreement.');
			$agreement.focus();	
			return;
		}
		//如果用户没有同意用户条款,则弹出提示
		if(!$agreement1.is(":checked")){
			alertWarn('please agree with the user agreement.');
			$agreement1.focus();	
			return;
		}

		//给支付form设置支付金额
		var $payForm = $("#payForm");
		$payForm.find("input.price").val(price);
		
		if("Paypal" == payMethod){
			var isCheck = checkStore();
			if(isCheck){
				$("#paypalForm").submit();
			}
		}else if("card" == payMethod){
			$cardPay.slideDown();
			<#--
			var isReturn = false;
			$.ajax({
				type:"post",
				url:"${ctx}/front/orders/getFingerprint.do",
				data:{price:price,currencySign:"${(bookInfo.cost.code)!}"},
				dataType:"json",
				async:false,
				success:function(data){
					$("#x_fp_sequence_input").val(data.sequence);
					$("#x_fp_timestamp_input").val(data.timeStamp);
					$("#x_fp_hash_input").val(data.fingerprintHash);
					isReturn = true;
					//setTimeout('$("#authorizeSimForm").submit();',300);
					//setTimeout('$("#authorizeSimForm").submit();',300);
				},
				error:function(data){
				},
			});
			if(isReturn){
				$("#authorizeSimForm").submit();
			}
			-->
		}	
	}
	
	function checkPayment() {
	
		//如果用户没有同意用户条款,则弹出提示
		if(!$agreement.is(":checked")){
			alertWarn('please agree with the user agreement.');
			$agreement.focus();	
			return;
		}	
		//如果用户没有同意用户条款,则弹出提示
		if(!$agreement1.is(":checked")){
			alertWarn('please agree with the user agreement.');
			$agreement1.focus();	
			return;
		}	
		$(".CPriceInput").val(price);
		
		var re = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/i;
		
		var $payForm = $cardPay.find("form");
		
		var $cardTypeSelect = $payForm.find(".cardTypeInput");
		var $cardNumber = $payForm.find(".cardNumberInput");
		var $authenticationNum = $payForm.find(".cardSecurityCode");
		var $country = $payForm.find(".countrySelect");
		
		if($cardTypeSelect.val() == 0) {
			alertWarn("Please select the card type.");
			addWarnningShow($cardTypeSelect);
			return;
		} else if ($cardNumber.val().trim() == "") {
			alertWarn("Please fill the card number.");
			addWarnningShow($cardNumber);
			return;
		} else if ($authenticationNum.val().trim() == "") {
			alertWarn("Please fill in the Card Security Code.");
			addWarnningShow($authenticationNum);
			return;
		} else if ($country.val() == 0) {
			alertWarn("Please select country.");
			addWarnningShow($country);
			return;
		} else if ($country.val() == "US" || $country.val() == "CA") {
			var $firstNameInput = $payForm.find(".firstNameInput");
			var $lastNameInput = $payForm.find(".lastNameInput");
			var $streetAddressInput = $payForm.find(".streetAddressInput");
			var $cityInput = $payForm.find(".cityInput");
			var $stateSelect = $payForm.find(".stateSelect");
			var $zipInput = $payForm.find(".zipInput");
			var $phoneNumberInput = $payForm.find(".phoneNumberInput");
			var $emailInput = $payForm.find(".emailInput");
			
			if ($firstNameInput.val().trim() == "") {
				alertWarn("Please fill in the first name.");
				addWarnningShow($firstNameInput);
				return;
			} else if ($lastNameInput.val().trim() == "") {
				alertWarn("Please fill in the last name.");
				addWarnningShow($lastNameInput);
				return;
			} else if ($streetAddressInput.val().trim() == "") {
				alertWarn("Please fill in the street address.");
				addWarnningShow($streetAddressInput);
				return;
			} else if ($cityInput.val().trim() == "") {
				alertWarn("Please fill in the city.");
				addWarnningShow($cityInput);
				return;
			} else if ($stateSelect.val().trim() == "") {
				alertWarn("Please select state.");
				addWarnningShow($stateSelect);
				return;
			} else if ($zipInput.val().trim() == "") {
				alertWarn("Please fill in the zip.");
				addWarnningShow($zipInput);
				return;
			} else if ($phoneNumberInput.val().trim() == ""){
				alertWarn("Please fill in the phone number.");
				addWarnningShow($phoneNumberInput);
				return;
			} else if ($emailInput.val().trim() == ""){
				alertWarn("Please fill in the email.");
				addWarnningShow($emailInput);
				return;
			} else if (!re.test($emailInput.val().trim())){
				alertWarn("Please enter the valid email.");
				addWarnningShow($emailInput);
				return;
			}else {
			    submitBooking($payForm);
				return;
			}
		} else {
			submitBooking($payForm);
			return;
		}
	}
	
	var submitBooking = function($payForm){
		if(bePaying == 0){
			var isCheck = checkStore();
			if(isCheck){
			    bePaying = 1;
				setTimeout(refreshPayingStatus,60000);
				$payForm.submit();
			}
		}
	}
	
	var refreshPayingStatus = function(){
	    bePaying = 0;
	}
	
	//给输入框添加必填的提示以及获取焦点后去除提示
	var addWarnningShow = function($input){
		$input.addClass("pay_mess_input_error");
		$input.focus(function(){
			$(this).removeClass("pay_mess_input_error");
		});
	}
	
	var checkStore = function(){
		var checkResult;
	    $.ajax({
	        async: false,
	        type : "GET",
	        url : '${ctx!}/front/orders/checkStore.do',
	        data: {ordersId:"${orders.id}",price:price},
	        success : function(result) {
				if(result == ''){
					checkResult =  true;
				}else if(result == '-2'){
					alertWarn("Your order has been paid or cancelled!");
					checkResult = false;
				}else if(result == '-1'){
					alertWarn("Wrong Price!");
					checkResult = false;
				}else{
					window.location.href = '${ctx!}' + result;
					checkResult = false;
				}
	        }
	    });
	    return checkResult;
	}
</script> 
</body>
</html>