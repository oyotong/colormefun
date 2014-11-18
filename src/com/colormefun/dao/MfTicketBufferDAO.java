package com.colormefun.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfTicketBuffer;

public interface MfTicketBufferDAO extends BaseDAO<MfTicketBuffer>{
	Double getTotleBySearchField(MfTicketBuffer o, String fieldName,
			Object... addArgs);
}