package tn.esprit.services.implimentation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.loader.custom.Return;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

import tn.esprit.entities.Offre;
import tn.esprit.entities.Payement;
import tn.esprit.entities.TypeCompte;
import tn.esprit.entities.User;
import tn.esprit.services.interfaces.PayementServiceRemote;
import tn.esprit.services.interfaces.ReclamationServiceRemote;

@Stateless
@LocalBean
public class PaymentService implements PayementServiceRemote{

	@PersistenceContext
	EntityManager em;
	private Payement[] tabs;

	@Override
	public int getNbrPublicationUserParMois(int idUser,int mois) {
		
		
		int c = 0;
		
		Date d = new Date();

		//User u = em.find(User.class, idUser);
		//return u.getOffre().size();
		System.out.println("*************");
		User u = (em.find(User.class, idUser));
		
	//	if(u instanceof CompanyManager) {
			System.out.println("******************if(u instanceof CompanyManager)*****************************");
			
			for (Offre o : u.getOffre()) {
				
				if(o.getDate().getMonth() == d.getMonth())
					c++;
				//System.out.println(o.getDate().getMonth());
			}
			
		//}
			System.out.println("/////////////////////////////////////////////////"+c);
		
		return c;
		
	}

	@Override
	public void payer(int idUser, String numeroCarte, int numCVV, Date dateExpiration, double montant) throws StripeException {
		
		

		User user = em.find(User.class, idUser);

		Stripe.apiKey = "sk_test_jijDISHFRipOAWcg93gVeFmd005eL2ZL7t";
	        
	        if(testPremierPayementGratuit(idUser)==false || user.getCustomerId()!=null) {//if he already have an account
	        	
		
	        	Customer newCustomer = Customer.retrieve(user.getCustomerId());
	    		System.out.println("****************************************** if he already have an account");
	    		//newCustomer.get

	        //	if((long) (newCustomer.getAccountBalance()-montant)>=0) {
	    	        
	    	        //to charge
	    	        Map < String, Object> chargeParam = new HashMap< String, Object> ();
	    	        chargeParam.put("amount",(long) montant);
	    	        chargeParam.put("currency", "usd");
	    	        chargeParam.put("customer", user.getCustomerId());
	    	        
	    	         Charge.create(chargeParam);
	    	       
	    	         
	    	        
	    	         
	    	        
	    	         
	    	         
	    	        // newCustomer.setAccountBalance((long) (newCustomer.getAccountBalance()-montant));
	        //	}
	        //	else
	        //		 System.out.println("pas assez de montant");
	    		
	        }
	        
	        
	        else
	        { if(testPremierPayementGratuit(idUser)==true) {
	        	
	        
	        	System.out.println("****************************************** first payement");
	        	//create new customer
	    		
	    		 Map < String, Object> customerParams = new HashMap< String, Object> ();
	    		 customerParams.put("email", user.getMail());
	    		 //customerParams.put("balance", (long) 50000);
	    		 Customer newCustomer = Customer.create(customerParams); 
	    		 
	    	
	    		 
	    		 //change custom id in the database
	    			user.setCustomerId(newCustomer.getId());
	    			
	    			em.merge(user);
	    			 
	    			//create new customer card
	    	        Map < String, Object> cardParam = new HashMap< String, Object> ();
	    	        cardParam.put("number", numeroCarte);
	    	        cardParam.put("exp_month", dateExpiration.getMonth()+1);
	    	        cardParam.put("exp_year", dateExpiration.getYear());
	    	        cardParam.put("cvc",String.valueOf(numCVV) );
	    	        
	    	        //create a token to add card to the customer
	    	        Map < String, Object> tokenParam = new HashMap< String, Object> ();
	    	        tokenParam.put("card", cardParam);
	    	        
	    	        Token token =Token.create(tokenParam);
	    	        
	    	        ///create source to connect the customer to his card
	    	        Map < String, Object> source = new HashMap< String, Object> ();
	    	        source.put("source",token.getId());
	    	        
	    	        newCustomer.getSources().create(source);
	    	        
	    	        //to charge
	    	        Map < String, Object> chargeParam = new HashMap< String, Object> ();
	    	        chargeParam.put("amount",(int) montant);
	    	        chargeParam.put("currency", "usd");
	    	        chargeParam.put("customer", newCustomer.getId());
	    	        
	    	        Charge.create(chargeParam);
		    		//System.out.println("******************************************"+newCustomer.getAccountBalance());
	    	        
	        }//if

	        }//else
	        

	        
	        
		
	}

