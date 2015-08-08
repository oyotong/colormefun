package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfCart;
import com.colormefun.entity.MfOrder;
import com.colormefun.entity.MfUser;

public interface MfOrderService {
	
	public void saveMfOrder(MfOrder o);
	
	public void updateMfOrder(MfOrder o);
	
	public void deleteMfOrder(MfOrder o);
	
	public void deleteMfOrderByIds(String[] ids);
	
	public MfOrder getMfOrderById(Serializable id);
	
	public List<MfOrder> findAllMfOrder();
	
	public List<MfOrder> findAllMfOrderWithPage();
	
	public List<MfOrder> findAllMfOrderBySearchWithPage(MfOrder o);
	
	public Map findAllMfOrderByNameWithIdAndName(String name);
	
	public List<MfOrder> findAllMfOrderByIds(String[] cids);

    public List<MfOrder> findMyOrders(MfOrder order);

    public void removeOrder(MfOrder order);

    public void completePayOrder(String orderNo);

    public MfOrder createOrderByCart(List<MfCart> mfCartList);
}
