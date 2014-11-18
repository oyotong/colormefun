package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfCartDAO;
import com.colormefun.entity.MfCart;

@Service
@Transactional
public class MfCartServiceImpl implements MfCartService {

	@Autowired()
	private MfCartDAO mfCartDAO;

	public MfCartDAO getMfCartDAO() {
		return mfCartDAO;
	}

	public void setMfCartDAO(MfCartDAO mfCartDAO) {
		this.mfCartDAO = mfCartDAO;
	}

	@Override
	public void deleteMfCart(MfCart o) {
        MfCart old = mfCartDAO.get(o.getCartNo());
		mfCartDAO.remove(old);
	}
	
	@Override
	public void deleteMfCartByIds(Integer[] ids){
		mfCartDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfCart> findAllMfCart() {
		return mfCartDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCart> findAllMfCartWithPage(){
		return mfCartDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCart> findAllMfCartBySearchWithPage(MfCart o){
		return mfCartDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfCart getMfCartById(Serializable id) {
		return mfCartDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfCartByNameWithIdAndName(String name) {
		return mfCartDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfCart(MfCart o) {
		mfCartDAO.persist(o);
	}

	@Override
	public void updateMfCart(MfCart o) {
		mfCartDAO.merge(o);
	}
	
	@Override
	public List<MfCart> findAllMfCartByIds(Integer[] cids) {
		return mfCartDAO.findAllByIds(cids);
	}

    @Override
    public List<MfCart> findAllMfCartByUser(MfUser user) {
        if(null == user || user.getUserNo() == null){
            return new ArrayList<MfCart>();
        }
        MfCart cart = new MfCart();
        cart.setUser(user);

        return findAllMfCartBySearchWithPage(cart);
    }

}
