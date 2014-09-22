/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hp.hpl.jena.tdb.TDB;
import domen.Find;
import domen.Organization;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import jsonParser.OrganizationRestParser;
import persistence.RDFModel;
import queries.Queries;
import queries.QueriesDetails;
import queries.SprqlService;

/**
 * REST Web Service
 *
 * @author asceric
 */
@Path("MyPath")
public class MyPathResource {

    @Context
    private UriInfo context;
    public Queries qu = new QueriesDetails();
    public SprqlService sp = new SprqlService();

    /**
     * Creates a new instance of MyPathResource
     */
    public MyPathResource() {
    }

    
   
    @GET
    @Produces("text/plain")
    public String getText() {
        //TODO return proper representation object
        return "Rest servis....";
    }

    @Path("/employees")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTypeOfEmployee() {
        Collection<String> lista = qu.getEmployees();

        JsonArray employeeArray = new JsonArray();

        for (String string : lista) {

            JsonObject jsonem = new JsonObject();
            jsonem.addProperty("jobTitle", string);
            employeeArray.add(jsonem);

        }
        return employeeArray.toString();

    }

    @Path("/countries")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCountries() {
        Collection<String> lista = qu.getAllCountries();

        JsonArray countriesArray = new JsonArray();
        for (String string : lista) {
            JsonObject jsonco = new JsonObject();
            jsonco.addProperty("location", string);
            countriesArray.add(jsonco);

        }
        return countriesArray.toString();

    }

    @Path("/findOrg")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String findOrg(Find f) throws Exception {
        if (f.getCountry().equals("all")) {
            f.setCountry("");
        }

        if (f.getJobOfEmployee().equals("all")) {
            f.setJobOfEmployee("");
        }

        Collection<Organization> organizations = qu.getOrganizations(f);
        System.out.println("FOUND "+f.getCountry());
        System.out.println("FOUND job"+f.getJobOfEmployee());
        
        if (organizations != null && !organizations.isEmpty()) {
            JsonArray orgarr = new JsonArray();

            for (Organization o : organizations) {

                JsonObject orgJson = OrganizationRestParser.serialize(o);
                orgarr.add(orgJson);
            }

            return orgarr.toString();
        }

        throw new WebApplicationException(Response.Status.NO_CONTENT);

    }

    /**
     * PUT method for updating or creating an instance of MyPathResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
}
