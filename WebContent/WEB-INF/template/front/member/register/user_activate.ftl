<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <#assign ctx = request.contextPath />
    <title>Re send validation message</title>
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/public.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/login.css" />
    <link rel="stylesheet" type="text/css" href="${ctx!}/assets-web/css/alert.css" />
    <script type="text/javascript" src="${ctx!}/assets-web/js/jquery-1.7.2.min.js"></script>
	<script src="${ctx!}/assets/js/jquery-validate/jquery.validate.min.js"></script> 
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	
	  ga('create', 'UA-67304615-1', 'auto');
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
				}
			},
		},
		messages: {
			account: {
				required: "Please enter the Email address",
                email: "Mailbox format error",
                remote: "The account did not fill in the registration information, <a href=\"${ctx!}/register.htm\">please register</a>"
			},
		},
	});
});
</script>
<body class="blog">
<section id="div_bg" >
	<div class="login-wrapper">
      <div class="login-container"> 
        <a href="/" title="Back Home" class="logo"></a>
        <div class="signup-forms">
          <div class="signup-box" >
            <div class="add-info" id="add-info">
              <form action="${ctx!}/register/sync_activate.htm" method="post" id="registerForm">
                 <div class="form-field">
	              <#if message?? && message!="">
		                 <div class="alert alert-error">
			                <div class="mt5">
			                   ${message}<br>
			                </div>
		                 </div>
		           </#if>
	              <input name="account" id="account" type="text" placeholder="email" autocomplete="off"  class="" value="${account!}">
	            </div>
	            <div class="submit-btn">
	              <button type="submit">Send validation messages</button>
	            </div>
	            <div class="form-bottom">
	            	<a href="${ctx!}/login.htm" class="form-field-a right">Existing account login</a>
	                <div class="clear"></div>
	            </div>
              </form>
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

