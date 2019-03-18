package com.wenjing.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 作者 E-mail: Bowden
 * @version 创建时间：2015-9-6 上午10:40:38
 *  类说明 :常见问题--回答
 */
public class QnaAnswer implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 **/
	private static final long serialVersionUID = -3816776750540355911L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 回答者姓名
	 */
	private String name;
	/**
	 * 回答者邮箱
	 */
	private String email;
	/**
	 * 回答内容
	 */
	private String content;
	/**
	 * 回答时间
	 */
	private Date createDate;
	/**
	 * 回答者ip地址
	 */
	private String ip;
	/**
	 * 回答问题id
	 */
	private String questionId;
	/**
	 * 会员id
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
	 * 处理状态0：未处理  1：已处理
	 */
	private int status;
	
	/**
	 * 会员
	 */
	private Member member;

	public QnaAnswer() {
		super();
	}

	public QnaAnswer(String id, int isshow, int status) {
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

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
