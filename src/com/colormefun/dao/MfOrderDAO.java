package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfOrder;

public interface MfOrderDAO extends BaseDAO<MfOrder>{
	Double getTotleBySearchField(MfOrder o, String fieldName,
			Object... addArgs);
}