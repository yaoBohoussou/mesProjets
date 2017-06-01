package metier;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity  
@DiscriminatorValue("ROMAN")
public class Roman extends Livre implements Serializable
{
	@Column(name="titre")
	private String titre;
	
	@Column(name="auteur")
	private String auteur;
	
	@Column(name="dateparution")
	private Date dateparution;
	
	@Column(name="style")
	private String style;
	
	public Roman(){}
	
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

	
}
