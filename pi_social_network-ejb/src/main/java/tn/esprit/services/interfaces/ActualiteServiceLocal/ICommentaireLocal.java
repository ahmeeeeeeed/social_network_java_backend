package tn.esprit.services.interfaces.ActualiteServiceLocal;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Commentaire;


@Local
public interface ICommentaireLocal {
	
	String AddCommentaire(Commentaire commentaire);

	void DeleteCommentaire(int id);

	void UpdateCommentaire(Commentaire commentaire);

	List<Commentaire> GetAll();

	Commentaire SearchCommentaireByDate(Date date);
	
	//void affecterCommentaireToPost(int idPost , int idCommentaire);
	
	public int getNBCommentairesParMois (int  mois);

}
