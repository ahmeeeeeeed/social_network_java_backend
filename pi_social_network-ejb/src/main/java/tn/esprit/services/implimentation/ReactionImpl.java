package tn.esprit.services.implimentation;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import tn.esprit.entities.Reaction;
import  tn.esprit.services.interfaces.ActualiteServiceLocal.*;
import tn.esprit.services.interfaces.ActualiteServiceRemote.*;

@Stateless
//@LocalBean
public class ReactionImpl implements IReactionLocal, IReactionRemote{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Object> NbLikePosts() {
		List<Object> results = em.createQuery("SELECT u.nom , u.prenom, COUNT (r.etat)  FROM  User u , Reaction r WHERE r.reactionPK.idUser = u.id AND r.etat='like' ").getResultList();
		return results;
	}

	@Override
	public List<Object> NbDislikePosts() {
		List<Object> results = em.createQuery("SELECT u.nom , u.prenom , COUNT (r.etat)  FROM  User u , Reaction r WHERE r.reactionPK.idUser = u.id AND r.etat='dislike' ").getResultList();
		return results;
	}

	


	
	

	

	

}
