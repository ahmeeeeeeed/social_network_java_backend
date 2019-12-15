package tn.esprit.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Post   implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy= GenerationType.IDENTITY)
	private int id ;
	private String description ;
	
	
	private boolean isActive = true;
	
	
	
	
	

	

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Temporal(TemporalType.DATE)
	private Date datePost ;
	
	

   
	

	public Post () {
	}
	
	public Post(int id, String description, Date datePost) {
		super();
		this.id = id;
		this.description = description;
		this.datePost = datePost;
	}

	
	
	
	public Post(String description, Date datePost) {
		super();
		
		this.description = description;
		this.datePost = datePost;
	}


	public Post(int idPost) {
		this.id=idPost;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + ", datePost=" + datePost + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDatePost() {
		return datePost;
	}
	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}

}
