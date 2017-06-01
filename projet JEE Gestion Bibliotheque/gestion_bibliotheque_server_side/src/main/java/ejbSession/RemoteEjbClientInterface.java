package ejbSession;

import java.util.List;
import java.util.Map;

import metier.Client;

public interface RemoteEjbClientInterface 
{
	void addClient(Client clt);
	List getListeClients();
	List getListeEmploye();
	Client getClientWithID(int id);
	Client getEmployeWithID(int id);
	public List getClientwithParam(Map parameters);
	void updateClient(int ID, Map parameters);
	void deleteClient(Map parameters);
	//void deleteEmploye(Map parameters);
	int maxId();
	

}
