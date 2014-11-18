package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfOrderCouponPK;

public interface MfOrderCouponPKDAO extends BaseDAO<MfOrderCouponPK>{
	Double getTotleBySearchField(MfOrderCouponPK o, String fieldName,
			Object... addArgs);
}