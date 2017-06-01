package metier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="emprunt")
public class Emprunt implements Serializable
{
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="dateemprunt")
	private Date dateemprunt;
	
	@Column(name="duree")
	private int duree;
	
	@OneToOne
	@JoinColumn(name = "idClient")
	private Client client;
	
	@OneToOne
	@JoinColumn(name = "idLivre")
	private Livre livre;
	
	public Emprunt(){}
	
	public void setClient(Client clt)
	{
		this.client = clt;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setLivre(Livre liv)
	{
		this.livre = liv;
	}
	
	public void setDateemprunt(Date dat)
	{
		this.dateemprunt = dat;
	}
	
	public void setDuree(int duree)
	{
		this.duree = duree;
	}
	
	public Client getClient()
	{
		return this.client ;
	}
	
	public int getId()
	{
		return this.id ;
	}
	
	public Livre getLivre()
	{
		return this.livre ;
	}
	
	public Date getDateemprunt()
	{
		return this.dateemprunt ;
	}
	
	public int getDuree()
	{
		return this.duree ;
	}

}
