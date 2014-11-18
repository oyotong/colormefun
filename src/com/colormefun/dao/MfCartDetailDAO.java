package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCartDetail;

public interface MfCartDetailDAO extends BaseDAO<MfCartDetail>{
	Double getTotleBySearchField(MfCartDetail o, String fieldName,
			Object... addArgs);
}