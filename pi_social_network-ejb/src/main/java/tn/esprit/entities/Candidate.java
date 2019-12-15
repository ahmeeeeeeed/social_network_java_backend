package tn.esprit.entities;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Candidate extends User implements Serializable  {
	
	
	private static final long serialVersionUID = 1L;
	private String bio;
	private String parcours;
	private String certification;
	private int afftected;
	@OneToMany(targetEntity=Competence.class,mappedBy="candidate"  )
	private List<Competence> competences;
	
	
	
	@OneToMany(mappedBy="cand")
	private List<Candidate> miniCand;
	
	@Enumerated(EnumType.STRING)
	private RoleCandidate role;

	
	
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	@ManyToOne
	@JoinColumn(name="candidate_fk")
	private Candidate cand;
	public Candidate getCand() {
		return cand;
	}
    
	public List<Candidate> getMiniCand() {
		return miniCand;
	}

@ManyToOne
private CompanyManager companyManager ;



	@Override
public String toString() {
		
	return super.toString()+"Candidate [bio=" + bio + ", parcours=" + parcours + ", certification=" + certification + ", afftected="
			+ afftected + ", competences=" + competences + ", miniCand=" + miniCand + ", role=" + role + ", cand="
			+ cand + ", companyManager=" + companyManager + "]";
}

	public CompanyManager getCompanyManager() {
	return companyManager;
}

public void setCompanyManager(CompanyManager companyManager) {
	this.companyManager = companyManager;
}

	public void setMiniCand(List<Candidate> miniCand) {
		this.miniCand = miniCand;
	}
	public void setCand(Candidate cand) {
		this.cand = cand;
	}

	public List<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public Candidate(int id, String prenom, String nom, String adresse, TypeCompte typeCompte, String mail) {
		super(id, prenom, nom, adresse, typeCompte, mail);
		// TODO Auto-generated constructor stub
	}

	public Candidate(int id, String prenom, String nom, String adresse, TypeCompte typeCompte, String mail, String bio,
			String parcours, String certification, int afftected, RoleCandidate role) {
		super(id, prenom, nom, adresse, typeCompte, mail);
		this.bio = bio;
		this.parcours = parcours;
		this.certification = certification;
		this.afftected = afftected;
		this.role = role;
	}

	

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getParcours() {
		return parcours;
	}

	public void setParcours(String parcours) {
		this.parcours = parcours;
	}

	public String getCertification() {
		return certification;
	}

	public void setCertification(String certification) {
		this.certification = certification;
	}

	public int getAfftected() {
		return afftected;
	}

	public void setAfftected(int afftected) {
		this.afftected = afftected;
	}

	public RoleCandidate getRole() {
		return role;
	}

	public void setRole(RoleCandidate role) {
		this.role = role;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
