package metier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity  
@DiscriminatorValue("EMPLOYE")
public class Employe extends Client implements Serializable
{
	@Column(name="dateEmbauche")
	private Date dateembauche;
	
	@Column(name="salaire")
	private double salaire;
	
	@Column(name="poste")
	private String poste;
	
	@Column(name="login")
	private String login;
	
	@Column(name="mdp")
	private String mdp;
	
	public Employe(){}
	
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

	

	
}
