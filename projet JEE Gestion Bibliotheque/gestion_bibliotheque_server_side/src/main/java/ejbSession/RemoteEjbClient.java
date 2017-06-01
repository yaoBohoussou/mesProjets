package ejbSession;


import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import dao.ClientDaoInterface;
import metier.Client;

@Stateless
@Remote(RemoteEjbClientInterface.class)
public class RemoteEjbClient implements RemoteEjbClientInterface
{
	@EJB(name="ClientDaoInterface")
	private ClientDaoInterface<Client> dao;

	@Override
	public void addClient(Client clt) 
	{
		dao.toString();
		dao.persist(clt);
	}

	@Override
	public List getListeClients() 
	{
		List entr;
		String hql = "FROM Client";
		return dao.select(hql);
	}

	@Override
	public List getListeEmploye() 
	{
		List entr;
		String val = "EMPLOYE";
		String hql = "FROM Client E WHERE typeClt = \'"+val+"\'" ;
		return dao.select(hql);
	}

	@Override
	public Client getClientWithID(int id) 
	{
		List entr =null;
		String hql = "FROM Client E WHERE E.id = "+id ;
		entr = dao.select(hql);
		return (Client)entr.get(0);
	}

	@Override
	public Client getEmployeWithID(int id) 
	{
		List entr;
		String val = "EMPLOYE";
		String hql = "FROM Client E WHERE E.id = "+id+" and typeClt = \'"+val+"\'" ;
		entr = dao.select(hql);
		return (Client)entr.get(0);
	}
	
	public List getClientwithParam(Map parameters)
	{
		String table = "Client";
		return dao.select(parameters, table);
	}

	@Override
	public void updateClient(int id, Map parameters) 
	{	
		String type = "UPDATE";
		String table = "Client";
		dao.executerUpdate(id, parameters,table,type);
	}

	@Override
	public void deleteClient(Map parameters) 
	{
		String type = "DELETE";
		String table = "Client";
		dao.executerUpdate(0, parameters,table,type);

	}
	
	public int maxId()
	{
		return dao.selectMaxId("Client");
	}

	/*@Override
	public void deleteEmploye(Map parameters) 
	{
		
	}*/
	
}
