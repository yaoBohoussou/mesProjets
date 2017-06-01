package gestion_bibliotheque_client_V1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejbSession.RemoteEjbAdresse;
import ejbSession.RemoteEjbClientInterface;
import ejbSession.RemoteEjbEmpruntInterface;
import ejbSession.RemoteEjbLivreInterface;
import metier.Adresse;
import metier.Client;
import metier.Docautre;
import metier.Emprunt;
import metier.Livre;
import metier.Roman;

@ManagedBean(name="beanEmploye")
@SessionScoped
public class ManagedBeanEmploye 
{
	
	private List<Client> listeclients;
	private List<Roman> listelivres;
	private List<Docautre> listedocautre;
	private List<Emprunt> listeemprunts;
	
	private List<String> listdesmods = new ArrayList<String>();
	
	private int id;
	private String nom;
	private String prenoms;
	private Date dateNaissance;
	private String mail;
	private String cni;
	private Adresse adr;
	
	private int idroman;//C'est un idLivre en fait dans la sémantique
	private int idclient;
	private int duree;
	
	private int idancienclient;
	private int idlivre2;
	private int duree2;
	
	private String nomemploye;
	
	public ManagedBeanEmploye()
	{
		this.nomemploye = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}
	
	public void setNomemploye(String nom)
	{
		this.nomemploye = nom;
	}
	
	public String getNomemploye()
	{
		this.nomemploye = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		return this.nomemploye;
	}
	
	public void setListdesmods(List<String> listdesmods)
	{
		this.listdesmods = listdesmods;
	}
	
	public List<String> getListdesmods()
	{
		return this.listdesmods ;
	}
	
	public void setDuree2(int duree)
	{
		this.duree2 = duree;
	}
	
	public int getDuree2()
	{
		return this.duree2;
	}
	
	public void setIdlivre2(int id)
	{
		this.idlivre2 = id;
	}
	
	public int getIdlivre2()
	{
		return this.idlivre2;
	}
	
	public void setIdancienclient(int id)
	{
		this.idancienclient = id;
	}
	
	public int getIdancienclient()
	{
		return this.idancienclient;
	}
	
	
	public void setDuree(int duree)
	{
		this.duree = duree;
	}
	
	public int getDuree()
	{
		return this.duree;
	}
	
	public void setIdroman(int id)
	{
		this.idroman = id;
	}
	
	public void setIdclient(int id)
	{
		this.idclient = id;
	}
	
	public int getIdroman()
	{
		return this.idroman;
	}
	
