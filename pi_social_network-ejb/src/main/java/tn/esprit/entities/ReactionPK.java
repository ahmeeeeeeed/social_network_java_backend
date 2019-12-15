package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ReactionPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2406165978009316090L;
	private int idUser;
	private int idPost;
	
	public ReactionPK() {}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdPost() {
		return idPost;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	public ReactionPK(int idUser, int idPost) {
		super();
		this.idUser = idUser;
		this.idPost = idPost;
	}
	
	@Override
	public String toString() {
		return "ReactionPK [idUser=" + idUser + ", idPost=" + idPost + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPost;
		result = prime * result + idUser;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReactionPK other = (ReactionPK) obj;
		if (idPost != other.idPost)
			return false;
		if (idUser != other.idUser)
			return false;
		return true;
	}

	
}
