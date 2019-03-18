package com.wenjing.entity;

import java.io.Serializable;

import com.wenjing.dao.MemberinformationMapper;

public class Member implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3538327769606232187L;
	
	/** "用户名"Cookie名称 */
	public static final String USERNAME_COOKIE_NAME = "wjuser";
	
	/**  */
	public static final String MEMBER_COOKIE_NAME = "loginMember";
	
	/** Cookie路径 */
	public static final String COOKIE_PATH = "/";
	
	/** Cookie作用域 */
	public static final String COOKIE_DOMAIN = "";
	
	/** Cookie超时时间 */
	public static final Integer COOKIE_MAXAGE = 20*60;

	/**
     * `member`.id (用户ID)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String id;

    /**
     * `member`.account (用户账户)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String account;

    /**
     * `member`.password (密码)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String password;

    /**
     * `member`.createTime (注册时间)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String createtime;

    /**
     * `member`.lasttime (最后登录时间)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String lasttime;

    /**
     * `member`.userStatus (用户状态)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private Byte userstatus;
    
    /**
     * `member`.ip (注册ip地址)
     * @ibatorgenerated 2015-04-22 23:01:27
     */
    private String ip;
    
    /**
     * 用户的信息
     */
    private Memberinformation memberinformation;

    /**
    private Set<Memberinformation> memberinformationsUserid = new HashSet<Memberinformation>(0);

    private Set<Ordercontacter> ordercontactersUserid = new HashSet<Ordercontacter>(0);

    private Set<Orders> orderssUserid = new HashSet<Orders>(0);
    */
    
    public String getId() {
        return id;
    }

	public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    public Byte getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Byte userstatus) {
        this.userstatus = userstatus;
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Memberinformation getMemberinformation() {
		return memberinformation;
	}

	public void setMemberinformation(Memberinformation memberinformation) {
		this.memberinformation = memberinformation;
	}
}