	@Override
	public boolean migrationVersPremium(int idUser, String numeroCarte, int numCVV, Date dateExpiration, double montant) {
		double prix = 5000;
		String happyHours="";
		User user = em.find(User.class, idUser);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date d = new Date();
		d.setMonth(d.getMonth()+1);
		
		if(testPremierPayementGratuit(idUser)==true &&user.getTypeCompte() == TypeCompte.FREE &&  montant>=200 && //if true then it's his first time
				dateExpiration.compareTo(new Date())>=0 && numeroCarte.length()==16 &&
				(numCVV>=100 && numCVV<=9999)) {
			Payement p = new Payement();
			p.setDatePayement(new Date());
			p.setUser(user);
			p.setNumeroCarte(numeroCarte);
			p.setDescription("premiere migration gratuite vers compte premium expirera le "+sdf.format(d));
			p.setMontant((double)(montant));
			
			user.setTypeCompte(TypeCompte.PREMIUM);
			user.setNumeroCarte(numeroCarte);
			
			try {
				payer(idUser, numeroCarte, numCVV, dateExpiration, 500);
			} catch (StripeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			em.merge(user);
			
			em.persist(p);
			
			
			return true;
			
		}
		else {

			if(/*testDateValidationComptePremium(idUser)<0 && */
					((user.getTypeCompte() == TypeCompte.PREMIUM && testPremierPayementGratuit(idUser)==false && afficherHistoriquePayement( idUser).size()==1) &&
					//if he has the trial account now and he has never bought a prumium account before but he wants now 
					montant>=200 && dateExpiration.compareTo(new Date())>=0 && numeroCarte.length()==16 && (numCVV>=100 && numCVV<=9999))
					||
					((user.getTypeCompte() == TypeCompte.FREE && testPremierPayementGratuit(idUser)==false && afficherHistoriquePayement( idUser).size()>1) &&
					//if he has the free account now but he has bought a prumium account before and he wants to buy again 
					montant>=200 && dateExpiration.compareTo(new Date())>=0 && numeroCarte.length()==16 && (numCVV>=100 && numCVV<=9999))
					) {
				
				System.out.println("*********************************");
				
				if(new Date().getDate()==3 || new Date().getDate()==2 || new Date().getDate()==1) 
					{
					prix = 2500;happyHours=" avec la réduction 50% de 'mounth starting!' ";
					}
	
					Payement p = new Payement();
					p.setDatePayement(new Date());
					p.setUser(user);
					p.setNumeroCarte(numeroCarte);
					p.setDescription("migration vers compte premium pour "+prix/100+"$/mois"+happyHours+" ,elle expirera le "+sdf.format(d));
					p.setMontant((double)(montant));
					
					user.setTypeCompte(TypeCompte.PREMIUM);
					user.setNumeroCarte(numeroCarte);
					
					try {
						payer(idUser, numeroCarte, numCVV, dateExpiration, montant);
					} catch (StripeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					em.merge(user);
					
					em.persist(p);
					
					
					return true;
					
				}
			
			System.out.println("---------------------------------------------");
			
				
			}
		return false;
		
	}

	@Override
	public boolean payerPourPublier(int idUser, String numeroCarte, int numCVV, Date dateExpiration, double montant) {
		
		User u = em.find(User.class, idUser);
		System.out.println("dateExpiration:"+ dateExpiration.getMonth());
		
		if(this.getNbrPublicationUserParMois(idUser,dateExpiration.getMonth())>1) {
			System.out.println("***********************if(this.getNbrPublicationUserParMois(idUser,new Date().getMonth())>1)************************");
			int prix = 500;
			String happyHours="";
			if(montant>=8 && dateExpiration.compareTo(new Date())>0 /*&& u instanceof CompanyManager*/ &&
					u.getNumeroCarte().equals(numeroCarte) &&
					u.getTypeCompte()==TypeCompte.FREE) {
				System.out.println("********************if(montant>=8 && dateExpiration.compareTo(new Date())>0***************************");
				System.out.println("***********************************************");
				
				if(new Date().getDate()==1 || new Date().getDate()==2 || new Date().getDate()==3) 
					{
					prix = 200;happyHours=" avec la réduction 50% de 'mounth starting!' ";
					}
				
					User user = em.find(User.class, idUser);
					
					Payement p = new Payement();
					p.setDatePayement(new Date());
					p.setUser(user);
					p.setNumeroCarte(numeroCarte);
					p.setDescription("payement pour la publication d'un offre d'emploi pour "+prix/100+"$/mois"+happyHours);
					p.setMontant((double)(montant-prix));
					
					
					
					try {
						payer(idUser, numeroCarte, numCVV, dateExpiration, montant);
					} catch (StripeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					em.persist(p);
					return true;
					
			}
			
			
		}
		
		return false;
		
	}



	@Override
	public List<Payement> afficherHistoriquePayement(int idUser) {
		//User u = em.find(User.class, idUser);
		
		TypedQuery<Payement> query = em.createQuery("select p from Payement p join p.user u "
				+ "where u.id=:user_id ",Payement.class);
		query.setParameter("user_id", idUser);
		
		List<Payement> listp = new ArrayList<Payement>();
		for (Payement p : query.getResultList()) 
		{
			listp.add(new Payement(p.getId(), p.getDatePayement(), p.getDescription(), 
					new User(p.getUser().getId(),p.getUser().getPrenom(),p.getUser().getNom(),p.getUser().getAdresse(),p.getUser().getTypeCompte(),p.getUser().getMail()),
					p.getNumeroCarte(),p.getMontant()));
		}
		return listp;
		

		
	//	return u.getPayements();
		
	}
	
	@Override
	public int testDateValidationComptePremium(int idUser) {
		
		User u = em.find(User.class, idUser);
		
		if(u.getTypeCompte() == TypeCompte.FREE) return -99;
		
		/*TypedQuery<Payement> query = em.createQuery("select p from Payement p join p.user u "
				+ "where u.id=:user_id and p.description LIKE '%migration%'",Payement.class);
		query.setParameter("user_id", idUser);
		*/
		
		tabs = new Payement[u.getPayements().size()];
		int x =0;
		
		for (Payement p :  u.getPayements()) 
				{
					tabs[x] = p;
					x++;		
				}
		
		for (int i = 0; i < tabs.length ; i++)  
        {
             int index = i;  
             for (int j = i + 1; j < tabs.length; j++)
             {
                  if (tabs[j].getDatePayement().compareTo(tabs[index].getDatePayement())>0){ 
                       index = j;
                  }
             }

             Payement min = tabs[index]; 
             tabs[index] = tabs[i]; 
             tabs[i] = min;
             
        }
		
		Payement dernierPeyment = new Payement();
		for (int i = 0; i < tabs.length -1 ; i++) 
			if(tabs[i].getDescription().contains("migration"))	{
				//System.out.println(tabs[i].getDescription());
				dernierPeyment = tabs[i];
				break;
			}
		
		//System.out.println(dernierPeyment.getDatePayement());
		Date d = new Date();
		d.setMonth(dernierPeyment.getDatePayement().getMonth()+1);
		
		return d.compareTo(new Date());//<0
		//-1 si dernierPeyment.getDatePayement()+1 < new date
		//1 si dernierPeyment.getDatePayement()+1 > new date

	}



	@Override
	public boolean testPremierPayementGratuit(int idUser) {
			List<Payement> lisp = afficherHistoriquePayement( idUser);
		
		for (Payement p : lisp) {
			
			if(p.getDescription().contains("premiere")) {
				return false;//if false then he have bought a free account 
			}
			
		}
		
		return true;
	}



	@Override
	public boolean changementDeTypeCompteVersFree(int idUser) {
		User u = em.find(User.class, idUser);
		
		if(testDateValidationComptePremium(idUser)== -1) {
			u.setTypeCompte(TypeCompte.FREE);
			//em.merge(u);
			ReclamationServiceRemote rs = new ReclamationService();
			
			//rs.ajouterNotification(idUser, "votre date d'expiration de compte premium est attente,votre compte revient FREE");
			return true;
		}
		return false;
	}




	
	
	

}
