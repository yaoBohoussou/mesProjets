<%@page language="java" import="java.sql.*,java.lang.*,MarqueDB.DB,MarqueDB.Marque" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%><%DB conn = new DB();
String str = request.getParameter("URL");
String query = "select * from marques where lien='"+str+"';";
ResultSet resultat=conn.selectMarque(query);
String resFin ="";
while(resultat.next())
{String lien=resultat.getString("lien");
String chemin =resultat.getString("chemin");
int pos=resultat.getInt("position");
int lng =resultat.getInt("longueur");
String obj = chemin+";;"+pos+";;"+lng;
if(resFin.equals(""))
	resFin = obj;
else
	resFin = obj+",,"+resFin;
}out.println(resFin);%>