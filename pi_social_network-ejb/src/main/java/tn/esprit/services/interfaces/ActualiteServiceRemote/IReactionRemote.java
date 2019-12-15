package tn.esprit.services.interfaces.ActualiteServiceRemote;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Reaction;

@Remote
public interface IReactionRemote {
	
	List<Object> NbLikePosts();
	List<Object> NbDislikePosts();
	

}
