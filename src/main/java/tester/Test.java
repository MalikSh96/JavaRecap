package tester;

import backend.Users;
import converter.UserJSONconverter;
import dto.UserInformationDTO;
import entities.UserInformation;
import java.util.Collection;

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
        
        //System.out.println("Retrieving all users: " + users.getAllUsersFromDTO());
        
        //TESTING IF CONVERTER TO JSON WORKS, validated by: https://jsonlint.com/
        UserJSONconverter conv = new UserJSONconverter();
        String json = conv.getJSONFromUsers(users.getAllUsers());
        //System.out.println("JSON format:\n" + json);
        
        //TESTING IF CONVERTER FROM JSON USING GSON WORKS
        Collection<UserInformationDTO> ufDTO;// = new UserInformation();
        ufDTO = conv.getUserInformationFromJSON(json);
        System.out.println("GSON format:\n" + ufDTO);
    }
}
