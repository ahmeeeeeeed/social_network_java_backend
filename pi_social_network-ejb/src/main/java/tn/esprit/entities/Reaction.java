package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.ws.rs.QueryParam;

@Entity
public class Reaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7474685471186041695L;
	
	private String etat ;
	
	@EmbeddedId
	private ReactionPK reactionPK;
	

	
	@ManyToOne
	@JoinColumn(name = "idUser",referencedColumnName ="id", nullable = false ,insertable=false,updatable=false)
	private User user ;
	
	@ManyToOne
	@JoinColumn(name = "idPost",referencedColumnName ="id",nullable = false,insertable=false,updatable=false)
	private Post post ;
	
	
	public Reaction () {}

	

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public ReactionPK getReactionPK() {
		return reactionPK;
	}

	public void setReactionPK(ReactionPK reactionPK) {
		this.reactionPK = reactionPK;
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

	public Reaction( String etat, ReactionPK reactionPK, User user , Post post) {
		super();
		
		this.etat = etat;
		this.reactionPK = reactionPK;
		this.user = user;
		this.post = post;
	}
	

	public Reaction (int idUser , int idPost , String etat)
	{
		this.getUser().setId(idUser);
		this.getPost().setId(idPost);
		this.setEtat(etat);
		
	}


	public Reaction(String etat, ReactionPK reactionPK) {
		super();
		this.etat = etat;
		this.reactionPK = reactionPK;
	}



	public Reaction(User user,  Post post, String etat) {
		super();
		
		this.user = user;
		this.post = post;
		this.etat = etat;
	}



	public Reaction(String etat2, User u, Post pt) {
		this.etat=etat2;
		this.user=u;
		this.post=pt;
	}

	 


	

	



	


	


	


	



	@Override
	public String toString() {
		return "Reaction [etat=" + etat + ", reactionPK=" + reactionPK + ", user=" + user + ", post=" + post + "]";
	}

	
	
	
	

}
