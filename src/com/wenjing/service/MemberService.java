package com.wenjing.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.wenjing.Mail;
import com.wenjing.action.front.LoginController;
import com.wenjing.action.front.RegisterController;
import com.wenjing.dao.ActivateMapper;
import com.wenjing.dao.GetScoreMapper;
import com.wenjing.dao.MemberMapper;
import com.wenjing.dao.MemberinformationMapper;
import com.wenjing.entity.Activate;
import com.wenjing.entity.GetScore;
import com.wenjing.entity.Member;
import com.wenjing.entity.Memberinformation;
import com.wenjing.util.FreemarkerUtils;
import com.wenjing.util.IPUtils;
import com.wenjing.util.Tools;
import com.wenjing.util.WebUtils;
import com.wenjing.vo.BookTourVO;

/**
 * @author 作者 E-mail:bowden
 * @version 创建时间：2015-4-27 下午3:45:08
 *  类说明 : 会员管理 Service
 */
@Service
public class MemberService {

	@Resource
	private MemberMapper memberMapper;
	@Resource
	private MemberinformationMapper memberinformationMapper;
	@Resource
	private ActivateMapper activateMapper;
	@Resource
	private GetScoreMapper getScoreMapper;
	@Resource
	private HttpServletRequest request;
	
	private static final String NAME = "完善真实姓名、性别";
	
	private static final String BIRTHDAY = "完善生日信息";
	
	private static final String NATIONALITY = "完善国籍信息";
	
	private static final String PASSPROT = "完善护照信息";
	
	private static final String USERADDR = "完善地址信息";
	
	private static final String USERTEL = "完善联系方式";
	
	private static final String IMEGR = "完善头像";
	
	private static final Integer SCORE = 10;
	

	/**
	 * 判断用户名是否存在
	 * 
	 * @param account
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isExistUserName(String account) {
		boolean flag = false;
		Member member = memberMapper.findByAccount(account);
		if (member != null) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 新增用户
	 * 
	 * @param member
	 */
	@Transactional
	public int insert(Member member) {
		int success = memberMapper.insertSelective(member);
		return success;
	}

	/**
	 * 根据account查询用户
	 * 
	 * @param account
	 * @return
	 */
	@Transactional(readOnly = true)
	public Member findByAccount(String account) {
		return memberMapper.findByAccount(account);
	}

	/**
	 * 查询所有会员列表
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Member> findAll() {
		return memberMapper.findAll();
	}

	/**
	 * 修改用户状态
	 * @param userstatus
	 */
	@Transactional
	public int updateStatus(String id , int userstatus) {
		return memberMapper.updateStatus(id, userstatus);
	}

