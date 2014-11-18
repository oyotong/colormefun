package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfHistory;

public interface MfHistoryDAO extends BaseDAO<MfHistory>{
	Double getTotleBySearchField(MfHistory o, String fieldName,
			Object... addArgs);
}