package tn.esprit.services.interfaces.ActualiteServiceRemote;

import java.util.List;

import javax.ejb.Remote;



@Remote
public interface IActiviteRemote {
	
	List<Object> HistoriqueActivite();

}
