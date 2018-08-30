/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.iteria.parquesbackend.service;

import co.com.iteria.parquesbackend.Parks;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Usuario1
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
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Parks entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Parks entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Parks find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Parks> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
    public JsonObject encontrarPorID(@PathParam("id") Integer id) {

        Parks parqueTemp = super.find(id);
        JsonObject json = Json.createObjectBuilder()
                .add("id", parqueTemp.getId())
                .add("name", parqueTemp.getName())
                .add("state", parqueTemp.getState())
                .add("capacity", parqueTemp.getCapacity())
                .add("status", parqueTemp.getStatus())
                .build();
        
        return json;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public JsonArray Todos() {
        List<Parks> lstparques = super.findAll();
        JsonArrayBuilder parqueArray = Json.createArrayBuilder();
        for (Parks parque : lstparques) {
            JsonObjectBuilder json = Json.createObjectBuilder()
                    .add("id", parque.getId())
                    .add("name", parque.getName())
                    .add("state", parque.getState())
                    .add("capacity", parque.getCapacity())
                    .add("status", parque.getStatus());
            parqueArray.add(json);
        }

        return parqueArray.build();
    }
    
}
