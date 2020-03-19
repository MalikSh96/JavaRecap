package converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dto.UserInformationDTO;
import entities.UserInformation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author malik
 */

//Link: https://github.com/stleary/JSON-java
//Link: https://github.com/google/gson/blob/master/UserGuide.md
public class UserJSONconverter {
    //To see what GSON is use the below link
    //Link: https://github.com/google/gson
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public String getJSONFromUsers(List<UserInformation> users) 
    {  
        //We parse over to make use of our dto, that has filtered data
        List<UserInformationDTO> usersDTO = new ArrayList<>();
        /*
        We loop through our list for every UserInformation u in users
        */
        for (UserInformation u : users) {
            usersDTO.add(new UserInformationDTO(u));
        }
        return gson.toJson(usersDTO);
    }
    
    public Collection<UserInformationDTO> getUserInformationFromJSON(String js){
        //js is short for json
        //The first parameter in gson.fromJson is the json element, and the second one is the type class
        //UserInformation uf = gson.fromJson(js, UserInformation.class);
        //return uf;
        /*
        Commented above throws an error of the following type:
        java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY    
        
        The reason is as the exception shows, is because it expected an object but recieved and array insteaf
        To work around the problem I have followed the following solution by using the below link
        Link: https://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
        Link: https://github.com/google/gson/blob/master/UserGuide.md
        
        When trying to serialize and deserialize a generic object type, you are not allowed as that will throw
        the exception above.
        Because if the object is of generic type, the the Generic type information is lost because of Java Type Erasure.
        What likely happens is that the code fails to interpret value as type Userinformation because Gson invokes 
        UserInformation.getClass() to get its class information, but this method returns a raw class, UserInformation.class. 
        This means that Gson has no way of knowing that this is an object of type, and not just plain.

        We can solve this problem by specifying the correct parameterized type for our generic type. 
        We can do this by using the TypeToken class.
        */
        
        java.lang.reflect.Type collectionType = new TypeToken<Collection<UserInformationDTO>>(){}.getType();
        //js is short for json
        //The first parameter in gson.fromJson is the json element, and the second one is the type class
        Collection<UserInformationDTO> uf = gson.fromJson(js, collectionType);
        return uf;
    }
}
