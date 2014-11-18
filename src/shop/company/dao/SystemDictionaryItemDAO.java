package shop.company.dao;

import java.util.List;

import shop.common.dao.auto.BaseDAO;
import shop.company.entity.SystemDictionaryItem;

public interface SystemDictionaryItemDAO extends BaseDAO<SystemDictionaryItem>{

	List<SystemDictionaryItem> findAllByDictId(String dictId);
	
}