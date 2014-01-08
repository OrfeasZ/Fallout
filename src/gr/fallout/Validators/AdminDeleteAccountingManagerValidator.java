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
public class AdminDeleteAccountingManagerValidator implements IValidator
{
    @Override
    public List<String> Validate(HashMap<String, List<String>> p_Parameters)
    {
        List<String> s_Errors = new ArrayList<String>();

        if (!p_Parameters.containsKey("user_id") || p_Parameters.get("user_id").get(0) == null)
            s_Errors.add("No user id specified.");

        try
        {
            Integer.parseInt(p_Parameters.get("user_id").get(0));
        }
        catch(Exception e)
        {
            s_Errors.add("User id is of an invalid format.");
        }

        return s_Errors;
    }
}
