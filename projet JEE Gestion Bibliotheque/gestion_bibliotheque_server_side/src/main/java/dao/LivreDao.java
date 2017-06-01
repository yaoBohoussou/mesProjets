package dao;

import javax.ejb.Local;
import javax.ejb.Stateful;

import metier.Livre;

@Stateful
@Local(LivreDaoInterface.class)
public class LivreDao extends AbstractDao<Livre>
{

}
