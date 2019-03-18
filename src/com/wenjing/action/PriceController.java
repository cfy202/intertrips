
package com.wenjing.action;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wenjing.dao.ProductMapper;
import com.wenjing.entity.Price;
import com.wenjing.entity.Product;
import com.wenjing.service.PriceService;

/**
 * 类说明		其他产品价格管理controller
 * @author sevens
 * @date 2015-4-23 
 */
@Controller
@RequestMapping("/admin/price")
public class PriceController {
	@Resource
	private PriceService priceService;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private HttpServletRequest request;
	
	
	
	/**
	 * 查询所有产品标签列表
	 * @return
	 * sevens
	 */
	@RequestMapping("/list")
	public String findAll(Model model,@Param("productid")String productid,HttpServletResponse response){
		Product product = productMapper.selectByPrimaryKey(productid);
		List<Price> pricelist = priceService.findAll(productid, request, response, model);
		String upUrl = request.getHeader("Referer");
		model.addAttribute("pricelist", pricelist);
		model.addAttribute("product", product);
		model.addAttribute("upUrl",upUrl);
		return "/admin/manage/price/price.ftl";
	}
	
	/**
	 * 根据id删除price
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") String id, final RedirectAttributes rAttributes) {
		  priceService.delete(id);
		return "redirect:/admin/price/list.do";
	}

	/**
	 * 保存价格
	 * @param tag
	 * @param id
	 * @return
	 * sevens
	 */
	@RequestMapping("/save")
	public String save(Price price,@RequestParam("id") String id) {
		price.setId(id);
		
		Price price2 = priceService.findById(id);
		if (price2 != null && !price2.equals("")) {
			priceService.update(price);
		} else {
			priceService.save(price);
		}
		return "redirect:/admin/price/list.do?productid="+price.getProductid();
		
	}

	/**
	 * 添加，修改产品价格
	 * @param price
	 * @param model
	 * @return
	 * sevens
	 */
	@RequestMapping("/update")
	public String update(Price price, Model model,@RequestParam("id") String id,@Param("type")String type) {
		price = priceService.findById(id);
		model.addAttribute("price", price);
		model.addAttribute("productType",type);
		return "/admin/manage/price/update.ftl";
	}
	
	/**
	 * 添加产品价格
	 * @param price
	 * @param model
	 * 
	 * @return
	 * sevens
	 */
	@RequestMapping("/add")
	public String add(Price price, Model model,@Param("productid")String productid,@Param("type")String type){
		String id = UUID.randomUUID().toString();//产生uuid
		if (null!=id && id.contains("-")){  
			id = id.replaceAll("-", "");  
        }
		price.setId(id);
		model.addAttribute("price", price);
		model.addAttribute("productid",productid);
		model.addAttribute("productType",type);
		return "/admin/manage/price/add.ftl";
	}
	

	/**
	 * @Title copyPrice
	 * @Description 价格复制
	 * @Author Bowden
	 * @CreateDate 2015-9-14 上午11:25:25
	 */
	@RequestMapping("/copyPrice")
	public String copyPrice(@RequestParam("productid") String productid, 
			                @RequestParam("costNum") String costNum,
			                final RedirectAttributes rAttributes){
		boolean flag = priceService.copyPrice(productid, costNum);
		if(flag){
			rAttributes.addFlashAttribute("message", "复制成功，请检查核对！");
		}else {
			rAttributes.addFlashAttribute("message", "复制失败，请选择重试！");
		}
		return "redirect:/admin/price/list.do?productid="+productid;
	}
}
