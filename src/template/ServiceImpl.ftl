package ${servicePackage};

import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${daoPackage}.${pojo.ClassName}DAO;
import ${entityPackage}.${pojo.ClassName};

@Service
@Transactional
public class ${pojo.ClassName}ServiceImpl implements ${pojo.ClassName}Service {

	@Autowired()
	private ${pojo.ClassName}DAO ${pojo.VarName}DAO;

	public ${pojo.ClassName}DAO get${pojo.ClassName}DAO() {
		return ${pojo.VarName}DAO;
	}

	public void set${pojo.ClassName}DAO(${pojo.ClassName}DAO ${pojo.VarName}DAO) {
		this.${pojo.VarName}DAO = ${pojo.VarName}DAO;
	}

	@Override
	public void delete${pojo.ClassName}(${pojo.ClassName} o) {
		${pojo.VarName}DAO.remove(o);
	}
	
	@Override
	public void delete${pojo.ClassName}ByIds(${pojo.IdClassName}[] ids){
		${pojo.VarName}DAO.removeByIds(ids);
	}

	@Override
	@Transactional(readOnly = true)
	public List<${pojo.ClassName}> findAll${pojo.ClassName}() {
		return ${pojo.VarName}DAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<${pojo.ClassName}> findAll${pojo.ClassName}WithPage(){
		return ${pojo.VarName}DAO.findAllWithPage();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<${pojo.ClassName}> findAll${pojo.ClassName}BySearchWithPage(${pojo.ClassName} o){
		return ${pojo.VarName}DAO.findAllBySearchWithPage(o);
	}

	@Override
	@Transactional(readOnly = true)
	public ${pojo.ClassName} get${pojo.ClassName}ById(Serializable id) {
		return ${pojo.VarName}DAO.get(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map findAll${pojo.ClassName}ByNameWithIdAndName(String name) {
		return ${pojo.VarName}DAO.findAllByNameWithIdAndName(name);
	}

	@Override
	public void save${pojo.ClassName}(${pojo.ClassName} o) {
		${pojo.VarName}DAO.persist(o);
	}

	@Override
	public void update${pojo.ClassName}(${pojo.ClassName} o) {
		${pojo.VarName}DAO.merge(o);
	}
	
	@Override
	public List<${pojo.ClassName}> findAll${pojo.ClassName}ByIds(${pojo.IdClassName}[] cids) {
		return ${pojo.VarName}DAO.findAllByIds(cids);
	}

}
