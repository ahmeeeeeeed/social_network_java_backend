package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import tn.esprit.entities.Claim;
import tn.esprit.entities.Notification;
import tn.esprit.entities.User;

@Remote
public interface ReclamationServiceRemote {
	
	public void ajouterReclamation(int idUser,String description);
	public int signalerCompte(int idUser,String description);
	public List<Claim> afficherReclamations();
	
	public void ajouterNotification(int idUser, String description);
	public List<Notification> afficherNotificationByUser(int idUser);
	public void bannerCompte(int idUser);
	public boolean verifierReclamation(int idClaim);
	List<User> getUsers();
	User getUserById(int idUser);
	User getUserByEmailAndPaswword(String email,String password);
	public List<Claim> afficherReclamations(int id);

}
