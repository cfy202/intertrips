<!DOCTYPE html>
<html lang="en-US" >
<head>
<meta charset="UTF-8">
<#assign ctx = request.contextPath />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" media="all" href="${ctx!}/assets-web/css/index.css" rel="stylesheet" />
<title>login</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="apple-touch-icon-precomposed" href="apple-touch-icon.png">
<link rel='stylesheet' id='font-awesome-css' href="${ctx!}/assets-web/css/font-awesome-4.4.0/css/font-awesome.min.css" type='text/css' media='all' />
<script type='text/javascript' src='${ctx!}/assets-web/js/jquery-1.10.2.min.js'></script>
<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script>


<!-- 密钥 -->
<script type="text/javascript" src="${ctx!}/assets/js/key/rsa.js"></script>
<script type="text/javascript" src="${ctx!}/assets/js/key/base64.js"></script> 
<script type="text/javascript" src="${ctx!}/assets/js/key/jsbn.js"></script> 
<script type="text/javascript" src="${ctx!}/assets/js/key/prng4.js"></script> 
<script type="text/javascript" src="${ctx!}/assets/js/key/rng.js"></script> 
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.2/html5shiv-printshiv.min.js?ver=4.3.1'></script> <![endif]-->
<!--[if lt IE 9]> 
<script type='text/javascript' src='//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js?ver=4.3.1'></script> 
<![endif]-->

<meta name="generator">
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-38017454-1', 'auto');
  ga('send', 'pageview');

</script>
</head>
<script type="text/javascript">
jQuery(document).ready(function($){
	$("form#registerForm").validate({
	    errorElement: "div",
        errorClass:"err-tip",
		rules: {
			account: {
				required: true,
				email: true,
				remote: {
                    type: "post",
                    url: "${ctx!}/register/isExist.do",
                    data: {
                        account: function() {
                            return $("#account").val();
                        }
                    },
                    dataType: "html",
                    dataFilter: function(data,type) {
			              if(data=="true"){  
			                  return false;
			               }else{  
			                  return true;
			               }   
                    }
				}
			},
			
			password: {
				required: true,
			    minlength: 6,
			    maxlength: 20,
			},
			
			confirm_password: {
                required: true,
                equalTo: "#password"
           },
           
           captcha: {
                required: true,
                remote: {
                    type: "post",
                    url: "${ctx!}/register/isCaptcha.do",
                    data: {
                        captcha: function() {
                            return $("#captcha").val();
                        }
                    },
                }
           }
		},
		
		messages: {
			account: {
                required: "Enter your email address",
                email: "Invalid email format",
                remote: "Email unregistered"
			},
			
			password: {
                required: "Enter password",
                minlength: "Your Password's length should be between 6-20 characters",
                maxlength: "Your Password's length should be between 6-20 characters"
			},
			
			confirm_password: {
                required: "Enter confirm password",
                equalTo: " The two passwords you typed do not match"
           },
           captcha: {
                required: "Enter verification code",
                remote: "Wrong verification code"
           },
		},
		
		submitHandler: function(form) {
			$.ajax({
				url: "${ctx!}/common/public_key.do",
				type: "GET",
				dataType: "json",
				cache: false,
				beforeSend: function() {
                            $('#sub-register').attr("disabled", true);
                            $("#loading_hint").show();
                            $("#hint").text("Being registered...");
                },
				success: function(data) {
					var rsaKey = new RSAKey();
					rsaKey.setPublic(b64tohex(data.modulus), b64tohex(data.exponent));
					var enPassword = hex2b64(rsaKey.encrypt($("#password").val()));
					$.ajax({
						url: $("#registerForm").attr("action"),
						type: "POST",
						data: {
						    account: $("#account").val(),
							enPassword: enPassword,
							refid : $("#refid").val(),
						},
						dataType: "json",
						cache: false,
						complete: function(message) {
						    $("#loading_hint").hide();
                            $('#sub-register').attr("disabled", false);
							var result = eval("(" + message.responseText + ")");  
				              if(result.success) {  
				                 $("#add-info").hide();
				                 $("#add-sessucess").show();
				                
				                } else {  
				                  $("#register-error").html(result.message);
				                }   
						}
					});
				}
			});
		}
	});
});
//再次激活
function activate () {
  var account = $("#add-success-email").text();
  window.location.href= "${ctx!}/register/user_activate.htm?account=" + account;
  <#--
	$.ajax({
        url:  "${ctx!}/register/again_activate.do",
        type: "post",
        data: {account: $("#add-success-email").text()},
        dataType: "json",
        cache: false,
        beforeSend: function() {
            $("#loading_hint").show();
            $("#hint").text("请稍等...");
        },
        success: function(data) {
        	$("#loading_hint").hide();
            if (data.success) {
            	alert("激活邮件发送成功");
                $("#email-success-account").text(data.message);
            } else {
                $("#theme-register-error").html(data.message);
            }
        }
    });
    -->
}
</script>
<body class="blog">
<section id="div_bg" >
	<div class="login-wrapper">
      <div class="login-container"> 
        <a href="/" title="Back Home" class="logo"></a>
        <div class="signup-forms">
          <div class="signup-box" >
            <div class="add-info" id="add-info">
              <form action="${ctx!}/register/submit.do" method="post" id="registerForm">
                <div class="form-field">
                  <input name="account" id="account" type="text" placeholder="Email Address" autocomplete="off"  class="login-email" value="">
                </div>
                <div class="form-field">
                  <input name="password" id="password" type="password" placeholder="Password" autocomplete="off"  class="login-password" value="">
                </div>
                <div class="form-field">
                  <input name="confirm_password" id="confirm_password" type="password" placeholder="Confirm Password" autocomplete="off"  class="login-password" value="">
                </div>
                <div class="form-field">
                  <div class="">
                   <input name="captcha" id="captcha" type="text" placeholder="Verification Code" autocomplete="off" class="form-field-CAPTCHA">
                   <a href="" class="vcode-send">
                    <img src="captcha.do?d='+new Date().getTime()" title="" onclick="this.src='captcha.do?d='+new Date().getTime()" class="yzm-img" style="width:120px;height:46px;">
                   </a>
                   <div class="clear"></div>
                  </div>
                </div>
                <div class="submit-btn">
                  <button type="submit">Create Account</button>
                </div>
                <div class="form-bottom">
                    <a href="${ctx!}/login.htm" class="form-field-a right">Already got an account? Sign in here</a>
                    <div class="clear"></div>
                </div>
              </form>
            </div>
            <div class="add-info" id="add-sessucess" style="display:none">
            <div class="form-field">
                <p>Mail has been sent to your email address.</p>
                <p class="add-success-email" id="add-success-email"></p>
                <p>Please click on the verification link in the mailbox to complete the verification.</p>
                
            </div>
            <div class="form-field">
                <p class="add-success-down-title">------------Did not receive the message?------------</p>
                <p>Try again<a href="javascript:;" onclick="activate();" style="color:#ff872f;">Application to send</a>Verification link</p>
            </div>
          </div>
          </div>
        </div>
      </div>
    </div>
</section>


 
<!-- Dynamic page generated in 0.606 seconds. --> 
<!-- Cached page generated by WP-Super-Cache on 2015-11-09 15:27:54 --> 

<!-- Compression = gzip -->
</body>

</html>