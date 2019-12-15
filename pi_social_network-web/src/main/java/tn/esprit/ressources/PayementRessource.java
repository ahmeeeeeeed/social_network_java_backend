package tn.esprit.ressources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.services.implimentation.GenererFichePDF;
import tn.esprit.services.implimentation.PaymentService;
import tn.esprit.services.interfaces.PayementServiceRemote;
import tn.esprit.utilities.Secured;;

@Path("payement")

public class PayementRessource {
	@EJB
	PayementServiceRemote payementService = new PaymentService();
	
	////////////////////////ok
	//@Secured
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("payement_history")
	public Response afficherHistoriquePayement(@QueryParam(value = "idUser")int idUser) {
		if(payementService.afficherHistoriquePayement(idUser)==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		if(payementService.afficherHistoriquePayement(idUser).size()==0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();
		else
			return Response.ok(payementService.afficherHistoriquePayement(idUser),MediaType.APPLICATION_JSON).build();
	}
	
	////////////////////////ok
	//@Secured
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("nbr_pub_user_par_mois")
	public Response getNbrPublicationUserParMois(@QueryParam(value = "idUser")int idUser,@QueryParam(value = "mois")int mois) {
		
		if(mois <0 || mois>11) {
			return Response.status(Response.Status.BAD_REQUEST).entity("mauvais arguments !!").build();
		}
		
		return Response.ok(payementService.getNbrPublicationUserParMois(idUser, mois),MediaType.TEXT_PLAIN).build();
	}
	
////////////////////////ok
	//@Secured
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.APPLICATION_XML)
	@Path("payer_premium")
	public Response migrationVersPremium(@QueryParam(value = "idUser")int idUser, 
										@QueryParam(value = "numeroCarte")String numeroCarte,
										@QueryParam(value = "numCVV")int numCVV,
										@QueryParam(value = "year")int year,
										@QueryParam(value = "mounth")int mounth,
										@QueryParam(value = "montant")double montant) {
		
		Date d = new Date();
		d.setMonth(mounth);
		d.setYear(year);
		if(montant<199) {
			return Response.status(Response.Status.BAD_REQUEST).entity("mauvais arguments !!").build();
		}
		

		if(payementService.migrationVersPremium(idUser, numeroCarte, numCVV, d, montant)==false) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("paiement non affécté !!").build();
		}
		
		return Response.ok(Response.Status.ACCEPTED).entity("congratulation vous etes prumium").build();
	}
	
	
	////////////////////////ok
	//@Secured
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	//@Consumes(MediaType.APPLICATION_XML)
	@Path("payer_pub")
	public Response payerPourPublier(@QueryParam(value = "idUser")int idUser,
			@QueryParam(value = "numeroCarte")String numeroCarte,
			@QueryParam(value = "numCVV")int numCVV,
			@QueryParam(value = "year")int year,
			@QueryParam(value = "mounth")int mounth,
			@QueryParam(value = "montant")double montant) {
		
		Date d = new Date();
		d.setMonth(mounth);
		d.setYear(year);
		System.out.println("dateExpiration2:"+d.getMonth());
		
		if(montant<0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("mauvais arguments !!").build();
		}
		
		if(payementService.payerPourPublier(idUser, numeroCarte, numCVV, d, montant)==false) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("paiement non affécté !!").build();
		}
		return Response.ok(Response.Status.ACCEPTED).entity("paiement de poste effécté avec succée !!").build();
 
	}
	
	////////////////////////ok
	//@Secured
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("test_valid_compte")
	public Response testDateValidationComptePremium(@QueryParam(value = "idUser")int idUser) {
		
		if(payementService.testDateValidationComptePremium(idUser)==-99) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("votre compte est deja de type FREE !!").build();
		}
		if(payementService.testDateValidationComptePremium(idUser)<0) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("votre compte premium n'est pas valid maintenant !!").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity("vous etes premium jusqu'à moment ").build();
	}
	


		////////////////////////ok
		//@Secured
		@GET
		@Produces(MediaType.TEXT_PLAIN)
		@Path("test_valid_compte_2")
		public Response testDateValidationComptePremium2(@QueryParam(value = "idUser")int idUser) {
		
		
		return Response.ok(payementService.testDateValidationComptePremium(idUser)).build();
		}
	
	//@Secured
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("change_type_compte")
	public Response changementDeTypeCompteVersFree(@QueryParam(value = "idUser")int idUser) {
		
		if(payementService.testDateValidationComptePremium(idUser)== -99) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity("votre compte est deja de type FREE !!").build();
		}
		if(payementService.changementDeTypeCompteVersFree(idUser)==true) {
			return Response.status(Response.Status.ACCEPTED).entity("votre compte premium n'est pas valid maintenant !!").build();
		}
		return Response.status(Response.Status.NOT_ACCEPTABLE).entity("vous etes premium jusqu'à moment ").build();
	}
	
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("send_bill")
	public Response envoyerFacture() throws IOException, SQLException {
		
		GenererFichePDF genererPDF = new GenererFichePDF();
		if(payementService.afficherHistoriquePayement(idUser)==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		if(payementService.afficherHistoriquePayement(idUser).size()==0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();
		else
			return Response.ok(payementService.afficherHistoriquePayement(idUser),MediaType.APPLICATION_JSON).build();
		
		if(genererPDF.pdf() == true)
	
			return Response.status(Response.Status.ACCEPTED).entity("une facture sera généré !!").build();
		
		return Response.status(Response.Status.NO_CONTENT).entity("erreur lors de la generation de la facture ").build();
		
	}
		*/
	
	

}
