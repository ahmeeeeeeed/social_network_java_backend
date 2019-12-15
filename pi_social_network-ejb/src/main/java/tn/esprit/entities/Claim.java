package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Claim  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	private int id ;
	private String desciption ;
	
	@Temporal(TemporalType.DATE)
	private Date dateReclamation ;
	private boolean verifier ;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private User user;
	
	@Enumerated(EnumType.STRING)
	TypeClaim typeclaim;
	public Claim () {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TypeClaim getTypeclaim() {
		return typeclaim;
	}

	public void setTypeclaim(TypeClaim typeclaim) {
		this.typeclaim = typeclaim;
	}

	

	public Claim(int id, String desciption, Date dateReclamation, boolean verifier, User user, TypeClaim typeclaim) {
		super();
		this.id = id;
		this.desciption = desciption;
		this.dateReclamation = dateReclamation;
		this.verifier = verifier;
		this.user = user;
		this.typeclaim = typeclaim;
	}

	public Claim(int id, String desciption, Date dateReclamation, boolean verifier) {
		super();
		this.id = id;
		this.desciption = desciption;
		this.dateReclamation = dateReclamation;
		this.verifier = verifier;
	}

	@Override
	public String toString() {
		return "Reclamation [id=" + id + ", desciption=" + desciption + ", dateReclamation=" + dateReclamation
				+ ", verifier=" + verifier + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Date getDateReclamation() {
		return dateReclamation;
	}

	public void setDateReclamation(Date dateReclamation) {
		this.dateReclamation = dateReclamation;
	}

	public boolean isVerifier() {
		return verifier;
	}

	public void setVerifier(boolean verifier) {
		this.verifier = verifier;
	}

}
