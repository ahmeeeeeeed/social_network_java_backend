package tn.esprit.ressources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.services.implimentation.ActiviteImpl;
import tn.esprit.utilities.Secured;

@Path("activite")
public class ActiviteWS {
	
	@EJB
	ActiviteImpl activitews;
	//private final String status = "{\"status\":\"ok\"}";
	//private  String status2;
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("allactivities")
	public List<Object> getActivite() {
		return  activitews.HistoriqueActivite();
	}

}
