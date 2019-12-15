package tn.esprit.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Competence  implements Serializable {
	
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	private int id ;
	private static final long serialVersionUID = 1L;
	private String competences;
	@ManyToOne
	private Candidate candidate;
	
	
	
	public Competence () {
		
	}
	
	public Competence(int id, String competences) {
		super();
		this.id = id;
		this.competences=competences;
		
	}
		
	@Override
	public String toString() {
		
		return super.toString()+"Competence [id=" + id + ", competences=" + competences + ", candidate=" + candidate + "]";
	}

	public String getCompetences() {
		return competences;
	}

	public void setCompetences(String competences) {
		this.competences = competences;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
