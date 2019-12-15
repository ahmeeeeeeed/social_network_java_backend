package tn.esprit.services.interfaces.ActualiteServiceRemote;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IRatingRemote {
	
	List<Object> NbRatePosts( );
	public int getMostResquestedPostBYRate();

}
