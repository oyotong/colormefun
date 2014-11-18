package com.colormefun.dao;

import java.io.Serializable;
import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfCase;

public interface MfCaseDAO extends BaseDAO<MfCase>{
	Double getTotleBySearchField(MfCase o, String fieldName,
			Object... addArgs);
    public MfCase getActivedMfCaseById(Integer caseNo);
    public MfCase getActivedMfCaseByIdAndStartDate(Integer caseNo, Date startDate);
}