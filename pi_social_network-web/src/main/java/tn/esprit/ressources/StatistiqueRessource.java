package tn.esprit.ressources;

import javax.ejb.EJB;
import javax.ws.rs.Path;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.esprit.services.implimentation.StatistiqueService;
import tn.esprit.services.interfaces.StatistiqueServiceRemote;
import tn.esprit.utilities.Secured;
@Path("statistique")

public class StatistiqueRessource {
	
	@EJB
	
	StatistiqueServiceRemote statistiqueService = new StatistiqueService();
	
	//@Secured
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	@Path("nbr_user_postuler_a_offre")
	public Response getNbrUserPostuleAOffre(@QueryParam(value = "idOffre")int idOffre) {


		/*if(statistiqueService.getNbrUserPostuleAOffre(idOffre)==0)
			return Response.status(Response.Status.NO_CONTENT).entity("pas de candidat postulés & cet offre").build();*/
		
		return Response.ok(statistiqueService.getNbrUserPostuleAOffre(idOffre)).build();
	}
	//@Secured
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	@Path("nbr_pub_offre_mois")
	public Response getNbrPublicationOffreParMois(@QueryParam(value = "idUser")int idUser,@QueryParam(value = "mois") int mois) {
		if(mois <0 || mois>11) {
			return Response.status(Response.Status.BAD_REQUEST).entity("mauvais arguments !!").build();
		}
		/*if(statistiqueService.getNbrPublicationOffreParMois(idUser, mois)==0)
			return Response.status(Response.Status.NO_CONTENT).entity("pas de publication d'offre ce mois").build();*/

		return Response.ok(statistiqueService.getNbrPublicationOffreParMois(idUser, mois)).build();

	}
	//@Secured
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("nbr_pub_chauqe_mois")
	public Response getNbrPublicationChaqueMois() {

		if(statistiqueService.getNbrPublicationChaqueMois()==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		if(statistiqueService.getNbrPublicationChaqueMois().size()==0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();
		else
			return Response.ok(statistiqueService.getNbrPublicationChaqueMois(),MediaType.APPLICATION_JSON).build();
	}
	//@Secured
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get_nbr_payment_premium_mois")
	public Response getNbrPayementPremiumChaqueMois() {

		

		if(statistiqueService.getNbrPayementPremiumChaqueMois()==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		/*if(statistiqueService.getNbrPayementPremiumChaqueMois().size()==0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();*/
		else
			return Response.ok(statistiqueService.getNbrPayementPremiumChaqueMois(),MediaType.APPLICATION_JSON).build();	
		}
	//@Secured
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("user_skills_required")
	public Response doStatUserSkillsMostRequiredPerMounth(@QueryParam(value = "listcompetence")String listcompetence,@QueryParam(value = "mois") int mois) {
		if(mois <0 || mois>11) {
			return Response.status(Response.Status.BAD_REQUEST).entity("mauvais arguments !!").build();
		}
		
		return null;
		
	}
	//@Secured
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	@Path("nbr_total_rec_mois")
	public Response doStatNbrToutRecrutementParMois(@QueryParam(value = "mois")int mois) {
		if(mois <0 || mois>11) {
			return Response.status(Response.Status.BAD_REQUEST).entity("mauvais arguments !!").build();
		}
		/*if(statistiqueService.doStatNbrToutRecrutementParMois(mois)==0)
			return Response.status(Response.Status.NO_CONTENT).entity("pas de candidats recrutés ce mois").build();*/
		

		return Response.ok(statistiqueService.doStatNbrToutRecrutementParMois(mois)).build();	
	

	}
	//@Secured
	@GET
	//@Produces(MediaType.APPLICATION_JSON)
	@Path("nbr_rec_entre_mois")
	public Response doStatNbrRecrutementEntrepriseParMois(@QueryParam(value = "mois")int mois,@QueryParam(value = "idCompanyManager") int idCompanyManager) {
		if(mois <0 || mois>11) {
			return Response.status(Response.Status.BAD_REQUEST).entity("mauvais arguments !!").build();
		}
		/*if(statistiqueService.doStatNbrRecrutementEntrepriseParMois(mois, idCompanyManager)==0)
			return Response.status(Response.Status.NO_CONTENT).entity("pas de candidats recrutés ce mois").build();*/
		

		return Response.ok(statistiqueService.doStatNbrRecrutementEntrepriseParMois(mois, idCompanyManager)).build();
	}
	//@Secured
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("nbr_rec_entre_chaque_mois")
	public Response doStatNbrRecrutementEntrepriseChaqueMois(@QueryParam(value = "idCompanyManager") int idCompanyManager) {

		if(statistiqueService.doStatNbrRecrutementEntrepriseChaqueMois(idCompanyManager)==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		if(statistiqueService.doStatNbrRecrutementEntrepriseChaqueMois(idCompanyManager).size()==0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();
		else
			return Response.ok(statistiqueService.doStatNbrRecrutementEntrepriseChaqueMois(idCompanyManager),MediaType.APPLICATION_JSON).build();	
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getListOffreParCompany")
	public Response getListOffreParCompany(@QueryParam(value = "idCompanyManager") int idCompanyManager) {

		if(statistiqueService.getListOffreParCompany(idCompanyManager)==null) {
			return Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		if(statistiqueService.getListOffreParCompany(idCompanyManager).length==0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();
		else
			return Response.ok(statistiqueService.getListOffreParCompany(idCompanyManager),MediaType.APPLICATION_JSON).build();	
	}

}
