/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.wenjing.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.wenjing.CommonAttributes;
import com.wenjing.EnumConverter;
import com.wenjing.Pages;
import com.wenjing.entity.ContactUs;
import com.wenjing.entity.Cost;
import com.wenjing.entity.Couponsduijiang;
import com.wenjing.entity.Product;
import com.wenjing.entity.QnaQuestion;
import com.wenjing.entity.Review;
import com.wenjing.entity.Tourdate;
import com.wenjing.entity.Tourline;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.DeepUnwrap;

/**
 * Utils - Freemarker
 *
 * @author SHOP++ Team
 * @version 3.0
 */
@SuppressWarnings("unchecked")
public final class FreemarkerUtils {

    /**
     * ConvertUtilsBean
     */
    private static final ConvertUtilsBean convertUtils;
    
//    @Resource
//    HttpServletRequest request;
    

    static {
        convertUtils = new ConvertUtilsBean() {
            @Override
            public String convert(Object value) {
                if (value != null) {
                    Class<?> type = value.getClass();
                    if (type.isEnum() && super.lookup(type) == null) {
                        super.register(new EnumConverter(type), type);
                    } else if (type.isArray() && type.getComponentType().isEnum()) {
                        if (super.lookup(type) == null) {
                            ArrayConverter arrayConverter = new ArrayConverter(type, new EnumConverter(type.getComponentType()), 0);
                            arrayConverter.setOnlyFirstToString(false);
                            super.register(arrayConverter, type);
                        }
                        Converter converter = super.lookup(type);
                        return ((String) converter.convert(String.class, value));
                    }
                }
                return super.convert(value);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(String value, Class clazz) {
                if (clazz.isEnum() && super.lookup(clazz) == null) {
                    super.register(new EnumConverter(clazz), clazz);
                }
                return super.convert(value, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(String[] values, Class clazz) {
                if (clazz.isArray() && clazz.getComponentType().isEnum() && super.lookup(clazz.getComponentType()) == null) {
                    super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
                }
                return super.convert(values, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(Object value, Class targetType) {
                if (super.lookup(targetType) == null) {
                    if (targetType.isEnum()) {
                        super.register(new EnumConverter(targetType), targetType);
                    } else if (targetType.isArray() && targetType.getComponentType().isEnum()) {
                        ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
                        arrayConverter.setOnlyFirstToString(false);
                        super.register(arrayConverter, targetType);
                    }
                }
                return super.convert(value, targetType);
            }
        };

        DateConverter dateConverter = new DateConverter();
        dateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        convertUtils.register(dateConverter, Date.class);
    }

    /**
     * 不可实例化
     */
    private FreemarkerUtils() {
    }

    /**
     * 解析字符串模板
     * @param template 字符串模板
     * @param model    数据
     * @return 解析后内容
     */
    public static String process(String template, Map<String, ?> model) throws IOException, TemplateException {
        Configuration configuration = null;
        ApplicationContext applicationContext = SpringUtils.getApplicationContext();
        if (applicationContext != null) {
            FreeMarkerConfigurer freeMarkerConfigurer = SpringUtils.getBean("freeMarkerConfigurer", FreeMarkerConfigurer.class);
            if (freeMarkerConfigurer != null) {
                configuration = freeMarkerConfigurer.getConfiguration();
            }
        }
        return process(template, model, configuration);
    }

    /**
     * 解析字符串模板
     *
     * @param template      字符串模板
     * @param model         数据
     * @param configuration 配置
     * @return 解析后内容
     */
    public static String process(String template, Map<String, ?> model, Configuration configuration) throws IOException, TemplateException {
        if (template == null) {
            return null;
        }
        if (configuration == null) {
            configuration = new Configuration();
        }
        StringWriter out = new StringWriter();
        new Template("template", new StringReader(template), configuration).process(model, out);
        return out.toString();
    }

    /**
     * 获取参数
     *
     * @param name   名称
     * @param type   类型
     * @param params 参数
     * @return 参数, 若不存在则返回null
     */
    public static <T> T getParameter(String name, Class<T> type, Map<String, TemplateModel> params) throws TemplateModelException {
        Assert.hasText(name);
        Assert.notNull(type);
        Assert.notNull(params);
        TemplateModel templateModel = params.get(name);
        if (templateModel == null) {
            return null;
        }
        Object value = DeepUnwrap.unwrap(templateModel);
        return (T) convertUtils.convert(value, type);
    }

    /**
     * 获取变量
     *
     * @param name 名称
     * @param env  Environment
     * @return 变量
     */
    public static TemplateModel getVariable(String name, Environment env) throws TemplateModelException {
        Assert.hasText(name);
        Assert.notNull(env);
        return env.getVariable(name);
    }

    /**
     * 设置变量
     *
     * @param name  名称
     * @param value 变量值
     * @param env   Environment
     */
    public static void setVariable(String name, Object value, Environment env) throws TemplateException {
        Assert.hasText(name);
        Assert.notNull(env);
        if (value instanceof TemplateModel) {
            env.setVariable(name, (TemplateModel) value);
        } else {
            env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
        }
    }

    /**
     * 设置变量
     *
     * @param variables 变量
     * @param env       Environment
     */
    public static void setVariables(Map<String, Object> variables, Environment env) throws TemplateException {
        Assert.notNull(variables);
        Assert.notNull(env);
        for (Entry<String, Object> entry : variables.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof TemplateModel) {
                env.setVariable(name, (TemplateModel) value);
            } else {
                env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
            }
        }
    }
    /**
     * 
     * @param context              
     * @param data                传送的数据
     * @param templatePath        ftl路径
     * @param targetHtmlPath      生成html文件的路径
     */
    public static void createHTML(ServletContext context,Map<String,Object> data,String templatePath,String targetHtmlPath) {
        Configuration cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/"); //ftl的基本路径
        cfg.setEncoding(Locale.getDefault(), "utf-8"); //设置编码
        Writer out = null;
        try {
            Template t = cfg.getTemplate(templatePath, "utf-8"); //获取模板文件
            t.setEncoding("utf-8");            
            //静态页面路径设置
            String htmlPath = context.getRealPath("/")+"/"+targetHtmlPath;
            File htmlFile = new File(htmlPath);
            htmlFile.getParentFile().mkdirs();
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile),"utf-8"));
           
            t.process(data, out);//将数据写到静态页面   
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(out != null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }            
        }
    }
    
    /**
     * 
     * @param context              
     * @param data                传送的数据
     * @param templatePath        ftl路径
     * @param targetHtmlPath      生成html文件的路径
     * @throws IOException 
     */
    public static void createTourlinePage(Object object,Map<String,Object> data,String targetHtmlPath) throws IOException {
    	String templatePath = File.separator + "front" + File.separator + "tourdetails.ftl";
    	if(File.separator.equals("\\")){
    		targetHtmlPath = targetHtmlPath.replace("/", "\\");
    	}
		String classPath  = object.getClass().getClassLoader().getResource("").getFile();
		try {
			classPath = URLDecoder.decode(classPath, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//获取模板路径
		File objFile = new File(classPath);
		String webInfoPath = objFile.getParent();
		String templateDirectory = webInfoPath + File.separator + "template";
		objFile = null;
		
		//获取contextPath
		String projectPath = new File(webInfoPath).getParent();
		try{
			String contextPath = projectPath.substring(projectPath.lastIndexOf(File.separator));
			if(File.separator.equals("\\")){
				//data.put("contextPath", contextPath.replace("\\", "/"));
				data.put("contextPath", "/intertrips");
			}else{
				data.put("contextPath", "");
			}
		}catch(Exception e){
			data.put("contextPath", "");
		}
		
		//获取静态页面路径
		targetHtmlPath = projectPath + targetHtmlPath;
    	
		if(!targetHtmlPath.endsWith(".htm")){
			return;
		}
		
		//创建目录
		String folderPath = targetHtmlPath.substring(0, targetHtmlPath.lastIndexOf(File.separator));
		File folder = new File(folderPath);
		if(!folder.exists()){
			folder.mkdirs();
		}
		folder = null;
		
		//删除源文件
		File targetHtml = new File(targetHtmlPath);
		if(targetHtml.isFile() && targetHtml.exists()){
			targetHtml.delete();
		}
		
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(templateDirectory));  
		cfg.setEncoding(Locale.getDefault(), "utf-8");
		
		Writer out = null;
		try{
			Template t = cfg.getTemplate(templatePath, "utf-8");
			t.setEncoding("utf-8");      
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetHtml),"utf-8"));
			t.process(data, out);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
	          if(out != null){
	          try {
	              out.flush();
	              out.close();
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	      }    
		}
    }    
    
