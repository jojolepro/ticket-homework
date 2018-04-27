/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojoalex.ticket.controller.endpoints;

import com.jojoalex.ticket.controller.utils.RESTUtils;
import com.jojoalex.ticket.model.dao.TicketDAO;
import com.jojoalex.ticket.model.dto.TicketDTO;
import com.jojoalex.ticket.model.dto.TicketUpdateDTO;
import com.jojoalex.ticket.model.entities.Ticket;
import com.jojoalex.ticket.model.entities.TicketUpdate;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author jojolepro
 */
@Path("tickets")
public class TicketFacadeRest {

    private final TicketDAO ticketDAO;

    public TicketFacadeRest() {
        ticketDAO = new TicketDAO();

    }

    /*@GET
    public String findAll(@HeaderParam("token") String token) {
        if (TokenStore.validateToken(token)) {
            ArrayList<CustomerDTO> list = customerDAO.findListOfCustomersDTO();
            return RESTUtils.JSONFactory.toJson(list);
        } else {
            return "Invalid Token";
        }
    }*/
    @GET
    @Path("opened")
    public String ticketOpened(){
        ArrayList<Ticket> t = ticketDAO.findListOfTickets();
        ArrayList<TicketDTO> o = new ArrayList<>();
        for(Ticket ti : t){
            if(ti.getUserByClosedBy() == null){
                o.add(new TicketDTO(ti));
            }
        }
        
        return RESTUtils.JSONFactory.toJson(o);
    }
    
    @GET
    @Path("closed")
    public String ticketClosed(){
        ArrayList<Ticket> t = ticketDAO.findListOfTickets();
        ArrayList<TicketDTO> o = new ArrayList<>();
        for(Ticket ti : t){
            if(ti.getUserByClosedBy() != null){
                o.add(new TicketDTO(ti));
            }
        }
        
        return RESTUtils.JSONFactory.toJson(o);
    }
    
    @GET
    @Path("user/{userid}")
    public String ticketForUser(@PathParam("userid") int userid){
        ArrayList<Ticket> t = ticketDAO.findListOfTickets();
        ArrayList<TicketDTO> o = new ArrayList<>();
        for(Ticket ti : t){
            if(ti.getUserByFor().getId() == userid){
                o.add(new TicketDTO(ti));
            }
        }
        
        return RESTUtils.JSONFactory.toJson(o);
    }
    
    @GET
    @Path("msg/{id}")
    public String ticketMsgForTicket(@PathParam("id") int id){
        ArrayList<TicketUpdate> t = ticketDAO.findListOfTicketUpdate();
        ArrayList<TicketUpdateDTO> o = new ArrayList<>();
        for(TicketUpdate ti : t){
            if(ti.getTicket().getId() == id){
                o.add(new TicketUpdateDTO(ti));
            }
        }
        
        return RESTUtils.JSONFactory.toJson(o);
    }
    
    @GET
    @Path("id/{id}")
    public String ticketForId(@PathParam("id") int id){
        Ticket t = ticketDAO.getTicketByID((short)id);
        if(t == null){
            return null;
        }
        return RESTUtils.JSONFactory.toJson(new TicketDTO(t));
    }
    
    public void createTicket(){
        
    }
    
    public void getTicket(int id){
        
    }
    
    public void closeTicket(int id){
        
    }

}