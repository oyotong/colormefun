package ${daoPackage};

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import ${entityPackage}.${pojo.ClassName};
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class ${pojo.ClassName}DAOImpl extends AbstractDAO<${pojo.ClassName}> implements
		BaseDAO<${pojo.ClassName}>, ${pojo.ClassName}DAO {

	public ${pojo.ClassName}DAOImpl() {
		super(${pojo.ClassName}.class);
	}
	
	private void makeCondition(${pojo.ClassName} o, StringBuffer condition,
			List args) {
		if (isNotNull(o) ){

		<#list pojo?keys as itemKey>
			<#assign item = pojo[itemKey]>
			<#if itemKey?matches("^[a-z].*$")>
			if (isNotNull(o.get${itemKey?cap_first}())) {
				condition.append(" and t.${itemKey}=? ");
				args.add(o.get${itemKey?cap_first}());
			}
			</#if>
		</#list>
		
		}
	}
	
	@Override
	public List<${pojo.ClassName}> findAllBySearchWithPage(${pojo.ClassName} o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from ${pojo.ClassName} t where 1=1 ";
		String countQl = "select count(*) from ${pojo.ClassName} t where 1=1";

		makeCondition(o,condition,args);

		List<${pojo.ClassName}> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.${pojo.Id},o.${pojo.Name} from ${pojo.ClassName} o where o.${pojo.Name} like ?";
		String hql2 = "select count(*) from ${pojo.ClassName} o where o.${pojo.Name} like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<${pojo.ClassName}> findAllByIds(java.io.Serializable[] cids){
		String hql = "from ${pojo.ClassName} o where o.${pojo.Id} in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<${pojo.ClassName}> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(${pojo.ClassName} o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from ${pojo.ClassName} t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
