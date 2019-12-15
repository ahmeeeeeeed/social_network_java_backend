package tn.esprit.services.implimentation;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import  tn.esprit.services.interfaces.ActualiteServiceLocal.*;
import tn.esprit.services.interfaces.ActualiteServiceRemote.*;

@Stateless
//@LocalBean
public class ActiviteImpl  implements IActiviteLocal, IActiviteRemote{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Object> HistoriqueActivite() 
	{

		List<Object> results = em.createQuery("SELECT p.description , p.datePost FROM  Post p , Activite a WHERE a.activitePK.idPost = p.id ORDER BY p.datePost desc").getResultList();
				return results;
	}

	
	


	

}
