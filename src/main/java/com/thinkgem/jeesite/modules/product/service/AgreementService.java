package com.thinkgem.jeesite.modules.product.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import com.google.gson.Gson;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.SystemPath;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class AgreementService {

	// 创建Configuration对象
	private static Configuration config = new Configuration();

	/**
	 * 模板名
	 * @param templateName
	 * 模板模型 用于在模板内输出结果集
	 * @param dataModel
	 * 输出对象
	 * @param out
	 */
	public static void processTemplate(String templateName, Map<?, ?> dataModel, Writer out) {
		try {
			// 获得模板
			Template template = config.getTemplate(templateName, "utf-8");
			// 通过模板生成的html文件
			template.process(dataModel, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out = null;
			}
		}
	}

	/**
	 * 初始化模板配置
	 * @param servletContext
	 * @param templateDir
	 */
	public static void initConfig() {
		try {
			config.setLocale(Locale.CHINA);
			config.setDefaultEncoding("utf-8");
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			config.setDirectoryForTemplateLoading(new File("/home/source/tomcat/conf/")); // 服务器路径

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String uploadAgreeMent(String path){
		String rt="";
		com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Zone.autoZone());

		UploadManager uploadManager = new UploadManager(cfg);

		String accessKey = "JBOU_azMVeLmco9aLHHOqGyoEraSli9rTpXl9C-u";
		String secretKey = "s2T5YlpK92Q9YulN_efSuiNUFDvgjVJVGSHXJvHy";
		String bucket = "yzadmin";

		String localFilePath = path;
	
		String key = null;

		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);

		try {
		    Response response = uploadManager.put(localFilePath, key, upToken);
		    //解析上传成功的结果
		    DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
		    if(putRet!=null){
		    		System.out.println("上传成功");
		    		rt = putRet.key;
		    }
		} catch (QiniuException ex) {
		    Response r = ex.response;
		    System.err.println(r.toString());
		    try {
		        System.err.println(r.bodyString());
		    } catch (QiniuException ex2) {
		    
		    }
		}
		
		return rt;
	}

	public void htmlCodeComeFromFile(String filePath, String pdfPath) {
		try {
			Document document = new Document(PageSize.A4);
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
			document.open();
			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
			worker.parseXHtml(pdfWriter, document, new FileInputStream(filePath), null, new AsianFontProviders());
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public class AsianFontProviders extends XMLWorkerFontProvider {  
		  
	    public Font getFont(final String fontname, final String encoding,  
	            final boolean embedded, final float size, final int style,  
	            final BaseColor color) {  
	        BaseFont bf = null;  
	        try {  
	            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        Font font = new Font(bf, size, style, color);  
	        font.setColor(color);  
	        return font;  
	    }
	    
	} 	
	public static void main(String[] args) {
		File directory = new File("");//设定为当前文件夹 
		try{ 
		    System.out.println(directory.getCanonicalPath());//获取标准的路径 
		    System.out.println(directory.getAbsolutePath());//获取绝对路径 
		}catch(Exception e) {} 

	}
}
