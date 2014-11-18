package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfHistoryDetailPK;

public interface MfHistoryDetailPKDAO extends BaseDAO<MfHistoryDetailPK>{
	Double getTotleBySearchField(MfHistoryDetailPK o, String fieldName,
			Object... addArgs);
}