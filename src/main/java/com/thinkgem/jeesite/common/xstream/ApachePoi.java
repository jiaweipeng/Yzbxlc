package com.thinkgem.jeesite.common.xstream;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ApachePoi {

	private static HSSFWorkbook wb = new HSSFWorkbook();
	
	public static HSSFWorkbook getHSSFWorkbook() {
		return wb;
	}
	
	public static HSSFCellStyle tableTopStyle() {
		// 创建单元格样式  
        HSSFCellStyle titleCellStyle = wb.createCellStyle();  
        // 指定单元格居中对齐，边框为细  
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        // 设置填充色  
        titleCellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
        titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        // 指定当单元格内容显示不下时自动换行  
        titleCellStyle.setWrapText(true);  
        // 设置单元格字体  
        HSSFFont titleFont = wb.createFont();  
        titleFont.setFontHeightInPoints((short) 12);  
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        titleCellStyle.setFont(titleFont);
		return titleCellStyle;
	}
	
	public static HSSFCellStyle tableBodyStyle() {
		// 创建单元格样式  
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        // 指定单元格居中对齐，边框为细  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        // 设置单元格字体  
        HSSFFont font = wb.createFont();  
        font.setFontHeightInPoints((short) 11);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        cellStyle.setFont(font);
		return cellStyle;
	}
	
	public static HttpServletResponse getResponseParam(String filename, HttpServletResponse response) {
        try {
        	//设置文件头编码格式        
        	response.setHeader("Content-disposition","attachment;filename="+new String(filename.getBytes("gb2312"),"ISO8859-1"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        response.setContentType("APPLICATION/OCTET-STREAM;charset=UTF-8");//设置类型
        response.setHeader("Cache-Control","no-cache");//设置头
        response.setDateHeader("Expires", 0);//设置日期头
        return response;
	}
	
}
