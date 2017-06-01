package metier;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity  
@DiscriminatorValue("AUTRE")
public class Docautre extends Livre implements Serializable 
{
	@Column(name="typedoc")
	private String typedoc;
	
	@Column(name="description")
	private String description;
	
	public Docautre(){}
	
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

}
