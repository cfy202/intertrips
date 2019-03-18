<section class="regular" style=" background-image: url(${ctx!}/assets-web/background/newsletter_bg.jpg);">
	<div class="container">
    	<div class="row">
            <div class="title-row" style=" text-align:center;">
                <h3 class="title-entry-2" style="font-size:20px; padding-right:0; color:#fff;">JOIN THE NEWSLETTER</h3>
            </div>
            <div class="col-xs-12" >
                <div class="home-search-field">
                	<form class="big-search" role="search" method="get" action=""> 
                    	<input type="text" name="s" placeholder="Your Email..." id="EMAIL" value=""> 
                        <button type="button" onclick="saveEmail()">Subscribe</button>
                        <div class="clear"></div>
                    </form>
                </div>
                <div class="clear"></div>
            </div>
    	</div>
	</div>
</section>

<footer class="page-container-responsive" style="background-image:url(${ctx!}/assets-web/background/footer_bg.jpg); background-repeat:repeat-x; padding:50px 0;">
	<div class="container">
        <div class="row">
            <div class="col-md-3 col-md-offset-1">
                <div class="language-curr-picker">
                   <#--
                    <div class="select widget">
                        <select class="language-selector" aria-labelledby="language-selector-label">
                           
                            <option value="en" selected="">English</option>
                           
                            <option value="id">Bahasa Indonesia</option>
                            <option value="ms">Bahasa Melayu</option>
                            <option value="ca">Català</option>
                            <option value="da">Dansk</option>
                            <option value="de">Deutsch</option>
                            <option value="es">Español</option>
                            <option value="el">Eλληνικά</option>
                            <option value="fr">Français</option>
                            <option value="it">Italiano</option>
                            <option value="hu">Magyar</option>
                            <option value="nl">Nederlands</option>
                            <option value="no">Norsk</option>
                            <option value="pl">Polski</option>
                            <option value="pt">Português</option>
                            <option value="fi">Suomi</option>
                            <option value="sv">Svenska</option>
                            <option value="tr">Türkçe</option>
                            <option value="is">Íslenska</option>
                            <option value="cs">Čeština</option>
                            <option value="ru">Русский</option>
                            <option value="th">ภาษาไทย</option>
                            <option value="zh">中文 (简体)</option>
                            <option value="zh-TW">中文 (繁體)</option>
                            <option value="ja">日本語</option>
                            <option value="ko">한국어</option
							
                        </select>
                    </div>   
                   -->               
                <div class="select widget">
                    <select class="currency-selector" aria-labelledby="currency-selector-label">
                        <option value="USD">United States</option>
                        <option value="AUD"> Australia</option>
                        <#--
                        <option value="CNY" selected="">CNY</option>
                        <option value="CAD">CAD</option>
                        <option value="EUR">EUR</option>
                        
                        <option value="AED">AED</option>
                        <option value="ARS">ARS</option>
                        <option value="BGN">BGN</option>
                        <option value="BRL">BRL</option>
                        <option value="CHF">CHF</option>
                        <option value="CRC">CRC</option>
                        <option value="CZK">CZK</option>
                        <option value="DKK">DKK</option>
                        <option value="GBP">GBP</option>
                        <option value="HKD">HKD</option>
                        <option value="HRK">HRK</option>
                        <option value="HUF">HUF</option>
                        <option value="IDR">IDR</option>
                        <option value="ILS">ILS</option>
                        <option value="INR">INR</option>
                        <option value="JPY">JPY</option>
                        <option value="KRW">KRW</option>
                        <option value="MAD">MAD</option>
                        <option value="MXN">MXN</option>
                        <option value="MYR">MYR</option>
                        <option value="NOK">NOK</option>
                        <option value="NZD">NZD</option>
                        <option value="PEN">PEN</option>
                        <option value="PHP">PHP</option>
                        <option value="PLN">PLN</option>
                        <option value="RON">RON</option>
                        <option value="RUB">RUB</option>
                        <option value="SEK">SEK</option>
                        <option value="SGD">SGD</option>
                        <option value="THB">THB</option>
                        <option value="TRY">TRY</option>
                        <option value="TWD">TWD</option>
                        <option value="UAH">UAH</option>
                        <option value="VND">VND</option>
                        <option value="ZAR">ZAR</option>
                        -->
                    </select>
                </div>
                <div class="cx-number"></div>
                </div>
            </div>
            <#--下导航-->
            <div id="downNavigation" class="col-md-2 col-md-offset-1 hidden-xs">
            </div>
            <#--
            <div class="col-md-2 hidden-xs">
                <h2 class="h5" style="margin:0; color:#fff; margin-bottom:10px;">Discover</h2>
                <ul class="list-layout">
                    <li><a href="" class="link-contrast">Trust &amp; Safety</a></li>
                    <li><a href="" class="link-contrast">Invite Friends</a></li>
                    <li><a href="" class="link-contrast">Gift Cards</a></li>
                    <li><a href="" class="link-contrast">Airbnb Picks</a></li>
                    <li><a href="" class="link-contrast">Mobile</a></li>
                    <li><a href="" class="link-contrast">Support NYC</a></li>
                    <li><a href="" class="link-contrast">Business Travel</a></li>
                    <li><a href="" class="link-contrast">Site Map</a></li>
                </ul>
            </div>
            
            <div class="col-md-2 hidden-xs">
                <h2 class="h5" style="margin:0; color:#fff; margin-bottom:10px;">Hosting</h2>
                <ul class="list-layout">
                    <li><a href="" class="link-contrast">Why Host</a></li>
                    <li><a href="" class="link-contrast">Hospitality</a></li>
                    <li><a href="" class="link-contrast">Responsible Hosting</a></li>
                    <li><a href="" class="link-contrast">Home Safety</a></li>
                </ul>
            </div>
            -->
            <#--下导航-->
        </div>
        <#--手机版下导航-->
        <div id="mobileDownNavigation" class="space-4 space-top-4 hide visible-xs">
        </div>
        <#--手机版下导航-->
       
	</div>
