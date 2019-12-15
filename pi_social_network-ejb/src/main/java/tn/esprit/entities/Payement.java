package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Payement implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	private int id ;
	
	@Temporal(TemporalType.DATE)
	private Date datePayement;
	
	private String description;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private User user;
	
	private String numeroCarte;
	
	private double montant;
	
	

	public String getNumeroCarte() {
		return numeroCarte;
	}

	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatePayement() {
		return datePayement;
	}

	public void setDatePayement(Date datePayement) {
		this.datePayement = datePayement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public Payement(int id, Date datePayement, String description, User user) {
		super();
		this.id = id;
		this.datePayement = datePayement;
		this.description = description;
		this.user = user;
	}
	
	

	public Payement(int id, Date datePayement, String description, User user, String numeroCarte, double montant) {
		super();
		this.id = id;
		this.datePayement = datePayement;
		this.description = description;
		this.user = user;
		this.numeroCarte = numeroCarte;
		this.montant = montant;
	}

	public Payement() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Payement [id=" + id + ", datePayement=" + datePayement + ", description=" + description + ", user="
				+ user + ", numeroCarte=" + numeroCarte + ", montant=" + montant + "]";
	}

	
}
