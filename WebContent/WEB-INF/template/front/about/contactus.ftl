<!DOCTYPE html>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<#assign ctx = request.contextPath />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="keywords" content="${(page.metakeywords)!}" />
	<meta name="description" content="${(page.metadescription)!}"/>
	<title>${(page.metatitle)!}</title>
	<link rel="shortcut icon" href="${ctx!}/favicon.ico">
	<link rel="apple-touch-icon-precomposed" href="${ctx!}/apple-touch-icon.png">
	<link type="text/css" media="all" href="${ctx!}/assets-web/css/index-min.css" rel="stylesheet" />
	<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
	<!--[if lt IE 9]> 
	<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv-printshiv.min.js?ver=4.3.1'></script> <![endif]-->
	<!--[if lt IE 9]> 
	<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js?ver=4.3.1'></script> 
	<![endif]-->
	<meta name="generator">
</head>
<body class="blog">
<div id="top"></div>
<#include "/front/include/top.ftl"/>
<section class="hero small-hero" style="background-image: url(${ctx!}/assets-web/images/contact-us-bg.jpg);color:#fff;position: relative;background-color:#6c6e73;background-position: center;background-size: cover;background-repeat: no-repeat;">
  <div class="bg-overlay">
    <div class="container" style="">
      <div class="breadcrumbs">
          <ul>
              <li><a href="${ctx!}/">Home</a></li>
              <li><a href="${ctx}${(page.filePath)!}">Contact Us</a></li>
          </ul>
      </div>
      <div class="list-intro-wrap">
        <h1 class="intro-title" style="text-align:center; margin-top:0;">Questions or comments? Get in touch.</h1>
      </div>
    </div>
  </div>
