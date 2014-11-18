package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCoupon;

public interface MfCouponDAO extends BaseDAO<MfCoupon>{
	Double getTotleBySearchField(MfCoupon o, String fieldName,
			Object... addArgs);
}