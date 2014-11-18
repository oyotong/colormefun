package shop.company.dao;

import java.util.*;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLProduct;

public interface QSLProductDAO extends BaseDAO<QSLProduct>{
	Double getTotleBySearchField(QSLProduct o, String fieldName,
                                 Object... addArgs);

	List<QSLProduct> findTopNProduct(Integer n);
}