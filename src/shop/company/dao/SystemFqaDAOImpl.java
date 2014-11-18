package shop.company.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.common.util.ArraysUtils;
import shop.company.entity.SystemFqa;

@Repository
@AutoDAO
public class SystemFqaDAOImpl extends AbstractDAO<SystemFqa> implements
		BaseDAO<SystemFqa>, SystemFqaDAO {

	public SystemFqaDAOImpl() {
		super(SystemFqa.class);
	}
	
	@Override
	public List<SystemFqa> findAllBySearchWithPage(SystemFqa o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from SystemFqa t where 1=1 ";
		String countQl = "select count(*) from SystemFqa t where 1=1";
		String orderBy = " order by t.createTime desc ";
		if (isNotNull(o) ){

			if (isNotNull(o.getCreateTime())) {
				condition.append(" and t.createTime>=? ");
				args.add(o.getCreateTime());
			}
			if (isNotNull(o.getCreateTime2())) {
				condition.append(" and t.createTime<=? ");
				args.add(o.getCreateTime2());
			}
			
			if (isNotNull(o.getFqaQuestionType())) {
				condition.append(" and t.fqaQuestionType=? ");
				args.add(o.getFqaQuestionType());
			}
			if (isNotNull(o.getFqaId())) {
				condition.append(" and t.fqaId=? ");
				args.add(o.getFqaId());
			}
			
			if (isNotNull(o.getFqaQuestion())) {
				condition.append(" and t.fqaQuestion like ? ");
				args.add("%"+o.getFqaQuestion()+"%");
			}
			
			if (isNotNull(o.getFqaAnswer())) {
				condition.append(" and t.fqaAnswer like ? ");
				args.add("%"+o.getFqaAnswer()+"%");
			}
			
			if (isNotNull(o.getUserLevel())) {
				condition.append(" and (t.userLevel=? or t.userLevel = ?) ");
				args.add(o.getUserLevel());
				args.add("");
			}
			if (isNotNull(o.getRewardStatus())) {
				condition.append(" and (t.rewardStatus=? or t.rewardStatus=?) ");
				args.add(o.getRewardStatus());
				args.add("");
			}
			if (isNotNull(o.getBonusLevel())) {
				condition.append(" and (t.bonusLevel=? or t.bonusLevel=?)");
				args.add(o.getBonusLevel());
				args.add("");				
			}
			if (isNotNull(o.getRepeatConsumeLevel())) {
				condition.append(" and (t.repeatConsumeLevel=? or t.repeatConsumeLevel=?) ");
				args.add(o.getRepeatConsumeLevel());
				args.add("");
			}
		
		}
		List<SystemFqa> instance = ifindByQLWithPage(hql + condition+orderBy, countQl
				+ condition, args.toArray(new String[0]));
		
		return instance;
	}
	
	@Override
	public Map findAllByNameWithIdAndName(String name){
		String hql1 = "select o.fqaId,o.fqaId from SystemFqa o where o.fqaId like ?";
		String hql2 = "select count(*) from SystemFqa o where o.fqaId like ?";
		List instance = ifindByQLWithPage(hql1,hql2,"%"+name.replace('.','_')+"%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>)instance);
	}
	
	@Override
	public List<SystemFqa> findAllByIds(java.io.Serializable[] cids){
		String hql = "from SystemFqa o where o.fqaId in "
			+ Arrays.toString(cids).replaceAll("\\[","(")
									.replaceAll("\\]",")")
									.replaceAll("[^\\,\\(\\)]+","?");
		List<SystemFqa> instance = ifindByQL(hql ,cids);		
		return instance;
	}

	public String findFTree(StringBuffer sb, String mid) {
		return null;
	}

	public String findATree(StringBuffer sb, String mid) {
		return null;
	}
}