    //通过模板构造邮件内容，移动版
    public static String getMailText(ServletContext context){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	 cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("mail/orders.ftl");  
            //FreeMarker通过Map传递动态数据  
            Map<?, ?> map=new HashMap<Object, Object>();
           
           
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    }  
    
   //通过模板构造分页
    public static String getPageContent(ServletContext context,Pages page){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/admin");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/pagination.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Pages> map=new HashMap<String, Pages>();
            map.put("page", page);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    }  
       
    /**
     * @Title: getDownPageContent
     * @Description: 通过模板构二级页面造下分页
     * @param context
     * @param page
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    public static String getDownPageContent(ServletContext context,Pages page){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/downPaging.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Pages> map=new HashMap<String, Pages>();
            map.put("page", page);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
    /**
     * @Title: getDownPageContent
     * @Description: 通过模板构造上分页
     * @param context
     * @param page
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    public static String getUpPageContent(ServletContext context,Pages page){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/upPaging.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Pages> map=new HashMap<String, Pages>();
            map.put("page", page);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
    /**
     * @Title: getDownPageContent
     * @Description: 通过模板构造二级页面产品展示
     * @param context
     * @param page
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    public static String getDatePriceContent(ServletContext context,List<Tourdate> tourdatelist,Cost cost){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/datePrice.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("tourdatelist", tourdatelist);
            map.put("cost", cost);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
    /**
     * @Title: getDownPageContent
     * @Description: 通过模板构造二级页面产品展示
     * @param context
     * @param page
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    public static String getDateContent(ServletContext context,List<Tourdate> tourdatelist,HttpServletRequest request){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/date.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("tourdatelist", tourdatelist);
            map.put("request", request);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
    //通过模板,构造会员注册激活邮件
    public static String getActivateMail(ServletContext context,String account, String activateurl, String templateurl){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate(templateurl,"UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, String> map=new HashMap<String, String>();
            map.put("account", account);
            map.put("activateurl", activateurl);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
    
    //通过模板,构造订单下成功提示邮件
    public static String getOrderNoticeMail(ServletContext context,String templateurl,Map<String,Object> paramMap){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate(templateurl,"UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,paramMap);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
    //通过模板,构造blog内容部分的模版
    public static String getBlogContent(ServletContext context,Map<String,Object> paramMap){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("blog/blogContent.ftl","UTF-8");  
            //FreeMarker通过Map传递动态数据  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,paramMap);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    }     
    
    //通过模板,构造blog评论的模版
    public static String getBlogComments(ServletContext context,Map<String,Object> paramMap){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("blog/blogComments.ftl","UTF-8");  
            //FreeMarker通过Map传递动态数据  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,paramMap);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    }   
    
    
    /**
     * @Title: getTourlineListContent
     * @Description: 通过模板构造二级页面产品展示
     * @param context
     * @param tourlinelist
     * @param request
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    public static String getTourlineListContent(ServletContext context,List<Tourline> tourlinelist,HttpServletRequest request){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/tourList.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("tourlinelist", tourlinelist);
            map.put("request", request);
            map.put("now", Tools.getTime());
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
    /**
     * @Title: getOrderEmailSuccess
     * @Description: 通过模板构造邮件订阅成功提示
     * @param context
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    public static String getOrderEmailSuccess(ServletContext context){
    	String contentString = "";
    	try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/admin");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("manage/email/orderEmailSuccess.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Object> map=new HashMap<String, Object>();
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            contentString=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return contentString;  
    }
    
    /**
     * @Title: getQA
     * @Description: 通过模板构造QA显示
     * @param context
     * @param page
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    public static String getQAContent(ServletContext context,List<QnaQuestion> qList, HttpServletRequest request){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/qna.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("qList", qList);
            map.put("request", request);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
//    /**
//     * @Title: getTagContent
//     * @Description: 通过模板构造线路详情页面标签内容
//     * @param context
//     * @param tourlinelist
//     * @param request
//     * @return    
//     * @return String    返回类型
//     * @author xiejin
//     */
//    public static String getTagContent(ServletContext context,List<Tag> tagList){  
//        String htmlText="";  
//        try {  
//        	Configuration cfg = new Configuration();
//        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
//            //通过指定模板名获取FreeMarker模板实例  
//            Template tpl= cfg.getTemplate("include/tagDetail.ftl","UTF-8");  
//            tpl.setEncoding("UTF-8");
//            //FreeMarker通过Map传递动态数据  
//            Map<String, Object> map=new HashMap<String, Object>();
//            map.put("tagList", tagList);
//            //解析模板并替换动态数据，最终替换标签对应的变量。  
//            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
//        } catch (Exception e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
//        return htmlText;  
//    } 
    
