package tn.esprit.services.interfaces.ActualiteServiceLocal;

import java.util.List;

import javax.ejb.Local;

@Local
public interface IRatingLocal {

	List<Object> NbRatePosts( );
	public int getMostResquestedPostBYRate();
	
}
