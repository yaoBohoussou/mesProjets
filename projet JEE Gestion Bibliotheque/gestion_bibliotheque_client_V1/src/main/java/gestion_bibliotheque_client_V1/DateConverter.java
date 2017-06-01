package gestion_bibliotheque_client_V1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter ("convertisseur_de_dates")
public class DateConverter implements Converter
{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) 
	{
		//String dt[] = arg2.split("-");
		Date date = new Date();
		if(arg2.length()>0)
			date = this.convertStringToDate(arg2);
		
		return (Object)date;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) 
	{
		return this.convertDateToString((Date)arg2);
	}
	
	private Date convertStringToDate(String dateString)
	{
	    Date date = null;
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    try{
	        date = df.parse(dateString);
	    }
	    catch ( Exception ex ){
	        System.out.println(ex);
	    }
	    return date;
	}
	
	private String convertDateToString(Date date)
	{
		
			String dateString =null;
		    DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		    try{
		    	dateString = df.format(date);
		    }
		    catch ( Exception ex ){
		        System.out.println(ex);
		    }
		return dateString;
	}

}
