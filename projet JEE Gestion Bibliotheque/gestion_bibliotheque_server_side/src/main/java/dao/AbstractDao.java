package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractDao<T extends Serializable> implements DaoInterface<T>
{
	protected Class<T> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;
	
	public AbstractDao() 
	{
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void persist(T entity) 
	{
		entityManager.persist(entity);
		
	}
	public List select (String hql)
	{
		return entityManager.createQuery(hql).getResultList();
	}
	
	public 	List select (Map parameters ,String table)
	{
		String hql = "FROM "+ table + " E WHERE ";
		Iterator<String> keySetIterator = parameters.keySet().iterator(); 
		int i=0;
		while(keySetIterator.hasNext())
		{ 
			String key = keySetIterator.next();
			if (i==0)
			{
				if(parameters.get(key).getClass() == String.class)
					hql = hql+key + "= \'" + parameters.get(key)+"\' ";
				else
					hql = hql+key + "= " + parameters.get(key)+" ";
			}
			else
			{
				if(parameters.get(key).getClass() == String.class)
					hql = hql + " and "+key + "= \'" + parameters.get(key)+"\' ";
				else
					hql = hql + " and "+key + "= " + parameters.get(key)+" ";		
			}
			i+=1;
		}
		
		return entityManager.createQuery(hql).getResultList();
	}
	
	public void executerUpdate(int id, Map parameters,String table ,String type)
	{
		if(type.equals("UPDATE"))
		{
			String hql = "UPDATE "+table+" set ";
			int i=0;
	
			Iterator<String> keySetIterator = parameters.keySet().iterator(); 
			while(keySetIterator.hasNext())
			{ 
				String key = keySetIterator.next();
				if (i==0)
					hql = hql+key + "= :" + key+" ";
				else
					hql = hql + ","+key + "= :" + key+" ";
				i+=1;
			}
			
			hql = hql + " WHERE id = :ID";
			System.out.println(hql);
			
			Query up = entityManager.createQuery(hql);
			keySetIterator = parameters.keySet().iterator(); 
			while(keySetIterator.hasNext())
			{ 
				String key = keySetIterator.next();
				up.setParameter(key, parameters.get(key));
			}
			up.setParameter("ID", id);
			up.executeUpdate();
		}
		else
		{
			String hql = "DELETE FROM "+table+" Where ";
			int i=0;
			
			Iterator<String> keySetIterator = parameters.keySet().iterator(); 
			while(keySetIterator.hasNext())
			{ 
				String key = keySetIterator.next();
				if (i==0)
					hql = hql+key + "= :" + key+" ";
				else
					hql = hql +" and "+key + "= :" + key+" ";
				i+=1;
			}
			Query up = entityManager.createQuery(hql);
			keySetIterator = parameters.keySet().iterator(); 
			while(keySetIterator.hasNext())
			{ 
				String key = keySetIterator.next();
				up.setParameter(key, parameters.get(key));
			}
			up.executeUpdate();
			
		}
	}

	public 	int selectMaxId(String table)
	{
		int max = 1;
		String hql = "select max(id) from "+table;
		List result = entityManager.createQuery(hql).getResultList();
		if(result.isEmpty())
			return max;
		else
			return (int)result.get(0);
	}
	
}
