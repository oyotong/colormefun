package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCart;

public interface MfCartDAO extends BaseDAO<MfCart>{
	Double getTotleBySearchField(MfCart o, String fieldName,
			Object... addArgs);
}