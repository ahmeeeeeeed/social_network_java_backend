package tn.esprit.services.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.stripe.exception.StripeException;

import tn.esprit.entities.Payement;
import tn.esprit.entities.User;

@Remote
public interface PayementServiceRemote {
	
	public boolean migrationVersPremium(int idUser,String numeroCarte,int numCVV,Date dateExpiration,double montant);
	public int getNbrPublicationUserParMois(int idUser,int mois);
	public boolean payerPourPublier(int idUser,String numeroCarte,int numCVV,Date dateExpiration,double montant);
	public List<Payement> afficherHistoriquePayement(int idUser);
	public int testDateValidationComptePremium(int idUser);
	public boolean testPremierPayementGratuit(int idUser);
	public boolean changementDeTypeCompteVersFree(int idUser);
	public void payer(int idUser,String numeroCarte,int numCVV,Date dateExpiration,double montant) throws StripeException;
	
	

}
