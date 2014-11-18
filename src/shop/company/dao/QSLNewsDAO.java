package shop.company.dao;

import java.util.*;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLNews;

public interface QSLNewsDAO extends BaseDAO<QSLNews>{
	
	Double getTotleBySearchField(QSLNews o, String fieldName,
                                 Object... addArgs);

	List<QSLNews> findTopNNews(String type, int n);
}