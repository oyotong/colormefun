package shop.company.dao;

import java.util.*;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLCoach;

public interface QSLCoachDAO extends BaseDAO<QSLCoach>{
	Double getTotleBySearchField(QSLCoach o, String fieldName,
                                 Object... addArgs);

	List<QSLCoach> findTopN(Integer n);
}