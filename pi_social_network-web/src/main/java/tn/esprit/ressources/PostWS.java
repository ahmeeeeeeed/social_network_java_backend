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

import tn.esprit.entities.Post;
import tn.esprit.services.implimentation.PostImpl;
import tn.esprit.utilities.Secured;

@Path("post")
public class PostWS {
	
	@EJB
	PostImpl postws;
	private final String status = "{\"status\":\" post a ete ajoute\"}";
	private final String status3 = "{\"status\":\" post a ete modifie\"}";
	private final String status4 = "{\"status\":\" post a ete supprime\"}";
	private final String status5 = "{\"status\":\" post ne contient pas des mots inapproprie\"}";
	private final String status6 = "{\"status\":\" la post a ete banirer\"}";
	private  String status2;
	
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("allposts")
	public List<Post> getPost() {
		return  postws.GetAll();
	}
	
	@POST
	@Path("addPost")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPost(@QueryParam("description") String description,
			@QueryParam("datePost") Date datePost

	) {
		Post p = new Post(description, datePost);

		String res=postws.AddPost(p);
		if(res.equals("POST EXIST"))
		{
			status2="{\"status\":\"POST EXIST\"}";
			return Response.status(200).entity(status2).build();
			
		}
				
		 return Response.status(200).entity(status).build();
	}
	
	@PUT
	@Path("updatePost")
	@Produces(MediaType.APPLICATION_JSON)

	public Response updatePost(@QueryParam("id") int id, @QueryParam("description") String description,
			@QueryParam("datePost") Date datePost

	) {
		Post p = new Post(id, description, datePost);
		postws.UpdatePost(p);

		return Response.status(200).entity(status3).build();
	}
	
	@DELETE
	@Path("deletePost")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePost(@QueryParam("id") int id) {
		postws.DeletePost(id);
		return Response.status(200).entity(status4).build();
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("searchPost")
	public Post SearchPostByDate(@QueryParam("datePost") Date datePost) {
		return postws.SearchPostByDate(datePost);
	}
	
	
	@POST
	@Path("signalerPost")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signalerPost(@QueryParam ("idPost") int idPost ,@QueryParam("description") String description,
			@QueryParam("datePost") Date datePost

	) {
		
		Post p = new Post(idPost ,description, datePost);
		//p.getUser().setId(idUser);

		String res=postws.signalerPost(p,idPost);
		if(res.equals("POST CONTIENT DES MOTS INAPPROPRIE"))
		{
			status2="{\"status\":\"POST CONTIENT DES MOTS INAPPROPRIE\"}";
			return Response.status(200).entity(status2).build();
			
		}
				
		 return Response.status(200).entity(status5).build();
	}
	
	@PUT
	@Path("bannerPost")
	@Produces(MediaType.APPLICATION_JSON)
	public Response bannerPost(@QueryParam("id") int idPost) {
		postws.bannerPost(idPost);
		return Response.status(200).entity(status6).build();
	}

	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("MostRequestedPost")
	public List<Object> mostrequestedPostbyRate() {
		return postws.MostRequestedPostBYRATE();
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	@Path("nbPostBYMonth")
	public int NbServiceUsed(@QueryParam("mois") int mois) {
		return postws.getNBPostsParMois(mois);
	}

	

}
