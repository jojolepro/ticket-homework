/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojoalex.ticket.controller.endpoints;

import com.jojoalex.ticket.controller.utils.EncryptionException;
import com.jojoalex.ticket.controller.utils.EncryptionUtils;
import com.jojoalex.ticket.controller.utils.RESTUtils;
import com.jojoalex.ticket.controller.utils.SessionUtils;
import com.jojoalex.ticket.controller.utils.TokenStore;
import com.jojoalex.ticket.model.dao.UserDAO;
import com.jojoalex.ticket.model.dto.TicketDTO;
import com.jojoalex.ticket.model.dto.UserDTO;
import com.jojoalex.ticket.model.entities.Ticket;
import com.jojoalex.ticket.model.entities.User;
import java.util.ArrayList;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

/**
 *
 * @author jojolepro
 */
@Path("users")
public class UserFacadeRest {

    private final UserDAO userDAO;

    public UserFacadeRest() {
        userDAO = new UserDAO();

    }

    @GET
    @Path("login")
    public String login(@Context HttpServletResponse ans,
            @HeaderParam("username") String userName,
            @HeaderParam("password") String password) throws EncryptionException {
        
        if(userName == null || userName.isEmpty() || password == null || password.isEmpty())
            return "Missing credentials";
        
        User user = userDAO.getUserByUserNameAndPassword(userName, password);
        if (user != null) {
            String t = TokenStore.createToken(user);
            ans.setHeader("token", t);
            return "OK";
        }
        return "Login ou Mot de passe incorrect";
    }
    
    @GET
    @Path("logout")
    public String logout(@Context HttpServletRequest req) throws EncryptionException {
        if(TokenStore.validateRequest(req)){
            TokenStore.remove(req.getHeader("token"));
            return "OK";
        }
        return "Not logged in";
    }
    
    /*@GET
    public String findAll(@HeaderParam("token") String token) {
        if (TokenStore.validateToken(token)) {
            ArrayList<User> list = userDAO.findListOfUsersDTO();
            return RESTUtils.JSONFactory.toJson(list);
        } else {
            return "Invalid Token";
        }
    }*/
    
    /*@GET
    @Path("{id}")
    public String findById(@HeaderParam("token") String token, @PathParam("id") Integer id) {
        if (TokenStore.validateToken(token)) {
            return RESTUtils.JSONFactory.toJson(userDAO.getUserById(id));
        } else {
            return "Invalid Token";
        }
    }*/
    
    @GET
    @Path("id/{id}")
    public String findById(@PathParam("id") Integer id) {
        //if (TokenStore.validateToken(token)) {
            return RESTUtils.JSONFactory.toJson(new UserDTO(userDAO.getUserById(id)));
        /*} else {
            return "Invalid Token";
        }*/
    }
    @GET
    @Path("admin/{admin}")
    public String findById(@PathParam("admin") Boolean admin) {
        ArrayList<User> user = userDAO.getUsers();
        ArrayList<UserDTO> o = new ArrayList<>();
        for(User u : user){
            if(u.isAdmin() == admin){
                o.add(new UserDTO(u));
            }
        }
        return RESTUtils.JSONFactory.toJson(o);
    }
    
    @POST
    @Path("new")
    public String addUser(@Context HttpServletRequest req,@FormParam("admin") boolean admin,@FormParam("email") String email,
            @FormParam("fullname") String fullname,@FormParam("password") String password,@FormParam("phone") String phone) throws EncryptionException{
        User u = TokenStore.userFromRequest(req);
        if(u != null && u.isAdmin()){
            EncryptionUtils eu = new EncryptionUtils();
            User u2 = new User();
            u2.setAdmin(admin);
            u2.setEmail(email);
            u2.setFullname(fullname);
            u2.setPassword(eu.encrypt(password));
            u2.setPhone(phone);
            
            userDAO.createUser(u2);
            return "OK";
        }else{
            return "Not authorised";
        }
    }

}