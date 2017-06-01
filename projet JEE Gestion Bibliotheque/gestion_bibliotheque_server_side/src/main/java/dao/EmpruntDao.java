package dao;

import javax.ejb.Local;
import javax.ejb.Stateful;

import metier.Emprunt;

@Stateful
@Local(EmpruntDaoInterface.class)
public class EmpruntDao extends AbstractDao<Emprunt>
{

}
