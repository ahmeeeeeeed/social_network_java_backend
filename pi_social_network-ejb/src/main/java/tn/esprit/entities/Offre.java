package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Offre  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	private int id ;
	private String contenu ;
	
	@Temporal(TemporalType.DATE)
	private Date date ;
	private String niveauExp;
	
	@ManyToMany(mappedBy="offre",fetch=FetchType.EAGER)
	private List<User> user;
	
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public Offre () {
		
	}

	public Offre(int id, String contenu, Date date, String niveauExp) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.date = date;
		this.niveauExp = niveauExp;
	}
	
	public Offre(int id, String contenu, Date date, String niveauExp,List<User> user) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.date = date;
		this.niveauExp = niveauExp;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Offre [id=" + id + ", contenu=" + contenu + ", date=" + date + ", niveauExp=" + niveauExp + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNiveauExp() {
		return niveauExp;
	}

	public void setNiveauExp(String niveauExp) {
		this.niveauExp = niveauExp;
	}

	
}
