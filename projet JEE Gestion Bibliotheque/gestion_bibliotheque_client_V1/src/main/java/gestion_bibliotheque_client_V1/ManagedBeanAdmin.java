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
import ejbSession.RemoteEjbLivreInterface;
import metier.Adresse;
import metier.Client;
import metier.Docautre;
import metier.Employe;
import metier.Livre;
import metier.Roman;

@ManagedBean(name="beanAdmin")
@SessionScoped
public class ManagedBeanAdmin 
{
	List<Employe> listeemploye;
	List<Roman> listeroman;
	List<Docautre> listedocautre;
	private List<String> listdesmods = new ArrayList<String>();
	private String typelivre ;

	
	private String nomadmin;
	
	private int id;
	private String nom;
	private String prenoms;
	private Date dateNaissance;
	private String mail;
	private String cni;
	private Adresse adr;
	private Date dateembauche;
	private double salaire;
	private String poste;
	private String login;
	private String mdp;
	
	

	private String edition;
	private int nombrestock;
	private String titre;
	private String auteur;
	private Date dateparution;
	private String style;
	private String typedoc;
	private String description;
	
	public ManagedBeanAdmin()
	{
		this.nomadmin = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
	}
	
	public void setListdesmods(List<String> listdesmods)
	{
		this.listdesmods = listdesmods;
	}
	
	public List<String> getListdesmods()
	{
		return this.listdesmods ;
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
	
	public void setDateembauche (Date dat)
	{
		this.dateembauche = dat;
	}
	

	public void setSalaire(double sal)
	{
		this.salaire = sal;
	}
	
	public void setPoste(String poste)
	{
		this.poste = poste;
	}
	
	public void setMdp(String mdp)
	{
		this.mdp = mdp;
	}
	
	public void setLogin(String log)
	{
		this.login = log;
	}
	
	public Date getDateembauche ()
	{
		return this.dateembauche ;
	}
	
	
	public double getSalaire()
	{
		return this.salaire ;
	}
	
	public String getPoste()
	{
		return this.poste ;
	}
	
	public String getLogin()
	{
		return this.login ;
	}
	
	public String getMdp()
	{
		return this.mdp ;
	}

	
	public void setListeemploye(List<Employe> listeemploye)
	{
		this.listeemploye = listeemploye;
	}

	
	public List<Employe> getListeemploye() throws NamingException
	{
		recuperer_liste_employe();
		return this.listeemploye ;
	}
	
	public void setNomadmin(String nom)
	{
		this.nomadmin = nom;
	}
	
	public String getNomadmin()
	{
		this.nomadmin = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		return this.nomadmin;
	}
	
	public String deconnexion()
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
		return "logout";
	}
	
	public String modeemploye()
	{
		return "mode_emp";
	}
	
	

	
	public void setNombrestock(int nombrestock)
	{
		this.nombrestock = nombrestock;
	}
	
	public void setEdition(String ed)
	{
		this.edition = ed;
	}
	
	
	public int getNombrestock()
	{
		return this.nombrestock ;
	}
	
	public String getEdition()
	{
		return this.edition ;
	}
	
	public void setTitre(String titre)
	{
		this.titre = titre;
	}
	
	public void setAuteur(String aut)
	{
		this.auteur = aut;
	}
	
	public void setStyle(String style)
	{
		this.style = style;
	}
	
	public void setDateparution(Date dateparution)
	{
		this.dateparution = dateparution;
	}
	
	public String getTitre()
	{
		return this.titre;
	}
	
	public String getAuteur()
	{
		return this.auteur;
	}
	
	public String getStyle()
	{
		return this.style;
	}
	
	public Date getDateparution()
	{
		return this.dateparution;
	}
	
	public void setTypedoc(String type)
	{
		this.typedoc = type;
	}

