/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.wenjing.service;


import com.wenjing.util.RSAUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Service - RSA安全
 *
 * @author SHOP++ Team
 * @version 3.0
 */
@Service("rsaServiceImpl")
public class RSAService  {

    /**
     * "私钥"参数名称
     */
    private static final String PRIVATE_KEY_ATTRIBUTE_NAME = "privateKey";

    /**
	 * 生成密钥(添加私钥至Session并返回公钥)
	 * 
	 * @param request
	 *            httpServletRequest
	 * @return 公钥
	 */
    @Transactional(readOnly = true)
    public RSAPublicKey generateKey(HttpServletRequest request) {
        Assert.notNull(request);
        KeyPair keyPair = RSAUtils.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        HttpSession session = request.getSession();
        session.setAttribute(PRIVATE_KEY_ATTRIBUTE_NAME, privateKey);
        return publicKey;
    }

    /**
	 * 移除私钥
	 * 
	 * @param request
	 *            httpServletRequest
	 */
    @Transactional(readOnly = true)
    public void removePrivateKey(HttpServletRequest request) {
        Assert.notNull(request);
        HttpSession session = request.getSession();
        session.removeAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
    }

    /**
	 * 解密参数
	 * 
	 * @param name
	 *            参数名称
	 * @param request
	 *            httpServletRequest
	 * @return 解密内容
	 */
    @Transactional(readOnly = true)
    public String decryptParameter(String name, HttpServletRequest request) {
        Assert.notNull(request);
        if (name != null) {
            HttpSession session = request.getSession();
            RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute(PRIVATE_KEY_ATTRIBUTE_NAME);
            String parameter = request.getParameter(name);
            if (privateKey != null && StringUtils.isNotEmpty(parameter)) {
                return RSAUtils.decrypt(privateKey, parameter);
            }
        }
        return null;
    }

}