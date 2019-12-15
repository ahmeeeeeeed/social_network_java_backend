package tn.esprit.services.interfaces.ActualiteServiceLocal;

import java.util.List;

import javax.ejb.Local;


import tn.esprit.entities.Reaction;

@Local
public interface IReactionLocal {

	List<Object> NbLikePosts( );
	List<Object> NbDislikePosts();
	
}
