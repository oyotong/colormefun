package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfOrderCoupon;

public interface MfOrderCouponDAO extends BaseDAO<MfOrderCoupon>{
	Double getTotleBySearchField(MfOrderCoupon o, String fieldName,
			Object... addArgs);
}