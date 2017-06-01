package ejbSession;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import dao.LivreDaoInterface;
import metier.Livre;

@Stateless
@Remote(RemoteEjbLivreInterface.class)
public class RemoteEjbLivre implements RemoteEjbLivreInterface
{
	@EJB(name="LivreDaoInterface")
	private LivreDaoInterface<Livre> dao;

	@Override
	public void addLivre(Livre livre) 
	{
		dao.toString();
		dao.persist(livre);
	}

	@Override
	public List getListeLivres() 
	{
		List entr;
		String hql = "FROM Livre";
		return dao.select(hql);
	}

	@Override
	public List getListeRomans()
	{
		List entr;
		String val = "ROMAN";
		String hql = "FROM Livre E WHERE typeLivre = \'"+val+"\'" ;
		return dao.select(hql);
	}

	@Override
	public List getListeDocsAutres() 
	{
		List entr;
		String val = "AUTRE";
		String hql = "FROM Livre E WHERE typeLivre = \'"+val+"\'" ;
		return dao.select(hql);
	}
	

	@Override
	public List getLivresWithParameter(Map parameters) 
	{
		String table = "Livre";
		return dao.select(parameters, table);
	}


	@Override
	public void updateLivre(int ID, Map parameters) 
	{
		String table = "Livre";
		String type = "UPDATE";
		dao.executerUpdate(ID, parameters, table, type);
		
	}

	@Override
	public void deleteLivre(Map parameters) 
	{
		String table = "Livre";
		String type = "DELETE";
		dao.executerUpdate(0, parameters, table, type);
		
	}
	
	public int maxId()
	{
		return dao.selectMaxId("Livre");
	}

}
