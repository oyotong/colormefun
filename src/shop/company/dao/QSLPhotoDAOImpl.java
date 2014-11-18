package shop.company.dao;

import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.company.entity.QSLPhoto;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class QSLPhotoDAOImpl extends AbstractDAO<QSLPhoto> implements
		BaseDAO<QSLPhoto>, QSLPhotoDAO {

	public QSLPhotoDAOImpl() {
		super(QSLPhoto.class);
	}

    public void makeCondition(QSLPhoto o, StringBuffer condition,
                                        List args) {
		if (isNotNull(o) ){

			if (isNotNull(o.getPicPath())) {
				condition.append(" and t.picPath=? ");
				args.add(o.getPicPath());
			}
			if (isNotNull(o.getCategoryId())) {
				condition.append(" and t.categoryId=? ");
				args.add(o.getCategoryId());
			}
			if (isNotNull(o.getLineNo())) {
				condition.append(" and t.lineNo=? ");
				args.add(o.getLineNo());
			}
			if (isNotNull(o.getSizeType())) {
				condition.append(" and t.sizeType=? ");
				args.add(o.getSizeType());
			}
		
		}
	}
	
	@Override
	public List<QSLPhoto> findAllBySearchWithPage(QSLPhoto o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from QSLPhoto t where 1=1 ";
		String countQl = "select count(*) from QSLPhoto t where 1=1";

		makeCondition(o,condition,args);

		List<QSLPhoto> instance = ifindByQLWithPage(hql + condition, countQl
				+ condition, args.toArray(new Object[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.id,o.id from QSLPhoto o where o.id like ?";
		String hql2 = "select count(*) from QSLPhoto o where o.id like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<QSLPhoto> findAllByIds(java.io.Serializable[] cids){
		String hql = "from QSLPhoto o where o.id in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<QSLPhoto> instance = ifindByQL(hql ,cids);		
		return instance;
	}
	
	@Override
	public Double getTotleBySearchField(QSLPhoto o, String fieldName,Object ... addArgs) {
		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select sum(t."+fieldName+") from QSLPhoto t where 1=1 ";

		for (int i = 0; i < addArgs.length; i=i+2) {		
			condition.append(" and t."+addArgs[i]+" = ? ");
			args.add(addArgs[i+1]);
		}
		
		makeCondition(o, condition, args);
		Double totle = (Double)((Object)igetByQL(hql + condition, args.toArray(new Object[0])));

		return totle;
	}
}