	/**
	 * 根据memberid 查询用户详细信息
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public Memberinformation findInfoByMemberId(String memberid) {
		Memberinformation info = memberinformationMapper.findInfoByMemberId(memberid);
		Member member = info.getMember();
		String ip = "";
		if(member != null){
		   ip = member.getIp();
		}
		if(ip != null && !"".equals(ip)){
			String path = request.getSession().getServletContext().getRealPath("/");
			IPUtils.load(path + "/ip/17monipdb.dat");
			String[] location = IPUtils.find(ip);
			member.setIp(Tools.stringArrToString(location));
			info.setMember(member);
		}
		/**
		String question = info.getQuestion();
		if(question!=null && !"".equals(question)){
			int q = Integer.valueOf(question);
			switch (q) {
			case 1:
				info.setQuestion("您母亲的姓名是？");
				break;
	        case 2:
	        	info.setQuestion("您配偶的生日是？");
				break;
			case 3:
				info.setQuestion("您的学号（或工号）是？");
				break;
			case 4:
				info.setQuestion("您母亲的生日是？");
				break;
			case 5:
				info.setQuestion("您高中班主任的名字是？");
				break;
			case 6:
				info.setQuestion("您父亲的姓名是？");
				break;
			case 7:
				info.setQuestion("您小学班主任的名字是？");
				break;
			case 8:
				info.setQuestion("您父亲的生日是？");
				break;
			case 9:
				info.setQuestion("您配偶的姓名是？");
				break;
			case 10:
				info.setQuestion("您初中班主任的名字是？");
				break;
			case 11:
				info.setQuestion("您最熟悉的童年好友名字是？");
				break;
			case 12:
				info.setQuestion("您最熟悉的学校宿舍室友名字是？");
				break;
			case 13:
				info.setQuestion("对您影响最大的人名字是？");
				break;
			default:
				info.setQuestion("");
				break;
			}
		}
		*/
		return info;
	}

	
	public Member getCurrentMember(HttpServletRequest request,HttpServletResponse response,BookTourVO bookTourVO){
		Member result = null;
		Member currentMember = memberMapper.findByAccount(bookTourVO.getEmail());
		//新用户
		if(currentMember == null){
			Member member = new Member();
			member.setId(Tools.getUUID());
			member.setCreatetime(Tools.getHHtime()); // 注册时间
			member.setLasttime(Tools.getHHtime()); // 最后登录时间
			member.setUserstatus((byte) 1); // 用户状态
			member.setAccount(bookTourVO.getEmail());
			member.setPassword(DigestUtils.md5Hex("12345678"));
			member.setIp(Tools.getRemortIP(request));
			int success = memberMapper.insert(member); // 添加会员
			if (success > 0) {
				Memberinformation memberinformation = new Memberinformation();
				memberinformation.setId(Tools.getUUID());
				memberinformation.setEmail(bookTourVO.getEmail());
				memberinformation.setFirstName(bookTourVO.getFirstName());
				memberinformation.setLastName(bookTourVO.getLastName());
				memberinformation.setUseraddr(bookTourVO.getAddress());
				memberinformation.setUsermobile(bookTourVO.getPhone());
				memberinformation.setMemberid(member.getId());
				memberinformation.setUserlevel(1); // 用户等级1
				memberinformation.setScore(100); // 积分
				memberinformationMapper.insert(memberinformation);
			}   
			result = member;
			Mail gmail;
			String emailMsgTxt;
			if("AUD".equals(bookTourVO.getCurrencyCode())){
				gmail = new Mail("AUD");
				emailMsgTxt = FreemarkerUtils.getActivateMail(request.getSession().getServletContext(),bookTourVO.getEmail(),"", RegisterController.AU_TEMPLATE_URL); // 获取激活邮件内容
			}else{
				gmail = new Mail();
				emailMsgTxt = FreemarkerUtils.getActivateMail(request.getSession().getServletContext(),bookTourVO.getEmail(),"", RegisterController.TEMPLATE_URL); 
			}
			gmail.setSendTo(new String[]{bookTourVO.getEmail()});
			gmail.setEmailMsgTxt(emailMsgTxt);
			gmail.setEmailSubjectTxt("Welcome to Intertrips");
			try {
				gmail.sendSSLMessage(); // 发送邮件
			} catch (MessagingException e) {
				try {
					gmail.sendSSLMessage(); // 发送邮件
				} catch (MessagingException e1) {
					try {
						gmail.sendSSLMessage(); // 发送邮件
					} catch (MessagingException e2) {
						e2.printStackTrace();
					}
				}
			}
			
			
		//老用户	
		}else{
			String lasttime = Tools.getTime(Tools.getDtimestemp(currentMember.getLasttime()));  
			String current_time = Tools.getTime();
			if(!lasttime.equals(current_time)){  
	            // 不同一天  , 积分增加提示信息  
				memberMapper.updateLasttime(Tools.getHHtime(), currentMember.getId());
	        	memberinformationMapper.updateScoreByMemberId(LoginController.SCORE, currentMember.getId());
	        }
			result = currentMember;
		}	
		request.getSession().setMaxInactiveInterval(45 * 60);
		request.getSession().setAttribute("islogin", true);
		request.getSession().setAttribute("member", result);
		WebUtils.addCookie(request, response, Member.MEMBER_COOKIE_NAME, JSON.toJSONString(result),40 * 60);
		WebUtils.addCookie(request, response, "JSESSIONID", request.getSession().getId(),40 * 60);
		return result;
	}
	
	/**
	 * 删除会员
	 * @param id
	 */
	@Transactional
	public void delete(String memberid) {
		int deletememberInfo = memberinformationMapper.deleteByMemberid(memberid);
		int deleteGetScore = getScoreMapper.deleteByMemberId(memberid);
		if(deletememberInfo>0 && deleteGetScore>0){
			memberMapper.deleteByPrimaryKey(memberid);
		}
	}

	/**
	 * 保存激活码信息
	 * @param activate
	 * @return
	 */
	@Transactional
	public int insertActivate(Activate activate) {
		return activateMapper.insertSelective(activate);
	}

	/**
	 * 根据memberid查询激活数据
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Activate findByAuthcode(String authcode) {
		return activateMapper.findByAuthcode(authcode);
	}

	/**
	 * 根据id查询Member
	 * @param memberid
	 * @return
	 */
	@Transactional(readOnly=true)
	public Member findById(String memberid) {
		return memberMapper.selectByPrimaryKey(memberid);
	}

	/**
	 * 删除失效激活码
	 */
	@Transactional
	public int deleteFailureAuthcode(int nowTime) {
		return activateMapper.deleteFailureAuthcode(nowTime);
	}

	/**
	 * 激活成功删除此激活码
	 */
	@Transactional
	public void deleteActivate(String id) {
     activateMapper.deleteByPrimaryKey(id);		
	}

	/**
	 * 激活成功删除此激活码
	 */
	@Transactional
	public void deleteActivateByMmeberId(String memberid) {
     activateMapper.deleteActivateByMemberId(memberid);		
	}
	
	/**
	 * 修改密码
	 * @param member
	 */
	@Transactional
	public void updatePassword(Member member) {
    memberMapper.updateByPrimaryKeySelective(member);		
	}

	/**
	 * 新增会员信息
	 * @param memberinformation
	 */
	@Transactional
	public void insertInfo(Memberinformation memberinformation) {
		memberinformationMapper.insertSelective(memberinformation);
	}

	/**
	 * 更新修改个人信息
	 * @param memberinformation
	 */
	@Transactional
	public int updateInfo(Memberinformation info) {
		String memberid = info.getMemberid();
		List<GetScore> getScoreList = getScoreMapper.findBymemberid(memberid, 0);
		for(GetScore  getScores :getScoreList){
			if(getScores !=null){
				int a = 0;
				String namepy = getScores.getNamepy().trim();
				if(namepy !=null && !"".equals(namepy)){
					List<GetScore> gScoreList = new ArrayList<GetScore>();
					List<String> namepylist = new ArrayList<String>();  
					String[] namepyArr = namepy.split(",");
					for (int j = 0; j < namepyArr.length; j++) {
						namepylist.add(namepyArr[j]);
						if("name".equals(namepyArr[j])){
							if(info.getLastName()!=null && info.getFirstName()!=null && info.getUsersex()!=null && !"0".equals(info.getUsersex())){
								a += SCORE;
								GetScore getScore = new GetScore(Tools.getUUID(), Tools.getDtimestemp(Tools.getDtime()), NAME, "name", null, SCORE, 1, memberid);
								gScoreList.add(getScore);
								namepylist.remove("name");
							}
						}
						
						if("birthday".equals(namepyArr[j])){
							if(info.getBirthday()!=null){
								a += SCORE;
								GetScore getScore = new GetScore(Tools.getUUID(), Tools.getDtimestemp(Tools.getDtime()), BIRTHDAY, "birthday", null, SCORE, 1, memberid);
								gScoreList.add(getScore);
								namepylist.remove("birthday");
							}
						}
						
						if("nationality".equals(namepyArr[j])){
							if(info.getNationality()!=null && !"0".equals(info.getNationality())){
								a += SCORE;
								GetScore getScore = new GetScore(Tools.getUUID(), Tools.getDtimestemp(Tools.getDtime()), NATIONALITY, "nationality", null, SCORE, 1, memberid);
								gScoreList.add(getScore);
								namepylist.remove("nationality");
							}
						}
						
						if("passport".equals(namepyArr[j])){
							if(info.getPassportNo()!=null && info.getPassportValid()!=null){
								a += SCORE;
								GetScore getScore = new GetScore(Tools.getUUID(), Tools.getDtimestemp(Tools.getDtime()), PASSPROT, "passport", null, SCORE, 1, memberid);
								gScoreList.add(getScore);
								namepylist.remove("passport");
							}
						}
						
						if("userAddr".equals(namepyArr[j])){
							if(info.getCountry()!=null && !"0".equals(info.getCountry()) && info.getUseraddr()!=null && info.getPostalcode()!=null){
								a += SCORE;
								GetScore getScore = new GetScore(Tools.getUUID(), Tools.getDtimestemp(Tools.getDtime()), USERADDR, "userAddr", null, SCORE, 1, memberid);
								gScoreList.add(getScore);
								namepylist.remove("userAddr");
							}			
						}
						
						if("userTel".equals(namepyArr[j])){
							if(info.getUsertel()!=null || info.getUsermobile()!=null){
								a += SCORE;
								GetScore getScore = new GetScore(Tools.getUUID(), Tools.getDtimestemp(Tools.getDtime()), USERTEL, "userTel", null, SCORE, 1, memberid);
								gScoreList.add(getScore);
								namepylist.remove("userTel");
							}
						}
						
						if("image".equals(namepyArr[j])){
							if(info.getImageurl()!=null){
								a += SCORE;
								GetScore getScore = new GetScore(Tools.getUUID(), Tools.getDtimestemp(Tools.getDtime()), IMEGR, "image", null, SCORE, 1, memberid);
								gScoreList.add(getScore);
								namepylist.remove("image");
							}
						}
					}
					if(a != 0){
						memberinformationMapper.updateScoreByMemberId(a, memberid);
						if(namepylist.size() == 0){
							//getScoreMapper.updateGetScoreByMemeberid("", 2, getScores.getId(), Tools.getDtimestemp(Tools.getDtime()));
							getScoreMapper.deleteByPrimaryKey(getScores.getId()); //删除
						}else {
							String newNamepy =  Tools.ListToString(namepylist); //返回一个包含所有对象的指定类型的数组   
							getScoreMapper.updateGetScoreByMemeberid(newNamepy, 0, getScores.getId(), Tools.getDtimestemp(Tools.getDtime()));
						}
						getScoreMapper.batchAddMemberInfo(gScoreList);//批量插入
					}
				}
			}
		}
		return memberinformationMapper.updateProfileInfo(info);
	}

	/**
	 * 根据info id查询
	 * @param id
	 * @return
	 */
	@Transactional
	public Memberinformation findByInfoid(String id) {
		return memberinformationMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据memberid修改密码
	 * @param md5Hex
	 * @param memberid
	 */
	@Transactional
	public int updatePassword(String password, String memberid) {
		return memberMapper.updatePasswordById(password,memberid);
	}

	/**
	 * 修改最后登录时间
	 * @param 
	 */
	@Transactional
	public void updateLasttime(String lasttime, String memberid) {
		memberMapper.updateLasttime(lasttime, memberid);
	}

	/**
	 * 修改积分
	 * @param score
	 * @param memberid
	 */
	@Transactional
	public int updateScoreByMemberId(int score, String memberid) {
		return memberinformationMapper.updateScoreByMemberId(score, memberid);
	}

	/**
	 * 后台修改会员资料
	 * @param memberinformation
	 */
	@Transactional
	public void adminUpdateInfo(Memberinformation memberinformation) {
		memberinformationMapper.updateByPrimaryKey(memberinformation);
	}

	/**
	 * @Title findGetScoreBymemberid
	 * @Description 根据用户id查询未完善资料
	 * @Author Bowden
	 * @CreateDate 2015-7-24 上午11:38:59
	 */
	@Transactional
	public List<GetScore> findGetScoreBymemberid(String memberid, int status) {
		return getScoreMapper.findBymemberid(memberid, status);
	}
	
	/**
	 * 
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=true)
	public Member findByCommentsId(String id){
		return memberMapper.findByCommentsId(id);
	}
}