	public String getTypedoc()
	{
		return this.typedoc ;
	}
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}

	public String getDescription()
	{
		return this.description ;
	}
	//
	
	public void setTypelivre(String typelivre)
	{
		this.typelivre = typelivre;
	}
	
	public String getTypelivre()
	{
		return this.typelivre ;
	}
	
	
	public List<Docautre> getListedocautre() throws NamingException
	{
		recuperer_liste_docsautres();
		return this.listedocautre ;
	}
	
	public void setListedocautre(List<Docautre> listedocautre)
	{
		this.listedocautre = listedocautre;
	}
	
	public void setListeroman(List<Roman> listeroman)
	{
		this.listeroman = listeroman;
	}
	
	public List<Roman> getListeroman() throws NamingException
	{
		recuperer_liste_romans();
		return this.listeroman ;
	}
	
	
	private String recuperer_liste_romans() throws NamingException
	{
		final RemoteEjbLivreInterface statelessLivrePersistentBeanRemote = lookupRemoteRemoteEjbLivre();
		this.listeroman = (List<Roman>)statelessLivrePersistentBeanRemote.getListeRomans();
		return "succes";
	}
	
	private String recuperer_liste_docsautres() throws NamingException
	{
		final RemoteEjbLivreInterface statelessLivrePersistentBeanRemote = lookupRemoteRemoteEjbLivre();
		this. listedocautre = (List<Docautre>)statelessLivrePersistentBeanRemote.getListeDocsAutres();
		return "succes";
	}
	
	private String recuperer_liste_employe() throws NamingException
	{
		final RemoteEjbClientInterface statelessClientPersistentBeanRemote = lookupRemoteRemoteEjbClient();
		this.listeemploye = (List<Employe>)statelessClientPersistentBeanRemote.getListeEmploye();
		return "success";
	}
	
	public String enregistrer_employe() throws NamingException
	{
		final RemoteEjbClientInterface statelessClientPersistentBeanRemote = lookupRemoteRemoteEjbClient();
		final RemoteEjbAdresse  statelessAdressePersistentBeanRemote = lookupRemoteRemoteEjbAdresse();
		
		int id_adresse, id_client;

		Employe emp = new Employe();
		
		id_adresse = statelessAdressePersistentBeanRemote.maxId()+1;
		id_client = statelessClientPersistentBeanRemote.maxId()+1;
		
		this.adr.setId(id_adresse);
		emp.setId(id_client);
		emp.setNom(this.nom);
		emp.setPrenoms(this.prenoms);
		emp.setDateNaissance(this.dateNaissance);
		emp.setAdr(this.adr);
		emp.setMail(this.mail);
		emp.setCni(this.cni);
		emp.setDateembauche(this.dateembauche);
		emp.setSalaire(this.salaire);
		emp.setLogin(this.login);
		emp.setMdp(this.mdp);
		emp.setPoste(this.poste);
		
		statelessAdressePersistentBeanRemote.addAdresse(emp.getAdr());
		statelessClientPersistentBeanRemote.addClient((Client)emp);
		
		resetvalues();
		return "success";
	}
	
	public String verrouiller_employe() throws NamingException
	{
		Map parameters = new HashMap();
		parameters.put("login", "supprime");
		parameters.put("mdp", "supprime");
		
		final RemoteEjbClientInterface statelessClientPersistentBeanRemote = lookupRemoteRemoteEjbClient();
		statelessClientPersistentBeanRemote.updateClient(this.id, parameters);
		
		 resetvalues();
		
		return "success";
	}
	
	public String deverrouiller_employe() throws NamingException
	{
		Map parameters = new HashMap();
		parameters.put("login", this.login);
		parameters.put("mdp", this.mdp);
		
		final RemoteEjbClientInterface statelessClientPersistentBeanRemote = lookupRemoteRemoteEjbClient();
		statelessClientPersistentBeanRemote.updateClient(this.id, parameters);
		
		 resetvalues();
		
		return "success";
	}
	
	
	public String modifier_employe() throws NamingException
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
			else if(valeurCourante.equals("7"))
			{
				parameters.put("dateEmbauche", this.dateembauche);
			}
			else if(valeurCourante.equals("8"))
			{
				parameters.put("salaire", this.salaire);
			}
			else if(valeurCourante.equals("9"))
			{
				parameters.put("poste", this.poste);
			}
			else if(valeurCourante.equals("10"))
			{
				parameters.put("login", this.login);
			}
			else if(valeurCourante.equals("11"))
			{
				parameters.put("mdp", this.mdp);
			}
			
		}
		
		final RemoteEjbClientInterface statelessClientPersistentBeanRemote = lookupRemoteRemoteEjbClient();
		statelessClientPersistentBeanRemote.updateClient(this.id, parameters);
		
		 resetvalues();
		
		return "success";
	}
	
	public String ajouter_livre() throws NamingException
	{
  	    final RemoteEjbLivreInterface statelessLivrePersistentBeanRemote =lookupRemoteRemoteEjbLivre();
  	    int idroman = statelessLivrePersistentBeanRemote.maxId()+1;
		if(this.typelivre.equals("1"))
		{
			Roman roman=new Roman();
			roman.setId(idroman);
			roman.setEdition(this.edition);
			roman.setNombrestock(this.nombrestock);
			roman.setAuteur(this.auteur);
			roman.setDateparution(this.dateparution);
			roman.setTitre(this.titre);
			roman.setStyle(this.style);
			
			statelessLivrePersistentBeanRemote.addLivre((Livre)roman);
			
		}
		else
		{
			Docautre doc = new Docautre();
			doc.setId(idroman);
			doc.setEdition(this.edition);
			doc.setNombrestock(this.nombrestock);
			doc.setTypedoc(this.typedoc);
			doc.setDescription(this.description);
			
			statelessLivrePersistentBeanRemote.addLivre((Livre)doc);

		}
		
		resetvalues();
		return "success";
	}
	
	public String modifier_livre() throws NamingException
	{
		Map parameters = new HashMap();
		Iterator<String> iterator = this.listdesmods.iterator(); 
		String valeurCourante;
		if(this.typelivre.equals("1"))
		{
			while(iterator.hasNext())
			{
				valeurCourante = iterator.next();
				if(valeurCourante.equals("1"))
				{
					parameters.put("edition", this.edition);
				}
				else if(valeurCourante.equals("2"))
				{
					parameters.put("nombrestock", this.nombrestock);
				}
				else if(valeurCourante.equals("3"))
				{
					parameters.put("titre", this.titre);
				}
				else if(valeurCourante.equals("4"))
				{
					parameters.put("auteur", this.auteur);
				}
				else if(valeurCourante.equals("5"))
				{
					parameters.put("dateparution", this.dateparution);
				}
				else if(valeurCourante.equals("6"))
				{
					parameters.put("style", this.style);
				}
				
			}
		}
		else
		{
			while(iterator.hasNext())
			{
				valeurCourante = iterator.next();
				if(valeurCourante.equals("1"))
				{
					parameters.put("edition", this.edition);
				}
				else if(valeurCourante.equals("2"))
				{
					parameters.put("nombrestock", this.nombrestock);
				}
				else if(valeurCourante.equals("3"))
				{
					parameters.put("description", this.description);
				}
				else if(valeurCourante.equals("4"))
				{
					parameters.put("typedoc", this.typedoc);
				}
				
			}
		}
		
  	    final RemoteEjbLivreInterface statelessLivrePersistentBeanRemote =lookupRemoteRemoteEjbLivre();
		statelessLivrePersistentBeanRemote.updateLivre(this.id, parameters);
		
		resetvalues();
		return "success";
	}
	
	private static RemoteEjbAdresse lookupRemoteRemoteEjbAdresse() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        final Context context = new InitialContext(jndiProperties);
        // let's do the lookup
        return (RemoteEjbAdresse) context.lookup("ejb:/gestion_bibliotheque_server_side/RemoteAdresse!"+ RemoteEjbAdresse.class.getName());
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
	
	
	private void resetvalues()
	{
		
		this.id =0;
		this.nom = null;
		this.prenoms= null;
		this.dateNaissance= null;
		this.mail= null;
		this.cni= null;
		this.adr= null;
		this.dateembauche = null;
		this.salaire = 0;
		this.poste = null;
		this.login = null;
		this.mdp = null;
		
		this.edition = null;
		this.nombrestock = 0;
		this.titre = null;
		this.auteur = null;
		this.dateparution = null;
		this.style = null;
		this.typedoc = null;
		this.description = null;
		
		this.typelivre = null;
		this.listdesmods = new ArrayList<String>();
		
	}
	
}
