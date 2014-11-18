package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfVipCase;

public interface MfVipCaseDAO extends BaseDAO<MfVipCase>{
	Double getTotleBySearchField(MfVipCase o, String fieldName,
			Object... addArgs);
}