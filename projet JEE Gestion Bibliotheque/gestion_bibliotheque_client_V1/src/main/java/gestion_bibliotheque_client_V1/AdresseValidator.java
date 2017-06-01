package gestion_bibliotheque_client_V1;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class AdresseValidator implements Validator
{

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException 
	{
		String adr = arg2.toString();
		String adr1[] = adr.split(",");
		if(adr1.length <4)
		{
			FacesMessage msg = new FacesMessage("Adresse Incorrecte");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		else
		{
			try
			{
				int zip = Integer.parseInt(adr1[0]);
			}
			catch (Exception e)
			{
				FacesMessage msg = new FacesMessage("Adresse Incorrecte :  vérifier code zip");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		}
	}

}
