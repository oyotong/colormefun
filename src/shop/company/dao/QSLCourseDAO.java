package shop.company.dao;

import java.util.*;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLCourse;

public interface QSLCourseDAO extends BaseDAO<QSLCourse>{
	Double getTotleBySearchField(QSLCourse o, String fieldName,
                                 Object... addArgs);

	List<QSLCourse> findAllByTabId(Integer tabId);
}