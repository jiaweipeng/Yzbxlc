/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mtusermony.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.xstream.DayWeek;
import com.thinkgem.jeesite.modules.mtusermony.entity.MtUsermony;
import com.thinkgem.jeesite.modules.mtusermony.dao.MtUsermonyDao;

/**
 * 客户资金列表Service
 * @author wuhao
 * @version 2017-08-19
 */
@Service
@Transactional(readOnly = true)
public class MtUsermonyService extends CrudService<MtUsermonyDao, MtUsermony> {

	public MtUsermony get(String id) {
		return super.get(id);
	}
	
	public List<MtUsermony> findList(MtUsermony mtUsermony) {
		return super.findList(mtUsermony);
	}
	
	public Page<MtUsermony> findPage(Page<MtUsermony> page, MtUsermony mtUsermony) {
		return super.findPage(page, mtUsermony);
	}
	
	@Transactional(readOnly = false)
	public void save(MtUsermony mtUsermony) {
		super.save(mtUsermony);
	}
	
	@Transactional(readOnly = false)
	public void delete(MtUsermony mtUsermony) {
		super.delete(mtUsermony);
	}
	
	public List<MtUsermony> makeDate(MtUsermony mtUsermony) {
		Integer number = 0;
		Integer pageSize = mtUsermony.getPagesize();
		List<MtUsermony> moneyList = new ArrayList<MtUsermony>();
		do {
			MtUsermony usermony = new MtUsermony();
			usermony.setUserid(mtUsermony.getUserid());
			usermony.setDate(DayWeek.minusDay(number));
			moneyList.add(usermony);
			number++;
		} while (number<pageSize);
		return moneyList;
	}
	
	public List<MtUsermony> findDate(MtUsermony mtUsermony) {
		Integer pageSize = mtUsermony.getPagesize();
		List<MtUsermony> moneyList = new ArrayList<MtUsermony>();
		if(mtUsermony.getPageno()==null) {
			mtUsermony.setPageno(1);
		}
		Date enddate = mtUsermony.getEnddate();
		String startdate = DateUtils.formatDate(mtUsermony.getStartdate());
		Integer endList = pageSize * mtUsermony.getPageno();
		Integer startList = endList - pageSize;
		Date date = null;
		do {
			try {
				MtUsermony usermony = new MtUsermony();
				String back = DayWeek.minusDay(startList, startdate);
				date = DateUtils.parseDate(back);
				usermony.setDate(back);
				usermony.setUserid(mtUsermony.getUserid());
				moneyList.add(usermony);
				startList++;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (startList<endList && date.getTime()>enddate.getTime());
		return moneyList;
	}
	
}