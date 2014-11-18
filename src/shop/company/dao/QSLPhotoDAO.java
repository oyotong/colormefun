package shop.company.dao;

import java.util.*;
import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLPhoto;

public interface QSLPhotoDAO extends BaseDAO<QSLPhoto>{
	Double getTotleBySearchField(QSLPhoto o, String fieldName,
                                 Object... addArgs);
}