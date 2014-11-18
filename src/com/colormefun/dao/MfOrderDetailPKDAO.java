package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfOrderDetailPK;

public interface MfOrderDetailPKDAO extends BaseDAO<MfOrderDetailPK>{
	Double getTotleBySearchField(MfOrderDetailPK o, String fieldName,
			Object... addArgs);
}