package tn.esprit.services.implimentation;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import  tn.esprit.services.interfaces.ActualiteServiceLocal.*;
import tn.esprit.services.interfaces.ActualiteServiceRemote.*;

@Stateless
//@LocalBean
public class RatingImpl  implements IRatingLocal, IRatingRemote{
	
	@PersistenceContext
	EntityManager em;

	

	@Override
	public List<Object> NbRatePosts() {
		List<Object> results = em.createQuery("SELECT u.nom , u.prenom, p.description  ,r.nombreRate  FROM  User u , Rating r  , Post p WHERE r.ratingPK.idUser = u.id AND r.ratingPK.idPost=p.id ")
	.getResultList();
		return results;
	}



	@Override
	public int getMostResquestedPostBYRate() {
		TypedQuery<Integer> query = 
				em.createQuery("select r.ratingPK.idPost ,r.nombreRate  from Rating r ", Integer.class);
		return query.getSingleResult();
	}

}
