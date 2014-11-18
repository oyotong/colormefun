package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfUser;

public interface MfUserDAO extends BaseDAO<MfUser>{
	Double getTotleBySearchField(MfUser o, String fieldName,
			Object... addArgs);
}