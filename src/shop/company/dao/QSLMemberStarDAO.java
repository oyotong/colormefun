package shop.company.dao;

import java.util.*;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLMemberStar;

public interface QSLMemberStarDAO extends BaseDAO<QSLMemberStar>{
	Double getTotleBySearchField(QSLMemberStar o, String fieldName,
                                 Object... addArgs);

	List<QSLMemberStar> findTopNStar(Integer n);
}