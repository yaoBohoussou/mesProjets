package MarqueDB;

public class Marque 
{
	String lien;
	String chemin;
	int position;
	int longueur;
	
	public Marque(String lien,String chemin,int position,int longueur)
	{
		this.lien = lien;
		this.chemin = chemin;
		this.position = position;
		this.longueur = longueur;
	}
	
	public String getLien()
	{
		return this.lien;
	}
	
	public String getChemin()
	{
		return this.chemin;
	}
	public int getPosition()
	{
		return this.position;
	}
	public int getLongueur()
	{
		return this.longueur;
	}
	
	
}
