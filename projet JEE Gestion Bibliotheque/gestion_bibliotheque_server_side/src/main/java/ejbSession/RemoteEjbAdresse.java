package ejbSession;

import metier.Adresse;

public interface RemoteEjbAdresse 
{
	void addAdresse(Adresse adr);
	int maxId();
}
