package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCartCoupon;

public interface MfCartCouponDAO extends BaseDAO<MfCartCoupon>{
	Double getTotleBySearchField(MfCartCoupon o, String fieldName,
			Object... addArgs);
}