package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfOrderDAO;
import com.colormefun.entity.MfOrder;
import shop.common.context.ApplicationContext;
import shop.common.exception.ApplicationException;
import shop.common.util.DateUtils;
import shop.common.util.page.Pagination;

@Service
@Transactional
public class MfOrderServiceImpl implements MfOrderService {

	@Autowired()
	private MfOrderDAO mfOrderDAO;

	public MfOrderDAO getMfOrderDAO() {
		return mfOrderDAO;
	}

	public void setMfOrderDAO(MfOrderDAO mfOrderDAO) {
		this.mfOrderDAO = mfOrderDAO;
	}

	@Override
	public void deleteMfOrder(MfOrder o) {
		mfOrderDAO.remove(o);
	}
	
	@Override
	public void deleteMfOrderByIds(String[] ids){
		mfOrderDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfOrder> findAllMfOrder() {
		return mfOrderDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfOrder> findAllMfOrderWithPage(){
		return mfOrderDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfOrder> findAllMfOrderBySearchWithPage(MfOrder o){
		return mfOrderDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfOrder getMfOrderById(Serializable id) {
        MfOrder order = mfOrderDAO.get(id);
        return order;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfOrderByNameWithIdAndName(String name) {
		return mfOrderDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfOrder(MfOrder o) {
		mfOrderDAO.persist(o);
	}

	@Override
	public void updateMfOrder(MfOrder o) {
		mfOrderDAO.merge(o);
	}
	
	@Override
	public List<MfOrder> findAllMfOrderByIds(String[] cids) {
		return mfOrderDAO.findAllByIds(cids);
	}

    @Override
    public List<MfOrder> findMyOrders(MfOrder order) {

        ApplicationContext context = ApplicationContext.getContext();

        MfUser currentUser = context.getCurrentMfUser();
        if(null == currentUser){
            return null;
        }
        if(order == null){
            order = new MfOrder();
        }

        order.setUser(currentUser);
        order.setActive("Y");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);

        order.setCreatedDate(DateUtils.setToFirstTime(DateUtils.setToFirstDateOfMonth(calendar.getTime())));

        Pagination pagination = context.getPagination();
        if(pagination == null){
            pagination = new Pagination();
            context.setPagination(pagination);
        }
        pagination.setPage(1);
        pagination.setRowCount(50);
        pagination.setOrderBys(new String[]{"createdDate desc"});

        List<MfOrder> orders = findAllMfOrderBySearchWithPage(order);

        return orders;
    }

    @Override
    public void removeOrder(MfOrder order) {
        MfOrder oldOne = getMfOrderById(order.getOrderNo());
        if(null == oldOne || !oldOne.getStatus().equals(MfOrder.OrderStatus.created.name())){
            throw new ApplicationException("无效的订单状态，无法删除。");
        }
        oldOne.setStatus(MfOrder.OrderStatus.deleted.name());
        saveMfOrder(oldOne);
    }

    @Override
    public void payOrder(MfOrder order) {
        MfOrder oldOne = getMfOrderById(order.getOrderNo());
        if(null == oldOne || !oldOne.getStatus().equals(MfOrder.OrderStatus.created.name())){
            throw new ApplicationException("无效的订单状态，无法支付。");
        }
        oldOne.setStatus(MfOrder.OrderStatus.paid.name());
        saveMfOrder(oldOne);
    }

}
