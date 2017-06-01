
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

//import javax.servlet.http.HttpServlet;

public class DB //extends HttpServlet 
{
	Connection conn=null;
	public DB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			try
			{
				String url="jdbc:mysql://localhost:3307/jee";
				String user="root";
				String pwd="070795";
				conn=(Connection) DriverManager.getConnection(url,user,pwd);
				System.out.println("connexion OK");
			}
			catch(SQLException e)
			{
				System.out.println("echec connexion");
			}
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("la classe du driver n'a pas été trouvée");
		}
	}
	
	public void stopConn()
	{
		try
		{
			conn.close();
			System.out.println("close OK");
		}catch(SQLException connclose){System.out.println("echec close connexion");}
	}
	
	
	public String insertMarque(Marque mq)
	{	
		try
		{
			Statement stmt=(Statement) this.conn.createStatement();
			String query="INSERT INTO marques VALUES ('"+mq.getLien()+"','"+mq.getChemin()+"',"+mq.getPosition()+","+mq.getLongueur()+");";
			int result=stmt.executeUpdate(query);
			return "MAJ OK";
		}
		catch(SQLException connclose)
		{
			connclose.printStackTrace();
			return "echec insert Marque";
		}
	}
	
	
	public void supprimerMarque(Marque mq)
	{	
		try
		{
			Statement stmt=(Statement) this.conn.createStatement();
			//update personne set id=10,nom="yano" where id=3;
			
			
			String query="DELETE  * FROM marques;";
			int result=stmt.executeUpdate(query);
			System.out.println("DELETE OK");
		}
		catch(SQLException connclose)
		{
			connclose.printStackTrace();
			System.out.println("echec DELETE toute les Marque");
		}
	}
	
	public ResultSet selectMarque(String Query)
	{
		try
		{
			Statement stmt=(Statement) this.conn.createStatement();
			System.out.println("SELECT OK");
			return stmt.executeQuery(Query);
		}
		catch(SQLException connclose)
		{
			connclose.printStackTrace();
			System.out.println("echec select Marque");
			return null;
		}
		
	}
}

