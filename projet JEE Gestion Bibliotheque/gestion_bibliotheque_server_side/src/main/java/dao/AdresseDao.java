package dao;


import javax.ejb.Local;
import javax.ejb.Stateful;

import metier.Adresse;

@Stateful
@Local(AdresseDaoInterface.class)
public class AdresseDao extends AbstractDao<Adresse>
{

	
}