	public int getIdclient()
	{
		return this.idclient;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public void setPrenoms(String pren)
	{
		this.prenoms = pren;
	}
	
	public void setDateNaissance(Date dat)
	{
		this.dateNaissance = dat;
	}
	
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	
	public void setCni(String cni)
	{
		this.cni = cni;
	}
	
	public void setAdr(Adresse adr)
	{
		this.adr = adr;
	}
	
	public int getId()
	{
		return this.id ;
	}
	
	public String getNom()
	{
		return this.nom ;
	}
	
	public String getPrenoms()
	{
		return this.prenoms ;
	}
	
	public Date getDateNaissance()
	{
		return this.dateNaissance;
	}
	
	public String getMail()
	{
		return this.mail ;
	}
	
	public String getCni()
	{
		return this.cni ;
	}
	
	public Adresse getAdr()
	{
		return this.adr ;
	}

	
	public void setListeclients(List<Client> listeclients)
	{
		this.listeclients = listeclients;
	}
	
	public void setListelivres(List<Roman> listelivres)
	{
		this.listelivres = listelivres;
	}
	
	public void setListedocautre(List<Docautre> listedocautre)
	{
		this.listedocautre = listedocautre;
	}
	
	public void setListeemprunts(List<Emprunt> listeemprunt)
	{
		this.listeemprunts = listeemprunt;
	}

	
	public List<Client> getListeclients() throws NamingException
	{
		recuperer_liste_client();
		return this.listeclients ;
	}
	
	public List<Roman> getListelivres() throws NamingException
	{
		recuperer_liste_romans();
		return this.listelivres ;
	}
	
	
	public List<Docautre> getListedocautre() throws NamingException
	{
		recuperer_liste_docsautres();
		return this.listedocautre ;
	}
	//List<Emprunt> listeemprunts
	public List<Emprunt> getListeemprunts() throws NamingException
	{
		recuperer_liste_Emprunts();
		return this.listeemprunts;
	}
	
	private String recuperer_liste_client() throws NamingException
	{
		final RemoteEjbClientInterface statelessClientPersistentBeanRemote = lookupRemoteRemoteEjbClient();
		this.listeclients = statelessClientPersistentBeanRemote.getListeClients();
		return "success";
	}
	
	private String recuperer_liste_romans() throws NamingException
	{
		final RemoteEjbLivreInterface statelessLivrePersistentBeanRemote = lookupRemoteRemoteEjbLivre();
		this.listelivres = (List<Roman>)statelessLivrePersistentBeanRemote.getListeRomans();
		return "succes";
	}
	
	private String recuperer_liste_docsautres() throws NamingException
	{
		final RemoteEjbLivreInterface statelessLivrePersistentBeanRemote = lookupRemoteRemoteEjbLivre();
		this. listedocautre = (List<Docautre>)statelessLivrePersistentBeanRemote.getListeDocsAutres();
		return "succes";
	}
	
	private String recuperer_liste_Emprunts() throws NamingException
	{
		final RemoteEjbEmpruntInterface statelessEmpruntPersistentBeanRemote = lookupRemoteRemoteEjbEmprunt();
		this.listeemprunts = (List<Emprunt>)statelessEmpruntPersistentBeanRemote.getListeEmprunts();
		return "succes";
	}
	
	public String enregistrer_emprunt_nouveau_client () throws NamingException
	{
		final RemoteEjbLivreInterface statelessLivrePersistentBeanRemote = lookupRemoteRemoteEjbLivre();
		final RemoteEjbClientInterface statelessClientPersistentBeanRemote = lookupRemoteRemoteEjbClient();
		final RemoteEjbAdresse  statelessAdressePersistentBeanRemote = lookupRemoteRemoteEjbAdresse();
		final RemoteEjbEmpruntInterface statelessEmpruntPersistentBeanRemote = lookupRemoteRemoteEjbEmprunt();


		int id_adresse, id_client, id_emprunt;

		Client clt = new Client();
		Livre livre;
		Emprunt emprunt = new Emprunt();
		
		id_adresse = statelessAdressePersistentBeanRemote.maxId()+1;
		id_client = statelessClientPersistentBeanRemote.maxId()+1;
		id_emprunt = statelessEmpruntPersistentBeanRemote.maxId()+1;
		
		this.adr.setId(id_adresse);
		clt.setId(id_client);
		clt.setNom(this.nom);
		clt.setPrenoms(this.prenoms);
		clt.setDateNaissance(this.dateNaissance);
		clt.setAdr(this.adr);
		clt.setMail(this.mail);
		clt.setCni(this.cni);
		
		Map parameter = new HashMap();
		parameter.put("id", this.idroman);
		List resLivres = statelessLivrePersistentBeanRemote.getLivresWithParameter(parameter);
		if(resLivres.size() !=0)
			livre = (Livre)resLivres.get(0);
		else
			return "Failed";
		
		emprunt.setId(id_emprunt);
		emprunt.setDuree(this.duree);
		emprunt.setDateemprunt(new Date());
		emprunt.setClient(clt);
		emprunt.setLivre(livre);
		
		statelessAdressePersistentBeanRemote.addAdresse(clt.getAdr());
		statelessClientPersistentBeanRemote.addClient(clt);
		statelessEmpruntPersistentBeanRemote.addEmprunt(emprunt);	
		
		resetvalues();
		return "success";
	}
	
	public String enregistrer_emprunt_ancien_client() throws NamingException
	{
		final RemoteEjbLivreInterface statelessLivrePersistentBeanRemote = lookupRemoteRemoteEjbLivre();
		final RemoteEjbClientInterface statelessClientPersistentBeanRemote = lookupRemoteRemoteEjbClient();
		final RemoteEjbEmpruntInterface statelessEmpruntPersistentBeanRemote = lookupRemoteRemoteEjbEmprunt();
		
		Client clt;
		Livre livre;
		Emprunt emprunt;
		int id_emprunt = statelessEmpruntPersistentBeanRemote.maxId()+1;
		
		Map parameter = new HashMap();
		parameter.put("id", this.idlivre2);
		List resLivres = statelessLivrePersistentBeanRemote.getLivresWithParameter(parameter);
		if(resLivres.size() !=0)
			livre = (Livre)resLivres.get(0);
		else
			return "Failed";
		
		Map parameter1 = new HashMap();
		parameter1.put("id", this.idancienclient);
		List resClient = statelessClientPersistentBeanRemote.getClientwithParam(parameter1);
		if(resClient.size() !=0)
			clt = (Client)resClient.get(0);
		else
			return "Failed";
		
		emprunt = new Emprunt();
		
		emprunt.setId(id_emprunt);
		emprunt.setDuree(this.duree2);
		emprunt.setDateemprunt(new Date());
		emprunt.setClient(clt);
		emprunt.setLivre(livre);
		 
		statelessEmpruntPersistentBeanRemote.addEmprunt(emprunt);
		
		 resetvalues();
		
		return "success";
	}
	
	
	public String modifier_client() throws NamingException
	{
		Map parameters = new HashMap();
		Iterator<String> iterator = this.listdesmods.iterator(); 
		String valeurCourante;
		while(iterator.hasNext())
		{ 
			valeurCourante = iterator.next();
			if(valeurCourante.equals("1"))
			{
				parameters.put("nomClt", this.nom);
			}
			else if(valeurCourante.equals("2"))
			{
				parameters.put("prenomsClt", this.prenoms);
			}
			else if(valeurCourante.equals("3"))
			{
				parameters.put("dateNaissance", this.dateNaissance);
			}
			else if(valeurCourante.equals("4"))
			{
				//parameters.put("Adresse", this.nom);
			}
			else if(valeurCourante.equals("5"))
			{
				parameters.put("mail", this.mail);
			}
			else if(valeurCourante.equals("6"))
			{
				parameters.put("cni", this.cni);
			}
		}
		
		
		final RemoteEjbClientInterface statelessClientPersistentBeanRemote = lookupRemoteRemoteEjbClient();
		statelessClientPersistentBeanRemote.updateClient(this.idancienclient, parameters);
		
		 resetvalues();
		
		return "success";
	}
	
	private void resetvalues()
	{
		this.listdesmods = new ArrayList<String>();
		
		this.id =0;
		this.nom = null;
		this.prenoms= null;
		this.dateNaissance= null;
		this.mail= null;
		this.cni= null;
		this.adr= null;
		
		this.idroman=0;
		this.idclient=0;
		this.duree=0;
		
		this.idancienclient=0;
		this.idlivre2=0;
		this.duree2=0;
		
	}
	
	private  RemoteEjbClientInterface lookupRemoteRemoteEjbClient() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        final Context context = new InitialContext(jndiProperties);
        // let's do the lookup
        return (RemoteEjbClientInterface) context.lookup("ejb:/gestion_bibliotheque_server_side/RemoteEjbClient!"+ RemoteEjbClientInterface.class.getName());
    }
	
	private  RemoteEjbLivreInterface lookupRemoteRemoteEjbLivre() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        final Context context = new InitialContext(jndiProperties);
        // let's do the lookup
        return (RemoteEjbLivreInterface) context.lookup("ejb:/gestion_bibliotheque_server_side/RemoteEjbLivre!"+ RemoteEjbLivreInterface.class.getName());
    }
	
	private static  RemoteEjbEmpruntInterface lookupRemoteRemoteEjbEmprunt() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        final Context context = new InitialContext(jndiProperties);
        // let's do the lookup
        return (RemoteEjbEmpruntInterface) context.lookup("ejb:/gestion_bibliotheque_server_side/RemoteEjbEmprunt!"+ RemoteEjbEmpruntInterface.class.getName());
    }
	
	private static RemoteEjbAdresse lookupRemoteRemoteEjbAdresse() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        final Context context = new InitialContext(jndiProperties);
        // let's do the lookup
        return (RemoteEjbAdresse) context.lookup("ejb:/gestion_bibliotheque_server_side/RemoteAdresse!"+ RemoteEjbAdresse.class.getName());
    }
	
	
	public String deconnexion()
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
		return "logout";
	}
	
	
	
}
