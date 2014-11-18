package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCartDetailPK;

public interface MfCartDetailPKDAO extends BaseDAO<MfCartDetailPK>{
	Double getTotleBySearchField(MfCartDetailPK o, String fieldName,
			Object... addArgs);
}