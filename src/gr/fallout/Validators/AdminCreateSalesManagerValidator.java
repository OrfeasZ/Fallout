package gr.fallout.Validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Date: 9/12/2013
 * Time: 3:24 μμ
 *
 * @author OrfeasZ
 */
public class AdminCreateSalesManagerValidator implements IValidator
{
    @Override
    public List<String> Validate(HashMap<String, List<String>> p_Parameters)
    {
        List<String> s_Errors = new ArrayList<String>();

        if (!p_Parameters.containsKey("username") || p_Parameters.get("username").get(0) == null)
            s_Errors.add("Please fill in a username.");

        if (p_Parameters.containsKey("username") && p_Parameters.get("username").get(0) != null && p_Parameters.get("username").get(0).length() < 4)
            s_Errors.add("The specified username is invalid.");

        if (!p_Parameters.containsKey("password") || p_Parameters.get("password").get(0) == null)
            s_Errors.add("Please fill in a password.");

        if (p_Parameters.containsKey("password") && p_Parameters.get("password").get(0) != null && p_Parameters.get("password").get(0).length() < 4)
            s_Errors.add("The specified password is invalid.");

        return s_Errors;
    }
}
