package backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import converter.UserJSONconverter;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author malik
 */
//specifying the path of the webservice
@Path("User")
public class UserResource {
    
    UserJSONconverter converter = new UserJSONconverter();
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");
    Users users = new Users(factory);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of backend.UserResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("allusers")
    //below describes (Produces) what kind of resource we will get, here I chose json
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsersAsJson() {
        //we need to return the response we want
        return Response.ok().entity(gson.toJson(users.getAllUsersFromDTO())).build();
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //Tester
        return "{\"message\":\"Hello from Rest service\"}";
    }
    
    
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    /**
     * PUT method for updating or creating an instance of UserResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
