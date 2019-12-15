package tn.esprit.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class CompanyManager  extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String introduction;
	private int nbEmployee;
	
	@OneToMany(mappedBy ="companyManager")
	private List <Candidate> ListCandidate ;
	
	
	
	public CompanyManager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompanyManager(int id, String prenom, String nom, String adresse, TypeCompte typeCompte, String mail) {
		super(id, prenom, nom, adresse, typeCompte, mail);
		// TODO Auto-generated constructor stub
	}
	public CompanyManager(int id, String prenom, String nom, String adresse, TypeCompte typeCompte, String mail,
			String introduction, int nbEmployee) {
		super(id, prenom, nom, adresse, typeCompte, mail);
		this.introduction = introduction;
		this.nbEmployee = nbEmployee;
	}
	
	
	
	
	@Override
	public String toString() {
		
		return super.toString()+"CompanyManager [introduction=" + introduction + ", nbEmployee=" + nbEmployee + ", ListCandidate="
				+ ListCandidate + "]";
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getNbEmployee() {
		return nbEmployee;
	}
	public void setNbEmployee(int nbEmployee) {
		this.nbEmployee = nbEmployee;
	}
	public List<Candidate> getListCandidate() {
		return ListCandidate;
	}
	public void setListCandidate(List<Candidate> listCandidate) {
		ListCandidate = listCandidate;
	}
	
	

}
