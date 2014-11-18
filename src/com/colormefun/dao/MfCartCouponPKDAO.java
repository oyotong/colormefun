package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCartCouponPK;

public interface MfCartCouponPKDAO extends BaseDAO<MfCartCouponPK>{
	Double getTotleBySearchField(MfCartCouponPK o, String fieldName,
			Object... addArgs);
}