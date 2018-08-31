/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.iteria.parquesbackend.service;

import co.com.iteria.parquesbackend.Parks;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Diego Fernando Segura
 */
@Stateless
@Path("/parks")
public class ParksFacadeREST extends AbstractFacade<Parks> {

    @PersistenceContext(unitName = "co.com.iteria_parquesBackend_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ParksFacadeREST() {
        super(Parks.class);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject crear(@Context HttpServletResponse resp ,Parks entity) {
        
        int ultimoParque = last() + 1;
        
        entity.setStatus("Open");
        entity.setId(String.valueOf(ultimoParque));
        
        create(entity);
        
        resp.setHeader("Location", "/parks/"+String.valueOf(ultimoParque));
        
        JsonObject json = Json.createObjectBuilder()
                .add("id", entity.getId())
                .add("name", entity.getName())
                .add("state", entity.getState())
                .add("capacity", Integer.parseInt(entity.getCapacity()))
                .add("status", entity.getStatus())
                .build();
        
        return json;       
    }
    
    @Override
    public void create(Parks entity) {
        
        super.create(entity);    
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") String id, Parks entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Parks> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /******************************************************************************/
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject encontrarPorID(@PathParam("id") String id) {

        Parks parqueTemp = super.find(id);
        JsonObject json = Json.createObjectBuilder()
                .add("id", parqueTemp.getId())
                .add("name", parqueTemp.getName())
                .add("state", parqueTemp.getState())
                .add("capacity", Integer.parseInt(parqueTemp.getCapacity()))
                .add("status", parqueTemp.getStatus())
                .build();
        
        return json;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray Todos(@DefaultValue("null") @QueryParam("status") String estado) {
        
        List<Parks> lstparks = new ArrayList<Parks>();
        
        if(estado.equalsIgnoreCase("null"))
        {
            lstparks = super.findAll();
        }
        else
        {
            lstparks = super.findByEstado(estado);
        }
        
        JsonArrayBuilder parksArray = Json.createArrayBuilder();
        for (Parks park : lstparks) {
            JsonObjectBuilder json = Json.createObjectBuilder()
                    .add("id", park.getId())
                    .add("name", park.getName())
                    .add("state", park.getState())
                    .add("capacity", park.getCapacity())
                    .add("status", park.getStatus());
            parksArray.add(json);
        }
        
        return parksArray.build();
    }
    
}
