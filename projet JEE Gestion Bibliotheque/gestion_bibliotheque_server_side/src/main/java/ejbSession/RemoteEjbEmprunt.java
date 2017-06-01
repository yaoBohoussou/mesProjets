package ejbSession;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import dao.EmpruntDaoInterface;
import metier.Emprunt;

@Stateless
@Remote(RemoteEjbEmpruntInterface.class)
public class RemoteEjbEmprunt implements RemoteEjbEmpruntInterface
{
	@EJB(name="EmpruntDaoInterface")
	private EmpruntDaoInterface<Emprunt> dao;

	@Override
	public void addEmprunt(Emprunt emprunt) 
	{
		dao.toString();
		dao.persist(emprunt);
	}

	@Override
	public List getListeEmprunts() 
	{
		List entr;
		String hql = "FROM Emprunt";
		return dao.select(hql);
	}

	@Override
	public List getListeEmprunts(Map parameters) 
	{
		String table = "Emprunt";
		return dao.select(parameters, table);
	}

	@Override
	public void updateEmprunt(int id, Map parameters) 
	{
		String type = "UPDATE";
		String table = "Emprunt";
		dao.executerUpdate(id, parameters,table,type);
	}
	
	public int maxId()
	{
		return dao.selectMaxId("Emprunt");
	}

}
