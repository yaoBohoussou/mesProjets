package dao;

import javax.ejb.Local;
import javax.ejb.Stateful;

import metier.Client;

@Stateful
@Local(ClientDaoInterface.class)
public class ClientDao extends AbstractDao<Client>
{

}
