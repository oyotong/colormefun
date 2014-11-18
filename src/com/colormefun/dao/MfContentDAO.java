package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfContent;

public interface MfContentDAO extends BaseDAO<MfContent>{
	Double getTotleBySearchField(MfContent o, String fieldName,
			Object... addArgs);
}