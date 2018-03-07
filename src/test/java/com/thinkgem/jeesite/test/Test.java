package com.thinkgem.jeesite.test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.thinkgem.jeesite.modules.mtinvest.entity.MtInvest;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.product.dao.MtProductDao;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;
import com.thinkgem.jeesite.modules.product.service.MtProductService;

public class Test {
	  public static void main(String[] args) throws Exception {
		  	String fileName = "E:\\1111.pdf";
			PdfReader reader = new PdfReader(fileName);  
	        ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
	        /* 将要生成的目标PDF文件名称 */   
	        PdfStamper ps = new PdfStamper(reader, bos);  
	        PdfContentByte under = ps.getUnderContent(1);
	         /* 使用中文字体 */    
	        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);  
	        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
	        fontList.add(bf);  
	        //给pdf合同赋值
	        Map<String, String> data = new HashMap<String, String>();
	        List<MtProduct> list=new ArrayList<MtProduct>();
	        data.put("pid","asda");	        
	        /* 取出报表模板中的所有字段 */    
	        AcroFields fields = ps.getAcroFields();  
	        fields.setSubstitutionFonts(fontList);  
	        fillData(fields, data);
	        /* 必须要调用这个，否则文档不会生成的 */    
	        ps.setFormFlattening(true);  
	        ps.close();
	        //文件存储的路径（需要先创建好文件夹）
	        OutputStream fos = new FileOutputStream("E:\\yzbxlc.pdf");  
	        fos.write(bos.toByteArray());  
	        fos.flush();  
	        fos.close();  
	        bos.close();  
	       System.out.println("成功");
	  }  
	  
	    public static void fillData(AcroFields fields, Map<String, String> data)  
	            throws IOException, DocumentException {  
	        for (String key : data.keySet()) {  
	            String value = data.get(key);  
	            fields.setField(key, value); // 为字段赋值,注意字段名称是区分大小写的  
	        }  
	    }  

	  }

