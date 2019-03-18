package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2015-9-6 上午10:54:57 
 * 类说明 ：常见问题--问题
 */
public class QnaQuestion implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 **/
	private static final long serialVersionUID = 5548200247196103960L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 提问者姓名
	 */
	private String name;
	/**
	 * 提问者邮箱
	 */
	private String email;
	/**
	 * 提问标题
	 */
	private String title;
	/**
	 * 提问内容
	 */
	private String content;
	/**
	 * 提问时间
	 */
	private Date createDate;
	/**
	 * 提问者ip
	 */
	private String ip;
	/**
	 * 问题对应的线路id
	 */
	private String productId;
	/**
	 * 对应会员id
	 */
	private String memberId;
	/**
	 * 是否显示
	 */
	private int isshow;
	
	/**
	 * 销售中心id
	 */
	private String costNumber;
	
	/**
	 * 状态 0:未处理过  1：已处理
	 */
	private int status;
	
	/**
	 * 对应的线路
	 */
	private Tourline tourline; 
	
	/**
	 * 对应的page
	 */
	private Page page;
	
	/**
	 * 会员信息
	 */
	private Member member;
	
	/**
	 * 销售中心
	 */
	private Cost cost;
	
	/**
	 * 对此提问的回答
	 */
	private List<QnaAnswer> qnaAnswerList;
	
	private Product product;
	
	/**
	 * 回答总条数
	 */
	private int answerCount;
	
	/**
	 * 回答未处理条数
	 */
	private int answerUntreated;
	
	public QnaQuestion() {
		super();
	}
	
	public QnaQuestion(String id, int isshow, int status) {
		super();
		this.id = id;
		this.isshow = isshow;
		this.status = status;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getIsshow() {
		return isshow;
	}

	public void setIsshow(int isshow) {
		this.isshow = isshow;
	}

	public String getCostNumber() {
		return costNumber;
	}

	public void setCostNumber(String costNumber) {
		this.costNumber = costNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Tourline getTourline() {
		return tourline;
	}

	public void setTourline(Tourline tourline) {
		this.tourline = tourline;
	}

	public List<QnaAnswer> getQnaAnswerList() {
		return qnaAnswerList;
	}

	public void setQnaAnswerList(List<QnaAnswer> qnaAnswerList) {
		this.qnaAnswerList = qnaAnswerList;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public int getAnswerUntreated() {
		return answerUntreated;
	}

	public void setAnswerUntreated(int answerUntreated) {
		this.answerUntreated = answerUntreated;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
