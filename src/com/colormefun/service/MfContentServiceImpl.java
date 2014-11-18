package com.colormefun.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfContentDAO;
import com.colormefun.entity.MfContent;
import shop.common.context.ApplicationContext;
import shop.common.util.page.Pagination;

@Service
@Transactional
public class MfContentServiceImpl implements MfContentService {

	@Autowired()
	private MfContentDAO mfContentDAO;

	public MfContentDAO getMfContentDAO() {
		return mfContentDAO;
	}

	public void setMfContentDAO(MfContentDAO mfContentDAO) {
		this.mfContentDAO = mfContentDAO;
	}

	@Override
	public void deleteMfContent(MfContent o) {
		mfContentDAO.remove(o);
	}
	
	@Override
	public void deleteMfContentByIds(Integer[] ids){
		mfContentDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfContent> findAllMfContent() {
		return mfContentDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfContent> findAllMfContentWithPage(){
		return mfContentDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfContent> findAllMfContentBySearchWithPage(MfContent o){
		return mfContentDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfContent getMfContentById(Serializable id) {
		return mfContentDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfContentByNameWithIdAndName(String name) {
		return mfContentDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfContent(MfContent o) {
		mfContentDAO.persist(o);
	}

	@Override
	public void updateMfContent(MfContent o) {
		mfContentDAO.merge(o);
	}
	
	@Override
	public List<MfContent> findAllMfContentByIds(Integer[] cids) {
		return mfContentDAO.findAllByIds(cids);
	}

    @Override
    public List<MfContent> findTopNMarqueeOrderBySeqNo(Integer n) {
        Pagination pagination = ApplicationContext.getContext().getPagination();
        if(null != pagination){
            pagination.setPage(1);
            pagination.setRowCount(n);
        }
        MfContent c = new MfContent();
        c.setActive("Y");
        c.setContentType(MfContent.ContentType.marquee.name());
        return findAllMfContentBySearchWithPage(c);
    }

    @Override
    public MfContent getAbout() {
        MfContent content = new MfContent();
        content.setContentType(MfContent.ContentType.about.toString());
        List<MfContent> list = findAllMfContentBySearchWithPage(content);
        if(null != list && list.size() > 0){
            content = list.get(0);
        }
        return content;
    }
}
