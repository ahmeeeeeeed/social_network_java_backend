package tn.esprit.services.implimentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.entities.Commentaire;
import tn.esprit.entities.Post;
import  tn.esprit.services.interfaces.ActualiteServiceLocal.*;
import tn.esprit.services.interfaces.ActualiteServiceRemote.*;

@Stateless
//@LocalBean
public class CommentaireImpl  implements ICommentaireLocal, ICommentaireRemote {
	
	@PersistenceContext/*(unitName="pi_social_network_ejb")*/
	EntityManager em;

	@Override
	public String AddCommentaire(Commentaire commentaire) {
		
		TypedQuery<String> q = em.createQuery("SELECT c.description FROM Commentaire c WHERE c.id = :id", String.class);
		q.setParameter("id", commentaire.getId());
		List<String> descriptions=q.getResultList();
		if(descriptions.isEmpty())
		{
			em.persist(commentaire);
			return "ADDED";
			
		}
		else
		{
			return "COMMENTAIRE EXIST";
		}
		
	}

	@Override
	public void DeleteCommentaire(int id) {
		em.remove(em.find(Commentaire.class, id));

	}

	@Override
	public void UpdateCommentaire(Commentaire commentaire) {
		
		Query q = em.createQuery("UPDATE Commentaire c SET c.description = :description, "
				+ "c.dateCommentaire = :dateCommentaire WHERE c.id = :id");

		q.setParameter("id", commentaire.getId());
		q.setParameter("description", commentaire.getDescription());
		q.setParameter("dateCommentaire", commentaire.getDateCommentaire());

		q.executeUpdate();

		
	}

	@Override
	public List<Commentaire> GetAll() {
		TypedQuery<Commentaire> c = em.createQuery("SELECT c FROM Commentaire c ", Commentaire.class);
		return (List<Commentaire>) c.getResultList();
	}

	@Override
	public Commentaire SearchCommentaireByDate(Date dateCommentaire) {
		TypedQuery<Commentaire> q = em.createQuery("SELECT c FROM Commentaire c where c.dateCommentaire = :dateCommentaire",
				Commentaire.class);
		q.setParameter("dateCommentaire", dateCommentaire);
		return (Commentaire) q.getSingleResult();
	}

	

	@Override
	public int getNBCommentairesParMois(int mois) {
		Query q = em.createQuery("SELECT Count(p.id) FROM Post p WHERE MONTH (p.datePost)= :mois");
		q.setParameter("mois",mois);
		
		 return ((Number) q.getSingleResult()).intValue();
	}

	

}
