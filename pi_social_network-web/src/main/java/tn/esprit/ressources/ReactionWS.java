package tn.esprit.ressources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.services.implimentation.ReactionImpl;
import tn.esprit.utilities.Secured;

@Path("reaction")
public class ReactionWS {
	@EJB
	ReactionImpl reactionws;
	

	private final String status = "{\"status\":\" l'user a liker la post\"}";
	private  String status2;
	
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("NBlikePosts")
	public List<Object> getReactionLike() {
		return  reactionws.NbLikePosts();
	}

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("NBDislikePosts")
	public List<Object> getReactionDislike() {
		return  reactionws.NbDislikePosts();
	}
	
	
}
