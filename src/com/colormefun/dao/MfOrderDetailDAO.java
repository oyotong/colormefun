package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfOrderDetail;

public interface MfOrderDetailDAO extends BaseDAO<MfOrderDetail>{
	Double getTotleBySearchField(MfOrderDetail o, String fieldName,
			Object... addArgs);
}