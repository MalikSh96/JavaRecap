/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import entities.UserInformation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author malik
 */
public class Users {
    
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");

    public Users() {
    }
    
    private EntityManager manager(){
        EntityManager manager = factory.createEntityManager();
        return manager;
    }
    
    //Following commented code is commented because the call to the function manager() creates an error
    /*public UserInformation createUser(UserInformation user){
        manager();
        System.out.println("Before we try\n");
        try{
            System.out.println("Now we try\n");
            //we start transaction
            manager().getTransaction().begin();
            manager().persist(user); //we persist (insert) our user
            System.out.println("Check if user has been persisted: " + user);
            manager().getTransaction().commit(); <-- WE NEVER DO THIS, CODE DIES HERE
            //we return the user
            return user;
        }
        finally{
            //we close the transaction
            manager().close();
        }
    }*/
    
    public UserInformation createUser(UserInformation user) 
    {
        EntityManager manager = factory.createEntityManager();
        System.out.println("Before try\n");
        try
        {
            System.out.println("Now we try\n");
            manager.getTransaction().begin();
            manager.persist(user);
            System.out.println("Check if user has been persisted: " + user);
            manager.getTransaction().commit();
            return user;
        }
        finally
        {
            manager.close();
        }
    }
    
    public List<UserInformation> getAllUsers(){
        EntityManager manager = factory.createEntityManager();
        //create a query and we use our named query
        Query query = manager.createNamedQuery("User.findAll");
        //create a list of our object userinformation
        List<UserInformation> getAllUsers = new ArrayList<>();
        getAllUsers = query.getResultList();
        return getAllUsers;
    }
    
    public List<UserInformation> getAllUsersFromDTO(){
        EntityManager manager = factory.createEntityManager();
        //create a query that retrieves from our dto
        Query query = manager.createQuery("SELECT NEW dto.UserInformationDTO(u) FROM UserInformation AS u");
        //create a list of our object userinformation
        List<UserInformation> getAllUsers = new ArrayList<>();
        getAllUsers = query.getResultList();
        return getAllUsers;
    }
}
