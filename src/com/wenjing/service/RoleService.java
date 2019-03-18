
package com.wenjing.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wenjing.dao.RoleMapper;
import com.wenjing.dao.RolecostMapper;
import com.wenjing.dao.RoletreeMapper;
import com.wenjing.entity.Role;
import com.wenjing.entity.Rolecost;
import com.wenjing.entity.Roletree;
import com.wenjing.util.Tools;

/**
 * 说明 后台角色管理
 * @author sevens
 *
 */
@Service
public class RoleService {

	
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private RoletreeMapper roletreeMapper;
	@Resource
	private RolecostMapper rolecostMapper;
	
	
	/**
	 * 查询所有role 
	 * 
	 * @return
	 * sevens
	 */
	@Transactional(readOnly=true)
	public List<Role> findAll(){
		return roleMapper.findAll();
	}
	
	/**
	 * 根据id删除role
	 * @param id
	 * @return
	 */
	@Transactional
	public int delete(String id) {
		roletreeMapper.deleteByroleId(id);    
		return roleMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据id查询role
	 * @param id
	 * @return
	 */
	@Transactional
	public Role findById(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存ROLE
	 * @param ROLE
	 * @return
	 */
	@Transactional
	public int save(Role role) {
		return roleMapper.insert(role);
	}
	
	
	/**
	 * 更新角色关联关系
	 * @author Sevens
	 * 时间 2015-5-18
	 */
	
	@Transactional
	public int updateregest(Role role,String[] treeids,String[] costids,int action,HttpServletRequest request){
		List<Roletree> roletrees  = new ArrayList<Roletree>();
		List<Rolecost> rolecosts = new ArrayList<Rolecost>();
		if(treeids!=null && treeids.length>0){
			for (String string : treeids) {
				StringBuffer buf  = new StringBuffer(); 
				Roletree roletree = new Roletree();
				//roletree.setId(UUID.randomUUID().toString());
				roletree.setId(Tools.getUUID());
				roletree.setRoleid(role.getId());
				roletree.setTreeid(string);
				String [] opations = request.getParameterValues(string);
				if(opations!=null&& opations.length>0){
					for (int i = 0; i < opations.length; i++) {
						buf.append(opations[i] + ",");
					}
				}
				if(buf.length()>0){
					String opurls = buf.substring(0, buf.length() - 1);
					roletree.setOpationIds(opurls);
				}
				roletrees.add(roletree);
				
			}
		}
		if(costids!=null && costids.length>0){
			for (String string : costids) {
				Rolecost rolecost = new Rolecost();
				//rolecost.setId(UUID.randomUUID().toString());
				rolecost.setId(Tools.getUUID());
				rolecost.setRoleid(role.getId());
				rolecost.setCostnumber(string);
				rolecosts.add(rolecost);
			}
		}
		if(roletrees!=null&&rolecosts!=null&& action==1){
			roletreeMapper.batchAddRoleTree(roletrees);
			rolecostMapper.batchAddRoleCost(rolecosts);
			
		}else{
			roletreeMapper.deleteByroleId(role.getId());
			rolecostMapper.deleteByroleId(role.getId());
			roletreeMapper.batchAddRoleTree(roletrees);
			rolecostMapper.batchAddRoleCost(rolecosts);
		}
		return 0;
	}
	/**
	 * 修改role
	 * @param role
	 * @return
	 * xiejin
	 */
	@Transactional
	public int update(Role role) {
		return roleMapper.updateByPrimaryKeySelective(role);
	}
	
	/**
	 * 查询最大sort
	 * @return
	 * xiejin
	 */
	@Transactional
	public int getOrderId(){
		return roleMapper.getMaxSort();
	}
}
