<script type='text/javascript' src="https://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script type='text/javascript' src="https://cdn.bootcss.com/jquery-validate/1.15.0/jquery.validate.min.js"></script>
<script type='text/javascript' src='https://cdn.bootcss.com/fitvids/1.1.0/jquery.fitvids.min.js'></script> 
<script type="text/javascript" src="${ctx!}/assets-web/js/common-frontInclude-key-lazyLoading-min.js"></script>

<!--登录弹出框-->
<div class="theme-popover">
	 <a href="javascript:;" title="close" class="theme-close">×</a>
     <header class="theme-popover-header">
        <div class="theme-popover-header-image">
          <img alt="Envato" class="is-hidden-no-svg is-hidden-no-js" src="${ctx!}/assets-web/images/logo-sign.png" width="185">
        </div>
     </header>
     <div class="theme-popover-section">
        <div class="theme-popover-modal">
        	<div class="theme-popover-box">
            	<div class="theme-popover-sign-in">
                  <form accept-charset="UTF-8"  action="${ctx!}/login/submit.do" id="loginForm" class="" method="post" >
                    <div class="theme-popover-group">
                      <div class="theme-popover-label">
                          <label>Email</label>
                      </div>
                      <div class="theme-popover-input">
                          <input class="form-input" name="account" id="account" type="text">
                      </div>
                    </div>
                    <div class="theme-popover-group">
                        <div class="theme-popover-label">
                            <label>Password</label>
                        </div>
                        <div class="theme-popover-input">
                            <input class="form-input"  name="password" id="password" type="password">
                        </div>
                        <div id="theme-login-error" class="theme-popover-input">
                        </div>
                    </div>
                    <div class="theme-popover-group">
                        <div class="theme-popover-label">
                            <label>Authentication Code</label>
                        </div>
                        <div class="theme-popover-input" id="loginCode">
                            <input class="theme-popover-captcha" name="captcha" id="captcha" size="50" type="text">
                        </div>
                    </div>
                    <input class="theme-popover-submit" name="commit" type="submit" id="sub-login"  value="Sign in to your account">
                  </form>
            	</div>
                <div class="theme-rm">
                    <div class="left"><a class="theme-password" id="theme-password" href="javascript:;">Forget Password</a></div>
                    <div class="right"><a class="theme-register" id="theme-register" href="javascript:;">Create Account</a></div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
  </div>
</div>


<!--注册弹出框-->
<div class="theme-popover-register">
     <a href="javascript:;" title="close" class="theme-close">×</a>
     <header class="theme-popover-header">
        <div class="theme-popover-header-image">
          <img alt="Envato" class="is-hidden-no-svg is-hidden-no-js" src="${ctx!}/assets-web/images/logo-sign.png" width="185">
        </div>
     </header>
     <div class="theme-popover-section">
        <div class="theme-popover-modal">
        	<div class="theme-popover-box">
            	<div class="theme-popover-sign-in">
                  <form accept-charset="UTF-8" action="${ctx!}/register/submit.do" id="registerForm" class="" method="post" >
                    <div class="theme-popover-group">
                      <div class="theme-popover-label">
                          <label>Email</label>
                      </div>
                      <div class="theme-popover-input">
                          <input class="form-input " name="account_register" id="account_register" type="text">
                      </div>
                     
                    </div>
                    <div class="theme-popover-group">
                        <div class="theme-popover-label">
                            <label>Password</label>
                        </div>
                        <div class="theme-popover-input">
                            <input class="form-input"  name="password_register" id="password_register" type="password">
                        </div>
                    </div>
                    <div class="theme-popover-group">
                        <div class="theme-popover-label">
                            <label>Confirm Password</label>
                        </div>
                        <div class="theme-popover-input">
                            <input class="form-input"  name="confirm_password_register" id="confirm_password_register" type="password">
                        </div>
                    </div>
                    <div class="theme-popover-group">
                        <div class="theme-popover-label">
                            <label>Authentication Code</label>
                        </div>
                        <div class="theme-popover-input" id="regestCode">
                            <input class="theme-popover-captcha" name="captcha_register" id="captcha_register" size="50" type="text">
                        </div>
                    </div>
                    <input class="theme-popover-submit" name="commit" type="submit" value="Create Account">
                  </form>
            	</div>
                <div class="theme-rm">
                    <div class="right">Already got an account? <a class="theme-login"  id="theme-register" href="javascript:;">Sign in here</a></div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
  </div>
</div>

