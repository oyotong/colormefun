package shop.company.dao;

import java.util.*;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLVenues;

public interface QSLVenuesDAO extends BaseDAO<QSLVenues>{
	Double getTotleBySearchField(QSLVenues o, String fieldName,
                                 Object... addArgs);

	List<QSLVenues> findTopN(Integer n);
}