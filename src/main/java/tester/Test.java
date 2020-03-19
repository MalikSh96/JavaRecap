package tester;

import backend.Users;
import entities.UserInformation;

/**
 *
 * @author malik
 */
public class Test {

    public static void main(String[] args) {
        String firstname    = "Souheib";
        String lastname     = "Dhaflaoui";
        String email        = "souheibdhaflaoui@hotmail.com";
        String password     = "souheib2200";
        String phonenumber  = "+45 87654321";
        String city         = "København";
        String address      = "Ydre københavnsgade 22";
        int zip             = 2200;
        String country      = "Denmark";

        //Create an instance of our userinformation
        UserInformation userinformation = new UserInformation(firstname, lastname, email, 
                                                password, phonenumber, city, 
                                                  address, zip, country);
        //create an instance of our users
        Users users = new Users();
        //create our user using our recent created instances
        //users.createUser(userinformation);
        //System.out.println("Following user created: " + users);
        
        System.out.println("Retrieving all users: " + users.getAllUsers());
    }
}
