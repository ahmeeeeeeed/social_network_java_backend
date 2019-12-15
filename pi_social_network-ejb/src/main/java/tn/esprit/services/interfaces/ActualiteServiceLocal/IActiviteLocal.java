package tn.esprit.services.interfaces.ActualiteServiceLocal;

import java.util.List;

import javax.ejb.Local;



@Local
public interface IActiviteLocal {
	
	List<Object> HistoriqueActivite();

}
