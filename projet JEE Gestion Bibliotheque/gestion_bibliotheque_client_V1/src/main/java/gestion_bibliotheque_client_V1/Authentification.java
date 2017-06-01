package gestion_bibliotheque_client_V1;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejbSession.RemoteEjbClientInterface;
import metier.Employe;

@ManagedBean (name="bean_authentification")
public class Authentification 
{
	private String login;
	private String mdp;
	private String logintype;
	
	public String getLogin()
	{return this.login;}
	
	public void setLogin(String login)
	{this.login = login;}
	
	public String getMdp()
	{
		return this.mdp;
	}
	public void setMdp(String mdp)
	{
		this.mdp = mdp;
	}
	
	public String getVerif() throws NamingException
	{
		if(this.logintype.equals("1") && this.mdp.equals("admin"))
		{
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", login);
			resetValues();
			return "admin";
		}
		else if(this.logintype.equals("2"))
			return "client";
		else 
		{
	        final RemoteEjbClientInterface statelessClientPersistentBeanRemote = lookupRemoteRemoteEjbClient();
			Employe emp ;//= (Employe) statelessClientPersistentBeanRemote.getEmployeWithID(2);
			List lst;
			
			Map parameters = new HashMap();
			parameters.put("login", this.login);
			parameters.put("mdp",this.mdp);
			lst = statelessClientPersistentBeanRemote.getClientwithParam(parameters);
			emp = (Employe)lst.get(0);
			
			if(emp.getLogin().equals(this.login) && emp.getMdp().equals(this.mdp))
			{
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", login);
				resetValues();
				return "employe";
			}
			else
			{
				resetValues();
				return "eror";
			}
		}

	}
	
	public void setLogintype(String loginType)
	{
		this.logintype = loginType;
	}
	public String getLogintype()
	{
		return this.logintype ;
	}
	
	private void resetValues()
	{
		this.login = null;
		this.mdp = null;
		this.logintype = null;
	}
	
	
	private  RemoteEjbClientInterface lookupRemoteRemoteEjbClient() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        
        final Context context = new InitialContext(jndiProperties);
        // let's do the lookup
        return (RemoteEjbClientInterface) context.lookup("ejb:/gestion_bibliotheque_server_side/RemoteEjbClient!"+ RemoteEjbClientInterface.class.getName());
    }
	
}
