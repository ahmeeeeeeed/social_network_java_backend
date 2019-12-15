package tn.esprit.ressources;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.entities.Commentaire;
import tn.esprit.services.implimentation.CommentaireImpl;
import tn.esprit.utilities.Secured;

@Path("commentaire")
public class CommentaireWS {
	
	@EJB
	CommentaireImpl commentairews;
	private final String status = "{\"status\":\" commentaire a ete ajoute\"}";
	private final String status3 = "{\"status\":\" commentaire a ete modifie\"}";
	private final String status4 = "{\"status\":\" commentaire a ete supprime\"}";
	private final String status5 = "{\"status\":\" commentaire a ete affecte a un post\"}";
	private  String status2;
	
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("allcommentaires")
	public List<Commentaire> getCommentaire() {
		return  commentairews.GetAll();
	}
	
	@POST
	@Path("addCommentaire")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPost(@QueryParam("description") String description,
			@QueryParam("dateCommentaire") Date dateCommentaire

	) {
		Commentaire p = new Commentaire(description, dateCommentaire);

		String res=commentairews.AddCommentaire(p);
		if(res.equals("COMMENTAIRE EXIST"))
		{
			status2="{\"status\":\"COMMENTAIRE EXIST\"}";
			return Response.status(200).entity(status2).build();
			
		}
				
		 return Response.status(200).entity(status).build();
	}
	
	@PUT
	@Path("updateCommentaire")
	@Produces(MediaType.APPLICATION_JSON)

	public Response updateCommentaire(@QueryParam("id") int id, @QueryParam("description") String description,
			@QueryParam("dateCommentaire") Date dateCommentaire

	) {
		Commentaire c = new Commentaire(id, description, dateCommentaire);
		commentairews.UpdateCommentaire(c);;

		return Response.status(200).entity(status3).build();
	}
	
	@DELETE
	@Path("deleteCommentaire")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCommentaire(@QueryParam("id") int id) {
		commentairews.DeleteCommentaire(id);
		return Response.status(200).entity(status4).build();
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("searchCommentaire")
	public Commentaire SearchCommentaireByDate(@QueryParam("dateCommentaire") Date dateCommentaire) {
		return commentairews.SearchCommentaireByDate(dateCommentaire);
	}
	
	

}
