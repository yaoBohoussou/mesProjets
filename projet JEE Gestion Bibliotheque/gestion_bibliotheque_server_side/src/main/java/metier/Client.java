package metier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity  
@Table(name = "Client")  
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="typeClt",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue(value="CLIENT")
public class Client implements Serializable
{
	@Id
	@Column(name = "id")
	protected int id;
	
	@Column(name="nomClt")
	protected String nom;
	
	@Column(name="prenomsClt")
	protected String prenoms;
	
	@Column(name="dateNaissance")
	protected Date dateNaissance;
	
	@Column(name="mail")
	protected String mail;
	
	@Column(name="cni")
	protected String cni;
	
	@OneToOne
    @JoinColumn(name = "idAdr")
	protected Adresse adr;
	
	public Client(){}
	
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
}