<!--注册成功提示框-->
<div class="theme-popover-password" id="theme-popover-password">
     <a href="javascript:;" title="close" class="theme-close">×</a>
     <header class="theme-popover-header">
        <div class="theme-popover-header-image">
          <img alt="Envato" class="is-hidden-no-svg is-hidden-no-js" src="${ctx!}/assets-web/images/logo-sign.png" width="185">
        </div>
     </header>
     <div class="theme-popover-section">
        <div class="theme-popover-modal">
        	<div style="margin-left:80px; width:70%;"> 
        	
        		<#--           	
                <p style="font-size:14px; margin-bottom:5px;">Thanks for signing up with Intertrips!</p>
                <p style="font-size:14px; margin-bottom:5px;">A confirmation email has been send to your email box. </p> 
                <p><a href="" style="color:#565a5c; text-decoration:underline;">A confirmation email has been send to your email box.</a> </p> 
                -->  
                <p style="font-size:14px; margin-bottom:5px;">You have successfully registered, please sign in!</p>  
                <p><a class="theme-login" style="color:0db4ff; text-decoration:none;cursor:pointer;float:right;">Sign in</a> </p>           	
            </div>
        </div>
  </div>
</div>
 
<!--密码修改-->
<div class="theme-popover-password" >
     <a href="javascript:;" title="close" class="theme-close">×</a>
     <header class="theme-popover-header">
        <div class="theme-popover-header-image">
          <img alt="Envato" class="is-hidden-no-svg is-hidden-no-js" src="${ctx!}/assets-web/images/logo-sign.png" width="185">
        </div>
     </header>
     <div class="theme-popover-section">
        <div class="theme-popover-modal">
        	<div class="theme-popover-box">
            	<div class="theme-popover-sign-in">
                  <form accept-charset="UTF-8" action="${ctx!}/password/send_resetemail.do" id="resetForm" class="" method="post" >
                    <div class="theme-popover-group">
                      <div class="theme-popover-label">
                          <label>Email</label>
                      </div>
                      <div class="theme-popover-input">
                          <input type="text" name="account_reset" id="account_reset" class="ipt" size="20" autocomplete="off" placeholder="please enter email!"/>
                          
                      </div>
                    </div>
                    <input class="theme-popover-submit" name="commit" type="submit" value="Password Retrieve">
                  </form>
            	</div>
                <div class="theme-rm">
                    <div class="right">Already got an account? <a class="theme-login" id="theme-register" href="javascript:;">Sign in here</a></div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
  </div>
</div>
<div class="theme-popover-mask"></div>

<!--手机弹出登陆框-->
<div class="theme-popover-mobile">
	 <a href="javascript:;" title="close" class="theme-close-mobile">×</a>
     <header class="theme-popover-header-mobile">
        <div class="theme-popover-header-image-mobile">
          <img alt="Envato" class=""  src="${ctx!}/assets-web/images/logo-sign.png"  width="185">
        </div>
     </header>
     <div class="theme-popover-section-mobile">
        <div class="theme-popover-modal-mobile">
        	<div class="theme-popover-box-mobile">
            	<div class="theme-popover-sign-in-mobile">
                  <form accept-charset="UTF-8" action="${ctx!}/login/submit.do" id="mobileLoginForm" class="" method="post" novalidate>
                    <div class="theme-popover-group-mobile">
                      <div class="theme-popover-label-mobile">
                          <label>Email</label>
                      </div>
                      <div class="theme-popover-input-mobile">
                          <input class="form-input-mobile account" name="account" id="account_mobile" type="text">
                      </div>
                    </div>
                    <div class="theme-popover-group-mobile">
                        <div class="theme-popover-label-mobile">
                            <label>Password</label>
                        </div>
                        <div class="theme-popover-input-mobile">
                            <input class="form-input-mobile" name="password" id="password_mobile" type="password">
                        </div>
                        <div id="theme-login-error-mobile" class="theme-popover-input-mobile">
                        </div>
                    </div>
                    <div class="theme-popover-group-mobile">
                        <div class="theme-popover-label-mobile">
                            <label>Authentication Code</label>
                        </div>
                        <div class="theme-popover-input-mobile" id="loginCode-mobile">
                            <input class="theme-popover-captcha-mobile" name="captcha" id="captcha_mobile" size="50" type="text">
                        <img src="https://www.intertrips.com/captcha.do?d='+new Date().getTime()" title="" onclick="this.src='https://www.intertrips.com/captcha.do?d='+new Date().getTime()" class="yzm-img-mobile loginCode"><div class="clear loginCode"></div></div>
                    </div>
                    <input class="theme-popover-submit-mobile" name="commit" type="submit" id="sub-login-mobile" value="Sign in to your account">
                  </form>
            	</div>
                <div class="theme-rm-mobile">
                    <div class="left"><a class="theme-password-mobile" id="theme-password-mobile" href="javascript:;">Forget Password</a></div>
                    <div class="right"><a class="theme-register-mobile" id="theme-register-mobile" href="javascript:;">Create Account</a></div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
  </div>
</div>

