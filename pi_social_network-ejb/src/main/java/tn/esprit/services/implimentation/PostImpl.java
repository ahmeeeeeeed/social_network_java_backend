package tn.esprit.services.implimentation;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.entities.Message;
import tn.esprit.entities.Post;
import tn.esprit.entities.User;
import  tn.esprit.services.interfaces.ActualiteServiceLocal.*;
import tn.esprit.services.interfaces.ActualiteServiceRemote.*;


@Stateless
//@LocalBean
public class PostImpl  implements IPostLocal, IPostRemote  {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public String AddPost(Post post) {
		
		TypedQuery<String> q = em.createQuery("SELECT p.description FROM Post p WHERE p.id = :id", String.class);
		q.setParameter("id", post.getId());
		List<String> descriptions=q.getResultList();
		if(descriptions.isEmpty())
		{
			em.persist(post);
			return "ADDED";
			
		}
		else
		{
			return "POST EXIST";
		}
		
	}

	@Override
	public void DeletePost(int id) {
		em.remove(em.find(Post.class, id));

		
	}

	@Override
	public void UpdatePost(Post post) {
		Query q = em.createQuery("UPDATE Post p SET p.description = :description, "
				+ "p.datePost = :datePost WHERE p.id = :id");

		q.setParameter("id", post.getId());
		q.setParameter("description", post.getDescription());
		q.setParameter("datePost", post.getDatePost());

		q.executeUpdate();
		
	}

	@Override
	public List<Post> GetAll() {
		TypedQuery<Post> q = em.createQuery("SELECT p FROM Post p  ", Post.class);
		return (List<Post>) q.getResultList();
	}

	@Override
	public Post SearchPostByDate(Date datepost) {
		TypedQuery<Post> q = em.createQuery("SELECT p FROM Post p where p.datePost = :datePost",
				Post.class);
		q.setParameter("datePost", datepost);
		return (Post) q.getSingleResult();
	}

	@Override
	public String signalerPost(Post post , int idPost ) {
		//User u = em.find(User.class, idUser);
		
		TypedQuery<Post> query = em.createQuery("select  p from Post p   WHERE  p.id = :id AND  not ( p.description like '%hahaha%'\r\n" + 
				"or p.description like '%blahblah%'\r\n" + 
				"or p.description like'%lool%')",Post.class);

		query.setParameter("id",post.getId());
		
		List<Post> listmessage= query.getResultList();
		
      if(listmessage.size()>1) {
			
			this.bannerPost(idPost);
			for(Post p : listmessage) 
				em.remove(p);
			return "POST A ETE SIGNALE";
			
		}
      else {
			
			
		
	 Post p = new Post();
		
	// p.setDatePost(new Date());
	// p.setDescription(description);
	 //p.setUser(u);
		

		em.persist(p);
		}
		
		return "POST NE CONTIENT PAS DE MAUVAIS MOTS ";


		
		
	}

	@Override
	public void bannerPost(int idPost) {
		
		Post u = em.find(Post.class, idPost);
		u.setActive(false);
		em.merge(u);
	}

	@Override
	public List<Object> MostRequestedPostBYRATE() {
		List<Object> results = em.createQuery("SELECT u.prenom ,u.nom , p.description , p.datePost , MAX (r.nombreRate)  FROM  User u , Rating r  , Post p WHERE r.ratingPK.idUser = u.id AND r.ratingPK.idPost=p.id ")
				.getResultList();
					return results;
	}

	@Override
	public int getNBPostsParMois( int mois) {
		
		
		Query q = em.createQuery("SELECT Count(p.id) FROM Post p WHERE MONTH (p.datePost)= :mois");
		q.setParameter("mois",mois);

		 return ((Number) q.getSingleResult()).intValue();
	}

	
	
	

}
