package com.colormefun.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colormefun.dao.MfCouponDAO;
import com.colormefun.entity.MfCoupon;
import shop.common.context.ApplicationContext;
import shop.common.exception.ApplicationException;
import shop.common.util.DateUtils;
import shop.common.util.StringUtils;
import shop.common.util.page.Pagination;

import javax.lang.model.element.NestingKind;

@Service
@Transactional
public class MfCouponServiceImpl implements MfCouponService {

	@Autowired()
	private MfCouponDAO mfCouponDAO;

	public MfCouponDAO getMfCouponDAO() {
		return mfCouponDAO;
	}

	public void setMfCouponDAO(MfCouponDAO mfCouponDAO) {
		this.mfCouponDAO = mfCouponDAO;
	}

	@Override
	public void deleteMfCoupon(MfCoupon o) {
		mfCouponDAO.remove(o);
	}
	
	@Override
	public void deleteMfCouponByIds(Integer[] ids){
		mfCouponDAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MfCoupon> findAllMfCoupon() {
		return mfCouponDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCoupon> findAllMfCouponWithPage(){
		return mfCouponDAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<MfCoupon> findAllMfCouponBySearchWithPage(MfCoupon o){
		return mfCouponDAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public MfCoupon getMfCouponById(Serializable id) {
		return mfCouponDAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAllMfCouponByNameWithIdAndName(String name) {
		return mfCouponDAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void saveMfCoupon(MfCoupon o) {
		mfCouponDAO.persist(o);
	}

	@Override
	public void updateMfCoupon(MfCoupon o) {
		mfCouponDAO.merge(o);
	}
	
	@Override
	public List<MfCoupon> findAllMfCouponByIds(Integer[] cids) {
		return mfCouponDAO.findAllByIds(cids);
	}

    @Override
    public void createMfCoupon(MfCoupon coupon) {
        // create coupon
        // deduction,discount,total,couponType,description,createdId,createdDate,createdDate,deadline
        // StringUtils.isNull(
        if(coupon == null){
            throw new ApplicationException("数据错误");
        }
        if(null == coupon.getDeduction()){
            throw new ApplicationException("优惠劵金额不能为空");
        }
        if(null == coupon.getTotal() || coupon.getTotal() <= 0){
            throw new ApplicationException("优惠劵生成数量不能为空，并需要大于0");
        }
        if(null == coupon.getCouponType()){
            throw new ApplicationException("优惠劵类别不能为空");
        }
        if(null == coupon.getDeadline()){
            throw new ApplicationException("到期时间不能为空");
        }
        if(coupon.getDeadline().before(DateUtils.now())){
            throw new ApplicationException("到期时间不能早于当前时间");
        }

        // get version
        String versionSeed = coupon.getCouponType() + "-" + DateUtils.dateTime(coupon.getCreatedDate())
                +"-"+DateUtils.dateTime(coupon.getDeadline())+"-"+coupon.getDeduction()+"-"+coupon.getDiscount();
        String version = StringUtils.toMD5(versionSeed,16);
        for(int i=0;i<coupon.getTotal();i++){

            // create couponNo
            String seed = i+"-"+version + "-" + coupon.getCouponType() + "-" + DateUtils.dateTime(coupon.getCreatedDate())
                    +"-"+DateUtils.dateTime(coupon.getDeadline())+"-"+coupon.getDeduction()+"-"+coupon.getDiscount();
            String couponNo = generateCouponCode(seed);

            MfCoupon c = new MfCoupon();

            c.setCouponNo(couponNo);

            String password = StringUtils.toMD5(version+couponNo);
            c.setPassword(password);

            c.setVersion(version);
            c.setTotal(coupon.getTotal());
            c.setCouponType(coupon.getCouponType());
            c.setCreatedDate(coupon.getCreatedDate());
            c.setCreatedId(coupon.getCreatedId());
            c.setActive("Y");
            c.setDeadline(coupon.getDeadline());
            c.setDeduction(coupon.getDeduction());
            c.setDiscount(coupon.getDiscount());
            c.setDescription(coupon.getDescription());
            c.setStatus("created");

            mfCouponDAO.persist(c);
        }
    }

    @Override
    public void saveAllMfCoupon(List<MfCoupon> list) {
        if(null == list){
            return;
        }
        for(MfCoupon coupon: list){
            saveMfCoupon(coupon);
        }
    }

    @Override
    public MfCoupon getMfCouponByCouponNo(String couponNo) {
        MfCoupon coupon = new MfCoupon();
        coupon.setCouponNo(couponNo);
        List<MfCoupon> list = findAllMfCouponBySearchWithPage(coupon);
        if(null == list || list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<MfCoupon> findAllMfCouponByUserNoAndStatus(Integer userNo, String locked) {
        Pagination pagination = new Pagination();
        pagination.setPage(1);
        pagination.setRowCount(1000);
        ApplicationContext.getContext().setPagination(pagination);
        MfCoupon coupon = new MfCoupon();
        coupon.setStatus(locked);
        coupon.setUserNo(userNo);
        List<MfCoupon> list = findAllMfCouponBySearchWithPage(coupon);
        return list;
    }

    public String generateCouponCode(String seed){
        return StringUtils.toMD5(seed, 16).replace('A','0').replace('B','1').replace('C','2').replace('D','3').replace('E','4').replace('F','5');
    }

}
