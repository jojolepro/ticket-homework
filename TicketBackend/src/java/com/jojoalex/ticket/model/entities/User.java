package com.jojoalex.ticket.model.entities;
// Generated 27-Apr-2018 2:24:39 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="User"
    ,catalog="tickets"
    , uniqueConstraints = @UniqueConstraint(columnNames="email") 
)
public class User  implements java.io.Serializable {


     private Integer id;
     private String fullname;
     private String password;
     private String email;
     private boolean admin;
     private String phone;
     private Set<Ticket> ticketsForFor = new HashSet<Ticket>(0);
     private Set<Ticket> ticketsForClosedBy = new HashSet<Ticket>(0);
     private Set<TicketUpdate> ticketUpdates = new HashSet<TicketUpdate>(0);
     private Set<Ticket> ticketsForOpenedBy = new HashSet<Ticket>(0);

    public User() {
    }

	
    public User(String fullname, String email, boolean admin, String phone) {
        this.fullname = fullname;
        this.email = email;
        this.admin = admin;
        this.phone = phone;
    }
    public User(String fullname, String password, String email, boolean admin, String phone, Set<Ticket> ticketsForFor, Set<Ticket> ticketsForClosedBy, Set<TicketUpdate> ticketUpdates, Set<Ticket> ticketsForOpenedBy) {
       this.fullname = fullname;
       this.password = password;
       this.email = email;
       this.admin = admin;
       this.phone = phone;
       this.ticketsForFor = ticketsForFor;
       this.ticketsForClosedBy = ticketsForClosedBy;
       this.ticketUpdates = ticketUpdates;
       this.ticketsForOpenedBy = ticketsForOpenedBy;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="fullname", nullable=false, length=100)
    public String getFullname() {
        return this.fullname;
    }
    
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    
    @Column(name="password", length=200)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    
    @Column(name="email", unique=true, nullable=false, length=200)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="admin", nullable=false)
    public boolean isAdmin() {
        return this.admin;
    }
    
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    
    @Column(name="phone", nullable=false)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="userByFor")
    public Set<Ticket> getTicketsForFor() {
        return this.ticketsForFor;
    }
    
    public void setTicketsForFor(Set<Ticket> ticketsForFor) {
        this.ticketsForFor = ticketsForFor;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="userByClosedBy")
    public Set<Ticket> getTicketsForClosedBy() {
        return this.ticketsForClosedBy;
    }
    
    public void setTicketsForClosedBy(Set<Ticket> ticketsForClosedBy) {
        this.ticketsForClosedBy = ticketsForClosedBy;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<TicketUpdate> getTicketUpdates() {
        return this.ticketUpdates;
    }
    
    public void setTicketUpdates(Set<TicketUpdate> ticketUpdates) {
        this.ticketUpdates = ticketUpdates;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="userByOpenedBy")
    public Set<Ticket> getTicketsForOpenedBy() {
        return this.ticketsForOpenedBy;
    }
    
    public void setTicketsForOpenedBy(Set<Ticket> ticketsForOpenedBy) {
        this.ticketsForOpenedBy = ticketsForOpenedBy;
    }




}