</section>
<section class="featured-destinations" style="background-color:#fff; padding-bottom:40px;">
	<div class="container">
		<div class="contact-box">
	        <div class="contact-top">
	          <h4>Contact Us</h4>
	        </div>
	        <div class="contact-content">
					<form action="${ctx!}/front/contact/save.do" method="post" id="frm">
						<input type="hidden" name="costnumber" value="${costnumber}"/> 
						<div class="contactbox">
							<ul class="contactbox-ul">
								<li class="left"><label>Email *</label> <input type="text" class="contactbox-text" name="email" id="email" value=""></li>
								<li class="right"><label>Name *</label> <input type="text" class="contactbox-text" name="name" id="name" value=""></li>
								<li class="left"><label>Nationality *</label> 
	                            	<select id="nationality" class="contactbox-text" name="nationality">
										<option value="">--Please select--</option>		
	                                    <option value="United States">United States</option>
	                                    <option value="Australia">Australia</option>
	                                    <option value="Canada">Canada</option>
	                                    <option value="Japan">Japan</option>
	                                    <option value="China">China</option>
										<!--Country()-->
									</select>
	                            </li>
								<li class="right"><label>Phone No. *</label><input type="text" class="contactbox-text" name="phoneNo" id="phoneNo" value=""></li>
								<li class="left"><label>Subject *</label><input type="text" class="contactbox-text" name="subject" id="subject" value=""></li>
	                            <div class="clear"></div>
							</ul>
						</div>
						<div class="contactbox">
							<ul class="contactbox-ul-2">
								<li class="commentsbox">
	                            	<label class="comm">Comments *</label>
									<textarea class="commentsbox-textarea" name="comments" id="comments"></textarea>
	                                <div class="clear"></div>
	                            </li>
							</ul>
							<p class="typehere_margin">
								<input type="text" class="contactbox-text2" name="safe" id="safe" value=""> 
	                            <img id="captchaCode" src="${ctx!}/captcha.do?d='+new Date().getTime();" onclick="changeCaptcha(this);" alt="" title="Click here" align="absmiddle">
	                            <input name="btn_submit" id="btn_submit" value="Submit" class="imginput" onclick="submitForm();" type="button">
							</p>
						</div>
						<div class="clear"></div>
					</form>
					<div class="con_main">
						<h4>intertrips.com</h4>
						<div class="sidebar_l">
							<#list officeContactsList as officeContacts>
								<#if officeContacts_index%2 == 0>
								<div class="con_main_box left">
								<#else>
								<div class="con_main_box right">
								</#if>
									<span class="con_main_box_t"> <strong>${officeContacts.officeName}</strong></span>
									<ul>
										<li><span class="bold">T:</span>${officeContacts.tel}</li>
										<li><span class="bold">F:</span>${officeContacts.fax}</li>
										<li><span class="bold">E:</span> <a title="blocked::mailto:${officeContacts.email}" href="mailto:${officeContacts.email}">${officeContacts.email}</a></li>
										<li><span class="bold">A:</span>${officeContacts.address}</li>
									</ul>
								</div>	
							</#list>
							<#--
							<div class="con_main_box">
								<span class="con_main_box_t"> <strong>Headquarter  Office</strong></span>
								<ul>
									<li><span class="bold">T:</span> (888) 410-4111 (626) 377-9888</li>
									<li><span class="bold">F:</span> (626) 602-7786</li>
									<li><span class="bold">E:</span> <a title="blocked::mailto:info@chinatour.com" href="mailto:info@chinatour.com">info@chinatour.com</a></li>
									<li><span class="bold">A:</span> 680 Brea Canyon Road, Suite 268, Diamond Bar, CA 91789</li>
								</ul>
							</div>
							<div class="con_main_box">
								<span class="con_main_box_t"><strong>NY Flushing Office</strong></span>
								<ul>
									<li><span class="bold">T:</span> (718) 412-0111</li>
									<li><span class="bold">F:</span> (718) 932-3814</li>
									<li><span class="bold">E:</span> <a title="blocked::mailto:ny@chinatour.com" href="mailto:ny@chinatour.com">ny@chinatour.com</a></li>
									<li><span class="bold">A:</span> 40-06 Main St, Rm 3A, Flushing, NY 11354</li>
								</ul>
							</div>
							<div class="con_main_box">
								<span class="con_main_box_t"><strong>SF Downtown Office</strong></span>
								<ul>
									<li><span class="bold">T:</span> (415) 876-7888 (866) 244-6287 </li>
									<li><span class="bold">F:</span> (415) 294-9004</li>
									<li><span class="bold">E:</span> <a title="blocked::mailto:sf@chinatour.com" href="mailto:sf@chinatour.com">sf@chinatour.com</a></li>
									<li><span class="bold">A:</span> 918 Clement St, Suite 101,
										San Francisco, CA 94118</li>
								</ul>
							</div>
							<div class="con_main_box">
								<span class="con_main_box_t"><strong>SF Pleasanton Office</strong></span>
								<ul>
									<li><span class="bold">T:</span> (925) 521-6999 </li>
									<li><span class="bold">F:</span> (415) 294-9004</li>
									<li><span class="bold">E:</span> <a title="blocked::mailto:sf@chinatour.com" href="mailto:sf@chinatour.com">sf@chinatour.com</a></li>
									<li><span class="bold">A:</span> 4299 Rosewood Dr., Suite 101, Pleasanton, CA 94588 </li>
								</ul>
							</div>
							<div class="con_main_box">
								<span class="con_main_box_t"><strong>LA San Gabriel Office</strong></span>
								<ul>
									<li><span class="bold">T:</span> (626) 576-9688 (888) 664-5678</li>
									<li><span class="bold">F:</span> (626) 576-9388</li>
									<li><span class="bold">E:</span> <a title="blocked::mailto:la@chinatour.com" href="mailto:la@chinatour.com">la@chinatour.com</a></li>
									<li><span class="bold">A:</span> 301 W.Valley Blvd. #220, San Gabriel, CA 91776</li>
								</ul>
							</div>
							<div class="con_main_box">
								<span class="con_main_box_t"><strong>LA Hacienda Heights Office</strong></span>
								<ul>
									<li><span class="bold">T:</span> (626) 768-9865</li>
									<li><span class="bold">F:</span> (626) 576-9388</li>
									<li><span class="bold">E:</span> <a title="blocked::mailto:lahh@chinatour.com" href="mailto:lahh@chinatour.com">lahh@chinatour.com</a></li>
									<li><span class="bold">A:</span> 17120 Colima Rd. #109, Hacienda Heights, CA 91745</li>
								</ul>
							</div>
							<div class="con_main_box">
								<span class="con_main_box_t"><strong> Vancouver Office</strong></span>
								<ul>
									<li><span class="bold">T:</span> (604) 800-6411 / 1 (888) 880-7718 </li>
									<li><span class="bold">E:</span> <a title="blocked:colin.z@chinatour.com" href="mailto:colin.z@chinatour.com">colin.z@chinatour.com</a></li>
									<li><span class="bold">A:</span> 5160-4000 No.3 Road, Richmond, BC V6X 0J8</li>
								</ul>
							</div>
							<div class="con_main_box">
								<span class="city">Spain</span> <span class="con_main_box_t">Madrid
									Office</span>
								<ul>
									<li><span class="bold">T:</span> (0034) 9130-88695
										</li>
									<li><span class="bold">E:</span> <a title="blocked::mailto:espana@chinatour.com" href="mailto:espana@chinatour.com">espana@chinatour.com</a></li>
									<li><span class="bold">A:</span> Calle Orense 66 1A, Madrid 28020 España</li>
								</ul>
							</div>
							<div class="con_main_box">
								<span class="con_main_box_t"><strong>Xi'an China Office (Operation Center)</strong></span>
								<ul>
									<li><span class="bold">T:</span>  +86 (029) 8866-3092 </li>
									<li><span class="bold">F:</span>  +86 (029) 8556-2093</li>
									<li><span class="bold">E:</span> <a title="blocked::mailto:op@chinatour.com" href="mailto:op@chinatour.com">op@chinatour.com</a></li>
									<li><span class="bold">A:</span> Unit-801, No.107 West Street, Lianhu District, Xi'an, China </li>
								</ul>
							</div>
							<div class="con_main_box">
								<span class="con_main_box_t"><strong>Beijing China Office</strong></span>
								<ul>
									<li><span class="bold">T:</span>  +86 (010) 6520-6752</li>
									<li><span class="bold">&nbsp;&nbsp;&nbsp;&nbsp;</span>  +86 (010) 8460-5505</li>
									<li><span class="bold">F:</span>  +86 (010) 6520-6793</li>
									<li><span class="bold">E:</span> <a title="blocked::mailto:bj@chinatour.com" href="mailto:bj@chinatour.com">bj@chinatour.com</a></li>
									<li><span class="bold">A:</span>  Section D, 7th Floor, Room 50726, Chaoyang Men SOHO Galaxy, Dongcheng District, Beijing China</li>
								</ul>
							</div>
							
							<div class="con_main_box">
								<span class="con_main_box_t"><strong>Suzhou China Office</strong></span>
								<ul>
									<li><span class="bold">T:</span>  +86 (0512) 6760-0096</li>
									<li><span class="bold">F:</span> +86 (0512) 6760-0122</li>
									<li><span class="bold">E:</span> <a title="blocked::mailto:sz@chinatour.com" href="mailto:sz@chinatour.com">sz@chinatour.com</a></li>
									<li><span class="bold">A:</span> Rm 1611, BLK A, No. 388 Suya Rd, SIP, Suzhou, China 215021</li>
								</ul>
							</div>
							
							<div class="con_main_box">
								<span class="con_main_box_t"><strong>Website Management Center</strong></span>
								<ul>
									<li><span class="bold">E:</span> <a title="blocked::mailto:webmaster@chinatour.com" href="mailto:webmaster@chinatour.com">webmaster@chinatour.com</a>
									</li>
								</ul>
							</div> 
							-->
							<div class="clear"></div>                      
						</div>
					</div>
				</div>
	    	</div>
	    </div>