</footer>
<section class="featured-destinations" style="background-color:#ffffff;">
	<div class="hezuo">
    	<img src="${ctx!}/assets-web/images/footer_icon.png">
    </div>
    <div class="text-center media">
        <div class="space-top-2 text-muted">Copyright  ©  2015 China Travel CA Inc All Rights Reserved -  California Seller of Travel #208037040</div>
    </div>
</section>
<#include "/front/include/login.ftl"/>
<#--
<link rel="stylesheet" href="${ctx!}/assets-web/css/jquery.bigautocomplete.css" type="text/css" />
<script type="text/javascript" src="${ctx!}/assets-web/js/jquery.bigautocomplete.js"></script>
-->
<script>
//邮箱订阅
function saveEmail(){
	var email = $("#EMAIL").val();
	var re = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/i;
	if(re.test(email) == false){
		alert("Please fill in the correct email.");
	}else{
		$.ajax({
			type: "POST",
			url: "${ctx!}/front/index/email.do",
			data: {email:email},
			success: function(data) {
				if(data == false){
					$("#EMAIL").val("");
					alert("Email already exists, please fill in the other email.");
				}else{
					$("#EMAIL").val("");
					alert("Email subscription successful,thank you.");
					$.ajax({
						type: "POST",
						url: "${ctx!}/front/index/send.do",
						data: {email:email},
						success: function(data) {
						},
						error: function(e) {
						},
					});
				}
			},
			error: function(e) {
				alert("Email subscription failed, please re-enter.");
			},
		});
	}
}

$(function(){
	$(".currency-selector").change(function(){
		var cValue = $(".currency-selector").val();
		setCurrency(cValue);
	})
})

<#--
var a=new Array();
		$(function(){
		   var width = $('#tt').width();
			$.ajax({
			type: "POST",
			url: "${ctx!}/front/index/getDestination.do",
			
			cache:true,
			success: function(data) {
			   $.each(data, function(i, des) {
			   a[i]={};
			   a[i].title =des['name'];
			});
			  $("#tt").bigAutocomplete({width:width,data:a,callback:function(data){
				$("#tt").val(data.title)
				
			}});
			 $("#SearchMore").bigAutocomplete({width:250,data:a,callback:function(data){
				$("#SearchMore").val(data.title)
				
			}});
			},
			error: function(e) {
			},
		});
		
		
		
		})
--> 
</script>