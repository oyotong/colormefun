package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfMonthlyReportPK;

public interface MfMonthlyReportPKDAO extends BaseDAO<MfMonthlyReportPK>{
	Double getTotleBySearchField(MfMonthlyReportPK o, String fieldName,
			Object... addArgs);
}