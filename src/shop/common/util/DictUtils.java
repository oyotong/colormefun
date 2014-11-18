package shop.common.util;


import java.util.List;

import org.springframework.web.context.support.WebApplicationContextUtils;

import shop.common.context.ApplicationContext;
import shop.company.entity.SystemDictionaryItem;
import shop.company.service.SystemDictionaryItemService;

public class DictUtils {
	private static SystemDictionaryItemService dictionaryItemService;
	static{
		dictionaryItemService = (SystemDictionaryItemService) WebApplicationContextUtils.getWebApplicationContext(ApplicationContext.getContext().getApplication()).
					getBeansOfType(SystemDictionaryItemService.class).values().iterator().next();
		
	}
	
	public static String dict(String dictId,String key){
		SystemDictionaryItem dictionaryItem = dictionaryItemService.getSystemDictionaryItemByDictIdAndKey(dictId, key);
		return (null == dictionaryItem) ? "" : dictionaryItem.getDictItemValue();
	}
	public static List<SystemDictionaryItem> dict(String dictId){
		List<SystemDictionaryItem> dictionaryItem = dictionaryItemService.findSystemDictionaryItemByDictId(dictId);
		return dictionaryItem;
	}
}
