package tn.esprit.ressources;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.services.implimentation.ReclamationService;
import tn.esprit.services.interfaces.ReclamationServiceRemote;
import tn.esprit.utilities.Secured;

@Path("reclamation")

public class ReclamationRessource {
	@EJB
	ReclamationServiceRemote reclamationService = new ReclamationService();
	
	///////////////////////////////////ok
	//@Secured
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.APPLICATION_XML)
	@Path("ajouter_reclamation")
	public Response ajouterReclamation(@QueryParam(value = "idUser")int idUser,@QueryParam(value = "description") String description) {
		
		
		reclamationService.ajouterReclamation(idUser, description);
		return Response.ok(Response.Status.ACCEPTED).entity("reclamation ajoutée avec succés").build();

	}
	
	////////////////////////////ok
	//@Secured
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("claim_history")
	public Response afficherReclamations() {
		if(reclamationService.afficherReclamations()==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		if(reclamationService.afficherReclamations().size()==0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();
		else
			return Response.ok(reclamationService.afficherReclamations(),MediaType.APPLICATION_JSON).build();
	}
	
////////////////////////////ok
	
	//@Secured
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.APPLICATION_XML)
	@Path("signaler")
	public Response signalerCompte(@QueryParam(value = "idUser")int idUser,@QueryParam(value = "description")String description) {
		
		int test = reclamationService.signalerCompte(idUser, description);
		
		if(test == 0) {
			return Response.status(Response.Status.CREATED).entity("un avertissement de signalisation sera envoyé vers le destinataire !").build();
		}
		if(test == 1)
			return Response.ok(Response.Status.ACCEPTED).entity("le destinataire sera bloqué").build();
		if(test == -1)
			return Response.ok(Response.Status.ACCEPTED).entity("le destinataire est déjà bloqué").build();
		
		return Response.status(Response.Status.BAD_REQUEST).entity("mauvais arguments !!").build();

		
	}
	
	//////////////////////////////ok
	//@Secured
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("notification_history")
	public Response afficherNotificationByUser(@QueryParam(value = "idUser")int idUser) {
	
		if(reclamationService.afficherNotificationByUser(idUser)==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		if(reclamationService.afficherNotificationByUser(idUser).size()==0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();
		else
			return Response.ok(reclamationService.afficherNotificationByUser(idUser),MediaType.APPLICATION_JSON).build();
	}
	
	////////////////////////////////////ok
	//@Secured
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("verify_claim")
	public Response verifierReclamation(@QueryParam(value = "idClaim")int idClaim) {
		
		boolean test = reclamationService.verifierReclamation(idClaim);
		if(test==true)
			return Response.ok(Response.Status.ACCEPTED).entity("reclamation vérifiée").build();
		 if(test==false)
			return Response.status(Response.Status.NOT_MODIFIED).entity("reclamation est déjà vérifiée !!").build();
		return Response.status(Response.Status.BAD_REQUEST).entity("mauvais arguments !!").build();

			

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("users")
	public Response getUsers() {
		if( reclamationService.getUsers()==null  ) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		if( reclamationService.getUsers().size()==0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();
		else
			return Response.ok(reclamationService.getUsers(),MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user_by_id")
	public Response getUserById(@QueryParam(value = "idUser")int idUser) {
		if( reclamationService.getUserById(idUser)==null  ) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		
		else
			return Response.ok(reclamationService.getUserById(idUser),MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user_by_email_password")
	public Response getUserByEmailAndPaswword(@QueryParam(value = "email")String email,@QueryParam(value = "password")String password) {
		if( reclamationService.getUserByEmailAndPaswword(email,password)==null  ) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		
		else
			return Response.ok(reclamationService.getUserByEmailAndPaswword(email,password),MediaType.APPLICATION_JSON).build();
	}
	
	//@Secured
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("claim_history_id")
		public Response afficherReclamations(@QueryParam(value = "idUser")int idUser) {
			if(reclamationService.afficherReclamations(idUser)==null) {
				return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
			}
			if(reclamationService.afficherReclamations(idUser).size()==0)
				return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();
			else
				return Response.ok(reclamationService.afficherReclamations(idUser),MediaType.APPLICATION_JSON).build();
		}
	

}
