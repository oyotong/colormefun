package shop.company.dao;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import shop.common.context.ApplicationContext;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import shop.common.exception.DAOException;
import shop.common.util.ArraysUtils;
import shop.company.entity.SystemDbBackup;

@Repository
@AutoDAO
public class SystemDbBackupDAOImpl extends AbstractDAO<SystemDbBackup>
		implements BaseDAO<SystemDbBackup>, SystemDbBackupDAO {

	private final String DB_PATH;
	private String host;
	private String user;
	private String pwd;

	public SystemDbBackupDAOImpl() {
		super(SystemDbBackup.class);
		DB_PATH = ApplicationContext.getContext().getApplication()
				.getRealPath("/WEB-INF/db");
		host = ApplicationContext.getContext().getConfiguration("Jdbc.Host");
		user = ApplicationContext.getContext()
				.getConfiguration("Jdbc.UserName");
		pwd = ApplicationContext.getContext().getConfiguration("Jdbc.Password");
	}

	@Override
	public List<SystemDbBackup> findAllBySearchWithPage(SystemDbBackup o) {

		List args = new ArrayList();
		StringBuffer condition = new StringBuffer(500);

		String hql = "select t from SystemDbBackup t where 1=1 ";
		String countQl = "select count(*) from SystemDbBackup t where 1=1";
		String orderBy = " order by t.bkTime desc ";
		if (isNotNull(o)) {

			if (isNotNull(o.getBkTitle())) {
				condition.append(" and t.bkTitle=? ");
				args.add(o.getBkTitle());
			}
			if (isNotNull(o.getBkId())) {
				condition.append(" and t.bkId=? ");
				args.add(o.getBkId());
			}
			if (isNotNull(o.getBkComment())) {
				condition.append(" and t.bkComment=? ");
				args.add(o.getBkComment());
			}
			if (isNotNull(o.getBkTime())) {
				condition.append(" and t.bkTime=? ");
				args.add(o.getBkTime());
			}

		}

		List<SystemDbBackup> instance = ifindByQLWithPage(hql + condition
				+ orderBy, countQl + condition, args.toArray(new String[0]));

		return instance;
	}

	@Override
	public Map findAllByNameWithIdAndName(String name) {
		String hql1 = "select o.bkId,o.bkTitle from SystemDbBackup o where o.bkTitle like ?";
		String hql2 = "select count(*) from SystemDbBackup o where o.bkTitle like ?";
		List instance = ifindByQLWithPage(hql1, hql2,
				"%" + name.replace('.', '_') + "%");
		return ArraysUtils.arrayPairListtoMap((List<Object[]>) instance);
	}

	@Override
	public List<SystemDbBackup> findAllByIds(java.io.Serializable[] cids) {
		String hql = "from SystemDbBackup o where o.bkId in "
				+ Arrays.toString(cids).replaceAll("\\[", "(")
						.replaceAll("\\]", ")")
						.replaceAll("[^\\,\\(\\)]+", "?");
		List<SystemDbBackup> instance = ifindByQL(hql, cids);
		return instance;
	}

	public void recoveryAll() {

		String path = ApplicationContext.getContext().getApplication()
				.getRealPath(DB_PATH + "/BaseBackup.dat");
		String cmd = "mysql -h " + host + " -u" + user + " -p" + pwd
				+ " doc < " + path;

		File file = new File(path);
		if (!file.exists()) {
			throw new DAOException("无效的还原文件名称：" + file.getName());
		}

		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DAOException("运行命令失败：" + cmd);
		}
	}

	public void recovery(int id) {
		SystemDbBackup dbb = get(id);
		File file = new File(DB_PATH + "/" + dbb.getBkPath());
		String cmd = "mysql.exe -h " + host + " -u" + user + " -p" + pwd
				+ " doc < " + file.getAbsolutePath();

		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DAOException("运行命令失败：" + cmd);
		}
	}

	public void backup() {

		String fileName = DB_PATH + "/DBBackup"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
				+ ".dat";

		File file = new File(fileName);
		file.getParentFile().mkdirs();

		String cmd = "mysqldump.exe -h " + host + " -u" + user + " -p" + pwd
				+ " doc >> " + file.getAbsolutePath();

		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DAOException("运行命令失败：" + cmd);
		}

		try {
			Runtime.getRuntime().exec(cmd);

			SystemDbBackup dbb = new SystemDbBackup(new Date(), "文件名："
					+ file.getName(), file.getName(), "");
			persist(dbb);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DAOException("运行命令失败：" + cmd);
		}

	}
}
