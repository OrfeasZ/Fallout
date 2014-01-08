package gr.fallout.Validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:28 μμ
 *
 * @author OrfeasZ
 */
public class SalesCreateCustomerValidator implements IValidator
{
    @Override
    public List<String> Validate(HashMap<String, List<String>> p_Parameters)
    {   
        List<String> s_Errors = new ArrayList<String>();
        
        if (!p_Parameters.containsKey("taxid") || p_Parameters.get("taxid").get(0) == null)
            s_Errors.add("Please fill in a Tax id.");

        try
        {
            Integer.parseInt(p_Parameters.get("taxid").get(0));
        }
        catch(NumberFormatException e)
        {
            s_Errors.add("Tax id is of an invalid format.");
        }

        if (p_Parameters.containsKey("taxid") && p_Parameters.get("taxid").get(0) != null && p_Parameters.get("taxid").get(0).length() != 9)
            s_Errors.add("Tax id is of an invalid format.");



        if (!p_Parameters.containsKey("phone") || p_Parameters.get("phone").get(0) == null)
            s_Errors.add("Please fill in a phone number.");

        try
        {
            Integer.parseInt(p_Parameters.get("phone").get(0));
        }
        catch(NumberFormatException e)
        {
            s_Errors.add("Phone number is of an invalid format.");
        }

        if (p_Parameters.containsKey("phone") && p_Parameters.get("phone").get(0) != null && p_Parameters.get("phone").get(0).length() != 10)
            s_Errors.add("Phone number is of an invalid format.");

        if (!p_Parameters.containsKey("fax") || p_Parameters.get("fax").get(0) == null)
            s_Errors.add("Phone number is of an invalid format.");

        try
        {
            Integer.parseInt(p_Parameters.get("fax").get(0));
        }
        catch(NumberFormatException e)
        {
            s_Errors.add("Fax is of an invalid format.");
        }


        if (p_Parameters.containsKey("fax") && p_Parameters.get("fax").get(0) != null && p_Parameters.get("phone").get(0).length() != 10)
            s_Errors.add("Fax is of an invalid format.");

        if (!p_Parameters.containsKey("name") || p_Parameters.get("name").get(0) == null)
            s_Errors.add("Please fill in a name.");

        if (p_Parameters.containsKey("name") && p_Parameters.get("name").get(0) != null && p_Parameters.get("name").get(0).length() < 3)
            s_Errors.add("The specified name is invalid.");

        if (!p_Parameters.containsKey("username") || p_Parameters.get("username").get(0) == null)
            s_Errors.add("Please fill in a username.");

        if (p_Parameters.containsKey("username") && p_Parameters.get("username").get(0) != null && p_Parameters.get("username").get(0).length() < 4)
            s_Errors.add("The specified username is invalid.");

        if (!p_Parameters.containsKey("password") || p_Parameters.get("password").get(0) == null)
            s_Errors.add("Please fill in a password.");

        if (p_Parameters.containsKey("password") && p_Parameters.get("password").get(0) != null && p_Parameters.get("password").get(0).length() < 4)
            s_Errors.add("The specified password is invalid.");

        if (!p_Parameters.containsKey("address") || p_Parameters.get("address").get(0) == null)
            s_Errors.add("Please fill in an address.");

        if (p_Parameters.containsKey("address") && p_Parameters.get("address").get(0) != null && p_Parameters.get("password").get(0).length() < 4)
            s_Errors.add("The specified address is invalid.");



        return s_Errors; 
    }
}
