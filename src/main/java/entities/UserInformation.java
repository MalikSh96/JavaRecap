package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author malik
 */
@Entity
@Table(name = "userinformation")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM UserInformation u")})
/*
Named queries are one of the core concepts in JPA. They enable you to declare a query 
in your persistence layer and reference it in your business code. That makes it 
easy to reuse an existing query. It also enables you to separate the definition 
of your query from your business code.

Link: https://thoughts-on-java.org/spring-data-jpa-named-queries/
Link: https://www.objectdb.com/java/jpa/query/named
*/
public class UserInformation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull 
    private String firstname;
    @NotNull
    private String lastname;
    
    @Basic(optional = false) 
    @NotNull
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_password")
    private String password;
    
    private String phonenumber;
    private String city;
    private String address;
    private int zip;
    private String country;

    //Constructors that is needed
    
    //Creates and empty instance, makes it so no problems occur
    //An empty constructor is needed to create a new instance via reflection by your persistence framework
    public UserInformation() {
    }
    
    //Constructor which helps creating the user
    public UserInformation(String firstname, String lastname, String email, 
                            String password, String phonenumber, String city, 
                             String address, int zip, String country) {
        this.firstname = firstname; //this refers to the object we are looking at
        this.lastname = lastname;
        this.email = email;
        String salt = BCrypt.gensalt();
        String hashedpassword = BCrypt.hashpw(password, salt);
        this.password = hashedpassword;
        this.phonenumber = phonenumber;
        this.city = city;
        this.address = address;
        this.zip = zip;
        this.country = country;
    }
    
    //Constructor that will help retrieving the user by using the email 
    public UserInformation(String email) {
        this.email = email;
    }
    
    //Password hashing and salting
    public boolean verifyPassword(String pw) {
        return BCrypt.checkpw(pw, password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Following information found about the user:\n" 
                + "Firstname: " + firstname 
                + "\nLastname: " + lastname 
                + "\nEmail: " + email 
                + "\nPhonenumber: " + phonenumber
                + "\n";
    }
}