    /**
     * 通过模版生成线路详情页中线路名称下面的积分html
     * 
     * @param request
     * @param reviewNumber
     * @param starNumber
     * @return
     */
    public static String getReviewScoreUnderTourName(HttpServletRequest request,int reviewNumber,int starNumber){
    	String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(request.getSession().getServletContext(), "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/upReviewScore.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("reviewNumber", reviewNumber);
            map.put("starNumber", starNumber);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    }
    
    /**
     * 通过模版生成线路详情页中reviews上面的积分html
     * 
     * @param request
     * @param reviewNumber
     * @param starNumber
     * @return
     */
    public static String getReviewScoreUpReviews(HttpServletRequest request,int reviewNumber,int starNumber,BigDecimal avgScore){
    	String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(request.getSession().getServletContext(), "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/downReviewScore.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("reviewNumber", reviewNumber);
            map.put("starNumber", starNumber);
            map.put("avgScore", avgScore);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    }    
    
    /**
     * @Title: getQA
     * @Description: 通过模板构造review显示
     * @param context
     * @param page
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    public static String getReviewContent(ServletContext context,List<Review> reviewList, HttpServletRequest request){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/review.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("reviewList", reviewList);
            map.put("request", request);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
    /**
     * @Title: getDownPageContent
     * @Description: 通过模板构二级页面造下分页
     * @param context
     * @param page
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    public static String getReviewPage(ServletContext context,Pages page){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/downPaging.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Pages> map=new HashMap<String, Pages>();
            map.put("page", page);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    /**
     * @Title: getDownPageContent
     * @Description: 通过模板构二级页面造下分页
     * @param context
     * @param page
     * @return    
     * @return String    返回类型
     */
    public static String getCouponsePage(ServletContext context,Couponsduijiang couponsduijinag){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/front");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("couponse/coupons.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Couponsduijiang> map=new HashMap<String, Couponsduijiang>();
            map.put("couponsduijinag", couponsduijinag);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
    /**
     * @Title: getProductContent
     * @Description: 构造添加促销活动线路读取分页
     * @param context
     * @param productList
     * @param request
     * @return    
     * @return String    返回类型
     * @author xiejin
     */
    public static String getProductContent(ServletContext context,List<Product> productList,HttpServletRequest request){  
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(context, "/WEB-INF/template/admin");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("include/productSelect.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("productList", productList);
            map.put("request", request);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    } 
    
    
    /**
     * 
     * @param context
     * @param request
     * @return
     */
    public static String getContactUsNotice(HttpServletRequest request,ContactUs contactUs){
        String htmlText="";  
        try {  
        	Configuration cfg = new Configuration();
        	cfg.setServletContextForTemplateLoading(request.getServletContext(), "/WEB-INF/template/admin");//ftl的基本路径
            //通过指定模板名获取FreeMarker模板实例  
            Template tpl= cfg.getTemplate("manage/contactus/contactUsNotice.ftl","UTF-8");  
            tpl.setEncoding("UTF-8");
            //FreeMarker通过Map传递动态数据  
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("request", request);
            map.put("contactUs",contactUs);
            //解析模板并替换动态数据，最终替换标签对应的变量。  
            htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,map);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return htmlText;  
    }
}