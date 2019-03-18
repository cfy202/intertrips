/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.wenjing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.wenjing.Setting.AccountLockType;
import com.wenjing.entity.Admin;
import com.wenjing.service.AdminService;
import com.wenjing.util.SettingUtils;

/**
 * 权限认证
 *
 * @author SHOP++ Team
 * @version 3.0
 */
public class AuthenticationRealm extends AuthorizingRealm {

  
    @Resource
    private AdminService adminService;

    /**
     * 获取认证信息
     *
     * @param token 令牌
     * @return 认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
        AuthenticationToken authenticationToken = (AuthenticationToken) token;
        String username = authenticationToken.getUsername();
        String password = new String(authenticationToken.getPassword());
        String ip = authenticationToken.getHost();
        
        if (username != null && password != null) {
            Admin admin = adminService.findByUsername(username);
            if (admin == null) {
                throw new UnknownAccountException();
            }
            if (!admin.isIsenabled()) {
                throw new DisabledAccountException();
            }
            Setting setting = SettingUtils.get();
            if (admin.isIslocked()) {
                if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.admin)) {
                    int loginFailureLockTime = setting.getAccountLockTime();
                    if (loginFailureLockTime == 0) {
                        throw new LockedAccountException();
                    }
                    Date lockedDate = admin.getLockeddate();
                    Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
                    if (new Date().after(unlockDate)) {
                        admin.setLoginfailurecount(0);
                        admin.setIslocked(false);
                        admin.setLockeddate(null);
                        adminService.update(admin);
                    } else {
                        throw new LockedAccountException();
                    }
                } else {
                	admin.setLoginfailurecount(0);
                    admin.setIslocked(false);
                    admin.setLockeddate(null);
                    adminService.update(admin);
                }
            }
            if (!DigestUtils.md5Hex(password).equals(admin.getPassword())) {
                int loginFailureCount = admin.getLoginfailurecount() + 1;
                if (loginFailureCount >= setting.getAccountLockCount()) {
                    admin.setIslocked(true);
                    admin.setLockeddate(new Date());
                }
                admin.setLoginfailurecount(loginFailureCount);
                adminService.update(admin);
                throw new IncorrectCredentialsException();
            }
            admin.setLoginip(ip);
            admin.setLogindate(new Date());
            admin.setLoginfailurecount(0);
            adminService.update(admin);
            return new SimpleAuthenticationInfo(new Principal(admin.getId(), username), password, getName());
        }
        throw new UnknownAccountException();
    }

    /**
     * 获取授权信息
     *
     * @param principals principals
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
        if (principal != null) {
        List<String > auth = new ArrayList<String>();
           String authoritiesa = adminService.findAuthorities(principal.getId());
            if (authoritiesa != null) {
            	String [] str = authoritiesa.split(",");
            	for (String string : str) {
            		auth.add(string);
				}
            	
                SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                authorizationInfo.addStringPermissions(auth);
                return authorizationInfo;
            }
        }
        return null;
    }

}