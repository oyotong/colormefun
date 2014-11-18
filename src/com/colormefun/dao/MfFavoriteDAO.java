package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfFavorite;

public interface MfFavoriteDAO extends BaseDAO<MfFavorite>{
	Double getTotleBySearchField(MfFavorite o, String fieldName,
			Object... addArgs);
}