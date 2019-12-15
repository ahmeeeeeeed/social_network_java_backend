package tn.esprit.services.interfaces.ActualiteServiceRemote;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Commentaire;

@Remote
public interface ICommentaireRemote {
	String AddCommentaire(Commentaire commentaire);

	void DeleteCommentaire(int id);

	void UpdateCommentaire(Commentaire commentaire);

	List<Commentaire> GetAll();

	Commentaire SearchCommentaireByDate(Date date);

	//void affecterCommentaireToPost(int idPost , int idCommentaire);
	
	public int getNBCommentairesParMois (int  mois);
}
