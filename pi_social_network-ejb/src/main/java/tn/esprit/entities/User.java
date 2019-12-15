package tn.esprit.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class User implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	private int id ;
	private String prenom;
	private String nom;
	private String adresse;
	
	
	private String gouvernorats;
	
	@Enumerated(EnumType.STRING)
	private TypeCompte typeCompte;
	
	private String mail;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user")
	private List<Notification> listNotification  = new ArrayList<Notification>();
	
	@OneToMany(mappedBy="user")
	private List<Activite> listActivite ;
	
	
	@OneToMany(mappedBy="user")
	private List<Message> listMessage ;
	
	@OneToMany(mappedBy="user")
	private List<Claim> listClaim;
	
	/***************************************************************************************/
	
	@ManyToMany(cascade=CascadeType.PERSIST/*,fetch=FetchType.EAGER*/)
	private List<Offre> offre;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="user",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private List<Payement> payements = new ArrayList<>();

	
	private String numeroCarte;
	
	private Boolean isActive;
	
	private String CustomerId ;
	
	private String password ;
	
	private int nbAbonnees;
	
	/***************************************************************************************/
	
	
	public String getNumeroCarte() {
		return numeroCarte;
	}
	public String getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}
	public String getGouvernorats() {
		return gouvernorats;
	}
	public void setGouvernorats(String gouvernorats) {
		this.gouvernorats = gouvernorats;
	}
	public List<Payement> getPayements() {
		return payements;
	}
	public void setPayements(List<Payement> payements) {
		this.payements = payements;
	}
	public List<Notification> getListNotification() {
		return listNotification;
	}
	public void setListNotification(List<Notification> listNotification) {
		this.listNotification = listNotification;
	}
	public List<Activite> getListActivite() {
		return listActivite;
	}
	public void setListActivite(List<Activite> listActivite) {
		this.listActivite = listActivite;
	}
	public List<Message> getListMessage() {
		return listMessage;
	}
	public void setListMessage(List<Message> listMessage) {
		this.listMessage = listMessage;
	}
	public List<Claim> getListClaim() {
		return listClaim;
	}
	public void setListClaim(List<Claim> listClaim) {
		this.listClaim = listClaim;
	}
	public List<Offre> getOffre() {
		return offre;
	}
	public void setOffre(List<Offre> offre) {
		this.offre = offre;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(int id) {
		super();
		this.id=id;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String prenom, String nom, String adresse, TypeCompte typeCompte, String mail ) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.typeCompte = typeCompte;
		this.mail = mail;
	}
	public User(int id, String prenom, String nom, String adresse, TypeCompte typeCompte, String mail ,String password,Boolean isActive,int nbabonnee) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.typeCompte = typeCompte;
		this.mail = mail;
		this.password = password;
		this.isActive = isActive;
		this.nbAbonnees = nbabonnee;
	}
	//for login in angular
	public User(int id, String prenom, String nom, String adresse, TypeCompte typeCompte, String mail ,String password,Boolean isActive,int nbabonnee,String CustomerId) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.typeCompte = typeCompte;
		this.mail = mail;
		this.password = password;
		this.isActive = isActive;
		this.nbAbonnees = nbabonnee;
		this.CustomerId = CustomerId;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", adresse=" + adresse + ", gouvernorats="
				+ gouvernorats + ", typeCompte=" + typeCompte + ", mail=" + mail + ", listNotification="
				+ listNotification + ", listActivite=" + listActivite + ", listMessage=" + listMessage + ", listClaim="
				+ listClaim + ", offre=" + offre + ", payements=" + payements + ", numeroCarte=" + numeroCarte
				+ ", isActive=" + isActive + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public TypeCompte getTypeCompte() {
		return typeCompte;
	}
	public void setTypeCompte(TypeCompte typeCompte) {
		this.typeCompte = typeCompte;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getNbAbonnees() {
		return nbAbonnees;
	}
	public void setNbAbonnees(int nbAbonnees) {
		this.nbAbonnees = nbAbonnees;
	}
	
	

}
