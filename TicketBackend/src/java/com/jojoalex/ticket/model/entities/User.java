package com.jojoalex.ticket.model.entities;
// Generated 23-Apr-2018 2:15:56 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(
            name = "findUserByUserNameAndPassword",
            query = "from User u where u.username = :userName and password=:password"
    )
})
public class User  implements java.io.Serializable {


     private Integer id;
     private String fullname;
     private String password;
     private String email;
     private boolean admin;
     private Set<TicketUpdate> ticketUpdates = new HashSet<TicketUpdate>(0);
     private Set<Ticket> tickets = new HashSet<Ticket>(0);
     private Set<Client> clients = new HashSet<Client>(0);

    public User() {
    }

	
    public User(String fullname, String password, String email, boolean admin) {
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }
    public User(String fullname, String password, String email, boolean admin, Set<TicketUpdate> ticketUpdates, Set<Ticket> tickets, Set<Client> clients) {
       this.fullname = fullname;
       this.password = password;
       this.email = email;
       this.admin = admin;
       this.ticketUpdates = ticketUpdates;
       this.tickets = tickets;
       this.clients = clients;
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

    
    @Column(name="password", nullable=false, length=200)
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

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<TicketUpdate> getTicketUpdates() {
        return this.ticketUpdates;
    }
    
    public void setTicketUpdates(Set<TicketUpdate> ticketUpdates) {
        this.ticketUpdates = ticketUpdates;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Ticket> getTickets() {
        return this.tickets;
    }
    
    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    public Set<Client> getClients() {
        return this.clients;
    }
    
    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }




}


