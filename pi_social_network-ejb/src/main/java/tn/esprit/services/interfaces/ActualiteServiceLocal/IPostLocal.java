package tn.esprit.services.interfaces.ActualiteServiceLocal;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;


import tn.esprit.entities.Post;

@Local
public interface IPostLocal {
	
	String AddPost(Post post);

	void DeletePost(int id);

	void UpdatePost(Post post);

	List<Post> GetAll();

	Post SearchPostByDate(Date date);
	
	public String signalerPost(Post post, int idPost);
	
	public void bannerPost(int idPost);
	
	public List<Object> MostRequestedPostBYRATE();
	
	public int getNBPostsParMois (int  mois);
	
	
	
	

}