</section>
<#include "/front/include/bottom.ftl"/>
<#include "/front/include/alertFrame.ftl"/>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-38017454-1', 'auto');
  ga('send', 'pageview');

</script>
<script type="text/javascript">
if (typeof jQuery.fn.fitVids === "undefined") { document.write("<script src='js/jquery.fitvids.min.js'>\x3C/script>"); }
	
	$(document).ready(function(){
		//限制字符个数
		$(".atgrid-item-description").each(function(){
			var maxwidth=110;
				if($(this).text().length>maxwidth){
				$(this).text($(this).text().substring(0,maxwidth));
				$(this).html($(this).html()+'…');
			}
		});
	});
	
	var $form = $("#frm");
	var $captchaCode = $("#captchaCode");
	var $submitButton = $("#btn_submit");
	var $email = $("#email");
	var $name = $("#name");
	var $nationality = $("#nationality");
	var $phoneNo = $("#phoneNo");
	var $subject = $("#subject");
	var $comments = $("#comments");
	var $captcha = $("#safe"); 
	
	var changeCaptcha = function(img){
		img.src = "${ctx!}/captcha.do?d=" + new Date().getTime();
	}
	
	var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
	
	var submitForm = function(){
		var isError = false;
		if($email.val().trim() == ''){
			alertWarn('Please fill in your email address.');
			$email.addClass("pay_mess_input_error");
			$email.focus(function(){
				$(this).removeClass("pay_mess_input_error");
			});
			isError = true;
			return;
		}
		if(!reg.test($email.val().trim())){
			alertWarn('Please enter the valid email.');
			$email.addClass("pay_mess_input_error");
			$email.focus(function(){
				$(this).removeClass("pay_mess_input_error");
			});
			isError = true;
			return;
		}
		if($name.val().trim() == ''){
			alertWarn('Please fill in your name.');
			$name.addClass("pay_mess_input_error");
			$name.focus(function(){
				$(this).removeClass("pay_mess_input_error");
			});
			isError = true;
			return;
		}
		if($nationality.val().trim() == ''){
			alertWarn('Please fill in your nationality.');
			$nationality.addClass("pay_mess_input_error");
			$nationality.focus(function(){
				$(this).removeClass("pay_mess_input_error");
			});
			isError = true;
			return;
		}
		if($phoneNo.val().trim() == ''){
			alertWarn('Please fill in your phone number.');
			$phoneNo.addClass("pay_mess_input_error");
			$phoneNo.focus(function(){
				$(this).removeClass("pay_mess_input_error");
			});
			isError = true;
			return;
		}
		if($subject.val().trim() == ''){
			alertWarn('Please fill in your subject.');
			$subject.addClass("pay_mess_input_error");
			$subject.focus(function(){
				$(this).removeClass("pay_mess_input_error");
			});
			isError = true;
			return;
		}
		if($comments.val().trim() == ''){
			alertWarn('Please fill in your comments.');
			$comments.addClass("pay_mess_input_error");
			$comments.focus(function(){
				$(this).removeClass("pay_mess_input_error");
			});
			isError = true;
			return;
		}
		if($captcha.val().trim() == ''){
			alertWarn('Please fill in your captcha.');
			$captcha.addClass("pay_mess_input_error");
			$captcha.focus(function(){
				$(this).removeClass("pay_mess_input_error");
			});
			isError = true;
			return;
		}
		$.ajax({
            type: "POST",
			url: "${ctx!}/register/isCaptcha.do",
            async: false,
            data: {captcha:$("#safe").val()},
            success:function(result){
            	if(result != true){
            		alertWarn('Please fill the correnct captcha.');
					$captcha.addClass("pay_mess_input_error");
					$captcha.focus(function(){
						$(this).removeClass("pay_mess_input_error");
					});            		
            		isError = true;
            		return;
            	}
            }
		});
		if(!isError){
			$.ajax({
	 			type: "POST",
		   		url: $form.attr("action"),
		   		dataType: "json",
		   		data: $form.serialize(),
		   		success:function(message){
		   			alertWarn(message);
		   			$email.val('');
		   			$name.val('');
		   			$nationality.val("");
		   			$phoneNo.val('');
		   			$subject.val('');
		   			$comments.val('');
		   			$captcha.val('');
		   		},
		   		error:function(e){
		   		}
	 		});	  
		}
	}
	$("#captchaCode").click();
</script>	
</body>
</html>