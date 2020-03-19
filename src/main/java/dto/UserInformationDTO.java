package dto;

import entities.UserInformation;

/**
 *
 * @author malik
 */

//Link: https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
/*
The idea behind using a DTO is to expose as little as possible of valuable information from the different entities
And in that way we avoid also having to call our entity directly, but instead implement this middle layer.
*/
public class UserInformationDTO {
    private String fullname;
    private String email;
    private String phonenumber;

    public UserInformationDTO(UserInformation user) {
        this.fullname       = user.getFirstname() + " " + user.getLastname();
        this.email          = user.getEmail();
        this.phonenumber    = user.getPhonenumber();
    }

    @Override
    public String toString() {
        return "Information from DTO:\n" + "Fullname: " + fullname 
                + "\nEmail: " + email 
                + "\nPhonenumber: " + phonenumber + "\n";
    }
}
