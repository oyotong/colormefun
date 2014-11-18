package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfOrder;
import com.colormefun.entity.MfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfFavoriteDAO;
import com.colormefun.entity.MfFavorite;
import shop.common.context.ApplicationContext;
import shop.common.exception.ApplicationException;
import shop.common.util.DateUtils;
import shop.common.util.page.Pagination;

@Service
@Transactional
public class MfFavoriteServiceImpl implements MfFavoriteService {

	@Autowired()
	private MfFavoriteDAO mfFavoriteDAO;

	public MfFavoriteDAO getMfFavoriteDAO() {
		return mfFavoriteDAO;
	}

	public void setMfFavoriteDAO(MfFavoriteDAO mfFavoriteDAO) {
		this.mfFavoriteDAO = mfFavoriteDAO;
	}

	@Override
	public void deleteMfFavorite(MfFavorite o) {
		mfFavoriteDAO.remove(o);
	}
	
	@Override
	public void deleteMfFavoriteByIds(Integer[] ids){
		mfFavoriteDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfFavorite> findAllMfFavorite() {
		return mfFavoriteDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfFavorite> findAllMfFavoriteWithPage(){
		return mfFavoriteDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfFavorite> findAllMfFavoriteBySearchWithPage(MfFavorite o){
		return mfFavoriteDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfFavorite getMfFavoriteById(Serializable id) {
		return mfFavoriteDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfFavoriteByNameWithIdAndName(String name) {
		return mfFavoriteDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfFavorite(MfFavorite o) {
        MfFavorite old = mfFavoriteDAO.igetByQL("select t from MfFavorite t where t.mfCase.caseNo=? and t.user.userNo=?",
                o.getMfCase().getCaseNo(), o.getUser().getUserNo());
        if(old != null){
            throw new ApplicationException("您以前已经收藏过该活动");
        }
		mfFavoriteDAO.persist(o);
	}

	@Override
	public void updateMfFavorite(MfFavorite o) {
		mfFavoriteDAO.merge(o);
	}
	
	@Override
	public List<MfFavorite> findAllMfFavoriteByIds(Integer[] cids) {
		return mfFavoriteDAO.findAllByIds(cids);
	}

    @Override
    public List<MfFavorite> findMyFavorites() {
        ApplicationContext context = ApplicationContext.getContext();

        MfUser currentUser = context.getCurrentMfUser();
        if(null == currentUser){
            return null;
        }

        MfFavorite favorite = new MfFavorite();

        favorite.setUser(currentUser);
        favorite.setActive("Y");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);

        favorite.setCreatedDate(DateUtils.setToFirstTime(DateUtils.setToFirstDateOfMonth(calendar.getTime())));

        Pagination pagination = context.getPagination();
        if(pagination == null){
            pagination = new Pagination();
            context.setPagination(pagination);
        }
        pagination.setPage(1);
        pagination.setRowCount(50);
        pagination.setOrderBys(new String[]{"createdDate desc"});

        List<MfFavorite> favorites = findAllMfFavoriteBySearchWithPage(favorite);

        return favorites;
    }


}
