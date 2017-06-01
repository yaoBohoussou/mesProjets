package ejbSession;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import dao.AdresseDaoInterface;
import metier.Adresse;

@Stateless
@Remote(RemoteEjbAdresse.class)
public class RemoteAdresse implements RemoteEjbAdresse
{
	@EJB(name="AdresseDaoInterface")
	private AdresseDaoInterface<Adresse> dao;
	
	
	@Override
	public void addAdresse(Adresse adr) 
	{
		dao.toString();
		dao.persist(adr);
	}
	
	public int maxId()
	{
		return dao.selectMaxId("Adresse");
	}

}
