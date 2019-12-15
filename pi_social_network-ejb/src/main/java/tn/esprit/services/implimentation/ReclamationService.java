package tn.esprit.services.implimentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.entities.Claim;
import tn.esprit.entities.Notification;
import tn.esprit.entities.Payement;
import tn.esprit.entities.TypeClaim;
import tn.esprit.entities.User;
import tn.esprit.services.interfaces.ReclamationServiceRemote;

@Stateless
public class ReclamationService implements ReclamationServiceRemote{

	
	@PersistenceContext
	EntityManager em;
	@Override
	public void ajouterReclamation(int idUser, String description) {

		User u = em.find(User.class, idUser);
		
		Claim c = new Claim();
		
		c.setDateReclamation(new Date());
		c.setDesciption(description);
		c.setTypeclaim(TypeClaim.NORMAL);
		c.setVerifier(false);
		c.setUser(u);

		em.persist(c);
		
	}

	@Override
	public int signalerCompte(int idUser,String description) {
		
		User u = em.find(User.class, idUser);
		//Notification notif = new Notification();
		
		if(u.getIsActive()==false)
			return -1;

		TypedQuery<Claim> query = em.createQuery("select c from Claim c join c.user u "
				+ "where u.id=:user_id and c.typeclaim LIKE 'SIGNALISATION'",Claim.class);
		query.setParameter("user_id", idUser);
		
		List<Claim> lisclaim = query.getResultList();
		//System.out.println("siiiiiiiiiiiiize : "+lisclaim.size());
		
		if(lisclaim.size()>0) {
			
			this.bannerCompte(idUser);
			for(Claim c : lisclaim) em.remove(c);
			return 1;
			
		}
		else {
			
			 ajouterNotification(idUser,"attention , il y a quelqun qui vous a signalé pour un movais comportement d'utilisation, la prochaine fois votre compte sera banné");  
			
		
		Claim c = new Claim();
		
		c.setDateReclamation(new Date());
		c.setDesciption(description);
		c.setTypeclaim(TypeClaim.SIGNALISATION);
		c.setVerifier(false);
		c.setUser(u);

		em.persist(c);
		return 0;
		}
		
		//return -1;

	}

	

	@Override
	public void ajouterNotification(int idUser, String description) {
		User u = em.find(User.class, idUser);
		Notification notif = new Notification();
		notif.setDate(new Date());
		notif.setDescription(description);
		notif.setUser(u);
		
		em.persist(notif);
		
	}

	

	@Override
	public void bannerCompte(int idUser) {
		
		User u = em.find(User.class, idUser);
		
		this.ajouterNotification(idUser, "à cause de signalisation de votre compte plus que 2 fois ,"
				+ "votre compte sera banné immediatement !");
		
		u.setIsActive(false);
		em.merge(u);
		
	}

	@Override
	public List<Claim> afficherReclamations() {
		
		TypedQuery<Claim> query = em.createQuery("select c from Claim c",Claim.class);
		
		List<Claim> listp = new ArrayList<Claim>();
		for (Claim p : query.getResultList()) 
		{
			listp.add(new Claim(p.getId(),p.getDesciption() ,p.getDateReclamation(),p.isVerifier(),
					new User(p.getUser().getId(),p.getUser().getPrenom(),p.getUser().getNom(),p.getUser().getAdresse(),p.getUser().getTypeCompte(),p.getUser().getMail()),
					p.getTypeclaim()));
		}
		return listp;
	}


	@Override
	public boolean verifierReclamation(int idClaim) {
		Claim claim = em.find(Claim.class,idClaim );
		
		if(claim.isVerifier() == false) {
			claim.setVerifier(true);
			return true;
		}
		else {
			System.out.println("nooooooooooooooooooooooooooooooooooooooooooooo");
			return false;
		}
		
		
		
	}

	@Override
	public List<Notification> afficherNotificationByUser(int idUser) {
		TypedQuery<Notification> query = em.createQuery("select n from Notification n join n.user u "
				+ "where u.id=:user_id ",Notification.class);
		query.setParameter("user_id", idUser);
		//return query.getResultList();
		
		
		
		List<Notification> listp = new ArrayList<Notification>();
		for (Notification p : query.getResultList()) 
		{
			listp.add(new Notification(p.getId(),p.getDescription(),p.getDate(),
					new User(p.getUser().getId(),p.getUser().getPrenom(),p.getUser().getNom(),p.getUser().getAdresse(),p.getUser().getTypeCompte(),p.getUser().getMail())));
		}
		return listp;
	}
	
	
	/////////////////////// validation phase 2
	@Override
public List<User> getUsers() {
		
		TypedQuery<User> query = em.createQuery("select u from User u",User.class);
		
		
		
		List<User> listp = new ArrayList<User>();
		for (User p : query.getResultList()) 
		{
			listp.add(
					new User(p.getId(),p.getPrenom(),p.getNom(),p.getAdresse(),p.getTypeCompte(),p.getMail(),p.getPassword(),p.getIsActive(),p.getNbAbonnees()));
		}
		return listp;
		
		
	}

	@Override
	public User getUserById(int idUser) {
		// TODO Auto-generated method stub
		 User p =em.find(User.class, idUser);
		 User u2 = new User(p.getId(),p.getPrenom(),p.getNom(),p.getAdresse(),p.getTypeCompte(),p.getMail(),p.getPassword(),p.getIsActive(),p.getNbAbonnees());
		 return u2;
	}

	@Override
	public User getUserByEmailAndPaswword(String email, String password) {
		TypedQuery<User> query = em.createQuery("select u from User u "
				+ "where u.password=:password and u.mail=:mail",User.class);
		query.setParameter("password", password);
		query.setParameter("mail", email);
		
		User p =query.getSingleResult();
		
		 User u2 = new User(p.getId(),p.getPrenom(),p.getNom(),p.getAdresse(),p.getTypeCompte(),p.getMail(),p.getPassword(),p.getIsActive(),p.getNbAbonnees());
		 System.out.println(u2);
		 return u2;
	}

	@Override
	public List<Claim> afficherReclamations(int id) {
TypedQuery<Claim> query = em.createQuery("select c from Claim c join c.user u where u.id=:user_id",Claim.class);
query.setParameter("user_id", id);

		
		List<Claim> listp = new ArrayList<Claim>();
		for (Claim p : query.getResultList()) 
		{
			listp.add(new Claim(p.getId(),p.getDesciption() ,p.getDateReclamation(),p.isVerifier(),
					new User(p.getUser().getId(),p.getUser().getPrenom(),p.getUser().getNom(),p.getUser().getAdresse(),p.getUser().getTypeCompte(),p.getUser().getMail()),
					p.getTypeclaim()));
		}
		return listp;
	}

}
