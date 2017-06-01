package ejbSession;

import java.util.List;
import java.util.Map;
import metier.Livre;

public interface RemoteEjbLivreInterface 
{
	void addLivre(Livre livre);
	List getListeLivres();
	List getListeRomans();
	List getListeDocsAutres();

	List getLivresWithParameter(Map parameter);
	void updateLivre(int ID, Map parameters);
	void deleteLivre(Map parameters);
	int maxId();
}
