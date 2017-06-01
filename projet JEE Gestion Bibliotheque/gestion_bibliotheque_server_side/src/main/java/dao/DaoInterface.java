package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface DaoInterface <T extends Serializable>
{
	void persist(T entity);
	List select (String hql);
	List select (Map parameters ,String table);
	void executerUpdate(int id, Map parameters,String table , String type);
	int selectMaxId(String table);
}
