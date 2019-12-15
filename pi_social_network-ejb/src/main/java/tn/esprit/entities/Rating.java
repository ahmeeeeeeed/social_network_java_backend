package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Rating   implements Serializable {
	
	private static final long serialVersionUID = 7474685471186041695L;
	private int nombreRate ;
	
	@EmbeddedId
	private RatingPK ratingPK;
	
	@ManyToOne
	@JoinColumn(name = "idUser",referencedColumnName ="id",insertable=false,updatable=false)
	private User user ;
	
	@ManyToOne
	@JoinColumn(name = "idPost",referencedColumnName ="id",insertable=false,updatable=false)
	private Post post ;
	
	public Rating () {
		
	}

	public int getNombreRate() {
		return nombreRate;
	}

	public void setNombreRate(int nombreRate) {
		this.nombreRate = nombreRate;
	}

	public RatingPK getRatingPK() {
		return ratingPK;
	}

	public void setRatingPK(RatingPK ratingPK) {
		this.ratingPK = ratingPK;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Rating(int nombreRate, RatingPK ratingPK, User user, Post post) {
		super();
		this.nombreRate = nombreRate;
		this.ratingPK = ratingPK;
		this.user = user;
		this.post = post;
	}

	@Override
	public String toString() {
		return "Rating [nombreRate=" + nombreRate + ", ratingPK=" + ratingPK + ", user=" + user + ", post=" + post
				+ "]";
	}
	
	

}
