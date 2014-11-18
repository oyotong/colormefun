package shop.company.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLCourseTab;

public interface QSLCourseTabDAO extends BaseDAO<QSLCourseTab>{
	Double getTotleBySearchField(QSLCourseTab o, String fieldName,
                                 Object... addArgs);

	QSLCourseTab findAllByYearAndMonth(Integer year, Integer month);
}