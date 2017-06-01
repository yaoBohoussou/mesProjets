package gestion_bibliotheque_client_V1;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import metier.Adresse;

@FacesConverter("AdresseConverter")
public class AdresseConverter implements Converter
{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) 
	{
		Adresse adresse = new Adresse();
		int val =-1;
		if(arg2.length()>0)
		{
			String adr[] = arg2.split(",");	
			if(adr.length==4)
			{
				try{val = Integer.parseInt(adr[0]);} catch (Exception ex){System.out.println(ex);}
				adresse.setCodezip(val);
				adresse.setRue(adr[1]);
				adresse.setVille(adr[2]);
				adresse.setPays(adr[3]);
			}
		}
		
		return adresse;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) 
	{
		Adresse adr = (Adresse)arg2;
		return adr.getCodezip() +", " + adr.getRue() + " ," + adr.getVille() + " ," + adr.getPays();
	}

}
