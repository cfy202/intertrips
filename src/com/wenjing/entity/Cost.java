package com.wenjing.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Cost implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2102829865906477429L;

	private String id;

    private String name;

    private String remark;

    private Set<Rolecost> rolecostsCostnumber = new HashSet<Rolecost>(0);
    
    private String code;	//货币编码
    
    private String sign;	//货币符号
    
    private  BigDecimal exchangerate;//对美元的汇率
    
    private Currency currency;

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public BigDecimal getExchangerate() {
		return exchangerate;
	}

	public void setExchangerate(BigDecimal exchangerate) {
		this.exchangerate = exchangerate;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setRolecostsCostnumber(Set<Rolecost> rolecostsCostnumber) {
        this.rolecostsCostnumber=rolecostsCostnumber;
    }

    public Set<Rolecost> getRolecostsCostnumber() {
        return rolecostsCostnumber;
    }

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}