<!--手机注册弹出框-->
<div class="theme-popover-register-mobile">
     <a href="javascript:;" title="close" class="theme-close-mobile">×</a>
     <header class="theme-popover-header-mobile">
        <div class="theme-popover-header-image-mobile">
          <img alt="Envato" class="" src="${ctx!}/assets-web/images/logo-sign.png" width="185">
        </div>
     </header>
     <div class="theme-popover-section-mobile">
        <div class="theme-popover-modal-mobile">
        	<div class="theme-popover-box-mobile">
            	<div class="theme-popover-sign-in-mobile">
                  <form accept-charset="UTF-8" action="${ctx!}/register/submit.do" id="mobileRegisterForm" class="" method="post" novalidate>
                    <div class="theme-popover-group-mobile">
                      <div class="theme-popover-label-mobile">
                          <label>Email</label>
                      </div>
                      <div class="theme-popover-input-mobile">
                          <input class="form-input-mobile account" name="account_register" id="account_register_mobile" type="text">
                      </div>
                     
                    </div>
                    <div class="theme-popover-group-mobile">
                        <div class="theme-popover-label-mobile">
                            <label>Password</label>
                        </div>
                        <div class="theme-popover-input-mobile">
                            <input class="form-input-mobile" name="password_register" id="password_register_mobile" type="password">
                        </div>
                    </div>
                    <div class="theme-popover-group-mobile">
                        <div class="theme-popover-label-mobile">
                            <label>Confirm Password</label>
                        </div>
                        <div class="theme-popover-input-mobile">
                            <input class="form-input-mobile" name="confirm_password_register" id="confirm_password_register" type="password">
                        </div>
                    </div>
                    <div class="theme-popover-group-mobile">
                        <div class="theme-popover-label-mobile">
                            <label>Authentication Code</label>
                        </div>
                        <div class="theme-popover-input-mobile" id="regestCode-mobile">
                            <input class="theme-popover-captcha-mobile" name="captcha_register" id="captcha_register_mobile" size="50" type="text">
                        </div>
                    </div>
                    <input class="theme-popover-submit-mobile" name="commit" type="submit" value="Create Account">
                  </form>
            	</div>
                <div class="theme-rm-mobile">
                    <div class="right">Already got an account? <a class="theme-login-mobile" id="theme-register-mobile" href="javascript:;">Sign in here</a></div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
  </div>
</div>
<!--手机注册成功提示框-->
<div class="theme-popover-password-mobile" id="theme-popover-password-mobile">
     <a href="javascript:;" title="close" class="theme-close-mobile">×</a>
     <header class="theme-popover-header-mobile">
        <div class="theme-popover-header-image-mobile">
          <img alt="Envato" class="" src="${ctx!}/assets-web/images/logo-sign.png" width="185">
        </div>
     </header>
     <div class="theme-popover-section-mobile">
        <div class="theme-popover-modal-mobile">
        	<div style="margin-left:80px; width:70%;">   
        		<#--
                <p style="font-size:14px; margin-bottom:5px;">Thanks for signing up with Intertrips!</p>
                <p style="font-size:14px; margin-bottom:5px;">A confirmation email has been send to your email box. </p> 
                -->
                <p style="font-size:14px; margin-bottom:5px;">You have successfully registered, please sign in!</p>  
                <p><a class="theme-login-mobile" style="color:0db4ff; text-decoration:none;cursor:pointer;float:right;">Sign in</a> </p> 
            </div>
        </div>
  </div>
</div>
<!--手机密码修改框-->
<div class="theme-popover-password-mobile">
     <a href="javascript:;" title="close" class="theme-close-mobile">×</a>
     <header class="theme-popover-header-mobile">
        <div class="theme-popover-header-image-mobile">
          <img alt="Envato" class="" src="${ctx!}/assets-web/images/logo-sign.png" width="185">
        </div>
     </header>
     <div class="theme-popover-section-mobile">
        <div class="theme-popover-modal-mobile">
        	<div class="theme-popover-box-mobile">
            	<div class="theme-popover-sign-in-mobile">
                  <form accept-charset="UTF-8" action="${ctx!}/password/send_resetemail.do" id="resetForm" class="" method="post">
                    <div class="theme-popover-group-mobile">
                      <div class="theme-popover-label-mobile">
                          <label>Email</label>
                      </div>
                      <div class="theme-popover-input-mobile">
                          <input class="form-input-mobile" name="account" type="text">
                      </div>
                    </div>
                    <input class="theme-popover-submit-mobile" name="commit" type="submit" value="Password Retrieve">
                  </form>
            	</div>
                <div class="theme-rm-mobile">
                    <div class="right">Already got an account? <a class="theme-login-mobile" id="theme-register-mobile" href="javascript:;">Sign in here</a></div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
  </div>
</div>
<div class="theme-popover-mask-mobile"></div>