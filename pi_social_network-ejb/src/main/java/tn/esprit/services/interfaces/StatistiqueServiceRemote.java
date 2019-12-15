package tn.esprit.services.interfaces;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import tn.esprit.entities.Competence;
import tn.esprit.entities.Offre;
import tn.esprit.entities.Payement;
import tn.esprit.entities.User;

@Remote
public interface StatistiqueServiceRemote {
	
	public int doStatNbrToutRecrutementParMois(int mois)  ;
	public int doStatNbrRecrutementEntrepriseParMois(int mois,int idCompanyManager)  ;
	public void doStatUserSkillsMostRequiredPerMounth(String listcompetence,int mois);
	public int getNbrPublicationOffreParMois(int idUser,int mois) ;
	public int getNbrUserPostuleAOffre(int idOffre);
	public Map<String,Integer> getNbrPayementPremiumChaqueMois();
	//public int getNbrTotalInscriptionChaqueMois();
	//public int getNbrPublicationParJour(int idUser) ;
	public Map<String,Integer> getNbrPublicationChaqueMois( );
	//public int getNbrPublicationParSemaine(int idUser ,int semaine) ;
	public Map<String,Integer> doStatNbrRecrutementEntrepriseChaqueMois( int idCompanyManager) ;
	public Offre[] getListOffreParCompany(int idCompany);
	

}
