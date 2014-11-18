package com.colormefun.dao;

import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfMonthlyReport;

public interface MfMonthlyReportDAO extends BaseDAO<MfMonthlyReport>{
	Double getTotleBySearchField(MfMonthlyReport o, String fieldName,
			Object... addArgs);

    void createMonthlyReport();
}