package ejbSession;

import java.util.List;
import java.util.Map;

import metier.Emprunt;


public interface RemoteEjbEmpruntInterface 
{
	void addEmprunt(Emprunt emprunt);
	List getListeEmprunts();
	List getListeEmprunts(Map parameters);
	void updateEmprunt( int id, Map parameters);
	int maxId();
}
