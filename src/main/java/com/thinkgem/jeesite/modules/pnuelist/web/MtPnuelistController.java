/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pnuelist.web;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.xstream.ApachePoi;
import com.thinkgem.jeesite.modules.mtuser.entity.MtUser;
import com.thinkgem.jeesite.modules.mtuser.service.MtUserService;
import com.thinkgem.jeesite.modules.pnuelist.entity.MtPnuelist;
import com.thinkgem.jeesite.modules.pnuelist.service.MtPnuelistService;
import com.thinkgem.jeesite.modules.product.entity.MtProduct;
import com.thinkgem.jeesite.modules.product.service.MtProductService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.ProductUtils;
import com.thinkgem.jeesite.modules.sys.utils.mtUserUtils;

/**
 * 平台收益Controller
 * @author wuhao
 * @version 2017-06-15
 */
@Controller
@RequestMapping(value = "${adminPath}/pnuelist/mtPnuelist")
public class MtPnuelistController extends BaseController {

	@Autowired
	private MtPnuelistService mtPnuelistService;
	private List<MtPnuelist> mtPnuelist;
	
	@ModelAttribute
	public MtPnuelist get(@RequestParam(required=false) String id) {
		MtPnuelist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = mtPnuelistService.get(id);
		}
		if (entity == null){
			entity = new MtPnuelist();
		}
		return entity;
	}
	
	@RequiresPermissions("pnuelist:mtPnuelist:view")
	@RequestMapping(value = {"list", ""})
	public String list(MtPnuelist mtPnuelist, HttpServletRequest request, HttpServletResponse response, Model model) {
		boolean show = false;
		String strdate = request.getParameter("date");
		if(!(strdate==null)) {			
			mtPnuelist.setFindtime(toDate(strdate)+"%");
			show = true;
		}
		Page<MtPnuelist> page = mtPnuelistService.findPage(new Page<MtPnuelist>(request, response), mtPnuelist);
		this.mtPnuelist = page.getList();
		model.addAttribute("page", page);
		model.addAttribute("show", show);
		return "modules/pnuelist/mtPnuelistList";
	}
	
	@RequestMapping(value = {"totalList", ""})
	public String totalList(MtPnuelist mtPnuelist, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(!(mtPnuelist.getCreateDate()==null)) {
			Date CreateDate = mtPnuelist.getCreateDate();
			mtPnuelist.setFindtime(DateUtils.formatDate(CreateDate)+"%");
		}
		Page<MtPnuelist> page = new Page<MtPnuelist>(request, response);
		List<MtPnuelist> dataList = mtPnuelistService.findTotalList(mtPnuelist);
		page.setCount(dataList.size());
		mtPnuelist.setPage(page);
		page.setList(dataList);
		model.addAttribute("page", page);
		return "modules/pnuelist/mtTotalList";
	}

	private String toDate(String time) {
		String createDate = null;
		try {
			SimpleDateFormat simple = null;
			simple = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
			Date date = (Date) simple.parse(time.toString());
			createDate = DateUtils.formatDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return createDate;
	}
	
	@RequiresPermissions("product:mtProduct:edit")
	@RequestMapping(value = "deriveData")
	public void deriveData(HttpServletResponse resp) {
		// 创建  
        HSSFWorkbook wb = ApachePoi.getHSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();  
        // 创建单元格样式  
        HSSFCellStyle titleCellStyle = ApachePoi.tableTopStyle();
        
        HSSFCell headerCell = null;
        HSSFRow headerRow = sheet.createRow(0);  
        String[] titles = getHeaderTitle();
        for (int c = 0; c < titles.length; c++) {  
            headerCell = headerRow.createCell(c);  
            headerCell.setCellStyle(titleCellStyle);  
            headerCell.setCellValue(titles[c]);  
            sheet.setColumnWidth(c, (30 * 160));  
        }  
        // 创建单元格样式  
        HSSFCellStyle cellStyle = ApachePoi.tableBodyStyle();
         
        List<MtPnuelist> list = mtPnuelist;
        
        for (int li=0; li<list.size(); li++) {  
        	Integer line = 0;
            MtPnuelist item = list.get(li);
            HSSFRow row = sheet.createRow(li + 1);
            
            HSSFCell cell = row.createCell(line++);  
            cell.setCellStyle(cellStyle);      
            MtUser mtUser = mtUserUtils.get(String.valueOf(item.getMtUserId()));
            cell.setCellValue(mtUser.getUserNickName());
            
            cell = row.createCell(line++);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(mtUser.getUserRealName());
            
            cell = row.createCell(line++);  
            cell.setCellStyle(cellStyle);  
            MtProduct mtProduct = ProductUtils.getProductByGrade(item.getPid());
            cell.setCellValue(mtProduct.getTitle());
            
            cell = row.createCell(line++);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(item.getInmount().toString());
            
            cell = row.createCell(line++);  
            cell.setCellStyle(cellStyle);
            if(!(item.getNper()==null)) {
            	cell.setCellValue(item.getNper());
            } else {
            	cell.setCellValue(0);
            }
            
            cell = row.createCell(line++);  
            cell.setCellStyle(cellStyle);  
            String type = DictUtils.getDictLabel(item.getType().toString(), "sitelucre_type", "");
            cell.setCellValue(type);
            
            cell = row.createCell(line++);  
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(DateUtils.formatDateTime(item.getCreateDate()));
        }  
        
        String fileName="平台收益.xls";        
        resp = ApachePoi.getResponseParam(fileName, resp);
  
        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("E:/test.xls");
			wb.write(resp.getOutputStream());  
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
        System.out.println("Done");
	}
	
	private String[] getHeaderTitle() {
		String[] titles = {"用户名","真实姓名","产品名称","收益金额","还款期数","收益类型","收益时间"};
		return titles;
	}
	
}