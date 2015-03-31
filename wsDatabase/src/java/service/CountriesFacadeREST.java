/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import client.JerseyClient;
import entities.Countries;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author oracle
 */
@Stateless
@Path("entities.countries")
public class CountriesFacadeREST extends AbstractFacade<Countries> {
    @PersistenceContext(unitName = "wsDatabasePU")
    private EntityManager em;

    private Countries my_country = new Countries();
    
    
    
    public CountriesFacadeREST() {
        super(Countries.class);
    }

    @POST
    @Path("/create")
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Countries entity) {
       
        super.create(entity);
        
        
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Countries entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Countries find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({ "application/json"})
    public List<Countries> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Countries> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("/saveCountry/{id}/{name}")
    @Produces("text/plain")
    public String saveNewCountry(@PathParam("id") String id, @PathParam("name") String name) {
       Countries a = new Countries() ;
       a.setCountryId(id);
       a.setCountryName(name);
       if (!my_country.saveCountry(a)) {
           return "wrong";
       }else{
       return "done";
       }
       
    
}

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

}
