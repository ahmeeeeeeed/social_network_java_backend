package tn.esprit.ressources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.services.implimentation.RatingImpl;
import tn.esprit.utilities.Secured;
@Path("rating")
public class RatingWS {
	@EJB
	RatingImpl ratingws;
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("NBRatingPosts")
	public List<Object> getNBRating() {
		return  ratingws.NbRatePosts();
	}

}
