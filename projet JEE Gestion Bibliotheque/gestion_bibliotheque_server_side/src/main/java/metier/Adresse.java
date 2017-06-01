package metier;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="adresse")
public class Adresse implements Serializable
{
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="codezip")
	private int codezip;
	
	@Column(name="rue")
	private String rue;
	
	@Column(name="ville")
	private String ville;
	
	@Column(name="pays")
	private String pays;
	
	public Adresse(){}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public void setCodezip (int zip)
	{
		this.codezip = zip;
	}
	
	public int getCodezip()
	{
		return this.codezip;
	}
	
	public void setRue (String rue)
	{
		this.rue = rue;
	}
	
	public String getRue()
	{
		return this.rue;
	}
	
	public void setVille (String ville)
	{
		this.ville = ville;
	}
	
	public String getVille()
	{
		return this.ville;
	}
	
	public void setPays (String pays)
	{
		this.pays = pays;
	}
	
	public String getPays()
	{
		return this.pays;
	}
	
	@Override
	public String toString()
	{
		return this.codezip+","+this.rue+","+this.ville+","+this.pays;
		
	}
	
}
