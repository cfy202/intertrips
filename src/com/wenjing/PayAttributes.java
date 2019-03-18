package com.wenjing;

import java.util.ArrayList;
import java.util.List;

public final class PayAttributes {

	/**
	 * paypal参数(正式环境)
	 */
	public static final String PAYPALURL = "https://www.paypal.com/cgi-bin/webscr"; 
	private static final String BUSINESS_ACCOUNT_US = "payment.chinatour.com@gmail.com"; 
	private static final String BUSINESS_ACCOUNT_CA = "van@chinatour.com";	
	private static final String BUSINESS_ACCOUNT_EUR = "espana@chinatour.com";
	private static final String BUSINESS_ACCOUNT_CH = "payment.chinatour.com@gmail.com";
	private static final String BUSINESS_ACCOUNT_AU = "joe.l@intertrips.com";
	private static final String BUSINESS_ACCOUNT_US_REAL = "ac.chinatour@gmail.com";	
	
	/**
	 * paypal参数 (测试环境)
	 */
//	public static final String PAYPALURL = "https://www.sandbox.paypal.com/cgi-bin/webscr"; 
//	public static final String BUSINESS_ACCOUNT_US = "JaredSell@paypal.com";
//	public static final String BUSINESS_ACCOUNT_CA = "JaredSell@paypal.com";
//	public static final String BUSINESS_ACCOUNT_EUR = "JaredSell@paypal.com";
//	public static final String BUSINESS_ACCOUNT_CH = "JaredSell@paypal.com";
//	public static final String BUSINESS_ACCOUNT_US_REAL = "JaredSell@paypal.com";	
	
	/**
	 * authorize参数(正式环境)
	 */
	public final static String SIMGATEWAY = "https://secure.authorize.net/gateway/transact.dll";
	public final static String LOGINID = "2rj2JPMa5K";
	public final static String TRANSACTIONKEY = "2z3fpb43D25DF2Tz";
	
	/**
	 * authorize参数(测试环境)
	 */
//	public final static String SIMGATEWAY = "https://test.authorize.net/gateway/transact.dll";
//	public final static String LOGINID = "2n6w3cTUh";
//	public final static String TRANSACTIONKEY = "237G4pBT25a2TyFe";
	private PayAttributes(){
	}
	
	/**
	 * 根据销售中心获取收款账户（用于发送支付请求）
	 * 
	 * @param costCode
	 * @return
	 */
	public static String getPayAccount(String costCode){
		if("USD".equals(costCode)){
			return BUSINESS_ACCOUNT_US;
		}else if("AUD".equals(costCode)){
			return BUSINESS_ACCOUNT_AU;
		}
		return "";
	} 
	
	/**
	 * 根据销售中心获取收款账户（用于校验收款账户）
	 * 
	 * @param costCode
	 * @return
	 */
	public static boolean checkReceiveAccount(String costCode,String recieveAccout){
		List<String> payAccounts = new ArrayList<String>();
		if("USD".equals(costCode)){
			payAccounts.add(BUSINESS_ACCOUNT_US);
			payAccounts.add(BUSINESS_ACCOUNT_US_REAL);
		}else if("AUD".equals(costCode)){
			payAccounts.add(BUSINESS_ACCOUNT_AU);
		}
		for(String payAccount : payAccounts){
			if(payAccount.equals(payAccount)){
				return true;
			}
		}
		return false;
	}
}
