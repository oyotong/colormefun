package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfHistoryDetail;

public interface MfHistoryDetailDAO extends BaseDAO<MfHistoryDetail>{
	Double getTotleBySearchField(MfHistoryDetail o, String fieldName,
			Object... addArgs);
}