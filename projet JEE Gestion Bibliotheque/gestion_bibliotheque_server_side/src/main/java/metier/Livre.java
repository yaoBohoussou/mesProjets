package metier;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity  
@Table(name = "livre")  
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="typeLivre",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue(value="LIVRE")
public class Livre implements Serializable
{
	@Id
	@Column(name="id")
	protected int id;
	
	@Column(name="edition")
	protected String edition;
	
	@Column(name="nombrestock")
	protected int nombrestock;
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setNombrestock(int nombrestock)
	{
		this.nombrestock = nombrestock;
	}
	
	public void setEdition(String ed)
	{
		this.edition = ed;
	}
	
	public int getId()
	{
		return this.id ;
	}
	
	public int getNombrestock()
	{
		return this.nombrestock ;
	}
	
	public String getEdition()
	{
		return this.edition ;
	}
	
}
